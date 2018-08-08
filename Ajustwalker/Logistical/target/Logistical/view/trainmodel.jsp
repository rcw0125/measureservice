<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="t" uri="http://www.talent.com/jsp/anyonelse"%>
<t:html>
<head>
<jsp:include page="common.jsp" flush="true" />
</head>
<body class="container-fluid" style="padding-top: 15px">
	<div class="modal fade" id="TrainmodelWindow" tabindex="-1" role="dialog" aria-labelledby="TrainmodelWindowLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">车型操作</h4>
				</div>
				<div class="modal-body">
					<form id="TrainmodelForm" onsubmit="return toVaild()">
						<input type="hidden" id="id" name="id" value="0" />
						<div class="row">
							<div class="col-sm-6">
								<t:textbox id="model" name="model" label="车&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型" require="true" />
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon" id="sizing-addon3">载重</span>
									<input type="text" class="form-control" placeholder="载重" id="load" name="load" aria-describedby="sizing-addon3" required="true" onfocus="setNullLoad()" />
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon" id="sizing-addon3">标准皮重</span>
									<input type="text" class="form-control" placeholder="标准皮重" id="standardtare" name="standardtare" aria-describedby="sizing-addon3" required="true" onfocus="setNullStandardtare()"/>
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon" id="sizing-addon3">扣重</span>
									<input type="text" class="form-control" placeholder="扣重" id="deduction" name="deduction" aria-describedby="sizing-addon3"/>
								</div>
							</div>
							<div class="col-sm-12">
								<t:textbox id="usermemo" name="usermemo" label="用户备注" />
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer btn-group-sm">
					<button type="button" class="btn btn-success" id="TrainmodelSaveBtn">保存</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-sm-12">
			<form id="queryform">
				<div class="row">
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon">车型</span> 
							<input type="text" class="form-control" placeholder="火车车型" id="model" name="model" aria-describedby="sizing-addon3"/>
						</div>
					</div>
					<div class="form-group btn-group-sm">
						<button id="query" type="button" class="btn btn-info enterkeyaction" onclick="queryinfo()">
							<span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;查询
						</button>
						<button id="add" type="button" class="btn btn-success" onclick="addFunction()">
							<span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;添加
						</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<div class="row">
		<div class="col-sm-12">
			<table id="TrainmodelGrid" data-toggle="table" data-row-style="rowStyle" data-query-params="queryParams" data-pagination="true" data-page-size="10" data-page-list="[10,40,ALL]" data-mobile-responsive="true">
				<thead>
					<tr>
						<th data-field="id" data-visible="false"></th>
						<th data-field="model" data-halign="center" data-searchable="true" class="text-center" sortable>车型</th>
						<th data-field="load" data-halign="center" data-searchable="true" class="text-center" sortable>载重/t</th>
						<th data-field="standardtare" data-halign="center" data-searchable="true" class="text-center" sortable>标准皮重/t</th>
						<th data-field="deduction" data-halign="center" data-searchable="true" class="text-center" sortable>扣重/t</th>
						<th data-field="createor" data-halign="center" data-searchable="true" class="text-nowrap text-center">创建人</th>
						<th data-field="createdate" data-halign="center" data-searchable="true" class="text-nowrap text-center">创建时间</th>
						<th data-field="usermemo" data-halign="center" data-searchable="true" class="text-nowrap text-center">备注</th>
						<th data-width="70px" data-align="center" data-valign="middle" data-formatter="operateFormatter" data-events="operateEvents">操作</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>

	<script type="text/javascript">
		jQuery(document).ready(function($) {
			queryinfo();
			$('#TrainmodelSaveBtn').on('click',function() {
				validForm('TrainmodelForm');
				saveFormData('TrainmodelForm','<c:url value="/trainmodel/edit"/>',
					function(data) {
						if (data.success) {
							successbox(data.msg);
							$('#TrainmodelWindow').modal(
									'toggle');
							$('#TrainmodelGrid')
									.bootstrapTable(
											'refresh');
						} else {
							errorbox(data.msg);
						}
					});
			});
		});

		function queryParams(params) {
			return jQuery.extend({}, params, $('#queryform').serializeJson());
		}

		function queryinfo() {
			$('#TrainmodelGrid').bootstrapTable('refresh', {
				url : "<c:url value='/trainmodel/list'/>"
			});
		}

		function addFunction() {
			openWindow('TrainmodelWindow', 'TrainmodelForm',
					'<c:url value="/trainmodel/form"/>?id=-1');
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
				openWindow('TrainmodelWindow', 'TrainmodelForm',
						'<c:url value="/trainmodel/form"/>?id=' + row.id);
			},
			'click .remove' : function(e, value, row) {
				cancelTrainmodel(row.id);
			}
		};

		function cancelTrainmodel(id) {
			dialogbox("请确认", "确认删除此条信息？", function(data) {
				if (data) {
					$.ajax({
						type : "post",
						url : '<c:url value="/trainmodel/cancel"/>',
						dataType : "json",
						data : {
							id : id
						},
						success : function(data) {
							if (data.success) {
								successbox(data.msg);
								$('#TrainmodelGrid').bootstrapTable('refresh');
							} else {
								errorbox(data.msg);
							}
						}
					});
				}
			});

		}
		function setNullLoad() {
			if (document.getElementById("load").value == 0) {
				$("#load").val("");
			}
		}
		function setNullStandardtare() {
			if (document.getElementById("standardtare").value == 0) {
				$("#standardtare").val("");
			}
		}

	</script>

</body>
</t:html>