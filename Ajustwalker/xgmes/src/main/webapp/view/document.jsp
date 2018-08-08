<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
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
</head>
<%
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		Date now = new Date();
		Calendar c = Calendar.getInstance();
		now = c.getTime();
		String begintime = dateFormat.format(now);//开始时间
		String endtime = dateFormat1.format(now);//结束时间
		String datetime = dateFormat2.format(now);//结束时间
		/* String operatype=request.getParameter("operatype");
		String secondflag=request.getParameter("secondflag"); */
%>
<body class="container-fluid" style="padding-top: 15px">
	<div class="row">
		<div class="col-sm-12">
			<form id="queryform">
				<input type="hidden" id="operatypes" name="operatype" value="${operatype}"> 
				<input type="hidden" name="secondflag" value="${secondflag}">
				 <input type="hidden" id="types" name="types" value="1">
				<input type="hidden" id="username" name="username" value="${username}"> 
				<input type="hidden" id="times" name="times" value="<%=begintime%>"> 
				<div class="row">
					<div class="col-sm-3">
						<div class='form-group input-group input-group-sm date'>
							<span class="input-group-addon">开始时间</span> <input type='text' class="form-control" placeholder="开始时间" id="begintime" name="begintime" value="<%=begintime%>" />
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon">单&nbsp;&nbsp;据&nbsp;&nbsp;号</span> <input type="text" class="form-control" placeholder="单据号" id="matchid" name="matchid" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">供　　货</span> <input type="text" class="form-control" placeholder="供货" name="sourcename" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">货　　名</span> <input type="text" class="form-control" placeholder="货名" id="materialname" name="materialname" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">当前用户</span> <input type="text" class="form-control" placeholder="制单人" id="creator" name="creator" value="${username}" aria-describedby="sizing-addon3">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-3">
						<div class='form-group input-group input-group-sm date'>
							<span class="input-group-addon">结束时间</span> <input type='text' class="form-control" placeholder="结束时间" id="endtime" name="endtime" value="<%=endtime%>" />
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">车　　号</span> <input type="text" class="form-control" placeholder="车号" id="carno" name="carno" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">收　　货</span> <input type="text" class="form-control" placeholder="收货" name="targetname" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-2 ">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">审核状态</span> <select id="auditlevel" name="auditlevel" class="form-control select2">
								<option value="-1">全部</option>
								<option value="0" <%if("1".equals(request.getParameter("reminder"))){%>selected="selected"<%}%>>一级未审核</option>
								<option value="2">一级已审核</option>
							</select>
						</div>
					</div>
					<div class="col-sm-2 ">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">使用状态</span> 
							 <select id="isoruse" name="isoruse" class="form-control select2">
								<option value="-1">全部</option>
								<option value="0">未使用</option>
								<option value="1">已使用</option>
								<option value="8">完成</option>
								<option value="2">终止</option>
							</select>
							
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-3">
						<t:combobox id="documentcode" name="documentcode" url="/bcommon/queryDtype.do?operatype=${operatype}" label="业务类型" require="false" alloptions="true" allowclear="true" value="${operatype}" />
					</div>
					
					<div class="form-group col-sm-6 btn-group-sm">
						<t:button text="查询" modulecode="otherdj" id="query" btnclass="btn btn-info" iconclass="glyphicon glyphicon-search" onclick="queryinfo()" />
						<t:button text="添加" modulecode="otherdj" id="DocumentBtn" btnclass="btn btn-success" iconclass="glyphicon glyphicon-th-large" />
						<t:button text="一级审核" modulecode="otherdj" id="appfirstBtn" btnclass="btn btn-primary" iconclass="glyphicon glyphicon-hand-up" onclick="updatefirst()" />
						<t:button text="一级弃审" modulecode="otherdj" id="giveupfirstBtn" btnclass="btn btn-warning" iconclass="glyphicon glyphicon-hand-down" onclick="giveupfirst()" />
						<button type="button" class="btn btn-danger" onclick="printbills()">
							<span class="glyphicon glyphicon-print" aria-hidden="true"></span>&nbsp;打印
						</button>
						<t:button text="复制" modulecode="otherdj" id="appsecondBtn" btnclass="btn btn-primary" iconclass="glyphicon glyphicon-th-large" />
<%-- 						<t:button text="二级审核" modulecode="otherdj" id="appsecondBtn" btnclass="btn btn-primary" iconclass="glyphicon glyphicon-hand-up" onclick="updatesecond()"/>
						    <t:button text="二级弃审" modulecode="otherdj" id="giveupsecondBtn" btnclass="btn btn-warning" iconclass="glyphicon glyphicon-hand-down"  onclick="giveupsecond()"/> --%>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<table id="DocumentInfoGrid" data-toggle="table" data-row-style="rowStyle" data-query-params="queryParams" data-pagination="true" data-page-size="10" data-page-list="[10,40,ALL]" data-mobile-responsive="true" >
				<thead>
					<tr>
						<th data-field="state" data-checkbox="true"></th>
						<th data-field="matchid" data-halign="center" data-searchable="true" class="text-center" sortable>单据号</th>
						<th data-field="operatype" class="text-center text-nowrap" data-searchable="true" sortable>单据类型</th>
						<%-- <th data-field="status" data-halign="center" data-searchable="true" class="text-nowrap">状态</th> --%>
						<th data-field="carno" data-halign="center" data-searchable="true" class="text-nowrap text-center">车号</th>
						<th data-field="sourcename" data-halign="center" data-searchable="true" class="text-nowrap text-left">供货</th>
						<th data-field="targetname" data-halign="center" data-searchable="true" class="text-nowrap text-left">收货</th>
					    <th data-field="materialname" data-halign="center" data-searchable="true" class="text-nowrap text-left">货名</th>
						<th data-field="snumber" data-halign="center" data-searchable="true" class="text-right text-right">数量</th>
						<th data-field="inoutdate" data-halign="center" data-searchable="true" class="text-nowrap text-center">到货时间</th>
						<th data-field="auditlevel" data-halign="center" data-searchable="true" class="text-right text-right" data-visible="false">审核等级</th>
						<th data-field="approverfirst" data-halign="center" data-searchable="true" class="text-center">一级审核人</th>
						<th data-field="firsttime" data-halign="center" data-searchable="true" class="text-nowrap text-center">一级审核时间</th>
						<%-- <th data-field="approversecond" data-halign="center" data-searchable="true" class="text-nowrap text-center">二级审核人</th>
							<th data-field="secondtime" data-halign="center" data-searchable="true" class="text-nowrap text-center">二级审核时间</th> --%>
						<th data-field="creator" data-halign="center" data-searchable="true" class="text-center">制单人</th>
						<th data-field="cdate" data-halign="center" data-searchable="true" class="text-nowrap">制单时间</th>
						<th data-field="isoruse" data-halign="center" data-searchable="true" class="text-center">是否使用</th>
						<th data-field="usermemo" data-halign="center" data-searchable="true" class="text-nowrap">备注</th>
						<th data-field="sysmemo" data-halign="center" data-searchable="true" class="text-nowrap">行备注</th>
						<th data-width="70px" data-align="center" data-valign="middle" data-formatter="operateFormatter" data-events="operateEvents">操作</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>

	<div class="modal fade" id="DocumentWindow" tabindex="-1" role="dialog" aria-labelledby="DocumentWindowLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">单据操作</h4>
				</div>
				<div class="modal-body">
					<form id="DocumentForm">
						<div class="row">
							<input type="hidden" id="matchids" name="matchid" value="-1" /> 
							<input type="hidden" id="firsttime" name="firsttime" value="-1" />
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">车　　　号</span> <input id="insertcarno" name="carno" type="text" class="form-control" aria-describedby="basic-addon1" required data-bv-notempty-message="请填写车号">
								</div>
							</div>
							<div class="col-sm-6">
								<t:combobox id="operatype" name="operatype" url="/bcommon/queryDtype.do?operatype=${operatype}" label="单&nbsp;据&nbsp;类&nbsp;型&nbsp;" />
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<!-- <span class="input-group-addon" onclick="checkFdocument()">原始单据</span> -->
									<span class="input-group-btn">
										<button class="btn btn-default" type="button" onclick="checkPlanid('0')">
											<span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>原始单据
										</button>
									</span> <input id="fdocumentno" name="fdocumentno" type="text" class="form-control" aria-describedby="basic-addon1">
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">业&nbsp;务&nbsp;名&nbsp;称&nbsp;</span> <input id="businessname" name="businessname" type="text" class="form-control text-nowrap" aria-describedby="basic-addon1">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">供　　　货</span> <input id="sourcename" name="sourcename" type="text" class="form-control" aria-describedby="basic-addon1" 　require="required">
									<%-- <t:combobox id="sourcecode" name="sourcecode"  url="/bcommon/querySourceinfo.do" label="供　　货" require="true"/> --%>
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">收　　　货</span> <input id="targetname" name="targetname" type="text" class="form-control" aria-describedby="basic-addon1" 　require="required">
									<%--  <t:combobox id="targetcode" name="targetcode"  url="/bcommon/queryTargetinfo.do" label="收　　货" require="true"/> --%>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">经　办　人</span> <input name="saleman" type="text" class="form-control" aria-describedby="basic-addon1">
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm date">
									<span class="input-group-addon">日　　　期</span> <input type="text" class="form-control" placeholder="进/出厂日期" id="inoutdate" name="inoutdate" required="required" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon" >是&nbsp;否&nbsp;计&nbsp;重&nbsp;</span> <select id="isormeasure" name="isormeasure" class="form-control select2" required="required">
										<option value="1">是</option>
										<option value="0">否</option>
									</select>
								</div>
							</div>
							<div class="col-sm-6">
								
								 <t:combobox id="approverfirst" name="approverfirst" url="/bcommon/queryUsername.do" label="审　核　人" pagination="true" alloptions="true" allowclear="true"/>
								<!--  <span class="input-group-addon">审&nbsp;&nbsp;核&nbsp;&nbsp;人</span> <input type="text" class="form-control" placeholder="审核人" id="approverfirst" name="approverfirst" required="required" />
								 --> 
							</div>

						</div>
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">事　　　由</span> <input name="reason" type="text" class="form-control" aria-describedby="basic-addon1">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">备　　　注</span> <input name="memo" type="text" class="form-control" aria-describedby="basic-addon1">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<table id="DocumentDetailGrid" data-mobile-responsive="true" data-unique-id="itemid"></table>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer  btn-group-sm">
					<button type="button" class="btn btn-success" id="appaddBtn" onclick="insert()">确认</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="CpDocumentWindow" tabindex="-1" role="dialog" aria-labelledby="DocumentWindowLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">单据复制</h4>
				</div>
				<div class="modal-body">
					<form id="CpDocumentForm">
						<div class="row">
							<input type="hidden" id="Cpmatchids" name="matchid" value="-1" /> 
							<input type="hidden" id="Cpfirsttime" name="firsttime" value="-1" />
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">车　　　号</span> <input id="cpcarno" name="carno" type="text" class="form-control" aria-describedby="basic-addon1" required data-bv-notempty-message="请填写车号">
								</div>
							</div>
							<div class="col-sm-6">
								<t:combobox id="Cpoperatype" name="operatype" url="/bcommon/queryDtype.do?operatype=${operatype}" label="单&nbsp;据&nbsp;类&nbsp;型&nbsp;" />
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<!-- <span class="input-group-addon" onclick="checkFdocument()">原始单据</span> -->
									<span class="input-group-btn">
										<button class="btn btn-default" type="button" onclick="checkPlanid('1')">
											<span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>原始单据
										</button>
									</span> <input id="Cpfdocumentno" name="fdocumentno" type="text" class="form-control" aria-describedby="basic-addon1">
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">业&nbsp;务&nbsp;名&nbsp;称&nbsp;</span> <input id="Cpbusinessname" name="businessname" type="text" class="form-control text-nowrap" aria-describedby="basic-addon1">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">供　　　货</span> <input id="Cpsourcename" name="sourcename" type="text" class="form-control" aria-describedby="basic-addon1" 　require="required">
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">收　　　货</span> <input id="Cptargetname" name="targetname" type="text" class="form-control" aria-describedby="basic-addon1" 　require="required">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">经　办　人</span> <input name="saleman" type="text" class="form-control" aria-describedby="basic-addon1">
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm date">
									<span class="input-group-addon">日　　　期</span> <input type="text" class="form-control" placeholder="进/出厂日期" id="Cpinoutdate" name="inoutdate" required="required" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon" >是&nbsp;否&nbsp;计&nbsp;重&nbsp;</span> <select id="Cpisormeasure" name="isormeasure" class="form-control select2" required="required">
										<option value="1">是</option>
										<option value="0">否</option>
									</select>
								</div>
							</div>
							<div class="col-sm-6">
								 <t:combobox id="Cpapproverfirst" name="approverfirst" url="/bcommon/queryUsername.do" label="审　核　人" pagination="true" alloptions="true" allowclear="true"/>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">事　　　由</span> <input name="reason" type="text" class="form-control" aria-describedby="basic-addon1">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">备　　　注</span> <input name="memo" type="text" class="form-control" aria-describedby="basic-addon1">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<table id="CpDocumentDetailGrid" data-mobile-responsive="true" data-unique-id="itemid"></table>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer  btn-group-sm">
					<button type="button" class="btn btn-success" id="appaddCpBtn" onclick="insertCP()">确认</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="showDetailDocumentWindow" tabindex="-1" role="dialog" aria-labelledby="DocumentWindowLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">单据明细</h4>
				</div>
				<div class="modal-body">
					<form id="showDocumentForm">
						<div class="row">
							<input type="hidden" id="showmatchids" name="matchid" value="-1" /> 
							<input type="hidden" id="firsttime" name="firsttime" value="-1" />
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">车　　号</span> <input name="carno" type="text" class="form-control" aria-describedby="basic-addon1" required data-bv-notempty-message="请填写车号"  disabled="disabled">
								</div>
							</div>
							<div class="col-sm-6">
								<t:combobox id="showoperatype" name="operatype" url="/bcommon/queryDtype.do?operatype=${operatype}" label="单据类型" disable="true" />
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">原始单据</span>
									 <input id="fdocumentno" name="fdocumentno" type="text" class="form-control" aria-describedby="basic-addon1" disabled="disabled" >
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">业务名称</span> <input id="businessname" name="businessname" type="text" class="form-control text-nowrap" aria-describedby="basic-addon1" disabled="disabled">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">供　　货</span> <input id="sourcename" name="sourcename" type="text" class="form-control" aria-describedby="basic-addon1" 　require="required" disabled="disabled" >
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">收　　货</span> <input id="targetname" name="targetname" type="text" class="form-control" aria-describedby="basic-addon1" 　require="required" disabled="disabled" >
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">经&nbsp;&nbsp;办&nbsp;&nbsp;人</span> <input name="saleman" type="text" class="form-control" aria-describedby="basic-addon1" disabled="disabled" >
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm date">
									<span class="input-group-addon">日　　期</span> <input type="text" class="form-control" placeholder="进/出厂日期" id="inoutdate" name="inoutdate" required="required" disabled="disabled" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon" >是否计重</span> <select id="isormeasure" name="isormeasure" class="form-control select2" required="required" disabled="disabled" >
										<option value="1">是</option>
										<option value="0">否</option>
									</select>
								</div>
							</div>
							<div class="col-sm-6">
								 <t:combobox id="shapproverfirst" name="approverfirst" url="/bcommon/queryUsername.do" label="审&nbsp;&nbsp;核&nbsp;&nbsp;人" pagination="true" alloptions="true" allowclear="true" disable="true" />
							</div>

						</div>
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">事　　由</span> <input name="reason" type="text" class="form-control" aria-describedby="basic-addon1" disabled="disabled" >
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">备　　注</span> <input name="memo" type="text" class="form-control" aria-describedby="basic-addon1" disabled="disabled" >
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<table id="showDocumentDetailGrid" data-mobile-responsive="true" data-unique-id="itemid"></table>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer  btn-group-sm">					
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var rowscount = 0;
		var operatype = $("#operatype").val();

		function prepend() {
			$('#DocumentDetailGrid').bootstrapTable('prepend', appendData());
		}
		function prependcp() {
			$('#CpDocumentDetailGrid').bootstrapTable('prepend', appendData());
		}

		function appendData() {
			var rows = [];
			rows.push({
				itemid : 'add' + rowscount,
				deletes : rowscount,
				materialcode : '',
				materialname : '',
				materialcounts : '',
				snumber : '',
				measureunit : '',
				isorin : '0',
				returntime : '',
				returntotal : '',
				isorout : '0',
				outtime : '',
				outtotal : '',
				usermemo : '',
			});
			rowscount++;
			return rows;
		}

		function deletestr(obj) {
			$('#DocumentDetailGrid').bootstrapTable('removeByUniqueId', obj);
		}
		function deletestrcp(obj) {
			$('#CpDocumentDetailGrid').bootstrapTable('removeByUniqueId', obj);
		}

		function deletesFormatter(value, row, index) {
			return "<div class='btn-group-sm'><button type='button' class='btn btn-danger'onclick='deletestr(\""
					+ row.itemid
					+ "\")'><span class='glyphicon glyphicon-minus' aria-hidden='true'></span></button></div>";
		}
		function deletesFormattercp(value, row, index) {
			return "<div class='btn-group-sm'><button type='button' class='btn btn-danger'onclick='deletestrcp(\""
					+ row.itemid
					+ "\")'><span class='glyphicon glyphicon-minus' aria-hidden='true'></span></button></div>";
		}

		jQuery(document).ready(function($) {
			var selects = $("#documentcode option");
			if (1 == selects.length) {
				$('#documentcode').val(selects[0].value).trigger('change', {
					term : selects[0].value,
					setvalue : true,
					remote : false
				});
			}
			queryinfo();

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
			$('#inoutdate').datetimepicker({
				format : 'YYYY-MM-DD',
				locale : 'zh-cn'
			});
			
			$("#inoutdate").on("dp.change", function(e) {
				$('#inoutdate').data("DateTimePicker").minDate($("#times").val());
			});
			$("#Cpinoutdate").on("dp.change", function(e) {
				$('#Cpinoutdate').data("DateTimePicker").minDate($("#times").val());
			});
			$('#Cpinoutdate').datetimepicker({
				format : 'YYYY-MM-DD',
				locale : 'zh-cn'
			});
			
		});

		function queryParams(params) {
			return jQuery.extend({}, params, $('#queryform').serializeJson());
		}

		function queryinfo() {
			$('#DocumentInfoGrid').bootstrapTable('refresh', {
				url : "<c:url value='/application/queryDocumentPage.do'/>"
			});
		}

		var openedPrintPanel;
		function printbills() {
			var matchids = '';
			var selectedRows = $('#DocumentInfoGrid').bootstrapTable('getAllSelections');
			if (0 == selectedRows.length) {
				errorbox('请至少选择一条数据进行打印');
				return;
			}
			for (var i = 0; i < selectedRows.length; i++) {
				matchids = matchids + ',' + selectedRows[i].matchid;
			}
			matchids = matchids.substr(1);
			openedPrintPanel = layer.open({
				type : 2,
				title : '计量信息打印',
				maxmin : true, //开启最大化最小化按钮
				area : [ '90%', '90%' ],
				content : '<c:url value="/print/printqtbill.do"/>?matchid=' + matchids
			});
		}
		
		function closePrintbillPanel(){
			layer.close(openedPrintPanel);
		}

		function operateFormatter(value, row, index) {
			return [
					'<div class="pull-right" style="width:60px;">',
					'<t:ibutton text="修改" modulecode="otherdj" id="documentmodify" btnclass="edit" iconclass="glyphicon glyphicon-pencil"/>',
					'<t:ibutton text="移除" modulecode="otherdj" id="documentremove" btnclass="remove" iconclass="glyphicon glyphicon-trash"/>',
					'<a class="detail" href="javascript:void(0)" title="详情">',
					'<i class="glyphicon glyphicon-list-alt"></i>', '</a>',
					'</div>' ].join('');
		}

		window.operateEvents = {
			'click .edit' : function(e, value, row) {
				openDocumentWindow(row.matchid,row.creator, row.firsttime);
			},
			'click .remove' : function(e, value, row) {
				cancelDocument(row.matchid,row.creator, row.firsttime);
			},
			'click .detail' : function(e, value, row) {
				layer.open({
					type : 2,
					title : '信息详情',
					maxmin : true, //开启最大化最小化按钮
					area : [ '90%', '90%' ],
					content : '<c:url value="/bcommon/showOtherDetail.do?type=1&ids="/>'+ row.itemid //注意，如果str是object，那么需要字符拼接。
				});
			}
		};

		$('#DocumentBtn').on('click', function() {
			$("#cpflag").val(0);
			openDocumentWindow(-1);
		});
		$('#appsecondBtn').on('click', function() {
			var matchid='';
			var selectedRows = $('#DocumentInfoGrid').bootstrapTable('getAllSelections');
			if (0 == selectedRows.length) {
				errorbox('请选择需要复制的信息');
				return;
			}else if(1==selectedRows.length){
				matchid=selectedRows[0].matchid
			}else{
				errorbox('请选择一条数据进行复制');
				return;
			}
			openCopyDocumentWindow(matchid);
		});

		

		function openCopyDocumentWindow(matchid, firsttime) {
			$("#Cpmatchids").val(matchid);
			$('#CpDocumentWindow').modal('show');
		}
		$('#CpDocumentWindow').on('shown.bs.modal',function() {
			var matchid_v = $("#Cpmatchids").val();
			var firsttime = $("#Cpfirsttime").val();
			loadFormData('CpDocumentForm','<c:url value="/application/queryApplicationbill.do?matchid="/>'
							+ matchid_v + "&typecode="+ $("#operatypes").val()+ "&type=document",
				function(data) {
				   $('#Cpapproverfirst').val(null).trigger('change');
						$("#Cpmatchids").val(matchid_v);
						var operatype = data.operatype;
						if (operatype == '108'|| operatype == '111') {//维修加工物资出厂、施工物资进厂，业务名称可编辑、原始单据不可编辑
							$("#Cpfdocumentno").attr("readonly",true);
							$("#Cpbusinessname").attr("readonly",false);
							$("#Cpisormeasure").val(0);
							$("#Cpisormeasure").trigger('change.select2');
							$("#Cpisormeasure").attr("disabled",true);
							validForm('CpDocumentForm');
						} else if (operatype == '109'|| operatype == '110') {//维修加工物资返厂、施工物资出厂：原始单据、业务名称可编辑，是否过磅不可编辑
							$("#Cpfdocumentno").attr("readonly",false);
							$("#Cpbusinessname").attr("readonly",false);
							$("#Cpisormeasure").val(0);
							$("#Cpisormeasure").trigger('change.select2');
							$("#Cpisormeasure").attr("disabled",true);
							validForm('CpDocumentForm');
						} else {
							$("#Cpfdocumentno").attr("readonly",true);
							$("#Cpbusinessname").attr("readonly",true);
						}
						
						$.ajax({
								type : "post",
								url : '<c:url value="/bcommon/queryModelcontent.do?operatype="/>'+ operatype+ "&flag=4",
								dataType : "json",
								success : function(data) {
									var cs = JSON.parse(data.msg);
									$('#CpDocumentDetailGrid').bootstrapTable('destroy').bootstrapTable({columns : cs}).bootstrapTable('refresh',{url : "<c:url value='/application/queryAppInfo.do?matchid='/>"+ matchid_v});
								}
						});
					});
		});
		function openDocumentWindow(id_vv,creator, firsttime) {
			var username = $("#username").val();
			$("#matchids").val(id_vv);
			$("#cpflag").val(0);
			if (firsttime == "" || firsttime == null) {
				if( id_vv==-1){
					$('#DocumentWindow').modal('show');
				}else{
					if(username==creator){
						$('#DocumentWindow').modal('show');
					}else{
						warningbox("不允许修改其他用户单据");
					}
				}
			} else {
				warningbox("已经审核不允许修改");
			}
		}
		$('#DocumentWindow').on('shown.bs.modal',function() {
			var matchid_v = $("#matchids").val();
			var firsttime = $("#firsttime").val();
			loadFormData('DocumentForm','<c:url value="/application/queryApplicationbill.do?matchid="/>'
							+ matchid_v + "&typecode="+ $("#operatypes").val()+ "&type=document",
					function(data) {
				   $('#approverfirst').val(null).trigger('change');
						$("#matchids").val(matchid_v);
						var operatype = data.operatype;
						if (operatype == '108'|| operatype == '111') {//维修加工物资出厂、施工物资进厂，业务名称可编辑、原始单据不可编辑
							$("#fdocumentno").attr("readonly",true);
							$("#businessname").attr("readonly",false);
							$("#isormeasure").val(0);
							$("#isormeasure").trigger('change.select2');
							$("#isormeasure").attr("disabled",true);
							validForm('DocumentForm');
						} else if (operatype == '109'|| operatype == '110') {//维修加工物资返厂、施工物资出厂：原始单据、业务名称可编辑，是否过磅不可编辑
							$("#fdocumentno").attr("readonly",false);
							$("#businessname").attr("readonly",false);
							$("#isormeasure").val(0);
							$("#isormeasure").trigger('change.select2');
							$("#isormeasure").attr("disabled",true);
							validForm('DocumentForm');
						} else {
							$("#fdocumentno").attr("readonly",true);
							$("#businessname").attr("readonly",true);
						}
						
						$.ajax({
								type : "post",
								url : '<c:url value="/bcommon/queryModelcontent.do?operatype="/>'+ operatype+ "&flag=3",
								dataType : "json",
								success : function(data) {
									var cs = JSON.parse(data.msg);
									$('#DocumentDetailGrid').bootstrapTable('destroy').bootstrapTable({columns : cs}).bootstrapTable('refresh',{url : "<c:url value='/application/queryAppInfo.do?matchid='/>"+ matchid_v});
								}
						});
					});
		});
		$('#DocumentWindow').on('shown.bs.modal',function() {
			var matchid_v = $("#matchids").val();
			var firsttime = $("#firsttime").val();
			loadFormData('DocumentForm','<c:url value="/application/queryApplicationbill.do?matchid="/>'
							+ matchid_v + "&typecode="+ $("#operatypes").val()+ "&type=document",
					function(data) {$('#approverfirst').val(null).trigger('change');
						$("#matchids").val(matchid_v);
						var operatype = data.operatype;
						if (operatype == '108'|| operatype == '111') {//维修加工物资出厂、施工物资进厂，业务名称可编辑、原始单据不可编辑
							$("#fdocumentno").attr("readonly",true);
							$("#businessname").attr("readonly",false);
							$("#isormeasure").val(0);
							$("#isormeasure").trigger('change.select2');
							$("#isormeasure").attr("disabled",true);
						} else if (operatype == '109'|| operatype == '110') {//维修加工物资返厂、施工物资出厂：原始单据、业务名称可编辑，是否过磅不可编辑
							$("#fdocumentno").attr("readonly",false);
							$("#businessname").attr("readonly",false);
							$("#isormeasure").val(0);
							$("#isormeasure").trigger('change.select2');
							$("#isormeasure").attr("disabled",true);
						} else {
							$("#fdocumentno").attr("readonly",true);
							$("#businessname").attr("readonly",true);
						}
						validForm('DocumentForm');
						$.ajax({
								type : "post",
								url : '<c:url value="/bcommon/queryModelcontent.do?operatype="/>'+ operatype+ "&flag=3",
								dataType : "json",
								success : function(data) {
									var cs = JSON.parse(data.msg);
									$('#DocumentDetailGrid').bootstrapTable('destroy').bootstrapTable({columns : cs}).bootstrapTable('refresh',{url : "<c:url value='/application/queryAppInfo.do?matchid='/>"+ matchid_v});
								}
						});
					});
		});
		$('#DocumentInfoGrid').bootstrapTable({
			onDblClickRow : function(row, $element) {
				openshowDetailWindow(row.matchid);
			}
		});
		function openshowDetailWindow(id_vv, firsttime) {
			$("#showmatchids").val(id_vv);
			$('#showDetailDocumentWindow').modal('show');
		}

		

		$('#showDetailDocumentWindow').on('shown.bs.modal',function() {
			var matchid_v = $("#showmatchids").val();
			loadFormData('showDocumentForm','<c:url value="/application/queryApplicationbill.do?matchid="/>'
							+ matchid_v + "&typecode="+ $("#operatypes").val()+ "&type=document",
					function(data) {$('#approverfirst').val(null).trigger('change');
						$("#showmatchids").val(matchid_v);
						var operatype = data.operatype;
						$.ajax({
								type : "post",
								url : '<c:url value="/bcommon/queryModelcontent.do?operatype="/>'+ operatype+ "&flag=5",
								dataType : "json",
								success : function(data) {
									var cs = JSON.parse(data.msg);
									$('#showDocumentDetailGrid').bootstrapTable('destroy').bootstrapTable({columns : cs}).bootstrapTable('refresh',{url : "<c:url value='/application/queryAppInfo.do?matchid='/>"+ matchid_v});
						}
				});
			});
		});

		/**
		 *作废单据信息
		 */
		function cancelDocument(matchid,creator, firsttime) {
			var username = $("#username").val();
			if(username==creator){
				if (firsttime == null || firsttime == "") {
					dialogbox("请确认","确认删除此项目？",function(data) {
						if (data) {
							$.ajax({
								type : "post",
								url : '<c:url value="/application/cancelDocument.do"/>',
								dataType : "json",
								data : {
									matchid : matchid
								},
								success : function(data) {
									if (data.success == true) {
										successbox(data.msg);
										$('#DocumentInfoGrid').bootstrapTable('refresh');
									} else {
										errorbox(data.msg);
									}
								}
							});
						}
					});

				} else {
					errorbox("已经审核不允许作废");
				}
			}else{
				errorbox("不允许作废其他用户单据");
			}
			
		}
		
		function isNumberic(value){
			return /^[0-9]+.?[0-9]*$/.test(value);
		}

		function insert() {
			validForm('DocumentForm');
			var gridData = $('#DocumentDetailGrid').bootstrapTable('getData');
			var isormeasure=$("#isormeasure").val();
			var msginfo = '';
			if (gridData.length == 0) {
				errorbox("请选择物料信息 ");
			} else {
				for (var i = 0; i < gridData.length; i++) {
					if (gridData[i].materialname == null|| gridData[i].materialname == ''|| gridData[i].materialname == '请选择') {
						msginfo = msginfo + "第" + (i * 1 + 1) + "行，物料信息为空</br>";
					}
					if (gridData[i].snumber == null|| gridData[i].snumber == ''|| gridData[i].snumber == 0) {
						msginfo = msginfo + "第" + (i * 1 + 1) + "行，数量信息为空</br>";
					}else{
						if(!isNumberic(gridData[i].snumber)){
							msginfo = msginfo + "第" + (i * 1 + 1) + "行，数量信息必须为数字</br>";
						}
					}
					if (gridData[i].measureunit == null|| gridData[i].measureunit == ''|| gridData[i].measureunit == '请选择') {
						msginfo = msginfo + "第" + (i * 1 + 1)+ "行，计量单位信息为空</br>";
					}
					if (gridData[i].materialcounts > 0) {
						var materialcounts = gridData[i].materialcounts * 1;
						if (gridData[i].snumber > 0 && gridData[i].returntotal > 0) {
							var num = gridData[i].snumber * 1
									+ gridData[i].returntotal * 1;
							if (num > materialcounts) {
								msginfo = msginfo + "第" + (i * 1 + 1)+ "行，数量超过原始单据添加数量</br>";
							}
						}

						if (gridData[i].snumber > 0 && gridData[i].outtotal > 0) {
							var num = gridData[i].snumber * 1+ gridData[i].outtotal * 1;
							if (num > materialcounts) {
								msginfo = msginfo + "第" + (i * 1 + 1)+ "行，数量超过原始单据添加数量</br>";
							}
						}

					}
				}
				if (msginfo.length == 0) {
					$.ajax({
			            type: "post",
			            url: '<c:url value="/bcommon/judgOrBlackCarno.do"/>',
			            dataType: "json",
			            data: {carno:$("#insertcarno").val()}, 
			            success: function(data){
			            	  if(data.success){//卡和车辆状态正常，根据车号查询业务信息
			            		  saveFormDataWithParams('DocumentForm','<c:url value="/application/insertDocument.do"/>',gridData, function(data) {
										if (data.success) {
											successbox(data.msg);
											$('#DocumentWindow').modal('toggle');
											$('#DocumentInfoGrid').bootstrapTable('refresh');
										} else {
											errorbox(data.msg);
										}
									});	
			            	 }else{//如果车辆或者卡有问题，系统提示
			            		errorbox(data.msg); 
			            	 }  
			            }
		            }); 
				} else {
					errorbox(msginfo);
				}

			}

		}
		
		function insertCP() {
			validForm('CpDocumentForm');
			
			var gridData = $('#CpDocumentDetailGrid').bootstrapTable('getData');
			var isormeasure=$("#Cpisormeasure").val();
			var msginfo = '';
			if (gridData.length == 0) {
				errorbox("请选择物料信息 ");
			} else {
				for (var i = 0; i < gridData.length; i++) {
					if (gridData[i].materialname == null|| gridData[i].materialname == ''|| gridData[i].materialname == '请选择') {
						msginfo = msginfo + "第" + (i * 1 + 1) + "行，物料信息为空</br>";
					}
					if (gridData[i].snumber == null|| gridData[i].snumber == ''|| gridData[i].snumber == 0) {
						msginfo = msginfo + "第" + (i * 1 + 1) + "行，数量信息为空</br>";
					}else{
						if(!isNumberic(gridData[i].snumber)){
							msginfo = msginfo + "第" + (i * 1 + 1) + "行，数量信息必须为数字</br>";
						}
					}
					if (gridData[i].measureunit == null|| gridData[i].measureunit == ''|| gridData[i].measureunit == '请选择') {
						msginfo = msginfo + "第" + (i * 1 + 1)+ "行，计量单位信息为空</br>";
					}
					if (gridData[i].materialcounts > 0) {
						var materialcounts = gridData[i].materialcounts * 1;
						if (gridData[i].snumber > 0 && gridData[i].returntotal > 0) {
							var num = gridData[i].snumber * 1
									+ gridData[i].returntotal * 1;
							if (num > materialcounts) {
								msginfo = msginfo + "第" + (i * 1 + 1)+ "行，数量超过原始单据添加数量</br>";
							}
						}

						if (gridData[i].snumber > 0 && gridData[i].outtotal > 0) {
							var num = gridData[i].snumber * 1+ gridData[i].outtotal * 1;
							if (num > materialcounts) {
								msginfo = msginfo + "第" + (i * 1 + 1)+ "行，数量超过原始单据添加数量</br>";
							}
						}

					}
				}
				
                $("#Cpmatchids").val("-1");
				if (msginfo.length == 0) {
					$.ajax({
			            type: "post",
			            url: '<c:url value="/bcommon/judgOrBlackCarno.do"/>',
			            dataType: "json",
			            data: {carno:$("#cpcarno").val()}, 
			            success: function(data){
			            	  if(data.success){//卡和车辆状态正常，根据车号查询业务信息
			            		  saveFormDataWithParams('CpDocumentForm','<c:url value="/application/insertDocument.do"/>',gridData, function(data) {
										if (data.success) {
											successbox(data.msg);
											$('#CpDocumentWindow').modal('toggle');
											$('#DocumentInfoGrid').bootstrapTable('refresh');
										} else {
											errorbox(data.msg);
										}
									});	
			            	 }else{//如果车辆或者卡有问题，系统提示
			            		errorbox(data.msg); 
			            	 }  
			            }
		            }); 
					
					
					
					
					  	
				} else {
					errorbox(msginfo);
				}

			}

		}

		/**
		 *一级审核
		 */
		function updatefirst() {
			var matchid = "";
			var flag = 0;
			var level = 0;
			var manflag=0;
			var username = $("#username").val();
			var rows = $('#DocumentInfoGrid').bootstrapTable('getSelections');
			if (rows.length == 0) {
				errorbox("请选择审核信息");
			} else {
				for (var i = 0; i < rows.length; i++) {
					if (rows[i].auditlevel > 0) {
						if (rows[i].firsttime == ""|| rows[i].firsttime == null) {
							matchid = matchid + rows[i].matchid + ",";
							if(rows[i].approverfirst!=username){
								manflag++;
							}
						} else {
							flag++;
						}
					} else {
						level++;
					}
				}
				if (flag == 0 && level == 0 && manflag==0) {
					dialogbox("请确认","确认审核单据吗？",function(data) {
						if (data) {
							$.ajax({
									type : "post",
									url : '<c:url value="/application/updatefirst.do"/>',
									dataType : "json",
									data : {matchid : matchid},
									success : function(data) {
										if (data.success == true) {
											successbox(data.msg);
											$('#DocumentInfoGrid').bootstrapTable('refresh');
										} else {
											errorbox(data.msg);
										}
									}
							});
						}
					});
				} else if (flag > 0 ) {
					errorbox("单据已经审核");
				} else if ( level > 0) {
					errorbox("单据无需 审核");
				}else if ( manflag>0) {
					errorbox("不是指定审核人，不允许审核");
				}
			}
		}

		/**
		 *二级审核
		 */
		function updatesecond() {
			var matchid = "";
			var flag = 0;
			var level = 0;
			var rows = $('#DocumentInfoGrid').bootstrapTable('getSelections');
			if (rows.length == 0) {
				errorbox("请选择审核信息");
			} else {
				for (var i = 0; i < rows.length; i++) {
					if (rows[i].auditlevel == 2) {
						if (rows[i].secondtime == null|| rows[i].secondtime == "") {
							matchid = matchid + rows[i].matchid + ",";
						} else {
							flag++;
						}
					} else {
						level++;
					}
				}
				if (flag == 0 && level == 0) {
					dialogbox("请确认","确认审核单据吗？",function(data) {
						if (data) {
							$.ajax({
								type : "post",
								url : '<c:url value="/application/updatesecond.do"/>',
								dataType : "json",
								data : {matchid : matchid},
								success : function(data) {
									if (data.success == true) {
										successbox(data.msg);
										$('#DocumentInfoGrid').bootstrapTable('refresh');
									} else {
										errorbox(data.msg);
									}
								}
							})
						}
					});
				} else if (flag > 0 && level == 0) {
					errorbox("单据已经审核");
				} else if (flag == 0 && level > 0) {
					errorbox("单据无需二级审核");
				}
			}
		}

		/**
		 *一级弃审
		 */
		function giveupfirst() {
			var firstflag = 0;
			var secondflag = 0;
			var matchid = "";
			var level = 0;
			var manflag=0;
			var username = $("#username").val();
			var rows = $('#DocumentInfoGrid').bootstrapTable('getSelections');
			if (rows.length == 0) {
				errorbox("请选择弃审信息");
			} else {
				for (var i = 0; i < rows.length; i++) {
					if (rows[i].auditlevel == 2) {//2级审核
						if (rows[i].secondtime == null|| rows[i].secondtime == "") {//审核时间为空系统任务未进行审核
							matchid = matchid + rows[i].matchid + ",";
						} else {
							secondflag++;
							if(rows[i].approverfirst!=username){
								manflag++;
							} 
						}
					} else if (rows[i].auditlevel == 1) {//一级审核
						if (rows[i].firsttime == null || rows[i].firsttime == "") {//审核时间为空系统任务未进行审核
							firstflag++;
						} else {
							matchid = matchid + rows[i].matchid + ",";
							if(rows[i].approverfirst!=username){
								manflag++;
							} 
						}
					} else {//无需审核
						level++;
					}
				}
				if (level == 0 && firstflag == 0 && secondflag == 0 && manflag==0) {
					dialogbox("请确认","确认弃审单据吗？",function(data) {
						if (data) {
							$.ajax({
								type : "post",
								url : '<c:url value="/application/giveupfirst.do"/>',
								dataType : "json",
								data : {matchid : matchid},
								success : function(data) {
									if (data.success == true) {
										successbox(data.msg);
										$('#DocumentInfoGrid').bootstrapTable('refresh');
									} else {
										errorbox(data.msg);
									}
								}
							});
						}
					});

				} else if (level > 0 ) {
					errorbox("单据无需弃审");
				} else if (firstflag > 0 ) {
					errorbox("单据未审核无需弃审");
				} else if ( secondflag > 0) {
					errorbox("二级已经审核，不允许弃审");
				}else if ( manflag > 0) {
					errorbox("非审核人员，不允许弃审");
				}
			}
		}

		/**
		 *二级弃审
		 */
		function giveupsecond() {
			var flag = 0;
			var matchid = "";
			var rows = $('#DocumentInfoGrid').bootstrapTable('getSelections');
			if (rows.length == 0) {
				errorbox("请选择弃审信息");
			} else {
				for (var i = 0; i < rows.length; i++) {
					if (rows[i].secondtime == null || rows[i].secondtime == "") {//审核时间为空系统任务未进行审核
						flag++;
					} else {
						matchid = matchid + rows[i].matchid + ",";
					}
				}
				if (flag == 0) {
					dialogbox("请确认","确认弃审单据吗？",function(data) {
								if (data) {
									$.ajax({
										type : "post",
										url : '<c:url value="/application/giveupsecond.do"/>',
										dataType : "json",
										data : {matchid : matchid},
										success : function(data) {
											if (data.success == true) {
												successbox(data.msg);
												$('#DocumentInfoGrid').bootstrapTable('refresh');
											} else {
												errorbox(data.msg);
											}
										}
									});
								}
							});
				} else {
					errorbox("单据未审核，无需弃审");
				}

			}
		}

		var carnoLayer;
		function checkFdocument(types) {
			var fdocumentno ;
			if(types==0){//单据添加
				fdocumentno=$("#fdocumentno").val().trim()
			}else{//单据复制
				fdocumentno=$("#Cpfdocumentno").val().trim()
			}
			if (fdocumentno == null || fdocumentno == '') {
				errorbox('请输入原始单据号');
			} else {
				if(types==0){//单据添加
					$('#DocumentDetailGrid').bootstrapTable('refresh',{url : "<c:url value='/application/querydocByitemid.do?matchid='/>"+ fdocumentno});
				}else{//单据复制
					$('#CpDocumentDetailGrid').bootstrapTable('refresh',{url : "<c:url value='/application/querydocByitemid.do?matchid='/>"+ fdocumentno});
				}
			}
		}

		function takeBack(saleitemid) {
			$('#DocumentDetailGrid').bootstrapTable('refresh',{url : "<c:url value='/application/querydocByitemid.do?saleitemid='/>"+ saleitemid});
		}

		function closeCheckCarno() {
			layer.close(carnoLayer);
		}

		function getdataddd() {
			alert(JSON.stringify($('#DocumentDetailGrid').bootstrapTable('getData')));
		}
		
		
		
		var planidLayer;
		function checkPlanid(types) {
			planidLayer = layer.open({
				type : 2,
				title : '原始单据信息选择',
				maxmin : true, //开启最大化最小化按钮
				area : [ '90%', '90%' ],
				content : '<c:url value="/application/queryFdocument?documentcode="/>'+$("#operatype").val()+"&types="+types//注意，如果str是object，那么需要字符拼接。
			});
		}

		function takeBackPlanid(planid,types) {
			if(types==0){
				$("#DocumentForm input[name='fdocumentno']").val(planid);
				$('#DocumentDetailGrid').bootstrapTable('refresh',{url : "<c:url value='/application/querydocByitemid.do?matchid='/>"+ planid});
			}else{
				$("#CpDocumentForm input[name='fdocumentno']").val(planid);
				$('#CpDocumentDetailGrid').bootstrapTable('refresh',{url : "<c:url value='/application/querydocByitemid.do?matchid='/>"+ planid});
			}
			
		}

		function closePlanid() {
			layer.close(planidLayer);
		}
	</script>
</body>
</t:html>