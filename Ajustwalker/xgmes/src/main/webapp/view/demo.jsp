<%@ page contentType="text/html;charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="t" uri="http://www.talent.com/jsp/anyonelse"%>
<!DOCTYPE html>
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
	<jsp:include page="common.jsp" flush="true"/>
</head>
<body class="section container-fluid">
	<div class="row-fluid">
		<div class="col-sm-12">
			<div class="modal fade" id="${progcode}Window" tabindex="-1" role="dialog" aria-labelledby="${progcode}WindowLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title">${progname}</h4>
						</div>
						<div class="modal-body">
							<form id="${progcode}Form">
								<div class="row">
									<div class="col-sm-12">
										<span class="help-block" id="errormsg"></span>
										<input type="hidden" id="id" name="id" value="-1"/>
										<input type="hidden" id="fid" name="fid" value="-1"/>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-6">
										<div class="form-group input-group">
											<input name="organname"  type="text" class="form-control" placeholder="必填" aria-describedby="basic-addon1">
											<span class="input-group-addon">机构名称&nbsp;&nbsp;</span>
										</div>
									</div>
									<div class="col-sm-6">
										<div class="form-group input-group">
											<input name="organcode"  type="text" class="form-control" placeholder="必填" aria-describedby="basic-addon1">
											<span class="input-group-addon">机构编码&nbsp;&nbsp;</span>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12">
										<t:combobox id="testcombobox" url="/bcommon/queryReaderType.do" label="测试combo加载" readonly="false"/>
									</div>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-success" id="${progcode}SaveBtn">aaaaaaaaaaa</button>
							<button type="button" class="btn btn-default" data-dismiss="modal"><property:read key="btn.close"/></button>
						</div>
					</div>
				</div>
			</div>
			<div id="${progcode}Toolbar">
				<div class="form-inline" role="form">
					<button id="add${progcode}Btn" type="button" class="btn btn-success" data-toggle="modal">
						<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;<property:read key="btn.add"/>
					</button>
					<button id="del${progcode}Btn" type="button" class="btn btn-danger" data-toggle="modal">
						<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>&nbsp;<property:read key="btn.delete"/>
					</button>
				</div>
			</div>
			<table id="${progcode}Grid" data-toggle="table" data-pagination="true"
				   data-page-list="[10,30,50]"
				   data-row-style="rowStyle"
				   data-show-refresh="true"
				   data-search="true"
				   data-url="<c:url value='/${progcode}/queryPage.do'/>"
				   data-toolbar="#${progcode}Toolbar">
				<thead>
				<tr>
					<th data-halign="center" data-checkbox="true"></th>
					<c:forEach items="${fields}" var="item" varStatus="status">
						<c:if test="${item.hidden}">
							<th data-field="${item.fieldName}" data-halign="center" data-visible="false"></th>
						</c:if>
						<c:if test="${!item.hidden}">
							<th data-field="${item.fieldName}" data-halign="center" data-searchable="true">${item.name}</th>
						</c:if>
					</c:forEach>
					<th data-width="70px" data-align="center" data-valign="middle" data-formatter="operateFormatter" data-events="operateEvents">操作</th>
				</tr>
				</thead>
			</table>
		</div>
	</div>
	<script type="text/javascript">
		jQuery(document).ready(function($){
			$('#${progcode}Form').bootstrapValidator({
				excluded: [':disabled', ':hidden', ':not(:visible)'],
				onSuccess:function() {
		        	$('#${progcode}Form').ajaxSubmit({
		                type: 'post',
		                url: '<c:url value="/${progcode}/addoredit.do"/>',
		                success: function(data){
		                	if(data.success){
		                		$('#${progcode}Window').modal('toggle');
			                	$('#${progcode}Grid').bootstrapTable('refresh');
		                	}else{
		                		toastMessage('error','警告',data.msg);
		                	}		                	
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
			
			$('#${progcode}SaveBtn').on('click',function(){
	        	//$('#${progcode}Form').bootstrapValidator('validate');
				loadComboxData('testcombobox','<c:url value="/bcommon/queryWorkline.do"/>',function(data){
					
				});
	        });
	        
	        $('#${progcode}Window').on('shown.bs.modal', function(){
	        	var id_v = $($('#'+$(this).attr("id")+' form :input[name="id"]'));
	        	$.ajax({
		            type: "post",
		            url: '<c:url value="/${progcode}/loadform.do"/>?id='+id_v.val(),
		            dataType: "json",
		            success: function(data){
		            	loadFormData('${progcode}Form',data);
		            }
	            });
	       	});
	        
	        $('#add${progcode}Btn').on('click',function(){
	        	addOrEdit(-1);
	        });
	        
	        $('#del${progcode}Btn').on('click',function(){
	        	var ids = '';
	        	var selectedRows = $('#${progcode}Grid').bootstrapTable('getAllSelections');
	        	for(var i=0;i<selectedRows.length;i++){
	        		ids = ids + ',' + selectedRows[i].id;
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
	        		remove(ids);
	        	}
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
        window.operateEvents = {
            'click .edit': function (e, value, row) {
            	addOrEdit(row.id);
            },
            'click .remove': function (e, value, row) {
            	remove(row.id);
            }
        };
        
        function addOrEdit(id_vv){
        	$('#${progcode}Window form :input[name="id"]').val(id_vv);
        	$('#${progcode}Window').modal('show');
        }
        
		function remove(id_vv){
			$.messager.confirm("请确认", "确认删除此项目？", function() { 
				$.ajax({
		            type: "post",
		            url: '<c:url value="/${progcode}/del.do"/>',
		            dataType: "json",
		            data: {ids:id_vv},
		            success: function(data){
		            	if(data.success == true){
		            		$('#${progcode}Grid').bootstrapTable('refresh');
		            	}
		            }
	            });
			});
		}
	</script>
</body>
</html>