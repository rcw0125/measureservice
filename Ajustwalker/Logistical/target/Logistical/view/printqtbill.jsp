<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="t" uri="http://www.talent.com/jsp/anyonelse"%>
<t:html>
<head>
<title><t:resource key="system.modulename" /></title>
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
<style type="text/css">
input {
	font-size: 15px;
}
</style>
<script type="text/javascript" src="<c:url value='/plugins/jquery.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/bootstrap/js/bootstrap.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/plugins/datatable/bootstrap-table.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/plugins/datatable/bootstrap-table-zh-CN.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/plugins/jquery.print.js'/>"></script>
</head>
<body style="width: 100%;">
	<div class="no-print">
		<button type="button" class="btn btn-danger" onclick="printbill()" style="width: 100%; border-radius: 0px;">全部打印</button>
	</div>
	<div id="printtable" style="width: 100%; padding: 0px; margin: 0px;">
		<c:forEach items="${printheaddata}" var="head" varStatus="num1">
			<div style="page-break-after: always; adding: 0px; margin: 0px;">
				<table id="innerprinttable${num1.index}" style="width: 100%; padding: 0px; margin: 0px;" border="1">
					<tr>
						<td colspan="9" align="center"><input style="border-left-width: 0px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; margin-bottom: 0px;" value="${head.reserve2}" /></td>
					</tr>
					<tr>
						<td>单据号</td>
						<td>车牌号</td>
						<td>业务员</td>
						<td>供货</td>
						<td>收货</td>
						<td>部门</td>
						<td>制单人</td>
						<td>制单时间</td>
						<td>发生日期</td>
					</tr>
					<tr>
						<td>${head.matchid}</td>
						<td>${head.carno}</td>
						<td>${head.saleman}</td>
						<td>${head.sourcename}</td>
						<td>${head.targetname}</td>
						<td></td>
						<td>${head.creator}</td>
						<td>${head.reserve1}</td>
						<td>${head.inoutdate}</td>
					</tr>
					<tr>
						<td>审批级别：</td>
						<td colspan="2">一级</td>
						<td colspan="2">一级审批人：${head.approverfirst}</td>
						<td>审批时间：${head.firsttime}</td>
						<td colspan="2">二级审批人:${head.approversecond}</td>
						<td>审批时间：${head.secondtime}</td>
					</tr>
					<tr>
						<td colspan="9">备注：${head.memo}</td>
					</tr>

					<tr>
						<td colspan="3">物料名称</td>
						<td>数量</td>
						<td>单位</td>
						<td colspan="4">备注</td>
					</tr>
					<c:forEach items="${printbodydata}" var="body" varStatus="num2">
						<c:if test="${(head.carno == body.carno) && (head.matchid == body.matchid)}">
							<tr>
								<td colspan="3">${body.materialname}</td>
								<td>${body.snumber}</td>
								<td>${body.measureunit}</td>
								<td colspan="4">${body.usermemo}</td>
							</tr>
						</c:if>
					</c:forEach>

					<tr>
						<td colspan="9" style="text-align:center;">
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
				    deferred: $.Deferred(),
				    whencomplete:function(){
				    	parent.closePrintbillPanel();
				    }
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
				    deferred: $.Deferred(),
				    whencomplete:function(){
				    	parent.closePrintbillPanel();
				    }
				});   
			}
			</c:forEach>
		</script>
</body>
</t:html>