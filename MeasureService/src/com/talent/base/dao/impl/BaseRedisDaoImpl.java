package com.talent.base.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.util.Assert;

import com.talent.base.dao.AbstractBaseRedisDao;
import com.talent.base.dao.BaseRedisDao;

public class BaseRedisDaoImpl<T> extends AbstractBaseRedisDao<String, Object> implements BaseRedisDao<T> {
	
	/**
	 * 在一个Key上新增一个属性
	 */
	public boolean hAdd(String key,String hKey,String hValue) {
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection conn) throws DataAccessException {
				StringRedisConnection connection = (StringRedisConnection)conn;
				return connection.hSetNX(key,hKey,hValue);
			}
		});
		return result;
	}
	
	/**
	 * 在一个Key上删除一个属性
	 */
	public boolean hDel(String key,String hKey) {
		long result = redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection conn) throws DataAccessException {
				StringRedisConnection connection = (StringRedisConnection)conn;
				return connection.hDel(key, hKey);
			}
		});
		if(0 == result){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 在一个Key上修改一个属性
	 */
	public boolean hEdit(String key,String hKey,String hValue) {
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection conn) throws DataAccessException {
				StringRedisConnection connection = (StringRedisConnection)conn;
				return connection.hSet(key, hKey,hValue);
			}
		});
		return result;
	}
	
	/**
	 * 根据一个Key和一个属性得到属性值
	 */
	public String hGet(String key,String hKey) {
		String result = redisTemplate.execute(new RedisCallback<String>() {
			public String doInRedis(RedisConnection conn) throws DataAccessException {
				try {
					StringRedisConnection connection = (StringRedisConnection)conn;
					return connection.hGet(key,hKey);
				}catch (Exception e) {
					return "";
				}
			}
		});
		return result;
	}
	
	public String[] hKeys(String key){
		String[] result = redisTemplate.execute(new RedisCallback<String[]>() {
			public String[] doInRedis(RedisConnection conn) throws DataAccessException {
				try {
					StringRedisConnection connection = (StringRedisConnection)conn;
					Set<String> r = connection.hKeys(key);
					if(0 != r.size()){
						String[] sarry = new String[1];
						return r.toArray(sarry);
					}else{
						return null;
					}
				}catch (Exception e) {
					return null;
				}
			}
		});
		return result;
	}
	
	/**
	 * 新增一个bean对象
	 */
	public boolean oAddorEdit(String key,T t) {
		try{
			HashMap<String,String> o2m = (HashMap<String, String>)BeanUtils.describe(t);
			return mAddorEdit(key,o2m);
		}catch(Exception e){
			return false;
		}
	}
	
	/**
	 * 新增一个Map对象
	 */
	public boolean mAddorEdit(String key, HashMap<String,String> m) {
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection conn) throws DataAccessException {
				try{
					StringRedisConnection connection = (StringRedisConnection)conn;
					connection.hMSet(key,m);
					return true;
				}catch(Exception e){
					e.printStackTrace();
					return false;
				}
			}
		});
		return result;
	}

	/**
	 * 批量新增
	 */
	public long lAdd(List<String> keys,List<T> list) {
		Assert.notEmpty(list);
		long result = redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection conn) throws DataAccessException {
				try{
					StringRedisConnection connection = (StringRedisConnection)conn;
					int i = 0;
					for (T t : list) {
						HashMap<String,String> o2m = (HashMap<String, String>)BeanUtils.describe(t);
						connection.hMSet(keys.get(i).toString(), o2m);
						i++;
					}
					return (long)i;
				}catch(Exception e){
					return (long)0;
				}
			}
		}, false, true);
		return result;
	}
	
	/**
	 * 批量新增
	 */
	public long lmAdd(List<String> keys,List<HashMap<String,String>> list) {
		Assert.notEmpty(list);
		long result = redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection conn) throws DataAccessException {
				try{
					StringRedisConnection connection = (StringRedisConnection)conn;
					int i = 0;
					for (HashMap<String,String> o2m : list) {
						connection.hMSet(keys.get(i).toString(), o2m);
						i++;
					}
					return (long)i;
				}catch(Exception e){
					return (long)0;
				}
			}
		}, false, true);
		return result;
	}
	
	/**
	 * 删除
	 */
	public boolean delete(String key) {
		try{
			List<String> list = new ArrayList<String>();
			list.add(key);
			delete(list);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	/**
	 * 删除多个
	 */
	public boolean delete(List<String> keys) {
		try{
			redisTemplate.delete(keys);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public void remove(String prefix){
		Set<String> keys = redisTemplate.keys(prefix);
		redisTemplate.delete(keys);
	}
	
	/**
	 * 获得key对应的所有属性值
	 * @param key
	 * @return
	 */
	public Map<String,String> hGetAll(String key,String... hKeys){
		Map<String,String> result = redisTemplate.execute(new RedisCallback<Map<String,String>>() {
			public Map<String,String> doInRedis(RedisConnection conn) throws DataAccessException {
				Map<String,String> rr = new HashMap<String,String>();
				try{
					StringRedisConnection connection = (StringRedisConnection)conn;
					if(0 == hKeys.length){
						rr = connection.hGetAll(key);
					}else{
						try{
							for(int i=0;i<hKeys.length;i++){
								rr.put(hKeys[i], connection.hGet(key,hKeys[i]));
							}
						}catch(Exception ee){
							return null;
						}
					}
				}catch(Exception e){
					return null;
				}
				return rr;
			}
		}, false,false);
		return result;
	}
	
	public Map<String,String> hGetAll(String key,List<String> hKeys){
		Map<String,String> result = redisTemplate.execute(new RedisCallback<Map<String,String>>() {
			public Map<String,String> doInRedis(RedisConnection conn) throws DataAccessException {
				Map<String,String> rr = new HashMap<String,String>();
				try{
					StringRedisConnection connection = (StringRedisConnection)conn;
					try{
						for(int i=0;i<hKeys.size();i++){
							rr.put(hKeys.get(i), connection.hGet(key,hKeys.get(i)));
						}
					}catch(Exception ee){
						
					}
				}catch(Exception e){
					rr = new HashMap<String,String>();
				}
				return rr;
			}
		}, false,false);
		return result;
	}
	
	/**
	 * 根据Key返回对象
	 * @param key
	 * @param t
	 */
	public T oGetAll(String key,T t,String...hKeys){
		try {
			BeanUtils.populate(t,hGetAll(key,hKeys));
			return t;
		}catch (Exception e) {
			return null;
		}
	}
	
	public T oGetAll(String key,T t,List<String> hKeys){
		try {
			BeanUtils.populate(t,hGetAll(key,hKeys));
			return t;
		}catch (Exception e) {
			return null;
		}
	}
	
	public boolean hasKey(String key){
		return redisTemplate.hasKey(key);
	}
	
	public boolean hasHkey(String key,String hKey){
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection conn) throws DataAccessException {
				StringRedisConnection connection = (StringRedisConnection)conn;
				return connection.hExists(key,hKey);
			}
		}, false, true);
		return result;
	}

	@Override
	public long sAdd(String key, String value) {
		long result = redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection conn) throws DataAccessException {
				StringRedisConnection connection = (StringRedisConnection)conn;
				connection.set(key, value);
				return 1L;
			}
		}, false, true);
		return result;
	}
	
	@Override
	public String sGet(String key) {
		String result = redisTemplate.execute(new RedisCallback<String>() {
			public String doInRedis(RedisConnection conn) throws DataAccessException {
				StringRedisConnection connection = (StringRedisConnection)conn;
				return connection.get(key);
			}
		});
		return result;
	}
}