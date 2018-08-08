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
	/* 
	String unitname = request.getParameter("unitname") == null ? "": request.getParameter("unitname");
	String unitcode = request.getParameter("unitcode") == null ? "": request.getParameter("unitcode"); */
%>
<body class="container-fluid" style="padding-top: 15px">
	<div class="row">
		<div class="col-sm-12">
			<form id="queryform">
			<input type="hidden" id="unitcodes"  value="${unitcode}">
				<div class="row">
					<div class="col-sm-3">
						<div class='form-group input-group input-group-sm date'>
							<span class="input-group-addon">开始时间</span> 
							<input type='text' class="form-control" placeholder="开始时间" id="begintime" name="begintime" value="<%=begintime%>" />
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">发&nbsp;&nbsp;运&nbsp;&nbsp;单</span>
							<input type="text" class="form-control" placeholder="发运单" id="planid" name="planid" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">车　　号</span>
							<input type="text" class="form-control" placeholder="车号" id="carno" name="carno" aria-describedby="sizing-addon3">
						</div>
					</div>

					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">库　　房</span>
							<!-- <input type="text" class="form-control" placeholder="供货" id="sourcename" name="sourcename" aria-describedby="sizing-addon3"> -->
						    <input type="text" class="form-control" placeholder="库房" id="storename" name="storename" aria-describedby="sizing-addon3" value="${unitname}">
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
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">物&nbsp;&nbsp;流&nbsp;&nbsp;号</span>
							<input type="text" class="form-control" placeholder="物流号" id="matchid" name="matchid" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">货　　名</span>
							<input	type="text" class="form-control" placeholder="货名" id="materialname" name="materialname" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">收　　货</span> 
							<input type="text" class="form-control" placeholder="收货" id="targetname" name="targetname" aria-describedby="sizing-addon3">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12 btn-group-sm">
						<t:button text="查询" modulecode="ChuKuGuanLi" id="query" btnclass="btn btn-info" iconclass="glyphicon glyphicon-search"  onclick="queryinfo()" />
						<t:button text="出库" modulecode="ChuKuGuanLi" id="StoreoutBtn" btnclass="btn btn-success" iconclass="glyphicon glyphicon-th-large" />
					    <t:button text="异常出库" modulecode="ChuKuGuanLi" id="StoreOutEXBtn" btnclass="btn btn-warning" iconclass="glyphicon glyphicon-th" />
					    <t:button text="EXCEL" modulecode="ChuKuGuanLi" id="StoreOutExcel" btnclass="btn btn-warning" iconclass="fa fa-file-excel-o" onclick="exportExcel()" /> 
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="row" style="padding-top:5px">
		<div class="col-sm-12">
			<table id="StoreoutGrid" data-toggle="table"
				data-row-style="rowStyle" data-query-params="queryParams"
				data-pagination="true" data-page-size="10"
				data-show-footer="true"
				data-page-list="[10, 40]" 　 data-mobile-responsive="true">
				<thead>
					<tr>
						<th data-field="matchid" data-halign="center"
							data-searchable="true" sortable class="text-center" nowrap="nowrap">物流号</th>
						<th data-field="planid" data-halign="center"
							data-searchable="true" sortable class="text-center" nowrap="nowrap">发运单</th>
						<th data-field="carno" data-halign="center" data-searchable="true"
							class="text-nowrap text-center" nowrap="nowrap">车号</th>
						<th data-field="materialname" data-halign="center"
							data-searchable="true" class="text-nowrap" nowrap="nowrap">货名</th>
						<th data-field="sourcename" data-halign="center"
							data-searchable="true" class="text-nowrap" nowrap="nowrap">供货</th>
						<th data-field="targetname" data-halign="center"
							data-searchable="true" class="text-nowrap" nowrap="nowrap">收货</th>
						<th data-field="storename" data-halign="center"
							data-searchable="true" class="text-center" nowrap="nowrap">发货库房</th>
						 <th data-field="counts" data-halign="center"
								data-searchable="true" class="text-center">出库数量</th>
							<%-- <th data-field="weight" data-halign="center"
								data-searchable="true" class="text-center">理论重量/t</th> --> --%>
						<th data-field="suttle" data-halign="center"
							data-searchable="true" class="text-center" nowrap="nowrap">出库重量/t</th>
						<th data-field="createoperaname" data-halign="center"
							data-searchable="true" class="text-nowrap text-center" nowrap="nowrap">出库人</th>
						<th data-field="createdate" data-halign="center" data-searchable="true"
							class="text-nowrap text-center" nowrap="nowrap">出库时间</th>
						<th data-field="usermemo" data-halign="center"
							data-searchable="true" class="text-nowrap" nowrap="nowrap">备注</th>
						<th data-width="70px" data-align="center" data-valign="middle"
							class="text-center" data-formatter="operateFormatter"
							data-events="operateEvents">操作</th>
					</tr>
				</thead>
			</table>

		</div>
	</div>
	<div class="modal fade" id="StoreoutWindow" tabindex="-1" role="dialog" aria-labelledby="StoreoutWindowLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">货场--${unitname}</h4>
				</div>
				<div class="modal-body">
					<form id="StoreoutForm">
						<input type="hidden" name="id" value="-1" />
						<input type="hidden" name="matchid" id="matchids" value="" /> 
						<input type="hidden" name="unitcode" id="unitcode" value="${unitcode}" />
						<input type="hidden" name="unitname" id="unitname" value="${unitname}">
						<input type="hidden" name="stoplink" id="stoplink" value="">
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">卡　　号</span>
									<input type="text" name="cardid" id="cardids" class="form-control" aria-describedby="basic-addon1" readonly="readonly" required data-bv-notempty-message="请刷卡获取卡号" />
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">车　　号</span> 
									<input name="carno"　type="text" class="form-control"　aria-describedby="basic-addon1" readonly="readonly" required　　data-bv-notempty-message="请刷卡获取车号">
								</div>
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
					<div class="row">
						<div class="col-sm-12">
							<table id="StoreoutinfoGrid" data-toggle="table"  data-mobile-responsive="true">
								<thead>
									<tr>
									    <th data-field="state" data-checkbox="true"></th>
										<%-- <th data-field="tare" data-formatter="stateFormatter"></th> --%>
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
					<div class="col-sm-2 btn-group-sm" align="left">
						<button type="button" class="btn btn-warning" id="StoreoutfinishBtn" onclick="insertforce()">终止</button>
					</div>
					<div class="col-sm-10 btn-group-sm" >
					   <button type="button" class="btn btn-success" id="StoreoutaddBtn" onclick="insert()">确认</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>	
				</div>
			</div>
		</div>
	</div>
    <div class="modal fade" id="StoreoutEXWindow" tabindex="-1" role="dialog" aria-labelledby="StoreoutWindowLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">货场--${unitname}</h4>
				</div>
				<div class="modal-body">
					<form id="StoreoutEXForm">
						<input type="hidden" name="id" value="-1" />
						<input type="hidden" name="matchid"  value="" /> 
						<input type="hidden" name="unitcode"  value="${unitcode}" />
						<input type="hidden" name="unitname" value="${unitname}">
						<input type="hidden" name="stoplink" id="stoplink" value="">
						<input type="hidden" name="exceptionflag" id="exceptionflag"  value="1">
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">卡　　号</span>
									<input type="text" name="cardid" id="cardids" class="form-control" aria-describedby="basic-addon1" readonly="readonly" required data-bv-notempty-message="请刷卡获取卡号" />
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">车　　号</span> 
									<input name="carno" id="excarno" type="text" class="form-control" aria-describedby="basic-addon1" data-provide="typeahead"  required data-bv-notempty-message="请输入车号">
								</div>
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
					<div class="row">
						<div class="col-sm-12">
							<table id="StoreoutinfoEXGrid" data-toggle="table"  data-mobile-responsive="true">
								<thead>
									<tr>
									    <th data-field="state" data-checkbox="true"></th>
										<%-- <th data-field="tare" data-formatter="stateFormatter"></th> --%>
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
					<button type="button" class="btn btn-success" id="StoreoutaddEXBtn" onclick="insertEX()">确认</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	var cardType = 'MHRF35LT';//'MC9500';
	jQuery(document).ready(function($) {
		queryinfo();	
		queryRTypeBycode();//通过作业点编码查询读卡器型号		
		$('.fixed-table-footer > table').remove();
		$('.fixed-table-footer').addClass('container-fluid').html('<div class="row"><div class="col-sm-12 text-center" style="font-size:15px;font-weight:bold" > 总车数/车:<span style="font-size:15px" id="sumcountFooter" class="label label-primary">0</span>&nbsp;&nbsp;总数量:<span style="font-size:15px" id="sumcountFooter" class="label label-primary">0.00</span> &nbsp;&nbsp;总重量/t:<span style="font-size:15px" id="sumsuttleFooter" class="label label-success">0.00</span></div></div>');
    
		$.fn.typeahead.Constructor.prototype.blur = function() {
		      var that = this;
		      setTimeout(function(){that.hide()},250);
	     };

	   $('#excarno').typeahead({source:function (query, process) {		
			        $.ajax({
						type : "post",
						url : '<c:url value="/bcommon/queryStoreoutCarno.do?carno="/>'+query,
						dataType : "json",
						success : function(data) {	
							var numArr = []; // 定义一个空数组
							 for (var i = 0; i < data.rows.length; i++) {
				                 numArr.push(data.rows[i].carno); // 将文本框的值添加到数组中
				             }							 
							 process(numArr);							
						}
			       });			        
			    },
			    updater:function(data){
			    	getinfobycarno(data);
			    	return data;
			    }
		});
	
	
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
			$('#StoreoutGrid').bootstrapTable('refresh', {url : "<c:url value='/storeout/queryPage.do'/>"});
		}
		$('#StoreoutGrid').bootstrapTable({
			onLoadSuccess : function(data) {
				$.ajax({
		            type: "post",
		            url: '<c:url value="/storeout/queryCount.do"/>',
		            dataType: "json",
		            data:  $('#queryform').serializeJson(), 
		            success: function(data){
		            	if(data!=null){
		            		if(data.data!=null){
								$('.fixed-table-footer > table').remove();
								$('.fixed-table-footer').addClass('container-fluid').html('<div class="row"><div class="col-sm-12 text-center" style="font-size:15px;font-weight:bold" > 总车数/车:<span style="font-size:15px" id="sumcountFooter" class="label label-primary">'+data.data.carcounts+'</span>&nbsp;&nbsp;总数量:<span style="font-size:15px" id="sumcountFooter" class="label label-primary">'+data.data.counts+'</span> &nbsp;&nbsp;总重量/t:<span style="font-size:15px" id="sumsuttleFooter" class="label label-success">'+data.data.suttle+'</span></div></div>');
				            }
		            	}
		              }
	            });
				
			},
			onDblClickRow : function(row, $element) {
				layer.open({
							type : 2,
							title : '信息详情',
							maxmin : true, //开启最大化最小化按钮
							area : [ '90%', '90%' ],
							content : '<c:url value="/bcommon/showdetail.do?matchid="/>'+ row.matchid //注意，如果str是object，那么需要字符拼接。
				   });
				}
		});
		
		
		
		function operateFormatter(value, row, index) {
			return [
                    '<div class="pull-right">',                   	
                   	'<t:ibutton text="移除" modulecode="ChuKuGuanLi" id="storeoutremove" btnclass="remove" iconclass="glyphicon glyphicon-trash"/>',
                   	'</div>'
               	].join('');			
		}
		window.operateEvents = {
			'click .remove' : function(e, value, row) {
				cancelStoreout(row.matchid);
			}
		};
		$('#StoreoutBtn').on('click', function() {
			openStoreoutWindow(-1);
		});
		function openStoreoutWindow(id_vv) {
			 $.ajax({
    	        url:'http://127.0.0.1:9001/talentlt/GetMachineInfo',
    	        dataType: "jsonp",
    	        crossDomain: true,
    	        timeout:10000,
    	        jsonp: "callback",
    	        success: function (data) {
    	        	$.ajax({
    		            type: "post",
    		            url: '<c:url value="/workpoint/queryInfoBycode.do"/>',
    		            dataType: "json",
    		            data: {workpointcode:$("#unitcodes").val(),workpointmac:data.macAddress}, 
    		            success: function(data){
    		            	if(data.success){
    		            		if(data.data.validflag=='3'){//审核过的作业点
	    		            		currentId = id_vv;
	    		        			$($('#StoreoutWindow form :input[name="id"]')).val(id_vv);
	    		        			$('#StoreoutWindow').modal('show');
    		            		}else{
   		            			 warningbox("作业点未审核");
   		            		}
    		            	}else{
    		            	   warningbox(data.msg);
    		            	}
    		             }
    	            });
    	        }
    	    }); 
			/* currentId = id_vv;
			$($('#StoreoutWindow form :input[name="id"]')).val(id_vv);
			$('#StoreoutWindow').modal('show'); */
		}
		
		/*打开发卡页面*/
		
	//	var cardType = 'MC9500';
		function onFindICard(data){//打卡读卡器进行哪些操作			
			$('#StoreoutForm input[name="cardid"]').val(data.cardid);
			$.ajax({
			       type: "post",
			       url: '<c:url value="/bcommon/judgCarno.do"/>',
			       dataType: "json",
			       data: {cardid:data.cardid}, 
			       success: function(data){
			         if(data.success == true){//卡和车辆状态正常，根据车号查询业务信息	
			        	$($('#StoreoutWindow form :input[name="carno"]')).val(data.rows[0].carno); 
			            $('#StoreoutinfoGrid').bootstrapTable('refresh',{url : "<c:url value='/storeout/queryInfoBycarno.do?carno='/>"+data.rows[0].carno});
			          }else{//如果车辆或者卡有问题，系统提示
			           errorbox(data.msg); 
			           $('#StoreoutaddBtn').prop('disabled', true); 
			           $('#StoreoutfinishBtn').prop('disabled', true); 
			         } 
			    }
		    });
			validForm('StoreoutForm');
		}
		
		function onDropICard(data){//关闭读卡器，进行哪些操作
			$('#StoreoutForm input[name="cardid"]').val('');
			$('#StoreoutForm input[name="carno"]').val('');
			$('#StoreoutForm input[name="usermemo"]').val('');
			$('#StoreoutForm input[name="matchid"]').val('');
			$('#StoreoutinfoGrid').bootstrapTable('removeAll');
			$('#StoreoutaddBtn').prop('disabled', false); 
			$('#StoreoutfinishBtn').prop('disabled', false); 
			validForm('StoreoutForm');
		}
		$('#StoreoutWindow').on('hide.bs.modal', function() {	 //页面隐藏时，关闭读卡器		
			 CloseMediaReader(cardType,function(data){//关闭读卡器
				
			}); 
		});
		
		/*打开出库页面*/
		$('#StoreoutWindow').on('shown.bs.modal',function() {
			$('#StoreoutaddBtn').prop('disabled', false); 
	        $('#StoreoutfinishBtn').prop('disabled', false); 
			loadFormData('StoreoutForm','<c:url value="/storeout/queryStoreout.do?unitcode="/>'+$("#unitcode").val()+'&unitname='+$("#unitname").val(),function(data){
				OpenMediaReader(cardType,onFindICard,onDropICard,function(data){//第一个参数是读卡器型号、第二个是打开卡操作，第三个参数是关闭读卡器操作
					
				});
			});

		});
		
		function stateFormatter(value, row, index) {
			if (row.tare == 0) {
				return '';
			} else {
				if (index === 0) {
					$("#matchids").val(row.matchid);
					return '<center><input name="tare"  type="radio" onclick="putmatchid(\''+ row.matchid + '\')" checked ><center>';
				} else {
					return '<center><input name="tare" type="radio" onclick="putmatchid(\''+ row.matchid + '\')"   ><center>';
				}

			}
		}
		//选中的信息存放到
		function putmatchid(matchid) {
			$("#matchids").val(matchid);
		}
		/**
		 *添加出库信息
		 */
		function insert() {
			$('#StoreoutaddBtn').prop('disabled', true);
			$('#StoreoutfinishBtn').prop('disabled', true);
			var matchid = $("#matchids").val();
			var gridData = $('#StoreoutinfoGrid').bootstrapTable('getSelections');
			var unitcode = $("#unitcode").val();
			   var carno = $('#StoreoutForm input[name="carno"]').val();	
			var flag=0;
			 if (gridData.length>0) {
				 var flag=0;
				 var cflag=0;
				for(var i=0;i<gridData.length;i++){
					 if(i>0){
						 if(gridData[i].matchid!=gridData[i-1].matchid){							 
							 flag++;
						 }
					 }
					 var isor=gridData[i].isormeasure
					 if(isor==0){
						 var vcounts=gridData[i].counts;
						 if(vcounts==null || vcounts==''){
					 	   cflag++;
						   }
					   }
				 
			     } 
				if(flag==0&&cflag==0){
					$.ajax({
    		            type: "post",
    		            url: "<c:url value='/storeout/queryInfoBycarnoNotcode.do?carno='/>"+carno+"&storecode="+unitcode,
    		            dataType: "json",
     		            success: function(data){
    		            	 if(!data.success){
    		            		errorbox(data.msg);
    		            		$('#StoreoutaddBtn').prop('disabled', false);
    							$('#StoreoutfinishBtn').prop('disabled', false);
    		            	} else{
    		            		 saveFormDataWithParams('StoreoutForm','<c:url value="/storeout/beforeinsertStoreout.do"/>',gridData,function(data) {
    								if (data.mfunc == 0) {
    									if (data.success) {
    										successbox(data.msg);
    										$('#StoreoutForm input[name="cardid"]').val('');
    										$('#StoreoutForm input[name="carno"]').val('');
    										$('#StoreoutForm input[name="usermemo"]').val('');
    										$('#StoreoutForm input[name="matchid"]').val('');
    										$('#StoreoutGrid').bootstrapTable('refresh');
    										$('#StoreoutinfoGrid').bootstrapTable('removeAll');
    									} else {
    										errorbox(data.msg);
    									}
    									$('#StoreoutaddBtn').prop('disabled', false);
    									$('#StoreoutfinishBtn').prop('disabled', false);
    									validForm('StoreoutForm');
    								} else {
    									if (data.mfunc == 3) { //为3时判断里面有一个禁止添加取样信息
    										errorbox(data.msg);
    									} else if (data.mfunc == 2) {//为2时用户选择是否添加取样信息
    										dialogbox("请确认",data.msg,function(data) {
   												if (data) {
   													//saveFormData('StoreoutForm','<c:url value="/storeout/insertStoreout.do"/>',function(data) {
   														saveFormDataWithParams('StoreoutForm','<c:url value="/storeout/insertStoreout.do"/>',gridData,function(data) {	
   															if (data.success) {
   																successbox(data.msg);
   																$('#StoreoutGrid').bootstrapTable('refresh');
   																$('#StoreoutForm input[name="cardid"]').val('');
   																$('#StoreoutForm input[name="carno"]').val('');
   																$('#StoreoutForm input[name="usermemo"]').val('');
   																$('#StoreoutinfoGrid').bootstrapTable('removeAll');
   															} else {
   																errorbox(data.msg);
   															}
   														$('#StoreoutaddBtn').prop('disabled', false);
   														$('#StoreoutfinishBtn').prop('disabled', false);
   													});
   												}else{
   													$('#StoreoutaddBtn').prop('disabled', false);
   													$('#StoreoutfinishBtn').prop('disabled', false);
   												}
    												
    										});
    									}
    								}
  		    					});
    		            	}  
    		            }
    		         });
				}else if(flag>0&&cflag==0){
					errorbox("请选相同物流号信息")
					$('#StoreoutaddBtn').prop('disabled', false);
					$('#StoreoutfinishBtn').prop('disabled', false);
	
				}else if(flag==0&&cflag>0){
					errorbox("请填写出库数量")
					$('#StoreoutaddBtn').prop('disabled', false);
					$('#StoreoutfinishBtn').prop('disabled', false);
				}else{
					errorbox("请选相同物流号信息并填写出库数量");
					$('#StoreoutaddBtn').prop('disabled', false);
					$('#StoreoutfinishBtn').prop('disabled', false);
					
				}
			} else {
				warningbox("请选择出库信息");
				$('#StoreoutaddBtn').prop('disabled', false);
				$('#StoreoutfinishBtn').prop('disabled', false);
			} 
		}
		/**
		 *终止业务信息
		 */

		function insertforce() {
			$('#StoreoutaddBtn').prop('disabled', true);
			$('#StoreoutfinishBtn').prop('disabled', true);
			$("#stoplink").val("SOUT");
			var matchid = $("#matchids").val();
			var gridData = $('#StoreoutinfoGrid').bootstrapTable('getSelections');
			var matchid = $("#matchids").val();
			if (gridData.length>0) {
				 var flag=0;
				for(var i=0;i<gridData.length;i++){
					 if(i>0){
						 if(gridData[i].matchid!=gridData[i-1].matchid){							 
							 flag++;
						 }
					 }
			     } 
				if(flag==0){
					$("#matchids").val(gridData[0].matchid);
					dialogbox("请确认", "确认终止业务吗？", function(data) {
						if (data) {
							saveFormData('StoreoutForm','<c:url value="/bcommon/forcestop.do"/>',function(data) {
								if (data.success) {
									successbox(data.msg);
									//$('#StoreoutWindow').modal('toggle');
									$('#StoreoutGrid').bootstrapTable('refresh');
								} else {
									errorbox(data.msg);
								}
							});
						}else{
							$('#StoreoutaddBtn').prop('disabled', false);
							$('#StoreoutfinishBtn').prop('disabled', false);
						}
				   });
				}else{
					errorbox("请选择相同物流号的信息");
					$('#StoreoutaddBtn').prop('disabled', false);
					$('#StoreoutfinishBtn').prop('disabled', false);
				}				
			} else {
				errorbox("请选择需要终止的业务");
				$('#StoreoutaddBtn').prop('disabled', false);
				$('#StoreoutfinishBtn').prop('disabled', false);
			}
		}
		/**
		 *作废出库信息
		 */
		function cancelStoreout(matchid) {
			dialogbox("请确认", "确认删除此项目？", function(data) {
				if (data) {
					$.ajax({
						type : "post",
						url : '<c:url value="/storeout/cancelStoreout.do"/>',
						dataType : "json",
						data : {
							matchid : matchid
						},
						success : function(data) {
							if (data.success == true) {
								successbox(data.msg);
								$('#StoreoutGrid').bootstrapTable('refresh');
							} else {
								errorbox(data.msg);
							}
						}
					});
				}
			});
		}
		/* $('#StoreoutGrid').bootstrapTable({
			onDblClickRow : function(row, $element) {
			layer.open({
						type : 2,
						title : '信息详情',
						maxmin : true, //开启最大化最小化按钮
						area : [ '90%', '90%' ],
						content : '<c:url value="/bcommon/showdetail.do?matchid="/>'+ row.matchid //注意，如果str是object，那么需要字符拼接。
			   });
			}
		}); */
		/* ----------------------------------------------异常出库---------------------------------------------- */
		$('#StoreOutEXBtn').on('click', function() {
			openStoreoutEXWindow(-1);
		});
		function openStoreoutEXWindow(matchid_vv) {
			$($('#StoreoutEXWindow form :input[name="id"]')).val(matchid_vv);
			$('#StoreoutEXWindow').modal('show');
		}
		/*打开异常出库页面*/
		$('#StoreoutEXWindow').on('shown.bs.modal',function() {
			$('#StoreoutaddEXBtn').prop('disabled', false); 
			var unitcode=$('#StoreoutEXWindow form :input[name="unitcode"]').val();
    		var unitname=$('#StoreoutEXWindow form :input[name="unitname"]').val();
			loadFormData('StoreoutEXForm','<c:url value="/storeout/queryStoreout.do?unitcode="/>'+unitcode+'&unitname='+unitname,function(data){
				$("#exceptionflag").val("1");
			});

		});
		function getinfobycarno(carno){
			$.ajax({
	            type: "post",
	            url: '<c:url value="/bcommon/judgOrBlackCarno.do"/>',
	            dataType: "json",
	            data: {carno:carno}, 
	            success: function(data){
	            	 if(data.success){//卡和车辆状态正常，根据车号查询业务信息	
	            		 $('#StoreoutEXWindow form :input[name="cardid"]').val(data.data)
	            		 $('#StoreoutinfoEXGrid').bootstrapTable('refresh',{url : "<c:url value='/storeout/queryInfoBycarno.do?carno='/>"+carno});	
	            	 	 validForm('StoreoutEXWindow');
	            	 }else{//如果车辆或者卡有问题，系统提示
	            		errorbox(data.msg); 
	            		$('#StoreoutaddEXBtn').prop('disabled', true); 
	            	 } 
	            }
            }); 
		}
		
		/**
		 *添加异常出库信息
		 */
		function insertEX() {
			$('#StoreoutaddEXBtn').prop('disabled', true);
			var matchid = $('#StoreoutEXForm input[name="matchid"]').val();			
			var unitcode = $('#StoreoutEXForm input[name="unitcode"]').val();
			var carno = $('#StoreoutEXForm input[name="carno"]').val();	
			var gridData = $('#StoreoutinfoEXGrid').bootstrapTable('getSelections');
			var flag=0;
			 if (gridData.length>0) {
				 var flag=0;
				 var cflag=0;
				for(var i=0;i<gridData.length;i++){
					 if(i>0){
						 if(gridData[i].matchid!=gridData[i-1].matchid){							 
							 flag++;
						 }
					 }
					 var isor=gridData[i].isormeasure
					 if(isor==0){
						 var vcounts=gridData[i].counts;
						 if(vcounts==null || vcounts==''){
					 	   cflag++;
						   }
					   }
				 
			     } 
				if(flag==0&&cflag==0){
					$.ajax({
    		            type: "post",
    		            url: "<c:url value='/storeout/queryInfoBycarnoNotcode.do?carno='/>"+carno+"&storecode="+unitcode,
    		            dataType: "json",
     		            success: function(data){
    		            	 if(!data.success){
    		            		errorbox(data.msg);
    		            		$('#StoreoutaddEXBtn').prop('disabled', false);
    		            	} else{
    		            		 saveFormDataWithParams('StoreoutEXForm','<c:url value="/storeout/beforeinsertStoreout.do"/>',gridData,function(data) {
    								if (data.mfunc == 0) {
    									if (data.success) {
    										successbox(data.msg);
    										$('#StoreoutEXForm input[name="cardid"]').val('');
    										$('#StoreoutEXForm input[name="carno"]').val('');
    										$('#StoreoutEXForm input[name="usermemo"]').val('');
    										$('#StoreoutEXForm input[name="matchid"]').val('');    										
    										$('#StoreoutinfoEXGrid').bootstrapTable('removeAll');
    										$('#StoreoutGrid').bootstrapTable('refresh');
    									} else {
    										errorbox(data.msg);
    									}
    									$('#StoreoutaddEXBtn').prop('disabled', false);
    									validForm('StoreoutEXForm');
    								} else {
    									if (data.mfunc == 3) { //为3时判断里面有一个禁止添加取样信息
    										errorbox(data.msg);
    									} else if (data.mfunc == 2) {//为2时用户选择是否添加取样信息
    										dialogbox("请确认",data.msg,function(data) {
   												if (data) {
   													saveFormDataWithParams('StoreoutEXForm','<c:url value="/storeout/insertStoreout.do"/>',gridData,function(data) {	
   															if (data.success) {
   																successbox(data.msg);   																
   																$('#StoreoutEXForm input[name="cardid"]').val('');
   																$('#StoreoutEXForm input[name="carno"]').val('');
   																$('#StoreoutEXForm input[name="usermemo"]').val('');
   																$('#StoreoutEXForm input[name="matchid"]').val('');
   																$('#StoreoutinfoEXGrid').bootstrapTable('removeAll');
   																$('#StoreoutGrid').bootstrapTable('refresh');
   															} else {
   																errorbox(data.msg);
   															}
   														$('#StoreoutaddEXBtn').prop('disabled', false);
   													});
   												}else{
   													$('#StoreoutaddEXBtn').prop('disabled', false);
   												}
    										});
    									}
    								}
  		    					});
    		            	}  
    		            }
    		         });
				}else if(flag>0&&cflag==0){
					errorbox("请选相同物流号信息")
					$('#StoreoutaddEXBtn').prop('disabled', false);
				}else if(flag==0&&cflag>0){
					errorbox("请填写出库数量")
					$('#StoreoutaddEXBtn').prop('disabled', false);
				}else{
					errorbox("请选相同物流号信息并填写出库数量");
					$('#StoreoutaddEXBtn').prop('disabled', false);
				}
			} else {
				warningbox("请选择出库信息");
				$('#StoreoutaddEXBtn').prop('disabled', false);
			}
		}

		function exportExcel() {
			remoteExportExcel('StoreoutGrid','', '', 'queryform');
		}
	</script>
</body>
</t:html>