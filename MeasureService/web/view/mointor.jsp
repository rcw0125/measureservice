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
<%
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
	SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
	Date now = new Date();
	Calendar c = Calendar.getInstance();
	now = c.getTime();
	String begintime = dateFormat.format(now);//开始时间
	String endtime = dateFormat1.format(now);//结束时间
%>
</head>
<body>
	<form id="queryform">
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
					<span class="input-group-addon" id="sizing-addon3">内　　容&nbsp;</span> <input
						type="text" class="form-control" placeholder="内容" id="errormsg"
						name="errormsg" aria-describedby="sizing-addon3">
				</div>
			</div>
			</div>
			<div class="row" style="padding-left: 5px; padding-right: 5px;">
			<div class="col-sm-3">
				<div class='input-group input-group-sm date'>
					<span class="input-group-addon">结束时间</span> <input type='text'
						class="form-control" placeholder="结束时间" id="endtime"
						name="endtime" value="<%=endtime%>" />
				</div>
			</div>
			
			<div class="col-sm-3">
				<div class="input-group input-group-sm">
					<span class="input-group-addon" id="sizing-addon3">衡器/人员</span> <input
						type="text" class="form-control" placeholder="内容" id="weightname"
						name="weightname" aria-describedby="sizing-addon3">
				</div>
			</div>
			<div class="col-sm-3 btn-group-sm" style="padding-top: 5px;">
				<button id="query" type="button" class="btn btn-info"
					onclick="queryinfo()">
					<span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;查询
				</button>

			</div>
		</div>
	</form>
	<div class="row"
		style="padding-top: 5px; padding-left: 5px; padding-right: 5px;">
		<div class="col-sm-12">
			<table id="MointorGrid" data-toggle="table"
				data-query-params="queryParams" data-row-style="rowStyle"
				data-pagination="true" data-page-list="[10, 30, 50,  ALL]"
				data-side-pagination="server">
				<thead>
					<tr>
						<th data-field="weightname" class="text-nowrap text-left" data-halign="center" sortable>衡器/人员</th>
						<th data-field="errormsg" data-halign="center"
							class="text-nowrap text-left" sortable>内容</th>
						<th data-field="stacktrace" data-halign="center"
							class="text-nowrap text-left">计算机信息</th>
						<th data-field="createdate" data-halign="center"
							class="text-nowrap text-left" data-searchable="true">时间</th>
						
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
			$('#MointorGrid').bootstrapTable('refresh', {
				url : "<c:url value='/systemointor/queryMointorinfo.do'/>"
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
</html>