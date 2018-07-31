package com.dogpro.lucene;

import java.math.BigDecimal;
import java.util.List;

import com.dogpro.domain.model.DogLocation;
import com.dogpro.lucene.index.DoglocationIndex;
import com.dogpro.lucene.util.LuceneQueue;


public class test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		DogLocation dogLocation = new DogLocation();
		dogLocation.setId(1L);
		dogLocation.setPid(0L);
		dogLocation.setAreaname("千灯湖");
		dogLocation.setAddressalias("南海千灯湖公园");
		dogLocation.setProvinces("广东省");
		dogLocation.setMunicipalities("佛山市");
		dogLocation.setDistricts("南海区");
		dogLocation.setTownstreet("街道");
		dogLocation.setAddress("广东省佛山市南海区灯湖西路");
		dogLocation.setLongitude(new BigDecimal(113.1476600000));
		dogLocation.setLatitude(new BigDecimal(23.0496800000));
		dogLocation.setPerimeter(1f);
		dogLocation.setCreatoruserid(1l);
		dogLocation.setHot(1);
		dogLocation.setOrders(1);
		
		LuceneQueue.pushQueue(dogLocation);
		LuceneQueue.popQueue();
		
//		DoglocationIndex doglocationIndex = new DoglocationIndex();
//		doglocationIndex.testCreateIndex(dogLocation);
//		List<DogLocation> dogLocations = doglocationIndex.testSearchIndex("南海");
//		for(DogLocation dLocation:dogLocations){
//			System.out.println(dLocation.getAddress());
//		}
//		doglocationIndex.testDeleteIndex(1l);
	}

}
