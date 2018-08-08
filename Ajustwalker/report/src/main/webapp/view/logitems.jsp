<%@ page contentType="text/html;charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html>
<html lang="en">
<head>
		<jsp:include page="common.jsp" flush="true"/>
		<link href="<c:url value='/css/docs.min.css'/>" rel="stylesheet">
		<link href="<c:url value='/css/jsonFormatter.css'/>" rel="stylesheet">
		
		<script type="text/javascript">
			window.ImgCollapsed = "<c:url value='/images/JsonCollapsed.gif'/>";
			window.ImgExpanded = "<c:url value='/images/JsonExpanded.gif'/>";
		</script>
		
		<style type="text/css">
			p{word-wrap:break-word;word-break:normal;}
			p{word-break:break-all; }
		</style>
	</head>
	<body class="container-fluid">
		<div class="row">
			<div class="col-sm-12" style="padding-left:0px;margin-left:0px;padding-right:0px;margin-right:0px;">
				<c:forEach items="${searchResults}" var="item" varStatus="status">
					<script type="text/javascript">
						var content${status.index} = '${item.content}';
					</script>
					<c:if test="${status.index%3 == 0}">
						<div class="bs-callout bs-callout-danger" id="callout-tooltip-multiline">
							<h4>${item.filename}&nbsp;&nbsp;<button type="button" class="btn btn-success" onclick="openFormatter(content${status.index})">格式化</button></h4>
							<p>${item.content}</p>
						</div>
					</c:if>
					<c:if test="${status.index%3 == 1}">
						<div class="bs-callout bs-callout-warning" id="callout-tooltip-multiline">
							<h4>${item.filename}</h4>
							<p>${item.content}</p>
						</div>
					</c:if>
					<c:if test="${status.index%3 == 2}">
						<div class="bs-callout bs-callout-info" id="callout-tooltip-multiline">
							<h4>${item.filename}</h4>
							<p>${item.content}</p>
						</div>
					</c:if>
				</c:forEach>
			</div>
		</div>
		<div class="modal fade" id="LogItemWindow" tabindex="-1" role="dialog" aria-labelledby="LogItemWindowLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">日志格式化结果</h4>
					</div>
					<div class="modal-body">
						<div id="Canvas" class="Canvas"></div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript" src="<c:url value='/plugins/jsonFormatterConvert.js'/>"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				try
				{
					window.parent.iFrameHeight('${reporttype}');
				}
				catch(e)
				{
					
				}				
			});
			function openFormatter(json){
				var r = Process(json);
				if(r){
					$('#LogItemWindow').modal('show');
				}
			}
		</script>
	</body>
</html>