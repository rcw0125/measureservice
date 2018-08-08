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
				<input type="hidden" id="typecode" name="typecode" value="${operatype}">
				<input type="hidden" id="unitcodes"  value="${unitcode}">
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
							<span class="input-group-addon" id="sizing-addon3">物&nbsp;&nbsp;流&nbsp;&nbsp;号 </span>
							 <input type="text" class="form-control" placeholder="物流号" name="matchid" aria-describedby="sizing-addon3">
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
					<div class="col-sm-3">
						  <t:combobox id="operatypes" name="operatype"  url="/bcommon/queryOperatype.do?operatype=${operatype}" label="业务类型" require="false" alloptions="true" allowclear="true"/>	
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
							<span class="input-group-addon" id="sizing-addon3">计&nbsp;&nbsp;划&nbsp;&nbsp;号 </span> 
							<input	type="text" class="form-control" placeholder="计划号" id="planid" name="planid" aria-describedby="sizing-addon3">
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
							<span class="input-group-addon" id="sizing-addon3">收　　货</span>
							<input type="text" class="form-control" placeholder="收货" id="targetname" name="targetname" aria-describedby="sizing-addon3">
						</div>
					</div>			
				</div>
				<div class="row">
					
					<div class="col-sm-12">
						<div class="form-group input-group  btn-group-sm">
							<t:button text="查询" modulecode="JinChuChangYiChang" id="query" btnclass="btn btn-info" iconclass="glyphicon glyphicon-search" onclick="queryinfo()"/>
							<t:button text="制卡" modulecode="JinChuChangYiChang" id="MakecardAllExBtn" btnclass="btn btn-success" iconclass="fa fa-credit-card" onclick="openWindow('init')" />
						    <t:button text="进厂" modulecode="JinChuChangYiChang" id="InBtn" btnclass="btn btn-primary" iconclass="fa fa-exchange" onclick="insertIn()"/>
						    <t:button text="计量" modulecode="JinChuChangYiChang" id="MeasureBtn" btnclass="btn btn-warning" iconclass="fa fa-balance-scale" onclick="OpenMeasure()"/>
						    <t:button text="作废计量" modulecode="JinChuChangYiChang" id="CancelMeasureBtn" btnclass="btn btn-danger" iconclass="fa fa-arrow-up" onclick="updateCurrMeasure()"/>
						    <t:button text="入库" modulecode="JinChuChangYiChang" id="StoreinAllExBtn" btnclass="btn btn-success" iconclass="fa fa-sign-in" onclick="OpenStorein()"/>
						    <t:button text="出库" modulecode="JinChuChangYiChang" id="StoreoutAllExBtn" btnclass="btn btn-info" iconclass="fa fa-sign-out"  onclick="OpenStoreout()"/>
						    <t:button text="出厂" modulecode="JinChuChangYiChang" id="OutBtn" btnclass="btn btn-primary" iconclass="fa fa-exchange"  onclick="insertOut()"/>
						    <t:button text="终止" modulecode="JinChuChangYiChang" id="ForceBtn" btnclass="btn btn-warning" iconclass="fa fa-exchange"  onclick="insertforce()"/>
						    <t:button text="还原终止" modulecode="JinChuChangYiChang" id="rebackForceBtn" btnclass="btn btn-info" iconclass="fa fa-exchange"  onclick="rebackforcestop()"/>  
						    <t:button text="取样信息回传" modulecode="JinChuChangYiChang" id="sampleInfo" btnclass="btn btn-danger" iconclass="fa fa-arrow-up" onclick="uploadInfo()"/>
						    <t:button text="取样重量回传" modulecode="JinChuChangYiChang" id="sampleWeight" btnclass="btn btn-info" iconclass="fa fa-hand-pointer-o" onclick="uploadSweight()"/>
						</div>
					</div>  
				</div>
			</form>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<table id="MakecardInfoGrid" 
			            data-toggle="table"  
						data-row-style="rowStyle" 
						data-query-params="queryParams"
						data-pagination="true" 
						data-page-size="10"
						data-page-list="[10,40,ALL]" 
						data-mobile-responsive="true">
				<thead>
					<tr>
					    <th data-field="state" data-radio="true" ></th>
						<th data-field="matchid" data-halign="center"
							data-searchable="true" sortable data-formatter="ExceptionFormatter">物流号</th>
						<th data-field="sysmemo" data-halign="center"
							data-searchable="true" sortable>状态</th>
						<th data-field="operaname" class="text-nowrap" data-halign="center"
							data-searchable="true" sortable>业务类型</th>
						<th data-field="operatype" data-visible="false" ></th>
						<th data-field="planid" data-halign="center"
							data-searchable="true"  class="text-nowrap" sortable>计划号</th>
						<th data-field="carno" data-halign="center" data-searchable="true"
							class="text-nowrap" >车号</th>
						<th data-field="icid" data-halign="center" data-searchable="true"
							class="text-nowrap" data-visible="false" >卡号</th>	
						<th data-field="materialname" data-halign="center"
							data-searchable="true" class="text-nowrap">货名</th>
						<th data-field="sourcename" data-halign="center"
							data-searchable="true" class="text-nowrap">供货</th>
						<th data-field="targetname" data-halign="center"
							data-searchable="true" class="text-nowrap">收货</th>						
						<%-- <th data-field="tareb" data-halign="center" data-searchable="true"
							class="text-nowrap">供方皮重/t</th>
						<th data-field="grossb" data-halign="center"
							data-searchable="true" class="text-nowrap">供方毛重/t</th>
						<th data-field="suttleb" data-halign="center"
							data-searchable="true" class="text-nowrap">供方净重/t</th> --%>
						<th data-field="suttleapp" data-halign="center"
							data-searchable="true" class="text-nowrap">发货重量/t</th>
						<th data-field="snumber" data-halign="center"
							data-searchable="true" class="text-nowrap">发货数量</th>
						<th data-field="documentcode" data-halign="center"  data-visible="false"
							data-searchable="true" class="text-nowrap">单据编号</th>
						<th data-field="usermemo" data-halign="center" data-searchable="true"
							class="text-nowrap">备注</th>
						<th data-field="isormeasure" data-visible="false" >是否计量</th>
						<th data-field="materialflow" data-visible="false">进出厂标记</th>
						<th data-field="gross" data-visible="false">毛重</th>	
						<th data-field="tare" data-visible="false">皮重</th>									
						<th data-width="70px" data-align="center" data-valign="middle"
							data-formatter="operateFormatter" data-events="operateEvents">操作</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	 <div class="modal fade" id="MakeCardWindow" tabindex="-1" role="dialog" aria-labelledby="MakeCardWindowLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">制卡</h4>
				</div>
				<div class="modal-body">
					<form id="MakeCardDetailForm">
						<div class="row">
							<input type="hidden" id="matchid" name="matchid" value="-1" />
							<input type="hidden" name="unitcode" id="unitcode" value="${unitcode}" />
							<input type="hidden" name="unitname" id="unitname" value="${unitname}">	
				            <input type="hidden" id="cardstate" name="cardstate"  >
				            <input type="hidden"   id="planidlist" name="planidlist"  >
				            <input type="hidden"   id="itemidlist" name="itemidlist"  >
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">卡　　号</span>
									<input id="icid" name="icid" type="text" class="form-control"  aria-describedby="basic-addon1" >
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">									
									<span class="input-group-btn">
										<button class="btn btn-default" type="button" onclick="checkCarno('insert')"><span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>车　号</button>
									</span>										
									<input id="carnos" name="carno" type="text" class="form-control" value=""  data-provide="typeahead"  aria-describedby="basic-addon1" >
								</div>
							</div>
						</div>				
						<div class="row">
							<div class="col-sm-12">
								  <t:combobox id="operatype" name="operatype"  url="/bcommon/queryOperatype.do"  label="业务类型" require="false" />	
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">备　　注</span>
									<input name="memo" type="text" class="form-control"	aria-describedby="basic-addon1" >
								</div>
							</div>
						</div>
					<div class="row"　>
						<div class="col-sm-12" >
							<table id="MakeCardDetailGrid" data-mobile-responsive="true" data-unique-id="itemid">
							</table>
						</div>
					</div>
					</form>
				</div>
				<div class="modal-footer  btn-group-sm">
					<button type="button" class="btn btn-success" id="addBtn" onclick="insert()">确认</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>					
				</div>
			</div>
		</div>
	</div>
	
	 <div class="modal fade" id="MakeCardUpdateWindow" tabindex="-1" role="dialog" aria-labelledby="MakeCardWindowLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">修改</h4>
				</div>
				<div class="modal-body"> 
					<form id="MakeCardUpdateForm"> 
						<div class="row">
							<input type="hidden" id="matchids" name="matchid" value="-1" />
							<input type="hidden"   id="upplanidlist" name="planidlist"  value="" >
				            <input type="hidden"   id="upitemidlist" name="itemidlist"  value="" >
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<div class="input-group-addon">IC&nbsp;&nbsp;卡&nbsp;&nbsp;号&nbsp;&nbsp;</div>
									<input  name="icid" type="text" class="form-control"  aria-describedby="basic-addon1" >
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">RFID卡号&nbsp;</span>
									<input id="rfidid" name="rfidid" type="text" class="form-control" aria-describedby="basic-addon1" readonly="readonly" >
								</div>
							</div>
						</div>						
						<div class="row">
						    <div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<!-- <div class="input-group-addon" onclick="checkCarno('update')">车　　号</div> -->
									<span class="input-group-btn">
										<button class="btn btn-default" type="button" onclick="checkCarno('update')"><span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>车　号&nbsp;&nbsp;</button>
									</span>
									<input id="upcarno"  name="carno" type="text" class="form-control" value="" aria-describedby="basic-addon1" required data-bv-notempty-message="请填写车号" readonly="readonly">
								</div>
							</div>
							<div class="col-sm-6">
								<t:combobox id="operatypesd" name="operatype"  url="/bcommon/queryOperatype.do?operatype=${operatype}" label="业务&nbsp;类&nbsp;型"/>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<t:combobox id="routeids" name="routeid" label="&nbsp;作业路线&nbsp;" require="false" url="/workline/select2data" allowclear="true" />
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group input-group-sm">
									<div class="input-group-addon">备　　&nbsp;&nbsp;注</div>
									<input name="memo" type="text" class="form-control"	aria-describedby="basic-addon1" >
								</div>
							</div>
						</div>
					<div class="row"　>
						<div class="col-sm-12" >
							<table id="MakeCardUpdateGrid" data-mobile-responsive="true" data-unique-id="itemid">
							</table>
						</div>
					</div>
					</form>
				</div>
				<div class="modal-footer  btn-group-sm">
					<button type="button" class="btn btn-success" id="updateBtn" onclick="update()">确认</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- ---------------------------------------入库 -------------------------------------------------------------->
	 <div class="modal fade" id="StoreinWindow" tabindex="-1" role="dialog"
		aria-labelledby="StoreinWindowLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">货场</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-sm-12">
							<form id="StoreinForm">
								<input type="hidden" name="id" value="-1" />
							    <input type="hidden" name="matchid"  value="" /> 
							    <input type="hidden" name="sysmemo"  value="" /> 
								<input type="hidden" name="stoplink" id="stoplink" value="">
								<div class="row">
									<div class="col-sm-6">
										<div class="form-group input-group input-group-sm">
											<span class="input-group-addon">卡　　号</span>
											 <input type="text" name="cardid" id="cardids" class="form-control" aria-describedby="basic-addon1" readonly="readonly"  />
										</div>
									</div>
									<div class="col-sm-6">
										<div class="form-group input-group input-group-sm">
											<span class="input-group-addon">车　　号</span> 
											<input name="carno" type="text" class="form-control" aria-describedby="basic-addon1" readonly="readonly" required data-bv-notempty-message="请刷卡获取车号">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-6">
										<div class="form-group input-group input-group-sm">
											<span class="input-group-addon">收货方式</span> 
											<select id="recetype" name="recetype" class="form-control select2" placeholder="必选" required data-bv-notempty-message="请选择收获方式">
												<option value="0">全部收货</option>
												<option value="1">部分收货</option>
												<option value="2">整车退货</option>
											</select>
										</div>
									</div>
									<div class="col-sm-6">
								      <input name="unitname" id="inunitname" type="hidden" class="form-control" value=""> 
								<t:combobox id="inunitcode" name="unitcode"  url="/bcommon/querySworkpoint.do" label="收货库房"  alloptions="true" allowclear="true" />	
							</div>
								</div>
								<div class="row">
									<div class="col-sm-12">
										<div class="form-group input-group input-group-sm">
											<span class="input-group-addon">备　　注</span>
											 <input name="usermemo" type="text" class="form-control" aria-describedby="basic-addon1">
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12">
							<table id="StoreininfoGrid" data-toggle="table"
								data-mobile-responsive="true">
								<thead>
									<tr>
										<th data-field="matchid" data-halign="center" nowrap="nowrap">物流号</th>
										<th data-field="planid" data-halign="center" nowrap="nowrap">到货单</th>
										<th data-field="materialcode" data-halign="center" class="text-nowrap text-left"  nowrap="nowrap">货名编码</th>
										<th data-field="materialname" data-halign="center" class="text-nowrap text-left"  nowrap="nowrap">货名</th>
										<th data-field="materialspec" data-halign="center" class="text-nowrap text-left"  nowrap="nowrap">规格</th>
										<th data-field="modelno" data-halign="center" class="text-nowrap text-left"  nowrap="nowrap">型号</th>
										<th data-field="sourcename" data-halign="center" class="text-nowrap text-left"  nowrap="nowrap">供货</th>
										<th data-field="targetname" data-halign="center" class="text-nowrap text-left"  nowrap="nowrap">收货</th>
										<th data-field="weight" data-halign="center" nowrap="nowrap">计划重量/t</th>
										<th data-field="outcounts" data-halign="center" nowrap="nowrap">计划数量</th>
										<!-- 计重时实际重量和实际数量不显示 -->
										<th data-field="counts" data-halign="center" nowrap="nowrap" data-editable="true">实际数量</th>
										<th data-field="measureunit" data-halign="center" nowrap="nowrap" >计量单位</th>
										<th data-field="pictureno" data-halign="center" class="text-nowrap text-left"  nowrap="nowrap">图号</th>
										<th data-field="prodlinename" data-halign="center" class="text-nowrap text-left"  nowrap="nowrap">产地</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				<div class="modal-footer  btn-group-sm">
					<button type="button" class="btn btn-success" id="SaddBtn" onclick="insertStorein()">确认</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- ---------------------------------------出库 -------------------------------------------------------------->
	
	<div class="modal fade" id="StoreoutWindow" tabindex="-1" role="dialog"
		aria-labelledby="StoreoutWindowLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">货场</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-sm-12">
							<form id="StoreoutForm">
								<input type="hidden" name="id" value="-1" />
							    <input type="hidden" name="matchid"  value="" /> 
							    <input type="hidden" name="sysmemo"  value="" /> 
								<input type="hidden" name="stoplink" id="stoplink" value="">
								<div class="row">
									<div class="col-sm-6">
										<div class="form-group input-group input-group-sm">
											<span class="input-group-addon">卡　　号</span>
											 <input type="text" name="cardid" id="cardids" class="form-control" aria-describedby="basic-addon1" readonly="readonly"  />
										</div>
									</div>
									<div class="col-sm-6">
										<div class="form-group input-group input-group-sm">
											<span class="input-group-addon">车　　号</span> 
											<input name="carno" type="text" class="form-control" aria-describedby="basic-addon1" readonly="readonly"
											>
										</div>
									</div>
								</div>
								 <div class="row">
									<div class="col-sm-6">
								      	<input id="outunitname" name="unitname"  type="hidden" class="form-control" value=""> 
								      	<t:combobox id="outunitcode" name="unitcode"  url="/bcommon/querySworkpoint.do" label="发货库房"   alloptions="true" allowclear="true" />	
							       </div>
								</div> 
								<div class="row">
									<div class="col-sm-12">
										<div class="form-group input-group input-group-sm">
											<span class="input-group-addon">备　　注</span>
											 <input name="usermemo" type="text" class="form-control" aria-describedby="basic-addon1">
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12">
							<table id="StoreoutinfoGrid" data-toggle="table"  data-mobile-responsive="true">
				                <thead>
				                  <tr>
				                    <%-- <th data-field="matchid" data-halign="center" nowrap="nowrap">物流号</th>
				                    <th data-field="planid" data-halign="center" nowrap="nowrap">发运单</th>
				                    <th data-field="materialname" data-halign="center" nowrap="nowrap">货名</th>
				                    <th data-field="sourcename" data-halign="center" nowrap="nowrap">供货</th>
				                    <th data-field="targetname" data-halign="center" nowrap="nowrap">收货</th>
				                    <th data-field="counts" data-halign="center" data-editable="true">数量</th> 
				                     --%>
				                     <th data-field="matchid" data-halign="center" nowrap="nowrap">物流号</th>
				                    <th data-field="itemid" data-visible="false">物流号</th>
				                    <th data-field="planid" data-halign="center" nowrap="nowrap">到货单</th>
				                    <th data-field="taskcode"  data-visible="false">业务号</th>
				                    <th data-field="materialcode"  data-visible="false">货名编码</th>
				                    <th data-field="materialname" data-halign="center" class="text-nowrap text-left"  nowrap="nowrap">货名</th>
				                    <th data-field="materialspec" data-halign="center" class="text-nowrap text-left"  nowrap="nowrap">规格</th>
				                    <th data-field="modelno" data-halign="center" class="text-nowrap text-left"  nowrap="nowrap">型号</th>
				                    <th data-field="sourcecode"  data-visible="false">供货</th>
				                    <th data-field="sourcename" data-halign="center" class="text-nowrap text-left"  nowrap="nowrap">供货</th>
				                    <th data-field="targetcode"  data-visible="false">供货</th>
				                    <th data-field="targetname" data-halign="center" class="text-nowrap text-left"  nowrap="nowrap" data-visible="false">收货</th>
				                    <!-- 计重时实际重量和实际数量不显示 -->
				                    <th data-field="counts" data-halign="center" nowrap="nowrap" data-editable="true">数量</th>
				                    <th data-field="measureunit" data-halign="center" nowrap="nowrap" >计量单位</th>
				                    <th data-field="isormeasure" data-halign="center" data-visible="false" >是否计量</th>
				                  </tr>
				                </thead>
				              </table>
						</div>
					</div>
				</div>
				<div class="modal-footer  btn-group-sm">
					<button type="button" class="btn btn-success" id="StoreoutaddBtn" onclick="insertStoreout()">确认</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<!-- ---------------------------------------计量 -------------------------------------------------------------->
	
		<div class="modal fade" id="MeasureWindow" tabindex="-1" role="dialog" aria-labelledby="BaseInfoWindowLabel"
				aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">计量</h4>
				</div>
				<div class="modal-body">
					<form id="MeasureForm">
						<input type="hidden" id="id" name="id" value="0" />	
						<input type="hidden" id="materialflow" name="materialflow" value="0" />	
						<div class="row">						
							<div class="col-sm-12">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">物&nbsp;&nbsp;&nbsp;&nbsp;流&nbsp;&nbsp;&nbsp;&nbsp;号</span>
									<input name="matchid" id="matchid" type="text" class="form-control"	aria-describedby="basic-addon1" readonly="readonly">
								</div>
							</div>
						</div>											
						<div class="row">						
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">毛　　&nbsp;&nbsp;重/t</span>
									<input name="gross" id="gross" type="text" class="form-control"	aria-describedby="basic-addon1" data-bv-numeric  data-bv-numeric-message="请输入数字">
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">皮　　&nbsp;&nbsp;重/t</span>
									<input id="tare" name="tare" type="text" class="form-control" aria-describedby="basic-addon1" data-bv-numeric  data-bv-numeric-message="请输入数字">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon" >毛&nbsp;重&nbsp;时&nbsp;间&nbsp;</span>
									<input name="grosstime" id="grosstime" type="text"	class="form-control" aria-describedby="basic-addon1">
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon" >皮&nbsp;重&nbsp;时&nbsp;间&nbsp;</span>
									<input name="taretime" id="taretime" type="text" class="form-control" aria-describedby="basic-addon1">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
									<input name="grossweigh" id="grossweigh" type="hidden" class="form-control" value=""> 
									<t:combobox id="grossweighid" name="grossweighid"  url="/bcommon/queryEqunameinfo.do" label="毛&nbsp;重&nbsp;衡&nbsp;器&nbsp;" require="false" alloptions="true" allowclear="true" />	
							</div>
							 <div class="col-sm-6">
									<input name="tareweigh" id="tareweigh" type="hidden" class="form-control" value=""> 
									<t:combobox id="tareweighid" name="tareweighid"  url="/bcommon/queryEqunameinfo.do" label="皮&nbsp;重&nbsp;衡&nbsp;器&nbsp;" require="false" alloptions="true" allowclear="true"/>	
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon" >毛重计量员</span>
									<input name="grossoperaname" type="text" class="form-control" aria-describedby="basic-addon1">
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon" >皮重计量员</span>
									<input	name="tareoperaname" type="text" class="form-control"	aria-describedby="basic-addon1">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">备　　　注</span>
									<input  name="usermemo" id="usermemo" class="form-control" placeholder="选填" rows="1" required="required" maxlength="100"></input>
								</div>
							</div>
						</div>
					</form>
				</div>	
				<div class="modal-footer  btn-group-sm">					
					<button type="button" class="btn btn-success" id="MeasureaddBtn"	onclick="insertMeasure()">确认</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="ExceptionWindow" role="dialog" aria-hidden="true" >
			<input type="hidden" id="Exceptionmatchid"  name="matchid" value="-1"/>
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">异常操作记录</h4>
					</div>
					<div class="modal-body">
						<div class="table-responsive">
							<table id="ExceptionGrid" data-toggle="table" data-row-style="rowStyle">
								<thead>
									<tr>
										<th data-field="matchid" data-halign="center" data-searchable="true" class="text-nowrap text-center">物流号</th>
										<th data-field="operaname" data-halign="center" data-searchable="true" class="text-nowrap text-center">操作人</th>
										<th data-field="usermemo" data-halign="center" data-searchable="true" class="text-nowrap text-center">备注</th>
										<th data-field="createdate" data-halign="center" data-searchable="true" class="text-nowrap text-center">操作时间</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
	<script type="text/javascript">
	  var cols = '';
	  var rowscount = 0;
	  var operatype = $("#operatype").val(); 
     function prepend(){    
        $("#MakeCardDetailGrid").bootstrapTable('prepend', appendData());
     }
     function appendData() {
         var rows = [];
         rows.push({ itemid:"add"+rowscount,
        	 deletes:rowscount,
			 planid:"请选择",
        	 materialcode: 0,
        	 materialname:'',
        	 sourcecode:'',
        	 sourcename:'',
        	 targetcode:0,
        	 targetname:'',
        	 grossb:0,
        	 tareb:0,
        	 suttleb:0,
        	 snumber:0,
        	 usermemo:''
         });
         rowscount++;
         return rows;
     }
     //function deletestr(obj) {
    	 /* if(operaflag==0){//标记为0时表示添加删除
    		 $('#MakeCardDetailGrid').bootstrapTable('removeByUniqueId', obj); 
    	 }else if(operaflag==1){//标记为1时表示修改删除
    		 $('#MakeCardUpdateGrid').bootstrapTable('removeByUniqueId', obj);
    	 } */
    	 function deletestr(obj,planid,saleitemid) {
 		    /* var planidlist;
 			var itemidlist; */
 			if (operaflag == 0) {//标记是0表示添加
 				var gridData = $('#MakeCardDetailGrid').bootstrapTable('getData')
 				if(gridData.length==1){
 					$("#planidlist").val("");
 					$("#itemidlist").val("");
 					$("#operatype").val("");
 					$("#operatype").trigger('change.select2');
 				}else{
 					//$("#planidlist").val($('#planidlist').val().replace("," + planid + ",", ","));
 					$("#itemidlist").val($('#itemidlist').val().replace("," + saleitemid + ",", ","));
 				}
 				$('#MakeCardDetailGrid').bootstrapTable('removeByUniqueId', obj);
 			} else {//否则表示为修改
 				var gridData = $('#MakeCardUpdateGrid').bootstrapTable('getData')
 				if(gridData.length==1){
 					$("#upplanidlist").val("");
 					$("#upitemidlist").val("");
 				}else{
 					$("#upitemidlist").val($('#upitemidlist').val().replace("," + saleitemid + ",", ","));
 				}
 				$('#MakeCardUpdateGrid').bootstrapTable('removeByUniqueId', obj);
 			}
 			
 		//}
     }
	
	/* function deletesFormatter(value,row,index){
		return "<div class='btn-group-sm'><button type='button' class='btn btn-danger'onclick='deletestr(\""+row.itemid+"\")'><span class='glyphicon glyphicon-minus' aria-hidden='true'></span></button></div>";
	} */
	function deletesFormatter(value, row, index) {
		return "<div class='btn-group-sm'><button type='button' class='btn btn-danger'onclick='deletestr(\""+ row.itemid+ "\",\""+ row.planid+ "\",\""+ row.saleitemid+ "\")'><span class='glyphicon glyphicon-minus' aria-hidden='true'></span></button></div>";
	}
	jQuery(document).ready(function($) {
		queryinfo();	
	   $.fn.typeahead.Constructor.prototype.blur = function() {
		      var that = this;
		      setTimeout(function () { that.hide() }, 250);
	   };

	   $('#carnos').typeahead({
		    source: function (query, process) {		
		        $.ajax({
					type : "post",
					url : '<c:url value="/bcommon/queryCarno.do?carno="/>'+query,
					dataType : "json",
					success : function(data) {	
						var numArr = []; // 定义一个空数组
						 for (var i = 0; i < data.rows.length; i++) {
			                 numArr.push(data.rows[i].carno); // 将文本框的值添加到数组中
			             }							 
						 process(numArr);							
					}
		       });			        
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
		$('#grosstime').datetimepicker({
			format : 'YYYY-MM-DD HH:mm:ss',
			locale : 'zh-cn'
		});
		$('#taretime').datetimepicker({
			format : 'YYYY-MM-DD HH:mm:ss',
			locale : 'zh-cn'
		});
	});
	function queryParams(params) {
		return jQuery.extend({}, params, $('#queryform').serializeJson());
	}

	function queryinfo() {
		$('#MakecardInfoGrid').bootstrapTable('refresh', {url : "<c:url value='/exception/queryPage.do'/>"});
	}
	
	//------------------------------------制卡方法---------------------------------------
	
	function operateFormatter(value, row, index) {
		/* return [ '<div class="pull-right">',
				'<a class="edit" href="javascript:void(0)" title="修改">',
				'<i class="glyphicon glyphicon-pencil"></i>', '</a>　',
				'<a class="remove" href="javascript:void(0)" title="移除">',
				'<i class="glyphicon glyphicon-trash"></i>', '</a>',
				'</div>' ].join(''); */
		return [
                '<div class="pull-right">',
               	'<t:ibutton text="修改" modulecode="JinChuChangYiChang" id="exceptionmodify" btnclass="edit" iconclass="glyphicon glyphicon-pencil"/>',
               	'<t:ibutton text="移除" modulecode="JinChuChangYiChang" id="exceptionremove" btnclass="remove" iconclass="glyphicon glyphicon-trash"/>',
               	'</div>'
           	].join('');		
		
	}
	window.operateEvents = {
		'click .edit' : function(e, value, row) {
			openUpdateMakecard(row.matchid);
		},
		'click .remove' : function(e, value, row) {
			cancelAllinfo(row.matchid,row.documentcode,row.materialflow);
		}
	};
	$('#MakecardAllExBtn').on('click', function() {
		openMakecard(-1);
	});
	function openMakecard(id_vv) {	
		$("#matchid").val(id_vv);
		operaflag=0;
		$('#MakeCardWindow').modal('show');
	}
	   
	function openUpdateMakecard(id_vv) {	
		$("#matchids").val(id_vv);
		operaflag=1
		$('#MakeCardUpdateWindow').modal('show');
	}
	
	$('#MakeCardWindow').on('shown.bs.modal',function() {
		    operaflag=0;
			var matchid = $("#matchid").val();
			var fkflag = $("#fkflag").val();
			var operatype =  $("#typecode").val();
		    loadFormData('MakeCardDetailForm','<c:url value="/application/queryApplicationbill.do?matchid="/>'+ matchid+'&type=makecard&typecode='+$("#typecode").val(),function(data){	
				$("#matchid").val(matchid);
				$("#fkflag").val(fkflag);
				$('#MakeCardDetailGrid').bootstrapTable('removeAll');
			}); 

   });	
	$('#MakeCardUpdateWindow').on('shown.bs.modal',function() {
		operaflag=1;
		$("#upplanidlist").val("");
		$("#upitemidlist").val("");
		var matchid = $("#matchids").val();		
		loadFormData('MakeCardUpdateForm','<c:url value="/application/queryApplicationbill.do?matchid="/>'+ matchid + "&typecode="+ $("#typecode").val()+ "&type=makecard",
			function(data) {
				$("#matchids").val(matchid);
				var operatype = $("#operatypesd").val();				
				loadComboxData('operatype','<c:url value="/bcommon/queryOperatype.do?operatype="/>'+ operatype);
				$.ajax({
						type : "post",
						url : '<c:url value="/bcommon/queryModelcontent.do?operatype="/>'+ operatype+"&flag=1",
						dataType : "json",
						success : function(data) {
							var cs = JSON.parse(data.msg);
							$('#MakeCardUpdateGrid').bootstrapTable('destroy').bootstrapTable({columns:cs}).bootstrapTable('refresh',{url : "<c:url value='/application/queryAppInfo.do?matchid='/>"+ matchid});
						}
				 });
		 });

   });
	
	/**
	 *作废制卡信息
	 */
	function cancelAllinfo(matchid,documentcode,materialflow) {
		dialogbox("请确认", "确认删除此项目？",function(data){
			if(data){
				$.ajax({
						type : "post",
						url : '<c:url value="/exception/cancelAllinfo.do"/>',
						dataType : "json",
						data : {matchid : matchid,documentcode:documentcode,materialflow:materialflow},
						success : function(data) {
						if (data.success) {
							successbox(data.msg);					
							$('#MakecardInfoGrid').bootstrapTable('refresh', {url : "<c:url value='/exception/queryPage.do'/>"});
						} else {
							errorbox(data.msg);
						}
					}
				});
			}
	   });
	}
	
	
	
	//操作制卡信息		
	function insert() {
		$('#addBtn').prop('disabled', true); 
		$("#motorcadename").val($('#motorcadecode option:selected').text())				
		var gridData = $('#MakeCardDetailGrid').bootstrapTable('getData')
		var msginfo='';
		if(gridData.length==0){
			errorbox("请选择业务信息 ");
			$('#addBtn').prop('disabled', false);
		}else{
			for(var i=0;i<gridData.length;i++){
			   if(gridData[i].planid==null || gridData[i].planid=='' || gridData[i].planid=='请选择' ){
				   msginfo=msginfo+"第"+(i*1+1)+"行，业务信息为空</br>" ;
			   }
			}
			if(msginfo.length==0){
			saveFormDataWithParams('MakeCardDetailForm','<c:url value="/application/beforeinsertApplicationbill.do"/>',gridData,function(data){
				 if(data.mfunc==0){
					 if(data.success){
		             		successbox(data.msg);		             		
		             		$('#MakeCardWindow').modal('toggle');
		             		$('#MakecardInfoGrid').bootstrapTable('refresh');	
		             	}else{
		             		errorbox(data.msg);
		             	} 
				 }else{						 
					 if(data.mfunc==3){ //为3时判断里面有一个禁止添加取样信息
						 errorbox(data.msg);
					 }else if(data.mfunc==2){//为2时用户选择是否添加取样信息
						 dialogbox("请确认", data.msg,function(data){
							if(data){
								saveFormDataWithParams('MakeCardDetailForm','<c:url value="/application/insertApplicationbill.do"/>',gridData,function(data){
									 if (data.success) {
										successbox(data.msg);
										$('#MakeCardWindow').modal('toggle');
					             		$('#MakecardInfoGrid').bootstrapTable('refresh');
									} else {
										errorbox(data.msg);
									}
								});	
							}
						});
					 }
				 }
				 $('#addBtn').prop('disabled', false);
			});
			}else{
				errorbox(msginfo);
			}
		}
	}
	
	//操作制卡信息		
	function update() {
		$('#updateBtn').prop('disabled', true); 		
		var gridData = $('#MakeCardUpdateGrid').bootstrapTable('getData')
		if(gridData.length==0){
			errorbox("请选择业务信息 ");
		}else{
			saveFormDataWithParams('MakeCardUpdateForm','<c:url value="/application/beforeinsertApplicationbill.do"/>',gridData,function(data){
				 if(data.mfunc==0){
					 if(data.success){
	             		successbox(data.msg);
						$('#MakeCardUpdateWindow').modal('toggle');
	             		$('#MakecardInfoGrid').bootstrapTable('refresh');	
		             }else{
		             	errorbox(data.msg);
		             } 
				 }else{						 
					 if(data.mfunc==3){ //为3时判断里面有一个禁止添加取样信息
						 errorbox(data.msg);
					 }else if(data.mfunc==2){//为2时用户选择是否添加取样信息
						 dialogbox("请确认", data.msg,function(data){
							if(data){
								saveFormDataWithParams('MakeCardUpdateForm','<c:url value="/application/insertApplicationbill.do"/>',gridData,function(data){
									if (data.success) {
										successbox(data.msg);										
										$('#MakeCardUpdateWindow').modal('toggle');
										$('#MakecardInfoGrid').bootstrapTable('refresh');	
									} else {
										errorbox(data.msg);
									}
								});	
							}
						});
					 }
				 }
				 $('#updateBtn').prop('disabled', false);
			});
		}
	}
	
	/*根据卡号判断卡号车辆状态*/
	function queryInfoBycarno() {
		$.ajax({
            type: "post",
            url: '<c:url value="/bcommon/judgCarno.do"/>',
            dataType: "json",
            data: {cardid:$("#icid").val()}, 
            success: function(data){
            	 if(data.success == true){
            		 //卡和车辆状态正常，根据车号查询业务信息
 	            	$("#carnos").val(data.rows[0].carno);
            		 $("#cardstate").val(data.rows[0].validflag)
            		 validForm('MakeCardDetailForm');
            	 }else{	            		
            		 //如果车辆或者卡有问题，系统提示
            	   errorbox(data.msg);  
            	 }
            }
           });
	}
	
	function paramFormat(){
		var carno=$("#carnos").val();
		var operatype=$("#operatype").val();
		if(operatype==''){
			operatype=$("#operatypes").val();
		}
		return 'carno='+carno+'&operatype='+operatype;
	}
	
	$('#MakecardInfoGrid').bootstrapTable({
		onDblClickRow : function(row, $element) {
			layer.open({
				  type: 2,
			      title: '信息详情',
			      maxmin: true, //开启最大化最小化按钮
			      area: ['90%', '90%'],
			    content: '<c:url value="/bcommon/showdetail.do?matchid="/>'+row.matchid //注意，如果str是object，那么需要字符拼接。
			  });
		}
	});
	
	var carnoLayer;
	var operaflag;
	function checkCarno(types) { //types值为insert时为添加，为update时为修改
		var carno;
		var cardstate;
		var planidlist;
		var itemidlist;
		var operatype='';
		
		if (types == 'insert') {
			carno = $("#carnos").val();
			cardstate = $("#cardstate").val();
			operaflag = 0;
			planidlist=$("#planidlist").val();
			itemidlist=$("#itemidlist").val();
			if($("#operatype").val()==null){
				operatype='';
			}else{
				operatype=$("#operatype").val();
			}
		} else {
			carno = $("#upcarno").val();
			cardstate = 3;
			operaflag = 1;
			var gridData = $('#MakeCardUpdateGrid').bootstrapTable('getData')
			for (var i = 0; i < gridData.length; i++) {
				if($("#upplanidlist").val().indexOf("," + gridData[i].planid+",")==-1){
					$("#upplanidlist").val($("#upplanidlist").val()+gridData[i].planid+",");
				}
				$("#upitemidlist").val($("#upitemidlist").val() + gridData[i].saleitemid+ "," );
			}
			planidlist=$("#upplanidlist").val();
			itemidlist=$("#upitemidlist").val();
			operatype=$("#operatype").val();
			  
		}
		carnoLayer = layer.open({
					type : 2,
					title : '制卡信息选择',
					maxmin : true, //开启最大化最小化按钮
					area : [ '90%', '90%' ],
					content : '<c:url value="/view/commonpage/applicationinfo.jsp?carno="/>'+
							carno + "&cardstate=" + cardstate+"&itemidlist="+itemidlist
							+"&planidlist="+planidlist+"&operatype="+operatype
		});
	}
	/* $("#operatype").change(function(){
		var operatype = $("#operatype").val()
		$.ajax({
			type : "post",
			url : '<c:url value="/bcommon/queryModelcontent.do?operatype="/>'+ operatype+"&flag=1",
			dataType : "json",
			success : function(data) {
				$('#MakeCardDetailGrid').bootstrapTable({columns : data}).bootstrapTable('refresh',{url : "<c:url value='/application/queryAppBypitemid.do?planid='/>"+ planid+ "&saleitemid="+ saleitemid});
			}
		});
	})	 */
	function takeBack(carno,operatype,planid,saleitemid){ 
		if (operaflag == 0) {//标记是0表示添加
		    $('#planidlist').val(planid);
			$('#itemidlist').val(saleitemid);
		} else {//否则表示为修改
			$('#upplanidlist').val(planid);
			$('#upitemidlist').val(saleitemid);
		}
		loadComboxData('operatype','<c:url value="/bcommon/queryOperatype.do?operatype="/>'+ operatype);
		$.ajax({
			type : "post",
			url : '<c:url value="/bcommon/queryModelcontent.do?operatype="/>'+ operatype+"&flag=1",
			dataType : "json",
			success : function(data) {
				var cs = JSON.parse(data.msg);
				if (operaflag == 0) {//标记是0表示添加
					var planids = $('#planidlist').val();
					var itemids = $('#itemidlist').val();
					$('#MakeCardDetailGrid').bootstrapTable('destroy').bootstrapTable({columns:cs}).bootstrapTable('refresh',{url : "<c:url value='/application/queryAppBypitemid.do?planid='/>"+ planids+ "&saleitemid="+ itemids});
				} else {//否则表示为修改
					var upplanids = $('#upplanidlist').val();
					var upitemids = $('#upitemidlist').val();						
					$('#MakeCardUpdateGrid').bootstrapTable('destroy').bootstrapTable({columns:cs}).bootstrapTable('refresh',{url : "<c:url value='/application/queryAllAppBypitemid.do?planid='/>"+ upplanids+ "&saleitemid="+ upitemids});
				}
			}
		});
		validForm('MakeCardDetailForm');	
   }
	
	function closeCheckCarno(){
		layer.close(carnoLayer);
	}
	
	//------------------------------------------入库--------------------------------------------------------------
	    function OpenStorein(){ 
	    	var selectedRows = $('#MakecardInfoGrid').bootstrapTable('getSelections');
			if(selectedRows.length==0){
				errorbox("请选择信息");
			}else if (selectedRows[0].materialflow==2){
				errorbox("出厂物资无需入库");
			} else if (selectedRows[0].isormeasure==1 && selectedRows[0].gross<=0){
				errorbox("未计量毛重不允许入库");
			} else{
				$('#StoreinForm input[name="matchid"]').val(selectedRows[0].matchid);
				$('#StoreinForm input[name="cardid"]').val(selectedRows[0].icid);
				$('#StoreinForm input[name="carno"]').val(selectedRows[0].carno);
				$('#StoreinWindow').modal('show');
				
			}
	    }
		$('#StoreinWindow').on('shown.bs.modal',function() {
			validForm('StoreinForm');
			$('#StoreininfoGrid').bootstrapTable('refresh',{url :"<c:url value='/exception/querySinInfoBymatchid.do?matchid='/>"+ $('#StoreinForm input[name="matchid"]').val()});
        });	
		
		function insertStorein(){
			$('#StoreinForm input[name="sysmemo"]').val("异常添加入库信息");
			 $("#inunitname").val($('#inunitcode option:selected').text());
			$('#SaddBtn').prop('disabled', true); 
			var gridData = $('#StoreininfoGrid').bootstrapTable('getData')
			//saveFormData('StoreinForm','<c:url value="/storein/beforeinsertStorein.do"/>',function(data){
				saveFormDataWithParams('StoreinForm','<c:url value="/storein/beforeinsertStorein.do"/>',gridData,function(data) {
				 if(data.mfunc==0){
					 if(data.success){
		             		successbox(data.msg);    
		             		$('#StoreinWindow').modal('toggle');
		        			$('#MakecardInfoGrid').bootstrapTable('refresh');
		             	}else{
		             		errorbox(data.msg);			             		
		             	}
				 }else{
					 if(data.mfunc==3){ //为3时判断里面有一个禁止添加取样信息
						 errorbox(data.msg);
					 }else if(data.mfunc==2){//为2时用户选择是否添加取样信息
						 dialogbox("请确认", data.msg,function(data){
							if(data){
								saveFormDataWithParams('StoreinForm','<c:url value="/storein/insertStorein.do"/>',gridData,function(data) {
								 //saveFormData('StoreinForm','<c:url value="/storein/insertStorein.do"/>',function(data){
									 if (data.success) {
										successbox(data.msg);
										$('#StoreinWindow').modal('toggle');
					        			$('#MakecardInfoGrid').bootstrapTable('refresh');
									} else {
										errorbox(data.msg);
									} 
								});	
							}
						});
					 }
				 }
					
			});
			 $('#SaddBtn').prop('disabled', false); 
		}
	//------------------------------------------出库--------------------------------------------------------------
	function OpenStoreout(){		
    	var selectedRows = $('#MakecardInfoGrid').bootstrapTable('getSelections');
		 if(selectedRows.length==0){
			errorbox("请选择信息");
		}else if (selectedRows[0].materialflow==1){
			errorbox("进厂物资无需出库");
		} else if (selectedRows[0].isormeasure==1 && selectedRows[0].tare<=0){ //计件不控制皮重
			errorbox("未计量皮重不允许出库");
		} else{ 
			$('#StoreoutForm input[name="matchid"]').val(selectedRows[0].matchid);
			$('#StoreoutForm input[name="cardid"]').val(selectedRows[0].icid);
			$('#StoreoutForm input[name="carno"]').val(selectedRows[0].carno);
			$('#StoreoutWindow').modal('show');
			
		}				
    }
	$('#StoreoutWindow').on('shown.bs.modal',function() {
		validForm('StoreoutForm');
		$('#StoreoutinfoGrid').bootstrapTable('refresh',{url :"<c:url value='/exception/querySoutInfoBymatchid.do?matchid='/>"+ $('#StoreoutForm input[name="matchid"]').val()});
     });	

	/**
	 *添加出库信息
	 */
	 
	 function insertStoreout(){	
		 validForm('StoreoutForm');
		 $('#StoreoutForm input[name="sysmemo"]').val("异常添加出库信息");
		 $("#outunitname").val($('#outunitcode option:selected').text());		
		 //$('#StoreoutaddBtn').prop('disabled', true); 
		 var gridData = $('#StoreoutinfoGrid').bootstrapTable('getData')
		 if (gridData.length>0) {
			 var flag=0;
			for(var i=0;i<gridData.length;i++){
				 var isor=gridData[i].isormeasure
				 if(isor==0){
					 if(gridData[i].counts==null || gridData[i].counts==''  || gridData[i].counts=='请填写'){
						 flag++;
					   }
				   }
			 
		     } 
			if(flag==0){
				 saveFormDataWithParams('StoreoutForm','<c:url value="/storeout/beforeinsertStoreout.do"/>',gridData,function(data) {	
						if (data.mfunc == 0) {
							if (data.success) {
								successbox(data.msg);
								$('#StoreoutWindow').modal('toggle');
			        			$('#MakecardInfoGrid').bootstrapTable('refresh');
							} else {
								errorbox(data.msg);
							}
							validForm('StoreoutForm');
						} else {
							if (data.mfunc == 3) { //为3时判断里面有一个禁止添加取样信息
								errorbox(data.msg);
							} else if (data.mfunc == 2) {//为2时用户选择是否添加取样信息
								dialogbox("请确认",data.msg,function(data) {
									if (data) {
										saveFormDataWithParams('StoreoutForm','<c:url value="/storeout/insertStoreout.do"/>',gridData,function(data) {
										//saveFormData('StoreoutForm','<c:url value="/storeout/insertStoreout.do"/>',function(data) {
											if (data.success) {
												successbox(data.msg);
												$('#StoreoutWindow').modal('toggle');
							        			$('#MakecardInfoGrid').bootstrapTable('refresh');
											} else {
												errorbox(data.msg);
											}
										});
									}
								});
							}
						} 

					});
			}else{
				errorbox("请填写出库数量");
				$('#StoreoutaddBtn').prop('disabled', false); 
			}
		 }
	}
	 

	
	//------------------------------------------计量--90909------------------------------------------------------------
	function OpenMeasure(){
    	var selectedRows = $('#MakecardInfoGrid').bootstrapTable('getSelections');
		if(selectedRows.length==0){
			errorbox("请选择信息");
		}else if (selectedRows[0].isormeasure==0){
			errorbox("计件业务无需计量");
		}else{
			$('#MeasureForm input[name="matchid"]').val(selectedRows[0].matchid);
			$('#MeasureForm input[name="materialflow"]').val(selectedRows[0].materialflow);
			$('#MeasureWindow').modal('show');
		}				
    }
	$('#MeasureWindow').on('shown.bs.modal',function() {
		 loadFormData('MeasureForm','<c:url value="/exception/queryMeasureBymatchid.do?matchid="/>'+ $('#MeasureForm input[name="matchid"]').val()+"&materialflow="+$("#materialflow").val(),function(data){});
     });
	
	function insertMeasure() {
		var materialflow = $("#materialflow").val();
		var gross =parseFloat($('#MeasureForm input[name="gross"]').val());
		var tare = parseFloat($('#MeasureForm input[name="tare"]').val());
		var grosstime = $('#MeasureForm input[name="grosstime"]').val();
		var taretime = $('#MeasureForm input[name="taretime"]').val();
		var tareoperaname = $('#MeasureForm input[name="tareoperaname"]').val();
		var grossoperaname = $('#MeasureForm input[name="grossoperaname"]').val();		 
		$("#grossweigh").val($('#grossweighid option:selected').text());
		$("#tareweigh").val($('#tareweighid option:selected').text());
		var tareweighid = $('#MeasureForm input[name="tareweighid"]').val();
		var grossweighid = $('#MeasureForm input[name="grossweighid"]').val();
			if(gross>=tare && tare>0){
	 			if(grosstime!='' && grossoperaname!='' && grossweighid!='' && taretime!=''&& tareoperaname!='' && tareweighid!='' ){
	 				if (materialflow == 1 ) {//进厂
	 					if(grosstime<taretime){
	 						savedata();//保存数据信息
	 					}else{
	 						errorbox("进厂业务，毛重时间应早于皮重时间");
	 					}
		 					 		 
					} else if (materialflow == 2 ) {//出厂
						if(grosstime>taretime){
	 					    savedata();//保存数据信息
	 					}else{
	 					   errorbox("出厂业务，皮重时间应早于毛重时间");
	 					}
					}
	 				
	 			}else if(grosstime==''|| grossoperaname==''|| grossweighid!=''|| taretime==''|| tareoperaname=='' ||tareweighid!='' ){
	 				errorbox("请填写完整重量信息")
	 			}
	 		}else if(gross<tare && gross>0 ){
	 			errorbox("毛重必须大于等于皮重")
	 		}else{
	 			if (materialflow == 1 ) {//进厂
	 				if(gross==0 && tare>0){
	 					errorbox("请填写毛重信息")
	 				}else if(gross>0 && tare==0){
	 					if(grosstime!='' && grossoperaname!='' && grossweighid!=''){
	 		 				savedata();//保存数据信息
	 		 			}else if(grosstime=='' || grossoperaname=='' || grossweighid!=''){
	 		 				errorbox("请填写完整毛重信息")
	 		 			}
	 				}else{
	 					$('#MeasureForm input[name="gross"]').val(0)
	 					$('#MeasureForm input[name="tare"]').val(0)
	 				    $('#MeasureForm input[name="grosstime"]').val("");
						$('#MeasureForm input[name="taretime"]').val("");
						$('#MeasureForm input[name="tareoperaname"]').val("");
						$('#MeasureForm input[name="grossoperaname"]').val("");		 
						$("#grossweigh").val("");
						$("#tareweigh").val("");
						$("#grossweighid").val("");
						$("#tareweighid").val("");
	 					savedata();//保存数据信息			 		 
	 				}
				} else if (materialflow == 2 ) {//出厂
					if(gross > 0 && tare == 0){
						errorbox("请填写皮重信息");
					}else if(gross == 0 && tare > 0){
						if(taretime!='' && tareoperaname!='' && tareweighid!=''){
	 		 				savedata();//保存数据信息
	 		 			}else if(taretime=='' || tareoperaname==''|| tareweighid!=''){
	 		 				errorbox("请填写完整皮重信息");
	 		 			}
					}else{
	 					$('#MeasureForm input[name="gross"]').val(0)
	 					$('#MeasureForm input[name="tare"]').val(0)
	 				    $('#MeasureForm input[name="grosstime"]').val("");
						$('#MeasureForm input[name="taretime"]').val("");
						$('#MeasureForm input[name="tareoperaname"]').val("");
						$('#MeasureForm input[name="grossoperaname"]').val("");		 
						$("#grossweigh").val("");
						$("#tareweigh").val("");
						$("#grossweighid").val("");
						$("#tareweighid").val("");
	 					savedata();//保存数据信息			 		 
	 				}
				     
				}
	 		}
	}
	
	function savedata(){
		saveFormData('MeasureForm','<c:url value="/exception/insertMeasureException.do"/>',function(data) {
			if (data.success) {
				successbox(data.msg);
				$('#MeasureWindow').modal('toggle');
				$('#MakecardInfoGrid').bootstrapTable('refresh');
			} else {
				errorbox(data.msg);
			}
		});
	}
	
	/**
	 *作废计量信息
	 */
	function updateCurrMeasure( ) {
		var selectedRows = $('#MakecardInfoGrid').bootstrapTable('getSelections');
		if(selectedRows.length==0){
			errorbox("请选择信息");
		}else{
			dialogbox("请确认", "确认作废计量信息？",function(data){
				if(data){
					$.ajax({
							type : "post",
							url : '<c:url value="/exception/updateCurrMeasure.do"/>',
							dataType : "json",
							data : {matchid : selectedRows[0].matchid,materialflow:selectedRows[0].materialflow},
							success : function(data) {
							if (data.success) {
								successbox(data.msg);					
								$('#MakecardInfoGrid').bootstrapTable('refresh', {url : "<c:url value='/exception/queryPage.do'/>"});
							} else {
								errorbox(data.msg);
							}
						}
					});
				}
		   });
		}
		
	}
	//------------------------------------------进厂----------------------------------------------------------------------
	function insertIn() {
		var selectedRows = $('#MakecardInfoGrid').bootstrapTable('getSelections');
		if(selectedRows.length==0){
			errorbox("请选择信息");
		}else{				
		   dialogbox("请确认", "确认进厂吗？", function(data) {
					if (data) {
						$.ajax({
							type : "post",
							url : '<c:url value="/exception/insertInEntrylog.do"/>',
							dataType : "json",
							data : {matchid : selectedRows[0].matchid,icid:selectedRows[0].icid,carno:selectedRows[0].carno},
							success : function(data) {
								if (data.success == true) {
									successbox(data.msg);
									$('#MakecardInfoGrid').bootstrapTable('refresh');
								} else {
									errorbox(data.msg);
								}
							}
						});
					}
			})			
		};
	}
	
	//-------------------------------------------出厂---------------------------------------------------------------------
	function insertOut() {
		var selectedRows = $('#MakecardInfoGrid').bootstrapTable('getSelections');
		if(selectedRows.length==0){
			errorbox("请选择信息");
		}else{	
		   dialogbox("请确认", "确认出厂吗？", function(data) {
					if (data) {
						$.ajax({
							type : "post",
							url : '<c:url value="/exception/insertExceptionOut.do"/>',
							dataType : "json",
							data : {matchid:selectedRows[0].matchid,icid:selectedRows[0].icid,carno:selectedRows[0].carno},
							success : function(data) {
								if (data.success == true) {
									successbox(data.msg);
									$('#MakecardInfoGrid').bootstrapTable('refresh');
								} else {
									errorbox(data.msg);
								}
							}
						});
					}
			})			
		};
	}
	//-----------------------------------------终止作业-----------------------------------------------------
	/**
	 *终止信息
	 */
	function insertforce() {
		var selectedRows = $('#MakecardInfoGrid').bootstrapTable('getSelections');
		if(selectedRows.length==0){
			errorbox("请选择信息");
		}else{
			dialogbox("请确认", "确认终止此项目？",function(data){
				if(data){
					$.ajax({
							type : "post",
							url : '<c:url value="/bcommon/forcestop.do"/>',
							dataType : "json",
							data : {matchid : selectedRows[0].matchid,sysmemo:"异常终止"},
							success : function(data) {
							if (data.success) {
								successbox(data.msg);					
								$('#MakecardInfoGrid').bootstrapTable('refresh', {url : "<c:url value='/exception/queryPage.do'/>"});
							} else {
								errorbox(data.msg);
							}
						}
					});
				}
		   });
		}
		
	}
	//-----------------------------------------还原终止作业-----------------------------------------------------
	/**
	 *终止信息
	 */
	function rebackforcestop() {
		var selectedRows = $('#MakecardInfoGrid').bootstrapTable('getSelections');
		if(selectedRows.length==0){
			errorbox("请选择信息");
		}else{
			dialogbox("请确认", "确认还原终止此项目？",function(data){
				if(data){
					$.ajax({
							type : "post",
							url : '<c:url value="/bcommon/rebackforcestop.do"/>',
							dataType : "json",
							data : {matchid : selectedRows[0].matchid,sysmemo:"异常还原"},
							success : function(data) {
							if (data.success) {
								successbox(data.msg);					
								$('#MakecardInfoGrid').bootstrapTable('refresh', {url : "<c:url value='/exception/queryPage.do'/>"});
							} else {
								errorbox(data.msg);
							}
						}
					});
				}
		   });
		}
		
	}
	function ExceptionFormatter(value, row, index) {
		return '<a class="edit" href="#" onclick="openExceptionWindow(\''+row.matchid+'\')">'+value+'</a>';
	}
	function openExceptionWindow(matchid) {
		$("#Exceptionmatchid").val(matchid);
		$('#ExceptionWindow').modal('show');
	}
	$('#ExceptionWindow').on('show.bs.modal', function(){
		$('#ExceptionGrid').bootstrapTable('refresh',{url:"<c:url value='/exception/queryExceptioninfo.do?matchid='/>"+$("#Exceptionmatchid").val()});
    });
	//------------------------------------------取样信息回传----------------------------------------------------------------------
	function uploadInfo() {
		var selectedRows = $('#MakecardInfoGrid').bootstrapTable('getSelections');
		if(selectedRows.length==0){
			errorbox("请选择信息");
		}else{				
		   dialogbox("请确认", "确认回传取样信息吗？", function(data) {
					if (data) {
						$.ajax({
							type : "post",
							url : '<c:url value="/exception/uploadSampleInfo.do"/>',
							dataType : "json",
							data : {matchid : selectedRows[0].matchid},
							success : function(data) {
								if (data.success == true) {
									successbox(data.msg);
									$('#MakecardInfoGrid').bootstrapTable('refresh');
								} else {
									errorbox(data.msg);
								}
							}
						});
					}
			})			
		};
	}
	//------------------------------------------取样重量回传----------------------------------------------------------------------
	function uploadSweight() {
		var selectedRows = $('#MakecardInfoGrid').bootstrapTable('getSelections');
		if(selectedRows.length==0){
			errorbox("请选择信息");
		}else{				
		   dialogbox("请确认", "确认回传取样重量吗？", function(data) {
					if (data) {
						$.ajax({
							type : "post",
							url : '<c:url value="/exception/queryMeasureInfoReturnQA.do"/>',
							dataType : "json",
							data : {matchid : selectedRows[0].matchid},
							success : function(data) {
								if (data.success == true) {
									successbox(data.msg);
									$('#MakecardInfoGrid').bootstrapTable('refresh');
								} else {
									errorbox(data.msg);
								}
							}
						});
					}
			})			
		};
	}
	</script>
</body>
</t:html>