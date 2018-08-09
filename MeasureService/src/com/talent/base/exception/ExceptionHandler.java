package com.talent.base.exception;

import org.springframework.aop.ThrowsAdvice;

public class ExceptionHandler implements ThrowsAdvice {

	public void afterThrowing(Exception e) throws Throwable {
		System.out.println("####################################出异常了####################################");
		
		//MailUtils.getInstance().sendMail(null,ResourceUtils.getResource("manager.mail.addr"), ResourceUtils.getResource("system.project_name") + "系统出现错误！",sdf.format(calendar.getTime()) + "数据库连接错误，请检查数据库连接！");
		e.printStackTrace();
	}
}
