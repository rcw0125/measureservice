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
				<input type="hidden" id="cardstate" name="cardstate" value="${param.cardstate}"> 
				<input type="hidden" id="operatype" name="operatype" value="${param.operatype}">
				<input type="hidden" id="planid" name="planid" value="${param.planidlist}">
				<input type="hidden" id="itemid" name="itemid" value="${param.itemidlist}">
				<input type="hidden" id="carnos" name="carnos" value="">
				 <div class="row"> 
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">车　号</span> 
							<input id="carno" name="carno" type="text" class="form-control" value="${param.carno}" data-provide="typeahead" aria-describedby="basic-addon1">
						</div>
					</div>
					<!-- <div class="col-sm-9 btn-group-sm">
						<button id="query" type="button" class="btn btn-info" onclick="queryinfo()">
							<span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;查询
						</button>						
					</div> -->
					<div class="row">
						<div class="col-sm-12 btn-group-sm" >
					        <font color="red" >　无作业路线单据不可用：无作业路线数据前端无选择框，不可选择</font>								
						</div>
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
								<th data-field="itemid" data-formatter="stateFormatter" data-align="center"><input type="checkbox" id="checkAll" onclick="checkAll()"/></th>
								<th data-field="carno" data-halign="center"  data-visible="false"></th>
								<th data-field="operatype" data-halign="center"
									data-align="center" data-searchable="true" data-visible="false"></th>
								<th data-field="routeid" data-halign="center"  data-visible="false" >路线</th>
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
			<div class="modal-footer  btn-group-sm">
				<button type="button" class="btn btn-success" onClick="yes()">确定</button>
				<button type="button" class="btn btn-default" data-dismiss="modal" onclick="exit()">关闭</button>
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
			///$("#operatype").val("");
		    ///$("#planid").val("");
			///$("#itemid").val("");
			$("#carno").val(carno_v);
			var cno =$("#carno").val();
			if(cno!=null && cno!='' ){
				if($("#cardstate").val()==1){
					$.ajax({
						type : "post",
						url : '<c:url value="/bcommon/judgOrFromcarno.do"/>',
						dataType : "json",
						data : {carno : cno,recordtype:0},
						success : function(data) {
							if (data.success == true) {
								$('#ApplicationGrid').bootstrapTable('refresh',{url : "<c:url value='/bcommon/queryPlaninfo.do'/>?page=1&rows=99999"});
							} else {
								//如果车辆或者卡有问题，系统提示
								errorbox(data.msg);
							}
						}
					});
				}else{
					$('#ApplicationGrid').bootstrapTable('refresh',{url : "<c:url value='/bcommon/queryPlaninfo.do'/>?page=1&rows=99999"});
				}
				
			}
			else{
				errorbox("请输入车号");
			}
		}
		
		function stateFormatter(value, row, index) {
			if(row.routeid==null || row.routeid==-1 || row.routeid==''){
				return '';
			}else{
				 var planids =$("#planid").val();
				 //var planid =planids.substring(1,planids.length-1);
				 var itemids =$("#itemid").val();
				var itemidlists=itemids.substring(1,itemids.length-1);
				if(itemidlists.indexOf(",")==-1){
			        if( row.saleitemid==itemidlists ){
						return '<center><input id=\"'+index+'\" name="ids" type="checkbox" checked onclick="putsaleitemid(\''+index+'\',\''+row.carno+'\',\''+row.operatype+'\',\''+row.planid+'\',\''+ row.saleitemid + '\')"  ><center>';
					}else{
						return '<center><input id=\"'+index+'\" name="ids" type="checkbox"  onclick="putsaleitemid(\''+index+'\',\''+row.carno+'\',\''+row.operatype+'\',\''+row.planid+'\',\''+ row.saleitemid + '\')"  ><center>';
					}
				}else{
					var flag=0;
					var str='';
					var itemidlist = itemidlists.split(",");
					for (var i=0;i<itemidlist.length;i++){
						if( row.saleitemid==itemidlist[i]){
							flag++;
						}
						
					}
					if(flag>0){
						str='<center><input id=\"'+index+'\" name="ids" type="checkbox" checked onclick="putsaleitemid(\''+index+'\',\''+row.carno+'\',\''+row.operatype+'\',\''+row.planid+'\',\''+ row.saleitemid + '\')"  ><center>';
						
					}else{
						str='<center><input id=\"'+index+'\" name="ids" type="checkbox"  onclick="putsaleitemid(\''+index+'\',\''+row.carno+'\',\''+row.operatype+'\',\''+row.planid+'\',\''+ row.saleitemid + '\')"  ><center>';
						
					}
					
					return str;
				}
			}
			
		}
		function putsaleitemid(index,carno,operatype,planid,saleitemid) {
		    var planids =$("#planid").val();
		    var itemids =$("#itemid").val();
			var oper= $("#operatype").val();	
			 $("#carnos").val(carno);
			if($('#'+index).is(':checked')){//点击选中
				if(oper!=null && oper!='' && operatype!=oper){				
					errorbox("请选择相同业务类型的信息");//业务类型不同，取消选中
					$('#'+index).prop({"checked":false});
				}else{
					$("#operatype").val(operatype);	
					if(planids!=null && planids !='' && planids!=(','+planid+",")){
						if(planids.indexOf("," + planid+",")==-1){
							$("#planid").val(planids + planid+ ",");
						}
						$("#itemid").val(itemids + saleitemid+ "," );
					}else{
						if(planids.indexOf("," + planid+",")==-1){
							if (planids.length == 0) {
								$("#planid").val(","+planid+",");
							} else {
								$("#planid").val(planids + planid+ ",");
							}
						}			
						if (itemids.length == 0) {
							$("#itemid").val(","+saleitemid+",");
						} else {
							$("#itemid").val(itemids + saleitemid+ "," );
						}
					}
					
				}
			}else{//点击取消选中	
				if($('input:checked').length==1){
					$("#operatype").val("");
					$("#planid").val("");
					$("#itemid").val("");
				 }else{
					 //$("#planid").val(planids.replace("," + planid+"," , ",")); 
					 $("#itemid").val(itemids.replace("," + saleitemid+"," , ","));
				 }					
			}
		}
		function yes() {	
			if($('#planid').val().length==0){
				errorbox("请选择信息");
			}else{
				parent.takeBack($("#carnos").val(),$("#operatype").val(),$('#planid').val(),$("#itemid").val());
		       	parent.closeCheckCarno();	
			}	       	
	     }
		function exit() {
	       	 parent.closeCheckCarno();
	    }
		
		function checkAll(){
			if($('#checkAll').is(':checked')){
				$("input[name='ids']:checkbox").prop({"checked":true});
				$("#operatype").val("");
				$("#planid").val("");
				$("#itemid").val("");
				$("#carnos").val("")
				var gridData = $('#ApplicationGrid').bootstrapTable('getData')
				for (var i = 0; i < gridData.length; i++) {
					if(i==0){
						 $("#operatype").val(gridData[i].operatype);	
						 $("#planid").val(","+gridData[i].planid+ ",");
						 $("#itemid").val(","+gridData[i].saleitemid+ "," );
						 $("#carnos").val(gridData[i].carno);
						 
					}/* else if(i==gridData.length-1){
						if(planids.indexOf("," + gridData[i].planid+",")==-1){
							$("#planid").val($("#planid").val()+planid);
						}
						$("#itemid").val($("#itemid").val() + gridData[i].saleitemid );
					} */else{
						if($("#operatype").val()==gridData[i].operatype){
							if($("#planid").val().indexOf("," + gridData[i].planid+",")==-1){
								$("#planid").val($("#planid").val()+gridData[i].planid+",");
							}
							$("#itemid").val($("#itemid").val() + gridData[i].saleitemid+ "," );
						}else{
							errorbox("选中的业务信息中业务类型不相同，请选择相同业务类型的信息");
							$("input[name='ids']:checkbox").prop({"checked":false});
							$("#checkAll:checkbox").prop({"checked":false});
							$("#operatype").val("");
							$("#planid").val("");
							$("#itemid").val("");
							return;
						}
						
					}
				}
				
			}else{
				$("input[name='ids']:checkbox").prop({"checked":false});
				$("#operatype").val("");
				$("#planid").val("");
				$("#itemid").val("");
			}
			
		} 
   </script>
</body>
</t:html>