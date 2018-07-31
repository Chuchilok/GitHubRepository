package com.dogpro.admin.service.impl.dbservice;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogpro.admin.service.dbservice.AdminTokendbService;
import com.dogpro.common.tool.MyUtil;
import com.dogpro.common.tool.UUIDGenerator;
import com.dogpro.domain.model.Token;

@Service("AdminTokendbService")
public class AdminTokendbServiceImpl implements AdminTokendbService {
	public static Map<Long, Object> map = new HashMap<Long, Object>();
 

	public String updateToken(long adminUserId) { 
		String token = UUIDGenerator.getToken64();
		Date date = new Date();
		Token record = new Token();
		record.setToken(token);
		record.setUpdateTimes(date);
		record.setLastUpdateTime(date);
		if(map.get(adminUserId)==null){
			record.setUserType(1);// 管理用户类型
			record.setUserId(adminUserId);
			record.setState(1);
			record.setAddTimes(date);
			map.put(adminUserId, record);
		}else{
			map.put(adminUserId, record);
		}
		return token;
	}

	public Map<String, Object> getTokenByAdminUserId(Long adminUserId) {
		Map<String, Object> m = new HashMap<String, Object>();
		Token record = (Token) map.get(adminUserId);
		if(record==null){
			record = new Token();
			record.setLastUpdateTime(MyUtil.getNowDate());
		}
		Date d = new Date();
		Long dateDiff_Minute = MyUtil.getDateDiff_Minute(
				record.getLastUpdateTime(), d);
		m.put("minute", dateDiff_Minute);
		record.setLastUpdateTime(d);
		map.put(adminUserId, record);
		int isErr = 1;
		m.put("isErr", isErr);
		m.put("token", record.getToken());
		return m;
	}

}
