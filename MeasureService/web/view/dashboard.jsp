<%@ page contentType="text/html;charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<jsp:include page="common.jsp" flush="true"/>
		<script type="text/javascript" src="<c:url value='/plugins/isotope/isotope.pkgd.min.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/plugins/flot/js/jquery.flot.min.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/plugins/flot/js/jquery.flot.spline.min.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/plugins/flot/js/jquery.flot.stack.min.js'/>"></script>
		<style type="text/css">
			.carousel-inner{
				margin-bottom:-7px;
			}
			.main-box {
				background:#FFFFFF;
				box-shadow:1px 1px 2px 0 #CCCCCC;
				margin-bottom:16px;
				border-radius:3px;
				background-clip:padding-box;
			}
			@media (max-width: 767px) {
				.main-box {
				margin-bottom:10px;
			}
			}.main-box h2 {
				font-size:1.3em;
				line-height:29px;
				margin:0;
				padding:0;
			}
			@media (max-width: 419px) {
				.main-box h2 {
				margin-bottom:5px;
			}
			}.main-box.no-header {
				padding-top:20px;
			}
			.main-box .main-box-header {
				min-height:50px;
				padding:10px 20px;
			}
			.main-box .main-box-header.with-border {
				border-bottom:1px solid #ecf0f1;
			}
			.main-box .main-box-body {
				padding:0 0px 0px 0px;
			}
			.profile-box-menu .main-box-body {
				padding:0;
			}
			.profile-box-menu .profile-box-header {
				padding:10px 10px;
				color:#fff;
				border-radius:3px 3px 0 0;
				background-clip:padding-box;
			}
			.profile-box-menu .profile-img {
				border-radius:50%;
				background-clip:padding-box;
				width:110px;
				height:110px;
				float:left;
				margin-right:15px;
				border:5px solid #fff;
			}
			.profile-box-menu h2 {
				padding:20px 0 3px;
				margin:0;
				display:inline-block;
				font-weight:400;
				line-height:1.1;
			}
			.profile-box-menu .job-position {
				font-weight:300;
				font-size:0.875em;
			}
			.profile-box-menu .profile-box-content .menu-items {
				margin:0;
				padding:0;
				list-style:none;
			}
			.profile-box-menu .profile-box-content .menu-items li a {
				display:block;
				height:40px;
				line-height:40px;
				border-bottom:1px solid #e7ebee;
				padding:0 20px;
				font-size:0.875em;
				-webkit-transition:background-color 0.15s ease-in-out 0s;
				transition:background-color 0.15s ease-in-out 0s;
				color:#344644;
			}
			.profile-box-menu .profile-box-content .menu-items li a:hover {
				background-color:#e7ebee;
				text-decoration:none;
			}
			.profile-box-menu .profile-box-content .menu-items li a i {
				width:24px;
			}
			.profile-box-menu .profile-box-content .menu-items li a span.label {
				margin-top:10px;
			}
			.profile-box-menu .profile-box-content .menu-items li:last-child a {
				border-bottom:none;
			}
			.emerald-bg {
				background-color:#3498db!important;
			}
			.red-bg {
				background-color:#e74c3c!important;
			}
			.yellow-bg {
				background-color:#f1c40f!important;
			}
			.green-bg {
				background-color:#2ecc71!important;
			}
			.purple-bg {
				background-color:#9b59b6!important;
			}
			.gray-bg {
				background-color:#95a5a6!important;
			}
			.white-bg {
				background-color:#ffffff!important;
			}
			h1 small,h2 small,h3 small,h1 .small,h2 .small,h3 .small {
				padding-left:8px;
			}
			.small-graph-box {
				padding:20px;
				color:#fff;
			}
			.small-graph-box .headline {
				display:block;
				font-size:0.875em;
				font-weight:400;
				margin-top:-5px;
				padding-bottom:5px;
				text-transform:uppercase;
			}
			.small-graph-box .value {
				display:block;
				font-size:1.9em;
				font-weight:600;
				margin-top:-5px;
			}
			.small-graph-box .progress {
				background:rgba(0,0,0,0.2);
				height:5px;
				margin-bottom:5px;
				margin-top:10px;
			}
			.small-graph-box .progress .progress-bar {
				background-color:#fff;
			}
			.small-graph-box .subinfo {
				display:block;
				font-size:0.8em;
				padding-top:5px;
			}
			.small-graph-box .subinfo i {
				font-size:1.2em;
			}
		</style>
	</head>
	<body class="section container-fluid" style="padding-top:20px;">
		<c:if test="${optrRole == '计量'}">
			<div class="row-fluid">				
				<div class="col-sm-12">
					<div class="panel panel-danger" style="width:100%;">
						<div class="panel-heading">任务监控</div>
						<div class="panel-body" style="padding-bottom:0px;">
							<div class="container-fluid" style="padding:0px;">
								<div class="row">
									<div class="col-md-3 col-sm-6 col-xs-12">
										<div class="main-box small-graph-box green-bg">
											<span class="value">15</span>
											<span class="headline">任务请求总数</span>
											<div class="progress">
												<div style="width: 60%;" aria-valuemax="100" aria-valuemin="0" aria-valuenow="60" role="progressbar" class="progress-bar">
													<span class="sr-only">60% Complete</span>
												</div>
											</div>
											<span class="subinfo">
												<i class="fa fa-users"></i>&nbsp;最近5分钟任务数量：5个
											</span>
										</div>
									</div>
									<div class="col-md-3 col-sm-6 col-xs-12">
										<div class="main-box small-graph-box emerald-bg">
											<span class="value">5</span>
											<span class="headline">正在处理的任务</span>
											<div class="progress">
												<div style="width: 84%;" aria-valuemax="100" aria-valuemin="0" aria-valuenow="84" role="progressbar" class="progress-bar">
													<span class="sr-only">84% Complete</span>
												</div>
											</div>
											<span class="subinfo">
												<i class="fa fa-globe"></i>&nbsp;任务处理排名：1、张三，2、李四，3、王五
											</span>
										</div>
									</div>
									<div class="col-md-3 col-sm-6 col-xs-12">
										<div class="main-box small-graph-box red-bg">
											<span class="value">10</span>
											<span class="headline">正在排队的任务</span>
											<div class="progress">
												<div style="width: 42%;" aria-valuemax="100" aria-valuemin="0" aria-valuenow="42" role="progressbar" class="progress-bar">
													<span class="sr-only">42% Complete</span>
												</div>
											</div>
											<span class="subinfo">
												<i class="fa fa-shopping-cart"></i>&nbsp;当前排队任务过多，请尽快处理！
											</span>
										</div>
									</div>
									<div class="col-md-3 col-sm-6 col-xs-12">
										<div class="main-box small-graph-box yellow-bg">
											<span class="value">3</span>
											<span class="headline">转发的任务</span>
											<div class="progress">
												<div style="width: 42%;" aria-valuemax="100" aria-valuemin="0" aria-valuenow="42" role="progressbar" class="progress-bar">
													<span class="sr-only">42% Complete</span>
												</div>
											</div>
											<span class="subinfo">
												<i class="fa fa-shopping-cart"></i>&nbsp;新东门4#衡器过磅时间过长！
											</span>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row-fluid">				
				<div class="col-sm-12">
					<div class="panel panel-success" style="width:100%;">
						<div class="panel-heading">衡器监控</div>
						<div class="panel-body" style="padding:0px;">
							<div class="container-fluid" style="padding-top:15px;">
								<c:forEach items="${equipmentList}" var="item" varStatus="status">
									<c:if test="${status.index%6 == 0}">
										<div class="row" style="padding-right:0px;">
									</c:if>
									<div class="col-md-2 col-sm-4 col-xs-12">
										<div class="main-box clearfix profile-box-menu">
											<div class="main-box-body clearfix">
												<div id="weighter${item.equcode}" class="profile-box-header green-bg clearfix">
													<span class="fa fa-truck fa-3x"></span><br/>
													<h2 style="padding-top:5px;">${item.equname}</h2>
													<div id="status${item.equcode}" class="job-position">
														正在计量
													</div>
												</div>
												<div class="profile-box-content clearfix">
													<ul class="menu-items">
														<li>
															<a href="#" class="clearfix">
																<i class="fa fa-bell-o fa-lg"></i>车数
																<span id="weightcount${item.equcode}" class="label label-danger label-circle pull-right">0</span>
															</a>
														</li>
														<li>
															<a href="#" class="clearfix">
																<i class="fa fa-clock-o fa-lg"></i>时间
																<span id="timecount${item.equcode}" class="label label-success label-circle pull-right">0</span>
															</a>
														</li>
														<li>
															<a href="#" class="clearfix">
																<i class="fa fa-print fa-lg"></i>打印
																<span class="label label-warning label-circle pull-right">正常</span>
															</a>
														</li>
														<li>
															<a href="#" class="clearfix">
																<span id="errormsg${item.equcode}" class="label label-primary label-circle pull-right">无异常</span>
															</a>
														</li>
													</ul>
												</div>
											</div>
										</div>
									</div>
									<c:if test="${status.index%6 == 5 || status.index == (fn:length(equipmentList) - 1)}">
										</div>
									</c:if>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
		</c:if>
		<c:if test="${optrRole == '系统'}">
			<h1 class="title text-center">系统监控</h1>
			<c:forEach items="${fn:split(baseConfig.clusters,',')}" var="item" varStatus="status">
		    	<div class="row">
		        	<div class="col-lg-12 col-md-12">
		            	<div class="widget">
		                	<div class="widget-header">
		                    	<div class="title">
									内存使用情况（${item}）
		                    	</div>
								<span class="tools">
									<i class="fa fa-cogs"></i>
								</span>
							</div>
							<div class="widget-body">
							    <div id="realtimechart"></div>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
			<c:forEach items="${fn:split(baseConfig.clusters,',')}" var="item" varStatus="status">
				<div class="row">
					<div class="col-lg-12 col-md-12">
						<div class="widget">
							<div class="widget-header">
								<div class="title">
									SQL执行情况（${item}）
								</div>
								<span class="tools">
									<i class="fa fa-cogs"></i>
								</span>
							</div>
							<div class="widget-body">
								<iframe id="SQLMonitor87" src="http://${item}:8080/MeasureService/druid/sql.html" style="width: 100%;height:298px;margin:0px;padding:0px;" frameborder="0"></iframe>
							</div>
		               </div>
		           </div>
				</div>
			</c:forEach>
		</c:if>
		<div class="row" style="padding-top:20px;">
			<div class="col-lg-12 col-md-12">
				<footer id="footer">
					<div class="footer section">
						<div class="container">
							<h1 class="title text-center" id="contact">天利和远程集中计量系统</h1>
							<div class="space"></div>
							<div class="row text-left">
								<div class="col-sm-6">
									<div class="footer-content">
										<p class="large">天利和软件远程集中计量系统致力于优化计量流程，降本增效，请记录您的宝贵意见，让我们共同发展。</p>
										<ul class="list-icons">
											<li><i class="fa fa-map-marker pr-10"></i>135 ZhengTong Road, Hi-Tech Zone, Zibo, P.R.China 255086</li>
											<li><i class="fa fa-phone pr-10"></i> +86 0533-3595851</li>
											<li><i class="fa fa-fax pr-10"></i>+86 0533-3595860 </li>
											<li><i class="fa fa-envelope-o pr-10"></i>services@talent.com</li>
										</ul>
									</div>
								</div>
								<div class="col-sm-6">
									<div class="footer-content">
										<form role="form" id="footer-form">
											<div class="form-group has-feedback">
												<label class="sr-only" for="name2">Name</label>
												<input type="text" class="form-control" id="name2" placeholder="您的姓名" name="name2" required>
												<i class="fa fa-user form-control-feedback"></i>
											</div>
											<div class="form-group has-feedback">
												<label class="sr-only" for="email2">Email address</label>
												<input type="email" class="form-control" id="email2" placeholder="您的电子邮箱" name="email2" required>
												<i class="fa fa-envelope form-control-feedback"></i>
											</div>
											<div class="form-group has-feedback">
												<label class="sr-only" for="message2">Message</label>
												<textarea class="form-control" rows="8" id="message2" placeholder="请输入您的建议" name="message2" required></textarea>
												<i class="fa fa-pencil form-control-feedback"></i>
											</div>
											<input type="submit" value="发送" class="btn btn-default">
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</footer>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12 col-md-12">
				<div class="subfooter">
					<p class="text-center">Copyright © 2015 智能化远程集中计量管理系统v4.0 <a target="_blank" href="http://www.cn-talent.net" style="color:#000000;">山东天利和软件股份有限公司</a></p>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			var memoryPlot;
			var memoryTimeer;
			var weightTimeer;
			var updateInterval = 300;
			var memoryMointor = '${baseConfig.memoryMointor}';
			var weightMointor = '${baseConfig.weightMointor}';
			
			jQuery(document).ready(function($){
	      		var v = $(this).val();
	      		if (v && !isNaN(+v)) {
	      			updateInterval = +v;
	      			if (updateInterval < 1)
	      				updateInterval = 1;
	      			$(this).val("" + updateInterval);
	      		}
		    });
			
			<c:if test="${optrRole == '系统'}">
		      	var data = [], totalPoints = 500;
		      	function getMemoryData() {
		      		var res = [];
		      		if('启用' == memoryMointor){
		       		if (data.length = totalPoints){
		       			data = data.slice(1);
		       		}
		       		
		       		if(1 == (totalPoints - data.length)){
		       			$.ajax({
		   		            type: "post",
		   		            url: '<c:url value="/systemointor/memory.do"/>',
		   		            dataType: "json",
		   		            async:false,
		   		            success: function(jsondata){
		   		            	 if(jsondata.success == true){
		   		            		 data.push(jsondata.used);
		   		            	 }
		   		            },
		   		            error:function(){
		   		            	data.push(0);
		   		            }
		   	            });
		       			for (var i = 0; i < totalPoints; ++i){
		           			res.push([i, data[totalPoints - i]])
		           		}
		       		}else{
		       			for (var i = 0; i < totalPoints; ++i){
		       				data.push(0);
		           			res.push([i,0]);
		           		}
		       		}
		      		}
		      		return res;
		      	}
			
		      	var options = {
		     			series: {show:true,shadowSize: 1},
		     			lines: {show:true,fill: true, fillColor: { colors: [ { opacity: 1 }, { opacity: 0.1 } ] }},
		     			xaxis: {show:false},
		     			yaxis: {min:100, max: 1000},
		     			colors: ["#73B9FF"],
		     			grid:{
		     				tickColor: "#dddddd",
		     				borderWidth: 0 
		     			}
		     		};
		      	
		      	try
		      	{
		      		memoryPlot = $.plot($("#realtimechart"), [getMemoryData()], options);
		      	}
		      	catch (e) 
		      	{
		      		
		      	}
		      	
	     		function updateMemoryLine() {
	     			if('启用' == memoryMointor){
	     				memoryPlot.setData([ getMemoryData() ]);
	         			memoryPlot.draw();
	         			memoryTimeer = setTimeout(updateMemoryLine, updateInterval*1000);
	     			}           			
	     		}
	     		updateMemoryLine();
	     	</c:if>
     		<c:if test="${optrRole == '计量'}">
	     		//计量衡器监控定时器
	     		function updateWeightMointor(){
	     			if('启用' == weightMointor){
		     			$.ajax({
		   		            type: "post",
		   		            url: '<c:url value="/systemointor/weight.do"/>',
		   		            dataType: "json",
		   		            success: function(data){
		   		            	 if(data.success == true){
		   		            		 for(var i=0;i<data.rows.length;i++){
		   		            			 $('#weightcount' + data.rows[i].weightcode).html(data.rows[i].weightcount);
		   		            			 $('#timecount' + data.rows[i].weightcode).html(data.rows[i].timecount);
		   		            			 if(null == data.rows[i].errormsg){
		   		            				$('#weighter' + data.rows[i].weightcode).removeClass('yellow-bg').removeClass('red-bg').addClass('green-bg');
		   		            				$('#errormsg' + data.rows[i].weightcode).html('无异常');
		   		            			 }else{
		   		            			 	$('#weighter' + data.rows[i].weightcode).removeClass('green-bg').removeClass('red-bg').addClass('yellow-bg');
		   		            			 	$('#errormsg' + data.rows[i].weightcode).html('<marquee behavior="scroll" scrollamount="3">'+data.rows[i].errormsg+'</marquee>');
		   		            			 }
		   		            		 }
		   		            	 }
		   		            },
		   		            error:function(){
		   		            	
		   		            }
		   	            });
		     			weightTimeer = setTimeout(updateWeightMointor, updateInterval*1000);
	     			}
	     		}
	     		updateWeightMointor();
     		</c:if>
		</script>
	</body>
</html>