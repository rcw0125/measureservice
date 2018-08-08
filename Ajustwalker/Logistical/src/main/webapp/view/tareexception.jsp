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
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
	SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
	Date now = new Date();
	Calendar c = Calendar.getInstance();
	now = c.getTime();
	String begintime = dateFormat.format(now);//开始时间
	String endtime = dateFormat1.format(now);//结束时间
%>
</head>
<body class="container-fluid" style="padding-top: 15px">
<div class="row">
		<div class="col-sm-12">
	<form id="queryform">
		<div class="row">
			<div class="col-sm-3">
				<div class='form-group input-group input-group-sm date'>
					<span class="input-group-addon">开始时间</span> <input type='text'
						class="form-control" placeholder="开始时间" id="begintime"
						name="begintime" value="<%=begintime%>" />
				</div>
			</div>
			<div class="col-sm-3">
				<div class='form-group input-group input-group-sm date'>
					<span class="input-group-addon">结束时间</span> <input type='text'
						class="form-control" placeholder="结束时间" id="endtime"
						name="endtime" value="<%=endtime%>" />
				</div>
			</div>
			<div class="col-sm-3">
				<div class="form-group input-group input-group-sm">
					<span class="input-group-addon" id="sizing-addon3">车号</span> <input
						type="text" class="form-control" placeholder="车号" id="carno"
						name="carno" aria-describedby="sizing-addon3">
				</div>
			</div>
			<div class="col-sm-3 btn-group-sm" >
			<!-- 	<t:button text="查询" modulecode="BenDiPiZhongYiChang" id="query" btnclass="btn btn-info" iconclass="glyphicon glyphicon-search" onclick="queryinfo()" />
			 -->
			    <button id="query" type="button" class="btn btn-info" onclick="queryinfo()">
					<span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;查询
				</button>
			 </div>
		</div>
	</form>
	</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<table id="TareinfoGrid" data-toggle="table"
				data-query-params="queryParams" data-row-style="rowStyle"
				data-pagination="true" data-page-list="[10, 20,  ALL]"
				data-side-pagination="server">
				<thead>
					<tr>
						<th data-field="carno" class="text-nowrap text-center" data-halign="center" sortable>车号</th>
						<th data-field="tare" data-halign="center"
							class="text-nowrap text-center" sortable>皮重/t</th>
						<th data-field="taretime" data-halign="center"
							class="text-nowrap text-center">皮重时间</th>
						<th data-field="tareweigh" data-halign="center"
							class="text-nowrap text-center" data-searchable="true">皮重衡器</th>
						<th data-field="tareoperaname" data-halign="center"
							class="text-nowrap text-center" class="text-nowrap">皮重计量员</th>
							<th data-width="70px" data-align="center" data-valign="middle"
							data-formatter="operateFormatter" data-events="operateEvents">操作</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
    <!-- -----------------------------------------    皮重修改     ------------------------------- -->
    <div class="modal fade" id="TareWindow" tabindex="-1" role="dialog" aria-labelledby="TareWindowLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">皮重信息</h4>
				</div>
				<div class="modal-body">
					<form id="TareForm">
						<div class="row">
						    <div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">
										物　流　号
									</span>
									<input  name="matchid" type="text"  class="form-control" value="" aria-describedby="basic-addon1" readonly="readonly">
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">
										车　　　号
									</span>
									<input id="carnos" name="carno" type="text"  class="form-control" readonly="readonly" aria-describedby="basic-addon1">
								</div>
							</div>
						</div>
						
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">皮 &nbsp;　　重/t</span>
									<input id="tare" name="tare" class="form-control" aria-describedby="basic-addon1"  type="text" required="required" data-bv-numeric data-bv-numeric-message="请输入数字">
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">皮&nbsp;重&nbsp;时&nbsp;间&nbsp;</span>
									<input name="taretime" id="taretime" type="text" class="form-control" aria-describedby="basic-addon1" required="required" >
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
							    <input name="tareweigh" id="tareweigh" type="hidden" class="form-control" value="">
								<t:combobox id="tareweighids" name="tareweighid" url="/bcommon/queryEqunameinfo.do" label="皮&nbsp;重&nbsp;衡&nbsp;器&nbsp;" require="true" allowclear="true"  />
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">皮重计量员</span>
									 <input	name="tareoperaname" type="text" class="form-control"	aria-describedby="basic-addon1" required="required">
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
    
    
	<script>
		jQuery(document).ready(function($) {
			queryinfo();

		});
		function queryinfo() {
			$('#TareinfoGrid').bootstrapTable('refresh', {
				url : "<c:url value='/exception/queryTareException.do'/>"
			});
		}

		$(function() {
			$('#begintime,#endtime,#taretime').datetimepicker({
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
		function operateFormatter(value, row, index) {
			/* return [
	                '<div class="pull-right">',
	               	'<t:ibutton text="修改" modulecode="BenDiPiZhongYiChang" id="taremodify" btnclass="edit" iconclass="glyphicon glyphicon-pencil"/>',
	               	'<t:ibutton text="移除" modulecode="BenDiPiZhongYiChang" id="tareremove" btnclass="remove" iconclass="glyphicon glyphicon-trash"/>',
	               	'</div>'
	           	].join('');	 */	
			 return [ '<div class="pull-right">',
						'<a class="edit" href="javascript:void(0)" title="修改">',
						'<i class="glyphicon glyphicon-pencil"></i>', '</a>　',
						'<a class="remove" href="javascript:void(0)" title="移除">',
						'<i class="glyphicon glyphicon-trash"></i>', '</a>',
						'</div>' ].join('');
			
		}
		window.operateEvents = {
				'click .edit' : function(e, value, row) {
					openWindow('TareWindow','TareForm','<c:url value="/exception/queryTareBycarno.do"/>?carno=' + row.carno);
				},
				'click .remove' : function(e, value, row) {
					cancelTare(row.carno);
				}
		};

			function stypeFormatter(value, row, index) {
				return [ '<div class="text-center">',
						'<a class="edits" href="javascript:void(0)" title="修改">',
						'<i class="glyphicon glyphicon-minus"></i>', '</a>　',
						'</div>' ].join('');
			}


			$('#TaskcodeWindow').on('shown.bs.modal',function() {
				var id = $("#id").val();
				loadFormData('TaskcodeForm','<c:url value="/taskcode/queryInfoBytaskcode.do?id="/>'+id )
		    });

			/**
			 *作废业务号信息
			 */
			function cancelTare(carno) {
		        dialogbox("请确认", "确认删除此项目？",function(data){
						if(data){
							$.ajax({
									type : "post",
									url : '<c:url value="/exception/cancelTareException.do"/>',
									dataType : "json",
									data : {carno : carno},
									success : function(data) {
									if (data.success == true) {
										successbox(data.msg);
										$('#TareinfoGrid').bootstrapTable('refresh');
									} else {
										errorbox(data.msg);
								   }
							}
						});
					}	
				});
			
			}

		function insert() {	
			$("#tareweigh").val($('#tareweighids option:selected').text()) 
				saveFormData('TareForm','<c:url value="/exception/updateTareException.do"/>',function(data){
					 if (data.success) {
						successbox(data.msg);
						$('#TareWindow').modal('toggle');
						$('#TareinfoGrid').bootstrapTable('refresh');
					} else {
						errorbox(data.msg);
					} 
				});
			}
	</script>
</body>
</t:html>