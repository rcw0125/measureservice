<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix='s' uri='http://www.springframework.org/security/tags'%>
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
		<link href="<c:url value='/css/jquery-confirm.css'/>" rel="stylesheet">
		<link href="<c:url value='/css/animate.min.css'/>" rel="stylesheet">
		<link href="<c:url value='/css/office/office.css'/>" rel="stylesheet">
		<link href="<c:url value='/css/office/skins/_all-skins.min.css'/>" rel="stylesheet">
		<link href="<c:url value='/plugins/datatable/bootstrap-table.min.css'/>" rel="stylesheet">
	
		<script type="text/javascript" src="<c:url value='/plugins/jquery.min.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/bootstrap/js/bootstrap.min.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/plugins/datatable/bootstrap-table.min.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/plugins/datatable/bootstrap-table-zh-CN.min.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/dwr/util.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/dwr/interface/pushMessageCompont.js'/>"></script>
		
	    <!--[if lte IE 6]>
			<script type="text/javascript" src="<c:url value='/bootstrap/js/bootstrap-ie.js'/>"></script>
		<![endif]-->
		
		<!--[if lte IE 9]>
			<script src="<c:url value='/plugins/html5shiv.min.js'/>"></script>
			<script src="<c:url value='/plugins/respond.min.js'/>"></script>
			<script type="text/javascript" src="<c:url value='/plugins/base64.min.js'/>"></script>
		<![endif]-->
		  
		<!--[if lte IE 6]>
			<link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap-ie6.css'/>">
		<![endif]-->
		
		<!--[if lte IE 7]>
			<link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/ie.css'/>">
		<![endif]-->
		
		<style type="text/css">
			.small,small{position: initial;}
			.content-tabs-nr{position:relative;height:50px;background:transparent;line-height:48px}.content-tabs-nr .roll-nav,.page-tabs-list{position:absolute;width:40px;height:48px;text-align:center;color:#fff;z-index:2;top:0}.content-tabs-nr .roll-left{left:0;}.content-tabs-nr .roll-right{right:0;}.content-tabs-nr button{background:transparent;border:0;height:48px;width:40px;outline:0}.content-tabs-nr button:hover{background:transparent}nav.page-tabs{margin-left:40px;width:100000px;height:48px;overflow:hidden;position:absolute;}nav.page-tabs .page-tabs-content{float:left}.page-tabs a{display:block;float:left;padding:0 10px}.page-tabs a i:hover{color:#c00}.content-tabs-nr .roll-nav:hover,.page-tabs a:hover{color:#fff;background:transparent;cursor:pointer}.roll-right.J_tabRight{right:180px}.roll-right.btn-group{right:100px;width:80px;padding:0}.roll-right.btn-group button{width:80px}.roll-right.J_tabExit{background:transparent;height:48px;width:60px;outline:0;right:40px;}.dropdown-menu-right{left:auto}.page-tabs a{color:#fff}.page-tabs a i{color:#ccc}.page-tabs a.active{background:transparent;color:#000000}.page-tabs a.active i:hover,.page-tabs a.active:hover{background:transparent;color:#000000}
			.jconfirm-box-container i {
				color: #000 !important;
			}
		</style>
		<script type="text/javascript">
			try
			{
				history.pushState(null, null, document.URL);
				window.addEventListener('popstate', function () {
					history.pushState(null, null, document.URL);
				});
			}
			catch(e)
			{
				
			}
			function token(subsystem,access_token_v){
				$.ajax({
					url:subsystem + '/index.do',
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
			window.getHeight = function(){
			    if(window.innerHeight!= undefined){  
			        return window.innerHeight;  
			    }
			    else{
			        var B= document.body, D= document.documentElement;  
			        return Math.min(D.clientHeight, B.clientHeight);  
			    }
			}
		</script>
		<%
			Cookie cookie = new Cookie("access_token",request.getParameter("access_token"));
			cookie.setMaxAge(365*24*60*60);
			response.addCookie(cookie);
		%>
	</head>
	<body class="hold-transition skin-blue fixed sidebar-mini" style="overflow:hidden" onbeforeunload="delCookie('DWRSESSIONID')" onload="dwr.engine.setActiveReverseAjax(true);dwr.engine.setNotifyServerOnPageUnload(true);onPageLoad();">
		<input type="hidden" id ="servicing" value="<s:authentication property='principal.servicing'/>" />
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
		<div class="modal fade" id="NoReadListWindow" tabindex="-1" role="dialog" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">待办事项</h4>
					</div>
					<div class="modal-body" style="padding-top:0px;">
						<div class="row">
							<div class="col-sm-12" style="padding-left:5px;padding-right:5px;">
								<table id="NoReadListGrid" data-toggle="table" data-row-style="rowStyle" data-pagination="false" data-mobile-responsive="true">
									<thead>
										<tr>
											<th data-field="DOCUMENTCODE" data-visible="false">ID</th>
											<th data-field="DOCUMENTNAME" data-halign="center" class="text-center">单据名称</th>
											<th data-field="C" data-halign="center" class="text-center">未读个数</th>
										</tr>
									</thead>
								</table>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12" style="padding-left:5px;padding-right:5px;padding-top:10px;">
								<button type="button" class="btn btn-success" onclick="neverRemind()" style="width:100%;">
									<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>&nbsp;不再提醒
								</button>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12" style="padding-left:5px;padding-right:5px;padding-top:10px;">
								<button type="button" class="btn btn-danger" onclick="closeRemindWindow()" style="width:100%;">
									<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>&nbsp;关　　闭
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	    <div id="wrapper">
	    	<header class="main-header">
		        <a href="#" class="logo">
		            <span class="logo-mini">XLS</span>
		            <span class="logo-lg"><t:resource key="system.shortname"/></span>
		        </a>
		        <nav class="navbar navbar-static-top">
		            <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
		                <span class="sr-only">菜单</span>
		                <span class="icon-bar"></span>
		                <span class="icon-bar"></span>
		                <span class="icon-bar"></span>
		            </a>
		            <div class="dashbard-1" id="page-wrapper">
						<div class="content-tabs-nr" style="left:40px;">
			            	<button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i></button>
			                <nav class="page-tabs J_menuTabs">
			                    <div class="page-tabs-content">
			                        <a href="javascript:;" class="active J_menuTab" data-id="<c:url value='/dashboard'/>">工作台</a>
			                    </div>
			                </nav>
			                <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i></button>
			                <div class="btn-group roll-nav roll-right">
			                    <button class="dropdown J_tabClose" data-toggle="dropdown">页面操作<span class="caret"></span></button>
			                    <ul role="menu" class="dropdown-menu dropdown-menu-right">
			                        <li class="J_tabShowActive"><a>定位当前选项卡</a></li>
			                        <li class="divider"></li>
			                        <li class="J_tabCloseAll"><a>关闭全部选项卡</a></li>
			                        <li class="J_tabCloseOther"><a>关闭其他选项卡</a></li>
			                        <li>
			                        	<div class="tab-pane" id="control-sidebar-home-tab"></div>
			                        </li>
			                    </ul>
			                </div>
			                <a href="javascript:void(0);" onclick="signout()" class="roll-nav roll-right J_tabExit"><span class="fa fa fa-sign-out"></span>退出</a>
			            </div>
		            </div>
		        </nav>
		    </header>
		    <aside class="main-sidebar">
		        <section class="sidebar">
		            <div class="user-panel">
		                <div class="pull-left image">
		                    <img src="<c:url value='images/company.png'/>" class="img-circle" alt="User Image">
		                </div>
		                <div class="pull-left info">
		                    <p><a href="javascript:void(0)" onclick="repassword()"><s:authentication property="principal.displayname"/></a></p>
		                    <a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
		                </div>
		            </div>
		            <ul class="sidebar-menu">
		                <li class="header">主菜单</li>
		                <li class="treeview">
		                    <a class="J_menuItem" href="<c:url value='/dashboard'/>">
		                        <i class="fa fa-dashboard"></i> <span>工作台</span>
		                    </a>
		                </li>
		                <c:forEach items="${menus}" var="item">
							<li class="treeview">
			                    ${item.ROOT}
		                        <ul class="treeview-menu">
		                           ${item.SUBS}
		                        </ul>
		                    </li>
						</c:forEach>
						
		                <%-- 
		                <li>
		                    <a href="../mailbox/mailbox.html">
		                        <i class="fa fa-envelope"></i> <span>我的邮件</span>
		                        <span class="pull-right-container">
		                          <small class="label pull-right bg-yellow">12</small>
		                          <small class="label pull-right bg-green">16</small>
		                          <small class="label pull-right bg-red">5</small>
		                        </span>
		                    </a>
		                </li> 
		                --%>
		                <li><a href="#"><i class="fa fa-book"></i> <span>帮助文档</span></a></li>
		                <li class="header">标签</li>
		                <li><a href="#"><i class="fa fa-circle-o text-red"></i> <span>重要</span></a></li>
		                <li><a href="#"><i class="fa fa-circle-o text-yellow"></i> <span>报警</span></a></li>
		                <li><a href="#"><i class="fa fa-circle-o text-aqua"></i> <span>通知</span></a></li>
		            </ul>
		        </section>
		    </aside>
		    <div class="content-wrapper J_mainContent" id="content-main">
				<iframe class="J_iframe" name="iframe0" width="100%" height="1000px" src="<c:url value='/dashboard'/>" frameborder="0" data-id="<c:url value='/dashboard'/>" seamless></iframe>
		    </div>
		    <aside class="control-sidebar control-sidebar-dark">
		        <ul class="nav nav-tabs nav-justified control-sidebar-tabs"></ul>
		        <div class="tab-content">
		            <div class="tab-pane" id="control-sidebar-home-tab"></div>
		            <div class="tab-pane" id="control-sidebar-stats-tab"></div>
		            <div class="tab-pane" id="control-sidebar-settings-tab"></div>
		        </div>
		    </aside>
	    </div>
	    <script type="text/javascript">
	    	var reminder;
	    	/* $(document).ready(function(){
	    		if(document.getElementById('servicing').value != '0'){
					$.alert({
						keyboardEnabled: true,
						icon: 'glyphicon glyphicon-remove-sign',
					    autoClose: 'confirm|5000',
					    title: '退出系统',
					    content:"用户已禁用，无权限使用系统！",
					    confirmButton: '退出',
					    confirmButtonClass: 'btn-success',
					    confirm: function(){
					    	delCookie('DWRSESSIONID');
				    		window.location.href='<c:url value="/unauth/logout.do"/>';
					    }
					});
				}else{
					resizeContentHeight();
		    		loadReminders();
				}
	    	}); */
	    	
	    	reminder = setInterval(function(){
	    		loadReminders();
    		},300000);
	    	
	    	function loadReminders(){
	    		$.ajax({
		            type: "post",
		            url: '<c:url value="/getreminders"/>',
		            dataType: "json",
		            success: function(data){
		            	if(data.success){
		            		$('#NoReadListWindow').unbind('shown.bs.modal');
		                	$('#NoReadListWindow').on('shown.bs.modal', function(){
		                		$('#NoReadListGrid').bootstrapTable('refreshOptions',{pagination:false,data:data.data});
		                   	}).modal('show');
		            	}
		            }
	            });
	    	}
	    	
	    	$('#NoReadListGrid').bootstrapTable({onDblClickRow:function(row, $element, field){
	    		window.open('http://192.168.2.42:6080/Logistical/application/document' + row.DOCUMENTCODE + '?reminder=1');
	    	}});
	    	
	    	function neverRemind(){
	    		window.clearInterval(reminder);
	    		$('#NoReadListWindow').modal('toggle');
	    	}
	    	
	    	function closeRemindWindow(){
	    		$('#NoReadListWindow').modal('toggle');
	    	}
	    	
	    	window.onresize = function(){
	    		resizeContentHeight();
            }
	    	
	    	function onPageLoad() {
	    		dwr.engine._errorHandler = function(message, ex){
	    			dwr.engine._debug("Error: " + ex.name + ", " + ex.message, true);
	    		};
	    		dwr.engine.setTimeout(5000);
			}
	    	
	    	function resizeContentHeight(){
	    		$('.J_iframe').css('height',(window.innerHeight - 50) + 'px');
	    	}
	    	
		    function repassword(){
				$('#RepasswordWindow').modal({show:true,backdrop:'static',keyboard:false});
			}
		    
		    function confirmpassword(){
				if('' == $.trim($('#rp_password').val())){
					$.alert({
						keyboardEnabled: true,
						icon: 'glyphicon glyphicon-remove-sign',
					    title: '系统提示',
					    content:'请填写原密码！',
					    confirmButton: '关闭',
					    confirmButtonClass: 'btn-danger'
					});
					return;
				}
				if('' == $.trim($('#rp_newpassword').val())){
					$.alert({
						keyboardEnabled: true,
						icon: 'glyphicon glyphicon-remove-sign',
					    title: '系统提示',
					    content:'请填写新密码！',
					    confirmButton: '关闭',
					    confirmButtonClass: 'btn-danger'
					});
					return;
				}
				if($.trim($('#rp_newpassword').val()) != $.trim($('#rp_cnewpassword').val())){
					$.alert({
						keyboardEnabled: true,
						icon: 'glyphicon glyphicon-remove-sign',
					    title: '系统提示',
					    content:'两次输入的新密码必须一致！',
					    confirmButton: '关闭',
					    confirmButtonClass: 'btn-danger'
					});
					return;
				}
				
				$.ajax({
		            type: "post",
		            url: '<c:url value="/user/repassword"/>',
		            dataType: "json",
		            data:{username:$('#rp_usercode').val(),repassword:$('#rp_password').val(),password:$('#rp_newpassword').val()},
		            success: function(data){
		            	$('#RepasswordWindow').modal('toggle');
		            	$.alert({
		        			keyboardEnabled: true,
		        			icon: 'glyphicon glyphicon-ok-sign',
		        			backgroundDismiss: true,
		        		    autoClose: 'confirm|3000',
		        		    title: '成功',
		        		    content:'修改密码成功！',
		        		    confirmButton: '关闭',
		        		    confirmButtonClass: 'btn-success'
		        		});
		            },
		            error:function(data){
		            	$.alert({
							keyboardEnabled: true,
							icon: 'glyphicon glyphicon-remove-sign',
						    title: '系统提示',
						    content:data.msg,
						    confirmButton: '关闭',
						    confirmButtonClass: 'btn-danger'
						});
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
		<script type="text/javascript" src="<c:url value='/plugins/jquery-confirm.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/plugins/app.min.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/plugins/rightslidebar.js'/>"></script>
	</body>
</html>