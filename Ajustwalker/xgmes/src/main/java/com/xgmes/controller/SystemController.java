package com.xgmes.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class SystemController {
	
	@RequestMapping(value = "/index.do")
	public String index(ModelMap model) {
		return "index";
	}
	
	@RequestMapping(value = "/main")
	public String main(ModelMap model) {
		return "main";
	}
	
	@RequestMapping(value = "/main2")
	public String main2(ModelMap model,HttpServletRequest request) {
		return "main2";
	}
}