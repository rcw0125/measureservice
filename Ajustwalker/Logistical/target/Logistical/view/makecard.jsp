<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="t" uri="http://www.talent.com/jsp/anyonelse"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.Date"%>
<t:html>
	<head>
		<jsp:include page="common.jsp" flush="true" />
		<script type="text/javascript" src="<c:url value='/plugins/datatable/extensions/editable/bootstrap-table-editable.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/plugins/datatable/extensions/popupedit/bootstrap-table-popupedit.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/plugins/bootstrap-typeahead.js'/>"></script>
	</head>

<%
	SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd 00:00:00");
	SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
	Date now = new Date();
	Calendar c = Calendar.getInstance();
	now = c.getTime();
	String begintime = dateFormat.format(now);//开始时间
	String endtime = dateFormat1.format(now);//结束时间
	
%>
<body class="container-fluid" style="padding-top: 15px">
	<div class="row">
		<div class="col-sm-12">
			<form id="queryform">			
				<input type="hidden" id="typecode" name="typecode" value="${operatype}">
				<input type="hidden" id="unitcodes"  value="${unitcode}">
				<input type="hidden" id="types"  name="types" value="0">
				<div class="row">
					<div class="col-sm-3">
						<div class='form-group input-group input-group-sm date'>
							<span class="input-group-addon">开始时间</span> 
							<input type='text' class="form-control" placeholder="开始时间" id="begintime" name="begintime" value="<%=begintime%>" />
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">物&nbsp;&nbsp;流&nbsp;&nbsp;号 </span>
							<input type="text" class="form-control" placeholder="物流号" name="matchid" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">货　　名</span> 
							<input type="text" class="form-control" placeholder="货名" id="materialname" name="materialname" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">作&nbsp;&nbsp;业&nbsp;&nbsp;点 </span> 
							<input type="text" class="form-control" placeholder="作业点" name="unitname"aria-describedby="sizing-addon3" value="${unitname}">
						</div>
					</div>
				</div>
				<div class="row" >
					<div class="col-sm-3">
						<div class='form-group input-group input-group-sm date'>
							<span class="input-group-addon">结束时间</span> 
							<input type='text' class="form-control" placeholder="结束时间" id="endtime" name="endtime" value="<%=endtime%>" />
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">计&nbsp;&nbsp;划&nbsp;&nbsp;号 </span> 
							<input type="text" class="form-control" placeholder="计划号" id="planid" name="planid" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">供　　货</span>
							<input type="text" class="form-control" placeholder="供货" id="sourcename" name="sourcename" aria-describedby="sizing-addon3">
						</div>
					</div>				
				</div>
				<div class="row">
					<div class="col-sm-3">
						  <t:combobox id="operatypes" name="operatype"  url="/bcommon/queryOperatype.do?operatype=${operatype}" label="业务类型" require="false" alloptions="true" allowclear="true"/>	
					</div>
					
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">车　　号</span>
							<input type="text" class="form-control" placeholder="车号" id="carno" name="carno" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">收　　货</span>
							<input type="text" class="form-control" placeholder="收货" id="targetname" name="targetname" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class=" col-sm-3 " >
					    <div class="form-group input-group  btn-group-sm">
						<!-- <button id="query" type="button" class="btn btn-info" onclick="queryinfo()">
							<span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;查询
						</button>&nbsp;&nbsp;
						<button id="MakecardBtn" type="button" class="btn btn-success">
							<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;制卡
						</button> -->
						    <t:button text="查询" modulecode="ZhiKaGuanLi" id="query" btnclass="btn btn-info" iconclass="glyphicon glyphicon-search" onclick="queryinfo()"/>&nbsp;
						    <t:button text="制卡" modulecode="ZhiKaGuanLi" id="MakecardBtn" btnclass="btn btn-success" iconclass="glyphicon glyphicon-plus" />
						</div>
					</div>

				</div>
			</form>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<table id="MakecardInfoGrid" 
			          data-toggle="table"  
						data-row-style=rowStyle" 
						data-query-params="queryParams"
						data-pagination="true" 
						data-page-size="10"
						data-page-list="[10,40,ALL]" 
						data-mobile-responsive="true">
				<thead>
					<tr>
					    <th data-width="70px" data-align="center" data-valign="middle"
							data-formatter="operateFormatter" data-events="operateEvents">操作</th>
						<th data-field="matchid" data-halign="center"
							data-searchable="true" sortable>物流号</th>
						<th data-field="operaname" class="text-nowrap" data-halign="center"
							data-searchable="true" sortable>业务类型</th>
						<th data-field="documentname" class="text-nowrap" data-halign="center"
							data-searchable="true" sortable>业务名称</th>
						<th data-field="operatype" data-visible="false" ></th>
						<th data-field="planid" data-halign="center"
							data-searchable="true"  class="text-nowrap"sortable>计划号</th>
						<th data-field="carno" data-halign="center" data-searchable="true"
							class="text-nowrap" >车号</th>
						<th data-field="materialcode" data-halign="center"
							data-searchable="true" class="text-nowrap">货名编码</th>	
						<th data-field="materialname" data-halign="center"
							data-searchable="true" class="text-nowrap">货名</th>
						<th data-field="sourcename" data-halign="center"
							data-searchable="true" class="text-nowrap">供货</th>
						<th data-field="targetname" data-halign="center"
							data-searchable="true" class="text-nowrap">收货</th>						
						<%-- <th data-field="tareb" data-halign="center" data-searchable="true"
							class="text-nowrap">供方皮重/t</th>
						<th data-field="grossb" data-halign="center"
							data-searchable="true" class="text-nowrap">供方毛重/t</th>
						<th data-field="suttleb" data-halign="center"
							data-searchable="true" class="text-nowrap">供方净重/t</th> --%>
						<th data-field="suttleapp" data-halign="center"
							data-searchable="true" class="text-nowrap">重量/t</th>
						<th data-field="snumber" data-halign="center"
							data-searchable="true" class="text-nowrap">数量</th>
						<th data-field="createdate" data-halign="center"
							data-searchable="true" class="text-nowrap">制卡时间</th>
						<th data-field="creator" data-halign="center"
							data-searchable="true" class="text-nowrap">制卡人</th>
						<th data-field="unitname" data-halign="center"
							data-searchable="true" class="text-nowrap">制卡点</th>	
						<th data-field="documentcode" data-halign="center"  
							class="text-nowrap" data-visible="false">单据编号</th>
						<th data-field="usermemo" data-halign="center" data-searchable="true"
							class="text-nowrap">备注</th>
						
					</tr>
				</thead>
			</table>

		</div>
	</div>
	 <div class="modal fade" id="MakeCardWindow" tabindex="-1" role="dialog" aria-labelledby="MakeCardWindowLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">制卡--${unitname}</h4>
				</div>
				<div class="modal-body">
					<form id="MakeCardDetailForm">
						<div class="row">
							<input type="hidden" id="matchid" name="matchid" value="-1" />
							<input type="hidden" id="rfidflag" name="rfidflag" value="0" />
							<input type="hidden" id="fkflag" name="fkflag" value="${fkflag}">
							<input type="hidden" name="unitcode" id="unitcode" value="${unitcode}" />
							<input type="hidden" name="unitname" id="unitname" value="${unitname}">	
				            <input type="hidden" id="cardstate" name="cardstate"  >
				            <input type="hidden"   id="planidlist" name="planidlist"  >
				            <input type="hidden"   id="itemidlist" name="itemidlist"  >
				            <input type="hidden"   id="materialcodes" name="materialcodes"  >
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">IC&nbsp;&nbsp;卡&nbsp;&nbsp;号&nbsp;&nbsp;</span>
									<input id="icid" name="icid" type="text" class="form-control" readonly="readonly" aria-describedby="basic-addon1" required data-bv-notempty-message="请刷卡">
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">RFID卡号&nbsp;</span>
									<input id="rfidid" name="rfidid" type="text" class="form-control" aria-describedby="basic-addon1" readonly="readonly" >
								</div>
							</div>
						</div>
									
						<div class="row">
						    <div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-btn">
										<button class="btn btn-default" type="button" onclick="checkCarno('insert')"><span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>车　号&nbsp;&nbsp;</button>
									</span>	
									<input id="carnos" name="carno" type="text" class="form-control" value="" readonly="readonly"  data-provide="typeahead"  aria-describedby="basic-addon1" >
								</div>
							</div> 
							<div class="col-sm-6">
								  <t:combobox id="operatype" name="operatype"  url="/bcommon/queryOperatype.do?operatype=${operatype}" label="业务&nbsp;类&nbsp;型" require="false"/>	
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">备　　&nbsp;&nbsp;注</span>
									<input name="memo" type="text" class="form-control"	aria-describedby="basic-addon1" >
								</div>
							</div>
						</div>
					<div class="row"　>
						<div class="col-sm-12" >
							<table id="MakeCardDetailGrid" data-mobile-responsive="true" data-unique-id="itemid">
							</table>
						</div>
					</div>
					</form>
				</div>
				<div class="modal-footer  btn-group-sm">
					<button type="button" class="btn btn-success" id="addBtn" onclick="insert()">确认</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>					
				</div>
			</div>
		</div>
	</div>
	
	 <div class="modal fade" id="MakeCardUpdateWindow" tabindex="-1" role="dialog" aria-labelledby="MakeCardWindowLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">修改</h4>
				</div>
				<div class="modal-body"> 
					<form id="MakeCardUpdateForm"> 
						<div class="row">
							<input type="hidden" id="matchids" name="matchid" value="-1" />
							<input type="hidden"   id="upplanidlist" name="upplanidlist"  >
				            <input type="hidden"   id="upitemidlist" name="upitemidlist"  >
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<div class="input-group-addon">卡　　号&nbsp;</div>
									<input  name="icid" type="text" class="form-control" readonly="readonly" aria-describedby="basic-addon1" required data-bv-notempty-message="请刷卡">
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
									<span class="input-group-addon">RFID卡号</span>
									<input id="uprfidid" name="rfidid" type="text" class="form-control" aria-describedby="basic-addon1" readonly="readonly" >
								</div>
							</div>
						</div>
						
						<div class="row">
						<div class="col-sm-6">
								<div class="form-group input-group input-group-sm">
								    <span class="input-group-btn">
										<button class="btn btn-default" type="button" onclick="checkCarno('update')"><span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>车　号&nbsp;</button>
									</span>
									<input  id="upcarno" name="carno" type="text" class="form-control" value="" aria-describedby="basic-addon1" required data-bv-notempty-message="请填写车号">
								</div>
							</div>
							<div class="col-sm-6">
								<t:combobox id="operatypesd" name="operatype"  url="/bcommon/queryOperatype.do?operatype=${operatype}" label="业务类型&nbsp;"/>
							</div>
						</div>

						<div class="row">
							<div class="col-sm-12">
								<div class="form-group input-group input-group-sm">
									<div class="input-group-addon">备　　注&nbsp;</div>
									<input name="memo" type="text" class="form-control"	aria-describedby="basic-addon1" >
								</div>
							</div>
						</div>
					
					<div class="row"　>
						<div class="col-sm-12" >
							<table id="MakeCardUpdateGrid" data-mobile-responsive="true" data-unique-id="itemid">
							</table>
						</div>
					</div>
					</form>
				</div>
				<div class="modal-footer  btn-group-sm">
					<button type="button" class="btn btn-success" id="updateBtn"
						onclick="update()">确认</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	  var cols = '';
	  var rowscount = 0;
	  var operatype = $("#operatype").val(); 
	    function prepend(){    
	        $("#MakeCardDetailGrid").bootstrapTable('prepend', appendData());
	     }
	     function appendData() {
	         var rows = [];
	         rows.push({ itemid:"add"+rowscount,
	        	 deletes:rowscount,
				 planid:"",
	        	 materialcode: 0,
	        	 materialname:'',
	        	 sourcecode:'',
	        	 sourcename:'',
	        	 targetcode:0,
	        	 targetname:'',
	        	 grossb:0,
	        	 tareb:0,
	        	 suttleb:0,
	        	 snumber:0,
	        	 usermemo:''
	         });
	         rowscount++;
	         return rows;
	     }
	     function deletestr(obj,planid,saleitemid) {
		    var planidlist;
			var itemidlist;
			if (operaflag == 0) {//标记是0表示添加
				
				var gridData = $('#MakeCardDetailGrid').bootstrapTable('getData')
				if(gridData.length==1){
					$("#planidlist").val("");
					$("#itemidlist").val("");
					$("#operatype").val('');
					$("#operatype").trigger('change.select2');
				}else{
					//$("#planidlist").val($('#planidlist').val().replace("," + planid + ",", ","));
					$("#itemidlist").val($('#itemidlist').val().replace("," + saleitemid + ",", ","));
				}
				$('#MakeCardDetailGrid').bootstrapTable('removeByUniqueId', obj);
			} else {//否则表示为修改
				var gridData = $('#MakeCardUpdateGrid').bootstrapTable('getData')
				if(gridData.length==1){
					$("#upplanidlist").val("");
					$("#upitemidlist").val("");
				}else{
					$("#upitemidlist").val($('#upitemidlist').val().replace("," + saleitemid + ",", ","));
				}
				$('#MakeCardUpdateGrid').bootstrapTable('removeByUniqueId', obj);
			}
			
		}

		function deletesFormatter(value, row, index) {
			return "<div class='btn-group-sm'><button type='button' class='btn btn-danger'onclick='deletestr(\""+ row.itemid+ "\",\""+ row.planid+ "\",\""+ row.saleitemid+ "\")'><span class='glyphicon glyphicon-minus' aria-hidden='true'></span></button></div>";
		}

		var cardType ;// ='MC9500';= 'LJYZN107~MHRF35LT';MC9500;LJYZN107;MHRF35LT
		jQuery(document).ready(function($) {
			queryinfo();
			queryRTypeBycode();//通过作业点编码查询读卡器型号	
			/* $.fn.typeahead.Constructor.prototype.blur = function() {
				var that = this;
				setTimeout(function() {that.hide()}, 250);
			};

			$('#carnos').typeahead({source : function(query,process) {
					$.ajax({
							type : "post",
							url : '<c:url value="/bcommon/queryCarno.do?carno="/>'+ query,
							dataType : "json",
							success : function(data) {
								var numArr = []; // 定义一个空数组
								for (var i = 0; i < data.rows.length; i++) {
									numArr.push(data.rows[i].carno); // 将文本框的值添加到数组中
								}
								process(numArr);
							}
					});
				}
			}); */

		});

		function queryRTypeBycode() {
			$.ajax({
				type : "post",
				url : '<c:url value="/bcommon/queryRTypeBycode.do"/>',
				dataType : "json",
				data : {workpointcode : $("#unitcodes").val()},
				success : function(data) {
					  if(data.more.ictype!=null&&data.more.ictype!=''){
	               		 if(cardType!=null&&cardType!=''){
	        				cardType=cardType+"~"+data.more.ictype;
	        			}else{
	        				cardType=data.more.ictype;
	        			}
               	    }
					  if(data.more.rfidtype!=null&&data.more.rfidtype!=''){
            			if(cardType!=null&&cardType!=''){
            				 cardType=cardType+"~"+data.more.rfidtype;
            			}else{
            				cardType=data.more.rfidtype;
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
		});
		function queryParams(params) {
			return jQuery.extend({}, params, $('#queryform').serializeJson());
		}

		function queryinfo() {
			$('#MakecardInfoGrid').bootstrapTable('refresh', {
				url : "<c:url value='/application/queryPage.do'/>"
			});
		}

		function operateFormatter(value, row, index) {
			return [
                    '<div class="pull-right" style="width:80px;">',
                    '<a class="print" href="javascript:void(0)" title="打印"><i class="glyphicon glyphicon-print"></i></a>　',
                   	'<t:ibutton text="修改" modulecode="ZhiKaGuanLi" id="makecardmodify" btnclass="edit" iconclass="glyphicon glyphicon-pencil"/>',
                   	'<t:ibutton text="移除" modulecode="ZhiKaGuanLi" id="makecardremove" btnclass="remove" iconclass="glyphicon glyphicon-trash"/>',
                   	'</div>'
               	].join('');			
		}
		window.operateEvents = {
			'click .print' : function(e, value, row) {
				openPrintMeasureInfo(row.matchid);
			},
			'click .edit' : function(e, value, row) {
				openUpdateMakecard(row.matchid);
			},
			'click .remove' : function(e, value, row) {
				cancelApplicationbill(row.matchid, row.documentcode);
			}
		};
		$('#MakecardBtn').on('click', function() {
			openMakecard(-1);
		});
		
		function openPrintMeasureInfo(matchid){
			layer.open({
				type : 2,
				title:'计量信息打印',
				maxmin:true, //开启最大化最小化按钮
				area:['90%','90%'],
				content:'<c:url value="/print/measurebillprint.do"/>?matchid=' + matchid
			});
		}
		function openMakecard(id_vv) {
			$.ajax({
    	        url:'http://127.0.0.1:9001/talentlt/GetMachineInfo',
    	        dataType: "jsonp",
    	        crossDomain: true,
    	        timeout:10000,
    	        jsonp: "callback",
    	        success: function (data) {
    	        	$.ajax({
    		            type: "post",
    		            url: '<c:url value="/workpoint/queryInfoBycode.do"/>',
    		            dataType: "json",
    		            data: {workpointcode:$("#unitcodes").val(),workpointmac:data.macAddress}, 
    		            success: function(data){
    		            	if(data.success){
    		            		if(data.data.validflag=='3'){//审核过的作业点
    		            			$("#matchid").val(id_vv);
    		            			$('#MakeCardWindow').modal('show');
    		            		}else{
    		            			 warningbox("作业点未审核");
    		            		}
    		            		
    		            	}else{
    		            	   warningbox(data.msg);
    		            	}
    		            	  
    		             }
    	            });
    	        }
    	    });
			
		}

		function openUpdateMakecard(id_vv) {
			$("#matchids").val(id_vv);
			$('#MakeCardUpdateWindow').modal('show');
		}

		/*打开发卡页面*/

		//var cardType = 'MC9500';
		function onFindICard(data) {//打卡读卡器进行哪些操作
			var getrfid =data.cardid;
			if('LJYZN107' == data.machinetype){			
				var rfid=$('#MakeCardDetailForm input[name="rfidid"]').val();
				if(rfid==null || rfid==''){
					$('#MakeCardDetailForm input[name="rfidid"]').val(getrfid);
				}else{
					if(rfid!=getrfid){
						errorbox("车辆已经发放RFID卡，无需重新发放");
					}
				}
			}else{
				$('#MakeCardDetailForm input[name="icid"]').val(getrfid);
				$("#planidlist").val("");
				$("#itemidlist").val("");
				$.ajax({
					type : "post",
					url : '<c:url value="/bcommon/judgCarno.do"/>',
					dataType : "json",
					data : {cardid : getrfid},
					success : function(data) {
						if (data.success) {//卡和车辆状态正常，根据车号查询业务信息	
							var validflag = data.rows[0].validflag;
							$("#cardstate").val(validflag)
							if ($("#fkflag").val() == 1) {
								if (validflag == 1) {
									//$("#carnos").removeAttr("readonly");
								} else {
									var getcarno = data.rows[0].carno;
									if(getcarno!=''&& getcarno!=null){
										$("#carnos").val(getcarno);
										//$("#carnos").attr("readonly","readonly");
										$.ajax({
											type : "post",
											url : '<c:url value="/application/queryUncomplete.do"/>',
											dataType : "json",
											data : {carno : getcarno},
											success : function(data) {
												if(data.success){
													
													if(data.mfunc==0){
														checkCarno('insert');
													}else if (data.mfunc>0){
														dialogbox("请确认",data.msg,function(data) {
															if (data) {
																checkCarno('insert');
															}
														});
													}
												}else{
													errorbox(data.msg);
												}
											}
										});
									}else{
										checkCarno('insert');
										
									}
									
									for(var i=0;i<data.rows.length;i++){
										if(data.rows[i].recordtype==1){
											$('#MakeCardDetailForm input[name="rfidid"]').val(data.rows[0].cardid);
											$("#rfidflag").val(1);
										}
									}
									
								}
							}
							
						} else {//如果车辆或者卡有问题，系统提示						
							$('#addBtn').prop('disabled', true);
							if (data.rows[0] != null) {
								var vflag = data.rows[0].validflag;
								$("#cardstate").val(vflag);
								if (vflag == 1) {
									//$("#carnos").removeAttr("readonly");
									$('#addBtn').prop('disabled', false);
									//if('LJYZN107' != cartypeflag){
									 checkCarno('insert');
									//}
								} else {
									errorbox(data.msg);
								}
							} else {
								errorbox(data.msg);
							}
						}
					}
				}); 
			}
			  
			validForm('MakeCardDetailForm');
		}
		function onDropICard(data) {//关闭读卡器，进行哪些操作
			if('LJYZN107' == data.machinetype){		
				if($("#rfidflag").val()==0){
					$('#MakeCardDetailForm input[name="rfidid"]').val('');
				}
			}else{
				$('#MakeCardDetailForm input[name="rfidid"]').val('');
				$('#MakeCardDetailForm input[name="icid"]').val('');
				$('#MakeCardDetailForm input[name="carno"]').val('');
				$("#operatype").val('');
				$("#operatype").trigger('change.select2');
				$('#MakeCardDetailForm input[name="usermemo"]').val('');
				$('#MakeCardDetailForm input[name="planidlist"]').val('');
				$('#MakeCardDetailForm input[name="itemidlist"]').val('');
				$('#MakeCardDetailGrid').bootstrapTable('removeAll');
				$('#addBtn').prop('disabled', false);
				$("#rfidflag").val(0);
				validForm('MakeCardDetailForm');
				
				closeCheckCarno();
			}
		}

		$('#MakeCardWindow').on('hide.bs.modal', function() { //页面隐藏时，关闭读卡器		
			CloseMediaReader(cardType, function(data) {// 暂时屏蔽读卡器关闭读卡器

			});
		});

		$('#MakeCardWindow').on('shown.bs.modal',function() {
          		operaflag = 0;
       			$('#addBtn').prop('disabled', false);
       			var matchid = $("#matchid").val();
       			var fkflag = $("#fkflag").val();
       			var operatype = $("#typecode").val();
       			var unitcode = $("#unitcode").val();
       			var unitname = $("#unitname").val();
       			loadFormData('MakeCardDetailForm','<c:url value="/application/queryApplicationbill.do?matchid="/>'+ matchid+ '&type=makecard&typecode='+ $("#typecode").val(),
       				function(data) {
       					$("#matchid").val(matchid);
       					$("#fkflag").val(fkflag);
       					$("#unitcode").val(unitcode);
       					$("#unitname").val(unitname);
       					OpenMediaReader(cardType,onFindICard,onDropICard,function(data) {//第一个参数是读卡器型号、第二个是打开卡操作，第三个参数是关闭读卡器操作
       						$('#MakeCardDetailGrid').bootstrapTable('removeAll');
       					});
       			});
			
		});

		$('#MakeCardUpdateWindow').on('shown.bs.modal',function() {
			$('#updateBtn').prop('disabled', false);
			operaflag = 1;
			var matchid = $("#matchids").val();
			loadFormData('MakeCardUpdateForm','<c:url value="/application/queryApplicationbill.do?matchid="/>'+ matchid + "&typecode="+ $("#typecode").val()+ "&type=makecard",	function(data) {
				$("#matchids").val(matchid);
				var operatype = $("#operatypesd").val();
				
				$('#MakeCardUpdateGrid').bootstrapTable('removeAll');
				loadComboxData('operatype','<c:url value="/bcommon/queryOperatype.do?operatype="/>'+ operatype);
				$.ajax({
						type : "post",
						url : '<c:url value="/bcommon/queryModelcontent.do?operatype="/>'+ operatype+"&flag=1",
						dataType : "json",
						success : function(data) {
							var cs = JSON.parse(data.msg);
							$('#MakeCardUpdateGrid').bootstrapTable('destroy').bootstrapTable({columns:cs}).bootstrapTable('refresh',{url : "<c:url value='/application/queryAppInfo.do?matchid='/>"+ matchid});
						}
				});
			});
		});

		/**
		 *作废制卡信息
		 */
		function cancelApplicationbill(matchid, documentcode) {
			dialogbox("请确认","确认删除此项目？",function(data) {
				if (data) {
					$.ajax({
							type : "post",
							url : '<c:url value="/application/cancelApplicationbill.do"/>',
							dataType : "json",
							data : {
								matchid : matchid,
								documentcode : documentcode
							},
							success : function(data) {
								if (data.success) {
									successbox(data.msg);
									$('#MakecardInfoGrid').bootstrapTable('refresh',{url : "<c:url value='/application/queryPage.do'/>"});
								} else {
									errorbox(data.msg);
								}
							}
					});
				}
			});
		}
		//操作制卡信息		
		function insert() {
			$('#addBtn').prop('disabled', true);
			var gridData = $('#MakeCardDetailGrid').bootstrapTable('getData')
			var msginfo = '';
			var mlist=''
			var carno=$('#carnos').val();
			if (gridData.length == 0) {
				errorbox("请选择业务信息 ");
			} else {
				for (var i = 0; i < gridData.length; i++) {
					if (gridData[i].planid == null || gridData[i].planid == ''
							|| gridData[i].planid == '请选择') {
						msginfo = msginfo + "第" + (i * 1 + 1) + "行，业务信息为空</br>";
					}
					 if(mlist==null&& mlist==''){
						 mlist=gridData[i].materialcode
	        		}else{
	        			 mlist=mlist+","+gridData[i].materialcode
	        			
	        		}
					
				}
				if (msginfo.length == 0) {
					var rfidid=$("#rfidid").val();
					$.ajax({
						type : "post",
						url : '<c:url value="/application/queryRfidBycarno.do"/>',
						dataType : "json",
						data : {carno:carno},
						success : function(data) {
							var getrfid=data.data;
							if(getrfid!=''&&getrfid!=null && rfidid!=null&& rfidid!='' && rfidid!=getrfid){
								errorbox("车辆已经发卡,无需重新发RFID卡");
							}else{
								$("#materialcodes").val(mlist);//记录物料编码传递到后台
								$.ajax({
									type : "post",
									url : '<c:url value="/application/queryRfidBymcode.do"/>',
									dataType : "json",
									data : {materialcode : mlist,rfidid:rfidid,carno:carno,rfidflag:$("#rfidflag").val()},
									success : function(data) {
										if (data.success) {
											saveFormDataWithParams('MakeCardDetailForm','<c:url value="/application/beforeinsertApplicationbill.do?rfidbak="/>'+rfidid,gridData,function(data) {
												if (data.mfunc == 0) {
													if (data.success) {
														successbox(data.msg);
														$('#MakeCardDetailForm input[name="icid"]').val('');
														$('#MakeCardDetailForm input[name="carno"]').val('');
														$('#MakeCardDetailForm input[name="operatype"]').val('');
														$('#MakeCardDetailForm input[name="usermemo"]').val('');
														$('#MakeCardDetailForm input[name="planidlist"]').val('');
														$('#MakeCardDetailForm input[name="itemidlist"]').val('');
														$('#MakeCardDetailGrid').bootstrapTable('removeAll');
													} else {
														errorbox(data.msg);
													}
												} else {
													if (data.mfunc == 3) { //为3时判断里面有一个禁止添加取样信息
														errorbox(data.msg);
													} else if (data.mfunc == 2) {//为2时用户选择是否添加取样信息
														dialogbox("请确认",data.msg,function(data) {
															if (data) {
																saveFormDataWithParams('MakeCardDetailForm','<c:url value="/application/insertApplicationbill.do?rfidbak="/>'+rfidid,gridData,function(data) {
																	if (data.success) {
																		successbox(data.msg);
																		$('#MakeCardDetailForm input[name="icid"]').val('');
																		$('#MakeCardDetailForm input[name="carno"]').val('');
																		$('#MakeCardDetailForm input[name="operatype"]').val('');
																		$('#MakeCardDetailForm input[name="usermemo"]').val('');
																		$('#MakeCardDetailForm input[name="planidlist"]').val('');
																		$('#MakeCardDetailForm input[name="itemidlist"]').val('');
																		$('#MakeCardDetailGrid').bootstrapTable('removeAll');
																	} else {
																		errorbox(data.msg);
																	}
																});
															}
														});
													}
												}
												$('#addBtn').prop('disabled', false);
												$('#MakecardInfoGrid').bootstrapTable('refresh',{url : "<c:url value='/application/queryPage.do'/>"});
											});

										} else {
											errorbox(data.msg);
											$('#addBtn').prop('disabled', false);
										}
									}
							   });
							}
						}
					 });
					
					
				} else {
					errorbox(msginfo);
				}
			}
		}

		//操作制卡信息		
		function update() {
			$('#updateBtn').prop('disabled', true);
			var gridData = $('#MakeCardUpdateGrid').bootstrapTable('getData')
			if (gridData.length == 0) {
				errorbox("请选择业务信息 ");
			} else {
				saveFormDataWithParams('MakeCardUpdateForm','<c:url value="/application/beforeinsertApplicationbill.do"/>',gridData,function(data) {
				if (data.mfunc == 0) {
					if (data.success) { 
						successbox(data.msg);
						$('#MakeCardUpdateForm input[name="icid"]').val('');
						$('#MakeCardUpdateForm input[name="carno"]').val('');
						$('#MakeCardUpdateForm input[name="operatype"]').val('');
						$('#MakeCardUpdateForm input[name="usermemo"]').val('');
						$('#MakeCardUpdateForm input[name="upplanidlist"]').val('');
						$('#MakeCardUpdateForm input[name="upitemidlist"]').val('');
						$('#MakeCardUpdateGrid').bootstrapTable('removeAll');
					} else {
						errorbox(data.msg);
					}
				} else {
					if (data.mfunc == 3) { //为3时判断里面有一个禁止添加取样信息
						errorbox(data.msg);
					} else if (data.mfunc == 2) {//为2时用户选择是否添加取样信息
						dialogbox("请确认",data.msg,function(data) {
							if (data) {
								saveFormDataWithParams('MakeCardUpdateForm','<c:url value="/application/insertApplicationbill.do"/>',gridData,function(data) {
									if (data.success) {
										successbox(data.msg);
										$('#MakeCardUpdateForm input[name="icid"]').val('');
										$('#MakeCardUpdateForm input[name="carno"]').val('');
										$('#MakeCardUpdateForm input[name="operatype"]').val('');
										$('#MakeCardUpdateForm input[name="usermemo"]').val('');
										$('#MakeCardUpdateForm input[name="upplanidlist"]').val('');
										$('#MakeCardUpdateForm input[name="upitemidlist"]').val('');
										$('#MakeCardUpdateGrid').bootstrapTable('removeAll');
									} else {
										errorbox(data.msg);
									}
								});
							}
						});
					}
				}
					
					$('#MakecardInfoGrid').bootstrapTable('refresh',{url : "<c:url value='/application/queryPage.do'/>"});
				});
			}
			$('#updateBtn').prop('disabled', false);
		}

		/*根据卡号判断卡号车辆状态*/
		function queryInfoBycarno() {
			$.ajax({
				type : "post",
				url : '<c:url value="/bcommon/judgCarno.do"/>',
				dataType : "json",
				data : {
					cardid : $("#icid").val()
				},
				success : function(data) {
					if (data.success == true) {
						//卡和车辆状态正常，根据车号查询业务信息
						$("#carnos").val(data.rows[0].carno);
						$("#cardstate").val(data.rows[0].validflag)
						validForm('MakeCardDetailForm');
					} else {
						//如果车辆或者卡有问题，系统提示
						errorbox(data.msg);
					}
				}
			});
		}

		function paramFormat() {
			var carno = $("#carnos").val();
			var operatype = $("#operatype").val();
			if (operatype == '') {
				operatype = $("#operatypes").val();
			}
			return 'carno=' + carno + '&operatype=' + operatype;
		}

		$('#MakecardInfoGrid').bootstrapTable({onDblClickRow : function(row, $element) {
				layer.open({
							type : 2,
							title : '信息详情',
							maxmin : true, //开启最大化最小化按钮
							area : [ '90%', '90%' ],
							content : '<c:url value="/bcommon/showdetail.do?matchid="/>'+ row.matchid //注意，如果str是object，那么需要字符拼接。
				});
			}
		});

		var carnoLayer;
		var operaflag=1;
		function checkCarno(types) { //types值为insert时为添加，为update时为修改
			var carno;
			var cardstate;
			var planidlist;
			var itemidlist;
			var operatype='';
			
			if (types == 'insert') {
				carno = $("#carnos").val();
				cardstate = $("#cardstate").val();
				operaflag = 0;
				planidlist=$("#planidlist").val();
				itemidlist=$("#itemidlist").val();
				if($("#operatype").val()==null){
					operatype='';
				}else{
					operatype=$("#operatype").val();
				}
			} else {
				carno = $("#upcarno").val();
				cardstate = 3;
				operaflag = 1;
			}
			carnoLayer = layer.open({
						type : 2,
						title : '制卡信息选择',
						maxmin : true, //开启最大化最小化按钮
						area : [ '90%', '90%' ],
						//content : '<c:url value="/application/applicationinfo.do?carno="/>'+ carno + "&cardstate=" + cardstate//注意，如果str是object，那么需要字符拼接。
						content : '<c:url value="/view/commonpage/applicationinfo.jsp?carno="/>'+
								carno + "&cardstate=" + cardstate+"&itemidlist="+itemidlist
								+"&planidlist="+planidlist+"&operatype="+operatype
			});
		}

		function takeBack(carno, operatype, planid, saleitemid) {
			$.ajax({
				type : "post",
				url : '<c:url value="/application/queryUncomplete.do"/>',
				dataType : "json",
				data : {carno : carno},
				success : function(data) {
					if(data.success){
						if(data.mfunc==0){
							getinfofrom(carno, operatype, planid, saleitemid);
						}else if(data.mfunc>0){
							dialogbox("请确认",data.msg,function(data) {
								if (data) {
									getinfofrom(carno, operatype, planid, saleitemid);
								}
							});
						}
					}else{
						errorbox(data.msg);
					}
					
				}
			});
			
			
			
			
		}
		function getinfofrom(carno, operatype, planid, saleitemid){
			$.ajax({
				type : "post",
				url : '<c:url value="/application/queryRfidBycarno.do?carno="/>'+ carno,
				dataType : "json",
				success : function(data) {
					var rfid=$('#MakeCardDetailForm input[name="rfidid"]').val();
					var getrfid = data.data;
					if(getrfid !='' && getrfid!=null ){
						if(rfid!='' && rfid!=null && rfid!=getrfid){
							errorbox("车辆已经发放RFID卡，无需重新发放");
						}
						$('#MakeCardDetailForm input[name="rfidid"]').val(data.data);
						$("#rfidflag").val(1);
					}	
					
					if (operaflag == 0) {//标记是0表示添加
						$('#carnos').val(carno);
						var planidls = $('#planidlist').val();
						var itemls = $('#itemidlist').val();
						$('#planidlist').val(planid);
						$('#itemidlist').val(saleitemid);
					} else {//否则表示为修改
						var upplanidls = $('#upplanidlist').val();
						var upitemls = $('#upitemidlist').val();
					}
					loadComboxData('operatype','<c:url value="/bcommon/queryOperatype.do?operatype="/>'+ operatype);
					$.ajax({
							type : "post",
							url : '<c:url value="/bcommon/queryModelcontent.do?operatype="/>'+ operatype+"&flag=1",
							dataType : "json",
							success : function(data) {
								var cs = JSON.parse(data.msg);
								if (operaflag == 0) {//标记是0表示添加
									var planids = $('#planidlist').val();
									var itemids = $('#itemidlist').val();
									$('#MakeCardDetailGrid').bootstrapTable('destroy').bootstrapTable({columns:cs}).bootstrapTable('refresh',{url : "<c:url value='/application/queryAppBypitemid.do?planid='/>"+ planids+ "&saleitemid="+ itemids});
								} else {//否则表示为修改
									var upplanids = $('#upplanidlist').val();
									var upitemids = $('#upitemidlist').val();						
									$('#MakeCardUpdateGrid').bootstrapTable('destroy').bootstrapTable({columns:cs}).bootstrapTable('refresh',{url : "<c:url value='/application/queryAllAppBypitemid.do?planid='/>"+ upplanids+ "&saleitemid="+ upitemids});
								}
								
							}
					});
					validForm('MakeCardDetailForm');
				}
		    });
		}

		function closeCheckCarno() {
			layer.close(carnoLayer);
		}
	</script>
</body>
</t:html>