package com.webpublish.common.interfacetool;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.webpublish.service.dbservice.UserTokendbService;
public class DefaultServiceHandler implements ServiceHandler {
	
	
	
	public ResultObject handle(ParameterObject parameterObject) {
		UserTokendbService userTokendbService = (UserTokendbService) ApplicationContextHelper.getBean("UserTokendbService");
		Object serviceObject = ApplicationContextHelper.getBean(parameterObject.getAction());
		if (serviceObject == null) {
			throw new MobileException("服务对象不存在：" + parameterObject.getAction());
		} 
		//判断是否传入token
		String ActionName = parameterObject.getAction();
		//若不是检测用户Service
		if(!ActionName.equals("UserService")){
			try {
				Long userId = parameterObject.getLongParameter("userId");
				String token = parameterObject.getToken();
				if(!userTokendbService.matchUserToken(userId, token)){
					throw new MobileException("用户ID对应token不正确");
				}
			} catch (Exception e) {
				// TODO: handle exception
				throw new MobileException("用户ID对应token不正确");
			}
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