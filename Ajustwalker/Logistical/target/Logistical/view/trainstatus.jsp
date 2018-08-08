<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="t" uri="http://www.talent.com/jsp/anyonelse"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Calendar"%>
<t:html>
<head>
<jsp:include page="common.jsp" flush="true" />
<script type="text/javascript" src="<c:url value='/plugins/datatable/extensions/editable/bootstrap-table-editable.js'/>"></script>
<script type="text/javascript" src="<c:url value='/plugins/datatable/extensions/popupedit/bootstrap-table-popupedit.js'/>"></script>
<%
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		String begintime = dateFormat.format(c.getTime()) + " 00:00:00";//开始时间
		String endtime = dateFormat.format(c.getTime()) + " 23:59:59";//结束时间
		SimpleDateFormat dformat2 = new SimpleDateFormat("yyyyMMdd");
%>
</head>
<body class="container-fluid" style="padding-top: 15px">
	<form id="queryform">
		<div class="row">
			<div class="col-sm-3">
				<div class='form-group input-group input-group-sm date'>
					<span class="input-group-addon">开始时间</span> <input type='text' class="form-control" placeholder="开始时间" id="begintime" name="begintime" value="<%=begintime%>" />
				</div>
			</div>
			<div class="col-sm-3">
				<div class="form-group input-group input-group-sm">
					<span class="input-group-addon" id="sizing-addon3">车&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型</span> <input type="text" class="form-control" placeholder="车型" id="vtraintype" name="vtraintype" aria-describedby="sizing-addon3">
				</div>
			</div>
			<div class="col-sm-3">
				<div class="form-group input-group input-group-sm">
					<span class="input-group-addon" id="sizing-addon3">物料名称</span> <input type="text" class="form-control" placeholder="物料名称" id="vinvname" name="vinvname" aria-describedby="sizing-addon3">
				</div>
			</div>
			<div class="col-sm-3">
				<t:combobox id="vstation" name="vstation" url="/trainstatus/queryVstation" label="发&nbsp;&nbsp;到&nbsp;&nbsp;站" require="false" alloptions="true" allowclear="true" pagination="true" />
			</div>
		</div>
		<div class="row">
			<div class="col-sm-3">
				<div class='form-group input-group input-group-sm date'>
					<span class="input-group-addon">结束时间</span> <input type='text' class="form-control" placeholder="结束时间" id="endtime" name="endtime" value="<%=endtime%>" />
				</div>
			</div>
			<div class="col-sm-3">
				<div class="form-group input-group input-group-sm">
					<span class="input-group-addon" id="sizing-addon3">车&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</span> <input type="text" class="form-control" placeholder="车号" id="vtraincode" name="vtraincode" aria-describedby="sizing-addon3">
				</div>
			</div>
			<div class="form-group col-sm-3 btn-group-sm">
				<t:button text="查询" modulecode="HuoYunYuBao" id="query" btnclass="btn btn-info" iconclass="glyphicon glyphicon-search" onclick="queryinfo()" />
				<t:button text="EXCEL" modulecode="HuoYunYuBao" id="Excel" btnclass="btn btn-warning" iconclass="fa fa-file-excel-o" onclick="exportExcel()" />
			</div>
		</div>
	</form>

	<div class="row">
		<div class="col-sm-12">
			<table id="TrainStatusGrid" data-toggle="table" data-row-style="rowStyle" data-query-params="queryParams" data-pagination="true" data-page-size="10" data-page-list="[10,40,ALL]" data-mobile-responsive="true">
				<thead>
					<tr>
						<th data-field="vreceivecode" data-halign="center" data-searchable="true" class="text-center">接车单号</th>
						<th data-field="vtraintype" data-halign="center" data-searchable="true" class="text-center" sortable>车型</th>
						<th data-field="vtraincode" data-halign="center" data-searchable="true" class="text-center" sortable>车号</th>
						<th data-field="istatus" data-halign="center" data-searchable="true" class="text-center" sortable>车皮状态</th>
						<th data-field="vinvname" data-halign="center" data-searchable="true" class="text-center" sortable>物料名称</th>
						<th data-field="vstation" data-halign="center" data-searchable="true" class="text-center" sortable>发到站</th>
						<th data-field="measstatus" data-halign="center" data-searchable="true" class="text-center" sortable>计量状态</th>
						<th data-field="createdate" data-halign="center" data-searchable="true" class="text-center" sortable>创建时间</th>
						<th data-width="70px" data-align="center" data-valign="middle" data-formatter="operateFormatter" data-events="operateEvents">操作</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>

	<script type="text/javascript">
		jQuery(document).ready(function($) {
			queryinfo();
		});

		function queryParams(params) {
			return jQuery.extend({}, params, $('#queryform').serializeJson());
		}

		function queryinfo() {
			$('#TrainStatusGrid').bootstrapTable('refresh', {
				url : "<c:url value='/trainstatus/list'/>"
			});
		}

		function operateFormatter(value, row, index) {
			return [
				'<t:ibutton text="移除" modulecode="HuoYunYuBao" id="trainstatusremove" btnclass="remove" iconclass="glyphicon glyphicon-trash"/>'
					].join('');
		}

		window.operateEvents = {
			'click .remove' : function(e, value, row) {
				deleteStatus(row.vreceivecode);
			}
		};

		function deleteStatus(vreceivecode) {
			dialogbox("请确认", "确认删除此条信息？", function(data) {
				if (data) {
					$
							.ajax({
								type : "post",
								url : '<c:url value="/trainstatus/delete"/>',
								dataType : "json",
								data : {
									vreceivecode : vreceivecode
								},
								success : function(data) {
									if (data.success) {
										successbox(data.msg);
										$('#TrainStatusGrid').bootstrapTable(
												'refresh');
									} else {
										errorbox(data.msg);
									}
								}
							});
				}
			});

		}

		function exportExcel() {
			remoteExportExcel('TrainStatusGrid', '', '火运预报', 'queryform');
		}
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
	</script>

</body>
</t:html>