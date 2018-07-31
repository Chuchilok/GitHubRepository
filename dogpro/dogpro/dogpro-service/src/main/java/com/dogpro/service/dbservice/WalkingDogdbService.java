package com.dogpro.service.dbservice;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.dogpro.domain.model.User;
import com.dogpro.domain.model.WalkingDogGroup;
import com.dogpro.domain.model.WalkingDogTrack;

/**
 * 遛狗接口
 * @author Administrator
 *
 */
public interface WalkingDogdbService {

	int joinGroup(WalkingDogGroup walkingDogGroup);

	/**
	 * 退出遛狗组
	 * @param walkingDogGroup
	 * @param isinitiactive 主动退出遛狗组：true  被动退出遛狗组（3小时被踢）：false
	 * @return
	 */
	int quitGroup(WalkingDogGroup walkingDogGroup,boolean isinitiactive);

	List<User> readGroupUsers(long userId, long doglocationId,Integer isContain,int pageNo,int pageSize);
	
	int uploadTrack(Long userId,Long groupId,BigDecimal longitude,BigDecimal latitude);
	/**
	 * 遛狗轨迹从redis插入数据库
	 */
	int redis2Mysql(WalkingDogTrack walkingDogTrack);
	/**
	 * 匹配遛狗组
	 */
	Map<String, Object> matchGroup(long userId,long doglocationId,BigDecimal longitude,BigDecimal latitude);
	/**
	 * 如果为第一人，退出匹配
	 */
	int exitMatchGroup(long userId,long doglocationId,BigDecimal longitude,BigDecimal latitude);

	List<User> readGroupUsersAndI(long userId, long doglocationId);
	
	/**
	 * 根据userId获取订阅的群组keys
	 * @param userId
	 * @return
	 */
	public Map<String, Object> getJoinGroupKeys(Long userId);
	
	/**
	 * 通过遛狗地点id（相当于群id）查找对应的UserId
	 * @param dogLocation
	 * @return
	 */
	public List<Long> findUserIdByDogLocation(Long dogLocation);
	
	public Map<String, Object> endWalkingDog(long userId,long doglocationId,BigDecimal longitude,BigDecimal latitude);
	/**
	 * 调用api 订阅群组topic
	 * @param userId
	 * @param doglocationId
	 * @return
	 */
	public boolean subscribeGroupTopic(Long userId,Long doglocationId);
	/**
	 * 若用户存在遛狗组 则返回遛狗组 遛狗地点Id 否则返回0
	 * @param userId
	 * @return
	 */
	public Long getDogLocationIdByUid(Long userId);
	
}
