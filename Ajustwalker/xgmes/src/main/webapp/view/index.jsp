<%@ page contentType="text/html;charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.Cookie"%>
<meta http-equiv="P3P" content='CP="IDC DSP COR CURa ADMa  OUR IND PHY ONL COM STA"'>
<%
	Cookie cookie = new Cookie("access_token",request.getParameter("access_token"));
	cookie.setMaxAge(365*24*60*60);
	response.addCookie(cookie);
%>