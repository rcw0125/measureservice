<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="t" uri="http://www.talent.com/jsp/anyonelse"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.Date"%>
<t:html>
<head>
<jsp:include page="/view/common.jsp" flush="true" />
<style type="text/css">
.select2-container--bootstrap .select2-selection {
	border-radius: 0px 4px 4px 0px;
}

.select2-container--bootstrap.input-sm .select2-selection--single,
	.input-group-sm .select2-container--bootstrap .select2-selection--single,
	.form-group-sm .select2-container--bootstrap .select2-selection--single
	{
	border-radius: 0px 4px 4px 0px;
}
</style>
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
<body class="container-fluid">
<div class="row">
		<div class="col-sm-12">	
		    <form id="queryform">
		    <input type="hidden" id="operatype" name="operatype" value="${param.operatype}" />
				<div class="row" >
					<%-- <div class="col-sm-3">
						<div class='form-group input-group input-group-sm date'>
							<span class="input-group-addon">开始时间</span> 
							<input type='text'class="form-control" placeholder="开始时间"  id="begintime" 	name="begintime" value="<%=begintime%>" />
						</div>
					</div> --%>
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">车　　号</span> 
							<input type="text" class="form-control" placeholder="车号" id="carno" name="carno" value="${param.carno}" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">供　　货</span> 
							<input type="text" class="form-control" placeholder="供货" id="sourcename" name="sourcename" aria-describedby="sizing-addon3">
						</div>
					</div>
					
				</div>
				<div class="row" >
					<%-- <div class="col-sm-3">
						<div class='form-group input-group input-group-sm date'>
							<span class="input-group-addon">结束时间</span> 
							<input type='text' class="form-control" placeholder=" 结束时间" id="endtime" name="endtime" value="<%=endtime%>" />
						</div>
					</div> --%>
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">物　　料</span> 
							<input type="text" class="form-control" placeholder="物料" id="materialname" name="materialname" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">收　　货</span> 
							<input type="text" class="form-control" placeholder="收货" id="targetname" name="targetname" aria-describedby="sizing-addon3" >
						</div>
					</div>
					<div class="col-sm-6 btn-group-sm" >
						<button id="query" type="button" class="btn btn-info" onclick="queryinfo()">
							<span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;查询
						</button>						
					</div>
				</div>
			</form>
			<table id="PlaninfoGrid" data-toggle="table"
				data-pagination="true" 
				data-page-list="[10,40]"
				data-row-style="rowStyle" 
				data-query-params="queryParams"
				data-page-size="10"
				data-click-to-select="true">
				<thead>
					<tr>
						<th data-field="state" data-radio="true"></th>
						<th data-field="planid" data-halign="center" data-align="center"
							data-searchable="true" class="text-nowrap">计划号</th>
						<th data-field="carno" data-halign="center" data-align="center"
							data-searchable="true" class="text-nowrap">车号</th>
						<th data-field="materialname" data-halign="center"
							data-searchable="true" class="text-nowrap">货名</th>
						<th data-field="materialcode" data-halign="center"
							data-align="center" data-searchable="true" data-visible="false" class="text-nowrap">编码</th>
						<th data-field="sourcename" data-halign="center"
							data-searchable="true" class="text-nowrap">供货</th>
						<th data-field="sourcecode" data-halign="center"
							data-align="center" data-searchable="true" data-visible="false" class="text-nowrap">供货编码</th>
						<th data-field="targetcode" data-halign="center" data-visible="false"
							data-searchable="true" class="text-nowrap">收货编码</th>
						<th data-field="targetname" data-halign="center"
							data-align="center" data-searchable="true" class="text-nowrap">收货</th>
						<th data-field="documentcode" data-halign="center" data-visible="false"
							data-align="center" data-searchable="true" class="text-nowrap">单据编码</th>
						<th data-field="routeid" data-halign="center" data-visible="false"
							data-align="center" data-searchable="true" class="text-nowrap" >路线ID</th>
						<th data-field="auditlevel" data-halign="center" data-visible="false"
							data-align="center" data-searchable="true" class="text-nowrap">审核等级</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<script type="text/javascript">
		jQuery(document).ready(function($) {
			loadGridData('PlaninfoGrid',"<c:url value='/bcommon/queryPlaninfo.do'/>");
		});
		 function queryParams(params) {
			return jQuery.extend({}, params, $('#queryform').serializeJson());
		} 

		 function queryinfo() {
				$('#PlaninfoGrid').bootstrapTable('refresh', {url : "<c:url value='/bcommon/queryPlaninfo.do'/>"});
			}
   </script>
</body>
</t:html>