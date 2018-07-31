package com.dogpro.lucene.util;

import java.math.BigDecimal;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;

import com.dogpro.domain.model.DogLocation;



public class DocumentUtils {
	/**
	 * 从Article转化为Document
	 * @param article
	 * @return
	 */
	public static Document DogLocation2Document(DogLocation dogLocation){
//		Document document = new Document();
//		Field idField = new Field("id",article.getId().toString(),Store.YES,Index.NOT_ANALYZED);
//		Field titleField = new Field("title",article.getTitle(),Store.YES,Index.ANALYZED);
//		Field contentField = new Field("content",article.getContent(),Store.YES,Index.ANALYZED);
//		document.add(idField);
//		document.add(titleField);
//		document.add(contentField);
//		return document;
		
		Document document = new Document();
		//地点id
		Field idField = new Field("id", dogLocation.getId().toString(),Store.YES,Index.NOT_ANALYZED);
		//PID
		Field PIDField = new Field("PID", dogLocation.getPid().toString(),Store.YES,Index.NOT_ANALYZED);
		//区域名
		Field areaNameField = new Field("areaName", dogLocation.getAreaname(),Store.YES,Index.ANALYZED);
		//地点别名
		Field addressAliasField = new Field("addressAlias", dogLocation.getAddressalias(),Store.YES,Index.ANALYZED);
		//省
		Field provincesField = new Field("provinces", dogLocation.getProvinces(),Store.YES,Index.ANALYZED);
		//市
		Field municipalitiesField = new Field("municipalities", dogLocation.getMunicipalities(),Store.YES,Index.ANALYZED);
		//区
		Field districtsField = new Field("districts", dogLocation.getDistricts(),Store.YES,Index.ANALYZED);
		//街道
		Field townStreetField = new Field("townStreet", dogLocation.getTownstreet(),Store.YES,Index.ANALYZED);
		//地址
		Field addressField = new Field("address", dogLocation.getAddress(),Store.YES,Index.ANALYZED);	
		//经度
		Field longitudeField = new Field("longitude", dogLocation.getLongitude().toString(),Store.YES,Index.NOT_ANALYZED);
		//纬度
		Field latitudeField = new Field("latitude", dogLocation.getLatitude().toString(),Store.YES,Index.NOT_ANALYZED);
		//周边几公里
		Field perimeterField = new Field("perimeter", dogLocation.getPerimeter().toString(),Store.YES,Index.NOT_ANALYZED);
		//新建用户id
		Field creatorUserIdField = new Field("creatorUserId", dogLocation.getCreatoruserid().toString(),Store.YES,Index.NOT_ANALYZED);
		//热度
		Field hotIdField = new Field("hot", dogLocation.getHot().toString(),Store.YES,Index.NOT_ANALYZED);
		//排序
		Field ordersField = new Field("orders", dogLocation.getOrders().toString(),Store.YES,Index.NOT_ANALYZED);
		
		document.add(idField);
		document.add(PIDField);
		document.add(areaNameField);
		document.add(addressAliasField);
		document.add(provincesField);
		document.add(municipalitiesField);
		document.add(districtsField);
		document.add(townStreetField);
		document.add(addressField);
		document.add(longitudeField);
		document.add(latitudeField);
		document.add(perimeterField);
		document.add(creatorUserIdField);
		document.add(hotIdField);
		document.add(ordersField);
		return document;
	}
	/**
	 * 从Document转化为Article
	 * @param document
	 * @return
	 */
	public static DogLocation document2DogLocation(Document document){
//		Article article = new Article();
//		article.setId(Long.parseLong(document.get("id")));
//		article.setTitle(document.get("title"));
//		article.setContent(document.get("content"));
//		return article;
		
		DogLocation dogLocation = new DogLocation();
		dogLocation.setId(Long.parseLong(document.get("id")));
		dogLocation.setPid(Long.parseLong(document.get("PID")));
		dogLocation.setAreaname(document.get("areaName"));
		dogLocation.setAddressalias(document.get("addressAlias"));
		dogLocation.setProvinces(document.get("provinces"));
		dogLocation.setMunicipalities(document.get("municipalities"));
		dogLocation.setDistricts(document.get("districts"));
		dogLocation.setTownstreet(document.get("townStreet"));
		dogLocation.setAddress(document.get("address"));
		dogLocation.setLongitude(new BigDecimal(document.get("longitude")));
		dogLocation.setLatitude(new BigDecimal(document.get("latitude")));
		dogLocation.setPerimeter(Float.valueOf(document.get("perimeter")));
		dogLocation.setCreatoruserid(Long.parseLong(document.get("creatorUserId")));
		dogLocation.setHot(Integer.parseInt(document.get("hot")));
		dogLocation.setOrders(Integer.parseInt(document.get("orders")));
		return dogLocation;
	}
}
