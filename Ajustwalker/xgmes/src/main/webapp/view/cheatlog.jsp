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
<body class="container-fluid" style="padding-top: 15px;">

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
					<div class="col-sm-3">
						<input name="equipmentname" id="equipmentname" type="hidden" class="form-control" value=""> 
						<t:combobox id="equipmentname" name="equipmentname"  url="/bcommon/queryEqunameinfo.do" label="衡器名称" require="false" alloptions="true" allowclear="true" />	
					</div>
					<div class="col-sm-3">
						<div class="form-group btn-group-sm">
							<button id="query" type="button" class="btn btn-info enterkeyaction" onclick="queryinfo()">
								<span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;查询
							</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>

	<div class="row">
		<div class="col-sm-12">
			<table id="CheatlogGrid" data-toggle="table" data-row-style="rowStyle" data-query-params="queryParams" data-pagination="true" data-page-size="10" data-page-list="[10,40,ALL]" data-mobile-responsive="true">
				<thead>
					<tr>
						<th data-field="id" data-visible="false"></th>
						<th data-field="equipmentname" data-halign="center" data-searchable="true" class="text-center">衡器名称</th>
						<th data-field="msg" data-halign="center" data-searchable="true" class="text-center">记录信息</th>
						<th data-field="createdate" data-halign="center" data-searchable="true" class="text-nowrap text-center">记录时间</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	
		<script type="text/javascript">
		jQuery(document)
				.ready(
						function($) {
							queryinfo();
						});
		
		function queryinfo() {
			$('#CheatlogGrid').bootstrapTable('refresh', {
				url : "<c:url value='/cheatlog/list'/>"
			});
		}
		
		function queryParams(params) {
			return jQuery.extend({}, params, $('#queryform').serializeJson());
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