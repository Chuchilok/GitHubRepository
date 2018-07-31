package com.dogpro.lucene;

import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dogpro.domain.model.DogLocation;
import com.dogpro.lucene.index.DoglocationIndex;
import com.dogpro.lucene.util.LuceneQueue;
import com.dogpro.service.dbservice.RedisdbService;

//@Component
////声明这是一个切面Bean
@Aspect
public class LuceneAOP {
	// 新增遛狗地点切点
	@AfterReturning(pointcut = "execution (* com.dogpro.service.impl.dbservice.DogLocationdbServiceImpl.addLocation(..))", returning = "returnValue")
	public void addLocation(JoinPoint jp, Object returnValue) throws Exception {
		DogLocation dogLocation = (DogLocation) returnValue;
//		DoglocationIndex doglocationIndex = new DoglocationIndex();
//		// 插入Lucene库
//		doglocationIndex.testCreateIndex(dogLocation);
		if(dogLocation!=null){
			LuceneQueue.pushQueue(dogLocation);
		}
	}

	// 修改遛狗地点切点
	@AfterReturning(pointcut = "execution (* com.dogpro.service.impl.dbservice.DogLocationdbServiceImpl.alterLocation(..))", returning = "returnValue")
	public void alterLocation(JoinPoint jp, Object returnValue)
			throws Exception {
		DogLocation dogLocation = (DogLocation) returnValue;
//		DoglocationIndex doglocationIndex = new DoglocationIndex();
//		// 修改该Lucene库
//		doglocationIndex.testUpdateIndex(dogLocation);
		if(dogLocation!=null){
			LuceneQueue.pushQueue(dogLocation);
		}
	}

	// 删除遛狗地点切点
	@AfterReturning(pointcut = "execution (* com.dogpro.service.impl.dbservice.DogLocationdbServiceImpl.deleteLocation(..))", returning = "returnValue")
	public void deleteLocation(JoinPoint jp, Object returnValue)
			throws Exception {
		Long dogLocationId = (Long) returnValue;
		DoglocationIndex doglocationIndex = new DoglocationIndex();
		// 修改该Lucene库
		if(dogLocationId>0){
			doglocationIndex.testDeleteIndex(dogLocationId);
		}
//		DoglocationIndex doglocationIndex2 = new DoglocationIndex();
//		List<DogLocation> dogLocations = doglocationIndex2.testSearchIndex("南海");
//		for(DogLocation dogLocation:dogLocations){
//			System.out.println(dogLocation.getId());
//		}
	}

}
