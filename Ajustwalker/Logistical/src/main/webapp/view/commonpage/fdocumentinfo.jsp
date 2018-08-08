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
<%
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		Date now = new Date();
		Calendar c = Calendar.getInstance();
		now = c.getTime();
		String begintime = dateFormat.format(now);//开始时间
		String endtime = dateFormat1.format(now);//结束时间
%>
<body class="container-fluid">
	<div class="row" style="padding-top: 5px">
		<div class="col-sm-12">
			<form id="queryform">
				<input type="text" id="saleitemid" name="saleitemid">
				<div class="row">
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">原始单据号</span> <input
								type="text" class="form-control" placeholder="原始单据号"
								id="matchid" name="matchid" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-9 btn-group-sm">
						<button id="query" type="button" class="btn btn-info" onclick="queryinfo()">
							<span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;查询
						</button>
					</div>
				</div>
			</form>
			<div class="row">
				<div class="col-sm-12">
					<table id="FdocumentinfoGrid" data-toggle="table"
						data-pagination="true" data-page-list="[10,40]"
						data-row-style="rowStyle" data-query-params="queryParams"
						data-page-size="10" data-click-to-select="true">
						<thead>
							<tr>
								<th data-field="itemid" data-formatter="stateFormatter"></th>
								<th data-field="matchid" data-halign="center"
									data-align="center" data-searchable="true" class="text-nowrap">单据号</th>
								<th data-field="saleitemid" data-halign="center"
									data-align="center" data-searchable="true" class="text-nowrap">单据行号</th>
								<th data-field="materialname" data-halign="center"
									data-searchable="true" class="text-nowrap">货名</th>
								<th data-field="materialcode" data-halign="center"
									data-align="center" data-searchable="true" data-visible="false"
									class="text-nowrap">编码</th>
								<th data-field="sourcename" data-halign="center"
									data-searchable="true" class="text-nowrap">供货</th>
								<th data-field="sourcecode" data-halign="center"
									data-align="center" data-searchable="true" data-visible="false"
									class="text-nowrap">供货编码</th>
								<th data-field="targetcode" data-halign="center"
									data-visible="false" data-searchable="true" class="text-nowrap">收货编码</th>
								<th data-field="targetname" data-halign="center"
									data-align="center" data-searchable="true" class="text-nowrap">收货</th>
								<th data-field="snumber" data-halign="center"
									data-align="center" data-searchable="true" class="text-nowrap">数量</th>
								<th data-field="measureunit" data-halign="center"
									data-align="center" data-searchable="true" class="text-nowrap">计量单位</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
			<div class="modal-footer  btn-group-sm">
				<button type="button" class="btn btn-success" onClick="yes()">
					<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;确定
				</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function queryParams(params) {
			return jQuery.extend({}, params, $('#queryform').serializeJson());
		}
		function queryinfo() {
			$('#FdocumentinfoGrid').bootstrapTable('refresh',{url : "<c:url value='/application/queryFdocumentBymatchid.do'/>"});
		}
		function stateFormatter(value, row, index) {
			return '<center><input id=\"'+index+'\" name="ids" type="checkbox" onclick="putsaleitemid(\''+ row.saleitemid + '\')"  ><center>';
		}
		function putsaleitemid(saleitemid) {
			var sid =$("#saleitemid").val();		
			if($('#'+index).is(':checked')){//点击选中						
				if (sid.length == 0) {
					$("#saleitemid").val(","+saleitemid+",");
				} else {
					$("#saleitemid").val(sid + "," + saleitemid);
				}	
			}else{//点击取消选中	
				var saleitemid = $("#saleitemid").val();
				 $("#saleitemid").val(sid.replace("," + saleitemid+"," , ","));
			     if(saleitemid.length==1 && saleitemid==','){
			    	 $("#saleitemid").val("");
			     }
			}
		}
		function yes() {
        	parent.takeBack($('#saleitemid').val());
        	parent.closeCheckCarno();
        }
	</script>
</body>
</t:html>