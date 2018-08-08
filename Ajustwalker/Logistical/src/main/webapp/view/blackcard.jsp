<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="t" uri="http://www.talent.com/jsp/anyonelse"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.Date"%>
<t:html>
<head>
<jsp:include page="common.jsp" flush="true" />
</head>
<%
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("00:00:00");
		SimpleDateFormat dateFormat3 = new SimpleDateFormat("23:59:59");
		Date now = new Date();
		Calendar c = Calendar.getInstance();
		String endtime = dateFormat1.format(c.getTime());//结束时间
		c.add(Calendar.MONTH, -1);// 月份减1  
		now = c.getTime();
		String begintime = dateFormat.format(now);//开始时间
%>
<body class="container-fluid" style="padding-top: 15px">
	<div class="row">
		<div class="col-sm-12">
			<form id="queryform">
				<div class="row">
					<%-- <div class="col-sm-3">
						<div class='form-group input-group input-group-sm date'>
							<span class="input-group-addon">开始时间</span> 
							<input type='text' class="form-control" placeholder="开始时间" id="begintime" name="begintime" value="<%=begintime%>" />
						</div>
					</div>		 --%>
					<c:if test="${param.flag==1}"> 
						<div class="col-sm-2">
							<div class="form-group input-group input-group-sm">
								<span class="input-group-addon" id="sizing-addon3">车号</span> <input type="text" class="form-control" placeholder="车号" id="cardid" name="cardid" aria-describedby="sizing-addon3">
							</div>
						</div>
					</c:if>
					<c:if test="${param.flag==2}">
						<div class="col-sm-2">
							<div class="form-group input-group input-group-sm">
								<span class="input-group-addon" id="sizing-addon3">卡号</span> <input type="text" class="form-control" placeholder="卡号" id="cardid" name="cardid" aria-describedby="sizing-addon3">
							</div>
						</div>
					</c:if>
					<c:if test="${param.flag==0}"> 
						<div class="col-sm-2">
							<div class="form-group input-group input-group-sm">
								<span class="input-group-addon" id="sizing-addon3">车号</span> <input type="text" class="form-control" placeholder="车号" id="cardid" name="cardid" aria-describedby="sizing-addon3">
							</div>
						</div>
						<div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">预警状态</span> <select id="validflag" name="validflag" class="form-control select2">
								<option value="-1">全部</option>
								<option value="1">未解除</option>
								<option value="0">已解除</option>
							</select>
						</div>
					</div>
					</c:if>
					<!-- <div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">类　　型 </span>
							 <select id="recordtype" name="recordtype" class="form-control select2">
								<option value="-1">全部</option>
		                        <option value="1">固定卡</option>
		                        <option value="0">临时卡</option>
							</select>
						</div>
					</div> -->
					<%-- 		</div>
				<div class="row" >
				<div class="col-sm-3">
						<div class='form-group input-group input-group-sm date'>
							<span class="input-group-addon">结束时间</span> 
							<input type='text' class="form-control" placeholder="结束时间" id="endtime" name="endtime" value="<%=endtime%>" />
						</div>
					</div> --%>
					<c:if test="${param.flag==1}">
					<div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">状 态</span> <select id="validflag" name="validflag" class="form-control select2">
								<option value="-1">全部</option>
								<option value="1">黑名单</option>
								<option value="0">已还原</option>
							</select>
						</div>
					</div>
					</c:if>
					<c:if test="${param.flag==2}">
					<div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">状 态</span> <select id="validflag" name="validflag" class="form-control select2">
								<option value="-1">全部</option>
								<option value="1">已挂失</option>
								<option value="0">已还原</option>
							</select>
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">卡类型</span> <select id="recordtype" name=recordtype class="form-control select2">
								<option value="-1">全部</option>
								<option value="0">IC卡</option>
								<option value="1">RFID卡</option>
							</select>
						</div>
					</div>
					</c:if>
					<div class="col-sm-6">
						<div class="form-group input-group input-group-sm btn-group-sm">
							<!-- <button id="query" type="button" class="btn btn-info" onclick="queryinfo()">
								<span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;查询
							</button>
							<button id="blackcardBtn" type="button" class="btn btn-success" onclick="openWindow()">
								<span class="glyphicon glyphicon-th-large" aria-hidden="true"></span>&nbsp;黑名单
							</button>
	                        <button id="fromcardBtn" type="button" class="btn btn-primary" onclick="cancelCard()">
								<span class="glyphicon glyphicon-th" aria-hidden="true"></span>&nbsp;还原
							</button> -->

							<t:button text="查询" modulecode="HeiMingDanGuanLi" id="query" btnclass="btn btn-info" iconclass="glyphicon glyphicon-search" onclick="queryinfo()" />
							<c:choose>
								<c:when test="${param.flag==0}">
									<t:button text="解除预警" modulecode="HeiMingDanGuanLi" id="returncardBtn1" btnclass="btn btn-primary" iconclass="glyphicon glyphicon-hand-up" onclick="returnCard(1)" />
									<t:button text="解除预警+" modulecode="HeiMingDanGuanLi" id="returncardBtn2" btnclass="btn btn-primary" iconclass="glyphicon glyphicon-hand-up" onclick="returnCard(2)" />
								</c:when>
								<c:when test="${param.flag==2}">
									<t:button text="还原" modulecode="HeiMingDanGuanLi" id="fromcardBtn" btnclass="btn btn-primary" iconclass="glyphicon glyphicon-hand-up" onclick="cancelCard()" />
								</c:when>
								<c:otherwise>
									<t:button text="黑名单" modulecode="HeiMingDanGuanLi" id="blackcardBtn" btnclass="btn btn-success" iconclass="glyphicon glyphicon-th-large" onclick="openWindow()" />
									<t:button text="还原" modulecode="HeiMingDanGuanLi" id="fromcardBtn" btnclass="btn btn-primary" iconclass="glyphicon glyphicon-hand-up" onclick="cancelCard()" />
									<t:button text="EXCEL" modulecode="BenDiYeWuHao" id="TaskcodeExcel" btnclass="btn btn-warning" iconclass="fa fa-file-excel-o" onclick="exportExcel()" />
									<t:button text="测试" modulecode="test" id="testbtn" btnclass="btn btn-warning" iconclass="fa fa-file-excel-o" onclick="exportExcel1()" />
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</form>

			<div class="row">
				<div class="col-sm-12">
					<table id="blackCardGrid" data-toggle="table" data-row-style="rowStyle" data-query-params="queryParams" data-pagination="true" data-page-size="10" data-page-list="[10, 40,  ALL]" data-mobile-responsive="true">
						<thead>
							<tr>
								<th data-field="state" data-radio="true"></th>
								<th data-field="id" data-halign="center" data-visible="false"></th>
								<c:choose>
									<c:when test="${param.flag==0}">
										<th data-field="usermemo" data-halign="center" data-searchable="true" class="text-nowrap text-center">预警信息</th>
										<th data-field="cardid" data-halign="center" data-searchable="true" sortable class="text-center">车号</th>
										<th data-field="validflag" data-halign="center" data-searchable="true" sortable class="text-center">状态</th>
										<th data-field="createdate" data-halign="center" data-searchable="true" class="text-nowrap text-center">预警时间</th>
										<th data-field="createman" data-halign="center" data-searchable="true" class="text-nowrap">预警大门</th>
										<th data-field="validtime" data-halign="center" data-searchable="true" class="text-nowrap">解除时间</th>
										<th data-field="validman" data-halign="center" data-searchable="true" class="text-nowrap">解除人</th>
									</c:when>
									<c:when test="${param.flag==2}">
									<th data-field="cardid" data-halign="center" data-searchable="true" sortable class="text-center">卡号</th>
										<th data-field="validflag" data-halign="center" data-searchable="true" sortable class="text-center">状态</th>
										<th data-field="createdate" data-halign="center" data-searchable="true" class="text-nowrap text-center">创建时间</th>
										<th data-field="createman" data-halign="center" data-searchable="true" class="text-nowrap">创建人</th>
										<th data-field="validtime" data-halign="center" data-searchable="true" class="text-nowrap">还原时间</th>
										<th data-field="validman" data-halign="center" data-searchable="true" class="text-nowrap">还原人</th>
										<th data-field="recordtype" data-halign="center" data-searchable="true" sortable class="text-center">用户备注</th>
									</c:when>
									<c:otherwise>
										<th data-field="cardid" data-halign="center" data-searchable="true" sortable class="text-center">车号</th>
										<th data-field="validflag" data-halign="center" data-searchable="true" sortable class="text-center">状态</th>
										<th data-field="recordtype" data-halign="center" data-searchable="true" sortable class="text-center">黑名单类型</th>
										<th data-field="createdate" data-halign="center" data-searchable="true" class="text-nowrap text-center">创建时间</th>
										<th data-field="createman" data-halign="center" data-searchable="true" class="text-nowrap">创建人</th>
										<th data-field="validtime" data-halign="center" data-searchable="true" class="text-nowrap">还原时间</th>
										<th data-field="validman" data-halign="center" data-searchable="true" class="text-nowrap">还原人</th>
										<th data-field="usermemo" data-halign="center" data-searchable="true" class="text-nowrap text-center">用户备注</th>
									</c:otherwise>
								</c:choose>
								<th data-field="sysmemo" data-halign="center" data-searchable="true" class="text-center">系统备注</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="blackCardWindow" tabindex="-1" role="dialog" aria-labelledby="blackCardWindowLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">黑名单</h4>
				</div>
				<div class="modal-body">
					<form id="blackCardForm">
						<input type="hidden" name="id" value="-1" />
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">车 号</span> <input name="cardid" type="text" class="form-control" aria-describedby="basic-addon1" value="" required data-bv-notempty-message="请填写车号">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">备 注</span> <input id="usermemo" name="usermemo" type="text" class="form-control" aria-describedby="basic-addon1" required data-bv-notempty-message="请填写备注">
								</div>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer  btn-group-sm">
					<button type="button" class="btn btn-success" id="StoreoutaddBtn" onclick="blackCard()">确认</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
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
		
		function GetQueryString(name)
		{
		     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
		     var r = window.location.search.substr(1).match(reg);
		     if(r!=null)return  unescape(r[2]); return null;
		}
		
		function queryinfo() {
			var myurl=GetQueryString("flag");
			if(myurl == 0){
				$('#blackCardGrid').bootstrapTable('refresh', {
					url : "<c:url value='/card/queryBlackInOutInfo.do'/>"
				});
			} else if(myurl == 1){
				$('#blackCardGrid').bootstrapTable('refresh', {
					url : "<c:url value='/card/queryBlackinfo.do'/>"
				});
			} else{
				$('#blackCardGrid').bootstrapTable('refresh', {
					url : "<c:url value='/card/queryCardinfo.do'/>"
				});
			}
		}
		
		function openWindow() {
			$('#blackCardWindow').modal('show');
		}
		/*打开黑名单*/
		$('#blackCardWindow').on(
				'shown.bs.modal',
				function() {
					loadFormData('blackCardForm',
							'<c:url value="/card/queryBcard.do"/>');
				});

		/**
		 *添加黑名单
		 */
		function blackCard() {
			validForm('blackCardForm');
			$('#blackCardForm').ajaxSubmit({
				type : 'post',
				url : '<c:url value="/card/insertBlack.do"/>',
				success : function(data) {
					if (data.success) {
						successbox(data.msg);
						$('#blackCardWindow').modal('toggle');
						$('#blackCardGrid').bootstrapTable('refresh');
					} else {
						errorbox(data.msg);
					}
				}
			});

		}

		/**
		 *还原
		 */
		function cancelCard() {
			var selectedRows = $('#blackCardGrid').bootstrapTable(
					'getSelections');
			if (selectedRows.length == 0) {
				errorbox("请选择还原信息");
			} else {
				var cardid = selectedRows[0].cardid
				var id = selectedRows[0].id
				dialogbox("请确认", "确认还原黑名单吗？", function(data) {
					if (data) {
						$.ajax({
							type : "post",
							url : '<c:url value="/card/cancelBlack.do"/>',
							dataType : "json",
							data : {
								cardid : cardid,
								id : id
							},
							success : function(data) {
								if (data.success == true) {
									var myurl=GetQueryString("flag");
										if(myurl == 0){
										$.ajax({
											type : "post",
											url : '<c:url value="/entrylog/delete"/>',
											dataType : "json",
											data : {cardid : selectedRows[0].cardid},
											success : function(data) {
												if (data.success) {
													successbox("车辆已可以进出厂！");
												}else{
													errorbox(data.msg);
												}
											}
										});
									}
									if(myurl != 0){
										successbox(data.msg);
									}
									$('#blackCardGrid').bootstrapTable('refresh');
								} else {
									errorbox(data.msg);
								}
							}
						});
					}
				});
			}

		}
		
		function returnCard(btn) {
			var selectedRows = $('#blackCardGrid').bootstrapTable(
					'getSelections');
			if (selectedRows.length == 0) {
				errorbox("请选择需要解除的信息");
			} else {
				if(selectedRows[0].validflag == "已解除")
					{
					errorbox("该条信息已解除预警！");
					} else {
						var cardid = selectedRows[0].cardid
						var id = selectedRows[0].id
						dialogbox("请确认", "确认解除预警吗？", function(data) {
							if (data) {
								$.ajax({
									type : "post",
									url : '<c:url value="/card/cancelBlack.do"/>',
									dataType : "json",
									data : {
										cardid : cardid,
										id : id
									},
									success : function(data) {
										if (data.success == true) {
												if(btn == 2){
												$.ajax({
													type : "post",
													url : '<c:url value="/entrylog/delete"/>',
													dataType : "json",
													data : {cardid : selectedRows[0].cardid},
													success : function(data) {
														if (data.success) {
															successbox("已关联作废最近的一条进出厂记录");
														}else{
															errorbox(data.msg);
														}
													}
												});
											}
											if(btn != 2){
												successbox(data.msg);
											}
											$('#blackCardGrid').bootstrapTable('refresh');
										} else {
											errorbox(data.msg);
										}
									}
								});
							}
						});
					}
			}

		}
		function exportExcel() {
			remoteExportExcel('blackCardGrid', '', '', 'queryform');
		}
	</script>
</body>
</t:html>