<%@ page contentType="text/html;charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="common.jsp" flush="true"/>
	<style type="text/css">
		.select2-container--bootstrap .select2-selection{
			border-radius: 0px 4px 4px 0px;
		}
		.select2-container--bootstrap.input-sm .select2-selection--single, .input-group-sm .select2-container--bootstrap .select2-selection--single, .form-group-sm .select2-container--bootstrap .select2-selection--single{
			border-radius: 0px 4px 4px 0px;
		}
	</style>
</head>
<%
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
	SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
	Date now = new Date();
	Calendar c = Calendar.getInstance();
	now = c.getTime();
	String begintime = dateFormat.format(now);//开始时间
	String endtime = dateFormat1.format(now);//结束时间
%>
<body class="container-fluid" style="padding-top:15px;">
	<form id="queryForm">
		<div class="row" style="padding-left:5px;padding-right:5px;">
			<div class="col-sm-12">
				<div class="input-group input-group-sm">
					<span class="input-group-addon">分组类型</span>
					<select id="grouptype" name="grouptype" class="form-control select2" placeholder="必选" multiple="multiple">
						<option value="carno">车号</option>
						<option value="operatype">业务类型</option>
						<option value="materialname" selected="selected">货名</option>
						<option value="sourcename" selected="selected">供货</option>
						<!-- <option value="10">供货库位</option> -->
						<option value="targetname" selected="selected">收货</option>
						<!-- <option value="90">收货库位</option> -->
						<option value="grossweigh">衡器</option>
						<option value="taskcode">业务号</option>
						<!-- <option value="measuretype">计量方式</option>
						<option value="ship">船名</option> -->
					</select>
				</div>
				<script type="text/javascript">
					$('#grouptype').select2({
					  tags: true,
					  theme:"bootstrap"
					});
				</script>
			</div>
		</div>
		<div class="row" style="padding-left:5px;padding-right:5px;">
			<div class="col-sm-3"> 
				<div class="input-group input-group-sm">
					<span class="input-group-addon">时间类型</span>
					<select id="selecttime" name="selecttime" class="form-control select2" placeholder="时间类型">
						<option value="suttletime">净重时间</option>
						<option value="grosstime">毛重时间</option>
						<option value="taretime">皮重时间</option>
						
					</select>
				</div>
			</div>
			<div class="col-sm-3">
		        <div class='input-group input-group-sm date'>
		        	<span class="input-group-addon">开始时间</span>
	                <input type='text' class="form-control" placeholder="开始时间" id="begintime" name="begintime" value="<%=begintime%>"/>
	            </div>
		    </div>
		    <div class="col-sm-3">
		        <div class='input-group input-group-sm date'>
		        	<span class="input-group-addon">结束时间</span>
	                <input type='text' class="form-control" placeholder="结束时间" id="endtime" name="endtime" value="<%=endtime%>"/>
	            </div>
		    </div>
			<script type="text/javascript">
			    $(function () {
			        $('#begintime').datetimepicker({format: 'YYYY-MM-DD HH:mm:ss',locale:'zh-cn'});
			        $('#endtime').datetimepicker({format: 'YYYY-MM-DD HH:mm:ss',locale:'zh-cn'});
			        $("#begintime").on("dp.change",function (e) {
			            $('#endtime').data("DateTimePicker").minDate(e.date);
			        });
			        $("#endtime").on("dp.change",function (e) {
			            $('#begintime').data("DateTimePicker").maxDate(e.date);
			        });
			    });
			</script>
		</div>
		<div class="row" style="padding-left:5px;padding-right:5px;">				
			<div class="col-sm-3"> 
				<div class="input-group input-group-sm">
					<span class="input-group-addon">货　　名</span>
					<input type="text" name="materialname" class="form-control" placeholder="货名">
				</div>
			</div>
			<div class="col-sm-3"> 
				<div class="input-group input-group-sm">
					<span class="input-group-addon">供　　货</span>
					<input type="text" name="sourcename" class="form-control" placeholder="供货">
				</div>
			</div>
			<div class="col-sm-3"> 
				<div class="input-group input-group-sm">
					<span class="input-group-addon">收　　货</span>
					<input type="text" name="targetname" class="form-control" placeholder="收货">
				</div>
			</div>
			<div class="col-sm-3"> 
				<div class="input-group input-group-sm">
					<span class="input-group-addon">供货库位</span>
					<input type="text" name="sp" class="form-control" placeholder="供货库位">
				</div>
			</div>
		</div>
		<div class="row" style="padding-left:5px;padding-right:5px;">				
			<div class="col-sm-3"> 
				<div class="input-group input-group-sm">
					<span class="input-group-addon">车　　号</span>
					<input type="text" name="carno" class="form-control" placeholder="车号">
				</div>
			</div>
			<div class="col-sm-3"> 
				<div class="input-group input-group-sm">
					<span class="input-group-addon">业&nbsp;&nbsp;务&nbsp;号</span>
					<input type="text" name="taskcode" class="form-control" placeholder="业务号">
				</div>
			</div>
			<div class="col-sm-3"> 
				<div class="input-group input-group-sm">
					<span class="input-group-addon">业务类型</span>
					<select id="operatype" name="operatype" class="form-control select2-data-ajax" ajax-url="<c:url value='/measure/queryOperatype.do'/>">
					</select>
				</div>
			</div>
			<div class="col-sm-3"> 
				<div class="input-group input-group-sm">
					<span class="input-group-addon">收货库位</span>
					<input type="text" name="tp" class="form-control" placeholder="收货库位">
				</div>
			</div>
		</div>
		<div class="row" style="padding-left:5px;padding-right:5px;">				
			<div class="col-sm-3"> 
				<div class="input-group input-group-sm">
					<span class="input-group-addon">衡　　器</span>
					<select id="grossweigh" name="grossweigh" placeholder="衡器" class="form-control  select2-data-ajax" ajax-url="<c:url value='/measure/queryEquipment.do'/>">
					</select>
				</div>
			</div>
			<div class="col-sm-3"> 
				<div class="input-group input-group-sm">
					<span class="input-group-addon">计量方式</span>
					<input type="text" name="measuretype" class="form-control" placeholder="计量方式">
				</div>
			</div>
				<div class="col-sm-3"> 
				<div class="input-group input-group-sm">
					<span class="input-group-addon">船　　名</span>
					<input type="text" name="ship" class="form-control" placeholder="船名">
				</div>
			</div>
			<div class="col-sm-3 btn-group-sm" style="padding-top:5px;"> 
				<button id="searchMeasureSumBtn" type="button" class="btn btn-info" onclick="search()">
					<span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;查询
				</button>
				<button id="exportMeasureSumBtn" type="button" class="btn btn-danger" onclick="exportExcel()">
					<span class="glyphicon glyphicon-floppy-save" aria-hidden="true"></span>&nbsp;Excel导出
				</button>
			</div>
		</div>
	</form>
	<div class="row" style="padding-top:5px;padding-left:5px;padding-right:5px;">
		<div class="col-sm-12">
			<table id="MeasureSumGrid" data-toggle="table" data-pagination="true"
				   data-page-list="[10,30,50,All]"
				   data-row-style="rowStyle"
				   data-click-to-select="true"
				   data-query-params="queryParams"
				   data-show-footer="true"
				   data-side-pagination="server">
				<thead>
					<tr>
						<th data-field="matchid" data-halign="center" data-visible="false"></th>							
						<th data-field="carno" data-halign="center">车号</th>
						<th data-field="operatype" data-halign="center" data-formatter="opertypeFormatter">业务类型</th>
						<th data-field="taskcode" data-halign="center">业务号</th>
						<th data-field="materialname" data-halign="center">货名</th>
						<th data-field="sourcename" data-halign="center">供货</th>
						<th data-field="targetname" data-halign="center">收货</th>
						<th data-field="ship" data-halign="center">船名</th>
						<th data-field="grossweigh" data-halign="center">衡器</th>
						<th data-field="measuretype" data-halign="center">计量方式</th>
						<th data-field="carcount" data-halign="center">车数（辆）</th>
						<th data-field="gross" data-halign="center">毛重/t</th>
						<th data-field="tare" data-halign="center">皮重/t</th>
						<th data-field="deduction" data-halign="center"  >扣重/t</th>
						<th data-field="suttle" data-halign="center">净重/t</th>
						<th data-field="suttleb" data-halign="center">供方净重/t</th>
						<th data-field="accordrate" data-halign="center">称差率/‰</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<script type="text/javascript">
		jQuery(document).ready(function($) {
			search();
			$('.fixed-table-footer > table').remove();
			$('.fixed-table-footer').addClass('container-fluid').html('<div class="row"><div class="col-sm-12 text-center" style="font-size:15px;font-weight:bold" >总车数/辆:<span style="font-size:15px" id="sumcarcountFooter" class="label label-default">0.00</span> &nbsp;&nbsp;总毛重/t:<span style="font-size:15px" id="sumgrossFooter" class="label label-warning">0.00</span> &nbsp;&nbsp;总皮重/t:<span style="font-size:15px" id="sumtareFooter" class="label label-primary">0.00</span> &nbsp;&nbsp;总净重/t:<span style="font-size:15px" id="sumsuttleFooter" class="label label-success">0.00</span> &nbsp;&nbsp;总供方净重/t:<span style="font-size:15px" id="sumsuttlebFooter" class="label label-danger">0.00</span></div></div>');

		});
	
		$('#MeasureSumGrid').bootstrapTable({
			onLoadSuccess:function (data) {
		    	$('#sumgrossFooter').html(data.more.sumgross);
		    	$('#sumtareFooter').html(data.more.sumtare);
		    	$('#sumsuttleFooter').html(data.more.sumsuttle);
		    	$('#sumsuttlebFooter').html(data.more.sumsuttleb);
		    	$('#sumcarcountFooter').html(data.more.sumcarcount);
		    }
		});
		
		function queryParams(params) {
			return jQuery.extend({},params,$('#queryForm').serializeJson());
		}
		
		function opertypeFormatter(value, row, index) {
			return dictionaryConverter("<c:url value='/measurerule/loadoperatetype.do'/>",'operatype',value);
		}
		
		function search(){
			var selectedGroupColumns = $("#grouptype").val() + ",";
			$("#grouptype option").each(function(){
				var columnName = $(this).attr('value');
				if(selectedGroupColumns.indexOf(columnName+",") != -1){
					$('#MeasureSumGrid').bootstrapTable('showColumn',columnName);
				}else{
					$('#MeasureSumGrid').bootstrapTable('hideColumn',columnName);
				}
			});
			$('#MeasureSumGrid').bootstrapTable('refresh',{url:"<c:url value='/measureReport/queryMeasureSum.do'/>"});
		}
		
		function exportExcel(){
			commonExportExcel('计量汇总报表','MeasureSumGrid','queryForm');
		}
	</script>
</body>
</html>