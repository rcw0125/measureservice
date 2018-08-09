package com.talent.base.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.PropertiesEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

public class BaseController {
	
	private static Logger log = Logger.getLogger(BaseController.class);
	
	/*
	 * 处理Date、int、long、double、float格式的数据，如果传递的数据为空字符或null，则自动赋值为0，简化view层对数字、时间数据的处理。
	 */
	@InitBinder
	protected void ininBinder(WebDataBinder binder){
		binder.registerCustomEditor(Date.class, new DateEditor());  
		binder.registerCustomEditor(int.class, new IntegerEditor());  
		binder.registerCustomEditor(long.class, new LongEditor());  
		binder.registerCustomEditor(double.class, new DoubleEditor());  
		binder.registerCustomEditor(float.class, new FloatEditor());
	}
	
	@ExceptionHandler(Exception.class)
	public void exceptionHandler(Exception ex,HttpServletResponse response,HttpServletRequest request) throws IOException{  
		if(ex.getClass() == BindException.class){
			log.error("Spring MVC数据绑定错误！,错误信息是:");
			log.error(ex.getMessage());
		}else{
			ex.printStackTrace();
		}
	}
	
	class DateEditor extends PropertiesEditor{
		@Override  
	    public void setAsText(String text) throws IllegalArgumentException {
	        if (text == null || text.equals("")) {  
	        	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            text = dateFormat.format(new Date());
	        }
	        try {
	        	formatDate("yyyy-MM-dd HH:mm:ss",text);
			}catch (ParseException e) {
				try{
					formatDate("yyyy-MM-dd HH:mm",text);
				}catch(ParseException ee){
					try{
						formatDate("yyyy-MM-dd HH",text);
					}catch(ParseException eee){
						try{
							formatDate("yyyy-MM-dd",text);
						}catch(ParseException eeee){
							try{
								formatDate("yyyy-MM",text);
							}catch(ParseException eeeee){
								try{
									formatDate("yyyy",text);
								}catch(ParseException eeeeee){
									log.error("Spring MVC日期数据绑定错误！,错误信息是:");
									log.error(eeee.getMessage());
								}
							}
						}
					}
				}				
			}  
	    }
	  
	    @Override  
	    public String getAsText() {  
	        return getValue().toString();  
	    }
	    
	    public Date formatDate(String format,String value) throws ParseException{
	    	SimpleDateFormat dateFormat = new SimpleDateFormat(format);
	    	try {
				return dateFormat.parse(value);
			} catch (ParseException e) {
				throw e;
			}
	    }
	}
	
	class DoubleEditor extends PropertiesEditor {  
	    @Override  
	    public void setAsText(String text) throws IllegalArgumentException {  
	        if (text == null || text.equals("")){  
	            text = "0";  
	        }
	        try{
	        	setValue(Double.parseDouble(text));  
	        }catch(Exception e){
	        	setValue(Double.parseDouble("0"));
	        	log.error("绑定double型数据错误，您传入的数据是：" + text + ",系统将自动识别为0。");
	        }
	    }  
	  
	    @Override  
	    public String getAsText() {  
	        return getValue().toString();  
	    }  
	}
	

	class IntegerEditor extends PropertiesEditor {  
	    @Override  
	    public void setAsText(String text) throws IllegalArgumentException {  
	        if (text == null || text.equals("")) {  
	            text = "0";  
	        }
	        try{
	        	setValue(Integer.parseInt(text)); 
	        }catch(Exception e){
	        	setValue(Integer.parseInt("0"));
	        	log.error("绑定int型数据错误，您传入的数据是：" + text + ",系统将自动识别为0。");
	        }
	    }  
	  
	    @Override  
	    public String getAsText() {  
	        return getValue().toString();  
	    }  
	}
	
	class FloatEditor extends PropertiesEditor {  
	    @Override  
	    public void setAsText(String text) throws IllegalArgumentException {  
	        if (text == null || text.equals("")) {  
	            text = "0";  
	        }
	        try{
	        	setValue(Float.parseFloat(text));
	        }catch(Exception e){
	        	setValue(Float.parseFloat("0"));
	        	log.error("绑定float型数据错误，您传入的数据是：" + text + ",系统将自动识别为0。");
	        }
	    }  
	  
	    @Override  
	    public String getAsText() {  
	        return getValue().toString();  
	    }  
	}
	
	class LongEditor extends PropertiesEditor {  
	    @Override  
	    public void setAsText(String text) throws IllegalArgumentException {  
	        if (text == null || text.equals("")) {  
	            text = "0";  
	        }
	        try{
	        	setValue(Long.parseLong(text));  
	        }catch(Exception e){
	        	setValue(Long.parseLong("0"));
	        	log.error("绑定long型数据错误，您传入的数据是：" + text + ",系统将自动识别为0。");
	        }
	    }  
	  
	    @Override  
	    public String getAsText() {  
	        return getValue().toString();  
	    }  
	}
}