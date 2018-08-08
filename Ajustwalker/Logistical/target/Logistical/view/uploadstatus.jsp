<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="t" uri="http://www.talent.com/jsp/anyonelse"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.Date"%>
<t:html>
	<head>
		<jsp:include page="common.jsp" flush="true" />
	</head>
	<%
		SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd");
		Calendar preFiveDays = Calendar.getInstance();
		preFiveDays.add(Calendar.DAY_OF_YEAR, -5);
		String begintime = dateFormat.format(preFiveDays.getTime()) + " 00:00:00";//开始时间
		String endtime = dateFormat.format(Calendar.getInstance().getTime()) + " 23:59:59";//结束时间
	%>
	<body class="container-fluid" style="padding-top: 15px">
		<div class="row">
			<div class="col-sm-12">
				<form id="queryform">			
					<input type="hidden" id="types" name="types" value="0">
					<div class="row">
						<div class="col-sm-3">
							<div class='form-group input-group input-group-sm date'>
								<span class="input-group-addon">开始时间</span> 
								<input type='text' class="form-control" placeholder="开始时间" id="begintime" name="begintime" value="<%=begintime%>" />
							</div>
							<input type="hidden" id="cartype" name="cartype" value="${param.cartype}" />
						</div>
						<div class="col-sm-3">
							<div class="form-group input-group input-group-sm">
								<span class="input-group-addon" id="sizing-addon3">货　　名</span> 
								<input type="text" class="form-control" placeholder="货名" id="materialname" name="materialname" aria-describedby="sizing-addon3">
							</div>
						</div>
						<div class="col-sm-3">
							<div class="form-group input-group input-group-sm">
								<span class="input-group-addon" id="sizing-addon3">车　　号</span>
								<input type="text" class="form-control" placeholder="车号" id="carno" name="carno" aria-describedby="sizing-addon3">
							</div>
						</div>
						<div class="col-sm-3">
							<div class="form-group input-group input-group-sm">
								<span class="input-group-addon" id="sizing-addon3">单&nbsp;&nbsp;据&nbsp;&nbsp;号 </span> 
								<input type="text" class="form-control" placeholder="单据号" id="planid" name="planid" aria-describedby="sizing-addon3">
							</div>
						</div>
					</div>
					<div class="row" >
						<div class="col-sm-3">
							<div class='form-group input-group input-group-sm date'>
								<span class="input-group-addon">结束时间</span> 
								<input type='text' class="form-control" placeholder="结束时间" id="endtime" name="endtime" value="<%=endtime%>" />
							</div>
						</div>
						<div class="col-sm-3">
							<div class="form-group input-group input-group-sm">
								<span class="input-group-addon" id="sizing-addon3">物流编码</span> 
								<input type="text" class="form-control" placeholder="物流编码" id="matchid" name="matchid" aria-describedby="sizing-addon3">
							</div>
						</div>
						<div class="col-sm-3">
							<div class="form-group input-group input-group-sm">
								<span class="input-group-addon" id="sizing-addon3">上传状态</span>
							    <select id="updatencflag" name="updatencflag" class="form-control select2">
									<option value="-1">全部</option>
									<option value="0">未上传</option>
									<option value="1">已上传</option>
								</select> 
							</div>
						</div>
						<div class="col-sm-3">
							<div class="form-group btn-group-sm">
								<button type="button" class="btn btn-info" onclick="queryinfo()">
									<span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;查询
								</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<table id="NcUploadInfoGrid" data-toggle="table" data-row-style=rowStyle" data-query-params="queryParams" data-pagination="true" data-page-size="10" data-page-list="[10,40,ALL]" data-mobile-responsive="true">
					<thead>
						<tr>
							<th data-field="PLANID" data-halign="center" class="text-nowrap">单据号</th>
							<th data-field="MATCHID" data-halign="center" class="text-nowrap">物流编码</th>
							<th data-field="CARNO" data-halign="center" class="text-nowrap" >车号</th>
							<th data-field="MATERIALCODE" data-halign="center" class="text-nowrap">货名编码</th>	
							<th data-field="MATERIALNAME" data-halign="center" class="text-nowrap">货名</th>
							<th data-field="SUTTLE" data-halign="center" class="text-nowrap">重（数）量</th>
							<th data-field="SUTTLETIME" data-halign="center" data-align="center" class="text-nowrap">净重时间</th>
							<th data-field="UPDATENCFLAG" data-halign="center" 	data-align="center" class="text-nowrap" data-formatter="ncUploadflagFormatter">上传状态</th>
							<th data-field="UPDATENCDATE" data-halign="center" class="text-nowrap">上传时间</th>
							<th data-width="70px" data-align="center" data-valign="middle" data-formatter="operateFormatter" data-events="operateEvents">操作</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
		<script type="text/javascript">
			jQuery(document).ready(function($) {				
				queryinfo()
			});
			
			$(function() {
				$('#begintime').datetimepicker({
					format : 'YYYY-MM-DD HH:mm:ss',
					locale : 'zh-cn'
				});
				$('#endtime').datetimepicker({
					format : 'YYYY-MM-DD HH:mm:ss',
					locale : 'zh-cn'
				});
			
				$("#begintime").on("dp.change", function(e) {
					$('#endtime').data("DateTimePicker").minDate(e.date);
				});
				$("#endtime").on("dp.change", function(e) {
					$('#begintime').data("DateTimePicker").maxDate(e.date);
				});
			});
			
			function queryParams(params) {
				return jQuery.extend({},params,$('#queryform').serializeJson());
			}
			
			function queryinfo() {
				$('#NcUploadInfoGrid').bootstrapTable('refresh',{
					url:"<c:url value='/interface/queryUploadInfoList.do'/>"
				});
			}
			
			function operateFormatter(value, row, index) {
	            return [
	               	'<a class="edit" href="javascript:void(0)" title="上传">',
	               	'<i class="glyphicon glyphicon-cloud"></i>',
	               	'</a>'
	           	].join('');
	        }
			
			function ncUploadflagFormatter(value,row,index){
				if(1 == value){
					return '已上传';
				}else{
					return '未上传';
				}
			}
			
	        window.operateEvents = {
	            'click .edit':function(e,value,row) {
	            	dialogbox('提示','上传数据？',function(){
	            		var url_v = '';
		            	if(row.ISORMEASURE == 0){
		            		url_v = '<c:url value="/unauth/interface/upload"/>?matchid='+row.MATCHID+'&isormeasure=0';
		            	}else{
		            		url_v = '<c:url value="/unauth/interface/upload"/>?matchid='+row.MATCHID;
		            	}
		            	$.ajax({
							type : "post",
							url : url_v,
							dataType : "json",
							success : function(data) {
								if(data.success){
									successbox('操作成功！');
								}else{
									errorbox(data.msg);
								}
							}
						});
	            	});
	            }
	        };
		</script>
	</body>
</t:html>