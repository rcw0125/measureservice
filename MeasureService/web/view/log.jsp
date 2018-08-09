<%@ page contentType="text/html;charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="common.jsp" flush="true"/>
</head>
<body class="section container-fluid" style="padding-top:15px;">
	<div class="row-fluid">
		<div class="col-sm-12">
			<div class="input-group">
				<span class="input-group-btn">
					<button class="btn btn-default" type="button" onclick="reindex()">重建索引</button>
				</span>
				<input id="keywordtext" type="text" class="form-control" placeholder="请输入关键字，多个以空格分隔">
				<span class="input-group-btn">
					<button class="btn btn-info" type="button" onclick="search()">&nbsp;查询</button>
				</span>
		    </div>
		</div>
	</div>
	<div class="row-fluid">
		<div class="col-sm-12" style="padding-top:20px;">
			<ul class="nav nav-tabs" role="tablist">
				<li role="presentation" class="active"><a href="#servicelog" aria-controls="servicelog" role="tab" data-toggle="tab">服务日志</a></li>
				<li role="presentation"><a href="#agentlog" aria-controls="agentlog" role="tab" data-toggle="tab">坐席日志</a></li>
				<li role="presentation"><a href="#clientlog" aria-controls="clientlog" role="tab" data-toggle="tab">终端日志</a></li>
			</ul>
			<div class="tab-content">
				<div role="tabpanel" class="tab-pane active" id="servicelog">
					<div class="table-responsive">
						<iframe id="servicelogFrame" src="<c:url value='/log/query.do?type=servicelog'/>" style="width:100%;" frameborder="0" scrolling="no" marginheight="0" marginwidth="0"></iframe>
					</div>
				</div>
				<div role="tabpanel" class="tab-pane" id="agentlog">
					<div class="table-responsive">
						<iframe id="agentlogFrame" src="<c:url value='/log/query.do?type=agentlog'/>" style="width:100%;" frameborder="0" scrolling="no" marginheight="0" marginwidth="0"></iframe>
					</div>
				</div>
				<div role="tabpanel" class="tab-pane" id="clientlog">
					<div class="table-responsive">
						<iframe id="clientlogFrame" src="<c:url value='/log/query.do?type=clientlog'/>" style="width:100%;" frameborder="0" scrolling="no" marginheight="0" marginwidth="0"></iframe>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function iFrameHeight(iframeid) {
	    	var ifm= document.getElementById(iframeid + 'Frame');
	    	var viewportHeight = document.documentElement.clientHeight;
	    	var subWeb = document.frames ? document.frames[iframeid + 'Frame'].document : ifm.contentDocument;
	    	if(ifm != null && subWeb != null) {
	    		try
	    		{
	    			ifm.height = (subWeb.body.scrollHeight < viewportHeight) ? viewportHeight : subWeb.body.scrollHeight;
	    		}
	    		catch(e)
	    		{
					ifm.height = viewportHeight;
	    		}
	    	}
		}
		
		function search(){
			var keyw = $('#keywordtext').val();
			if('' == keyw){
				toastMessage("error","错误",'请输入关键字！');
			}else{
				document.getElementById("servicelogFrame").src="<c:url value='/log/query.do'/>?type=servicelog&keywords=" + encodeURI(keyw);
				document.getElementById("agentlogFrame").src="<c:url value='/log/query.do'/>?type=agentlog&keywords=" + encodeURI(keyw);
				document.getElementById("clientlogFrame").src="<c:url value='/log/query.do'/>?type=clientlog&keywords=" + encodeURI(keyw);
			}
		}
		
		function reindex(){
			$.ajax({
	            type: "post",
	            url: '<c:url value="/log/index.do"/>',
	            dataType: "json",
	            success:function(data){
	            	 if(data.success == true){
	            		 toastMessage("success","成功",'重建索引成功');
	            	 }
	            }
            });
		}
	</script>
</body>
</html>