package com.talent.base.dao.impl;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

public class BaseRedisTemplate extends RedisTemplate<String, String> {
	
	private RedisSerializer<Object> baseSerializer = null;
	
	public BaseRedisTemplate() {
		if(null == baseSerializer){
			baseSerializer = new BaseRedisSerializer();
		}
		setKeySerializer(baseSerializer);
		setValueSerializer(baseSerializer);
		setHashKeySerializer(baseSerializer);
		setHashValueSerializer(baseSerializer);
	}

	public BaseRedisTemplate(RedisConnectionFactory connectionFactory) {
		this();
		setConnectionFactory(connectionFactory);
		afterPropertiesSet();
	}

	protected RedisConnection preProcessConnection(RedisConnection connection, boolean existingConnection) {
		return new DefaultBaseRedisConnection(connection,baseSerializer);
	}
}
