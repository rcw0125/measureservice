<%@ page contentType="text/html;charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="t" uri="http://www.talent.com/jsp/anyonelse"%>
<t:html>
	<head>
		<jsp:include page="common.jsp" flush="true"/>
	</head>
	<body>
		<div class="modal fade" id="RoleWindow" tabindex="-1" role="dialog" aria-labelledby="RoleWindowLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">角色管理</h4>
					</div>
					<div class="modal-body">
						<form id="RoleForm">
							<input type="hidden" id="id" name="id" value="0"/>
							<div class="row">
								<div class="col-sm-12">
									<t:textbox id="rolename" label="角色名称" require="true"/>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<t:textbox id="rolecode" label="角色编码" require="true"/>
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer btn-group-sm">
					    <button type="button" class="btn btn-success" id="RoleSaveBtn">保存</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
		</div>
		<div id="querytoolbar">
			<div class="form-inline" role="form">
				<form id="queryform">
					<t:textbox id="rolecode_query" name="rolecode" label="角色编码" require="false"/>
					<t:textbox id="rolename_query" name="rolename" label="角色名称" require="false"/>	
					<div class="form-group btn-group-sm">
						<button id="query" type="button" class="btn btn-info enterkeyaction" onclick="loadRoleGridData()">
							<span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;查询
						</button>
					</div>
				</form>
			</div>
		</div>
		<div class="row-fluid">
			<div class="col-sm-12">
				<t:datatable toolbar="querytoolbar" queryformid="queryform" url="/role/list" entities="com.talent.core.privilege.model.Role" id="RoleGrid" showcrud="true" btntypes="add,remove" btnfuncs="addFunction,removeFunction" hiddencolumns="selected,sn,validflag,creator,cdate,updator,udate,deletor,ddate,classindex,classtype,usermemo,sysmemo,memo,begintime,endtime,unitcode,unitname,unitid,remark,updater,canceler,canceldate"/>
			</div>
		</div>
	</body>
	<script type="text/javascript">
		jQuery(document).ready(function($){
			$('#RoleSaveBtn').on('click',function(){
				saveFormData('RoleForm','<c:url value="/role/edit"/>',function(data){
					if(data.success){
						$('#RoleWindow').modal('toggle');
	                	$('#RoleGrid').bootstrapTable('refresh');
					}
				});
	        });
			
			$('#rolename').on('blur',function(){
				$('#rolecode').val(pinyin.getFullChars($(this).val()));
				validForm('RoleForm');
			});
		});
		
		function addFunction(){
        	openWindow('RoleWindow','RoleForm','<c:url value="/role/form"/>?id=-1');
        }
        
		function removeFunction(){
        	listGridIds('RoleGrid',function(ids){
        		removeData('<c:url value="/role/delete"/>',ids,function(data){
        			$('#RoleGrid').bootstrapTable('refresh');
        		});
        	});
        }
		
		function operateFormatter(value, row, index) {
            return [
                '<div class="pull-center">',
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
            	openWindow('RoleWindow','RoleForm','<c:url value="/role/form"/>?id=' + row.id);
            },
            'click .remove': function (e, value, row) {
            	removeData('<c:url value="/role/delete"/>',row.id,function(data){
        			$('#RoleGrid').bootstrapTable('refresh');
        		});
            }
        };
	</script>
</t:html>