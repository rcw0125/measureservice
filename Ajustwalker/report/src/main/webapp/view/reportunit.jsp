<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="common.jsp" flush="true" />
	<style type="text/css">
		.select2-container--bootstrap .select2-selection{
			border-radius: 0px 4px 4px 0px;
		}
		.select2-container--bootstrap.input-sm .select2-selection--single, .input-group-sm .select2-container--bootstrap .select2-selection--single, .form-group-sm .select2-container--bootstrap .select2-selection--single{
			border-radius: 0px 4px 4px 0px;
		}
	</style>
</head>
<%
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	Date now = new Date();
	Calendar c = Calendar.getInstance();
	int d = c.get(Calendar.DATE);
	String datetime = dateFormat.format(c.getTime());
	c.set(Calendar.DATE, d-5);
	String begintime = dateFormat.format(c.getTime()); 

%>
<body class="section container-fluid" style="padding-top:15px;">
	<div class="row-fluid">
		<div class="col-sm-12">
			<form id="queryform">
				<div class="col-sm-3">
					<div class="input-group input-group-sm">
						<span class="input-group-addon" id="sizing-addon3">类型</span> <select
							id="classes" name="classes" class="form-control select2"
							placeholder="类型">
							<option value="-1">全部</option>
							<option value="出库">出库</option>
							<option value="入库">入库</option>
						</select>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="input-group input-group-sm">
						<span class="input-group-addon" id="sizing-addon3">单位</span> <input
							type="text" name="unitname" id="unitname" class="form-control"
							placeholder="单位" aria-describedby="sizing-addon3">
					</div>
				</div>

				<div class="col-sm-3">
					<div class="input-group input-group-sm">
						<span class="input-group-addon" id="sizing-addon3">产线</span> <input
							type="text" class="form-control" placeholder="产线"
							id="productline" name="productline"
							aria-describedby="sizing-addon3">
					</div>
				</div>
				<div class="col-sm-3">
					<div class="input-group input-group-sm">
						<span class="input-group-addon" id="sizing-addon3">标记</span> <select
							id="flag" name="flag" class="form-control select2" placeholder="类型">
							<option value="-1">全部</option>
							<option value="1">钢后</option>
							<option value="2">铁前</option>
						</select>
					</div>
				</div>
				<div class="col-sm-3 btn-group-sm" style="padding-top: 5px;">
					<button id="query" type="button" class="btn btn-info"
						onclick="queryinfo()">
						<span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;查询
					</button>
					<button id="addReportunitBtn" type="button" class="btn btn-success"
						data-toggle="modal">
						<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;添加
					</button>
				</div>
		</div>

		</form>
		<div class="table-responsive"
			style="padding-top: 5px; padding-left: 5px; padding-right: 5px;">
			<table id="ReportunitGrid" data-toggle="table"
				data-row-style="rowStyle" data-pagination="true"
				data-page-list="[5, 10, 15, 20, ALL]"
				data-query-params="queryParams" data-side-pagination="server">
				<thead>
					<tr>
						<th data-field="id" data-halign="center" data-visible="false"></th>
						<th data-field="classes" data-halign="center" class="text-center">类型</th>
						<th data-field="unitname" data-halign="center" class="text-nowrap">单位</th>
						<th data-field="productline" data-halign="center"
							class="text-nowrap">产线</th>
						<th data-field="flag" data-halign="center" class="text-center">标记</th>
						<th data-field="seq" data-halign="center" class="text-center">顺序</th>
						<th data-field="operatype" data-halign="center" class="text-center">业务类型</th>
						<th data-field="createman" data-halign="center"
							class="text-center">添加人</th>
						<th data-field="createdate" data-halign="center"
							class="text-center">添加时间</th>
						<th data-field="updateman" data-halign="center"
							class="text-center">修改人</th>
						<th data-field="updatedate" data-halign="center"
							class="text-center">修改时间</th>
						<th data-width="70px" data-align="center" class="text-nowrap"
							data-valign="middle" data-formatter="operateFormatter"
							data-events="operateEvents">操作</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	</div>
	<div class="modal fade" id="ReportunitWindow" role="dialog"
		aria-labelledby="ReportunitWindowLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">单位信息</h4>
				</div>
				<div class="modal-body">
					<form id="ReportunitForm">
						<input type="hidden" name="id" value="-1">

						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group" style="margin: 2px">
									<span class="input-group-addon">单　　位</span><input type="text"
										class="form-control" placeholder="单位" id="unitname"
										name="unitname" aria-describedby="sizing-addon3">
								</div>
							</div>

						</div>
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group" style="margin: 2px">
									<span class="input-group-addon">产　　线</span><input type="text"
										class="form-control" placeholder="产线" id="productline"
										name="productline" aria-describedby="sizing-addon3">
								</div>
							</div>

						</div>
                        <div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group" style="margin: 2px">
									<span class="input-group-addon" id="sizing-addon3">类　　型</span> <select
										id="classes" name="classes" class="form-control select2"
										placeholder="类型">
										<option value="">全部</option>
										<option value="出库">出库</option>
										<option value="入库">入库</option>
									</select>
								</div>
							</div>

						</div>
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group" style="margin: 2px">
									<span class="input-group-addon" id="sizing-addon3">标　　记</span> <select
										id="flag" name="flag" class="form-control select2"
										placeholder="标记">
										<option value="">全部</option>
										<option value="1">钢后</option>
										<option value="2">铁前</option>
									</select>
								</div>
							</div>

						</div>
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group" style="margin: 2px">
									<span class="input-group-addon">顺　　序</span><input type="text"
										class="form-control" placeholder="顺序" id="seq" name="seq"
										aria-describedby="sizing-addon3">
								</div>
							</div>

						</div>
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group" style="margin: 2px">
									<span class="input-group-addon">业务类型</span><select
										name="operatype" id="operatype" class="form-control select2-data-ajax" multiple="multiple"
										ajax-url="<c:url value='/measure/queryOperatype.do'/>">
									</select>
								</div>
							</div>
						</div>
						<span class="help-block" id="errormsg"></span>
					</form>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-success"
						id="ReportunitSaveBtn">保存</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		jQuery(document).ready(function($) {

			queryinfo();
			$('#ReportunitForm').bootstrapValidator({
				excluded : [ ':disabled', ':hidden', ':not(:visible)' ],
				onSuccess : function() {
					$('#ReportunitForm').ajaxSubmit({
						type : 'post',
						url : '<c:url value="/reportunit/insertEquiment.do"/>',
						success : function(data) {
							if (data.success) {
								toastMessage("success", "提示", data.msg);
								$('#ReportunitWindow').modal('toggle');
								$('#ReportunitGrid').bootstrapTable('refresh');
							} else {
								toastMessage("error", "错误", data.msg);

							}

						}
					});
				},

				fields : {
					unitname : {
						container : "#errormsg",
						validators : {
							notEmpty : {
								message : '请填写单位名称！'
							}
						}
					},
					productline : {
						container : "#errormsg",
						validators : {
							notEmpty : {
								message : '请填写产线名称！'
							}
						}
					},
					classes : {
						container : "#errormsg",
						validators : {
							notEmpty : {
								message : '请选择类型！'
							}
						}
					},
					flag : {
						container : "#errormsg",
						validators : {
							notEmpty : {
								message : '请选择标记！'
							}
						}
					},
					seq : {
						container : "#errormsg",
						validators : {
							stringLength : {
								min : 1,
								max : 7,
								message : '量程长度必须在1到7位之间'
							},notEmpty : {
								message : '请填写顺序！'
							},
							regexp : {
								regexp : /^[0-9]+(\.[0-9]+)?$/,
								message : '顺序只能填写数字'
							}
						}
					}
				}
			});

			$('#ReportunitSaveBtn').on('click', function() {
				$('#ReportunitForm').bootstrapValidator('validate');
			});
			$('#addReportunitBtn').on('click', function() {
				openReportunitWindow(-1);
			});
			window.operateEvents = {
				'click .edit' : function(e, value, row) {
					openReportunitWindow(row.id);
				},
				'click .remove' : function(e, value, row) {
					cancelReportunit(row.id);
				}
			};

		});
		function operateFormatter(value, row, index) {
			return [ '<div class="pull-right">',
					'<a class="edit" href="javascript:void(0)" title="修改">',
					'<i class="glyphicon glyphicon-pencil"></i>', '</a>　',
					'<a class="remove" href="javascript:void(0)" title="移除">',
					'<i class="glyphicon glyphicon-trash"></i>', '</a>',
					'</div>' ].join('');
		}
		function queryParams(params) {
			return jQuery.extend({}, params, $('#queryform').serializeJson());
		}

		function queryinfo() {
			$('#ReportunitGrid').bootstrapTable('refresh', {
				url : "<c:url value='/reportunit/queryReportunit.do'/>"
			});
		}
		function openReportunitWindow(id_vv) {
			currentId = id_vv;
			$($('#ReportunitWindow form :input[name="id"]')).val(id_vv);
			$('#ReportunitWindow').modal('show');
		}
		function cancelReportunit(id_vv) {
			$.messager.confirm("请确认", "确认删除此项目？", function() {
				$.ajax({
					type : "post",
					url : '<c:url value="/reportunit/cancelReportunit.do"/>',
					dataType : "json",
					data : {
						id : id_vv
					},
					success : function(data) {
						if (data.success == true) {
							toastMessage("success", "提示", "操作成功");
							$('#ReportunitGrid').bootstrapTable('refresh');
						} else {
							toastMessage("error", "错误", data.msg);
						}
					}
				});
			});
		}
		$('#ReportunitWindow').on('shown.bs.modal',function() {
			var id_v = $($('#' + $(this).attr("id")+ ' form :input[name="id"]'));
							$.ajax({
										type : "post",
										url : '<c:url value="/reportunit/queryReportunitByid.do"/>?id='
												+ id_v.val(),
										dataType : "json",
										success : function(data) {
											loadFormData('ReportunitForm', data);
										}
									});
			});
	</script>
</body>
</html>