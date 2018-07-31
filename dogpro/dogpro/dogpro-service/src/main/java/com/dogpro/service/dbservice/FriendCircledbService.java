package com.dogpro.service.dbservice;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.dogpro.domain.model.FriendCircle;
import com.dogpro.domain.model.FriendCircleExample;
import com.dogpro.domain.model.extend.FriendCircleExtend;

public interface FriendCircledbService {
	public List<FriendCircle> selectByExample(FriendCircleExample example);
	//获取联盟列表+更新朋友圈id列表
	public Map<String, Object> getallianceList(Long userId,int pageSize,Long friendCirId,int isRefresh,Long topfriendCirCode,Long bottomfriendCirCode,Long requestCode);
	//获取联盟列表
	public List<Map<String, Object>> getFCList(Long userId, int pageSize,Long friendCirCode, int isRefresh);
	//获取更新朋友圈id列表
	public List<Long> getUpdateFCid(Long topfriendCirCode,Long bottomfriendCirCod,Long requestCode);
	//获取首页单条朋友圈信息
	public Map<String, Object> getSingelFC(Long userId,FriendCircleExtend friendCircleExtend);
	
	
	//获取单条朋友圈对应详情(朋友圈内容+点赞情况)
	public Map<String, Object> getFCdetail(Long friendCir_id,Long userId);
	//发布朋友圈
	public int publishFC(FriendCircle friendCircle,List<Map<String,Object>> media,HttpServletRequest request);
	//我的 详情
	public Map<String, Object> Mine(Long userId);
	//我的相册
	public List<Map<String, Object>> minePhotoAlbum(Long userId,int pageNO,int pageSize);
	//我的朋友圈
	public List<Map<String, Object>> mineFriendCircle(Long userId,int pageNO,int pageSize);
	/**
	 * 删除朋友圈
	 * @return
	 */
	public int delFriendCir(Long friendCirId);
	/**
	 * 获取朋友圈 第一张媒体url(缩略图 视频截图)
	 * @param friendCircleId
	 * @return
	 */
	public String getFCfirstMedia(Long friendCircleId);
	/**
	 * 设置朋友圈 更新code
	 * @param friendCirId
	 * @param date
	 * @return
	 */
	public boolean setFCupdateCode(Long friendCirId,Long updateCode);
}
