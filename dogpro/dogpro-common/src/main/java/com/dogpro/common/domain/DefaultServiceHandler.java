package com.dogpro.common.domain;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import com.dogpro.common.domain.CheckUser;
import com.dogpro.common.tool.JedisManager;
import com.dogpro.common.tool.JedisUtil;
import com.dogpro.common.tool.MessageConsumerConfig;
import com.dogpro.common.tool.ObjectUtil;

import redis.clients.jedis.Jedis;

 

public class DefaultServiceHandler implements ServiceHandler {
	public ResultObject handle(ParameterObject parameterObject) {
		Object serviceObject = ApplicationContextHelper.getBean(parameterObject.getAction());
		
		if (serviceObject == null) {
			throw new MobileException("服务对象不存在：" + parameterObject.getAction());
		} 
		
		Class<?> classType = serviceObject.getClass();
		try {
			
			Method method = classType.getMethod(parameterObject.getModule(), new Class[] { ParameterObject.class });
			return (ResultObject) method.invoke(serviceObject, new Object[] { parameterObject });
		} catch (SecurityException e) {
			throw new MobileException("服务对象无法访问");
		} catch (NoSuchMethodException e) {
			throw new MobileException("服务对象方法不存在：" + parameterObject.getAction() + "." + parameterObject.getModule());
		} catch (IllegalArgumentException e) {
			throw new MobileException("服务对象方法参数不正确");
		} catch (IllegalAccessException e) {
			throw new MobileException("服务对象无法访问");
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		throw new MobileException("服务对象无法访问");
	}
	/**
	 * 返回表格填充 数据
	 * @param parameterObject
	 * @return
	 */
	public DataGridResult dataGridHandle(ParameterObject parameterObject) {
		Object serviceObject = ApplicationContextHelper.getBean(parameterObject.getAction());
		if (serviceObject == null) {
			throw new MobileException("服务对象不存在：" + parameterObject.getAction());
		} 
		Class<?> classType = serviceObject.getClass();
		try {
			Method method = classType.getMethod(parameterObject.getModule(), new Class[] { ParameterObject.class });
			return (DataGridResult) method.invoke(serviceObject, new Object[] { parameterObject });
		} catch (SecurityException e) {
			throw new MobileException("服务对象无法访问");
		} catch (NoSuchMethodException e) {
			throw new MobileException("服务对象方法不存在：" + parameterObject.getAction() + "." + parameterObject.getModule());
		} catch (IllegalArgumentException e) {
			throw new MobileException("服务对象方法参数不正确");
		} catch (IllegalAccessException e) {
			throw new MobileException("服务对象无法访问");
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		throw new MobileException("服务对象无法访问");
	}
}