<%@page import="org.hibernate.Session"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.panda.pojo.Request"%>
<%@page import="com.panda.pojo.User"%>
<%
	String materialClassifyCode = request.getParameter("materialClassifyCode");
	request.setAttribute("materialClassifyCode", materialClassifyCode);
	HttpSession httpSession = request.getSession();
	User user = (User) httpSession.getAttribute("userInfo");
	request.setAttribute("userRole", user.getRole());
	request.setAttribute("userCname", user.getCname());
%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
	<!-- Google Chrome Frame也可以让IE用上Chrome的引擎: -->
	<meta name="renderer" content="webkit">

	<title>需求计划详情</title>

	<!-- 公共样式 开始 -->
	<link rel="stylesheet" href="../../css/admin_css.css" />
	<link rel="stylesheet" type="text/css" href="../../css/base.css">
	<link rel="stylesheet" type="text/css" href="../../css/iconfont.css">
	<script type="text/javascript" src="../../framework/jquery-1.11.3.min.js"></script>
	<link rel="stylesheet" type="text/css" href="../../layui/css/layui.css">
	<script type="text/javascript" src="../../layui/layui.js"></script>
	<!-- 滚动条插件 -->
	<link rel="stylesheet" type="text/css" href="../../css/jquery.mCustomScrollbar.css">
	<script src="../../framework/jquery-ui-1.10.4.min.js"></script>
	<script src="../../framework/jquery.mousewheel.min.js"></script>
	<script src="../../framework/jquery.mCustomScrollbar.min.js"></script>
	<script src="../../framework/cframe.js"></script>
	<script src="../../js/jquery.js"></script>
	<!-- 仅供所有子页面使用 -->
	<!-- 公共样式 结束 -->
	<script src="../../js/out_intoData.js"></script>
	<!--切换表-->
	<link rel="stylesheet" href="../../css/boostrap.min.css">
	<script src="../../js/jquery.min.js"></script>
	<script src="../../js/bootstrap.min.js"></script>
	<style>
		.searchResult {
			position: absolute;
			top: 50px;
			left: 15px;
			z-index: 10;
			background-color: #FFF;
			width: 100px;
			border: 1px solid #d2d2d2;
			border-radius: 2px;
		}

		.searchResult li {
			padding: 0 10px;
			line-height: 36px;
			white-space: nowrap;
			overflow: hidden;
			text-overflow: ellipsis;
			cursor: pointer;
		}

		.searchResult li:hover {
			background-color: #eee;
		}
		/*弹出选择框*/

		.box {
			position: absolute;
			top: 50%;
			left: 50%;
			width: 400px;
			height: 200px;
			background: #fff;
			border: 2px solid #009688;
			margin: -102px 0 0 -202px;
			display: none;
			/* 默认对话框隐藏 */
		}

		.box.show {
			display: block;
		}

		.box .x {
			font-size: 18px;
			text-align: right;
			display: block;
		}

		.box input {
			width: 80%;
			font-size: 18px;
			margin-top: 5px;
		}
	</style>
</head>

<body>
<div class="cBody">
	<div class="console">
		<div class="layui-form-item" style="margin-left: 40px;">
			<!--<a class="layui-btn" onclick="addbasicInfo('basicInfo')">新增</a>-->
			<button class="layui-btn" id="add_operate">新增</button>
			<a class="layui-btn" onclick="addbasicInfo('basicInfo')">复制</a>
			<a class="layui-btn" onclick="submit()">提交</a>
			<a class="layui-btn" href="base_print.html">打印</a>
			<a class="layui-btn" onclick="addbasicInfo('basicInfo')">导出</a>
			<a class="layui-btn" onclick="addbasicInfo('basicInfo')">附件</a>
			<a class="layui-btn" onclick="addbasicInfo('basicInfo')">提醒</a>
			<a class="layui-btn" href="delete_list.jsp" onclick="menuCAClick('goods_list.jsp',this)">删除</a>
			<a class="layui-btn" href="base_modify.jsp" onclick="addbasicInfo('basicInfo')">关闭/退出</a>
		</div>
	</div>

	<div class="add_plan_desc">
		<!--年度计划 -->
		<div id="first_need" style="margin-left: 80px;width: 100%;margin-top: 20px;">
			<font style="font-size: 16px;color:#000000;"><strong>需求计划类型</strong></font>
			<select id="requestPlanType1" name="requestPlanType" style="background: white;">
				<option value="年度计划">年度计划</option>
				<option value="紧急计划">紧急计划</option>
			</select>
			<font><strong>需求计划编码</strong></font> <input id="requestPlanCode1" name="requestPlanCode" type="text" readonly="true" />
			<font><strong>需求计划名称</strong></font> <input id="requestPlanName1" name="requestPlanName" type="text" />
			<font><strong>备注</strong></font> <input id="requestRemarks1" name="requestRemarks" type="text" />
		</div>
		<div id="second_need" style="margin-left: 80px;width: 100%;margin-top: 20px;">
			<input type="button" onClick="msgbox6(1)" value="需求部门">
			<input type="text" id="requestDeportment1" />
			<div id='depart1' class="box">
				<a class='x' href='' ; onclick="msgbox6(0); return false;">关闭</a>
				<script type="text/javascript" src="../../js/jquery.js"></script>
				<table id="tab">
					<tr>
						<td>财务部</td>
						<td><input type="button" value="财务部" onclick="msgbox6(0); return false;" /></td>
					</tr>
					<tr>
						<td>审批部门</td>
						<td><input type="button" value="审批部门" onclick="msgbox6(0); return false;" /></td>
					</tr>
				</table>

				<script type="text/javascript">
					$(function() {
						$("#tab").on("click", ":button", function(event) {
							$("#requestDeportment").val($(this).closest("tr").find("td").eq(0).text());
						});
					});
				</script>
			</div>
			<font><strong>需求人员</strong></font> <input id="requestPerson1" name="requestPerson" type="text" />
			<font><strong>需求计划状态</strong></font> <input id="requestStatus1" name="requestStatus" type="text" readonly="true" />
			<font><strong>审批状态</strong></font> <input id="approvalStatus1" readonly="readonly" name="approvalStatus" type="text" style="height: 20px;width: 50px;" readonly="true" />
		</div>
		<div style="margin-left: 95px;width: 70%;margin-top: 20px;">
			<font><input type="checkbox" id="allAndNotAll1" /><strong>是否预算内计划</strong></font>
		</div>
	</div>
	<div class="layui-tab" lay-filter="myPage">
		<!--<ul class="layui-tab-title">
<li class="layui-this" lay-id="historyList">基本信息</li>
<li lay-id="todayList">流程信息</li>
<li lay-id="todayList">流程信息</li>
</ul>-->
		<ul id="myTab" class="nav nav-tabs">
			<li class="active">
				<a href="#first_table" data-toggle="tab">
					基本信息
				</a>
			</li>
			<li>
				<a href="#second_table" data-toggle="tab" onclick="getApprovalList()">流程信息</a>

			</li>
			<li>
				<a href="#thrid_table" data-toggle="tab" onclick="systemMassageQuery()">系统消息</a>
			</li>
		</ul>
	</div>
	<div class="layui-tab-content">
		<div class="right_content">
			<div style="height:500px;background: white;margin: 0 auto;overflow-y:scroll;padding-bottom: 15px;margin-left: 12px;margin-right: 12px;">
				<!--阴影效果-->
				<div style="width: 100%;height: 5px;background: #E7EBED;"></div>
				<!--表格部分-->
				<div id="myTabContent" class="tab-content">
					<div class="tab-pane fade in active" id="first_table">
						<table>
							<thead>
							<th> 行号</th>
							<th>物料分类编码</th>
							<th>物料分类名称</th>
							<th>物料编码</th>
							<th>物料名称</th>
							<th>规格</th>
							<th>型号</th>
							<th>单位</th>
							<th>需求数量</th>
							<th>需求月份</th>
							<th>需求日期</th>
							<th>货源是否确定</th>
							<th>期望供应商</th>
							<th>固定供应商</th>
							<th>备注</th>
							<th>物料追踪码</th>
							<th>操作</th>
							</thead>
							<tbody id="t_body">
							<!--显示数据-->
							</tbody>
						</table>
						<!--底部分页部分-->
						<div style="width: 100%;height: 50px;line-height: 50px;text-align: right;">
							<button >上一页</button>
							<button id="next_btn" style="margin-right: 12px;">下一页</button>
						</div>
					</div>
					<div class="tab-pane fade" id="second_table">
						<table>
							<thead>
							<tr>
							<thead>
							<th> </th>
							<th>行号</th>
							<th> </th>
							<th>审批人</th>
							<th> </th>
							<th>审批时间</th>
							<th> </th>
							<th>审批意见</th>

							<th>说明</th>
							</thead>
							</tr>
							</thead>
							<tbody id="t_body1">
							<!--显示审批表数据-->
							</tbody>
						</table>
					</div>
					<div class="tab-pane fade" id="thrid_table">
						<div class="add_window_desc">
							<font>* -编制人:</font> <input id="createPerson" required lay-verify="required" type="text" />
						</div>
						<div class="add_window_desc">
							<font>* 编制时间:</font> <input id="createTime" type="text" />
						</div>
						<div class="add_window_desc">
							<font>* 修改人:</font> <input id="modifyPerson" type="text" />
						</div>
						<div class="add_window_desc">
							<font>* 修改时间:</font> <input id="updateTime" type="text" />
						</div>
						<div class="add_window_desc">
							<font>* 修改原因:</font> <input id="modifyReason" type="text" />
						</div>
						<!--<div id="createPerson" style="float:left;width: 70%;margin-top: 20px;margin-left: 70px;height: 45px;line-height: 45px;">
                            <font><strong>编制人</strong></font>
                            <input name="Splan_coding" type="text" style="width: 40%;height: 25px;text-align: center;" />
                        </div>
                        <div id="createTime" style="float:left;width: 70%;margin-top: 20px;margin-left: 70px;height: 45px;line-height: 45px;">
                            <font><strong>编制时间</strong></font>
                            <input name="Splan_name" type="text" style="width: 40%;height: 25px;" />
                        </div>
                        <div id="modifyPerson" style="float:left;width: 70%;margin-top: 20px;margin-left: 70px;height: 45px;line-height: 45px;">
                            <font><strong>修改人</strong></font>
                            <input name="SrequestRemarks" type="text" style="width: 40%;height: 25px;" />
                        </div>
                        <div id="updateTime" style="float:left;width: 70%;margin-top: 20px;margin-left: 70px;height: 45px;line-height: 45px;">
                            <font><strong>修改时间</strong></font> <input id="Splan_coding" name="plan_coding" type="text" style="width: 40%;height: 25px;" />
                        </div>
                        <div id="modifyReason" style="float:left;width: 70%;margin-top: 20px;margin-left: 70px;height: 45px;line-height: 45px;">
                            <font><strong>修改原因</strong></font>
                            <input name="Splan_reason" type="text" style="width: 40%;height: 25px;" />
                        </div>-->
					</div>
				</div>

			</div>
		</div>
	</div>
	<!--1、物料分类编码弹出选择框
<div id='inputbox' class="box">
<script type="text/javascript" src="../../js/jquery.js"></script>
<a class='x' href='' ; onclick="msgbox(0); return false;">关闭</a>
<table >
<tbody id="classificationCode">
<tr>
<td>物料1</td>
<td><input type="button" value="物料1" onclick="msgbox(0); return false;" /></td>
</tr>
<tr>
<td>物料2</td>
<td><input type="button" value="物料2" onclick="msgbox(0); return false;" /></td>
</tr>
<tr>
<td>物料3</td>
<td><input type="button" value="物料3" onclick="msgbox(0); return false;" /></td>
</tr>
<tr>
<td>物料4</td>
<td><input type="button" value="物料4" onclick="msgbox(0); return false;" /></td>
</tr>
</tbody>
</table>
<script type="text/javascript">
$(function() {
$("#classificationCode").on("click", ":button", function(event) {
$("#classificationCode").val($(this).closest("tr").find("td").eq(0).text());
});
});
//There are a function to get MaterialMainDAta
function getMaterialMainDAta(){
$.ajax({
type:"post",
url:"http://123.56.68.20:8080/request/getMaterialMainDAta",
data: null,
dataType:"json",
success:function(ret){
console.log(JSON.stringify(ret));
if(ret.status == "1") {
showMaterialMainDAta(ret.data);
}
}
});
}
function showMaterialMainDAta(list){

}
</script>
</div>-->

	<!-----------------右边内容区-------------------------------------------->
	<!--主要内容区域-->
	<div id="main_content"></div>

	<!--添加窗口部分，默认是隐藏的-->
	<div id="add_window">
		<div class="first_div" style="height: 70%;width: 60%;">
			<div class="first_first_div">
				<!--新增弹出顶部-->
				<div class="add_window_content_top">
					<font id="addWinDesc">新增/编辑订单</font> <img id="close_window" src="../../images/close_btn.png" />
				</div>
				<!--表单上半部分内容-->
				<div style="width: 100%; height: auto; position: relative;">
					<table style="width: auto;height: auto;">
						<td>
							<div class="add_window_desc">
								<font>* 需求计划类型</font>
								<select id="requestPlanType" name="requestPlanType">
									<option value="年度计划">年度计划</option>
									<option value="紧急计划">紧急计划</option>
								</select>
							</div>

							<div class="add_window_desc">
								<font>* 需求计划名称:</font> <input id="requestPlanName" required lay-verify="required" type="text" />
							</div>

							<div class="add_window_desc">
								<font>- 需求计划备注:</font> <input id="requestRemarks" type="text" />
							</div>
							<div class="add_window_desc">
								<font>* 需求计划部门:</font> <input id="requestDeportment" type="button" onclick="msgbox6(1)" style="text-align: left;" />
								<div id='depart' class="box">
									<a class='x' href='' ; onclick="msgbox6(0); return false;">关闭</a>
									<script type="text/javascript" src="../../js/jquery.min.js"></script>
									<table id="tabb">
										<tr>
											<td>10号部门</td>
											<td><input type="button" value="√" onclick="msgbox6(0); return false;" /></td>
										</tr>
										<tr>
											<td>20号部门</td>
											<td><input type="button" value="√" onclick="msgbox6(0); return false;" /></td>
										</tr>
										<tr>
											<td>30号部门</td>
											<td><input type="button" value="√" onclick="msgbox6(0); return false;" /></td>
										</tr>
										<tr>
											<td>40号部门</td>
											<td><input type="button" value="√" onclick="msgbox6(0); return false;" /></td>
										</tr>
									</table>
									<script type="text/javascript">
										$(function() {
											$("#tabb").on("click", ":button", function(event) {
												$("#requestDeportment").val($(this).closest("tr").find("td").eq(0).text());
											});
										});
									</script>
								</div>
							</div>
							<div class="add_window_desc">
								<font>* 需求计划人员:</font> <input id="requestPerson" type="text" />
							</div>
							<div class="add_window_desc">
								<font>* 物料分类编码:</font> <input id="classificationCode" type="text" onclick="msgbox(1)" style="text-align: left;" />
								<!--1、物料分类编码弹出选择框-->
								<div id='inputbox' class="box">
									<script type="text/javascript" src="../../js/jquery.min.js"></script>
									<a class='x' href='' ; onclick="msgbox(0); return false;">关闭</a>
									<table id="tab1">
										<tr>
											<td>物料分类编码1号</td>
											<td><input type="button" value="√" onclick="msgbox(0); return false;" /></td>
											<input id="classificationNameD1" value="物料分类名称一号" hidden="true" />
										</tr>
										<tr>
											<td>物料分类编码2号</td>
											<td><input type="button" value="√" onclick="msgbox(0); return false;" /></td>
											<input id="classificationNameD2" value="物料分类名称二号" hidden="true" />
										</tr>
									</table>
									<script type="text/javascript">
										$(function() {
											$("#tab1").on("click", ":button", function(event) {
												$("#classificationCode").val($(this).closest("tr").find("td").eq(0).text());
											});
										});
									</script>
								</div>
							</div>
							<div class="add_window_desc">
								<font>* 物料分类名称:</font> <input type="text" id="classificationName" />
							</div>

							<div class="add_window_desc">
								<font>* 货源是否确定:</font>
								<select id="sureSource" name="goodsType">
									<option value="是">是</option>
									<option value="否">否</option>
								</select>

							</div>
							<div class="add_window_desc">
								<font>* 期望供应商:</font> <input type="button" id="wishSupplier" onclick="msgbox4(1)" style="text-align: left;" />
								<!--4、期望供应商弹出选择框-->
								<div id='inputproviderbox' class="box">
									<script type="text/javascript" src="../../js/jquery.min.js"></script>
									<a class='x' href='' ; onclick="msgbox4(0); return false;">关闭</a>
									<table id="tab5">
										<tr>
											<td>期望供应商一号</td>
											<td><input type="button" value="√" onclick="msgbox4(0); return false;" /></td>
										</tr>
										<tr>
											<td>期望供应商二号</td>
											<td><input type="button" value="√" onclick="msgbox4(0); return false;" /></td>
										</tr>
									</table>
									<script type="text/javascript">
										$(function() {
											$("#tab5").on("click", ":button", function(event) {
												$("#wishSupplier").val($(this).closest("tr").find("td").eq(0).text());
											});
										});
									</script>
								</div>
							</div>
							<div class="add_window_desc">
								<font>* 固定供应商:</font> <input type="button" id="fixedSupplier" onclick="msgbox5(1)" style="text-align: left;" />
								<!--5、固定供应商弹出选择框-->
								<div id='inputsupplierbox' class="box">
									<script type="text/javascript" src="../../js/jquery.min.js"></script>
									<a class='x' href='' ; onclick="msgbox5(0); return false;">关闭</a>
									<table id="tab8">
										<tr>
											<td>固定供应商一号</td>
											<td><input type="button" value="√" onclick="msgbox5(0); return false;" /></td>
										</tr>
										<tr>
											<td>固定供应商二号</td>
											<td><input type="button" value="√" onclick="msgbox5(0); return false;" /></td>
										</tr>
									</table>
									<script type="text/javascript">
										$(function() {
											$("#tab8").on("click", ":button", function(event) {
												$("#fixedSupplier").val($(this).closest("tr").find("td").eq(0).text());

											});
										});
									</script>
								</div>
							</div>
						</td>
						<td>
							<div class="add_window_desc">
								<font>* 物料编码: </font> <input type="text" id="materialCode" onclick="msgbox2(1)" style="text-align: left;" />
								<!--2、物料编码弹出选择框-->
								<div id='inputcodebox' class="box">
									<script type="text/javascript" src="../../js/jquery.min.js"></script>
									<a class='x' href='' ; onclick="msgbox2(0); return false;">关闭</a>
									<table id="tab3">
										<tr>
											<td>物料名称一号</td>
											<td><input type="button" value="√" onclick="msgbox2(0); return false;" /></td>
											<input id="materialNameD1" value="物料名称一号" hidden="true" />

										</tr>
										<tr>
											<td>物料名称二号</td>
											<td><input type="button" value="√" onclick="msgbox2(0); return false;" /></td>
											<input id="materialNameD2" value="物料名称二号" hidden="true" />
										</tr>
										<tr>
											<td>物料名称三号</td>
											<td><input type="button" value="√" onclick="msgbox2(0); return false;" /></td>
											<input id="materialNameD3" value="物料名称三号" hidden="true" />
										</tr>
									</table>
									<script type="text/javascript">
										$(function() {
											$("#tab3").on("click", ":button", function(event) {
												$("#materialCode").val($(this).closest("tr").find("td").eq(0).text());
											});
										});
									</script>
								</div>
							</div>
							<div class="add_window_desc">
								<font>* 物料名称: </font> <input type="text" id="materialName" />
							</div>
							<div class="add_window_desc">
								<font>* 物料规格: </font> <input type="text" id="type" />
							</div>
							<div class="add_window_desc">
								<font>* 物料型号: </font> <input type="text" id="specification" />
							</div>
							<div class="add_window_desc">
								<font>* 物料单位: </font> <input type="text" id="unit" onclick="msgbox3(1)" style="text-align: left;" />
								<!--3、单位弹出选择框-->
								<div id='inputunitbox' class="box">
									<a class='x' href='' ; onclick="msgbox3(0); return false;">关闭</a>
									<script type="text/javascript" src="../../js/jquery.min.js"></script>
									<table id="tab4">
										<tr>
											<td>单位一号</td>
											<td><input type="button" value="√" onclick="msgbox3(0); return false;" /></td>
										</tr>
										<tr>
											<td>单位二号</td>
											<td><input type="button" value="√" onclick="msgbox3(0); return false;" /></td>
										</tr>
										<tr>
											<td>单位三号</td>
											<td><input type="button" value="√" onclick="msgbox3(0); return false;" /></td>
										</tr>
									</table>
									<script type="text/javascript">
										$(function() {
											$("#tab4").on("click", ":button", function(event) {
												$("#unit").val($(this).closest("tr").find("td").eq(0).text());
											});
										});
									</script>
								</div>
							</div>
							<div class="add_window_desc">
								<font>* 需求数量: </font> <input type="text" id="quantity" />
							</div>
							<div class="add_window_desc">
								<font>* 需求月份: </font> <input type="text" id="requestMonth" />
							</div>
							<div class="add_window_desc">
								<font>* 需求日期: </font> <input type="text" id="requestDate" />

							</div>
							<div class="add_window_desc">
								<font> 物料备注:</font> <input type="text" id="remarks" />
							</div>
						</td>
					</table>


					<!--关闭按钮-->
					<div style="width: 100%; height: 50px; text-align: center; margin-top: 30px;">
						<button style="width: 60px; height: 35px; background: #787878; margin-right: 20%;" onclick="close_add_win()">关闭</button>
						<button id="saveBtn" style="width: 60px; height: 35px; background: #209e85;" onclick="addGoods()">保存</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--增加删除按钮对话提示框-->
	<div id="add_dialog_window" style="width: 100%; height: 150%; background: rgba(0, 0, 0, 0.5); position: absolute; top: 0; overflow-y: scroll; display: none;">
		<div style="width: 30%; height: 30%; background: white; margin: 0 auto; position: relative; top: 25%; border-radius: 10px;">
			<div style="width: 100%; height: 40px; background: #008EBC; position: relative; border-top-left-radius: 10px; border-top-right-radius: 10px; line-height: 40px;">
				<font style="margin-left: 12px; font-size: 18px; color: white;">温馨提示：</font>
			</div>
			<div style="width: 100%; height: 50%; display: -webkit-box; -webkit-box-align: center; -webkit-box-pack: center;">
				<font style="margin: 0 auto; font-size: 16px; color: #353535;">您确定需要进行删除操作吗？</font>
			</div>
			<div style="width: 100%; height: 45px; text-align: right; line-height: 45px;">
				<button style="background-color: #878787;" onclick="hide_dialog();">取消</button>
				<button style="margin-right: 40px; margin-left: 15px;" onclick="deleteGoodsById();">确定</button>
			</div>
		</div>
	</div>
</div>
<input id="cMaterialClassifyCode" value="${materialClassifyCode}" hidden="true" />
<input id="userCname" value="${userCname}" hidden="true" />
<script>
	layui.use(['form', 'laydate'], function() {
		var form = layui.form;
		var laydate = layui.laydate;

		//监听提交
		form.on('submit(formDemo)', function(data) {
			layer.msg(JSON.stringify(data.field));
			return false;
		});

		//系统时间设置
		var newDate = new Date();
		nowTime = newDate.getFullYear() + "-" + (newDate.getMonth() + 1);
		laydate.render({
			elem: '#requestMonth',
			format: 'yyyyMM'
		});

		var newDate = new Date();
		nowTime = newDate.getFullYear() + "-" + (newDate.getMonth() + 1);
		laydate.render({
			elem: '#requestDate',
			format: 'yyyyMMdd'
		});
	});

	//显示从查询页面传过来的物料编码号查询到的详情页面
	    loadchangepage();

	function loadchangepage() {
		var cMaterialClassifyCode = $("#cMaterialClassifyCode").val();
		//alert(cMaterialClassifyCode);
		edit_win(cMaterialClassifyCode);
	}
	loadGoodsListView();
	//需求管理所对应的数据页面
	function loadGoodsListView() {

		//1、新增弹出窗口事件
		$("#add_operate").click(function() {
			open_add_win();
		});

		//2、关闭弹出窗口事件
		$("#close_window").click(function() {
			close_add_win();
		});
	}
	//进入页面就打开新增页面

	//打开新增窗口
	function open_add_win() {
		//新增前先清除之前可能被赋值过的input标签
		clearAddWindowValues();
		$("#addWinDesc").html("新增需求");
		$("#saveBtn").attr("onclick", "addGoods()");
		$("#add_window").css("display", "block");
	}

	//关闭新增窗口
	function close_add_win() {
		$("#add_window").css("display", "none");
	}

	//系统消息请求后台数据
	function systemMassageQuery() {
		var requestPlanCode = $("#requestPlanCode1").val();
		if(null == requestPlanCode || "" == requestPlanCode) {
			alert("请输入需求计划编码");
			return;
		}

		var json = {
			requestPlanCode: requestPlanCode
		}
		alert("你将要查询需求编码为" + requestPlanCode + "的系统消息情况？");
		$.ajax({
			type: "GET",
			url: "http://123.56.68.20:8080/Batik/systemMassageQuery",
			data: json,
			dataType: "json",
			success: function(ret) {
				console.log(ret.data);
				if(ret.status == "1") {
					var obj = ret.data;
					$("#updateTime").val(obj.updateTime);
					$("#modifyReason").val(obj.modifyReason);
					$("#createTime").val(obj.createTime);
					$("#createPerson").val(obj.createPerson);
					$("#modifyPerson").val(obj.modifyPerson);

				}
			}
		});
	}

	var goodsUniqueId = null;

	//打开编辑窗口
	function edit_win(materialTrackingCode) {
		$("#addWinDesc").html("需求计划详情");
		$("#saveBtn").attr("onclick", "updateGoods()");
		$("#add_window").css("display", "block");

		var json = {
			materialTrackingCode: materialTrackingCode
		}

		//根据需求物料编码查询订单信息
		$.ajax({
			url: "http://123.56.68.20:8080/Batik/goods/queryById",
			type: "POST",
			data: json,
			dataType: "json",
			success: function(ret) {
				console.log(JSON.stringify(ret));
				if(ret.status == "1") {
					var obj = ret.data; //先给input标签赋值
					$("#classificationCode").val(obj.classificationCode);
					$("#classificationName").val(obj.classificationName);
					$("#materialCode").val(obj.materialCode);
					$("#materialName").val(obj.materialName);
					$("#type").val(obj.type);
					$("#specification").val(obj.specification);
					$("#unit").val(obj.unit);
					$("#quantity").val(obj.quantity);
					$("#requestMonth").val(obj.requestMonth);
					$("#requestDate").val(obj.requestDate);
					$("#sureSource").val(obj.sureSource);
					$("#wishSupplier").val(obj.wishSupplier);
					$("#fixedSupplier").val(obj.fixedSupplier);
					$("#remarks").val(obj.remarks);
					$("#materialTrackingCode").val(obj.materialTrackingCode);
					//$("#showChooseImage").attr("src", obj.goodsPicUrl);
					$("#requestPlanType").val(obj.requestPlanType);
					$("#requestPlanName").val(obj.requestPlanName);
					$("#requestRemarks").val(obj.requestRemarks);
					$("#requestDeportment").val(obj.requestDeportment);
					$("#requestPerson").val(obj.requestPerson);
					$("#requestPlanType1").val(obj.requestPlanType);
					$("#requestPlanCode1").val(obj.requestPlanCode);
					$("#requestPlanName1").val(obj.requestPlanName);
					$("#requestRemarks1").val(obj.requestRemarks);
					$("#requestDeportment1").val(obj.requestDeportment);
					$("#requestPerson1").val(obj.requestPerson);
					$("#requestStatus1").val(obj.requestStatus);
					$("#approvalStatus1").val(obj.approvalStatus);
					//复制给全局变量
					goodsUniqueId = materialTrackingCode;
				}
			}
		});

	}
	//清除新增弹窗的input标签中的值
	function clearAddWindowValues() {
		$("#requestPlanType").val("");
		$("#requestPlanName").val("");
		$("#requestRemarks").val("");
		$("#requestDeportment").val("");
		$("#requestPerson").val("");
		$("#classificationCode").val("");
		$("#classificationName").val("");
		$("#materialCode").val("");
		$("#materialName").val("");
		$("#type").val("");
		$("#specification").val("");
		$("#unit").val("");
		$("#quantity").val("");
		$("#requestMonth").val("");
		$("#requestDate").val("");
		$("#sureSource").val("");
		$("#wishSupplier").val("");
		$("#fixedSupplier").val("");
		$("#remarks").val("");

		//$("#showChooseImage").attr("src", "");
	}

	//定义一个全局变量，用于存放欲被删除的行
	var deleteValue = null;

	//显示删除提示对话框
	function show_dialog(el) {
		$("#add_dialog_window").css("display", "block");
		deleteValue = $(el).attr("value");
	}

	//隐藏删除提示对话框
	function hide_dialog() {
		//清空全局变量
		deleteValue = null;
		$("#add_dialog_window").css("display", "none");
	}

	//根据ID删除订单
	function deleteGoodsById() {

		if(deleteValue == null) {
			alert("当前订单信息不全，不能删除");
			return;
		}

		var json = {
			goodsId: deleteValue
		}

		//根据订单id查询需求计划信息
		$.ajax({
			url: "http://123.56.68.20:8080/Batik/goods/deleteById",
			type: "POST",
			data: json,
			dataType: "json",
			success: function(ret) {
				console.log(ret.data);
				if(ret.status == "1") {
					//显示数据
					submit();
					//关闭对话框
					hide_dialog();
					//重新刷新页面
					loadGoodsListView();
				}
			}
		});

	}
	//新增需求计划
	function addGoods() {
		//年度计划
		var requestPlanType = $("#requestPlanType").val();
		if(requestPlanType == null || requestPlanType == "") {
			alert("请输入需求计划类型");
			return;
		}
		//需求计划名称
		var requestPlanName = $("#requestPlanName").val();
		if(requestPlanName == null || requestPlanName == "") {
			alert("请输入需求计划名称");
			return;
		}
		//需求备注
		var requestRemarks = $('#requestRemarks').val();
		//alert("需求备注" + requestRemarks);
		//需求部门
		var requestDeportment = $("#requestDeportment").val();
		if(requestDeportment == null || requestDeportment == "") {
			alert("请输入需求部门");
			return;
		}
		//需求人员
		var requestPerson = $("#requestPerson").val();
		if(requestPerson == null || requestPerson == "") {
			alert("请输入需求人员");
			return;
		}
		//需求计划状态,保存的时候自动生成,初始值为0
		var requestStatus = "自由";
		//审批状态，初始值为0
		var approvalStatus = 2;
		//是否预算计划内
		//		var allAndNotAll = $("input[type='checkbox']").is(':checked');
		//		alert("是否计划内" + allAndNotAll)
		//表格部分
		//1、物料分类编码
		var classificationCode = $("#classificationCode").val();
		if(null == classificationCode || "" == classificationCode) {
			alert("请选择物料分类编码");
			//return;
		}
		//2、物料分类名称，由系统代入
		var classificationName = $("#classificationName").val();
		if(null == classificationName || "" == classificationName) {
			alert("请选择物料分类名称");
			return;
		}
		//3、物料编码
		var materialCode = $("#materialCode").val();
		if(null == materialCode || "" == materialCode) {
			alert("请选择物料编码");
			return;
		}
		//4、物料名称，由物料编码带出
		//var materialName = $("#materialName").val();
		var materialName = $("#materialName").val();
		if(null == materialName || "" == materialName) {
			alert("请选择物料名称");
			return;
		}
		//5、规格
		var type = $("#type").val();
		if(null == type || "" == type) {
			alert("规格");
			return;
		}
		//6、型号
		var specification = $("#specification").val();
		if(null == specification || "" == specification) {
			alert("请输入型号");
			return;
		}
		//7、单位
		var unit = $("#unit").val();
		if(null == unit || "" == unit) {
			alert("请输入单位");
			return;
		}
		//8、需求数量
		var quantity = $("#quantity").val();
		if(null == quantity || "" == quantity) {
			alert("请输入需求数量");
			return;
		}
		//9、需求月份
		var requestMonth = $("#requestMonth").val();
		if(null == requestMonth || "" == requestMonth) {
			alert("请输入需求月份");
			return;
		}
		//10、需求日期
		var requestDate = $("#requestDate").val();
		if(null == requestDate || "" == requestDate) {
			alert("请输入需求日期");
		}
		//11、货源是否确定
		var sureSource = $("#sureSource").val();
		if(null == sureSource || "" == sureSource) {
			alert("请输入货源是否确定");
		}
		//12、期望供应商
		var wishSupplier = $("#wishSupplier").val();
		if(null == wishSupplier || "" == wishSupplier) {
			alert("请输入期望供应商");
		}
		//13、固定供应商
		var fixedSupplier = $("#fixedSupplier").val();
		if(null == fixedSupplier || "" == fixedSupplier) {
			alert("请输入固定供应商");
		}
		//14、备注
		var remarks = $("#remarks").val();
		var userCname = $("#userCname").val();

		var json = {
			requestPlanType: requestPlanType,
			requestPlanName: requestPlanName,
			requestRemarks: requestRemarks,
			requestDeportment: requestDeportment,
			requestPerson: requestPerson,
			requestStatus: requestStatus,
			approvalStatus: approvalStatus,
			//"allAndNotAll": allAndNotAll,
			//表
			classificationCode: classificationCode,
			classificationName: classificationName,
			materialCode: materialCode,
			materialName: materialName,
			type: type,
			specification: specification,
			unit: unit,
			quantity: quantity,
			requestMonth: requestMonth,
			requestDate: requestDate,
			sureSource: sureSource,
			wishSupplier: wishSupplier,
			fixedSupplier: fixedSupplier,
			remarks: remarks,
			userCname: userCname
		}

		$.ajax({
			type: "POST",
			url: "http://123.56.68.20:8080/Batik/request/save",
			data: json,
			dataType: "json",
			success: function(result) {
				console.log(JSON.stringify(result));
				if(result.status == 1) {
					alert("保存成功");
					//成功后需要关闭当前的窗口
					close_add_win();
					//重新加载一遍，相当于刷新一次
					loadGoodsListView();
				}
			}
		});
	}

	//调用获取需求列表方法
	function getGoodsList() {
		$.ajax({
			type: "GET",
			url: "http://123.56.68.20:8080/Batik/goods/queryAll",
			data: null,
			dataType: "json",
			success: function(ret) {
				console.log(JSON.stringify(ret));
				if(ret.status == "1") {
					showGoodsList(ret.data);
				}
			}
		});
	}

	//调用获取审批列表方法
	function getApprovalList() {
		var requestPlanCode = $("#requestPlanCode1").val();
		if(null == requestPlanCode || "" == requestPlanCode) {
			alert("请输入需求计划编码");
			return;
		}

		var json = {
			requestPlanCode: requestPlanCode
		}
		alert("你将要查询需求编码为" + requestPlanCode + "的审批情况？");
		$.ajax({
			type: "GET",
			url: "http://123.56.68.20:8080/Batik/goods/queryApproval",
			data: json,
			dataType: "json",
			success: function(ret) {
				console.log(JSON.stringify(ret));
				if(ret.status == "1") {
					showApprovalList(ret.data);

				}
			}
		});
	}

	function showApprovalList(list) {
		//添加先清空
		$("#t_body1").empty();
		var html = '';
		for(var i = 0; i < list.length; i++) {
			html += '<tr id="' + list[i].requestPlanCode + '">' +
					'<td>' + " " + '</td>' +
					'<td>' + i + '</td>' +
					'<td>' + " " + '</td>' +
					'<td>' + list[i].approvalPerson + '</td>' +
					'<td>' + " " + '</td>' +
					'<td>' + list[i].approvalTime + '</td>' +
					'<td>' + " " + '</td>' +
					'<td>' + list[i].approvalOpinion + '</td>' +
					'<td>' + " " + '</td>';
			if(typeof(list[i].explain) == "undefined") {
				html += '<td>无</td>';
			} else {
				html += '<td>' + list[i].explain + '</td>';
			}

		}

		$("#t_body1").html(html);

	}
	//提交申请表单
	function submit() {
		alert("提交成功");
		//显示数据
		getGoodsList();
	}
	//更新/编辑/修改申请表单
	function updateGoods() {
		alert("详情页面，不可编辑，如果您需编辑进进入修改页面！！！");
		//物料追踪码
		if(goodsUniqueId == null) {
			alert("未检测到物料追踪码");
			return;
		}

		//年度计划
		var requestPlanType = $("#requestPlanType").val();
		if(requestPlanType == null || requestPlanType == "") {
			alert("请输入需求计划类型");
			return;
		}
		//需求计划名称
		var requestPlanName = $("#requestPlanName").val();
		if(requestPlanName == null || requestPlanName == "") {
			alert("请输入需求计划名称");
			return;
		}
		//需求备注
		var requestRemarks = $('#requestRemarks').val();

		//需求部门
		var requestDeportment = $("#requestDeportment").val();
		if(requestDeportment == null || requestDeportment == "") {
			alert("请输入需求部门");
			return;
		}
		//需求人员
		var requestPerson = $("#requestPerson").val();
		if(requestPerson == null || requestPerson == "") {
			alert("请输入需求人员");
			return;
		}
		//需求计划状态,保存的时候自动生成,初始值为0
		var requestStatus = "自由";
		//审批状态，初始值为0
		var approvalStatus = 0;
		//是否预算计划内
		//		var allAndNotAll = $("input[type='checkbox']").is(':checked');
		//		alert("是否计划内" + allAndNotAll)
		//表格部分
		//1、物料分类编码
		var classificationCode = $("#classificationCode").val();
		if(null == classificationCode || "" == classificationCode) {
			alert("请选择物料分类编码");
			//return;
		}
		//2、物料分类名称，由系统代入
		var classificationName = $("#classificationName").val();
		if(null == classificationName || "" == classificationName) {
			alert("请选择物料分类名称");
			return;
		}
		//3、物料编码
		var materialCode = $("#materialCode").val();
		if(null == materialCode || "" == materialCode) {
			alert("请选择物料编码");
			return;
		}
		//4、物料名称，由物料编码带出
		//var materialName = $("#materialName").val();
		var materialName = $("#materialName").val();
		if(null == materialName || "" == materialName) {
			alert("请选择物料名称");
			return;
		}
		//5、规格
		var type = $("#type").val();
		if(null == type || "" == type) {
			alert("规格");
			return;
		}
		//6、型号
		var specification = $("#specification").val();
		if(null == specification || "" == specification) {
			alert("请输入型号");
			return;
		}
		//7、单位
		var unit = $("#unit").val();
		if(null == unit || "" == unit) {
			alert("请输入单位");
			return;
		}
		//8、需求数量
		var quantity = $("#quantity").val();
		if(null == quantity || "" == quantity) {
			alert("请输入需求数量");
			return;
		}
		//9、需求月份
		var requestMonth = $("#requestMonth").val();
		if(null == requestMonth || "" == requestMonth) {
			alert("请输入需求月份");
			return;
		}
		//10、需求日期
		var requestDate = $("#requestDate").val();
		if(null == requestDate || "" == requestDate) {
			alert("请输入需求日期");
		}
		//11、货源是否确定
		var sureSource = $("#sureSource").val();
		if(null == sureSource || "" == sureSource) {
			alert("请输入货源是否确定");
		}
		//12、期望供应商
		var wishSupplier = $("#wishSupplier").val();
		//alert("期望供应商" + wishSupplier);
		//13、固定供应商
		var fixedSupplier = $("#fixedSupplier").val();
		//alert("固定供应商" + fixedSupplier);
		//14、备注
		var remarks = $("#remarks").val();
		//alert("备注" + remarks);

		var json = {
			id: goodsUniqueId,
			requestPlanType: requestPlanType,
			requestPlanName: requestPlanName,
			requestRemarks: requestRemarks,
			requestDeportment: requestDeportment,
			requestPerson: requestPerson,
			requestStatus: requestStatus,
			approvalStatus: approvalStatus,
			classificationCode: classificationCode,
			classificationName: classificationName,
			materialCode: materialCode,
			materialName: materialName,
			type: type,
			specification: specification,
			unit: unit,
			quantity: quantity,
			requestMonth: requestMonth,
			requestDate: requestDate,
			sureSource: sureSource,
			wishSupplier: wishSupplier,
			fixedSupplier: fixedSupplier,
			remarks: remarks
		}
		$.ajax({
			type: "POST",
			url: "http://123.56.68.20:8080/Batik/goods/update",
			data: json,
			dataType: "json",
			success: function(result) {
				if(result.status == 1) {
					//成功后需要关闭当前的窗口
					close_add_win();
					//重新加载一遍，相当于刷新一次
					loadGoodsListView();
					//重新给全局变量复制为null
					goodsUniqueId = null;
				}
			}
		});
	}
	//搜索按钮弹起
	function seachKeyup(_this) {
		var w = $(_this).width() + 10;
		$(_this).siblings().remove();
		//							弹出选择框
		var str = '<ul class="searchResult" style="width: ' + w + 'px">' +
				'<li onclick="seachChoose(this)">测试11</li>' +
				'<li onclick="seachChoose(this)">测试11</li>' +
				'<li onclick="seachChoose(this)">测试11</li>' +
				'<li onclick="seachChoose(this)">测试11</li>' +
				'</ul>';
		$(_this).after(str);

		//点击其他地方搜索下拉框消失
		$(document).click(function() {
			$(".searchResult").remove();
		});

		//点击input也消失
		$(_this).click(function() {
			$(".searchResult").remove();
		});
	}
	//			搜索下拉选项选中

	function seachChoose(_this) {
		$(_this).parent().siblings("input").val($(_this).text());
	}

	//删除
	function delbasicInfo(_this) {
		layui.use(['form', 'laydate'], function() {
			layer.confirm('确定要删除么？', {
				btn: ['确定', '取消'] //按钮
			}, function() {
				$(_this).parent().parent().remove();
				layer.msg('删除成功', {
					icon: 1
				});
			}, function() {
				layer.msg('取消删除', {
					time: 2000 //20s后自动关闭
				});
			});
		});
	}
	layui.use('element', function() {
		var element = layui.element;

		//获取hash来切换选项卡，假设当前地址的hash为lay-id对应的值
		var layid = location.hash.replace(/^#test1=/, '');
		element.tabChange('myPage', layid); //假设当前地址为：http://a.com#test1=222，那么选项卡会自动切换到“发送消息”这一项
		//监听Tab切换，以改变地址hash值
		element.on('tab(myPage)', function() {
			location.hash = 'test1=' + this.getAttribute('lay-id');
			console.log(this.getAttribute('lay-id'));
		});
	});
	function msgbox(n) {
		document.getElementById('inputbox').style.display = n ? 'block' : 'none'; /* 点击按钮打开/关闭 对话框 */
		$("#classificationName").val($("#classificationNameD1").val());
		$("#classificationName").val($("#classificationNameD2").val());
	}

	function msgbox2(n) {
		document.getElementById('inputcodebox').style.display = n ? 'block' : 'none'; /* 点击按钮打开/关闭 对话框 */
		$("#materialName").val($("#materialNameD1").val());
		$("#materialName").val($("#materialNameD2").val());
	}

	function msgbox3(n) {
		document.getElementById('inputunitbox').style.display = n ? 'block' : 'none'; /* 点击按钮打开/关闭 对话框 */
	}

	function msgbox4(n) {
		document.getElementById('inputproviderbox').style.display = n ? 'block' : 'none'; /* 点击按钮打开/关闭 对话框 */
	}

	function msgbox5(n) {
		document.getElementById('inputsupplierbox').style.display = n ? 'block' : 'none'; /* 点击按钮打开/关闭 对话框 */
		getdeptlist();
	}

	function msgbox6(n) {
		document.getElementById('depart').style.display = n ? 'block' : 'none'; /* 点击按钮打开/关闭 对话框 */
		getdeptlist();
	}

	function msgbox7(n) {
		document.getElementById('depart1').style.display = n ? 'block' : 'none'; /* 点击按钮打开/关闭 对话框 */
	}

	function showGoodsList(list) {
		//添加先清空
		$("#t_body").empty();
		var html = '';
		for(var i = 0; i < list.length; i++) {
			html += '<tr id="' + list[i].materialTrackingCode + '">' +
					'<td>' + i + '</td>' +
					'<td>' + list[i].classificationCode + '</td>' +
					'<td>' + list[i].classificationName + '</td>' +
					'<td>' + list[i].materialCode + '</td>' +
					'<td>' + list[i].materialName + '</td>' +
					'<td>' + list[i].type + '</td>' +
					'<td>' + list[i].specification + '</td>' +
					'<td>' + list[i].unit + '</td>' +
					'<td>' + list[i].quantity + '</td>' +
					'<td>' + list[i].requestMonth + '</td>' +
					'<td>' + list[i].requestDate + '</td>' +
					'<td>' + list[i].sureSource + '</td>' +
					'<td>' + list[i].wishSupplier + '</td>' +
					'<td>' + list[i].fixedSupplier + '</td>' +
					'<td>' + list[i].remarks + '</td>' +
					'<td>' + list[i].materialTrackingCode + '</td>';
			html += '<td>' +
					'	<div style="position: relative;">' +
					'		<font onclick="edit_win(' + '\'' + list[i].materialTrackingCode + '\'' + ')">详情</font>' +
					'	</div>' +
					'</td>' +
					'</tr>';
		}

		$("#t_body").html(html);

	}
</script>
</body>

</html>