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
			.detailtable{
				border-collapse:collapse; 
				border-spacing:0; 
				border:1px solid #000;
				font-size: 10px;
			}
			.detailtable td{
				border:1px solid #000;
				text-align:left;
			}
		</style>
	</head>
	<body style="width:100%;">
		<div class="no-print">
			<button type="button" class="btn btn-danger" onclick="printbill()" style="width:100%;border-radius:0px;">全部打印</button>
		</div>
		<div id="printtable">
			<c:forEach items="${printheaddata}" var="head" varStatus="num1">
				<div style="page-break-after:always;">
					<table id="innerprinttable${num1.index}" style="width:100%;">
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
							<td colspan="3">备　注：<textarea id="usermemo" style="border-left-width:0px;border-top-width:0px;border-right-width:0px;border-bottom-width:0px;margin-bottom:0px;width:100%;height:40px;">${head.usermemo}</textarea></td>
						</tr>
						<tr>
							<td colspan="3">
								<table class="detailtable" style="width:100%;" border="1">
								<THEAD style="display:table-header-group;font-weight:bold">
									<tr>
										<td style="width:90px;font-size:14px;">存货编码</td>
										<td style="width:90px;font-size:14px;">存货名称</td>
										<td style="width:170px;font-size:14px;">规格型号图号/产地</td>
										<td style="width:40px;font-size:14px;">单位</td>
										<td style="width:80px;font-size:14px;">应收</td>
										<td style="width:80px;font-size:14px;">应收辅</td>
										<td style="width:80px;font-size:14px;">实收</td>
										<td style="width:80px;font-size:14px;">实收辅</td>
										<td style="width:90px;font-size:14px;">使用单位</td>
										<td style="width:90px;font-size:14px;">项目</td>
										<td style="width:90px;font-size:14px;">到货单号</td>
										<td style="width:130px;font-size:14px;">客商</td>
										<td style="width:90px;font-size:14px;">收货人</td>
									</tr>
									</THEAD>
									<c:forEach items="${printbodydata}" var="body" varStatus="num2">
										<c:if test="${(head.carno == body.carno && (null != body.materialcode && fn:startsWith(body.materialcode,head.prematerialcode))) || (head.carno == body.carno && null == body.materialcode)}">
											<tr>
												<td valign="top">${body.materialcode}</td>
												<td valign="top">${body.materialname}</td>
												<td valign="top" style="font-size:11px;">${body.reserve10}</td>
												<td valign="top">${body.measureunit}</td>
												<td valign="top"><c:if test="${body.suttle == 0}">${body.counts}</c:if><c:if test="${body.suttle != 0}">${body.suttle}</c:if></td>
												<td valign="top"></td>
												<td valign="top"></td>
												<td valign="top"></td>
												<td valign="top">${body.middlemanname}</td>
												<td valign="top">${body.reserve8}</td>
												<td valign="top">${body.planid}</td>
												<td valign="top">${body.sourcename}</td>
												<td valign="top">${body.createoperaname}</td>
											</tr>
										</c:if>
									</c:forEach>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="3" style="text-align:center;">
								<div class="no-print">
									<button type="button" class="btn btn-success" onclick="printbill${num1.index}()" style="width:100%;border-radius:0px;">打印前面单据</button>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</c:forEach>
		</div>
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
			<c:forEach items="${printheaddata}" var="head" varStatus="num3">
				function printbill${num3.index}(){
					$("#innerprinttable${num3.index}").print({
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
			</c:forEach>
		</script>
	</body>
</t:html>