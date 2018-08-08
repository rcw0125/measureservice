<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="t" uri="http://www.talent.com/jsp/anyonelse"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.Date"%>
<t:html>
<head>
<jsp:include page="common.jsp" flush="true" />
<style type="text/css">
.select2-container--bootstrap .select2-selection {
	border-radius: 0px 4px 4px 0px;
}

.select2-container--bootstrap.input-sm .select2-selection--single, .input-group-sm .select2-container--bootstrap .select2-selection--single, .form-group-sm .select2-container--bootstrap .select2-selection--single {
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
<body class="container-fluid" style="padding-top: 15px;">
	<form id="queryForm">
		<div class="row">
			<div class="col-sm-12">
				<div class="form-group input-group input-group-sm">
					<span class="input-group-addon">分组类型</span> <select id="grouptype" name="grouptype" class="form-control select2" placeholder="必选" multiple="multiple">
						<option value="carno">车号</option>
						<option value="t.operatype">业务类型</option>
						<option value="materialname" selected="selected">货名</option>
						<option value="sourcename" selected="selected">供货</option>
						<option value="targetname" selected="selected">收货</option>
						<option value="grossweigh">衡器</option>
						<option value="taskcode">业务号</option>
						<option value="planid">计划号</option>
					</select>
					<input type="hidden" id="cartype" name="cartype" value="${param.cartype}" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-3">
				<div class="form-group input-group input-group-sm">
					<span class="input-group-addon">时间类型</span> <select id="selecttime" name="selecttime" class="form-control select2" placeholder="时间类型">
						<option value="t.suttletime">净重时间</option>
						<option value="t.grosstime">毛重时间</option>
						<option value="t.taretime">皮重时间</option>
						<option value="t.createdate">创建时间</option>
					</select>
				</div>
			</div>
			<div class="col-sm-3">
				<t:combobox id="materialname" name="materialname" url="/measure/queryMaterial.do" label="货&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名" require="false" alloptions="true" allowclear="true" pagination="true"/>
			</div>
			<div class="col-sm-3">
				<div class="form-group input-group input-group-sm">
					<span class="input-group-addon">车&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</span> <input type="text" name="carno" class="form-control" placeholder="车号">
				</div>
			</div>
			<div class="col-sm-3">
				<input name="tareweigh" id="tareweigh" type="hidden" class="form-control" value="">
				<t:combobox id="equcode" name="equname" url="/measure/queryEquipment.do" label="计量衡器" require="false" alloptions="true" allowclear="true" />
			</div>

		</div>
		<div class="row">
			<div class="col-sm-3">
				<div class='form-group input-group input-group-sm date'>
					<span class="input-group-addon">开始时间</span> <input type='text' class="form-control" placeholder="开始时间" id="begintime" name="begintime" value="<%=begintime%>" />
				</div>
			</div>
			<div class="col-sm-3">
				<t:combobox id="sourcenames" name="sourcename" url="/measure/queryCustomer.do" label="供&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;货" require="false" alloptions="true" allowclear="true" pagination="true" />
			</div>

			<div class="col-sm-3">
				<div class="form-group input-group input-group-sm">
					<span class="input-group-addon">业&nbsp;&nbsp;务&nbsp;号&nbsp;</span> <input type="text" name="taskcode" class="form-control" placeholder="业务号">
				</div>
			</div>
			<div class="col-sm-3">
				<div class="form-group input-group input-group-sm">
					<span class="input-group-addon">计&nbsp;&nbsp;划&nbsp;号</span> <input type="text" name="planid" class="form-control" placeholder="计划号">
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-3">
				<div class='form-group input-group input-group-sm date'>
					<span class="input-group-addon">结束时间</span> <input type='text' class="form-control" placeholder="结束时间" id="endtime" name="endtime" value="<%=endtime%>" />
				</div>
			</div>
			<div class="col-sm-3">
				<t:combobox id="targetnames" name="targetname" url="/measure/queryCustomer.do" label="收&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;货" require="false" alloptions="true" allowclear="true" pagination="true" />
			</div>
			<div class="col-sm-3">
				<t:combobox id="operatypes" name="operatype" url="/measure/queryOperatype.do?operatype=${operatype}" label="业务类型" require="false" alloptions="true" allowclear="true" />
			</div>
			<div class="col-sm-3">
				<div class="form-group input-group input-group-sm">
					<span class="input-group-addon" id="sizing-addon3">物资流向</span> <select name="materialflow" id="materialflow" value="${materialflow}" class="form-control select2" />
					<c:if test="${materialflow==1}">
						<option value="1">进厂</option>
					</c:if>
					<c:if test="${materialflow==0}">
						<option value="0">调拨</option>
					</c:if>
					<c:if test="${materialflow==2}">
						<option value="2">出厂</option>
					</c:if>
					<c:if test="${materialflow==null}">
						<option value="-1">全部</option>
						<option value="0">调拨</option>
						<option value="1">进厂</option>
						<option value="2">出厂</option>
					</c:if>
					</select>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group col-sm-3 btn-group-sm">
				<div class="btn-group-sm">
					<button id="searchMeasureSumBtn" type="button" class="btn btn-info" onclick="search()">
						<span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;查询
					</button>
					<button id="exportMeasureSumBtn" type="button" class="btn btn-danger" onclick="exportExcel()">
						<span class="fa fa-file-excel-o" aria-hidden="true"></span>&nbsp;Excel
					</button>
				</div>
			</div>
		</div>

	</form>
	<div class="row">
		<div class="col-sm-12">
			<table id="MeasureSumGrid" data-toggle="table" data-pagination="true" data-page-list="[10,30,50,All]" data-row-style="rowStyle" data-click-to-select="true" data-query-params="queryParams" data-show-footer="true" data-side-pagination="server">
				<thead>
					<tr>
						<th data-field="matchid" data-halign="center" data-visible="false"></th>
						<th data-field="carno" data-halign="center">车号</th>
						<th data-field="operatype" data-halign="center" data-visible="false">业务类型</th>
						<th data-field="c_operatype" data-halign="center">业务类型</th>
						<th data-field="planid" data-halign="center">计划号</th>
						<th data-field="taskcode" data-halign="center">业务号</th>
						<th data-field="materialname" data-halign="center">货名</th>
						<th data-field="sourcename" data-halign="center">供货</th>
						<th data-field="targetname" data-halign="center">收货</th>
						<th data-field="grossweigh" data-halign="center">衡器</th>
						<th data-field="carcount" data-halign="center">车数/辆</th>
						<th data-field="gross" data-halign="center">毛重/t</th>
						<th data-field="tare" data-halign="center">皮重/t</th>
						<th data-field="suttle" data-halign="center">净重/t</th>
						<th data-field="deduction" data-halign="center">扣重/t</th>
						<th data-field="suttleb" data-halign="center">扣后净重/t</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<script type="text/javascript">
		jQuery(document)
				.ready(
						function($) {
							search();
							$('.fixed-table-footer > table').remove();
							$('.fixed-table-footer')
									.addClass('container-fluid')
									.html(
											'<div class="row"><div class="col-sm-12 text-center" style="font-size:15px;font-weight:bold" >总车数/辆:<span style="font-size:15px" id="sumcarcountFooter" class="label label-default">0</span> &nbsp;&nbsp;总毛重/t:<span style="font-size:15px" id="sumgrossFooter" class="label label-warning">0.00</span> &nbsp;&nbsp;总皮重/t:<span style="font-size:15px" id="sumtareFooter" class="label label-primary">0.00</span> &nbsp;&nbsp;总扣重/t:<span style="font-size:15px" id="sumsuttlebFooter" class="label label-danger">0.00</span> &nbsp;&nbsp;总净重/t:<span style="font-size:15px" id="sumsuttleFooter" class="label label-success">0.00</span> &nbsp;&nbsp;总扣后净重/t:<span style="font-size:15px" id="sumsuttleFooter" class="label label-success">0.00</span></div></div>');
						});

		$('#MeasureSumGrid')
				.bootstrapTable(
						{
							onLoadSuccess : function(data) {
								$
										.ajax({
											type : "post",
											url : "<c:url value='/measureReport/querySumCount.do'/>",
											dataType : "json",
											data : $('#queryForm')
													.serializeJson(),
											success : function(data) {
												if (data != null) {
													if (data.data != null) {
														$(
																'.fixed-table-footer > table')
																.remove();
														$('.fixed-table-footer')
																.addClass(
																		'container-fluid')
																.html(
																		'<div class="row"><div class="col-sm-12 text-center" style="font-size:15px;font-weight:bold" >总车数/辆:<span style="font-size:15px" id="sumcarcountFooter" class="label label-default">'
																				+ data.data.carcount
																				+ '</span> &nbsp;&nbsp;总毛重/t:<span style="font-size:15px" id="sumgrossFooter" class="label label-warning">'
																				+ data.data.gross
																				+ '</span> &nbsp;&nbsp;总皮重/t:<span style="font-size:15px" id="sumtareFooter" class="label label-primary">'
																				+ data.data.tare
																				+ '</span> &nbsp;&nbsp;总净重/t:<span style="font-size:15px" id="sumsuttleFooter" class="label label-success">'
																				+ data.data.suttle
																				+ '</span> &nbsp;&nbsp;总扣重/t:<span style="font-size:15px" id="sumsuttlebFooter" class="label label-danger">'
																				+ data.data.deduction
																				+ '</span> &nbsp;&nbsp;总扣后净重/t:<span style="font-size:15px" id="sumsuttleFooter" class="label label-success">'
																				+ data.data.suttleb
																				+ '</span></div></div>');
													}
												}
											}
										});

							}

						});

		function queryParams(params) {
			return jQuery.extend({}, params, $('#queryForm').serializeJson());
		}

		function search() {
			var selectedGroupColumns = $("#grouptype").val() + ",";
			$("#grouptype option")
					.each(
							function() {
								var columnName = $(this).attr('value');
								if (selectedGroupColumns.indexOf(columnName
										+ ",") != -1) {
									if ('t.operatype' == columnName) {
										$('#MeasureSumGrid').bootstrapTable(
												'showColumn', 'c_operatype');
									} else {
										$('#MeasureSumGrid').bootstrapTable(
												'showColumn', columnName);
									}
								} else {
									if ('t.operatype' == columnName) {
										$('#MeasureSumGrid').bootstrapTable(
												'hideColumn', 'c_operatype');
									} else {
										$('#MeasureSumGrid').bootstrapTable(
												'hideColumn', columnName);
									}

								}
							});
			$('#MeasureSumGrid').bootstrapTable('refresh', {
				url : "<c:url value='/measureReport/queryMeasureSum.do'/>"
			});
		}

		function exportExcel() {
			remoteExportExcel('MeasureSumGrid', '', '汽运计量汇总',
					'queryForm');
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

		$('#grouptype').select2({
			tags : true,
			theme : "bootstrap"
		});
	</script>

</body>
</t:html>