<%@ page contentType="text/html;charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html>
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
	<jsp:include page="../common.jsp" flush="true"/>
	<style type="text/css">
		.table>tbody>tr>td{
			vertical-align:top;
		}
		.fixed-table-container tbody td .th-inner, .fixed-table-container thead th .th-inner{
			text-align:center;
		}
		.select2-container--bootstrap .select2-selection{
			border-radius: 4px 0px 0px 4px;
		}
		.select2-container--bootstrap.input-sm .select2-selection--single, .input-group-sm .select2-container--bootstrap .select2-selection--single, .form-group-sm .select2-container--bootstrap .select2-selection--single{
			border-radius: 4px 0px 0px 4px;
		}
	</style>
</head>
<body class="section container-fluid">
	<div class="row" style="padding-left:25px;padding-right:25px;">
	    <div class="col-sm-3">
	        <div class="row" style="padding-top:10px;padding-bottom:0px;">
				<div class="col-sm-12" style="margin:0px;padding:0px;">
	        		<div class="form-group">
						<input id="s_resourcename" name="s_resourcename" type="text" class="form-control" placeholder="输入资源名称查询"></input>
					</div>
	        	</div>
	       	</div>
	        <div class="row">
				<div class="col-sm-12" style="margin:0px;padding:0px;">
	        		<div id="resourceTree"></div>
	        	</div>
	        </div>
		</div>
	    <div class="col-sm-4" style="margin-top:0px;padding-top:0px;">
			<div class="row" style="padding-top:10px;padding-bottom:14px;">
				<div class="col-sm-12">
					<button id="addResourceBtn" type="button" class="btn btn-success" data-toggle="modal">
						<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;同级
					</button>
	        		<button id="addSubResourceBtn" type="button" class="btn btn-warning" data-toggle="modal">
						<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;子项
					</button>
					<button id="delResourceBtn" type="button" class="btn btn-danger" data-toggle="modal">
						<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>&nbsp;删除
					</button>
					<button id="clearResourceBtn" type="button" class="btn btn-primary" data-toggle="modal">
						<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>&nbsp;清空
					</button>
					<button id="saveResourceBtn" type="button" class="btn btn-success" data-toggle="modal">
						<span class="glyphicon glyphicon-floppy-save" aria-hidden="true"></span>&nbsp;保存
					</button>
				</div>
			</div>
			<form id="resourceForm">
				<div class="row">
					<div class="col-sm-12">
						<div class="form-group input-group" style="margin-top:0px;padding-top:0px;">
							<input name="resourcetype" type="text" placeholder="必选" class="form-control" aria-label="..." readonly="readonly">
							<div class="input-group-btn">
								<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">资源类型<span class="caret"></span></button>
								<ul class="dropdown-menu dropdown-menu-right">
									<li><a>功能模块</a></li>
									<li><a>控制按钮</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<div class="form-group  input-group" style="margin-top:0px;padding-top:0px;">
							<input id="resourcename" name="resourcename" type="text" class="form-control" placeholder="必填"></input>
							<span class="input-group-addon">资源名称&nbsp;&nbsp;</span>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<div class="form-group  input-group" style="margin-top:0px;padding-top:0px;">
							<input name="resourcecode" type="text" class="form-control" placeholder="选填"></input>
							<span class="input-group-addon">资源编码&nbsp;&nbsp;</span>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<div class="form-group  input-group" style="margin-top:0px;padding-top:0px;">
							<input name="icon" type="text" class="form-control" placeholder="选填"></input>
							<span class="input-group-addon">资源图标&nbsp;&nbsp;</span>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<div class="form-group  input-group" style="margin-top:0px;padding-top:0px;">
							<input name="link" type="text" class="form-control" placeholder="选填"></input>
							<span class="input-group-addon">资源链接&nbsp;&nbsp;</span>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<div class="form-group  input-group" style="margin-top:0px;padding-top:0px;">
							<select id="isdisplay" name="isdisplay" class="form-control select2" placeholder="必选">
								<option value="0">不显示</option>
								<option value="1">显示</option>
							</select>
							<div class="input-group-addon">是否显示&nbsp;&nbsp;</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<div class="form-group input-group" style="margin-top:0px;padding-top:0px;">
							<input name="resourcememo" type="text" class="form-control" placeholder="选填"></input>
							<span class="input-group-addon">备　　注&nbsp;&nbsp;</span>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<span class="help-block" id="errormsg"></span>
						<input type="hidden" id="id" name="id" value="-1"/>
						<input type="hidden" id="fid" name="fid" value="0"/>
						<input type="hidden" id="rolecode" name="rolecode" value=""/>
					</div>
				</div>
			</form>
		</div>
		<div class="col-sm-5" style="margin:0px;padding:0px;">
			<div id="resourceRoleToolbar">
				<div class="form-inline" role="form">
					<button id="allPrivilegeBtn" type="button" class="btn btn-default" data-toggle="modal" onclick="moduleRight('全部')">
						<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>&nbsp;全部权限
					</button>
					<button id="accessPrivilegeBtn" type="button" class="btn btn-default" data-toggle="modal" onclick="moduleRight('访问')">
						<span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>&nbsp;访问权限
					</button>
				</div>
			</div>
			<table id="resourceRoleGrid" data-toggle="table" data-row-style="rowStyle" data-toolbar="#resourceRoleToolbar">
				<thead>
					<tr>
						<th data-field="id" data-halign="center" data-visible="false"></th>							
						<th data-field="selected" data-halign="center" data-formatter="resourceRoleFormatter"></th>							
						<th data-field="rolename" data-halign="center" data-searchable="true">角色名称</th>
						<th data-field="rolecode" data-halign="center" data-searchable="true">角色编码</th>
						<th data-field="rolememo" data-halign="center" data-searchable="true">备注</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</body>
<script type="text/javascript">
	var currentResourceID = 0;
	var currentResourceFID = 0;
	var currentResourceCode = '';
	var moduleCtrlType = '';
	jQuery(document).ready(function($){
		$('#resourceForm').bootstrapValidator({
			excluded: [':disabled', ':hidden', ':not(:visible)'],
			onSuccess:function() {
	        	$('#resourceForm').ajaxSubmit({
	                type: 'post',
	                url: '<c:url value="/resource/addoredit.do"/>',
	                success: function(data){
	                	toastMessage("success","提示","操作成功！");
	                	loadTree($('#resourcename').val());
	                }
	            });
	        },
	        fields: {
	        	resourcetype: {
	        		container:"#errormsg",
	                validators: {
	                    notEmpty: {
	                        message: '请选择资源类型！'
	                    }
	                }
	            },
	            resourcename: {
	        		container:"#errormsg",
	                validators: {
	                    notEmpty: {
	                        message: '请填写资源名称！'
	                    }
	                }
	            },
	            resourcecode:{
	            	container:"#errormsg",
	                validators: {
	                    notEmpty: {
	                        message: '请填写资源编码！'
	                    }
	                }
	            }
	        }
	    });
	    
	    $('#s_resourcename').on('keyup',function(e){
	    	search($(this).val());
	    });
		
		$('#addResourceBtn').on('click',function(){
			loadForm(currentResourceFID,-1);
        });
		
		$('#addSubResourceBtn').on('click',function(){
			loadForm(currentResourceID,-1);
        });
		
		$('#clearResourceBtn').on('click',function(){
			loadForm(0,-1);
        });
		
		$('#saveResourceBtn').on('click',function(){
			$('#resourceForm').bootstrapValidator('validate');
        });
		
		$('#delResourceBtn').on('click',function(){
			removeResource(currentResourceID);
        });
		
		loadTree();
	});
	
	function search(revealNode){
		var options = {revealResults:true};
		$('#resourceTree').treeview('selectNode',[$('#resourceTree').treeview('search',[revealNode,options]),{silent:true}]);
	}
	
	function loadTree(revealNode){
		$.ajax({
		    type: "post",
		    url: '<c:url value="/resource/tree.do"/>',
		    dataType: "json",
		    success: function(jsondata){
		    	$('#resourceTree').treeview({
		            color: "#428bca",
		            expandIcon: "glyphicon glyphicon-chevron-right",
		            collapseIcon: "glyphicon glyphicon-chevron-down",
		            levels:1,
		            showTags: true,
		            data:jsondata,
		            onNodeSelected: function(event, node) {
		            	var hrefArry = node.href.split('&&');
		            	currentResourceID = hrefArry[0];
		            	currentResourceFID = hrefArry[1];
		            	currentResourceCode = hrefArry[3];
		            	loadForm(currentResourceFID,currentResourceID);
		            	moduleCtrlType = '全部';
		            	if('功能模块' == hrefArry[4]){
		            		$('#accessPrivilegeBtn').show();
		            		$('#accessPrivilegeBtn').attr("class","btn");
		            		$('#allPrivilegeBtn').show();
		            		$('#allPrivilegeBtn').attr("class","btn");
		            		$('#allPrivilegeBtn').addClass('btn-success');
		            	}else{
		            		$('#accessPrivilegeBtn').hide();
		            		$('#allPrivilegeBtn').hide();
		            	}
		            	$('#resourceRoleGrid').bootstrapTable('refresh',{url:"<c:url value='/resource/queryList.do'/>?resourcecode=" + currentResourceCode + "&ctrltype=" + moduleCtrlType});
		            }
		        });
		        if(undefined != revealNode){
		        	search(revealNode);
		        }
		    }
		});
	}
	
	function loadForm(fid_v,id_v){
		$.ajax({
            type: "post",
            url: '<c:url value="/resource/loadform.do"/>?id=' + id_v + '&fid=' + fid_v,
            dataType: "json",
            success: function(data){
            	loadFormData('resourceForm',data);
            }
        });
	}
	
	function removeResource(id_vv){
		$.messager.confirm("请确认", "确认删除此项目？", function() {
			$.ajax({
	            type: "post",
	            url: '<c:url value="/resource/del.do"/>',
	            dataType: "json",
	            data: {ids:id_vv},
	            success: function(data){
	            	 if(data.success == true){
	            		 toastMessage("success","提示","操作成功！");
	            		 loadTree();
	            	 }
	            }
	    	});
		});
	}
	
	function resourceRoleFormatter(value, row, index) {
    	if(value == 0){
        	return '<center><input type="checkbox" onclick="chengeRole(\''+row.rolecode+'\',\'添加\')"><center>';
        }else{
        	return '<center><input type="checkbox" checked onclick="chengeRole(\''+row.rolecode+'\',\'删除\')"><center>';
        }
    }
	
	function moduleRight(type){
		if('访问' == type){
			$('#accessPrivilegeBtn').attr("class","btn");
    		$('#accessPrivilegeBtn').addClass('btn-success');
    		$('#allPrivilegeBtn').attr("class","btn");
		}else{
			$('#allPrivilegeBtn').attr("class","btn");
    		$('#allPrivilegeBtn').addClass('btn-success');
    		$('#accessPrivilegeBtn').attr("class","btn");
		}
		moduleCtrlType = type;
		$('#resourceRoleGrid').bootstrapTable('refresh',{url:"<c:url value='/resource/queryList.do'/>?resourcecode=" + currentResourceCode + "&ctrltype=" + moduleCtrlType});
	}
    
    function chengeRole(rolecode,optr){
    	$.ajax({
            type: "post",
            url: '<c:url value="/resource/setrole.do"/>',
            dataType: "json",
            data: {resourcecode:currentResourceCode,rolecode:rolecode,optr:optr,ctrltype:moduleCtrlType},
            success: function(data){
            	 if(data.success == true){
            		 toastMessage("success","提示","操作成功！");
            		 $('#userRoleGrid').bootstrapTable('refresh',{url:"<c:url value='/resource/queryList.do'/>?resourcecode=" + currentResourceCode});
            	 }
            }
        });
    }
</script>
</html>