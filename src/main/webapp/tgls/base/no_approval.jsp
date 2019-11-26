<%@page import="org.hibernate.Session"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.panda.pojo.User"%>
<%
	HttpSession httpSession = request.getSession();
	User user = (User) httpSession.getAttribute("userInfo");
	request.setAttribute("userRole", user.getRole());
	request.setAttribute("userName",user.getCname());
	System.out.println("用户角色编码：" + user.getRole() + "用户中文名" + user.getCname());
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
		<meta name="author" content="穷在闹市" />
		<!-- 作者 -->
		<meta name="revised" content="穷在闹市.v3, 2019/05/01" />
		<!-- 定义页面的最新版本 -->
		<meta name="description" content="网站简介" />
		<!-- 网站简介 -->
		<meta name="keywords" content="搜索关键字，以半角英文逗号隔开" />
		<title>穷在闹市出品</title>

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
			//获取后台信息
			function getApprovalStatus(role){
				var data = {
					"role" : role
				}
				console.log(JSON.stringify(data));
				$.ajax({
					type:"post",
					url:"http://123.56.68.20:8080/Batik/request/approval/getUnapproval",
					data : data,
					dataType: "json",
					success: function(ret){
						console.log(JSON.stringify(ret));
						if(ret.status == "1"){
							alert("查询成功，正在调用方法显示");
							showApprovalStatus(ret.data);
						}else if(ret.status == "2"){
							alert(ret.message);
							showDisableButton();
						}else{
							alert("查询失败");
						}
					}
				});
				
			}
			function showDisableButton(){
				$("#approvalButton").empty();
				html2 = '';
				html2+= '<button id="goods_print" style="margin-left: 500px;width:100px;height:35px;background-color:#263236;color: #F1F1F1;" onclick="approvalPass()" disabled="disabled">审批通过</button>'
						 +  '<button id="goods_output" style="margin-left: 30px;width:100px;height:35px;background-color:#209E85;color: #F1F1F1;" onclick="approvalBack()" disabled="disabled">审批退回</button>'
						 +  '<button id="goods_save" style="margin-left: 30px;width:100px;height:35px;background-color:#263236;color: #F1F1F1;">返回</button>';
				$("#aprrovalButton").html(html2);
			}
			function showApprovalStatus(list){
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
						 +  '<td> <input type="checkbox" id="select" name="printPlan" /></td>'
					 	 +  '<td id="requestPlanCode">' + list[i].requestPlanCode + '</td>'
					 	 +	'<td id="requestPlanName">' + list[i].requestPlanName + '</td>'
					 	 +  '<td id="requestPlanType">' + list[i].requestPlanType + '</td>'
					 	 +  '<td>' + approvalStatus  + '</td>'
					 	 +  '<td>' + list[i].requestPerson + '</td>'
					 	 +  '<td>' + list[i].requestMonth + '</td>'
					 	 +  '</tr>';
					html1+= '<label class="layui-form-label">审批意见:</label>'
						 +  '<div class="layui-input-block">'
						 +  '<textarea id="approvalOpinion" name="desc" placeholder="请不要超过100字..." class="layui-textarea" maxlength="100" style="width: 500px;height: 40px;"></textarea>'
						 +  '</div>';
					
				}
				$("#approvalStatus").html(html);
				$("#approvalOpinionDiv").html(html1);
				
			}
			function approvalPass(){
				alert("审批通过");
				var requestPlanCode = $("#requestPlanCode").text();
				var cName = $("#cname").val();
				var approvalOpinion = document.getElementById("approvalOpinion").value;
				var role = $("#role").val();
				alert(approvalOpinion)
				var data = {
					"cName" : cName,
					"requestPlanCode" : requestPlanCode,
					"approvalResult" : "审批通过",
					"approvalOpinion" : approvalOpinion
				}
				console.log(JSON.stringify(data))
				$.ajax({
					type:"post",
					url:"http://123.56.68.20:8080/Batik/request/approval/approvalPass",
					data:data,
					dataType:"json",
					success:function(ret){
						console.log(JSON.stringify(ret));
						if(ret.status == "1"){
							alert("修改审批状态成功");
							getApprovalStatus(role);
						}else{
							alert("修改审批状态失败");
						}
					}
				});
			}
			function approvalBack(){
				alert("审批退回");
				var requestPlanCode = $("#requestPlanCode").text();
				var cName = $("#cname").val();
				var approvalOpinion = document.getElementById("approvalOpinion").value;
				var data = {
					"cName" : cName,
					"requestPlanCode" : requestPlanCode,
					"approvalResult" : "审批退回",
					"approvalOpinion" : approvalOpinion
				}
				console.log(JSON.stringify(data))
				$.ajax({
					type:"post",
					url:"http://123.56.68.20:8080/Batik/request/approval/approvalBack",
					data:data,
					dataType:"json",
					success:function(ret){
						console.log(JSON.stringify(ret));
						if(ret.status == "1"){
							alert("修改审批状态成功");
						}else{
							alert("修改审批状态失败");
						}
					}
				});
			}
		</script>

	</head>

	<body>
		<div class="cBody">
			<table class="layui-table">
				<thead>
					<tr>
						<th>行号</th>
						<th>选择</th>
						<th>需求计划编码</th>
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
						<td>
						<input type="checkbox" name="bianma" id="bianma"  onclick="check()";checked>
						</td>
						<td id="requestPlanCode">ztw</td>
						<td>xxx</td>
						<td>xxx</td>
						<td>xx</td>
						<td>xx</td>
						<td>xxx</td>
					</tr>
					<tr>
						<td>2</td>
						<td>
						<input type="checkbox" name="bianma" id="bianma"  onclick="check()";checked>
						</td>
						<td>xxxxxx</td>
						<td>xxx</td>
						<td>xxx</td>
						<td>xx</td>
						<td>xx</td>
						<td>xxx</td>
					</tr>
					<tr>
						<td>3</td>
						<td>
						<input type="checkbox" name="bianma" id="bianma"  onclick="check()";checked>
						</td>
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
						<textarea  name="desc" placeholder="请不要超过100字..." class="layui-textarea" maxlength="100" style="width: 500px;height: 40px;"></textarea>
					</div>
			</div>
			<div id="approvalButton" class="batton" style="margin-top: 50px;width: 100%;height: 200px;">
				<button id="goods_print" style="margin-left: 500px;width:100px;height:35px;background-color:#263236;color: #F1F1F1;" onclick="approvalPass()">审批通过</button>
				<button id="goods_output" style="margin-left: 30px;width:100px;height:35px;background-color:#209E85;color: #F1F1F1;" onclick="approvalBack()">审批退回</button>
				<button id="goods_save" style="margin-left: 30px;width:100px;height:35px;background-color:#263236;color: #F1F1F1;">返回</button>
			</div>
		</div>
		<input id="role" value="${userRole}" hidden="true" />
		<input id="cname" value="${userName}" hidden="true" />
	</body>
	<script type="text/javascript">
		$(function() {
			role = $("#role").val();
			alert("-role-" + role);		
			getApprovalStatus(role);
		});
	</script>

</html>