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
			<form id="queryhcform">
			   <input type="hidden" class="form-control" placeholder="子表id" id="saleitemid" name="saleitemid" aria-describedby="sizing-addon3">
			   <input type="hidden" class="form-control" placeholder="单据号" id="planid" name="planid" aria-describedby="sizing-addon3"> 
			   <input type="hidden" class="form-control" placeholder="物料编码" id="materialcode" name="materialcode" >
			   <input type="hidden" class="form-control" placeholder="供货编码" id="sourcecode" name="sourcecode" >
			   <input type="hidden" class="form-control" placeholder="物料" id="reserve1" name="reserve1" >
			   <input type="hidden" class="form-control" placeholder="供货" id="reserve2" name="reserve2" >
			    <div class="row"> 
					<div class="col-sm-3">
						<div class='form-group input-group input-group-sm date'>
							<span class="input-group-addon">开始时间</span>
							 <input type='text' class="form-control" placeholder="开始时间" id="begintime" name="begintime" value="<%=begintime%>" />
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
							<span class="input-group-addon" id="sizing-addon3">物&nbsp;&nbsp;流&nbsp;&nbsp;号</span>
							 <input type="text" class="form-control" placeholder="物流号" name="matchid" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">发　　站</span>
							 <input type="text" class="form-control" placeholder="发站" name="sourceplace" aria-describedby="sizing-addon3">
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
					<!-- <div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">计&nbsp;&nbsp;划&nbsp;&nbsp;号</span>
							 <input type="text" class="form-control" placeholder="计划号"
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
								<span class="input-group-addon" id="sizing-addon3">是否勾兑</span>
							    <select id="basket" name="basket" class="form-control select2">
									<option value="">全部</option>
									<option value="0" selected="selected">未勾兑</option>
									<option value="1">已勾兑</option>
								</select> 
							</div>
						</div>
					<div class="col-sm-3">
						<div class="form-group input-group  btn-group-sm">
							<t:button text="查询" modulecode="trainblend" id="queryhc" btnclass="btn btn-info" iconclass="glyphicon glyphicon-search" onclick="queryhcinfo()" />
						    <t:button text="勾兑" modulecode="trainblend" id="blend" btnclass="btn btn-success" iconclass="glyphicon glyphicon-plus" onclick="trainblend()" />
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
						data-query-params="queryhcParams"
						data-pagination="true" 
						data-page-size="10"
						data-page-list="[10,40,ALL]" 
						data-mobile-responsive="true">
				<thead>
					<tr>
					   <th data-field="state" data-checkbox="true" ></th>
						<%-- <th data-field="state" data-radio="true"></th> --%>
						<th data-field="matchid" data-halign="center"
							data-searchable="true" sortable>物流号</th>
						<th data-field="basket" data-halign="center" data-visible="false">勾兑标记</th>
						<%-- <th data-field="operatype" class="text-nowrap"
							data-halign="center" data-searchable="true" sortable>业务类型</th>							 
						<th data-field="sysmemo" data-halign="center" data-searchable="true"
							class="text-nowrap">是否勾兑</th>--%>
						<th data-field="planid" data-halign="center"
							data-searchable="true" class="text-nowrap" sortable>单据号</th>	
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
						<th data-field="sourceplace" data-halign="center" data-searchable="true"
							class="text-nowrap" >发站</th>	
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
						<%--<th data-field="usermemo" data-halign="center"
							data-searchable="true" class="text-nowrap">备注</th> --%>	
						 <th data-width="70px" data-align="center" data-valign="middle"
							data-formatter="operateFormatter" data-events="operateEvents">操作</th>
						</tr>
				</thead>
			</table>
		</div>
	</div>
	<!-- ---------------------------------接口数据---------------------------------- -->
	<div class="row">
		<div class="col-sm-12">
			<form id="queryform">			
				<input type="hidden" id="types"  name="types" value="0">
				<div class="row">
					<div class="col-sm-3">
						<div class='form-group input-group input-group-sm date'>
							<span class="input-group-addon">开始时间</span> 
							<input type='text' class="form-control" placeholder="开始时间" id="interbegintime" name="begintime" value="<%=begintime%>" />
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
							<span class="input-group-addon" id="sizing-addon3">发　　站</span>
							<input type="text" class="form-control" placeholder="发站" id="sourceplace" name="sourceplace" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">船　　名</span>
							<input type="text" class="form-control" placeholder="船名" id="ship" name="ship" aria-describedby="sizing-addon3">
						</div>
					</div>
				</div>
				<div class="row" >
					<div class="col-sm-3">
						<div class='form-group input-group input-group-sm date'>
							<span class="input-group-addon">结束时间</span> 
							<input type='text' class="form-control" placeholder="结束时间" id="interendtime" name="endtime" value="<%=endtime%>" />
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">单&nbsp;&nbsp;据&nbsp;&nbsp;号 </span> 
							<input type="text" class="form-control" placeholder="单据号" id="planid" name="planid" aria-describedby="sizing-addon3">
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
								<span class="input-group-addon" id="sizing-addon3">单据状态</span>
							    <select id="validflag" name="validflag" class="form-control select2">
									<option value="">全部</option>
									<option value="1">正常</option>
									<option value="8">完成</option>
								</select> 
							</div>
						</div>
					<!-- <div class="col-sm-2">
							<div class="form-group input-group input-group-sm">
								<span class="input-group-addon" id="sizing-addon3">单据类型</span>
							    <select id="sysmemo" name="sysmemo" class="form-control select2">
									<option value="">全部</option>
									<option value="1">到货单</option>
									<option value="2">发运单</option>
								</select> 
							</div>
						</div> -->					
					<div class=" col-sm-2" >
					    <div class="form-group input-group  btn-group-sm">
						    <t:button text="查询" modulecode="trainblend" id="query" btnclass="btn btn-info" iconclass="glyphicon glyphicon-search" onclick="queryinfo()"/>
						</div>
					</div>
							
				</div>
			</form>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<table id="InterfaceInfoGrid" 
			          data-toggle="table"  
						data-row-style=rowStyle" 
						data-query-params="queryParams"
						data-pagination="true" 
						data-page-size="3"
						data-page-list="[3,ALL]" 
						data-mobile-responsive="true">
				<thead>
					<tr>
					    <th data-field="state" data-radio="true" ></th>
						<th data-field="planid" data-halign="center"
							data-searchable="true"  class="text-nowrap" >单据号</th>
						<th data-field="saleitemid" data-visible="false"
							>行号</th>
						<%-- <th data-field="status" data-halign="center" data-searchable="true"
							class="text-nowrap" >状态</th> --%>
						<th data-field="materialcode" data-halign="center"
							data-searchable="true" class="text-nowrap">货名编码</th>	
						<th data-field="materialname" data-halign="center"
							data-searchable="true" class="text-nowrap">货名</th>
						<th data-field="sourcename" data-halign="center"
							data-searchable="true" class="text-nowrap">供货</th>
						<th data-field="sourcecode" data-halign="center"
							data-searchable="true" class="text-nowrap">供货</th>	
						<th data-field="targetname" data-halign="center"
							data-searchable="true" class="text-nowrap">收货</th>	
						<th data-field="sourceplace" data-halign="center" data-searchable="true"
							class="text-nowrap" >发站</th>	
						<th data-field="carno" data-halign="center" data-searchable="true"
							class="text-nowrap" >船名</th>						
						<th data-field="planmaterialcount" data-halign="center"
							data-searchable="true" class="text-nowrap">计划车数</th>
						<th data-field="snumber" data-halign="center"
							data-searchable="true" class="text-nowrap">剩余车数</th>
						<th data-field="measureunit" data-halign="center"
							data-searchable="true" class="text-nowrap">计量单位</th>
						<th data-field="arrivetime" data-halign="center"
							data-searchable="true" class="text-nowrap">到货时间</th>
						<%-- <th data-field="createdate" data-halign="center"
							data-searchable="true" class="text-nowrap">下载时间</th> --%>
						<th data-field="sysmemo" data-halign="center"
							data-searchable="true" class="text-nowrap">单据类型</th>	
						<th data-field="usermemo" data-halign="center" data-searchable="true"
							class="text-nowrap">备注</th>
						<th data-width="70px" data-align="center" data-valign="middle"
							data-formatter="handFormatter" data-events="operateEvent">操作</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<script type="text/javascript">
	jQuery(document).ready(function($) {				
		queryinfo();
		queryhcinfo();
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
		
		$('#interbegintime').datetimepicker({
			format : 'YYYY-MM-DD HH:mm:ss',
			locale : 'zh-cn'
		});
		$('#interendtime').datetimepicker({
			format : 'YYYY-MM-DD HH:mm:ss',
			locale : 'zh-cn'
		});
	
		$("#interbegintime").on("dp.change", function(e) {
			$('#interendtime').data("DateTimePicker").minDate(e.date);
		});
		$("#interendtime").on("dp.change", function(e) {
			$('#interbegintime').data("DateTimePicker").maxDate(e.date);
		});
		
		
	});
	
	function queryParams(params) {
		return jQuery.extend({}, params, $('#queryform').serializeJson());
	}
	
	function queryinfo() {
		$('#InterfaceInfoGrid').bootstrapTable('refresh', {
			url : "<c:url value='/trainblend/queryBlendInterList.do'/>"
		});
	}
	
	function queryhcParams(params) {
		return jQuery.extend({}, params, $('#queryhcform').serializeJson());
	}
	
	function queryhcinfo() {
		$('#MeasureInfoGrid').bootstrapTable('refresh', {
			url : "<c:url value='/trainblend/queryTrainBlendList.do'/>"
		});
	}
	function operateFormatter(value, row, index) {
		return [
                '<div class="pull-left">',
               	'<t:ibutton text="移除" modulecode="trainblend" id="trainblendremove" btnclass="remove" iconclass="glyphicon glyphicon-trash"/>',
               	'</div>'
             	].join('');			
	}
	window.operateEvents = {
		'click .remove' : function(e, value, row) {
			cancelTrainblend(row.matchid);
		}
	};
	function handFormatter(value, row, index) {
		return [
                '<div class="pull-left">',
               	'<t:ibutton text="完成" modulecode="trainblend" id="trainblendcomplete" btnclass="remove" iconclass="fa fa-hand-pointer-o"/>',
               	'</div>'
             	].join('');			
	}
	window.operateEvent = {
		'click .remove' : function(e, value, row) {
			updatePlanStatus(row.planid);
		}
	};
		
	/**
	作废勾兑信息
	*/
	function cancelTrainblend(matchid) {
		dialogbox("请确认", "确认删除勾兑信息？",function(data){
			if(data){
				$.ajax({
						type : "post",
						url : '<c:url value="/trainblend/cancelTrainBlend.do"/>',
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
	
	/**
	火运计划完成
	*/
	function updatePlanStatus(planid) {
		dialogbox("请确认", "确认完成计划？",function(data){
			if(data){
				$.ajax({
						type : "post",
						url : '<c:url value="/trainblend/updatePlanStatus.do"/>',
						dataType : "json",
						data : {planid : planid},
						success : function(data) {
						if (data.success) {
							successbox(data.msg);					
							$('#InterfaceInfoGrid').bootstrapTable('refresh');
						} else {
							errorbox(data.msg);
						}
					}
				});
			}
	   });
	}
   function trainblend(){
	   var selectedRows = $('#InterfaceInfoGrid').bootstrapTable('getSelections');
	   var gridData = $('#MeasureInfoGrid').bootstrapTable('getSelections');
	   var glength=selectedRows.length;
	   var mlength=gridData.length;
	   if(mlength>0 && glength>0){
		   /* var saleitemid = selectedRows[0].saleitemid; */
		   $("#saleitemid").val(selectedRows[0].saleitemid);
		   $("#planid").val(selectedRows[0].planid);
		   $("#materialcode").val(selectedRows[0].materialcode)
		   $("#sourcecode").val(selectedRows[0].sourcecode)
		   $("#reserve1").val(selectedRows[0].materialname)
		   $("#reserve2").val(selectedRows[0].sourcename)
		   var flag=0;
		   for(var i=0;i<mlength;i++){
			   if(gridData[i].basket!='' && gridData[i].basket!=null){
				  flag++; 
			   }
		   }
		   if(flag==0){//如果没有已经勾兑的数据信息，直接进行勾兑操作
			   saveFormDataWithParams('queryhcform','<c:url value="/trainblend/updateTrainBlend.do"/>',gridData, function(data) {
					if (data.success) {
						successbox(data.msg);
						$('#MeasureInfoGrid').bootstrapTable('refresh');
						$('#InterfaceInfoGrid').bootstrapTable('refresh');
					} else {
						errorbox(data.msg);
					}
				});	
		   }else if(flag>0){//如果大于0，询问是否重新勾兑信息
			   dialogbox("请确认","数据已经勾兑，是否重新勾兑？",function(data) {//确认重复勾兑，提交后台勾兑信息
				   saveFormDataWithParams('queryhcform','<c:url value="/trainblend/updateTrainBlend.do"/>',gridData, function(data) {
						if (data.success) {
							successbox(data.msg);
							$('#MeasureInfoGrid').bootstrapTable('refresh');
							$('#InterfaceInfoGrid').bootstrapTable('refresh');
						} else {
							errorbox(data.msg);
						}
					});	
			   });
		   }
		  
	   }else if(mlength==0 && glength>0){
		   warningbox("请选择需要勾兑的重量信息");
	   }else if(mlength>0 && glength==0){
		   warningbox("请选择单据信息");
	   }
   }
	</script>
</body>
</t:html>