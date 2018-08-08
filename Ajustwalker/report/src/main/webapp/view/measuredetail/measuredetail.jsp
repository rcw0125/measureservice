<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="t" uri="http://www.talent.com/jsp/anyonelse"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Calendar"%>
<t:html>
<head>
<jsp:include page="../common.jsp" flush="true" />
<%
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Calendar c = Calendar.getInstance();
	String begintime = dateFormat.format(c.getTime()) + " 00:00:00";//开始时间
	String endtime = dateFormat.format(c.getTime()) + " 23:59:59";//结束时间
	SimpleDateFormat dformat2 = new SimpleDateFormat("yyyyMMdd");
	String yearm = dformat2.format(c.getTime());//报表名
%>
</head>
<body class="container-fluid" style="padding-top: 15px;">
	<form id="queryform">
		<div class="row">
			<div class="col-sm-3">
				<div class="form-group input-group input-group-sm">
					<span class="input-group-addon" id="sizing-addon3">时间类型</span> <select name="selecttime" id="selecttime" class="form-control select2" />
					<option value="t.grosstime">毛重时间</option>
					<option value="t.suttletime">净重时间</option>
					<option value="t.taretime">皮重时间</option>
					</select> <input type="hidden" id="operatypes" name="operatypes" value="${opeatype}" /> <input type="hidden" id="operatype" name="operatype" value="${opeatype}" /> <input type="hidden" id="titlename" name="titlename" value="${titlename}" /> <input type="hidden" id="reportname" name="reportname" value="<%=yearm%>" /> <input type="hidden" id="matchids" name="matchids" value="" />
				</div>
			</div>
			<div class="col-sm-3">
				<div class="form-group input-group input-group-sm date">
					<span class="input-group-addon">开始时间</span> <input type="text" class="form-control" placeholder="开始时间" id="begintime" name="begintime" value="<%=begintime%>" />
				</div>
			</div>
			<div class="col-sm-3">
				<div class="form-group input-group input-group-sm date">
					<span class="input-group-addon">结束时间</span> <input type="text" class="form-control" placeholder="结束时间" id="endtime" name="endtime" value="<%=endtime%>" />
				</div>
			</div>
			<div class="col-sm-3">
				<div class="form-group input-group input-group-sm">
					<span class="input-group-addon" id="sizing-addon3">单&nbsp;&nbsp;据&nbsp;&nbsp;号</span> <input type="text" class="form-control" placeholder="单据号" id="planid" name="planid" aria-describedby="sizing-addon3">
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-3">
				<t:combobox id="materialname" name="materialname" url="/measure/queryMaterial.do" label="存货名称" require="false" alloptions="true" allowclear="true" pagination="true" />
			</div>
			<div class="col-sm-3">
				<t:combobox id="sourcenames" name="sourcename" url="/measure/queryCustomer.do" label="供&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;货" require="false" alloptions="true" allowclear="true" pagination="true" />
			</div>

			<div class="col-sm-3">
				<t:combobox id="targetnames" name="targetname" url="/measure/queryCustomer.do" label="收&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;货" require="false" alloptions="true" allowclear="true" pagination="true" />
			</div>

			<div class="col-sm-3">
				<div class="form-group input-group input-group-sm">
					<span class="input-group-addon" id="sizing-addon3">打印状态</span> <select name="printflag" id="printflag" class="form-control select2" />
					<option value="3">全部</option>
					<option value="0">未打印</option>
					<option value="1">已打印</option>
					</select>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-3">
				<div class="form-group input-group input-group-sm">
					<span class="input-group-addon" id="sizing-addon3">存货编码</span> <input type="text" class="form-control" placeholder="存货名称" id="materialcode" name="materialcode" aria-describedby="sizing-addon3">
				</div>
			</div>
			<div class="col-sm-3">
				<div class="form-group input-group input-group-sm">
					<span class="input-group-addon" id="sizing-addon3">车&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</span> <input type="text" class="form-control" placeholder="车号" id="carno" name="carno" aria-describedby="sizing-addon3">
				</div>
			</div>
			<div class="col-sm-3">
				<div class="form-group input-group input-group-sm">
					<span class="input-group-addon" id="sizing-addon3">磅&nbsp;&nbsp;单&nbsp;&nbsp;号</span> <input type="text" class="form-control" placeholder="磅单号" id="matchid" name="matchid" aria-describedby="sizing-addon3">
				</div>
			</div>
			<div class="col-sm-6 btn-group-sm">
				<button id="query" type="button" class="btn btn-info" onclick="queryinfo()">
					<span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;查询
				</button>
				<button id="delResourceBtn" type="button" class="btn btn-success" onclick="print(0)">
					<span class="glyphicon glyphicon-print" aria-hidden="true"></span>&nbsp;打印
				</button>
				<button id="specResourceBtn" type="button" class="btn btn-danger" onclick="print(1)">
					<span class="glyphicon glyphicon-print" aria-hidden="true"></span>&nbsp;规格打印
				</button>
				<%if("10".equals(request.getParameter("opeatype"))){ %>
					<button id="specResourceBtn" type="button" class="btn btn-danger" onclick="print(2)">
						<span class="glyphicon glyphicon-print" aria-hidden="true"></span>&nbsp;周转打印
					</button>
				<%} %>
			</div>
		</div>
	</form>
	<div class="row" style="padding-top: 5px;">
		<div class="col-sm-12">
			<table id="MeasureDetailGrid" data-toggle="table" data-query-params="queryParams" data-row-style="rowStyle" data-pagination="true" data-show-footer="true" data-page-size="10" data-page-list="[10, 20, 40, 80, ALL]" data-side-pagination="server" data-sort-name="suttletime" data-sort-order="desc">
				<thead>
					<tr>
						<th data-field="state" data-checkbox="true"></th>
						<th data-field="matchid" data-halign="center" sortable>磅单号</th>
						<th data-field="carno" data-sortable="true" data-halign="center" class="text-nowrap">车号</th>
						<th data-field="planid" data-sortable="true" data-halign="center" class="text-nowrap">单据号</th>
						<th data-field="materialcode" data-halign="center" class="text-nowrap">存货编号</th>
						<th data-field="materialname" data-halign="center" class="text-nowrap">存货名称</th>
						<th data-field="materialspec" data-halign="center" class="text-nowrap">规格型号</th>
						<c:if test="${opeatype!=10}">
						<th data-field="ks" data-halign="center" class="text-nowrap">客商</th>
						</c:if>
						<c:if test="${opeatype==10}">
						<th data-field="sourcename" data-halign="center">供货</th>
						<th data-field="targetname" data-halign="center">收货</th>
						</c:if>
						<th data-field="gross" data-halign="center" class="text-right">毛重/t</th>
						<th data-field="tare" data-halign="center" class="text-right">皮重/t</th>
						<th data-field="suttle" data-halign="center" class="text-right">净重/t</th>
						<th data-field="grosstime" data-sortable="true" data-halign="center" class="text-nowrap">毛重时间</th>
						<th data-field="taretime" data-sortable="true" data-halign="center" class="text-nowrap">皮重时间</th>
						<th data-field="grossoperaname" data-halign="center">毛重计量员</th>
						<th data-field="tareoperaname" data-halign="center">皮重计量员</th>
						<th data-field="printcount" data-halign="center">打印次数</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<script type="text/javascript">
		jQuery(document).ready(function($) {
			queryinfo();
			$('.fixed-table-footer > table').remove();
			$('.fixed-table-footer')
					.addClass('container-fluid')
					.html(
							'<div class="row"><div class="col-sm-12 text-center" style="font-size:15px;font-weight:bold" >总毛重/t:<span style="font-size:15px" id="sumgrossFooter" class="label label-warning">0.00</span> &nbsp;&nbsp;总皮重/t:<span style="font-size:15px" id="sumtareFooter" class="label label-primary">0.00</span> &nbsp;&nbsp;总净重/t:<span style="font-size:15px" id="sumsuttleFooter" class="label label-success">0.00</span></div></div>');
		
});
		
		$('#MeasureDetailGrid')
		.bootstrapTable(
				{
					onLoadSuccess : function(data) {
						$
								.ajax({
									type : "post",
									url : "<c:url value='/measuredetail/queryCount.do'/>",
									dataType : "json",
									data : $('#queryform')
											.serializeJson(),
									success : function(data) {
										if (data != null) {
											if (data.data != null) {
												$(
														'.fixed-table-footer > table')
														.remove();
												$(
														'.fixed-table-footer')
														.addClass(
																'container-fluid')
														.html(
																'<div class="row"><div class="col-sm-12 text-center" style="font-size:15px;font-weight:bold" >总毛重/t:<span style="font-size:15px" id="sumgrossFooter" class="label label-warning">'
																		+ data.data.gross
																		+ '</span> &nbsp;&nbsp;总皮重/t:<span style="font-size:15px" id="sumtareFooter" class="label label-primary">'
																		+ data.data.tare
																		+ '</span> &nbsp;&nbsp;总净重/t:<span style="font-size:15px" id="sumsuttleFooter" class="label label-success">'
																		+ data.data.suttle
																		+ '</span></div></div>');
											}
										}
									}
								});

					}
				});
		
		function queryParams(params) {
			return jQuery.extend({}, params, $('#queryform').serializeJson());
		}

		function queryinfo() {
			$('#MeasureDetailGrid').bootstrapTable('refresh', {
				url : "<c:url value='/measuredetail/queryMeasureDetail.do'/>"
			});
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

		function print(spec) {
			var url_v = '';
			if (0 == spec) {
				url_v = '<c:url value="/measuredetail/printMeasureDetail.do"/>';
			}else if (1 == spec) {
				url_v = '<c:url value="/measuredetail/printMeasureDetailSpec.do"/>';
			} else {
				url_v = '<c:url value="/measuredetail/printMeasureDetailZZ.do"/>';
			}
			startwaiting();
			var gridData = $('#MeasureDetailGrid').bootstrapTable(
					'getSelections');
			if (gridData.length > 0) {
				var matchids = '';
				for (var i = 0; i < gridData.length; i++) {
					if (i == 0) {
						matchids = gridData[i].matchid;
					} else {
						matchids = matchids + "," + gridData[i].matchid;
					}
				}
				$('#matchids').val(matchids);
			}
			$.ajax({
				type : "post",
				url : url_v,
				dataType : "json",
				data : $('#queryform').serializeJson(),
				success : function(data) {
					if (data.success) {
						layer.open({
							type : 2,
							title : '${titlename}打印',
							maxmin : true, //开启最大化最小化按钮
							area : [ '100%', '100%' ],
							content : "http://192.168.2.42:5080/pdfdownload/"
									  //"http://localhost:9080/pdfdownload/"
									+ data.msg
						});
					} else {
						errorbox(data.msg);
					}
				},
				complete : function(xhr, ts) {
					stopwaiting();
				}
			});
		}
	</script>
</body>
</t:html>