<%@ page contentType="text/html;charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="t" uri="http://www.talent.com/jsp/anyonelse"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<t:html>
	<head>
		<title><t:resource key="system.modulename"/></title>
		<meta name="description" content="<t:resource key="system.modulename"/>">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
		<meta name="renderer" content="webkit">
		<meta http-equiv="Expires" content="0">
		<meta http-equiv="Pragma" content="no-cache">
		<meta http-equiv="Cache-control" content="no-cache">
		<meta http-equiv="Cache" content="no-cache">
		<link rel="shortcut icon" href="<c:url value='/images/Talent.ico'/>">
		<link href="<c:url value='/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet" media="screen">
		<link href="<c:url value='/fonts/font-awesome/css/font-awesome.min.css'/>" rel="stylesheet">
		<link href="<c:url value='/plugins/datatable/bootstrap-table.min.css'/>" rel="stylesheet">
		<!--[if lte IE 6]>
			<link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap-ie6.css'/>">
		<![endif]-->
		
		<!--[if lte IE 7]>
			<link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/ie.css'/>">
		<![endif]-->
		
		<script type="text/javascript" src="<c:url value='/plugins/jquery.min.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/bootstrap/js/bootstrap.min.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/plugins/datatable/bootstrap-table.min.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/plugins/datatable/bootstrap-table-zh-CN.min.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/plugins/jquery.print.js'/>"></script>
		<style type="text/css">
			.text-nowrap{
				text-align: center;
    			line-height: 3;
			}
			.detailtable{
				border-collapse:collapse; 
				border-spacing:0; 
				border:1px solid #aaa;
			}
			.detailtable td{
				border:1px solid #aaa;
				text-align:center; 
				color:#3C3C3C;
			}
		</style>
	</head>
	<body style="width:100%;">
		<div class="no-print">
			<button type="button" class="btn btn-danger" onclick="printbill()" style="width:100%">打印</button>
		</div>
		<c:forEach items="${printheaddata}" var="head" varStatus="num1">
			<table id="printtable" style="width:100%;">
				<tr>
					<td colspan="3" style="text-align:center;">
						<h1>作业单</h1>
					</td>
				</tr>
				<tr>
					<td>单据号：${head.matchid}</td>
					<td>业务类型：${head.operaname}</td>
					<td>是否退货：${head.recetype}</td>
				</tr>
				<tr>
					<td>车牌号：${head.carno}</td>
					<td>进厂时间：${head.entrytime}</td>
					<td>车　　型：</td>
				</tr>
				<tr>
					<td colspan="3">备　注：<input id="usermemo" style="width:100%;" value="${head.usermemo}"/></td>
				</tr>
				<tr>
					<td colspan="3">
						<table id="detailtable" class="detailtable" style="width:100%;" border="1">
							<tr>
								<td class="text-nowrap" nowrap="nowrap">存货编码</td>
								<td class="text-nowrap" nowrap="nowrap">存货名称</td>
								<td class="text-nowrap">规格型号图号/产地</td>
								<td class="text-nowrap" nowrap="nowrap">单位</td>
								<td class="text-nowrap" nowrap="nowrap">应收</td>
								<td class="text-nowrap" nowrap="nowrap">应收辅</td>
								<td class="text-nowrap" nowrap="nowrap">实收</td>
								<td class="text-nowrap" nowrap="nowrap">实收辅</td>
								<td class="text-nowrap" nowrap="nowrap">使用单位</td>
								<td class="text-nowrap">项目</td>
								<td class="text-center" nowrap="nowrap">到货单号</td>
								<td class="text-center" nowrap="nowrap">客商</td>
								<td class="text-center" nowrap="nowrap">收货人</td>
							</tr>
							<c:forEach items="${printbodydata}" var="body" varStatus="num2">
								<c:if test="${head.carno == body.carno && fn:startsWith(body.materialcode,head.prematerialcode)}">
									<tr>
										<td class="text-nowrap" nowrap="nowrap">${body.materialcode}</td>
										<td class="text-nowrap" nowrap="nowrap">${body.materialname}</td>
										<td class="text-nowrap">${body.reserve10}</td>
										<td class="text-nowrap" nowrap="nowrap">${body.measureunit}</td>
										<td class="text-nowrap" nowrap="nowrap"><c:if test="${body.suttle == 0}">${body.counts}</c:if><c:if test="${body.suttle != 0}">${body.suttle}</c:if></td>
										<td class="text-nowrap" nowrap="nowrap"></td>
										<td class="text-nowrap" nowrap="nowrap"></td>
										<td class="text-nowrap" nowrap="nowrap"></td>
										<td class="text-nowrap" nowrap="nowrap">${body.middlemanname}</td>
										<td class="text-nowrap"></td>
										<td class="text-center" nowrap="nowrap">${body.planid}</td>
										<td class="text-center" nowrap="nowrap">${body.sourcename}</td>
										<td class="text-center" nowrap="nowrap">${body.createoperaname}</td>
									</tr>
								</c:if>
							</c:forEach>
						</table>
					</td>
				</tr>
			</table>
		</c:forEach>
		
		<script type="text/javascript">
			function printbill(){
				$("#printtable").print({
				    globalStyles:true,
				    mediaPrint: false,
				    stylesheet: null,
				    noPrintSelector: ".no-print",
				    iframe: true,
				    append: null,
				    prepend: null,
				    manuallyCopyFormValues: true,
				    deferred: $.Deferred()
				});   
			}
		</script>
	</body>
</t:html>