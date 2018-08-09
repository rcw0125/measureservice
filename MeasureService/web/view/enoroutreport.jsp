<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.Date"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="common.jsp" flush="true" />
	<script type="text/javascript" src="<c:url value='/plugins/datatable/extensions/export/jquery.table2excel.js'/>"></script>
	<%
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		Calendar c = Calendar.getInstance();
		int d = c.get(Calendar.DATE);
		c.set(Calendar.DATE, d - 1);
		now = c.getTime();
		String datetime = dateFormat.format(now);//开始时间
	%>
</head>
<body>
	<form id="queryform">
		<div class="row" style="padding-left: 5px; padding-right: 5px;">
			<div class="col-sm-3">
				<div class='input-group input-group-sm date'>
					<span class="input-group-addon">日期</span> <input type='text'
						class="form-control" placeholder="日期" id="datetime"
						name="datetime" value="<%=datetime%>" />
				</div>
			</div>
			
			<div class="col-sm-3 btn-group-sm" style="padding-top: 5px;">
				<button id="query" type="button" class="btn btn-info"
					onclick="queryinfo()">
					<span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;查询
				</button>
				<button id="exportExcel" type="button" class="btn btn-danger"
					onclick="excel()">
					<span class="glyphicon glyphicon-floppy-save" aria-hidden="true"></span>&nbsp;Excel导出
				</button>
			</div>
		</div>
	</form>
	<div class="row" style="padding-top: 5px; padding-left: 5px; padding-right: 5px;">
		<div class="col-sm-12">
			<table id="EnoroutinfoGrid" data-toggle="table" data-query-params="queryParams" data-row-style="rowStyle">
				<thead>
					<tr>
						<th data-field="operatype" class="text-nowrap text-center" data-halign="center" sortable>业务</th>
						<th data-field="classes" data-halign="center" class="text-nowrap text-center" sortable>类型</th>
						<th data-field="materialname" data-halign="center" class="text-nowrap text-left">货名</th>
						<th data-field="ship" data-halign="center" class="text-nowrap text-left" data-searchable="true">船名</th>
						<th data-field="daysuttle" data-halign="center" class="text-nowrap text-right" class="text-nowrap">当日重量/t</th>
						<th data-field="monthsuttle" data-halign="center" class="text-nowrap text-right" class="text-nowrap">当月累计/t</th>
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
			$('#EnoroutinfoGrid').bootstrapTable({rowStyle:rowStyle,queryParams:queryParams}).bootstrapTable('removeAll').bootstrapTable('refresh',{url : "<c:url value='/StatisticsReport/queryMeasureByOperatype.do'/>"});
		}
		
		function resizeCallBack(){
			$('#EnoroutinfoGrid').mergeCell({cols:[0,1,2]});
		}

		$(function() {
			$('#datetime').datetimepicker({
				format : 'YYYY-MM-DD',
				locale : 'zh-cn'
			});
		});

		function queryParams(params) {
			return jQuery.extend({}, params, $('#queryform').serializeJson());
		}
		
		function excel() {
			$("#EnoroutinfoGrid").table2excel({
				exclude: ".noExl",
				name: "Excel Document Name",
				filename: "进出厂累计报表",
				fileext: ".xls",
				exclude_img: true,
				exclude_links: true,
				exclude_inputs: true
			});
		}
	</script>
</html>