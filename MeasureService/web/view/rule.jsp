<%@ page contentType="text/html;charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="common.jsp" flush="true"/>
	<style type="text/css">
		.select2-container--bootstrap .select2-selection{
			border-radius: 4px 0px 0px 4px;
		}
		.select2-container--bootstrap.input-sm .select2-selection--single, .input-group-sm .select2-container--bootstrap .select2-selection--single, .form-group-sm .select2-container--bootstrap .select2-selection--single{
			border-radius: 4px 0px 0px 4px;
		}
	</style>
</head>
<body class="container-fluid" style="padding-top:10px;">
	<div class="row" style="padding-left:20px;padding-right:20px;">
		<div id="MeasureRuleToolbar">
			<div class="form-inline btn-group-sm" role="form">
				<button id="addMeasureRuleBtn" type="button" class="btn btn-success" data-toggle="modal">
					<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;添加
				</button>
			</div>
		</div>
		<table id="MeasureRuleGrid" data-toggle="table"
			   data-row-style="rowStyle"
			   data-url="<c:url value='/measurerule/queryPage.do'/>"
			   data-toolbar="#MeasureRuleToolbar">
			<thead>
			<tr>
				<th data-field="id" data-halign="center" data-visible="false"></th>							
				<th data-field="opertype" data-halign="center" data-searchable="true" data-formatter="opertypeFormatter">业务类型</th>
				<th data-field="measuretype" data-halign="center" data-searchable="true" data-formatter="measuretypeFormatter">计量类型</th>
				<th data-field="materialcode" data-halign="center" data-searchable="true" data-formatter="materialcodeFormatter">物料名称</th>
				<th data-width="70px" data-align="center" data-valign="middle" data-formatter="operateFormatter" data-events="operateEvents">操作</th>
			</tr>
			</thead>
		</table>
	</div>
	<div class="modal fade" id="MeasureRuleWindow" tabindex="-1" role="dialog" aria-labelledby="MeasureRuleWindowLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">配置参数</h4>
				</div>
				<div class="modal-body">
					<form id="MeasureRuleForm">
						<div class="row">
							<div class="col-sm-12">
								<span class="help-block" id="errormsg"></span>
							</div>
						</div>
						<div class="row">
							<input type="hidden" name="id" value="-1"/>
							<div class="col-sm-4">								
								<div class="form-group input-group">
									<select id="opertype" name="opertype" class="form-control select2-data-ajax" placeholder="必选" ajax-url="<c:url value='/measurerule/loadoperatetype.do'/>">
									</select>
									<div class="input-group-addon">业务类型</div>
								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group input-group">
									<select id="measuretype" name="measuretype" class="form-control select2-data-ajax" ajax-url="<c:url value='/measurerule/loadmeasuretype.do'/>">
									</select>
									<div class="input-group-addon">计量类型</div>
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form-group input-group">
									<select id="materialcode" name="materialcode" class="form-control select2-data-ajax" ajax-url="<c:url value='/measurerule/loadmaterialcode.do'/>" multiple="multiple">
									</select>
									<div class="input-group-addon">物料名称</div>
								</div>
							</div>
							<div class="col-sm-1">
								<div class="form-group input-group">
									<button type="button" class="btn btn-success" id="MeasureRuleSaveBtn">保存</button>
								</div>
							</div>
						</div>
					</form>
					<div class="row">
						<div class="col-sm-12">
							<table id="MeasureRuleDetailGrid" data-toggle="table">
								<thead>
									<tr>
										<th data-field="id" data-halign="center" data-visible="false"></th>							
										<th data-field="pid" data-halign="center" data-visible="false"></th>							
										<th data-field="selected" data-halign="center" data-formatter="functionSelectFormatter"></th>							
										<th data-field="functionname" data-halign="center">方法名称</th>
										<th data-field="functiondesc" data-halign="center">方法描述</th>
										<th data-field="ctrldesc" data-halign="center">控制描述</th>
										<th data-field="functionmemo" data-halign="center">备注</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>					
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var currentId = 0;
		var currentOpType = 0;
		jQuery(document).ready(function($){
			$('#MeasureRuleForm').bootstrapValidator({
				excluded: [':disabled', ':hidden', ':not(:visible)'],
				onSuccess:function() {
		        	$('#MeasureRuleForm').ajaxSubmit({
		                type: 'post',
		                url: '<c:url value="/measurerule/addoredit.do"/>',
		                success: function(data){
		                	currentId = data.total;
		                	currentOpType = data.more;
		                	$('#MeasureRuleGrid').bootstrapTable('refresh');
		                	$('#MeasureRuleDetailGrid').bootstrapTable('refresh',{url:"<c:url value='/measurerule/queryDetail.do'/>",query:{pid:currentId,operateType:currentOpType}});
		                }
		            });
		        },
		        fields: {
		        	opertype: {
		        		container:"#errormsg",
		                validators: {
		                    notEmpty: {
		                        message: '请选择业务类型！'
		                    }
		                }
		            },
		            measuretype: {
		        		container:"#errormsg",
		                validators: {
		                    notEmpty: {
		                        message: '请选择计量类型！'
		                    }
		                }
		            },
		            materialcode:{
		            	container:"#errormsg",
		                validators: {
		                    notEmpty: {
		                        message: '请选择物料！'
		                    }
		                }
		            }
		        }
		    });
			
			$('#MeasureRuleSaveBtn').on('click',function(){
	        	$('#MeasureRuleForm').bootstrapValidator('validate');
	        });
	        
	        $('#MeasureRuleWindow').on('shown.bs.modal', function(){
	        	var id_v = $($('#'+$(this).attr("id")+' form :input[name="id"]'));
	        	$.ajax({
		            type: "post",
		            url: '<c:url value="/measurerule/loadform.do"/>?id='+id_v.val(),
		            dataType: "json",
		            success: function(data){
		            	loadFormData('MeasureRuleForm',data);
		            	currentId = -2;
		            	if(-1 == data.id){
		            		currentId = -2;
		            		currentOpType = "";
		            	}else{
		            		currentId = data.id;
		            		currentOpType = data.opertype;
		            	}
		            	$('#MeasureRuleDetailGrid').bootstrapTable('refresh',{url:"<c:url value='/measurerule/queryDetail.do'/>",query:{pid:currentId,operateType:currentOpType}});
		            }
	            });
	       	});
	        
	        $('#addMeasureRuleBtn').on('click',function(){
	        	openMeasureRuleWindow(-1);
	        });
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
		
		function opertypeFormatter(value, row, index) {
			return dictionaryConverter("<c:url value='/measurerule/loadoperatetype.do'/>",'operatype',value);
        }
		
		function measuretypeFormatter(value, row, index) {
			if('G' == value){
            	return '计毛';
            }else if('T' == value){
            	return '计皮';
            }
		}
		
		function materialcodeFormatter(value, row, index) {
			return dictionaryConverter("<c:url value='/measurerule/loadmaterialcode.do'/>",'materialcode',value);
		}
		
		function functionSelectFormatter(value, row, index) {
			if(value == 0){
	        	return '<center><input type="checkbox" onclick="selectFunction('+row.id+',\''+row.functionname+'\',\''+row.ctrldesc+'\',\'添加\')"><center>';
	        }else{
	        	return '<center><input type="checkbox" checked onclick="selectFunction('+row.id+',\''+row.functionname+'\',\''+row.ctrldesc+'\',\'删除\')"><center>';
	        }
		}
		
		function selectFunction(id,fn,cm,optr){
			$.ajax({
	            type: "post",
	            url: '<c:url value="/measurerule/addorDeleteDetail.do"/>',
	            dataType: "json",
	            data: {optr:optr,id:id,pid:currentId,functionname:fn,ctrlmemo:cm},
	            success: function(data){
                	$('#MeasureRuleDetailGrid').bootstrapTable('refresh',{url:"<c:url value='/measurerule/queryDetail.do'/>",query:{pid:currentId,operateType:currentOpType}});
                }
            });
		}
		
        window.operateEvents = {
            'click .edit': function (e, value, row) {
            	openMeasureRuleWindow(row.id);
            },
            'click .remove': function (e, value, row) {
            	removeMeasureRule(row.id);
            }
        };
        
        function openMeasureRuleWindow(id_vv){
        	currentId = id_vv;
        	$($('#MeasureRuleWindow form :input[name="id"]')).val(id_vv);
        	$('#MeasureRuleWindow').modal('show');
        }
        
		function removeMeasureRule(id_vv){
			$.messager.confirm("请确认", "确认删除此项目？", function() {
				$.ajax({
		            type: "post",
		            url: '<c:url value="/measurerule/del.do"/>',
		            dataType: "json",
		            data: {ids:id_vv},
		            success: function(data){
		            	 if(data.success == true){
		            		 $('#MeasureRuleGrid').bootstrapTable('refresh');
		            	 }
		            }
	            });
			});
		}
	</script>
</body>
</html>