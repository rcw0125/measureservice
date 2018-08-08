<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="t" uri="http://www.talent.com/jsp/anyonelse"%>
<t:html>
<head>
<jsp:include page="common.jsp" flush="true" />
</head>
<body class="container-fluid" style="padding-top: 15px;">

	<div class="modal fade" id="CustomerWindow" tabindex="-1" role="dialog" aria-labelledby="CustomerWindowLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">客商操作</h4>
				</div>
				<div class="modal-body">
					<form id="CustomerForm">
						<input type="hidden" id="id" name="id" value="0" />
						<div class="col-sm-6">
							<div class="form-group input-group input-group-sm">
								<span class="input-group-addon">客户名称</span> <input type="text" class="form-control" placeholder="客户名称" id="customername" name="customername" aria-describedby="sizing-addon3" required>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="form-group input-group input-group-sm">
								<span class="input-group-addon" id="sizing-addon3">客商类型</span> <select name="customertype" id="customertype" class="form-control select2" required>
									<option value="1">客户</option>
									<option value="2">供应商</option>
								</select>
							</div>
						</div>
						<div class="col-sm-6">
							<t:textbox id="tele" name="tele" label="联系电话" />
						</div>
						<div class="col-sm-6">
							<t:textbox id="customerarea" name="customerarea" label="客户区域" />
						</div>
						<div class="col-sm-6">
							<t:textbox id="address" name="address" label="地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址" />
						</div>
						<div class="col-sm-6">
							<t:textbox id="queryword" name="queryword" label="拼音缩写" />
						</div>
						<div class="col-sm-12">
							<t:textbox id="usermemo" name="usermemo" label="用户备注" />
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

	<div class="row">
		<div class="col-sm-12">
			<form id="queryform">
				<div class="row">
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon">客户编码</span> <input type="text" class="form-control" placeholder="客户编码" id="customercode" name="customercode" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon">客户名称</span> <input type="text" class="form-control" placeholder="客户名称" id="customername" name="customername" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon">拼音头</span> <input type="text" class="form-control" placeholder="拼音头" id="queryword" name="queryword" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">运输类型</span> <select name="types" id="type" class="form-control select2">
								<option value="-1">全部</option>
								<option value="1">汽运</option>
								<option value="2" selected>火运</option>
							</select>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">客商类型</span> <select name="customertype" id="customertype" class="form-control select2">
								<option value="-1">全部</option>
								<option value="1">客户</option>
								<option value="2">供应商</option>
							</select>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="btn-group-sm">
						<t:button text="查询" modulecode="KeHuGuanLi" id="query" btnclass="btn btn-info" iconclass="glyphicon glyphicon-search" onclick="queryinfo()"/>
						<t:button text="火运客商添加" modulecode="KeHuGuanLi" id="addBtn" btnclass="btn btn-success" iconclass="glyphicon glyphicon-th-large" onclick="addFunction()" />
					    <t:button text="火运客商修改" modulecode="KeHuGuanLi" id="editBtn" btnclass="btn btn-primary" iconclass="glyphicon glyphicon-hand-up" onclick="editFunction()"/>
					    <t:button text="火运客商作废" modulecode="KeHuGuanLi" id="cancelBtn" btnclass="btn btn-danger" iconclass="glyphicon glyphicon-hand-down" onclick="cancelFunction()"/>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>

	<div class="row">
		<div class="col-sm-12">
			<table id="CustomerGrid" data-toggle="table" data-row-style="rowStyle" data-query-params="queryParams" data-pagination="true" data-page-size="10" data-page-list="[10,40,ALL]" data-mobile-responsive="true">
				<thead>
					<tr>
						<th data-field="id" data-visible="false"></th>
						<th data-field="state" data-radio="true"></th>
						<th data-field="customercode" data-halign="center" data-searchable="true" class="text-center" sortable>客户编码</th>
						<th data-field="customertype" data-halign="center" data-searchable="true" class="text-center" sortable>客户类型</th>
						<th data-field="customername" data-halign="center" data-searchable="true" class="text-center">客户名称</th>
						<th data-field="queryword" data-halign="center" data-searchable="true" class="text-center">拼音头</th>
						<th data-field="customerarea" data-halign="center" data-searchable="true" class="text-center">客户区域</th>
						<th data-field="address" data-halign="center" data-searchable="true" class="text-center">地址</th>
						<th data-field="tele" data-halign="tele" data-searchable="true" class="text-center">电话</th>
						<th data-field="creator" data-halign="center" data-searchable="true" class="text-nowrap text-center">创建人</th>
						<th data-field="cdate" data-halign="center" data-searchable="true" class="text-nowrap text-center">创建时间</th>
						<th data-field="usermemo" data-halign="center" data-searchable="true" class="text-nowrap text-center">备注</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>

	<script type="text/javascript">
		jQuery(document).ready(function($) {
			queryinfo()
		});

		//查询
		function queryinfo() {
			$('#CustomerGrid').bootstrapTable('refresh', {
				url : "<c:url value='/customer2/list'/>"
			});
		}

		function queryParams(params) {
			return jQuery.extend({}, params, $('#queryform').serializeJson());
		}

		function addFunction() {
			openWindow('CustomerWindow', 'CustomerForm','<c:url value="/customer2/form"/>?id=-1');
		}

		jQuery(document).ready(
				function($) {
					$('#CustomerSaveBtn').on(
							'click',
							function() {
								saveFormData('CustomerForm',
										'<c:url value="/customer2/edit"/>',
										function(data) {
											if (data.success) {
												successbox(data.msg);
												$('#CustomerWindow').modal('toggle');
												$('#CustomerGrid').bootstrapTable('refresh');
											} else {
												errorbox(data.msg);
											}
										});
							});
					$('#customername').on('blur', function() {
						var val = $(this).val();
						if (val.length > 0) {
							$('#queryword').val(pinyin.getCamelChars(val));
						}
					});
				});

		function editFunction(id) {
			var selectedRows = $('#CustomerGrid').bootstrapTable('getSelections');
			if (selectedRows.length != 0) {
				if (selectedRows[0].customercode.match("CT")) {
					openWindow('CustomerWindow', 'CustomerForm','<c:url value="/customer2/form"/>?id='+ selectedRows[0].id);
				} else {
					errorbox("请选择火运类型的客商!")
				}
			} else {
				errorbox("请选择需要修改的火运类型的客商!")
			}
		}
		
		function cancelFunction(id) {
			var selectedRows = $('#CustomerGrid').bootstrapTable('getSelections');
			if (selectedRows.length != 0) {
				if (selectedRows[0].customercode.match("CT")) {
					dialogbox("请确认", "确认删除此条信息？", function(data) {
						if (data) {
							$.ajax({
								type : "post",
								url : '<c:url value="/customer2/del"/>?id='+ selectedRows[0].id,
								dataType : "json",
								data : {
									id : id
								},
								success : function(data) {
									if (data.success == true) {
										successbox(data.msg);
										$('#CustomerGrid').bootstrapTable('refresh');
									} else {
										errorbox(data.msg);
									}
								}
							});
						}
					});
				} else {
					errorbox("请选择火运类型的客商!")
				}
			} else {
				errorbox("请选择要删除的火运类型的客商!")
			}


		}
	</script>
</body>
</t:html>