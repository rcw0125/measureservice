<%@ page contentType="text/html;charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="t" uri="http://www.talent.com/jsp/anyonelse"%>
<t:html>
	<head>
		<jsp:include page="common.jsp" flush="true"/>
	</head>
	<body>
		<div class="row-fluid">
			<div class="col-sm-8">
				<t:datatable url="/dictionary/list" entities="com.talent.core.platform.model.Dictionary" id="DictionaryGrid" showsearch="true" showcrud="true" btntypes="add,remove" btnfuncs="addFunction,removeFunction" hiddencolumns="sn,creator,cdate,updator,udate,deletor,ddate,classindex,classtype,usermemo,sysmemo,memo,begintime,endtime,unitcode,unitname,unitid"/>
			</div>
			<div class="col-sm-4" style="padding-top:10px;">
				<div class="panel panel-warning" style="width:100%;">
					<div class="panel-heading">字典编辑</div>
					<div class="panel-body">
						<div class="container-fluid" style="padding:0px;">
							<form id="DictionaryForm">
								<input type="hidden" id="id" name="id" value="-1"/>
								<div class="row">
									<div class="col-sm-12">
										<t:textbox id="catalogname" label="目录名称" require="true"/>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12">
										<t:textbox id="catalogcode" label="目录编码" require="true"/>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12">
										<t:textbox id="dictname" label="字典名称" require="true"/>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12">
										<t:textbox id="dictcode" label="字典编码" require="true"/>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12">
										<t:textbox id="dictvalue" label="字典描述" require="true"/>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12">
										<button type="button" class="btn btn-success" id="DictionarySaveBtn" style="width:100%">保存资源</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
	<script type="text/javascript">
		jQuery(document).ready(function($){
			$('#DictionarySaveBtn').on('click',function(){
				saveFormData('DictionaryForm','<c:url value="/dictionary/edit"/>',function(data){
					if(data.success){
						$('#DictionaryForm').resetForm();
						validForm('DictionaryForm');
	                	$('#DictionaryGrid').bootstrapTable('refresh');
					}
				});
	        });
			$('#dictname').on('blur',function(){
				$('#dictvalue').val($(this).val());
				validForm('DictionaryForm');
			});
		});
		
		$('#DictionaryGrid').bootstrapTable({onDblClickRow:function(row,element){
			loadFormData('DictionaryForm','<c:url value="/dictionary/form"/>?id=' + row.id);
		}});
		
		function addFunction(){
        	loadFormData('DictionaryForm','<c:url value="/dictionary/form"/>?id=-1');
        }
        
		function removeFunction(){
        	listGridIds('DictionaryGrid',function(ids){
        		removeData('<c:url value="/dictionary/delete"/>',ids,function(data){
        			$('#DictionaryGrid').bootstrapTable('refresh');
        		});
        	});
        }
		
		function DictionaryGridOperateFormatter(value, row, index) {
            return [
                '<div class="pull-center">',
                '<a class="edit" href="javascript:void(0)" title="修改">',
               	'<i class="glyphicon glyphicon-pencil"></i>',
               	'</a>　',
               	'<a class="remove" href="javascript:void(0)" title="移除">',
               	'<i class="glyphicon glyphicon-trash"></i>',
               	'</a>',
               	'</div>'
           	].join('');
        }
		
        window.DictionaryGridOperateEvents = {
       		'click .edit': function (e, value, row) {
				loadFormData('DictionaryForm','<c:url value="/dictionary/form"/>?id=' + row.id);
            },
            'click .remove': function (e, value, row) {
            	removeData('<c:url value="/dictionary/delete"/>',row.id,function(data){
        			$('#DictionaryGrid').bootstrapTable('refresh');
        		});
            }
        };
	</script>
</t:html>