<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>BasePartsCategory</title>
<script type="text/javascript" src="/jereh/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/jereh/js/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="../themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="../themes/icon.css" />
<style>
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
	$("#barList").datagrid('getPager').pagination({
    	displayMsg:'当前显示从第 {from} 条到第 {to} 条，共 {total} 条记录'
	});
		$("#barList")
				.datagrid(
						{
							idField : 'code',
							toolbar : '#tb',
							fit : true,
							striped : true,
							singleSelect : false,
							selectOnCheck : true,
							checkOnSelect : false,
							url : '/jereh/servlet/GetBasePartsCategoryJsonServlet',
							columns : [ [
									{
										field : 'id',
										checkbox : true,
									},
									{
										field : 'code',
										title : '类别编号',										
										align : 'center',
										fixed:true
									},
									{
										field : 'cateGoryName',
										title : '类别名称',										
										align : 'center',
										fixed:true
									},
									{
										field : 'addDate',
										title : '操作日期',
										width : 150,
										align : 'center',
										fixed:true
									},
									{
										field : 'remarks',
										title : '备注',										
										align : 'center',
										fixed:true
									},
									{
										field : 'addUserName',
										title : '操作员',										
										align : 'center',				
										fixed:true
									},
									{
										field : 'isShow',
										title : '显示状态',										
										align : 'center',
										formatter : function(isShow) {
											var ret = null;
											if (isShow == 1) {
												ret = '显示';
											} else {
												ret = '隐藏';
											}
											return ret;
										}
									},
									{
										field : 'operate',
										title : '操作',
										fixed:true,
										resizable : false,
										formatter : function(val, row, idx) {//"<input type='button' value='删除' onclick=\"delRow('"+row.code+"')\" />"
											var content = "<input type='button' value='删除' onclick=\"delRow('"
													+ row.code
													+ "')\" /> <input type='button' value='修改' onclick=\"updateRow("
													+ idx + ")\" />";
											return content;
										},
										align : 'center'
									}, {
										field : 'pCode',
										title : '父类编号',
										width : 150,
										align : 'center',
										hidden : true
									} ] ],
							pagination : true,
							pageList : [ 5, 10, 20 ],
							pageSize : 10,
						});
	});
	function delRow(code) {
		$.messager
				.confirm(
						"删除提醒",
						"确认要执行删除操作吗?",
						function(r) {
							if (r) {
								/*$.ajax({url:'/jereh/servlet/DeleteBasePartsCategoryServlet',
									data:{'code':code},
									type:'post',
									success:function(data){
										if(data==1){
											alert("删除成功！");
											$("#barList").datagrid("reload");							
										}
									}
								});	*/

								window.location.href = "../servlet/DeleteBasePartsCategoryServlet?code="
										+ code;
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
		showDialog('修改配件类别');
		$("input[name='opt']").val("updata");
		var row = $("#barList").datagrid("getRows")[idx];
		var code = row.code;
		var cateGoryName = row.cateGoryName;
		var remarks = row.remarks;
		var isShow = row.isShow;
		var pCode = row.pCode;

		$("input[name='code']").val(code);
		$("input[name='cateGoryName']").val(cateGoryName);
		$("input[name='remarks']").val(remarks);

		if (pCode == "001") {
			$("select[name='pCode'] > option:eq(0)").prop("selected", true);
		} else if (pCode == "002") {
			$("select[name='pCode'] > option:eq(1)").prop("selected", true);
		} else {
			$("select[name='pCode'] > option:eq(2)").prop("selected", true);
		}

		if (isShow == 1) {
			$("input[name='isShow']:first").prop("checked", true);
		} else {
			$("input[name='isShow']:last").prop("checked", true);
		}
	}

	function checkDelete() {
		//返回选中多行  
		var selRows = $("#barList").datagrid("getSelections");
		//判断是否选中行  
		if (selRows.length == 0) {
			$.messager.alert("提示", "请选择要删除的行！", "info");
			return;
		} else {
			$.messager
					.confirm(
							"删除提醒",
							"确认要执行删除操作吗?",
							function(r) {
								if (r) {
									//var count = 0;
									for (i = 0; i < selRows.length; i++) {
										var code = selRows[i].code;
										var categoryCode = selRows[i].categoryCode;
										$
												.ajax({
													type : 'post',
													async : false,
													url : '/jereh/servlet/DeleteBasePartsCategoryServlet',
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
									//alert("批量删除成功！");
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
	function outWord(){
		var code=$("input[name='code']").val();
		/*var pCode=$("select[name='pCode']").val();
		var cateGoryName=$("input[name='cateGoryName']").val();
		var isShow=$("input[name='isShow']").val();
		var remarks=$("input[name='remarks']").val();*/
		$.ajax({
			type : 'post',
			url : '../servlet/OutputWordBasePartsCategoryServlet',
			data : {
				'code' : code/*,
				'pCode' : pCode,
				'cateGoryName' : cateGoryName,
				'isShow' : isShow,
				'remarks' : remarks*/
			}
		});
	}
</script>
</head>
<body>
	<div id="barList"></div>
	<div id="tb">
		<form action="#" name="search" id="search" class="search">
			<span>检索条件：</span> <span>类别编号：</span> <input type="text"
				name="serCode" id="serCode" placeholder="请输入类别编号 例如：001"> <span>所属类别：</span>
			<select id="serPCode" name="serPCode" style="width:150px;">
				<option value="">--选择类别--</option>
				<option value="001">一级类别</option>
				<option value="002">二级类别</option>
				<option value="003">三级类别</option>
			</select> <input type="button" value="重置" onclick="reset()">
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



	<div id="addParts" style="padding:10px;">
		<form action="../servlet/UpdataBasePartsCategoryServlet" method="post"
			class="dialog">
			<table border="1" bordercolor="#CFDAE8" cellpadding="0"
				cellspacing="0">
				<input type="hidden" name="id" />
				<input type="hidden" name="opt" />
				<tr>
					<td style="width: 100px; text-align: right">所属类别：</td>
					<td style="width: 200px;"><select name="pCode" id="pCode">
							<option value="001">一级类别</option>
							<option value="002">二级类别</option>
							<option value="003">三级类别</option>
					</select></td>
					<td style="width: 100px; text-align: right">类别编号：</td>
					<td style="width: 200px;"><input type="text" id="code"
						name="code" readonly="true" /></td>
				</tr>
				<tr>
					<td style="width: 100px; text-align: right">类别名称：</td>
					<td><input type="text" id="cateGoryName" name="cateGoryName" /></td>
					<td style="width: 100px; text-align: right">显示状态：</td>
					<td><input type="radio" name="isShow" id="isShow" value="1"
						checked="checked" />显示 <input type="radio" name="isShow"
						id="isShow" value="0" />隐藏</td>
				</tr>
				<tr>
					<td style="width: 100px; text-align: right">备注：</td>
					<td colspan="3"><input type="text" name="remarks" id="remarks"
						value="" style="width: 600px;" /></td>
				</tr>
			</table>
			<input type="submit" name="" id="" value="保   存" class="button" /> <input
				type="button" name="" id="" value="打   印" class="button" onclick="outWord()"/> <input
				type="button" name="" id="" value="返   回" class="button"
				onclick="closeDialog()" />
		</form>
	</div>
</body>

</html>
