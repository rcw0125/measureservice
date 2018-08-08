<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="t" uri="http://www.talent.com/jsp/anyonelse"%>
<meta charset="utf-8">
<title><t:resource key="system.modulename"/></title>
<meta name="description" content="<t:resource key="system.modulename"/>">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta name="renderer" content="webkit">
<meta http-equiv="Expires" content="0">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-control" content="no-cache">
<meta http-equiv="Cache" content="no-cache">
<link rel="shortcut icon" href="<c:url value='/images/Talent.ico'/>">
<link href="<c:url value='/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet" media="screen">
<link href="<c:url value='/fonts/font-awesome/css/font-awesome.min.css'/>" rel="stylesheet">
<link href="<c:url value='/css/animate.min.css'/>" rel="stylesheet">
<link href="<c:url value='/css/style.min.css'/>" rel="stylesheet">
<link href="<c:url value='/css/bootstrap-datetimepicker.css'/>" rel="stylesheet">
<link href="<c:url value='/css/select2.min.css'/>" rel="stylesheet">
<link href="<c:url value='/css/select2-bootstrap.css'/>" rel="stylesheet">
<link href="<c:url value='/plugins/datatable/bootstrap-table.min.css'/>" rel="stylesheet">
<link href="<c:url value='/css/bootstrapValidator.min.css'/>" rel="stylesheet">
<link href="<c:url value='/css/bootstrap-editable.css'/>" rel="stylesheet">
<link href="<c:url value='/css/jquery-confirm.css'/>" rel="stylesheet">
<link href="<c:url value='/css/toastr.min.css'/>" rel="stylesheet">
<link href="<c:url value='/css/fileinput.min.css'/>" rel="stylesheet">
<link href="<c:url value='/css/landscape/landscape.css'/>" rel="stylesheet">
<link href="<c:url value='/css/landscape/skins/_all-skins.css'/>" rel="stylesheet">

<!--[if lte IE 6]>
	<link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap-ie6.css'/>">
<![endif]-->

<!--[if lte IE 7]>
	<link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/ie.css'/>">
<![endif]-->

<style type="text/css">
	.select2-container--bootstrap .select2-selection{
		border-radius: 0px 4px 4px 0px;
	}
	.select2-container--bootstrap.input-sm .select2-selection--single, .input-group-sm .select2-container--bootstrap .select2-selection--single, .form-group-sm .select2-container--bootstrap .select2-selection--single{
		border-radius: 0px 4px 4px 0px;
	}
	.popupedit-click, a.popupedit-click, a.popupedit-click:hover {
	    text-decoration: none;
	    border-bottom: dashed 1px #0088cc;
	}
	.select2-container--open{
		z-index:9999;
	}
	.bootstrap-table .select2-selection{
		border-radius:0px !important;
	}

</style>

<script type="text/javascript" src="<c:url value='/plugins/jquery.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/bootstrap/js/bootstrap.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/plugins/map.js'/>"></script>
<script type="text/javascript" src="<c:url value='/plugins/jquery.cookie.js'/>"></script>
<script type="text/javascript" src="<c:url value='/plugins/talent.js'/>"></script>
<script type="text/javascript" src="<c:url value='/plugins/select2.full.js'/>"></script>
<script type="text/javascript" src="<c:url value='/plugins/select2.zh-CN.js'/>"></script>
<script type="text/javascript" src="<c:url value='/plugins/serializeJson.js'/>"></script>
<script type="text/javascript" src="<c:url value='/plugins/moment-with-locales.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/plugins/bootstrap-datetimepicker.js'/>"></script>
<script type="text/javascript" src="<c:url value='/plugins/jquery.form.js'/>"></script>
<script type="text/javascript" src="<c:url value='/plugins/bootstrapValidator.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/plugins/bootstrapValidator.zh_CN.js'/>"></script>
<script type="text/javascript" src="<c:url value='/plugins/datatable/bootstrap-table.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/plugins/datatable/bootstrap-table-zh-CN.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/plugins/datatable/extensions/mobile/bootstrap-table-mobile.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/plugins/datatable/extensions/crud/bootstrap-table-crud.js'/>"></script>
<script type="text/javascript" src="<c:url value='/plugins/bootstrap-treeview.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/plugins/jquery.ba-resize.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/plugins/echarts.common.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/plugins/bootstrap-editable.js'/>"></script>
<script type="text/javascript" src="<c:url value='/plugins/jquery-confirm.js'/>"></script>
<script type="text/javascript" src="<c:url value='/plugins/layer/layer.js'/>"></script>
<script type="text/javascript" src="<c:url value='/plugins/toastr.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/plugins/fileinput.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/plugins/fileinput_locale_zh.js'/>"></script>

<!--[if lt IE 9]>
	<script src="<c:url value='/plugins/html5shiv.min.js'/>"></script>
	<script src="<c:url value='/plugins/respond.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/plugins/base64.min.js'/>"></script>
<![endif]-->

<!--[if lte IE 6]>
	<script type="text/javascript" src="<c:url value='/bootstrap/js/bootstrap-ie.js'/>"></script>
<![endif]-->

<script type="text/javascript">
	var comboDataMap = new Map();
	var dataGridsMap = new Map();
	var waiting;
	$.fn.modal.Constructor.prototype.enforceFocus =function(){};
	
	toastr.options = {
		"closeButton": false,
		"debug": false,
		"newestOnTop": false,
		"progressBar": false,
		"rtl": false,
		"positionClass": "toast-top-right",
		"preventDuplicates": false,
		"onclick": null,
		"showDuration": 300,
		"hideDuration": 1000,
		"timeOut": 5000,
		"extendedTimeOut": 1000,
		"showEasing": "swing",
		"hideEasing": "linear",
		"showMethod": "fadeIn",
		"hideMethod": "fadeOut"
	}
	
	function startwaiting(){
		waiting = layer.load(0,{shade: [0.1,'#fff']});
	}
	
	function stopwaiting(){
		layer.close(waiting);
	}
	
	jQuery(document).ready(function($){
		$('.select2').select2({theme:"bootstrap"});
		$('ul[class="dropdown-menu dropdown-menu-right"] li a').on('click',function(){
			var inputElement = $(this).parent().parent().parent().parent().children('input');
			$(inputElement).val($(this).html());
			
			try{
				$('form').data('bootstrapValidator').updateStatus($(inputElement).attr('name'), 'NOT_VALIDATED', null).validateField($(inputElement).attr('name'));
			}catch (e){
	    		
	    	}
		});
		
		$('#SelectionsWindow').on('shown.bs.modal', function(){
	    	if(undefined != loadSelectionGridData){
	    		loadSelectionGridData();
	    	}
	   	});
		
		$('form').bootstrapValidator({excluded: [':disabled',':hidden',':not(:visible)']}).bootstrapValidator('validate');
	});
	
	function initcombobox(comboid,url_v,placeholder_v,allowclear_v){
		$('#' + comboid).select2({
			placeholder: {
			    id: "",
			    placeholder:placeholder_v
			},
			id: function(data){return {id:data.id};},
			language: "zh-CN",
			allowClear:allowclear_v,
			ajax:{
				dataType: 'json',
				delay: 250,
				url:url_v,
				data: function (params) {
					params.page = params.page || 1;
					return {
						searchText: params.term,
						page: params.page,
						rows:30
					};
				},
			    processResults: function (data, params) {
					return {
						results: data.rows,
						pagination:{
							more: (params.page * 30) < data.total
						}
					};
			    },
				cache:true
			},
			theme:"bootstrap"
		});
	}
	
	var extract_preselected_ids = function(element){
        var preselected_ids = [];
        if(element.val())
            $(element.val().split(",")).each(function () {
                preselected_ids.push({id: this});
            });
        return preselected_ids;
    };
    
    var preselect = function(preselected_ids){
        var pre_selections = [];
        for(index in selections)
            for(id_index in preselected_ids)
                if (selections[index].id == preselected_ids[id_index].id)
                    pre_selections.push(selections[index]);
        return pre_selections;
    };
	
	function fillFormFields(formID,obj,loadedcallback){
	    var key,value,tagName,type,arr;
	    for(x in obj){
	        key = x;
	        value = obj[x];
	        $("#"+formID+" :input[name='"+key+"'],#"+formID+" :input[name='"+key+"[]']").each(function(){
	            tagName = $(this)[0].tagName;
	            type = $(this).attr('type');
	            if(tagName=='INPUT'){
	                if(type=='radio'){
	                    $(this).attr('checked',$(this).val()==value);
	                }else if(type=='checkbox'){
	                    arr = value.split(',');
	                    for(var i =0;i<arr.length;i++){
	                        if($(this).val()==arr[i]){
	                            $(this).attr('checked',true);
	                            break;
	                        }
	                    }
	                }else{
	                    $(this).val(value);
	                }
	            }else if(tagName=='TEXTAREA'){
	            	$(this).val(value);
	            }else if(tagName=='SELECT'){
	            	if($(this).hasClass('select2-data-ajax')){
	            		if(null == value || '' == value){
	            			$(this).val('').trigger('change',{term:'',setvalue:true,remote:false});
	            		}else{
	            			$(this).trigger('change',{term:value,setvalue:true,remote:true}).val(value);
	            		}
	            	}else{
	            		$(this).val(value.toString()).trigger('change',{term:value.toString(),setvalue:true,remote:false});
	            	}
	            }
	        });
	    }
	    $('#'+formID).bootstrapValidator({excluded: [':disabled',':hidden',':not(:visible)']}).bootstrapValidator('validate');
	    stopwaiting();
	    if(undefined != loadedcallback){
	    	loadedcallback(obj);
	    }
	}
	
	function validForm(formid){
		$('#'+formid).bootstrapValidator('resetForm');
		$('#'+formid).bootstrapValidator({excluded: [':disabled',':hidden',':not(:visible)']}).bootstrapValidator('validate');
	}
	
	function loadFormData(formId,url_v,callback){
		startwaiting();
		$('#'+formId).bootstrapValidator('resetForm');
		$.ajax({
            type: "post",
            url: url_v,
            dataType: "json",
            success: function(data){
            	fillFormFields(formId,data,callback);
            },
            complete:function(xhr,ts){
            	stopwaiting();
            }
        });
	}
	
	function openWindow(windowId,formId,url_v,callback){
    	$('#' + windowId).unbind('shown.bs.modal');
    	$('#' + windowId).on('shown.bs.modal', function(){
    		loadFormData(formId,url_v,callback);      	
       	}).modal('show');
    }
	
	function removeData(url_v,ids_vv,callback){
		dialogbox("请确认", "确认删除此项目？",function(data){
			if(data){
				startwaiting();
				$.ajax({
		            type: "post",
		            url: url_v,
		            dataType: "json",
		            data: {ids:ids_vv},
		            success: function(data){
		            	 if(data.success == true){
		            		 if(undefined != callback){
		            			 callback(data);
		            		 }
		            	 }else{
		            		 errorbox(data.msg);
		            	 }
		            },complete:function(XMLHttpRequest, textStatus){
		            	stopwaiting();
		            }
	            });
			}
		});
	}
	
	function listGridIds(gridId,callback){
		var ids = '';
    	var selectedRows = $('#' + gridId).bootstrapTable('getAllSelections');
    	for(var i=0;i<selectedRows.length;i++){
    		ids = ids + ',' + selectedRows[i].id;
    	}
    	if('' == ids){
    		warningbox('请至少选择一条数据操作！');
    	}else{
    		ids = ids.substr(1);
    		if(undefined != callback){
    			callback(ids);
    		}
    	}
	}
	function loadGridData(gridId,url_v){
		var dg = $('#' + gridId).bootstrapTable('refresh',{url:url_v});
		dataGridsMap.put(gridId,dg);
	}
	
	function saveFormData(formId,url_v,callback){
		if($('#' + formId).data('bootstrapValidator').isValid()){
			startwaiting();
			$('#' + formId).ajaxSubmit({
	            type: 'post',
	            url: url_v,
	            success: function(data){
	            	stopwaiting();
	            	if(undefined != callback){
	            		callback(data);
	            	}
	            },
	            error:function(data){
	            	stopwaiting();
	            	if(undefined != callback){
	            		data.success=false;
	            		callback(data);
	            	}
	            }
	        });
		}
	}
	
	function loadComboxData(comboid,url_v,callback){
		$('#' + comboid).empty();
		$.ajax({
            type: "post",
            url: url_v,
            dataType: "json",
            success: function(data_v){
            	$('#' + comboid).select2({theme:"bootstrap",data:data_v});
            	if(undefined != callback){
            		callback(data_v);
            	}
            }
        });
	}
	
	function saveFormDataWithParams(formId,url_v,params_v,callback){
		if($('#' + formId).data('bootstrapValidator').isValid()){
			startwaiting();
			$('#' + formId).ajaxSubmit({
	            type: 'post',
	            url: url_v,
	            data:{exparams:params_v},
	            success: function(data){
	            	stopwaiting();
	            	if(undefined != callback){
	            		callback(data);
	            	}
	            },
	            error:function(data){
	            	stopwaiting();
	            	if(undefined != callback){
	            		data.success=false;
	            		callback(data);
	            	}
	            }
	        });
		}
	}
	
	function rowStyle(row, index) {
		var classes = ['active', 'success', 'info', 'warning', 'danger'];
		return {
			classes: classes[index % 5]
		};
	}
	
	function successtoast(msg){
		toastr["success"](msg);
    }
	
	function successbox(msg){
		$.alert({
			keyboardEnabled: true,
			icon: 'glyphicon glyphicon-ok-sign',
			backgroundDismiss: true,
		    autoClose: 'confirm|3000',
		    title: '成功',
		    content:msg,
		    confirmButton: '关闭',
		    confirmButtonClass: 'btn-success'
		});
    }
	
	function warningbox(msg){
		$.alert({
			keyboardEnabled: true,
			icon: 'glyphicon glyphicon-warning-sign',
			autoClose: 'confirm|5000',
		    title: '警告',
		    content:msg,
		    confirmButton: '关闭',
		    confirmButtonClass: 'btn-warning'
		});
    }
	
	function errorbox(msg){
		$.alert({
			keyboardEnabled: true,
			icon: 'glyphicon glyphicon-remove-sign',
		    title: '错误',
		    content:msg,
		    confirmButton: '关闭',
		    confirmButtonClass: 'btn-danger'
		});
    }
	
	function notifybox(msg){
		$.alert({
			keyboardEnabled: true,
			icon: 'glyphicon glyphicon-info-sign',
		    autoClose: 'confirm|5000',
		    backgroundDismiss: true,
		    title: '通知',
		    content:msg,
		    confirmButton: '关闭',
		    confirmButtonClass: 'btn-primary'
		});
    }
	
	function dialogbox(title,content,callback){
		$.confirm({
			keyboardEnabled: true,
			icon: 'glyphicon glyphicon-question-sign',
		    title: title,
		    content:content,
		    confirmButton: '确定',
		    cancelButton: '取消',
		    confirmButtonClass: 'btn-danger',
		    cancelButtonClass: 'btn-info',
		    confirm: function(){
		        if(undefined != callback){
		        	callback(true);
		        }
		    }
		});
	}
	
	var dictDataMap = new Map();
	function dictionaryConverter(ccode,value){
		var dictdata;
		var result = '';
		dictdata = dictDataMap.get(ccode);
		if(null==dictdata){
			$.ajax({
		        type: "post",
		        url: '<c:url value='/dict/convert/'/>',
		        dataType: "json",
		        data:{catalogcode:ccode},
		        async:false,
		        success: function(data){
		        	dictdata = data.rows;
		        	dictDataMap.put(ccode,data.rows);
		        }
		    });
		}
		for(var i=0;i<dictdata.length;i++){
			if(value == dictdata[i].dictcode){
	        	result = dictdata[i].dictvalue;
	        	break;
	        }
		}
		return result;
	}
	
	function remoteExportExcel(tableid,moduleclass,datatablename,queryformid){
		var griddata = $('#' + tableid).bootstrapTable('getData',false);
		if(0 == griddata.length){
			notifybox("没有要导出的数据！");
			return;
		}
		startwaiting();
		var tableOptions = $('#' + tableid).bootstrapTable('getOptions');
		var allColumns = tableOptions.columns[0];
		var visibleColumnsField = '';
		var visibleColumnsTitle = '';
		for(var i=0;i<allColumns.length;i++){
			if(allColumns[i].visible && '' != allColumns[i].field && '操作' != allColumns[i].title){
				visibleColumnsField = visibleColumnsField + ',' + allColumns[i].field;
				visibleColumnsTitle = visibleColumnsTitle + ',' + allColumns[i].title;
			}
		}
		visibleColumnsField = visibleColumnsField.substr(1);
		visibleColumnsTitle = visibleColumnsTitle.substr(1);
		
		$.ajax({
	        type: "post",
	        url: '<c:url value='/common/excel/export.do'/>',
	        dataType: "json",
	        data:jQuery.extend({},{reportName:datatablename,reportUrl:tableOptions.url,requestbody:JSON.stringify(jQuery.extend({},{page:1,rows:5000},$('#' + queryformid).serializeJson())),totalRows:tableOptions.totalRows,vcf:visibleColumnsField,vct:visibleColumnsTitle}),
	        success: function(data){
	        	stopwaiting();
	        	if(data.success == true){
					window.location.href=data.data+ "?fileName=" + encodeURI(data.msg);
				}
	        }
	    });
	}
	
	var importingTable = '';
	var importingTableModelclass = '';
	function commonImportExcel(tableid,moduleclass,datatablename,queryformid){
		$('#excelimportinput').fileinput('refresh',{uploadExtraData:{modelclass:moduleclass}});
		importingTable = tableid;
		importingTableModelclass = moduleclass;
		$('#CommonImportExcelWindow').modal('show');
	}
	
	var clientImportExcelCallback;
	function clientImportExcel(callback){
		if(undefined == callback || typeof(callback) != "function"){
			errorbox('请传入一个回调函数作为参数！');
			return;
		}
		clientImportExcelCallback = callback;
		$('#ClientImportExcelWindow').modal('show');
		$('#ClientImportExcelWindow').on('hidden.bs.modal', function (e) {
			$('#ClientImportExcelGrid').bootstrapTable('destroy');
		});
	}
	
	//----------------------------------------------------------------------------------------------
	var selectionsData;
	function showSelectionWindow(){
		$('#SelectionsWindow').modal('show');
	}
	
	function returnSelections(){
		selectionsData = $('#SelectionsGrid').bootstrapTable('getAllSelections');
		$('#SelectionsWindow').modal('toggle');
		if(undefined != assignValueFunc){
			assignValueFunc(selectionsData);
		}
	}
	
	function closeSelections(){
		$('#SelectionsWindow').modal('toggle');
	}
	//----------------------------------------------------------------------------------------------
	function returnGridPopup(){
		$('#GridPopupWindow').modal('toggle');
	}
	
	function setDatatableTitle(tableid,field,title){
		$('#' + tableid + ' th[data-field="' + field + '"] div[class="th-inner "]').html(title);
	}
</script>
<div class="modal fade" id="SelectionsWindow" tabindex="-1" role="dialog" aria-labelledby="SelectionsWindowLabel" aria-hidden="true" style="z-index:99999;">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">请选择...</h4>
			</div>
			<div class="modal-body" style="padding-top:0px;">
				<table id="SelectionsGrid" data-toggle="table" data-pagination="true"
				   data-page-list="[10,30,50]"
				   data-row-style="rowStyle"
				   data-show-refresh="true"
				   data-search="true"
				   data-click-to-select="true"
				   data-single-select="true">
				</table>
			</div>
			<div class="modal-footer">
				<div class="form-inline btn-group-sm" role="form">
					<button type="button" class="btn btn-success" data-toggle="modal" onclick="returnSelections()">
						<span class="glyphicon glyphicon-import" aria-hidden="true"></span>&nbsp;取回
					</button>
					<button type="button" class="btn btn-default" data-toggle="modal" onclick="closeSelections()">
						<span class="glyphicon glyphicon-off" aria-hidden="true"></span>&nbsp;关闭
					</button>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="GridPopupWindow" tabindex="-1" role="dialog" aria-labelledby="GridPopupWindowLabel" aria-hidden="true" style="z-index:99999;">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">请选择...</h4>
			</div>
			<div class="modal-body" style="padding:0px;">
				<iframe class="J_iframe" name="popupframe" width="100%" height="500" frameborder="0" seamless></iframe>
			</div>
			<div class="modal-footer">
			    <div class="form-inline btn-group-sm" role="form">
					<button type="button" class="btn btn-success" data-toggle="modal" onclick="returnGridPopup()">
						<span class="glyphicon glyphicon-import" aria-hidden="true"></span>&nbsp;取回
					</button>
				</div>
			</div>
		</div>
	</div>
</div>
<%-- <div class="modal fade" id="CommonImportExcelWindow" tabindex="-1" role="dialog" aria-labelledby="CommonImportExcelLabel" aria-hidden="true" style="z-index:99999;">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">导入Excel</h4>
			</div>
			<div class="modal-body" style="padding-top:0px;">
				<div class="row">
					<div class="col-sm-11" style="padding-top:15px;padding-right:5px;padding-left:5px;">
						<div class="form-group">
		                    <input id="excelimportinput" class="file" type="file" data-language="zh" multiple></input>
		                    <div id="excelimportinput-error" style="margin-top:10px;display:none"></div>
		                </div>
					</div>
					<div class="col-sm-1" style="padding-top:15px;padding-left:0px;">
						<div class="form-group">
							<button type="button" class="btn btn-success" data-toggle="modal" onclick="importExcelAction()">
								<span class="glyphicon glyphicon-import" aria-hidden="true"></span>&nbsp;导入
							</button>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-6" style="padding-left:5px;padding-right:5px;">
						<table id="ImportExcelColumnGrid" data-toggle="table" data-row-style="rowStyle" data-pagination="false" data-mobile-responsive="true" data-use-row-attr-func="true" data-reorderable-rows="true">
							<thead>
								<tr>
									<th data-field="id" data-checkbox="true">ID</th>
									<th data-field="index" data-visible="false"></th>
									<th data-field="column" data-halign="center" class="text-center">列名</th>
								</tr>
							</thead>
						</table>
					</div>
					<div class="col-sm-6" style="padding-left:5px;padding-right:5px;">
						<table id="ImportEntityFieldsGrid" data-toggle="table" data-row-style="rowStyle" data-pagination="false" data-mobile-responsive="true" data-use-row-attr-func="true" data-reorderable-rows="true">
							<thead>
								<tr>
									<th data-field="id" data-checkbox="true" data-formatter="stateFormatter">ID</th>
									<th data-field="field" data-visible="false"></th>
									<th data-field="title" data-halign="center" class="text-center">名称</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="ClientImportExcelWindow" tabindex="-1" role="dialog" aria-labelledby="ClientImportExcelLabel" aria-hidden="true" style="z-index:99999;">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">导入Excel</h4>
			</div>
			<div class="modal-body" style="padding-top:0px;">
				<div class="row">
					<div class="col-sm-11" style="padding-top:15px;padding-right:5px;padding-left:5px;">
						<div class="form-group">
		                    <input id="clientimportexcelinput" class="file" type="file" data-language="zh" multiple></input>
		                </div>
					</div>
					<div class="col-sm-1" style="padding-top:15px;padding-left:0px;">
						<div class="form-group">
							<button type="button" class="btn btn-success" data-toggle="modal" onclick="clientImportExcelAction()">
								<span class="glyphicon glyphicon-import" aria-hidden="true"></span>&nbsp;取回
							</button>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12" style="padding-left:5px;padding-right:5px;">
						<table id="ClientImportExcelGrid" data-mobile-responsive="true"></table>
					</div>					
				</div>
			</div>
		</div>
	</div>
</div> --%>
<script type="text/javascript">
	/* $("#excelimportinput").fileinput({
	    uploadUrl: "<c:url value='/common/excel/preimport.do'/>",
	    uploadAsync: true,
	    showPreview: false,
	    allowedFileExtensions: ['xls', 'xlsx'],
	    maxFileCount: 5,
	    elErrorContainer: '#excelimportinput-error'
	}).on('fileuploaded', function(event, data, id, index) {
		if(undefined != data.jqXHR.responseJSON.columns){
			$('#ImportExcelColumnGrid').bootstrapTable('removeAll');
		    $('#ImportEntityFieldsGrid').bootstrapTable('removeAll');
		    $('#ImportExcelColumnGrid').bootstrapTable('load',data.jqXHR.responseJSON.columns);
		    $('#ImportEntityFieldsGrid').bootstrapTable('load',data.jqXHR.responseJSON.fields);
		}else{
			$('#' + importingTable).bootstrapTable('refresh');
			if(data.jqXHR.responseJSON.success){
				successtoast(data.jqXHR.responseJSON.msg);
				$('#CommonImportExcelWindow').modal('toggle');
			}else{
				errorbox(data.jqXHR.responseJSON.msg);
			}
		}
	});
	
	$("#clientimportexcelinput").fileinput({
	    uploadUrl: "<c:url value='/common/excel/clientimport.do'/>",
	    uploadAsync: true,
	    showPreview: false,
	    allowedFileExtensions: ['xls', 'xlsx'],
	    maxFileCount: 5
	}).on('fileuploaded', function(event, data, id, index) {
		var gridcolumns = data.jqXHR.responseJSON.mores;
		var griddata = data.jqXHR.responseJSON.rows;
		$('#ClientImportExcelGrid').bootstrapTable('destroy').bootstrapTable({columns:gridcolumns,onPostBody:function(data){$('#ClientImportExcelGrid').bootstrapTable('checkAll');}}).bootstrapTable('load',griddata);
	});
	
	$('#ImportExcelColumnGrid').bootstrapTable({
		onPostBody:function(data){
    		$('#ImportExcelColumnGrid').bootstrapTable('checkAll');
        }
    });
	
	function clientImportExcelAction(){
		clientImportExcelCallback($('#ClientImportExcelGrid').bootstrapTable('getAllSelections'));
		$('#ClientImportExcelWindow').modal('toggle');
	}
	
	function importExcelAction(){
		var cindexs = '';
		var mfields = '';
		var mfieldnames = '';
    	var columnSelectedRows = $('#ImportExcelColumnGrid').bootstrapTable('getAllSelections');
    	var moduleSelectedRows = $('#ImportEntityFieldsGrid').bootstrapTable('getAllSelections');
    	for(var i=0;i<columnSelectedRows.length;i++){
    		cindexs = cindexs + ',' + columnSelectedRows[i].index;
    	}
    	if('' == cindexs){
    		warningbox('请至少选择一个字段进行导入！');
    	}else{
    		for(var i=0;i<moduleSelectedRows.length;i++){
    			mfields = mfields + ',' + moduleSelectedRows[i].field;
    			mfieldnames = mfieldnames + ',' + moduleSelectedRows[i].title;
        	}
    		if('' == mfields){
    			warningbox('导入失败，无法加载目标字段！');
    		}else{
    			$('#excelimportinput').fileinput('refresh',{uploadUrl: "<c:url value='/common/excel/import.do'/>",uploadExtraData:{cis:cindexs.substr(1),mfs:mfields.substr(1),mfns:mfieldnames.substr(1),modelclass:importingTableModelclass}});
    			$('#excelimportinput').fileinput('upload');
    		}
    	}
	} */
	
	function stateFormatter(value, row, index) {
		return {
            disabled: true,
            checked: true
        }
    }
</script>
<iframe id="exportArea" height="0" hidden="true"></iframe>