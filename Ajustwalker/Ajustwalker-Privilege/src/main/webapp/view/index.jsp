<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.Cookie"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="t" uri="http://www.talent.com/jsp/anyonelse"%>
<!DOCTYPE html>
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
	<head>
	    <meta charset="utf-8">
		<title><t:resource key="system.modulename"/></title>
		<meta name="description" content="<t:resource key="system.modulename"/>">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
		<meta name="renderer" content="webkit">
		<meta http-equiv="Expires" content="0">
		<meta http-equiv="Pragma" content="no-cache">
		<meta http-equiv="Cache-control" content="no-cache">
		<meta http-equiv="Cache" content="no-cache">
		<link rel="shortcut icon" href="<c:url value='/images/Talent.ico'/>">
		<link href="<c:url value='/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet" media="screen">
		<link href="<c:url value='/fonts/font-awesome/css/font-awesome.min.css'/>" rel="stylesheet">
		<link href="<c:url value='/css/animate.min.css'/>" rel="stylesheet">
		<link href="<c:url value='/css/login.min.css'/>" rel="stylesheet">
		<link href="<c:url value='/css/bootstrap-switch.min.css'/>" rel="stylesheet">
		<link href="<c:url value='/css/toastr.min.css'/>" rel="stylesheet">
	
		<script type="text/javascript" src="<c:url value='/plugins/jquery.min.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/plugins/bootstrap-switch.min.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/plugins/toastr.min.js'/>"></script>
	    <!--[if lte IE 6]>
			<script type="text/javascript" src="<c:url value='/bootstrap/js/bootstrap-ie.js'/>"></script>
		<![endif]-->
		
		<!--[if lt IE 9]>
			<script src="<c:url value='/plugins/html5shiv.min.js'/>"></script>
			<script src="<c:url value='/plugins/respond.min.js'/>"></script>
		<![endif]-->
		  
		<!--[if lte IE 6]>
			<link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap-ie6.css'/>">
		<![endif]-->
		
		<!--[if lte IE 7]>
			<link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/ie.css'/>">
		<![endif]-->
		<!--[if lt IE 8]>
			<meta http-equiv="refresh" content="0;ie.html" />
		<![endif]-->
		<script>
			if (window.top !== window.self) {
				window.top.location = window.location
			};
		</script>
		<style type="text/css">
			a{color:#fff !important;}
			a:hover{color:#fff !important;}
			a:visited{color:#fff !important;}
			.h1, .h2, .h3, h1, h2, h3 {
			    margin-top: 10px;
			}
			.h1, h1 {
			    font-size: 30px;
			}
		</style>
		<%
		    String sessionid = "";
			Cookie[] cookies = request.getCookies();
			if(null != cookies){
				for(int i=0;i<cookies.length;i++){
				    cookies[i].setMaxAge(0);
				    if(cookies[i].getName().equals("DWRSESSIONID")  ){
				    	sessionid = "DWRSESSIONID";
				    }
				    response.addCookie(cookies[i]);
				}
			}
		 %>
		 
	</head>
	<body class="signin" onload="document.loginForm.username.focus();">
		<div class="signinpanel">
			<div class="row">
				<div class="col-sm-7">
					<div class="signin-info">
						<div class="logopanel m-b">
							<h1><t:resource key="system.modulename"/></h1>
						</div>
						<div class="m-b"></div>
						<h4>
							信誉至上 管理为先 以人为本 永不满足
						</h4>
						<ul class="m-b">
							<li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 阳光计量,物流畅通</li>
							<li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 求精</li>
							<li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 求细</li>
							<li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 求实</li>
							<li><i class="fa fa-phone m-r-xs"></i> 项目组电话：18713913596</li>
						</ul>
						<ul class="m-b">
							<li>
								<a href="http://192.168.2.42:8080/Firefox-WinXP.exe">火狐浏览器XP&raquo;</a>&nbsp;
								<a href="http://192.168.2.42:8080/Firefox-Win7.exe">火狐浏览器Win7&raquo;</a>&nbsp;
								<a href="http://192.168.2.42:8080/TalentM3.exe">读卡插件&raquo;</a>
							</li>
							<li>
								<a href="http://192.168.2.42:6080/Logistical/unauth/registeworkpoint.do">作业点注册&raquo;</a>
								<a href="http://192.168.2.42:8080/rfidmgr.doc">RFID管理文档&raquo;</a>&nbsp;
								<a href="http://192.168.2.42:8080/usermanual.doc">操作手册&raquo;</a>&nbsp;
								<a href="http://192.168.2.42:8080/newsystem.doc">切换方案&raquo;</a>
							</li>
							<li>
								<a href="http://192.168.2.42:8080/JiLiangPiaoJuGuanLiGuiDing.doc">计量票据管理规定&raquo;</a>
								<a href="http://192.168.2.42:8080/ZhouZhuanXinxi.xls">周转信息&raquo;</a>&nbsp;
							</li>
						</ul>
					</div>
				</div>
				<div class="col-sm-5">
					<form name="loginForm" method="post" action="<c:url value='/login'/>" onsubmit="return beforeLogin()">
						<h4 class="no-margins">请输入用户名和密码</h4>
						<input id="sessionid" type="hidden"  value="<%=sessionid%>"/>
						<input id="username" type="hidden" name="username" value="LANDSCAPE"/> <!-- 用用户名字段传递登录成功后主页面的风格+用户名 -->
						<input id="remark" type="text" name="remark" class="form-control uname" placeholder="用户名"/>
						<input id="password" type="password" name="password"  class="form-control pword m-b" placeholder="密码" />
						<input id="switch-style" type="checkbox" checked data-on-color="info" data-handle-width="95" data-size="small" data-off-text="普通版" data-on-text="宽屏版">
						<input id="loginbtn" type="submit" value="<c:if test="${param.authentication_error == null}">登录</c:if><c:if test="${param.authentication_error == 1}">权限不足</c:if><c:if test="${param.authentication_error == 2}">登录失败，请重试</c:if><c:if test="${param.authentication_error == 3}">密钥过期，请刷新页面</c:if>" class="btn <c:if test="${param.authentication_error == null}">btn-info</c:if><c:if test="${param.authentication_error == 2 || param.authentication_error == 1 || param.authentication_error == 3}">btn-danger</c:if> btn-block"/>
					</form>
				</div>
			</div>
			<div cass="signup-footer">
				<div class="pull-left">&copy; 2016 All Rights Reserved. 山东天利和软件股份有限公司</div>
			</div>
		</div>
		<script type="text/javascript">
			toastr.options = {
					"closeButton": false,
					"debug": false,
					"newestOnTop": false,
					"progressBar": false,
					"rtl": false,
					"positionClass": "toast-top-center",
					"preventDuplicates": false,
					"onclick": null,
					"showDuration": 300,
					"hideDuration": 1000,
					"timeOut": 5000,
					"extendedTimeOut": 1000,
					"showEasing": "swing",
					"hideEasing": "linear",
					"showMethod": "fadeIn",
					"hideMethod": "fadeOut"
				}
			if($('#sessionid').val() == "DWRSESSIONID"){
				toastr["error"]('无法同时登录多个用户！');
			}
			String.prototype.trim=function(){
				return this.replace(/(^\s*)|(\s*$)/g, "");
			}
			
			$("#switch-style").bootstrapSwitch();
			$("#switch-style").on('switchChange.bootstrapSwitch', function(event, state) {
				if(state){
					$('#username').val('LANDSCAPE');
				}else{
					$('#username').val('PORTRAIT');
				}
			});
			
			$('#loginbtn').click(function(){
				$(this).removeClass('btn-info');
				$(this).addClass('btn-success');
				$(this).val('正在登录...');
			});
			
			function beforeLogin(){
				var username_v = $('#remark').val();
				var password_v = $('#password').val();
				if(getCookie('DWRSESSIONID') != null){
					toastr["error"]('无法同时登录多个用户！');
					$('#loginbtn').val('登录');
					return false;
				}else if(username_v.trim().length == 0){
					toastr["error"]('请填写用户名！');
					$('#loginbtn').val('登录');
					return false;
				}else if(password_v.trim().length == 0){
					toastr["error"]('请填写密码！');
					$('#loginbtn').val('登录');
					return false;
				}else{
					$('#username').val($('#username').val() + '@##@' + $('#remark').val());
					return true;
				}
			}
			function getCookie(name)
			{
				var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
				if(arr=document.cookie.match(reg))
				return unescape(arr[2]);
				else
				return null;
			}
		</script>
	</body>
</html>