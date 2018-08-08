<%@ page contentType="text/html;charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="t" uri="http://www.talent.com/jsp/anyonelse"%>
<t:html>
	<head>
		<jsp:include page="common.jsp" flush="true"/>
		<link href="<c:url value='/css/ztree.css'/>" rel="stylesheet">
		<script type="text/javascript" src="<c:url value='/plugins/jquery.ztree.all.min.js'/>"></script>
		<script type="text/javascript">
			(function ($) {
			    'use strict';
				$.fn.bootstrapTable.locales['zh-CN'] = {
			        formatRecordsPerPage: function (pageNumber) {
			            return '';
			        },
			        formatShowingRows: function (pageFrom, pageTo, totalRows) {
			            return '';
			        }
			    };
				$.extend($.fn.bootstrapTable.defaults, $.fn.bootstrapTable.locales['zh-CN']);
			})(jQuery);
		</script>
	</head>
	<body class="container-fluid" style="padding-top: 15px">
		<div class="row">
			<div class="col-sm-4">
				<div class="panel panel-success" style="width:100%;">
					<div class="panel-heading">权限设置</div>
					<div class="panel-body">
						<div class="container-fluid" style="padding:0px;">
							<div class="row">
								<div class="col-sm-12">
									<t:datatable showsearch="true" url="/role/list" showoperatecolumn="false" singleselect="true" entities="com.talent.core.privilege.model.Role" usercolumn="false" id="RoleGrid" showcrud="false" hiddencolumns="selected,sn,validflag,creator,cdate,updator,udate,deletor,ddate,classindex,classtype,usermemo,sysmemo,memo,begintime,endtime,unitcode,unitname,unitid,remark,updater,canceler,canceldate"/>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12" style="padding-top:15px;">
									<t:datatable showsearch="true" url="/user/list" showoperatecolumn="false" singleselect="true" entities="com.talent.privilege.model.UserJSP" usercolumn="false" id="UserGrid" showcrud="false" hiddencolumns="sn,validflag,creator,cdate,updator,udate,deletor,ddate,classindex,classtype,usermemo,sysmemo,memo,begintime,endtime,unitcode,unitname,sex,nation,servicing,age,phone,email,lastLoginTime,unitid,remark,updater,canceler,canceldate"/>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="panel panel-danger" style="width:100%;">
					<div class="panel-heading">资源列表</div>
					<div class="panel-body">
						<div class="container-fluid" style="padding:0px;">
							<ul id="resourceTree" class="ztree"></ul>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="panel panel-warning" style="width:100%;">
					<div class="panel-heading">资源编辑</div>
					<div class="panel-body">
						<div class="container-fluid" style="padding:0px;">
							<form id="ResourceForm">
								<input type="hidden" id="id" name="id" value="-1"/>
								<input type="hidden" id="fid" name="fid" value="0"/>
								<div class="row">
									<div class="col-sm-12">
										<t:combobox id="resourcetype" label="资源类型" data="[{\"id\":\"menu\",\"text\":\"功能模块\"},{\"id\":\"func\",\"text\":\"操作按钮\"}]"/>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12">
										<t:textbox id="resourcename" label="资源名称" require="true"/>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12">
										<t:textbox id="resourcecode" label="资源编码" require="true"/>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12">
										<t:textbox id="resourcelink" label="链接地址" require="false"/>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12">
										<t:textbox id="resourceicon" label="资源图标" require="true"/>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12">
										<t:numberbox id="sn" label="序　　号" require="true" scale="0"/>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12">
										<t:combobox id="display" label="是否显示" data="[{\"id\":false,\"text\":\"不显示\"},{\"id\":true,\"text\":\"显示\"}]"/>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12">
										<button type="button" class="btn btn-success" id="ResourceSaveBtn" style="width:100%">保存资源</button>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12">
										<button type="button" class="btn btn-warning" id="ResourceSubsysBtn" style="width:100%;margin-top:10px;">新根资源</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="RoleResourceWindow" tabindex="-1" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span>&times;</span>
						</button>
						<h4 class="modal-title">角色权限复制</h4>
					</div>
					<div class="modal-body" style="padding-top:0px;">
						<t:datatable showoperatecolumn="false" showsearch="true" url="/role/copyrights/list" singleselect="false" entities="com.talent.core.privilege.model.Role" usercolumn="false" id="RoleCopyGrid" showcrud="true" btntypes="copy" btnfuncs="copyRoleRights" hiddencolumns="sn,validflag,creator,cdate,updator,udate,deletor,ddate,classindex,classtype,usermemo,sysmemo,memo,begintime,endtime,unitcode,unitname"/>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="UserResourceWindow" tabindex="-1" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span>&times;</span>
						</button>
						<h4 class="modal-title">用户权限复制</h4>
					</div>
					<div class="modal-body" style="padding-top:0px;">
						<t:datatable showoperatecolumn="false" showsearch="true" url="/user/copyrights/list" singleselect="false" entities="com.talent.privilege.model.UserJSP" usercolumn="false" id="UserCopyGrid" showcrud="true" btntypes="copy" btnfuncs="copyUserRights" hiddencolumns="age,email,sex,nation,servicing,phone,sn,validflag,creator,cdate,updator,udate,deletor,ddate,classindex,classtype,usermemo,sysmemo,memo,begintime,endtime,unitcode,unitname,lastLoginTime"/>
					</div>
				</div>
			</div>
		</div>
	</body>
	<script type="text/javascript">
		var pageload = true;
		var resourceTree;
		var editingParentNodeId;
		var setting = {
			view: {
				addHoverDom: addHoverDom,
				removeHoverDom: removeHoverDom,
				selectedMulti: false
			},
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			edit: {
				enable: true,
				showRenameBtn: false
			},
			callback: {
				onClick:function(event,treeId,treeNode,clickFlag){
					editingParentNodeId = treeNode.fid;
					loadFormData('ResourceForm','<c:url value="/resource/form"/>?id=' + treeNode.id + '&fid=' + treeNode.fid,function(data){
						
					});
				},
				beforeCheck:function(treeId, treeNode) {
					if($('#RoleGrid').bootstrapTable('getAllSelections').length + $('#UserGrid').bootstrapTable('getAllSelections').length == 0){
						warningbox('请先选择角色或用户，再赋权！');
						return false;
					}else if(treeNode.checked && $('#UserGrid').bootstrapTable('getAllSelections').length > 0 && 'ROLE' == treeNode.type){
						errorbox("角色权限，不允许取消！");
						return false;
					}
					return true;
				},
				onCheck:function(event,treeId,treeNode){
					var roleGridSelections = $('#RoleGrid').bootstrapTable('getAllSelections');
					var userGridSelections;
					if(roleGridSelections.length > 0){
						$.ajax({
				            type: "post",
				            url:'<c:url value="/resource/role"/>',
				            dataType: "json",
				            contentType:"application/json;charset=UTF-8",
				            data:JSON.stringify({roleid:roleGridSelections[0].id,exparams:getSubmitNodeData()}),
				            success: function(data){
				            	if(data.success){
				            		successtoast(data.msg);
				            	}else{
				            		errorbox(data.msg);
				            	}
				            }
				        });
					}else{
						userGridSelections = $('#UserGrid').bootstrapTable('getAllSelections');
						if(userGridSelections.length > 0){
							$.ajax({
					            type: "post",
					            url:'<c:url value="/resource/user"/>',
					            dataType: "json",
					            contentType:"application/json;charset=UTF-8",
					            data:JSON.stringify({userid:userGridSelections[0].id,exparams:getSubmitNodeData()}),
					            success: function(data){
					            	if(data.success){
					            		successtoast(data.msg);
					            	}else{
					            		errorbox(data.msg);
					            	}
					            }
					        });
						}
					}
				},onRemove:function(event,treeId,treeNode){
					editingParentNodeId = treeNode.fid;
					removeData('<c:url value="/resource/delete"/>',treeNode.id,function(data){
						reloadResourceTree();
	        		});
				},onNodeCreated:function(event,treeId,treeNode){
					if(!pageload){
						$.fn.zTree.getZTreeObj(treeId).selectNode(treeNode);
						loadFormData('ResourceForm','<c:url value="/resource/form"/>?id=-1&fid=' + treeNode.fid);
					}
				}
			}
		};
		
		function getSubmitNodeData(){
			var selectedNodes = $.fn.zTree.getZTreeObj("resourceTree").getCheckedNodes(true);
			var submitNodeData = new Array();
			for(var i=0;i<selectedNodes.length;i++){
				submitNodeData[i] = {id:selectedNodes[i].id,fid:selectedNodes[i].fid,name:selectedNodes[i].name};
			}
			return submitNodeData;
		}
		
		function reloadResourceTree(){
			var roleGridSelections = $('#RoleGrid').bootstrapTable('getAllSelections');
			var userGridSelections;
			if(roleGridSelections.length > 0){
				loadResourceTree('ROLE',roleGridSelections[0].id);
			}else{
				userGridSelections = $('#UserGrid').bootstrapTable('getAllSelections');
				if(userGridSelections.length > 0){
					loadResourceTree('USER',userGridSelections[0].id);
				}else{
					loadResourceTree('INIT',0);
				}
			}
		}
		
		jQuery(document).ready(function($){
			loadResourceTree('INIT',0);
			$('#ResourceSaveBtn').on('click',function(){
				saveFormData('ResourceForm','<c:url value="/resource/edit"/>',function(data){
					pageload = true;
					if(data.success){
						successtoast(data.msg);
						$('#ResourceForm').resetForm();
						reloadResourceTree();
					}else{
						errorbox(data.msg);
					}					
				});
	        });
			
			$('#ResourceSubsysBtn').on('click',function(){
				loadFormData('ResourceForm','<c:url value="/resource/form"/>?id=-2',function(data){
					pageload = true;
				});
	        });
			
			$('#resourcename').on('blur',function(){
				$('#resourcecode').val(pinyin.getFullChars($(this).val()));
				validForm('ResourceForm');
			});
		});
		
		function loadResourceTree(loadby_v,loadid_v){
			startwaiting();
			$.ajax({
	            type: "post",
	            url:'<c:url value="/resource/list"/>',
	            dataType: "json",
	            data:{loadby:loadby_v,loadid:loadid_v},
	            success: function(data){
	            	resourceTree = $.fn.zTree.init($("#resourceTree"), setting,data);
	            	var tpNode = resourceTree.getNodeByParam("id",editingParentNodeId,null);
	    			resourceTree.expandNode(tpNode, true, true, true, true);
	            },
	            complete:function(xhr,ts){
	            	stopwaiting();
	            }
	        });
		}
		
		$('#UserGrid').bootstrapTable({
			onCheck:function(row,element){
				pageload = true;
				$('#RoleGrid').bootstrapTable('uncheckAll');
				loadResourceTree('USER',row.id);
			},
			onUncheck:function(row,element){
				if(0 == $('#RoleGrid').bootstrapTable('getAllSelections').length){
					loadResourceTree('INIT',0);
				}
			}
		});
		
		$('#RoleGrid').bootstrapTable({
			onCheck:function(row,element){
				pageload = true;
				$('#UserGrid').bootstrapTable('uncheckAll');
				loadResourceTree('ROLE',row.id);
			},
			onUncheck:function(row,element){
				if(0 == $('#UserGrid').bootstrapTable('getAllSelections').length){
					loadResourceTree('INIT',0);
				}
			}
		});
		
        var newCount = 1;
		function addHoverDom(treeId, treeNode) {
			pageload = false;
			var sObj = $("#" + treeNode.tId + "_span");
			if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
			var addStr = "<span class='button add' id='addBtn_" + treeNode.tId + "' title='新建资源' onfocus='this.blur();'></span>";
			sObj.after(addStr);
			var btn = $("#addBtn_"+treeNode.tId);
			if (btn) btn.bind("click", function(){
				editingParentNodeId = treeNode.id;
				var zTree = $.fn.zTree.getZTreeObj("resourceTree");
				zTree.addNodes(treeNode, {id:-1, fid:treeNode.id, name:"新资源" + (newCount++)});
				return false;
			});
		};
		
		function removeHoverDom(treeId, treeNode) {
			pageload = true;
			$("#addBtn_"+treeNode.tId).unbind().remove();
		}
		
		var copyingObjectId = -1;
		function RoleGridOperateFormatter(value,row,index){
			return [
                '<div class="pull-center">',
               	'<a class="copy" href="javascript:void(0)" title="复制角色权限">',
               	'<i class="fa fa-files-o"></i>',
               	'</a>',
               	'</div>'
           	].join('');
		}
		
		window.RoleGridOperateEvents = {
            'click .copy': function (e, value, row) {
            	$('#RoleResourceWindow').unbind('shown.bs.modal');
            	$('#RoleResourceWindow').on('shown.bs.modal', function(){
            		copyingObjectId = row.id;
            		$('#RoleCopyGrid').bootstrapTable('refresh',{url:'<c:url value="/role/copyrights/list"/>?id=' + row.id});
               	}).modal('show');
            }
        }
		
		function UserGridOperateFormatter(value,row,index){
			return [
                '<div class="pull-center">',
               	'<a class="copy" href="javascript:void(0)" title="复制用户权限">',
               	'<i class="fa fa-files-o"></i>',
               	'</a>',
               	'</div>'
           	].join('');
		}
		
		window.UserGridOperateEvents = {
            'click .copy': function (e, value, row) {
            	$('#UserResourceWindow').unbind('shown.bs.modal');
            	$('#UserResourceWindow').on('shown.bs.modal', function(){
            		copyingObjectId = row.id;
            		$('#UserCopyGrid').bootstrapTable('refresh',{url:'<c:url value="/user/copyrights/list"/>?id=' + row.id});
               	}).modal('show');
            }
        }
		
		function copyRoleRights(){
			var ids_v = '';
			$('#RoleCopyGrid').bootstrapTable('getAllSelections').forEach(function(value,index,array){
				ids_v = ids_v + ',' + value.id;
			});
			if('' != ids_v){
				ids_v = ids_v.substr(1);
				dialogbox("请确认", "复制角色权限将移除目标角色原先拥有的权限，是否继续？",function(data){
					if(data){
						startwaiting();
						$.ajax({
				            type: "post",
				            url: '<c:url value="/role/copyrights/edit"/>',
				            dataType: "json",
				            data:{sourceRoleId:copyingObjectId,targetRoleIds:ids_v},
				            success: function(data){
				            	successtoast(data.msg);
				            },
				            complete:function(xhr,ts){
				            	stopwaiting();
				            }
				        });
					}
				});
			}else{
				errorbox('请选择目标角色！');
			}
		}
		
		function copyUserRights(){
			var ids_v = '';
			$('#UserCopyGrid').bootstrapTable('getAllSelections').forEach(function(value,index,array){
				ids_v = ids_v + ',' + value.id;
			});
			if('' != ids_v){
				ids_v = ids_v.substr(1);
				dialogbox("请确认", "复制用户权限将移除目标用户原先拥有的权限，是否继续？",function(data){
					if(data){
						startwaiting();
						$.ajax({
				            type: "post",
				            url: '<c:url value="/user/copyrights/edit"/>',
				            dataType: "json",
				            data:{sourceUserId:copyingObjectId,targetUserIds:ids_v},
				            success: function(data){
				            	successtoast(data.msg);
				            },
				            complete:function(xhr,ts){
				            	stopwaiting();
				            }
				        });
					}
				});
			}else{
				errorbox('请选择目标用户！');
			}
		}
	</script>
</t:html>