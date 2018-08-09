package com.talent.base.util;

import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.Pattern;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ControllerFilter implements Filter{
	
	private static JobLauncher jobLauncher = null;
	
	private static Job logJob = null;
	
	private static ObjectMapper mapper = null;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	@SuppressWarnings({ "unchecked"})
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		if(null == jobLauncher){
			jobLauncher = (JobLauncher)BaseUtil.getApplicationContext().getBean("jobLauncher");
			logJob = (Job)BaseUtil.getApplicationContext().getBean("logJob");
			mapper = new ObjectMapper();
		}
		
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HashMap<String,String> bean = (HashMap<String,String>)WebUtils.Requst2Bean(httpRequest,HashMap.class);
		
		String operatename = "SYSTEM";
		Cookie[] cookies = httpRequest.getCookies();
		if(null != cookies){ 
			operatename = cookies.length > 1 ? URLDecoder.decode(cookies[1].getValue(),"UTF-8") : "SYSTEM";
		}
		
		String inParams = mapper.writeValueAsString(bean);
		if (!sqlValidate(URLDecoder.decode(inParams,"utf-8"))) {  
            throw new IOException("传入的请求参数中含有非法字符");
        }
		
		ResponseWrapper wrapper = new ResponseWrapper((HttpServletResponse)response);
		chain.doFilter(request, wrapper);
		
		if(!"1".equals(request.getParameter("nolog"))){
			try {
				String outResult = new String(wrapper.getResponseData(),"UTF-8");	
				if(outResult.indexOf("DOCTYPE html") == -1 && outResult.indexOf("workbook.xml") == -1){
					try{
						jobLauncher.run(logJob, new JobParametersBuilder()
								  .addString("operator","操作人：" + operatename)
								  .addString("inParams","输入参数：" + inParams)
								  .addString("outResults","输出参数：" + outResult)
								  .addString("createTime","操作时间：" + sdf.format(Calendar.getInstance().getTimeInMillis()))
								  .toJobParameters());
					}catch(Exception e){
						
					}				
				}
			}catch(Exception e){
				System.out.println("写业务日志过程出现错误！！！！");
			}
		}
		
		ServletOutputStream out = response.getOutputStream(); 
		out.write(wrapper.getResponseData()); 
		out.flush();
	}
	
	//SQL注入校验
    protected boolean sqlValidate(String str) {  
    	String reg = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|" + "(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";  
    	Pattern sqlPattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
    	if (sqlPattern.matcher(str).find()) {  
            return false;  
        }
        return true;
    }

	@Override
	public void destroy(){
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException{
		
	}
}
