<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>铁水罐定位跟踪</title>
		<script type="text/javascript" src="/Logistical/dwr/engine.js"></script>
		<script type="text/javascript" src="/Logistical/dwr/util.js"></script>
		<script type="text/javascript" src="/Logistical/dwr/interface/pushMessageCompont.js"></script>
	</head>
	<body onload="onPageLoad();dwr.engine.setActiveReverseAjax(true);dwr.engine.setNotifyServerOnPageUnload(true);">
	</body>
	<script type="text/javascript">
		function onPageLoad() {
			pushMessageCompont.onPageLoad("AAAAA");
		}
		function clickEvent() {
			alert("success!!!");
		}
		function showMessage(sendMessages, clickEvent) {
			alert(33333);
		}
	</script>
</html>