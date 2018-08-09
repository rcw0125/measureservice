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
			<form id="hoverform">
				<div class="row" style="padding-left: 5px; padding-right: 5px;">
					<div class="col-sm-3">
					<div class='input-group input-group-sm date'>
						<span class="input-group-addon">开始时间</span> <input type='text'
							class="form-control" placeholder="开始时间" id="begintime"
							name="begintime" value="<%=begintime%>" />
					</div>
				</div>
					<div class="col-sm-3">
						<div class="input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">班别</span> <select
								id="workgroup" name="workgroup" class="form-control select2"
								placeholder="必选">
								<option value="-1">全部</option>
								<option value="白班">白班</option>
								<option value="中班">中班</option>
								<option value="夜班">夜班</option>
							</select>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">交班人</span> <input
								type="text" class="form-control" placeholder="交班人"
								id="handoverman" name="handoverman"
								aria-describedby="sizing-addon3">
						</div>
					</div>
				  <div class="col-sm-3">
						<div class="input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">状态</span> <select
								id="validflag" name="validflag" class="form-control select2"
								placeholder="必选">
								<option value="-1">全部</option>
								<option value="1" selected >正常</option>
								<option value="0">作废</option>
							</select>
						</div>
					</div>	
				</div>
				<div class="row" style="padding-left: 5px; padding-right: 5px;">
					<div class="col-sm-3">
					<div class='input-group input-group-sm date'>
						<span class="input-group-addon">结束时间</span> <input type='text'
							class="form-control" placeholder="结束时间" id="endtime"
							name="endtime" value="<%=datetime%>" />
					</div>
				</div>
					<div class="col-sm-3">
						<div class="input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">班次</span> <select
								id="workgroupsqe" name="workgroupsqe"
								class="form-control select2" placeholder="必选">
								<option value="-1">全部</option>
								<option value="甲">甲</option>
								<option value="乙">乙</option>
								<option value="丙">丙</option>
								<option value="丁">丁</option>
							</select>
						</div>
					</div>

					<div class="col-sm-3">
						<div class="input-group input-group-sm">

							<span class="input-group-addon" id="sizing-addon3">接班人</span> <input
								type="text" name="successor" id="successor" class="form-control"
								placeholder="接班人" aria-describedby="sizing-addon3">
						</div>
					</div>
					
					<div class="col-sm-3 btn-group-sm" style="padding-top: 5px;">
						<button id="query" type="button" class="btn btn-info" onclick="queryinfo()">
							<span class="glyphicon glyphicon-search" aria-hidden="true" ></span>&nbsp;查询
						</button>
						<button id="addMeasureweighBtn" type="button"
							class="btn btn-success" data-toggle="modal">
							<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;添加
						</button>
					</div>
				</div>
			</form>
			<div class="table-responsive"
				style="padding-top: 5px; padding-left: 5px; padding-right: 5px;">
				<table id="baseConfigGrid" data-toggle="table"
					data-row-style="rowStyle" data-pagination="true"
					data-page-list="[5, 10, 15, 20, ALL]" 
					data-query-params="queryParams"
					data-side-pagination="server"
					>
					<thead>
						<tr>
							<th data-field="id" data-halign="center" data-visible="false"></th>
							<th data-field="datetime" data-halign="center"
								class="text-nowrap">日期</th>
							<th data-field="workgroup" data-halign="center"
								class="text-nowrap">班别</th>
							<th data-field="workgroupsqe" data-halign="center"
								class="text-nowrap">班次</th>
							<th data-field="handoverman" data-halign="center"
								class="text-nowrap">交班人</th>
							<th data-field="successor" data-halign="center"
								class="text-nowrap">接班人</th>
							<th data-field="equipmentstatus" data-halign="center"
								class="text-nowrap">设备运行状态</th>
							<th data-field="handroomstatus" data-halign="center">工作通知记录</th>
							<th data-field="general" data-halign="center">异常情况处理</th>
							<th data-field="nextworknote" data-halign="center">下班注意问题</th>
							<th data-field="validflag" data-halign="center">状态</th>
							<th data-width="70px" data-align="center" class="text-nowrap"
								data-valign="middle" data-formatter="operateFormatter"
								data-events="operateEvents">操作</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	<div class="modal fade" id="HandoverWindow" role="dialog"
		aria-labelledby="MeasureRoleWindowLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">交接班台账添加</h4>
				</div>
				<div class="modal-body">
					<form id="HandoveraddForm">
						<input type="hidden" name="id" value="-1">
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group" style="margin: 2px">
									<span class="input-group-addon">日　期</span>
									<input class="form-control" placeholder="日期" id="datetime"
										name="datetime" value="<%=datetime%>" />
								</div>
							</div>

						</div>

						<div class="row">
							<div class="col-sm-6">
								<div class="form-group input-group" style="margin: 2px">
									<span class="input-group-addon">班　别</span>
									<select id="workgroup" name="workgroup"
										class="form-control select2" placeholder="必选">
										<option value="">全部</option>
										<option value="白班">白班</option>
										<option value="中班">中班</option>
										<option value="夜班">夜班</option>
									</select>



								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group" style="margin: 2px">
									<span class="input-group-addon">班　次</span>
									<select id="workgroupsqe" name="workgroupsqe"
										class="form-control select2" placeholder="必选">
										<option value="">全部</option>
										<option value="甲">甲</option>
										<option value="乙">乙</option>
										<option value="丙">丙</option>
										<option value="丁">丁</option>
									</select>
								</div>

							</div>
						</div>

						<div class="row">
							<div class="col-sm-6">
								<div class="form-group input-group" style="margin: 2px">
									<span class="input-group-addon">交班人</span><input type="text"
										class="form-control" placeholder="交班人" id="handoverman"
										name="handoverman" aria-describedby="sizing-addon3">
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group" style="margin: 2px">
									<span class="input-group-addon">接班人</span> <input type="text"
										name="successor" id="successor" class="form-control"
										placeholder="接班人" aria-describedby="sizing-addon3">
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group" style="margin: 2px">
									<span class="input-group-addon">设备运行情况</span>
									<textarea class="form-control" placeholder="设备运行情况"
										id="equipmentstatus" name="equipmentstatus" rows="5"></textarea>
								</div>
							</div>

						</div>

						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group" style="margin: 2px">
									<span class="input-group-addon">工作通知记录</span>
									<textarea class="form-control" placeholder="工作通知记录"
										id="handroomstatus" name="handroomstatus" rows="8"></textarea>
								</div>
							</div>

						</div>

						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group" style="margin: 2px">
									<span class="input-group-addon">异常情况处理</span>
									<textarea class="form-control" placeholder="异常情况处理"
										id="general" name="general" rows="5"></textarea>
								</div>
							</div>

						</div>

						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group" style="margin: 2px">
									<span class="input-group-addon">下班注意问题</span>
									<textarea class="form-control" placeholder="下班注意问题"
										id="nextworknote" name="nextworknote" rows="5"></textarea>
								</div>
							</div>

						</div>
						<span class="help-block" id="errormsg"></span>
					</form>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-success"
						id="MeasureweighSaveBtn">保存</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="HandoverDetailWindow" role="dialog"
		aria-labelledby="MeasureRoleWindowLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">交接班台账详情</h4>
				</div>
				<div class="modal-body">
					<form id="HandoverdetailForm">
						<input type="hidden" name="id" value="-1">
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group" style="margin: 2px">
									<span class="input-group-addon">日　期</span>
									<input class="form-control" placeholder="日期" id="datetime"
										name="datetime" disabled="disabled" />
								</div>
							</div>

						</div>

						<div class="row">
							<div class="col-sm-6">
								<div class="form-group input-group" style="margin: 2px">
									<span class="input-group-addon">班　别</span>
									<select id="workgroup" name="workgroup"
										class="form-control select2" placeholder="必选"
										disabled="disabled">
										<option value="">全部</option>
										<option value="白班">白班</option>
										<option value="中班">中班</option>
										<option value="夜班">夜班</option>
									</select>
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group" style="margin: 2px">
									<span class="input-group-addon">班　次</span>
									<select id="workgroupsqe" name="workgroupsqe"
										class="form-control select2" placeholder="必选"
										disabled="disabled">
										<option value="">全部</option>
										<option value="甲">甲</option>
										<option value="乙">乙</option>
										<option value="丙">丙</option>
										<option value="丁">丁</option>
									</select>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-sm-6">
								<div class="form-group input-group" style="margin: 2px">
									<span class="input-group-addon">交班人</span><input type="text"
										class="form-control" placeholder="交班人" id="handoverman"
										name="handoverman" aria-describedby="sizing-addon3"
										disabled="disabled">
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group" style="margin: 2px">
									<span class="input-group-addon">接班人</span> <input type="text"
										name="successor" id="successor" class="form-control"
										placeholder="接班人" aria-describedby="sizing-addon3"
										disabled="disabled">
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group" style="margin: 2px">
									<span class="input-group-addon">设备运行情况</span>
									<textarea class="form-control" placeholder="设备运行情况"
										id="equipmentstatus" name="equipmentstatus" rows="5"
										disabled="disabled"></textarea>
								</div>
							</div>

						</div>

						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group" style="margin: 2px">
									<span class="input-group-addon">工作通知记录</span>
									<textarea class="form-control" placeholder="工作通知记录"
										id="handroomstatus" name="handroomstatus" rows="8"
										disabled="disabled"></textarea>
								</div>
							</div>

						</div>

						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group" style="margin: 2px">
									<span class="input-group-addon">异常情况处理</span>
									<textarea class="form-control" placeholder="异常情况处理"
										id="general" name="general" rows="5" disabled="disabled"></textarea>
								</div>
							</div>

						</div>

						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group" style="margin: 2px">
									<span class="input-group-addon">下班注意问题</span>
									<textarea class="form-control" placeholder="下班注意问题"
										id="nextworknote" name="nextworknote" rows="5"
										disabled="disabled"></textarea>
								</div>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		jQuery(document).ready(function($) {

			queryinfo();
			$('#HandoveraddForm').bootstrapValidator({
				excluded : [ ':disabled', ':hidden', ':not(:visible)' ],
				onSuccess : function() {
					$('#HandoveraddForm').ajaxSubmit({
						type : 'post',
						url : '<c:url value="/handover/insertHandover.do"/>',
						success : function(data) {
							if (data.success == true) {
								toastMessage("success", "提示", data.msg);
								$('#HandoverWindow').modal('toggle');
								$('#baseConfigGrid').bootstrapTable('refresh');
							} else {
								toastMessage("error", "错误", "操作失败！");

							}

						}
					});
				},
		        fields: {
		        	datetime: {
		        		container:"#errormsg",
		                validators: {
		                    notEmpty: {
		                        message: '请填写日期！'
		                    }
		                }
		            },
		            workgroup: {
		        		container:"#errormsg",
		                validators: {
		                    notEmpty: {
		                        message: '请填写班别！'
		                    }
		                }
		            },
		            workgroupsqe:{
		            	container:"#errormsg",
		                validators: {
		                    notEmpty: {
		                        message: '请填写班次！'
		                    }
		                }
		            },
		            handoverman:{
		            	container:"#errormsg",
		                validators: {
		                    notEmpty: {
		                        message: '请填写交班人！'
		                    }
		                }
		            },
		            successor:{
		            	container:"#errormsg",
		                validators: {
		                    notEmpty: {
		                        message: '请填写接班人！'
		                    }
		                }
		            },
		            equipmentstatus:{
		            	container:"#errormsg",
		                validators: {
		                	stringLength: {
		                        min: 0,
		                        max: 1000,
		                        message: '填写的内容超过1000字！'
		                    }
		                    
		                }
		            },
		            handroomstatus:{
		            	container:"#errormsg",
		                validators: {
		                	stringLength: {
		                        min: 0,
		                        max: 1000,
		                        message: '填写的内容超过1000字！'
		                    }
		                }
		            }
		            ,
		            general:{
		            	container:"#errormsg",
		                validators: {
		                	stringLength: {
		                        min: 0,
		                        max: 1000,
		                        message: '填写的内容超过1000字！'
		                    }
		                }
		            },
		            nextworknote:{
		            	container:"#errormsg",
		                validators: {
		                	stringLength: {
		                        min: 0,
		                        max: 1000,
		                        message: '填写的内容超过1000字！'
		                    }
		                }
		            }
		        }
			});

			
			
			$('#MeasureweighSaveBtn').on('click', function() {

				$('#HandoveraddForm').bootstrapValidator('validate');
			});
			$('#addMeasureweighBtn').on('click', function() {

				openHandoverWindow(-1);
			});
			window.operateEvents = {
				'click .edit' : function(e, value, row) {
					openHandoverWindow(row.id);
				},
				'click .remove' : function(e, value, row) {
					cancelMeasureweigh(row.id);
				}
			};

		});
		function queryParams(params) {
			return jQuery.extend({},params,$('#hoverform').serializeJson());
		}

		function queryinfo() {
			$('#baseConfigGrid').bootstrapTable('refresh', {
				url : "<c:url value='/handover/queryHandover.do'/>"
			});
		}
		function openHandoverWindow(id_vv) {
			currentId = id_vv;
			$($('#HandoverWindow form :input[name="id"]')).val(id_vv);
			$('#HandoverWindow').modal('show');
		}
		function openHandoverDetailWindow(id_vv) {
			currentId = id_vv;
			$($('#HandoverDetailWindow form :input[name="id"]')).val(id_vv);
			$('#HandoverDetailWindow').modal('show');
		}

		$(function() {
			$('#datetime,#begintime,#endtime,#datetimes').datetimepicker({
				format : 'YYYY-MM-DD',
				locale : 'zh-cn'
			});
			
			
		});
		function operateFormatter(value, row, index) {
			return [ '<div class="pull-right">',
					'<a class="edit" href="javascript:void(0)" title="修改">',
					'<i class="glyphicon glyphicon-pencil"></i>', '</a>　',
					'<a class="remove" href="javascript:void(0)" title="移除">',
					'<i class="glyphicon glyphicon-trash"></i>', '</a>',
					'</div>' ].join('');
		}
		function cancelMeasureweigh(id_vv) {
			$.messager.confirm("请确认", "确认删除此项目？", function() {
				$.ajax({
					type : "post",
					url : '<c:url value="/handover/cancelHandover.do"/>',
					dataType : "json",
					data : {
						id : id_vv
					},
					success : function(data) {
						if (data.success == true) {
							toastMessage("success", "提示", "操作成功");
							$('#baseConfigGrid').bootstrapTable('refresh');
						} else {
							toastMessage("error", "错误", data.msg);
						}
					}
				});
			});
		}
		$('#HandoverWindow').on('shown.bs.modal',
						function() {
							var id_v = $($('#' + $(this).attr("id")
									+ ' form :input[name="id"]'));
							$.ajax({
										type : "post",
										url : '<c:url value="/handover/queryHandoverByid.do"/>?id='
												+ id_v.val(),
										dataType : "json",
										success : function(data) {
											loadFormData('HandoveraddForm',
													data);
										}
									});
						});
		$('#HandoverDetailWindow').on('shown.bs.modal',
						function() {
							var id_v = $($('#' + $(this).attr("id")
									+ ' form :input[name="id"]'));
							$.ajax({
										type : "post",
										url : '<c:url value="/handover/queryHandoverByid.do"/>?id='
												+ id_v.val(),
										dataType : "json",
										success : function(data) {
											loadFormData('HandoverdetailForm',data);
										}
									});
						});
		$('#baseConfigGrid').bootstrapTable({
			onDblClickRow : function(row, $element) {
				openHandoverDetailWindow(row.id);

			}
		});
	</script>
</body>
</html>