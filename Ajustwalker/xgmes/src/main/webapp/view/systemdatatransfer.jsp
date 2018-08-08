<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
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
		<script type="text/javascript" src="<c:url value='/plugins/bootstrap-typeahead.js'/>"></script>
	</head>

<%
	SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd 00:00:00");
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
						<div class='form-group input-group input-group-sm date'>
							<span class="input-group-addon">开始时间</span> 
							<input type='text' class="form-control" placeholder="开始时间" id="begintime" name="begintime" value="<%=begintime%>" />
						</div>
					</div>
					<div class="col-sm-3">
						<div class='form-group input-group input-group-sm date'>
							<span class="input-group-addon">结束时间</span> 
							<input type='text' class="form-control" placeholder="结束时间" id="endtime" name="endtime" value="<%=endtime%>" />
						</div>
					</div>
					<div class=" col-sm-3 " >
					    <div class="form-group input-group  btn-group-sm">
						    <t:button text="查询" modulecode="ZhiKaGuanLi" id="query" btnclass="btn btn-info" iconclass="glyphicon glyphicon-search" onclick="queryinfo()"/>
						    <t:button text="数据迁移" modulecode="ZhiKaGuanLi" id="datatransfer" btnclass="btn btn-success" iconclass="glyphicon glyphicon-plus" onclick="datatransfers()" />
						    <t:button text="数据删除" modulecode="ZhiKaGuanLi" id="datadelete" btnclass="btn btn-warning" iconclass="glyphicon glyphicon-plus" onclick="datadeletes()" />
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<table id="MakecardInfoGrid" 
			          data-toggle="table"  
						data-row-style="rowStyle" 
						data-query-params="queryParams"
						data-pagination="true" 
						data-page-size="10"
						data-page-list="[10,40,ALL]" 
						data-mobile-responsive="true">
				<thead>
					<tr>
						<th data-field="matchid" data-halign="center"
							data-searchable="true" sortable>物流号</th>
						<th data-field="operaname" class="text-nowrap" data-halign="center"
							data-searchable="true" sortable>业务类型</th>
						<th data-field="operatype" data-visible="false" ></th>
						<th data-field="planid" data-halign="center"
							data-searchable="true"  class="text-nowrap"sortable>计划号</th>
						<th data-field="carno" data-halign="center" data-searchable="true"
							class="text-nowrap" >车号</th>
						<th data-field="materialname" data-halign="center"
							data-searchable="true" class="text-nowrap">货名</th>
						<th data-field="sourcename" data-halign="center"
							data-searchable="true" class="text-nowrap">供货</th>
						<th data-field="targetname" data-halign="center"
							data-searchable="true" class="text-nowrap">收货</th>	
						<th data-field="suttleapp" data-halign="center"
							data-searchable="true" class="text-nowrap">重量/t</th>
						<th data-field="snumber" data-halign="center"
							data-searchable="true" class="text-nowrap">数量</th>
						<th data-field="createdate" data-halign="center"
							data-searchable="true" class="text-nowrap">添加时间</th>
						<th data-field="usermemo" data-halign="center" data-searchable="true"
							class="text-nowrap">备注</th>
						
					</tr>
				</thead>
			</table>

		</div>
	</div>
	
	<script type="text/javascript">
		$(function() {
			queryinfo();
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
			$('#MakecardInfoGrid').bootstrapTable('refresh', {
				url : "<c:url value='/application/queryPage.do'/>"
			});
		}

	/* 数据迁移 */	
	  function datatransfers() {
			saveFormData('queryform','<c:url value="/exception/datatransfer.do"/>', function(data) {
				if (data.success) {
					successbox(data.msg);
				} else {
					errorbox(data.msg);
				}
			});
		}
	  /* 数据删除 */
	  function datadeletes() {
			saveFormData('queryform','<c:url value="/exception/datadelete.do"/>', function(data) {
				if (data.success) {
					successbox(data.msg);
				} else {
					errorbox(data.msg);
				}
			});
		}
	</script>
</body>
</t:html>