package com.dogpro.common.tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.lucene.util.StringHelper;

public class HttpRequest {
	
	
	/** 
     * 发起http请求获取返回结果 
     * @param req_url 请求地址 
     * @return 
     */ 
	
	private static Map packagesMap = MessageConsumerConfig.readConfig("config.properties");
	
	
    public static String httpRequest(String req_url) {
        StringBuffer buffer = new StringBuffer();  
        try {  
            URL url = new URL(req_url);  
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();  

            httpUrlConn.setDoOutput(false);  
            httpUrlConn.setDoInput(true);  
            httpUrlConn.setUseCaches(false);  

            httpUrlConn.setRequestMethod("GET");  
            httpUrlConn.connect();  

            // 将返回的输入流转换成字符串  
            InputStream inputStream = httpUrlConn.getInputStream();  
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  

            String str = null;  
            while ((str = bufferedReader.readLine()) != null) {  
                buffer.append(str);  
            }  
            bufferedReader.close();  
            inputStreamReader.close();  
            // 释放资源  
            inputStream.close();  
            inputStream = null;  
            httpUrlConn.disconnect();  

        } catch (Exception e) {  
            System.out.println(e.getStackTrace());  
        }  
        return buffer.toString();  
    }  
	
	public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
//        	//设置代理
//        	setProxy();
        	
        	
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
//            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
	/**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuffer result = new StringBuffer("");
        try {
//        	//设置代理
//        	setProxy();
        	
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("contentType", "utf-8");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
//            out = new PrintWriter(conn.getOutputStream());
//            // 发送请求参数
//            out.print(param);
            
            out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(),"utf-8"));  
            out.println(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(new String(line.getBytes(), "utf-8"));
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result.toString();
    }    
    public static String getUrlParamsByMap(Map<String, Object> map) {  
        if (map == null) {  
            return "";  
        }  
        StringBuffer sb = new StringBuffer();  
        for (Map.Entry<String, Object> entry : map.entrySet()) {  
            sb.append(entry.getKey() + "=" + entry.getValue());  
            sb.append("&");  
        }  
        String s = sb.toString();  
        if (s.endsWith("&")) {  
            s = org.apache.commons.lang.StringUtils.substringBeforeLast(s, "&");  
        }  
        return s;  
    }  
    
    /**
 	 * Basic Authorization 认证 Get方法  
 	 * @param userName
 	 * @param passWord
 	 * @param url(包含完整的url+参数)
 	 * @return
 	 * @throws Exception
 	 */
	public static String doGet(String userName,String passWord,String url)throws Exception{
 		BufferedReader in = null;
 		StringBuffer result = null;
 		try {
 			// 获取接口地址
 			String authStr = userName + ":" + passWord;
 			String encoding = DatatypeConverter.printBase64Binary(authStr.getBytes("UTF-8"));
 			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			connection.setRequestProperty("Authorization", "Basic " + encoding);
			// 建立实际的连接
			connection.connect();
			// 链接超时时间 3分钟  180000毫秒
			connection.setConnectTimeout(180000);
			// 读取页面信息时间 3分钟 180000毫秒
			connection.setReadTimeout(180000);
			int statusCode = connection.getResponseCode();
			if (statusCode == HttpURLConnection.HTTP_OK) {
				in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line = null;
				result = new StringBuffer(); // 请求获取到的回传信息
				while ((line = in.readLine()) != null) {
					result.append(line);
				}
				return result.toString();
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("doGet 方法执行失败  " + e.getMessage());
		}
 		// 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
 	}
	
	public static String doPost(String userName,String passWord,String url,String param)throws Exception{
 		PrintWriter out = null;
 		BufferedReader in = null;
 		StringBuffer result = null;
 		try {
 			String authStr = userName + ":" + passWord;
 			String encoding = DatatypeConverter.printBase64Binary(authStr.getBytes("UTF-8"));
 			URL realUrl = new URL(url);
 			HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
 			connection.setRequestProperty("accept", "*/*");
 			connection.setRequestProperty("connection", "Keep-Alive");
 			connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
 			connection.setRequestProperty("Authorization", "Basic " + encoding);
 			// 发送POST请求必须设置如下两行
 			connection.setDoOutput(true);
 			connection.setDoInput(true);
 			// 获取URLConnection对象对应的输出流
 			out = new PrintWriter(connection.getOutputStream());
 			// 发送请求参数
 			out.print(param);
 			// flush输出流的缓冲
 			out.flush();
 			// 定义BufferedReader输入流来读取URL的响应
            int statusCode = connection.getResponseCode();
			if (statusCode == HttpURLConnection.HTTP_OK) {
				// 定义BufferedReader输入流来读取URL的响应
	            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	            String line = null;
				result = new StringBuffer(); // 请求获取到的回传信息
	            while ((line = in.readLine()) != null) {
	            	result.append(line);
	            }
	            return result.toString();
			}
            return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("doPost 方法执行失败   " + e.getMessage());
		}
 		// 使用finally块来关闭输入流
        finally {
            try {
            	if(out!=null){
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
 	}
	
	private static void trustAllHosts() {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[]{};
            }

            public void checkClientTrusted(X509Certificate[] chain, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] chain, String authType) {
            }
        }};
        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	
	public static String sendHtpps(String a,String url) {
        String result = "";
        PrintWriter out = null;
        BufferedReader in = null;
        HttpURLConnection conn;
        try {
            trustAllHosts();
            URL realUrl = new URL(url);
            //通过请求地址判断请求类型(http或者是https)
            if (realUrl.getProtocol().toLowerCase().equals("https")) {
                HttpsURLConnection https = (HttpsURLConnection) realUrl.openConnection();
                https.setHostnameVerifier(new HostnameVerifier() {

                    public boolean verify(String hostname, SSLSession session) {
                        return true;

                    }

                });
                conn = https;
            } else {
                conn = (HttpURLConnection) realUrl.openConnection();
            }
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "text/plain;charset=utf-8");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(a);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {// 使用finally块来关闭输出流、输入流
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

	public static String DELETE(String mqttAdmin_username,String mqttAdmin_password,String url,Map<String,Object> dataForm) throws Exception{  
        HttpClient httpClient = new HttpClient();  
        DeleteMethod deleteMethod = new DeleteMethod(url);  
  
        List<NameValuePair> data = new ArrayList<NameValuePair>();  
        if(dataForm!=null){  
            Set<String> keys = dataForm.keySet();  
            for(String key:keys){  
                NameValuePair nameValuePair = new NameValuePair(key, (String) dataForm.get(key));  
                data.add(nameValuePair);  
            }  
        }  
        deleteMethod.setQueryString(data.toArray(new NameValuePair[0]));  
//        connection.setRequestProperty("accept", "*/*");
//			connection.setRequestProperty("connection", "Keep-Alive");
//			connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//			connection.setRequestProperty("Authorization", "Basic " + encoding);
			deleteMethod.setDoAuthentication(true);
        try {  
            int statusCode = httpClient.executeMethod(deleteMethod);  
            if (statusCode != HttpStatus.SC_OK) {  
                return "Method failed: " + deleteMethod.getStatusLine();  
            }  
  
            // Read the response body.  
            byte[] responseBody = deleteMethod.getResponseBody();  
            // Deal with the response.  
            // Use caution: ensure correct character encoding and is not binary data  
            return new String(responseBody);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }finally {  
            deleteMethod.releaseConnection();  
        }  
        return null;  
    }  
	
	public static String basicDelete(String userName,String passWord,String targeturl,String param) throws IOException {  
		  
        URL url = new URL(targeturl);  
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();  
        connection.setRequestMethod("DELETE");  
        connection.setDoInput(true);  
        connection.setDoOutput(false);  
        connection.setRequestProperty("name", "robben");  
        connection.setRequestProperty("content-type", "text/html");  
        
        
        String authStr = userName + ":" + passWord;
		String encoding = DatatypeConverter.printBase64Binary(authStr.getBytes("UTF-8"));
        connection.setRequestProperty("accept", "*/*");
		connection.setRequestProperty("connection", "Keep-Alive");
		connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
		connection.setRequestProperty("Authorization", "Basic " + encoding);
//        OutputStreamWriter out = new OutputStreamWriter(  
//                connection.getOutputStream(), "UTF-8");  
        // 获取返回的数据  
        BufferedReader in = new BufferedReader(new InputStreamReader(  
                connection.getInputStream()));  
        String line = null;  
        StringBuffer content = new StringBuffer();  
        while ((line = in.readLine()) != null) {  
            // line 为返回值，这就可以判断是否成功  
            content.append(line);  
        }  
        in.close();  
        return content.toString();  
    }  
	
	
//	public static void setProxy(){
//		if(packagesMap.get("isproxy")!=null&&packagesMap.get("isproxy").equals("true"))
//		{
//			String proxy_host = packagesMap.get("proxy_host").toString();
//			String proxy_port = packagesMap.get("proxy_port").toString();
//			String proxy_username = packagesMap.get("proxy_username").toString();
//			String proxy_password = packagesMap.get("proxy_password").toString();
//			System.setProperty("http.proxyHost", proxy_host);  
//			System.setProperty("http.proxyPort", proxy_port);  
//			if(proxy_username!=null&&!proxy_username.equals("")){
//				Authenticator.setDefault(new BasicAuthenticator(proxy_username, proxy_password));
//			}
//		}
//	}
}
