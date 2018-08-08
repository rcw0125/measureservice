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
<script type="text/javascript" src="<c:url value='/plugins/bootstrap-typeahead.js'/>"></script>
</head>
<%
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Calendar c = Calendar.getInstance();
	String begintime = dateFormat.format(c.getTime()) + " 00:00:00";//开始时间
	String endtime = dateFormat.format(c.getTime()) + " 23:59:59";//结束时间
%>
</head>
<body class="container-fluid" style="padding-top: 15px">
	<div class="row">
		<div class="col-sm-12">
			<form id="queryform">
				<div class="row">

					<div class="col-sm-3">
						<div class='form-group input-group input-group-sm date'>
							<span class="input-group-addon">开始时间</span> <input type='text' class="form-control" placeholder="开始时间" id="begintime" name="begintime" value="<%=begintime%>" />
						</div>
					</div>
					<div class="col-sm-3">
						<div class='form-group input-group input-group-sm date'>
							<span class="input-group-addon">结束时间</span> <input type='text' class="form-control" placeholder="结束时间" id="endtime" name="endtime" value="<%=endtime%>" />
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">车号</span> <input type="text" class="form-control" placeholder="车号" id="carno" name="carno" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">类型</span> <select name="types" id="types" class="form-control select2" />
							<option value="1">皮重信息</option>
							<option value="2">皮重日志</option>
							</select>
						</div>
						<input type="hidden" id="cartype" name="cartype" value="${param.cartype}" />
					</div>
					<div class="col-sm-2">
						<div class="form-group input-group  btn-group-sm">
							<button id="query" type="button" class="btn btn-info" onclick="queryinfo()">
								<span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
							</button>
						</div>
					</div>
					</div>
			</form>
		</div>
	</div>
	<div class="row" style="padding-top: 5px; padding-left: 5px; padding-right: 5px;">
		<div class="col-sm-12">
			<table id="TareinfoGrid" data-toggle="table" data-query-params="queryParams" data-row-style="rowStyle" data-pagination="true" data-page-list="[10, 30, 50, 70, 100, ALL]" data-side-pagination="server">
				<thead>
					<tr>
						<th data-field="id" data-visible="false"></th>
						<th data-field="carno" class="text-nowrap text-left" data-halign="center" sortable>车号</th>
						<th data-field="tare" data-halign="center" class="text-nowrap text-right" sortable>皮重/t</th>
						<th data-field="taretime" data-halign="center" class="text-nowrap text-center">皮重时间</th>
						<th data-field="tareweigh" data-halign="center" class="text-nowrap text-left" data-searchable="true">皮重衡器</th>
						<th data-field="tareoperaname" data-halign="center" class="text-nowrap text-left" class="text-nowrap">皮重计量员</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>

	<script>
		jQuery(document).ready(function($) {
			queryinfo();

		});
		function queryinfo() {
			$('#TareinfoGrid').bootstrapTable('refresh', {
				url : "<c:url value='/measureReport/queryTareinfo.do'/>"
			});
		}

		$(function() {
			$('#begintime,#endtime').datetimepicker({
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
	</script>
</t:html>