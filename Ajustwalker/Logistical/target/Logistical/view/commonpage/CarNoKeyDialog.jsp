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
<title>车号编辑</title>
    <script language="javascript">
        function exit() {
        	parent.closeCheckCarno();
        }

        function yes() {
        	parent.takeBackCarno($('#keyinfo').val());
        	parent.closeCheckCarno();
        }

        function delKeyInfo() {
            var keyinfo = $('#keyinfo').val();
            var len = keyinfo.length;
            keyinfo = keyinfo.substring(0, len - 1);
            $('#keyinfo').val(keyinfo);
            $('#keyinfo').focus();
        }

        function delKeyInfoAll() {
        	 $('#keyinfo').val("");
             $('#keyinfo').focus();
        }

        function setKeyInfo(val) {
            var keyinfo = $('#keyinfo').val();
            if (keyinfo.length < 10) {
            	$('#keyinfo').val(keyinfo + val);
            }
            var newvalue = $('#keyinfo').val();
            $('#keyinfo').val("");
            $('#keyinfo').focus();
            $('#keyinfo').val(newvalue) ;
        }
        function onMouse_Over(val) {
            val.bgColor = "dddddd";
        }

        function onMouse_Out(val) {
            val.bgColor = "ffffff";
        }
        $(document).ready(function() {
            var keyinfo = $("input[name=keyinfo]").val();
            $("#keyinfo").val("");
            $("#keyinfo").focus();
            $("#keyinfo").val(keyinfo);
            $("#keyinfo").keyup(function() {
                if ($('#keyinfo').val() != $('#keyinfo').val().toUpperCase())
                $("#keyinfo").val($("#keyinfo").val().toUpperCase());
            });
        });
        
    </script>
</head>

<body>
	<div class="row">
		<div class="col-sm-3">
			<div class='form-group input-group input-group-sm date'>
				<span class="input-group-addon">选择信息:</span> 
				<input name="keyinfo" type="text" id="keyinfo" class="form-control"  value="冀">
			</div>
		</div>
		<div class="col-sm-9">
			<div class=' btn-group-sm'>
				<button  type="button" class="btn btn-info"  onClick="delKeyInfo()">
					<span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span>&nbsp;回退
				</button>
				<button  type="button" class="btn btn-warning"   onClick="delKeyInfoAll()"  >
					<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>&nbsp;清空
				</button>
				<button  type="button" class="btn btn-success"  onClick="yes()">
					<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;确定
				</button>
				<button  type="button" class="btn btn-default"  onClick="exit()">
					<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>&nbsp;关闭
				</button>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<table data-toggle="table" data-mobile-responsive="true" >
				<tbody>
					<c:forEach items="${cardlist}" var="cardhead" varStatus="num">
						<c:if test="${num.index%8==0}"><tr></c:if>
							<td height="25" align="center" onclick="setKeyInfo('${cardhead.briefname}')" onMouseOver="onMouse_Over(this)" onMouseOut="onMouse_Out(this)">${cardhead.briefname}</td>
						<c:if test="${(num.index)%8==7}"></tr></c:if>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</t:html>
