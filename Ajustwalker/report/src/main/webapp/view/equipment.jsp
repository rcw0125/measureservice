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
			<form id="equipmentform">
				<div class="col-sm-3">
					<div class="input-group input-group-sm">
						<span class="input-group-addon" id="sizing-addon3">衡器名称</span> <input
							type="text" name="equname" id="equname" class="form-control"
							placeholder="衡器名称" aria-describedby="sizing-addon3">
					</div>
				</div>
				<div class="col-sm-3">
					<div class="input-group input-group-sm">
						<span class="input-group-addon" id="sizing-addon3">衡器类型</span>
						<select id="equtype" name="equtype" class="form-control select2" placeholder="衡器类型">
							<option value="">全部</option>
							<option value="C">衡器</option>
							<option value="RC">坐席</option>
							<!-- <option value="I">铁水衡器</option>
										<option value="TD">火车动态衡</option>
										<option value="TJ">火车静态 </option> -->
						</select>						 
					</div>
				</div>
				<div class="col-sm-3">
					<div class="input-group input-group-sm">
						<span class="input-group-addon" id="sizing-addon3">IP地址</span> <input
							type="text" class="form-control" placeholder="IP地址" id="ip"
							name="ip" aria-describedby="sizing-addon3">
					</div>
				</div>
				
				<div class="col-sm-3 btn-group-sm" style="padding-top: 5px;">
					<button id="query" type="button" class="btn btn-info"
						onclick="queryinfo()">
						<span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;查询
					</button>
					<button id="addEquipmentBtn" type="button" class="btn btn-success"
						data-toggle="modal">
						<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;添加
					</button>
				</div>
		</div>

		</form>
		<div class="table-responsive"
				style="padding-top: 5px; padding-left: 5px; padding-right: 5px;">
				<table id="EquipmentGrid" data-toggle="table"
					data-row-style="rowStyle" data-pagination="true"
					data-page-list="[5, 10, 15, 20, ALL]" 
					data-query-params="queryParams"
					data-side-pagination="server"
					>
					<thead>			
						<tr>
							<th data-field="id" data-halign="center" data-visible="false"></th>
							<th data-field="equcode" data-halign="center"
								class="text-center">衡器编码</th>
							<th data-field="equname" data-halign="center"
								class="text-nowrap">衡器名称</th>
							<th data-field="equtype" data-halign="center"
								class="text-center">衡器类型</th>
							<th data-field="ip" data-halign="center"
								class="text-left">IP地址</th>
							<th data-field="range" data-halign="center"
								class="text-center">量程/t</th>
							<th data-field="createman" data-halign="center"
								class="text-center">添加人</th>
							<th data-field="createtime" data-halign="center"
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
	<div class="modal fade" id="EquipmentWindow" role="dialog"
		aria-labelledby="EquipmentWindowLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">衡器信息</h4>
				</div>
				<div class="modal-body">
					<form id="EquipmentForm">
						<input type="hidden" name="id" value="-1">					

						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group" style="margin: 2px">
									<span class="input-group-addon">衡器编码</span><input type="text"
										class="form-control" placeholder="衡器编码" id="equcode"
										name="equcode" aria-describedby="sizing-addon3">
								</div>
							</div>
							
						</div>
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group" style="margin: 2px">
									<span class="input-group-addon">衡器名称</span><input type="text"
										class="form-control" placeholder="衡器名称" id="equname"
										name="equname" aria-describedby="sizing-addon3">
								</div>
							</div>
							
						</div>
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group" style="margin: 2px">
									<span class="input-group-addon">IP地址　</span><input type="text"
										class="form-control" placeholder="IP地址" id="ip"
										name="ip" aria-describedby="sizing-addon3">
								</div>
							</div>
							
						</div>
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group" style="margin: 2px">
									<span class="input-group-addon">量程/t&nbsp;　</span><input type="text"
										class="form-control" placeholder="量程" id="range"
										name="range" aria-describedby="sizing-addon3">
								</div>
							</div>
							
						</div>
                        	<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group" style="margin: 2px">
									<span class="input-group-addon">衡器类型</span>
									<select id="equtype" name="equtype"
										class="form-control select2" placeholder="必选">
										<option value="">全部</option>
										<option value="C">衡器</option>
										<option value="RC">坐席</option>
										<!-- <option value="I">铁水衡器</option>
										<option value="TD">火车动态衡</option>
										<option value="TJ">火车静态 </option> -->
									</select>



								</div>
							</div>
						
						</div>		
						<span class="help-block" id="errormsg"></span>
					</form>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-success"
						id="EquipmentSaveBtn">保存</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		jQuery(document).ready(function($) {
			
		queryinfo();
			$('#EquipmentForm').bootstrapValidator({
				excluded : [ ':disabled', ':hidden', ':not(:visible)' ],
				onSuccess : function() {
					$('#EquipmentForm').ajaxSubmit({
						type : 'post',
						url : '<c:url value="/equipment/insertEquiment.do"/>',
						success : function(data) {
							if (data.success) {
								toastMessage("success", "提示", data.msg);
								$('#EquipmentWindow').modal('toggle');
								$('#EquipmentGrid').bootstrapTable('refresh');
							} else {
								toastMessage("error", "错误", data.msg);

							}

						}
					});
				},
				fields : {
					equcode : {
						container : "#errormsg",
						validators : {
							notEmpty : {
								message : '请填写衡器编码！'
							}
						}
					},
					equname : {
						container : "#errormsg",
						validators : {
							notEmpty : {
								message : '请填写衡器名称！'
							}
						}
					},
					equtype : {
						container : "#errormsg",
						validators : {
							notEmpty : {
								message : '请选择衡器类型！'
							}
						}
					},
					range : {
						container : "#errormsg",
						validators : {
							stringLength : {
								min : 1,
								max : 7,
								message : '量程长度必须在1到7位之间'
							},
							regexp : {
								regexp : /^[0-9]+(\.[0-9]+)?$/,
								message : '量程只能填写数字'
							}
						}
					}
				}
			});

			$('#EquipmentSaveBtn').on('click', function() {
				$('#EquipmentForm').bootstrapValidator('validate');
			});
			$('#addEquipmentBtn').on('click', function() {

				openEquipmentWindow(-1);
			});
			window.operateEvents = {
				'click .edit' : function(e, value, row) {
					openEquipmentWindow(row.id);
				},
				'click .remove' : function(e, value, row) {
					cancelEquipment(row.id);
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
			return jQuery.extend({}, params, $('#equipmentform')
					.serializeJson());
		}

		function queryinfo() {
			$('#EquipmentGrid').bootstrapTable('refresh', {
				url : "<c:url value='/equipment/queryEquiment.do'/>"
			});
		}
		function openEquipmentWindow(id_vv) {
			currentId = id_vv;
			$($('#EquipmentWindow form :input[name="id"]')).val(id_vv);
			$('#EquipmentWindow').modal('show');
		}
		function cancelEquipment(id_vv) {
			$.messager.confirm("请确认", "确认删除此项目？", function() {
				$.ajax({
					type : "post",
					url : '<c:url value="/equipment/cancelEquiment.do"/>',
					dataType : "json",
					data : {
						id : id_vv
					},
					success : function(data) {
						if (data.success == true) {
							toastMessage("success", "提示", "操作成功");
							$('#EquipmentGrid').bootstrapTable('refresh');
						} else {
							toastMessage("error", "错误", data.msg);
						}
					}
				});
			});
		}
		$('#EquipmentWindow').on('shown.bs.modal',function() {
			var id_v = $($('#' + $(this).attr("id")+ ' form :input[name="id"]'));
							$.ajax({
									type : "post",
									url : '<c:url value="/equipment/queryEquimentByid.do"/>?id='
												+ id_v.val(),
									dataType : "json",
									success : function(data) {
											loadFormData('EquipmentForm', data);
									}
							});
						});
	</script>
</body>
</html>