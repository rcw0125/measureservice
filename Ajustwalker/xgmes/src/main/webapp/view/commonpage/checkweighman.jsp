<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="t" uri="http://www.talent.com/jsp/anyonelse"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.Date"%>
<t:html>
<head>
	<jsp:include page="common.jsp" flush="true" />
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
<body class="container-fluid" style="padding-top: 15px" >

	<div class="modal fade" id="initCardWindow" tabindex="-1" role="dialog"
		aria-labelledby="initCardWindowLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">监秤人</h4>
				</div>
				<div class="modal-body">
					<form id="checkmanForm">
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group input-group-sm">
									<div class="input-group-addon">IC&nbsp;&nbsp;卡&nbsp;&nbsp;号</div>
									<input  name="checkweighid" type="text" class="form-control"aria-describedby="basic-addon1" value="" required data-bv-notempty-message="未读取卡号">
								</div>
							</div>
						</div>
						<div class="row" >
							<div class="col-sm-12">
								<div class="form-group input-group input-group-sm">
									<div class="input-group-addon">印刷编号</div>
									<input  name="checkweighman" type="text"class="form-control" aria-describedby="basic-addon1">
								</div>
							</div>
						</div>		
					</form>
				</div>
				<div class="modal-footer btn-group-sm">
					<button type="button" class="btn btn-success" id="initBtn"
						onclick="initCard()">确认</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>  
	<script type="text/javascript">
	//var cardType = 'LJYZN107~MHRF35LT';  //MC9500  MHRF35LT   LJYZN107
	var cardType;// = 'MHRF35LT'; 
	var rfidType='LJYZN107'
	jQuery(document).ready(function($) {
		queryinfo();	
		queryRTypeBycode();//通过作业点编码查询读卡器型号
		  
	});
	
	function queryRTypeBycode(){
		$.ajax({
            type: "post",
            url: '<c:url value="/bcommon/queryRTypeBycode.do"/>',
            dataType: "json",
            data: {workpointcode:$("#unitcodes").val()}, 
            success: function(data){
            	 if(data.more!=null){
            		if(data.more.ictype!=null&&data.more.ictype!=''){
               		    cardType= data.more.ictype;
               	    }
            		if(data.more.rfidtype!=null&&data.more.rfidtype!=''){
                  		rfidType=data.more.rfidtype;
                  	}
            	} 
             }
        });
	}		
		$(function() {
			$('#begintime').datetimepicker({
				format : 'YYYY-MM-DD HH:mm:ss',
				locale : 'zh-cn'
			});
			$('#endtime').datetimepicker({
				format : 'YYYY-MM-DD HH:mm:ss',
				locale : 'zh-cn'
			});
			$("#begintime").on("dp.change", function(e) {
				$('#endtime').data("DateTimePicker").minDate(e.date);
			});
			$("#endtime").on("dp.change", function(e) {
				$('#begintime').data("DateTimePicker").maxDate(e.date);
			});
			
			$('#beginperiod').datetimepicker({
				format : 'HH:mm:ss',
				locale : 'zh-cn'
			});
			$('#endperiod').datetimepicker({
				format : 'HH:mm:ss',
				locale : 'zh-cn'
			});
			$("#beginperiod").on("dp.change", function(e) {
				$('#endperiod').data("DateTimePicker").minDate(e.date);
			});
			$("#endperiod").on("dp.change", function(e) {
				$('#beginperiod').data("DateTimePicker").maxDate(e.date);
			});
			
			$('#begintimes').datetimepicker({
				format : 'YYYY-MM-DD HH:mm:ss',
				locale : 'zh-cn'
			});
			$('#endtimes').datetimepicker({
				format : 'YYYY-MM-DD HH:mm:ss',
				locale : 'zh-cn'
			});
			$("#begintimes").on("dp.change", function(e) {
				$('#endtimes').data("DateTimePicker").minDate(e.date);
			});
			$("#endtimes").on("dp.change", function(e) {
				$('#begintimes').data("DateTimePicker").maxDate(e.date);
			});
			
			$('#begintimess').datetimepicker({
				format : 'YYYY-MM-DD HH:mm:ss',
				locale : 'zh-cn'
			});
			$('#endtimess').datetimepicker({
				format : 'YYYY-MM-DD HH:mm:ss',
				locale : 'zh-cn'
			});
			$("#begintimess").on("dp.change", function(e) {
				$('#endtimess').data("DateTimePicker").minDate(e.date);
			});
			$("#endtimess").on("dp.change", function(e) {
				$('#begintimess').data("DateTimePicker").maxDate(e.date);
			});
			
			$('#beginperiods').datetimepicker({
				format : 'HH:mm:ss',
				locale : 'zh-cn'
			});
			$('#endperiods').datetimepicker({
				format : 'HH:mm:ss',
				locale : 'zh-cn'
			});
			$("#beginperiods").on("dp.change", function(e) {
				$('#endperiods').data("DateTimePicker").minDate(e.date);
			});
			$("#endperiods").on("dp.change", function(e) {
				$('#beginperiods').data("DateTimePicker").maxDate(e.date);
			});
		});
		
		function queryParams(params) {
			return jQuery.extend({}, params, $('#queryform').serializeJson());
		}
		
		function queryinfo() {
			$('#CardGrid').bootstrapTable('refresh', {url : "<c:url value='/card/queryPage.do'/>"});
		}
		
		function openWindow(page) {  
			if ("init" == page) {
				$('#initCardWindow').modal('show');
			}else if ("initrfid" == page) {
				$('#initRfidCardWindow').modal('show');
			} else if ("from" == page) {
				$('#fromCardWindow').modal('show');
			} else if ("back" == page) {
				$('#backCardWindow').modal('show');
			} else if ("rfidfrom" == page) {
				$('#fromRfidCardWindow').modal('show');
			} else if ("rfidback" == page) {
				var selectedRows = $('#CardGrid').bootstrapTable('getSelections');
				var cardid = selectedRows[0].cardid;
				if(selectedRows[0].cardclass==1){
					if(cardid!=null && cardid!=''){
						$('#backRfidCardForm input[name="cardid"]').val(cardid)
						$('#backRfidCardWindow').modal('show');
					}else{
						errorbox("请选择需要退卡的RFID卡")
					}
				}
				
				
			} 
		}
		
		function cardclassFormatter(value, row, index) {
			if(row.cardclass==0){
				return 'IC卡';
			}else{
				return 'RFID卡';
			}
			
		}
		//---------------------------------------IC初始化------------------------------------------------	
		/*打开初始化*/		
		function onFindCard(data){//打卡读卡器进行哪些操作
			$('#initBtn').prop('disabled', false);
			$('#initCardForm input[name="cardid"]').val(data.cardid);
			$.ajax({
	            type: "post",
	            url: '<c:url value="/card/judgInitCarno.do"/>',
	            dataType: "json",
	            data: {cardid:data.cardid}, 
	            success: function(data){
	            	  if(!data.success ){//卡和车辆状态正常，根据车号查询业务信息
	            		errorbox(data.msg);
	            		$('#initBtn').prop('disabled', true); 
	            	 }
	            }
            });
			validForm('initCardForm');
		}
		
		function onDropCard(data){//关闭读卡器，进行哪些操作
			$('#initCardForm input[name="cardid"]').val('');		      
		    $('#initBtn').prop('disabled', false);  
			validForm('initCardForm'); 
		}
		
		 /* 打开IC卡初始化页面 */
		$('#initCardWindow').on('shown.bs.modal', function() {		
			loadFormData('initCardForm', '<c:url value="/card/queryBcard.do?page=ic"/>',function(formdata){
				OpenMediaReader(cardType,onFindCard,onDropCard,function(data){//第一个参数是读卡器型号、第二个是打开卡操作，第三个参数是关闭读卡器操作
				
				}); 
			});
		});
		 /*关闭IC初始化页面*/
		$('#initCardWindow').on('hide.bs.modal', function() {			
			CloseMediaReader(cardType,function(data){//关闭读卡器
				
			});
		});
		/**
		 *添加IC初始化
		 */
		function initCard() {
			saveFormData('initCardForm','<c:url value="/card/initCard.do"/>',function(data){				
				if (data.success) {
					successbox(data.msg);
					$('#CardGrid').bootstrapTable('refresh');
					$('#initCardForm input[name="cardid"]').val('');
					$('#initCardForm input[name="cardno"]').val($('#initCardForm input[name="cardno"]').val()*1+1); 
					validForm('initCardForm');
				} else {
					errorbox(data.msg);
				}
			});
		}
		//---------------------------------------Rfid初始化------------------------------------------------	
		/*打开初始化*/		
		function onFindInitRFIDCard(data){//打卡读卡器进行哪些操作
			$('#initrfidBtn').prop('disabled', false);
		     var rfid = data.cardid;
			$('#initRfidCardForm input[name="cardid"]').val(rfid);
			$.ajax({
	            type: "post",
	            url: '<c:url value="/card/judgInitCarno.do"/>',
	            dataType: "json",
	            data: {cardid:rfid}, 
	            success: function(data){
	            	  if(!data.success ){//卡和车辆状态正常，根据车号查询业务信息
	            		errorbox(data.msg);
	            		$('#initrfidBtn').prop('disabled', true); 
	            	 }
	            }
            });			    
			validForm('initRfidCardForm');
		}
		
		function onDropInitRFIDCard(data){//关闭读卡器，进行哪些操作
		    $('#initRfidCardForm input[name="rfidno"]').val('');   
		    $('#initrfidBtn').prop('disabled', false);  
			validForm('initRfidCardForm'); 
		}
     
		/* 打开RFID卡初始化页面 */
		$('#initRfidCardWindow').on('shown.bs.modal', function() {		
			loadFormData('initRfidCardForm', '<c:url value="/card/queryBcard.do?page=rfid"/>',function(formdata){
				OpenMediaReader(rfidType,onFindInitRFIDCard,onDropInitRFIDCard,function(data){//第一个参数是读卡器型号、第二个是打开卡操作，第三个参数是关闭读卡器操作
				
				}); 
			});
		});
		$('#initRfidCardWindow').on('hide.bs.modal', function() {			
			CloseMediaReader(rfidType,function(data){//关闭读卡器
				
			});
		});
		
		/**
		 *添加Rfid初始化
		 */
		function initRfidCard() {
			saveFormData('initRfidCardForm','<c:url value="/card/initCard.do"/>',function(data){				
				if (data.success) {
					successbox(data.msg);
					$('#CardGrid').bootstrapTable('refresh');
					$('#initRfidCardForm input[name="cardid"]').val('');
					$('#initRfidCardForm input[name="cardno"]').val($('#initRfidCardForm input[name="cardno"]').val()*1+1); 
					validForm('initRfidCardForm');
				} else {
					errorbox(data.msg);
				}
			});
		}
		//---------------------------------------IC发卡------------------------------------------------	
		/*打开IC发卡页面*/
		
		function onFindICard(data){//打卡读卡器进行哪些操作
			$('#fromBtn').prop('disabled', false); 
			$('#fromCardForm input[name="cardid"]').val(data.cardid);		
			$.ajax({
	            type: "post",
	            url: '<c:url value="/card/judgCarId.do"/>',
	            dataType: "json",
	            data: {cardid:data.cardid}, 
	            success: function(data){
	            	  if(!data.success){//卡和车辆状态正常，根据车号查询业务信息
	            		errorbox(data.msg);
	            		$('#fromBtn').prop('disabled', true); 
	            	 }
	            }
            });		
			validForm('fromCardForm');
		}
		
		function onDropICard(data){//关闭读卡器，进行哪些操作
			$('#fromCardForm input[name="cardid"]').val('');
			$('#fromCardForm input[name="cardtype"]').val('');
			$('#fromCardForm input[name="carno"]').val('');
			$('#fromCardForm input[name="driver"]').val('');
			$('#fromCardForm input[name="deposit"]').val('');
			$('#fromCardForm input[name="cartype"]').val('');
			$('#fromCardForm input[name="motorcadecode"]').val('');
			$('#fromCardForm input[name="motorcadename"]').val('');
			$('#fromCardForm input[name="usermemo"]').val('');
			$('#fromCardForm input[name="begintime"]').val('');
			$('#fromCardForm input[name="endtime"]').val('');
			$('#fromCardForm input[name="beginperiod"]').val('');
			$('#fromCardForm input[name="endperiod"]').val('');			      
			$('#fromBtn').prop('disabled', false); 
			validForm('fromCardForm');
		}
		
		
	
		$('#fromCardWindow').on('shown.bs.modal', function() {			
			loadFormData('fromCardForm', '<c:url value="/card/queryBcard.do"/>',function(formdata){
				OpenMediaReader(cardType,onFindICard,onDropICard,function(data){//第一个参数是读卡器型号、第二个是打开卡操作，第三个参数是关闭读卡器操作
					
				});
			});
		});
		
		$('#fromCardWindow').on('hide.bs.modal', function() {			
			CloseMediaReader(cardType,function(data){//关闭读卡器
				
			});
		});

		/**
		 *添加发卡
		 */
		function fromCard() {
			$('#fromBtn').prop('disabled', true); 
			$('#cardclassic').val(0);
			
			$("#frommotorcadename").val($('#frommotorcadecode option:selected').text())		
			saveFormData('fromCardForm','<c:url value="/card/fromCard.do"/>',function(data){
				if (data.success) {
					successbox(data.msg);
					$('#CardGrid').bootstrapTable('refresh');
					$('#fromCardForm input[name="cardid"]').val('');
					$('#fromCardForm input[name="cardtype"]').val('');
					$('#fromCardForm input[name="carno"]').val('');
					$('#fromCardForm input[name="driver"]').val('');
					$('#fromCardForm input[name="deposit"]').val('');
					$('#fromCardForm input[name="cartype"]').val('');
					$('#fromCardForm input[name="motorcadecode"]').val('');
					$('#fromCardForm input[name="motorcadename"]').val('');
					$('#fromCardForm input[name="usermemo"]').val('');
					$('#fromCardForm input[name="begintime"]').val('');
					$('#fromCardForm input[name="endtime"]').val('');
					$('#fromCardForm input[name="beginperiod"]').val('');
					$('#fromCardForm input[name="endperiod"]').val('');	
				} else {
					errorbox( data.msg);
				}				
			});
		}
		var carnoLayer;
		var flag;
		function checkCarno(type) {
			flag=type;
			carnoLayer = layer.open({
				type : 2,
				title : '车牌号选择',
				maxmin : true, //开启最大化最小化按钮
				area : [ '90%', '90%' ],
				content : '<c:url value="/bcommon/queryCardhead.do"/>'//注意，如果str是object，那么需要字符拼接。
			});
		}
		
		function takeBackCarno(carno_v){
			if(flag=='IC'){
				$('#fromCardForm input[name="carno"]').val(carno_v);
				validForm('fromCardForm');
			}else{
				$('#fromRfidCardForm input[name="carno"]').val(carno_v);
				validForm('fromRfidCardForm');
			}
		}
		
		function closeCheckCarno(){
			layer.close(carnoLayer);
		}
		//---------------------------Rfid发卡-----------------------------------------------------
		/*打开发卡页面*/
		function onFindRfidCard(data){//打卡读卡器进行哪些操作
			$('#fromRfidCard').prop('disabled', false); 
			$('#fromRfidCardForm input[name="cardid"]').val(data.cardid);		
			$.ajax({
	            type: "post",
	            url: '<c:url value="/card/judgCarId.do"/>',
	            dataType: "json",
	            data: {cardid:data.cardid}, 
	            success: function(data){
	            	  if(!data.success){//卡和车辆状态正常，根据车号查询业务信息
	            		errorbox(data.msg);
	            		$('#fromRfidCard').prop('disabled', true); 
	            	 }
	            }
            });		
			validForm('fromRfidCardForm');
		}
		
		function onDropRfidCard(data){//关闭读卡器，进行哪些操作
			$('#fromRfidCardForm input[name="cardid"]').val('');
			$('#fromRfidCardForm input[name="cardtype"]').val('');
			$('#fromRfidCardForm input[name="carno"]').val('');
			$('#fromRfidCardForm input[name="cartype"]').val('');
			$('#fromRfidCardForm input[name="motorcadecode"]').val('');
			$('#fromRfidCardForm input[name="motorcadename"]').val('');
			$('#fromRfidCardForm input[name="usermemo"]').val('');	      
			$('#fromRfidCard').prop('disabled', false); 
			validForm('fromRfidCardForm');
		}
		
		$('#fromRfidCardWindow').on('shown.bs.modal', function() {			
			loadFormData('fromRfidCardForm', '<c:url value="/card/queryBcard.do?page=rfidfrom"/>',function(formdata){
				OpenMediaReader(rfidType,onFindRfidCard,onDropRfidCard,function(data){//第一个参数是读卡器型号、第二个是打开卡操作，第三个参数是关闭读卡器操作
					
				});
			});
		});
		
		$('#fromRfidCardWindow').on('hide.bs.modal', function() {	
			//关闭读卡器
			CloseMediaReader(rfidType,function(data){});
		});
		

		/**
		 *添加Rfid发卡
		 */
		function fromRfidCard() {
			$('#fromRfidCard').prop('disabled', true);
			$('#cardclassrfid').val(1);
			$('#cardtyperfid').val(1);
			$('#cartyperfid').val(0);
			/* 
			$("#frommotorcadename").val($('#frommotorcadecode option:selected').text())	 */	
			saveFormData('fromRfidCardForm','<c:url value="/card/fromCard.do"/>',function(data){
				if (data.success) {
					successbox(data.msg);
					$('#fromRfidCardForm input[name="cardid"]').val('');
					$('#fromRfidCardForm input[name="cardtype"]').val('');
					$('#fromRfidCardForm input[name="carno"]').val('');
					$('#fromRfidCardForm input[name="cartype"]').val('');
					$('#fromRfidCardForm input[name="motorcadecode"]').val('');
					$('#fromRfidCardForm input[name="motorcadename"]').val('');
					$('#fromRfidCardForm input[name="usermemo"]').val('');	
					$('#CardGrid').bootstrapTable('refresh');
				} else {
					errorbox( data.msg);
				}				
			});
		}
		
		
	   //---------------------------------------IC退卡------------------------------------------------	
		/*打开退卡页面*/
		function onFindBackICard(data){//打卡读卡器进行哪些操作
			$('#backBtn').prop('disabled', false ); 
			var cardid = data.cardid;
			 $('#backCardForm input[name="cardid"]').val(cardid);
			 $.ajax({
	            type: "post",
	            url: '<c:url value="/card/judgCarno.do"/>',
	            dataType: "json",
	            data: {cardid:cardid}, 
	            success: function(data){
	            	 if(data.success ){//卡和车辆状态正常，根据车号查询业务信息
	            		loadFormData('backCardForm', '<c:url value="/card/queryinfoBycardid.do?cardid="/>'+cardid); 	            		
	            	 }else{
	            	    errorbox(data.msg);
		            	$('#backBtn').prop('disabled', true);  
	            	 }
	            }
            }); 
			 validForm('backCardForm');
		}
		
		function onDropBackICard(data){//关闭读卡器，进行哪些操作
			$('#backCardForm input[name="cardid"]').val('');
			$('#backCardForm input[name="cardtype"]').val('');
			$('#backCardForm input[name="carno"]').val('');
			$('#backCardForm input[name="driver"]').val('');
			$('#backCardForm input[name="deposit"]').val('');
			$('#backCardForm input[name="cardtype"]').val('');
			$('#backCardForm input[name="motorcadecode"]').val('');
			$('#backCardForm input[name="motorcadename"]').val('');
			$('#backCardForm input[name="memo"]').val('');
			$('#backBtn').prop('disabled', false); 
			validForm('backCardForm');
		}

		
		$('#backCardWindow').on('hide.bs.modal', function() {			
			CloseMediaReader(cardType,function(data){//关闭读卡器				
			});
		});
		$('#backCardWindow').on('shown.bs.modal', function() {
			OpenMediaReader(cardType,onFindBackICard,onDropBackICard,function(data){//第一个参数是读卡器型号、第二个是打开卡操作，第三个参数是关闭读卡器操作
			
			}); 
		});
		/**
		 *添加退卡
		 */
		function backCard() {
			$('#backBtn').prop('disabled', true); 
			saveFormData('backCardForm','<c:url value="/card/backCard.do"/>',function(data){
				if (data.success) {
					successbox(data.msg);
					$('#backCardForm input[name="cardid"]').val('');
					$('#backCardForm input[name="cardtype"]').val('');
					$('#backCardForm input[name="carno"]').val('');
					$('#backCardForm input[name="driver"]').val('');
					$('#backCardForm input[name="deposit"]').val('');
					$('#backCardForm input[name="cardtype"]').val('');
					$('#backCardForm input[name="motorcadecode"]').val('');
					$('#backCardForm input[name="motorcadename"]').val('');
					$('#backCardForm input[name="memo"]').val('');
					//$('#backCardWindow').modal('toggle');
					$('#CardGrid').bootstrapTable('refresh');
				} else {
					errorbox(data.msg);
				}
				
			});
		}
        //----------------------------------------RFID退卡----------------------------------------------
          
    	/*打开退卡页面*/
		/*function onFindBackRfidCard(data){//打卡读卡器进行哪些操作
			$('#backRfidBtn').prop('disabled', false ); 
			var cardid = data.cardid;
			 $('#backRfidCardForm input[name="cardid"]').val(cardid);
			 $.ajax({
	            type: "post",
	            url: '<c:url value="/card/judgCarno.do"/>',
	            dataType: "json",
	            data: {cardid:cardid}, 
	            success: function(data){
	            	 if(data.success ){//卡和车辆状态正常，根据车号查询业务信息
	            		loadFormData('backRfidCardForm', '<c:url value="/card/queryinfoBycardid.do?cardid="/>'+cardid); 	            		
	            	 }else{
	            	    errorbox(data.msg);
		            	$('#backRfidBtn').prop('disabled', true);  
	            	 }
	            }
            }); 
			 validForm('backRfidCardForm');
		}
		
		 function onDropBackRfidCard(data){//关闭读卡器，进行哪些操作
			$('#backRfidCardForm input[name="cardid"]').val('');
			$('#backRfidCardForm input[name="carno"]').val('');
			$('#backRfidCardForm input[name="motorcadecode"]').val('');
			$('#backRfidCardForm input[name="motorcadename"]').val('');
			$('#backRfidCardForm input[name="memo"]').val('');
			$('#backRfidBtn').prop('disabled', false); 
			validForm('backRfidCardForm');
		}

		
		$('#backRfidCardWindow').on('hide.bs.modal', function() {			
			CloseMediaReader(rfidType,function(data){//关闭读卡器				
			});
		}); */
		$('#backRfidCardWindow').on('shown.bs.modal', function() {
			/* OpenMediaReader(rfidType,onFindBackRfidCard,onDropBackRfidCard,function(data){//第一个参数是读卡器型号、第二个是打开卡操作，第三个参数是关闭读卡器操作
			
			}); */ 
			loadFormData('backRfidCardForm', '<c:url value="/card/queryinfoBycardid.do?cardid="/>'+$('#backRfidCardForm input[name="cardid"]').val())
		});
		/**
		 *添加退卡
		 */
		function backRfidCard() {
			$('#backRfidBtn').prop('disabled', true); 
			saveFormData('backRfidCardForm','<c:url value="/card/backCard.do"/>',function(data){
				if (data.success) {
					successbox(data.msg);
					$('#backRfidCardForm input[name="cardid"]').val('');
					$('#backRfidCardForm input[name="carno"]').val('');
					$('#backRfidCardForm input[name="motorcadecode"]').val('');
					$('#backRfidCardForm input[name="motorcadename"]').val('');
					$('#backRfidCardForm input[name="memo"]').val('');
					//$('#backCardWindow').modal('toggle');
					$('#CardGrid').bootstrapTable('refresh');
					$('#backRfidBtn').prop('disabled', false); 
				} else {
					errorbox(data.msg);
					$('#backRfidBtn').prop('disabled', false); 
				}
				
			});
		}
        
        
        
		/**
		 *挂失
		 */
		function cancelCard(cardid) {
			var selectedRows = $('#CardGrid').bootstrapTable('getSelections');
			if (selectedRows.length == 0) {
				warningbox("请选择挂失卡信息");
			} else {
				dialogbox("请确认", "确认挂失电子卡吗？",function(data){	
                  if(data){						
					$.ajax({
						type : "post",
						url : '<c:url value="/card/cancelCard.do"/>',
						dataType : "json",
						data : {cardid : selectedRows[0].cardid,cardclass:selectedRows[0].cardclass	},
						success : function(data) {
							if (data.success) {
								successbox(data.msg);
								$('#CardGrid').bootstrapTable('refresh');
							}else{
								errorbox(data.msg);
							}
						}
					});
				  }
				});
			}
		}
		function judgOrFromcarno() {
			$.ajax({
				type : "post",
				url : '<c:url value="/card/judgOrFromcarno.do"/>',
				dataType : "json",
				data : {carno : $('#fromCardForm input[name="carno"]').val(),recordtype:0},
				success : function(data) {					
					if (data.success==false) {
						errorbox(data.msg);
					} 
				}
			});
			
		}
		
		function updatetime(cardid) {
			var selectedRows = $('#CardGrid').bootstrapTable('getSelections');
			if (selectedRows.length != 0) {
				if(selectedRows[0].cardclass==0){
					if(selectedRows[0].validflag == "发卡"){
						if(selectedRows[0].cardtype == "固定卡"){
								if(selectedRows[0].cartype == "员工车辆"){
									$('#changeTimeForm input[name="cardid"]').val(selectedRows[0].cardid);
									$('#changeTimeWindow').modal('show');
									loadFormData('changeTimeForm', '<c:url value="/card/queryinfoBycardid.do?cardid="/>'+$('#changeTimeForm input[name="cardid"]').val())
								} else {
									errorbox("请选择员工车辆卡!")
								}
							} else {
								errorbox("请选择固定卡!")
							}
						
					} else {
						errorbox("请选择已发卡的IC卡!")
					}
				}else{
					errorbox("请选择IC卡!")
				}
			} else {
				errorbox("请选择需要修改有效期的IC卡!")
			}
		}
		
		function changeTime() {
			$('#changeBtn').prop('disabled', true); 
			saveFormData('changeTimeForm','<c:url value="/card/updatetime.do"/>',function(data){
				if (data.success) {successbox(data.msg);
					$('#changeTimeForm input[name="begintime"]').val('');
					$('#changeTimeForm input[name="endtime"]').val('');
					$('#changeTimeForm input[name="usermemo"]').val('');
					$('#CardGrid').bootstrapTable('refresh');
					$('#changeBtn').prop('disabled', false); 
				} else {
					errorbox(data.msg);
					$('#changeBtn').prop('disabled', false); 
				}
				
			});
		}
	</script>
</body>
</t:html>