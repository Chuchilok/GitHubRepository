package com.webpublish.common.utils;

import javax.servlet.http.HttpServletRequest;

public class NETTools {
	/**
	 * 通过HttpServletRequest返回IP地址
	 * @param request HttpServletRequest
	 * @return ip String
	 * @throws Exception
	 */
	public static String getIpAddr(HttpServletRequest request) throws Exception {
	    String ip = request.getHeader("x-forwarded-for");
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("HTTP_CLIENT_IP");
	    }
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	    }
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getRemoteAddr();
	    }
	    return ip;
	}
	
	  /**
     * 获取客户端源端口
     * @param request
     * @return
     */
    public static Integer getRemotePort(final HttpServletRequest request){
        try{
//            String port = request.getHeader("remote-port");
//            if(port!=null&&port.length()>0) {
//                try{
//                    return Integer.parseInt(port);
//                }catch(NumberFormatException ex){
//                    return 0;
//                }
//            }else{
//                return 0;
//            }   
        	int port = request.getRemotePort();
        	return port;
        }catch(Exception e){
            return 0;
        }
    }
}
