package com.dogpro.admin.service.impl.webapi;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.dogpro.admin.excelutil.ExcelUtil;
import com.dogpro.admin.service.dbservice.AdminDogLocationdbService;
import com.dogpro.admin.service.webapi.ExcelService;
import com.dogpro.common.Interfacetool.ResultObject;
import com.dogpro.common.tool.MessageConsumerConfig;
import com.dogpro.common.tool.UUIDGenerator;
import com.dogpro.domain.model.DogLocation;
@Service("ExcelService")
public class ExcelServiceImpl implements ExcelService{

	@Autowired
	private AdminDogLocationdbService adminDogLocationdbService;

	private Map packagesMap = MessageConsumerConfig
			.readConfig("config.properties");
	private String default_groupPic = packagesMap.get("default_groupPic")
			.toString().trim();
	public ResultObject importExcel(CommonsMultipartFile file) throws IOException {
		ResultObject result = new ResultObject();
		Map<String, Object> map = ExcelUtil.loadExl(file);
		int res = 0;
		if ("1".equals(map.get("code").toString())) {
			List<Map<String,String>> infoList = (List<Map<String, String>>) map.get("result");
			// 管理员 创建的 地点
			for (Map<String, String> info : infoList) {
				DogLocation dogLocation=new DogLocation();
				dogLocation.setAddressalias(info.get("addressAlias"));
				dogLocation.setAreaname(info.get("areaName"));
				dogLocation.setProvinces(info.get("provinces"));
				dogLocation.setMunicipalities(info.get("municipalities"));
				dogLocation.setDistricts(info.get("districts"));
				dogLocation.setTownstreet(info.get("townStreet"));
				dogLocation.setPerimeter(Float.valueOf(info.get("perimeter")));
				String longAndLat = info.get("longitudeAndLatitude");
				if (!"".equals(longAndLat)) {
					String[] split = longAndLat.split(",");
					BigDecimal longitude=new BigDecimal(split[0]);
					BigDecimal latitude=new BigDecimal(split[1]);
					dogLocation.setLongitude(longitude);
					dogLocation.setLatitude(latitude);
				}
				dogLocation.setHot(Integer.valueOf(info.get("hot")));
				dogLocation.setOrders(Integer.valueOf(info.get("orders")));
				dogLocation.setCreatoruserid(0l);
				dogLocation.setPid(0l);
				dogLocation.setState(1);
				dogLocation.setKeyss(UUIDGenerator.getToken64());
				String address = dogLocation.getProvinces()
						+ dogLocation.getMunicipalities()
						+ dogLocation.getDistricts() + dogLocation.getTownstreet();
				dogLocation.setAddress(address);
				if (dogLocation.getLocationpic() == null
						|| dogLocation.getLocationpic().equals("")) {
					dogLocation.setLocationpic(default_groupPic);
				}
				Date currentTime=new Date();
				dogLocation.setAddtimes(currentTime);
				dogLocation.setUpdatetimes(currentTime);
				DogLocation DogLocation2 = adminDogLocationdbService
						.addLocation(dogLocation);// 修改遛狗地点	
				if (DogLocation2 != null) {
					res = res + 1;
				}
			}
			
		}
		result.setState(ResultObject.STATE_SUCCESS);
		result.setMessage(map.get("msg").toString());
		result.setCode(ResultObject.CODE_STATE_SUCCESS);
		result.setResult(res);
		return result ;
	}

}
