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
					<div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">组　　号</span>
							<input type="text" class="form-control" placeholder="组号" name="grossgroupno" aria-describedby="sizing-addon3">
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
							<span class="input-group-addon" id="sizing-addon3">发　　站</span>
							<input type="text" class="form-control" placeholder="发站"  name="sourceplace" aria-describedby="sizing-addon3">
						</div>
					</div>
					<!-- <div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">单&nbsp;&nbsp;据&nbsp;&nbsp;号</span>
							 <input type="text" class="form-control" placeholder="单据号"
							id="planid" name="planid" aria-describedby="sizing-addon3">
						</div>
					</div> -->
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
					<div class="col-sm-2">
							<div class="form-group input-group input-group-sm">
								<span class="input-group-addon" id="sizing-addon3">业务类型</span>
								<select id="operatype" name="operatype" class="form-control select2">
									<option value="">全部</option>
									<option value="95">火运进厂</option>
									<option value="85">火运出厂</option>
								</select>
							</div>
					</div>
				</div>
				<div class="row">
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
							data-halign="center" data-searchable="true" sortable>业务类型</th> 
						<th data-field="planid" data-halign="center"
							data-searchable="true" class="text-nowrap" sortable>计划号</th>--%>
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
						<th data-field="sourceplace" data-halign="center"
							data-searchable="true" class="text-nowrap">发站</th>	
						<th data-field="gross" data-halign="center"
							data-searchable="true" class="text-nowrap" >毛重/t</th>
						<th data-field="grossgroupno" data-halign="center"
						data-searchable="true" class="text-nowrap" >毛重组号</th>
						<th data-field="grossserial" data-halign="center"
						data-searchable="true" class="text-nowrap" >毛重序号</th>
						<th data-field="tare" data-halign="center" data-searchable="true"
							class="text-nowrap">皮重/t</th>
						<th data-field="taregroupno" data-halign="center" data-searchable="true"
						class="text-nowrap">皮重组号</th>
						<th data-field="tareserial" data-halign="center" data-searchable="true"
						class="text-nowrap">皮重序号</th>
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
						<input type="hidden" id="memo1" name="memo1" value="" />
						<input type="hidden" id="memo6" name="memo6" value="" /><!-- 
						<input type="hidden" id="memo8" name="memo8" value="" /> -->
						<div class="row">							
							<!-- <div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
								    <span class="input-group-addon">车　　　号</span> 
								    <span class="input-group-btn">
										<button class="btn btn-default" type="button" onclick="checkPlanid()">
										<span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>车　　号</button>
									</span>
								    
									<input id="carnos" name="carno" type="text"  class="form-control" value="" aria-describedby="basic-addon1" required data-bv-notempty-message="请填写车号">
								</div>
							</div> -->
							<div class="col-sm-6">
						      <t:combobox id="operatypeadd" name="operatype"  url="/bcommon/queryOperatype.do?cartype=T" label="业&nbsp;务&nbsp;类&nbsp;型&nbsp;"  require="true" alloptions="true" allowclear="true"/>	
					        </div>
					         <div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
								    <span class="input-group-addon">车　　　型</span>
									<input id="memo8"   name="memo8" type="text"  class="form-control" value="" aria-describedby="basic-addon1" required data-bv-notempty-message="请填写车号">
								</div>
							</div> 
						</div>
						<div class="row">
							<div class="col-sm-6">
									<div class="form-group input-group input-group-sm">
									    <span class="input-group-addon">车　　　号</span>
										<input id="carnos" name="carno" type="text"  class="form-control" value="" aria-describedby="basic-addon1" required data-bv-notempty-message="请填写车号">
									</div>
								</div>
								
							<!-- <div class="col-sm-6">
								<div class="form-group input-group input-group-sm">									
									<span class="input-group-addon">计　划　号</span>
									<input id="planid" name="planid" type="text" class="form-control" aria-describedby="basic-addon1" required data-bv-notempty-message="请选择计划号">
								</div>
							</div> -->
							<div class="col-sm-6">					 
									<input id="materialnames" name="materialname" type="hidden" class="form-control text-nowrap" aria-describedby="basic-addon1" >
									<t:combobox id="materialcode" name="materialcode"  url="/bcommon/queryHcMateril.do"  label="货　　　名" require="true" pagination="true" allowclear="true" alloptions="true" />
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
									<input id="sourcenames" name="sourcename" type="hidden" class="form-control text-nowrap" aria-describedby="basic-addon1" > 
									<t:combobox id="sourcecode" name="sourcecode"  url="/bcommon/queryHcCustomer.do" label="供　　　货" require="false" pagination="true" allowclear="true" alloptions="true"/> 	
							</div>
							<div class="col-sm-6">
									 <input id="targetnames" name="targetname" type="hidden" class="form-control" aria-describedby="basic-addon1" >	
									 <t:combobox id="targetcode" name="targetcode"  url="/bcommon/queryHcCustomer.do" label="收　　　货" require="false" pagination="true" allowclear="true" alloptions="true" />
							</div>
						</div>
					    <div class="row">
							<div class="col-sm-6">
								<t:combobox id="sourceplace" name="sourceplace"  url="/bcommon/queryStation.do" label="发　　　站" require="false" pagination="true" allowclear="true" alloptions="true"/> 	
							</div>
							<div class="col-sm-6">
								<t:combobox id="targetplace" name="targetplace"  url="/bcommon/queryStation.do" label="到　　　站" require="false" pagination="true" allowclear="true" alloptions="true" />
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">毛重车次号</span> 
									<input name="grossgroupno" type="text" class="form-control"	aria-describedby="basic-addon1" data-bv-numeric data-bv-numeric-message="请输入数字">
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">皮重车次号</span>
									 <input	name="taregroupno" type="text" class="form-control"	aria-describedby="basic-addon1" data-bv-numeric data-bv-numeric-message="请输入数字">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">毛&nbsp;重&nbsp;序&nbsp;号&nbsp;</span> 
									<input name="grossserial" type="text" class="form-control"	aria-describedby="basic-addon1" data-bv-numeric data-bv-numeric-message="请输入数字">
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">皮&nbsp;重&nbsp;序&nbsp;号&nbsp;</span>
									 <input	name="tareserial" type="text" class="form-control"	aria-describedby="basic-addon1" data-bv-numeric data-bv-numeric-message="请输入数字">
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
								<t:combobox id="grossweighid" name="grossweighid" url="/bcommon/queryEqunameinfoByType.do?operatype=T" label="毛&nbsp;重&nbsp;衡&nbsp;器&nbsp;" require="false" allowclear="true" />
							</div>
							<div class="col-sm-6">
								<input name="tareweigh" id="tareweigh" type="hidden" class="form-control" value="">
								<t:combobox id="tareweighid" name="tareweighid" url="/bcommon/queryEqunameinfoByType.do?operatype=T" label="皮&nbsp;重&nbsp;衡&nbsp;器&nbsp;" require="false" allowclear="true"  />
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
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">扣　　　重</span> 
									<input name="deduction" type="text" class="form-control"	aria-describedby="basic-addon1">
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">比对&nbsp;重&nbsp;量/t</span> 
									<input name="planweight" type="text" class="form-control"	aria-describedby="basic-addon1" readonly="readonly">
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
		$('#MeasureInfoGrid').bootstrapTable('refresh', {url : "<c:url value='/exception/queryExceptionhcList.do'/>"});
	}
	
	//------------------------------------计量---------------------------------------
	
	function operateFormatter(value, row, index) {
		return [
                '<div class="pull-left">',
               	'<t:ibutton text="修改" modulecode="DiaoBoYiChang" id="exceptiondbmodify" btnclass="edit" iconclass="glyphicon glyphicon-pencil"/>',
               	'<t:ibutton text="移除" modulecode="DiaoBoYiChang" id="exceptiondbremove" btnclass="remove" iconclass="glyphicon glyphicon-trash"/>',
              	'</div>'
             	].join('');			
	}
	window.operateEvents = {
		'click .edit' : function(e, value, row) {
			openMakecard(row.matchid);
		},
		'click .remove' : function(e, value, row) {
			cancelExceptionDB(row.matchid);
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
		loadFormData('MeasureForm','<c:url value="/exception/queryExcehcBymatchid.do?matchid="/>'+matchid ,function(data){
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
	//操作火车信息		

	function insert() {
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
		$("#sourcenames").val($('#sourcecode option:selected').text());
		$("#targetnames").val($('#targetcode option:selected').text());
		$("#materialnames").val($('#materialcode option:selected').text());
		var operatype=$("#operatypeadd").val();
		if (gross == 0 && tare == 0) {
			errorbox("请填写重量信息");
		} else {
			if(gross>=tare && tare>0){
	 			if(grosstime!='' && grossoperaname!='' && grossweighid!='' && taretime!=''&& tareoperaname!='' && tareweighid!='' ){
	 				if (operatype==95 ) {//先毛后皮
	 					if(grosstime<taretime){
	 						savedata();//保存数据信息
	 					}else{
	 						errorbox("先毛后皮业务，毛重时间应早于皮重时间");
	 					}			 		 
					} else if (operatype==85 ) {//先皮后毛
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
	 			if (operatype==95 ) {//先毛后皮
	 				if(gross==0 && tare>0){
	 					errorbox("请填写毛重信息")
	 				}else if(gross>0 && tare==0){
	 					if(grosstime!='' && grossoperaname!='' && grossweighid!=''){
	 		 				savedata();//保存数据信息
	 		 			}else if(grosstime=='' || grossoperaname=='' || grossweighid!=''){
	 		 				errorbox("请填写完整毛重信息")
	 		 			}
	 				}			 		 
				} else if (operatype==85 ) {//先皮后毛
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
		saveFormData('MeasureForm','<c:url value="/exception/beforInsertHc.do"/>',function(data) {
				if (data.success) {
					successbox(data.msg);
					$('#MeasureWindow').modal('toggle');
					$('#MeasureInfoGrid').bootstrapTable('refresh');
				} else {
					if(data.mfunc==2){
						dialogbox("请确认", data.msg,function(data){
							saveFormData('MeasureForm','<c:url value="/exception/insertHcException.do"/>',function(data) {
								if (data.success) {
									successbox(data.msg);
									$('#MeasureWindow').modal('toggle');
									$('#MeasureInfoGrid').bootstrapTable('refresh');
								} else {
									errorbox(data.msg);
								}
							});
						});
					}
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
		//------------------------打开页面选择业务号信息------------------------------------------

		var planidLayer;
		function checkPlanid() {
			planidLayer = layer.open({
				type : 2,
				title : '业务号信息选择',
				maxmin : true, //开启最大化最小化按钮
				area : [ '90%', '90%' ],
				content : '<c:url value="/bcommon/taskcodeinfo.do"/>'//注意，如果str是object，那么需要字符拼接。
			});
		}

		function takeBackPlanid(carno,planid, operatype, materialcode,
				materialname, sourcecode, sourcename, targetcode, targetname,
				sourceplace,targetplace) {
			$("#MeasureForm input[name='carno']").val(carno);
			$("#MeasureForm input[name='planid']").val(planid);
			$("#MeasureForm input[name='operatype']").val(operatype);
			$("#MeasureForm input[name='materialcode']").val(materialcode);
			$("#MeasureForm input[name='materialname']").val(materialname);
			$("#MeasureForm input[name='sourcecode']").val(sourcecode);
			$("#MeasureForm input[name='sourcename']").val(sourcename);
			$("#MeasureForm input[name='targetcode']").val(targetcode);
			$("#MeasureForm input[name='targetname']").val(targetname);
			$("#MeasureForm input[name='sourceplace']").val(sourceplace);
			$("#MeasureForm input[name='targetplace']").val(targetplace);
		}

		function closePlanid() {
			layer.close(planidLayer);
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
	/* 	$('#MeasureWindow').on('shown.bs.modal',function() {
			loadFormData('MeasureForm','<c:url value="/exception/queryExcedbBymatchid.do?matchid="/>'+ $('#MeasureForm input[name="matchid"]').val(), function(data) {});
		}); */
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