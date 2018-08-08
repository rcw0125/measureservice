<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="common.jsp" flush="true" />
	<style type="text/css">
		.select2-container--bootstrap .select2-selection{
			border-radius: 4px 0px 0px 4px;
		}
		.select2-container--bootstrap.input-sm .select2-selection--single, .input-group-sm .select2-container--bootstrap .select2-selection--single, .form-group-sm .select2-container--bootstrap .select2-selection--single{
			border-radius: 4px 0px 0px 4px;
		}
	</style>
</head>
<body class="section container-fluid" style="padding-top:15px;">
	<div class="row-fluid">
		<div class="col-sm-12">
			<div id="MeasureweighToolbar">
				<div class="form-inline btn-group-sm" role="form">
					<button id="addMeasureweighBtn" type="button"
						class="btn btn-success" data-toggle="modal">
						<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;添加
					</button>
				</div>
			</div>
			<table id="MeasureweighGrid" data-toggle="table"
				data-pagination="true" 
				data-page-list="[10,30,50]"
				data-row-style="rowStyle" 
				
				data-page-size="10"
				data-url="<c:url value='/measure/queryMeasureweighinfo.do'/>"
				data-side-pagination="server" 
				data-toolbar="#MeasureweighToolbar">
				<thead>
					<tr>
						<th data-field="id" data-halign="center" data-visible="false"></th>
						<th data-field="operatype" data-halign="center"
							data-searchable="true">业务类型</th>
						<th data-field="tareweigh" data-halign="center"
							data-searchable="true">衡器名称</th>
						<th data-field="mtype" data-halign="center"
							data-searchable="true">计量类型</th>
						<th data-field="materialname" data-halign="center"
							data-searchable="true">物料名称</th>
						<th data-width="70px" data-align="center" data-valign="middle"
							data-formatter="operateFormatter" data-events="operateEvents">操作</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<div class="modal fade" id="MeasureweighWindowsave" role="dialog"
		aria-labelledby="MeasureRoleWindowLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					 <h4 class="modal-title">计量衡器配置</h4> 
				</div>
				<div class="modal-body">
					<form id="MeasureweighFormsave">
						<input type="hidden" name="id" value="-1">
						<div class="form-group input-group">
							<select id="operatype" name="operatype" class="form-control select2-data-ajax" ajax-url="<c:url value='/measure/queryOperatype.do'/>">
							</select>
							<div class="input-group-addon">业务类型</div>
						</div>
						<div class="form-group input-group">
							<input type="hidden" id="tareweigh" name="tareweigh" >
							<select id="tareweighid" name="tareweighid" multiple="multiple" class="form-control select2-data-ajax" ajax-url="<c:url value='/measure/queryEquipment.do'/>">
							</select>
							<div class="input-group-addon">衡器名称</div>
						</div>
						<div class="form-group input-group">
							<select id="mtype" name="mtype"  class="form-control select2"  placeholder="必选">
								<option value=""></option>
								<option value="G">计毛</option>
								<option value="T">计皮</option> 
							</select>
						 <div class="input-group-addon">计量类型</div>
						</div>
						 <div class="form-group input-group">
						   <input type="hidden" id="materialname" name = "materialname" value="">
							<select id="materialcode" name="materialcode"
								class="form-control select2" >
								<option value="-1">不限物料</option>
								<option value="水泥">水泥</option>
								<option value="矿渣粉" >矿渣粉</option>
								<option value="矿粉">矿粉</option>
							</select>
							<div class="input-group-addon">物料名称</div>
						</div> 
					</form>
			         <span class="help-block" id="errormsg"></span>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-success"
						id="MeasureweighSaveBtn">保存</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
		<div class="modal fade" id="MeasureweighWindowupdate" role="dialog"
		aria-labelledby="MeasureRoleWindowLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					 <h4 class="modal-title">计量衡器配置</h4> 
				</div>
				<div class="modal-body">
					<form id="MeasureweighFormupdate">
						<input type="hidden" name="id" value="-1">
						<div class="form-group input-group">
							<select  name="operatype" class="form-control select2-data-ajax" ajax-url="<c:url value='/measure/queryOperatype.do'/>">
							</select>
							<div class="input-group-addon">业务类型</div>
						</div>
						<div class="form-group input-group">
							<input type="hidden" id="tareweighs" name="tareweigh" >
							<select  id="tareweighids" name="tareweighid"  class="form-control select2-data-ajax" ajax-url="<c:url value='/measure/queryEquipment.do'/>">
							</select>
							<div class="input-group-addon">衡器名称</div>
						</div>
						<div class="form-group input-group">
							<select  name="mtype"  class="form-control select2"  placeholder="必选">
								<option value=""></option>
								<option value="G">计毛</option>
								<option value="T">计皮</option> 
							</select>
						 <div class="input-group-addon">计量类型</div>
						</div>
						 <div class="form-group input-group">
						   <input type="hidden" id="materialnames" name = "materialname" value="">
							<select id="materialcodes" name="materialcode" class="form-control select2" >
								<option value="-1">不限物料</option>
								<option value="水泥">水泥</option>
								<option value="矿渣粉" >矿渣粉</option>
								<option value="矿粉">矿粉</option>
							</select>
							<div class="input-group-addon">物料名称</div>
						</div> 
					</form>
			         <span class="help-block" id="errormsg"></span>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-success"
						id="MeasureweighupdateBtn">保存</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		var currentId = 0;
		jQuery(document).ready(function($) {
		
		$('#MeasureweighFormsave').bootstrapValidator({
					excluded : [ ':disabled',
								':hidden',
								':not(:visible)' ],
					onSuccess : function() {
		
						
					$('#MeasureweighFormsave').ajaxSubmit({	
						type : 'post',
						url : '<c:url value="/measure/insertMeasureweigh.do"/>',
						success : function(data) {
								if (data.success == true) {
									toastMessage("success","提示",data.msg);
									$('#MeasureweighGrid').bootstrapTable('refresh');
									$('#MeasureweighWindowsave').modal('toggle');
								}else{
									toastMessage("error","错误","操作失败！");
									
								}
		
                              }
							});
						   },
						fields : {
							operatype: {
                                  container : "#errormsg",
								  validators : {
								  notEmpty : {
								  message : '请选择业务类型！'
							   }
							 }
							},	
							mtype : {	
							   container : "#errormsg",
								validators : {
								notEmpty : {
								message : '请选择计量类型！'
									}
								}
							},
							tareweighid : {
								container : "#errormsg",
								validators : {
								notEmpty : {
								message : '请衡器名称！'
										}
										}
									}
								}
						});
             
		
		
		$('#MeasureweighFormupdate').bootstrapValidator({
			excluded : [ ':disabled',
						':hidden',
						':not(:visible)' ],
			onSuccess : function() {

				
			$('#MeasureweighFormupdate').ajaxSubmit({	
				type : 'post',
				url : '<c:url value="/measure/updateMeasureweigh.do"/>',
				success : function(data) {
						if (data.success == true) {
							toastMessage("success","提示",data.msg);
							$('#MeasureweighGrid').bootstrapTable('refresh');
							$('#MeasureweighWindowupdate').modal('toggle');
						}else{
							toastMessage("error","错误","操作失败！");
							
						}

                      }
					});
				   },
				fields : {
					operatype: {
                          container : "#errormsg",
						  validators : {
						  notEmpty : {
						  message : '请选择业务类型！'
					   }
					 }
					},	
					mtype : {	
					   container : "#errormsg",
						validators : {
						notEmpty : {
						message : '请选择计量类型！'
							}
						}
					},
					tareweighid : {
						container : "#errormsg",
						validators : {
						notEmpty : {
						message : '请衡器名称！'
								}
								}
							}
						}
				});
		    
			$('#MeasureweighSaveBtn').on('click',function() {
					$('#MeasureweighFormsave').bootstrapValidator('validate');
				});
				
				 
				$('#MeasureweighupdateBtn').on('click',function() {
				
						$('#MeasureweighFormupdate').bootstrapValidator('validate');
					});	
			$('#addMeasureweighBtn').on('click', function() {
				openMeasureweighWindowsave(-1);
				});
			$('#materialcode').change(function (){

				$('#materialname').val($('#materialcode option:selected').text()); 
		
			});
			$('#tareweighid').change(function (){
				
				$('#tareweigh').val($('#tareweighid option:selected').text());
		
			});
			$('#materialcodes').change(function (){

				$('#materialnames').val($('#materialcodes option:selected').text()); 
		
			});
			$('#tareweighids').change(function (){
				
				$('#tareweighs').val($('#tareweighids option:selected').text());
		
			});
			

		});

		function operateFormatter(value, row, index) {
			return [ '<div class="pull-right">',
					'<a class="edit" href="javascript:void(0)" title="修改">',
					'<i class="glyphicon glyphicon-pencil"></i>', '</a>　',
					'<a class="remove" href="javascript:void(0)" title="移除">',
					'<i class="glyphicon glyphicon-trash"></i>', '</a>',
					'</div>' ].join('');
		}


		function operateFormatterDetail(value, row, index) {
			return [ '<div class="pull-center">',
					'<a class="remove" href="javascript:void(0)" title="移除">',
					'<i class="glyphicon glyphicon-trash"></i>', '</a>',
					'</div>' ].join('');
		}

		window.operateEvents = {
			'click .edit' : function(e, value, row) {
				openMeasureweighWindowupdate(row.id);
			},
			'click .remove' : function(e, value, row) {
				cancelMeasureweigh(row.id);
			}
		};

		function openMeasureweighWindowupdate(id_vv) {
			currentId = id_vv;
			$($('#MeasureweighWindowupdate form :input[name="id"]')).val(id_vv);
			$('#MeasureweighWindowupdate').modal('show');
		}
		function openMeasureweighWindowsave(id_vv) {
			currentId = id_vv;
			$($('#MeasureweighWindowsave form :input[name="id"]')).val(id_vv);
			$('#MeasureweighWindowsave').modal('show');
		}
		function cancelMeasureweigh(id_vv) {
			$.messager.confirm("请确认", "确认删除此项目？", function() {
			$.ajax({
					type : "post",
					url : '<c:url value="/measure/cancelMeasureweigh.do"/>',
					dataType : "json",
					data : {id : id_vv
					},
					success : function(data) {
						if (data.success == true) {
							toastMessage("success","提示","操作成功！");
							$('#MeasureweighGrid').bootstrapTable('refresh');
						}else{
							toastMessage("error","错误","操作失败！");
							
						}
					}
				});
			});
		}
		$('#MeasureweighWindowupdate').on('shown.bs.modal',function() {
							var id_v = $($('#' + $(this).attr("id")+ ' form :input[name="id"]'));
							$.ajax({
								type : "post",
								url : '<c:url value="/measure/queryMweighinfoByid.do"/>?id='+ id_v.val(),
								dataType : "json",
								success : function(data) { 
								//alert(data.data);
								loadFormData('MeasureweighFormupdate',data);
								}
							});
						});
		$('#MeasureweighWindowsave').on('shown.bs.modal',function() {
			var id_v = $($('#' + $(this).attr("id")+ ' form :input[name="id"]'));
			$.ajax({
				type : "post",
				url : '<c:url value="/measure/queryMweighinfosave.do"/>?id='+ id_v.val(),
				dataType : "json",
				success : function(data) {
				loadFormData('MeasureweighFormsave',data);
				}
			});
		});
	</script>

</body>
</html>