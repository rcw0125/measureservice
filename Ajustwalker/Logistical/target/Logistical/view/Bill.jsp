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
				<%-- <input type="hidden"  name="typeflag" value="${typeflag}"> --%>
				<div class="row">
					<div class="col-sm-3">
						<div class='form-group input-group input-group-sm date'>
							<span class="input-group-addon">开始时间</span> <input type='text' class="form-control" placeholder="开始时间" id="begintime" name="begintime" value="<%=begintime%>" />
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon">单&nbsp;&nbsp;据&nbsp;&nbsp;号</span> <input type="text" class="form-control" placeholder="单据号" id="planid" name="planid" aria-describedby="sizing-addon3">
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
					<div class="col-sm-2">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">单据类型</span> 
							 <select id="typeflag" name="typeflag" class="form-control select2">
								<c:if test="${typeflag==1}">
								  <option value="1" selected="selected">到货单</option>
								</c:if>
								<c:if test="${typeflag==2}">
								  <option value="2" selected="selected">发运单</option>
								</c:if>
								<c:if test="${typeflag==-1}">
								  <option value="-1">全部</option>
								  <option value="1">到货单</option>
								  <option value="2">发运单</option>
								</c:if>
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
							</select>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-sm-6 btn-group-sm">
						<t:button text="查询" modulecode="bill" id="query" btnclass="btn btn-info" iconclass="glyphicon glyphicon-search" onclick="queryinfo()" />
						<t:button text="添加" modulecode="bill" id="BillBtn" btnclass="btn btn-success" iconclass="glyphicon glyphicon-th-large" />
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<table id="BillInfoGrid" data-toggle="table" data-row-style="rowStyle" data-query-params="queryParams" data-pagination="true" data-page-size="10" data-page-list="[10,40,ALL]" data-mobile-responsive="true" >
				<thead>
					<tr>
					    <th data-field="planid" data-halign="center"
							data-searchable="true"  class="text-nowrap" >单据号</th>
						<th data-field="id"  data-visible="false">行号</th>							
						<th data-field="carno" data-halign="center" data-searchable="true"
							class="text-nowrap" >车号</th>
						<th data-field="status" data-halign="center" data-searchable="true"
							class="text-nowrap" >状态</th>
						<th data-field="materialcode" data-halign="center"
							data-searchable="true" class="text-nowrap">货名编码</th>	
						<th data-field="materialname" data-halign="center"
							data-searchable="true" class="text-nowrap">货名</th>
						<th data-field="sourcename" data-halign="center"
							data-searchable="true" class="text-nowrap">供货</th>
						<th data-field="targetname" data-halign="center"
							data-searchable="true" class="text-nowrap">收货</th>	
						<th data-field="snumber" data-halign="center"
							data-searchable="true" class="text-nowrap">数量</th>
						<th data-field="measureunit" data-halign="center"
							data-searchable="true" class="text-nowrap">计量单位</th>
						<th data-field="arrivetime" data-halign="center"
							data-searchable="true" class="text-nowrap">到货时间</th>
						<th data-field="creator" data-halign="center"
							data-searchable="true" class="text-nowrap">制单人</th>
						<th data-field="createdate" data-halign="center"
							data-searchable="true" class="text-nowrap">制单时间</th>														
						<th data-field="usermemo" data-halign="center" data-searchable="true"
							class="text-nowrap">备注</th>
					    <th data-width="70px" data-align="center" data-valign="middle" data-formatter="operateFormatter" data-events="operateEvents">操作</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>

	<div class="modal fade" id="BillWindow" tabindex="-1" role="dialog" aria-labelledby="BillWindowLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">单据操作</h4>
				</div>
				<div class="modal-body">
					<form id="BillForm">
					    <input type="hidden" id="ids" name="id" value="-1" /> 
					    <input type="hidden" id="planids" name="planid" value="-1" /> 
					    <div class="row">
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon" id="sizing-addon3">单据类型</span>
									<select id="typeflag" name="typeflag" class="form-control select2">
										<c:if test="${typeflag==1}">
											<option value="1" selected="selected">到货单</option>
										</c:if>
										<c:if test="${typeflag==2}">
											<option value="2" selected="selected">发运单</option>
										</c:if>
										<c:if test="${typeflag==-1}">
											<option value="-1">全部</option>
											<option value="1">到货单</option>
											<option value="2">发运单</option>
										</c:if>
									</select>
								</div>
							</div>
							<c:if test="${typeflag==1 and reserve1=='汽运'}">
								<div class="col-sm-6">
									<div class="form-group input-group input-group-sm">
										<span class="input-group-addon">采购区域</span> 
										<input id="reserve2" name="reserve2" type="text" class="form-control" aria-describedby="basic-addon1" placeholder="采购区域" 　require="required">
									</div>
							    </div>
						   </c:if>
						   <c:if test="${typeflag==2 and reserve1=='汽运'}">
								<div class="col-sm-6">
									<div class="form-group input-group input-group-sm">
										<span class="input-group-addon">车　　号</span> 
										<input id="carno" name="carno" type="text" class="form-control" aria-describedby="basic-addon1" placeholder="车号" 　require="required">
									</div>
							    </div>
						   </c:if>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<!--<div class="form-group input-group input-group-sm">
									 <span class="input-group-addon">供　　货</span>  -->
									<input id="sourcename" name="sourcename" type="hidden" placeholder="供货" class="form-control" aria-describedby="basic-addon1" 　require="required">
									<t:combobox id="sourcecode" name="sourcecode"  url="/bcommon/querySourceinfo.do" label="供　　货" require="true" pagination="true" allowclear="true" alloptions="true" />
									 
								<!-- </div> -->
							</div>
							<div class="col-sm-6">
								<!--<div class="form-group input-group input-group-sm">
									 <span class="input-group-addon">收　　货</span>  -->
									<input id="targetname" name="targetname" type="hidden" placeholder="收货" class="form-control" aria-describedby="basic-addon1" 　require="required">
									<t:combobox id="targetcode" name="targetcode"  url="/bcommon/queryTargetinfo.do" label="收　　货" require="true" pagination="true" allowclear="true" alloptions="true" />
								     
								<!-- </div> -->
							</div>
						</div>
						<c:if test="${reserve1=='火运'}">
							<div class="row">
								<div class="col-sm-6">
									<div class="form-group input-group input-group-sm">
										<span class="input-group-addon">发　　站</span> 
										<input id="sourceplace" name="sourceplace" type="text" placeholder="发站" class="form-control" aria-describedby="basic-addon1" 　require="required">
									</div>
								</div>
								<div class="col-sm-6">
									<div class="form-group input-group input-group-sm">
										<span class="input-group-addon">到　　站</span> 
										<input id="targetplace" name="targetplace" type="text" placeholder="到站" class="form-control" aria-describedby="basic-addon1" 　require="required">
									</div>
								</div>
							</div>
						</c:if>
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">发运方式</span> 
									<select id="reserve1" name="reserve1" class="form-control select2">
									   <c:if test="${reserve1=='汽运'}">
									     <option value="汽运" selected="selected">汽运</option>
									   </c:if>
									   <c:if test="${reserve1=='火运'}">
									     <option value="火运" selected="selected">火运</option>
									   </c:if>
									</select>
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm date">
									<span class="input-group-addon">日　　期</span> <input type="text" class="form-control" placeholder="进/出厂日期" id="arrivetime" name="arrivetime" required="required" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">备　　注</span> <input name="memo" type="text" placeholder="备注" class="form-control" aria-describedby="basic-addon1">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<table id="BillDetailGrid" data-mobile-responsive="true" data-unique-id="saleitemid"></table>
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
	
	<div class="modal fade" id="showDetailBillWindow" tabindex="-1" role="dialog" aria-labelledby="BillWindowLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">单据明细</h4>
				</div>
				<div class="modal-body">
					<form id="showBillForm">
						<div class="row">
							<input type="hidden" id="showmatchids" name="matchid" value="-1" /> 
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
									<span class="input-group-addon" onclick="checkFdocument()">原始单据</span>
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
	<jsp:include page="/view/commonpage/materialinfo.jsp" flush="true" />
	<script type="text/javascript">
		var rowscount = 0;
		var operatype = $("#operatype").val();

		function prepend() {
			$('#BillDetailGrid').bootstrapTable('prepend', appendData());
		}
		function appendData() {
			var rows = [];
			rows.push({
				saleitemid : 'add' + rowscount,
				deletes : rowscount,
				carno : '',
				materialcode : '',
				materialname : '',
				materialspec : '',
				snumber : '',
				measureunit : '',
				steelname : '',
				middlemanname : '',
				usermemo : ''
			});
			rowscount++;
			return rows;
		}

		function deletestr(obj) {
			$('#BillDetailGrid').bootstrapTable('removeByUniqueId', obj);
		}
		function deletesFormatter(value, row, index) {
			return "<div class='btn-group-sm'><button type='button' class='btn btn-danger'onclick='deletestr(\""
					+ row.saleitemid
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
			$('#arrivetime').datetimepicker({
				format : 'YYYY-MM-DD',
				locale : 'zh-cn'
			});
		});

		function queryParams(params) {
			return jQuery.extend({}, params, $('#queryform').serializeJson());
		}

		function queryinfo() {
			$('#BillInfoGrid').bootstrapTable('refresh', {
				url : "<c:url value='/bill/queryPage.do'/>"
			});
		}

		function operateFormatter(value, row, index) {
			return [
					'<div class="pull-right" style="width:60px;">',
					'<t:ibutton text="修改" modulecode="otherdj" id="billmodify" btnclass="edit" iconclass="glyphicon glyphicon-pencil"/>',
					'<t:ibutton text="移除" modulecode="otherdj" id="billremove" btnclass="remove" iconclass="glyphicon glyphicon-trash"/>',
					'</div>' ].join('');
		}

		window.operateEvents = {
			'click .edit' : function(e, value, row) {
				openDocumentWindow(row.id,row.planid);
			},
			'click .remove' : function(e, value, row) {
				cancelBill(row.id);
			}
		};

		$('#BillBtn').on('click', function() {
			openDocumentWindow(-1,-1);
		});



		function openDocumentWindow(id_v,planid_v) {
			$("#ids").val(id_v);
			$("#planids").val(planid_v);
		    $('#BillWindow').modal('show');
		}
		$('#BillWindow').on('shown.bs.modal',function() {
			var id_v = $("#ids").val();
			var planid_v = $("#planids").val();
			var typeflag =$("#typeflag").val();
			var reserve1 = $("#reserve1").val();
			loadFormData('BillForm','<c:url value="/bill/queryMainBymatchid.do?planid="/>'+ planid_v+"&typeflag="+typeflag+"&reserve1="+reserve1 ,function(data) {
				$("#ids").val(id_v);
				$("#planids").val(planid_v);
				 $.ajax({
						type : "post",
						url : '<c:url value="/bcommon/queryModelcontent.do?operatype="/>'+ typeflag+ "&flag=3",
						dataType : "json",
						success : function(data) {
							var cs = JSON.parse(data.msg);
							$('#BillDetailGrid').bootstrapTable('destroy').bootstrapTable({columns : cs}).bootstrapTable('refresh',{url : "<c:url value='/bill/queryItemByFid.do?id='/>"+ id_v});
						}
				}); 
			 });
		});

		$('#BillInfoGrid').bootstrapTable({
			onDblClickRow : function(row, $element) {
				$("#showmatchids").val(row.matchid);
				$('#showDetailDocumentWindow').modal('show');
			}
		});

		

		$('#showDetailDocumentWindow').on('shown.bs.modal',function() {
			var matchid_v = $("#showmatchids").val();
			loadFormData('showDocumentForm','<c:url value="/application/queryApplicationbill.do?matchid="/>'+ matchid_v + "&typecode="+ $("#operatypes").val()+ "&type=document",
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
		function cancelBill(id) {
			dialogbox("请确认","确认删除此项目？",function(data) {
				if (data) {
					$.ajax({
						type : "post",
						url : '<c:url value="/bill/cancelBill.do"/>',
						dataType : "json",
						data : {id : id},
						success : function(data) {
							if (data.success == true) {
								successbox(data.msg);
								$('#BillInfoGrid').bootstrapTable('refresh');
							} else {
								errorbox(data.msg);
							}
						}
					});
				}
			});
		}
		function insert() {
			$("#sourcename").val($('#sourcecode option:selected').text())	
			$("#targetname").val($('#targetcode option:selected').text())	
			validForm('BillForm');
			var gridData = $('#BillDetailGrid').bootstrapTable('getData');			
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
					}
					if (gridData[i].measureunit == null|| gridData[i].measureunit == ''|| gridData[i].measureunit == '请选择') {
						msginfo = msginfo + "第" + (i * 1 + 1)+ "行，计量单位信息为空</br>";
					}
				}
				if (msginfo.length == 0) {
					  saveFormDataWithParams('BillForm','<c:url value="/bill/insertBill.do"/>',gridData, function(data) {
							if (data.success) {
								successbox(data.msg);
								$('#BillWindow').modal('toggle');
								$('#BillInfoGrid').bootstrapTable('refresh');
							} else {
								errorbox(data.msg);
							}
						});		
				} else {
					errorbox(msginfo);
				}

			}

		}
	</script>
</body>
</t:html>