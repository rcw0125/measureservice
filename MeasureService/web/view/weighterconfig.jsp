<%@ page contentType="text/html;charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
	<head>
		<jsp:include page="common.jsp" flush="true"/>
		<style type="text/css">
			.select2-container--bootstrap .select2-selection{
				border-radius: 4px 0px 0px 4px;
			}
			.select2-container--bootstrap.input-sm .select2-selection--single, .input-group-sm .select2-container--bootstrap .select2-selection--single, .form-group-sm .select2-container--bootstrap .select2-selection--single{
				border-radius: 4px 0px 0px 4px; 
			}
			.table>tbody>tr>td{
				vertical-align:top;
			}
			.fixed-table-container tbody td .th-inner, .fixed-table-container thead th .th-inner{
				text-align:center;
			}
		</style>
	</head>
	<body class="container-fluid" style="overflow-x:hidden;padding-left:20px;padding-right:20px;">
		<ul class="nav nav-tabs" role="tablist">
			<c:forEach items="${weighterconfiglist}" var="item" varStatus="status">
				<li role="presentation" class="<c:if test="${status.index == 0}">active</c:if>">
					<a href="#" role="tab" data-toggle="tab" onclick="loadEquipmentInfo('${item.equcode}')">${item.equname}</a>
				</li>
			</c:forEach>
	    </ul>
		<div class="row">
			<div class="col-lg-3 col-md-6" style="padding:10px;">
				<div class="panel panel-info" style="width:100%;">
					<div class="panel-heading">基础信息</div>
					<div class="panel-body">
						<div class="container-fluid" style="padding:0px;">
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<input type="text" id="ClientName" name="ClientName" class="form-control" aria-describedby="basic-addon1" placeholder="衡器名称">
										<span class="input-group-btn">
									        <button class="btn btn-default" type="button" onclick="saveWeighterConfig('//param[@name=\'ClientName\']')">衡器名称　&nbsp;</button>
										</span>
									</div>	
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<input type="text" id="ClientIp" name="ClientIp" class="form-control" aria-describedby="basic-addon1" placeholder="衡器ＩＰ" readonly="readonly">
										<span class="input-group-btn">
									        <button class="btn btn-default" type="button" onclick="saveWeighterConfig('//*[name()=\'ClientIp\']','ClientIp')">衡器ＩＰ　&nbsp;</button>
										</span>
									</div>	
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<input type="text" id="MeasureType" name="MeasureType" class="form-control" aria-describedby="basic-addon1" placeholder="计量方式" value="远程计量" readonly="readonly">
										<div class="input-group-btn">
											<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">计量方式&nbsp;&nbsp;<span class="caret"></span></button>
											<ul class="dropdown-menu dropdown-menu-right">
												<li><a onclick="">远程计量</a></li>
												<li><a onclick="">现场自助</a></li>
											</ul>
										</div>
									</div>	
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<input type="text" id="StarType" name="StarType" class="form-control" aria-describedby="basic-addon1" placeholder="启动方式" value="重量+IC卡" readonly="readonly">
										<div class="input-group-btn">
											<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">启动方式&nbsp;&nbsp;<span class="caret"></span></button>
											<ul class="dropdown-menu dropdown-menu-right">
												<li><a onclick="">重量</a></li>
												<li><a onclick="">重量+IC卡</a></li>
												<li><a onclick="">重量+RFID卡</a></li>
												<li><a onclick="">重量+IC卡+RFID卡</a></li>
											</ul>
										</div>
									</div>	
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<input type="text" id="ConfirmTime" name="ConfirmTime" class="form-control" aria-describedby="basic-addon1" placeholder="确认时间(秒)，0为手动确认" value="0">
										<span class="input-group-btn">
									        <button class="btn btn-default" type="button">确认时间(S)</button>
										</span>
									</div>	
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-3 col-md-6" style="padding:10px;">
				<div class="panel panel-info" style="width:100%;">
					<div class="panel-heading">服务器配置</div>
					<div class="panel-body">
						<div class="container-fluid" style="padding:0px;">
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<input type="text" id="" name="" class="form-control" aria-describedby="basic-addon1" placeholder="计量服务器" value="http://10.1.196.86:8090/MeasureService2">
										<span class="input-group-btn">
									        <button class="btn btn-default" type="button">计量服务器</button>
										</span>
									</div>	
								</div>
							</div>
							<div class="row">
								<div class="col-sm-8">
									<div class="form-group input-group input-group-sm">
										<input type="text" id="SeatUrl" name="SeatUrl" class="form-control" aria-describedby="basic-addon1" placeholder="任务服务器" value="http://10.1.196.86">
										<span class="input-group-btn">
									        <button class="btn btn-default" type="button">任务服务器</button>
										</span>
									</div>	
								</div>
								<div class="col-sm-4">
									<div class="form-group input-group input-group-sm">
										<input type="text" id="SeatPort" name="SeatPort" class="form-control" aria-describedby="basic-addon1" placeholder="任务服务器" value="80">
										<span class="input-group-btn">
									        <button class="btn btn-default" type="button">端口</button>
										</span>
									</div>	
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<input type="text" id="" name="" class="form-control" aria-describedby="basic-addon1" placeholder="日志服务器" value="ftp://10.1.196.88">
										<span class="input-group-btn">
									        <button class="btn btn-default" type="button">日志服务器</button>
										</span>
									</div>	
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<input type="text" id="" name="" class="form-control" aria-describedby="basic-addon1" placeholder="照片服务器" value="ftp://10.1.196.88">
										<span class="input-group-btn">
									        <button class="btn btn-default" type="button">照片服务器</button>
										</span>
									</div>	
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<input type="text" id="" name="" class="form-control" eadonly="readonly" aria-describedby="basic-addon1" placeholder="视频服务器" value="日钢无效">
										<span class="input-group-btn">
									        <button class="btn btn-default" type="button">视频服务器</button>
										</span>
									</div>	
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		
			<div class="col-lg-3 col-md-6" style="padding:10px;">
				<div class="panel panel-info" style="width:100%;">
					<div class="panel-heading">IC读写器</div>
					<div class="panel-body">
						<div class="container-fluid" style="padding:0px;">
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<input type="text" id="IC_ConType" name="IC_ConType" class="form-control" aria-describedby="basic-addon1" placeholder="接口方式" value="串口" readonly="readonly">
										<div class="input-group-btn">
											<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">接口方式　<span class="caret"></span></button>
											<ul class="dropdown-menu dropdown-menu-right">
												<li><a onclick="">串口</a></li>
												<li><a onclick="">网口</a></li>
											</ul>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<input type="text" id="IC_Comport" name="IC_Comport" class="form-control" aria-describedby="basic-addon1" placeholder="串口号" value="COM3">
										<div class="input-group-btn">
											<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">串　口　号<span class="caret"></span></button>
											<ul class="dropdown-menu dropdown-menu-right">
												<li><a onclick="">COM1</a></li>
												<li><a onclick="">COM2</a></li>
												<li><a onclick="">COM3</a></li>
												<li><a onclick="">COM4</a></li>
												<li><a onclick="">COM5</a></li>
												<li><a onclick="">COM6</a></li>
												<li><a onclick="">COM7</a></li>
												<li><a onclick="">COM8</a></li>
												<li><a onclick="">COM9</a></li>
												<li><a onclick="">COM10</a></li>
												<li><a onclick="">COM11</a></li>
												<li><a onclick="">COM12</a></li>
												<li><a onclick="">COM13</a></li>
												<li><a onclick="">COM14</a></li>
												<li><a onclick="">COM15</a></li>
												<li><a onclick="">COM16</a></li>
												<li><a onclick="">COM17</a></li>
												<li><a onclick="">COM18</a></li>
												<li><a onclick="">COM19</a></li>
												<li><a onclick="">COM20</a></li>
											</ul>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<input type="text" id="IC_Baudrate" name="IC_Baudrate" class="form-control" aria-describedby="basic-addon1" placeholder="波特率" value="115200bps">
										<div class="input-group-btn">
											<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">波　特　率<span class="caret"></span></button>
											<ul class="dropdown-menu dropdown-menu-right">
												<li><a onclick="">1200bps</a></li>
												<li><a onclick="">4800bps</a></li>
												<li><a onclick="">9600bps</a></li>
												<li><a onclick="">19200bps</a></li>
												<li><a onclick="">38400bps</a></li>
												<li><a onclick="">56000bps</a></li>
												<li><a onclick="">57600bps</a></li>
												<li><a onclick="">115200bps</a></li>
											</ul>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<select id="IC_ICReadType" name="IC_ICReadType" class="form-control select2" placeholder="必选">
											<option value="0">明华</option>
											<option value="1">明泰</option>
										</select>
										<span class="input-group-addon">读卡器型号&nbsp;&nbsp;</span>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<select id="IC_IsUse" name="IC_IsUse" class="form-control select2" placeholder="必选">
											<option value="否">否</option>
											<option value="是" selected="selected">是</option>
										</select>
										<span class="input-group-addon">是&nbsp;否&nbsp;启&nbsp;用&nbsp;&nbsp;&nbsp;</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="col-lg-3 col-md-6" style="padding:10px;">
				<div class="panel panel-info" style="width:100%;">
					<div class="panel-heading">RFID读写器</div>
					<div class="panel-body">
						<div class="container-fluid" style="padding:0px;">
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<input type="text" id="RFID_Ip" name="RFID_Ip" class="form-control" aria-describedby="basic-addon1" placeholder="IP地址" value="10.1.44.242">
										<span class="input-group-addon">IP地址&nbsp;&nbsp;</span>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<input type="text" id="RFID_Port" name="RFID_Port" class="form-control" aria-describedby="basic-addon1" placeholder="端口" value="8000">
										<span class="input-group-addon">端口&nbsp;&nbsp;</span>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<input type="text" id="RFID_Interval" name="RFID_Interval" class="form-control" aria-describedby="basic-addon1" placeholder="寻卡时间" value="1000">
										<span class="input-group-addon">寻卡时间&nbsp;&nbsp;</span>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<input type="text" id="RFID_Power" name="RFID_Power" class="form-control" aria-describedby="basic-addon1" placeholder="功率" value="1500">
										<span class="input-group-addon">功率&nbsp;&nbsp;</span>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<select id="RFID_IsUse" name="RFID_IsUse" class="form-control select2" placeholder="必选">
											<option value="否">否</option>
											<option value="是" selected="selected">是</option>
										</select>
										<span class="input-group-addon">是否启用</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-lg-3 col-md-6" style="padding:10px;">
				<div class="panel panel-danger" style="width:100%;">
					<div class="panel-heading">仪表配置</div>
					<div class="panel-body">
						<div class="container-fluid" style="padding:0px;">
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<select id="DeviceName" name="DeviceName" class="form-control select2" placeholder="仪表厂家">
											<option value="托利多@WEIGHT\Talent.Weight.TLD.dll">托利多@WEIGHT\Talent.Weight.TLD.dll</option>
											<option value="金钟@WEIGHT\Talent.Weight.JZ.dll">金钟@WEIGHT\Talent.Weight.JZ.dll</option>
										</select>
										<span class="input-group-addon">仪表厂家&nbsp;</span>
									</div>	
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<input type="text" id="Weighter_Comport" name="Weighter_Comport" class="form-control" aria-describedby="basic-addon1" placeholder="串口号" value="COM3">
										<div class="input-group-btn">
											<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">串口号&nbsp;&nbsp;<span class="caret"></span></button>
											<ul class="dropdown-menu dropdown-menu-right">
												<li><a onclick="">COM1</a></li>
												<li><a onclick="">COM2</a></li>
												<li><a onclick="">COM3</a></li>
												<li><a onclick="">COM4</a></li>
												<li><a onclick="">COM5</a></li>
												<li><a onclick="">COM6</a></li>
												<li><a onclick="">COM7</a></li>
												<li><a onclick="">COM8</a></li>
												<li><a onclick="">COM9</a></li>
												<li><a onclick="">COM10</a></li>
												<li><a onclick="">COM11</a></li>
												<li><a onclick="">COM12</a></li>
												<li><a onclick="">COM13</a></li>
												<li><a onclick="">COM14</a></li>
												<li><a onclick="">COM15</a></li>
												<li><a onclick="">COM16</a></li>
												<li><a onclick="">COM17</a></li>
												<li><a onclick="">COM18</a></li>
												<li><a onclick="">COM19</a></li>
												<li><a onclick="">COM20</a></li>
											</ul>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<input type="text" id="Weighter_Baudrate" name="Weighter_Baudrate" class="form-control" aria-describedby="basic-addon1" placeholder="波特率" value="9600bps">
										<div class="input-group-btn">
											<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">波特率&nbsp;&nbsp;<span class="caret"></span></button>
											<ul class="dropdown-menu dropdown-menu-right">
												<li><a onclick="">1200bps</a></li>
												<li><a onclick="">4800bps</a></li>
												<li><a onclick="">9600bps</a></li>
												<li><a onclick="">19200bps</a></li>
												<li><a onclick="">38400bps</a></li>
												<li><a onclick="">56000bps</a></li>
												<li><a onclick="">57600bps</a></li>
												<li><a onclick="">115200bps</a></li>
											</ul>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<input type="text" id="Parity" name="Parity" class="form-control" aria-describedby="basic-addon1" placeholder="校验位" value="偶校验" readonly="readonly">
										<div class="input-group-btn">
											<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">校验位&nbsp;&nbsp;<span class="caret"></span></button>
											<ul class="dropdown-menu dropdown-menu-right">
												<li><a onclick="">无</a></li>
												<li><a onclick="">偶校验</a></li>
												<li><a onclick="">奇校验</a></li>
											</ul>
										</div>
									</div>	
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6">
									<div class="form-group input-group input-group-sm">
										<input type="text" id="Stopsize" name="Stopsize" class="form-control" aria-describedby="basic-addon1" placeholder="停止位" value="1" readonly="readonly">
										<div class="input-group-btn">
											<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">停止位&nbsp;&nbsp;<span class="caret"></span></button>
											<ul class="dropdown-menu dropdown-menu-right">
												<li><a onclick="">1</a></li>
												<li><a onclick="">1.5</a></li>
												<li><a onclick="">2</a></li>
											</ul>
										</div>
									</div>	
								</div>
								<div class="col-sm-6">
									<div class="form-group input-group input-group-sm">
										<input type="text" id="ByteSize" name="ByteSize" class="form-control" aria-describedby="basic-addon1" placeholder="数据位" value="7" readonly="readonly">
										<div class="input-group-btn">
											<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">数据位&nbsp;&nbsp;<span class="caret"></span></button>
											<ul class="dropdown-menu dropdown-menu-right">
												<li><a onclick="">7</a></li>
												<li><a onclick="">8</a></li>
											</ul>
										</div>
									</div>	
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="col-lg-3 col-md-6" style="padding:10px;">
				<div class="panel panel-danger" style="width:100%;">
					<div class="panel-heading">打印机1配置</div>
					<div class="panel-body">
						<div class="container-fluid" style="padding:0px;">
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<input type="text" id="PRINT1_Comport" name="PRINT1_Comport" class="form-control" aria-describedby="basic-addon1" placeholder="串口号" value="COM3">
										<div class="input-group-btn">
											<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">串　口　号<span class="caret"></span></button>
											<ul class="dropdown-menu dropdown-menu-right">
												<li><a onclick="">COM1</a></li>
												<li><a onclick="">COM2</a></li>
												<li><a onclick="">COM3</a></li>
												<li><a onclick="">COM4</a></li>
												<li><a onclick="">COM5</a></li>
												<li><a onclick="">COM6</a></li>
												<li><a onclick="">COM7</a></li>
												<li><a onclick="">COM8</a></li>
												<li><a onclick="">COM9</a></li>
												<li><a onclick="">COM10</a></li>
												<li><a onclick="">COM11</a></li>
												<li><a onclick="">COM12</a></li>
												<li><a onclick="">COM13</a></li>
												<li><a onclick="">COM14</a></li>
												<li><a onclick="">COM15</a></li>
												<li><a onclick="">COM16</a></li>
												<li><a onclick="">COM17</a></li>
												<li><a onclick="">COM18</a></li>
												<li><a onclick="">COM19</a></li>
												<li><a onclick="">COM20</a></li>
											</ul>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<input type="text" id="PRINT1_Baudrate" name="PRINT1_Baudrate" class="form-control" aria-describedby="basic-addon1" placeholder="波特率" value="9600bps">
										<div class="input-group-btn">
											<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">波　特　率<span class="caret"></span></button>
											<ul class="dropdown-menu dropdown-menu-right">
												<li><a onclick="">1200bps</a></li>
												<li><a onclick="">4800bps</a></li>
												<li><a onclick="">9600bps</a></li>
												<li><a onclick="">19200bps</a></li>
												<li><a onclick="">38400bps</a></li>
												<li><a onclick="">56000bps</a></li>
												<li><a onclick="">57600bps</a></li>
												<li><a onclick="">115200bps</a></li>
											</ul>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<select id="PRINT1_Brand" name="PRINT1_Brand" class="form-control select2" placeholder="必选">
											<option value="新北洋">新北洋</option>
											<option value="CUSTOM">CUSTOM</option>
										</select>
										<span class="input-group-addon">打印机型号&nbsp;&nbsp;</span>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<select id="PRINT1_Notch" name="PRINT1_Notch" class="form-control select2" placeholder="必选">
											<option value="是" selected="selected">是</option>
											<option value="否">否</option>
										</select>
										<span class="input-group-addon">是&nbsp;否&nbsp;切&nbsp;纸&nbsp;&nbsp;&nbsp;</span>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<select id="PRINT1_IsUse" name="PRINT1_IsUse" class="form-control select2" placeholder="必选">
											<option value="是" selected="selected">是</option>
											<option value="否">否</option>
										</select>
										<span class="input-group-addon">是&nbsp;否&nbsp;启&nbsp;用&nbsp;&nbsp;&nbsp;</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		
			<div class="col-lg-3 col-md-6" style="padding:10px;">
				<div class="panel panel-danger" style="width:100%;">
					<div class="panel-heading">打印机2配置</div>
					<div class="panel-body">
						<div class="container-fluid" style="padding:0px;">
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<input type="text" id="PRINT2_Comport" name="PRINT2_Comport" class="form-control" aria-describedby="basic-addon1" placeholder="串口号" value="COM3">
										<div class="input-group-btn">
											<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">串　口　号<span class="caret"></span></button>
											<ul class="dropdown-menu dropdown-menu-right">
												<li><a onclick="">COM1</a></li>
												<li><a onclick="">COM2</a></li>
												<li><a onclick="">COM3</a></li>
												<li><a onclick="">COM4</a></li>
												<li><a onclick="">COM5</a></li>
												<li><a onclick="">COM6</a></li>
												<li><a onclick="">COM7</a></li>
												<li><a onclick="">COM8</a></li>
												<li><a onclick="">COM9</a></li>
												<li><a onclick="">COM10</a></li>
												<li><a onclick="">COM11</a></li>
												<li><a onclick="">COM12</a></li>
												<li><a onclick="">COM13</a></li>
												<li><a onclick="">COM14</a></li>
												<li><a onclick="">COM15</a></li>
												<li><a onclick="">COM16</a></li>
												<li><a onclick="">COM17</a></li>
												<li><a onclick="">COM18</a></li>
												<li><a onclick="">COM19</a></li>
												<li><a onclick="">COM20</a></li>
											</ul>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<input type="text" id="PRINT2_Baudrate" name="PRINT2_Baudrate" class="form-control" aria-describedby="basic-addon1" placeholder="波特率" value="38400bps">
										<div class="input-group-btn">
											<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">波　特　率<span class="caret"></span></button>
											<ul class="dropdown-menu dropdown-menu-right">
												<li><a onclick="">1200bps</a></li>
												<li><a onclick="">4800bps</a></li>
												<li><a onclick="">9600bps</a></li>
												<li><a onclick="">19200bps</a></li>
												<li><a onclick="">38400bps</a></li>
												<li><a onclick="">56000bps</a></li>
												<li><a onclick="">57600bps</a></li>
												<li><a onclick="">115200bps</a></li>
											</ul>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<select id="PRINT2_Brand" name="PRINT2_Brand" class="form-control select2" placeholder="必选">
											<option value="新北洋">新北洋</option>
											<option value="CUSTOM" selected="selected">CUSTOM</option>
										</select>
										<span class="input-group-addon">打印机型号&nbsp;&nbsp;</span>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<select id="PRINT2_Notch" name="PRINT2_Notch" class="form-control select2" placeholder="必选">
											<option value="是" selected="selected">是</option>
											<option value="否">否</option>
										</select>
										<span class="input-group-addon">是&nbsp;否&nbsp;切&nbsp;纸&nbsp;&nbsp;&nbsp;</span>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<select id="PRINT2_IsUse" name="PRINT2_IsUse" class="form-control select2" placeholder="必选">
											<option value="是" selected="selected">是</option>
											<option value="否">否</option>
										</select>
										<span class="input-group-addon">是&nbsp;否&nbsp;启&nbsp;用&nbsp;&nbsp;&nbsp;</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="col-lg-3 col-md-6" style="padding:10px;">
				<div class="panel panel-danger" style="width:100%;">
					<div class="panel-heading">键盘配置</div>
					<div class="panel-body">
						<div class="container-fluid" style="padding:0px;">
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<input type="text" id="KeyOk" name="KeyOk" class="form-control" aria-describedby="basic-addon1" placeholder="键值" value="6">
										<span class="input-group-btn">
									        <button class="btn btn-default" type="button">确　　　定&nbsp;&nbsp;</button>
										</span>
									</div>	
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<input type="text" id="KeyHelp" name="KeyHelp" class="form-control" aria-describedby="basic-addon1" placeholder="键值" value="48">
										<span class="input-group-btn">
									        <button class="btn btn-default" type="button">求　　　助&nbsp;&nbsp;</button>
										</span>
									</div>	
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<input type="text" id="KeyDelete" name="KeyDelete" class="form-control" aria-describedby="basic-addon1" placeholder="键值" value="2">
										<span class="input-group-btn">
									        <button class="btn btn-default" type="button">删　　　除&nbsp;&nbsp;</button>
										</span>
									</div>	
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<input type="text" id="KeyClear" name="KeyClear" class="form-control" aria-describedby="basic-addon1" placeholder="键值" value="32">
										<span class="input-group-btn">
									        <button class="btn btn-default" type="button">清　　　空&nbsp;&nbsp;</button>
										</span>
									</div>	
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<input type="text" id="KeyCancel" name="KeyCancel" class="form-control" aria-describedby="basic-addon1" placeholder="键值" value="13">
										<span class="input-group-btn">
									        <button class="btn btn-default" type="button">取　　　消&nbsp;&nbsp;</button>
										</span>
									</div>	
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-lg-4 col-md-12" style="padding:10px;">
				<div class="panel panel-success" style="width:100%;">
					<div class="panel-heading">视频配置</div>
					<div class="panel-body">
						<div class="container-fluid" style="padding:0px;">
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<select id="VideoType" name="VideoType" class="form-control select2" placeholder="视频厂家">
											<option value="海康@IoDll\hk\Talent.Video.HKVideo.dll" selected="selected">海康@IoDll\hk\Talent.Video.HKVideo.dll</option>
											<option value="大华@IoDll\hk\Talent.Video.DHVideo.dll">大华@IoDll\hk\Talent.Video.DHVideo.dll</option>
										</select>
										<span class="input-group-addon">视频厂家</span>
									</div>	
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<input type="text" id="Video_Ip" name="Video_Ip" class="form-control" aria-describedby="basic-addon1" placeholder="IP地址" value="[IP]">
										<span class="input-group-btn">
									        <button class="btn btn-default" type="button">IP&nbsp;地&nbsp;址&nbsp;</button>
										</span>
									</div>	
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<input type="text" id="Video_Port" name="Video_Port" class="form-control" aria-describedby="basic-addon1" placeholder="端口" value="8000">
										<span class="input-group-btn">
									        <button class="btn btn-default" type="button">端　　口</button>
										</span>
									</div>	
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<input type="text" id="Video_UserName" name="Video_UserName" class="form-control" aria-describedby="basic-addon1" placeholder="用户名" value="admin">
										<span class="input-group-btn">
									        <button class="btn btn-default" type="button">用&nbsp;户&nbsp;名&nbsp;</button>
										</span>
									</div>	
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<input type="password" id="Video_PassWord" name="Video_PassWord" class="form-control" aria-describedby="basic-addon1" placeholder="密码" value="123456">
										<span class="input-group-btn">
									        <button class="btn btn-default" type="button">密　　码</button>
										</span>
									</div>	
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-2 col-md-12" style="padding:10px;">
				<div class="panel panel-success" style="width:100%;">
					<div class="panel-heading">通道1设置</div>
					<div class="panel-body">
						<div class="container-fluid" style="padding:0px;">
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<input type="text" id="Video1_VideoName" name="Video1_VideoName" class="form-control" aria-describedby="basic-addon1" placeholder="视频名称" value="">
										<span class="input-group-btn">
									        <button class="btn btn-default" type="button">视频名称</button>
										</span>
									</div>	
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<input type="text" id="Video1_Position" name="Video1_Position" class="form-control" aria-describedby="basic-addon1" placeholder="视频位置" value="1">
										<span class="input-group-btn">
									        <button class="btn btn-default" type="button">视频位置</button>
										</span>
									</div>	
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<input type="text" id="Video1_Channel" name="Video1_Channel" class="form-control" aria-describedby="basic-addon1" placeholder="视频通道" value="1">
										<span class="input-group-btn">
									        <button class="btn btn-default" type="button">视频通道</button>
										</span>
									</div>	
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<select id="Video1_Photograph" name="Video1_Photograph" class="form-control select2" placeholder="必选">
											<option value="是" selected="selected">是</option>
											<option value="否">否</option>
										</select>
										<span class="input-group-addon">是否拍照</span>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<select id="Video1_IsUse" name="Video1_IsUse" class="form-control select2" placeholder="必选">
											<option value="是" selected="selected">是</option>
											<option value="否">否</option>
										</select>
										<span class="input-group-addon">是否启用</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-2 col-md-12" style="padding:10px;">
				<div class="panel panel-success" style="width:100%;">
					<div class="panel-heading">通道2设置</div>
					<div class="panel-body">
						<div class="container-fluid" style="padding:0px;">
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<input type="text" id="Video2_VideoName" name="Video2_VideoName" class="form-control" aria-describedby="basic-addon1" placeholder="视频名称" value="">
										<span class="input-group-btn">
									        <button class="btn btn-default" type="button">视频名称</button>
										</span>
									</div>	
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<input type="text" id="Video2_Position" name="Video2_Position" class="form-control" aria-describedby="basic-addon1" placeholder="视频位置" value="1">
										<span class="input-group-btn">
									        <button class="btn btn-default" type="button">视频位置</button>
										</span>
									</div>	
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<input type="text" id="Video2_Channel" name="Video2_Channel" class="form-control" aria-describedby="basic-addon1" placeholder="视频通道" value="1">
										<span class="input-group-btn">
									        <button class="btn btn-default" type="button">视频通道</button>
										</span>
									</div>	
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<select id="Video2_Photograph" name="Video2_Photograph" class="form-control select2" placeholder="必选">
											<option value="是" selected="selected">是</option>
											<option value="否">否</option>
										</select>
										<span class="input-group-addon">是否拍照</span>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<select id="Video2_IsUse" name="Video2_IsUse" class="form-control select2" placeholder="必选">
											<option value="是" selected="selected">是</option>
											<option value="否">否</option>
										</select>
										<span class="input-group-addon">是否启用</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-2 col-md-12" style="padding:10px;">
				<div class="panel panel-success" style="width:100%;">
					<div class="panel-heading">通道3设置</div>
					<div class="panel-body">
						<div class="container-fluid" style="padding:0px;">
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<input type="text" id="Video3_VideoName" name="Video3_VideoName" class="form-control" aria-describedby="basic-addon1" placeholder="视频名称" value="">
										<span class="input-group-btn">
									        <button class="btn btn-default" type="button">视频名称</button>
										</span>
									</div>	
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<input type="text" id="Video3_Position" name="Video3_Position" class="form-control" aria-describedby="basic-addon1" placeholder="视频位置" value="1">
										<span class="input-group-btn">
									        <button class="btn btn-default" type="button">视频位置</button>
										</span>
									</div>	
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<input type="text" id="Video3_Channel" name="Video3_Channel" class="form-control" aria-describedby="basic-addon1" placeholder="视频通道" value="1">
										<span class="input-group-btn">
									        <button class="btn btn-default" type="button">视频通道</button>
										</span>
									</div>	
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<select id="Video3_Photograph" name="Video3_Photograph" class="form-control select2" placeholder="必选">
											<option value="是" selected="selected">是</option>
											<option value="否">否</option>
										</select>
										<span class="input-group-addon">是否拍照</span>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<select id="Video3_IsUse" name="Video3_IsUse" class="form-control select2" placeholder="必选">
											<option value="是" selected="selected">是</option>
											<option value="否">否</option>
										</select>
										<span class="input-group-addon">是否启用</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-2 col-md-12" style="padding:10px;">
				<div class="panel panel-success" style="width:100%;">
					<div class="panel-heading">通道4设置</div>
					<div class="panel-body">
						<div class="container-fluid" style="padding:0px;">
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<input type="text" id="Video4_VideoName" name="Video4_VideoName" class="form-control" aria-describedby="basic-addon1" placeholder="视频名称" value="">
										<span class="input-group-btn">
									        <button class="btn btn-default" type="button">视频名称</button>
										</span>
									</div>	
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<input type="text" id="Video4_Position" name="Video4_Position" class="form-control" aria-describedby="basic-addon1" placeholder="视频位置" value="1">
										<span class="input-group-btn">
									        <button class="btn btn-default" type="button">视频位置</button>
										</span>
									</div>	
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<input type="text" id="Video4_Channel" name="Video4_Channel" class="form-control" aria-describedby="basic-addon1" placeholder="视频通道" value="1">
										<span class="input-group-btn">
									        <button class="btn btn-default" type="button">视频通道</button>
										</span>
									</div>	
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<select id="Video4_Photograph" name="Video4_Photograph" class="form-control select2" placeholder="必选">
											<option value="是" selected="selected">是</option>
											<option value="否">否</option>
										</select>
										<span class="input-group-addon">是否拍照</span>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group input-group input-group-sm">
										<select id="Video4_IsUse" name="Video4_IsUse" class="form-control select2" placeholder="必选">
											<option value="是" selected="selected">是</option>
											<option value="否">否</option>
										</select>
										<span class="input-group-addon">是否启用</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			$(document).ready(function(){
				
			});
			
			var xps = '';
			var xps_ids = new Array();
			xps_ids["//param[@name='ClientName']"] = 'ClientName';
			xps_ids["//param[@name='MeasureType']"] = 'MeasureType';
			xps_ids["//param[@name='ClientIp']"] = 'ClientIp';
			xps_ids["//param[@name='StarType']"] = 'StarType';
			xps_ids["//param[@name='ConfirmTime']"] = 'ConfirmTime';
			
			xps_ids["//submodule[@code='IcConfig']//param[@name='ConType']"] = 'IC_ConType';
			xps_ids["//submodule[@code='IcConfig']//param[@name='Comport']"] = 'IC_Comport';
			xps_ids["//submodule[@code='IcConfig']//param[@name='Baudrate']"] = 'IC_Baudrate';
			xps_ids["//submodule[@code='IcConfig']//param[@name='ICReadType']"] = 'IC_ICReadType';
			xps_ids["//submodule[@code='IcConfig']//param[@name='IsUse']"] = 'IC_IsUse';
			
			xps_ids["//submodule[@code='RfidConfig']//param[@name='Ip']"] = 'RFID_Ip';
			xps_ids["//submodule[@code='RfidConfig']//param[@name='Port']"] = 'RFID_Port';
			xps_ids["//submodule[@code='RfidConfig']//param[@name='Interval']"] = 'RFID_Interval';
			xps_ids["//submodule[@code='RfidConfig']//param[@name='Interval']"] = 'RFID_Power';
			xps_ids["//submodule[@code='RfidConfig']//param[@name='IsUse']"] = 'IC_IsUse';
			
			xps_ids["//submodule[@code='WeighterConfig']//param[@name='DeviceName']"] = 'DeviceName';
			xps_ids["//submodule[@code='WeighterConfig']//param[@name='Comport']"] = 'Weighter_Comport';
			xps_ids["//submodule[@code='WeighterConfig']//param[@name='Baudrate']"] = 'Weighter_Baudrate';
			xps_ids["//submodule[@code='WeighterConfig']//param[@name='Parity']"] = 'Parity';
			xps_ids["//submodule[@code='WeighterConfig']//param[@name='Stopsize']"] = 'Stopsize';
			xps_ids["//submodule[@code='WeighterConfig']//param[@name='ByteSize']"] = 'ByteSize';
			
			xps_ids["//param[@value='上打印机']/parent::*//param[@name='Comport']"] = 'PRINT1_Comport';
			xps_ids["//param[@value='上打印机']/parent::*//param[@name='Baudrate']"] = 'PRINT1_Baudrate';
			xps_ids["//param[@value='上打印机']/parent::*//param[@name='Brand']"] = 'PRINT1_Brand';
			xps_ids["//param[@value='上打印机']/parent::*//param[@name='Notch']"] = 'PRINT1_Notch';
			xps_ids["//param[@value='上打印机']/parent::*//param[@name='IsUse']"] = 'PRINT1_IsUse';
			
			xps_ids["//param[@value='下打印机']/parent::*//param[@name='Comport']"] = 'PRINT2_Comport';
			xps_ids["//param[@value='下打印机']/parent::*//param[@name='Baudrate']"] = 'PRINT2_Baudrate';
			xps_ids["//param[@value='下打印机']/parent::*//param[@name='Brand']"] = 'PRINT2_Brand';
			xps_ids["//param[@value='下打印机']/parent::*//param[@name='Notch']"] = 'PRINT2_Notch';
			xps_ids["//param[@value='下打印机']/parent::*//param[@name='IsUse']"] = 'PRINT2_IsUse';
			
			xps_ids["//param[@value='KeyOk']/parent::*//param[@name='KeyValue']"] = 'KeyOk';
			xps_ids["//param[@value='KeyHelp']/parent::*//param[@name='KeyValue']"] = 'KeyHelp';
			xps_ids["//param[@value='KeyDelete']/parent::*//param[@name='KeyValue']"] = 'KeyDelete';
			xps_ids["//param[@value='KeyClear']/parent::*//param[@name='KeyValue']"] = 'KeyClear';
			xps_ids["//param[@value='KeyCancel']/parent::*//param[@name='KeyValue']"] = 'KeyCancel';
			
			for(var key in xps_ids){
			    xps = xps + ',' + key;
			}
			xps = xps.substr(1);
			
			function loadEquipmentInfo(equcode){
				$.ajax({
		            type: "post",
		            url: '<c:url value='/weighter/info'/>',
		            dataType: "json",
		            data: {equcode:equcode,xps:xps},
		            success: function(data){
		            	var xpsArry = xps.split(',');
		            	for(var i=0;i<xpsArry.length;i++){
		            		$('#' + xps_ids[xpsArry[i]]).val(data[xpsArry[i]]);
		            	}
		            }
	            });
			}
		</script>
	</body>
</html>