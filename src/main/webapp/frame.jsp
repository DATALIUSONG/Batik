<%@page import="org.hibernate.Session"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.panda.pojo.User"%>
<%
	HttpSession httpSession = request.getSession();
	User user = (User) httpSession.getAttribute("userInfo");
	request.setAttribute("userRole", user.getRole());
	System.out.println("用户信息：" + user.getRole());
%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
		<!-- Google Chrome Frame也可以让IE用上Chrome的引擎: -->
		<meta name="renderer" content="webkit">
		<!--国产浏览器高速模式-->
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- 定义页面的最新版本 -->
		<meta name="description" content="网站简介" />
		<!-- 网站简介 -->
		<meta name="keywords" content="搜索关键字，以半角英文逗号隔开" />
		<title>采购计划管理</title>

		<!-- 公共样式 开始 -->
		<link rel="shortcut icon" href="images/favicon.ico" />
		<link rel="bookmark" href="images/favicon.ico" />
		<link rel="stylesheet" type="text/css" href="css/base.css">
		<link rel="stylesheet" type="text/css" href="css/iconfont.css">
		<script type="text/javascript" src="framework/jquery-1.11.3.min.js"></script>
		<link rel="stylesheet" type="text/css" href="layui/css/layui.css">
		<script type="text/javascript" src="layui/layui.js"></script>
		<!-- 滚动条插件 -->
		<link rel="stylesheet" type="text/css" href="css/jquery.mCustomScrollbar.css">
		<script src="framework/jquery-ui-1.10.4.min.js"></script>
		<script src="framework/jquery.mousewheel.min.js"></script>
		<script src="framework/jquery.mCustomScrollbar.min.js"></script>
		<script src="framework/cframe.js"></script>
		<!-- 仅供所有子页面使用 -->
		<!-- 公共样式 结束 -->

		<link rel="stylesheet" type="text/css" href="css/frameStyle.css">
		<script type="text/javascript" src="framework/frame.js"></script>

	</head>

	<body>
		<!-- 左侧菜单 - 开始 -->
		<div class="frameMenu">
			<div class="logo">
				<img src="images/logo.png" />
				<div class="logoText">
					<h1>蜡染后台管理系统</h1>
					<p>wax printing</p>
				</div>
			</div>
			<div class="menu">
				<ul>
					<li id="homePage" >
						<a class="menuFA"   onclick="menuCAClick('tgls/welcome.html',this)"><i class="iconfont icon-peisong2 left"></i>首页</a>
					</li>
					<li id="createRequestPlan" >
						<a class="menuFA"  ><i class="iconfont icon-yunying left"></i>物资需求计划管理<i class="iconfont icon-dajiantouyou right"></i></a>
						<dl>
							<dt><a  onclick="menuCAClick('tgls/base/base_customNewList.jsp',this)">物资需求年度计划新增</a></dt>
							<dt><a  onclick="menuCAClick('tgls/base/base_list.html',this)">物资需求计划查改</a></dt>
							<dt><a  onclick="menuCAClick('tgls/base/base_monthplan.jsp',this)">物资需求月度计划新增</a></dt>
							<dt><a  onclick="menuCAClick('tgls/base/no_approval.jsp',this)">待审批物资需求计划</a></dt>
							<dt><a  onclick="menuCAClick('tgls/base/approvaled.jsp',this)">已审批物资需求计划</a></dt>
							<dt><a  onclick="menuCAClick('tgls/base/TotalTable.jsp',this)">物质需求汇总表</a></dt>
						</dl>
					</li>
					<li id="purchasePlan" >
						<a class="menuFA"   onclick="menuCAClick('tgls/iconfont.html',this)"><i class="iconfont icon-yijiedan left"></i>采购计划主界面<i class="iconfont icon-dajiantouyou right"></i></a>
						<dl>
							<dt><a  onclick="menuCAClick('tgls/qdAPI.html',this)">编制物资需求清单</a></dt>
							<dt><a  onclick="menuCAClick('tgls/iconfont.html',this)">采购计划查询/修改</a></dt>
							<dt><a  onclick="menuCAClick('tgls/approval/no_approval.jsp',this)">待审批采购计划</a></dt>
							<dt><a  onclick="menuCAClick('tgls/approval/approvaled.jsp',this)">已审批采购计划</a></dt>
						</dl>
					</li>
				</ul>
			</div>
		</div>
		<!-- 左侧菜单 - 结束 -->

		<div class="main">
			<!-- 头部栏 - 开始 -->
			<div class="frameTop">
				<img class="jt" src="images/top_jt.png" />
				<div class="topMenu">
					<ul>
						<li>
							<a  onclick="menuCAClick('tgls/user_information.jsp',this)"><i class="iconfont icon-yonghu1"></i>个人资料</a>
						</li>
						<li>
							<a href="login.jsp"><i class="iconfont icon-084tuichu"></i>注销</a>
						</li>
					</ul>
				</div>
			</div>
			<!-- 头部栏 - 结束 -->

			<!-- 核心区域 - 开始 -->
			<div class="frameMain">
				<div class="title" id="frameMainTitle">
					<span><i class="iconfont icon-xianshiqi"></i>后台首页</span>
				</div>
				<div class="con">
					<iframe id="mainIframe" src="tgls/home_page01.html" scrolling="no"></iframe>
				</div>
			</div>
			<!-- 核心区域 - 结束 -->
		</div>
		<input id="role" value="${userRole}" hidden="true" />
	</body>
	<script type="text/javascript">
		var role = null;
		$(function() {
			role = $("#role").val();
			switch(parseInt(role)) {
				case 0:
					$("#materialSupplyWay").show();
					$("#createRequestPlan").show();
					$("#purchasePlan").show();
					break;
				case 1:
					$("#purchasePlanApproval").show();
					break;
				case 2:
					$("#purchasePlanApproval").show();
					break;
				case 3:
					break;
				case 4:
					$("#purchasePlanApproval").show();
					break;
				case 5:
					$("#createRequestPlan").show();
					$("#purchasePlan").show();
					break;
				case 6:
					$("#purchasePlanApproval").show();
					break;
				case 7:
					$("#materialSupplyWay").show();
					break;
				case 8:
					$("#purchasePlanApproval").show();
					break;
				case 9:
					$("#purchasePlanApproval").show();
					break;
				case 10:
					break;
				case 100:
					$("#createRequestPlan").show();
					$("#purchasePlan").show();
					$("#purchasePlanApproval").show();
					$("#materialSupplyWay").show();
					break;
				default:
					break;
			}
		});
	</script>
</html>