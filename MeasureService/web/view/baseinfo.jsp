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
			<div class="panel panel-danger" style="width:100%;">
				<div class="panel-heading">个性设置</div>
				<div class="panel-body">
					<div class="container-fluid" style="padding:0px;">
						<div class="row-fluid">
							<div class="col-sm-2" style="padding-left:0px;"> 
								<div class="form-group input-group">
									<input name="memoryMointor" type="text" class="form-control" aria-label="..." readonly="readonly" value="${baseConfig.memoryMointor}">
									<div class="input-group-btn">
										<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">内存监控&nbsp;<span class="caret"></span></button>
										<ul class="dropdown-menu dropdown-menu-right">
											<li><a onclick="updateBaseConfig('memoryMointor','启用')">启用</a></li>
											<li><a onclick="updateBaseConfig('memoryMointor','禁用')">禁用</a></li>
										</ul>
									</div>
								</div>
							</div>
							<div class="col-sm-2" style="padding-left:0px;">
								<div class="form-group input-group">
									<input name="cpuMointor" type="text" class="form-control" aria-label="..." readonly="readonly" value="${baseConfig.cpuMointor}">
									<div class="input-group-btn">
										<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">CPU监控&nbsp;<span class="caret"></span></button>
										<ul class="dropdown-menu dropdown-menu-right">
											<li><a onclick="updateBaseConfig('cpuMointor','启用')">启用</a></li>
											<li><a onclick="updateBaseConfig('cpuMointor','启用')">禁用</a></li>
										</ul>
									</div>
								</div>
							</div>
							<div class="col-sm-2" style="padding-left:0px;">
								<div class="form-group input-group">
									<input name="weightMointor" type="text" class="form-control" aria-label="..." readonly="readonly" value="${baseConfig.weightMointor}">
									<div class="input-group-btn">
										<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">称体监控&nbsp;<span class="caret"></span></button>
										<ul class="dropdown-menu dropdown-menu-right">
											<li><a onclick="updateBaseConfig('weightMointor','启用')">启用</a></li>
											<li><a onclick="updateBaseConfig('weightMointor','启用')">禁用</a></li>
										</ul>
									</div>
								</div>
							</div>
							<div class="col-sm-2" style="padding-left:0px;">
								<div class="form-group input-group">
									<input name="alertMointor" type="text" class="form-control" aria-label="..." readonly="readonly" value="${baseConfig.alertMointor}">
									<div class="input-group-btn">
										<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">系统报警&nbsp;<span class="caret"></span></button>
										<ul class="dropdown-menu dropdown-menu-right">
											<li><a onclick="updateBaseConfig('alertMointor','启用')">启用</a></li>
											<li><a onclick="updateBaseConfig('alertMointor','启用')">禁用</a></li>
										</ul>
									</div>
								</div>
							</div>
							<div class="col-sm-2" style="padding-left:0px;">
								<div class="form-group input-group">
									<input type="text" id="adminEmail" name="adminEmail" class="form-control" aria-describedby="basic-addon1" placeholder="管理员邮箱" value="${baseConfig.adminEmail}">
									<span class="input-group-btn">
								        <button class="btn btn-default" type="button" onclick="updateBaseConfig('adminEmail',$('#adminEmail').val())">管理邮箱</button>
									</span>
								</div>	
							</div>
							<div class="col-sm-2" style="padding-left:0px;padding-right:0px;">
								<div class="form-group input-group">
									<input type="text" class="form-control" id="clusters" name="clusters" aria-describedby="basic-addon1" placeholder="负载集群" value="${baseConfig.clusters}">
									<span class="input-group-btn">
								        <button class="btn btn-default" type="button" onclick="updateBaseConfig('clusters',$('#clusters').val())">负载集群</button>
									</span>
								</div>	
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row-fluid">				
		<div class="col-sm-12">
			<div class="panel panel-warning" style="width:100%;">
				<div class="panel-heading">硬件控制</div>
				<div class="panel-body">
					<div>
						<ul class="nav nav-tabs" role="tablist">
							<li role="presentation" class="active"><a href="#infraredconfig" aria-controls="infraredconfig" role="tab" data-toggle="tab">红外对射</a></li>
							<li role="presentation"><a href="#automeasureconfig" aria-controls="automeasureconfig" role="tab" data-toggle="tab">自助计量</a></li>
						</ul>
						<div class="tab-content">
							<div role="tabpanel" class="tab-pane active" id="infraredconfig">
								<div class="table-responsive">
									<table class="table table-bordered">
										<thead>
											<tr>
												<th align="center">控制分类</th>
												<th align="center">控制类型</th>
												<th width="260" align="center">详情</th>
											</tr>
										</thead>
										<tbody>
											<tr class="success">
												<td rowspan="3" align="center" style="text-align:center;">强制启用</td>
												<td align="center">业务类型</td>
												<td style="margin:0px;padding:0px;" align="center">
													<table id="hwywlxqzqyTable" data-toggle="table" data-url="<c:url value='/deviceconfig/query.do'/>?deviceName=红外对射&ctrlType=业务类型&ctrlGoal=强制启用">
									                    <thead>
										                    <tr>
										                    	<th data-field="id" data-halign="center" data-visible="false"></th>
										                        <th data-field="configDetail" data-formatter="clientconfigFormatter" data-events="clientconfigEvents" data-halign="center">
																	<button type="button" class="btn btn-info" data-toggle="modal" onclick="newHardwareConfig('hwywlxqzqyTable','红外对射','业务类型','强制启用')">新增</button>
																</th>
										                    </tr>
									                    </thead>
									                </table>
												</td>
											</tr>
											<tr class="success">
												<td align="center">物料名称</td>
												<td style="margin:0px;padding:0px;" align="center">
													<table id="hwwlmcqzqyTable" data-toggle="table" data-url="<c:url value='/deviceconfig/query.do'/>?deviceName=红外对射&ctrlType=物料名称&ctrlGoal=强制启用">
									                    <thead>
										                    <tr>
										                    	<th data-field="id" data-halign="center" data-visible="false"></th>
										                        <th data-field="configDetail" data-formatter="clientconfigFormatter" data-events="clientconfigEvents" data-halign="center">
																	<button type="button" class="btn btn-warning" data-toggle="modal" onclick="newHardwareConfig('hwwlmcqzqyTable','红外对射','物料名称','强制启用')">新增</button>
																</th>
										                    </tr>
									                    </thead>
									                </table>
												</td>
											</tr>
											<tr class="success">
												<td align="center">物料大类</td>
												<td style="margin:0px;padding:0px;" align="center">
													<table id="hwwldlqzqyTable" data-toggle="table" data-url="<c:url value='/deviceconfig/query.do'/>?deviceName=红外对射&ctrlType=物料大类&ctrlGoal=强制启用">
									                    <thead>
										                    <tr>
										                    	<th data-field="id" data-halign="center" data-visible="false"></th>
										                        <th data-field="configDetail" data-formatter="clientconfigFormatter" data-events="clientconfigEvents" data-halign="center">
																	<button type="button" class="btn btn-success" data-toggle="modal" onclick="newHardwareConfig('hwwldlqzqyTable','红外对射','物料大类','强制启用')">新增</button>
																</th>
										                    </tr>
									                    </thead>
									                </table>
												</td>
											</tr>
											<tr class="danger">
												<td rowspan="3" align="center" style="text-align:center;">强制禁用</td>
												<td align="center">业务类型</td>
												<td style="margin:0px;padding:0px;" align="center">
													<table id="hwywlxqzjyTable" data-toggle="table" data-url="<c:url value='/deviceconfig/query.do'/>?deviceName=红外对射&ctrlType=业务类型&ctrlGoal=强制禁用">
									                    <thead>
										                    <tr>
										                    	<th data-field="id" data-halign="center" data-visible="false"></th>
										                        <th data-field="configDetail" data-formatter="clientconfigFormatter" data-events="clientconfigEvents" data-halign="center">
																	<button type="button" class="btn btn-info" data-toggle="modal" onclick="newHardwareConfig('hwywlxqzjyTable','红外对射','业务类型','强制禁用')">新增</button>
																</th>
										                    </tr>
									                    </thead>
									                </table>
												</td>
											</tr>
											<tr class="danger">
												<td align="center">物料名称</td>
												<td style="margin:0px;padding:0px;" align="center">
													<table id="hwwlmcqzjyTable" data-toggle="table" data-url="<c:url value='/deviceconfig/query.do'/>?deviceName=红外对射&ctrlType=物料名称&ctrlGoal=强制禁用">
									                    <thead>
										                    <tr>
										                    	<th data-field="id" data-halign="center" data-visible="false"></th>
										                        <th data-field="configDetail" data-formatter="clientconfigFormatter" data-events="clientconfigEvents" data-halign="center">
																	<button type="button" class="btn btn-warning" data-toggle="modal" onclick="newHardwareConfig('hwwlmcqzjyTable','红外对射','物料名称','强制禁用')">新增</button>
																</th>
										                    </tr>
									                    </thead>
									                </table>
												</td>
											</tr>
											<tr class="danger">
												<td align="center">物料大类</td>
												<td style="margin:0px;padding:0px;" align="center">
													<table id="hwwldlqzjyTable" data-toggle="table" data-url="<c:url value='/deviceconfig/query.do'/>?deviceName=红外对射&ctrlType=物料大类&ctrlGoal=强制禁用">
									                    <thead>
										                    <tr>
										                    	<th data-field="id" data-halign="center" data-visible="false"></th>
										                        <th data-field="configDetail" data-formatter="clientconfigFormatter" data-events="clientconfigEvents" data-halign="center">
																	<button type="button" class="btn btn-success" data-toggle="modal" onclick="newHardwareConfig('hwwldlqzjyTable','红外对射','物料大类','强制禁用')">新增</button>
																</th>
										                    </tr>
									                    </thead>
									                </table>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
							<div role="tabpanel" class="tab-pane" id="automeasureconfig">
								<div class="table-responsive">
									<table class="table table-bordered">
										<thead>
											<tr>
												<th align="center">配置类别</th>
												<th align="center">计量类别</th>
												<th align="center">是否强制</th>
												<th width="260" align="center">详情</th>
											</tr>
										</thead>
										<tbody>
											<tr class="warning">
												<td rowspan="4" align="center" style="text-align:center;">业务类型</td>
												<td rowspan="2" align="center">自助计量</td>
												<td align="center">是</td>
												<td style="margin:0px;padding:0px;" align="center">
													<table id="zdywqzzzTable" data-toggle="table" data-url="<c:url value='/deviceconfig/query.do'/>?deviceName=终端机&ctrlType=业务类型&ctrlGoal=强制自助">
									                    <thead>
										                    <tr>
										                    	<th data-field="id" data-halign="center" data-visible="false"></th>
										                        <th data-field="configDetail" data-formatter="clientconfigFormatter" data-events="clientconfigEvents" data-halign="center">
																	<button type="button" class="btn btn-info" data-toggle="modal" onclick="newHardwareConfig('zdywqzzzTable','终端机','业务类型','强制自助')">新增</button>
																</th>
										                    </tr>
									                    </thead>
									                </table>
												</td>
											</tr>
											<tr class="warning">
												<td align="center">否</td>
												<td style="margin:0px;padding:0px;" align="center">
													<table id="zdywzzjlTable" data-toggle="table" data-url="<c:url value='/deviceconfig/query.do'/>?deviceName=终端机&ctrlType=业务类型&ctrlGoal=自助计量">
									                    <thead>
										                    <tr>
										                    	<th data-field="id" data-halign="center" data-visible="false"></th>
										                        <th data-field="configDetail" data-formatter="clientconfigFormatter" data-events="clientconfigEvents" data-halign="center">
																	<button type="button" class="btn btn-danger" data-toggle="modal" onclick="newHardwareConfig('zdywzzjlTable','终端机','业务类型','自助计量')">新增</button>
																</th>
										                    </tr>
									                    </thead>
									                </table>
												</td>
											</tr>
											<tr class="warning">
												<td rowspan="2" align="center">远程计量</td>
												<td align="center">是</td>
												<td style="margin:0px;padding:0px;" align="center">
													<table id="zdywqzycTable" data-toggle="table" data-url="<c:url value='/deviceconfig/query.do'/>?deviceName=终端机&ctrlType=业务类型&ctrlGoal=强制远程">
									                    <thead>
										                    <tr>
										                    	<th data-field="id" data-halign="center" data-visible="false"></th>
										                        <th data-field="configDetail" data-formatter="clientconfigFormatter" data-events="clientconfigEvents" data-halign="center">
																	<button type="button" class="btn btn-warning" data-toggle="modal" onclick="newHardwareConfig('zdywqzycTable','终端机','业务类型','强制远程')">新增</button>
																</th>
										                    </tr>
									                    </thead>
									                </table>
												</td>
											</tr>												
											<tr class="warning">
												<td align="center">否</td>
												<td style="margin:0px;padding:0px;" align="center">
													<table id="zdywycjlTable" data-toggle="table" data-url="<c:url value='/deviceconfig/query.do'/>?deviceName=终端机&ctrlType=业务类型&ctrlGoal=远程计量">
									                    <thead>
										                    <tr>
										                    	<th data-field="id" data-halign="center" data-visible="false"></th>
										                        <th data-field="configDetail" data-formatter="clientconfigFormatter" data-events="clientconfigEvents" data-halign="center">
																	<button type="button" class="btn btn-success" data-toggle="modal" onclick="newHardwareConfig('zdywycjlTable','终端机','业务类型','远程计量')">新增</button>
																</th>
										                    </tr>
									                    </thead>
									                </table>
												</td>
											</tr>
											<tr class="success">
												<td rowspan="4" align="center" style="text-align:center;">物料大类</td>
												<td rowspan="2" align="center">自助计量</td>
												<td align="center">是</td>
												<td style="margin:0px;padding:0px;" align="center">
													<table id="zdwldlqzzzTable" data-toggle="table" data-url="<c:url value='/deviceconfig/query.do'/>?deviceName=终端机&ctrlType=物料大类&ctrlGoal=强制自助">
									                    <thead>
										                    <tr>
										                    	<th data-field="id" data-halign="center" data-visible="false"></th>
										                        <th data-field="configDetail" data-formatter="clientconfigFormatter" data-events="clientconfigEvents" data-halign="center">
																	<button type="button" class="btn btn-info" data-toggle="modal" onclick="newHardwareConfig('zdwldlqzzzTable','终端机','物料大类','强制自助')">新增</button>
																</th>
										                    </tr>
									                    </thead>
									                </table>
												</td>
											</tr>
											<tr class="success">
												<td align="center">否</td>
												<td style="margin:0px;padding:0px;" align="center">
													<table id="zdwldlzzjlTable" data-toggle="table" data-url="<c:url value='/deviceconfig/query.do'/>?deviceName=终端机&ctrlType=物料大类&ctrlGoal=自助计量">
									                    <thead>
										                    <tr>
										                    	<th data-field="id" data-halign="center" data-visible="false"></th>
										                        <th data-field="configDetail" data-formatter="clientconfigFormatter" data-events="clientconfigEvents" data-halign="center">
																	<button type="button" class="btn btn-danger" data-toggle="modal" onclick="newHardwareConfig('zdwldlzzjlTable','终端机','物料大类','自助计量')">新增</button>
																</th>
										                    </tr>
									                    </thead>
									                </table>
												</td>
											</tr>
											<tr class="success">
												<td rowspan="2" align="center">远程计量</td>
												<td align="center">是</td>
												<td style="margin:0px;padding:0px;" align="center">
													<table id="zdwldlqzycTable" data-toggle="table" data-url="<c:url value='/deviceconfig/query.do'/>?deviceName=终端机&ctrlType=物料大类&ctrlGoal=强制远程">
									                    <thead>
										                    <tr>
										                    	<th data-field="id" data-halign="center" data-visible="false"></th>
										                        <th data-field="configDetail" data-formatter="clientconfigFormatter" data-events="clientconfigEvents" data-halign="center">
																	<button type="button" class="btn btn-warning" data-toggle="modal" onclick="newHardwareConfig('zdwldlqzycTable','终端机','物料大类','强制远程')">新增</button>
																</th>
										                    </tr>
									                    </thead>
									                </table>
												</td>
											</tr>												
											<tr class="success">
												<td align="center">否</td>
												<td style="margin:0px;padding:0px;" align="center">
													<table id="zdwldlycjlTable" data-toggle="table" data-url="<c:url value='/deviceconfig/query.do'/>?deviceName=终端机&ctrlType=物料大类&ctrlGoal=远程计量">
									                    <thead>
										                    <tr>
										                    	<th data-field="id" data-halign="center" data-visible="false"></th>
										                        <th data-field="configDetail" data-formatter="clientconfigFormatter" data-events="clientconfigEvents" data-halign="center">
																	<button type="button" class="btn btn-success" data-toggle="modal" onclick="newHardwareConfig('zdwldlycjlTable','终端机','物料大类','远程计量')">新增</button>
																</th>
										                    </tr>
									                    </thead>
									                </table>
												</td>
											</tr>
											<tr class="danger">
												<td rowspan="4" align="center" style="text-align:center;">物料名称</td>
												<td rowspan="2" align="center">自助计量</td>
												<td align="center">是</td>
												<td style="margin:0px;padding:0px;" align="center">
													<table id="zdwlmcqzzzTable" data-toggle="table" data-url="<c:url value='/deviceconfig/query.do'/>?deviceName=终端机&ctrlType=物料名称&ctrlGoal=强制自助">
									                    <thead>
										                    <tr>
										                    	<th data-field="id" data-halign="center" data-visible="false"></th>
										                        <th data-field="configDetail" data-formatter="clientconfigFormatter" data-events="clientconfigEvents" data-halign="center">
																	<button type="button" class="btn btn-info" data-toggle="modal" onclick="newHardwareConfig('zdwlmcqzzzTable','终端机','物料名称','强制自助')">新增</button>
																</th>
										                    </tr>
									                    </thead>
									                </table>
												</td>
											</tr>
											<tr class="danger">
												<td align="center">否</td>
												<td style="margin:0px;padding:0px;" align="center">
													<table id="zdwlmczzjlTable" data-toggle="table" data-url="<c:url value='/deviceconfig/query.do'/>?deviceName=终端机&ctrlType=物料名称&ctrlGoal=自助计量">
									                    <thead>
										                    <tr>
										                    	<th data-field="id" data-halign="center" data-visible="false"></th>
										                        <th data-field="configDetail" data-formatter="clientconfigFormatter" data-events="clientconfigEvents" data-halign="center">
																	<button type="button" class="btn btn-danger" data-toggle="modal" onclick="newHardwareConfig('zdwlmczzjlTable','终端机','物料名称','自助计量')">新增</button>
																</th>
										                    </tr>
									                    </thead>
									                </table>
												</td>
											</tr>
											<tr class="danger">
												<td rowspan="2" align="center">远程计量</td>
												<td align="center">是</td>
												<td style="margin:0px;padding:0px;" align="center">
													<table id="zdwlmcqzycTable" data-toggle="table" data-url="<c:url value='/deviceconfig/query.do'/>?deviceName=终端机&ctrlType=物料名称&ctrlGoal=强制远程">
									                    <thead>
										                    <tr>
										                    	<th data-field="id" data-halign="center" data-visible="false"></th>
										                        <th data-field="configDetail" data-formatter="clientconfigFormatter" data-events="clientconfigEvents" data-halign="center">
																	<button type="button" class="btn btn-warning" data-toggle="modal" onclick="newHardwareConfig('zdwlmcqzycTable','终端机','物料名称','强制远程')">新增</button>
																</th>
										                    </tr>
									                    </thead>
									                </table>
												</td>
											</tr>												
											<tr class="danger">
												<td align="center">否</td>
												<td style="margin:0px;padding:0px;" align="center">
													<table id="zdwlmcycjlTable" data-toggle="table" data-url="<c:url value='/deviceconfig/query.do'/>?deviceName=终端机&ctrlType=物料名称&ctrlGoal=远程计量">
									                    <thead>
										                    <tr>
										                    	<th data-field="id" data-halign="center" data-visible="false"></th>
										                        <th data-field="configDetail" data-formatter="clientconfigFormatter" data-events="clientconfigEvents" data-halign="center">
																	<button type="button" class="btn btn-success" data-toggle="modal" onclick="newHardwareConfig('zdwlmcycjlTable','终端机','物料名称','远程计量')">新增</button>
																</th>
										                    </tr>
									                    </thead>
									                </table>
												</td>
											</tr>												
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal fade" id="materialClassWindow" tabindex="-1" role="dialog" aria-labelledby="materialClassSelectLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span></button>
							<h4 class="modal-title">请选择物料大类</h4>
						</div>
						<div class="modal-body">
							<table id="materialClassSelectGrid" data-toggle="table" 
														   data-url="<c:url value='/measure/getmaterialclass.do'/>" 
														   data-search="true"
														   data-pagination="true"
														   data-page-list="[10,30,50,100]"
														   data-row-style="rowStyle"
														   data-show-refresh="true">
								<thead>
									<tr>
										<th data-halign="center" data-checkbox="true"></th>
										<th data-field="FOLDERNAME" data-halign="center" data-searchable="true">物料大类</th>							
									</tr>
								</thead>
							</table>
						</div>
						<div class="modal-footer">
                                  <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
							<button type="button" class="btn btn-success" id="materialClassSelectSaveBtn">选择</button>
						</div>
					</div>
				</div>
			</div>
			<div class="modal fade" id="opterateTypeSelectWindow" tabindex="-1" role="dialog" aria-labelledby="opterateTypeSelectLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span></button>
							<h4 class="modal-title">请选择业务类型</h4>
						</div>
						<div class="modal-body">
							<table id="opterateTypeSelectGrid" data-toggle="table" 
														   data-url="<c:url value='/measure/getoperatename.do'/>" 
														   data-search="true"
														   data-pagination="true"
														   data-page-list="[10,30,50,100]"
														   data-row-style="rowStyle"
														   data-show-refresh="true">
								<thead>
									<tr>
										<th data-halign="center" data-checkbox="true"></th>
										<th data-field="OPERANAME" data-halign="center" data-searchable="true">业务类型名称</th>							
									</tr>
								</thead>
							</table>
						</div>
						<div class="modal-footer">
                                  <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
							<button type="button" class="btn btn-success" id="opterateTypeSelectSaveBtn">选择</button>
						</div>
					</div>
				</div>
			</div>
			<div class="modal fade" id="materialSelectWindow" tabindex="-1" role="dialog" aria-labelledby="materialSelectLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span></button>
							<h4 class="modal-title">请选择物料名称</h4>
						</div>
						<div class="modal-body">
							<table id="materialSelectGrid" data-toggle="table" 
														   data-url="<c:url value='/measure/getmaterialname.do'/>" 
														   data-search="true"
														   data-pagination="true"
														   data-page-list="[10,30,50,100]"
														   data-row-style="rowStyle"
														   data-show-refresh="true"
														   data-side-pagination="server">
								<thead>
									<tr>
										<th data-halign="center" data-checkbox="true"></th>
										<th data-field="MATERIALNAME" data-halign="center" data-searchable="true">物料名称</th>							
									</tr>
								</thead>
							</table>
						</div>
						<div class="modal-footer">
                                           <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
							<button type="button" class="btn btn-success" id="materialSelectSaveBtn">选择</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var deviceConfigTableid = '';
	    var deviceConfigDeviceName = '';
	    var deviceConfigCtrlType = '';
	    var deviceConfigCtrlGoal = '';
	    
		jQuery(document).ready(function($){
		    $('#materialSelectSaveBtn').on('click',function(){
	        	var materialnames = '';
	        	var selectedRows = $('#materialSelectGrid').bootstrapTable('getAllSelections');
	        	for(var i=0;i<selectedRows.length;i++){
	        		materialnames = materialnames + ',' + selectedRows[i].MATERIALNAME;
	        	}
	        	
	        	materialnames = materialnames.substr(1);
	        	saveConfigDetails('materialSelectWindow',materialnames);
	        });
	        
	   		$('#materialClassSelectSaveBtn').on('click',function(){
	        	var materialclasses = '';
	        	var selectedRows = $('#materialClassSelectGrid').bootstrapTable('getAllSelections');
	        	for(var i=0;i<selectedRows.length;i++){
	        		materialclasses = materialclasses + ',' + selectedRows[i].FOLDERNAME;
	        	}
	        	
	        	materialclasses = materialclasses.substr(1);
	        	saveConfigDetails('materialClassWindow',materialclasses);
			});
		            		 
	   		$('#opterateTypeSelectSaveBtn').on('click',function(){
	          	var configDetails = '';
	          	var selectedRows = $('#opterateTypeSelectGrid').bootstrapTable('getAllSelections');
	          	for(var i=0;i<selectedRows.length;i++){
	          		configDetails = configDetails + ',' + selectedRows[i].OPERANAME;
	          	}
	          	
	          	configDetails = configDetails.substr(1);
	          	saveConfigDetails('opterateTypeSelectWindow',configDetails);
			});
		});
		
		function updateBaseConfig(name,val){
			$.ajax({
	            type: "post",
	            url: '<c:url value="/baseconfig/update.do"/>',
	            dataType: "json",
	            data:{fieldName:name,fieldValue:val},
	            success: function(jsondata){
	            	if(jsondata.success == true){
	            		toastMessage("success","提示","操作成功！");
	            	}
	            }
            });
		}
		
		function saveConfigDetails(win_id,details_v){
        	$.ajax({
	            type: "post",
	            url: '<c:url value="/deviceconfig/save.do"/>',
	            dataType: "json",
	            data: {deviceName:deviceConfigDeviceName,ctrlType:deviceConfigCtrlType,ctrlGoal:deviceConfigCtrlGoal,details:details_v},
	            success: function(data){
	            	 if(data.success == true){
	            		 toastMessage("success","提示","操作成功！");
	            		 $('#' + win_id).modal('toggle');
	            		 $('#' + deviceConfigTableid).bootstrapTable('refresh');
	            	 }else{
	            		 toastMessage("error","错误",data.msg);
	            	 }
	            }
            });
        }
		
		window.clientconfigEvents = {
		        'click .remove': function (e, value, row) {
		        	var tableid = $($(this).parent().parent().parent().parent().parent()).attr('id');
		        	$.ajax({
			            type: "post",
			            url: '<c:url value="/deviceconfig/remove.do"/>',
			            dataType: "json",
			            data: {id:row.id},
			            success: function(data){
			            	 if(data.success == true){
			            		 toastMessage("success","提示","操作成功！");
			            		 $('#' + tableid).bootstrapTable('refresh');
			            	 }else{
			            		 toastMessage("error","错误","操作失败！");
			            	 }
			            }
		            });
		        }
		    };

	    function clientconfigFormatter(value, row, index) {
	        return [
	            '<div class="pull-left">',
	            value,
	            '</div>',
	            '<div class="pull-right">',
	            '<a class="remove" href="javascript:void(0)" title="移除">',
	            '<i class="glyphicon glyphicon-remove"></i>',
	            '</a>',
	            '</div>'
	        ].join('');
	    }
	    
	    function newHardwareConfig(tableid,deviceName,ctrlType,ctrlGoal){
	    	deviceConfigTableid = tableid;
            deviceConfigDeviceName = deviceName;
            deviceConfigCtrlType = ctrlType;
            deviceConfigCtrlGoal = ctrlGoal;
            
            if('业务类型' == ctrlType){
            	$('#opterateTypeSelectWindow').modal('show');
		    	$('#opterateTypeSelectGrid').bootstrapTable('refresh');
            }else if('物料大类' == ctrlType){
            	$('#materialClassWindow').modal('show');
		    	$('#materialClassSelectGrid').bootstrapTable('refresh');
            }else if('物料名称' == ctrlType){
            	$('#materialSelectWindow').modal('show');
		    	$('#materialSelectGrid').bootstrapTable('refresh');
            }		    	
	    }
	</script>
</body>
</html>