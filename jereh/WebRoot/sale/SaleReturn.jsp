<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
<meta charset="utf-8" />
<title>SaleReturn</title>
<script type="text/javascript" src="/jereh/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/jereh/js/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="../themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="../themes/icon.css" />
<style>
* {
	margin: 0px;
}

.search {
	padding: 0px 8px 0px 8px;
	background-color: #C9DFFF;
	font-size: 13px;
	margin: 0px;
}

hr {
	padding: 0px;
	margin: 0px;
}

.button {
	margin: 15px 0px 0px 0px;
}
</style>
<script type="text/javascript">
	$(function() {
		$("#addParts").dialog({
			title : '',
			width : 750,
			heigth : 500,
			modal : true,
			closed : true
		});
		$("#addParts").dialog("close");
		$("#infoTb").hide();
		$("#barList")
				.datagrid(
						{
							idField : 'code',
							toolbar : '#barListTb',
							//fit : true,
							//height : 400,
							striped : true,
							singleSelect : false,
							selectOnCheck : true,
							checkOnSelect : false,
							url : '/jereh/sale/GetSaleReturnServlet',
							columns : [ [
									{
										field : 'id',
										checkbox : true,
									},
									{
										field : 'code',
										title : '销退编号',
										width : 150,
										align : 'center',
										formatter : function(val, row, idx) {
											var content = "<a href='#' onclick='showInfo()'>"
													+ val + "</a>";
											return content;
										},
									},
									{
										field : 'xtDate',
										title : '销退日期',
										width : 100,
										align : 'center',
									},
									{
										field : 'customerCode',
										title : '客户名称',
										width : 100,
										align : 'center',
									},
									{
										field : 'nums',
										title : '数量',
										width : 50,
										align : 'center',
									},
									{
										field : 'numSprice',
										title : '销退金额',
										width : 100,
										align : 'center',
									},
									{
										field : 'contacter',
										title : '联系人',
										width : 100,
										align : 'center',
									},
									{
										field : 'telPhone',
										title : '联系方式',
										width : 150,
										align : 'center',
									},
									{
										field : 'state',
										title : '审核状态',
										width : 50,
										align : 'center',
										formatter : function(isShow) {
											var ret = null;
											if (isShow == 1) {
												ret = '已审核';
											} else {
												ret = '未审核';
											}
											return ret;
										}
									},
									{
										field : 'addUser',
										title : '操作员',
										width : 150,
										align : 'center',
									},
									{
										field : 'operate',
										title : '操作',
										width : 150,
										resizable : false,
										formatter : function(val, row, idx) { //"<input type='button' value='删除' onclick=\"delRow('"+row.code+"')\" />"
											var content = "<input type='button' value='删除' onclick=\"delRow('"
													+ row.code
													+ "')\" /> <input type='button' value='修改' onclick=\"updateRow("
													+ idx + ")\" />";
											return content;
										},
										align : 'center'
									} ] ],
							pagination : true,
							pageList : [ 5, 10, 20 ],
							pageSize : 10
						});

	});

	function showInfo(code) {
		$("#infoTb").show();
		$("#info")
				.datagrid(
						{
							idField : 'code',
							//toolbar : '#infoTb',
							//height: 400,
							//fit : true,
							striped : true,
							singleSelect : false,
							selectOnCheck : true,
							checkOnSelect : false,
							url : '/jereh/servlet/GetBasePartsCategoryJsonServlet',
							columns : [ [
									{
										field : 'code',
										title : '出库单号',
										width : 150,
										align : 'center'
									},
									{
										field : 'cateGoryName',
										title : '件号',
										width : 100,
										align : 'center'
									},
									{
										field : 'addDate',
										title : '配件名称',
										width : 100,
										align : 'center'
									},
									{
										field : 'remarks',
										title : '配件品牌',
										width : 100,
										align : 'center'
									},
									{
										field : 'addUserName',
										title : '配件型号',
										width : 100,
										align : 'center'
									},
									{
										field : 'addUserName',
										title : '数量',
										width : 50,
										align : 'center'
									},
									{
										field : 'addUserName',
										title : '单价',
										width : 150,
										align : 'center'
									},
									{
										field : 'addUserName',
										title : '金额',
										width : 150,
										align : 'center'
									},
									{
										field : 'addUserName',
										title : '备注',
										width : 150,
										align : 'center'
									},
									{
										field : 'operate',
										title : '操作',
										width : 100,
										resizable : false,
										formatter : function(val, row, idx) { //"<input type='button' value='删除' onclick=\"delRow('"+row.code+"')\" />"
											var content = "<input type='button' value='删除' onclick=\"delRow('"
													+ row.code + "')\" />";
											return content;
										},
										align : 'center'
									} ] ],
						});
	};

	function delRow(code) {
		$.messager.confirm("删除提醒", "确认要执行删除操作吗?", function(r) {
			if (r) {
				$.ajax({
					url : '/jereh/sale/DeleteSaleReturnServlet',
					data : {
						'code' : code
					},
					type : 'post',
					success : function(data) {
						if (data == 1) {
							alert("删除成功！");
							$("#barList").datagrid("reload");
						}
					}
				});
				//	window.location.href = "../servlet/DeleteBasePartsCategoryServlet?code="
				//			+ code;
			}
		})
	};

	function showDialog(stitle) {
		$("#addParts").dialog({
			title : stitle,
			width : 750,
			heigth : 500,
			modal : true,
			closed : true
		});
		$("#addParts").dialog("open");
	}
	//添加客户

	function addRow() {
		showDialog('添加配件类别');
		$("input[name='opt']").val("add");
		$("input[name='code']").val("");
		$("input[name='cateGoryName']").val("");
		$("input[name='remarks']").val("");
		$("input[name='isShow']:first").prop("checked", true);
	}
	//修改客户

	function updateRow(idx) {
		//var idx=idx;
		showDialog('销售选项');
		$("input[name='opt']").val("updata");
		var row = $("#barList").datagrid("getRows")[idx];
		var code = row.code;
		//var xtDate = row.xtDate;
		var customerCode = row.customerCode;
		var contacter = row.contacter;
		var telPhone = row.telPhone;
		var fax = row.fax;
		var remarks = row.remarks;
		$("input[name='code']").val(code);
		//$("input[name='xtDate']").val(xtDate);
		$("input[name='customerCode']").val(customerCode);
		$("input[name='contacter']").val(contacter);
		$("input[name='telPhone']").val(telPhone);
		$("input[name='fax']").val(fax);
		$("input[name='remarks']").val(remarks);

	}

	function checkDelete() {
		//返回选中多行  
		var selRows = $("#barList").datagrid("getSelections");
		//判断是否选中行  
		if (selRows.length == 0) {
			$.messager.alert("提示", "请选择要删除的行！", "info");
			return;
		} else {
			$.messager.confirm("删除提醒", "确认要执行删除操作吗?", function(r) {
				if (r) {
					//var count = 0;
					for (i = 0; i < selRows.length; i++) {
						var code = selRows[i].code;
						var categoryCode = selRows[i].categoryCode;
						$.ajax({
							type : 'post',
							async : false,
							url : '/jereh/sale/DeleteSaleReturnServlet',
							data : {
								'code' : code
							},
							success : function(data) {
								if (data == 1) {
									count++;
								}
							}
						});
					}
					//location.reload() ;
					$("#barList").datagrid("reload");
					//if (count == selRows.length) {
					alert("批量删除成功！");
					//}
					//window.location.href = "../servlet/DeleteBasePartsCategoryServlet?code="
					//		+ code;
				}
			})
		}
	}

	function closeDialog() {
		$("#addParts").dialog("close");
	}

	function searchRow() {
		var code = $("input[name='serCode']").val();
		var pCode = $("select[name='serPCode']").val();
		$("#barList").datagrid("reload", {
			code : code,
			pCode : pCode
		});
	}

	function outExcel() {
		var code = $("input[name='serCode']").val();
		var pCode = $("select[name='serPCode']").val();
		$.ajax({
			type : 'post',
			url : '../servlet/OutputExcelBasePartsCategoryServlet',
			data : {
				'code' : code,
				'pCode' : pCode
			}
		});
	}

	function outWord() {
		var code = $("input[name='code']").val();
		$.ajax({
			type : 'post',
			url : '../servlet/OutputWordBasePartsCategoryServlet',
			data : {
				'code' : code
			}
		});
	}
</script>
</head>

<body>
	<div id="barList"></div>

	<div id="barListTb">
		<form action="#" name="search" id="search" class="search">
			<span>检索条件：</span> <span>销退编号：</span> <input
				class="easyui-validatebox" type="text" id="serCode"
				placeholder="请输入销退编号" /> <span>开始日期：</span> <input
				id="serBeginDate" type="text" class="easyui-datebox"
				required="required"></input> <span>结束日期：</span> <input
				id="serEndDate" type="text" class="easyui-datebox"
				required="required"></input> <span>客户名称：</span> <input
				class="easyui-validatebox" type="text" id="serUserName"
				placeholder="请输入客户名称" /> <input type="button" value="重置"
				onclick="reset()">
		</form>

		<a class="easyui-linkbutton"
			data-options="iconCls:'icon-search',plain:true" onclick="searchRow()">查询</a>
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:true" onclick="addRow()">增加</a>
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-cancel',plain:true"
			onclick="checkDelete()">批量删除</a> <a href="#"
			class="easyui-linkbutton"
			data-options="iconCls:'icon-reload',plain:true" onclick="outExcel()">导出EXCEL</a>
	</div>


	<div class="infoTb" id="infoTb" style="background-color: #FFFFFF;">
		<span id="" class="ghost" style="font-weight: bold;"> 这是什么鬼？？？
		</span>
		<div id="info"></div>
	</div>



	<div id="addParts" style="padding:10px;">
		<form action="../sale/UpdataSaleReturnServlet" method="post"
			class="dialog">
			<table border="1" bordercolor="#CFDAE8" cellpadding="0"
				cellspacing="0">
				<input type="hidden" name="id" />
				<input type="hidden" name="opt" />

				<tr>

					<td style="width: 100px; text-align: right">销退编号：</td>
					<td style="width: 200px;"><input type="text" id="code"
						name="code" readonly="true" /></td>
					<td style="width: 100px; text-align: right">销退日期：</td>
					<td style="width: 200px;">
						<!--  <input id="xtDate" name="xtDate"
						type="text" class="easyui-datebox" required="required">
						</input>-->
					</td>
				</tr>

				<tr>
					<td style="width: 100px; text-align: right">客户名称：</td>
					<td><input type="text" id="customerCode" name="customerCode"
						readonly="true" /></td>
					<td style="width: 100px; text-align: right">联系人员：</td>
					<td><input type="text" id="contacter" name="contacter"></td>
				</tr>
				<tr>
					<td style="width: 100px; text-align: right">电话：</td>
					<td><input type="text" id="telPhone" name="telPhone" /></td>
					<td style="width: 100px; text-align: right">传真：</td>
					<td><input type="text" id="fax" name="fax"></td>
				</tr>
				<tr>
					<td style="width: 100px; text-align: right">备注：</td>
					<td colspan="3"><input type="text" name="remarks" id="remarks"
						value="" style="width: 600px;" /></td>
				</tr>
			</table>
			<input type="submit" name="" id="" value="新   增" class="button" /> <input
				type="submit" name="" id="" value="选出库单" class="button" /> <input
				type="submit" name="" id="" value="保   存" class="button" /> <input
				type="submit" name="" id="" value="审   核" class="button" /> <input
				type="submit" name="" id="" value="撤   销" class="button" /> <input
				type="button" name="" id="" value="打   印" class="button"
				onclick="outWord()" /> <input type="button" name="" id=""
				value="返   回" class="button" onclick="closeDialog()" />
		</form>
	</div>
</body>

</html>
