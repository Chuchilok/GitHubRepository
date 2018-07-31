package com.dogpro.admin.service.webapi;

import com.dogpro.common.Interfacetool.DataGridResult;
import com.dogpro.common.Interfacetool.ParameterObject;
import com.dogpro.common.Interfacetool.ResultObject;

/**
 *	版本    管理类
 * @author Administrator
 *
 */
public interface VersionControlService {
	/**
	 * 返回版本的信息（分页）
	 * @param paramterObject
	 * @return
	 */
	public  DataGridResult findVersionList(ParameterObject paramterObject);
	/**
	 * 进行VersionControl 的操作>>根据type类型判断操作 增加，编辑，删除
	 * @param paramterObject
	 * @return 
	 */
	public  ResultObject operateVersionControl(ParameterObject paramterObject);
	
	/**
	 * app调用的检测版本更新
	 * @param paramterObject
	 * @return
	 */
	public ResultObject checkVersion(ParameterObject paramterObject);
	
	/**
	 * IOS检测版本更新
	 * @param paramterObject
	 * @return
	 */
	public ResultObject IOSCheckVersion(ParameterObject paramterObject);
}
