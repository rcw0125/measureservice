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
	<link href="<c:url value='/css/bootstrap-duallistbox.min.css'/>" rel="stylesheet">
	<link href="<c:url value='/plugins/datatable/extensions/reorder-rows/bootstrap-table-reorder-rows.css'/>" rel="stylesheet">
	<script type="text/javascript" src="<c:url value='/plugins/jquery.bootstrap-duallistbox.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/plugins/datatable/extensions/reorder-rows/jquery.tablednd.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/plugins/datatable/extensions/reorder-rows/bootstrap-table-reorder-rows.min.js'/>"></script>
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
					<%-- <div class="col-sm-3">
						<div class='form-group input-group input-group-sm date'>
							<span class="input-group-addon">开始时间</span> 
							<input type='text' class="form-control" placeholder="开始时间" id="begintime" name="begintime" value="<%=begintime%>" />
						</div>
					</div> --%>
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">物&nbsp;&nbsp;流&nbsp;&nbsp;号 </span>
							<input type="text" class="form-control" placeholder="物流号" name="matchid" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">车　　号</span>
							<input type="text" class="form-control" placeholder="车号" id="carno" name="carno" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class=" col-sm-3 " >
					    <div class="form-group input-group  btn-group-sm">
						    <t:button text="查询" modulecode="ZhiKaGuanLi" id="query" btnclass="btn btn-info" iconclass="glyphicon glyphicon-search" onclick="queryinfo()"/>&nbsp;
						</div>
					</div>
					<%-- <div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">货　　名</span> 
							<input type="text" class="form-control" placeholder="货名" id="materialname" name="materialname" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">作&nbsp;&nbsp;业&nbsp;&nbsp;点 </span> 
							<input type="text" class="form-control" placeholder="作业点" name="unitname"aria-describedby="sizing-addon3" value="${unitname}">
						</div>
					</div> --%>
				</div>
				<%-- <div class="row" >
					<div class="col-sm-3">
						<div class='form-group input-group input-group-sm date'>
							<span class="input-group-addon">结束时间</span> 
							<input type='text' class="form-control" placeholder="结束时间" id="endtime" name="endtime" value="<%=endtime%>" />
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">计&nbsp;&nbsp;划&nbsp;&nbsp;号 </span> 
							<input type="text" class="form-control" placeholder="计划号" id="planid" name="planid" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">供　　货</span>
							<input type="text" class="form-control" placeholder="供货" id="sourcename" name="sourcename" aria-describedby="sizing-addon3">
						</div>
					</div>				
				</div>
				<div class="row">
					<div class="col-sm-3">
						  <t:combobox id="operatypes" name="operatype"  url="/bcommon/queryOperatype.do?operatype=${operatype}" label="业务类型" require="false" alloptions="true" allowclear="true"/>	
					</div>
					
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">车　　号</span>
							<input type="text" class="form-control" placeholder="车号" id="carno" name="carno" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">收　　货</span>
							<input type="text" class="form-control" placeholder="收货" id="targetname" name="targetname" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class=" col-sm-3 " >
					    <div class="form-group input-group  btn-group-sm">
						    <t:button text="查询" modulecode="ZhiKaGuanLi" id="query" btnclass="btn btn-info" iconclass="glyphicon glyphicon-search" onclick="queryinfo()"/>&nbsp;
						    <t:button text="制卡" modulecode="ZhiKaGuanLi" id="MakecardBtn" btnclass="btn btn-success" iconclass="glyphicon glyphicon-plus" />
						</div>
					</div>
				</div> --%>
			</form>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<table id="MakecardInfoGrid" 
			          data-toggle="table"  
						data-row-style=rowStyle" 
						data-query-params="queryParams"
						data-pagination="true" 
						data-page-size="10"
						data-page-list="[10,40,ALL]" 
						data-mobile-responsive="true">
				<thead>
					<tr>
					    <th data-width="70px" data-align="center" data-valign="middle"
							data-formatter="operateFormatter" data-events="operateEvents">操作</th>
						<th data-field="matchid" data-halign="center"
							data-searchable="true" sortable>物流号</th>
						<th data-field="operaname" class="text-nowrap" data-halign="center"
							data-searchable="true" sortable>业务类型</th>
						<%-- <th data-field="documentname" class="text-nowrap" data-halign="center"
							data-searchable="true" sortable>业务名称</th> --%>
						<th data-field="operatype" data-visible="false" ></th>
						<th data-field="planid" data-halign="center"
							data-searchable="true"  class="text-nowrap"sortable>计划号</th>
						<th data-field="carno" data-halign="center" data-searchable="true"
							class="text-nowrap" >车号</th>
						<th data-field="materialcode" data-halign="center"
							data-searchable="true" class="text-nowrap">货名编码</th>	
						<th data-field="materialname" data-halign="center"
							data-searchable="true" class="text-nowrap">货名</th>
						<th data-field="sourcename" data-halign="center"
							data-searchable="true" class="text-nowrap">供货</th>
						<th data-field="targetname" data-halign="center"
							data-searchable="true" class="text-nowrap">收货</th>						
						<th data-field="documentcode" data-halign="center"  
							class="text-nowrap" data-visible="false">单据编号</th>
						<th data-field="usermemo" data-halign="center" data-searchable="true"
							class="text-nowrap">备注</th>
						<th data-field="routeid" data-halign="center" data-visible="false" >路线</th>	
						<th data-field="reason" data-halign="center" data-visible="false" >路线名称</th>
					</tr>
				</thead>
			</table>

		</div>
	</div>
	<div class="modal fade" id="WorklineWindow" tabindex="-1" role="dialog" aria-labelledby="WorklineWindowLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">作业点调整</h4>
				</div>
				<div class="modal-body">
					<form id="worklineitemForm" onsubmit="return false;" style="padding-top:0px;">
<!-- 					<input type="hidden" id="id" name="id" value="-1"/>
						<input type="hidden" id="sn" name="sn" value="1"/>
						<input type="hidden" id="fromid" name="fromid" value="0"/> -->
						<input type="hidden" id="fid" name="fid" value=""/>
						<input type="hidden" id="fids" name="fids" value=""/>
						<input type="hidden" id="worklinecode_item" name="worklinecode" value=""/>
						<div class="row">
							<div class="col-sm-12">
								<div class="form-inline btn-group-sm" style="padding-bottom:10px;">
									<button id="saveworklineitemBtn" type="button" class="btn btn-warning" data-toggle="modal">
										<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>&nbsp;保存
									</button>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<t:textbox id="worklinename_item" name="worklinename" label="路线名称" readonly="true"/>
							</div>
							 <%-- <div class="col-sm-3">
								<t:textbox id="queryword_item" name="queryword" label="拼&nbsp;&nbsp;音&nbsp;&nbsp;头" readonly="true"/>
							</div>
							<div class="col-sm-3">
								<t:textbox id="usermemo_item" name="usermemo" label="备　　注" readonly="true"/>
							</div> --%>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<table id="worklineitemGrid" data-toggle="table"
									data-pagination="false" data-row-style="rowStyle"
									data-click-to-select="true" data-single-select="true"
									data-use-row-attr-func="true" data-reorderable-rows="true">
									<thead>
										<tr>
											<th data-halign="center" data-radio="true"></th>
											<th data-field="id" data-halign="center" data-visible="true"></th>
											<th data-field="sn" data-halign="center">序号</th>
											<th data-field="nodename" data-halign="center"<%-- data-formatter="nodecodeFormatter" --%>>节点</th>
											<%-- 	<th data-field="nodecode" data-halign="center" data-formatter="pointtimeFormatter" data-class="dtformat">时间（分钟）</th>
											<th data-width="65px" data-align="center" data-valign="middle" data-formatter="operateFormatter2" data-events="operateEvents2">操作</th>
										 --%>
										</tr>
									</thead>
								</table>
							</div>
							<div class="col-sm-6">
								<div class="row">
									<div class="col-sm-6">
										<t:combobox id="nodecode" label="节点名称" url="/bcommon/queryLinkinfo.do?operatype=1"/>
										<input type="hidden" id="nodename" name="nodename" value=""/>
									</div>
								 </div>
								<div class="row"> 
									<div class="col-sm-12" >
										<select id="workpointcode" name="workpointcode" multiple="multiple" size="10"></select>
										 <script>
											var workpointselect = $('#workpointcode').bootstrapDualListbox({filterPlaceHolder:'过滤作业点',infoText:false,showFilterInputs:false});
										</script>
									</div>
								</div>
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
			$('#MakecardInfoGrid').bootstrapTable('refresh', {
				url : "<c:url value='/adjustworkline/queryPage.do'/>"
			});
		}

		function operateFormatter(value, row, index) {
			return [
                    '<div class="pull-right" style="width:80px;">',
                   	'<t:ibutton text="修改" modulecode="ZhiKaGuanLi" id="makecardmodify" btnclass="edit" iconclass="glyphicon glyphicon-pencil"/>',
                    '</div>'
               	].join('');			
		}
		window.operateEvents = {
			'click .edit' : function(e, value, row) {
				openMakecard(row.routeid,row.reason,row.matchid);
			}
		};

		function openMakecard(routeid,reason,matchid) {
			$('#worklineitemForm input[id="worklinecode_item"]').val(routeid);
			$('#worklineitemForm input[id="worklinename_item"]').val(reason);
			$('#worklineitemForm input[id="fid"]').val(routeid);
			$('#worklineitemForm input[id="fids"]').val(matchid);
			$('#WorklineWindow').modal('show');
		}
		/*打开发卡页面*/
		$('#WorklineWindow').on('shown.bs.modal',function() {
			loadWorkpointSelect($('#nodecode').find("option:selected").text());
			$('#worklineitemGrid').bootstrapTable('refresh',{url:'<c:url value="/adjustworkline/queryNodename.do"/>?worklinecode=' + $('#worklinecode_item').val()});
		});

		function loadWorkpointSelect(nodename,selected){
			workpointselect.empty();
			 if('计毛' == nodename || '计皮' == nodename){
				nodename = '衡器';
			}else if('出库' == nodename || '入库' == nodename){
				nodename = '库房';
			}else if('进厂' == nodename || '出厂' == nodename || '制卡' == nodename){
				nodename = '门岗';
			}
			$.ajax({
	            type: "post",
	            url:'<c:url value="/workpoint/querybytype.do"/>',
	            dataType: "json",
	            data:{linktype:nodename},
	            success: function(data){
	            	var selectedOptions = new Array();
	            	if(undefined != selected){
	            		selectedOptions = selected.split(',');
	            	}
	            	var selectFlag = "";
	            	for(var i=0;i<data.length;i++){
	            		selectFlag = "";
	            		for(var j=0;j<selectedOptions.length;j++){
	            			if(data[i].workpointcode == selectedOptions[j]){
	            				selectFlag = "selected";
	            				break;
	            			}
	            		}
	            		workpointselect.append('<option value="'+data[i].workpointcode+'" '+selectFlag+'>'+data[i].workpointname+'</option>');
	            	}
	            	workpointselect.bootstrapDualListbox('refresh', true);
	            }
	        }); 
		}
		$('#saveworklineitemBtn').click(function(){
			saveFormData('worklineitemForm','<c:url value="/adjustworkline/insertWorkline.do"/>',function(data){
				//$('#worklineitemGrid').bootstrapTable('refresh',{url:'<c:url value="/adjustworkline/queryNodename.do"/>?worklinecode=' + $('#worklinecode_item').val()});
			   if (data.success == true) {
					successbox(data.msg);
					$('#WorklineWindow').modal('toggle');
					$('#MakecardInfoGrid').bootstrapTable('refresh');
				} else {
					errorbox(data.msg);
				}
			});
		});
		
		$('#worklineitemGrid').bootstrapTable({
		    onCheck:function(row,element){
		    	var matchid =$('#worklineitemForm input[id="fids"]').val();
		    	var routeid =$('#worklineitemForm input[id="worklinecode_item"]').val()
		    	loadFormData('worklineitemForm','<c:url value="/worklineitem/loadform.do"/>?id=' + row.id,function(data){
		    		loadWorkpointSelect($('#nodecode').find("option:selected").text(),data.workpointcode);
		    		$('#worklineitemForm input[id="fids"]').val(matchid);
		    		$('#worklineitemForm input[id="worklinecode_item"]').val(routeid);
		    		
		    	});
		    }
		});
	</script>
</body>
</t:html>