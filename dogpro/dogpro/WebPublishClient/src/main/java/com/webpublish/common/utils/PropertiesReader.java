package com.webpublish.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesReader {
	
	public Map<String, String> getProperties(String propertiesName){
	       Properties props = new Properties();
	       Map<String, String> map=new HashMap<String, String>();
	       try {
	        InputStream in= getClass().getResourceAsStream(propertiesName);
	        props.load(in);
	        in.close();
	        Enumeration en=props.propertyNames();
	        while (en.hasMoreElements()) {
	            String key=(String) en.nextElement();
	            String property=props.getProperty(key);
	            map.put(key, property);
//	            System.out.println(key + "."+property);
	        }
	    } catch (Exception e) {
	        // TODO: handle exception
	    }
	       return map;
	   }
	   
	   /**修改lastid的值，并保存
	     * @param id
	     */
	    public void saveLastKey(String propertiesName,String key,String value){
	    	Properties props = new Properties();
	    	InputStream in= getClass().getResourceAsStream(propertiesName);
	        try {
				props.load(in);
				in.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        props.setProperty(key, value);
	        //保存文件
	        try {
	            URL fileUrl = getClass().getResource(propertiesName);//得到文件路径
	            FileOutputStream fos = new FileOutputStream(new File(fileUrl.toURI()));
	            props.store(fos, "the primary key of article table");
	            fos.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	}

