<%@page pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<script src="../js/jquery.min.js" language="javascript"></script>
<script src="../js/jquery.easyui.min.js" language="javascript"></script>
<link href="../themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="../themes/icon.css" rel="stylesheet" type="text/css" />
<style>
body{ padding:0px; margin:0px;}
.label{ width:100px; text-align:right;}
.detailTd{ width:100px; height:20px; text-align:center;}
.in{ width:300px;}
td{ border:solid #95ADCC; border-width:1px;}
.hint{ position:relative; top:2px;}
</style>
<script>
var opt = 1;
var state = 0;

$(function(){
	//格式化日期
	$("input.easyui-datebox").datebox({
		formatter:function(date){
			var year = date.getFullYear();
			var month = date.getMonth() + 1;
			var day = date.getDate();
			return year + "-" + month + "-" + day;
		},
		parse:function(date){
			var time = Date.parse(date);
			if(!isNaN(time))
				return new Date(time);
			else
				return new Date();
		}
	});
	//ajax
	$("#purchaseReturnList").datagrid({//往哪里添加table
		title:'采购订单表',
		fitColumns:true,
		idField:'code',//必须选择一个id
		singleSelect:false,
		toolbar:'#tool',//把newsTool标识成工具栏
		url:'/jereh/purchase/GetPurchaseReturnServlet',
		columns:[[
			{checkbox:true},
			{field:'code',title:'订单编号',width:130,formatter:function(val,row,idx){
				return "<a href='#' onclick='detail(" + idx + ")'>" + row.code + "</a>";
			}},
			{field:'rdate',title:'订单日期',width:150,fixed:true},
			{field:'supplierCode',title:'供应商名',fixed:true},
			{field:'nums',title:'数量',fixed:true},
			{field:'numsPrice',title:'金额',fixed:true},
			{field:'contacter',title:'联系人',fixed:true},
			{field:'telphone',title:'联系方式',fixed:true},
			{field:'state',title:'审核状态',fixed:true},
			{field:'addUser',title:'操作员',fixed:true},
			{field:'opt',title:'操作',fixed:true,formatter:function(val,row,idx){
				var content = "<input type='button' value='删除' onclick='del(" + idx + ")' />";
				content += "<input type='button' value='修改' onclick='update(" + idx + ")' />";
				content += "<input type='button' value='打印' onclick='wordOut(" + idx + ")' />";
				return content;
			}}
		]],
//		fit:true,
		pagination:true,//翻页
		pageList:[3,5,10],//可选一页显示多少条
		pageSize:5,//默认一页显示多少条
		pageNumber:<%=session.getAttribute("purchaseReturnPageNo")%>
	});
	$("#supplierWindow").dialog({
		title:'供应商列表',
		width:950,
		height:300
	});
	$("#partsWindow").dialog({
		title:'配件列表',
		width:950,
		height:300
	});
	$("#supplierWindow").dialog("close");
	$("#partsWindow").dialog("close");
	//添加用户弹窗
	$("#word").dialog({
		title:'word',
		width:950,
		height:350,
		buttons:[{
			text:'导出word并打印',handler:function(){
			
			}
		},{
			text:'关 闭',handler:function(){
				$("#word").dialog("close");
			}
		}]
	});
	$("#window").dialog({
		title:'window',
		width:900,
		height:400,
		buttons:[{
			text:'新 增',handler:function(){
				if(opt == 1){
					$("#windowForm").attr("action",
							"/jereh/purchase/AddPurchaseReturnServlet?purchaseReturnPageNo=" + 
									getCurrPageNo());
					$("#windowForm").get(0).submit();
				}
				if(opt == 3){
					$("#windowForm").attr("action",
							"/jereh/purchase/AddPurchaseReturnDetailServlet?purchaseReturnPageNo=" + 
									getCurrPageNo());
					$("#windowForm").get(0).submit();
				}
			}
		},{
			text:'保 存',handler:function(){
				if(opt == 2){
					$("#windowForm").attr("action",
							"/jereh/purchase/UpdatePurchaseReturnServlet?purchaseReturnPageNo=" + 
									getCurrPageNo());
					$("#windowForm").get(0).submit();
				}
			}
		},{
			text:'关 闭',handler:function(){
				$("#window").dialog("close");
			}
		}]
	});
	$("#word").dialog("close");
	$("#window").dialog("close");
});

var index = 0;

/*清空复位按钮*/
function rst(){
	$("input[name='code']").val("");
	$("input[name='rdate']").val("");
	$("input[name='supplierCode']").val("");
	$("input[name='contacter']").val("");
	$("input[name='telPhone']").val("");
	$("input[name='fax']").val("");
	$("input[name='remarks']").val("");
	$("input[name='isShow']").val("");
	$("input[name='nums']").val("");
	$("input[name='numsPrice']").val("");
	$("input[name='state']").val("");
	$("input[name='addUser']").val("");
	$("input[name='addUserName']").val("");
	$("input[name='addIP']").val("");
	$("input[name='compCode']").val("");
}
function detailRst(){
	$("input[name='dcode']").val("");
	$("input[name='ctcode']").val("");
	$("input[name='rkcode']").val("");
	$("input[name='pcode']").val("");
	$("input[name='pdNums']").val("");
	$("input[name='price']").val("");
	$("input[name='remarks']").val("");
}
function rstTool(){
	$("input[name='purchaseReturnCode']").val("");
	$("#beforeDateTool").datebox("setValue","");
	$("#afterDateTool").datebox("setValue","");
	$("input[name='purchaseReturnSupplierCode']").val("");
}

/*得到当前页码*/
function getCurrPageNo(){
	return $("#purchaseReturnList").datagrid("getPager").data("pagination").options.pageNumber;  
}

//删除单个
function del(idx){
	var row = $("#purchaseReturnList").datagrid("getData").rows[idx];
	$.messager.confirm("删除提醒","确实执行删除操作吗？",function(r){
		if(r){
			window.location.href = "/jereh/purchase/DeletePurchaseReturnServlet?purchaseReturnPageNo=" + 
					getCurrPageNo() + "&code=" + row.code;
		}
	});
}
//批量删除
function deleteBatch(){
	var rows = $("#purchaseReturnList").datagrid("getSelections");//得到被select的一个数组
	if(rows.length == 0)
		$.messager.alert("信息提示","请选择一条记录");
	else{
		$.messager.confirm("删除确认","确实要删除记录吗？",function(r){
			if(r){//如果点击了确定则执行
				var codes = [];
				for(var i = 0; i < rows.length; i++)
					codes.push(rows[i].code);
				window.location.href = "/jereh/purchase/DeleteBatchPurchaseReturnServlet?purchaseReturnPageNo=" + 
						getCurrPageNo() + "&codes=" + codes;
			}
		});
	}
}
//修改
function update(idx){
	$("#returnDetail").hide();
	$("#return").show();
	var row = $("#purchaseReturnList").datagrid("getData").rows[idx];
	$.ajax({
		url:'/jereh/purchase/PurchaseReturnServlet?code=' + row.code,
		//async:false,//这种情况很少见；//async:true,//默认，异步，并行；
		dataType:'json',
		error:function(){},
		success:function(data){
			$("input[name='code']").val(data[0].code);
			$("input[name='rdate']").val(data[0].rdate);
			$("input[name='supplierCode']").val(data[0].supplierCode);
			$("input[name='contacter']").val(data[0].contacter);
			$("input[name='telphone']").val(data[0].telphone);
			$("input[name='fax']").val(data[0].fax);
			$("input[name='remarks']").val(data[0].remarks);
			$("input[name='isShow']").val(data[0].isShow);
			$("input[name='nums']").val(data[0].nums);
			$("input[name='numsPrice']").val(data[0].numsPrice);
			$("input[name='state']").val(data[0].state);
			$("input[name='addUser']").val(data[0].addUser);
			$("input[name='addUserName']").val(data[0].addUserName);
			$("input[name='addIP']").val(data[0].addIP);
			$("input[name='compCode']").val(data[0].compCode);
		}	
	});
	opt = 2;//修改
	$("#window").dialog("open");
}
//添加
function add(){
	rst();
	opt = 1;//添加
	$("input[name='ctcode']").val("");
	$("#return").show();
	$("#returnDetail").show();
	$("#window").dialog("open");
}
//查询
function find(){
	$("#findForm").attr("action",
			"/jereh/purchase/FindPurchaseReturnServlet?purchaseReturnPageNo=" + 
					getCurrPageNo());
	$("#findForm").get(0).submit();
}
//详显
var ctcode = '';
function detail(idx){
	state = 1;
	var row = $("#purchaseReturnList").datagrid("getData").rows[idx];
	$("#confirm").html("单据编号为：" + row.code + "的明细如下所列！");
	ctcode = row.code;//保存
	$("#detailList").datagrid({//往哪里添加table
		title:'详显',
//		fitColumns:true,
		idField:'dcode',//必须选择一个id
		url:'/jereh/purchase/GetPurchaseReturnDetailServlet?ctcode=' + row.code,
		columns:[[
			{field:'code',title:'明细编号',width:150},
			{field:'ctcode',title:'退货编号',width:150},
			{field:'rkcode',title:'入库编号',width:150},
			{field:'pcode',title:'配件编号',width:150},
			{field:'nums',title:'数量',width:150},
			{field:'remarks',title:'备注',width:150},
			{field:'opt',title:'操作',width:150,formatter:function(val,row,idx){
				return "<input type='button' value='删除' onclick='detailDel(" + idx + ")'>";
			}}
		]],
		fit:true
	});
}
//导出excel
function excelOut(){
	$.messager.confirm("导出excel","确实执行导出excel操作吗？",function(r){
		if(r){
			window.location.href = "/jereh/purchase/PurchaseReturnExcelServlet?purchaseReturnPageNo=" + 
					getCurrPageNo();
		}
	});
}
//导出word
function wordOut(idx){
	var row = $("#purchaseReturnList").datagrid("getData").rows[idx];
	$.ajax({
		url:'/jereh/purchase/PurchaseReturnServlet?code=' + row.code,
		//async:false,//这种情况很少见；//async:true,//默认，异步，并行；
		dataType:'json',
		error:function(){},
		success:function(data){
			$("input[name='code2']").val(data[0].code);
			$("input[name='rdate2']").val(data[0].rdate);
			$("input[name='supplierCode2']").val(data[0].supplierCode);
			$("input[name='contacter2']").val(data[0].contacter);
			$("input[name='telPhone2']").val(data[0].telPhone);
			$("input[name='fax2']").val(data[0].fax);
			$("input[name='addUserName2']").val(data[0].addUserName);
			$("input[name='remarks2']").val(data[0].remarks);
		}	
	});
	$.ajax({
		url:'/jereh/purchase/GetPurchaseReturnDetailServlet?ctcode=' + row.code,
		//async:false,//这种情况很少见；//async:true,//默认，异步，并行；
		dataType:'json',
		error:function(){},
		success:function(data){
			$("#detailTable").html("");
			var sumAmount = 0;
			var sumMoney = 0;
			for(var i = 0; i < data.length; i++){
				var trObj = $("<tr>").appendTo("#detailTable");
				trObj.append(
					'<td class="detailTd">' + data[i].dcode + "</td>" +
					'<td class="detailTd" style="width:130px;">' + data[i].ctcode + "</td>" +
					'<td class="detailTd">' + data[i].rkcode + "</td>" +
					'<td class="detailTd">' + data[i].pcode + "</td>" +
					'<td class="detailTd">' + data[i].nums + "</td>" +
					'<td class="detailTd">' + data[i].price + "</td>" +
					'<td class="detailTd">' + data[i].nums*data[i].price + "</td>" +
					'<td class="detailTd">' + data[i].remarks + "</td>"
				);
				sumAmount += data[i].nums;
				sumMoney += data[i].nums*data[i].price;
			}
			if(data.length != 0){
				trObj = $("<tr>").appendTo("#detailTable");
				trObj.append(
					'<td colspan="3"></td>' +
					'<td class="detailTd">合计</td>' +
					'<td class="detailTd">' + sumAmount + '</td>' +
					'<td class="detailTd"></td>' +
					'<td class="detailTd">' + sumMoney + '</td>' +
					'<td colspan="2"></td>'
				);
			}
		}
	});
	$("#word").dialog("open");
}
function detailAdd(){
	if(state == 1){
		$("#return").hide();
		detailRst();
		opt = 3;
		$("#returnDetail").show();
		$("input[name='ctcode']").val(ctcode);
		$("#window").dialog("open");
	}
}
function detailDel(idx){
	var row = $("#detailList").datagrid("getData").rows[idx];
	$.messager.confirm("删除提醒","确实执行删除操作吗？",function(r){
		if(r){
			window.location.href = "/jereh/purchase/DeletePurchaseReturnDetailServlet?purchaseReturnPageNo=" + 
					getCurrPageNo() + "&dcode=" + row.code + "&ctcode=" + row.ctcode;
		}
	});
}
function supplierList(){
	$("#supplierList").datagrid({//往哪里添加table
		fitColumns:true,
		toolbar:'#supplierTool',//把newsTool标识成工具栏
		url:'/jereh/BaseCustomerSupplier/GetBaseCustomerSupplierServlet',
		columns:[[
			{field:'csName',title:'供应商名',width:120,fixed:true},
			{field:'contacter',title:'联系人',width:120,fixed:true},
			{field:'telephone',title:'联系电话',width:120,fixed:true},
			{field:'fax',title:'传真',width:120,fixed:true},
			{field:'addUser',title:'操作用户',width:120,fixed:true},
			{field:'addUserName',title:'操作员名',width:120,fixed:true},
			{field:'addIP',title:'操作IP',width:120,fixed:true}
		]],
		fit:true,
		onDblClickRow:function(idx,data){
			var row = $("#supplierList").datagrid("getData").rows[idx];
			$("input[name='supplierCode']").val(row.csName);
			$("input[name='contacter']").val(row.contacter);
			$("input[name='telphone']").val(row.telephone);
			$("input[name='fax']").val(row.fax);
			$("input[name='addUser']").val(row.addUser);
			$("input[name='addUserName']").val(row.addUserName);
			$("input[name='addIP']").val(row.addIP);
			$("#supplierWindow").dialog("close");
		}
	});
	$("#supplierWindow").dialog("open");
}
function partsList(){
	$("#partsList").datagrid({//往哪里添加table
		fitColumns:true,
		toolbar:'#partsTool',//把newsTool标识成工具栏
		url:'/jereh/baseParts/SearchBasePartsServlet',
		columns:[[
			{checkbox:true},
			{field:'partsCode',title:'配件编号',width:200,fixed:true},
			{field:'salePrice',title:'单价',width:200,fixed:true}
		]],
		fit:true,
		onDblClickRow:function(idx,data){
			var row = $("#partsList").datagrid("getData").rows[idx];
			$("input[name='pcode']").val(row.partsCode);
			$("input[name='price']").val(row.salePrice);
			$("#partsWindow").dialog("close");
		}
	});
	$("#partsWindow").dialog("open");
}
</script>
</head>

<body>
<div id="tool">
	<form id="findForm" action="" method="post">
		<b>检索条件：</b>询价编号：<input type="text" name="purchaseReturnCode" style="width:130px;" />&nbsp;开始日期：<input 
				type="text" class="easyui-datebox" id="beforeDateTool" name="purchaseReturnBeforeDate" 
				style="width:130px;" />&nbsp;结束日期：<input type="text" class="easyui-datebox" 
				id="afterDateTool" name="purchaseReturnAfterDate" style="width:130px;" />&nbsp;供应商名：<input 
				type="text" name="purchaseReturnSupplierCode" style="width:130px;" /><input type="button" 
				value="搜索" onclick="find()" /><input type="button" value="重置" onclick="rstTool()" />
	</form>
	<button class="easyui-linkbutton" iconCls="icon-add" onclick="find()">查询</button>
	<button class="easyui-linkbutton" iconCls="icon-add" onclick="add()">添加</button>
	<button class="easyui-linkbutton" iconCls="icon-add" onclick="deleteBatch()">批量删除</button>
	<button class="easyui-linkbutton" iconCls="icon-add" onclick="excelOut()">导出EXCEL</button>
	<button class="easyui-linkbutton" iconCls="icon-add" onclick="detailAdd()">添加配件</button>
</div>
<div id="purchaseReturnList">
	<!--table-->
</div>
<div id="window" style="padding:10px;">
	<form id="windowForm" action="" method="post">
		<table id="return" border="1" cellspacing="0" style="border-collapse:collapse;">	
            <tr>
                <td class="label">订单编号：</td>
                <td class="in"><input type="text" name="code" readonly="readonly" /></td>
                <td class="label">订单日期：</td>
                <td class="in"><input type="text" name="rdate" readonly="readonly" /></td>
            </tr>
            <tr>
                <td class="label"><input type="button" value="供应商名：" onclick="supplierList()" /></td>
                <td><input type="text" name="supplierCode" /></td>
                <td class="label">联系人：</td><td><input type="text" name="contacter" /></td>
            </tr>
            <tr>
                <td class="label">联系电话：</td><td><input type="text" name="telphone" /></td>
                <td class="label">传真：</td><td><input type="text" name="fax" /></td>
            </tr>
            <tr>
            	<td class="label">是否显示：</td>
                <td>
                	<input type="radio" name="isShow" value="1" checked="checked" />显示
                	<input type="radio" name="isShow" value="0" />隐藏
                </td>
                <td class="label">数量：</td><td><input type="text" name="nums" /></td>
            </tr>
            <tr>
                <td class="label">单价：</td><td><input type="text" name="numsPrice" /></td>
                <td class="label">状态：</td><td><input type="text" name="state" /></td>
            </tr>
            <tr>
                <td class="label">添加日期：</td><td><input type="text" name="addDate" /></td>
                <td class="label">操作用户：</td><td><input type="text" name="addUser" /></td>
            </tr>
            <tr>
                <td class="label">操作员名：</td><td><input type="text" name="addUserName" /></td>
                <td class="label">操作IP：</td><td><input type="text" name="addIP" /></td>
            </tr>
         	<tr>
            	<td class="label">公司编号：</td><td><input type="text" name="compCode" /></td>
            </tr>
            <tr>
            	<td class="label">备注：</td>
            	<td colspan="3"><input type="text" name="remarks" style="width:600px;" /></td>
            </tr>
    	</table>
    	<br/>
    	<table id="returnDetail" border="1" cellspacing="0" style="border-collapse:collapse;">	
    		<tr>
                <td class="label">采购退货明细主键：</td>
                <td class="in"><input type="text" name="dcode" /></td>
                <td class="label">采购退货单据编号：</td>
                <td class="in"><input type="text" name="ctcode" readonly="readonly" /></td>
            </tr>
            <tr>
                <td class="label">入库单号：</td><td><input type="text" name="xcode" /></td>
                <td class="label"><input type="button" value="配件编号：" onclick="partsList()" /></td>
                <td><input type="text" name="pcode" /></td>
            </tr>
            <tr>
                <td class="label">数量：</td><td><input type="text" name="pdNums" /></td>
                <td class="label">单价：</td><td><input type="text" name="price" /></td>
            </tr>
            <tr>
            	<td class="label">备注：</td>
            	<td colspan="3"><input type="text" name="remarks" style="width:600px;" /></td>
            </tr>
    	</table>
    </form>
</div>
<span id="confirm" style="font-size:14px; font-weight:bold;">
</span>
<div id="detailList">
	<!--detail_table-->
</div>
<div id="word" style="padding:10px;">
	<form id="wordForm" action="" method="post">
		<table border="1" cellspacing="0" style="border-collapse:collapse;">	
	    	<tr>
	        	<td class="label">订单编号：</td><td class="in"><input type="text" name="code2" readonly="readonly" /></td>
	        	<td class="label">订单日期：</td><td class="in"><input type="text" name="rdate2" readonly="readonly" /></td>
	        </tr>
	        <tr>
	        	<td class="label">供应商名：</td><td class="in"><input type="text" name="supplierCode2" readonly="readonly" /></td>
	        	<td class="label">联系人员：</td><td class="in"><input type="text" name="contacter2" readonly="readonly" /></td>
	        </tr>
	        <tr>
	        	<td class="label">电话：</td><td class="in"><input type="text" name="telphone2" readonly="readonly" /></td>
	        	<td class="label">传真：</td><td class="in"><input type="text" name="fax2" readonly="readonly" /></td>
	        </tr>
	        <tr>
	        	<td class="label">业务人员：</td><td class="in"><input type="text" name="addUserName2" readonly="readonly" /></td>
	        </tr>
	        <tr>
	        	<td class="label">备注：</td><td colspan="3"><input type="text" name="remarks2" style="width:600px;" readonly="readonly" /></td>
	        </tr>
		</table>
		<br/>
		<table border="1" cellspacing="0" style="border-collapse:collapse;">
			<tr>
				<td class="detailTd">项号</td>
				<td class="detailTd" style="width:130px;">订单编号</td>
				<td class="detailTd">件号</td><td class="detailTd">配件型号</td>
				<td class="detailTd">数量</td><td class="detailTd">单价</td>
				<td class="detailTd">金额</td>
				<td class="detailTd">备注</td>
			</tr>
		</table>
		<table id="detailTable" border="1" cellspacing="0" style="border-collapse:collapse; position:relative; bottom:1px;">
		</table>
	</form>
</div>
<div id="supplierWindow">
	<div id="supplierTool">
		<b>检索条件：</b>件号：<input type="text" />&nbsp;名称：<input type="text" />
				<input type="button" value="搜索" onclick="supplierFind()" />
				<input type="button" value="重置" onclick="supplierRstTool()" />
	</div>
	<div id="supplierList">
		<!--供应商列表-->
	</div>
</div>
<div id="partsWindow">
	<div id="partsTool">
		<b>检索条件：</b>件号：<input type="text" />&nbsp;名称：<input type="text" />
				<input type="button" value="搜索" onclick="partsFind()" />
				<input type="button" value="重置" onclick="partsRstTool()" />
	</div>
	<div id="partsList">
		<!--配件列表-->
	</div>
</div>
</body>
</html>
