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
	<script type="text/javascript" src="<c:url value='/plugins/bootstrap-typeahead.js'/>"></script>
</head>
<body class="container-fluid">
	<div class="row" style="padding-top: 5px">
		<div class="col-sm-12">
			<form id="queryform">				
				<div class="row">
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">业务号</span> <input
								name="taskcode" type="text" class="form-control" value="">
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">供　货</span> <input
								name="sourcename" type="text" class="form-control" value="">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">货　名</span> <input
								name="materialname" type="text" class="form-control" value="">
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">收　货</span> <input
								name="targetname" type="text" class="form-control" value="">
						</div>
					</div>
					<div class="col-sm-3">
					    <div class="form-group input-group  btn-group-sm">
							<button id="query" type="button" class="btn btn-info"
								onclick="queryinfo()">
								<span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;查询
							</button>
						</div>
					</div>
				</div>
			</form>
			<div class="row">
				<div class="col-sm-12">
					<table id="TaskcodeGrid" data-toggle="table"
						data-row-style="rowStyle" data-query-params="queryParams"
						data-pagination="true" 
						data-page-size="10"
						data-page-list="[10,40,ALL]" 
						data-mobile-responsive="true" data-click-to-select="true">
						<thead>
							<tr>
								<th data-field="state" data-radio="true"></th>
								<th data-field="operatype" data-halign="center"
									data-align="center" data-searchable="true" data-visible="false"></th>
								<th data-field="taskcode" data-halign="center"
									data-align="center" data-searchable="true" class="text-nowrap">业务号</th>
								<th data-field="materialcode" data-halign="center"
									data-align="center" data-searchable="true" data-visible="false"></th>
								<th data-field="materialname" data-halign="center"
									data-searchable="true" class="text-nowrap"　>货名</th>
								<th data-field="sourcecode" data-halign="center"　
									data-visible="false">供货</th>
								<th data-field="sourcename" data-halign="center"
									data-searchable="true" class="text-nowrap">供货</th>
								<th data-field="targetcode" data-halign="center"　
									data-align="center" data-visible="false">收货</th>
								<th data-field="targetname" data-halign="center"
									data-align="center" data-searchable="true" class="text-nowrap">收货</th>
								<th data-field="mflag" data-visible="false">计量顺序</th>
								<th data-field="measurement" data-halign="center"
							        data-searchable="true" class="text-nowrap text-center">计量顺序</th>	
							</tr>
						</thead>
					</table>
				</div>
			</div>
			<div class="modal-footer  btn-group-sm">
				<button type="button" class="btn btn-success" onClick="yes()">确定</button>
				<button type="button" class="btn btn-default" data-dismiss="modal"
					onclick="exit()">关闭</button>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		jQuery(document).ready(function($) {
			queryinfo();
		});
		function queryParams(params) {
			return jQuery.extend({}, params, $('#queryform').serializeJson());
		}

		function queryinfo() {
			$('#TaskcodeGrid').bootstrapTable('refresh', {
				url : "<c:url value='/bcommon/queryTaskcode.do'/>"
			});
		}

		function yes() {
			var rows = $('#TaskcodeGrid').bootstrapTable('getSelections');
			if (rows.length == 0) {
				errorbox("请选择信息");
			} else {
				parent.takeBackTaskcode(rows[0].taskcode, rows[0].operatype,
						rows[0].materialcode, rows[0].materialname,
						rows[0].sourcecode, rows[0].sourcename,
						rows[0].targetcode, rows[0].targetname,rows[0].mflag,rows[0].tarehour);
				parent.closeTakecode();
			}
		}
		function exit() {
			parent.closeTakecode();
		}
		function mflagFormatter(value, row, index) {
			var msg;
			if(row.mflag==1){
				msg="先毛后皮";
			}else if(row.mflag==2){
				msg="先皮后毛";
			}
			
			return msg;
		}
	</script>
</body>
</t:html>