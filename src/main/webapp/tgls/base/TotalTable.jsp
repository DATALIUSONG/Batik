<%@page import="org.hibernate.Session"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>物资需求汇总表</title>
		<link rel="stylesheet" href="../../layui/css/layui.css" media="all">
		<meta http-equiv="Access-Control-Allow-Origin" content="*">
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
		<script src="../..framework/cframe.js"></script>
		<!-- 仅供所有子页面使用 -->
		<script src="../../framework/printThis.js"></script>
		<!-- 公共样式 结束 -->
		<style>
			.layui-form-item-text {
				text-align: center;
				line-height: 38px;
				background: #e8e4e4;
				font-size: 18px;
				margin-bottom: 0;
				color: #5f5d5d;
			}
			
			table {
				text-align: center;
			}
			
			.layui-table th {
				text-align: center;
			}
			
			.layui-btn {
				float: left;
				margin-top: 10px;
			}
			
			.layui-table thead tr {
				background: #fff;
			}
			
			.layui-table tbody tr:hover {
				background: #fff;
			}
			
			.layui-table {
				margin: 0;
			}
			
			.layui-bg-green {
				margin-top: 5px;
			}
			
			.layui-form-add {
				min-height: 70px;
				border: 0;
			}
			
			.layui-form-item.layui-form-item-add.tttttt {
				width: 35%;
			}
			
			.layui-form-item.layui-form-item-add.tttttt .layui-form-label-add {
				width: 20%;
			}
			
			.layui-form-item.layui-form-item-add.tttttt .layui-input-block-add {
				width: 30%;
				margin-left: 2%;
			}
		</style>
	</head>

	<body>
		<div class="layui-fluid">
			<div class="layui-row layui-col-space15">
				<div class="layui-col-md12">
					<div class="layui-card">
						<div class="layui-card-header" id="title-header">
							<fieldset class="layui-elem-field layui-field-title" style="text-align: center;">
								<legend>物资需求汇总表</legend>
							</fieldset>
						</div>
						<div class="layui-card-body">
							
							<table class="layui-table" id="backViewTable" >
								
								
								<colgroup>
									<col width="6.3%">
									<col width="6.3%">
									<col width="6.3%">
									<col width="6.3%">
								</colgroup>
								<th colspan="13" class="layui-form-item layui-form-item-text">物资需求汇总表</th>
								<div class="layui-form">
									<tr>
										<th>需求计划编码:</th>
										<td>00000</td>
										<th>需求部门</th>
										<td>aaa</td>
										<th>需求类型:</th>
										<td>aaa</td>
									</tr>
								</div>

								<div class="layui-form">
									<tr>
										<th width="6%" class="textCenter">行号</th>
										<th width="6%">物料分类编码</th>
										<th width="6%" class="textCenter">物料分类名称</th>
										<th width="6%" class="textCenter">物料编码</th>
										<th width="6%" class="textCenter">物料名称</th>
										<th width="6%" class="textCenter">规格</th>
										<th width="6%" class="textCenter">型号</th>
										<th width="6%" class="textCenter">规格</th>
										<th width="6%" class="textCenter">单位</th>
										<th width="6%" class="textCenter">需求数量</th>
										<th width="6%" class="textCenter">需求月份</th>
										<th width="6%" class="textCenter">需求日期</th>
										<th width="6%">备注</th>
									</tr>
								</div>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>

		<script>
			//请求数据
			$(function() {
				getPrintData();
			});

			function getPrintData() {
				
				$.ajax({
					type: "GET",
					url: "http://123.56.68.20:8080/Batik/goods/queryAll",
					data: null,
					dataType: "json",
					success: function(ret) {
						console.log(JSON.stringify(ret));
						if(ret.status == "1") {
							//alert("数据排版成功！！！");
							showPrintData(ret.data);
						} else {
							alert(ret.message);
						}
					}
				});
			}

			function showPrintData(data) {
				$("#printDataTable").empty();
				var nowDate = new Date();
				html = '';
				for(var i = 0; i < data.length; i++) {
					html += '<tr>' +
						'<td class="printName" colspan="13">' + data[i].requestPlanName + '</td>' +
						'</tr>' +
						'<tr>' +
						'<th width="2%" class="textCenter">行号</th>' +
						'<th width="8%">物料分类编码</th>' +
						'<th width="9%" class="textCenter">物料分类名称</th>' +
						'<th width="8%" class="textCenter">物料编码</th>' +
						'<th width="8%" class="textCenter">物料名称</th>' +
						'<th width="8%" class="textCenter">规格</th>' +
						'<th width="8%" class="textCenter">型号</th>' +
						'<th width="8%" class="textCenter">单位</th>' +
						'<tr>' +
						'<td class="textCenter">' + i+ '</td>' +
						'<td>' + data[i].classificationCode + '</td>' +
						'<td>' + data[i].classificationName + '</td>' +
						'<td>' + data[i].materialCode + '</td>' +
						'<td>' + data[i].materialName + '</td>' +
						'<td>' + data[i].specification + '</td>' +
						'<td>' + data[i].type + '</td>' +
						'<td>' + data[i].unit + '</td>' +
						'</tr>'+
						'</tr>' +
						'<th width="8%" class="textCenter">需求数量</th>' +
						'<th width="8%" class="textCenter">需求月份</th>' +
						'<th width="8%" class="textCenter">需求日期</th>' +
						'<th width="8%" class="textCenter">货源是否确定</th>' +
						'<th width="8%" class="textCenter">期望供应商</th>' +
						'<th width="8%" class="textCenter">固定供应商</th>' +
						'<th width="8%" class="textCenter">所属需求计划编码</th>' +
						'<th width="8%" class="textCenter">所属需求部门</th>' +
						'</tr>' +
						'<tr>' +
						'<td>' + data[i].quantity + '</td>' +
						'<td>' + data[i].requestMonth + '</td>' +
						'<td>' + data[i].requestDate + '</td>' +
						'<td>' + data[i].sureSource + '</td>' +
						'<td>' + data[i].wishSupplier + '</td>' +
						'<td>' + data[i].fixedSupplier + '</td>' +
						'<td>' + data[i].requestPlanCode + '</td>' +
						'<td>' + data[i].requestDeportment + '</td>' +
						'</tr>';
				}
				$("#backViewTable").html(html);
			}
			
			document.getElementById('excelBtn').onclick = () => {
				exportExcel.exports(backViewTable);
			};

			var filename = "上班统计";
			class ExportExcel {
				constructor() {
					this.idTmr = null;
					this.uri = 'data:application/vnd.ms-excel;base64,';
					this.template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" ' +
						'xmlns="http://www.w3.org/TR/REC-html40"><head><meta charset="UTF-8">' +
						'<!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions>' +
						'<x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]-->' +
						// 在这里自定义table样式，可以将样式导出到excal表格
						' <style type="text/css">' +
						'table td,table th {' +
						'width: 200px;' +
						'height: 30px;' +
						' text-align: center;' +
						' }' +
						'</style>' +
						'</head><body><table>{table}</table></body></html>';
				}
				getBrowser() {
					var explorer = window.navigator.userAgent;
					//ie
					if(explorer.indexOf("Trident") >= 0) {
						return 'ie';
					}
					//firefox
					else if(explorer.indexOf("Firefox") >= 0) {
						return 'Firefox';
					}
					//Chrome
					else if(explorer.indexOf("Chrome") >= 0) {
						return 'Chrome';
					}
					//Opera
					else if(explorer.indexOf("Opera") >= 0) {
						return 'Opera';
					}
					//Safari
					else if(explorer.indexOf("Safari") >= 0) {
						return 'Safari';
					}
				};
				exports(tableid) {
					if(this.getBrowser() == 'ie') {
						var curTbl = document.getElementById(tableid);
						var oXL = new ActiveXObject("Excel.Application");
						var oWB = oXL.Workbooks.Add();
						var xlsheet = oWB.Worksheets(1);
						var sel = document.body.createTextRange();
						sel.moveToElementText(curTbl);
						sel.select();
						sel.execCommand("Copy");
						xlsheet.Paste();
						oXL.Visible = true;

						try {
							var fname = oXL.Application.GetSaveAsFilename(filename + "Excel.xls", "Excel Spreadsheets (*.xls), *.xls");
						} catch(e) {
							alert(e);
						} finally {
							oWB.SaveAs(fname);
							oWB.Close(savechanges = false);
							oXL.Quit();
							oXL = null;
							this.idTmr = window.setInterval("Cleanup();", 1);
						}
					} else {
						this.openExport(tableid)
					}
				};
				openExport(table, name) {
					if(!table.nodeType) {
						table = document.getElementById(table)
					}
					var ctx = {
						worksheet: name || 'Worksheet',
						table: table.innerHTML
					};
					var a = document.createElement("a");
					a.download = filename;
					a.href = this.uri + this.base64(this.format(this.template, ctx));
					document.body.appendChild(a);
					a.click();
					document.body.removeChild(a);
					// window.location.href = this.uri + this.base64(this.format(this.template, ctx));
				};
				base64(s) {
					return window.btoa(unescape(encodeURIComponent(s)))
				};
				format(s, c) {
					return s.replace(/{(\w+)}/g, function(m, p) {
						return c[p];
					});
				};
			}
			var exportExcel = new ExportExcel();
		</script>
	</body>

</html>