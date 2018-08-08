<%@ page contentType="text/html;charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="t" uri="http://www.talent.com/jsp/anyonelse"%>
<t:html>
<head>
	<jsp:include page="common.jsp" flush="true"/>
	<link href="<c:url value='/css/bootstrap-duallistbox.min.css'/>" rel="stylesheet">
	<link href="<c:url value='/plugins/datatable/extensions/reorder-rows/bootstrap-table-reorder-rows.css'/>" rel="stylesheet">
	<script type="text/javascript" src="<c:url value='/plugins/jquery.bootstrap-duallistbox.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/plugins/datatable/extensions/reorder-rows/jquery.tablednd.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/plugins/datatable/extensions/reorder-rows/bootstrap-table-reorder-rows.min.js'/>"></script>
	<style type="text/css">
		.info-container{
			display:none;
		}
		
		.dtformat{
			margin:0px !important;
			padding:0px !important;
		}
		
		.dtformat .fixed-table-container{
			border:0px;
		}
		
		.dtformat .bootstrap-table .th-inner{
			display:none;
		}
		
		.fixed-table-body {
		    overflow-x: scoll;
		    overflow-y: scoll;
		    height: 100%;
		}
	</style>
</head>
<body class="container-fluid" style="padding-top:15px;">
	<div class="row">
		<div class="col-sm-4">
			<div class="panel panel-warning" style="width:100%;">
				<div class="panel-heading">路线列表</div>
				<div class="panel-body" style="padding-top:0px;">
					<t:datatable url="/workline/queryPage.do" entities="com.talent.materialflow.model.Workline" id="worklineGrid" showsearch="true" showcrud="true" btntypes="add,remove" btnfuncs="addWorkline,removeWorkline" hiddencolumns="remark,queryword,validflag,creator,cdate,updater,udate,canceler,canceldate,classindex,classtype,usermemo,sysmemo"/>
				</div>
			</div>
		</div>
		<div class="col-sm-8">
			<div class="panel panel-info" style="width:100%;">
				<div class="panel-heading">路线详情</div>
				<div class="panel-body" style="padding-top:0px;">
					<form id="worklineitemForm" onsubmit="return false;" style="padding-top:15px;">
						<input type="hidden" id="id" name="id" value="-1"/>
						<input type="hidden" id="fid" name="fid" value="-1"/>
						<input type="hidden" id="sn" name="sn" value="1"/>
						<input type="hidden" id="fromid" name="fromid" value="0"/>
						<input type="hidden" id="worklinecode_item" name="worklinecode" value=""/>
						<div class="row">
							<div class="col-sm-12">
								<div class="form-inline btn-group-sm" style="padding-bottom:10px;">
									<button id="addworklineitemBtn" type="button" class="btn btn-success" data-toggle="modal">
										<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;添加
									</button>
									<button id="saveworklineitemBtn" type="button" class="btn btn-warning" data-toggle="modal">
										<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>&nbsp;保存
									</button>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<t:textbox id="worklinename_item" name="worklinename" label="路线名称" readonly="true"/>
							</div>
							<div class="col-sm-3">
								<t:textbox id="queryword_item" name="queryword" label="拼&nbsp;&nbsp;音&nbsp;&nbsp;头" readonly="true"/>
							</div>
							<div class="col-sm-3">
								<t:textbox id="usermemo_item" name="usermemo" label="备　　注" readonly="true"/>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<table id="worklineitemGrid" data-toggle="table" data-pagination="false" data-row-style="rowStyle" data-click-to-select="true" data-single-select="true" data-use-row-attr-func="true" data-reorderable-rows="true">
									<thead>
										<tr>
											<th data-halign="center" data-radio="true"></th>
											<th data-field="id" data-halign="center" data-visible="false"></th>							
											<th data-field="sn" data-halign="center">序号</th>
											<th data-field="nodecode" data-halign="center" data-formatter="nodecodeFormatter">节点</th>
											<th data-field="nodecode" data-halign="center" data-formatter="pointtimeFormatter" data-class="dtformat">时间（分钟）</th>
											<th data-width="65px" data-align="center" data-valign="middle" data-formatter="operateFormatter2" data-events="operateEvents2">操作</th>
										</tr>
									</thead>
								</table>
							</div>
							<div class="col-sm-6">
								<div class="row">
									<div class="col-sm-6">
										<t:combobox id="nodecode" label="节点名称" url="/bcommon/queryLinkinfo.do?operatype=1"/>
										<input type="hidden" id="nodename" name="nodename" value=""/>
									</div>
									<div class="col-sm-6">
										<t:numberbox id="timecost" label="节点时间" scale="0"/>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12">
										<select id="workpointcode" name="workpointcode" multiple="multiple" size="10"></select>
										<script>
											var workpointselect = $('#workpointcode').bootstrapDualListbox({filterPlaceHolder:'过滤作业点',infoText:false});
										</script>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="worklineWindow" tabindex="-1" workline="dialog" aria-labelledby="worklineWindowLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">作业路线</h4>
				</div>
				<div class="modal-body">
					<form id="worklineForm">
						<div class="row">
							<div class="col-sm-12">
								<input type="hidden" id="id" name="id" value="0"/>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<t:textbox id="worklinename" label="路线名称" require="true"/>
							</div>
							<div class="col-sm-6">
								<t:textbox id="worklinecode" label="路线编码" require="true"/>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<t:textbox id="queryword" label="拼&nbsp;&nbsp;音&nbsp;&nbsp;头" require="true"/>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<t:textbox id="usermemo" label="用户备注" require="false"/>
							</div>
							<div class="col-sm-6">
								<t:textbox id="sysmemo" label="系统备注" require="false"/>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer btn-group-sm">
				    <button type="button" class="btn btn-success" id="worklineSaveBtn">保存</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		jQuery(document).ready(function($){
			$('#worklineSaveBtn').on('click',function(){
				saveFormData('worklineForm','<c:url value="/workline/addoredit.do"/>',function(data){
					$('#worklineWindow').modal('toggle');
                	$('#worklineGrid').bootstrapTable('refresh');
				});
	        });
			loadWorkpointSelect($('#nodecode').find("option:selected").text());
			$('#saveworklineitemBtn').click(function(){
				saveFormData('worklineitemForm','<c:url value="/worklineitem/addoredit.do"/>',function(data){
					$('#worklineitemGrid').bootstrapTable('refresh',{url:'<c:url value="/worklineitem/queryPage.do"/>?worklinecode=' + $('#worklinecode_item').val()});
				});
			});
			$('#addworklineitemBtn').click(function(){
				if('' == $('#worklinecode_item').val().trim()){
					errorbox('请先选择作业路线！');
				}else if($('#worklineitemGrid').bootstrapTable('getData').length>0 && $('#worklineitemGrid').bootstrapTable('getSelections') <=0){
					errorbox('请在左侧列表选择上一作业点！');
				}else{
					$('#worklineitemForm #id').val(-1);
					$('#worklineitemForm #timecost').val(0);
					var selectedLine = $('#worklineitemGrid').bootstrapTable('getSelections');
					if(selectedLine.length > 0){
						$('#worklineitemForm #presn').val(selectedLine[0].sn);
				    	$('#worklineitemForm #sn').val(selectedLine[0].sn + 1);
				    	$('#worklineitemForm #fromid').val(selectedLine[0].id);
					}else{
						$('#worklineitemForm #presn').val(0);
				    	$('#worklineitemForm #sn').val(1);
				    	$('#worklineitemForm #fromid').val(0);
					}
					successtoast('新建成功，完成表单后保存作业点！');
				}
			});
			$('#worklinename').on('blur',function(){
				$('#worklinecode').val(pinyin.getFullChars($(this).val()));
				$('#queryword').val(pinyin.getCamelChars($(this).val()));
				validForm('worklineForm');
			});
		});
		
		$('#worklineGrid').bootstrapTable({
			onCheck:function(row,element){
				$('#worklineitemForm input[id="worklinecode_item"]').val(row.worklinecode);
				$('#worklineitemForm input[id="fid"]').val(row.id);
				$('#worklineitemForm input[id="worklinename_item"]').val(row.worklinename);
				$('#worklineitemForm input[id="queryword_item"]').val(row.queryword);
				$('#worklineitemForm input[id="usermemo_item"]').val(row.usermemo);
				worklinecodeSelectBack(row);
			}
		});
		
		function addWorkline(){
			openWindow('worklineWindow','worklineForm','<c:url value="/workline/loadform.do"/>?id=-1',function(data){
				
			});
		}
		
		function removeWorkline(){
			listGridIds('worklineGrid',function(ids){
        		removeData('<c:url value="/workline/del.do"/>',ids,function(data){
        			$('#worklineGrid').bootstrapTable('refresh');
        		});
        	});
		}
		
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
            	openWindow('worklineWindow','worklineForm','<c:url value="/workline/loadform.do"/>?id=' + row.id);
            },
            'click .remove': function (e, value, row) {
            	removeData('<c:url value="/workline/del.do"/>',row.id,function(data){
        			$('#worklineGrid').bootstrapTable('refresh');
        		});
            }
        };
        $('#nodecode').change(function (evt) {
			$('#nodename').val($(this).find("option:selected").text());
			loadWorkpointSelect($(this).find("option:selected").text());
		});
		
		function worklinecodeSelectBack(data){
			$('#worklineitemForm #id').val(-1);
			$('#worklineitemForm #timecost').val(0);
			loadWorkpointSelect($('#nodecode').find("option:selected").text());
			$('#worklineitemGrid').bootstrapTable('refresh',{url:'<c:url value="/worklineitem/queryPage.do"/>?worklinecode=' + $('#worklinecode_item').val()});
		}
		
		var worklineitemGridData;
		
		$('#worklineitemGrid').bootstrapTable({
			onLoadSuccess: function (data1) {
				worklineitemGridData = data1;
				for(var j=0;j<data1.rows.length;j++){
					$('#linetimedesign' + data1.rows[j].id).bootstrapTable({
						onLoadSuccess: function (data) {
							for(var i=0;i<data.rows.length;i++){
								$('#timeset' + data.rows[i].id).editable({
								    type: 'text',
								    pk: data.rows[i].id,
								    url: '<c:url value="/worklineitem/updateNodetimecost.do"/>',
								    title: '请输入时间（分钟）',
								    success: function(response, newValue) {
								    	//$('#timeset' + data.rows[i].id).href="javascript:void(0)";
								    }
								});
							}
						}
			    	}).bootstrapTable('refresh',{url:'<c:url value="/worklineitem/loadtimedesign.do"/>?toid=' + data1.rows[j].id});
				}
		    },
		    onCheck:function(row,element){
		    	loadFormData('worklineitemForm','<c:url value="/worklineitem/loadform.do"/>?id=' + row.id,function(data){
		    		loadWorkpointSelect($('#nodecode').find("option:selected").text(),data.workpointcode);
		    	});
		    },onReorderRow:function(data){
		    	var sns = '';
		    	var ids = '';
		    	for(var i=0;i<data.length;i++){
		    		sns = sns + ',' + (i+1);
		    		ids = ids + ',' + data[i].id;
		    	}
		    	$.ajax({
		            type: "post",
		            url:'<c:url value="/worklineitem/reorder"/>',
		            dataType: "json",
		            data:{sns:sns.substr(1),ids:ids.substr(1),worklinecode:$('#worklinecode').val()},
		            success: function(data){
		            	$('#worklineitemGrid').bootstrapTable('refresh');
		            }
		        });
		    }
		});
		
		function loadWorkpointSelect(nodename,selected){
			workpointselect.empty();
			if('计毛' == nodename || '计皮' == nodename){
				nodename = '衡器';
			}else if('出库' == nodename || '入库' == nodename){
				nodename = '库房';
			}else if('进厂' == nodename || '出厂' == nodename || '制卡' == nodename){
				nodename = '门岗';
			}
			$.ajax({
	            type: "post",
	            url:'<c:url value="/workpoint/querybytype.do"/>',
	            dataType: "json",
	            data:{linktype:nodename},
	            success: function(data){
	            	var selectedOptions = new Array();
	            	if(undefined != selected){
	            		selectedOptions = selected.split(',');
	            	}
	            	var selectFlag = "";
	            	for(var i=0;i<data.length;i++){
	            		selectFlag = "";
	            		for(var j=0;j<selectedOptions.length;j++){
	            			if(data[i].workpointcode == selectedOptions[j]){
	            				selectFlag = "selected";
	            				break;
	            			}
	            		}
	            		workpointselect.append('<option value="'+data[i].workpointcode+'" '+selectFlag+'>'+data[i].workpointname+'</option>');
	            	}
	            	workpointselect.bootstrapDualListbox('refresh', true);
	            }
	        });
		}
		
		function pointtimeFormatter(value, row, index) {
			return '<table id="linetimedesign'+row.id+'" data-toggle="table" data-classes="table table-no-bordered" data-pagination="false"><thead><tr><th data-field="fromid" data-formatter="innerPointtimeFormatter" data-events="innerPointtimeEvents"></th></tr></thead></table>';
		}
		
		window.innerPointtimeEvents = {
	        'click .remove': function (e, value, row) {
	        	removeData('<c:url value="/worklinetimecost/del.do"/>',row.id,function(data){
	        		$('#linetimedesign' + row.id).bootstrapTable('refresh');
        		});
	        }
	    };
	    function innerPointtimeFormatter(value, row, index) {
	    	var result = '';
	    	for(var i=0;i<worklineitemGridData.rows.length;i++){
	    		if(value == worklineitemGridData.rows[i].id){
	    			$('#nodecode option').each(function () {
	    			    var $option = $(this);
	    			    var text = $option.html();
	    			    var id = $option.val();
	    			    if(worklineitemGridData.rows[i].nodecode == id){
	    			    	result =  text;
	    			    }
	    			});
	    			break;
	    		}
	    	}
	        return [
	            '<div class="pull-left">',
	            '至上个【',
	            result,
	            '】的作业时间限定为',
	            '【<a id="timeset'+row.id+'" href="javascript:void(0)">'+row.timecost+'</a>】分钟',
	            '</div>',
	            '<div class="pull-right">',
	            '<a class="remove" href="javascript:void(0)" title="Remove">',
	            '<i class="glyphicon glyphicon-remove"></i>',
	            '</a>',
	            '</div>'
	        ].join('');
	    }
		
		function nodecodeFormatter(value, row, index) {
			//return dictionaryConverter('nodecode',value);
			var result;
			$('#nodecode option').each(function () {
			    var $option = $(this);
			    var text = $option.html();
			    var id = $option.val();
			    if(value == id){
			    	result =  text;
			    }
			});
			return result;
		}
		
		function operateFormatter2(value, row, index) {
			return [
                    '<div class="pull-right">',
                	'<a id="timeSettingPopover'+row.id+'" class="time" href="javascript:void(0)" title="请选择节点和维护时间">',
                	'<i class="glyphicon glyphicon-time"></i>',
                	'</a>　',
                	'<a class="cancel" href="javascript:void(0)" title="作废">',
                	'<i class="glyphicon glyphicon-trash"></i>',
                	'</a>',
                	'</div>'
            		].join('');
        }
        window.operateEvents2 = {
       		'click .time': function (e, value, row) {
       			$('#timeSettingPopover' + row.id).popover({
       				html : true,
       				placement:'left',
       				title: function() {
       					return '请选择节点和维护时间';
       				},
       				content: function() {
       					return '<div class="container-fluid" style="width:200px;"><div class="row"><div class="col-sm-12"><div class="input-group input-group-sm"><select id="nodecode_'+row.id+'" name="nodecode_'+row.id+'" class="form-control select2" placeholder="节点名称" data-bv-notempty data-bv-notempty-message="请填写节点名称!"></select></div></div></div><div class="row"><div class="col-sm-12" style="padding-top:10px;"><div class="input-group input-group-sm"><input type="text" class="form-control" placeholder="节点时间" id="pointtime_'+row.id+'" name="pointtime_'+row.id+'" value="0" data-bv-notempty data-bv-notempty-message="请填写节点时间!" data-bv-integer data-bv-integer-message="节点时间必须填写整数!"/></div></div></div><div class="row"><div class="col-sm-12" style="padding-top:10px;"><div class="input-group input-group-sm"><button type="button" class="btn btn-success" onclick="saveTimeSettings('+row.id+')">保存</button>&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-default" onclick="closeTimeSettings('+row.id+')">关闭</button></div></div></div></div>';
       				}
       			}).popover('show');
       			loadComboxData('nodecode_' + row.id,'<c:url value="/worklineitem/loadothernodes.do"/>?worklinecode=' + row.worklinecode + '&id=' + row.id,function(data){
       				
       			});
            },
            'click .cancel': function (e, value, row) {
            	removeData('<c:url value="/worklineitem/del.do"/>',row.id,function(data){
        			$('#worklineitemGrid').bootstrapTable('refresh');
        		});
            }
        };
        
        function closeTimeSettings(id_v){
        	$('#timeSettingPopover' + id_v).popover('hide')
        }
        
        function saveTimeSettings(id_v){
        	$.ajax({
	            type: "post",
	            url:'<c:url value="/worklineitem/addNodeTimedesign.do"/>',
	            dataType: "json",
	            data:{toid:id_v,fromid:$('#nodecode_' + id_v).val(),timecost:$('#pointtime_' + id_v).val()},
	            success: function(data){
	            	$('#timeSettingPopover' + id_v).popover('hide')
	            	$('#linetimedesign' + id_v).bootstrapTable('refresh');
	            }
	        });
        }
	</script>
</body>
</t:html>