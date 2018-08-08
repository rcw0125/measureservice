<%@ page contentType="text/html;charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="t" uri="http://www.talent.com/jsp/anyonelse"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<t:html>
	<head>
		<jsp:include page="../common.jsp" flush="true"/>
	</head>
	<body>
		<div id="querytoolbar">
			<div class="form-inline" role="form">
				<form id="queryform">
					<t:datebox id="begintime" format="YYYY-MM-DD HH:mm:ss" label="开始时间" require="false"/>
					<t:datebox id="endtime" format="YYYY-MM-DD HH:mm:ss" label="结束时间" require="false"/>
					<t:textbox id="exportman" label="操作人" require="false"/>
					<t:textbox id="logfilename" label="文件名" require="false"/>
					<div class="form-group btn-group-sm">
						<button id="query" type="button" class="btn btn-info enterkeyaction" onclick="loadPrintlogGridData()">
							<span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;查询
						</button>
					</div>
				</form>
			</div>
		</div>
		<div class="row-fluid">
			<div class="col-sm-12">
				<t:datatable showselectbox="false" hiddencolumns="validflag,creator,cdate,updater,udate,canceler,canceldate,classindex,classtype,usermemo,sysmemo,remark,lastLoginTime" pagination="false" toolbar="querytoolbar" queryformid="queryform" url="/printlog/list" entities="com.talent.report.model.Printlog" id="PrintlogGrid" showcrud="false"/>
			</div>
		</div>
	</body>
	<script type="text/javascript">		
		function operateFormatter(value, row, index) {
            /* return [
                '<div class="pull-right">',
               	'<t:ibutton text="预览" modulecode="printlog" id="printlogpreview" btnclass="review" iconclass="glyphicon glyphicon-eye-open"/>',
               	'</div>'
           	].join(''); */
			return [
               	'<a class="review" href="javascript:void(0)" title="预览">',
               	'<i class="glyphicon glyphicon-eye-open"></i>',
               	'</a>',
           	].join('');
        }
		
        window.operateEvents = {
            'click .review': function (e, value, row) {
            	layer.open({
					type:2,
					title:'${titlename}打印',
					maxmin:true, //开启最大化最小化按钮
					area:['100%', '100%'],
					content:"http://192.168.2.42:5080/pdfdownload/" + row.logfilename
				});
            }
        };
	</script>
</t:html>