<%@ page contentType="text/html;charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
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
	/* String unitname = request.getParameter("unitname") == null ? "": request.getParameter("unitname");
	String unitcode = request.getParameter("unitcode") == null ? "": request.getParameter("unitcode"); */
%>
<body class="container-fluid" style="padding-top: 15px">
	<div class="row">
		<div class="col-sm-12">
			<form id="queryform">
			  <input type="hidden"  id="unitcodes" value="${unitcode}" />
			  <input type="hidden" name="unitcode" value="${unitcode}" />
			  <input type="hidden" name="unitname"  value="${unitname}">
				<div class="row">
					<div class="col-sm-3">
						<div class='form-group input-group input-group-sm date'>
							<span class="input-group-addon">开始时间</span> 
							<input type='text' class="form-control" placeholder="开始时间" id="begintime" name="begintime" value="<%=begintime%>" />
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">到&nbsp;&nbsp;货&nbsp;&nbsp;单</span>
							<input type="text" class="form-control" placeholder="到货单" id="planid" name="planid" aria-describedby="sizing-addon3">
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
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">物&nbsp;&nbsp;流&nbsp;&nbsp;号</span>
							<input type="text" class="form-control" placeholder="物流号" id="matchid" name="matchid" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">货　　名</span>
							<input type="text" class="form-control" placeholder="货名" id="materialname" name="materialname" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">库　　房</span>
							<!-- <input type="text" class="form-control" placeholder="收货" id="targetname" name="targetname" aria-describedby="sizing-addon3"> -->
						    <input type="text" class="form-control" placeholder="收货" id="storename" name="storename" aria-describedby="sizing-addon3" value="${unitname}">
						</div>
					</div>

				</div>

				<div class="row">
					<div class="col-sm-12 btn-group-sm">
						<!-- <button id="query" type="button" class="btn btn-info" onclick="queryinfo()">
							<span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;查询
						</button>
						<button id="StoreinBtn" type="button" class="btn btn-success">
							<span class="glyphicon glyphicon-th-large" aria-hidden="true"></span>&nbsp;入库
						</button> -->
						<t:button text="查询" modulecode="RuKuGuanLi" id="query" btnclass="btn btn-info" iconclass="glyphicon glyphicon-search" onclick="queryinfo()" />
						<t:button text="入库" modulecode="RuKuGuanLi" id="StoreinBtn" btnclass="btn btn-success" iconclass="glyphicon glyphicon-th-large" />
						<button type="button" class="btn btn-danger" onclick="print(1)">
							<span class="glyphicon glyphicon-print" aria-hidden="true"></span>&nbsp;打印
						</button>
						<t:button text="异常入库" modulecode="RuKuGuanLi" id="StoreinEXBtn" btnclass="btn btn-warning" iconclass="glyphicon glyphicon-th" />
						<t:button text="EXCEL" modulecode="RuKuGuanLi" id="StoreinExcel" btnclass="btn btn-warning" iconclass="fa fa-file-excel-o" onclick="exportExcel()" />
					   <!-- <button type="button" class="btn btn-default" id="StoreinEXBtn">
							<span class="glyphicon glyphicon-th" aria-hidden="true"></span>&nbsp;异常入库
						</button> -->
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="row"  style="padding-top:5px">
		<div class="col-sm-12">
			<table id="StoreinGrid" 
			    data-toggle="table" 
			    data-row-style="rowStyle"
				data-query-params="queryParams" 
				data-pagination="true"
				data-page-size="10" 
				data-page-list="[10, 40]"
				data-show-footer="true"
				data-mobile-responsive="true">
				<thead>
					<tr>
						<th data-field="id" data-checkbox="true"></th>
						<th data-field="matchid" data-halign="center"
							data-searchable="true" sortable class="text-center" nowrap="nowrap">物流号</th>
						<th data-field="planid" data-halign="center"
							data-searchable="true" sortable class="text-center" nowrap="nowrap">到货单</th>
						<th data-field="carno" data-halign="center" data-searchable="true"
							class="text-nowrap text-center" nowrap="nowrap" >车号</th>
						<th data-field="materialcode" data-halign="center"
							data-searchable="true" class="text-nowrap" nowrap="nowrap">货名编码</th>	
						<th data-field="materialname" data-halign="center"
							data-searchable="true" class="text-nowrap" nowrap="nowrap">货名</th>
						<th data-field="materialspec" data-halign="center"
							data-searchable="true" class="text-nowrap text-center" nowrap="nowrap">规格</th>
						<th data-field="modelno" data-halign="center"
							data-searchable="true" class="text-nowrap text-center" nowrap="nowrap">型号</th>
						<th data-field="pictureno" data-halign="center"
							data-searchable="true" class="text-nowrap text-center" nowrap="nowrap">图号</th>	
						<th data-field="sourcename" data-halign="center"
							data-searchable="true" class="text-nowrap" nowrap="nowrap">供货</th>
							<th data-field="middlemanname" data-halign="center" class="text-nowrap text-left"  nowrap="nowrap">使用单位</th>
						<th data-field="counts" data-halign="center"
							data-searchable="true" class="text-center" nowrap="nowrap">入库数量</th>
						<th data-field="suttle" data-halign="center" 
							 class="text-center" nowrap="nowrap">入库重量/t</th>
						<th data-field="targetname" data-halign="center" data-visible="false"
							class="text-nowrap" nowrap="nowrap">收货</th>
						<th data-field="storename" data-halign="center"
							data-searchable="true" class="text-nowrap" nowrap="nowrap">收货库房</th>
						
						<th data-field="createoperaname" data-halign="center"
							data-searchable="true" class="text-nowrap text-center" nowrap="nowrap">入库人</th>
						<th data-field="createdate" data-halign="center" data-searchable="true"
							class="text-nowrap text-center" nowrap="nowrap">入库时间</th>
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
	<div class="modal fade" id="StoreinWindow" tabindex="-1" role="dialog"
		aria-labelledby="StoreinWindowLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">货场--${unitname}</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-sm-12">
							<form id="StoreinForm">
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
											<input name="carno" type="text" class="form-control" aria-describedby="basic-addon1"  readonly="readonly" required data-bv-notempty-message="请刷卡获取车号">
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
								</div>
								<div class="row">
									<div class="col-sm-12">
										<div class="form-group input-group input-group-sm">
											<span class="input-group-addon">备　　注</span> 
											<input name="usermemo" type="text" class="form-control" aria-describedby="basic-addon1">
										</div>
									</div>
								</div>
								<div class="row">
								<div class="col-sm-12">
									<table id="StoreininfoGrid" data-toggle="table"
										data-mobile-responsive="true">
										<thead>
											<tr>
											    <th data-field="state" data-checkbox="true"></th>
												<%-- <th data-field="gross" data-formatter="stateFormatter" ></th> --%>
												<th data-field="matchid" data-halign="center" nowrap="nowrap">物流号</th>
												<th data-field="itemid" data-visible="false">行号</th>
												<th data-field="planid" data-halign="center" nowrap="nowrap">到货单</th>
												<th data-field="taskcode"  data-visible="false">业务号</th>
												<th data-field="materialcode" data-halign="center" class="text-nowrap text-left" >编码</th>
												<th data-field="materialname" data-halign="center" class="text-nowrap text-left"  nowrap="nowrap">货名</th>
												<th data-field="materialspec" data-halign="center" class="text-nowrap text-left"  nowrap="nowrap">规格</th>
												<th data-field="modelno" data-halign="center" class="text-nowrap text-left"  nowrap="nowrap">型号</th>
												<th data-field="sourcecode"  data-visible="false">供货编码</th>
												<th data-field="sourcename" data-halign="center" class="text-nowrap text-left"  nowrap="nowrap">供货</th>
												<th data-field="targetcode"  data-visible="false">供货编码</th>
												<th data-field="targetname" data-halign="center" class="text-nowrap text-left"  nowrap="nowrap" data-visible="false">收货</th>
												<th data-field="gross" data-halign="center" nowrap="nowrap">毛重/t</th>
												<th data-field="weight" data-halign="center" nowrap="nowrap"  data-visible="false">计划重量/t</th>
												<th data-field="outcounts" data-halign="center" nowrap="nowrap">计划数量</th>
												<!-- 计重时实际重量和实际数量不显示 -->
												<th data-field="suttle" data-halign="center" nowrap="nowrap" >实际重量/t</th>
												<th data-field="counts" data-halign="center" nowrap="nowrap" data-editable="true">实际数量</th>
												<th data-field="measureunit" data-halign="center" nowrap="nowrap" >计量单位</th>
												<th data-field="middlemanname" data-halign="center" class="text-nowrap text-left"  nowrap="nowrap">使用单位</th>	
												<th data-field="pictureno" data-halign="center" class="text-nowrap text-left"  nowrap="nowrap">图号</th>
												<th data-field="prodlinename" data-halign="center" class="text-nowrap text-left"  nowrap="nowrap">产地</th>	
												<th data-field="isormeasure" data-halign="center"  data-visible="false">是否计量</th>
												<th data-field="operaname" data-visible="false" >业务类型</th>
												<th data-field="entrytime" data-visible="false" >进厂时间</th>
											</tr>
										</thead>
									</table>
								</div>
							</div>
							</form>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					 <div class="col-sm-2 btn-group-sm" align="left">
					     <button type="button" class="btn btn-warning" id="StoreinfinishBtn" onclick="insertforce()" disabled="disabled">终止</button>
					</div>
					<div class="col-sm-10 btn-group-sm">
					     <button type="button" class="btn btn-info" id="PrintBtn"	onclick="print(0)" >打印</button>
						<button type="button" class="btn btn-success" id="SaddBtn"	onclick="insert()" disabled="disabled">确认</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
		</div>
	</div>
   <div class="modal fade" id="StoreinEXWindow" tabindex="-1" role="dialog"
		aria-labelledby="StoreinWindowLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">货场--${unitname}</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-sm-12">
							<form id="StoreinEXForm">
								<input type="hidden" name="id" value="-1" />
							    <input type="hidden" name="matchid"   value="" /> 
								<input type="hidden" name="unitcode"  value="${unitcode}" />
								<input type="hidden" name="unitname"  value="${unitname}">	
								<input type="hidden" name="stoplink"  value="">
								<input type="hidden" name="exceptionflag" id="exceptionflag"  value="1">
								<div class="row">
									<div class="col-sm-6">
										<div class="form-group input-group input-group-sm">
											<span class="input-group-addon">卡　　号</span> 
											<input type="text" name="cardid" class="form-control" aria-describedby="basic-addon1" readonly="readonly" required data-bv-notempty-message="请刷卡获取卡号" />
										</div>
									</div>
									<div class="col-sm-6">
										<div class="form-group input-group input-group-sm">
											<span class="input-group-addon">车　　号</span> 
											<input name="carno" id="excarno" type="text" class="form-control" aria-describedby="basic-addon1" data-provide="typeahead"  required data-bv-notempty-message="请刷卡获取车号">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-6">
										<div class="form-group input-group input-group-sm">
											<span class="input-group-addon">收货方式</span> 
											<select  name="recetype" class="form-control select2" placeholder="必选" required data-bv-notempty-message="请选择收获方式">
												<option value="0">全部收货</option>
												<option value="1">部分收货</option>
												<option value="2">整车退货</option>
											</select>
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
								<div class="row">
								<div class="col-sm-12">
									<table id="StoreininfoEXGrid" data-toggle="table"
										data-mobile-responsive="true">
										<thead>
											<tr>
											    <th data-field="state" data-checkbox="true"></th>
												<%-- <th data-field="gross" data-formatter="stateFormatter" ></th> --%>
												<th data-field="matchid" data-halign="center" nowrap="nowrap">物流号</th>
												<th data-field="itemid" data-visible="false">行号</th>
												<th data-field="planid" data-halign="center" nowrap="nowrap">到货单</th>
												<th data-field="taskcode"  data-visible="false">业务号</th>
												<th data-field="materialcode" data-halign="center" class="text-nowrap text-left" >编码</th>
												<th data-field="materialname" data-halign="center" class="text-nowrap text-left"  nowrap="nowrap">货名</th>
												<th data-field="materialspec" data-halign="center" class="text-nowrap text-left"  nowrap="nowrap">规格</th>
												<th data-field="modelno" data-halign="center" class="text-nowrap text-left"  nowrap="nowrap">型号</th>
												<th data-field="sourcecode"  data-visible="false">供货编码</th>
												<th data-field="sourcename" data-halign="center" class="text-nowrap text-left"  nowrap="nowrap">供货</th>
												<th data-field="targetcode"  data-visible="false">供货编码</th>
												<th data-field="targetname" data-halign="center" class="text-nowrap text-left"  nowrap="nowrap" data-visible="false">收货</th>
												<th data-field="gross" data-halign="center" nowrap="nowrap">毛重/t</th>
												<th data-field="weight" data-halign="center" nowrap="nowrap"  data-visible="false">计划重量/t</th>
												<th data-field="outcounts" data-halign="center" nowrap="nowrap">计划数量</th>
												<!-- 计重时实际重量和实际数量不显示 -->
												<th data-field="suttle" data-halign="center" nowrap="nowrap" >实际重量/t</th>
												<th data-field="counts" data-halign="center" nowrap="nowrap" data-editable="true">实际数量</th>
												<th data-field="measureunit" data-halign="center" nowrap="nowrap" >计量单位</th>
												<th data-field="middlemanname" data-halign="center" class="text-nowrap text-left"  nowrap="nowrap">使用单位</th>	
												<th data-field="pictureno" data-halign="center" class="text-nowrap text-left"  nowrap="nowrap">图号</th>
												<th data-field="prodlinename" data-halign="center" class="text-nowrap text-left"  nowrap="nowrap">产地</th>	
												
												<th data-field="isormeasure" data-halign="center"  data-visible="false">是否计量</th>
												<th data-field="operaname" data-visible="false" >业务类型</th>
												<th data-field="entrytime" data-visible="false" >进厂时间</th>
											</tr>
										</thead>
									</table>
								</div>
							</div>
							</form>
						</div>
					</div>
				</div>
				<div class="modal-footer  btn-group-sm">
				    <button type="button" class="btn btn-info" id="PrintEXBtn"	onclick="printEx()" >打印</button>
					<button type="button" class="btn btn-success" id="SaddEXBtn"	onclick="insertEX()" >确认</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	var cardType;//= 'MHRF35LT';//'MC9500';
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
							url : '<c:url value="/bcommon/queryStoreinCarno.do?carno="/>'+query,
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
		
		function getPrintData(){
			return $('#StoreininfoGrid').bootstrapTable('getAllSelections');
		}
		
		function getPrintsData(){
			return $('#StoreinGrid').bootstrapTable('getAllSelections');
		}
		 $('#StoreinGrid').bootstrapTable({
			onLoadSuccess : function(data) {
				$.ajax({
		            type: "post",
		            url: '<c:url value="/storein/queryCount.do"/>',
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
		

			function print(batch){
				if(0 == batch){
					var usermemo = $('#StoreinForm input[name="usermemo"]').val();
					var gridData = $('#StoreininfoGrid').bootstrapTable('getSelections');
					var matchid = '';
					 if (gridData.length>0) {
						 var flag=0;
						for(var i=0;i<gridData.length;i++){
							matchid = matchid + "," + gridData[i].itemid;
					    } 
						matchid = matchid.substr(1);
			        	   layer.open({
								type:2,
								title:'作业单打印',
								maxmin:true,
								area:['90%','90%'],
								content:'<c:url value="/print/workbillbeforeprint.do"/>?matchid=' + matchid + '&usermemo=' + usermemo
							});
					 }else{
						 errorbox("请选择打印信息")
					 }
				}else{
					var selectedrows = $('#StoreinGrid').bootstrapTable('getAllSelections');
					if(selectedrows.length > 0){
						var matchids = '';
						for(var i=0;i<selectedrows.length;i++){
							matchids = matchids + "," + selectedrows[i].matchid;
						}
						matchids = matchids.substr(1);
						layer.open({
							type:2,
							title:'作业单打印',
							maxmin:true,
							area:['90%','90%'],
							content:'<c:url value="/print/workbillprint.do"/>?matchid=' + matchids
						});
					}else{
						errorbox("请选择收货信息！");
					}
				}
			}
		
		function queryRTypeBycode(){
			$.ajax({
	            type: "post",
	            url: '<c:url value="/bcommon/queryRTypeBycode.do"/>',
	            dataType: "json",
	            data: {workpointcode:$("#unitcodes").val()}, 
	            success: function(data){
	            	  if(data.more != null && data.more.ictype != null && data.more.ictype != ''){
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
			loadGridData('StoreinGrid',"<c:url value='/storein/queryPage.do'/>");
		}
		function operateFormatter(value, row, index) {
			return [
                    '<div class="pull-right">',                   	
                   	'<t:ibutton text="移除" modulecode="RuKuGuanLi" id="storeinremove" btnclass="remove" iconclass="glyphicon glyphicon-trash"/>',
                   	'</div>'
               	].join('');			
		}
		window.operateEvents = {
			'click .remove' : function(e, value, row) {
				cancelStorein(row.matchid);
			}
		};
		$('#StoreinBtn').on('click', function() {
			openStoreinWindow(-1);
		});
		
		
		function openStoreinWindow(id_vv) {
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
		    		        			$($('#StoreinWindow form :input[name="id"]')).val(id_vv);
		    		        			$('#StoreinWindow').modal('show'); 
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
			$($('#StoreinWindow form :input[name="id"]')).val(id_vv);
			$('#StoreinWindow').modal('show');  */
		}
		
		/*打开发卡页面*/		
		function onFindICard(data){//打卡读卡器进行哪些操作
			$('#SaddBtn').prop('disabled', false); 
    		$('#StoreinfinishBtn').prop('disabled', false);
    		var unitcode = $("#unitcode").val();
			    $($('#StoreinForm input[name="cardid"]')).val(data.cardid);
				$.ajax({
		            type: "post",
		            url: '<c:url value="/bcommon/judgCarno.do"/>',
		            dataType: "json",
		            data: {cardid:data.cardid}, 
		            success: function(data){
		            	 if(data.success){//卡和车辆状态正常，根据车号查询业务信息		
		            		 var carno = data.rows[0].carno;
		            		$('#StoreinWindow form :input[name="carno"]').val(carno);  
		            		$('#StoreininfoGrid').bootstrapTable({
		            			onLoadSuccess: function (data1){
		            				if($('#StoreininfoGrid').bootstrapTable('getData').length==0){
		    		            			$.ajax({
		    		        		            type: "post",
		    		        		            url: "<c:url value='/storein/queryInfoBycarnoNotcode.do?carno='/>"+carno+"&unitcode="+unitcode,
		    		        		            dataType: "json",
		    		         		            success: function(data){
		    		        		            	 /* if(!data.success){
		    		        		            		errorbox(data.msg);
		    		        		            	}  */
		    		        		            }
		    		        		         });
		    		            		} 
		            			}}).bootstrapTable('refresh', {url : "<c:url value='/storein/queryInfoBycarno.do?carno='/>"+carno+"&unitcode="+unitcode});
		            		
		            		validForm('StoreinForm');
		            	 }else{//如果车辆或者卡有问题，系统提示
		            		errorbox(data.msg); 
		            		$('#SaddBtn').prop('disabled', true); 
		            		$('#StoreinfinishBtn').prop('disabled', true);
		            	 } 
		            }
	            }); 
				
			
		}
		
		function onDropICard(data){//关闭读卡器，进行哪些操作
			$('#StoreinForm input[name="cardid"]').val('');
			$('#StoreinForm input[name="carno"]').val('');
			$('#StoreinForm input[name="usermemo"]').val('');
			$('#StoreinForm input[name="matchid"]').val('');
			$('#StoreininfoGrid').bootstrapTable('removeAll');
			$('#SaddBtn').prop('disabled', false);
			$('#StoreinfinishBtn').prop('disabled', false);
			validForm('StoreinForm');
		}
		$('#StoreinWindow').on('hide.bs.modal', function() {	 //页面隐藏时，关闭读卡器		
			 CloseMediaReader(cardType,function(data){//关闭读卡器				
			}); 
		});
		/*打开入库页面*/
		$('#StoreinWindow').on('shown.bs.modal',function() {
			$('#SaddBtn').prop('disabled', false); 
    		$('#StoreinfinishBtn').prop('disabled', false);
			loadFormData('StoreinForm','<c:url value="/storein/queryStorein.do?unitcode="/>'+$("#unitcode").val()+'&unitname='+$("#unitname").val(),function(data){	
				 OpenMediaReader(cardType,onFindICard,onDropICard,function(data){//第一个参数是读卡器型号、第二个是打开卡操作，第三个参数是关闭读卡器操作
					
				 });
			});
		});
		function stateFormatter(value, row, index) {			
	        if(row.gross == 0){
	        	if(row.operatype=='91'||row.operatype=='93'){
	        		if (index === 0) {
			        	$("#matchids").val(row.matchid);
			        	return '<center><input name="gross" type="checkbox" onclick="putmatchid(\''+row.matchid+'\')" checked ><center>';
					}else{
						return '<center><input name="gross" type="checkbox" onclick="putmatchid(\''+row.matchid+'\')"  ><center>';
					}
	        	}else{
	        		return '';
	        	}		       
		    }else{
		       if (index === 0) {
		        	$("#matchids").val(row.matchid);
		        	return '<center><input name="gross" type="checkbox" onclick="putmatchid(\''+row.matchid+'\')" checked ><center>';
				}else{
					return '<center><input name="gross" type="checkbox" onclick="putmatchid(\''+row.matchid+'\')"  ><center>';
				}	
		    }
		}
		//选中的信息存放到
		function putmatchid(matchid){
			$("#matchids").val(matchid);	
		}
		/**
	    *添加入库信息
		*/
		function insert(){
			$('#SaddBtn').prop('disabled', true); 
			$('#StoreinfinishBtn').prop('disabled', true);  
			var gridData = $('#StoreininfoGrid').bootstrapTable('getSelections');
			var matchid = $("#matchids").val();
			var unitcode = $("#unitcode").val();
		   var carno = $('#StoreinForm input[name="carno"]').val();	
			 if (gridData.length>0) {
				 var flag=0;
				 var cflag=0;
				for(var i=0;i<gridData.length;i++){
					 if(i>0){
						 if(gridData[i].matchid!=gridData[i-1].matchid){							 
							 flag++;
						 }
					 }
					/*  var isor=gridData[i].isormeasure
					 if(isor==0){
						 if(gridData[i].counts==null || gridData[i].counts=='' ){
							   cflag++;
						 }
					} */
			     } 
				if(flag==0&&cflag==0){
					
					$.ajax({
    		            type: "post",
    		            url: "<c:url value='/storein/queryInfoBycarnoNotcode.do?carno='/>"+carno+"&storecode="+unitcode,
    		            dataType: "json",
     		            success: function(data){
    		            	 if(!data.success){
    		            		errorbox(data.msg);
    		            	} else{
    		            		saveFormDataWithParams('StoreinForm','<c:url value="/storein/beforeinsertStorein.do"/>',gridData,function(data) {
    								 if(data.mfunc==0){
    									 if(data.success){
    						             		successbox(data.msg);    
    						        			$('#StoreinForm input[name="cardid"]').val('');
    						        			$('#StoreinForm input[name="carno"]').val('');
    						        			$('#StoreinForm input[name="usermemo"]').val(''); 
    						        			$('#StoreinForm input[name="matchid"]').val('');
    						        			$('#StoreinGrid').bootstrapTable('refresh');	
    						        			$('#StoreininfoGrid').bootstrapTable('removeAll');
    						             	}else{
    						             		errorbox(data.msg);			             		
    						             	}
    									 $('#SaddBtn').prop('disabled', false); 
    									 $('#StoreinfinishBtn').prop('disabled', false);
    								 }else if(data.mfunc==3){ //为3时判断里面有一个禁止添加取样信息
    									 errorbox(data.msg);
    								 }else if(data.mfunc==2){//为2时用户选择是否添加取样信息
    									 dialogbox("请确认", data.msg,function(data){
    										if(data){
    											saveFormDataWithParams('StoreinForm','<c:url value="/storein/insertStorein.do"/>',gridData,function(data) {
    												 if (data.success) {
    													successbox(data.msg);
    								             		$('#StoreinForm input[name="cardid"]').val('');
    								        			$('#StoreinForm input[name="carno"]').val('');
    								        			$('#StoreinForm input[name="usermemo"]').val('');
    								        			$('#StoreinForm input[name="matchid"]').val('');
    								        			$('#StoreinGrid').bootstrapTable('refresh');
    								                	$('#StoreininfoGrid').bootstrapTable('removeAll');
    												} else {
    													errorbox(data.msg);
    													$('#SaddBtn').prop('disabled', false); 
    													$('#StoreinfinishBtn').prop('disabled', false);
    												} 
    												   
    											});	
    										}
    									});
    								 }
    							 });
    		            	} 
    		            }
    		         });
		
				}else if(flag>0&&cflag==0){
					errorbox("请选相同物流号信息")
	
				}else if(flag==0&&cflag>0){
					errorbox("请填写入库数量")
					
				}else{
					errorbox("请选相同物流号信息并填写入库数量")
					
				}	
			 }else{
				warningbox("请选择入库信息");
				$('#SaddBtn').prop('disabled', false); 
				$('#StoreinfinishBtn').prop('disabled', false);
			} 
		}
		  /**
		    *终止业务信息，车辆信息修改为强制终止
			*/
			
		function insertforce() {
			$('#SaddBtn').prop('disabled', true); 
			$('#StoreinfinishBtn').prop('disabled', true); 
			$("#stoplink").val("SIN");
			var gridData = $('#StoreininfoGrid').bootstrapTable('getSelections');
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
							saveFormData('StoreinForm','<c:url value="/bcommon/forcestop.do"/>',function(data) {
								if (data.success) {
									successbox(data.msg);
									$('#StoreinWindow').modal('toggle');
									$('#StoreinGrid').bootstrapTable('refresh');
									$('#StoreinForm input[name="matchid"]').val('');
								} else {
									errorbox(data.msg);
									$('#SaddBtn').prop('disabled', false); 
									$('#StoreinfinishBtn').prop('disabled', false);
									
								}
								 
							});
						}else{
							$('#SaddBtn').prop('disabled', false); 
							$('#StoreinfinishBtn').prop('disabled', false); 
						}
					});
				}else{
					errorbox("请选择相同物流号的信息");
				}
				
			} else {
				errorbox("请选择需要终止的业务");
			}
		}
		/**
		 *作废入库信息
		 */
		function cancelStorein(matchid) {
			dialogbox("请确认", "确认删除此项目？", function(data) {
				if (data) {
					$.ajax({
						type : "post",
						url : '<c:url value="/storein/cancelStorein.do"/>',
						dataType : "json",
						data : {matchid : matchid},
						success : function(data) {
							if (data.success == true) {
								successbox(data.msg);
								$('#StoreinGrid').bootstrapTable('refresh');
							} else {
								errorbox(data.msg);
							}
						}
					});
				}
			});
		}
		/* $('#StoreinGrid').bootstrapTable({
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
		
		function showFormatter(value, row, index) {	
        	if(row.operatype=='91'||row.operatype=='93'){
        		if (index === 0) {
		        	return '<center><input name="gross" type="radio" onclick="putmatchid(\''+row.matchid+'\')" checked ><center>';
				}else{
					return '<center><input name="gross" type="radio" onclick="putmatchid(\''+row.matchid+'\')"  ><center>';
				}
        	}else{
        		return '';
        	}
		}
		
		function opendf(){
			var carno = $('#StoreinWindow form :input[name="carno"]').val();
    		$('#StoreininfoGrid').bootstrapTable('refresh', {url : "<c:url value='/storein/queryInfoBycarno.do?carno='/>"+carno});
		}
     /* ---------------------------------------异常入库-------------------------------------------------------------- */		
		/**
		异常入库按钮
		*/
		$('#StoreinEXBtn').on('click', function() {
			openStoreinEXWindow(-1);
		});
		/**
		 异常入库
		*/
		function openStoreinEXWindow(id_vv) {
			 currentId = id_vv;
			$($('#StoreinEXWindow form :input[name="id"]')).val(id_vv);
			$('#StoreinEXWindow').modal('show');
		}	
		/*打开异常入库页面*/
		$('#StoreinEXWindow').on('shown.bs.modal',function() {
			/* $('#SaddBtn').prop('disabled', false); 
    		$('#StoreinfinishBtn').prop('disabled', false); */
    		var unitcode=$('#StoreinEXWindow form :input[name="unitcode"]').val();
    		var unitname=$('#StoreinEXWindow form :input[name="unitname"]').val();
			loadFormData('StoreinEXForm','<c:url value="/storein/queryStorein.do?unitcode="/>'+unitcode+'&unitname='+unitname,function(data){
				$("#exceptionflag").val("1");
			});
		});
		function getinfobycarno(carno){
			var unitcode=$('#StoreinEXWindow form :input[name="unitcode"]').val();
			$.ajax({
	            type: "post",
	            url: '<c:url value="/bcommon/judgOrBlackCarno.do"/>',
	            dataType: "json",
	            data: {carno:carno}, 
	            success: function(data){
	            	 if(data.success){//卡和车辆状态正常，根据车号查询业务信息	
	            		 $('#StoreinEXWindow form :input[name="cardid"]').val(data.data)
	            		 $('#StoreininfoEXGrid').bootstrapTable('refresh', {url : "<c:url value='/storein/queryInfoBycarno.do?carno='/>"+carno+"&unitcode="+unitcode});
	             		validForm('StoreinEXForm');
	            	 }else{//如果车辆或者卡有问题，系统提示
	            		errorbox(data.msg); 
	            		$('#SaddEXBtn').prop('disabled', true); 
	            	 } 
	            }
            }); 
			
		}
		
		function printEx(){
				var usermemo = $('#StoreinEXForm input[name="usermemo"]').val();
				var gridData = $('#StoreininfoEXGrid').bootstrapTable('getSelections');
				var matchid = '';
				 if (gridData.length>0) {
					 var flag=0;
					for(var i=0;i<gridData.length;i++){
						matchid = matchid + "," + gridData[i].itemid;
				    } 
					matchid = matchid.substr(1);
		        	   layer.open({
							type:2,
							title:'作业单打印',
							maxmin:true,
							area:['90%','90%'],
							content:'<c:url value="/print/workbillbeforeprint.do"/>?matchid=' + matchid + '&usermemo=' + usermemo
						});
				 }else{
					 errorbox("请选择打印信息")
				 }
		}
		/**
		    *添加异常入库信息
			*/
			function insertEX(){
				$('#SaddEXBtn').prop('disabled', true); 
				$("#exceptionflag").val("1");
				var gridData = $('#StoreininfoEXGrid').bootstrapTable('getSelections');
				//var matchid = $("#matchids").val();
				var unitcode = $('#StoreinEXForm input[name="unitcode"]').val();
			   var carno = $('#StoreinEXForm input[name="carno"]').val();	
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
						$.ajax({
	    		            type: "post",
	    		            url: "<c:url value='/storein/queryInfoBycarnoNotcode.do?carno='/>"+carno+"&storecode="+unitcode,
	    		            dataType: "json",
	     		            success: function(data){
	    		            	 if(!data.success){
	    		            		errorbox(data.msg);
	    		            	} else{
	    		            		saveFormDataWithParams('StoreinEXForm','<c:url value="/storein/beforeinsertStorein.do"/>',gridData,function(data) {
	    								 if(data.mfunc==0){
	    									 if(data.success){
	    						             		successbox(data.msg);    
	    						        			$('#StoreinEXForm input[name="cardid"]').val('');
	    						        			$('#StoreinEXForm input[name="carno"]').val('');
	    						        			$('#StoreinEXForm input[name="usermemo"]').val(''); 
	    						        			$('#StoreinEXForm input[name="matchid"]').val('');
	    						        			$('#StoreinGrid').bootstrapTable('refresh');	
	    						        			$('#StoreininfoEXGrid').bootstrapTable('removeAll');
	    						             	}else{
	    						             		errorbox(data.msg);			             		
	    						             	}
	    									 $('#SaddEXBtn').prop('disabled', false); 
	    								 }else if(data.mfunc==3){ //为3时判断里面有一个禁止添加取样信息
	    									 errorbox(data.msg);
	    								 }else if(data.mfunc==2){//为2时用户选择是否添加取样信息
	    									 dialogbox("请确认", data.msg,function(data){
	    										if(data){
	    											saveFormDataWithParams('StoreinEXForm','<c:url value="/storein/insertStorein.do"/>',gridData,function(data) {
	    												 if (data.success) {
	    													successbox(data.msg);
	    								             		$('#StoreinEXForm input[name="cardid"]').val('');
	    								        			$('#StoreinEXForm input[name="carno"]').val('');
	    								        			$('#StoreinEXForm input[name="usermemo"]').val('');
	    								        			$('#StoreinEXForm input[name="matchid"]').val('');
	    								        			$('#StoreinGrid').bootstrapTable('refresh');
	    								                	$('#StoreininfoEXGrid').bootstrapTable('removeAll');
	    												} else {
	    													errorbox(data.msg);
	    													$('#SaddEXBtn').prop('disabled', false); 
	    												} 
	    											});	
	    										}
	    									});
	    								 }
	    							 });
	    		            	} 
	    		            }
	    		         });
			
					}else{
						errorbox("请选相同物流号信息")		
					}
					$('#SaddEXBtn').prop('disabled', false);
				 }else{
					warningbox("请选择入库信息");
					$('#SaddEXBtn').prop('disabled', false); 
				} 
			}

			function exportExcel() {
				remoteExportExcel('StoreinGrid','', '', 'queryform');
			}
	</script>
</body>
</t:html>