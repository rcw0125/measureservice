package com.talent.base.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

public class WebUtils {
	@SuppressWarnings("rawtypes")
	public static <T> T Requst2Bean(HttpServletRequest request, Class<T> bean) {
		T t = null;
		try {
			t = bean.newInstance();
			Enumeration parameterNames = request.getParameterNames();
			DateConverter convert = new DateConverter();
			String[] patterns = { "yyyyMMdd", "yyyy-MM-dd" };
			convert.setPatterns(patterns);
			ConvertUtils.register(convert, Date.class);
			while (parameterNames.hasMoreElements()) {
				String name = (String) parameterNames.nextElement();
				String value = request.getParameter(name);
				try{
					BeanUtils.setProperty(t, name, value);
				}catch(IllegalArgumentException e){
					System.out.println(name + "绑定的值：" + value + "格式错误！");
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
	
	public static String get(String url_s, String params) {  
        String result = "";  
        BufferedReader reader = null;
        try {
        	params = params.replace("{", "").replaceAll("\":", "=").replaceAll(",\"", "&").replaceAll("\"", "").replace("}", "").replaceAll(" ", "%20");
            URL url = new URL(url_s + "?nolog=1&" + params);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");  
			connection.connect();
			
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"),81920);
			String lines = "";
			StringBuffer sb = new StringBuffer("");
			while ((lines = reader.readLine()) != null){
				sb.append(lines);
			} 
			result = sb.toString();
        } catch (Exception e) {  
        	result = null;
        }  
        finally {  
            try {  
                if (reader != null) {  
                	reader.close();  
                }  
            }catch (IOException ex) {  
            	result = null;
            }  
        }  
        return result;  
    } 
	
	public static String post(String url_s,String requestbody) {
		BufferedReader reader = null;
		String result = "";
		try {
			// 创建连接
			URL url = new URL(url_s);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);  
			connection.setDoInput(true);  
			connection.setRequestMethod("POST");  
			connection.setUseCaches(false);  
			connection.setInstanceFollowRedirects(true);  
			connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式  
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
			connection.setChunkedStreamingMode(5);  
			connection.connect();
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(),"UTF-8");
            out.append(requestbody);
			out.flush();  
			out.close();

			// 读取响应
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"),81920);
			String lines = "";
			StringBuffer sb = new StringBuffer("");
			while ((lines = reader.readLine()) != null){
				sb.append(lines);
			}
			reader.close();
			connection.disconnect();
			result = sb.toString();
		} catch (Exception e) {
			result = null;
		}
		finally {  
            try {  
                if (reader != null) {  
                	reader.close();  
                }  
            }catch (IOException ex) {  
            	result = null;
            }  
        }  
        return result;
	}
	
	public static String getClientIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
