package com.talent.privilege.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import com.talent.base.dao.impl.BaseDaoiBatis;
import com.talent.base.util.CacheUtil;
import com.talent.dubbo.config.api.annotation.Service;
import com.talent.privilege.dao.PrivilegeDao;
import com.talent.privilege.model.Resource;
import com.talent.privilege.model.Role;
import com.talent.privilege.model.User;

@Service
@Component
@SuppressWarnings({"deprecation","unchecked"})
public class PrivilegeDaoImpl extends BaseDaoiBatis implements PrivilegeDao {
    
	@Autowired
	private CacheUtil cacheUtil;
	
	public User getLoginUser(User user) throws DataAccessException {
        return (User) getOracleSqlMapClientTemplate().queryForObject("User.query_list", user);
    }
    
    public List<Resource> queryResourcesByUsercode(User user) throws DataAccessException{
    	List<Resource> result = new ArrayList<Resource>();
    	Map<Integer,Resource> userResources = new HashMap<Integer,Resource>();
    	user.setIsdisplay(0);
		List<Resource> defineUserResources = getOracleSqlMapClientTemplate().queryForList("Resource.selectResourceUser",user);
		for(Resource r : defineUserResources){
			//将本身加入菜单
			userResources.put(r.getId(), r);
			
			//查找它的所有父节点，使其拥有访问权限
			List<Resource> temps = cacheUtil.getCache("resourceCache").list(r, new String[]{"id","isdisplay"}, new String[]{cacheUtil.EQ,cacheUtil.EQ},new Object[]{r.getFid(),1});
			if(null != temps && 0 != temps.size()){
				getAccessResource(temps,userResources);
			}
			
			//如果是管理权限的功能模块，查找它所有的子节点
			if("功能模块".equals(r.getResourcetype()) && "全部".equals(r.getCtrltype())){
				temps = cacheUtil.getCache("resourceCache").list(r, new String[]{"fid","isdisplay"}, new String[]{cacheUtil.EQ,cacheUtil.EQ},new Object[]{r.getId(),1});
				if(null != temps && 0 != temps.size()){
					getModuleResource(temps,userResources);
				}
			}
		}
		Resource[] resourceArray = new Resource[1];
		resourceArray = userResources.values().toArray(resourceArray);
		
		queryMenuTree(resourceArray,0,result);
		return result;
	}
    
    public List<Resource> queryMeasureMenuByUsercode(User user,String contextName) throws DataAccessException{
    	List<Resource> result = new ArrayList<Resource>();
    	Map<Integer,Resource> userResources = new HashMap<Integer,Resource>();
    	user.setIsdisplay(1);
		List<Resource> defineUserResources = getOracleSqlMapClientTemplate().queryForList("Resource.selectResourceUser",user);
		for(Resource r : defineUserResources){
			//将本身加入菜单
			userResources.put(r.getId(), r);
			
			//查找它的所有父节点，使其拥有访问权限
			List<Resource> temps = cacheUtil.getCache("resourceCache").list(r, new String[]{"id","isdisplay"}, new String[]{cacheUtil.EQ,cacheUtil.EQ},new Object[]{r.getFid(),1});
			if(null != temps && 0 != temps.size()){
				getAccessResource(temps,userResources);
			}
			
			//如果是管理权限的功能模块，查找它所有的子节点
			if("功能模块".equals(r.getResourcetype()) && "全部".equals(r.getCtrltype())){
				temps = cacheUtil.getCache("resourceCache").list(r, new String[]{"fid","isdisplay"}, new String[]{cacheUtil.EQ,cacheUtil.EQ},new Object[]{r.getId(),1});
				if(null != temps && 0 != temps.size()){
					getModuleResource(temps,userResources);
				}
			}
		}
		
		Collection<Resource> crs = userResources.values();
		Resource[] resourceArray = new Resource[crs.size()];
		Iterator<Resource> ir = crs.iterator();
		int i = 0;
		while(ir.hasNext()){
			Resource tr = ir.next();
			Resource tr_t = new Resource();
			BeanUtils.copyProperties(tr, tr_t);
			tr_t.setIcon(contextName + tr.getIcon());
			resourceArray[i] = tr_t;
			i++;
		}
		
		queryMenuTree(resourceArray,0,result);
		return result;
	}
    
    public String queryMeasureMenuStrByUsercode(User user,String contextName) throws DataAccessException{
    	StringBuffer result = new StringBuffer();
    	Map<Integer,Resource> userResources = new HashMap<Integer,Resource>();
    	user.setIsdisplay(1);
		List<Resource> defineUserResources = getOracleSqlMapClientTemplate().queryForList("Resource.selectResourceUser",user);
		for(Resource r : defineUserResources){
			//将本身加入菜单
			userResources.put(r.getId(), r);
			
			//查找它的所有父节点，使其拥有访问权限
			List<Resource> temps = cacheUtil.getCache("resourceCache").list(r, new String[]{"id","isdisplay"}, new String[]{cacheUtil.EQ,cacheUtil.EQ},new Object[]{r.getFid(),1});
			if(null != temps && 0 != temps.size()){
				getAccessResource(temps,userResources);
			}
			
			//如果是管理权限的功能模块，查找它所有的子节点
			if("功能模块".equals(r.getResourcetype()) && "全部".equals(r.getCtrltype())){
				temps = cacheUtil.getCache("resourceCache").list(r, new String[]{"fid","isdisplay"}, new String[]{cacheUtil.EQ,cacheUtil.EQ},new Object[]{r.getId(),1});
				if(null != temps && 0 != temps.size()){
					getModuleResource(temps,userResources);
				}
			}
		}
		
		Resource[] resourceArray = new Resource[1];
		resourceArray = userResources.values().toArray(resourceArray);
		
		queryMenuStr(resourceArray,0,result,contextName);
		return result.toString();
    }
    
    private void getModuleResource(List<Resource> rs,Map<Integer,Resource> rm){
    	for(int i=0;i<rs.size();i++){
    		rm.put(rs.get(i).getId(), rs.get(i));
    		List<Resource> subList = cacheUtil.getCache("resourceCache").list(rs.get(i), new String[]{"fid"}, new String[]{cacheUtil.EQ},new Object[]{rs.get(i).getId()});
    		if(0 != subList.size()){
    			getModuleResource(subList,rm);
    		}
    	}
    }
    
    private void getAccessResource(List<Resource> rs,Map<Integer,Resource> rm){
    	for(int i=0;i<rs.size();i++){
    		rm.put(rs.get(i).getId(), rs.get(i));
    		List<Resource> parentList = cacheUtil.getCache("resourceCache").list(rs.get(i), new String[]{"id"}, new String[]{cacheUtil.EQ},new Object[]{rs.get(i).getFid()});
    		if(0 != parentList.size()){
    			getAccessResource(parentList,rm);
    		}
    	}
    }
    
    private List<Resource> queryMenuTree(Resource[] resourceArray,int fid,List<Resource> resultList){
    	List<Resource> subMenuList = new ArrayList<Resource>();
    	
    	for(Resource r_t : resourceArray){
    		if(fid == r_t.getFid()){
    			subMenuList.add(r_t);
    		}
    	}
    	if(subMenuList.size() > 0){
    		for(Resource mm : subMenuList){
    			int subCount = 0;
    			for(Resource sr_t : resourceArray){
    				if(mm.getId() == sr_t.getFid()){
    					subCount++;
    					break;
    				}
    			}
    			if(subCount > 0){
    				List<Resource> t = new ArrayList<Resource>();
    				mm.setChildren(queryMenuTree(resourceArray,mm.getId(),t));
    			}
    			resultList.add(mm);
    		}
    	}
    	return subMenuList;
    }
    
    private StringBuffer queryMenuStr(Resource[] resourceArray,int fid,StringBuffer resultStr,String contextName){
    	
    	StringBuffer subMenuStr = new StringBuffer();
    	StringBuffer sb = new StringBuffer();
    	for(Resource r_t : resourceArray){
    		if(fid == r_t.getFid()){
    			boolean hasChildren = false;
    			for(Resource sr_t : resourceArray){
    				if(r_t.getId() == sr_t.getFid()){
    					hasChildren = true;
    					break;
    				}
    			}
    			if(hasChildren){
    				subMenuStr.append("<li><a href=\"javascript:void(0)\"><span class=\"glyphicon glyphicon-menu-hamburger\"></span>&nbsp;"+r_t.getResourcename()+"<span class=\"caret\"></span></a>");
    				subMenuStr.append("<ul class=\"dropdown-menu\">");
    				subMenuStr.append(queryMenuStr(resourceArray,r_t.getId(),resultStr,contextName));
    				subMenuStr.append("</ul>");
    				subMenuStr.append("</li>");
    				resultStr.append(subMenuStr);
    				sb = subMenuStr;
    		    	subMenuStr.delete(0, subMenuStr.length());
    			}else{
    				sb.append("<li id=\"menuid"+r_t.getId()+"\"><a href=\"javascript:void(0)\" onclick=\"openContent('"+r_t.getId()+"','"+r_t.getResourcename()+"','" + contextName + r_t.getLink()+"','"+r_t.getIcon()+"',0)\"><span class=\"glyphicon "+r_t.getIcon()+"\"></span>&nbsp;"+r_t.getResourcename()+"</a></li>");
    			}
			}
    	}
    	return sb;
    }
    
    public List<User> getAllUser() throws DataAccessException{
        return getOracleSqlMapClientTemplate().queryForList("User.getAllMeasureUser");
    }
    
    public void insertUserRole(User user) throws DataAccessException{
    	getOracleSqlMapClientTemplate().insert("User.insertUserRole", user);
    }
    
    public void deleteUserRole(User user) throws DataAccessException{
    	getOracleSqlMapClientTemplate().delete("User.deleteUserRole", user);
    }
    
    public List<Role> selectUserRole(User user) throws DataAccessException{
    	return (List<Role>)getOracleSqlMapClientTemplate().queryForList("User.selectUserRole", user);
    }
    
    public void insertResourceRole(Resource resource) throws DataAccessException{
    	getOracleSqlMapClientTemplate().insert("Resource.insertResourceRole", resource);
    }
    
    public void deleteResourceRole(Resource resource) throws DataAccessException{
    	getOracleSqlMapClientTemplate().delete("Resource.deleteResourceRole", resource);
    }
    
    public List<Role> selectResourceRole(Resource resource) throws DataAccessException{
    	return (List<Role>)getOracleSqlMapClientTemplate().queryForList("Resource.selectResourceRole", resource);
    }
    
    public User queryPassword(User user) throws DataAccessException{
    	return (User)getOracleSqlMapClientTemplate().queryForObject("User.queryPassword",user);
    }
    
    public int updatePassword(User user) throws DataAccessException{
    	return getOracleSqlMapClientTemplate().update("User.updatePassword",user);
    }
}
