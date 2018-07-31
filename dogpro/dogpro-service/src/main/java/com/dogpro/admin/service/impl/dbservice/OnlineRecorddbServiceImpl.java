package com.dogpro.admin.service.impl.dbservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.PaginationUtil;

import com.dogpro.admin.service.dbservice.AdminRedisdbService;
import com.dogpro.admin.service.dbservice.OnlineRecorddbService;
import com.dogpro.common.tool.MyUtil;
import com.dogpro.dao.OnlineRecordMapper;
import com.dogpro.domain.model.OnlineRecord;
import com.dogpro.domain.model.OnlineRecordExample;

@Service("OnlineRecorddbService")
public class OnlineRecorddbServiceImpl implements OnlineRecorddbService {
	@Autowired
	public AdminRedisdbService adminRedisdbService;
	@Autowired
	public OnlineRecordMapper onlineRecordMapper;
	
	public Map<String, Object> getTotalOnlineUsers() {
		//返回参数
		Map<String, Object> result = new HashMap<String, Object>();
		Long totalOnlineUsers = adminRedisdbService.SCARDonlineuser();
		if(totalOnlineUsers==null){
			totalOnlineUsers = 0l;
			result.put("totalOnlineUsers", totalOnlineUsers);
			result.put("flag", 0);
			result.put("msg", "查询失败");
		}else{
			result.put("totalOnlineUsers", totalOnlineUsers);
			result.put("flag", 1);
			result.put("msg", "查询成功");
		}
		return result;
	}

	public List<Map<String, Object>> getOnlineRecord(int pageNO, int pageSize) {
		int totalnum = adminRedisdbService.LLENonlineRecord().intValue();
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		if(totalnum>pageNO*pageSize){
			int start = pageSize*pageNO;
			int stop = pageSize*(pageNO+1)-1;
			List<OnlineRecord> onlineRecords = adminRedisdbService.LRANGEonlineRecord(start, stop);
			int index = 0;
			for(OnlineRecord onlineRecord:onlineRecords){
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("recordNo", start+index);
				model.put("totalOnlineUsers", onlineRecord.getTotalOnlineUser());
				model.put("recordTime", MyUtil.dateToString(onlineRecord.getRecordTime()));
				model.put("state", onlineRecord.getState());
				model.put("isDB", 0);
				result.add(model);
				index ++;
			}
			if(index<pageSize){
				List<OnlineRecord> dbOnlineRecords = getDBonlineRecords(0, pageSize-index);
				for(OnlineRecord onlineRecord:dbOnlineRecords){
					Map<String, Object> model = new HashMap<String, Object>();
					model.put("recordNo", onlineRecord.getOnlineRecordId());
					model.put("totalOnlineUsers", onlineRecord.getTotalOnlineUser());
					model.put("recordTime", MyUtil.dateToString(onlineRecord.getRecordTime()));
					model.put("state", onlineRecord.getState());
					model.put("isDB", 1);
					result.add(model);
				}
			}
		}else{
			//差值
			int dvalue = pageNO*pageSize-totalnum;
			//db需要补的记录 
			List<OnlineRecord> dList = getDBonlineRecords(0, dvalue%pageSize);
			List<Long> recordId = new ArrayList<Long>();
			for(OnlineRecord onlineRecord:dList){
				recordId.add(onlineRecord.getOnlineRecordId());
			}
			//分页 查 db
			OnlineRecordExample oExample = new OnlineRecordExample();
			OnlineRecordExample.Criteria oCriteria = oExample.createCriteria();
			oCriteria.andStateEqualTo(1);
			oCriteria.andOnlineRecordIdNotIn(recordId);
			oExample.setOrderByClause("record_time desc");
			PaginationUtil paginationUtil = new PaginationUtil(dvalue/pageSize,pageSize);
			oExample.setPagination(paginationUtil);
			List<OnlineRecord> dbOnlineRecords = onlineRecordMapper.selectByExample(oExample);
			for(OnlineRecord onlineRecord:dbOnlineRecords){
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("recordNo", onlineRecord.getOnlineRecordId());
				model.put("totalOnlineUsers", onlineRecord.getTotalOnlineUser());
				model.put("recordTime", MyUtil.dateToString(onlineRecord.getRecordTime()));
				model.put("state", onlineRecord.getState());
				model.put("isDB", 1);
				result.add(model);
			}
		}
		return result;
	}

	public Map<String, Object> onlineRecordToDB(int recordNO) {
		//返回参数
		Map<String, Object> result = new HashMap<String, Object>();
		Long len  = adminRedisdbService.LLENonlineRecord();
		int flag = 0;
		String msg = "操作失败";
		int index;
		for(index = recordNO;index<len;index++){
			OnlineRecord onlineRecord = adminRedisdbService.RPOPonlineRecord();
			if(onlineRecord!=null){
				onlineRecordMapper.insertSelective(onlineRecord);
			}else{
				break;
			}
		}
		if(index>=len){
			flag = 1;
			msg = "操作成功";
		}
		result.put("flag", flag);
		result.put("msg", msg);
		return result;
	}

	public Long getTotalOnlineRecord() {
		// TODO Auto-generated method stub
		return adminRedisdbService.LLENonlineRecord();
	}
	
	public int getTotalDBonlineRecord(){
		OnlineRecordExample oExample = new OnlineRecordExample();
		OnlineRecordExample.Criteria oCriteria = oExample.createCriteria();
		oCriteria.andStateEqualTo(1);
		oExample.setOrderByClause("record_time desc");
		return onlineRecordMapper.countByExample(oExample);
	}
	
	public List<OnlineRecord> getDBonlineRecords(int pageNO,int pageSize){
		OnlineRecordExample oExample = new OnlineRecordExample();
		OnlineRecordExample.Criteria oCriteria = oExample.createCriteria();
		oCriteria.andStateEqualTo(1);
		oExample.setOrderByClause("record_time desc");
		PaginationUtil paginationUtil = new PaginationUtil(pageNO,pageSize);
		oExample.setPagination(paginationUtil);
		return onlineRecordMapper.selectByExample(oExample);
	}
	
}
