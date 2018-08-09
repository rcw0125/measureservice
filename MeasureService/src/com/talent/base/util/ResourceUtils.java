package com.talent.base.util;

import java.util.Properties;

public class ResourceUtils {
	
	private static Properties messageSource = null;
	
		
	public static void setMessageSource(Properties messageSource) {
		ResourceUtils.messageSource = messageSource;
	}


	public static String getResource(String key) {
		String result = messageSource.getProperty(key);
		try{
			result = new String(result.getBytes("ISO8859-1"),"UTF-8");
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
}