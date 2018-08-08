<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>

<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="common.jsp" flush="true" />

</head>

<div id="main" style="width: 600px; height: 400px;"></div>
<div id="line" style="width: 600px; height: 400px;"></div>
<script type="text/javascript">
	// 基于准备好的dom，初始化echarts实例
	var myChart = echarts.init(document.getElementById('main'));

	// 指定图表的配置项和数据
	var option = {
		title : {
			text : 'ECharts 入门示例'
		},
		tooltip : {},

		xAxis : {
			data : [ "衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子" ]
		},
		yAxis : {},
		series : [ {
			name : '销量',
			type : 'bar',
			label : {
				normal : {
					show : true,
					position : 'inside'
				}
			},
			data : [ 5, 20, 36, 10, 10, 20 ]
		} ]
	};

	// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(option);

	var myLine = echarts.init(document.getElementById('line'));

	// 指定图表的配置项和数据
	option = {
		title : {
			text : '堆叠区域图'
		},
		tooltip : {
			trigger : 'axis'
		},
		legend : {
			data : [ '邮件营销', '联盟广告', '视频广告', '直接访问', '搜索引擎' ]
		},
		toolbox : {
			feature : {
				saveAsImage : {}
			}
		},
		grid : {
			left : '3%',
			right : '4%',
			bottom : '3%',
			containLabel : true
		},
		xAxis : [ {
			type : 'category',
			boundaryGap : false,
			data : [ '周一', '周二', '周三', '周四', '周五', '周六', '周日' ]
		} ],
		yAxis : [ {
			type : 'value'
		} ],
		series : [

		{
			name : '搜索引擎',
			type : 'line',
			stack : '总量',
			label : {
				normal : {
					show : true
				}
			},
			areaStyle : {
				normal : {}
			},
			data : [ 820, 932, 901, 934, 1290, 1330, 1320 ]
		} ]
	};

	// 使用刚指定的配置项和数据显示图表。
	myLine.setOption(option);
</script>
</body>
</html>