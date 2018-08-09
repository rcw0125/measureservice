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
		<div class="col-sm-12">
			<div id="roleToolbar">
				<div class="form-inline" role="form">
					<button id="addRoleBtn" type="button" class="btn btn-success" data-toggle="modal">
						<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;添加
					</button>
					<button id="delRoleBtn" type="button" class="btn btn-danger" data-toggle="modal">
						<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>&nbsp;删除
					</button>
				</div>
			</div>
			<table id="roleGrid" data-toggle="table" data-pagination="true"
				   data-page-list="[10,30,50]"
				   data-row-style="rowStyle"
				   data-show-refresh="true"
				   data-search="true"
				   data-url="<c:url value='/role/queryPage.do'/>"
				   data-side-pagination="server"
				   data-toolbar="#roleToolbar">
				<thead>
				<tr>
					<th data-halign="center" data-checkbox="true"></th>
					<th data-field="id" data-halign="center" data-visible="false"></th>							
					<th data-field="rolename" data-halign="center" data-searchable="true">角色名称</th>
					<th data-field="rolecode" data-halign="center" data-searchable="true">角色编码</th>
					<th data-field="rolememo" data-halign="center" data-searchable="true">备注</th>
					<th data-width="70px" data-align="center" data-valign="middle" data-formatter="operateFormatter" data-events="operateEvents">操作</th>
				</tr>
				</thead>
			</table>
			<div class="modal fade" id="RoleWindow" tabindex="-1" role="dialog" aria-labelledby="RoleWindowLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title">配置模块</h4>
						</div>
						<div class="modal-body">
							<form id="roleForm">
								<div class="row">
									<div class="col-sm-12">
										<span class="help-block" id="errormsg"></span>
										<input type="hidden" id="id" name="id" value="0"/>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-6">
										<div class="form-group input-group">
											<input name="rolename"  type="text" class="form-control" placeholder="必填" aria-describedby="basic-addon1">
											<span class="input-group-addon" id="paramname">角色名称&nbsp;&nbsp;</span>
										</div>
									</div>
									<div class="col-sm-6">
										<div class="form-group input-group">
											<input name="rolecode"  type="text" class="form-control" placeholder="必填" aria-describedby="basic-addon1">
											<span class="input-group-addon" id="paramname">角色编码&nbsp;&nbsp;</span>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12">
										<div class="input-group">
											<input name="rolememo" type="text" class="form-control" placeholder="选填"></input>
											<span class="input-group-addon" id="paramemo">备注详情&nbsp;&nbsp;</span>
										</div>
									</div>
								</div>
							</form>
						</div>
						<div class="modal-footer">
						    <button type="button" class="btn btn-success" id="roleSaveBtn">保存</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		jQuery(document).ready(function($){
			$('#roleForm').bootstrapValidator({
				excluded: [':disabled', ':hidden', ':not(:visible)'],
				onSuccess:function() {
		        	$('#roleForm').ajaxSubmit({
		                type: 'post',
		                url: '<c:url value="/role/addoredit.do"/>',
		                success: function(data){
		                	$('#RoleWindow').modal('toggle');
		                	$('#roleGrid').bootstrapTable('refresh');
		                }
		            });
		        },
		        fields: {
		        	rolename: {
		        		container:"#errormsg",
		                validators: {
		                    notEmpty: {
		                        message: '请填写角色名称！'
		                    }
		                }
		            },
		            rolecode: {
		        		container:"#errormsg",
		                validators: {
		                    notEmpty: {
		                        message: '请填写角色编码！'
		                    }
		                }
		            }
		        }
		    });
			
			$('#roleSaveBtn').on('click',function(){
	        	$('#roleForm').bootstrapValidator('validate');
	        });
	        
	        $('#RoleWindow').on('shown.bs.modal', function(){
	        	var id_v = $($('#'+$(this).attr("id")+' form :input[name="id"]'));
	        	$.ajax({
		            type: "post",
		            url: '<c:url value="/role/loadform.do"/>?id='+id_v.val(),
		            dataType: "json",
		            success: function(data){
		            	loadFormData('roleForm',data);
		            }
	            });
	       	});
	        
	        $('#addRoleBtn').on('click',function(){
	        	openRoleWindow(-1);
	        });
	        
	        $('#delRoleBtn').on('click',function(){
	        	var ids = '';
	        	var selectedRows = $('#roleGrid').bootstrapTable('getAllSelections');
	        	for(var i=0;i<selectedRows.length;i++){
	        		ids = ids + ',' + selectedRows[i].id;
	        	}
	        	if('' == ids){
	        		$.alert({
	        		    title: '警告',
	        		    content: '请至少选择一条数据操作！',
	        		    confirmButton: '确定',
	        		    confirmButtonClass: 'btn-info'
	        		});
	        	}else{
	        		ids = ids.substr(1);
	        		removeRole(ids);
	        	}
	        });
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
            	openRoleWindow(row.id);
            },
            'click .remove': function (e, value, row) {
            	removeRole(row.id);
            }
        };
        
        function openRoleWindow(id_vv){
        	$($('#RoleWindow form :input[name="id"]')).val(id_vv);
        	$('#RoleWindow').modal('show');
        }
        
		function removeRole(id_vv){
			$.messager.confirm("请确认", "确认删除此项目？", function() {
				$.ajax({
		            type: "post",
		            url: '<c:url value="/role/del.do"/>',
		            dataType: "json",
		            data: {ids:id_vv},
		            success: function(data){
		            	 if(data.success == true){
		            		 $('#roleGrid').bootstrapTable('refresh');
		            	 }
		            }
	            });
			});
		}
	</script>
</body>
</html>