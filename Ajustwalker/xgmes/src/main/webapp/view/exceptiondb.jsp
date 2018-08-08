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
				<input type="hidden" id="unitcodes" value="${unitcode}"> 
				<input type="hidden" id="types" name="types" value="0">
				<div class="row">
					<div class="col-sm-3">
						<div class='form-group input-group input-group-sm date'>
							<span class="input-group-addon">开始时间</span>
							 <input type='text' class="form-control" placeholder="开始时间" id="begintime" name="begintime" value="<%=begintime%>" />
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">物&nbsp;&nbsp;流&nbsp;&nbsp;号</span>
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
				</div>
				<div class="row">
					<div class="col-sm-3">
						<div class='form-group input-group input-group-sm date'>
							<span class="input-group-addon">结束时间</span>
							<input type='text' class="form-control" placeholder="结束时间" id="endtime" name="endtime" value="<%=endtime%>" />
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">业&nbsp;&nbsp;务&nbsp;&nbsp;号</span>
							 <input type="text" class="form-control" placeholder="业务号" id="planid" name="planid" aria-describedby="sizing-addon3">
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
					<div class="col-sm-3">
						<div class="form-group input-group  btn-group-sm">
							<t:button text="查询" modulecode="DiaoBoYiChang" id="query" btnclass="btn btn-info" iconclass="glyphicon glyphicon-search" onclick="queryinfo()" />
							<t:button text="添加" modulecode="DiaoBoYiChang" id="MeasureBtn" btnclass="btn btn-success" iconclass="glyphicon glyphicon-plus" />
						    <t:button text="EXCEL" modulecode="DiaoBoYiChang" id="DBExcel" btnclass="btn btn-warning" iconclass="fa fa-file-excel-o" onclick="exportExcel()" /> 
							    
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<table id="MeasureInfoGrid" data-toggle="table"
						data-row-style="rowStyle" 
						data-query-params="queryParams"
						data-pagination="true" 
						data-page-size="10"
						data-page-list="[10,40,ALL]" 
						data-mobile-responsive="true">
				<thead>
					<tr>
						<%-- <th data-field="state" data-radio="true"></th> --%>
						<th data-field="matchid" data-halign="center"
							data-searchable="true" sortable data-formatter="ExceptionFormatter">物流号</th>
						<%-- <th data-field="operatype" class="text-nowrap"
							data-halign="center" data-searchable="true" sortable>业务类型</th> --%>
						<th data-field="taskcode" data-halign="center"
							data-searchable="true" class="text-nowrap" sortable>业务号</th>
						<th data-field="carno" data-halign="center" data-searchable="true"
							class="text-nowrap">车号</th>
						<th data-field="icid" data-halign="center" data-searchable="true"
							class="text-nowrap" data-visible="false">卡号</th>
						<th data-field="materialname" data-halign="center"
							data-searchable="true" class="text-nowrap">货名</th>
						<th data-field="sourcename" data-halign="center"
							data-searchable="true" class="text-nowrap">供货</th>
						<th data-field="targetname" data-halign="center"
							data-searchable="true" class="text-nowrap">收货</th>
						<th data-field="gross" data-halign="center"
							data-searchable="true" class="text-nowrap" >毛重/t</th>
						<th data-field="tare" data-halign="center" data-searchable="true"
							class="text-nowrap">皮重/t</th>
						<th data-field="suttle" data-halign="center"
							data-searchable="true" class="text-nowrap">净重/t</th>
						<th data-field="deduction" data-halign="center"
							data-searchable="true" class="text-nowrap">扣重/t</th>
						<th data-field="suttleb" data-halign="center"
							data-searchable="true" class="text-nowrap">扣后净重/t</th>		
						<th data-field="grosstime" data-halign="center"
							data-searchable="true" class="text-nowrap">毛重时间</th>
					   <th data-field="taretime" data-halign="center" data-searchable="true"
							class="text-nowrap">皮重时间</th>
					    <th data-field="deductiontime" data-halign="center" data-searchable="true"
							class="text-nowrap">扣重时间</th>
						<th data-field="deductionname" data-halign="center" data-searchable="true"
							class="text-nowrap">扣重人</th>	
						<th data-field="usermemo" data-halign="center"
							data-searchable="true" class="text-nowrap">备注</th>
						<th data-width="70px" data-align="center" data-valign="middle"
							data-formatter="operateFormatter" data-events="operateEvents">操作</th>
					</tr>
				</thead>
			</table>

		</div>
	</div>
	<!-- ---------------------------------------计量 -------------------------------------------------------------->

	<div class="modal fade" id="MeasureWindow" tabindex="-1" role="dialog" aria-labelledby="MeasureWindowLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">计量</h4>
				</div>
				<div class="modal-body">
					<form id="MeasureForm">
						<input type="hidden" id="matchid" name="matchid" value="-1" />
						<input type="hidden" id="mflag" name="mflag" value="0" />
						<input type="hidden" id="tarehour" name="tarehour" value="0" />
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">卡　　　号</span> 
									<input id="icid" name="icid" type="text" class="form-control" readonly="readonly"	aria-describedby="basic-addon1">									
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-btn">
										<button class="btn btn-default" type="button" onclick="checkCarno()"><span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>车　　号</button>
									</span>
									<input id="carnos" name="carno" type="text" onchange="gettare()"  class="form-control" value="" aria-describedby="basic-addon1" required data-bv-notempty-message="请填写车号">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-btn">
										<button class="btn btn-default" type="button" onclick="checkTakecode()">
										<span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>业&nbsp;&nbsp;务&nbsp;&nbsp;号</button>
									</span>
									<input type="hidden" id="operatype" name="operatype" value="" />
									<input id="taskcode" name="taskcode" type="text" class="form-control" aria-describedby="basic-addon1" required data-bv-notempty-message="请填写业务号">
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">货　　　名</span> 
									<input id="materialcode" name="materialcode" type="hidden" value="" >
									<input id="materialname" name="materialname" type="text" readonly="readonly" class="form-control" aria-describedby="basic-addon1" required data-bv-notempty-message="请填写货名">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">供　　　货</span> 
									<input id="sourcecode" name="sourcecode" type="hidden">
									<input id="sourcename" name="sourcename" type="text" readonly="readonly" class="form-control" aria-describedby="basic-addon1" required data-bv-notempty-message="请填写供货">
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">收　　　货</span> 
									<input id="targetcode" name="targetcode" type="hidden" value="" >
									<input id="targetname" name="targetname" type="text" class="form-control" value=""  readonly="readonly" data-provide="typeahead" aria-describedby="basic-addon1"required data-bv-notempty-message="请填写收货">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">毛 &nbsp;　　重/t</span>
									<input name="gross" id="gross"  class="form-control" aria-describedby="basic-addon1" type="text"  data-bv-numeric  data-bv-numeric-message="请输入数字" >
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">皮 &nbsp;　　重/t</span>
									<input id="tare" name="tare" class="form-control" aria-describedby="basic-addon1"  type="text"  data-bv-numeric data-bv-numeric-message="请输入数字">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">毛&nbsp;重&nbsp;时&nbsp;间&nbsp;</span>
									<input name="grosstime" id="grosstime" type="text" class="form-control" aria-describedby="basic-addon1" >
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">皮&nbsp;重&nbsp;时&nbsp;间&nbsp;</span>
									<input name="taretime" id="taretime" type="text" class="form-control" aria-describedby="basic-addon1" >
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
									<input name="grossweigh" id="grossweigh" type="hidden" class="form-control" value="">
									<t:combobox id="grossweighid" name="grossweighid" url="/bcommon/queryEqunameinfo.do" label="毛&nbsp;重&nbsp;衡&nbsp;器&nbsp;" require="false" allowclear="true" />
							</div>
							<div class="col-sm-6">
									<input name="tareweigh" id="tareweigh" type="hidden" class="form-control" value="">
									<t:combobox id="tareweighid" name="tareweighid" url="/bcommon/queryEqunameinfo.do" label="皮&nbsp;重&nbsp;衡&nbsp;器&nbsp;" require="false" allowclear="true"  />
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">毛重计量员</span> 
									<input name="grossoperaname" type="text" class="form-control"	aria-describedby="basic-addon1">
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">皮重计量员</span>
									 <input	name="tareoperaname" type="text" class="form-control"	aria-describedby="basic-addon1">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">备&nbsp;注&nbsp;信&nbsp;息&nbsp;</span>
									<input name="usermemo" id="usermemo" class="form-control" placeholder="选填" rows="1" required="required"></input>
								</div>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer  btn-group-sm">
				<button type="button" class="btn btn-success" id="MeasureaddBtn"	onclick="insert()">确认</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			</div>
		 </div>
		</div>
	</div>
		<!-- ---------------------------------------扣重 -------------------------------------------------------------->

	<div class="modal fade" id="MDeductionWindow" tabindex="-1" role="dialog" aria-labelledby="MDeductionWindowLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">扣重</h4>
				</div>
				<div class="modal-body">
					<form id="MDeductionForm">
						<input type="hidden" id="mdeduction" name="matchid" value="-1" />
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">卡　　　号</span> 
									<input id="icid" name="icid" type="text" class="form-control" readonly="readonly"	aria-describedby="basic-addon1">									
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">
										车　　　号
									</span>
									<input id="carnos" name="carno" type="text" readonly="readonly"  class="form-control" value="" aria-describedby="basic-addon1" required data-bv-notempty-message="请填写车号">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">
										业　务　号
									</span>
									<input type="hidden" id="operatype" name="operatype" value="" />
									<input id="taskcode" name="taskcode" type="text" readonly="readonly" class="form-control" aria-describedby="basic-addon1" required data-bv-notempty-message="请填写业务号">
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">货　　　名</span> 
									<input id="materialcode" name="materialcode" type="hidden" value="" >
									<input id="materialname" name="materialname" type="text" readonly="readonly" class="form-control" aria-describedby="basic-addon1" required data-bv-notempty-message="请填写货名">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">供　　　货</span> 
									<input id="sourcecode" name="sourcecode" type="hidden">
									<input id="sourcename" name="sourcename" type="text" readonly="readonly" class="form-control" aria-describedby="basic-addon1" required data-bv-notempty-message="请填写供货">
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">收　　　货</span> 
									<input id="targetcode" name="targetcode" type="hidden" value="" >
									<input id="targetname" name="targetname" type="text" class="form-control" value=""  readonly="readonly" data-provide="typeahead" aria-describedby="basic-addon1"required data-bv-notempty-message="请填写收货">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">毛 &nbsp;　　重/t</span>
									<input name="gross" id="gross"  class="form-control" aria-describedby="basic-addon1" type="text" readonly="readonly"  data-bv-numeric  data-bv-numeric-message="请输入数字" >
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">皮 &nbsp;　　重/t</span>
									<input id="tare" name="tare" class="form-control" aria-describedby="basic-addon1"  type="text"  readonly="readonly" data-bv-numeric data-bv-numeric-message="请输入数字">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">毛&nbsp;重&nbsp;时&nbsp;间&nbsp;</span>
									<input name="grosstime" id="grosstime" type="text" class="form-control" readonly="readonly" aria-describedby="basic-addon1" >
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">皮&nbsp;重&nbsp;时&nbsp;间&nbsp;</span>
									<input name="taretime" id="taretime" type="text" class="form-control" readonly="readonly" aria-describedby="basic-addon1" >
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
									<input name="grossweigh" id="grossweigh" type="hidden" class="form-control" value="">
									<t:combobox id="grossweighid" name="grossweighid" url="/bcommon/queryEqunameinfo.do" label="毛&nbsp;重&nbsp;衡&nbsp;器&nbsp;" require="false" allowclear="true" readonly="readonly" />
							</div>
							<div class="col-sm-6">
									<input name="tareweigh" id="tareweigh" type="hidden" class="form-control" value="">
									<t:combobox id="tareweighid" name="tareweighid" url="/bcommon/queryEqunameinfo.do" label="皮&nbsp;重&nbsp;衡&nbsp;器&nbsp;" require="false" allowclear="true" readonly="readonly"  />
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">毛重计量员</span> 
									<input name="grossoperaname" type="text" class="form-control" readonly="readonly"	aria-describedby="basic-addon1">
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">皮重计量员</span>
									 <input	name="tareoperaname" type="text" class="form-control" readonly="readonly"	aria-describedby="basic-addon1">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">扣 &nbsp;　　重/t</span>
									<input name="deduction" id="deduction"  class="form-control" aria-describedby="basic-addon1" type="text"  required="required"  data-bv-numeric  data-bv-numeric-message="请输入数字" >
								</div>
							</div>
							
						</div>
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">备&nbsp;注&nbsp;信&nbsp;息&nbsp;</span>
									<input name="usermemo" id="usermemo" class="form-control" placeholder="选填" rows="1" ></input>
								</div>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer  btn-group-sm">
				<button type="button" class="btn btn-success" id="MeasureaddBtn"	onclick="saveDeductiondata()">确认</button>
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
	jQuery(document).ready(function($) {
		queryinfo();	
	  /*  $.fn.typeahead.Constructor.prototype.blur = function() {
		      var that = this;
		      setTimeout(function () { that.hide() }, 250);
	   };

	   $('#carnos').typeahead({source: function (query, process) {		
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
		}); */
		  
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
		$('#MeasureInfoGrid').bootstrapTable('refresh', {url : "<c:url value='/exception/queryExceptiondbList.do'/>"});
	}
	
	//------------------------------------计量---------------------------------------
	
	function operateFormatter(value, row, index) {
		return [
                '<div class="pull-left">',
               	'<t:ibutton text="修改" modulecode="DiaoBoYiChang" id="exceptiondbmodify" btnclass="edit" iconclass="glyphicon glyphicon-pencil"/>',
               	'<t:ibutton text="移除" modulecode="DiaoBoYiChang" id="exceptiondbremove" btnclass="remove" iconclass="glyphicon glyphicon-trash"/>',
               	'<t:ibutton text="扣重" modulecode="DiaoBoYiChang" id="exceptiondbdeduction" btnclass="deduction" iconclass="glyphicon glyphicon-list-alt"/>',
               	'</div>'
             	].join('');			
	}
	window.operateEvents = {
		'click .edit' : function(e, value, row) {
			openMakecard(row.matchid);
		},
		'click .remove' : function(e, value, row) {
			cancelExceptionDB(row.matchid);
		},
		'click .deduction' : function(e, value, row) {
			openMDeduction(row.matchid);
		}
	};
	$('#MeasureBtn').on('click', function() {
		openMakecard(-1);
	});
	function openMakecard(id_vv) {	
		$("#matchid").val(id_vv);
		$('#MeasureWindow').modal('show');
	}
	function openMDeduction(matchid_vv) {	
		$("#mdeduction").val(matchid_vv);
		$('#MDeductionWindow').modal('show');
	}
	  
	
	$('#MeasureWindow').on('shown.bs.modal',function() {
		var matchid = $("#matchid").val();
		loadFormData('MeasureForm','<c:url value="/exception/queryExcedbBymatchid.do?matchid="/>'+matchid ,function(data){
		}); 
    });
	$('#MDeductionWindow').on('shown.bs.modal',function() {
		var matchid = $("#mdeduction").val();
		loadFormData('MDeductionForm','<c:url value="/exception/queryExcedbBymatchid.do?matchid="/>'+matchid ,function(data){
		}); 
    });
	/**
	 *作废厂内信息
	 */
	function cancelExceptionDB(matchid) {
		dialogbox("请确认", "确认删除此项目？",function(data){
			if(data){
				$.ajax({
						type : "post",
						url : '<c:url value="/exception/cancelExceptionDB.do"/>',
						dataType : "json",
						data : {matchid : matchid},
						success : function(data) {
						if (data.success) {
							successbox(data.msg);					
							$('#MeasureInfoGrid').bootstrapTable('refresh');
						} else {
							errorbox(data.msg);
						}
					}
				});
			}
	   });
	}
	//操作厂内调拨信息		

	function insert() {
		var mflag = $('#MeasureForm input[name="mflag"]').val();
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
		if (gross == 0 && tare == 0) {
			errorbox("请填写重量信息");
		} else {
			if(gross>=tare && tare>0){
	 			if(grosstime!='' && grossoperaname!='' && grossweighid!='' && taretime!=''&& tareoperaname!='' && tareweighid!='' ){
	 				if (mflag == 1 ) {//先毛后皮
	 					if(grosstime<taretime){
	 						savedata();//保存数据信息
	 					}else{
	 						errorbox("先毛后皮业务，毛重时间应早于皮重时间");
	 					}			 		 
					} else if (mflag == 2 ) {//先皮后毛
						if(grosstime>taretime){
	 					    savedata();//保存数据信息
	 					}else{
	 					   errorbox("先皮后毛业务，皮重时间应早于毛重时间");
	 					} 
					}
	 			}else if(grosstime==''|| grossoperaname==''|| grossweighid!=''|| taretime==''|| tareoperaname=='' ||tareweighid!='' ){
	 				errorbox("请填写完整重量信息")
	 			}
	 		}else if(gross<tare && gross>0 ){
	 			errorbox("毛重必须大于等于皮重")
	 		}else{
	 			if (mflag == 1 ) {//先毛后皮
	 				if(gross==0 && tare>0){
	 					errorbox("请填写毛重信息")
	 				}else if(gross>0 && tare==0){
	 					if(grosstime!='' && grossoperaname!='' && grossweighid!=''){
	 		 				savedata();//保存数据信息
	 		 			}else if(grosstime=='' || grossoperaname=='' || grossweighid!=''){
	 		 				errorbox("请填写完整毛重信息")
	 		 			}
	 				}			 		 
				} else if (mflag == 2 ) {//先皮后毛
					if(gross > 0 && tare == 0){
						errorbox("请填写皮重信息");
					}else{gross == 0 && tare > 0}{
						if(taretime!='' && tareoperaname!='' && tareweighid!=''){
	 		 				savedata();//保存数据信息
	 		 			}else if(taretime=='' || tareoperaname==''|| tareweighid!=''){
	 		 				errorbox("请填写完整皮重信息")
	 		 			}
					}
				     
				}
	 		}
		 	 
		}

	}
	function savedata(){		
		saveFormData('MeasureForm','<c:url value="/exception/insertExceptionDB.do"/>',function(data) {
				if (data.success) {
					successbox(data.msg);
					$('#MeasureWindow').modal('toggle');
					$('#MeasureInfoGrid').bootstrapTable('refresh');
				} else {
					errorbox(data.msg);
				}
			});
		
	} 
	function saveDeductiondata(){		
		saveFormData('MDeductionForm','<c:url value="/exception/updateDeducation.do"/>',function(data) {
				if (data.success) {
					successbox(data.msg);
					$('#MDeductionWindow').modal('toggle');
					$('#MeasureInfoGrid').bootstrapTable('refresh');
				} else {
					errorbox(data.msg);
				}
			});
		
	}

	/*根据卡号判断卡号车辆状态*/
	function queryInfoBycarno() {
		$.ajax({
			type : "post",
			url : '<c:url value="/bcommon/judgCarno.do"/>',
			dataType : "json",
			data : {
				cardid : $("#icid").val()
			},
			success : function(data) {
				if (data.success == true) { //卡和车辆状态正常，根据车号查询业务信息
					$("#carnos").val(data.rows[0].carno);
					$("#cardstate").val(data.rows[0].validflag)
					validForm('MakeCardDetailForm');
				} else {//如果车辆或者卡有问题，系统提示
					errorbox(data.msg);
				}
			}
		});
	}

		$('#MakecardInfoGrid').bootstrapTable({onDblClickRow : function(row, $element) {
				layer.open({
							type : 2,
							title : '信息详情',
							maxmin : true, //开启最大化最小化按钮
							area : [ '90%', '90%' ],
							content : '<c:url value="/bcommon/showdetail.do?matchid="/>'+ row.matchid //注意，如果str是object，那么需要字符拼接。
				});
			}
		});
		//---------------------------打开页面查询车号信息---------------------------------------
		var carnoLayer;
		function checkCarno() {
			carnoLayer = layer.open({
				type : 2,
				title : '车牌号选择',
				maxmin : true, //开启最大化最小化按钮
				area : [ '90%', '90%' ],
				content : '<c:url value="/bcommon/queryCardhead.do"/>'//注意，如果str是object，那么需要字符拼接。
			});
		}

		function takeBackCarno(carno_v) {
			$("#MeasureForm input[name='carno']").val(carno_v);
			 var taskcode=$("#MeasureForm input[name='taskcode']").val();
			 var tarehour=$("#MeasureForm input[name='tarehour']").val();
			 var mflag=$("#MeasureForm input[name='mflag']").val();
			 if(carno_v!=null) {
			  $.ajax({
					type : "post",
					url : '<c:url value="/bcommon/getTareBYCarnoT.do"/>',
					dataType : "json",
					data : {taskcode : taskcode,mflag:mflag,tarehour:tarehour,carno:carno_v},
					success : function(data) {
					if (data.success == true) {
						if(data.more.matchid!=null && data.more.matchid!=''){
							$("#MeasureForm input[name='matchid']").val(data.more.matchid);
						}
					   
					   if(taskcode==null||taskcode==''){
						    $("#MeasureForm input[name='taskcode']").val(data.more.taskcode);
						    $("#MeasureForm input[name='operatype']").val(data.more.operatype);
						    $("#MeasureForm input[name='materialcode']").val(data.more.materialcode);
						    $("#MeasureForm input[name='materialname']").val(data.more.materialname);
						    $("#MeasureForm input[name='sourcecode']").val(data.more.sourcecode);
						    $("#MeasureForm input[name='sourcename']").val(data.more.sourcename);
						    $("#MeasureForm input[name='targetcode']").val(data.more.targetcode);
						    $("#MeasureForm input[name='targetname']").val(data.more.targetname);
						    $("#MeasureForm input[name='mflag']").val(data.more.mflag);
						    $("#MeasureForm input[name='tarehour']").val(data.more.tarehour);
						   }
					   $("#MeasureForm input[name='icid']").val(data.more.icid);
					   $("#MeasureForm input[name='tare']").val(data.more.tare);	
					   $("#MeasureForm input[name='taretime']").val(data.more.taretime);
					   $("#tareweighid").val(data.more.tareweighid);
					   $("#MeasureForm input[name='tareweigh']").val(data.more.tareweigh);
					   $("#tareweighid").trigger('change.select2');
					   $("#MeasureForm input[name='tareoperaname']").val(data.more.tareoperaname);
					}
					validForm('MeasureForm');	
			     }
		        });
			 }
		}
		
		function gettare(){
			 var carno=$("#MeasureForm input[name='carno']").val();
			 var taskcode=$("#MeasureForm input[name='taskcode']").val();
			 var tarehour=$("#MeasureForm input[name='tarehour']").val();
			 var mflag=$("#MeasureForm input[name='mflag']").val();
			 if(carno!=null) {
			  $.ajax({
					type : "post",
					url : '<c:url value="/bcommon/getTareBYCarnoT.do"/>',
					dataType : "json",
					data : {taskcode : taskcode,mflag:mflag,tarehour :tarehour,carno:carno},
					success : function(data) {
					if (data.success == true) {
						if(data.more.matchid!=null && data.more.matchid!=''){
							$("#MeasureForm input[name='matchid']").val(data.more.matchid);
						}
					   if(taskcode==null||taskcode==''){
					    $("#MeasureForm input[name='taskcode']").val(data.more.taskcode);
					    $("#MeasureForm input[name='operatype']").val(data.more.operatype);
					    $("#MeasureForm input[name='materialcode']").val(data.more.materialcode);
					    $("#MeasureForm input[name='materialname']").val(data.more.materialname);
					    $("#MeasureForm input[name='sourcecode']").val(data.more.sourcecode);
					    $("#MeasureForm input[name='sourcename']").val(data.more.sourcename);
					    $("#MeasureForm input[name='targetcode']").val(data.more.targetcode);
					    $("#MeasureForm input[name='targetname']").val(data.more.targetname);
					    $("#MeasureForm input[name='mflag']").val(data.more.mflag);
					    $("#MeasureForm input[name='tarehour']").val(data.more.tarehour);
					   }
					   
					   $("#MeasureForm input[name='icid']").val(data.more.icid);
					   $("#MeasureForm input[name='tare']").val(data.more.tare);	
					   $("#MeasureForm input[name='taretime']").val(data.more.taretime);
					   $("#tareweighid").val(data.more.tareweighid);
					   $("#MeasureForm input[name='tareweigh']").val(data.more.tareweigh);
					   $("#tareweighid").trigger('change.select2');
					   $("#MeasureForm input[name='tareoperaname']").val(data.more.tareoperaname);
					}
					validForm('MeasureForm');	
			     }
		        });
			 }
		}

		function closeCheckCarno() {
			layer.close(carnoLayer);
		}
		//------------------------打开页面选择业务号信息------------------------------------------

		var taskcodeLayer;
		function checkTakecode() {
			taskcodeLayer = layer.open({
				type : 2,
				title : '业务号信息选择',
				maxmin : true, //开启最大化最小化按钮
				area : [ '90%', '90%' ],
				content : '<c:url value="/bcommon/taskcodeinfo.do"/>'//注意，如果str是object，那么需要字符拼接。
			});
		}

		function takeBackTaskcode(taskcode, operatype, materialcode,
				materialname, sourcecode, sourcename, targetcode, targetname,
				mflag,tarehour) {
			$("#MeasureForm input[name='taskcode']").val(taskcode);
			$("#MeasureForm input[name='operatype']").val(operatype);
			$("#MeasureForm input[name='materialcode']").val(materialcode);
			$("#MeasureForm input[name='materialname']").val(materialname);
			$("#MeasureForm input[name='sourcecode']").val(sourcecode);
			$("#MeasureForm input[name='sourcename']").val(sourcename);
			$("#MeasureForm input[name='targetcode']").val(targetcode);
			$("#MeasureForm input[name='targetname']").val(targetname);
			$("#MeasureForm input[name='mflag']").val(mflag);
			$("#MeasureForm input[name='tarehour']").val(tarehour);
			 var carno=$("#MeasureForm input[name='carno']").val();
			 if(carno!=null) {
			  $.ajax({
					type : "post",
					url : '<c:url value="/bcommon/getTareBYCarnoT.do"/>',
					dataType : "json",
					data : {taskcode : taskcode,mflag:mflag,tarehour :tarehour,carno:carno},
					success : function(data) {
					if (data.success == true) {
						if(data.more.matchid!=null && data.more.matchid!=''){
							$("#MeasureForm input[name='matchid']").val(data.more.matchid);
						}
					   $("#MeasureForm input[name='tare']").val(data.more.tare);	
					   $("#MeasureForm input[name='taretime']").val(data.more.taretime);
					   $("#tareweighid").val(data.more.tareweighid);
					   $("#MeasureForm input[name='tareweigh']").val(data.more.tareweigh);
					   $("#tareweighid").trigger('change.select2');
					   $("#MeasureForm input[name='tareoperaname']").val(data.more.tareoperaname);
					   $("#MeasureForm input[name='icid']").val(data.more.icid);
					}
					validForm('MeasureForm');	
			     }
		        });
			 }
		}

		function closeTakecode() {
			layer.close(taskcodeLayer);
		}

		function OpenMeasure() {
			var selectedRows = $('#MakecardInfoGrid').bootstrapTable('getSelections');
			if (selectedRows.length == 0) {
				errorbox("请选择信息");
			} else {
				$('#MeasureForm input[name="matchid"]').val(selectedRows[0].matchid);
				$('#MeasureWindow').modal('show');
			}
		}
		$('#MeasureWindow').on('shown.bs.modal',function() {
			loadFormData('MeasureForm','<c:url value="/exception/queryExcedbBymatchid.do?matchid="/>'+ $('#MeasureForm input[name="matchid"]').val(), function(data) {});
		});
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
		 function exportExcel() {
				remoteExportExcel('MeasureInfoGrid','', '', 'queryform');
		 }
	</script>
</body>
</t:html>