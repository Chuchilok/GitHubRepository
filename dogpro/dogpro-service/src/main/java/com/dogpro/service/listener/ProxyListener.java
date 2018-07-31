package com.dogpro.service.listener;



import java.net.Authenticator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.dogpro.common.tool.BasicAuthenticator;
import com.dogpro.common.tool.MessageConsumerConfig;

public class ProxyListener implements ServletContextListener {

	
	
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void contextInitialized(ServletContextEvent arg0) {
		Map packagesMap = MessageConsumerConfig.readConfig("config.properties");
		if(packagesMap.get("isproxy")!=null&&packagesMap.get("isproxy").equals("true"))
		{
			String proxy_host = packagesMap.get("proxy_host").toString();
			String proxy_port = packagesMap.get("proxy_port").toString();
			String proxy_username = packagesMap.get("proxy_username").toString();
			String proxy_password = packagesMap.get("proxy_password").toString();
			
			Properties prop = System.getProperties();
		    // 设置http访问要使用的代理服务器的地址
		    prop.setProperty("http.proxyHost", proxy_host);
		    // 设置http访问要使用的代理服务器的端口
		    prop.setProperty("http.proxyPort",proxy_port);
		    // 设置不需要通过代理服务器访问的主机，可以使用*通配符，多个地址用|分隔
//		    prop.setProperty("http.nonProxyHosts", "localhost|192.168.0.*");
		    // 设置安全访问使用的代理服务器地址与端口
		    // 它没有https.nonProxyHosts属性，它按照http.nonProxyHosts 中设置的规则访问
		    prop.setProperty("https.proxyHost", proxy_host);
		    prop.setProperty("https.proxyPort", proxy_port);
		    // 使用ftp代理服务器的主机、端口以及不需要使用ftp代理服务器的主机
//		    prop.setProperty("ftp.proxyHost", "183.45.78.31");
//		    prop.setProperty("ftp.proxyPort", "21");
//		    prop.setProperty("ftp.nonProxyHosts", "localhost|192.168.0.*");
		    // socks代理服务器的地址与端口
//		    prop.setProperty("socksProxyHost", "183.45.78.31");
//		    prop.setProperty("socksProxyPort", "1080");
			
			if(proxy_username!=null&&!proxy_username.equals("")){
				// 设置登陆到代理服务器的用户名和密码
			    Authenticator.setDefault(new BasicAuthenticator(proxy_username, proxy_password));
			}
		}
		
		
	    
	}


}





