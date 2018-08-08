<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="t" uri="http://www.talent.com/jsp/anyonelse"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.Date"%>
<t:html>
<head>
<jsp:include page="/view/common.jsp" flush="true" />
<style type="text/css">
.select2-container--bootstrap .select2-selection {
	border-radius: 0px 4px 4px 0px;
}

.select2-container--bootstrap.input-sm .select2-selection--single,
	.input-group-sm .select2-container--bootstrap .select2-selection--single,
	.form-group-sm .select2-container--bootstrap .select2-selection--single
	{
	border-radius: 0px 4px 4px 0px;
}
</style>
</head>

<body class="section container-fluid">
<div class="row-fluid">
		<div class="col-sm-12">	
			<table id="TargetinfoGrid" data-toggle="table"
				data-pagination="true" 
				data-page-list="[10,40]"
				data-row-style="rowStyle" 
				data-search="true"
				data-show-refresh="true"
				data-page-size="10"
				data-url="<c:url value='/bcommon/queryTargetinfo.do'/>">
				<thead>
					<tr>
						<th data-field="targetcode" data-halign="center" data-align="center"
							data-searchable="true">收货编码</th>
						<th data-field="targetname" data-halign="center"
							data-searchable="true">收货名称</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</body>
</t:html>