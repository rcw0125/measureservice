<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="t" uri="http://www.talent.com/jsp/anyonelse"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.Date"%>
<t:html>
<head>
<jsp:include page="common.jsp" flush="true" />
</head>
<%
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		Date now = new Date();
		Calendar c = Calendar.getInstance();
		now = c.getTime();
		String begintime = dateFormat.format(now);//开始时间
		String endtime = dateFormat1.format(now);//结束时间
%>
<body class="container-fluid" style="padding-top: 15px">
	<div class="row">
		<div class="col-sm-12">
			<form id="queryform">
				<div class="row">
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon">作业点名称</span> <input type="text" class="form-control" placeholder="类型名称" name="workpointname" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon">作&nbsp;业&nbsp;点&nbsp;I&nbsp;P</span> <input type="text" class="form-control" placeholder="作业点IP" name="workpointip" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-3">
						<t:combobox id="rfidtypes" name="rfidtype" url="/bcommon/queryReaderType.do" label="读卡器类型" require="false" alloptions="true" allowclear="true" />
					</div>
				</div>
				<div class="row">
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">作业点状态</span> <select name="validflag" class="form-control select2">
								<option value="-1">全部</option>
								<option value="0">作废</option>
								<option value="1">正常</option>
								<option value="2">注册</option>
								<option value="3">审核</option>
							</select>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon">作业点MAC</span> <input type="text" class="form-control" placeholder="作业点MAC" name="workpointmac" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-6">
						<div class="btn-group-sm">
							<button id="query" type="button" class="btn btn-info" onclick="queryinfo()">
								<span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;查询
							</button>
							<button id="add" type="button" class="btn btn-success">
								<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;添加
							</button>
							<!-- <button id="registe" type="button" class="btn btn-warning" >
								<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>&nbsp;注册
							</button> -->
							<button id="approve" type="button" class="btn btn-primary" onclick="approveWorkpoint()">
								<span class="glyphicon glyphicon-hand-up" aria-hidden="true"></span>&nbsp;审核
							</button>
							<button id="giveup" type="button" class="btn btn-warning" onclick="giveupWorkpoint()">
								<span class="glyphicon glyphicon-hand-up" aria-hidden="true"></span>&nbsp;弃审
							</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<table id="WorkpointInfoGrid" data-toggle="table" data-row-style="rowStyle" data-query-params="queryParams" data-pagination="true" data-page-size="10" data-page-list="[10,40,ALL]" data-mobile-responsive="true">
				<thead>
					<tr>
						<th data-field="state" data-checkbox="true"></th>
						<th data-field="id" data-visible="false"></th>
						<th data-field="workpointcode" data-halign="center" data-searchable="true" class="text-center" sortable>作业点编码</th>
						<th data-field="workpointname" data-halign="center" data-searchable="true" class="text-center" sortable>作业点名称</th>
						<th data-field="c_validflag" data-halign="center" data-searchable="true" class="text-center" sortable>状态</th>
						<th data-field="workpointip" class="text-left" data-halign="center" data-searchable="true" sortable>作业点IP</th>
						<th data-field="workpointmac" data-halign="center" data-searchable="true" class="text-nowrap text-center">作业点MAC</th>
						<th data-field="ictype" data-halign="center" data-searchable="true" class="text-nowrap text-center">IC读卡器类型</th>
						<th data-field="rfidtype" data-halign="center" data-searchable="true" class="text-nowrap text-center">RFID读卡器类型</th>
						<th data-field="creator" data-halign="center" data-searchable="true" class="text-nowrap text-center">创建人</th>
						<th data-field="cdate" data-halign="center" data-searchable="true" class="text-nowrap text-center">创建时间</th>
						<th data-field="registers" data-halign="center" data-searchable="true" class="text-nowrap text-center">注册人</th>
						<th data-field="workpointtime" data-halign="center" data-searchable="true" class="text-nowrap text-center">注册时间</th>
						<th data-field="approver" data-halign="center" data-searchable="true" class="text-nowrap text-center">审核人</th>
						<th data-field="approvertime" data-halign="center" data-searchable="true" class="text-nowrap text-center">审核时间</th>
						<th data-field="usermemo" data-halign="center" data-searchable="true" class="text-nowrap text-center">备注</th>
						<th data-width="70px" data-align="center" data-valign="middle" data-formatter="operateFormatter" data-events="operateEvents">操作</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<div class="modal fade" id="WorkpointWindow" tabindex="-1" role="dialog" aria-labelledby="WorkpointWindowLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">作业点操作</h4>
				</div>
				<div class="modal-body">
					<form id="WorkpointForm">
						<input type="hidden" id="id" name="id" value="-1" />
						<div class="row">
							<div class="col-sm-12">
								<input type="hidden" id="linktype" name="linktype" value="" /> <input id="linkname" name="linkname" type="hidden" aria-describedby="basic-addon1">
								<t:combobox id="linkcode" name="linkcode" url="/bcommon/queryLinkinfo.do?operatype=2" label="物　流　&nbsp;环　节" />
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<input id="workpointname" name="workpointname" type="hidden" class="form-control" value="">
								<t:combobox id="workpointcode" name="workpointcode" url="/bcommon/queryStorenames.do" label="作&nbsp;&nbsp;业&nbsp;&nbsp;点&nbsp;&nbsp;&nbsp;名&nbsp;&nbsp;称" require="true"/>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">作&nbsp;&nbsp;&nbsp;&nbsp;业&nbsp;&nbsp;&nbsp;&nbsp;点&nbsp;&nbsp;&nbsp;I&nbsp;&nbsp;P</span> <input type="text" class="form-control" placeholder="作业点IP" name="workpointip" aria-describedby="sizing-addon3">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">作&nbsp;&nbsp;&nbsp;业&nbsp;&nbsp;&nbsp;点&nbsp;&nbsp;MAC</span> <input type="text" class="form-control" placeholder="作业点MAC" name="workpointmac" aria-describedby="sizing-addon3">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<t:combobox id="ictype" name="ictype" url="/bcommon/queryReaderType.do?operatype=1" label="IC&nbsp;读&nbsp;卡&nbsp;器&nbsp;类&nbsp;型" require="false" allowclear="true" />
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<t:combobox id="rfidtype" name="rfidtype" url="/bcommon/queryReaderType.do?operatype=2" label="RFID读卡器类型" require="false" allowclear="true" />
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">备　　　　　&nbsp;注</span> <input name="usermemo" type="text" class="form-control" aria-describedby="basic-addon1">
								</div>
							</div>
						</div>

					</form>
				</div>
				<div class="modal-footer  btn-group-sm">
					<button type="button" class="btn btn-success" id="addBtn" onclick="insert()">确认</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="RegisteWindow" tabindex="-1" role="dialog" aria-labelledby="WorkpointWindowLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">作业点注册</h4>
				</div>
				<div class="modal-body">
					<form id="RegisteForm">
						<input type="hidden" id="id" name="id" value="-1" />
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">作&nbsp;&nbsp;业&nbsp;&nbsp;点&nbsp;&nbsp;&nbsp;名&nbsp;&nbsp;&nbsp;称</span> <input id="workpointname" name="workpointname" class="form-control" type="text" aria-describedby="basic-addon1" readonly="readonly">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">作&nbsp;&nbsp;&nbsp;&nbsp;业&nbsp;&nbsp;&nbsp;&nbsp;点&nbsp;&nbsp;&nbsp;&nbsp;I&nbsp;&nbsp;P</span> <input type="text" class="form-control" placeholder="作业点IP" name="workpointip" aria-describedby="sizing-addon3" readonly="readonly">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">作&nbsp;&nbsp;&nbsp;业&nbsp;&nbsp;&nbsp;&nbsp;点&nbsp;&nbsp;MAC</span> <input type="text" class="form-control" placeholder="作业点MAC" name="workpointmac" aria-describedby="sizing-addon3" readonly="readonly">
								</div>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer  btn-group-sm">
					<button type="button" class="btn btn-success" id="addBtn" onclick="registeWorkpoint()">确认</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		jQuery(document).ready(function($) {
			queryinfo();
			$('#workpointname').on('blur', function() {
				$('#workpointcode').val(pinyin.getFullChars($(this).val()));
				validForm('WorkpointForm');
			});

			$('#linkcode').on('change', function() {
				var v = $(this).find("option:selected").text();
				if ('记毛' == v || '记皮' == v) {
					$('#linktype').val('衡器');
				} else if ('出库' == v || '入库' == v) {
					$('#linktype').val('库房');
				} else if ('进厂' == v || '出厂' == v) {
					$('#linktype').val('大门');
				} else {
					$('#linktype').val(v);
				}
			});
		});

		function queryParams(params) {
			return jQuery.extend({}, params, $('#queryform').serializeJson());
		}

		function queryinfo() {
			$('#WorkpointInfoGrid').bootstrapTable('refresh', {
				url : "<c:url value='/workpoint/queryPage.do'/>"
			});
		}

		function operateFormatter(value, row, index) {
			return [ '<div class="pull-right">',
					'<a class="edit" href="javascript:void(0)" title="修改">',
					'<i class="glyphicon glyphicon-pencil"></i>', '</a>　',
					'<a class="remove" href="javascript:void(0)" title="移除">',
					'<i class="glyphicon glyphicon-trash"></i>', '</a>',
					'</div>' ].join('');
		}

		window.operateEvents = {
			'click .edit' : function(e, value, row) {
				if (row.validflag == 1 || row.validflag == 2) {
					openWindow('WorkpointWindow', 'WorkpointForm',
							'<c:url value="/workpoint/queryInfoByid.do"/>?id='
									+ row.id);
				}else if (row.validflag==3){
									errorbox("作业点已经审核不允许修改");
								}else if (row.validflag==0){
									errorbox("作业点已经作废不允许修改");
								}

			},
			'click .remove' : function(e, value, row) {
				cancelWorkpoint(row.id);
			}
		};

		function stypeFormatter(value, row, index) {
			return [ '<div class="text-center">',
					'<a class="edits" href="javascript:void(0)" title="修改">',
					'<i class="glyphicon glyphicon-minus"></i>', '</a>　',
					'</div>' ].join('');
		}

		$('#add').on('click',function() {
			openWindow('WorkpointWindow', 'WorkpointForm',
					'<c:url value="/workpoint/queryInfoByid.do"/>?id=-1');
		});

		$('#registe').on('click',function() {
			openWindow('RegisteWindow', 'RegisteForm',
					'<c:url value="/workpoint/queryInfoByIp.do"/>?workpointip=12.23.36.143');
		});

		function insert() {
			$("#workpointname").val($('#workpointcode option:selected').text())
			saveFormData('WorkpointForm',
					'<c:url value="/workpoint/insertWorkpoint.do"/>', function(
							data) {
						if (data.success) {
							successbox(data.msg);
							$('#WorkpointWindow').modal('toggle');
							$('#WorkpointInfoGrid').bootstrapTable('refresh');
						} else {
							errorbox(data.msg);
						}
					});
		}

		/**
		 *作废作业点信息
		 */
		function cancelWorkpoint(id) {
			dialogbox("请确认", "确认删除此项目？", function(data) {
				if (data) {
					$.ajax({
						type : "post",
						url : '<c:url value="/workpoint/cancelWorkpoint.do"/>',
						dataType : "json",
						data : {id : id},
						success : function(data) {
							if (data.success == true) {
								successbox(data.msg);
								$('#WorkpointInfoGrid').bootstrapTable(
										'refresh');
							} else {
								errorbox(data.msg);
							}
						}
					});
				}
			});

		}

		/**
		 *注册作业点信息
		 */
		function registeWorkpoint() {
			saveFormData('RegisteForm',
					'<c:url value="/workpoint/registeWorkpoint.do"/>',
					function(data) {
						if (data.success) {
							successbox(data.msg);
							$('#RegisteWindow').modal('toggle');
							$('#WorkpointInfoGrid').bootstrapTable('refresh');
						} else {
							errorbox(data.msg);
						}
					});
		}

		/**
		 *审核作业点信息
		 */
		function approveWorkpoint() {
			var id = "";
			var flag = 0;
			var rows = $('#WorkpointInfoGrid').bootstrapTable('getSelections');

			if (rows.length == 0) {
				errorbox("请选择审核信息");
			} else {
				for (var i = 0; i < rows.length; i++) {
					if (rows[i].validflag == 2) {//已注册的才允许审核					
						id = id + rows[i].id + ",";
					} else {
						flag++;
					}
				}
				if (flag > 0) {
					errorbox("已注册的作业点才允许审核，请确认选择的作业点的状态");
				} else {
					$.ajax({
								type : "post",
								url : '<c:url value="/workpoint/approveWorkpoint.do"/>',
								dataType : "json",
								data : {
									ids : id
								},
								success : function(data) {
									if (data.success == true) {
										successbox(data.msg);
										$('#WorkpointInfoGrid').bootstrapTable(
												'refresh');
									} else {
										errorbox(data.msg);
									}
								}
							});
				}

			}
		}

		/**
		 *作业点信息弃审
		 */
		function giveupWorkpoint() {
			var flag = 0;
			var id = "";
			var rows = $('#WorkpointInfoGrid').bootstrapTable('getSelections');
			if (rows.length == 0) {
				errorbox("请选择弃审信息");
			} else {
				for (var i = 0; i < rows.length; i++) {
					if (rows[i].validflag == 3) {//已审核的才允许弃审					
						id = id + rows[i].id + ",";
					} else {
						flag++;
					}
				}
				if (flag == 0) {
					dialogbox(
							"请确认",
							"确认弃审单据吗？",
							function(data) {
								if (data) {
									$.ajax({
												type : "post",
												url : '<c:url value="/workpoint/giveupWorkpoint.do"/>',
												dataType : "json",
												data : {
													ids : id
												},
												success : function(data) {
													if (data.success == true) {
														successbox(data.msg);
														$('#WorkpointInfoGrid')
																.bootstrapTable(
																		'refresh');
													} else {
														errorbox(data.msg);
													}
												}
											});
								}
							});

				} else if (flag > 0) {
					errorbox("作业点未审核无需弃审");
				}
			}
		}
	</script>
</body>
</t:html>






