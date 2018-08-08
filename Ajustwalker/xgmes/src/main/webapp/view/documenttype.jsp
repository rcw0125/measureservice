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
<script type="text/javascript"
	src="<c:url value='/plugins/datatable/extensions/editable/bootstrap-table-editable.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/plugins/datatable/extensions/popupedit/bootstrap-table-popupedit.js'/>"></script>
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
<body class="container-fluid" style="padding-top: 15px">
	<div class="row">
		<div class="col-sm-12">
			<form id="queryform">
				<div class="row">
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon">类型名称</span> 
							<input type="text" class="form-control" placeholder="类型名称" id="documentname" name="documentname" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">物资流向</span> 
							<select name="materiallflow" class="form-control select2">
								<option value="-1">全部</option>
								<option value="1">进厂</option>
								<option value="2">出厂</option>
								</select>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">审核等级</span> 
							<select name="auditlevel" class="form-control select2">
								<option value="-1">全部</option>
								<option value="0">无需审核</option>
								<option value="1">一级审核</option>
<!-- 								<option value="2">二级审核</option> -->
							</select>	
						</div>
					</div>
				<div class="col-sm-3">
						<div class="btn-group-sm">
							<button id="query" type="button" class="btn btn-info" onclick="queryinfo()">
								<span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;查询
							</button>
							<button id="add" type="button" class="btn btn-success">
								<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;添加
							</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<table id="DocumentInfoGrid" 
			       data-toggle="table"
				   data-row-style="rowStyle" 
				   data-query-params="queryParams"
				   data-pagination="true" 
				   data-page-size="10"
				   data-page-list="[10,40,ALL]"
				   data-mobile-responsive="true">
				<thead>
					<tr>
						<th data-field="id" data-visible="false" ></th>
						<th data-field="documentcode" data-halign="center"
							data-searchable="true" class="text-center" sortable>单据类型编码</th>
						<th data-field="documentname" class="text-left"
							data-halign="center" data-searchable="true" sortable>单据类型名称</th>
						<th data-field="materiallflow" data-halign="center"
							data-searchable="true" class="text-nowrap text-center">流向</th>
						<th data-field="worklinename" data-halign="center"
							data-searchable="true" class="text-nowrap text-center">路线名称</th>
						<th data-field="fdocumentcode" data-halign="center"
							data-searchable="true" class="text-nowrap text-center">原始单据编码</th>
						<th data-field="fdocumentname" data-halign="center"
							data-searchable="true" class="text-nowrap text-center">原始单据名称</th>
						<th data-field="auditlevel" data-halign="center"
							data-searchable="true" class="text-nowrap text-center">审核等级</th>
						<th data-field="creator" data-halign="center"
							data-searchable="true" class="text-nowrap text-center">创建人</th>	
						<th data-field="cdate" data-halign="center"
							data-searchable="true" class="text-nowrap text-center">创建时间</th>	
						<th data-width="70px" data-align="center" data-valign="middle"
							data-formatter="operateFormatter" data-events="operateEvents">操作</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>

	<div class="modal fade" id="DocumenttypeWindow" tabindex="-1" role="dialog"	aria-labelledby="DocumenttypeWindowLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">单据类型操作</h4>
				</div>
				<div class="modal-body">
					<form id="DocumenttypeForm">
					<input type="hidden" id="id" name="id" value="-1" />
						<div class="row">
						<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon" id="sizing-addon3">类型名称</span>
									<input id="documentname" name="documentname"  class="form-control text-nowrap" aria-describedby="basic-addon1" required data-bv-notempty-message="请填写单据名称">
								</div>
							</div>
							 	<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon" id="sizing-addon3">物资流向</span> 
									<select name="materiallflow" class="form-control select2" required data-bv-notempty-message="请选择物资流向">
										<option value="1">进厂</option>
										<option value="2">出厂</option>
									</select>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">		 
									<input id="worklinename" name="worklinename" type="hidden" class="form-control text-nowrap" aria-describedby="basic-addon1" >
									<t:combobox id="worklinecode" name="worklinecode"  url="/bcommon/queryWorkline.do" label="线路名称" require="true" pagination="true"/>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">审核等级</span> 
									<select name="auditlevel" class="form-control select2" required data-bv-notempty-message="请选择审核等级">
										<option value="0">无需审核</option>
										<option value="1">一级审核</option>
								<!-- 		<option value="2">二级审核</option> -->
									</select>
								</div>
							</div>
							</div>
							<div class="row">
							<div class="col-sm-6">						
									 <input id="fdocumentname" name="fdocumentname" type="hidden" class="form-control" aria-describedby="basic-addon1" >	
									 <t:combobox id="fdocumentcode" name="fdocumentcode"  url="/bcommon/queryDtype.do" label="原始单据" require="false"/>
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
				</div>
				<div class="modal-footer  btn-group-sm">
					<button type="button" class="btn btn-success" id="addBtn" onclick="insert()">确认</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		
		jQuery(document).ready(function($) {
			queryinfo()
		});


		function queryParams(params) {
			return jQuery.extend({}, params, $('#queryform').serializeJson());
		}

		function queryinfo() {
			$('#DocumentInfoGrid').bootstrapTable('refresh', {
				url : "<c:url value='/documenttype/queryPage.do'/>"
			});
		}

	
		function operateFormatter(value, row, index) {
			return [ '<div class="pull-right">',
					'<a class="edit" href="javascript:void(0)" title="修改">',
					'<i class="glyphicon glyphicon-pencil"></i>', '</a>　',
					'<a class="remove" href="javascript:void(0)" title="移除">',
					'<i class="glyphicon glyphicon-trash"></i>', '</a>',
					'</div>' ].join('');
		}

		window.operateEvents = {
			'click .edit' : function(e, value, row) {
				openWindow('DocumenttypeWindow','DocumenttypeForm','<c:url value="/documenttype/queryInfoByid.do"/>?id=' + row.id);
			},
			'click .remove' : function(e, value, row) {
				cancelTaskcode(row.id);
			}
		};

		function stypeFormatter(value, row, index) {
			return [ '<div class="text-center">',
					'<a class="edits" href="javascript:void(0)" title="修改">',
					'<i class="glyphicon glyphicon-minus"></i>', '</a>　',
					'</div>' ].join('');
		}

		$('#add').on('click', function() {
			openWindow('DocumenttypeWindow','DocumenttypeForm','<c:url value="/documenttype/queryInfoByid.do"/>?id=-1');
		});


		$('#DocumenttypeWindow').on('shown.bs.modal',function() {
			var id = $("#id").val();
			loadFormData('DocumenttypeForm','<c:url value="/documenttype/queryInfoByid.do?id="/>'+id )
	});

		/**
		 *作废业务号信息
		 */
		function cancelTaskcode(id) {
	        dialogbox("请确认", "确认删除此项目？",function(data){
					if(data){
						$.ajax({
								type : "post",
								url : '<c:url value="/documenttype/cancelDtype.do"/>',
								dataType : "json",
								data : {id : id},
								success : function(data) {
								if (data.success == true) {
									successbox(data.msg);
									$('#DocumentInfoGrid').bootstrapTable('refresh');
								} else {
									errorbox(data.msg);
							   }
						}
					});
				}	
			});
		
		}

	function insert() {			
		//$("#sourcenames").val($('#sourcecode option:selected').text())
			$("#worklinename").val($('#worklinecode option:selected').text())
			$("#fdocumentname").val($('#fdocumentcode option:selected').text())		 
			saveFormData('DocumenttypeForm','<c:url value="/documenttype/insertDocumenttype.do"/>',function(data){
				 if (data.success) {
					successbox(data.msg);
					$('#DocumenttypeWindow').modal('toggle');
					$('#DocumentInfoGrid').bootstrapTable('refresh');
				} else {
					errorbox(data.msg);
				} 
			});
		}
	</script>
</body>
</t:html>






