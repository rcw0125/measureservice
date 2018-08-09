<%@ page contentType="text/html;charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<meta charset="utf-8">
<title>远程集中计量系统V3.0</title>
<meta name="description" content="山东天利和软件股份有限公司远程集中计量系统">
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
<link href="<c:url value='/css/animations.css'/>" rel="stylesheet">
<link href="<c:url value='/css/style.css'/>" rel="stylesheet">
<link href="<c:url value='/css/bootstrap-datetimepicker.css'/>" rel="stylesheet">
<link href="<c:url value='/css/select2.min.css'/>" rel="stylesheet">
<link href="<c:url value='/css/select2-bootstrap.css'/>" rel="stylesheet">
<link href="<c:url value='/plugins/datatable/bootstrap-table.min.css'/>" rel="stylesheet">
<link href="<c:url value='/css/bootstrapValidator.min.css'/>" rel="stylesheet">

<!--[if lte IE 6]>
	<link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap-ie6.css'/>">
<![endif]-->

<!--[if lte IE 7]>
	<link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/ie.css'/>">
<![endif]-->

<script type="text/javascript" src="<c:url value='/plugins/jquery.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/bootstrap/js/bootstrap.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/plugins/jquery.bootstrap.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/plugins/modernizr.js'/>"></script>
<script type="text/javascript" src="<c:url value='/plugins/talent.js'/>"></script>
<script type="text/javascript" src="<c:url value='/plugins/map.js'/>"></script>
<script type="text/javascript" src="<c:url value='/plugins/select2.full.js'/>"></script>
<script type="text/javascript" src="<c:url value='/plugins/serializeJson.js'/>"></script>
<script type="text/javascript" src="<c:url value='/plugins/moment-with-locales.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/plugins/bootstrap-datetimepicker.js'/>"></script>
<script type="text/javascript" src="<c:url value='/plugins/jquery.form.js'/>"></script>
<script type="text/javascript" src="<c:url value='/plugins/bootstrapValidator.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/plugins/bootstrapValidator.zh_CN.js'/>"></script>
<script type="text/javascript" src="<c:url value='/plugins/datatable/bootstrap-table.js'/>"></script>
<script type="text/javascript" src="<c:url value='/plugins/datatable/bootstrap-table-zh-CN.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/plugins/bootstrap-treeview.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/plugins/jquery.ba-resize.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/plugins/spin.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/plugins/Chart.js'/>"></script>
<script type="text/javascript" src="<c:url value='/plugins/echarts.common.min.js'/>"></script>

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
	$.fn.modal.Constructor.prototype.enforceFocus =function(){};
	
	var waitingSpiner;	
	var waitingopts = {
		lines: 13, // The number of lines to draw
		length: 29, // The length of each line
		width: 8, // The line thickness
		radius: 27, // The radius of the inner circle
		scale: 1, // Scales overall size of the spinner
		corners: 1, // Corner roundness (0..1)
		color: '#000', // #rgb or #rrggbb or array of colors
		opacity: 0.55, // Opacity of the lines
		rotate: 0, // The rotation offset
		direction: 1, // 1: clockwise, -1: counterclockwise
		speed: 1.2, // Rounds per second
		trail: 100, // Afterglow percentage
		fps: 20, // Frames per second when using setTimeout() as a fallback for CSS
		zIndex: 2e9, // The z-index (defaults to 2000000000)
		className: 'spinner', // The CSS class to assign to the spinner
		top: '49%', // Top position relative to parent
		left: '49%', // Left position relative to parent
		shadow: false, // Whether to render a shadow
		hwaccel: false, // Whether to use hardware acceleration
		position: 'absolute' // Element positioning
	};
	
	function startWaiting(){
		waitingSpiner = new Spinner(waitingopts).spin(document.getElementById('waitingdiv'));
	}
	
	function stopWaiting(){
		waitingSpiner.stop();
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
		
		$('.select2-data-ajax').each(function(){
			$(this).select2({
				placeholder: {
				    id: "",
				    placeholder: "请选择"
				},
				id: function(data){return {id:data.id};},
				language: "zh-CN",
				allowClear:true,
				ajax: {
					url:$(this).attr('ajax-url'),
					dataType:'json',				
					data: function (params) {
						return {
							q:params.term
						};
				    },
				    processResults: function (data, params){
						return{
			                results:data.rows
			            };
					},
					cache:false
				},
				theme:"bootstrap",
		        formatResult: function(obj){return obj[opts.text]},  
		        formatSelection:function(obj){return obj[opts.text]},  
		        escapeMarkup:function(m){return m;}
			});
		});
		
		$(".bootstrap-table").resize(function(){
			try
			{
				window.parent.iFrameHeight();
			}
			catch(e)
			{
	    		
	    	}
		});
	});
	
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
	
	function loadFormData(formID,obj){
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
	            }else if(tagName=='SELECT' || tagName=='TEXTAREA'){
	            	var $this = $(this);
	                if($this.hasClass('select2-data-ajax')){
	                	$this.trigger('change.select2');
	        	    	var ajax_url = $this.attr('ajax-url');
	                	$this.select2({
	                		placeholder: {
	        				    id: "",
	        				    placeholder: "请选择"
	        				},
	        				id: function(data){return {id:data.id};},
	        				language: "zh-CN",
	        				allowClear:true,
	        				ajax: {
	        					url:ajax_url,
	        					dataType:'json',				
	        					data: function (params) {
	        						return {
	        							q:params.term
	        						};
	        				    },
	        				    processResults: function (data, params){
	        				    	if(undefined == params.term || '' == params.term){
	        				    		comboDataMap.put('combo' + $this.attr('id'),data.rows);
	        				    	}
	        				    	
	        						return {  
	        			                results:data.rows
	        			            };
	        					},
	        					cache:false
	        				},
	        		        initSelection: function (element,callback){
	        		        	var aaa = element.ajax;
	        		        	var cdata = comboDataMap.get('combo' + $this.attr('id'));
	        		        	if(null == value){
	        		        		value = '';
	        		        	}
	        		        	var values = value.split(',');
	        		        	if(null == cdata){
	        		        		$.ajax(ajax_url,{
		        	                    data:{q:value},
		        	                    dataType: "json",
		        	                    async: false,
		        	                    success:function(data){
		        	                    	var selecteds = [];
		        	                    	comboDataMap.put('combo' + $this.attr('id'),data.rows);
		        	                    	$("#"+$this.attr('id')+" option").remove();
		        	                    	for(var i=0;i<data.rows.length;i++){
		        	                    		for(var j=0;j<values.length;j++){
		        	                    			if(values[j] == data.rows[i].id){
		        	                    				selecteds.push(data.rows[i]);
				        	                			$this.append('<option value="'+data.rows[i].id+'">'+data.rows[i].text+'</option>')
				        	                		}
		        	                    		}
			        	                	}
		        	                    	callback(selecteds);
		        	                    }
		        	                });
	        		        	}else{
	        		        		var selecteds = [];
        		        			for(var i=0;i<cdata.length;i++){
        		        				for(var j=0;j<values.length;j++){
        		        					if(values[j] == cdata[i].id){
		        	                			selecteds.push(cdata[i]);
		        	                		}
        		        				}		        	                		
	        	                	}
        		        			callback(selecteds);
	        		        	}
	        		        	return comboDataMap.get('combo' + $this.attr('id'));
	        		        },
	        				theme:"bootstrap",
	        		        formatResult: function(obj){return obj[opts.text]},  
	        		        formatSelection:function(obj){return obj[opts.text]},  
	        		        escapeMarkup:function(m){return m;}
	        			});
	                }else if($this.hasClass('select2')){
	                	if(null == value){
    		        		value = '';
    		        	}
	                	$this.val(value);
	                	$this.trigger('change.select2');
	                }
	                $this.val(value);
	            }
	        });
	    }
	    try{
			$('#'+formID).data('bootstrapValidator').updateStatus($(inputElement).attr('name'), 'NOT_VALIDATED', null).validateField($(inputElement).attr('name'));
		}catch (e){
    		
    	}
	}
	
	function rowStyle(row, index) {
		var classes = ['active', 'success', 'info', 'warning', 'danger'];
		return {
			classes: classes[index % 5]
		};
	}
	
	function toastMessage(type,title,content){
    	//toastr[type](content,title);
		$.messager.alert(title,content);
    }
	
	function initSelect(iid,url_v){
		$("#"+iid).select2({
			id: function(data){return {id:data.id};},
			ajax: {
				url:url_v,
				dataType:'json',				
				delay: 250,
				data: function (params) {
					return {
						q:params.term
					};
			    },
			    processResults: function (data, params) {
					return {  
		                results:data.rows
		            };
				},
				cache:false
			},
			theme:"bootstrap"
		});
	}
	
	var dictDataMap = new Map();
	function dictionaryConverter(url,dcode,value){
		var dictdata;
		var result = '';
		dictdata = dictDataMap.get(dcode);
		if(null==dictdata){
			$.ajax({
		        type: "post",
		        url: url,
		        dataType: "json",
		        async:false,
		        success: function(data){
		        	dictdata = data.rows;
		        	dictDataMap.put(dcode,data.rows);
		        }
		    });
		}
		
		for(var i=0;i<dictdata.length;i++){
			if(value == dictdata[i].id){
	        	result = dictdata[i].text;
	        	break;
	        }
		}
		return result;
	}
	
	function commonExportExcel(reportname,tableid,queryformid){
		startWaiting();
		var griddata = $('#' + tableid).bootstrapTable('getData',false);
		if(0 == griddata.length){
			toastMessage("error","很遗憾","没有要导出的数据！");
			return;
		}
		
		var tableOptions = $('#' + tableid).bootstrapTable('getOptions');
		var allColumns = tableOptions.columns[0];
		var visibleColumnsField = '';
		var visibleColumnsTitle = '';
		for(var i=0;i<allColumns.length;i++){
			if(allColumns[i].visible){
				visibleColumnsField = visibleColumnsField + ',' + allColumns[i].field;
				visibleColumnsTitle = visibleColumnsTitle + ',' + allColumns[i].title;
			}
		}
		visibleColumnsField = visibleColumnsField.substr(1);
		visibleColumnsTitle = visibleColumnsTitle.substr(1);
		
		$.ajax({
	        type: "post",
	        url: '<c:url value='/excel/generate.do'/>',
	        dataType: "json",
	        data:jQuery.extend({},{reportName:reportname,reportUrl:tableOptions.url,requestbody:JSON.stringify(jQuery.extend({},{offset:0,limit:5000},$('#' + queryformid).serializeJson())),totalRows:tableOptions.totalRows,vcf:visibleColumnsField,vct:visibleColumnsTitle}),
	        success: function(data){
	        	stopWaiting();
	        	if(data.success == true){
					window.location.href=data.downloadurl+ "?fileName=" + encodeURI(data.fileName);
				}
	        }
	    });
	}
</script>
<div id="waitingdiv"></div>
<iframe id="exportArea" height="0" hidden="true"></iframe>