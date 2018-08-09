package com.talent.base.util;

import org.springframework.beans.BeansException;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class BaseUtil implements ApplicationContextAware{
	
	private static BaseUtil instance;
	
	public static CacheManager cacheManager = null;

	private ApplicationContext applicationContext;

	private static synchronized BaseUtil getInstance() {

		if (instance == null) {
			instance = new BaseUtil();
		}
		return instance;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (getInstance().applicationContext == null) {
			getInstance().applicationContext = applicationContext;
		}

	}

	public static ApplicationContext getApplicationContext() {
		return getInstance().applicationContext;
	}
}
