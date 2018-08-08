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
			.custom-bg {
			    background-color:#f54dac;
			    color: #fff;
			}
			.fixed-table-container {
			    border-radius: 0px !important;
			}
			.panel-body{
				padding:0px;
			}
		</style>
	</head>
	<body class="gray-bg">
		<div class="row animated fadeInRight">
			<div class="col-sm-12">
				<div class="text-center animated fadeInDown"><h2>${worklinename}</h2></div>
				<div id="vertical-timeline" class="vertical-container light-timeline">
					<div class="vertical-timeline-block">
						<div class="vertical-timeline-icon blue-bg">
							<i class="fa fa-credit-card"></i>
						</div>
						<div class="vertical-timeline-content panel <c:if test="${empty makecardlist}">panel-default</c:if><c:if test="${!empty makecardlist}">panel-success</c:if>">
							<div class="panel-heading">制卡信息</div>
							<div class="panel-body">
								<table data-toggle="table" data-mobile-responsive="true">
									<thead>
										<tr align="center" style="FONT-WEIGHT: bold; FONT-SIZE: 10px; MARGIN: 7px 0px; COLOR: #000000">
											<th nowrap="nowrap" alt="taskcode" align="center"><font size="2">物流号</font></th>
											<%-- <th nowrap="nowrap" alt="operatype"><font size="2">订单号</font></th> --%>
											<th nowrap="nowrap" alt="planid"><font size="2">计划号</font></th>
											<th nowrap="nowrap" alt="mflag"><font size="2">车号</font></th>
											<th nowrap="nowrap" alt="materialname"><font size="2">货名</font></th>
											<th nowrap="nowrap" alt="sourcename"><font size="2">供货单位</font></th>
											<th nowrap="nowrap" alt="transitway"><font size="2">收货单位</font></th>
											<th nowrap="nowrap" alt="unitname"><font size="2">制卡单位</font></th>
											<th nowrap="nowrap" alt="unitname"><font size="2">制卡时间</font></th>
											<%-- <th nowrap="nowrap" alt="isplan"><font size="2">船名</font></th>
											<th nowrap="nowrap" alt="createman"><font size="2">港口</font></th> 
											<th nowrap="nowrap" alt="createdate"><font size="2">计划量/t</font></th>
											<th nowrap="nowrap" alt="createdate"><font size="2">供方毛重/t</font></th>
											<th nowrap="nowrap" alt="createdate"><font size="2">供方皮重/t</font></th>--%>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${makecardlist}" var="makecard" varStatus="num">
											<tr height="25"  align="center">
												<td nowrap="nowrap"><font size="2">${makecard.matchid}&nbsp;</font></td>
												<%-- <td nowrap="nowrap"><font size="2">${makecard.orderno}&nbsp;</font></td> --%>
												<td nowrap="nowrap"><font size="2">${makecard.planid}&nbsp;</font></td>
												<td nowrap="nowrap"><font size="2">${makecard.carno}&nbsp;</font></td>
												<td nowrap="nowrap"><font size="2">${makecard.materialname}&nbsp;</font></td>
												<td nowrap="nowrap"><font size="2">${makecard.sourcename}&nbsp;</font></td>
												<td nowrap="nowrap"><font size="2">${makecard.targetname}&nbsp;</font></td>
												<td nowrap="nowrap"><font size="2">${makecard.unitname}&nbsp;</font></td>
												<td nowrap="nowrap"><font size="2">${makecard.createdate}&nbsp;</font></td>
												<%-- <td nowrap="nowrap"><font size="2">${makecard.ship}&nbsp;</font></td>
												<td nowrap="nowrap"><font size="2">${makecard.sourceplace}&nbsp;</font></td> 
												<td nowrap="nowrap"><font size="2">${makecard.planweight}&nbsp;</font></td>
												<td nowrap="nowrap"><font size="2">${makecard.grossb}&nbsp;</font></td>
												<td nowrap="nowrap"><font size="2">${makecard.tareb}&nbsp;</font></td>--%>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<c:set var="lastmeasure" value="0"/>
					<c:forEach items="${nodelist}" var="node" varStatus="num">
						<div class="vertical-timeline-block">
							<c:if test="${node.nodename=='进厂'}">
								<div class="vertical-timeline-icon navy-bg">
									<i class="fa fa-exchange"></i>
									<!-- <div style="position:absolute;left:-25px;padding-top:80px;">
										<p style="color:#000;font-size:10px;width:80px;">10分钟</p>
									</div> -->
								</div>
								<div class="vertical-timeline-content panel <c:if test="${empty entrylist}">panel-default</c:if><c:if test="${!empty entrylist}">panel-success</c:if>">
									<div class="panel-heading">进厂信息【${node.workpointname}】</div>
									<div class="panel-body">
										<table data-toggle="table" data-mobile-responsive="true">
											<thead>
												<tr align="center">
													<th nowrap="nowrap" align="center"><font size="2">车号</font></th>
													<%-- <th nowrap="nowrap" ><font size="2">卡类型</font></th> --%>
													<th nowrap="nowrap" ><font size="2">进厂大门</font></th>
													<th nowrap="nowrap" ><font size="2">进厂操作人</font></th>
													<th nowrap="nowrap" ><font size="2">进厂时间</font></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${entrylist}" var="entryrow" varStatus="num">
													<tr height="25" align="center">
														<td nowrap="nowrap"><font size="2">${entryrow.carno}&nbsp;</font></td>
														<%-- <td nowrap="nowrap">
															<font size="2">
																<c:if test="${entryrow.ictype==0}">临时卡</c:if>
																<c:if test="${entryrow.ictype==1}">固定卡</c:if> &nbsp;
															</font>
														</td> --%>
														<td nowrap="nowrap"><font size="2">${entryrow.gatename}&nbsp;</font></td>
														<td nowrap="nowrap"><font size="2">${entryrow.creator}&nbsp;</font></td>
														<td nowrap="nowrap"><font size="2">${entryrow.createdate}
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							</c:if>
							<c:if test="${node.nodename=='出厂'}">
								<div class="vertical-timeline-icon navy-bg">
									<i class="fa fa-exchange"></i>
									<!-- <div style="position:absolute;left:-25px;padding-top:80px;">
										<p style="color:#000;font-size:10px;width:80px;">10分钟</p>
									</div> -->
								</div>
								<div class="vertical-timeline-content panel <c:if test="${empty entrylist}">panel-default</c:if><c:if test="${!empty entrylist}">panel-success</c:if>">
									<div class="panel-heading">出厂信息【${node.workpointname}】</div>
									<div class="panel-body">
										<table data-toggle="table" data-mobile-responsive="true">
											<thead>
												<tr align="center">
													<th nowrap="nowrap" align="center"><font size="2">车号</font></th>
													<th nowrap="nowrap" ><font size="2">出厂大门</font></th>
													<th nowrap="nowrap" ><font size="2">出厂操作人</font></th>
													<th nowrap="nowrap" ><font size="2">出厂时间</font></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${entrylist}" var="entryrow" varStatus="num">
													<tr height="25" align="center">
														<td nowrap="nowrap"><font size="2">${entryrow.carno}&nbsp;</font></td>
														<td nowrap="nowrap"><font size="2">${entryrow.entry.gatename}&nbsp;</font></td>
														<td nowrap="nowrap"><font size="2">${entryrow.entry.creator}&nbsp;</font></td>
														<td nowrap="nowrap"><font size="2">${entryrow.entry.createdate}&nbsp;</font></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							</c:if>
							<c:if test="${node.nodename=='取样'}">
								<div class="vertical-timeline-icon red-bg">
									<i class="fa fa-eyedropper"></i>
									<!-- <div style="position:absolute;left:-25px;padding-top:80px;">
										<p style="color:#000;font-size:10px;width:80px;">10分钟</p>
									</div> -->
								</div>
								<div class="vertical-timeline-content panel <c:if test="${empty qualitylist}">panel-default</c:if><c:if test="${!empty qualitylist}">panel-success</c:if>">
									<div class="panel-heading">取样信息【${node.workpointname}】</div>
									<div class="panel-body">
										<table data-toggle="table" data-mobile-responsive="true">
											<thead>
												<tr align="center" style="FONT-WEIGHT: bold; FONT-SIZE: 10px; MARGIN: 7px 0px; COLOR: #000000">
													<th nowrap="nowrap" alt="taskcode" align="center"><font size="2">物流号</font></th>
													<th nowrap="nowrap" alt="mflag"><font size="2">车号</font></th>
													<th nowrap="nowrap" alt="sourcename"><font size="2">取样人</font></th>
													<th nowrap="nowrap" alt="isplan"><font size="2">取样地点</font></th>
													<th nowrap="nowrap" alt="transitway"><font size="2">取样时间</font></th>
													<th nowrap="nowrap" alt="createman"><font size="2">备注</font></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${qualitylist}" var="quality" varStatus="num">
													<tr height="25"  align="center">
														<td nowrap="nowrap"><font size="2">${quality.matchid}&nbsp;</font></td>
														<td nowrap="nowrap"><font size="2">${quality.carno}&nbsp;</font></td>
														<td nowrap="nowrap"><font size="2">${quality.creator}&nbsp;</font></td>
														<td nowrap="nowrap"><font size="2">${quality.qmpostion}&nbsp;</font></td>
														<td nowrap="nowrap"><font size="2">${quality.createdate}&nbsp;</font></td>
														<td nowrap="nowrap"><font size="2">${quality.usermemo}&nbsp;</font></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							</c:if>
							<c:if test="${operatype!='91' and  operatype!='93' and  operatype!='81' and  operatype!='83'}">
							<c:if test="${node.nodename=='计皮'}">
								<div class="vertical-timeline-icon lazur-bg">
									<i class="fa fa-balance-scale"></i>
									<!-- <div style="position:absolute;left:-25px;padding-top:80px;">
										<p style="color:#000;font-size:10px;width:80px;">10分钟</p>
									</div> -->
								</div>
								<div class="vertical-timeline-content panel <c:if test="${empty measurelist}">panel-default</c:if><c:if test="${!empty measurelist}">panel-success</c:if>">
									<div class="panel-heading">计皮信息【${node.workpointname}】</div>
									<div class="panel-body">
										<table data-toggle="table" data-mobile-responsive="true">
											<thead>
												<tr align="center" style="FONT-WEIGHT: bold; FONT-SIZE: 10px; MARGIN: 7px 0px; COLOR: #000000">
													<th nowrap="nowrap" alt="taskcode"><font size="2">物流号</font></th>
													<th nowrap="nowrap" class="fieldorder"><font size="2">车号</font></th>
													<th nowrap="nowrap" class="fieldorder"><font size="2">皮重/t</font></th>
													<th nowrap="nowrap" class="fieldorder"><font size="2">皮重时间</font></th>
													<th nowrap="nowrap" class="fieldorder"><font size="2">皮重衡器</font></th>
													<c:if test="${lastmeasure == 1}">
														<th nowrap="nowrap" class="fieldorder"><font size="2">净重/t</font></th>
														<th nowrap="nowrap" class="fieldorder"><font size="2">净重时间</font></th>
													</c:if>
												</tr>
											</thead>
											<tbody>
											<c:forEach items="${measurelist}" var="measure" varStatus="num">
												<tr height="25"  align="center">
													<td nowrap="nowrap"><font size="2">${measure.matchid}&nbsp;</font></td>
													<td nowrap="nowrap"><font size="2">${measure.carno}&nbsp;</font></td>
													<td nowrap="nowrap"><font size="2">${measure.tare}&nbsp;</font></td>
													<td nowrap="nowrap"><font size="2">${measure.taretime}&nbsp;</font></td>
													<td nowrap="nowrap"><font size="2">${measure.tareweigh}&nbsp;</font></td>
													<c:if test="${lastmeasure == 1}">
														<td nowrap="nowrap"><font size="2">${measure.suttle}&nbsp;</font></td>
														<td nowrap="nowrap"><font size="2">${measure.suttletime}&nbsp;</font></td>
													</c:if>
												</tr>
											</c:forEach>
											</tbody>
										</table>
										<c:if test="${lastmeasure == 0}">
											<c:set var="lastmeasure" value="1"/>
										</c:if>
									</div>
								</div>
							</c:if>
							</c:if>
							<c:if test="${operatype!='91' and  operatype!='93' and  operatype!='81' and  operatype!='83'}">
							<c:if test="${node.nodename=='计毛'}">
								<div class="vertical-timeline-icon lazur-bg">
									<i class="fa fa-balance-scale"></i>
									<!-- <div style="position:absolute;left:-25px;padding-top:80px;">
										<p style="color:#000;font-size:10px;width:80px;">10分钟</p>
									</div> -->
								</div>
								<div class="vertical-timeline-content panel <c:if test="${empty measurelist}">panel-default</c:if><c:if test="${!empty measurelist}">panel-success</c:if>">
									<div class="panel-heading">计毛信息【${node.workpointname}】</div>
									<div class="panel-body">
										<table data-toggle="table" data-mobile-responsive="true">
											<thead>
												<tr align="center" style="FONT-WEIGHT: bold; FONT-SIZE: 10px; MARGIN: 7px 0px; COLOR: #000000">
													<th nowrap="nowrap" alt="taskcode"><font size="2">物流号</font></th>
													<th nowrap="nowrap" class="fieldorder"><font size="2">车号</font></th>
													<th nowrap="nowrap" class="fieldorder"><font size="2">毛重/t</font></th>
													<th nowrap="nowrap" class="fieldorder"><font size="2">毛重时间</font></th>
													<th nowrap="nowrap" class="fieldorder"><font size="2">毛重衡器</font></th>
													<c:if test="${lastmeasure == 1}">
														<th nowrap="nowrap" class="fieldorder"><font size="2">净重/t</font></th>
														<th nowrap="nowrap" class="fieldorder"><font size="2">净重时间</font></th>
													</c:if>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${measurelist}" var="measure" varStatus="num">
													<tr height="25"  align="center">
														<td nowrap="nowrap"><font size="2">${measure.matchid}&nbsp;</font></td>
														<td nowrap="nowrap"><font size="2">${measure.carno}&nbsp;</font></td>
														<td nowrap="nowrap"><font size="2">${measure.gross}&nbsp;</font></td>
														<td nowrap="nowrap"><font size="2">${measure.grosstime}&nbsp;</font></td>
														<td nowrap="nowrap"><font size="2">${measure.grossweigh}&nbsp;</font></td>
														<c:if test="${lastmeasure == 1}">
															<td nowrap="nowrap"><font size="2">${measure.suttle}&nbsp;</font></td>
															<td nowrap="nowrap"><font size="2">${measure.suttletime}&nbsp;</font></td>
														</c:if>
													</tr>
												</c:forEach>
												<c:if test="${lastmeasure == 0}">
													<c:set var="lastmeasure" value="1"/>
												</c:if>
											</tbody>
										</table>
									</div>
								</div>
							</c:if>
							</c:if>
							<c:if test="${node.nodename=='入库'}">
								<div class="vertical-timeline-icon yellow-bg">
									<i class="fa fa-sign-in"></i>
									<!-- <div style="position:absolute;left:-25px;padding-top:80px;">
										<p style="color:#000;font-size:10px;width:80px;">10分钟</p>
									</div> -->
								</div>
								<div class="vertical-timeline-content panel <c:if test="${empty storeinlist}">panel-default</c:if><c:if test="${!empty storeinlist}">panel-success</c:if>">
									<div class="panel-heading">入库信息【${node.workpointname}】</div>
									<div class="panel-body">
										<table data-toggle="table" data-mobile-responsive="true">
											<thead>
												<tr align="center" style="FONT-WEIGHT: bold; FONT-SIZE: 10px; MARGIN: 7px 0px; COLOR: #000000">
													<th nowrap="nowrap"><font size="2">物流号</font></th>
													<th nowrap="nowrap"><font size="2">车号</font></th>
													<th nowrap="nowrap"><font size="2">收货库房</font></th>
													<th nowrap="nowrap"><font size="2">入库数量</font></th>
													<th nowrap="nowrap"><font size="2">入库重量/t</font></th>
													<th nowrap="nowrap"><font size="2">入库操作人</font></th>
													<th nowrap="nowrap"><font size="2">入库时间</font></th>
													<th nowrap="nowrap"><font size="2">备注</font></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${storeinlist}" var="storein" varStatus="num">
													<tr height="25"  align="center">
														<td nowrap="nowrap"><font size="2">${storein.matchid}&nbsp;</font></td>
														<td nowrap="nowrap"><font size="2">${storein.carno}&nbsp;</font></td>
														<td nowrap="nowrap"><font size="2">${storein.storename}&nbsp;</font></td>
														<td nowrap="nowrap"><font size="2">${storein.counts}&nbsp;</font></td>
														<td nowrap="nowrap"><font size="2">${storein.suttle}&nbsp;</font></td>
														<td nowrap="nowrap"><font size="2">${storein.createoperaname}&nbsp;</font></td>
														<td nowrap="nowrap"><font size="2">${storein.storeintime}&nbsp;</font></td>
														<td nowrap="nowrap"><font size="2">${storein.usermemo}&nbsp;</font></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							</c:if>
							<c:if test="${node.nodename=='出库'}">
								<div class="vertical-timeline-icon custom-bg">
									<i class="fa fa-sign-out"></i>
									<!-- <div style="position:absolute;left:-25px;padding-top:80px;">
										<p style="color:#000;font-size:10px;width:80px;">10分钟</p>
									</div> -->
								</div>
								<div class="vertical-timeline-content panel <c:if test="${empty storeoutlist}">panel-default</c:if><c:if test="${!empty storeoutlist}">panel-success</c:if>">
									<div class="panel-heading">出库信息【${node.workpointname}】</div>
									<div class="panel-body">
										<table data-toggle="table" data-mobile-responsive="true">
											<thead>
												<tr align="center" style="FONT-WEIGHT: bold; FONT-SIZE: 10px; MARGIN: 7px 0px; COLOR: #000000">
													<th nowrap="nowrap"><font size="2">物流号</font></th>
													<th nowrap="nowrap"><font size="2">车号</font></th>
													<th nowrap="nowrap"><font size="2">发货库房</font></th>
													<th nowrap="nowrap"><font size="2">实际重量/t</font></th>
													<th nowrap="nowrap"><font size="2">出库操作人</font></th>
													<th nowrap="nowrap"><font size="2">出库时间</font></th>
													<th nowrap="nowrap"><font size="2">备注</font></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${storeoutlist}" var="storeout" varStatus="num">
													<tr height="25"  align="center">
														<td nowrap="nowrap"><font size="2">${storeout.matchid}&nbsp;</font></td>
														<td nowrap="nowrap"><font size="2">${storeout.carno}&nbsp;</font></td>
														<td nowrap="nowrap"><font size="2">${storeout.storename}&nbsp;</font></td>
														<td nowrap="nowrap"><font size="2">${storeout.suttle}&nbsp;</font></td>
														<td nowrap="nowrap"><font size="2">${storeout.createoperaname}&nbsp;</font></td>
														<td nowrap="nowrap"><font size="2">${storeout.storeintime}&nbsp;</font></td>
														<td nowrap="nowrap"><font size="2">${storeout.usermemo}&nbsp;</font></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							</c:if>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</body>
</t:html>