<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
	<title>智能化远程集中计量管理系统v4.0</title>
	<meta name="description" content="山东天利和软件股份有限公司智能化远程集中计量管理系统 v4.0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="renderer" content="webkit">
	<link rel="shortcut icon" href="<c:url value='/images/Talent.ico'/>">
	<link href="<c:url value='/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/css/jquery.smartmenus.bootstrap.css'/>" rel="stylesheet">
    <link href="<c:url value='/fonts/font-awesome/css/font-awesome.min.css'/>" rel="stylesheet">
	<link href="<c:url value='/css/login-style.css'/>" rel="stylesheet">

	<script type="text/javascript" src="<c:url value='/plugins/jquery.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/bootstrap/js/bootstrap.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/plugins/jquery.bootstrap.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/plugins/jquery.smartmenus.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/plugins/jquery.smartmenus.bootstrap.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/plugins/jquery.cookie.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/plugins/json2.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/plugins/map.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/plugins/jquery.goup.min.js'/>"></script>

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

    <style>
        body{
            margin: 0;
            font-family: "Microsoft YaHei" ! important;
            font-size: 12px;
            line-height: 20px;
            color: #333333;
            background: #eee;
        }

        a{
            text-decoration: none;
        }

        .navbar-default {
            color: #4ACFE2;
            background-color:#4ACFE2;
            -webkit-transition: all 0.2s ease-in-out;
            -moz-transition: all 0.2s ease-in-out;
            -o-transition: all 0.2s ease-in-out;
            -ms-transition: all 0.2s ease-in-out;
            transition: all 0.2s ease-in-out;
            height: 55px;
            padding: 0 0px;
            vertical-align: middle;
            border-color: rgba(0, 0, 0, 0.10);
        }
        .navbar-right>li{
            top:3px;
        }

        .navbar-default .navbar-nav > li > a {
            color: #fff;
            font-size: 15px;
        }

        .navbar-collapse.in {
            background: #4ACFE2;
            color: #fff;
        }

        .navbar-default .navbar-nav > .open > a, .navbar-default .navbar-nav > .open > a:hover {
            background-color: transparent;
        }

        .logo,
        .site-name-and-slogan {
            padding-top: 5px;
            float: left;
        }

        .site-name {
            font-size: 24px;
            color: #ffffff;
            -webkit-transition: all 0.3s ease-in-out;
            -moz-transition: all 0.3s ease-in-out;
            -o-transition: all 0.3s ease-in-out;
            -ms-transition: all 0.3s ease-in-out;
            transition: all 0.3s ease-in-out;
        }

        .site-slogan {
            font-size: 12px;
            line-height: 30px;
        }
        
        .modal-content {
			-webkit-border-radius: 0px;
			-moz-border-radius: 0px;
			border-radius: 4px;
		}
		.modal-header {
			background-color: #4ACFE2;
			color: #ffffff;
			border-radius: 4px;
		}
		.modal-header h4 {
			color: #ffffff;
		}
		.modal-body{
			font-size:15px;
		}
		button.btn{
			height:40px;
			line-height:30px;
		}
		button.btn-danger{
			color: #fff;
    		background-color: #d9534f;
    		border-color: #d43f3a;
		}
		.modal-header .close {
			font-weight: 300;
			color: #FFFFFF;
			text-shadow: none;
			filter: alpha(opacity=100);
			opacity: 1;
		}
		@media (min-width:1200px) {
			.modal-lg {
				width: 1140px;
			}
		}
    </style>
	<script type="text/javascript">	
		var openedMenu = new Array();
	    function openContent(id_v,name_v,url_v,icon_v,opened_v){
	    	document.getElementById("mainContent").style.height = (document.documentElement.clientHeight - 100) + 'px';
	    	var isOpen = false;
	    	for(var i=0;i<openedMenu.length;i++){
    			if(openedMenu[i].id == id_v){
    				isOpen = true;
    			}
    		}
	    	
	    	if(0 == opened_v && !isOpen){
	    		if(3 == openedMenu.length){
	    			$('#liid'+openedMenu[0].id).remove();
	    			openedMenu.shift();
	    		}
	    		$('#favoritePage').prepend('<li id="liid'+id_v+'"><a href="javascript:void(0)" onclick="openContent(\''+id_v+'\',\''+name_v+'\',\''+url_v+'\',\''+icon_v+'\',1)"><span class="glyphicon '+icon_v+'"></span>&nbsp;'+name_v+'</a></li>');
	    		openedMenu[openedMenu.length%3] = {id:id_v,name:name_v,url:url_v,icon:icon_v};
	    	}
	    	var menuHtml = $('#menuid' + id_v).parent().parent().children('a').html();
	    	$('#subMenuName').html(menuHtml.substring(menuHtml.indexOf('</span>') + 7,menuHtml.indexOf('<span class="caret">')).replace('&nbsp;',''));
	    	$('#endMenuName').html(name_v);
        	document.getElementById("mainContent").src = url_v;
        }
	    
	    var preHeight = -1;
	    var browserVersion = window.navigator.userAgent.toUpperCase();
	    var isOpera = false, isFireFox = false, isChrome = false, isSafari = false, isIE = false;
	    
	    function iFrameHeight(){
	    	preHeight = -1;
	    }
	    
	    function reinitIframe(){
	        try {
	            var bHeight = 0;
	            var dHeight = 0;
	            var iframe = document.getElementById("mainContent");
	            if (isChrome == false && isSafari == false){
	            	bHeight = iframe.contentWindow.document.body.scrollHeight;
	            }
	            
	            if (isFireFox == true){
	            	dHeight = iframe.contentWindow.document.documentElement.offsetHeight + 2;
	            }else if (isIE == false && isOpera == false){
	            	dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
	            }
	            
	            var height = Math.max(bHeight, dHeight);
	            if(preHeight != height){
	            	iframe.style.height = height + "px";
	            	try
	            	{
	            		if(undefined != iframe.contentWindow.resizeCallBack){
	            			iframe.contentWindow.resizeCallBack();
			            }
	            	}
	            	catch(e)
	            	{
	            		
	            	}
	            	preHeight = height;
	            }
	        }catch(ex){}
	    }
	    
	    function startInit() {
	        isOpera = browserVersion.indexOf("OPERA") > -1 ? true : false;
	        isFireFox = browserVersion.indexOf("FIREFOX") > -1 ? true : false;
	        isChrome = browserVersion.indexOf("CHROME") > -1 ? true : false;
	        isSafari = browserVersion.indexOf("SAFARI") > -1 ? true : false;
	        if (!!window.ActiveXObject || "ActiveXObject" in window){
	        	isIE = true;
	        }
			
	        window.setInterval(reinitIframe,500);
	    }
	</script>
</head>
<body style="overflow-x:hidden;padding:0px;">
    <div class="modal fade" id="LoginWindow" tabindex="-1" role="dialog" aria-labelledby="LoginWindowLabel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<div class="top-content">        	
			            <div class="inner-bg">
		                    <div class="row">
		                        <div class="col-sm-12 form-box">
		                        	<div class="form-top">
		                        		<div class="form-top-left">
		                        			<h3>智能化远程集中计量管理系统</h3>
		                            		<p>输入您的工号和密码登录</p>
		                            		<h5 style="color:#66CCFF;">请使用IE8及以上浏览器访问本系统，建议使用火狐浏览器</h5>
		                        		</div>
		                        		<div class="form-top-right">
		                        			<i class="fa fa-key"></i>
		                        		</div>
		                            </div>
		                            <div class="form-bottom">
					                    <div class="continer-fluid">
					                    	<div class="row">
					                    		<div class="form-group">
						                    		<label class="sr-only" for="form-username">用户名</label>
						                        	<input type="text" id="usercode" name="usercode" placeholder="用户名" class="form-username form-control">
						                        </div>
					                    	</div>
					                    	<div class="row">
					                    		<div class="form-group">
						                        	<label class="sr-only" for="form-password">密　码</label>
						                        	<input type="password" id="password" name="password" placeholder="密码" class="form-password form-control">
						                        </div>
					                    	</div>
					                    	<div class="row">
					                    		<div class="col-sm-12" style="padding-left:0px;padding-right:0px;">
					                    			<div class="form-group">
						                    			<button type="button" class="btn btn-success" onclick="login()" style="width:100%;">
															<span class="glyphicon glyphicon-user" aria-hidden="true"></span>&nbsp;登录
														</button>
													</div>
					                    		</div>
					                    	</div>
					                    	<div class="row">
					                    		<div class="col-sm-12" style="padding-left:0px;padding-right:0px;">
					                    			<div style="float:right;"><a onclick="repassword()" style="cursor:hand"><i class="glyphicon glyphicon-lock"></i></a></div>
					                    		</div>
					                    	</div>
					                    </div>
				                    </div>
		                        </div>
		                    </div>                   
			            </div>            
			        </div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="RepasswordWindow" tabindex="-1" role="dialog" aria-labelledby="RepasswordLabel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">修改密码</h4>
				</div>
				<div class="modal-body" style="padding-bottom:0px;">
					<div class="continer-fluid">
                    	<div class="row-fluid">
	                        <div class="form-group input-group">
								<span class="input-group-addon">用　户　名</span>
								<input id="rp_usercode" name="rp_usercode"  type="text" class="form-control" placeholder="必填" aria-describedby="basic-addon1">
							</div>
                    	</div>
                    	<div class="row-fluid">
	                        <div class="form-group input-group">
								<span class="input-group-addon">原　密　码</span>
								<input id="rp_password" name="rp_password"  type="password" class="form-control" placeholder="必填" aria-describedby="basic-addon1">
							</div>
                    	</div>
                    	<div class="row-fluid">
	                        <div class="form-group input-group">
								<span class="input-group-addon">新　密　码</span>
								<input id="rp_newpassword" name="rp_newpassword"  type="password" class="form-control" placeholder="必填" aria-describedby="basic-addon1">
							</div>
                    	</div>
                    	<div class="row-fluid">
                    		<div class="form-group input-group">
								<span class="input-group-addon">确认新密码</span>
								<input id="rp_cnewpassword" name="rp_cnewpassword"  type="password" class="form-control" placeholder="必填" aria-describedby="basic-addon1">
							</div>
                    	</div>
                    </div>
				</div>
				<div class="modal-footer">
				    <button type="button" class="btn btn-warning" onclick="confirmpassword()" style="width:100%;">
						<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>&nbsp;确定
					</button>
				</div>
			</div>
		</div>
	</div>
    <div class="navbar navbar-default navbar-fixed-top" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <table>
                	<tr>
                		<td width="50">
                			<img src="<c:url value='/images/logo.png'/>" style="width:50px;height:50px;margin-top:-5px;" alt="山东天利和软件股份有限公司">
                		</td>
                		<td>
                			<div class="site-name-and-slogan">
			                    <div class="site-name">智能化远程集中计量管理系统</div>
			                    <div class="site-slogan"><a target="_blank" href="http://www.cn-talent.net/" style="color:#ffffff">山东天利和软件股份有限公司</a>
			                    </div>
			                </div>
                		</td>
                	</tr>
                </table>
            </div>
            <div class="navbar-collapse collapse">
                <ul id="favoritePage" class="nav navbar-nav navbar-right">
                    <li><a href="javascript:void(0)"><span class="glyphicon glyphicon-list"></span>&nbsp;计量系统<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                           ${usermenus}
                        </ul>
                    </li>
                    <li>
	                	<a href="javascript:void(0)">
	                		<span class="glyphicon glyphicon-user"></span>&nbsp;${username}
	                	</a>
	                </li>
	                <li>
	                	<a href="javascript:void(0)" onclick="signout()">
	                		<span class="glyphicon glyphicon-log-out"></span>&nbsp;退出
	                	</a>
	                </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="container-fluid" style="margin-top:55px;">
    	<div class="row" style="height:36px;">
	    	<ul class="breadcrumb">
				<li>
					<a href="javascript:void(0)" class="fa fa-home">&nbsp;计量系统</a>
					<span class="divider"></span>
				</li>
				<li>
					<a id="subMenuName" href="javascript:void(0)">计量配置</a>
					<span class="divider"></span>
				</li>
				<li id="endMenuName" class="active">系统监控</li>
			</ul>
		</div>
        <div class="row">
            <iframe id="mainContent" src="<c:url value='/dashboard.do'/>" style="width:100%;" frameborder="0" scrolling="no" marginheight="0" marginwidth="0"></iframe>
        </div>
    </div>
    <script type="text/javascript">
    	startInit();
        $(document).ready(function() {
			$(function(){
				if(undefined == $.cookie('username') || null == $.cookie('username') || 'null' == $.cookie('username')){
			        $('#LoginWindow').modal({show:true,backdrop:'static',keyboard:false});
				}
		    });
			
			$.goup({
                trigger: 100,
                bottomOffset:30,
                locationOffset:30,
                title: '回到顶部',
                titleAsText: true
            });
		});
		
		$('#LoginWindow').keyup(function(event){
			if(event.keyCode ==13){		
				login();
			}
		});
		
		function repassword(){
			$('#RepasswordWindow').modal({show:true,backdrop:'static',keyboard:false});
		}
		
		function confirmpassword(){
			if('' == $.trim($('#rp_usercode').val())){
				$.messager.popup('请填写用户名！');
				return;
			}
			if('' == $.trim($('#rp_password').val())){
				$.messager.popup('请填写原密码！');
				return;
			}
			if('' == $.trim($('#rp_newpassword').val())){
				$.messager.popup('请填写新密码！');
				return;
			}
			if($.trim($('#rp_newpassword').val()) != $.trim($('#rp_cnewpassword').val())){
				$.messager.popup('两次输入的新密码必须一致！');
				return;
			}
			
			$.ajax({
	            type: "post",
	            url: '<c:url value="/privilege/updatePassword.do"/>',
	            dataType: "json",
	            data:{usercode:$('#rp_usercode').val(),repassword:$('#rp_password').val(),password:$('#rp_newpassword').val()},
	            success: function(data){
	            	$('#RepasswordWindow').modal('toggle');
	            	$.messager.popup(data.msg);
	            },
	            error:function(data){
	            	$.messager.popup(data.msg);
	            }
            });
		}
		
		function login(){
			$('#loginButton').attr('disabled',true);
			$.ajax({
	            type: "post",
	            url: '<c:url value="/privilege/login.do"/>',
	            dataType: "json",
	            data:{usercode:$('#usercode').val(),password:$('#password').val()},
	            success: function(data){
	            	$('#loginButton').attr('disabled',false);
	            	if(data.success){
	            		$.cookie('username',data.more.username);
	            		$.cookie('usercode',data.more.usercode);
	            		$('#LoginWindow').modal('toggle');
	            		window.location.href='<c:url value="/"/>';
	            	}else{
	            		$.messager.popup(data.msg);
	            	}
	            },
	            error:function(data){
	            	$('#loginButton').attr('disabled',false);
	            	$.messager.popup(data.msg);
	            	window.location.href='<c:url value="/"/>';
	            }
            });
		}
		
		function signout(){
			$.messager.confirm("请确认", "确认要退出远程计量系统？", function() { 
				$.cookie('username',null);
	    		$.cookie('usercode',null);
	    		window.location.href='<c:url value="/"/>';
		    });			
		}
    </script>
</body>
</html>