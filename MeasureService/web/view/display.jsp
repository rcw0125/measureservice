<%@ page contentType="text/html;charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="common.jsp" flush="true"/>
	<style type="text/css">
		.table>tbody>tr>td{
			vertical-align:top;
		}
		.fixed-table-container tbody td .th-inner, .fixed-table-container thead th .th-inner{
			text-align:center;
		}
	</style>
</head>
<body class="container-fluid" style="padding-top:20px;">
	<div class="row" style="padding-left:20px;padding-right:20px;">
		<ul id="myTabs" class="nav nav-tabs" role="tablist">
			<c:set var="curroperate"/>
			<c:forEach items="${displayList}" var="item" varStatus="status">
				<li role="presentation" class="dropdown <c:if test="${status.index == 0}"><c:set var="curroperate" value="${item.OPERATYPE}"/>active</c:if>">
					<a href="#" id="myTabDrop${status.index}" class="dropdown-toggle" aria-expanded="true" data-toggle="dropdown" aria-controls="myTabDrop1-contents">${item.OPERANAME}<span class="caret"></span></a>
					<ul class="dropdown-menu" aria-labelledby="myTabDrop${status.index}">
						<li><a href="#" role="tab" data-toggle="tab" aria-controls="dropdown${status.index}" onclick="loadDisplayConfig('${item.OPERATYPE}','1')">坐席</a></li>
						<li><a href="#" role="tab" data-toggle="tab" aria-controls="dropdown${status.index + 1}" onclick="loadDisplayConfig('${item.OPERATYPE}','0')">终端</a></li>
					</ul>
				</li>
			</c:forEach>
	    </ul>
		<div id="myTabContent" class="tab-content" style="padding-bottom:25px;">					
			<div role="tabpanel" class="tab-pane fade in active">
				<div class="container-fluid" style="margin-top:0px;padding:0px;">
					<div class="row-fluid">
						<div class="col-sm-12" style="margin:0px;padding:0px;">
							<table id="MeasureDisplayGrid" data-toggle="table" 
								   data-row-style="rowStyle"
								   data-url="<c:url value='/measuredisplay/queryList.do'/>?operatype=${curroperate}&points=1">
								<thead>
									<tr>
									<div>
									   <input type="hidden" id="points" name="points">
									</div>
										<th data-field="points" data-halign="center" data-visible="false"></th>	
										<th data-field="operatype" data-halign="center" data-visible="false"></th>	
										<th data-field="orderno" data-halign="center">显示顺序</th>
										<th data-field="displayname" data-halign="center" data-searchable="true">显示名称</th>
										<th data-field="fieldname" data-halign="center" data-searchable="true">字段名称</th>
										<th data-field="isdisplay" data-halign="center" data-searchable="true" data-formatter="isdisplayFormatter">是否显示</th>
										<th data-field="aboutweight" data-halign="center" data-searchable="true" data-formatter="aboutweightFormatter">是否重量相关</th>
										<th data-field="quicksuggest" data-halign="center" data-searchable="true" data-formatter="quicksuggestFormatter">是否快速提示</th>
										<th data-field="writeable" data-halign="center" data-searchable="true" data-formatter="writeableFormatter">是否可写</th>
										<th data-width="70px" data-align="center" data-valign="middle" data-formatter="operateFormatter" data-events="operateEvents">排序</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function operateFormatter(value, row, index) {
	        return [
	        	'<div class="pull-right">',
		       	'<a class="edit" href="javascript:editOrder(\''+row.operatype+'\',\''+row.fieldname+'\',\''+row.points+'\','+row.orderno+',\'向上\')" title="上移">',
		       	'<i class="glyphicon glyphicon-hand-up"></i>',
		       	'</a>　',
		       	'<a class="remove" href="javascript:editOrder(\''+row.operatype+'\',\''+row.fieldname+'\',\''+row.points+'\','+row.orderno+',\'向下\')" title="下移">',
		       	'<i class="glyphicon glyphicon-hand-down"></i>',
		       	'</a>',
		       	'</div>'
	   		].join('');
	    }
		
		function editOrder(operatype,fieldname,points,orderno,direction) {
			$.ajax({
	            type: "post",
	            url: '<c:url value="/measuredisplay/editorder.do"/>',
	            dataType: "json",
	            data: {operatype:operatype,fieldname:fieldname,points:points,orderno:orderno,direction:direction},
	            success: function(data){
	            	 if(data.success == true){
	            		 toastMessage("success","提示","操作成功！");
	            		 loadDisplayConfig(operatype,points);
	            	 }
	            }
            });
	    }
		
		function chengeStatus(operatype,fieldname,points,optrfield,optrvalue,orderno){
			$.ajax({
	            type: "post",
	            url: '<c:url value="/measuredisplay/setctrlflag.do"/>',
	            dataType: "json",
	            data: {operatype:operatype,fieldname:fieldname,points:points,optrfield:optrfield,optrvalue:optrvalue,orderno:orderno},
	            success: function(data){
	            	 if(data.success == true){
	            		 toastMessage("success","提示","操作成功！");
	            		 loadDisplayConfig(operatype,points);
	            	 }
	            }
            });
		}
		
		function isdisplayFormatter(value, row, index) {
	        if('0' == value){
	        	return '<center><input type="checkbox" onclick="chengeStatus(\''+row.operatype+'\',\''+row.fieldname+'\',\''+row.points+'\',\'isdisplay\',1,'+row.orderno+')"><center>';
	        }else{
	        	return '<center><input type="checkbox" checked onclick="chengeStatus(\''+row.operatype+'\',\''+row.fieldname+'\',\''+row.points+'\',\'isdisplay\',0,'+row.orderno+')"><center>';
	        }
	    }
		
		function aboutweightFormatter(value, row, index) {
	        if('0' == value){
	        	return '<center><input type="checkbox" onclick="chengeStatus(\''+row.operatype+'\',\''+row.fieldname+'\',\''+row.points+'\',\'aboutweight\',1,'+row.orderno+')"><center>';
	        }else{
	        	return '<center><input type="checkbox" checked onclick="chengeStatus(\''+row.operatype+'\',\''+row.fieldname+'\',\''+row.points+'\',\'aboutweight\',0,'+row.orderno+')"><center>';
	        }
	    }
		
		function quicksuggestFormatter(value, row, index) {
	        if('0' == value){
	        	return '<center><input type="checkbox" onclick="chengeStatus(\''+row.operatype+'\',\''+row.fieldname+'\',\''+row.points+'\',\'quicksuggest\',1,'+row.orderno+')"><center>';
	        }else{
	        	return '<center><input type="checkbox" checked onclick="chengeStatus(\''+row.operatype+'\',\''+row.fieldname+'\',\''+row.points+'\',\'quicksuggest\',0,'+row.orderno+')"><center>';
	        }
	    }
		
		function writeableFormatter(value, row, index) {
	        if('0' == value){
	        	return '<center><input type="checkbox" onclick="chengeStatus(\''+row.operatype+'\',\''+row.fieldname+'\',\''+row.points+'\',\'writeable\',1,'+row.orderno+')"><center>';
	        }else{
	        	return '<center><input type="checkbox" checked onclick="chengeStatus(\''+row.operatype+'\',\''+row.fieldname+'\',\''+row.points+'\',\'writeable\',0,'+row.orderno+')"><center>';
	        }
	    }
		
		function loadDisplayConfig(operateType,deviceType){
			$('#MeasureDisplayGrid').bootstrapTable('refresh',{url:"<c:url value='/measuredisplay/queryList.do'/>",query:{operatype:operateType,points:deviceType}});
		}
	</script>
</body>
</html>