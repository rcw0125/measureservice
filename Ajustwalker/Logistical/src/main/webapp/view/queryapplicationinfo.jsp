<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="t" uri="http://www.talent.com/jsp/anyonelse"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.Date"%>
<t:html>
<head>
<jsp:include page="/view/common.jsp" flush="true" />
<script type="text/javascript" src="<c:url value='/plugins/bootstrap-typeahead.js'/>"></script>
</head>
<body class="container-fluid">
	<div class="row" style="padding-top: 5px">
		<div class="col-sm-12">
			<form id="queryform">
				 <div class="row"> 
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">车　号</span> 
						  	<input id="carno" name="carno" type="text" class="form-control" value="${param.carno}" data-provide="typeahead" aria-describedby="basic-addon1">
						</div>
					</div>
					<!--  <div class="col-sm-9 btn-group-sm">
						<button id="query" type="button" class="btn btn-info" onclick="queryinfo()">
							<span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;查询
						</button>						
					</div> -->
				</div>
				 <div class="row"> 

					<div class="col-sm-12 btn-group-sm" >
				        <font color="red" >如果输入车号查询不到信息，请确认单据状态：NC单据是否导入到物流系统；单据是否在有效期；单据车号是否正确；其他单据是否审核</font>								
					</div>
	
				</div>
			</form>
			<div class="row">
				<div class="col-sm-12">
					<table id="ApplicationGrid" data-toggle="table"
						data-row-style="rowStyle" data-query-params="queryParams"
						data-page-size="10" data-click-to-select="true">
						<thead>
							<tr>
								
								<th data-field="operatype" data-halign="center"
									data-align="center" data-searchable="true" data-visible="false"></th>
								<th data-field="operaname" data-halign="center"
									data-align="center" data-searchable="true" class="text-nowrap">业务类型</th>
								<th data-field="documentname" data-halign="center"
									data-align="center" data-searchable="true" class="text-nowrap">业务名称</th>	
								<th data-field="planid" data-halign="center"
									data-align="center" data-searchable="true" class="text-nowrap">单据号</th>
								<th data-field="saleitemid" data-halign="center"
									data-align="center" data-searchable="true" class="text-nowrap" data-visible="false">单据行号</th>
								<th data-field="materialcode" data-halign="center"
									data-searchable="true" class="text-nowrap">货名编码</th>	
								<th data-field="materialname" data-halign="center"
									data-searchable="true" class="text-nowrap">货名</th>		
								<th data-field="materialspec" data-halign="center"
									data-searchable="true" class="text-nowrap">规格</th>	
								<th data-field="steelname" data-halign="center"
									data-searchable="true" class="text-nowrap">型号</th>								
								<th data-field="sourcename" data-halign="center"
									data-searchable="true" class="text-nowrap">供货</th>	
								<th data-field="targetname" data-halign="center"
									data-align="center" data-searchable="true" class="text-nowrap">收货</th>
								<th data-field="snumber" data-halign="center"
									data-align="center" data-searchable="true" class="text-nowrap">数量</th>
								<th data-field="measureunit" data-halign="center"
									data-align="center" data-searchable="true" class="text-nowrap">计量单位</th>
								<th data-field="arrivetime" data-halign="center"
									data-align="center" data-searchable="true" class="text-nowrap">到货时间</th>
								<th data-field="usermemo" data-halign="center"
									data-align="center" data-searchable="true" class="text-nowrap">备注</th>	
							</tr>
						</thead>
					</table>
				</div>
			</div>
			
		</div>
	</div>
	<script type="text/javascript">
		jQuery(document).ready(function($) {
			//$('#carno').focus();
			var cno =$("#carno").val();
			if(cno!=null && cno!='' ){
				$("#carno").attr("readonly","readonly");
				queryinfo(cno);
			}
			
			 $.fn.typeahead.Constructor.prototype.blur = function() {
			      var that = this;
			      setTimeout(function(){that.hide()},250);
		     };

		   $('#carno').typeahead(
				   {source:function (query, process) {		
				        $.ajax({
							type : "post",
							url : '<c:url value="/bcommon/queryCarno.do?carno="/>'+query,
							dataType : "json",
							success : function(data) {	
								var numArr = []; // 定义一个空数组
								 for (var i = 0; i < data.rows.length; i++) {
					                 numArr.push(data.rows[i].carno); // 将文本框的值添加到数组中
					             }							 
								 process(numArr);							
							}
				       });			        
				    },
				    updater:function(data){
				    	queryinfo(data);
				    	return data;
				    }
			});
		});

		
		function queryParams(params) {
			return jQuery.extend({},params,$('#queryform').serializeJson());
		}
	
		function queryinfo(carno_v) {
			$("#carno").val(carno_v);
			var cno =$("#carno").val();
			if(cno!=null && cno!='' ){
				$('#ApplicationGrid').bootstrapTable('refresh',{url : "<c:url value='/bcommon/queryPlaninfo.do'/>?page=1&rows=99999"});
				
			}else{
				errorbox("请输入车号");
			}
		}

   </script>
</body>
</t:html>