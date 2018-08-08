<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="t" uri="http://www.talent.com/jsp/anyonelse"%>
<t:html>
	<head>
	    <meta charset="utf-8">
		<title><t:resource key="system.modulename"/></title>
		<meta name="description" content="<t:resource key="system.description"/>">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="renderer" content="webkit">
		<meta http-equiv="Expires" content="0">
		<meta http-equiv="Pragma" content="no-cache">
		<meta http-equiv="Cache-control" content="no-cache">
		<meta http-equiv="Cache" content="no-cache">
		<link rel="shortcut icon" href="<c:url value='/images/Talent.ico'/>">
		<link href="<c:url value='/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet" media="screen">
		<link href="<c:url value='/fonts/font-awesome/css/font-awesome.min.css'/>" rel="stylesheet">
		<link href="<c:url value='/css/style.min.css'/>" rel="stylesheet">
	</head>
	<body class="gray-bg">
        <div class="middle-box text-center animated fadeInDown">
            <h1 style="font-size:200px;"><i class="fa fa-universal-access "></i></h1>
            <h2 style="font-size:70px;">401</h2>
            <h3 class="font-bold">没有授权</h3>
            <div class="error-desc">
               	 尚未授权，请联系管理员
                <br/>
				<a href="javascript:void(0)" onclick="returntoindex()" class="btn btn-primary m-t">重新登录</a>
            </div>
        </div>
        <script type="text/javascript">
        	function returntoindex(){
        		window.location.href='<c:url value="/"/>';
        	}
        </script>
    </body>
</t:html>
