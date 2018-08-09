<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.Date"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<jsp:include page="common.jsp" flush="true" />
		<%
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
			SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
			Date now = new Date();
			Calendar c = Calendar.getInstance();
			now = c.getTime();
			String begintime = dateFormat.format(now);//开始时间
			String endtime = dateFormat1.format(now);//结束时间
		%>
	</head>
	<body class="container-fluid">
		<div class="row" style="padding-left: 5px; padding-right: 5px;">
			<form id="queryform">
				<div class="col-sm-3">
					<div class='input-group input-group-sm date'>
						<span class="input-group-addon">开始时间</span> <input type='text'
							class="form-control" placeholder="开始时间" id="begintime"
							name="begintime" value="<%=begintime%>" />
					</div>
				</div>
				<div class="col-sm-3">
					<div class='input-group input-group-sm date'>
						<span class="input-group-addon">结束时间</span> <input type='text'
							class="form-control" placeholder="结束时间" id="endtime"
							name="endtime" value="<%=endtime%>" />
					</div>
				</div>
				<div class="col-sm-3">
					<div class="input-group input-group-sm">
						<span class="input-group-addon" id="sizing-addon3">计量员</span> <input
							type="text" class="form-control" placeholder="计量员" id="opername"
							name="opername" aria-describedby="sizing-addon3">
					</div>
				</div>
				<div class="col-sm-3 btn-group-sm" style="padding-top: 5px;">
					<button id="query" type="button" class="btn btn-info"
						onclick="queryinfo()">
						<span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;查询
					</button>
					<button id="delResourceBtn" type="button" class="btn btn-danger" onclick="exportExcel()">
						<span class="glyphicon glyphicon-floppy-save" aria-hidden="true"></span>&nbsp;Excel导出
					</button>
		
				</div>
			</form>
		</div>
		
		<div class="row" style="padding-top: 5px; padding-left: 5px; padding-right: 5px;">
			<div class="col-sm-12">
				<table id="TaskCountGrid" data-toggle="table"
					data-query-params="queryParams" data-row-style="rowStyle"
					data-side-pagination="server">
					<thead>
						<tr>
							<th data-field="opername" data-halign="center" sortable>计量员</th>
							<th data-field="carcount" data-halign="center"
								class="text-nowrap text-right" sortable>正常任务</th>
							<th data-field="avgtime" data-halign="center"
								class="text-nowrap text-right">平均速度/s</th>
							<th data-field="backtask" data-halign="center"
								class="text-nowrap text-right" data-searchable="true">回退任务</th>
							<th data-field="finishtask" data-halign="center"
								class="text-nowrap text-right" class="text-nowrap">终止任务</th>
							<th data-field="mvcardtask" data-halign="center"
								class="text-nowrap text-right" class="text-nowrap">移卡终止任务</th>
							<th data-field="autobacktask" data-halign="center"
								class="text-nowrap text-right" class="text-nowrap">系统自动回退</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	
		<div class="row">
			<div id="line" style="width: 100%; height: 400px;"></div>
		</div>
	
		<div class="row">
			<div id="bar" style="width: 100%; height: 400px;"></div>
		</div>
	
		<script type="text/javascript">
			var myBar
			var myLine;
			
			jQuery(document).ready(function($) {
				queryinfo();
			});
			
			function queryinfo() {
				$('#TaskCountGrid').bootstrapTable('refresh', {
					url : "<c:url value='/StatisticsReport/querytaskdata.do'/>"
				});
			}
			
			$('#TaskCountGrid').bootstrapTable({
				onLoadSuccess : function(data) {
					getpicture();
				}
			});
	
			$(function() {
				$('#begintime,#endtime').datetimepicker({
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
				return jQuery.extend({}, params, $('#queryform').serializeJson());
			}
	
			function getpicture() {
				var length;
				var labelslist = new Array(); //计量员集合
				var datalist = new Array(); //任务集合
				var avgtimelist = new Array();//个人平均时间集合
				var avgcarcountlist = new Array();//平均任务
				var avgcounttimelist = new Array();//平均时间
				var avgcounttime;
				var avgcarcount;
				$("#queryform").ajaxSubmit({
					url : '<c:url value="/StatisticsReport/queryavgtaskinfo.do"/>',
					dataType : 'json',
					success : function(data) {
						avgcarcount = data.avgcarcount;
						avgcounttime = data.avgcounttime;
						length = data.list.length;
						for (var i = 0; i < data.list.length; i++) {
							labelslist.push(data.list[i].opername);
							datalist.push(data.list[i].carcount);
							avgtimelist.push(data.list[i].avgtime);
	
						}
						
						for (var m = 0; m < length; m++) {
							avgcounttimelist.push(avgcounttime);
							avgcarcountlist.push(avgcarcount);
						}
						
						// 基于准备好的dom，初始化echarts实例
						try
						{
							$('#bar').empty();
						}
						catch(e)
						{
							
						}
						myBar = echarts.init(document.getElementById('bar'));
			
						// 指定图表的配置项和数据
						var option = {
							title : {
								left : '50%',
								text : '计量任务统计'
							},
							tooltip : {
								
							},
							toolbox : {
								feature : {
									saveAsImage : {}
								}
							},
							grid : {
								left : '1%',
								right : '1%',
								bottom : '15',
								containLabel : true
							},
							xAxis : {
						
					                     type : 'category',
					                     axisLabel:{
					                         interval:0
					                     },
								data : labelslist
							},
							yAxis : {},
							series : [ {
								
								name : '正常任务',
								type : 'bar',
								stack : '总量',
								label : {
									normal : {
										show : true,
										position : 'top'
									}
								},
								areaStyle : {
									normal : {}
								},
								
								data : datalist
							} ]
						};
			
						// 使用刚指定的配置项和数据显示图表。
						myBar.setOption(option);
			
						try
						{
							$('#line').empty();
						}
						catch(e)
						{
							
						}
						myLine = echarts.init(document.getElementById('line'));
			
						// 指定图表的配置项和数据
						option = {
							title : {
								left : '50%',
								text : '计量速度统计'
							},
							tooltip : {
								trigger : 'axis'
							},
			
							toolbox : {
								feature : {
									saveAsImage : {}
								}
							},
							grid : {
								left : '1%',
								right : '2%',
								bottom : '1%',
								containLabel : true
							},
							xAxis : [ {
								type : 'category',
								boundaryGap : false,
								data : labelslist
							} ],
							yAxis : [ {
								type : 'value'
							} ],
							series : [
			
							{
			
								type : 'line',
								stack : '',
								label : {
									normal : {
										show : true,
										position : 'top'
									}
								},
								areaStyle : {
									normal : {}
								},
								data : avgtimelist
							}, {
			
								type : 'line',
								//stack : '总量',
								label : {
									normal : {
										show : true,
										position : 'top'
									}
								},
								areaStyle : {
									normal : {}
								},
								data : avgcounttimelist
							}
			
							]
						};
			
						// 使用刚指定的配置项和数据显示图表。
						myLine.setOption(option);
					},
					error : function() {
						alert("请求失败！");
					}
				});
			}
			
			function exportExcel() {
				commonExportExcel('任务统计报表', 'TaskCountGrid', 'queryform');
			}
		</script>
	</body>
</html>