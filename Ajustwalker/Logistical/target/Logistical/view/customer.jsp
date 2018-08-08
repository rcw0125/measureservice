<%@ page contentType="text/html;charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="t" uri="http://www.talent.com/jsp/anyonelse"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<t:html>
	<head>
		<jsp:include page="common.jsp" flush="true"/>
		<script type="text/javascript" src="<c:url value='/plugins/datatable/extensions/editable/bootstrap-table-editable.js'/>"></script>
	</head>
	<body>
		<div class="modal fade" id="CustomerWindow" tabindex="-1" role="dialog" aria-labelledby="CustomerWindowLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">配置模块</h4>
					</div>
					<div class="modal-body">
						<form id="CustomerForm">
							<input type="hidden" id="id" name="id" value="0"/>
							<div class="row">
								<div class="col-sm-6">
									<t:textbox id="customertype" label="类 　　型" require="true"/>
								</div>
								<div class="col-sm-6">
									<t:textbox id="customername" label="名　　称" require="true"/>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6">
									<t:textbox id="customercode" label="编　　码" require="true"/>
								</div>
								<div class="col-sm-6">
									<t:textbox id="queryword" label="拼音缩写" require="true"/>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6">
									<t:textbox id="erpcode" label="ERP编码" require="false"/>
								</div>
								<div class="col-sm-6">
									<t:textbox id="customerarea" label="客户区域" require="false"/>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6">
									<t:textbox id="tele" label="工作电话" require="false"/>
								</div>
								<div class="col-sm-6">
									<t:textbox id="address" label="工作地点" require="false"/>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<t:textbox id="addoffice" label="办公地点" require="false"/>
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer btn-group-sm">
					    <button type="button" class="btn btn-success" id="CustomerSaveBtn">保存</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="QuerySchemaWindow" tabindex="-1" role="dialog" aria-labelledby="QuerySchemaWindowLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">查询方案</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-sm-3">
								<div class="row">
									<div class="col-sm-12">
										<div class="form-group btn-group-sm">
										    <button type="button" class="btn btn-info" onclick="AddQuerySchema()">
												<span class="glyphicon glyphicon-heart-empty" aria-hidden="true"></span>&nbsp;添加方案
											</button>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12">
										<table id="SchemaList" data-toggle="table" data-row-style=rowStyle" data-pagination="false" data-url="<c:url value="/common/schema/main/query/CustomerGrid"/>">
										    <thead>
											    <tr>
											        <th data-field="planName" data-formatter="querySchemaFormatter" data-events="querySchemaEvents" data-halign="center">备选方案</th>
											    </tr>
										    </thead>
										</table>
									</div>
								</div>
							</div>
							<div class="col-sm-9">
								<div class="row">
									<div class="col-sm-12">
										<div class="form-group btn-group-sm">
										    <button type="button" class="btn btn-info" onclick="AddQuerySchemaCondition()">
												<span class="fa fa-gg" aria-hidden="true"></span>&nbsp;添加条件
											</button>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12">
										<table id="SchemaDetail" data-toggle="table" data-row-style=rowStyle" data-pagination="false" data-url="<c:url value="/common/schema/detail/query/CustomerGrid"/>">
											<thead>
												<tr>
													<th data-field="fieldName" data-editable="true" data-width="200" data-edittype="combobox" data-halign="center" data-url="<c:url value="/common/schema/fields/query?modelClass=com.talent.materialflow.model.Customer"/>">字段名称</th>
													<th data-field="compareFlag" data-editable="true" data-width="200" data-edittype="combobox" data-halign="center" data-combodata='[{"id":"=","text":"等于"},{"id":"&gt;","text":"大于"},{"id":"&lt;","text":"小于"},{"id":"&ge;","text":"大于等于"},{"id":"&le;","text":"小于等于"},{"id":"!=","text":"不等于"},{"id":"like","text":"模糊匹配"},{"id":"not like","text":"模糊排除"},{"id":"in","text":"包含"},{"id":"not in","text":"不包含"}]'>字段比较符</th>
													<th data-field="fieldValue" data-editable="true" data-halign="center">字段比较值</th>
													<th data-field="linkConditon" data-editable="true" data-width="200" data-edittype="combobox" data-halign="center" data-combodata='[{"id":"and","text":"并且"},{"id":"or","text":"或者"}]'>条件连接</th>
												</tr>
											</thead>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer form-group btn-group-sm">
					    <button id="query" type="button" class="btn btn-info" onclick="SaveQuerySchema()">
							<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>&nbsp;保存方案
						</button>
					    <button id="query" type="button" class="btn btn-success" onclick="ExecQuerySchema()">
							<span class="glyphicon glyphicon-filter" aria-hidden="true"></span>&nbsp;执行方案
						</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
		</div>
		<div id="querytoolbar">
			<div class="form-inline" role="form">
				<form id="queryform">
					<input id="foldercode_query" name="foldercode" type="hidden"/>
					<t:textbox id="customercode_query" name="customercode" label="&nbsp;客户编码&nbsp;" require="false"/>
					<t:textbox id="customername_query" name="customername" label="&nbsp;客户名称&nbsp;" require="false"/>	
					<t:textbox id="queryword_query" name="queryword" label="&nbsp;拼音缩写&nbsp;" require="false"/>
					<div class="form-group btn-group-sm">
						<button type="button" class="btn btn-info" onclick="loadCustomerGridData()">
							<span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;查询
						</button>
						<button type="button" class="btn btn-warning" onclick="clientexcel()">
							<span class="glyphicon fa fa-file-excel-o" aria-hidden="true"></span>&nbsp;导入Excel
						</button>
						<button type="button" class="btn btn-danger" onclick="seachschema()">
							<span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;查询方案
						</button>
					</div>
				</form>
			</div>
		</div>
		<div class="row-fluid">
			<div class="col-sm-12">
				<t:datatable datatablename="客户信息" toolbar="querytoolbar" queryformid="queryform" url="/customer/list" entities="com.talent.materialflow.model.Customer" id="CustomerGrid" showcrud="true" btntypes="add,remove,exportremote,importexcel" btnfuncs="addFunction,removeFunction,remoteExportExcel,commonImportExcel" hiddencolumns="validflag,creator,cdate,updater,udate,canceler,canceldate,classindex,classtype,usermemo,sysmemo"/>
			</div>
		</div>
	</body>
	<script type="text/javascript">
		jQuery(document).ready(function($){
			$('#CustomerSaveBtn').on('click',function(){
				saveFormData('CustomerForm','<c:url value="/customer/edit"/>',function(data){
					if(data.success){
						$('#CustomerWindow').modal('toggle');
	                	$('#CustomerGrid').bootstrapTable('refresh');
					}
				});
	        });
			$('#customername').on('blur',function(){
				$('#customercode').val(pinyin.getFullChars($(this).val()));
				$('#queryword').val(pinyin.getCamelChars($(this).val()));
				validForm('CustomerForm');
			});
		});
		
		var rowscount = 0;
		function AddQuerySchemaCondition(){
			$("#SchemaDetail").bootstrapTable('prepend', appendData());
		}
		function appendData() {
			var rows = [];
			rows.push({
				ids:"add"+rowscount,
				sn:rowscount,
				fieldName:"",
				compareFlag:'=',
				fieldValue:'',
				linkConditon:'and'
			});
			rowscount++;
			return rows;
		 }
		
		$('#SchemaList').bootstrapTable({
			onClickRow:function(row,$element,field) {
				alert(row.planName);
		    }
		});
		
		function seachschema(){
			$('#QuerySchemaWindow').modal('show');
		}
		
		function ExecQuerySchema(){
			$('#CustomerGrid').bootstrapTable('refresh',{url:'<c:url value="/common/schema/query/result"/>',query:{selects:'select customercode from B_CUSTOMER_T',wheres:$('#SchemaDetail').bootstrapTable('getData'),models:'com.talent.materialflow.model.Customer'}});
		}
		
		function clientexcel(){
			clientImportExcel(function(data){
				alert(data.length);
			});
		}
		
		function addFunction(){
        	openWindow('CustomerWindow','CustomerForm','<c:url value="/customer/form"/>?id=-1');
        }
        
		function removeFunction(){
        	listGridIds('CustomerGrid',function(ids){
        		removeData('<c:url value="/customer/delete"/>',ids,function(data){
        			$('#CustomerGrid').bootstrapTable('refresh');
        		});
        	});
        }
		
		function operateFormatter(value, row, index) {
            return [
                '<div class="pull-right">',
               	'<t:ibutton text="修改" modulecode="customer" id="customermodify" btnclass="edit" iconclass="glyphicon glyphicon-pencil"/>',
               	'<t:ibutton text="移除" modulecode="customer" id="customerremove" btnclass="remove" iconclass="glyphicon glyphicon-trash"/>',
               	'</div>'
           	].join('');
        }
		
        window.operateEvents = {
            'click .edit': function (e, value, row) {
            	openWindow('CustomerWindow','CustomerForm','<c:url value="/customer/form"/>?id=' + row.id);
            },
            'click .remove': function (e, value, row) {
            	removeData('<c:url value="/customer/delete"/>',row.id,function(data){
        			$('#CustomerGrid').bootstrapTable('refresh');
        		});
            }
        };
        
        window.querySchemaEvents = {
		    'click .like': function (e, value, row) {
		        alert('You click like action, row: ' + JSON.stringify(row));
		    },
		    'click .remove': function (e, value, row) {
		        alert('You click remove action, row: ' + JSON.stringify(row));
		    }
		};
		
		function querySchemaFormatter(value, row, index) {
		    return [
		        '<div class="pull-left">',
		        '<a href="https://github.com/wenzhixin/' + value + '" target="_blank">' + value + '</a>',
		        '</div>',
		        '<div class="pull-right">',
		        '<a class="like" href="javascript:void(0)" title="Like">',
		        '<i class="glyphicon glyphicon-heart"></i>',
		        '</a>  ',
		        '<a class="remove" href="javascript:void(0)" title="Remove">',
		        '<i class="glyphicon glyphicon-remove"></i>',
		        '</a>',
		        '</div>'
		    ].join('');
		}
	</script>
</t:html>