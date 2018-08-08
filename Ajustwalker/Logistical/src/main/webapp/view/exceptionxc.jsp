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
							<span class="input-group-addon" id="sizing-addon3">车　　号</span> 
							<input type="text" class="form-control" placeholder="车号" id="carno" name="carno" aria-describedby="sizing-addon3">
						</div>
					</div>
						
					<div class="col-sm-2">
						  <t:combobox id="operatypes" name="operatype"  url="/bcommon/queryOperatype.do?operatype=${operatype}" label="业务类型" require="false" alloptions="true" allowclear="true"/>	
					</div>
					<div class="col-sm-2">
							<div class="form-group input-group input-group-sm">
								<span class="input-group-addon" id="sizing-addon3">状　　态</span>
								<select id="validflag" name="validflag" class="form-control select2">
									<option value="">全部</option>
									<option value="1">正常</option>
									<option value="8">完成</option>
								</select>
							</div>
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
							<span class="input-group-addon" id="sizing-addon3">收　　货</span>
							<input type="text" class="form-control" placeholder="收货" id="targetname" name="targetname" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group input-group  btn-group-sm">
							<t:button text="查询" modulecode="JinChuChangYiChang" id="query" btnclass="btn btn-info" iconclass="glyphicon glyphicon-search" onclick="queryinfo()"/>
						</div>
					</div>  
				</div>
			</form>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<table id="XCInfoGrid" 
			          data-toggle="table"  
						data-row-style="rowStyle" 
						data-query-params="queryParams"
						data-pagination="true" 
						data-page-size="10"
						data-page-list="[10,40]" 
						data-mobile-responsive="true">
				<thead>
					<tr>
						<th data-field="matchid" data-halign="center"
							data-searchable="true" sortable>物流号</th>
						<th data-field="planid" data-halign="center"
							data-searchable="true"  class="text-nowrap" sortable>计划号</th>
						<th data-field="carno" data-halign="center" data-searchable="true"
							class="text-nowrap" >车号</th>
						<th data-field="icid" data-halign="center" data-searchable="true"
							class="text-nowrap" data-visible="false" >卡号</th>	
						<th data-field="materialname" data-halign="center"
							data-searchable="true" class="text-nowrap">货名</th>
						<th data-field="targetname" data-halign="center"
							data-searchable="true" class="text-nowrap">收货</th>						
						<th data-field="entertime" data-halign="center"
							data-searchable="true" class="text-nowrap">进厂时间</th>
						<th data-field="entergatename" data-halign="center"
							data-searchable="true" class="text-nowrap">进厂大门</th>
						<th data-field="leavetime" data-halign="center"  
							data-searchable="true" class="text-nowrap">出厂时间</th>
						<th data-field="leavegatename" data-halign="center" data-searchable="true"
							class="text-nowrap">出厂大门</th>
						<th data-field="validflag" data-visible="false">皮重</th>									
						<th data-width="70px" data-align="center" data-valign="middle"
							data-formatter="operateFormatter" data-events="operateEvents">操作</th>
					</tr>
				</thead>
			</table>
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
	});
	function queryParams(params) {
		return jQuery.extend({}, params, $('#queryform').serializeJson());
	}

	function queryinfo() {
		$('#XCInfoGrid').bootstrapTable('refresh', {url : "<c:url value='/exception/queryXCList.do'/>"});
	}
	function operateFormatter(value, row, index) {
		return [
                '<div class="pull-right">',
               '<t:ibutton text="移除" modulecode="JinChuChangYiChang" id="exceptionremove" btnclass="remove" iconclass="glyphicon glyphicon-trash"/>',
               	'</div>'
           	].join('');		
		
	}
	window.operateEvents = {
		'click .remove' : function(e, value, row) {
			cancelExceptionXC(row.matchid,row.carno,row.icid,row.validflag);
		}
	};
	
	/**
	 *作废线材信息
	 */
	function cancelExceptionXC(matchid,carno,icid,validflag) {
		if(validflag==8){
			errorbox("车辆已经出厂不允许作废");
		}else{
			dialogbox("请确认", "确认删除此项目？",function(data){
				 if(data){
					$.ajax({
							type : "post",
							url : '<c:url value="/exception/cancelExceptionXC.do"/>',
							dataType : "json",
							data : {matchid:matchid,carno:carno,icid:icid},
							success : function(data) {
							if (data.success) {
								successbox(data.msg);					
								$('#XCInfoGrid').bootstrapTable('refresh', {url : "<c:url value='/exception/queryXCList.do'/>"});
							} else {
								errorbox(data.msg);
							}
						}
					});
				} 
		   });
		}
		
	}

	</script>
</body>
</t:html>