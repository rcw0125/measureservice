<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="t" uri="http://www.talent.com/jsp/anyonelse"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<t:html>
<head>
<jsp:include page="common.jsp" flush="true" />
</head>
<body class="container-fluid" style="padding-top: 15px">
	<div class="modal fade" id="MaterialWindow" tabindex="-1" role="dialog" aria-labelledby="MaterialWindowLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">物料操作</h4>
				</div>
				<div class="modal-body">
					<form id="MaterialForm">
						<input type="hidden" id="id" name="id" value="0" />
						<div class="row">
							<div class="col-sm-12">
								<t:textbox id="materialname" name="materialname" label="物料名称" require="true" />
							</div>
							<div class="col-sm-12" >
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon" id="sizing-addon3">物料类型</span> <select name="types" id="types" class="form-control select2" required>
										<option value="1">汽运物料</option>
										<option value="2">火运物料</option>
									</select>
								</div>
							</div>
							<div class="col-sm-12">
								<t:textbox id="queryword" name="queryword" label="拼音缩写" />
							</div>
							<div class="col-sm-12">
								<t:textbox id="usermemo" name="usermemo" label="用户备注" />
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer  btn-group-sm">
					<button type="button" class="btn btn-success" id="MaterialSaveBtn">保存</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-sm-12">
			<form id="queryform">
				<div class="row">
					<div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon">物料编码</span> <input type="text" class="form-control" placeholder="物料编码" id="materialcode" name="materialcode" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon">物料名称</span> <input type="text" class="form-control" placeholder="物料名称" id="materialname" name="materialname" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon">拼音头</span> <input type="text" class="form-control" placeholder="拼音头" id="queryword" name="queryword" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">物料类型</span> <select name="types" id="type" class="form-control select2">
								<option value="-1">全部</option>
								<option value="1">汽运物料</option>
								<option value="2">火运物料</option>
							</select>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="btn-group-sm">
							<button id="query" type="button" class="btn btn-info" onclick="queryinfo()">
								<span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;查询
							</button>
							<button id="add" type="button" class="btn btn-success" onclick="addFunction()">
								<span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;添加
							</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<table id="MaterialGrid" data-toggle="table" data-row-style="rowStyle" data-query-params="queryParams" data-pagination="true" data-page-size="10" data-page-list="[10,40,ALL]" data-mobile-responsive="true">
				<thead>
					<tr>
						<th data-field="id" data-visible="false"></th>
						<th data-field="materialcode" data-halign="center" data-searchable="true" class="text-center" sortable>物料编码</th>
						<th data-field="materialname" class="text-center" data-halign="center" data-searchable="true" sortable>物料名称</th>
						<th data-field="queryword" data-halign="center" data-searchable="true" class="text-nowrap text-center">拼音缩写</th>
						<th data-field="creator" data-halign="center" data-searchable="true" class="text-nowrap text-center">创建人</th>
						<th data-field="cdate" data-halign="center" data-searchable="true" class="text-nowrap text-center">创建时间</th>
						<th data-field="usermemo" data-halign="center" data-searchable="true" class="text-nowrap">备注</th>
						<th data-width="70px" data-align="center" data-valign="middle" data-formatter="operateFormatter" data-events="operateEvents">操作</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</body>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		queryinfo()
	});

	//查询
	function queryinfo() {
		$('#MaterialGrid').bootstrapTable('refresh', {
			url : "<c:url value='/material2/list'/>"
		});
	}
	jQuery(document).ready(
			function($) {
				$('#MaterialSaveBtn').on(
						'click',
						function() {
							saveFormData('MaterialForm',
									'<c:url value="/material2/edit"/>',
									function(data) {
										if (data.success) {
											successbox(data.msg);
											$('#MaterialWindow')
													.modal('toggle');
											$('#MaterialGrid').bootstrapTable(
													'refresh');
										} else {
											errorbox(data.msg);
										}
									});
						});
				$('#materialname').on('blur', function() {
					var val = $(this).val();
					if (val.length > 0) {
						$('#queryword').val(pinyin.getCamelChars(val));
					}
				});
			});
	function queryParams(params) {
		return jQuery.extend({}, params, $('#queryform').serializeJson());
	}

	function addFunction() {
		openWindow('MaterialWindow', 'MaterialForm',
				'<c:url value="/material2/form"/>?id=-1');
	}

	function operateFormatter(value, row, index) {
		return [ '<div class="pull-right">',
				'<a class="remove" href="javascript:void(0)" title="移除">',
				'<i class="glyphicon glyphicon-trash"></i>', '</a>', '</div>' ]
				.join('');

	}

	window.operateEvents = {
		'click .remove' : function(e, value, row) {
			cancelMaterial(row.id);
		}
	};

	function cancelMaterial(id) {
		dialogbox("请确认", "确认删除此条信息？", function(data) {
			if (data) {
				$.ajax({
					type : "post",
					url : '<c:url value="/material2/delete"/>',
					dataType : "json",
					data : {
						id : id
					},

					success : function(data) {
						if (data.success == true) {
							successbox(data.msg);
							$('#MaterialGrid').bootstrapTable('refresh');
						} else {
							errorbox(data.msg);
						}
					}
				});
			}
		});

	}
</script>
</t:html>