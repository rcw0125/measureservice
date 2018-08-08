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
</head>
<%
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		Date now = new Date();
		Calendar c = Calendar.getInstance();
		now = c.getTime();
		String begintime = dateFormat.format(now);//开始时间
		String endtime = dateFormat1.format(now);//结束时间

		/* String unitname = request.getParameter("unitname") == null ? ""
				: request.getParameter("unitname");
		String unitcode = request.getParameter("unitcode") == null ? ""
				: request.getParameter("unitcode"); */
%>
<body class="container-fluid" style="padding-top: 15px">
	<div class="row">
		<div class="col-sm-12">
			<form id="queryform">
			<input type="hidden" id="unitcodes"  value="${unitcode}">
				<div class="row" >
					<div class="col-sm-3">
						<div class='form-group input-group input-group-sm date'>
							<span class="input-group-addon">开始时间</span> 
							<input type='text' class="form-control" placeholder="开始时间" id="begintime" name="begintime" value="<%=begintime%>" />
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">到&nbsp;&nbsp;货&nbsp;&nbsp;单</span>
							<input type="text" class="form-control" placeholder="到货单" id="planid" name="planid" aria-describedby="sizing-addon3">
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
							<span class="input-group-addon" id="sizing-addon3">供　　货</span>
							<input type="text" class="form-control" placeholder="供货" id="sourcename" name="sourcename" aria-describedby="sizing-addon3">
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
							<span class="input-group-addon" id="sizing-addon3">物&nbsp;&nbsp;流&nbsp;&nbsp;号</span> 
							<input type="text" class="form-control" placeholder="物流号" id="matchid" 	name="matchid" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">货　　名</span>
							<input	type="text" class="form-control" placeholder="货名" id="materialname" name="materialname" aria-describedby="sizing-addon3">
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
							<span class="input-group-addon" id="sizing-addon3">取样点</span>
							<input type="text" class="form-control" placeholder="取样点" id="qmpostion" name="qmpostion" value="${unitname}" aria-describedby="sizing-addon3">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-3 btn-group-sm" >
						<button id="query" type="button" class="btn btn-info" onclick="queryinfo()">
							<span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;查询
						</button>
						<button id="QMBtn" type="button" class="btn btn-success">
							<span class="glyphicon glyphicon-th-large" aria-hidden="true"></span>&nbsp;取样
						</button>
					</div>
				</div>
			</form>
</div>
	</div>
	<div class="row" style="padding-top:5px">
		<div class="col-sm-12">
				<table id="QMGrid" data-toggle="table" data-row-style="rowStyle"
					data-query-params="queryParams" data-pagination="true"
					data-page-size="10" data-page-list="[10, 40,  ALL]"
					data-mobile-responsive="true">
					<thead>
						<tr>
							<th data-field="matchid" data-halign="center" data-editable="false"
								data-searchable="true" sortable class="text-center" nowrap="nowrap">物流号</th>
							<th data-field="planid" data-halign="center"
								data-searchable="true" sortable class="text-center" nowrap="nowrap">到货单</th>
							<th data-field="carno" data-halign="center"
								data-searchable="true" class="text-nowrap text-center" nowrap="nowrap">车号</th>
							<th data-field="materialname" data-halign="center"
								data-searchable="true" class="text-nowrap" nowrap="nowrap">货名</th>
							<th data-field="sourcename" data-halign="center"
								data-searchable="true" class="text-nowrap" nowrap="nowrap">供货</th>
							<th data-field="targetname" data-halign="center"
								data-searchable="true" class="text-nowrap" nowrap="nowrap">收货</th>
							<th data-field="qmpostion" data-halign="center"
								data-searchable="true" class="text-center" nowrap="nowrap">取样点</th>
							<th data-field="creator" data-halign="center"
								data-searchable="true" class="text-nowrap text-center" nowrap="nowrap">取样人</th>
							<th data-field="createdate" data-halign="center"
								data-searchable="true" class="text-nowrap text-center" nowrap="nowrap">取样时间</th>
							<th data-field="usermemo" data-halign="center"
								data-searchable="true" class="text-nowrap" nowrap="nowrap">备注</th>
							<th data-width="70px" data-align="center" data-valign="middle" class="text-center"
								data-formatter="operateFormatter" data-events="operateEvents">操作</th>
						</tr>
					</thead>
				</table>	
		</div>
	</div>
	<div class="modal fade" id="QualityWindow" tabindex="-1" role="dialog"	aria-labelledby="QualityWindowLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">取样--${unitname}</h4>
				</div>
				<div class="modal-body">
					<form id="QualityForm">
						<input type="hidden" name="id" value="-1" /> 
						<input type="hidden" name="matchid" id="matchids" value="" /> 
	                    <input name="unitname" id="unitname" type="hidden" class="form-control" aria-describedby="basic-addon1" value="${unitname}">
						<input  name="unitcode" id="unitcode" type="hidden" class="form-control" readonly="readonly" aria-describedby="basic-addon1" value="${unitcode}">
						<input type="hidden" name="stoplink" id="stoplink" value="">				
						<div class="row" >
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">卡　　号</span>
									<input type="text" name="cardid" id="cardids" class="form-control" aria-describedby="basic-addon1" readonly="readonly" required data-bv-notempty-message="请刷卡获取卡号" />
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">车　　号</span>
									<input  name="carno" type="text" class="form-control" aria-describedby="basic-addon1" readonly="readonly" required data-bv-notempty-message="请刷卡获取车号">
								</div>
							</div>
						</div>
						<div class="row" >
							<div class="col-sm-12">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">备　　注</span>
									<input name="usermemo" type="text" class="form-control" aria-describedby="basic-addon1">
								</div>
							</div>
						</div>
					</form>
					<div class="row">
						<div class="col-sm-12">
							<table id="QualityinfoGrid" data-toggle="table"　 data-mobile-responsive="true">
								<thead>
									<tr>
										<th data-field="state" data-radio="true" data-formatter="stateFormatter"></th>
										<th data-field="matchid" data-halign="center" nowrap="nowrap">物流号</th>
										<th data-field="planid" data-halign="center" nowrap="nowrap">到货单</th>
										<th data-field="carno" data-halign="center" nowrap="nowrap">车号</th>
										<th data-field="materialname" data-halign="center" nowrap="nowrap">货名</th>
										<th data-field="sourcename" data-halign="center" nowrap="nowrap">供货</th>
										<th data-field="targetname" data-halign="center" nowrap="nowrap">收货</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				<div class="modal-footer btn-group-sm">
					<button type="button" class="btn btn-success" id="QaddBtn" onclick="insert()">确认</button>
					<!-- <button type="button" class="btn btn-warning" id="QualityfinishBtn" onclick="insertforce()">终止</button> -->
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
	var cardType = 'MC9500';
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
            	 if(data.more.ictype!=null&&data.more.ictype!=''){
            		 cardType= data.more.ictype;
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
		});
		function queryParams(params) {
			return jQuery.extend({}, params, $('#queryform').serializeJson());
		}

		function queryinfo() {
			$('#QMGrid').bootstrapTable('refresh', {
				url : "<c:url value='/quality/queryPage.do'/>"
			});
		}
		function operateFormatter(value, row, index) {
			return [ '<div class="pull-center text-center" >',
					'<a class="remove" href="javascript:void(0)" title="【】">',
					'<i class="glyphicon glyphicon-trash"></i>', '</a>',
					'</div>' ].join('');
		}
		window.operateEvents = {
			'click .remove' : function(e, value, row) {
				cancelMeasureweigh(row.matchid);
			}
		};
		$('#QMBtn').on('click', function() {
			openQualityWindow(-1);
		});
		function openQualityWindow(id_vv) {
			currentId = id_vv;
			$($('#QualityWindow form :input[name="id"]')).val(id_vv);
			$('#QualityWindow').modal('show');
		}
		
		
		/*打开发卡页面*/
		//var cardType = 'MC9500';
		function onFindICard(data){//打卡读卡器进行哪些操作
			$('#QualityForm input[name="cardid"]').val(data.cardid);
			$.ajax({
	            type: "post",
	            url: '<c:url value="/bcommon/judgCarno.do"/>',
	            dataType: "json",
	            data: {cardid:data.cardid}, 
	            success: function(data){
	            	  if(data.success){//卡和车辆状态正常，根据车号查询业务信息
	            		 $($('#QualityWindow form :input[name="carno"]')).val(data.rows[0].carno);  
	            		 validForm('QualityForm'); 
	            		 $('#QualityinfoGrid').bootstrapTable('refresh', {url : "<c:url value='/quality/queryInfoBycarno.do?carno='/>"+data.rows[0].carno}); 
	            	 }else{//如果车辆或者卡有问题，系统提示
	            		 errorbox(data.msg); 
	            		 $('#QaddBtn').prop('disabled', true); 
	            		 $('#QualityfinishBtn').prop('disabled', true); 
	            	 } 
	            }
            }); 
			 
		}
		
		function onDropICard(data){//关闭读卡器，进行哪些操作
			$('#QualityForm input[name="cardid"]').val('');
			$('#QualityForm input[name="carno"]').val('');			
			$('#QualityinfoGrid').bootstrapTable('removeAll');
			$('#QaddBtn').prop('disabled', false); 
   		    $('#QualityfinishBtn').prop('disabled', false); 
			validForm('QualityForm');
		}
		
		$('#QualityWindow').on('hide.bs.modal', function() {	 //页面隐藏时，关闭读卡器		
			 CloseMediaReader(cardType,function(data){//关闭读卡器				
			}); 
		});
		/*打开取样页面*/
		$('#QualityWindow').on('shown.bs.modal',function() {
			var unitcode = $("#unitcode").val();
			var unitname = $("#unitname").val();
		 	loadFormData('QualityForm','<c:url value="/quality/queryQuality.do?unitcode="/>'+unitcode+'&unitname='+unitname,function(data){
				OpenMediaReader(cardType,onFindICard,onDropICard,function(data){  
				}); 
			}); 
		});
		function stateFormatter(value, row, index) {
			 if (index === 0) {
				return {
					checked : true
				};
			} 
			return value;
		}

		/**
	    *添加取样信息
		*/
		function insert(){
			validForm('QualityForm');
			$('#QaddBtn').prop('disabled', true); 
   		    $('#QualityfinishBtn').prop('disabled', true); 
			var selectedRows = $('#QualityinfoGrid').bootstrapTable('getSelections');
			var matchid = selectedRows[0].matchid
			$("#matchids").val(matchid);			
			 saveFormData('QualityForm','<c:url value="/quality/beforeinsertquality.do"/>',function(data){
				 if(data.mfunc==0){
					 if(data.success){
		             		successbox(data.msg);
		             		$('#QualityinfoGrid').bootstrapTable('removeAll');
		             		$('#QualityForm input[name="cardid"]').val('');
		        			$('#QualityForm input[name="carno"]').val('');
			                $('#QMGrid').bootstrapTable('refresh');
		             	}else{
		             		errorbox(data.msg);
		             	} 
				 }else{
					 if(data.mfunc==3){ //为3时判断里面有一个禁止添加取样信息
						 errorbox(data.msg);
					 }else if(data.mfunc==2){//为2时用户选择是否添加取样信息
						 dialogbox("请确认", data.msg,function(data){
							if(data){
								saveFormData('QualityForm','<c:url value="/quality/insertquality.do"/>',function(data){
									if(data.success){
					             		successbox(data.msg);
						                $('#QualityForm input[name="cardid"]').val('');
						    			$('#QualityForm input[name="carno"]').val('');
						                $('#QMGrid').bootstrapTable('refresh');
						                $('#QualityinfoGrid').bootstrapTable('removeAll');
					             	}else{
					             		errorbox(data.msg);
					             	} 
								});	
							}
						});
					 }
				 }				
			});			
		}
		  /**
		    *终止业务信息，车号添加到黑名单
			*/
			function insertforce(){
			 $('#QaddBtn').prop('disabled', true); 
       		 $('#QualityfinishBtn').prop('disabled', true); 
			  $("#stoplink").val("SP");
			  var selectedRows = $('#QualityinfoGrid').bootstrapTable('getSelections');
			   if(selectedRows.length>0){
				dialogbox("请确认", "确认终止此业务？",function(data){
				if(data){
						var matchid = selectedRows[0].matchid
						$("#matchids").val(matchid);
						saveFormData('QualityForm','<c:url value="/bcommon/forcestop.do"/>',function(data){
								if (data.success) {
									successbox(data.msg);
					                $('#QMGrid').bootstrapTable('refresh');
					                $('#QualityinfoGrid').bootstrapTable('removeAll');
								} else {
									errorbox(data.msg);
								} 
							});
						}
					});
				}else{
					errorbox("请选择需要终止的业务");
				}
				 
			}
		/**
		*作废取样信息
		*/
		function cancelMeasureweigh(matchid){
			dialogbox("请确认", "确认删除此项目？",function(data){
				if(data){
					$.ajax({
			            type: "post",
			            url: '<c:url value="/quality/cancelquality.do"/>',
			            dataType: "json",
			            data: {matchid:matchid},
			            success: function(data){
			            	 if(data.success){
			            		 successbox(data.msg);
			            		 $('#QMGrid').bootstrapTable('refresh');
			            	 }else{
			            		 errorbox( data.msg);
			            	 }
			            }
		            });
			     }
			});
		}
		$('#QMGrid').bootstrapTable({
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
	</script>
</body>
</t:html>