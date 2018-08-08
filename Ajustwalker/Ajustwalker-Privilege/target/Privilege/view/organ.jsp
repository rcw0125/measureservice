<%@ page contentType="text/html;charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="t" uri="http://www.talent.com/jsp/anyonelse"%>
<t:html>
	<head>
		<jsp:include page="common.jsp" flush="true"/>
	</head>
	<body>
		<div class="modal fade" id="OrganWindow" tabindex="-1" role="dialog" aria-labelledby="OrganWindowLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">机构管理</h4>
					</div>
					<div class="modal-body">
						<form id="OrganForm">
							<input type="hidden" id="id" name="id" value="-1"/>
							<div class="row">
								<div class="col-sm-12">
									<t:textbox id="organname" label="机构名称" require="true"/>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<t:textbox id="organcode" label="机构编码" require="true"/>
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer btn-group-sm">
					    <button type="button" class="btn btn-success" id="OrganSaveBtn">保存</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
		</div>
		<div class="row-fluid">
			<div class="col-sm-12">
				<t:datatable url="/organ/list" entities="com.talent.core.privilege.model.Organ" id="OrganGrid" showcrud="true" btntypes="add,remove,exportremote" btnfuncs="addFunction,removeFunction,remoteExportExcel" hiddencolumns="sn,validflag,creator,cdate,updator,udate,deletor,ddate,classindex,classtype,usermemo,sysmemo,memo,begintime,endtime,unitcode,unitname,unitid"/>
			</div>
		</div>
	</body>
	<script type="text/javascript">
		jQuery(document).ready(function($){
			$('#OrganSaveBtn').on('click',function(){
				saveFormData('OrganForm','<c:url value="/organ/edit"/>',function(data){
					if(data.success){
						$('#OrganWindow').modal('toggle');
	                	$('#OrganGrid').bootstrapTable('refresh');
					}
				});
	        });
			
			$('#organname').on('blur',function(){
				$('#organcode').val(pinyin.getFullChars($(this).val()));
				validForm('OrganForm');
			});
		});
		
		function addFunction(){
        	openWindow('OrganWindow','OrganForm','<c:url value="/organ/form"/>?id=-1');
        }
        
		function removeFunction(){
        	listGridIds('OrganGrid',function(ids){
        		removeData('<c:url value="/organ/delete"/>',ids,function(data){
        			$('#OrganGrid').bootstrapTable('refresh');
        		});
        	});
        }
		
		function OrganGridOperateFormatter(value, row, index) {
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
		
        window.OrganGridOperateEvents = {
            'click .edit': function (e, value, row) {
            	openWindow('OrganWindow','OrganForm','<c:url value="/organ/form"/>?id=' + row.id);
            },
            'click .remove': function (e, value, row) {
            	removeData('<c:url value="/organ/delete"/>',row.id,function(data){
        			$('#OrganGrid').bootstrapTable('refresh');
        		});
            }
        };
	</script>
</t:html>