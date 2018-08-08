<%@ page contentType="text/html;charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="t" uri="http://www.talent.com/jsp/anyonelse"%>
<t:html>
	<head>
		<jsp:include page="common.jsp" flush="true"/>
	</head>
	<body>
		<div class="modal fade" id="UserWindow" tabindex="-1" role="dialog" aria-labelledby="UserWindowLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">用户管理</h4>
					</div>
					<div class="modal-body">
						<form id="UserForm">
							<input type="hidden" id="id" name="id" value="0"/>
							<input type="hidden" id="prepassword" name="prepassword"/>
							<div class="row">
								<div class="col-sm-6">
									<t:textbox id="username" label="用户编码" require="true"/>
								</div>
								<div class="col-sm-6">
									<t:textbox id="displayname" label="用户名称" require="true"/>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6">
									<t:combobox id="sex" label="性　　别" data="[{\"id\":\"男\",\"text\":\"男\"},{\"id\":\"女\",\"text\":\"女\"}]" readonly="false" require="true" value="男"/>
								</div>
								<div class="col-sm-6">
									<t:combobox id="nation" label="民　　族" data="[{\"id\":\"汉族\",\"text\":\"汉族\"},{\"id\":\"外籍友人\",\"text\":\"外籍友人\"},{\"id\":\"壮族\",\"text\":\"壮族\"},{\"id\":\"回族\",\"text\":\"回族\"},{\"id\":\"满族\",\"text\":\"满族\"},{\"id\":\"维吾尔族\",\"text\":\"维吾尔族\"},{\"id\":\"苗族\",\"text\":\"苗族\"},{\"id\":\"彝族\",\"text\":\"彝族\"},{\"id\":\"土家族\",\"text\":\"土家族\"},{\"id\":\"藏族\",\"text\":\"藏族\"},{\"id\":\"蒙古族\",\"text\":\"蒙古族\"},{\"id\":\"侗族\",\"text\":\"侗族\"},{\"id\":\"布依族\",\"text\":\"布依族\"},{\"id\":\"瑶族\",\"text\":\"瑶族\"},{\"id\":\"白族\",\"text\":\"白族\"},{\"id\":\"朝鲜族\",\"text\":\"朝鲜族\"},{\"id\":\"哈尼族\",\"text\":\"哈尼族\"},{\"id\":\"黎族\",\"text\":\"黎族\"},{\"id\":\"哈萨克族\",\"text\":\"哈萨克族\"},{\"id\":\"傣族\",\"text\":\"傣族\"},{\"id\":\"畲族\",\"text\":\"畲族\"},{\"id\":\"傈僳族\",\"text\":\"傈僳族\"},{\"id\":\"东乡族\",\"text\":\"东乡族\"},{\"id\":\"仡佬族\",\"text\":\"仡佬族\"},{\"id\":\"拉祜族\",\"text\":\"拉祜族\"},{\"id\":\"佤族\",\"text\":\"佤族\"},{\"id\":\"水族\",\"text\":\"水族\"},{\"id\":\"纳西族\",\"text\":\"纳西族\"},{\"id\":\"羌族\",\"text\":\"羌族\"},{\"id\":\"土族\",\"text\":\"土族\"},{\"id\":\"仫佬族\",\"text\":\"仫佬族\"},{\"id\":\"锡伯族\",\"text\":\"锡伯族\"},{\"id\":\"柯尔克孜族\",\"text\":\"柯尔克孜族\"},{\"id\":\"景颇族\",\"text\":\"景颇族\"},{\"id\":\"达斡尔族\",\"text\":\"达斡尔族\"},{\"id\":\"撒拉族\",\"text\":\"撒拉族\"},{\"id\":\"布朗族\",\"text\":\"布朗族\"},{\"id\":\"毛南族\",\"text\":\"毛南族\"},{\"id\":\"塔吉克族\",\"text\":\"塔吉克族\"},{\"id\":\"普米族\",\"text\":\"普米族\"},{\"id\":\"阿昌族\",\"text\":\"阿昌族\"},{\"id\":\"怒族\",\"text\":\"怒族\"},{\"id\":\"鄂温克族\",\"text\":\"鄂温克族\"},{\"id\":\"京族\",\"text\":\"京族\"},{\"id\":\"基诺族\",\"text\":\"基诺族\"},{\"id\":\"德昂族\",\"text\":\"德昂族\"},{\"id\":\"保安族\",\"text\":\"保安族\"},{\"id\":\"俄罗斯族\",\"text\":\"俄罗斯族\"},{\"id\":\"裕固族\",\"text\":\"裕固族\"},{\"id\":\"乌孜别克族\",\"text\":\"乌孜别克族\"},{\"id\":\"门巴族\",\"text\":\"门巴族\"},{\"id\":\"鄂伦春族\",\"text\":\"鄂伦春族\"},{\"id\":\"独龙族\",\"text\":\"独龙族\"},{\"id\":\"赫哲族\",\"text\":\"赫哲族\"},{\"id\":\"高山族\",\"text\":\"高山族\"},{\"id\":\"珞巴族\",\"text\":\"珞巴族\"},{\"id\":\"塔塔尔族\",\"text\":\"塔塔尔族\"},{\"id\":\"未识别民族\",\"text\":\"未识别民族\"},{\"id\":\"入籍外国人\",\"text\":\"入籍外国人\"}]" readonly="false" require="true" value="汉族"/>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6">
									<t:textbox id="password" label="密　　码" password="true" require="true"/>
								</div>
								<div class="col-sm-6">
									<t:textbox id="repassword" label="确认密码" password="true" require="true"/>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6">
									<t:numberbox id="age" label="年　　龄" scale="0" require="false"/>
								</div>
								<div class="col-sm-6">
									<t:phonebox id="phone" label="电　　话" require="false"/>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6">
									<t:emailbox id="email" label="邮　　箱" require="false"/>
								</div>
								<div class="col-sm-6">
									<t:combobox id="servicing" label="是否禁用" data="[{\"id\":\"1\",\"text\":\"是\"},{\"id\":\"0\",\"text\":\"否\"}]" readonly="false" require="false" value="是"/>
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer btn-group-sm">
					    <button type="button" class="btn btn-success" id="UserSaveBtn">保存</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
		</div>
		<div id="querytoolbar">
			<div class="form-inline" role="form">
				<form id="queryform">
					<t:textbox id="username_query" name="username" label="用户编码" require="false"/>
					<t:textbox id="displayname_query" name="displayname" label="用户名称" require="false"/>	
					<div class="form-group btn-group-sm">
						<button id="query" type="button" class="btn btn-info enterkeyaction" onclick="queryinfo();refreshRoleData();refreshOrganData();">
							<span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;查询
						</button>
					</div>
				</form>
			</div>
		</div>
		<div class="row-fluid">
			<div class="col-sm-12">
			<%-- 				<t:datatable toolbar="querytoolbar" queryformid="queryform" url="/user/list" singleselect="true" entities="com.talent.privilege.model.UserJSP" 
			id="UserGrid" showcrud="true" btntypes="add" btnfuncs="addFunction" 
			hiddencolumns="sn,validflag,creator,cdate,updator,udate,deletor,ddate,classindex,classtype,usermemo,sysmemo,memo,begintime,endtime,unitcode,unitname,lastLoginTime,unitid,remark,updater,canceler,canceldate" usercolumn="false"/>
			 --%>		  
			  <table id="UserGrid" 
			          data-toggle="table" 
			          data-row-style="rowStyle" 
			          data-click-to-select="true"
			          data-single-select="true" 
			          data-show-crud="true" 
			          data-btn-types="add" 
			          data-btn-funcs="addFunction" 
			          data-page-size ="10"
			          data-pagination ="true"
			          data-page-list="[10,30,50,all]"
			          data-toolbar="#querytoolbar" 
			          data-query-form="queryform" 
			          data-mobile-responsive="true" 
			          class="table table-hover">
					<thead>
						<tr>
							<th data-field="state" data-radio="true" ></th>
							<th class="text-nowrap" data-field="username" data-halign="center" data-align="left">用户编码</th>
							<th class="text-nowrap" data-field="displayname" data-halign="center" data-align="left">用户名称</th>
							<th class="text-nowrap" data-field="sex" data-halign="center" data-align="left">性别</th>
							<th class="text-nowrap" data-field="nation" data-halign="center" data-align="left">民族</th>
							<th class="text-nowrap" data-field="servicing" data-halign="center" data-formatter="servicingFormatter" data-align="left">是否禁用</th>
							<th class="text-nowrap" data-field="age" data-halign="center" data-align="left">年龄</th>
							<th class="text-nowrap" data-field="phone" data-halign="center" data-align="left">联系电话</th>
							<th class="text-nowrap" data-field="email" data-halign="center" data-align="left">电子邮箱</th>
							<th class="text-nowrap" data-width="90px" data-align="center" data-valign="middle" data-formatter="operateFormatter" data-events="operateEvents">操作</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
		<div class="row-fluid">
			<div class="col-sm-6" style="padding-top:10px;">
				<t:datatable showsearch="true" url="/role/list" entities="com.talent.core.privilege.model.Role" id="RoleGrid" showselectbox="false" showoperatecolumn="false" showcrud="false"  hiddencolumns="sn,validflag,creator,cdate,updator,udate,deletor,ddate,classindex,classtype,usermemo,sysmemo,memo,begintime,endtime,unitcode,unitname,unitid,remark,updater,canceler,canceldate" usercolumn="false"/>
			</div>
			<div class="col-sm-6" style="padding-top:10px;">
				<t:datatable showsearch="true" url="/organ/list" entities="com.talent.core.privilege.model.Organ" id="OrganGrid" singleselect="true" showoperatecolumn="false" showcrud="false"  hiddencolumns="sn,validflag,creator,cdate,updator,udate,deletor,ddate,classindex,classtype,usermemo,sysmemo,memo,begintime,endtime,unitcode,unitname,unitid,remark,updater,canceler,canceldate" usercolumn="false"/>
			</div>
		</div>
	</body>
	<script type="text/javascript">
		jQuery(document).ready(function($){
			$('#UserSaveBtn').on('click',function(){
				saveFormData('UserForm','<c:url value="/user/edit"/>',function(data){
					if(data.success){
						$('#UserWindow').modal('toggle');
	                	$('#UserGrid').bootstrapTable('refresh','<c:url value="/user/list"/>');
					}else{
						errorbox(data.msg);
					}
				});
	        });
			queryinfo();
		});
		function queryinfo() {
			//$('#UserGrid').bootstrapTable('refresh','<c:url value="/user/list"/>');
			//loadGridData('UserGrid','<c:url value="/user/list"/>');
			loadGridData('UserGrid','<c:url value="/user/list"/>');
		}
		function selectedFormatter(value, row, index) {
        	if(value == 0){
	        	return '<center><input type="checkbox" onclick="chengeRole(\''+row.id+'\',\''+value+'\',\'ADD\')"><center>';
	        }else{
	        	return '<center><input type="checkbox" checked onclick="chengeRole(\''+row.id+'\',\''+value+'\',\'DEL\')"><center>';
	        }
        }
		
		function servicingFormatter(value,row,index){
			if(1 == value){
				return '是';
			}else{
				return '否';
			}
		}
        
        function chengeRole(roleid_v,userroleid_v,optr){
        	var userSelections = $('#UserGrid').bootstrapTable('getAllSelections');
        	if(userSelections.length == 1){
            	$.ajax({
    	            type: "post",
    	            url: '<c:url value="/user/role/set"/>',
    	            dataType: "json",
    	            data: {userid:userSelections[0].id,roleid:roleid_v,userroleid:userroleid_v,optr:optr},
    	            success: function(data){
    	            	 if(data.success == true){
    	            		 successtoast('操作成功！');
    	            		 $('#RoleGrid').bootstrapTable('refresh',{url:'<c:url value="/user/role"/>?userid=' + userid_v});
    	            	 }
    	            }
                });
        	}else{
        		errorbox('请选择用户再赋予角色！');
        	}
        }
        
        function refreshRoleData(){
        	$('#RoleGrid').bootstrapTable('refreshOptions',{pagination:true,url:'<c:url value="/role/list"/>'});
        }
        
        function refreshOrganData(){
        	$('#OrganGrid').bootstrapTable('refreshOptions',{pagination:true,url:'<c:url value="/organ/list"/>'});
        }
		
		$('#UserGrid').bootstrapTable({
			onCheck:function(row,element){
				$('#RoleGrid').bootstrapTable('refreshOptions',{pagination:false,url:'<c:url value="/user/role"/>?userid=' + row.id});
			}
		});
		
		function addFunction(){
        	openWindow('UserWindow','UserForm','<c:url value="/user/form"/>?id=-1');
        }
		
		function operateFormatter(value, row, index) {
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
		function servicingFormatter (value, row, index) {
			if(value == '0'){
				return '否';
			}else if(value =='1'){
				return '是';
			}
		}
        window.operateEvents = {
            'click .edit': function (e, value, row) {
            	openWindow('UserWindow','UserForm','<c:url value="/user/form"/>?id=' + row.id);
            },
            'click .remove': function (e, value, row) {
            	removeData('<c:url value="/user/delete"/>',row.id,function(data){
        			$('#UserGrid').bootstrapTable('refresh','<c:url value="/user/list"/>');
        		});
            }
        };
	</script>
</t:html>