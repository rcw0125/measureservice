<%@ page contentType="text/html;charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="common.jsp" flush="true"/>
	<style type="text/css">
		.table>tbody>tr>td{
			vertical-align:top;
		}
		.fixed-table-container tbody td .th-inner, .fixed-table-container thead th .th-inner{
			text-align:center;
		}
		.show-grid [class ^="col-"]{
		 border:1px solid #ddd;
		}
	</style>
</head>
<body class="section container-fluid" style="padding-top:15px;">
	<div class="row-fluid">
		<div class="col-sm-12">
			<ul id="myTabs" class="nav nav-tabs" role="tablist">
				<c:set var="curroperate" />
				<c:forEach items="${displayList}" var="item" varStatus="status">
					<li role="presentation" value="${item.OPERATYPE}"
						class=" <c:if test="${status.index == 0}">active <c:set var="curroperate" value="${item.OPERATYPE}"/></c:if>">
						<a href="#" class="dropdown-toggle"
						onclick="loadDisplayConfig('${item.OPERATYPE}')">${item.OPERANAME}</a>
					</li>
				</c:forEach>
			</ul>
			<div id="myTabContent" class="tab-content">
				<form id="OperaconfigForm">
					<input type="hidden" id="operatype" name="operatype"
						value="${curroperate}">
					<div class="container-fluid">
						<div class="row table-bordered "
							style="background: #CCCCFF; vertical-align: middle;">
							<div class="col-md-3" style="text-align: center;">出入库设置</div>
							<div class="col-md-9">
								<div class="row  table-bordered">
									<div class="col-md-12 form-group input-group"
										style="margin: 0px">
										<span class="input-group-addon" style="width: 35%">出入库设置</span><select
											id="sflag" name="sflag" class="form-control select2"
											placeholder="必选">
											<option value="0">不限制</option>
											<option value="1">出库</option>
											<option value="2">入库</option>
											<option value="3">出入库</option>
										</select>
									</div>
								</div>
								<div class="row table-bordered">
									<div class="col-md-12 form-group input-group"
										style="margin: 0px">
										<span class="input-group-addon" style="width: 35%">无出库处理</span>
										<select id="notstoreoutdo" name="notstoreoutdo"
											class="form-control select2" placeholder="必选">
											<option value="0">不限制</option>
											<option value="1">提示</option>
											<option value="2">选择</option>
											<option value="3">禁止</option>
										</select>
									</div>
								</div>
								<div class="row table-bordered">
									<div class="col-md-12 form-group input-group"
										style="margin: 0px">
										<span class="input-group-addon" style="width: 35%">无入库处理</span>
										<select id="notstoreindo" name="notstoreindo"
											class="form-control select2" placeholder="必选">
											<option value="0">不限制</option>
											<option value="1">提示</option>
											<option value="2">选择</option>
											<option value="3">禁止</option>
										</select>
									</div>
								</div>

							</div>
						</div>
						<div class="row table-bordered " style="background: #CCFFCC">
							<div class="col-md-3" style="text-align: center;">进出厂设置</div>
							<div class="col-md-9">

								<div class="row  table-bordered">
									<div class="col-md-12 form-group input-group"
										style="margin: 0px">
										<span class="input-group-addon" style="width: 35%">进出厂设置</span>
										<select id="gflag" name="gflag" class="form-control select2"
											placeholder="必选">
											<option value="0">不限制</option>
											<option value="1">进厂</option>
											<option value="2">出厂</option>
											<option value="3">进出厂</option>
										</select>
									</div>
								</div>
								<div class="row table-bordered">
									<div class="col-md-12 form-group input-group"
										style="margin: 0px">
										<span class="input-group-addon" style="width: 35%">无进厂处理</span>
										<select id="notentergatedo" name="notentergatedo"
											class="form-control select2" placeholder="必选">
											<option value="0">不限制</option>
											<option value="1">提示</option>
											<option value="2">选择</option>
											<option value="3">禁止</option>
										</select>
									</div>
								</div>
								<div class="row table-bordered">
									<div class="col-md-12 form-group input-group"
										style="margin: 0px">
										<span class="input-group-addon" style="width: 35%">无出厂处理</span>
										<select id="notleavegatedo" name="notleavegatedo"
											class="form-control select2" placeholder="必选">
											<option value="0">不限制</option>
											<option value="1">提示</option>
											<option value="2">选择</option>
											<option value="3">禁止</option>
										</select>
									</div>
								</div>

							</div>
						</div>

						<div class="row table-bordered "
							style="background: #CCCCFF; vertical-align: middle;">
							<div class="col-md-3" style="text-align: center;">取样设置</div>
							<div class="col-md-9">
								<div class="row  table-bordered">
									<div class="col-md-12 form-group input-group"
										style="margin: 0px">
										<span class="input-group-addon" style="width: 35%">取样方式</span>
										<select id="qflag" name="qflag" class="form-control select2"
											placeholder="必选">
											<option value="0">不取样</option>
											<option value="1">计毛前</option>
											<option value="2">收货前</option>
											<option value="3">收货后</option>
										</select>
									</div>
								</div>
								<div class="row table-bordered" style="background: #CCFFCC">
									<div class="col-md-12 form-group input-group"
										style="margin: 0px">
										<span class="input-group-addon" style="width: 35%">无取样处理</span>
										<select id="notslampedo" name="notslampedo"
											class="form-control select2" placeholder="必选">
											<option value="0">不限制</option>
											<option value="1">提示</option>
											<option value="2">选择</option>
											<option value="3">禁止</option>
										</select>
									</div>
								</div>
							</div>
						</div>

						<div class="row table-bordered " style="background: #CCFFCC">
							<div class="col-md-3" style="text-align: center;">发货皮重设置</div>
							<div class="col-md-9">

								<div class="row  table-bordered">
									<div class="col-md-12 form-group input-group"
										style="margin: 0px">
										<span class="input-group-addon" style="width: 35%">发货皮重超差上限/kg</span>
										<input name="tareoutup" type="text" class="form-control"
											aria-describedby="basic-addon1">

									</div>
								</div>
								<div class="row table-bordered">
									<div class="col-md-12 form-group input-group"
										style="margin: 0px">
										<span class="input-group-addon" style="width: 35%">发货皮重超差下限/kg</span>
										<input name="tareoutdown" type="text" class="form-control"
											aria-describedby="basic-addon1">
									</div>
								</div>
								<div class="row table-bordered" style="background: #CCFFCC">
									<div class="col-md-12 form-group input-group"
										style="margin: 0px">
										<span class="input-group-addon" style="width: 35%">发货皮重超差处理</span>
										<select id="tareoutdo" name="tareoutdo"
											class="form-control select2" placeholder="必选">
											<option value="0">不限制</option>
											<option value="1">提示</option>
											<option value="2">选择</option>
											<option value="3">禁止</option>
										</select>
									</div>
								</div>

							</div>
						</div>

						<div class="row table-bordered "
							style="background: #CCCCFF; vertical-align: middle;">
							<div class="col-md-3" style="text-align: center;">发货毛重设置</div>
							<div class="col-md-9">

								<div class="row  table-bordered">
									<div class="col-md-12 form-group input-group"
										style="margin: 0px">
										<span class="input-group-addon" style="width: 35%">发货毛重超差上限/kg</span>
										<input name="grossoutup" type="text" class="form-control"
											aria-describedby="basic-addon1">
									</div>
								</div>
								<div class="row table-bordered">
									<div class="col-md-12 form-group input-group"
										style="margin: 0px">
										<span class="input-group-addon" style="width: 35%">发货毛重超差下限/kg</span>
										<input name="grossoutdown" type="text" class="form-control"
											aria-describedby="basic-addon1">
									</div>
								</div>
								<div class="row table-bordered" style="background: #CCFFCC">
									<div class="col-md-12 form-group input-group"
										style="margin: 0px">
										<span class="input-group-addon" style="width: 35%">发货毛重超差处理</span>
										<select id="grossoutdo" name="grossoutdo"
											class="form-control select2" placeholder="必选">
											<option value="0">不限制</option>
											<option value="1">提示</option>
											<option value="2">选择</option>
											<option value="3">禁止</option>
										</select>
									</div>
								</div>

							</div>
						</div>

						<div class="row table-bordered " style="background: #CCFFCC">
							<div class="col-md-3" style="text-align: center;">发货净重设置</div>
							<div class="col-md-9">

								<div class="row  table-bordered">
									<div class="col-md-12 form-group input-group"
										style="margin: 0px">
										<span class="input-group-addon" style="width: 35%">发货净重超差上限/kg</span>
										<input name="suttleoutup" type="text" class="form-control"
											aria-describedby="basic-addon1">
									</div>
								</div>
								<div class="row table-bordered">
									<div class="col-md-12 form-group input-group"
										style="margin: 0px">
										<span class="input-group-addon" style="width: 35%">发货净重超差下限/kg</span>
										<input name="suttleoutdown" type="text" class="form-control"
											aria-describedby="basic-addon1">
									</div>
								</div>
								<div class="row table-bordered">
									<div class="col-md-12 form-group input-group"
										style="margin: 0px">
										<span class="input-group-addon" style="width: 35%">发货净重超差处理</span>
										<select id="suttleoutdo" name="suttleoutdo"
											class="form-control select2" placeholder="必选">
											<option value="0">不限制</option>
											<option value="1">提示</option>
											<option value="2">选择</option>
											<option value="3">禁止</option>
										</select>
									</div>
								</div>

							</div>
						</div>


						<div class="row table-bordered "
							style="background: #CCCCFF; vertical-align: middle;">
							<div class="col-md-3" style="text-align: center;">重复计量设置</div>
							<div class="col-md-9">

								<div class="row  table-bordered">
									<div class="col-md-12 form-group input-group"
										style="margin: 0px">
										<span class="input-group-addon" style="width: 35%">重复计皮处理</span>
										<select id="retaredo" name="retaredo"
											class="form-control select2" placeholder="必选">
											<option value="0">不限制</option>
											<option value="1">提示</option>
											<option value="2">选择</option>
											<option value="3">禁止</option>
										</select>
									</div>
								</div>
								<div class="row table-bordered">
									<div class="col-md-12 form-group input-group"
										style="margin: 0px">
										<span class="input-group-addon" style="width: 35%">重复计毛处理</span>
										<select id="regrossdo" name="regrossdo"
											class="form-control select2" placeholder="必选">
											<option value="0">不限制</option>
											<option value="1">提示</option>
											<option value="2">选择</option>
											<option value="3">禁止</option>
										</select>
									</div>
								</div>
								<div class="row table-bordered">
									<div class="col-md-12 form-group input-group"
										style="margin: 0px">
										<span class="input-group-addon" style="width: 35%">重复计净处理</span>
										<select id="resuttledo" name="resuttledo"
											class="form-control select2" placeholder="必选">
											<option value="0">不限制</option>
											<option value="1">提示</option>
											<option value="2">选择</option>
											<option value="3">禁止</option>
										</select>
									</div>
								</div>

							</div>
						</div>


						<div class="row table-bordered " style="background: #CCFFCC">
							<div class="col-md-3" style="text-align: center;">计划量设置</div>
							<div class="col-md-9">

								<div class="row  table-bordered">
									<div class="col-md-12 form-group input-group"
										style="margin: 0px">
										<span class="input-group-addon" style="width: 35%">计划量超差上限/kg</span>
										<input name="planweighout" type="text" class="form-control"
											aria-describedby="basic-addon1">
									</div>
								</div>
								<div class="row table-bordered">
									<div class="col-md-12 form-group input-group"
										style="margin: 0px">
										<span class="input-group-addon" style="width: 35%">计划量超差处理</span>
										<select id="checkplanweighdo" name="checkplanweighdo"
											class="form-control select2" placeholder="必选">
											<option value="0">不限制</option>
											<option value="1">提示</option>
											<option value="2">选择</option>
											<option value="3">禁止</option>
										</select>
									</div>
								</div>
							</div>
						</div>
						<div class="row table-bordered "
							style="background: #CCCCFF; vertical-align: middle;">
							<div class="col-md-3" style="text-align: center;">计划车数设置</div>
							<div class="col-md-9">

								<div class="row  table-bordered">
									<div class="col-md-12 form-group input-group"
										style="margin: 0px">
										<span class="input-group-addon" style="width: 35%">计划车数超差上限</span>
										<input name="plancarcountout" type="text" class="form-control"
											aria-describedby="basic-addon1">
									</div>
								</div>
								<div class="row table-bordered">
									<div class="col-md-12 form-group input-group"
										style="margin: 0px">
										<span class="input-group-addon" style="width: 35%">计划车数超差处理</span>
										<select id="checkplancarcountdo" name="checkplancarcountdo"
											class="form-control select2" placeholder="必选">
											<option value="0">不限制</option>
											<option value="1">提示</option>
											<option value="2">选择</option>
											<option value="3">禁止</option>
										</select>
									</div>
								</div>
							</div>
						</div>

						<div class="row table-bordered " style="background: #CCFFCC">
							<div class="col-md-3" style="text-align: center;">计划支/件设置</div>
							<div class="col-md-9">

								<div class="row  table-bordered">
									<div class="col-md-12 form-group input-group"
										style="margin: 0px">
										<span class="input-group-addon" style="width: 35%">计划支/件数超差上限</span>
										<input name="planmaterialcountout" type="text"
											class="form-control" aria-describedby="basic-addon1">
									</div>
								</div>
								<div class="row table-bordered">
									<div class="col-md-12 form-group input-group"
										style="margin: 0px">
										<span class="input-group-addon" style="width: 35%">计划支/件数超差处理</span>
										<select id="checkplanmaterialcountdo"
											name="checkplanmaterialcountdo" class="form-control select2"
											placeholder="必选">
											<option value="0">不限制</option>
											<option value="1">提示</option>
											<option value="2">选择</option>
											<option value="3">禁止</option>
										</select>
									</div>
								</div>
							</div>
						</div>



						<div class="row table-bordered "
							style="background: #CCCCFF; vertical-align: middle;">
							<div class="col-md-3" style="text-align: center;">相似重量设置</div>
							<div class="col-md-9">

								<div class="row  table-bordered">
									<div class="col-md-12 form-group input-group"
										style="margin: 0px">
										<span class="input-group-addon" style="width: 35%">相似皮重上限/kg</span>
										<input name="sametareoutup" type="text" class="form-control"
											aria-describedby="basic-addon1">
									</div>
								</div>
								<div class="row table-bordered">
									<div class="col-md-12 form-group input-group"
										style="margin: 0px">
										<span class="input-group-addon" style="width: 35%">相似皮重下限/kg</span>
										<input name="sametareoutdown" type="text" class="form-control"
											aria-describedby="basic-addon1">
									</div>
								</div>
								<div class="row  table-bordered">
									<div class="col-md-12 form-group input-group"
										style="margin: 0px">
										<span class="input-group-addon" style="width: 35%">相似毛重上限/kg</span>
										<input name="samegrossoutup" type="text" class="form-control"
											aria-describedby="basic-addon1">
									</div>
								</div>
								<div class="row  table-bordered">
									<div class="col-md-12 form-group input-group"
										style="margin: 0px">
										<span class="input-group-addon" style="width: 35%">相似毛重下限/kg</span>
										<input name="samegrossoutdown" type="text"
											class="form-control" aria-describedby="basic-addon1">
									</div>
								</div>
								<div class="row  table-bordered">
									<div class="col-md-12 form-group input-group"
										style="margin: 0px">
										<span class="input-group-addon" style="width: 35%">相似皮重时间/分钟</span>
										<input name="sametaretime" type="text" class="form-control"
											aria-describedby="basic-addon1">
									</div>
								</div>


								<div class="row  table-bordered">
									<div class="col-md-12 form-group input-group"
										style="margin: 0px">
										<span class="input-group-addon" style="width: 35%">相似毛重时间/分钟</span>
										<input name="samegrosstime" type="text" class="form-control"
											aria-describedby="basic-addon1">
									</div>
								</div>

								<div class="row  table-bordered">
									<div class="col-md-12 form-group input-group"
										style="margin: 0px">
										<span class="input-group-addon" style="width: 35%">相似重量处理</span>
										<select id="sameweightdo" name="sameweightdo"
											class="form-control select2" placeholder="必选">
											<option value="0">不限制</option>
											<option value="1">提示</option>
											<option value="2">选择</option>
											<option value="3">禁止</option>
										</select>
									</div>
								</div>

							</div>
						</div>


						<div class="row table-bordered " style="background: #CCFFCC">
							<div class="col-md-3" style="text-align: center;">小票打印设置</div>
							<div class="col-md-9">

								<div class="row  table-bordered">
									<div class="col-md-12 form-group input-group"
										style="margin: 0px">
										<span class="input-group-addon" style="width: 35%">毛重打印联</span>
										<input name="printsetgross" type="text" class="form-control"
											aria-describedby="basic-addon1">
									</div>
								</div>
								<div class="row table-bordered">
									<div class="col-md-12 form-group input-group"
										style="margin: 0px">
										<span class="input-group-addon" style="width: 35%">皮重打印联</span>
										<input name="printsettare" type="text" class="form-control"
											aria-describedby="basic-addon1">
									</div>
								</div>

								<div class="row table-bordered">
									<div class="col-md-12 form-group input-group"
										style="margin: 0px">
										<span class="input-group-addon" style="width: 35%">净重打印联</span>
										<input name="printsetsuttle" type="text" class="form-control"
											aria-describedby="basic-addon1">
									</div>
								</div>

							</div>
						</div>



						<div class="row table-bordered "
							style="background: #CCCCFF; vertical-align: middle;">
							<div class="col-md-3" style="text-align: center;">
								皮重有效期设置<br>(长期有效)
							</div>
							<div class="col-md-9">

								<div class="row  table-bordered">
									<div class="col-md-12 form-group input-group"
										style="margin: 0px">
										<span class="input-group-addon" style="width: 35%">皮重有效期/小时</span>
										<input name="tarehour" type="text" class="form-control"
											aria-describedby="basic-addon1">
									</div>
								</div>
								<div class="row table-bordered">
									<div class="col-md-12 form-group input-group"
										style="margin: 0px">
										<span class="input-group-addon" style="width: 35%">皮重超期处理</span>
										<select id="tarehourdo" name="tarehourdo"
											class="form-control select2" placeholder="必选">
											<option value="0">不限制</option>
											<option value="1">提示</option>
											<option value="2">选择</option>
											<option value="3">禁止</option>
										</select>
									</div>
								</div>
							</div>
						</div>
						<div class="row table-bordered " style="background: #CCFFCC">
							<div class="col-md-3" style="text-align: center;">
								皮重有效期设置<br>(一车一皮)
							</div>
							<div class="col-md-9">

								<div class="row  table-bordered">
									<div class="col-md-12 form-group input-group"
										style="margin: 0px">
										<span class="input-group-addon" style="width: 35%">皮重有效期/小时</span>
										<input name="taretimeout" type="text" class="form-control"
											aria-describedby="basic-addon1">
									</div>
									<!--<div class="col-md-12"></div>-->
								</div>
								<div class="row table-bordered">
									<div class="col-md-12 form-group input-group"
										style="margin: 0px">
										<span class="input-group-addon" style="width: 35%">皮重超期处理</span>
										<select id="taretimeoutdo" name="taretimeoutdo"
											class="form-control select2" placeholder="必选">
											<option value="0">不限制</option>
											<option value="1">提示</option>
											<option value="2">选择</option>
											<option value="3">禁止</option>
										</select>
									</div>
								</div>
							</div>
						</div>
						<div class="row table-bordered " style="background:#CCCCFF">
							<div class="col-md-3" style="text-align: center;">
								皮重超差设置
							</div>
							<div class="col-md-9">

								<div class="row  table-bordered">
									<div class="col-md-12 form-group input-group"
										style="margin: 0px">
										<span class="input-group-addon" style="width: 35%">皮重差值上限/kg</span>
										<input name="checktarelast" type="text" class="form-control"
											aria-describedby="basic-addon1">
									</div>
									<!--<div class="col-md-12"></div>-->
								</div>
								<div class="row table-bordered">
									<div class="col-md-12 form-group input-group"
										style="margin: 0px">
										<span class="input-group-addon" style="width: 35%">皮重超差处理</span>
										<select id="checktaredo" name="checktaredo"
											class="form-control select2" placeholder="必选">
											<option value="0">不限制</option>
											<option value="1">提示</option>
											<option value="2">选择</option>
											<option value="3">禁止</option>
										</select>
									</div>
								</div>
							</div>
						</div>
						<div class="row table-bordered "
							style="background: #CCFFCC; vertical-align: middle;">
							<div class="col-md-3" style="text-align: center;">毛重有效期设置</div>
							<div class="col-md-9">

								<div class="row  table-bordered">
									<div class="col-md-12 form-group input-group"
										style="margin: 0px">
										<span class="input-group-addon" style="width: 35%">毛重有效期/小时</span>
										<input name="grosstimeout" type="text" class="form-control"
											aria-describedby="basic-addon1">
									</div>
								</div>
								<div class="row table-bordered">
									<div class="col-md-12 form-group input-group"
										style="margin: 0px">
										<span class="input-group-addon" style="width: 35%">毛重超期处理</span>
										<select id="grosstimeoutdo" name="grosstimeoutdo"
											class="form-control select2" placeholder="必选">
											<option value="0">不限制</option>
											<option value="1">提示</option>
											<option value="2">选择</option>
											<option value="3">禁止</option>
										</select>
									</div>
								</div>

							</div>
						</div>
						<div class="row table-bordered " style="background: #CCCCFF">
							<div class="col-md-3" style="text-align: center;">环节间隔时间设置</div>
							<div class="col-md-9">

								<div class="row  table-bordered">
									<div class="col-md-12 form-group input-group"
										style="margin: 0px">
										<span class="input-group-addon" style="width: 35%">环节时间间隔/分钟</span>
										<input name="checkoperatime" type="text" class="form-control"
											aria-describedby="basic-addon1">
									</div>
								</div>
								<div class="row table-bordered"
									style="background: #CCCCFF; vertical-align: middle;">
									<div class="col-md-12 form-group input-group"
										style="margin: 0px">
										<span class="input-group-addon text-left" style="width: 35%">环节超时处理</span>
										<select id="checkoperatimedo" name="checkoperatimedo"
											class="form-control select2" placeholder="必选">
											<option value="0">不限制</option>
											<option value="1">提示</option>
											<option value="2">选择</option>
											<option value="3">禁止</option>
										</select>
									</div>
								</div>

							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-success"
							id="baseConfigSaveBtn">保存</button>
					</div>
				</form>
			</div>
		</div>
		<script type="text/javascript">
	jQuery(document).ready(function($){
		
		$('#myTabs a').click(function (e) {
			e.preventDefault()
			$(this).tab('show')
		});
	
		var operateType = $("#operatype").val();
		loadDisplayConfig(operateType) ;       
	});
		function loadDisplayConfig(operateType) {

			$.ajax({
						type : "post",
						url : '<c:url value="/measure/queryOperaconfig.do"/>?operatype='+ operateType,
						dataType : "json",
						success : function(data) {
							loadFormData('OperaconfigForm', data);
						}
					});

		}
		$("#baseConfigSaveBtn").click(function(){
			
			$('#OperaconfigForm').ajaxSubmit({
	            type: 'post',
	            url: '<c:url value="/measure/updateOperaconfig.do"/>',
	            success: function(data){
	            	if (data.success) {
	            	
						toastMessage("success", "提示", data.msg);
						
					} else {
						toastMessage("error", "错误", data.msg);

					}
	            }
	        });
		})
		
		
	</script>
</body>
</html>