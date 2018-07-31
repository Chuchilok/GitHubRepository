package com.dogpro.service.webapi;

import com.dogpro.common.Interfacetool.ParameterObject;
import com.dogpro.common.Interfacetool.ResultObject;

/**
 * 遛狗地点接口
 * @author 许明诚
 *
 */
public interface DogLocationService {
	/**
	 * 地点列表
	 * @param parameterObject
	 * @return
	 */
	public ResultObject locationList(ParameterObject parameterObject);
	/**
	 * 新增地点
	 * @param parameterObject
	 * @return
	 */
	public ResultObject addLocation(ParameterObject parameterObject);
	/**
	 * 修改地点
	 * @param parameterObject
	 * @return
	 */
	public ResultObject alterLocation(ParameterObject parameterObject);
	/**
	 * 删除地点
	 * @param parameterObject
	 * @return
	 */
	public ResultObject deleteLocation(ParameterObject parameterObject);
	/**
	 * 搜索地点
	 * @param parameterObject
	 * @return
	 */
	public ResultObject searchLocation(ParameterObject parameterObject);
	/**
	 * 分页获取 同地区 按 距离由近至远 分页 遛狗地点
	 * @param parameterObject
	 * @return
	 */
	public ResultObject dogLocationDistanceList(ParameterObject parameterObject);

}
