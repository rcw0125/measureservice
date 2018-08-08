<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix='s' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="t" uri="http://www.talent.com/jsp/anyonelse"%>
<t:html>
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
		<link href="<c:url value='/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet">
	    <link href="<c:url value='/css/jquery.smartmenus.bootstrap.css'/>" rel="stylesheet">
	    <link href="<c:url value='/fonts/font-awesome/css/font-awesome.min.css'/>" rel="stylesheet">
		<link href="<c:url value='/css/jquery-confirm.css'/>" rel="stylesheet">
		<link href="<c:url value='/css/animate.min.css'/>" rel="stylesheet">
		<link href="<c:url value='/css/style.min2.css'/>" rel="stylesheet">
	
		<script type="text/javascript" src="<c:url value='/plugins/jquery.min.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/bootstrap/js/bootstrap.min.js'/>"></script>
	    <script type="text/javascript" src="<c:url value='/plugins/jquery.smartmenus.min.js'/>"></script>
	    <script type="text/javascript" src="<c:url value='/plugins/jquery.smartmenus.bootstrap.min.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/plugins/json2.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/plugins/map.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/plugins/jquery.goup.min.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/plugins/jquery-confirm.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/dwr/util.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/dwr/interface/pushMessageCompont.js'/>"></script>
	
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
	        .small,small{position: initial;}
	    </style>
	    <script type="text/javascript">
		    function token(subsystem,access_token_v){
				$.ajax({
					url:subsystem + '/',
					dataType: "jsonp",
			        crossDomain: true,
			        jsonp:"callback",
		            data:{access_token:access_token_v},
		            complete:function(xhr,ts){
		            	
		            }
		        });
			}
			function callback(json){
			    
			}
		</script>
	</head>
	<body style="overflow-x:hidden;padding:0px;" onload="dwr.engine.setActiveReverseAjax(true);dwr.engine.setNotifyServerOnPageUnload(true);onPageLoad();">
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
									<input id="rp_usercode" name="rp_usercode" readonly="readonly" type="text" class="form-control" aria-describedby="basic-addon1" value="<s:authentication property="principal.displayname"/>">
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
					    <button type="button" class="btn btn-success" onclick="confirmpassword()" style="width:100%;">
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
				                    <div class="site-name"><t:resource key="system.modulename"/></div>
				                    <div class="site-slogan"><a target="_blank" href="http://www.cn-talent.net/" style="color:#ffffff">山东天利和软件股份有限公司</a>
				                    </div>
				                </div>
	                		</td>
	                	</tr>
	                </table>
	            </div>
	            <div class="navbar-collapse collapse">
	                <ul id="favoritePage" class="nav navbar-nav navbar-right">
	                	<c:forEach items="${menus}" var="item">
							<li>
		                    	<a href="javascript:void(0)">${item.ROOT}<span class="caret"></span></a>
		                        <ul class="dropdown-menu">
		                           ${item.SUBS}
		                        </ul>
		                    </li>
						</c:forEach>
	                    <li>
		                	<a href="javascript:void(0)" onclick="repassword()">
		                		<span class="glyphicon glyphicon-user"></span>&nbsp;<s:authentication property="principal.displayname"/>
		                	</a>
		                </li>
	                    <li>
		                	<a href="javascript:void(0)">
		                		<span class="glyphicon glyphicon-bell"></span>
		                	</a>
		                </li>
		                <li>
		                	<a href="javascript:void(0)" onclick="signout()">
		                		<span class="glyphicon glyphicon-off"></span>
		                	</a>
		                </li>
	                </ul>
	            </div>
	        </div>
	    </div>
	    <div class="container-fluid" style="padding-top:55px;">
			<div class="row content-tabs-nr">
                <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
                </button>
                <nav class="page-tabs J_menuTabs">
                    <div class="page-tabs-content">
                        <a href="javascript:;" class="active J_menuTab" data-id="<c:url value='/dashboard'/>"><i class="fa fa-home" aria-hidden="true"></i>&nbsp;工作台</a>
                    </div>
                </nav>
                <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
                </button>
                <div class="btn-group roll-nav roll-right">
                    <button class="dropdown J_tabClose" data-toggle="dropdown">页面操作<span class="caret"></span></button>
                    <ul role="menu" class="dropdown-menu dropdown-menu-right">
                        <li class="J_tabShowActive"><a><span class="glyphicon glyphicon-pushpin"></span>&nbsp;定位当前页面</a>
                        </li>
                        <li class="divider"></li>
                        <li class="J_tabCloseAll"><a><span class="glyphicon glyphicon-trash"></span>&nbsp;关闭全部页面</a>
                        </li>
                        <li class="J_tabCloseOther"><a><span class="glyphicon glyphicon-remove"></span>&nbsp;关闭其他页面</a>
                        </li>
                        <li class="J_tabCloseOther"><a><span class="glyphicon glyphicon-star"></span>&nbsp;收藏到工作台</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="row J_mainContent" id="content-main">
                <iframe class="J_iframe" name="iframe0" width="100%" src="<c:url value='/dashboard'/>" frameborder="0" data-id="<c:url value='/dashboard'/>" seamless></iframe>
            </div>
	    </div>
	    <!--mini聊天窗口开始-->
        <div class="small-chat-box fadeInRight animated">
            <div class="heading" draggable="true">
                <small class="chat-date pull-right">
                </small> 消息盒子
            </div>
            <div class="content">
                <div class="left">
                    <div class="author-name">游客
                    <small class="chat-date">
                    	11:24
                    </small>
                    </div>
                    <div class="chat-message active">
                    	你好
                    </div>
                </div>
                <div class="right">
                    <div class="author-name">游客
                        <small class="chat-date">
                            11:24
                        </small>
                    </div>
                    <div class="chat-message">
						你好
                    </div>
                </div>
            </div>
            <div class="form-chat">
                <div class="input-group input-group-sm">
                    <input type="text" class="form-control"> <span class="input-group-btn"> <button
                        class="btn btn-primary" type="button">发送
                </button> </span>
                </div>
            </div>
        </div>
        <div id="small-chat">
            <span class="badge badge-warning pull-right">1</span>
            <a class="open-small-chat">
                <i class="fa fa-comments"></i>
            </a>
        </div>
	    <script type="text/javascript">
		    function onPageLoad() {
	    		dwr.engine._errorHandler = function(message, ex){
	    			dwr.engine._debug("Error: " + ex.name + ", " + ex.message, true);
	    		};
	    		dwr.engine.setTimeout(5000);
			}
		    
			function repassword(){
				$('#RepasswordWindow').modal({show:true,backdrop:'static',keyboard:false});
			}
			
			function confirmpassword(){
				if('' == $.trim($('#rp_password').val())){
					errorbox('请填写原密码！');
					return;
				}
				if('' == $.trim($('#rp_newpassword').val())){
					errorbox('请填写新密码！');
					return;
				}
				if($.trim($('#rp_newpassword').val()) != $.trim($('#rp_cnewpassword').val())){
					errorbox('两次输入的新密码必须一致！');
					return;
				}
				
				$.ajax({
		            type: "post",
		            url: '<c:url value="/user/repassword"/>',
		            dataType: "json",
		            data:{username:$('#rp_usercode').val(),repassword:$('#rp_password').val(),password:$('#rp_newpassword').val()},
		            success: function(data){
		            	$('#RepasswordWindow').modal('toggle');
		            	successbox(data.msg);
		            },
		            error:function(data){
		            	errorbox(data.msg);
		            }
	            });
			}
			
			function signout(){
				$.confirm({
					keyboardEnabled: true,
					icon: 'glyphicon glyphicon-question-sign',
				    title: '退出系统',
				    content:"确认要退出<t:resource key="system.modulename"/>？",
				    confirmButton: '确定',
				    cancelButton: '取消',
				    confirmButtonClass: 'btn-danger',
				    cancelButtonClass: 'btn-info',
				    confirm: function(){
				    	delCookie('DWRSESSIONID');
			    		window.location.href='<c:url value="/unauth/logout.do"/>';
				    }
				});
			}
			
			function successbox(msg){
				$.alert({
					keyboardEnabled: true,
					icon: 'glyphicon glyphicon-ok-sign',
					backgroundDismiss: true,
				    autoClose: 'confirm|3000',
				    title: '成功',
				    content:msg,
				    confirmButton: '关闭',
				    confirmButtonClass: 'btn-success'
				});
		    }
			
			function errorbox(msg){
				$.alert({
					keyboardEnabled: true,
					icon: 'glyphicon glyphicon-remove-sign',
				    title: '错误',
				    content:msg,
				    confirmButton: '关闭',
				    confirmButtonClass: 'btn-danger'
				});
		    }
			
			function showMessage(sendMessages) {
		    	$.alert({
					keyboardEnabled: true,
					icon: 'glyphicon glyphicon-remove-sign',
				    title: '系统提示',
				    content:sendMessages,
				    confirmButton: '关闭',
				    confirmButtonClass: 'btn-danger'
				});
			}
			function delCookie(cookiename){    
	    	     var date = new Date(); 
	             date.setTime(date.getTime() - 10000); 
	             document.cookie = cookiename+"=a; expires=" + date.toGMTString()+"; path=/Privilege";
	             document.cookie = cookiename+"=a; expires=" + date.toGMTString()+"; path=/Ajustwalker-Privilege";
	         } 
	    </script>
	    <script type="text/javascript" src="<c:url value='/plugins/jquery.metisMenu.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/plugins/jquery.slimscroll.min.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/plugins/layer/layer.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/plugins/platform.min.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/plugins/contabs.min.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/plugins/pace.min.js'/>"></script>
	</body>
</t:html>