package com.talent.base.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface BaseRedisDao<T> {
	
	public boolean hAdd(String key,String hKey,String hValue);
	
	public boolean hDel(String key,String hKey);
	
	public boolean hEdit(String key,String hKey,String hValue);
	
	public String hGet(String key,String hKey);
	
	public String[] hKeys(String key);
	
	public boolean oAddorEdit(String key, T t);
	
	public boolean mAddorEdit(String key, HashMap<String,String> m);
	
	public long sAdd(String key,String value);
	
	public String sGet(String key);
	
	public long lAdd(List<String> keys, List<T> list);
	
	public long lmAdd(List<String> keys,List<HashMap<String,String>> list);
	
	public boolean delete(String key);
	
	public boolean delete(List<String> keys);
	
	public Map<String,String> hGetAll(String key,String... hKeys);
	
	public Map<String,String> hGetAll(String key,List<String> hKeys);
	
	public T oGetAll(String key,T t,String... hKeys);
	
	public T oGetAll(String key,T t,List<String> hKeys);
	
	public boolean hasKey(String key);
	
	public boolean hasHkey(String key,String hKey);
	
	public void remove(String prefix);
}