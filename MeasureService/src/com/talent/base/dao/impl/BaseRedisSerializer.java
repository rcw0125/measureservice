package com.talent.base.dao.impl;

import java.nio.charset.Charset;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.Assert;


public class BaseRedisSerializer implements RedisSerializer<Object>{

	private final Charset charset;

	public BaseRedisSerializer() {
		this(Charset.forName("UTF8"));
	}

	public BaseRedisSerializer(Charset charset) {
		Assert.notNull(charset);
		this.charset = charset;
	}

	public String deserialize(byte[] bytes) {
		String temp = (bytes == null ? null : new String(bytes, charset));
		if("null".equals(temp)){
			temp = null;
		}
		return temp;
	}

	public byte[] serialize(Object obj) {
		try{
			if(null == obj){
				obj = "null";
			}
			return obj.toString().getBytes(charset);
		}catch(Exception e){
			return "null".getBytes();
		}
	}
}
