package com.dogpro.service.webapi;

import com.dogpro.common.Interfacetool.ParameterObject;
import com.dogpro.common.Interfacetool.ResultObject;

public interface AllianceService {
	//联盟列表
	public ResultObject allianceList(ParameterObject parameterObject);
	//单条朋友圈详情
	public ResultObject allianceDetail(ParameterObject parameterObject);
	//发布朋友圈
	public ResultObject publishFriendCir(ParameterObject parameterObject);
	//我的详情接口
	public ResultObject mine(ParameterObject parameterObject);
	//我的相册
	public ResultObject minePhotoAlbum(ParameterObject parameterObject);
	//我的朋友圈接口
	public ResultObject mineFriendCircle(ParameterObject parameterObject);
	//删除朋友圈
	public ResultObject delFriendCir(ParameterObject parameterObject);
}
