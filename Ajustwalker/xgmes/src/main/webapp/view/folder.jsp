<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="t" uri="http://www.talent.com/jsp/anyonelse"%>
<t:html>
<head>
<jsp:include page="common.jsp" flush="true" />
<link href="<c:url value='/css/ztree.css'/>" rel="stylesheet">
<script type="text/javascript" src="<c:url value='/plugins/jquery.ztree.all.min.js'/>"></script>
</head>
<body class="container-fluid" style="padding-top: 15px">
	<div class="row">
		<div class="col-sm-3">
			<ul id="FolderTree" class="ztree"></ul>
		</div>
		<div class="col-sm-9">
			<div class="container-fluid">
				<div class="row">
					<div class="container-fluid" style="padding: 0px;">
						<form id="FolderForm">
							<input type="hidden" id="id" name="id" value="0" /> <input type="hidden" id="fid" name="fid" value="0" /> <input type="hidden" id="foldercode" name="foldercode" value="0" />
							<%-- 		<div class="row">
									<div class="col-sm-12">
										<t:textbox id="foldercode" label="大类编码" require="true"/>
									</div>
								</div> --%>
							<div class="row">
								<div class="col-sm-12">
									<t:textbox id="foldername" label="&nbsp;大类名称" require="true" readonly="true" />
								</div>
							</div>
							<%-- <div class="row">
									<div class="col-sm-12">
										<t:textbox id="queryword" label="拼&nbsp;&nbsp;&nbsp;音&nbsp;&nbsp;缩&nbsp;&nbsp;写" require="true"/>
									</div>
								</div> --%>
							<div class="row">
								<div class="col-sm-12">
									<t:combobox id="routeid" label="&nbsp;作业路线" url="/workline/select2data" require="false" allowclear="true" />
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<t:combobox id="isormeasure" label="&nbsp;是否计量" url="/bcommon/queryYesOrNo" require="false" allowclear="true" />
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<t:combobox id="isormonitor" label="&nbsp;是否监秤" url="/bcommon/queryYesOrNo" require="false" allowclear="true" />
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<t:combobox id="isorrfid" label="启用RFID" url="/bcommon/queryYesOrNo" require="false" allowclear="true" />
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<button type="button" class="btn btn-success" id="FolderSaveBtn" style="width: 100%">保存</button>
								</div>
							</div>
						</form>
					</div>
				</div>
				<div id="querytoolbar">
					<div class="form-inline" role="form">
						<form id="queryform">
								<input id="foldercode_query" name="foldercode" type="hidden" />
								<t:textbox id="materialcode_query" name="materialcode" label="&nbsp;物料编码&nbsp;" require="false" />
								<t:textbox id="materialname_query" name="materialname" label="&nbsp;物料名称&nbsp;" require="false" />
								<%-- <t:textbox id="queryword_query" name="queryword" label="&nbsp;拼音缩写&nbsp;" require="false"/> --%>
								<div class="form-group btn-group-sm">
									<button id="query" type="button" class="btn btn-info enterkeyaction" onclick="loadMaterialGridData()">
										<span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;查询
									</button>
									<button id="downloadfloder" type="button" class="btn btn-warning enterkeyaction" onclick="downloadf()">
										<span class="glyphicon glyphicon-cloud-download" aria-hidden="true"></span>&nbsp;大类下载
									</button>
									<button id="downloadmaterial" type="button" class="btn btn-danger enterkeyaction" onclick="downloadm()">
										<span class="glyphicon glyphicon-cloud-download" aria-hidden="true"></span>&nbsp;物料下载
									</button>
								</div>
						</form>
					</div>
				</div>
				<div class="row">
					<t:datatable toolbar="querytoolbar" queryformid="queryform" url="/material/list" entities="com.talent.materialflow.model.Material" id="MaterialGrid" showcrud="true" btntypes="add,remove" btnfuncs="addFunction,removeFunction" hiddencolumns="validflag,creator,cdate,updater,udate,canceler,canceldate,classindex,classtype,usermemo,sysmemo,materialunit,trackflag,theorycoefficient,eachweight,istheory,mqflag,sizerange,qyfs,remark,erpcode,queryword" />
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="MaterialWindow" tabindex="-1" role="dialog" aria-labelledby="MaterialWindowLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">配置模块</h4>
				</div>
				<div class="modal-body">
					<form id="MaterialForm">
						<input type="hidden" id="id" name="id" value="0" /> <input type="hidden" id="foldercode_m" name="foldercode" value="0" /> <input type="hidden" id="materialcode" name="materialcode" value="0" /> <input type="hidden" id="queryword" name="queryword" value="0" /> <input type="hidden" id="erpcode_m" name="erpcode" value="0" />
						<div class="row">
							<div class="col-sm-12">
								<t:textbox id="materialname" label="物料名称" require="true" />
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<t:combobox id="routeid_m" name="routeid" label="作业路线" require="false" url="/workline/select2data" allowclear="true" />
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<t:combobox id="isormeasure_material" name="isormeasure" label="是否计量" url="/bcommon/queryYesOrNo" require="false" allowclear="true" />
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<t:combobox id="isormonitor_material" name="isormonitor" label="是否监秤" url="/bcommon/queryYesOrNo" require="false" allowclear="true" />
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<t:combobox id="isorrfid_material" name="isorrfid" label="启用RFID" url="/bcommon/queryYesOrNo" require="false" allowclear="true" />
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer btn-group-sm">
					<button type="button" class="btn btn-success" id="MaterialSaveBtn">保存</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var FolderTree;
	var currentFoldercode = '';
	var parentNode;
	var pageload = true;
	var setting = {
		view : {
			addHoverDom : addHoverDom,
			removeHoverDom : removeHoverDom,
			selectedMulti : false
		},
		check : {
			enable : false
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		edit : {
			enable : true
		},
		callback : {
			onClick : function(event, treeId, treeNode, clickFlag) {
				if (-1 != treeNode.id) {
					parentNode = FolderTree.getNodeByTId(treeNode.parentTId);
					loadFormData('FolderForm',
							'<c:url value="/folder/form"/>?id=' + treeNode.id
									+ '&fid=' + treeNode.fid, function(data) {

							});
					currentFoldercode = treeNode.foldercode;
					$('#foldercode_query').val(treeNode.foldercode);
					loadGridData('MaterialGrid',
							'<c:url value="/material/list"/>');
				}
			},
			onRemove : function(event, treeId, treeNode) {
				removeData('<c:url value="/folder/delete"/>', treeNode.id,
						function(data) {
							loadFolderTree();
						});
			},
			onNodeCreated : function(event, treeId, treeNode) {
				if (!pageload) {
					$.fn.zTree.getZTreeObj(treeId).selectNode(treeNode);
					loadFormData('FolderForm',
							'<c:url value="/folder/form"/>?id=-1&fid='
									+ treeNode.fid, function(data) {

							});
				}
			}
		}
	};
	jQuery(document).ready(
			function($) {
				loadFolderTree();
				$('#FolderSaveBtn').on(
						'click',
						function() {
							saveFormData('FolderForm',
									'<c:url value="/folder/edit"/>', function(
											data) {
										if (data.success) {
											loadFolderTree();
										} else {
											errorbox(data.msg);
										}
									});
						});

				$('#foldername').blur(function() {
					var val = $(this).val();
					if (val.length > 0) {
						$('#queryword').val(pinyin.getCamelChars(val));
						validForm('FolderForm');
					}
				})

				$('#MaterialSaveBtn').on(
						'click',
						function() {
							saveFormData('MaterialForm',
									'<c:url value="/material/edit"/>',
									function(data) {
										if (data.success) {
											$('#MaterialWindow')
													.modal('toggle');
											$('#MaterialGrid').bootstrapTable(
													'refresh');
										}
									});
						});

				$('#materialname').blur(function() {
					var val = $(this).val();
					if (val.length > 0) {
						$('#queryword_m').val(pinyin.getCamelChars(val));
						validForm('MaterialForm');
					}
				})
				
				$('#isormeasure').change(function(){
					if('0' == $(this).val()){
						$('#isorrfid').prop('disabled',true);
					}else{
						$('#isorrfid').prop('disabled',false);
					}
				});
				$('#isormeasure_material').change(function(){
					if('0' == $(this).val()){
						$('#isorrfid_material').prop('disabled',true);
					}else{
						$('#isorrfid_material').prop('disabled',false);
					}
				});
			});

	function loadFolderTree() {
		startwaiting();
		$
				.ajax({
					type : "post",
					url : '<c:url value="/folder/tree"/>',
					dataType : "json",
					success : function(data) {
						pageload = true;
						FolderTree = $.fn.zTree.init($("#FolderTree"), setting,
								data);
						if (undefined != parentNode) {
							FolderTree.expandNode(parentNode, true, false,
									true, false);
						}
					},
					complete : function(xhr, ts) {
						stopwaiting();
					}
				});
	}

	function addFunction() {
		openWindow('MaterialWindow', 'MaterialForm',
				'<c:url value="/material/form"/>?id=-1&foldercode='
						+ currentFoldercode);
	}

	function removeFunction() {
		listGridIds('MaterialGrid', function(ids) {
			removeData('<c:url value="/material/delete"/>', ids,
					function(data) {
						$('#MaterialGrid').bootstrapTable('refresh');
					});
		});
	}

	function operateFormatter(value, row, index) {
		return [ '<div class="pull-right" style="width:60px;">',
				'<a class="edit" href="javascript:void(0)" title="修改">',
				'<i class="glyphicon glyphicon-pencil"></i>', '</a>　',
				'<a class="remove" href="javascript:void(0)" title="移除">',
				'<i class="glyphicon glyphicon-trash"></i>', '</a>', '</div>' ]
				.join('');
	}

	window.operateEvents = {
		'click .edit' : function(e, value, row) {
			openWindow('MaterialWindow', 'MaterialForm',
					'<c:url value="/material/form"/>?id=' + row.id);
		},
		'click .remove' : function(e, value, row) {
			removeData('<c:url value="/material/delete"/>', row.id, function(
					data) {
				$('#MaterialGrid').bootstrapTable('refresh');
			});
		}
	};

	var newCount = 1;
	function addHoverDom(treeId, treeNode) {
		var sObj = $("#" + treeNode.tId + "_span");
		if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0)
			return;
		var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
				+ "' title='新建物料大类' onfocus='this.blur();'></span>";
		sObj.after(addStr);
		var btn = $("#addBtn_" + treeNode.tId);
		if (btn)
			btn.bind("click", function() {
				pageload = false;
				var zTree = $.fn.zTree.getZTreeObj("FolderTree");
				zTree.addNodes(treeNode, {
					id : -1,
					fid : treeNode.id,
					name : "新物料大类" + (newCount++)
				});
				return false;
			});
	};

	function removeHoverDom(treeId, treeNode) {
		$("#addBtn_" + treeNode.tId).unbind().remove();
	};

	function routeidFormatter(value, row, index) {
		var result = (value == -1 || value == 0) ? '无' : value;
		$('#routeid option').each(function() {
			var $option = $(this);
			var text = $option.html();
			var id = $option.val();
			if (value == id) {
				result = text;
			}
		});
		return result;
	}

	function isormeasureFormatter(value, row, index) {
		if (1 == value) {
			return '是';
		} else if (-1 == value) {
			return '不检查';
		} else {
			return '否';
		}
	}

	function isormonitorFormatter(value, row, index) {
		if (1 == value) {
			return '是';
		} else if (-1 == value) {
			return '不检查';
		} else {
			return '否';
		}
	}

	function isorrfidFormatter(value, row, index) {
		if (1 == value) {
			return '是';
		} else if (-1 == value) {
			return '不检查';
		} else {
			return '否';
		}
	}

	function downloadf() {
		startwaiting();
		$.ajax({
			type : "post",
			url : '<c:url value="/folder/downloadfolder.do"/>',
			dataType : "json",
			success : function(data) {
				if (data.success == true) {
					successbox(data.msg);
				} else {
					errorbox(data.msg);
				}
			},
			complete : function(xhr, ts) {
				stopwaiting();
			}
		});
	}
	function downloadm() {
		startwaiting();
		$.ajax({
					type : "post",
					url : '<c:url value="/folder/downloadmaterial.do"/>',
					dataType : "json",
					success : function(data) {
						if (data.success == true) {
							successbox(data.msg);
						} else {
							errorbox(data.msg);
						}
					},
					complete : function(xhr, ts) {
						stopwaiting();
					}
				});
		
	}
</script>
</t:html>