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
					<input type="hidden" id="operatypes" name="operatype"  value="${operatype}">	
					<input type="hidden" name="secondflag"  value="${secondflag}">
					<input type="hidden" id="types"  name="types" value="1">	
					<div class="row">
						<div class="col-sm-3">
							<div class='form-group input-group input-group-sm date'>
								<span class="input-group-addon">开始时间</span>
								<input type='text' class="form-control" placeholder="开始时间" id="begintime" name="begintime" value="<%=begintime%>" />
							</div>
						</div>
						<div class="col-sm-2">
							<div class="form-group input-group input-group-sm">
								<span class="input-group-addon">单&nbsp;&nbsp;据&nbsp;&nbsp;号</span>
								<input type="text" class="form-control" placeholder="单据号" id="matchid" name="matchid" aria-describedby="sizing-addon3" >
							</div>
						</div>
						<div class="col-sm-2">
							<div class="form-group input-group input-group-sm">
								<span class="input-group-addon" id="sizing-addon3">供　　货</span>
								<input type="text" class="form-control" placeholder="供货"  name="sourcename" aria-describedby="sizing-addon3">
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
							<span class="input-group-addon" id="sizing-addon3">是否使用</span>
						    <select id="fendanflag" name="fendanflag" class="form-control select2">
								<option value="">全部</option>
								<option value="0">未使用</option>
								<option value="1">已使用</option>
							</select> 
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
								<span class="input-group-addon" id="sizing-addon3">车　　号</span>
								<input type="text" class="form-control" placeholder="车号" id="carno" name="carno" aria-describedby="sizing-addon3">
							</div>
						</div>
						<div class="col-sm-2">
							<div class="form-group input-group input-group-sm">
								<span class="input-group-addon" id="sizing-addon3">收　　货</span>
								<input type="text" class="form-control" placeholder="收货"  name="targetname" aria-describedby="sizing-addon3">
							</div>
						</div>
						<div class="col-sm-2">
							<t:combobox id="documentcode" name="documentcode"  url="/bcommon/queryDtype.do" require="false" allowclear="true" alloptions="true" label="业务类型"/>
						</div>
						<div class="form-group col-sm-1 btn-group-sm">
							<!-- <button id="query" type="button" class="btn btn-info" onclick="queryinfo()">
								<span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;查询
							</button> -->
							<t:button text="查询" modulecode="XuNiFaKa" id="query" btnclass="btn btn-info" iconclass="glyphicon glyphicon-search" onclick="queryinfo()" />						
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<table id="virtualInfoGrid" 
						data-toggle="table"
						data-row-style="rowStyle" 
						data-query-params="queryParams"
						data-pagination="true" 
						data-page-size="10"
						data-page-list="[10,40,ALL]" 
						data-mobile-responsive="true">
					<thead>
						<tr>				
							<th data-field="matchid" data-halign="center" data-searchable="true" class="text-center" sortable >单据号</th>
							<th data-field="itemid" data-visible="false"  >单据类型</th>
							<th data-field="operatype" class="text-center text-nowrap"  data-searchable="true"  sortable>单据类型</th>
							<th data-field="status" class="text-center text-nowrap"  data-searchable="true"  sortable>单据状态</th>							
							<th data-field="carno" data-halign="center" data-searchable="true" class="text-nowrap text-center">车号</th>
							<th data-field="sourcename" data-halign="center" data-searchable="true" class="text-nowrap text-left">供货</th>
							<th data-field="targetname" data-halign="center" data-searchable="true" class="text-nowrap text-left">收货</th>
							<th data-field="materialname" data-halign="center" data-searchable="true" class="text-nowrap text-left">货名</th>
							<th data-field="snumber" data-halign="center" data-searchable="true" class="text-right text-right">数量</th>
							<th data-field="auditlevel" data-halign="center" data-searchable="true" class="text-right text-right" data-visible="false">审核等级</th>
							<th data-field="approverfirst" data-halign="center" data-searchable="true" class="text-center">一级审核人</th>
							<th data-field="firsttime" data-halign="center" data-searchable="true" class="text-nowrap text-center">一级审核时间</th>
							<%-- <th data-field="approversecond" data-halign="center" data-searchable="true" class="text-nowrap text-center">二级审核人</th>
							<th data-field="secondtime" data-halign="center" data-searchable="true" class="text-nowrap text-center">二级审核时间</th>
							 --%><th data-field="memo" data-halign="center" data-searchable="true" class="text-nowrap">备注</th>
							<th data-width="70px" data-align="center" data-valign="middle" data-formatter="operateFormatter" data-events="operateEvents">操作</th>
							<th data-field="fendanflag" data-halign="center" data-searchable="true" class="text-nowrap" data-visible="false" >是否是虚拟已经</th>
							<th data-field="isoruse" data-halign="center" data-searchable="true" class="text-nowrap" data-visible="false">是否使用</th>
						</tr>
					</thead>
				</table>
			</div>	
		</div>
		 <div class="modal fade" id="MakeCardWindow" tabindex="-1" role="dialog" aria-labelledby="MakeCardWindowLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">虚拟信息</h4>
				</div>
				<div class="modal-body"> 
					<form id="MakeCardForm"> 
						<div class="row">
							<input type="hidden" id="matchids" name="matchid" value="-1" />
							<input type="hidden"   id="upplanidlist" name="upplanidlist"  >
				            <input type="hidden"   id="upitemidlist" name="upitemidlist"  >
				            <input type="hidden"    name="icid"  >							
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
								    <div class="input-group-addon">车　　号</div>
									<input  id="upcarno" name="carno" type="text" class="form-control" value=""  readonly="readonly" aria-describedby="basic-addon1" required data-bv-notempty-message="请填写车号">
								</div>
							</div>
							<div class="col-sm-6">
							    <div class="form-group input-group input-group-sm">
									<span class="input-group-addon">业务类型</span>
									<input  id="operatype" name="operatype"  type="hidden"  >
									<input  name="operaname" type="text" class="form-control" readonly="readonly" aria-describedby="basic-addon1">
								</div>
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
							<table id="MakeCardGrid" data-mobile-responsive="true" data-unique-id="itemid">
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

		<script type="text/javascript">
			var rowscount = 0;
			var operatype = $("#operatype").val();
	
			
			function deletestr(obj) {
				$('#MakeCardGrid').bootstrapTable('removeByUniqueId', obj);
			}
			
			function deletesFormatter(value,row,index){
				return "<div class='btn-group-sm'><button type='button' class='btn btn-danger'onclick='deletestr(\""+row.itemid+"\")'><span class='glyphicon glyphicon-minus' aria-hidden='true'></span></button></div>";
			}
			
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
				$("#DocumentDetailGrid input[name='returntime']").datetimepicker({
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
				$('#virtualInfoGrid').bootstrapTable('refresh', {
					url : "<c:url value='/exception/queryVirtualInfo.do'/>"
				});
			}
			function operateFormatter(value, row, index) {
				return [
	                    '<div class="pull-right">',
	                   	'<t:ibutton text="修改" modulecode="XuNiFaKa" id="virtualmodify" btnclass="edit" iconclass="glyphicon glyphicon-pencil"/>',
	                   	'<t:ibutton text="移除" modulecode="XuNiFaKa" id="virtualremove" btnclass="remove" iconclass="glyphicon glyphicon-trash"/>',
	                   	'</div>'
	               	].join('');		
			}
			
			window.operateEvents = {
				'click .edit' : function(e, value, row) {
					if(row.isoruse==0){
						$.ajax({
							type : "post",
							url : '<c:url value="/application/queryUncomplete.do"/>',
							dataType : "json",
							data : {carno : row.carno},
							success : function(data) {
								if(data.success){
									
									if(data.mfunc==0){
										openDocumentWindow(row.matchid,row.isoruse);
									}else if (data.mfunc>0){
										dialogbox("请确认",data.msg,function(data) {
											if (data) {
												openDocumentWindow(row.matchid,row.isoruse);
											}
										});
									}
								}else{
									errorbox(data.msg);
								}
							}
						});
						
						
						
					}else{
						errorbox("单据已经使用，不允许重新发卡");
					}
					
				},
				'click .remove' : function(e, value, row) {
					cancelDocument(row.matchid, row.fendanflag);
				}
			};
			
			function stypeFormatter(value, row, index) {
				return ['<div class="text-center">',
						'<a class="edits" href="javascript:void(0)" title="修改">',
						'<i class="glyphicon glyphicon-minus"></i>', '</a>　',
						'</div>' ].join('');
			}

			function openDocumentWindow(id_vv) {
			    $("#matchids").val(id_vv);
				$('#MakeCardWindow').modal('show');
			}
	
			$('#MakeCardWindow').on('shown.bs.modal',function() {
				var matchid = $("#matchids").val();
				$('#MakeCardGrid').bootstrapTable('removeAll');
				loadFormData('MakeCardForm','<c:url value="/exception/queryOneVirtualbymatchid.do?matchid="/>'+ matchid,	function(data) {
					$("#matchids").val(matchid);
					var operatype = $("#operatype").val();
					$.ajax({
							type : "post",
							url : '<c:url value="/bcommon/queryModelcontent.do?operatype="/>'+ operatype+"&flag=2",
							dataType : "json",
							success : function(data) {
								var cs = JSON.parse(data.msg);
								$('#MakeCardGrid').bootstrapTable('destroy').bootstrapTable({columns:cs}).bootstrapTable('refresh',{url : "<c:url value='/exception/queryVirtualbymatchid.do?matchid='/>"+ + matchid});
							
							}
					}); 
				});
			});
			
			//操作制卡信息		
			function insert() {
				var gridData = $('#MakeCardGrid').bootstrapTable('getData')
				var msginfo = '';
				if (gridData.length == 0) {
					errorbox("请选择业务信息 ");
				} else {
					for (var i = 0; i < gridData.length; i++) {
						if (gridData[i].planid == null || gridData[i].planid == ''|| gridData[i].planid == '请选择') {
							msginfo = msginfo + "第" + (i * 1 + 1) + "行，业务信息为空</br>";
						}
					}
					if (msginfo.length == 0) {
						saveFormDataWithParams('MakeCardForm','<c:url value="/exception/insertVApplicationbill.do"/>',gridData,function(data) {
							if (data.success) {
								successbox(data.msg);
								$('#MakeCardWindow').modal('toggle');
								$('#virtualInfoGrid').bootstrapTable('refresh');
							} else {
								errorbox(data.msg);
							}
						});
						
					} else {
						errorbox(msginfo);
					}
				}
			}

			/**
			 *作废单据信息
			 */
			function cancelDocument(matchid, fendanflag) {
				if (fendanflag==1) {
						dialogbox("请确认", "确认删除此项目？",function(data){
							if(data){
								$.ajax({
									type : "post",
									url : '<c:url value="/exception/cancelVApplicationbill.do"/>',
									dataType : "json",
									data : {matchid : matchid},
									success : function(data) {
										if (data.success == true) {
											successbox(data.msg);
											$('#virtualInfoGrid').bootstrapTable('refresh');
										} else {
											errorbox(data.msg);
										}
									}
								});
							}
					});
				}else{
					errorbox("非虚拟发卡信息，不允许作废");
				}
			}
			$('#virtualInfoGrid').bootstrapTable({onDblClickRow : function(row, $element) {
				layer.open({
					type : 2,
					title : '信息详情',
					maxmin : true, //开启最大化最小化按钮
					area : [ '90%', '90%' ],
					content : '<c:url value="/bcommon/showOtherDetail.do?type=1&ids="/>'+ row.itemid //注意，如果str是object，那么需要字符拼接。
				});
			}
		});

		</script>
	</body>
</t:html>






