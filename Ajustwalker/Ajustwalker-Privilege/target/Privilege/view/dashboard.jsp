<%@ page contentType="text/html;charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="t" uri="http://www.talent.com/jsp/anyonelse"%>
<t:html>
	<head>
		<jsp:include page="common.jsp" flush="true"/>
	   <style>
			body{
				background-color: #ecf0f5;
			}
			.box-body {
			  border-top-left-radius: 0;
			  border-top-right-radius: 0;
			  border-bottom-right-radius: 3px;
			  border-bottom-left-radius: 3px;
			  padding: 10px;
			}
			.info-box{
				border: 1px solid #dddddd;
			}
		</style> 
	</head>
	
	<body >
		<div class="box">
			<div class="box-header with-border">
				<h1 class="box-title" >欢迎使用邢台钢铁进出厂物流系统</h1>
			</div>
			<div class="box-body" >
				<div class="row" id ="counts">
					<div class="col-md-3 col-sm-6 col-xs-12">
						<div class="info-box">
							<span class="info-box-icon bg-aqua"><i class="fa fa-area-chart"  onmouseover="this.style.cursor='pointer'"></i></span>
							<div class="info-box-content">
								<span class="info-box-text">CPU各核心平均使用率</span><span class="info-box-number"><span id="cpuUtilizationRatio">0</span><small>%</small></span>
							</div>
						</div>
					</div>
					<div class="col-md-3 col-sm-6 col-xs-12">
						<div class="info-box">
							<span class="info-box-icon bg-red"><i class="fa fa-microchip"  onmouseover="this.style.cursor='pointer'"></i></span>
							<div class="info-box-content">
								<span class="info-box-text">内存使用率</span> <span class="info-box-number"><span id="memoryUtilizationRatio">0</span><small>%</small></span>
							</div>
						</div>
					</div>
					<div class="clearfix visible-sm-block"></div>
					<div class="col-md-3 col-sm-6 col-xs-12">
						<div class="info-box">
							<span class="info-box-icon bg-green"><i class="fa fa-database"  onmouseover="this.style.cursor='pointer'"></i></span>
							<div class="info-box-content">
								<span class="info-box-text">磁盘使用率</span> <span class="info-box-number"><span id="diskUtilizationRatio">0</span><small>%</small></span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
	<script type="text/javascript">
		var html = '';
		jQuery(document).ready(function($){
			querycount();
			html = document.getElementById('counts').innerHTML;
		});
		window.setInterval("querycount()",1000*60*2);//
		function querycount(){
			$.ajax({
				type : "post",
				url : '<c:url value="/querycount"/>',
				dataType : "json",
				success : function(data) {
					
					if (data.success == true) {
						document.getElementById('counts').innerHTML=
							"<div class='col-md-3 col-sm-6 col-xs-12'>"+
								"<div class='info-box'>"+
									"<span class='info-box-icon bg-yellow'><i class='fa fa-users'  onmouseover='this.style.cursor=pointer'></i></span>"+
									"<div class='info-box-content'>"+
										"<span class='info-box-text'><a href='javascript:void(0)'  style='cursor:pointer;'>在线车辆</a></span> <span id='onlineuser' class='info-box-number'>"+data.data+"</span>"+
									"</div>"+
								"</div>"+
						"</div>"+html;
					}else{
						document.getElementById('counts').innerHTML =html;
					}
				}
			});
		}
	</script>
</t:html>