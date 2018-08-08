<%@ page contentType="text/html;charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="t" uri="http://www.talent.com/jsp/anyonelse"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
		<style type="text/css">
			input{
				font-size:15px;
			}
		</style>
		<script type="text/javascript" src="<c:url value='/plugins/jquery.min.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/bootstrap/js/bootstrap.min.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/plugins/datatable/bootstrap-table.min.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/plugins/datatable/bootstrap-table-zh-CN.min.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/plugins/jquery.print.js'/>"></script>
	</head>
	<body style="width:100%;">
		<div class="no-print">
			<button type="button" class="btn btn-danger" onclick="printbill()" style="width:100%">打印</button>
		</div>
		<table id="printtable" style="width:100%;" border="0">
			<c:if test="${empty measurelist}">
				<tr><td colspan="9">无法获取打印信息！</td></tr>
			</c:if>
			<c:if test="${!empty measurelist}">
				<tr>
					<td colspan="9"><input id="targetname" style="width:100%;border-left-width:0px;border-top-width:0px;border-right-width:0px;border-bottom-width:0px;margin-bottom:60px;margin-left:30px;" value="<c:if test="${fn:startsWith(measurelist[0].operatype,'8')}">${measurelist[0].targetname}</c:if><c:if test="${fn:startsWith(measurelist[0].operatype,'9')}">${measurelist[0].sourcename}</c:if>"/></td>
				</tr>
				<tr>
					<td width="60"><input style="border-left-width:0px;border-top-width:0px;border-right-width:0px;border-bottom-width:0px;margin-bottom:30px;" value="${measurelist[0].materialname}"/></td>
					<td><input style="width:40px;border-left-width:0px;border-top-width:0px;border-right-width:0px;border-bottom-width:0px;margin-bottom:30px;" value="吨"/></td>
					<td width="0"></td>
					<td><input style="width:80px;border-left-width:0px;border-top-width:0px;border-right-width:0px;border-bottom-width:0px;margin-bottom:30px;" value="${measurelist[0].carno}"/></td>
					<td></td>
					<td><input style="width:60px;border-left-width:0px;border-top-width:0px;border-right-width:0px;border-bottom-width:0px;margin-bottom:30px;" value="${measurelist[0].gross}"/></td>
					<td><input style="width:60px;border-left-width:0px;border-top-width:0px;border-right-width:0px;border-bottom-width:0px;margin-bottom:30px;" value="${measurelist[0].tare}"/></td>
					<td><input style="border-left-width:0px;border-top-width:0px;border-right-width:0px;border-bottom-width:0px;margin-bottom:30px;" value="${measurelist[0].suttle}"/></td>
					<td></td>
				</tr>
				<tr>
					<td colspan="9"><input style="width:100%;border-left-width:0px;border-top-width:0px;border-right-width:0px;border-bottom-width:0px;margin-bottom:20px;" value="${measurelist[0].grossweigh}　${measurelist[0].gross}　${measurelist[0].grossoperaname}　${measurelist[0].grosstime}"/></td>
				</tr>
				<tr>
					<td colspan="9"><input style="width:100%;border-left-width:0px;border-top-width:0px;border-right-width:0px;border-bottom-width:0px;" value="${measurelist[0].tareweigh}　${measurelist[0].tare}　${measurelist[0].tareoperaname}　${measurelist[0].taretime}"/></td>
				</tr>
			</c:if>
		</table>
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