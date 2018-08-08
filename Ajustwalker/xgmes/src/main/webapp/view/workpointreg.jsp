<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="t" uri="http://www.talent.com/jsp/anyonelse"%>
<t:html>
	<head>
		<jsp:include page="common.jsp" flush="true" />
	</head>
	<body class="gray-bg">
		<div class="middle-box text-center loginscreen   animated fadeInDown">
	        <div>
	            <div>
	                <h1 class="logo-name"><i class="fa fa-key fa-1x"></i>&nbsp;&nbsp;作业点注册</h1>
	            </div>
	            <form id="WorkpointForm" class="m-t" role="form">
	                <input id="workpointname" name="workpointname" type="hidden" aria-describedby="basic-addon1">
	                <t:textbox id="creator" name="creator" require="true" label="注　册　人"/>
	                <t:combobox id="workpointcode" name="workpointcode"  url="/unauth/workpoint/select2.do" label="作业点名称" require="true"/>
	                <t:textbox id="workpointip" name="workpointip" readonly="true" require="true" label="IP　地　址"/>
	                <t:textbox id="workpointmac" name="workpointmac" readonly="true" require="true" label=" M A C 地址"/>
	                <button type="button" onclick="insert()" class="btn btn-primary block full-width m-b">注 册</button>
	            </form>
	        </div>
	    </div>
	    <script type="text/javascript">
	    	$(document).ready(function(){
	    		var mediawaiting = layer.msg('获取本机信息，请稍候...',{time:0},function(){
	    			mediawaiting = 0;
	    		});
	    		
	    		$.ajax({
	    	        url:'http://127.0.0.1:9001/talentlt/GetMachineInfo',
	    	        dataType: "jsonp",
	    	        crossDomain: true,
	    	        timeout:10000,
	    	        jsonp: "callback",
	    	        success: function (data) {
	    	        	$('#workpointip').val(data.ipAddress);
	    	        	$('#workpointmac').val(data.macAddress);
	    	        	validForm('WorkpointForm');
	    	        },
	    	        complete:function(xhr,ts){
	    	        	layer.close(mediawaiting);
	    	        },
	    	        jsonpCallback: "jsoncallback"
	    	    });
	    	});
	    	
	    	function insert() {			
			    $("#workpointname").val($('#workpointcode option:selected').text())
				saveFormData('WorkpointForm','<c:url value="/unauth/workpoint/registeWorkpoint.do"/>',function(data){
					 if (data.success) {
						successtoast(data.msg);
					} else {
						errorbox(data.msg);
					} 
				});
			}
	    </script>
	</body>
</t:html>






