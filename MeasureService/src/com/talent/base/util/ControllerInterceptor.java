package com.talent.base.util;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
public class ControllerInterceptor extends HandlerInterceptorAdapter {

	@Override
	@SuppressWarnings("unchecked")
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		
		HashMap<String,String> bean = (HashMap<String,String>)WebUtils.Requst2Bean(request, HashMap.class);
		System.out.println(bean.get("validflag"));
        return super.preHandle(request, response, handler); 
	}
}