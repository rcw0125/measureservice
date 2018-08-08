<%@ page contentType="text/html;charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="common.jsp" flush="true"/>
</head>

<body class="section container-fluid" style="padding-top:20px;">
	<div class="row-fluid">
		<div class="col-sm-12">
			<form id="queryform">
				<div class="row" style="padding-left: 5px; padding-right: 5px;">

					<div class="col-sm-3">
						<div class="input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">车 号</span> <input
								type="text" class="form-control" placeholder="车号" id="carno"
								name="carno" aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-3">
						<div class="input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">供 货</span> <input
								type="text" class="form-control" placeholder="供货"
								id="sourcename" name="sourcename"
								aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-3">
						<div class="input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">物&nbsp;&nbsp;流&nbsp;号</span>
							<input type="text" class="form-control" placeholder="物流号"
								id="matchid" name="matchid" aria-describedby="sizing-addon3" >
						</div>
					</div>

				</div>

				<div class="row" style="padding-left: 5px; padding-right: 5px;">
					<div class="col-sm-3">
						<div class="input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">货 名</span> <input
								type="text" class="form-control" placeholder="货名"
								id="materialname" name="materialname"
								aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-3">
						<div class="input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">收 货</span> <input
								type="text" class="form-control" placeholder="收货"
								id="targetname" name="targetname"
								aria-describedby="sizing-addon3">
						</div>
					</div>
					<div class="col-sm-3">
						<div class="input-group input-group-sm">
							<span class="input-group-addon" id="sizing-addon3">业&nbsp;&nbsp;务&nbsp;号</span>
							<input type="text" class="form-control" placeholder="业务号"
								id="taskcode" name="taskcode" aria-describedby="sizing-addon3">
						</div>
					</div>

					<div class="col-sm-3 btn-group-sm" style="padding-top: 5px;">
						<button id="query" type="button" class="btn btn-info">
							<span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;查询
						</button>

					</div>
				</div>
			</form>


			<div class="modal fade" id="BaseInfoWindow" tabindex="-1"
				role="dialog" aria-labelledby="BaseInfoWindowLabel"
				aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title">异常修改</h4>

						</div>
						<div class="modal-body">
							<form id="exceptionForm">
								 <input
									type="hidden" id="id" name="id" value="0" />
								<div class="row">
									<div class="col-sm-6">
										<div class="form-group input-group" style="margin: 2px">

											<span class="input-group-addon" style="width: 100px">业务类型</span><input
												name="operatype" type="text" class="form-control"
												aria-describedby="basic-addon1" readonly="readonly">
										</div>
									</div>
									<div class="col-sm-6">
										<div class="form-group input-group" style="margin: 2px">
											<span class="input-group-addon" style="width: 100px">车&nbsp;&nbsp;&nbsp;&nbsp;号</span><input
												name="carno" type="text" placeholder="必选"
												class="form-control" aria-label="..." readonly="readonly">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-6">
										<div class="form-group input-group" style="margin: 2px">
											<span class="input-group-addon" style="width: 100px">物流号</span><input
												name="applicationno" type="text" class="form-control"
												aria-describedby="basic-addon1" readonly="readonly">

										</div>
									</div>

									<div class="col-sm-6">
										<div class="form-group input-group" style="margin: 2px">
											<span class="input-group-addon" style="width: 100px">货名</span><input
												name="materialname" type="text" class="form-control"
												aria-describedby="basic-addon1" readonly="readonly">
										</div>
									</div>

								</div>

								<!--  <div class="row">
									<div class="col-sm-6">
									  <div class="form-group input-group" style="margin: 2px">
											<span class="input-group-addon" style="width: 100px">货名</span><input
												name="materialname" type="text" class="form-control"
												aria-describedby="basic-addon1" readonly="readonly">
										</div>
									</div>
									<div class="col-sm-6">
									   <div class="form-group input-group" style="margin: 2px">
											
											<span class="input-group-addon" style="width: 100px" >规格;</span><input
												name="materialspec" type="text" class="form-control"
												aria-describedby="basic-addon1" readonly="readonly">
										</div>
									</div>
									</div> -->
								<div class="row">
									<div class="col-sm-6">
										<div class="form-group input-group" style="margin: 2px">
											<span class="input-group-addon" style="width: 100px">供货</span><input
												name="sourcename" type="text" class="form-control class="
												text-nowrap""
												aria-describedby="basic-addon1"
												readonly="readonly">

										</div>
									</div>
									<div class="col-sm-6">
										<div class="form-group input-group" style="margin: 2px">

											<span class="input-group-addon" style="width: 100px">收货</span><input
												name="targetname" type="text" class="form-control"
												aria-describedby="basic-addon1" readonly="readonly">

										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-6">
										<div class="form-group input-group" style="margin: 2px">
											<span class="input-group-addon" style="width: 100px">计划量/t</span><input
												name="snumber" type="text" class="form-control"
												aria-describedby="basic-addon1" readonly="readonly">
										</div>
									</div>
									<div class="col-sm-6">
										<div class="form-group input-group" style="margin: 2px">

											<span class="input-group-addon" style="width: 100px">支数/支</span><input
												name="suttleapp" type="text" class="form-control"
												aria-describedby="basic-addon1" readonly="readonly">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-6">
										<div class="form-group input-group" style="margin: 2px">
											<span class="input-group-addon" style="width: 100px">供方毛重/t</span><input
												name="grossb" type="text" class="form-control"
												aria-describedby="basic-addon1" readonly="readonly">

										</div>
									</div>
									<div class="col-sm-6">

										<div class="form-group input-group" style="margin: 2px">

											<span class="input-group-addon" style="width: 100px">供方皮重/t</span><input
												name="tareb" type="text" class="form-control"
												aria-describedby="basic-addon1" readonly="readonly">

										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12">
										<div class="form-group input-group" style="margin: 2px">
											<span class="input-group-addon">供方净重/t&nbsp;&nbsp;</span><input
												name="suttleb" type="text" class="form-control"
												aria-describedby="basic-addon1" readonly="readonly">

										</div>
									</div>

								</div>
								<div class="row">
									<div class="col-sm-6">
										<div class="form-group input-group" style="margin: 2px">
											<span class="input-group-addon" style="width: 100px">毛&nbsp;&nbsp;&nbsp;重/t</span><input
												name="gross" id="gross"  type="text" class="form-control"
												aria-describedby="basic-addon1">
										</div>
									</div>
									<div class="col-sm-6">
										<div class="form-group input-group" style="margin: 2px">
											<span class="input-group-addon" style="width: 100px">皮&nbsp;&nbsp;&nbsp;重/t</span><input
												id="tare"  name="tare" type="text" class="form-control"
												aria-describedby="basic-addon1">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-6">
										<div class="form-group input-group" style="margin: 2px">
											<span class="input-group-addon" style="width: 100px">毛重时间</span><input
												name="grosstime" id="grosstime" type="text" class="form-control"
												aria-describedby="basic-addon1">
										</div>
									</div>
									<div class="col-sm-6">
										<div class="form-group input-group" style="margin: 2px">
											<span class="input-group-addon" style="width: 100px">皮重时间</span><input
												name="taretime" id="taretime" type="text" class="form-control"
												aria-describedby="basic-addon1">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-6">
										<div class="form-group input-group" style="margin: 2px">
											<span class="input-group-addon" style="width: 100px">毛重衡器</span>
											 <input name="grossweigh" id="grossweigh" type="hidden"
												class="form-control"  value="dfasdasdasdas">
											<select id="grossweighid" name="grossweighid"
												placeholder="毛重衡器" class="form-control  select2-data-ajax"
												ajax-url="<c:url value='/measure/queryEquipment.do'/>">
											</select>
										</div>
									</div>
									<div class="col-sm-6">
										<div class="form-group input-group" style="margin: 2px">
											<span class="input-group-addon" style="width: 100px">皮重衡器</span>
											<input name="tareweigh" id="tareweigh" type="hidden" class="form-control"
												> 
											<select id="tareweighid" name="tareweighid"
												placeholder="皮重衡器" class="form-control  select2-data-ajax"
												ajax-url="<c:url value='/measure/queryEquipment.do'/>">
											</select>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-6">
										<div class="form-group input-group" style="margin: 2px">
											<span class="input-group-addon" style="width: 100px">毛重计量员</span><input
												name="grossoperaname" type="text" class="form-control"
												aria-describedby="basic-addon1">
										</div>
									</div>
									<div class="col-sm-6">
										<div class="form-group input-group" style="margin: 2px">
											<span class="input-group-addon" style="width: 100px">皮重计量员</span><input
												name="tareoperaname" type="text" class="form-control"
												aria-describedby="basic-addon1">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12">
										<div class="input-group" style="margin: 2px">
											<span class="input-group-addon" >备注详情&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
											<input name="usermemo" id="usermemo" class="form-control" placeholder="选填"
												rows="1"></input>
										</div>
									</div>

								</div>
								<span class="help-block" id="errormsg"></span>
							</form>
						</div>
						<div class="modal-footer">
						    <button type="button" class="btn btn-success"
								id="baseConfigSaveBtn">保存</button>
							<button type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button>
							
						</div>
					</div>
				</div>
			</div>
			<div class="table-responsive" style="padding-top:5px;padding-left:5px;padding-right:5px;">
				<table id="baseConfigGrid" data-toggle="table" id="table"
					data-row-style="rowStyle" data-pagination="true"
					data-page-list="[5, 10, 15, 20, ALL]" data-page-size="10"
					data-side-pagination="server"
					>
					<thead>
						<tr>
							<th data-field="matchid" data-halign="center"
								data-visible="false"></th>
							<th data-field="flag" data-halign="center" data-visible="false"></th>
							<th data-field="msg" data-halign="center" data-visible="false"></th>
							<th data-field="applicationno" data-halign="center"
								data-searchable="true">物流号</th>
							<th data-field="carno" data-halign="center"
								data-searchable="true" class="text-nowrap">车号</th>
								<th data-field="taskcode" data-halign="center"
									class="text-nowrap" data-searchable="true">业务号</th>
								<th data-field="materialname" data-halign="center"
									data-searchable="true" class="text-nowrap">货名</th>
								<th data-field="sourcename" data-halign="center"
									data-searchable="true" class="text-nowrap">供货</th>
								<th data-field="targetname" data-halign="center"
									data-searchable="true" class="text-nowrap">收货</th>
								<th data-field="gross" data-halign="center"
									data-searchable="true">毛重/t</th>
								<th data-field="grossweigh" data-halign="center"
									class="text-nowrap" data-searchable="true">毛重衡器</th>
								<th data-field="tare" data-halign="center"
									data-searchable="true">皮重/t</th>
								<th data-field="tareweigh" data-halign="center"
									class="text-nowrap" data-searchable="true">皮重衡器</th>
								<th data-field="deduction" data-halign="center"
									data-searchable="true">扣重/t</th>
								<th data-field="suttle" data-halign="center"
									data-searchable="true">净重/t</th>
								<th data-field="suttleweigh" data-halign="center"
									class="text-nowrap" data-searchable="true">净重衡器</th>
									<th data-field="mtypes" data-halign="center"
									class="text-nowrap" data-searchable="true">计量类型</th>
								<th data-width="70px" data-align="center" data-valign="middle"
									data-formatter="operateFormatter" data-events="operateEvents">操作</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		jQuery(document).ready(function($) {
			$('#exceptionForm').bootstrapValidator({
				  fields: {
		                gross: {
		                	container:"#errormsg",
		                    validators: {
			                    stringLength: {
			                        min: 1,
			                        max: 7,
			                        message: '毛重长度必须在1到7位之间'
			                    },
			                    regexp: {
			                        regexp:/^[0-9]+(\.[0-9]+)?$/ ,
			                        message: '重量只能填写数字'
			                    }
		                    }
		                },
		                tare: {
		                	container:"#errormsg",
		                    validators: {
			                    stringLength: {
			                        min: 1,
			                        max: 7,
			                        message: '皮重长度必须在1到8位之间'
			                    },
			                    regexp: {
			                        regexp:/^[0-9]+(\.[0-9]+)?$/ ,
			                        message: '重量只能填写数字'
			                    }
		                    }
		                },
		                usermemo: {
		                	container:"#errormsg",
		                    validators: {
			                    stringLength: {
			                        min: 1,
			                        max: 100,
			                        message: '备注信息请在100个字以内'
			                    },notEmpty: {
			                        message: '请填写备注信息！'
			                    }
		                    }
		                }
		             
		            },
				excluded : [ ':disabled',':hidden',':not(:visible)' ],
				onSuccess : function() {
			
					if(($("#gross").val()*0.1>0)&&($("#gross").val()*0.1<$("#tare").val()*0.1)){
						
						toastMessage("error","错误","毛重必须大于皮重！");
					}else{
						$('#exceptionForm').ajaxSubmit({ 
							type : 'post',
							url : '<c:url value="/measure/saveException.do"/>',
							success : function(data) {
							if (data.success) {
								toastMessage("success","提示",data.msg);
								$('#BaseInfoWindow').modal('toggle');
								queryinfo();
							} else {
								toastMessage("error","错误",data.msg);
							}
                             }
						});
					}
				
							}
						});

			$('#baseConfigSaveBtn').on('click',function() {
				$('#exceptionForm').bootstrapValidator('validate');
			});

			$('#BaseInfoWindow').on('shown.bs.modal',function() {
					var id_v = $($('#'+ $(this).attr("id")+ ' form :input[name="id"]'));
					$.ajax({
							type : "post",
							url : '<c:url value="/measure/queryInfo.do"/>?applicationno='+ id_v.val(),
							dataType : "json",
							success : function(data) {
									loadFormData('exceptionForm',data);
							}
						});
					});

			$('#addBaseConfigBtn').on('click', function() {
				openBaseConfigWindow(-1);
			});

			queryinfo();
			$("#query").click(function() {$('#baseConfigGrid').bootstrapTable('refresh',{url : "<c:url value='/measure/queryException.do'/>",
				query : $('#queryform').serializeJson()
				});
			})
			
			 $(function () {
			        $('#grosstime').datetimepicker({format: 'YYYY-MM-DD HH:mm:ss',locale:'zh-cn'});
			        $('#taretime').datetimepicker({format: 'YYYY-MM-DD HH:mm:ss',locale:'zh-cn'});
			     
			    });
			
			
			 
			 $("#grossweighid").change(function(){
				
				  $("#grossweigh").val($('#grossweighid option:selected').text());
				});
			 $('#tareweighid').change(function (){
					
					$('#tareweigh').val($('#tareweighid option:selected').text());
			
				});
			});

		function operateFormatter(value, row, index) {
			
			if(row.suttle>0){
				return [].join('');
			}else{
				return [ '<div class="pull-right">',
					'<a class="edit" href="javascript:void(0)" title="修改">',
					'<i class="glyphicon glyphicon-pencil"></i>', '</a>　',
					'</div>' ].join('');
			}
		}
		window.operateEvents = {
			'click .edit' : function(e, value, row) {
				if (row.flag == 0) {
					alert(row.msg);
				} else {
					openBaseConfigWindow(row.matchid);

				}
			},

		};

		function openBaseConfigWindow(id_vv) {
			$($('#BaseInfoWindow form :input[name="id"]')).val(id_vv);
			$('#BaseInfoWindow').modal('show');
		}
		function queryinfo(){
	
			$('#baseConfigGrid').bootstrapTable('refresh',{url : "<c:url value='/measure/queryException.do'/>",
				query : $('#queryform').serializeJson()
			});
			
		}
	</script>
</body>
</html>