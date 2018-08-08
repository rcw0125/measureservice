<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="t" uri="http://www.talent.com/jsp/anyonelse"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.Date"%>
<t:html>
<head>
<jsp:include page="common.jsp" flush="true" />
<script type="text/javascript" src="<c:url value='/plugins/datatable/extensions/editable/bootstrap-table-editable.js'/>"></script>
<script type="text/javascript" src="<c:url value='/plugins/datatable/extensions/popupedit/bootstrap-table-popupedit.js'/>"></script>
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
					<%-- <div class="col-sm-3">
						<div class='form-group input-group input-group-sm date'>
							<span class="input-group-addon">开始时间</span> 
							<input type='text' class="form-control" placeholder="开始时间" id="begintime" name="begintime" value="<%=begintime%>" />
						</div>
					</div> --%>
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon">业&nbsp;&nbsp;务&nbsp;&nbsp;号</span> <input type="text" class="form-control" placeholder="业务号" id="taskcode" name="taskcode" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">供&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;货</span> <input type="text" class="form-control" placeholder="供货" name="sourcename" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">计量顺序</span> <select id="mflag" name="mflag" class="form-control select2">
								<option value="">全部</option>
								<option value="1">先毛后皮</option>
								<option value="2">先皮后毛</option>
							</select>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">打印次数</span> <select id="printcount" name="printcount" class="form-control select2">
								<option value="">全部</option>
								<option value="1">1次</option>
								<option value="2">2次</option>
							</select>
						</div>
					</div>
				</div>
				<div class="row">
					<%-- <div class="col-sm-3">
						<div class='form-group input-group input-group-sm date'>
							<span class="input-group-addon">结束时间</span>
							<input type='text' class="form-control" placeholder="结束时间" id="endtime" name="endtime" value="<%=endtime%>" />
						</div>
					</div> --%>
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">货&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</span> <input type="text" class="form-control" placeholder="货名" name="materialname" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">收&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;货</span> <input type="text" class="form-control" placeholder="收货" name="targetname" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-3">
						<div class="btn-group-sm">
							<t:button text="查询" modulecode="YeWuHaoWeiHu" id="query" btnclass="btn btn-info" iconclass="glyphicon glyphicon-search" onclick="queryinfo()" />
							<t:button text="添加" modulecode="YeWuHaoWeiHu" id="TaskcodeBtn" btnclass="btn btn-success" iconclass="glyphicon glyphicon-plus" />
							<t:button text="EXCEL" modulecode="BenDiYeWuHao" id="TaskcodeExcel" btnclass="btn btn-warning" iconclass="fa fa-file-excel-o" onclick="exportExcel()" />
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<table id="TaskcodeInfoGrid" data-toggle="table" data-row-style="rowStyle" data-query-params="queryParams" data-pagination="true" data-page-size="10" data-page-list="[10,40,ALL]" data-mobile-responsive="true">
				<thead>
					<tr>
						<th data-field="id" data-visible="false"></th>
						<th data-field="taskcode" data-halign="center" data-searchable="true" class="text-center" sortable>业务号</th>
						<th data-field="operatype" class="text-center" data-halign="center" data-searchable="true" sortable>业务类型</th>
						<th data-field="materialname" data-halign="center" data-searchable="true" class="text-nowrap text-center">货名</th>
						<th data-field="sourcename" data-halign="center" data-searchable="true" class="text-nowrap text-center">供货</th>
						<th data-field="targetname" data-halign="center" data-searchable="true" class="text-nowrap text-center">收货</th>
						<th data-field="tarehour" data-halign="center" data-searchable="true" class="text-nowrap text-center">皮重有效期</th>
						<th data-field="measurement" data-halign="center" data-searchable="true" class="text-nowrap text-center">计量顺序</th>
						<th data-field="printcount" data-halign="center" data-searchable="true" class="text-nowrap">打印次数</th>
						<th data-field="usermemo" data-halign="center" data-searchable="true" class="text-nowrap">备注</th>
						<th data-width="70px" data-align="center" data-valign="middle" data-formatter="operateFormatter" data-events="operateEvents">操作</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>

	<div class="modal fade" id="TaskcodeWindow" tabindex="-1" role="dialog" aria-labelledby="TaskcodeWindowLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">业务号操作</h4>
				</div>
				<div class="modal-body">
					<form id="TaskcodeForm">
						<input type="hidden" id="id" name="id" value="-1" />
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">业&nbsp;&nbsp;&nbsp;&nbsp;务&nbsp;&nbsp;&nbsp;&nbsp;号</span> <input id="taskcode" name="taskcode" class="form-control" required="required" maxlength="5" data-bv-stringlength-min="5" data-bv-integer data-bv-integer-message="请输入整数" aria-describedby="basic-addon1" onblur="queryCountbyTaskcode()">
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon" id="sizing-addon3">打&nbsp;印&nbsp;次&nbsp;数&nbsp;</span> <select id="printcount" name="printcount" required data-bv-notempty-message="请选择打印次数" class="form-control select2">
										<option value="1">1次</option>
										<option value="2">2次</option>
									</select>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">业&nbsp;务&nbsp;类&nbsp;型&nbsp;</span> <select name="operatype" class="form-control select2" required data-bv-notempty-message="请选择业务类型">
										<option value="10">厂内调拨</option>
										<!-- 
										<option value="11">跨区调拨</option> -->
									</select>
								</div>
							</div>
							<div class="col-sm-6">
								<input id="materialnames" name="materialname" type="hidden" class="form-control text-nowrap" aria-describedby="basic-addon1">
								<t:combobox id="materialcode" name="materialcode" url="/bcommon/queryBMaterilinfo.do" label="货　　　名" require="true" pagination="true" allowclear="true" alloptions="true" />
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<input id="sourcenames" name="sourcename" type="hidden" class="form-control text-nowrap" aria-describedby="basic-addon1">
								<t:combobox id="sourcecode" name="sourcecode" url="/bcommon/queryStoreinfo.do" label="供　　　货" require="true" pagination="true" allowclear="true" alloptions="true" />
							</div>
							<div class="col-sm-6">
								<input id="targetnames" name="targetname" type="hidden" class="form-control" aria-describedby="basic-addon1">
								<t:combobox id="targetcode" name="targetcode" url="/bcommon/queryStoreinfo.do" label="收　　　货" require="true" pagination="true" allowclear="true" alloptions="true" />
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">计&nbsp;量&nbsp;顺&nbsp;序&nbsp;</span> <select id="mflag" name="mflag" required data-bv-notempty-message="请选择计量顺序" class="form-control select2">
										<option value="1">先毛后皮</option>
										<option value="2">先皮后毛</option>
									</select>
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">皮重有效期</span> <input name="tarehour" class="form-control" data-bv-integer data-bv-integer-message="请输入整数" aria-describedby="basic-addon1">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">备　　　注</span> <input name="usermemo" type="text" class="form-control" aria-describedby="basic-addon1">
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

	<script type="text/javascript">
		jQuery(document).ready(function($) {
			queryinfo()
		});

		$(function() {
			$('#begintime').datetimepicker({
				format : 'YYYY-MM-DD HH:mm:ss',
				locale : 'zh-cn'
			});
			$('#endtime').datetimepicker({
				format : 'YYYY-MM-DD HH:mm:ss',
				locale : 'zh-cn'
			});
			$("#begintime").on("dp.change", function(e) {
				$('#endtime').data("DateTimePicker").minDate(e.date);
			});
			$("#endtime").on("dp.change", function(e) {
				$('#begintime').data("DateTimePicker").maxDate(e.date);
			});

		});

		function queryParams(params) {
			return jQuery.extend({}, params, $('#queryform').serializeJson());
		}

		function queryinfo() {
			$('#TaskcodeInfoGrid').bootstrapTable('refresh', {
				url : "<c:url value='/taskcode/queryPage.do'/>"
			});
		}

		function operateFormatter(value, row, index) {
			/* return [ '<div class="pull-right">',
					'<a class="edit" href="javascript:void(0)" title="修改">',
					'<i class="glyphicon glyphicon-pencil"></i>', '</a>　',
					'<a class="remove" href="javascript:void(0)" title="移除">',
					'<i class="glyphicon glyphicon-trash"></i>', '</a>',
					'</div>' ].join(''); */

			return [
					'<div class="pull-right">',
					'<t:ibutton text="修改" modulecode="YeWuHaoWeiHu" id="taskcodemodify" btnclass="edit" iconclass="glyphicon glyphicon-pencil"/>',
					'<t:ibutton text="移除" modulecode="YeWuHaoWeiHu" id="taskcoderemove" btnclass="remove" iconclass="glyphicon glyphicon-trash"/>',
					'</div>' ].join('');

		}

		window.operateEvents = {
			'click .edit' : function(e, value, row) {
				openWindow('TaskcodeWindow', 'TaskcodeForm',
						'<c:url value="/taskcode/queryInfoBytaskcode.do"/>?id='
								+ row.id);
			},
			'click .remove' : function(e, value, row) {
				cancelTaskcode(row.id);
			}
		};

		function stypeFormatter(value, row, index) {
			return [ '<div class="text-center">',
					'<a class="edits" href="javascript:void(0)" title="修改">',
					'<i class="glyphicon glyphicon-minus"></i>', '</a>　',
					'</div>' ].join('');
		}

		$('#TaskcodeBtn')
				.on(
						'click',
						function() {
							openWindow('TaskcodeWindow', 'TaskcodeForm',
									'<c:url value="/taskcode/queryInfoBytaskcode.do"/>?id=-1');
						});

		$('#TaskcodeWindow').on(
				'shown.bs.modal',
				function() {
					var id = $("#id").val();
					loadFormData('TaskcodeForm',
							'<c:url value="/taskcode/queryInfoBytaskcode.do?id="/>'
									+ id)
				});

		/**
		 *作废业务号信息
		 */
		function cancelTaskcode(id) {
			dialogbox("请确认", "确认删除此项目？", function(data) {
				if (data) {
					$.ajax({
						type : "post",
						url : '<c:url value="/taskcode/cancelTaskcode.do"/>',
						dataType : "json",
						data : {
							id : id
						},
						success : function(data) {
							if (data.success == true) {
								successbox(data.msg);
								$('#TaskcodeInfoGrid')
										.bootstrapTable('refresh');
							} else {
								errorbox(data.msg);
							}
						}
					});
				}
			});

		}

		function insert() {
			$("#sourcenames").val($('#sourcecode option:selected').text())
			$("#targetnames").val($('#targetcode option:selected').text())
			$("#materialnames").val($('#materialcode option:selected').text())
			saveFormData('TaskcodeForm',
					'<c:url value="/taskcode/insertTaskcode.do"/>', function(
							data) {
						if (data.success) {
							successbox(data.msg);
							$('#TaskcodeWindow').modal('toggle');
							$('#TaskcodeInfoGrid').bootstrapTable('refresh');
						} else {
							errorbox(data.msg);
						}
					});
		}
		/**
		 *查询输入的业务号是否存在
		 */
		function queryCountbyTaskcode() {
			$.ajax({
				type : "post",
				url : '<c:url value="/taskcode/queryCountbyTaskcode.do"/>',
				dataType : "json",
				data : {
					id : $("#taskcode").val()
				},
				success : function(data) {
					if (!data.success) {
						errorbox(data.msg);
						$("#taskcode").val("");
					}
				}
			});
		}
		function exportExcel() {
			remoteExportExcel('TaskcodeInfoGrid', '', '', 'queryform');
		}
	</script>
</body>
</t:html>






