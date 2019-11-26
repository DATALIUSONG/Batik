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
		<meta name="author" content="小熊猫团队" />
		<!-- 作者 -->
		<meta name="revised" content="小熊猫团队.v3, 2019/05/01" />
		<!-- 定义页面的最新版本 -->
		<meta name="description" content="网站简介" />
		<!-- 网站简介 -->
		<meta name="keywords" content="搜索关键字，以半角英文逗号隔开" />
		<title>小熊猫团队出品</title>

		<!-- 公共样式 开始 -->
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
		<script src="../../framework/cframe.js"></script><!-- 仅供所有子页面使用 -->
		<!-- 公共样式 结束 -->



	<script type="text/javascript">
		function getApprovalStatus(role){
			var data = {
				"role" : role
			}
			console.log(JSON.stringify(data));
			$.ajax({
				type:"post",
				url:"http://123.56.68.20:8080/Batik/request/approval/getApprovaled",
				data : data,
				dataType: "json",
				success: function(ret){
					console.log(JSON.stringify(ret));
					if(ret.status == "1"){
						alert("查询成功，正在调用方法显示");
						showApprovalStatus(ret.data, ret.opinion);
					}else if(ret.status == "2"){
						alert(ret.message);
					}else{
						alert("查询失败");
					}
				}
			});
		}
		function showApprovalStatus(list, opinion){
				alert("正在显示数据");
				$("#approvalStatus").empty();
				$("#approvalOpinionDiv").empty();
				html = '';
				html1 = '';
				for (var i = 0; i < list.length; i++ ) {
					var status =  parseInt(list[i].approvalStatus);
					var approvalStatus = '';
					switch (status){
						case 0:
							approvalStatus += "未提交";
							break;
						case 2:
							approvalStatus += "审批中";
							break;
						case 3:
							approvalStatus += "生产主管审批已通过";
							break;	
						case 4:
							approvalStatus += "需求计划员审批已通过";
							break;
					}
					html += '<tr>'
						 +  '<td>' + parseInt(i + 1) + '</td>'
						 +  '<td> <input type="checkbox" id="select" /></td>'
					 	 +  '<td id="requestPlanCode">' + list[i].requestPlanCode + '</td>'
					 	 +	'<td id="requestPlanName">' + list[i].requestPlanName + '</td>'
					 	 +  '<td id="requestPlanType">' + list[i].requestPlanType + '</td>'
					 	 +  '<td>' + approvalStatus  + '</td>'
					 	 +  '<td>' + list[i].requestPerson + '</td>'
					 	 +  '<td>' + list[i].requestMonth + '</td>'
					 	 +  '</tr>';				
				}
				for (var i = 0; i < opinion.length; i++) {
					html1+= '<label class="layui-form-label">审批意见:</label>'
						 +  '<div class="layui-input-block">'
						 +  '<textarea id="approvalOpinion" name="desc" placeholder="'+ opinion[i].opinion +'" class="layui-textarea" maxlength="100" style="width: 500px;height: 40px;"></textarea>'
						 +  '</div>';
				}
				alert(html1);
		}	
	</script>
	</head>

	<body>
		<div class="cBody">
			
			<table class="layui-table">
				<thead>
					<tr>
						<th>行号</th>
						<th>需求计划编号</th>
						<th>需求计划名称</th>
						<th>需求计划类型</th>
						<th>审批状态</th>
						<th>需求人员</th>
						<th>需求月份</th>
					</tr>
				</thead>
				<tbody id="approvalStatus">
					<tr>
						<td>1</td>
						<td>xxxxxx</td>
						<td>xxx</td>
						<td>xxx</td>
						<td>xx</td>
						<td>xx</td>
						<td>xxx</td>
					</tr>
				</tbody>
			</table>
			
			<!-- layUI 分页模块 -->
			<div id="pages"></div>
			<script>
				layui.use(['laypage', 'layer'], function() {
					var laypage = layui.laypage,
						layer = layui.layer;
				
					//总页数大于页码总数
					laypage.render({
					    elem: 'pages'
					    ,count: 100
					    ,layout: [ 'prev', 'page', 'next', ]
					    ,jump: function(obj){
					      console.log(obj)
					    }
					});
				});
			</script>
			<div id="approvalOpinionDiv" class="layui-form-item layui-form-text" style="margin-top: 30px;">
					<label class="layui-form-label">审批意见:</label>
					<div class="layui-input-block">
						<textarea name="desc" placeholder="请不要超过100字..." class="layui-textarea" maxlength="100" style="width: 500px;height: 40px;" ></textarea>
					</div>
			</div>
			<div class="batton" style="margin-top: 50px;width: 100%;height: 200px;">
				<button id="back" style="margin-left: 500px;width:100px;height:35px;background-color:#209E85;color: #F1F1F1;">返回</button>
			</div>
		</div>
		<input id="role" value="${userRole}" hidden="true" />
	</body>
	<script type="text/javascript">
	$(function() {
		role = $("#role").val();
		getApprovalStatus(role);
		alert(role);
	});
	</script>
</html>