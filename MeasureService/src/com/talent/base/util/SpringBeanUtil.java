package com.talent.base.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;

public class SpringBeanUtil implements ApplicationContextAware {

	private ConfigurableApplicationContext applicationContext = null;
	
	private BeanDefinitionRegistry beanDefinitionRegistry = null;
			
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = (ConfigurableApplicationContext) applicationContext;
	}
	
	public ConfigurableApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ConfigurableApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public void init() {
		beanDefinitionRegistry = (DefaultListableBeanFactory)applicationContext.getBeanFactory();
	}

	public <T> void registerBean(String beanId,Class<T> clazz) {
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
		BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
		beanDefinitionRegistry.registerBeanDefinition(beanId, beanDefinition);
		applicationContext.refresh();
	}
	
	public <T> T getBean(Class<T> clazz) {
		return (T)applicationContext.getBean(clazz);
	}

	public void unregisterBean(String beanId) {
		beanDefinitionRegistry.removeBeanDefinition(beanId);
	}
}