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
		.select2-container--bootstrap.input-sm .select2-selection--single,
			.input-group-sm .select2-container--bootstrap .select2-selection--single,
			.form-group-sm .select2-container--bootstrap .select2-selection--single
			{
			border-radius: 0px 4px 4px 0px;
		}
	</style>
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
	
		/* String unitname = request.getParameter("unitname") == null ? "": request.getParameter("unitname");
		String unitcode = request.getParameter("unitcode") == null ? "": request.getParameter("unitcode"); */
	%>
</head>
<body class="container-fluid" style="padding-top: 15px" >
	<div class="row">
		<div class="col-sm-12">
			<form id="queryform">
			<input type="hidden" id="unitcodes"  value="${unitcode}">
				<div class="row" >
					<%-- <div class="col-sm-3">
						<div class='form-group input-group input-group-sm date'>
							<span class="input-group-addon">开始时间</span> 
							<input type='text'class="form-control" placeholder="开始时间"  id="begintime" 	name="begintime" value="<%=begintime%>" />
						</div>
					</div> --%>
					<div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">车　　号</span> 
							<input type="text" class="form-control" placeholder="车号"  name="carno" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">卡&nbsp;&nbsp;编&nbsp;&nbsp;号</span> 
							<input type="text" class="form-control" placeholder="卡号" id="cardno" name="cardno" aria-describedby="sizing-addon3" >
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">卡　　号</span> 
							<input type="text" class="form-control" placeholder="卡号" id="cardid" name="cardid" aria-describedby="sizing-addon3" >
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">卡&nbsp;&nbsp;类&nbsp;&nbsp;型 </span>
							<select id="cardtype" name="cardtype" class="form-control select2">
								<option value="-1">全部</option>
								<option value="1">固定卡</option>
								<option value="0">临时卡</option>
							</select>
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">卡&nbsp;&nbsp;状&nbsp;&nbsp;态 </span> 
							<select id="validflag" name="validflag" class="form-control select2">
								<option value="-1">全 部</option>
								<option value="0">挂 失</option>
								<option value="1">初始化</option>
								<option value="3">已发卡</option>
							</select>
						</div>
					</div>
					
					<div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">卡&nbsp;&nbsp;种&nbsp;&nbsp;类 </span>
							<select id="cardclass" name="cardclass" class="form-control select2">
								<option value="-1">全部</option>
								<option value="0">IC卡</option>
								<option value="1">RFID卡</option>
							</select>
						</div>
					</div>
					
				</div>
				<div class="row" >
					<div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon">司&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机</span> <input type="text" class="form-control" placeholder="司机" name="driver" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon">部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门</span> <input type="text" class="form-control" placeholder="部门" name="unitname" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">车辆类型 </span>
							<select id="cartype" name="cartype" class="form-control select2">
								<option value="-1">全部</option>
								<option value="0">业务车辆</option>
								<option value="1">员工车辆</option>
								<option value="3">指令员卡</option>
							</select>
						</div>
					</div>
					<%-- <div class="col-sm-3">
						<div class='form-group input-group input-group-sm date'>
							<span class="input-group-addon">结束时间</span> 
							<input type='text' class="form-control" placeholder=" 结束时间" id="endtime" name="endtime" value="<%=endtime%>" />
						</div>
					</div> 
					<div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">卡&nbsp;&nbsp;种&nbsp;&nbsp;类 </span>
							<select id="cardclass" name="cardclass" class="form-control select2">
								<option value="-1">全部</option>
								<option value="0">IC卡</option>
								<option value="1">RFID卡</option>
							</select>
						</div>
					</div>--%>
					<div class="col-sm-10 btn-group-sm" >
						 <!-- <button id="query" type="button" class="btn btn-info" onclick="queryinfo()">
							<span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;查询
						</button>
						<button id="initcardBtn" type="button" class="btn btn-success" onclick="openWindow('init')">
							<span class="glyphicon glyphicon-th-large" aria-hidden="true"></span>&nbsp;IC初始化
						</button>						
						<button id="fromcardBtn" type="button" class="btn btn-primary" onclick="openWindow('from')">
							<span class="glyphicon glyphicon-th" aria-hidden="true"></span>&nbsp;IC发卡
						</button>
						<button id="backcardBtn" type="button" class="btn btn-danger" onclick="openWindow('back')">
							<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>&nbsp;IC退卡
						</button>
						<button id="initRfidcardBtn" type="button" class="btn btn-success" onclick="openWindow('initrfid')">
							<span class="glyphicon glyphicon-th-large" aria-hidden="true"></span>&nbsp;RFID初始化
						</button>
						<button id="fromcardBtn" type="button" class="btn btn-primary" onclick="openWindow('rfidfrom')"> 
							<span class="glyphicon glyphicon-th" aria-hidden="true"></span>&nbsp;RFID发卡
						</button>
						<button id="backcardBtn" type="button" class="btn btn-danger" onclick="openWindow('rfidback')">
							<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>&nbsp;RFID退卡
						</button>
						<button id="cancelcardBtn" type="button" class="btn btn-warning" onclick="cancelCard()">
							<span class="glyphicon glyphicon-align-justify " aria-hidden="true"></span>&nbsp;挂失
						</button> -->
						 
						<t:button text="查1询" modulecode="ICKaGuanLi" id="query" btnclass="btn btn-info" iconclass="glyphicon glyphicon-search" onclick="queryinfo()"/>
						<t:button text="IC初始化" modulecode="ICKaGuanLi" id="initcardBtn" btnclass="btn btn-success" iconclass="glyphicon glyphicon-th-large" onclick="openWindow('init')" />
					    <t:button text="IC发卡" modulecode="ICKaGuanLi" id="fromcardBtn" btnclass="btn btn-primary" iconclass="glyphicon glyphicon-hand-up" onclick="openWindow('from')"/>
					    <t:button text="IC退卡" modulecode="ICKaGuanLi" id="backcardBtn" btnclass="btn btn-danger" iconclass="glyphicon glyphicon-hand-down" onclick="openWindow('back')"/>
					    <t:button text="RFID初始化" modulecode="ICKaGuanLi" id="initRfidcardBtn" btnclass="btn btn-success" iconclass="glyphicon glyphicon-hand-up" onclick="openWindow('initrfid')"/>
					    <t:button text="RFID发卡" modulecode="ICKaGuanLi" id="fromRfidcardBtn" btnclass="btn btn-primary" iconclass="glyphicon glyphicon-hand-down"  onclick="openWindow('rfidfrom')"/>
					    <t:button text="RFID退卡" modulecode="ICKaGuanLi" id="backRfidcardBtn" btnclass="btn btn-danger" iconclass="glyphicon glyphicon-hand-up" onclick="openWindow('rfidback')"/>
					    <t:button text="挂失" modulecode="ICKaGuanLi" id="cancelcardBtn" btnclass="btn btn-warning" iconclass="glyphicon glyphicon-hand-down"  onclick="cancelCard()"/>
					    <t:button text="有效期修改" modulecode="ICKaGuanLi" id="changeTimeBtn" btnclass="btn btn-primary" iconclass="glyphicon glyphicon-time"  onclick="updatetime()"/>
					</div>
				</div>
			</form>
			<div class="row">
			    <div class="col-sm-12">
				<table id="CardGrid" 
				       data-toggle="table"
				       data-row-style="rowStyle"
					   data-query-params="queryParams" 
					   data-pagination="true"
					   data-page-size="1"
					   data-page-list="[10, 40,  ALL]"
					   data-side-pagination="server" 
					   data-mobile-responsive="true">
					<thead>
						<tr>
							<th data-field="state" data-radio="true"></th>							
							<th data-field="cardid" data-halign="center"
								data-searchable="true" sortable class="text-center">卡号</th>
							<th data-field="validflag" data-halign="center"
								data-searchable="true" sortable class="text-center">状态</th>
							<th data-field="cardno" data-halign="center"
								data-searchable="true" sortable class="text-center">卡编码</th>
							<th data-field="cardtype" data-halign="center"
								data-searchable="true" class="text-nowrap text-center">卡类型</th>
							<th data-field="cardclass" data-halign="center"  data-formatter="cardclassFormatter"
								data-searchable="true" sortable class="text-center">卡种类</th>	
							<th data-field="cartype" data-halign="center"
								data-searchable="true" class="text-nowrap">车辆类型</th>
							<th data-field="carno" data-halign="center"
								data-searchable="true" class="text-nowrap">车号</th>
							<th data-field="driver" data-halign="center" class="text-center">司机</th>
							<th data-field="unitname" data-halign="center" class="text-center">部门</th>
										<c:choose>
											<c:when test="${flag!=0}">
												<th data-field="begintime" data-halign="center" class="text-center">开始时间</th>
												<th data-field="endtime" data-halign="center" class="text-center">结束时间</th>
											</c:when>
										</c:choose>
							<th data-field="fromman" data-halign="center"
								data-searchable="true" class="text-center" class="text-nowrap">发卡人</th>
							<th data-field="fromdate" data-halign="center"
								data-searchable="true" class="text-center">发卡时间</th>
							<th data-field="creator" data-halign="center"
								data-searchable="true" class="text-center">初始化人</th>
							<th data-field="cdate" data-halign="center"
								data-searchable="true" class="text-center">初始化时间</th>
							<th data-field="usermemo" data-halign="center" data-searchable="true"class="text-nowrap">备注</th>
						</tr>
					</thead>
				</table>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="initCardWindow" tabindex="-1" role="dialog"
		aria-labelledby="initCardWindowLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">IC卡初始化</h4>
				</div>
				<div class="modal-body">
					<form id="initCardForm">
						<input type="hidden"  name="id" value="-1" />
						<input type="hidden" name="cardclass" value="0" />
						<input type="hidden" name="unitcode" id="unitcode" value="${unitcode}" /> 
						<input type="hidden" name="unitname" id="unitname" value="${unitname}" />
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group input-group-sm">
									<div class="input-group-addon">IC&nbsp;&nbsp;卡&nbsp;&nbsp;号</div>
									<input  name="cardid" type="text" class="form-control"aria-describedby="basic-addon1" value="" required data-bv-notempty-message="未读取卡号">
								</div>
							</div>
						</div>
						<div class="row" >
							<div class="col-sm-12">
								<div class="form-group input-group input-group-sm">
									<div class="input-group-addon">印刷编号</div>
									<input  name="cardno" type="text"class="form-control" aria-describedby="basic-addon1">
								</div>
							</div>
						</div>		
					</form>
				</div>
				<div class="modal-footer btn-group-sm">
					<button type="button" class="btn btn-success" id="initBtn"
						onclick="initCard()">确认</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>    
	<div class="modal fade" id="initRfidCardWindow" tabindex="-1" role="dialog"
		aria-labelledby="initRfidCardWindowLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">RFID卡初始化</h4>
				</div>
				<div class="modal-body">
					<form id="initRfidCardForm">
						<input type="hidden"  name="id" value="-1" />
						<input type="hidden" name="cardclass" value="1" />
						<input type="hidden" name="unitcode" id="unitcode" value="${unitcode}" /> 
						<input type="hidden" name="unitname" id="unitname" value="${unitname}" />
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group input-group-sm">
									<div class="input-group-addon">RFID卡号</div>
									<input  name="cardid" type="text" class="form-control"aria-describedby="basic-addon1" value="" required data-bv-notempty-message="未读取卡号">
								</div>
							</div>
						</div>						
						 <div class="row" >
							<div class="col-sm-12">
								<div class="form-group input-group input-group-sm">
									<div class="input-group-addon">印刷编号</div>
									<input  name="cardno" type="text"class="form-control" aria-describedby="basic-addon1">
								</div>
							</div>
						</div>	
					</form>
				</div>
				<div class="modal-footer btn-group-sm">
					<button type="button" class="btn btn-success" id="initBtn"
						onclick="initRfidCard()">确认</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="fromCardWindow" tabindex="-1" role="dialog"
		aria-labelledby="fromCardWindowLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">IC发卡</h4>
				</div>
				<div class="modal-body">
					<form id="fromCardForm">
						<input type="hidden" name="id" value="-1" /> 
						<input type="hidden" name="unitcode" id="unitcode" value="${unitcode}" /> 
						  <input type="hidden" name="cardclass" id="cardclassic"  />
						<div class="row" >
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">IC&nbsp;&nbsp;卡&nbsp;&nbsp;号</span>
									<input id="cardid" name="cardid" type="text" class="form-control"aria-describedby="basic-addon1" readonly required data-bv-notempty-message="请刷卡获取卡号">
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">卡&nbsp;&nbsp;类&nbsp;&nbsp;型</span>
									<select id="cardtype" name="cardtype" class="form-control select2" required data-bv-notempty-message="请选择卡类型">
										<c:choose>
											<c:when test="${flag==0}">
												<option value="0" selected="selected">临时卡</option>
											</c:when>
											<c:otherwise>
												<option value="1">固定卡</option>
												<option value="0">临时卡</option>
											</c:otherwise>
										</c:choose>
									</select>
								</div>
							</div>
						</div>
						<div class="row" >
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
								    <span class="input-group-btn">
										<button class="btn btn-default" type="button" onclick="checkCarno('IC')"><span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>车　号</button>
									</span>
									<input id="carno" name="carno" type="text" class="form-control"
									aria-describedby="basic-addon1" required data-bv-notempty-message="请填写车号"
									onblur="judgOrFromcarno() "
									>
									
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">车辆类型</span> <select
										id="cartype" name="cartype" class="form-control select2"
										required data-bv-notempty-message="请选择车辆类型">
										<c:choose>
											<c:when test="${flag==0}">
												<option value="0">业务车辆</option>
											</c:when>
											<c:otherwise>
												<option value="0">业务车辆</option>
												<option value="1">员工车辆</option>
												<option value="2">操作员卡</option>
												<option value="3">指令员卡</option>
											</c:otherwise>
										</c:choose>
									</select>
								</div>
							</div>
						</div>
						<div class="row" >
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">司&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机</span>
									<input id="driver" name="driver" type="text" class="form-control" aria-describedby="basic-addon1" >
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门</span>
									<input id="unitname" name="unitname" type="text" class="form-control" aria-describedby="basic-addon1" >
								</div>
							</div>
						</div>

						<%-- <div class="row" >
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">押　　金</span>
									<input id="deposit" name="deposit" type="text" class="form-control" aria-describedby="basic-addon1"
									data-bv-numeric ="true"  data-bv-numeric-message="请输入数字"
									>
								</div>
							</div>
							<div class="col-sm-6">
									  <input name="motorcadename" id="frommotorcadename" type="hidden"   >
									  <t:combobox id="frommotorcadecode" name="motorcadecode"  url="/bcommon/queryMotorcadeinfo.do" label="部　　门"/>	
							</div>
						</div> 

						<div class="row" >
							<div class="col-sm-6">
								
							</div>
						</div>	--%>					
						<div class="row" >
							<div class="col-sm-6">
								<div class='form-group input-group input-group-sm  date'>
									<span class="input-group-addon">开始时间</span>
									 <input type='text' class="form-control" placeholder="有效期开始时间" id="begintimes" name="begintime" value="<%=begintime%>" />
								</div>

							</div>
							<div class="col-sm-6">
								<div class='form-group input-group input-group-sm date'>
									<span class="input-group-addon">结束时间</span> 
									<input type='text' class="form-control" placeholder="有效期结束时间" id="endtimes" name="endtime" value="<%=endtime%>" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<div class='form-group input-group input-group-sm  date'> 
									<span class="input-group-addon">开始时段</span>
									<input type='text'class="form-control" placeholder="开始时段" id="beginperiod" name="beginperiod" value="00:00:00" />
								</div>
							</div>
							<div class="col-sm-6">
								<div class='form-group input-group input-group-sm  date'>
									<span class="input-group-addon">结束时段</span>
									<input type='text' class="form-control" placeholder="结束时段" id="endperiod" name="endperiod" value="23:59:59" />
								</div>
							</div>
						</div>
						<div class="row" >
							<div class="col-sm-12">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">备　　注</span>
									<input id="usermemo" name="usermemo" type="text" class="form-control" aria-describedby="basic-addon1">
								</div>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer btn-group-sm" >
					<button type="button" class="btn btn-success" id="fromBtn" onclick="fromCard()">确认</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="fromRfidCardWindow" tabindex="-1"
		role="dialog" aria-labelledby="fromRfidCardWindowLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">RFID发卡</h4>
				</div>
				<div class="modal-body">
					<form id="fromRfidCardForm">
						<input type="hidden" name="id" value="-1" />
						<input type="hidden" name="unitcode" id="unitcode" value="${unitcode}" /> 
						<input type="hidden" name="unitname" id="unitname" value="${unitname}" />
						<input type="hidden" name="cardtype" id="cardtyperfid" value="1" />
						<input type="hidden" name="cartype"  id="cartyperfid" value="0" />
						<input type="hidden" name="cardclass" id="cardclassrfid"  />
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">RFID卡号</span>
									<input id="cardid" name="cardid" type="text"
										class="form-control" aria-describedby="basic-addon1" required
										data-bv-notempty-message="请刷卡获取卡号">
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-btn">
										<button class="btn btn-default" type="button" onclick="checkCarno('RFID')">
											<span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>车　号
										</button>
									</span> 
									<input name="carno" type="text" class="form-control" aria-describedby="basic-addon1" required
										data-bv-notempty-message="请填写车号" onblur="judgOrFromcarno() ">
								</div>
							</div>
						</div>
						<%-- <div class="row">
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">司　　机</span> <input id="driver"
										name="driver" type="text" class="form-control"
										aria-describedby="basic-addon1">
								</div>
							</div>
							<div class="col-sm-6">
									<input name="motorcadename" id="frommotorcadename" type="hidden">
									<t:combobox id="rfidmotorcadecode" name="motorcadecode" url="/bcommon/queryMotorcadeinfo.do" label="部　　门" />
							</div>
						</div> --%>
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">备　　注</span> 
									<input id="usermemo" name="usermemo" type="text" class="form-control" aria-describedby="basic-addon1">
								</div>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer btn-group-sm">
					<button type="button" class="btn btn-success" id="fromBtn"
						onclick="fromRfidCard()">确认</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="backCardWindow" tabindex="-1" role="dialog" aria-labelledby="backRfidCardWindowLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">IC退卡</h4>
				</div>
				<div class="modal-body">
					<form id="backCardForm">
						<input type="hidden" name="id" value="-1" /> 
						<input type="hidden" name="unitcode" id="unitcode" value="${unitcode}" /> 
						 <input type="hidden" name="unitname" id="unitname" value="${unitname}" />						
						<div class="row" >
							<div class="col-sm-12">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">IC&nbsp;&nbsp;卡&nbsp;&nbsp;号</span>
									<input name="cardid" type="text" class="form-control" aria-describedby="basic-addon1" readonly="readonly" required data-bv-notempty-message="未读取卡号">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">卡&nbsp;&nbsp;类&nbsp;&nbsp;型</span>
									<select id="cardtype" name="cardtype" disabled="disabled"
										class="form-control select2"　>
										<option value="1">固定卡</option>
										<option value="0">临时卡</option>
									</select>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">车　　号</span>
									<input id="carno" name="carno" type="text" class="form-control" aria-describedby="basic-addon1" readonly="readonly">
								</div>
							</div>
						</div>  
						<!-- <div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">司　　机</span>
									<input id="driver" name="driver" type="text" class="form-control" aria-describedby="basic-addon1" readonly="readonly">
								</div>
							</div>
						</div> 
						<div class="row" >
							<div class="col-sm-12">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">押　　金</span>
									<input id="deposit" name="deposit" type="text" class="form-control" aria-describedby="basic-addon1" readonly="readonly">
								</div>
							</div> 
						</div> -->
						<div class="row" >
							<div class="col-sm-12">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">车辆类型</span>
									<select id="cartype" name="cartype" class="form-control select2" required data-bv-notempty-message="请选择车辆类型">
										<option value="0">业务车辆</option>
										<option value="1">员工车辆</option>
									</select> 
								</div>
							</div>
						</div>
						<!-- <div class="row" >
							<div class="col-sm-12">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">部　　门</span>
									<input id="motorcadename" name="motorcadename" type="text" class="form-control" aria-describedby="basic-addon1" readonly="readonly">
								</div>
							</div>
						</div>	 -->					
					</form>
				</div>
				<div class="modal-footer  btn-group-sm">
					<button type="button" class="btn btn-success" id="backBtn" onclick="backCard()">确认</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="backRfidCardWindow" tabindex="-1" role="dialog" aria-labelledby="backRfidCardWindowLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">RFID退卡</h4>
				</div>
				<div class="modal-body">
					<form id="backRfidCardForm">
						<input type="hidden" name="id" value="-1" /> 
						<input type="hidden" name="unitcode" id="unitcode" value="${unitcode}" /> 
						 <input type="hidden" name="unitname" id="unitname" value="${unitname}" />						
						<div class="row" >
							<div class="col-sm-12">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">RFID卡号</span>
									<input name="cardid" type="text" class="form-control" aria-describedby="basic-addon1" readonly="readonly" required data-bv-notempty-message="未读取卡号">
								</div>
							</div> 
						</div>
						
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">车　　号&nbsp;</span>
									<input id="carno" name="carno" type="text" class="form-control" aria-describedby="basic-addon1" readonly="readonly">
								</div>
							</div>
						</div>  
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">司　　机&nbsp;</span>
									<input id="driver" name="driver" type="text" class="form-control" aria-describedby="basic-addon1" readonly="readonly">
								</div>
							</div>
						</div> 	
						<div class="row" >
							<div class="col-sm-12">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">部　　门&nbsp;</span>
									<input id="motorcadename" name="motorcadename" type="text" class="form-control" aria-describedby="basic-addon1" readonly="readonly">
								</div>
							</div>
						</div>						
					</form>
				</div>
				<div class="modal-footer  btn-group-sm">
					<button type="button" class="btn btn-success" id="backRfidBtn" onclick="backRfidCard()">确认</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="changeTimeWindow" tabindex="-1" role="dialog"
		aria-labelledby="changeTimeWindowLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">IC卡修改有效期</h4>
				</div>
				<div class="modal-body">
					<form id="changeTimeForm">
						<input type="hidden" name="id" value="-1" /> 
						<input type="hidden" name="unitcode" id="unitcode" value="${unitcode}" /> 
						  <input type="hidden" name="cardclass" id="cardclassic"  />
						<div class="row" >
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">IC&nbsp;&nbsp;卡&nbsp;&nbsp;号</span>
									<input id="cardid" name="cardid" type="text" class="form-control"aria-describedby="basic-addon1" readonly required data-bv-notempty-message="请刷卡获取卡号">
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">卡&nbsp;&nbsp;类&nbsp;&nbsp;型</span>
									<select id="cardtype" name="cardtype" disabled="disabled"
										class="form-control select2"　>
										<option value="1">固定卡</option>
									</select>
								</div>
							</div>
						</div>
						<div class="row" >
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
								    <span class="input-group-addon">车&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</span>
									<input id="carno" name="carno" type="text" class="form-control" readonly
									aria-describedby="basic-addon1"
									onblur="judgOrFromcarno() "
									>
									
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">车辆类型</span> <select
										id="cartype" name="cartype"  disabled="disabled" class="form-control select2">
											<option value="1">员工车辆</option>
									</select>
								</div>
							</div>
						</div>
						<div class="row" >
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">司&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机</span>
									<input id="driver" name="driver" type="text" class="form-control" readonly aria-describedby="basic-addon1" >
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门</span>
									<input id="unitname" name="unitname" type="text" class="form-control" readonly aria-describedby="basic-addon1" >
								</div>
							</div>
						</div>					
						<div class="row" >
							<div class="col-sm-6">
								<div class='form-group input-group input-group-sm  date'>
									<span class="input-group-addon">开始时间</span>
									 <input type='text' class="form-control" placeholder="有效期开始时间" required data-bv-notempty-message="请选择有效开始时间" id="begintimess" name="begintime" value="<%=begintime%>" />
								</div>

							</div>
							<div class="col-sm-6">
								<div class='form-group input-group input-group-sm date'>
									<span class="input-group-addon">结束时间</span> 
									<input type='text' class="form-control" placeholder="有效期结束时间" required data-bv-notempty-message="请选择有效结束时间" id="endtimess" name="endtime" value="<%=endtime%>" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<div class='form-group input-group input-group-sm  date'> 
									<span class="input-group-addon">开始时段</span>
									<input type='text'class="form-control" placeholder="开始时段" id="beginperiods" name="beginperiod" value="00:00:00" />
								</div>
							</div>
							<div class="col-sm-6">
								<div class='form-group input-group input-group-sm  date'>
									<span class="input-group-addon">结束时段</span>
									<input type='text' class="form-control" placeholder="结束时段" id="endperiods" name="endperiod" value="23:59:59" />
								</div>
							</div>
						</div>
						<div class="row" >
							<div class="col-sm-12">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">备　　注</span>
									<input id="usermemo" name="usermemo" type="text" class="form-control" aria-describedby="basic-addon1">
								</div>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer btn-group-sm" >
					<button type="button" class="btn btn-success" id="changeBtn" onclick="changeTime()">确认</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
	//var cardType = 'LJYZN107~MHRF35LT';  //MC9500  MHRF35LT   LJYZN107
	var cardType;// = 'MHRF35LT'; 
	var rfidType='LJYZN107'
	jQuery(document).ready(function($) {
		queryinfo();	
		queryRTypeBycode();//通过作业点编码查询读卡器型号
		  
	});
	
	function queryRTypeBycode(){
		$.ajax({
            type: "post",
            url: '<c:url value="/bcommon/queryRTypeBycode.do"/>',
            dataType: "json",
            data: {workpointcode:$("#unitcodes").val()}, 
            success: function(data){
            	 if(data.more!=null){
            		if(data.more.ictype!=null&&data.more.ictype!=''){
               		    cardType= data.more.ictype;
               	    }
            		if(data.more.rfidtype!=null&&data.more.rfidtype!=''){
                  		rfidType=data.more.rfidtype;
                  	}
            	} 
             }
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
			
			$('#beginperiod').datetimepicker({
				format : 'HH:mm:ss',
				locale : 'zh-cn'
			});
			$('#endperiod').datetimepicker({
				format : 'HH:mm:ss',
				locale : 'zh-cn'
			});
			$("#beginperiod").on("dp.change", function(e) {
				$('#endperiod').data("DateTimePicker").minDate(e.date);
			});
			$("#endperiod").on("dp.change", function(e) {
				$('#beginperiod').data("DateTimePicker").maxDate(e.date);
			});
			
			$('#begintimes').datetimepicker({
				format : 'YYYY-MM-DD HH:mm:ss',
				locale : 'zh-cn'
			});
			$('#endtimes').datetimepicker({
				format : 'YYYY-MM-DD HH:mm:ss',
				locale : 'zh-cn'
			});
			$("#begintimes").on("dp.change", function(e) {
				$('#endtimes').data("DateTimePicker").minDate(e.date);
			});
			$("#endtimes").on("dp.change", function(e) {
				$('#begintimes').data("DateTimePicker").maxDate(e.date);
			});
			
			$('#begintimess').datetimepicker({
				format : 'YYYY-MM-DD HH:mm:ss',
				locale : 'zh-cn'
			});
			$('#endtimess').datetimepicker({
				format : 'YYYY-MM-DD HH:mm:ss',
				locale : 'zh-cn'
			});
			$("#begintimess").on("dp.change", function(e) {
				$('#endtimess').data("DateTimePicker").minDate(e.date);
			});
			$("#endtimess").on("dp.change", function(e) {
				$('#begintimess').data("DateTimePicker").maxDate(e.date);
			});
			
			$('#beginperiods').datetimepicker({
				format : 'HH:mm:ss',
				locale : 'zh-cn'
			});
			$('#endperiods').datetimepicker({
				format : 'HH:mm:ss',
				locale : 'zh-cn'
			});
			$("#beginperiods").on("dp.change", function(e) {
				$('#endperiods').data("DateTimePicker").minDate(e.date);
			});
			$("#endperiods").on("dp.change", function(e) {
				$('#beginperiods').data("DateTimePicker").maxDate(e.date);
			});
		});
		
		function queryParams(params) {
			return jQuery.extend({}, params, $('#queryform').serializeJson());
		}
		
		function queryinfo() {
			$('#CardGrid').bootstrapTable('refresh', {url : "<c:url value='/card/queryPage.do'/>"});
		}
		
		function openWindow(page) {  
			if ("init" == page) {
				$('#initCardWindow').modal('show');
			}else if ("initrfid" == page) {
				$('#initRfidCardWindow').modal('show');
			} else if ("from" == page) {
				$('#fromCardWindow').modal('show');
			} else if ("back" == page) {
				$('#backCardWindow').modal('show');
			} else if ("rfidfrom" == page) {
				$('#fromRfidCardWindow').modal('show');
			} else if ("rfidback" == page) {
				var selectedRows = $('#CardGrid').bootstrapTable('getSelections');
				var cardid = selectedRows[0].cardid;
				if(selectedRows[0].cardclass==1){
					if(cardid!=null && cardid!=''){
						$('#backRfidCardForm input[name="cardid"]').val(cardid)
						$('#backRfidCardWindow').modal('show');
					}else{
						errorbox("请选择需要退卡的RFID卡")
					}
				}
				
				
			} 
		}
		
		function cardclassFormatter(value, row, index) {
			if(row.cardclass==0){
				return 'IC卡';
			}else{
				return 'RFID卡';
			}
			
		}
		//---------------------------------------IC初始化------------------------------------------------	
		/*打开初始化*/		
		function onFindCard(data){//打卡读卡器进行哪些操作
			$('#initBtn').prop('disabled', false);
			$('#initCardForm input[name="cardid"]').val(data.cardid);
			$.ajax({
	            type: "post",
	            url: '<c:url value="/card/judgInitCarno.do"/>',
	            dataType: "json",
	            data: {cardid:data.cardid}, 
	            success: function(data){
	            	  if(!data.success ){//卡和车辆状态正常，根据车号查询业务信息
	            		errorbox(data.msg);
	            		$('#initBtn').prop('disabled', true); 
	            	 }
	            }
            });
			validForm('initCardForm');
		}
		
		function onDropCard(data){//关闭读卡器，进行哪些操作
			$('#initCardForm input[name="cardid"]').val('');		      
		    $('#initBtn').prop('disabled', false);  
			validForm('initCardForm'); 
		}
		
		 /* 打开IC卡初始化页面 */
		$('#initCardWindow').on('shown.bs.modal', function() {		
			loadFormData('initCardForm', '<c:url value="/card/queryBcard.do?page=ic"/>',function(formdata){
				OpenMediaReader(cardType,onFindCard,onDropCard,function(data){//第一个参数是读卡器型号、第二个是打开卡操作，第三个参数是关闭读卡器操作
				
				}); 
			});
		});
		 /*关闭IC初始化页面*/
		$('#initCardWindow').on('hide.bs.modal', function() {			
			CloseMediaReader(cardType,function(data){//关闭读卡器
				
			});
		});
		/**
		 *添加IC初始化
		 */
		function initCard() {
			saveFormData('initCardForm','<c:url value="/card/initCard.do"/>',function(data){				
				if (data.success) {
					successbox(data.msg);
					$('#CardGrid').bootstrapTable('refresh');
					$('#initCardForm input[name="cardid"]').val('');
					$('#initCardForm input[name="cardno"]').val($('#initCardForm input[name="cardno"]').val()*1+1); 
					validForm('initCardForm');
				} else {
					errorbox(data.msg);
				}
			});
		}
		//---------------------------------------Rfid初始化------------------------------------------------	
		/*打开初始化*/		
		function onFindInitRFIDCard(data){//打卡读卡器进行哪些操作
			$('#initrfidBtn').prop('disabled', false);
		     var rfid = data.cardid;
			$('#initRfidCardForm input[name="cardid"]').val(rfid);
			$.ajax({
	            type: "post",
	            url: '<c:url value="/card/judgInitCarno.do"/>',
	            dataType: "json",
	            data: {cardid:rfid}, 
	            success: function(data){
	            	  if(!data.success ){//卡和车辆状态正常，根据车号查询业务信息
	            		errorbox(data.msg);
	            		$('#initrfidBtn').prop('disabled', true); 
	            	 }
	            }
            });			    
			validForm('initRfidCardForm');
		}
		
		function onDropInitRFIDCard(data){//关闭读卡器，进行哪些操作
		    $('#initRfidCardForm input[name="rfidno"]').val('');   
		    $('#initrfidBtn').prop('disabled', false);  
			validForm('initRfidCardForm'); 
		}
     
		/* 打开RFID卡初始化页面 */
		$('#initRfidCardWindow').on('shown.bs.modal', function() {		
			loadFormData('initRfidCardForm', '<c:url value="/card/queryBcard.do?page=rfid"/>',function(formdata){
				OpenMediaReader(rfidType,onFindInitRFIDCard,onDropInitRFIDCard,function(data){//第一个参数是读卡器型号、第二个是打开卡操作，第三个参数是关闭读卡器操作
				
				}); 
			});
		});
		$('#initRfidCardWindow').on('hide.bs.modal', function() {			
			CloseMediaReader(rfidType,function(data){//关闭读卡器
				
			});
		});
		
		/**
		 *添加Rfid初始化
		 */
		function initRfidCard() {
			saveFormData('initRfidCardForm','<c:url value="/card/initCard.do"/>',function(data){				
				if (data.success) {
					successbox(data.msg);
					$('#CardGrid').bootstrapTable('refresh');
					$('#initRfidCardForm input[name="cardid"]').val('');
					$('#initRfidCardForm input[name="cardno"]').val($('#initRfidCardForm input[name="cardno"]').val()*1+1); 
					validForm('initRfidCardForm');
				} else {
					errorbox(data.msg);
				}
			});
		}
		//---------------------------------------IC发卡------------------------------------------------	
		/*打开IC发卡页面*/
		
		function onFindICard(data){//打卡读卡器进行哪些操作
			$('#fromBtn').prop('disabled', false); 
			$('#fromCardForm input[name="cardid"]').val(data.cardid);		
			$.ajax({
	            type: "post",
	            url: '<c:url value="/card/judgCarId.do"/>',
	            dataType: "json",
	            data: {cardid:data.cardid}, 
	            success: function(data){
	            	  if(!data.success){//卡和车辆状态正常，根据车号查询业务信息
	            		errorbox(data.msg);
	            		$('#fromBtn').prop('disabled', true); 
	            	 }
	            }
            });		
			validForm('fromCardForm');
		}
		
		function onDropICard(data){//关闭读卡器，进行哪些操作
			$('#fromCardForm input[name="cardid"]').val('');
			$('#fromCardForm input[name="cardtype"]').val('');
			$('#fromCardForm input[name="carno"]').val('');
			$('#fromCardForm input[name="driver"]').val('');
			$('#fromCardForm input[name="deposit"]').val('');
			$('#fromCardForm input[name="cartype"]').val('');
			$('#fromCardForm input[name="motorcadecode"]').val('');
			$('#fromCardForm input[name="motorcadename"]').val('');
			$('#fromCardForm input[name="usermemo"]').val('');
			$('#fromCardForm input[name="begintime"]').val('');
			$('#fromCardForm input[name="endtime"]').val('');
			$('#fromCardForm input[name="beginperiod"]').val('');
			$('#fromCardForm input[name="endperiod"]').val('');			      
			$('#fromBtn').prop('disabled', false); 
			validForm('fromCardForm');
		}
		
		
	
		$('#fromCardWindow').on('shown.bs.modal', function() {			
			loadFormData('fromCardForm', '<c:url value="/card/queryBcard.do"/>',function(formdata){
				OpenMediaReader(cardType,onFindICard,onDropICard,function(data){//第一个参数是读卡器型号、第二个是打开卡操作，第三个参数是关闭读卡器操作
					
				});
			});
		});
		
		$('#fromCardWindow').on('hide.bs.modal', function() {			
			CloseMediaReader(cardType,function(data){//关闭读卡器
				
			});
		});

		/**
		 *添加发卡
		 */
		function fromCard() {
			$('#fromBtn').prop('disabled', true); 
			$('#cardclassic').val(0);
			
			$("#frommotorcadename").val($('#frommotorcadecode option:selected').text())		
			saveFormData('fromCardForm','<c:url value="/card/fromCard.do"/>',function(data){
				if (data.success) {
					successbox(data.msg);
					$('#CardGrid').bootstrapTable('refresh');
					$('#fromCardForm input[name="cardid"]').val('');
					$('#fromCardForm input[name="cardtype"]').val('');
					$('#fromCardForm input[name="carno"]').val('');
					$('#fromCardForm input[name="driver"]').val('');
					$('#fromCardForm input[name="deposit"]').val('');
					$('#fromCardForm input[name="cartype"]').val('');
					$('#fromCardForm input[name="motorcadecode"]').val('');
					$('#fromCardForm input[name="motorcadename"]').val('');
					$('#fromCardForm input[name="usermemo"]').val('');
					$('#fromCardForm input[name="begintime"]').val('');
					$('#fromCardForm input[name="endtime"]').val('');
					$('#fromCardForm input[name="beginperiod"]').val('');
					$('#fromCardForm input[name="endperiod"]').val('');	
				} else {
					errorbox( data.msg);
				}				
			});
		}
		var carnoLayer;
		var flag;
		function checkCarno(type) {
			flag=type;
			carnoLayer = layer.open({
				type : 2,
				title : '车牌号选择',
				maxmin : true, //开启最大化最小化按钮
				area : [ '90%', '90%' ],
				content : '<c:url value="/bcommon/queryCardhead.do"/>'//注意，如果str是object，那么需要字符拼接。
			});
		}
		
		function takeBackCarno(carno_v){
			if(flag=='IC'){
				$('#fromCardForm input[name="carno"]').val(carno_v);
				validForm('fromCardForm');
			}else{
				$('#fromRfidCardForm input[name="carno"]').val(carno_v);
				validForm('fromRfidCardForm');
			}
		}
		
		function closeCheckCarno(){
			layer.close(carnoLayer);
		}
		//---------------------------Rfid发卡-----------------------------------------------------
		/*打开发卡页面*/
		function onFindRfidCard(data){//打卡读卡器进行哪些操作
			$('#fromRfidCard').prop('disabled', false); 
			$('#fromRfidCardForm input[name="cardid"]').val(data.cardid);		
			$.ajax({
	            type: "post",
	            url: '<c:url value="/card/judgCarId.do"/>',
	            dataType: "json",
	            data: {cardid:data.cardid}, 
	            success: function(data){
	            	  if(!data.success){//卡和车辆状态正常，根据车号查询业务信息
	            		errorbox(data.msg);
	            		$('#fromRfidCard').prop('disabled', true); 
	            	 }
	            }
            });		
			validForm('fromRfidCardForm');
		}
		
		function onDropRfidCard(data){//关闭读卡器，进行哪些操作
			$('#fromRfidCardForm input[name="cardid"]').val('');
			$('#fromRfidCardForm input[name="cardtype"]').val('');
			$('#fromRfidCardForm input[name="carno"]').val('');
			$('#fromRfidCardForm input[name="cartype"]').val('');
			$('#fromRfidCardForm input[name="motorcadecode"]').val('');
			$('#fromRfidCardForm input[name="motorcadename"]').val('');
			$('#fromRfidCardForm input[name="usermemo"]').val('');	      
			$('#fromRfidCard').prop('disabled', false); 
			validForm('fromRfidCardForm');
		}
		
		$('#fromRfidCardWindow').on('shown.bs.modal', function() {			
			loadFormData('fromRfidCardForm', '<c:url value="/card/queryBcard.do?page=rfidfrom"/>',function(formdata){
				OpenMediaReader(rfidType,onFindRfidCard,onDropRfidCard,function(data){//第一个参数是读卡器型号、第二个是打开卡操作，第三个参数是关闭读卡器操作
					
				});
			});
		});
		
		$('#fromRfidCardWindow').on('hide.bs.modal', function() {	
			//关闭读卡器
			CloseMediaReader(rfidType,function(data){});
		});
		

		/**
		 *添加Rfid发卡
		 */
		function fromRfidCard() {
			$('#fromRfidCard').prop('disabled', true);
			$('#cardclassrfid').val(1);
			$('#cardtyperfid').val(1);
			$('#cartyperfid').val(0);
			/* 
			$("#frommotorcadename").val($('#frommotorcadecode option:selected').text())	 */	
			saveFormData('fromRfidCardForm','<c:url value="/card/fromCard.do"/>',function(data){
				if (data.success) {
					successbox(data.msg);
					$('#fromRfidCardForm input[name="cardid"]').val('');
					$('#fromRfidCardForm input[name="cardtype"]').val('');
					$('#fromRfidCardForm input[name="carno"]').val('');
					$('#fromRfidCardForm input[name="cartype"]').val('');
					$('#fromRfidCardForm input[name="motorcadecode"]').val('');
					$('#fromRfidCardForm input[name="motorcadename"]').val('');
					$('#fromRfidCardForm input[name="usermemo"]').val('');	
					$('#CardGrid').bootstrapTable('refresh');
				} else {
					errorbox( data.msg);
				}				
			});
		}
		
		
	   //---------------------------------------IC退卡------------------------------------------------	
		/*打开退卡页面*/
		function onFindBackICard(data){//打卡读卡器进行哪些操作
			$('#backBtn').prop('disabled', false ); 
			var cardid = data.cardid;
			 $('#backCardForm input[name="cardid"]').val(cardid);
			 $.ajax({
	            type: "post",
	            url: '<c:url value="/card/judgCarno.do"/>',
	            dataType: "json",
	            data: {cardid:cardid}, 
	            success: function(data){
	            	 if(data.success ){//卡和车辆状态正常，根据车号查询业务信息
	            		loadFormData('backCardForm', '<c:url value="/card/queryinfoBycardid.do?cardid="/>'+cardid); 	            		
	            	 }else{
	            	    errorbox(data.msg);
		            	$('#backBtn').prop('disabled', true);  
	            	 }
	            }
            }); 
			 validForm('backCardForm');
		}
		
		function onDropBackICard(data){//关闭读卡器，进行哪些操作
			$('#backCardForm input[name="cardid"]').val('');
			$('#backCardForm input[name="cardtype"]').val('');
			$('#backCardForm input[name="carno"]').val('');
			$('#backCardForm input[name="driver"]').val('');
			$('#backCardForm input[name="deposit"]').val('');
			$('#backCardForm input[name="cardtype"]').val('');
			$('#backCardForm input[name="motorcadecode"]').val('');
			$('#backCardForm input[name="motorcadename"]').val('');
			$('#backCardForm input[name="memo"]').val('');
			$('#backBtn').prop('disabled', false); 
			validForm('backCardForm');
		}

		
		$('#backCardWindow').on('hide.bs.modal', function() {			
			CloseMediaReader(cardType,function(data){//关闭读卡器				
			});
		});
		$('#backCardWindow').on('shown.bs.modal', function() {
			OpenMediaReader(cardType,onFindBackICard,onDropBackICard,function(data){//第一个参数是读卡器型号、第二个是打开卡操作，第三个参数是关闭读卡器操作
			
			}); 
		});
		/**
		 *添加退卡
		 */
		function backCard() {
			$('#backBtn').prop('disabled', true); 
			saveFormData('backCardForm','<c:url value="/card/backCard.do"/>',function(data){
				if (data.success) {
					successbox(data.msg);
					$('#backCardForm input[name="cardid"]').val('');
					$('#backCardForm input[name="cardtype"]').val('');
					$('#backCardForm input[name="carno"]').val('');
					$('#backCardForm input[name="driver"]').val('');
					$('#backCardForm input[name="deposit"]').val('');
					$('#backCardForm input[name="cardtype"]').val('');
					$('#backCardForm input[name="motorcadecode"]').val('');
					$('#backCardForm input[name="motorcadename"]').val('');
					$('#backCardForm input[name="memo"]').val('');
					//$('#backCardWindow').modal('toggle');
					$('#CardGrid').bootstrapTable('refresh');
				} else {
					errorbox(data.msg);
				}
				
			});
		}
        //----------------------------------------RFID退卡----------------------------------------------
          
    	/*打开退卡页面*/
		/*function onFindBackRfidCard(data){//打卡读卡器进行哪些操作
			$('#backRfidBtn').prop('disabled', false ); 
			var cardid = data.cardid;
			 $('#backRfidCardForm input[name="cardid"]').val(cardid);
			 $.ajax({
	            type: "post",
	            url: '<c:url value="/card/judgCarno.do"/>',
	            dataType: "json",
	            data: {cardid:cardid}, 
	            success: function(data){
	            	 if(data.success ){//卡和车辆状态正常，根据车号查询业务信息
	            		loadFormData('backRfidCardForm', '<c:url value="/card/queryinfoBycardid.do?cardid="/>'+cardid); 	            		
	            	 }else{
	            	    errorbox(data.msg);
		            	$('#backRfidBtn').prop('disabled', true);  
	            	 }
	            }
            }); 
			 validForm('backRfidCardForm');
		}
		
		 function onDropBackRfidCard(data){//关闭读卡器，进行哪些操作
			$('#backRfidCardForm input[name="cardid"]').val('');
			$('#backRfidCardForm input[name="carno"]').val('');
			$('#backRfidCardForm input[name="motorcadecode"]').val('');
			$('#backRfidCardForm input[name="motorcadename"]').val('');
			$('#backRfidCardForm input[name="memo"]').val('');
			$('#backRfidBtn').prop('disabled', false); 
			validForm('backRfidCardForm');
		}

		
		$('#backRfidCardWindow').on('hide.bs.modal', function() {			
			CloseMediaReader(rfidType,function(data){//关闭读卡器				
			});
		}); */
		$('#backRfidCardWindow').on('shown.bs.modal', function() {
			/* OpenMediaReader(rfidType,onFindBackRfidCard,onDropBackRfidCard,function(data){//第一个参数是读卡器型号、第二个是打开卡操作，第三个参数是关闭读卡器操作
			
			}); */ 
			loadFormData('backRfidCardForm', '<c:url value="/card/queryinfoBycardid.do?cardid="/>'+$('#backRfidCardForm input[name="cardid"]').val())
		});
		/**
		 *添加退卡
		 */
		function backRfidCard() {
			$('#backRfidBtn').prop('disabled', true); 
			saveFormData('backRfidCardForm','<c:url value="/card/backCard.do"/>',function(data){
				if (data.success) {
					successbox(data.msg);
					$('#backRfidCardForm input[name="cardid"]').val('');
					$('#backRfidCardForm input[name="carno"]').val('');
					$('#backRfidCardForm input[name="motorcadecode"]').val('');
					$('#backRfidCardForm input[name="motorcadename"]').val('');
					$('#backRfidCardForm input[name="memo"]').val('');
					//$('#backCardWindow').modal('toggle');
					$('#CardGrid').bootstrapTable('refresh');
					$('#backRfidBtn').prop('disabled', false); 
				} else {
					errorbox(data.msg);
					$('#backRfidBtn').prop('disabled', false); 
				}
				
			});
		}
        
        
        
		/**
		 *挂失
		 */
		function cancelCard(cardid) {
			var selectedRows = $('#CardGrid').bootstrapTable('getSelections');
			if (selectedRows.length == 0) {
				warningbox("请选择挂失卡信息");
			} else {
				dialogbox("请确认", "确认挂失电子卡吗？",function(data){	
                  if(data){						
					$.ajax({
						type : "post",
						url : '<c:url value="/card/cancelCard.do"/>',
						dataType : "json",
						data : {cardid : selectedRows[0].cardid,cardclass:selectedRows[0].cardclass	},
						success : function(data) {
							if (data.success) {
								successbox(data.msg);
								$('#CardGrid').bootstrapTable('refresh');
							}else{
								errorbox(data.msg);
							}
						}
					});
				  }
				});
			}
		}
		function judgOrFromcarno() {
			$.ajax({
				type : "post",
				url : '<c:url value="/card/judgOrFromcarno.do"/>',
				dataType : "json",
				data : {carno : $('#fromCardForm input[name="carno"]').val(),recordtype:0},
				success : function(data) {					
					if (data.success==false) {
						errorbox(data.msg);
					} 
				}
			});
			
		}
		
		function updatetime(cardid) {
			var selectedRows = $('#CardGrid').bootstrapTable('getSelections');
			if (selectedRows.length != 0) {
				if(selectedRows[0].cardclass==0){
					if(selectedRows[0].validflag == "发卡"){
						if(selectedRows[0].cardtype == "固定卡"){
								if(selectedRows[0].cartype == "员工车辆"){
									$('#changeTimeForm input[name="cardid"]').val(selectedRows[0].cardid);
									$('#changeTimeWindow').modal('show');
									loadFormData('changeTimeForm', '<c:url value="/card/queryinfoBycardid.do?cardid="/>'+$('#changeTimeForm input[name="cardid"]').val())
								} else {
									errorbox("请选择员工车辆卡!")
								}
							} else {
								errorbox("请选择固定卡!")
							}
						
					} else {
						errorbox("请选择已发卡的IC卡!")
					}
				}else{
					errorbox("请选择IC卡!")
				}
			} else {
				errorbox("请选择需要修改有效期的IC卡!")
			}
		}
		
		function changeTime() {
			$('#changeBtn').prop('disabled', true); 
			saveFormData('changeTimeForm','<c:url value="/card/updatetime.do"/>',function(data){
				if (data.success) {successbox(data.msg);
					$('#changeTimeForm input[name="begintime"]').val('');
					$('#changeTimeForm input[name="endtime"]').val('');
					$('#changeTimeForm input[name="usermemo"]').val('');
					$('#CardGrid').bootstrapTable('refresh');
					$('#changeBtn').prop('disabled', false); 
				} else {
					errorbox(data.msg);
					$('#changeBtn').prop('disabled', false); 
				}
				
			});
		}
	</script>
</body>
</t:html>