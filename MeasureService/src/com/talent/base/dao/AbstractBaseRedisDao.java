package com.talent.base.dao;

import org.springframework.beans.factory.annotation.Autowired;

import com.talent.base.dao.impl.BaseRedisTemplate;

public abstract class AbstractBaseRedisDao<K, V> {
	
	@Autowired
	protected BaseRedisTemplate redisTemplate;

	/**
	 * 设置redisTemplate
	 */
	public void setRedisTemplate(BaseRedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	public BaseRedisTemplate getRedisTemplate() {
		return this.redisTemplate;
	}
}