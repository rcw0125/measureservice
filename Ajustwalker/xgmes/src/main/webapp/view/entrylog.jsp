<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="t" uri="http://www.talent.com/jsp/anyonelse"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.Date"%>
<t:html>
<head>
<jsp:include page="common.jsp" flush="true" />
<script type="text/javascript" src="<c:url value='/plugins/bootstrap-typeahead.js'/>"></script>
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
<body class="container-fluid" style="padding-top: 15px">
	<div class="row">
		<div class="col-sm-12">
			<form id="queryform">
				<div class="row">
					<div class="col-sm-3">
						<div class='form-group input-group input-group-sm date'>
							<span class="input-group-addon">开始时间</span> <input type='text' class="form-control" placeholder="开始时间" id="begintime" name="begintime" value="<%=begintime%>" />
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon">卡&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</span> <input type="text" class="form-control" placeholder="卡号" name="icid" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型</span> <select id="entrytype" name="entrytype" class="form-control select2">
								<option value="">全部</option>
								<option value="1">进厂</option>
								<option value="2">出厂</option>
							</select>
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态</span> <select id="validflag" name="validflag" class="form-control select2">
								<option value="">全部</option>
								<option value="2">进厂未出厂</option>
								<option value="8">出厂</option>
							</select>
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">作&nbsp;&nbsp;业&nbsp;&nbsp;点</span> <select id="gatename" name="gatename" class="form-control select2">
								<option value="">全部</option>
								<option value="北门东">北门东</option>
								<option value="北门西">北门西</option>
								<option value="东南门">东南门</option>
								<option value="钢材市场门">钢市门</option>
								<option value="钢市二道门">钢市二道门</option>
								<option value="焦化二道门">焦化二道门</option>
								<option value="正门二道门">正门二道门</option>
							</select>
						</div>
					</div>
					<%-- <div class="col-sm-2">
						<t:combobox id="gatename" name="gatename"  url="/bcommon/queryGatename.do" label="大门 " require="false" alloptions="true" allowclear="true"/>
					    </div> --%>
				</div>
				<div class="row">
					<div class="col-sm-3">
						<div class='form-group input-group input-group-sm date'>
							<span class="input-group-addon">结束时间</span> <input type='text' class="form-control" placeholder="结束时间" id="endtime" name="endtime" value="<%=endtime%>" />
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">车&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</span> <input type="text" class="form-control" placeholder="车号" id="carno" name="carno" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-2 ">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">业务类型</span>
							<!-- <input type="text" class="form-control" placeholder="大　　门" id="gatename" name="gatename" aria-describedby="sizing-addon3"> -->
							<select id="operatype" name="operatype" class="form-control select2">
								<option value="">全部</option>
								<option value="0">其他</option>
								<option value="84">线材</option>
							</select>
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">车辆类型 </span> <select id="cartype" name="cartype" class="form-control select2">
								<option value="-1">全部</option>
								<option value="0">业务车辆</option>
								<option value="1">员工车辆</option>
								<option value="3">指令员卡</option>
							</select>
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">物&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;料</span> <input type="text" class="form-control" placeholder="物料" id="materialname" name="materialname" aria-describedby="sizing-addon3">
						</div>
					</div>

					<!-- <div class="col-sm-3 ">
							<div class="form-group input-group input-group-sm">
								<span class="input-group-addon" id="sizing-addon3">卡&nbsp;&nbsp;类&nbsp;&nbsp;型</span> <select
									id="ictype" name="ictype" class="form-control select2">
									<option value="">全部</option>
									<option value="0">临时卡</option>
									<option value="1">固定卡</option>
								</select>
							</div>
						</div> -->
				</div>

				<div class="row">
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon">司&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机</span> <input type="text" class="form-control" placeholder="司机" name="driver" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon">部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门</span> <input type="text" class="form-control" placeholder="部门" name="unitname" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-6">
						<div class="form-group btn-group-sm">
							<t:button text="查询" modulecode="JinChuChang" id="query" btnclass="btn btn-info" iconclass="glyphicon glyphicon-search" onclick="queryinfo()" />
							<t:button text="EXCEL" modulecode="JinChuChang" id="EntryExcel" btnclass="btn btn-warning" iconclass="fa fa-file-excel-o" onclick="exportExcel()" />
							<t:button text="进厂" modulecode="JinChuChang" id="InEXBtn" btnclass="btn btn-success" iconclass="fa fa-exchange" />
							<t:button text="出厂" modulecode="JinChuChang" id="OutEXBtn" btnclass="btn btn-primary" iconclass="fa fa-exchange" />
						</div>
					</div>
				</div>

			</form>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<table id="EntryInfoGrid" data-toggle="table" data-row-style="rowStyle" data-query-params="queryParams" data-pagination="true" data-page-size="10" data-page-list="[10,40,ALL]" data-mobile-responsive="true">
				<thead>
					<tr>
						<th data-field="state" data-radio="true" ></th>
						<th data-field="id" data-visible="false"></th>
						<th data-field="types" data-visible="false"></th>
						<th data-field="carno" data-halign="center" class="text-center">车号</th>
						<th data-field="driver" data-halign="center" class="text-center">司机</th>
						<th data-field="unitname" data-halign="center" class="text-center">部门</th>
						<th data-field="icid" class="text-center" data-searchable="true">卡号</th>
						<th data-field="cartype" data-halign="center" data-searchable="true" class="text-nowrap text-center">车辆类型</th>
						<th data-field="entrytype" data-halign="center" data-searchable="true" class="text-nowrap text-center">类型</th>
						<%-- <th data-field="ictype" data-halign="center" data-searchable="true" class="text-nowrap text-left">卡类型</th> --%>
						<th data-field="gatename" data-halign="center" data-searchable="true" class="text-nowrap text-left">大门</th>
						<th data-field="createdate" data-halign="center" data-searchable="true" class="text-nowrap text-center">进/出厂时间</th>
						<%-- <th data-field="statetime" data-halign="center" data-searchable="true" class="text-right text-right">厂内停留时间</th> --%>
						<th data-field="stateminute" data-halign="center" data-searchable="true" class="text-right text-right">耗时/分钟</th>
						<th data-field="planid" data-halign="center" data-searchable="true" class="text-nowrap text-center">单号</th>
						<th data-field="materialname" data-halign="center" data-searchable="true" class="text-nowrap text-left">货名</th>
						<th data-field="targetname" data-halign="center" data-searchable="true" class="text-nowrap text-left">客商</th>
						<th data-field="creator" data-searchable="true" class="text-right text-center">操作人</th>
						<th data-field="cardmemo" data-halign="center" data-searchable="true" class="text-nowrap">卡备注</th>
						<th data-field="usermemo" data-halign="center" data-searchable="true" class="text-nowrap">备注</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<div class="modal fade" id="InEXWindow" tabindex="-1" role="dialog" aria-labelledby="StoreoutWindowLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">进厂</h4>
				</div>
				<div class="modal-body">
					<form id="InEXForm">
						<input type="hidden" name="id" value="-1" /> <input type="hidden" name="exceptionflag" id="exceptionflag" value="1"> <input type="hidden" name="cardtype" id="incardtype" value="0"> <input type="hidden" name="cartype" id="incartype" value="0"> <input type="hidden" name="entrytype" id="inentrytype" value="0"> <input type="hidden" name="creator" id="creator" value="${creator}">
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">卡 号</span> <input type="text" name="icid" id="inicid" class="form-control" aria-describedby="basic-addon1" readonly="readonly" required data-bv-notempty-message="请刷卡获取卡号" />
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">车 号</span> <input name="carno" id="excarno" type="text" class="form-control" aria-describedby="basic-addon1" data-provide="typeahead" required data-bv-notempty-message="请输入车号">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">司 机</span> <input type="text" class="form-control" id="indriver" name="driver" aria-describedby="basic-addon1" readonly="readonly">
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">部 门</span> <input type="text" class="form-control" id="inunitname" name="unitname" aria-describedby="basic-addon1" readonly="readonly">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<input id="ingatename" name="gatename" type="hidden" class="form-control" value="">
								<t:combobox id="ingatecode" name="gatecode" url="/bcommon/queryInOutGatename.do" label="大 门 " require="true" alloptions="true" allowclear="true" />
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">备注</span> <input name="usermemo" type="text" class="form-control" maxlength="95" aria-describedby="basic-addon1" required="required">
								</div>
							</div>
						</div>
					</form>
					<div class="row">
						<div class="col-sm-12">
							<table id="InEXGrid" data-toggle="table" data-mobile-responsive="true">
								<thead>
									<tr>
										<th data-field="matchid" data-halign="center" nowrap="nowrap">物流号</th>
										<th data-field="planid" data-halign="center" nowrap="nowrap">到货单</th>
										<th data-field="materialname" data-halign="center" class="text-nowrap text-left" nowrap="nowrap">货名</th>
										<th data-field="materialspec" data-halign="center" class="text-nowrap text-left" nowrap="nowrap">规格</th>
										<th data-field="steelname" data-halign="center" class="text-nowrap text-left" nowrap="nowrap">型号</th>
										<th data-field="pictureno" data-halign="center" class="text-nowrap text-left" nowrap="nowrap">图号</th>
										<th data-field="snumber" data-halign="center" nowrap="nowrap" data-editable="true">数量</th>
										<th data-field="measureunit" data-halign="center" nowrap="nowrap">计量单位</th>
										<th data-field="sourcename" data-halign="center" class="text-nowrap text-left" nowrap="nowrap">供货</th>
										<th data-field="targetname" data-halign="center" class="text-nowrap text-left" nowrap="nowrap">收货</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				<div class="modal-footer  btn-group-sm">
					<button type="button" class="btn btn-success" id="InaddEXBtn" onclick="insertInEX()">确认</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="OutEXWindow" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">出厂</h4>
				</div>
				<div class="modal-body">
					<form id="OutEXForm">
						<input type="hidden" name="id" value="-1" /> <input type="hidden" name="exceptionflag" id="outexceptionflag" value="1"> <input type="hidden" name="cardtype" id="outcardtype" value="0"> <input type="hidden" name="cartype" id="outcartype" value="0"> <input type="hidden" name="entrytype" id="outentrytype" value="0"> <input type="hidden" name="creator" id="outcreator" value="${creator}">
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">卡 号</span> <input type="text" name="icid" id="outicid" class="form-control" aria-describedby="basic-addon1" readonly="readonly" required data-bv-notempty-message="请刷卡获取卡号" />
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">车 号</span> <input name="carno" id="exoutcarno" type="text" class="form-control" aria-describedby="basic-addon1" data-provide="typeahead" required data-bv-notempty-message="请输入车号">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">司 机</span> <input type="text" class="form-control" id="outdriver" name="driver" aria-describedby="basic-addon1" readonly="readonly">
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">部 门</span> <input type="text" class="form-control" id="outunitname" name="unitname" aria-describedby="basic-addon1" readonly="readonly">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<input id="outgatename" name="gatename" type="hidden" class="form-control" value="">
								<t:combobox id="outgatecode" name="gatecode" url="/bcommon/queryInOutGatename.do" label="大 门 " require="true" alloptions="true" allowclear="true" />
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">备 注</span> <input name="usermemo" type="text" class="form-control" maxlength="95" aria-describedby="basic-addon1" required="required">
								</div>
							</div>
						</div>
					</form>
					<div class="row">
						<div class="col-sm-12">
							<table id="OutEXGrid" data-toggle="table" data-mobile-responsive="true">
								<thead>
									<tr>
										<th data-field="matchid" data-halign="center" nowrap="nowrap">物流号</th>
										<th data-field="planid" data-halign="center" nowrap="nowrap">到货单</th>
										<th data-field="materialname" data-halign="center" class="text-nowrap text-left" nowrap="nowrap">货名</th>
										<th data-field="materialspec" data-halign="center" class="text-nowrap text-left" nowrap="nowrap">规格</th>
										<th data-field="steelname" data-halign="center" class="text-nowrap text-left" nowrap="nowrap">型号</th>
										<th data-field="pictureno" data-halign="center" class="text-nowrap text-left" nowrap="nowrap">图号</th>
										<th data-field="snumber" data-halign="center" nowrap="nowrap" data-editable="true">数量</th>
										<th data-field="measureunit" data-halign="center" nowrap="nowrap">计量单位</th>
										<th data-field="sourcename" data-halign="center" class="text-nowrap text-left" nowrap="nowrap">供货</th>
										<th data-field="targetname" data-halign="center" class="text-nowrap text-left" nowrap="nowrap">收货</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				<div class="modal-footer  btn-group-sm">
					<button type="button" class="btn btn-success" id="OutaddEXBtn" onclick="insertOutEX()">确认</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		jQuery(document)
				.ready(
						function($) {
							queryinfo();

							$.fn.typeahead.Constructor.prototype.blur = function() {
								var that = this;
								setTimeout(function() {
									that.hide()
								}, 250);
							};

							$('#excarno')
									.typeahead(
											{
												source : function(query,
														process) {
													$
															.ajax({
																type : "post",
																url : '<c:url value="/bcommon/queryInOutCarno.do?carno="/>'
																		+ query,
																dataType : "json",
																success : function(
																		data) {
																	var numArr = []; // 定义一个空数组
																	for (var i = 0; i < data.rows.length; i++) {
																		numArr
																				.push(data.rows[i].carno); // 将文本框的值添加到数组中
																	}
																	process(numArr);
																}
															});
												},
												updater : function(data) {
													getinfobycarno(data);
													return data;
												}
											});
							$('#exoutcarno')
									.typeahead(
											{
												source : function(query,
														process) {
													$
															.ajax({
																type : "post",
																url : '<c:url value="/bcommon/queryInOutCarno.do?carno="/>'
																		+ query,
																dataType : "json",
																success : function(
																		data) {
																	var numArr = []; // 定义一个空数组
																	for (var i = 0; i < data.rows.length; i++) {
																		numArr
																				.push(data.rows[i].carno); // 将文本框的值添加到数组中
																	}
																	process(numArr);
																}
															});
												},
												updater : function(data) {
													getOutinfobycarno(data);
													return data;
												}
											});

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
			$('#EntryInfoGrid').bootstrapTable('refresh', {
				url : "<c:url value='/entry/queryPage.do'/>"
			});
		}
		$('#EntryInfoGrid')
				.bootstrapTable(
						{
							onDblClickRow : function(row, $element) {
								layer
										.open({
											type : 2,
											title : '信息详情',
											maxmin : true, //开启最大化最小化按钮
											area : [ '90%', '90%' ],
											content : '<c:url value="/bcommon/showOtherDetail.do?type=2&ids="/>'
													+ row.id
													+ "&entrytype="
													+ row.types //注意，如果str是object，那么需要字符拼接。
										});
							}
						});
		/*---------------------------------------------------进厂------------------------------------------  */
		$('#InEXBtn').on('click', function() {
			$($('#InEXWindow form :input[name="id"]')).val(-1);
			$('#InEXWindow').modal('show');
		});
		/*打开异常进厂页面*/
		$('#InEXWindow')
				.on(
						'shown.bs.modal',
						function() {
							$('#InaddEXBtn').prop('disabled', false);
							var unitcode = $(
									'#InEXWindow form :input[name="unitcode"]')
									.val();
							var unitname = $(
									'#InEXWindow form :input[name="unitname"]')
									.val();
							var creator = $(
									'#InEXWindow form :input[name="creator"]')
									.val();
							loadFormData(
									'InEXForm',
									'<c:url value="/storeout/queryStoreout.do?unitcode="/>'
											+ unitcode + '&unitname='
											+ unitname,
									function(data) {
										$("#exceptionflag").val("1");
										$(
												'#InEXWindow form :input[name="creator"]')
												.val(creator);
									});
						});

		function getinfobycarno(carno) {
			$
					.ajax({
						type : "post",
						url : '<c:url value="/bcommon/judgOrBlackCarno.do"/>',
						dataType : "json",
						data : {
							carno : carno
						},
						success : function(data) {
							if (data.success) {//卡和车辆状态正常，根据车号查询业务信息
								$('#inicid').val(data.data)
								$('#incardtype').val(data.flags)
								$('#incartype').val(data.more)
								$('#indriver').val(data.mfunc)
								$('#inunitname').val(data.mores)
								$('#InEXGrid')
										.bootstrapTable(
												'refresh',
												{
													url : "<c:url value='/entry/queryEXList.do?carno='/>"
															+ carno
												});
								validForm('InEXForm');
							} else {//如果车辆或者卡有问题，系统提示
								errorbox(data.msg);
								$('#InaddEXBtn').prop('disabled', true);
							}
						}
					});
		}

		/**
		 *添加异常进厂信息
		 */
		function insertInEX() {
			validForm('InEXForm');
			$('#ingatename').val($('#ingatecode option:selected').text());
			$("#inentrytype").val("1");
			var cardtype = $("#incardtype").val();
			var cartype = $("#incartype").val();
			var carno = $("#excarno").val();
			var driver = $("#indriver").val();
			var unitname = $("#inunitname").val();
			var gridData = $('#InEXGrid').bootstrapTable('getData')
			var glength = gridData.length;
			if (glength > 0 || (glength == 0 && cardtype == 1) || cartype == 3) {
				if (cartype == 3) {
					saveFormData(
							'InEXForm',
							'<c:url value="/entry/insertInEX.do"/>',
							function(data) {
								if (data.success) {
									successbox(data.msg);
									$('#InEXWindow form :input[name="icid"]').val("");
									$('#InEXWindow form :input[name="carno"]').val("");
									$('#InEXWindow form :input[name="gatecode"]').val("");
									$('#InEXWindow form :input[name="gatename"]').val("");
									$('#InEXWindow form :input[name="driver"]').val("");
									$('#InEXWindow form :input[name="unitname"]').val("");
									$("#ingatecode").trigger('change.select2');
									$('#InEXWindow form :input[name="usermemo"]').val("");
									validForm('InEXForm');
									$('#EntryInfoGrid').bootstrapTable(
											'refresh');

								} else {
									errorbox(data.msg);
								}
							});
				} else {
					$
							.ajax({
								type : "post",
								url : '<c:url value="/entry/queryOrIn.do"/>',
								dataType : "json",
								data : {
									carno : carno
								},
								success : function(data) {
									if (data.total == 0) {//卡和车辆状态正常，根据车号查询业务信息	
										if (glength == 0) {
											saveFormData(
													'InEXForm',
													'<c:url value="/entry/insertInEX.do"/>',
													function(data) {
														if (data.success) {
															successbox(data.msg);
															$(
																	'#InEXWindow form :input[name="icid"]')
																	.val("");
															$(
																	'#InEXWindow form :input[name="carno"]')
																	.val("");
															$(
																	'#InEXWindow form :input[name="gatecode"]')
																	.val("");
															$(
																	'#InEXWindow form :input[name="gatename"]')
																	.val("");
															$('#InEXWindow form :input[name="driver"]').val("");
															$('#InEXWindow form :input[name="unitname"]').val("");
															$("#ingatecode")
																	.trigger(
																			'change.select2');
															$(
																	'#InEXWindow form :input[name="usermemo"]')
																	.val("");
															validForm('InEXForm');
															$('#EntryInfoGrid')
																	.bootstrapTable(
																			'refresh');
														} else {
															errorbox(data.msg);
														}
													});
										} else {
											saveFormData(
													'InEXForm',
													'<c:url value="/unauth/entry/insertInEntrylog.do"/>',
													function(data) {
														if (data.success) {
															successbox(data.msg);
															$(
																	'#InEXWindow form :input[name="icid"]')
																	.val("");
															$(
																	'#InEXWindow form :input[name="carno"]')
																	.val("");
															$(
																	'#InEXWindow form :input[name="gatecode"]')
																	.val("");
															$(
																	'#InEXWindow form :input[name="gatename"]')
																	.val("");
															$("#ingatecode")
																	.trigger(
																			'change.select2');
															$(
																	'#InEXWindow form :input[name="usermemo"]')
																	.val("");
															$('#InEXWindow form :input[name="driver"]').val("");
															$('#InEXWindow form :input[name="unitname"]').val("");
															validForm('InEXForm');
															$('#EntryInfoGrid')
																	.bootstrapTable(
																			'refresh');
															$('#InEXGrid')
																	.bootstrapTable(
																			'removeAll');
														} else {
															errorbox(data.msg);
														}
													});
										}
									} else {//如果车辆或者卡有问题，系统提示
										errorbox(data.msg);
									}
								}
							});
				}

			} else {
				errorbox("无业务信息不允许进厂");
			}

		}
		/*---------------------------------------------------出厂------------------------------------------  */
		$('#OutEXBtn').on('click', function() {
			$($('#OutEXWindow form :input[name="id"]')).val(-1);
			$('#OutEXWindow').modal('show');
		});
		/*打开异常进厂页面*/
		$('#OutEXWindow').on(
				'shown.bs.modal',
				function() {
					$('#OutaddEXBtn').prop('disabled', false);
					var unitcode = $(
							'#OutEXWindow form :input[name="unitcode"]').val();
					var unitname = $(
							'#OutEXWindow form :input[name="unitname"]').val();
					var creator = $('#OutEXWindow form :input[name="creator"]')
							.val();
					loadFormData('OutEXForm',
							'<c:url value="/storeout/queryStoreout.do?unitcode="/>'
									+ unitcode + '&unitname=' + unitname,
							function(data) {
								$("#outexceptionflag").val("1");
								$('#OutEXWindow form :input[name="creator"]')
										.val(creator);
							});
				});

		function getOutinfobycarno(carno) {
			$
					.ajax({
						type : "post",
						url : '<c:url value="/bcommon/judgOrBlackCarno.do"/>',
						dataType : "json",
						data : {
							carno : carno
						},
						success : function(data) {
							if (data.success) {//卡和车辆状态正常，根据车号查询业务信息	
								$('#outicid').val(data.data)
								$('#outcardtype').val(data.flags)
								$('#outcartype').val(data.more)
								$('#outdriver').val(data.mfunc)
								$('#outunitname').val(data.mores)
								$('#OutEXGrid')
										.bootstrapTable(
												'refresh',
												{
													url : "<c:url value='/entry/queryEXList.do?carno='/>"
															+ carno
												});
								validForm('OutEXForm');
							} else {//如果车辆或者卡有问题，系统提示
								errorbox(data.msg);
								$('#OutaddEXBtn').prop('disabled', true);
							}
						}
					});
		}

		/**
		 *添加异常出厂信息
		 */
		function insertOutEX() {
			validForm('OutEXForm');
			$('#outgatename').val($('#outgatecode option:selected').text());
			$("#outentrytype").val("2");
			var cardtype = $("#outcardtype").val();
			var cartype = $("#outcartype").val();
			var carno = $("#exoutcarno").val();
			var driver = $("#outdriver").val();
			var unitname = $("#outunitname").val();
			var gridData = $('#OutEXGrid').bootstrapTable('getData')
			var glength = gridData.length;
			if (glength > 0 || (glength == 0 && cardtype == 1) || cartype == 3) {
				if (cartype == 3) {
					saveFormData(
							'OutEXForm',
							'<c:url value="/entry/insertInEX.do"/>',
							function(data) {
								if (data.success) {
									successbox(data.msg);
									$('#OutEXWindow form :input[name="icid"]').val("");
									$('#OutEXWindow form :input[name="carno"]').val("");
									$('#OutEXWindow form :input[name="gatecode"]').val("");
									$('#OutEXWindow form :input[name="gatename"]').val("");
									$('#OutEXWindow form :input[name="driver"]').val("");
									$('#OutEXWindow form :input[name="unitname"]').val("");
									$("#outgatecode").trigger('change.select2');
									$('#OutEXWindow form :input[name="usermemo"]').val("");
									validForm('OutEXForm');
									$('#EntryInfoGrid').bootstrapTable('refresh');
								} else {
									errorbox(data.msg);
								}
							});
				} else {
					$
							.ajax({
								type : "post",
								url : '<c:url value="/entry/queryOrIn.do"/>',
								dataType : "json",
								data : {
									carno : carno
								},
								success : function(data) {
									if (data.total == 1) {//卡和车辆状态正常，根据车号查询业务信息	
										if (glength == 0) {
											saveFormData(
													'OutEXForm',
													'<c:url value="/entry/insertOutEX.do"/>',
													function(data) {
														if (data.success) {
															successbox(data.msg);
															$('#OutEXWindow form :input[name="icid"]').val("");
															$('#OutEXWindow form :input[name="carno"]').val("");
															$('#OutEXWindow form :input[name="gatecode"]').val("");
															$('#OutEXWindow form :input[name="gatename"]').val("");
															$('#OutEXWindow form :input[name="driver"]').val("");
															$('#OutEXWindow form :input[name="unitname"]').val("");
															$("#outgatecode").trigger('change.select2');
															$('#OutEXWindow form :input[name="usermemo"]').val("");
															validForm('OutEXForm');
															$('#EntryInfoGrid').bootstrapTable('refresh');
														} else {
															errorbox(data.msg);
														}
													});
										} else {
											saveFormData(
													'OutEXForm',
													'<c:url value="/unauth/entry/insertOutEntrylog.do"/>',
													function(data) {
														if (data.success) {
															successbox(data.msg);
															$('#OutEXWindow form :input[name="icid"]').val("");
															$('#OutEXWindow form :input[name="carno"]').val("");
															$('#OutEXWindow form :input[name="gatecode"]').val("");
															$('#OutEXWindow form :input[name="gatename"]').val("");
															$('#OutEXWindow form :input[name="driver"]').val("");
															$('#OutEXWindow form :input[name="unitname"]').val("");
															$("#outgatecode").trigger('change.select2');
															$(
																	'#OutEXWindow form :input[name="usermemo"]')
																	.val("");
															validForm('OutEXForm');
															$('#EntryInfoGrid')
																	.bootstrapTable(
																			'refresh');
															$('#OutEXGrid')
																	.bootstrapTable(
																			'removeAll');
														} else {
															errorbox(data.msg);
														}
													});
										}
									} else {//如果车辆或者卡有问题，系统提示
										errorbox(data.msg);
									}
								}
							});
				}
			} else {
				errorbox("无业务信息不允许进厂");
			}
		}
		
		function exportExcel() {
			remoteExportExcel('EntryInfoGrid', '', '', 'queryform');
		}
	</script>
</body>
</t:html>






