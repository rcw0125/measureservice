<%@ page contentType="text/html;charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html>
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
	<jsp:include page="../common.jsp" flush="true"/>
</head>
<body class="section container-fluid">
	<div class="row-fluid">
		<div class="col-sm-7">
			<table id="userGrid" data-toggle="table" data-pagination="true"
				   data-page-list="[10,30,50]"
				   data-row-style="rowStyle"
				   data-show-refresh="true"
				   data-search="true"
				   data-url="<c:url value='/user/queryPage.do'/>"
				   data-click-to-select="true"
				   data-single-select="true"
				   data-side-pagination="server"
				   data-toolbar="#baseInfoToolbar">
				<thead>
				<tr>
					<th data-halign="center" data-checkbox="true"></th>
					<th data-field="id" data-halign="center" data-visible="false"></th>							
					<th data-field="rolecode" data-halign="center" data-visible="false"></th>							
					<th data-field="username" data-halign="center" data-searchable="true">用户姓名</th>
					<th data-field="usercode" data-halign="center" data-searchable="true">用户编码</th>
					<th data-field="sex" data-halign="center" data-searchable="true">性别</th>
					<th data-field="nation" data-halign="center" data-searchable="true">民族</th>
					<th data-field="servicing" data-halign="center" data-searchable="true">是否在职</th>
					<th data-field="age" data-halign="center" data-searchable="true">年龄</th>
					<th data-field="usermemo" data-halign="center" data-searchable="true">备注</th>
					<th data-width="70px" data-align="center" data-valign="middle" data-formatter="operateFormatter" data-events="operateEvents">操作</th>
				</tr>
				</thead>
			</table>
			<div id="baseInfoToolbar">
				<div class="form-inline" role="form">
					<button id="addUserBtn" type="button" class="btn btn-success" data-toggle="modal">
						<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;添加
					</button>
				</div>
			</div>
			<div class="modal fade" id="UserWindow" tabindex="-1" role="dialog" aria-labelledby="UserWindowLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title">人员编辑</h4>
						</div>
						<div class="modal-body">
							<form id="userForm">
								<div class="row">
									<div class="col-sm-12">
										<span class="help-block" id="errormsg"></span>
										<input type="hidden" id="id" name="id" value="0"/>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-6">
										<div class="form-group input-group">
											<input name="username"  type="text" class="form-control" placeholder="必填" aria-describedby="basic-addon1">
											<span class="input-group-addon" >用户姓名&nbsp;&nbsp;</span>
										</div>
									</div>
									<div class="col-sm-6">
										<div class="input-group">
											<input name="usercode" type="text" class="form-control" placeholder="选填"></input>
											<span class="input-group-addon" >用户编码&nbsp;&nbsp;</span>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-6">
										<div class="form-group input-group">
											<input name="password"  type="password" class="form-control" placeholder="必填" aria-describedby="basic-addon1">
											<span class="input-group-addon">密　　码&nbsp;&nbsp;</span>
										</div>
									</div>
									<div class="col-sm-6">
										<div class="form-group input-group">
											<input name="sex" type="text" placeholder="必选" class="form-control" aria-label="..." readonly="readonly">
											<div class="input-group-btn">
												<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">性　　别<span class="caret"></span></button>
												<ul class="dropdown-menu dropdown-menu-right">
													<li><a>男</a></li>
													<li><a>女</a></li>
												</ul>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-6">
										<div class="form-group input-group">
											<input name="nation" type="text" placeholder="必选" class="form-control" aria-label="..." readonly="readonly">
											<div class="input-group-btn">
												<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">民　　族<span class="caret"></span></button>
												<ul class="dropdown-menu dropdown-menu-right">
													<li><a>汉</a></li>
													<li><a>其它</a></li>
												</ul>
											</div>
										</div>
									</div>
									<div class="col-sm-6">
										<div class="form-group input-group">
											<input name="servicing" type="text" placeholder="必选" class="form-control" aria-label="..." readonly="readonly">
											<div class="input-group-btn">
												<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">是否在职<span class="caret"></span></button>
												<ul class="dropdown-menu dropdown-menu-right">
													<li><a>是</a></li>
													<li><a>否</a></li>
												</ul>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-6">
										<div class="form-group input-group">
											<input name="age"  type="text" class="form-control" placeholder="必填" aria-describedby="basic-addon1">
											<span class="input-group-addon">年　　龄&nbsp;&nbsp;</span>
										</div>
									</div>
									<div class="col-sm-6">
										<div class="input-group">
											<input name="usermemo" type="text" class="form-control" placeholder="选填"></input>
											<span class="input-group-addon">备　　注&nbsp;&nbsp;</span>
										</div>
									</div>
								</div>
							</form>
						</div>
						<div class="modal-footer">
						    <button type="button" class="btn btn-success" id="userSaveBtn">保存</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
							
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-sm-5">
			<table id="userRoleGrid" data-toggle="table" 
				   data-row-style="rowStyle"
				   data-show-refresh="true"
				   data-search="true" style="padding-top:0px;margin-top:0px;">
				<thead>
				<tr>
					<th data-field="id" data-halign="center" data-visible="false"></th>							
					<th data-field="selected" data-halign="center" data-formatter="userRoleSelectFormatter"></th>							
					<th data-field="rolename" data-halign="center" data-searchable="true">角色名称</th>
					<th data-field="rolecode" data-halign="center" data-searchable="true">角色编码</th>
					<th data-field="rolememo" data-halign="center" data-searchable="true">备注</th>
				</tr>
				</thead>
			</table>
		</div>
	</div>
	<script type="text/javascript">
		var currentUserCode = '';
		jQuery(document).ready(function($){
			$('#userForm').bootstrapValidator({
				excluded: [':disabled', ':hidden', ':not(:visible)'],
				onSuccess:function() {
		        	$('#userForm').ajaxSubmit({
		                type: 'post',
		                url: '<c:url value="/user/addoredit.do"/>',
		                success: function(data){
		                	$('#UserWindow').modal('toggle');
		                	$('#userGrid').bootstrapTable('refresh');
		                }
		            });
		        },
		        fields: {
		        	username: {
		        		container:"#errormsg",
		                validators: {
		                    notEmpty: {
		                        message: '请填写用户名！'
		                    }
		                }
		            },
		            usercode: {
		        		container:"#errormsg",
		                validators: {
		                    notEmpty: {
		                        message: '请填写用户编码！'
		                    }
		                }
		            },
		            sex:{
		            	container:"#errormsg",
		                validators: {
		                    notEmpty: {
		                        message: '请选择性别！'
		                    }
		                }
		            },
		            nation:{
		            	container:"#errormsg",
		                validators: {
		                    notEmpty: {
		                        message: '请选择民族！'
		                    }
		                }
		            },
		            servicing:{
		            	container:"#errormsg",
		                validators: {
		                    notEmpty: {
		                        message: '请选择是否在职！'
		                    }
		                }
		            },
		            age:{
		            	container:"#errormsg",
		                validators: {
		                    notEmpty: {
		                        message: '请填写年龄！'
		                    }
		                }
		            }
		        }
		    });
			
			$('#userSaveBtn').on('click',function(){
	        	$('#userForm').bootstrapValidator('validate');
	        });
	        
	        $('#UserWindow').on('shown.bs.modal', function(){
	        	var id_v = $($('#'+$(this).attr("id")+' form :input[name="id"]'));
	        	$.ajax({
		            type: "post",
		            url: '<c:url value="/user/loadform.do"/>?id='+id_v.val(),
		            dataType: "json",
		            success: function(data){
		            	loadFormData('userForm',data);
		            }
	            });
	       	});
	        
	        $('#addUserBtn').on('click',function(){
	        	openUserWindow(-1);
	        });
		});
		
        $('#userGrid').bootstrapTable({
        	onClickRow:function(row,$element) {
        		currentUserCode = row.usercode;
        		$('#userRoleGrid').bootstrapTable('refresh',{url:"<c:url value='/userole/queryList.do'/>?usercode=" + currentUserCode});
            }
        });

		
		function operateFormatter(value, row, index) {
            return [
                    '<div class="pull-right">',
                	'<a class="edit" href="javascript:void(0)" title="修改">',
                	'<i class="glyphicon glyphicon-pencil"></i>',
                	'</a>　',
                	'<a class="remove" href="javascript:void(0)" title="移除">',
                	'<i class="glyphicon glyphicon-trash"></i>',
                	'</a>',
                	'</div>'
            		].join('');
        }
        window.operateEvents = {
            'click .edit': function (e, value, row) {
            	openUserWindow(row.id);
            },
            'click .remove': function (e, value, row) {
            	removeUser(row.id);
            }
        };
        
        function userRoleSelectFormatter(value, row, index) {
        	if(value == 0){
	        	return '<center><input type="checkbox" onclick="chengeRole(\''+row.rolecode+'\',\'添加\')"><center>';
	        }else{
	        	return '<center><input type="checkbox" checked onclick="chengeRole(\''+row.rolecode+'\',\'删除\')"><center>';
	        }
        }
        
        function chengeRole(rolecode,optr){
        	$.ajax({
	            type: "post",
	            url: '<c:url value="/userole/setrole.do"/>',
	            dataType: "json",
	            data: {usercode:currentUserCode,rolecode:rolecode,optr:optr},
	            success: function(data){
	            	 if(data.success == true){
	            		 $('#userRoleGrid').bootstrapTable('refresh',{url:"<c:url value='/userole/queryList.do'/>?usercode=" + currentUserCode});
	            	 }
	            }
            });
        }
        
        function openUserWindow(id_vv){
        	$($('#UserWindow form :input[name="id"]')).val(id_vv);
        	$('#UserWindow').modal('show');
        }
        
		function removeUser(id_vv){
			$.messager.confirm("请确认", "确认删除此项目？", function() { 
				$.ajax({
		            type: "post",
		            url: '<c:url value="/user/del.do"/>',
		            dataType: "json",
		            data: {ids:id_vv},
		            success: function(data){
		            	 if(data.success == true){
		            		 $('#userGrid').bootstrapTable('refresh');
		            	 }
		            }
	            });
		    });
		}
	</script>
</body>
</html>