<%@ page contentType="text/html;charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="t" uri="http://www.talent.com/jsp/anyonelse"%>
<t:html>
	<head>
		<jsp:include page="common.jsp" flush="true"/>
	</head>
	<body class="gray-bg">
	    <div class="wrapper wrapper-content animated fadeInRight">
	        <div class="row">
	            <div class="col-sm-3">
	                <div class="widget style1 red-bg">
	                    <div class="row">
	                        <div class="col-xs-4">
	                            <i class="fa fa-exclamation-triangle fa-5x"></i>
	                        </div>
	                        <div class="col-xs-8 text-right">
	                            <span> 报警管理</span>
	                            <h2 class="font-bold">0</h2>
	                        </div>
	                    </div>
	                </div>
	            </div>
	            <div class="col-sm-3">
	                <div class="widget style1 navy-bg">
	                    <div class="row">
	                        <div class="col-xs-4">
	                            <i class="fa fa-flag fa-5x"></i>
	                        </div>
	                        <div class="col-xs-8 text-right">
	                            <span> 系统消息 </span>
	                            <h2 class="font-bold">26</h2>
	                        </div>
	                    </div>
	                </div>
	            </div>
	            <div class="col-sm-3">
	                <div class="widget style1 blue-bg">
	                    <div class="row">
	                        <div class="col-xs-4">
	                            <i class="fa fa-envelope-o fa-5x"></i>
	                        </div>
	                        <div class="col-xs-8 text-right">
	                            <span> 通知消息 </span>
	                            <h2 class="font-bold">0</h2>
	                        </div>
	                    </div>
	                </div>
	            </div>
	            <div class="col-sm-3">
	                <div class="widget style1 yellow-bg">
	                    <div class="row">
	                        <div class="col-xs-4">
	                            <i class="fa fa-align-justify fa-5x"></i>
	                        </div>
	                        <div class="col-xs-8 text-right">
	                            <span> 待办事项 </span>
	                            <h2 class="font-bold">0</h2>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	        <div class="row">
	            <div class="col-sm-3">
	                <div class="widget navy-bg no-padding">
	                    <div class="p-m">
	                        <h1 class="m-xs"><i class="fa fa-balance-scale"></i>&nbsp;&nbsp;34,670</h1>
	                        <h3 class="font-bold no-margins">
								进厂量
	                        </h3>
	                    </div>
	                    <div class="flot-chart">
	                        <div class="flot-chart-content" id="flot-chart1"></div>
	                    </div>
	                </div>
	            </div>
	            <div class="col-sm-3">
	                <div class="widget navy-bg no-padding">
	                    <div class="p-m">
	                        <h1 class="m-xs"><i class="fa fa-truck"></i>&nbsp;&nbsp;1,540</h1>
	                        <h3 class="font-bold no-margins">
								进厂车数
	                        </h3>
	                    </div>
	                    <div class="flot-chart">
	                        <div class="flot-chart-content" id="flot-chart1"></div>
	                    </div>
	                </div>
	            </div>
	            <div class="col-sm-3">
	                <div class="widget yellow-bg no-padding">
	                    <div class="p-m">
	                        <h1 class="m-xs"><i class="fa fa-balance-scale" aria-hidden="true"></i>&nbsp;&nbsp;210,660</h1>
	                        <h3 class="font-bold no-margins">
								销售量
	                        </h3>
	                    </div>
	                    <div class="flot-chart">
	                        <div class="flot-chart-content" id="flot-chart2"></div>
	                    </div>
	                </div>
	            </div>
	            <div class="col-sm-3">
	                <div class="widget yellow-bg no-padding">
	                    <div class="p-m">
	                        <h1 class="m-xs"><i class="fa fa-truck"></i>&nbsp;&nbsp;1,992</h1>
	                        <h3 class="font-bold no-margins">
								销售车数
	                        </h3>
	                    </div>
	                    <div class="flot-chart">
	                        <div class="flot-chart-content" id="flot-chart3"></div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	</body>
</t:html>