<%@ page contentType="text/html;charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="t" uri="http://www.talent.com/jsp/anyonelse"%>
<t:html>
	<head>
		<jsp:include page="common.jsp" flush="true"/>
	</head>
	<body class="gray-bg">
	    <div class="wrapper wrapper-content animated fadeInRight">
	        <div class="row">
	            <div class="col-sm-12">
	                <div class="form-group"> 
					    <label for="name">发送系统消息给所有用户${_csrf.token}</label> 
					    <textarea id="msgcontent" class="form-control" rows="5"></textarea> 
					</div>
	            </div>
	        </div>
	        <div class="row">
	            <div class="col-sm-12">
	                <button type="button" class="btn btn-danger" onclick="sendallmessage()" style="width:100%">发送</button>
	            </div>
	        </div>
	    </div>
	    <script type="text/javascript">
	    	$(document).ready(function(){
	    		$('#msgcontent').val('进出厂物流系统将在5分钟后进行更新维护，届时将停止系统的一切功能，请保存现有的工作并退出系统，试运行期间对您的工作造成的不便，敬请谅解。');
	    	});
	    	
	    	function sendallmessage(){
	    		$.ajax({
	                type:"post",
	                url:'<c:url value='/sendmsgtoall'/>',
	                dataType: "json",
	                data:{content:$('#msgcontent').val()},
	                success: function(data){
	                	successtoast(data.msg);
	                }
	            });
	    	}
	    </script>
	</body>
</t:html>