<%-- <%@ page contentType="text/html;charset=UTF-8" language="java"
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
<body class="section container-fluid">
<div class="row-fluid" id="MaterialinfoGridWindow">
		<div class="col-sm-12">	
			<table id="MaterialinfoGrid" data-toggle="table"
				data-pagination="true" 
				data-page-list="[10,40]"
				data-row-style="rowStyle" 
				data-search="true"
				data-show-refresh="true"
				data-page-size="10"
				data-click-to-select="true">
				<thead>
					<tr > 
					   <th data-field="state" data-radio="true" ></th>
						<th data-field="materialcode" data-halign="center" data-align="center"
							data-searchable="true">编码</th>
						<th data-field="materialname" data-halign="center"
							data-searchable="true">货名</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<script type="text/javascript">
		jQuery(document).ready(function($) {
			loadGridData('MaterialinfoGrid',"<c:url value='/bcommon/queryMateril.do'/>");
		});
	</script>
</body>
</t:html> --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>


<div class="modal fade" id="MaterialinfoGridWindow" tabindex="-1" role="dialog" aria-labelledby="MaterialinfoGridWindowLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">选择物料</h4>
			</div>
			<div class="modal-body">
				<form id="Materialinfoform">
					<div class="row">
					  
						<div class="col-sm-3">
							<div class="form-group input-group input-group-sm">
								<span class="input-group-addon" id="sizing-addon3">编码</span> <input type="text" class="form-control" placeholder="编码" id="materialcode" name="materialcode" aria-describedby="sizing-addon3">
							</div>
						</div>
						<div class="col-sm-3">
							<div class="form-group input-group input-group-sm">
								<span class="input-group-addon" id="sizing-addon3">货名</span> <input type="text" class="form-control" placeholder="货名" id="materialname" name="materialname" aria-describedby="sizing-addon3">
							</div>
						</div>
					
						<div class="col-sm-3 btn-group-sm">
							<button id="query" type="button" class="btn btn-info" onclick="queryinfolist()">
							  <span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;查询
						    </button>
						</div>
						
					</div>
				</form>
				<table id="MaterialinfoGrid" data-toggle="table" data-pagination="true" data-page-list="[8,40]" data-row-style="rowStyle" data-query-params="queryPlanParams" data-page-size="10" data-click-to-select="true">
					<thead>
						<tr>
							<th data-field="state" data-radio="true" ></th>
							<th data-field="materialcode" data-halign="center"
								data-align="center" data-searchable="true">编码</th>
							<th data-field="materialname" data-halign="center"
								data-searchable="true">货名</th>
						</tr>
					</thead>
				</table>
			</div>
			<div class="modal-footer  btn-group-sm">
				<button type="button" class="btn btn-success" data-dismiss="modal">确认</button>
			    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">

    //获取查询条件
	function queryPlanParams(params) {
		return jQuery.extend({}, params, $('#Materialinfoform').serializeJson());
	}
    //查询
	function queryinfolist() {
/* 		$('#PlaninfoGrid').bootstrapTable('refresh', {
			url : "<c:url value='/bcommon/queryPlaninfo.do'/>"
		}); */
		loadGridData('MaterialinfoGrid',"<c:url value='/bcommon/queryMateril.do'/>");
	}
	//双击返回信息
	$("#MaterialinfoGrid").bootstrapTable({onDblClickRow:function(row, $element, field){	
		$('#MaterialinfoGridWindow').modal('hide');
	}});
	//页面显示后加载
	$('#MaterialinfoGridWindow').on('shown.bs.modal', function (e) {
		queryinfolist();
	 })
</script>



