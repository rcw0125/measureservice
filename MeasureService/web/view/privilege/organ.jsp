<%@ page contentType="text/html;charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html>
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
	<jsp:include page="../common.jsp" flush="true"/>
</head>
<body class="section container-fluid">
	<div class="row-fluid">
		<div class="col-sm-12">
			<div class="modal fade" id="OrganWindow" tabindex="-1" role="dialog" aria-labelledby="OrganWindowLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title">机构维护</h4>
						</div>
						<div class="modal-body">
							<form id="organForm">
								<div class="row">
									<div class="col-sm-12">
										<span class="help-block" id="errormsg"></span>
										<input type="hidden" id="rowid" name="rowid" value="0"/>
										<input type="hidden" id="frowid" name="frowid" value="0"/>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-6">
										<div class="form-group input-group">
											<input name="organname"  type="text" class="form-control" placeholder="必填" aria-describedby="basic-addon1">
											<span class="input-group-addon" id="paramname">机构名称&nbsp;&nbsp;</span>
										</div>
									</div>
									<div class="col-sm-6">
										<div class="form-group input-group">
											<input name="organcode"  type="text" class="form-control" placeholder="必填" aria-describedby="basic-addon1">
											<span class="input-group-addon" id="paramname">机构编码&nbsp;&nbsp;</span>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12">
										<div class="form-group input-group">
											<input name="organmemo"  type="text" class="form-control" placeholder="必填" aria-describedby="basic-addon1">
											<span class="input-group-addon" id="paramvalue">备注详情&nbsp;&nbsp;</span>
										</div>
									</div>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-success" id="organSaveBtn">保存</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						</div>
					</div>
				</div>
			</div>
			<div id="organToolbar">
				<div class="form-inline" role="form">
					<button id="addOrganBtn" type="button" class="btn btn-success" data-toggle="modal">
						<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;添加
					</button>
					<button id="delOrganBtn" type="button" class="btn btn-danger" data-toggle="modal">
						<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>&nbsp;删除
					</button>
				</div>
			</div>
			<table id="organGrid" data-toggle="table" data-pagination="true"
				   data-page-list="[10,30,50]"
				   data-row-style="rowStyle"
				   data-show-refresh="true"
				   data-search="true"
				   data-url="<c:url value='/organ/queryPage.do'/>"
				   data-side-pagination="server"
				   data-toolbar="#organToolbar">
				<thead>
				<tr>
					<th data-halign="center" data-checkbox="true"></th>
					<th data-field="rowid" data-halign="center" data-visible="false"></th>							
					<th data-field="frowid" data-halign="center" data-visible="false"></th>							
					<th data-field="organname" data-halign="center" data-searchable="true">机构名称</th>
					<th data-field="organcode" data-halign="center" data-searchable="true">机构编码</th>
					<th data-field="organmemo" data-halign="center" data-searchable="true">备注</th>
					<th data-width="70px" data-align="center" data-valign="middle" data-formatter="operateFormatter" data-events="operateEvents">操作</th>
				</tr>
				</thead>
			</table>
		</div>
	</div>
	<script type="text/javascript">
		jQuery(document).ready(function($){
			$('#organForm').bootstrapValidator({
				excluded: [':disabled', ':hidden', ':not(:visible)'],
				onSuccess:function() {
		        	$('#organForm').ajaxSubmit({
		                type: 'post',
		                url: '<c:url value="/organ/addoredit.do"/>',
		                success: function(data){
		                	$('#OrganWindow').modal('toggle');
		                	$('#organGrid').bootstrapTable('refresh');
		                }
		            });
		        },
		        fields: {
		        	moduletype: {
		        		container:"#errormsg",
		                validators: {
		                    notEmpty: {
		                        message: '请填写参数类型！'
		                    }
		                }
		            },
		            modulename: {
		        		container:"#errormsg",
		                validators: {
		                    notEmpty: {
		                        message: '请填写参数名称！'
		                    }
		                }
		            },
		            modulecode:{
		            	container:"#errormsg",
		                validators: {
		                    notEmpty: {
		                        message: '请填写配置数据！'
		                    }
		                }
		            },
		            memo:{
		            	container:"#errormsg",
		                validators: {
		                    notEmpty: {
		                        message: '请填写备注详情！'
		                    }
		                }
		            }
		        }
		    });
			
			$('#organSaveBtn').on('click',function(){
	        	$('#organForm').bootstrapValidator('validate');
	        });
	        
	        $('#OrganWindow').on('shown.bs.modal', function(){
	        	var id_v = $($('#'+$(this).attr("rowid")+' form :input[name="rowid"]'));
	        	$.ajax({
		            type: "post",
		            url: '<c:url value="/organ/loadform.do"/>?rowid='+id_v.val(),
		            dataType: "json",
		            success: function(data){
		            	loadFormData('organForm',data);
		            }
	            });
	       	});
	        
	        $('#addOrganBtn').on('click',function(){
	        	openOrganWindow(-1);
	        });
	        
	        $('#delOrganBtn').on('click',function(){
	        	var ids = '';
	        	var selectedRows = $('#organGrid').bootstrapTable('getAllSelections');
	        	for(var i=0;i<selectedRows.length;i++){
	        		ids = ids + ',' + selectedRows[i].rowid;
	        	}
	        	if('' == ids){
	        		$.alert({
	        		    title: '警告',
	        		    content: '请至少选择一条数据操作！',
	        		    confirmButton: '确定',
	        		    confirmButtonClass: 'btn-info'
	        		});
	        	}else{
	        		ids = ids.substr(1);
	        		removeOrgan(ids);
	        	}
	        });
	        
	        $("form input[type=\"checkbox\"],input[type=\"radio\"]").not("[data-switch-no-init]").bootstrapSwitch();
		});
		
		function operateFormatter(value, row, index) {
            return [
                    '<div class="pull-right">',
                	'<a class="edit" href="javascript:void(0)" title="修改">',
                	'<i class="glyphicon glyphicon-pencil"></i>',
                	'</a>　',
                	'<a class="remove" href="javascript:void(0)" title="移除">',
                	'<i class="glyphicon glyphicon-trash"></i>',
                	'</a>',
                	'</div>'
            		].join('');
        }
        window.operateEvents = {
            'click .edit': function (e, value, row) {
            	openOrganWindow(row.rowid);
            },
            'click .remove': function (e, value, row) {
            	removeOrgan(row.rowid);
            }
        };
        
        function openOrganWindow(id_vv){
        	$($('#OrganWindow form :input[name="rowid"]')).val(id_vv);
        	$('#OrganWindow').modal('show');
        }
        
		function removeOrgan(id_vv){
			$.messager.confirm("请确认", "确认删除此项目？", function() { 
				$.ajax({
		            type: "post",
		            url: '<c:url value="/organ/del.do"/>',
		            dataType: "json",
		            data: {ids:id_vv},
		            success: function(data){
		            	if(data.success == true){
		            		$('#organGrid').bootstrapTable('refresh');
		            	}
		            }
	            });
			});
		}
	</script>
</body>
</html>