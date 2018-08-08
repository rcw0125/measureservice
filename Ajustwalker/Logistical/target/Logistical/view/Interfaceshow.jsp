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
				<input type="hidden" id="types"  name="types" value="0">
				<div class="row">
					<div class="col-sm-3">
						<div class='form-group input-group input-group-sm date'>
							<span class="input-group-addon">开始时间</span> 
							<input type='text' class="form-control" placeholder="开始时间" id="begintime" name="begintime" value="<%=begintime%>" />
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">货　　名</span> 
							<input type="text" class="form-control" placeholder="货名" id="materialname" name="materialname" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">供　　货</span>
							<input type="text" class="form-control" placeholder="供货" id="sourcename" name="sourcename" aria-describedby="sizing-addon3">
						</div>
					</div>	
					<div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">车　　号</span>
							<input type="text" class="form-control" placeholder="车号" id="carno" name="carno" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-2">
							<div class="form-group input-group input-group-sm">
								<span class="input-group-addon" id="sizing-addon3">单据类型</span>
							    <select id="sysmemo" name="sysmemo" class="form-control select2">
									<option value="">全部</option>
									<option value="1">到货单</option>
									<option value="2">发运单</option>
								</select> 
							</div>
						</div>
				</div>
				<div class="row" >
					<div class="col-sm-3">
						<div class='form-group input-group input-group-sm date'>
							<span class="input-group-addon">结束时间</span> 
							<input type='text' class="form-control" placeholder="结束时间" id="endtime" name="endtime" value="<%=endtime%>" />
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">单&nbsp;&nbsp;据&nbsp;&nbsp;号 </span> 
							<input type="text" class="form-control" placeholder="计划号" id="planid" name="planid" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">收　　货</span>
							<input type="text" class="form-control" placeholder="收货" id="targetname" name="targetname" aria-describedby="sizing-addon3">
						</div>
					</div>	
					<div class="col-sm-2">
							<div class="form-group input-group input-group-sm">
								<span class="input-group-addon" id="sizing-addon3">是否使用</span>
							    <select id="isoruse" name="isoruse" class="form-control select2">
									<option value="">全部</option>
									<option value="0">未使用</option>
									<option value="1">已使用</option>
								</select> 
							</div>
						</div>
					<div class=" col-sm-2" >
					    <div class="form-group input-group  btn-group-sm">
						    <t:button text="查询" modulecode="ZhiKaGuanLi" id="query" btnclass="btn btn-info" iconclass="glyphicon glyphicon-search" onclick="queryinfo()"/>&nbsp;
						    </div>
					</div>
							
				</div>
			</form>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<table id="InterfaceInfoGrid" 
			          data-toggle="table"  
						data-row-style=rowStyle" 
						data-query-params="queryParams"
						data-pagination="true" 
						data-page-size="10"
						data-page-list="[10,40,ALL]" 
						data-mobile-responsive="true">
				<thead>
					<tr>
						<th data-field="planid" data-halign="center"
							data-searchable="true"  class="text-nowrap" >单据号</th>
						<th data-field="saleitemid" data-visible="false"
							>行号</th>
						<th data-field="carno" data-halign="center" data-searchable="true"
							class="text-nowrap" >车号</th>
						<th data-field="status" data-halign="center" data-searchable="true"
							class="text-nowrap" >状态</th>
						<th data-field="materialcode" data-halign="center"
							data-searchable="true" class="text-nowrap">货名编码</th>	
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
						<th data-field="measureunit" data-halign="center"
							data-searchable="true" class="text-nowrap">计量单位</th>
						<th data-field="arrivetime" data-halign="center"
							data-searchable="true" class="text-nowrap">到货时间</th>
						<th data-field="createdate" data-halign="center"
							data-searchable="true" class="text-nowrap">下载时间</th>
						<th data-field="sysmemo" data-halign="center"
							data-searchable="true" class="text-nowrap">单据类型</th>	
						<th data-field="usermemo" data-halign="center" data-searchable="true"
							class="text-nowrap">备注</th>
					</tr>
				</thead>
			</table>
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
		$('#InterfaceInfoGrid').bootstrapTable('refresh', {
			url : "<c:url value='/interface/queryInterfaceList.do'/>"
		});
	}
	$('#InterfaceInfoGrid').bootstrapTable({onDblClickRow : function(row, $element) {
		layer.open({
					type : 2,
					title : '信息详情',
					maxmin : true, //开启最大化最小化按钮
					area : [ '90%', '90%' ],
					content : '<c:url value="/bcommon/showOtherDetail.do?type=1&ids="/>'+ row.saleitemid //注意，如果str是object，那么需要字符拼接。
		});
	}
});
		
	</script>
</body>
</t:html>