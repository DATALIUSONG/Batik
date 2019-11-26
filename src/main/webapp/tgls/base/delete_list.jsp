<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8" %>
<%
	System.out.println("欢迎来到蜡染智能制造共享平台删除页面~");
%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>删除需求计划</title>
	<link rel="stylesheet" href="../../css/admin_css.css" />
</head>
<div style="height:500px;background: white;margin: 0 auto;overflow-y:scroll;padding-bottom: 15px;margin-left: 12px;margin-right: 12px;">
	<!--阴影效果-->
	<div style="width: 100%;height: 5px;background: #E7EBED;"></div>
	<!--表格部分-->
	<table>
		<thead>
		<!--<th><input type="checkbox" id="allAndNotAll" /> 需求编号</th>-->
		<th> 需求编号</th>
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
		<th>需求计划状态</th>
		<th>更新日期</th>
		<th>操作</th>

		</thead>
		<tbody id="t_body">
		<!--显示数据部分-->
		</tbody>
	</table>
	<!--底部分页部分-->
	<div style="width: 100%;height: 50px;line-height: 50px;text-align: right;">
		<button style="background-image: url(img/keji2.jpg);">上一页</button>
		<button id="next_btn" style="margin-right: 12px;background-image: url(img/keji2.jpg);">下一页</button>
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

<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript">

	//定义一个全局变量，用于存放欲被删除的行
	var deleteValue = null;

	//显示删除提示对话框
	function show_dialog(el) {
		deleteValue = $(el).attr("value");
		$("#add_dialog_window").css("display", "block");

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
		//更新需求计划状态的值为
		var	requestStatus = "已删除";
		var json = {
			materialTrackingCode: deleteValue,
			requestStatus: requestStatus
		}

		//根据物料追踪码查询需求计划信息
		$.ajax({
			url: "http://123.56.68.20:8080/Batik/goods/delete",
			type: "POST",
			data: json,
			dataType: "json",
			success: function(ret) {
				console.log(ret.message);
				if(ret.status == "1") {
					//关闭对话框
					hide_dialog();
					//重新刷新页面
					getGoodsList();
				}
			}
		});
		alert("删除数据成功");
		//关闭对话框
		hide_dialog();
		//重新刷新页面
		getGoodsList();
	}

	getGoodsList();
	//调用获取需求列表方法
	function getGoodsList() {
		$.ajax({
			type: "GET",
			url: "http://123.56.68.20:8080/Batik/deletequery",
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

	function showGoodsList(list) {
		//添加先清空
		$("#t_body").empty();
		alert("需求计划状态为“自由”的数据");
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
					'<td>' + list[i].remarks + '</td>'+
					'<td>' + list[i].materialTrackingCode + '</td>'+
					'<td>' + list[i].requestStatus + '</td>';
			if(typeof(list[i].updateTime) == "undefined"){
				html+=    '<td>无</td>';
			}else{
				html+=    '<td>'+list[i].updateTime+'</td>';
			}
			html += '<td>' +
					'	<div style="position: relative;">' +
					'		<font onclick="show_dialog(this)" value="' + list[i].materialTrackingCode + '">删除</font>' +
					'	</div>' +
					'</td>' +
					'</tr>';
		}

		$("#t_body").html(html);

	}
</script>
</html>