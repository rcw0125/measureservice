<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
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
	<style type="text/css">
		.select2-container--bootstrap .select2-selection{
			border-radius: 0px 4px 4px 0px;
		}
		.select2-container--bootstrap.input-sm .select2-selection--single, .input-group-sm .select2-container--bootstrap .select2-selection--single, .form-group-sm .select2-container--bootstrap .select2-selection--single{
			border-radius: 0px 4px 4px 0px; 
		}
	</style>
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
			<div class="col-sm-3">
				<div class='input-group input-group-sm date'>

					<span class="input-group-addon" id="sizing-addon3">类型</span> <select
						name="flag" id="flag" class="form-control select2" />
					<option value="1">钢后</option>
					<option value="2">铁前</option>
					</select>
				</div>
			</div>
			<div class="col-sm-3 btn-group-sm" style="padding-top: 5px;">
				<button id="query" type="button" class="btn btn-info" onclick="queryinfo()">
					<span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;查询
				</button>
				<button id="exportExcel" type="button" class="btn btn-danger"
					onclick="excel()">
					<span class="glyphicon glyphicon-floppy-save" aria-hidden="true"></span>&nbsp;Excel导出
				</button>
				<button id="createunitweight" type="button" class="btn btn-warning"
					onclick="createdata()">
					<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>&nbsp;数据生成
				</button>
			</div>
		</div>
	</form>
	<div class="row" style="padding-top: 5px; padding-left: 5px; padding-right: 5px;">
		<div class="col-sm-12">
			<table id="unitweightinfoGrid" data-toggle="table" data-query-params="queryParams" data-row-style="rowStyle">
				<thead>
					<tr>
						<th data-field="productline" class="text-nowrap text-left" data-halign="center" sortable>产线</th>
						<th data-field="unitname" data-halign="center" class="text-nowrap text-left" sortable>单位</th>
						<th data-field="classes" data-halign="center" class="text-nowrap text-center">类型</th>
						<th data-field="materialname" data-halign="center" class="text-nowrap text-left" data-searchable="true">物料</th>
						<th data-field="daysuttle" data-halign="center" class="text-nowrap text-right" class="text-nowrap">当日重量/t</th>
						<th data-field="monthsuttle" data-halign="center" class="text-nowrap text-right" class="text-nowrap">当月累计/t</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>

	<script type="text/javascript">
		jQuery(document).ready(function($) {
			queryinfo();
		});
		
		function queryinfo() {
			$('#unitweightinfoGrid').bootstrapTable({rowStyle:rowStyle,queryParams:queryParams}).bootstrapTable('removeAll').bootstrapTable('refresh',{url:"<c:url value='/StatisticsReport/queryunitweight.do'/>"});
		}
		
		function resizeCallBack(){
			$('#unitweightinfoGrid').mergeCell({cols:[0,1,2]});
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
			$("#unitweightinfoGrid").table2excel({
				exclude: ".noExl",
				name: "Excel Document Name",
				filename: "钢后累计报表",
				fileext: ".xls",
				exclude_img: true,
				exclude_links: true,
				exclude_inputs: true
			});
		}
	
		function createdata() {
			var datetime = $("#datetime").val();
			$.messager.confirm("请确认", "确认生成本日累计数据吗？", function() {
				startWaiting();
				$.ajax({
					type : "post",
					url : '<c:url value="/StatisticsReport/createunitweight.do"/>',
					dataType : "json",
					data : {
						datetime : datetime
					},
					success : function(data) {
						stopWaiting();
						if (data.success == true) {
							toastMessage("success", "提示", "操作成功！");
							$('#unitweightinfoGrid').bootstrapTable('refresh');
						} else {
							toastMessage("error", "错误", "操作失败！");

						}
					}
				});
			});
		}
	</script>
</html>