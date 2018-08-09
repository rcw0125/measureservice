package com.talent.base.dao.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import com.talent.base.dao.PlatformDao;
import com.talent.base.model.BaseEntity;
import com.talent.base.model.PageModel;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.dao.DataAccessException;

@SuppressWarnings({ "deprecation", "unchecked" })
public class PlatformDaoImpl extends BaseDaoiBatis implements PlatformDao {

    public <T> PageModel queryPage(T t,PageModel pm) throws DataAccessException{
    	try{
            int count = (Integer) getOracleSqlMapClientTemplate().queryForObject(t.getClass().getSimpleName() + ".query_count",t);
            pm.setAllcount(count);
            pm.setup();
            pm.setList(getOracleSqlMapClientTemplate().queryForList(t.getClass().getSimpleName() + ".query_list",t, pm.getStart(), pm.getTruerows()));
    	}catch(Exception e){
    		e.printStackTrace();
    	}
        return pm;
    }
    
    public <T> PageModel queryPage2(T t,PageModel pm) throws DataAccessException{
    	try{
    		int count = (Integer) getOracleSqlMapClientTemplate().queryForObject(t.getClass().getSimpleName() + ".query_count2",t);
    		pm.setAllcount(count);
    		pm.setup();
    		pm.setList(getOracleSqlMapClientTemplate().queryForList(t.getClass().getSimpleName() + ".query_list2",t, pm.getStart(), pm.getTruerows()));
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return pm;
    }
    
    public <T> List<T> queryList(T t) throws DataAccessException{
    	return getOracleSqlMapClientTemplate().queryForList(t.getClass().getSimpleName() + ".query_list",t);
    }
    
    public <T> List<T> queryAllList(T t) throws DataAccessException{
    	return getOracleSqlMapClientTemplate().queryForList(t.getClass().getSimpleName() + ".query_all_list");
    }    
    
    public <T> int remove(T t,String ids) throws DataAccessException{
    	return getOracleSqlMapClientTemplate().update(t.getClass().getSimpleName() + ".delete",ids);
    }
    
	public <T> int update(T t) throws DataAccessException {
		return getOracleSqlMapClientTemplate().update(t.getClass().getSimpleName() + ".update", t);
	}

	public <T> void insert(T t) throws DataAccessException {
		getOracleSqlMapClientTemplate().insert(t.getClass().getSimpleName() + ".insert", t);
	}
	
	public <T> int getNewID(T t) throws DataAccessException {
		int id = (Integer)(getOracleSqlMapClientTemplate().queryForObject(t.getClass().getSimpleName() + ".getNewID"));
		try {
			BeanUtils.setProperty(t, "id", id);
		} catch (Exception e) {
			
		}
		return id;
	}

	public <T> T get(T t) throws DataAccessException {
		
		List<T> temp = getOracleSqlMapClientTemplate().queryForList(t.getClass().getSimpleName() + ".query_list",t);
		if(0 != temp.size()){
			t = (T)(temp.get(0));
		}else{
			t = null;
		}
		return t;
	}
	
	public <T> List<T> queryTree(T t,String state,String sel,List<T> list)
	{
		ArrayList<T> subList = null;
		try{
			Method getID = t.getClass().getMethod("getId");
			int fid = Integer.parseInt(getID.invoke(t).toString());
			BaseEntity be = new BaseEntity();
			be.setFid(fid);
			subList = (ArrayList<T>)(getOracleSqlMapClientTemplate().queryForList(t.getClass().getSimpleName() + ".query_tree",be));
			if(subList != null && !subList.isEmpty()){
				for(int i = 0; i < subList.size();i++){
					T o = (T)subList.get(i);
					fid = Integer.parseInt(getID.invoke(o).toString());
					be.setFid(fid);
					int subCount = (getOracleSqlMapClientTemplate().queryForList(t.getClass().getSimpleName() + ".query_tree",be)).size();
					if(subCount > 0){
						List<T> subSubList=new ArrayList<T>();
						Method setNodes = t.getClass().getMethod("setNodes",List.class);
						setNodes.invoke(o,queryTree(o,state, sel,subSubList));
					}
					list.add(o);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}		
		return subList;
	}
	
	public <T> List<Object> queryTree(T t,int id,List<BaseEntity> allList,List<BaseEntity> list){
		if(null == allList){
			allList = (ArrayList<BaseEntity>)(getOracleSqlMapClientTemplate().queryForList(t.getClass().getSimpleName() + ".query_tree"));
		}
		ArrayList<Object> subList = new ArrayList<Object>();
		try{
			for(BaseEntity b : allList){
				if(id == b.getFid()){
					subList.add(b);
				}
			}
			if(subList != null && !subList.isEmpty()){
				for(int i = 0; i < subList.size();i++){
					BaseEntity o = (BaseEntity)subList.get(i);
					int fid = o.getId();
					int subCount = 0;
					for(BaseEntity b : allList){
						if(fid == b.getFid()){
							subCount++;
						}
					}
					if(subCount > 0){
						List<BaseEntity> subSubList=new ArrayList<BaseEntity>();
						o.setNodes((List<Object>)queryTree(t,fid,allList,subSubList));
						List<String> countList = new ArrayList<String>();
						countList.add(subCount+"");
						o.setTags(countList);
					}
					list.add(o);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}		
		return subList;
	}
	
	public String dynamicSQL(String sql) throws DataAccessException{
		List<Object> temp = getOracleSqlMapClientTemplate().queryForList("common.dynamicSQL",sql);
		if(0 != temp.size()){
			return temp.get(0).toString();
		}else{
			return null;
		}
	}
	
	public <T> void order(T t,String orderfield,String direction) throws DataAccessException {
		try {
			String currentOrder = BeanUtils.getProperty(t, orderfield);
			int orderInt = Integer.parseInt(currentOrder);
			if(!("向上".equals(direction) && 1 == orderInt)){
				if("向上".equals(direction)){
					BeanUtils.setProperty(t, orderfield, orderInt - 1);
				}else{
					BeanUtils.setProperty(t, orderfield, orderInt + 1);
				}
				T t1 = (T)getOracleSqlMapClientTemplate().queryForObject(t.getClass().getSimpleName() + ".query_order",t);
				if(null != t1){
					getOracleSqlMapClientTemplate().update(t.getClass().getSimpleName() + ".update_order",t);
					BeanUtils.setProperty(t1, orderfield, orderInt);
					getOracleSqlMapClientTemplate().update(t.getClass().getSimpleName() + ".update_order",t1);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
