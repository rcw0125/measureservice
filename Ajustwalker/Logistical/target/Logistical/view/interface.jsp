<%@ page contentType="text/html;charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="t" uri="http://www.talent.com/jsp/anyonelse"%>
<t:html>
	<head>
		<jsp:include page="common.jsp" flush="true"/>
		<link href="<c:url value='/css/fileinput.min.css'/>" rel="stylesheet">
		<script type="text/javascript" src="<c:url value='/plugins/fileinput.min.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/plugins/fileinput_locale_zh.js'/>"></script>
	</head>
	<body>
		<div class="container">
			<div class="row">
	            <form enctype="multipart/form-data">
	            	<input type="hidden" id="id" name="id" value="0"/>
	            	<input type="hidden" id="fid" name="fid" value="0"/>
	                <div class="form-group">
	                    <input id="file-5" class="file" type="file" data-language="zh" multiple data-preview-file-type="any" data-upload-url="<c:url value='/unauth/interface/byfile'/>" data-preview-file-icon="">
	                </div>
	            </form>
			</div>
        </div>
	</body>
</t:html>