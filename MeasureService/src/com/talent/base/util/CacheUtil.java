package com.talent.base.util;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.stereotype.Component;

import com.talent.dubbo.config.api.annotation.Service;

import org.springframework.cache.CacheManager;

import net.sf.ehcache.search.Attribute;
import net.sf.ehcache.search.Query;
import net.sf.ehcache.search.Result;
import net.sf.ehcache.search.Results;

@SuppressWarnings("unchecked")
@Service
@Component
public class CacheUtil {

	private CacheManager cacheManager = null;
	
	private Cache cache = null;
	
	public String EQ = "eq";
	
	public String NE = "noteq";
	
	public String LLIKE = "llike";
	
	public String RLIKE = "rlike";
	
	public String LIKE = "like";
	
	public String GE = "ge";
	
	public String GT = "gt";
	
	public String LE = "le";
	
	public String LT = "lt";

	public CacheUtil getCache(String cacheName){
		if(null == cache || !cacheName.equalsIgnoreCase(cache.getName())){
			cache = cacheManager.getCache(cacheName);
		}
		return this;
	}

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}
	
	public Object get(Object key) {
		ValueWrapper vw = cache.get(key);
		if(null == vw){
			return null;
		}else{
			return vw.get();
		}
	}
	
	public void clear(){
		cache.clear();
	}
	
	public void put(Object key,Object value){
		cache.put(key,value);
	}
	
	public <T> T get(T t,String[] properties,Object[] values){
		List<T> r = list(t,properties,values);
		if(null != r && 0 != r.size()){
			return (T)(r.get(0));
		}else{
			return null;
		}
	}
	
	public <T> List<T> list(T t,String[] properties,Object[] values){
		
		try{
			net.sf.ehcache.Cache ehcache = (net.sf.ehcache.Cache)(cache.getNativeCache());
			Query query = ehcache.createQuery();
			for(int i = 0;i<properties.length;i++){
				String property = properties[i];
				Attribute<Object> propAttr = ehcache.getSearchAttribute(property);
				Type type = BeanUtils.findPropertyType(property,t.getClass());
				if(type == String.class){
					query.addCriteria(propAttr.eq(values[i])).includeValues();
				}else if(type == int.class){
					query.addCriteria(propAttr.eq(Integer.parseInt(values[i].toString()))).includeValues();
				}
			}
			
			Results r = query.execute();
			List<T> objects = new ArrayList<T>(r.size());
			
			List<Result> rr = r.all();
			for(Result result : rr){
				objects.add((T)result.getValue());
			}
			return objects;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}	
	}
	
	public <T> List<T> list(T t,String[] properties,String[] compare,Object[] values){
		
		try{
			net.sf.ehcache.Cache ehcache = (net.sf.ehcache.Cache)(cache.getNativeCache());
			Query query = ehcache.createQuery();
			for(int i = 0;i<properties.length;i++){
				String property = properties[i];
				Attribute<Object> propAttr = ehcache.getSearchAttribute(property);
				Type type = BeanUtils.findPropertyType(property,t.getClass());
				if(type == String.class){
					if(EQ.equals(compare[i])){
						query.addCriteria(propAttr.eq(values[i])).includeValues();
					}else if(LLIKE.equals(compare[i])){
						query.addCriteria(propAttr.ilike("*" + values[i])).includeValues();
					}else if(RLIKE.equals(compare[i])){
						query.addCriteria(propAttr.ilike(values[i] + "*")).includeValues();
					}else if(LIKE.equals(compare[i])){
						query.addCriteria(propAttr.ilike("*" + values[i] + "*")).includeValues();
					}else if(NE.equals(compare[i])){
						query.addCriteria(propAttr.ne(values[i])).includeValues();
					}					
				}else if(type == int.class){
					if(EQ.equals(compare[i])){
						query.addCriteria(propAttr.eq(Integer.parseInt(values[i].toString()))).includeValues();
					}else if(NE.equals(compare[i])){
						query.addCriteria(propAttr.ne(values[i])).includeValues();
					}else if(GT.equals(compare[i])){
						query.addCriteria(propAttr.gt(values[i])).includeValues();
					}else if(GE.equals(compare[i])){
						query.addCriteria(propAttr.ge(values[i])).includeValues();
					}else if(LT.equals(compare[i])){
						query.addCriteria(propAttr.lt(values[i])).includeValues();
					}else if(LE.equals(compare[i])){
						query.addCriteria(propAttr.le(values[i])).includeValues();
					}
				}
			}
			
			Results r = query.execute();
			List<T> objects = new ArrayList<T>(r.size());
			
			List<Result> rr = r.all();
			for(Result result : rr){
				objects.add((T)result.getValue());
			}
			return objects;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}	
	}
	
	public <T> List<T> lList(T t,String[] properties,Object[] values){
		
		try{
			net.sf.ehcache.Cache ehcache = (net.sf.ehcache.Cache)(cache.getNativeCache());
			Query query = ehcache.createQuery();
			for(int i = 0;i<properties.length;i++){
				String property = properties[i];
				Attribute<Object> propAttr = ehcache.getSearchAttribute(property);
				Type type = BeanUtils.findPropertyType(property,t.getClass());
				if(type == String.class){
					query.addCriteria(propAttr.ilike("*" + values[i] + "*")).includeValues();
				}else if(type == int.class){
					query.addCriteria(propAttr.eq(Integer.parseInt(values[i].toString()))).includeValues();
				}
			}
			
			Results r = query.execute();
			List<T> objects = new ArrayList<T>(r.size());
			
			List<Result> rr = r.all();
			for(Result result : rr){
				objects.add((T)result.getValue());
			}
			return objects;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}	
	}
	
	public <T> List<T> list(T t,String prekey){
		List<T> objects = new ArrayList<T>();
		try{
			net.sf.ehcache.Cache ehcache = (net.sf.ehcache.Cache)(cache.getNativeCache());
			List<String> keys = ehcache.getKeys();
			for(String key : keys){
				if(key.indexOf(prekey) != -1){
					objects.add((T)ehcache.get(key).getObjectValue());
				}
			}
			return objects;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}	
	}
}