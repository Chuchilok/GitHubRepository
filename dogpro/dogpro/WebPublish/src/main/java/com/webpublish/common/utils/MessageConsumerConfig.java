package com.webpublish.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

import org.aspectj.apache.bcel.util.ClassPath;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class MessageConsumerConfig {
	public static HashMap readConfig(String fileName){
		HashMap hashMap = new HashMap(); 
	        Properties pro =  new  Properties();    
	        Resource  res=new ClassPathResource( fileName );
	       
  
	         try  {    
	        	 InputStream in = res.getInputStream();
	        	 		pro.load(in);      
						Enumeration enu2 = pro.propertyNames();
						while (enu2.hasMoreElements()) {
							String key = (String) enu2.nextElement();
							String value = pro.getProperty(key);
							hashMap.put(key, value);
						} 
		 
		        }  catch  (IOException e) {    
		            e.printStackTrace();    
		        }  
	         
		return hashMap;
	}

}
