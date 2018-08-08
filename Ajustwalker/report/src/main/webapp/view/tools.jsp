<%@ page contentType="text/html;charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>工具</title>
	<link rel="shortcut icon" href="<c:url value='/images/Talent.ico'/>">
	<link href="<c:url value='/css/custom.css'/>" rel="stylesheet">
	<link href="<c:url value='/bootstrap/css/bootstrap.css'/>" rel="stylesheet">
	<script type="text/javascript" src="<c:url value='/plugins/jquery.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/bootstrap/js/bootstrap.min.js'/>"></script>
</head>
<body class="page-header-fixed page-quick-sidebar-over-content no-trans">
	<div class="container-fluid">
		<h1 id="clients" class="title text-center">测试页面1</h1>
		<div class="row">
			<div class="col-lg-11">
				<div class="input-group">
					<input type="text" class="form-control" id="restURL" name="restURL" aria-describedby="basic-addon1" value="http://localhost:8080/MeasureService/tools/measurerule.do?matchid=00015121400002&optr=汽运直供采购计皮&caller=1">
					<span class="input-group-addon" id="paramname">测试地址1</span>
				</div>
			</div>
			<div class="col-lg-1" style="margin-top:5px;">
				<button type="button" class="btn btn-success" data-toggle="modal" onclick="javascript:RestDataBaseRequest();">
					<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;执行
				</button>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-11">
				<div class="input-group">
					<textarea class="form-control" placeholder="选填" rows="3" id="restResult" name="restResult"></textarea>
					<span class="input-group-addon" id="paramemo">备注详情&nbsp;&nbsp;</span>
				</div>
			</div>
		</div>
		
		<h1 id="clients" class="title text-center">测试地址2</h1>
		<div class="row">
			<div class="col-lg-11">
				<div class="input-group">
					<input type="text" class="form-control" id="redisDelURL" name="redisDelURL" aria-describedby="basic-addon1" value="http://localhost:8080/MeasureService/tools/hardwarectrl.do">
					<span class="input-group-addon" id="paramname">测试地址2</span>
				</div>
			</div>
			<div class="col-lg-1" style="margin-top:5px;">
				<button type="button" class="btn btn-success" data-toggle="modal" onclick="javascript:RedisDelRequest();">
					<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;执行
				</button>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-11">
				<div class="input-group">
					<textarea class="form-control" placeholder="选填" rows="3" id="redisDelResult" name="redisDelResult"></textarea>
					<span class="input-group-addon" id="paramemo">返回结果&nbsp;&nbsp;</span>
				</div>
			</div>
		</div>
		
		<h1 id="clients" class="title text-center">测试地址3</h1>
		<div class="row">
			<div class="col-lg-11">
				<div class="input-group">
					<input type="text" class="form-control" id="redisReadURL" name="redisReadURL" aria-describedby="basic-addon1" value="http://localhost:8080/MeasureService/tools/jsonconvert.do?jsonParams={%22validflag222%22:1111}">
					<span class="input-group-addon" id="paramname">测试地址3</span>
				</div>
			</div>
			<div class="col-lg-1" style="margin-top:5px;">
				<button type="button" class="btn btn-success" data-toggle="modal" onclick="javascript:RedisReadRequest();">
					<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;执行
				</button>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-11">
				<div class="input-group">
					<textarea class="form-control" placeholder="选填" rows="3" id="redisReadResult" name="redisReadResult"></textarea>
					<span class="input-group-addon" id="paramemo">返回结果&nbsp;&nbsp;</span>
				</div>
			</div>
		</div>
		
		<h1 id="clients" class="title text-center">刷新Redis业务数据</h1>
		<div class="row">
			<div class="col-lg-11">
				<div class="input-group">
					<input type="text" class="form-control" id="wsURL" name="wsURL" aria-describedby="basic-addon1" value="http://localhost:8080/MeasureService/tools/reload.do">
					<span class="input-group-addon" id="paramname">测试地址4</span>
				</div>
			</div>
			<div class="col-lg-1" style="margin-top:5px;">
				<button type="button" class="btn btn-success" data-toggle="modal" onclick="javascript:WSRequest();">
					<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;执行
				</button>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-11">
				<div class="input-group">
					<textarea class="form-control" placeholder="选填" rows="3" id="wsResult" name="wsResult"></textarea>
					<span class="input-group-addon" id="paramemo">返回结果&nbsp;&nbsp;</span>
				</div>
			</div>
		</div>
		
		<h1 id="clients" class="title text-center">清空Redis业务数据</h1>
		<div class="row">
			<div class="col-lg-11">
				<div class="input-group">
					<input type="text" class="form-control" id="redisWriteURL" name="redisWriteURL" aria-describedby="basic-addon1" value="http://localhost:8080/MeasureService/tools/clear.do?key=">
					<span class="input-group-addon" id="paramname">测试地址5</span>
				</div>
			</div>
			<div class="col-lg-1" style="margin-top:5px;">
				<button type="button" class="btn btn-success" data-toggle="modal" onclick="javascript:RedisWriteRequest();">
					<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;执行
				</button>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-11">
				<div class="input-group">
					<textarea class="form-control" placeholder="选填" rows="3" id="redisWriteResult" name="redisWriteResult"></textarea>
					<span class="input-group-addon" id="paramemo">返回结果&nbsp;&nbsp;</span>
				</div>
			</div>
		</div>
		</div>
	<br/>
	<br/>
	<script type="text/javascript">
		function RestDataBaseRequest(){
			$.ajax({
	            type: "post",
	            url: $('#restURL').val(),
	            dataType: "json",
	            success: function(data){
	            	 $('#restResult').val(JSON.stringify(data))
	            }
            });
		}

		function WSRequest(){
			$.ajax({
	            type: "post",
	            url: $('#wsURL').val(),
	            dataType: "json",
	            success: function(data){
	            	 $('#wsResult').val(JSON.stringify(data))
	            }
            });
		}

		function RedisWriteRequest(){
			$.ajax({
	            type: "post",
	            url: $('#redisWriteURL').val(),
	            dataType: "json",
	            success: function(data){
	            	 $('#redisWriteResult').val(JSON.stringify(data))
	            }
            });
		}

		function RedisReadRequest(){
			$.ajax({
	            type: "post",
	            url: $('#redisReadURL').val(),
	            dataType: "json",
	            data:{validflag:12456},
	            success: function(data){
	            	 $('#redisReadResult').val(JSON.stringify(data))
	            }
            });
		}
		
		function RedisDelRequest(){
			$.ajax({
	            type: "post",
	            url: $('#redisDelURL').val(),
	            dataType: "json",
	            success: function(data){
	            	 $('#redisDelResult').val(JSON.stringify(data))
	            }
            });
		}
	</script>
</body>
</html>