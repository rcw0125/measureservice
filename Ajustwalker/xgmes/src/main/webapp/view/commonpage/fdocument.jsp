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
	<script type="text/javascript" src="<c:url value='/plugins/bootstrap-typeahead.js'/>"></script>
</head>
<%
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
	SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
	Date now = new Date();
	Calendar c = Calendar.getInstance();
	String endtime = dateFormat1.format(c.getTime());//结束时间
	c.add(Calendar.MONTH, -3);// 月份减1  
	now = c.getTime();		
	String begintime = dateFormat.format(now);//开始时间

%>
<body class="container-fluid">
<div class="row" style="padding-top: 5px">
		<div class="col-sm-12">
			<form id="queryform">	
			    <input type="hidden" name="fdocumentno" value="${fdocumentno}">		
			    <input type="hidden" id="types" name="types" value="${types}">		
			    	
				<div class="row">
				<%-- <div class="col-sm-3">
						<div class='form-group input-group input-group-sm date'>
							<span class="input-group-addon">开始时间</span> <input type='text' class="form-control" placeholder="开始时间" id="begintime" name="begintime" value="<%=begintime%>" />
						</div>
					</div> --%>
				<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">供　货</span> <input
								name="sourcename" type="text" class="form-control" value="">
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">单据号</span> <input
								name="matchid" type="text" class="form-control" value="">
						</div>
					</div>
					
				</div>
				<div class="row">
				    <%-- <div class="col-sm-3">
						<div class='form-group input-group input-group-sm date'>
							<span class="input-group-addon">结束时间</span> <input type='text' class="form-control" placeholder="结束时间" id="endtime" name="endtime" value="<%=endtime%>" />
						</div>
					</div> --%>
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">收　货</span> <input
								name="targetname" type="text" class="form-control" value="">
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">制单人</span> <input
								name="creator" type="text" class="form-control" value="">
						</div>
					</div>
					<div class="col-sm-3">
					    <div class="form-group input-group  btn-group-sm">
							<button id="query" type="button" class="btn btn-info"
								onclick="queryinfo()">
								<span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;查询
							</button>
							<button type="button" class="btn btn-warning" data-dismiss="modal"
					          onclick="exit()">
					           <span class="fa fa-times" aria-hidden="true"></span>&nbsp;关闭    
					        </button>
						</div>
					</div>
				</div>
			</form>
			<div class="row">
				<div class="col-sm-12">
					<table id="FdocumentGrid" data-toggle="table"
						data-row-style="rowStyle" data-query-params="queryParams"
						data-pagination="true" 
						data-page-size="10"
						data-page-list="[10,40,ALL]" 
						data-mobile-responsive="true" data-click-to-select="true">
						<thead>
							<tr>
								<th data-field="state" data-radio="true"></th>
								<th data-field="matchid" data-halign="center"
									data-align="center" data-searchable="true" class="text-nowrap  text-center">单据号</th>
								<%-- <th data-field="documentcode" class="text-nowrap" >单据类型</th>	 --%>
								<th data-field="sourcename" data-halign="center"
									data-searchable="true" class="text-nowrap  text-center">供货</th>
								<th data-field="targetname" data-halign="center"
									data-align="center" data-searchable="true" class="text-nowrap text-center">收货</th>
								<th data-field="inoutdate" class="text-nowrap  text-center" >到货日期</th>
								<th data-field="creator" data-halign="center"
							        data-searchable="true" class="text-nowrap text-center">添加人</th>	
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		jQuery(document).ready(function($) {
			queryinfo();
		});
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
		function queryParams(params) {
			return jQuery.extend({}, params, $('#queryform').serializeJson());
		}

		function queryinfo() {
			$('#FdocumentGrid').bootstrapTable('refresh', {
				url : "<c:url value='/application/queryFdocumentinfo.do'/>"
			});
		}
		//双击返回信息
		$("#FdocumentGrid").bootstrapTable({onDblClickRow:function(row, $element, field){	
	
			parent.takeBackPlanid(row.matchid,$("#types").val());
			parent.closePlanid();
		}});
		function exit() {
			parent.closePlanid();
		}

	</script>
</body>
</t:html>