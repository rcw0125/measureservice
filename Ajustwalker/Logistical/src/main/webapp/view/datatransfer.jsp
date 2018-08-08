<%@ page contentType="text/html;charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="t" uri="http://www.talent.com/jsp/anyonelse"%>
<t:html>
	<head>
		<jsp:include page="common.jsp" flush="true"/>
		<style type="text/css">
			.fixed-table-body{overflow-x:auto;overflow-y:auto;}
		</style>
	</head>
	<body>
		<div class="modal fade" id="DatatransferWindow" tabindex="-1" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span>&times;</span>
						</button>
						<h4 class="modal-title">接口属性</h4>
					</div>
					<div class="modal-body">
						<form id="DatatransferForm">
							<input type="hidden" id="id" name="id" value="0"/>
							<input type="hidden" id="fid" name="fid" value="0"/>
							<div class="row">
								<div class="col-sm-6">
									<t:textbox id="iname" label="接口名称" require="true"/>
								</div>
								<div class="col-sm-6">
									<t:textbox id="icode" label="接口编码" require="true"/>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6">
									<t:combobox id="itype" label="接口类型" data="[{\"id\":\"xml\",\"text\":\"XML格式\"},{\"id\":\"json\",\"text\":\"JSON格式\"}]" require="true"/>
								</div>
								<div class="col-sm-6">
									<t:combobox id="inorout" label="操作类型" data="[{\"id\":\"in\",\"text\":\"下载\"},{\"id\":\"out\",\"text\":\"回写\"}]" require="true"/>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6">
									<t:textbox id="maintabledata" label="主表地址" require="true"/>
								</div>
								<div class="col-sm-6">
									<t:textbox id="maintable" label="主表名称" require="true"/>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6">
									<t:textbox id="itemtabledata" label="子表地址" require="false"/>
								</div>
								<div class="col-sm-6">
									<t:textbox id="itemtable" label="子表名称" require="false"/>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6">
									<t:textbox id="idfield" label="主表主键" require="false"/>
								</div>
								<div class="col-sm-6">
									<t:textbox id="idpath" label="主键路径" require="false"/>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6">
									<t:textbox id="subidfield" label="子表主键" require="false"/>
								</div>
								<div class="col-sm-6">
									<t:textbox id="subidpath" label="主键路径" require="false"/>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<t:combobox id="serviceing" label="是否启用" data="[{\"id\":\"1\",\"text\":\"是\"},{\"id\":\"0\",\"text\":\"否\"}]" require="true"/>
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer btn-group-sm">
					    <button type="button" class="btn btn-success" id="DatatransferSaveBtn">保存</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="DatatransferItemWindow" tabindex="-1" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span>&times;</span>
						</button>
						<h4 class="modal-title">接口配置详情</h4>
					</div>
					<div class="modal-body">
						<form id="DatatransferItemForm">
							<input type="hidden" id="id" name="id" value="0"/>
							<input type="hidden" id="fid" name="fid" value="0"/>
							<div class="row">
								<div class="col-sm-12">
									<t:textbox id="dbcolumn" label="数据字段" require="true"/>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<t:textbox id="icolumn" label="接口字段" require="true"/>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<t:textbox id="icolumndesc" label="字段描述" require="true"/>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<t:combobox id="datatable" label="接口类型" data="[{\"id\":\"main\",\"text\":\"主表\"},{\"id\":\"item\",\"text\":\"子表\"}]" require="true"/>
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer btn-group-sm">
					    <button type="button" class="btn btn-success" id="DatatransferItemSaveBtn">保存</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
		</div>
		<div id="DatatransferItemToolbar">
			<div class="form-inline btn-group-sm" role="form">
				<button id="AddItemLineBtn" type="button" class="btn btn-success" data-toggle="modal" onclick="addItemFunction()">
					<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
				</button>
			</div>
		</div>
		<div class="row-fluid">
			<div class="col-sm-12">
				<t:datatable singleselect="true" url="/datatransfer/list" entities="com.talent.materialflow.model.Datatransfer" id="DatatransferGrid" showcrud="true" btntypes="add,remove" btnfuncs="addFunction,removeFunction" hiddencolumns="validflag,creator,cdate,updater,udate,canceler,canceldate,classindex,classtype,usermemo,sysmemo,remark"/>
			</div>
		</div>
		<div class="row-fluid">
			<div class="col-sm-12">
				<table id="DatatransferItemGrid" data-toggle="table" data-row-style="rowStyle" data-click-to-select="true" data-single-select="true" data-show-columns="true"  data-toolbar="#DatatransferItemToolbar" data-mobile-responsive="true" data-id-field="id">
					<thead>
						<tr>
							<th data-halign="center" data-radio="true"></th>
							<th data-field="id" data-visible="false">ID</th>
							<th data-field="fid" data-visible="false">FID</th>
							<th data-field="dbcolumn" data-halign="center" data-align="left">数据库字段</th>
							<th data-field="icolumn" data-halign="center" data-align="left">接口字段</th>
							<th data-field="icolumndesc" data-halign="center" data-align="left">字段描述</th>
							<th data-field="datatable" data-halign="center" data-align="left" data-formatter="datatableFormatter">数据库表</th>
							<th data-width="70px" data-align="center" data-valign="middle" data-formatter="operateFormatter2" data-events="operateEvents2">操作</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</body>
	<script type="text/javascript">
		jQuery(document).ready(function($){
			$('#DatatransferSaveBtn').on('click',function(){
				saveFormData('DatatransferForm','<c:url value="/datatransfer/edit"/>',function(data){
					if(data.success){
						$('#DatatransferWindow').modal('toggle');
	                	$('#DatatransferGrid').bootstrapTable('refresh');
					}
				});
	        });
			
			$('#DatatransferItemSaveBtn').on('click',function(){
				saveFormData('DatatransferItemForm','<c:url value="/datatransferitem/edit"/>',function(data){
					if(data.success){
						$('#DatatransferItemWindow').modal('toggle');
	                	$('#DatatransferItemGrid').bootstrapTable('refresh');
					}
				});
	        });
			
			$('#iname').on('blur',function(){
				$('#icode').val(pinyin.getFullChars($(this).val()));
				validForm('DatatransferForm');
			});
		});
		
		function SaveDatatransferItem(row){
			$.ajax({
	            type: "post",
	            url: '<c:url value="/datatransferitem/edit"/>',
	            dataType: "json",
	            data:row,
	            success: function(data){
	            	$('#DatatransferItemGrid').bootstrapTable('refresh',{url:'<c:url value="/datatransferitem/list"/>?fid=' + row.fid});
	            }
	        });
		}
		
		var selectFid;
		$('#DatatransferGrid').bootstrapTable({
			onCheck:function(row,element){
				selectFid = row.id;
				$('#DatatransferItemGrid').bootstrapTable('refresh',{url:'<c:url value="/datatransferitem/list"/>?fid=' + selectFid});
			}
		});
		
		function addFunction(){
	    	openWindow('DatatransferWindow','DatatransferForm','<c:url value="/datatransfer/form"/>?id=-1');
	    }
		
		function addItemFunction(){
			if($('#DatatransferGrid').bootstrapTable('getAllSelections').length > 0){
	    		openWindow('DatatransferItemWindow','DatatransferItemForm','<c:url value="/datatransferitem/form"/>?id=-1&fid=' + selectFid);
			}else{
				errorbox('请先选择配置文件，再进行详细配置！');
			}
	    }
	    
		function removeFunction(){
	    	listGridIds('DatatransferGrid',function(ids){
	    		removeData('<c:url value="/datatransfer/delete"/>',ids,function(data){
	    			$('#DatatransferGrid').bootstrapTable('refresh');
	    		});
	    	});
	    }
		
		function datatableFormatter(value, row, index) {
			if('main' == value){
				return '主表';
			}else{
				return '子表'
			}
		}
		
		function serviceingFormatter(value, row, index) {
			if('1' == value){
				return '启用';
			}else{
				return '不启用'
			}
		}
		
		function operateFormatter(value, row, index) {
	        return [
	            '<div class="pull-center" style="width:50px;">',
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
	        	openWindow('DatatransferWindow','DatatransferForm','<c:url value="/datatransfer/form"/>?id=' + row.id);
	        },
	        'click .remove': function (e, value, row) {
	        	removeData('<c:url value="/datatransfer/delete"/>',row.id,function(data){
	    			$('#DatatransferGrid').bootstrapTable('refresh');
	    		});
	        }
	    };
	    
	    function operateFormatter2(value, row, index) {
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
		
	    window.operateEvents2 = {
    		'click .edit': function (e, value, row) {
	        	openWindow('DatatransferItemWindow','DatatransferItemForm','<c:url value="/datatransferitem/form"/>?id=' + row.id);
	        },
	    	'click .remove': function (e, value, row) {
	        	removeData('<c:url value="/datatransferitem/delete"/>',row.id,function(data){
	    			$('#DatatransferItemGrid').bootstrapTable('refresh');
	    		});
	        }
	    };
	</script>
</t:html>