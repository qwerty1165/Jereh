<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>销售订单管理</title>
<script src="/jereh/js/jquery-1.7.2.min.js"></script>
<script src="/jereh/js/jquery.easyui.min.js"></script>
<link type="text/css" href="/jereh/themes/default/easyui.css" rel="stylesheet" />
<link type="text/css" href="/jereh/themes/icon.css" rel="stylesheet" />
<style>
body{
  padding: 0px;
  margin: 0px;
}
.search {
	background-color: #C9DFFF;
	font-size: 13px;
	padding:0px;
	margin:0px;
}

  td{padding:2px;}
  .td1{width:200px;}
  .td2{width:500px;}

</style>
<script>

$(function(){
    $("#showData").hide();
    $("#List").hide();
      
      
    $("#customer").dialog({		
		width:800,high:400,
		modal:true,
		closed:true			
	});	
	$("#customer").dialog("close");
	
	$("#parts").dialog({		
		width:800,high:400,
		modal:true,
		closed:true			
	});	
	$("#parts").dialog("close");
    $("#dg").dialog({		//设置弹窗属性
		width:1000,height:500,
		modal:true,
		closed:true			
	});  
	$("#dg").dialog("close"); 
	
	//格式化日期
	$("input.easyui-datebox").datebox({
		formatter:function(date){
			var y = date.getFullYear();
			var m = date.getMonth() + 1;
			var d = date.getDate();		
			return y+"-"+m+"-"+d;  
		},
		parse:function(date){
			var time = Date.parse(date);
			if(!isNaN(time))
				return new Date(time);
			else
				return new Date();
		}
	});	
$("#orderList").datagrid({//往哪里添加table	
	toolbar:'#barListTb',
	idField:'code',
//	fit : true,
	striped : true,
	singleSelect : false,
	checkOnSelect : false,
	url:'/jereh/saleOrder/GetSaleOrderServlet',
    columns:[[{field:'id',checkbox:true,},
              {field:'code',title:'订单编号',fixed:true,
                  formatter:function(val,row,idx){
                       return  "<a onclick=\"show('"+row.code+"')\" href='#' target='_self'>"+val+"</a>";
                  }},
             {field:'orderDate',title:'订单日期',fixed:true},
			{field:'csName',title:'客户名称',fixed:true},
			{field:'nums',title:'数量',fixed:true},
			{field:'numsPrice',title:'总货值',fixed:true},
			{field:'contacter',title:'联系人',fixed:true},
			{field:'telphone',title:'联系方式',fixed:true},			
			{field:'state',title:'审核状态',fixed:true,
				formatter:function(val,row,idx){
				if(val=="1") return "已审核";
				else return "未审核";				
				}
			},
			{field:'addUser',title:'操作员',fixed:true},
			{field:'opt',title:'操作',fixed:true,formatter:function(val,row,idx){
				var content = "<input type='button' value='删除' onclick='delParts(\"" + row.partsCode + "\")' />";
				content += "<input type='button' value='修改' onclick='updateRow(\"" + idx + "\")' />";
				content += "<input type='button' value='打印并下载' onclick='printParts(" + idx + ")' />";
				return content;	
			}}
    
    ]],
    pagination : true,
	pageList : [ 3, 5, 10 ],
	pageSize : 10,
    
});
  $('#barList').datagrid('getPager').pagination({
    	displayMsg:'当前显示从第 {from} 条到第 {to} 条，共 {total} 条记录'
	}); 
});	

 function getCurDate(){
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();
	var h = date.getHours();
	var m = date.getMinutes();
	return "MTBJ"+year+month+day+h+m;
   }
 function getAddDate(){
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth()+1;
    var day = date.getDate();
    return year+"-"+month+"-"+day;
}
   
function delRow(code){
    $.messager.confirm("删除提醒","确认删除吗？",function(r){
    if(r){
      $.ajax({
        url:'/jereh/SaleQuotation/DeleteSaleQuotationServlet',
        data:{'code':code},
        success:function(data){
          $("#orderList").datagrid("reload");
        }
      });
    }
    });
}
function show(code){
   $("#codeinfo").text(code);
   $("#showData").show();
   $("#showList").datagrid({
       url:'/jereh/SaleQuotation/GetSaleQuotationDetailServlet',
       queryParams:{'code':code},
       columns:[[{field:'code',title:'报价单号',fixed:true},
                 {field:'scode',title:'件号',fixed:true},
                 {field:'pcode',title:'配件名称',fixed:true},
                 {field:'nums',title:'配件品牌',fixed:true},
                 {field:'price',title:'配件型号',fixed:true},
                 {field:'deliveryMode',title:'数量',fixed:true},
                  {field:'price',title:'金额',fixed:true},
                 {field:'remarks',title:'备注',fixed:true}
       ]]
   });
}

function showDailog(stitle){
	$("#dg").dialog({title:stitle});
	$("#dg").dialog("open");
}
function closeDialog(){
	$("#dg").dialog("close");
}
//添加订单
function addData(){
	showDailog("添加定单");	
	$("input[name='opt']").val("1");//opt=1表示添加，opt=2表示修改		
	$("input[name='code']").val(getCurDate());
}
//修改订单
function updateRow(idx){
	showDailog("修改定单");	
	$("input[name='opt']").val("2");//opt=1表示添加，opt=2表示修改	
	var row=$("#orderList").datagrid("getRows")[idx];	
	var code = row.code;
	var sqDate = row.sqDate;
	var csName = row.csName;
	var contacter = row.contacter;
	var telphone = row.telphone;
	var fax = row.fax;
	var remarks = row.remarks;
	$("input[name='code']").val(code).attr("readonly",true);
	$("#sqDate").datebox("setValue",sqDate);
	$("input[name='csName']").val(csName);
	$("input[name='contacter']").val(contacter);
	$("input[name='telphone']").val(telphone);
	$("input[name='fax']").val(fax);
	$("input[name='remarks']").val(remarks);
	
    $("#List").show();
    $("#List").datagrid({
		url:'',
		columns:[[{field:'partsNo',title:'件号',fixed:true},
		          {field:'partsName',title:'配件名称',fixed:true},
		          {field:'partsBrand',title:'配件品牌',fixed:true},
		          {field:'partsModel',title:'配件型号',fixed:true},
		          {field:'nums',title:'数量',fixed:true},
		          {field:'price',title:'单价',fixed:true},
		          {field:'numsPrice',title:'金额',fixed:true},
		          {field:'deliveryMode',title:'交货期',fixed:true},
		          {field:'remarks',title:'备注',fixed:true},
		          {field:'opt',title:'操作',fixed:true,
		             formatter : function(val, row, idx) {//"<input type='button' value='删除' onclick=\"delRow('"+row.code+"')\" />"
								var content="<input type='button' value='删除' onclick=\"delRow('"+ row.code+ "')\" /> ";
											return content;}}]]		
  });
}
function showName(){
   $("#customer").dialog({title:"请选择客户"});
   $("#customer").dialog("open");
   $("#cusList").datagrid({  
       onDblClickRow:function(idx, row){
          var row=$("#cusList").datagrid("getRows")[idx];
          var code=row.code;
          var csName=row.csName;
          var contacter=row.contacter;
    	  var telphone=row.telephone;
    	  var fax=row.fax;
           $("input[name='code']").val(code);
		  $("input[name='csName']").val(csName);
		  $("input[name='contacter']").val(contacter);
		   $("input[name='telphone']").val(telphone);
		    $("input[name='fax']").val(fax);
       },
        url:'/jereh/BaseCustomerSupplier/GetBaseCustomerSupplierServlet',
       toolbar:'#cusListTb',
	   idField:'code',
       columns:[[{field:'code',title:'客户代码',fixed:true},
       			 {field:'csName',title:'客户名称',fixed:true},
       			 {field:'contacter',title:'联系人员',fixed:true},
       			 {field:'telephone',title:'电话',fixed:true},
       			 {field:'fax',title:'传真',fixed:true},
       			 {field:'address',title:'地址',fixed:true},
   ]]}); 
}
function addParts(){
   $("#parts").dialog({title:"选择配件"});
   $("#parts").dialog("open");
   $("#parList").datagrid({
       url:'',
       toolbar:'#parListTb',
	   idField:'',
       columns:[[{field:'',title:'件号',fixed:true},
       			 {field:'',title:'配件名称',fixed:true},
       			 {field:'',title:'配件品牌',fixed:true},
       			 {field:'',title:'配件型号',fixed:true},
       			 {field:'',title:'所属仓库',fixed:true},
       			 {field:'',title:'销售单价',fixed:true},
       			 {field:'',title:'库存量',fixed:true},
       			 {field:'',title:'上次价格',fixed:true},
       			 {field:'',title:'备注',fixed:true},
   ]]});
   
}
</script>
	</head>
	<body>
	<div id="orderList"></div>
		<div id="barListTb">
			<form action="" name="search" method="post" class="search">
				<b>检索条件:</b>
				订单编号:<input type="text" name="searchcode" />
				开始日期:<input type="text" class="easyui-datebox" name="startDate" />
				结束日期:<input type="text" class="easyui-datebox" name="endDate" />
				<input type="button" value="搜索" onclick="search()"/>
				<input type="reset" value="重置" />
				</form>
				<button class="easyui-linkbutton" iconCls="icon-search" onclick="SearchData()">查询</button>
				<button class="easyui-linkbutton" iconCls="icon-add" onclick="addData()">增加</button>
				<button class="easyui-linkbutton" iconCls="icon-remove" onclick="del()">批量删除</button>
				<button class="easyui-linkbutton" iconCls="icon-ok" onclick="outExcel()">导出excel</button>		
		</div>
		<div id="showData">
		单据编号为: <strong id="codeinfo"></strong>的明细如下列！
		<div id="showList"></div>
		</div>
		
	<div id="dg" style="padding:20px">
    <form action="/jereh/saleOrder/UpdateSaleOrderServlet" >
    <input type="hidden" name="customerName">
     <input type="hidden" name="opt" />
      <table border="1" style="border-collapse:collapse;">   
        <tr><td class="td1"><span style="color:red">*</span>订单编号：</td><td class="td2"><input name="code" type="text"></td>
            <td class="td1"><span style="color:red">*</span>订单日期：</td><td class="td2"><input id="orderDate" name="orderDate" type="text"  class="easyui-datebox"></td></tr>
        <tr><td class="td1"><span style="color:red">*</span>客户名称：</td><td class="td2"><input type="text" name="csName" onclick="showName()"></td>
            <td class="td1"><span style="color:red">*</span>联系人员：</td><td class="td2"><input type="text" name="contacter"></td></tr>
        <tr><td class="td1">电话：</td><td class="td2"><input type="text" name="telphone" ></td>
            <td class="td1">传真：</td><td class="td2"><input type="text" name="fax" ></td></tr> 
        <tr><td class="td1">运输方式</td><td class="td2">
        <select name="trans">
        <option value="1">圆通快递</option>
        <option value="2">申通快递</option>
        <option value="3">顺丰快递</option>
        </select>
        </td>
        <td class="td1"><span style="color:red">*</span>交货日期：</td><td class="td2"><input id="deLiveryDate" name="deLiveryDate" type="text"  class="easyui-datebox"></td></tr></tr>   
        <tr><td class="td1">备注：</td><td rowspan="3"><input type="text" name="remarks" ></td></tr>
      </table>
      <br/>
      <input type="submit" value="新增"  />
       <input type="button" value="选报价单" />
      <input type="button" value="添加配件" onclick="addParts()" />
      <input type="button" value="保存" />
      <input type="button" value="审核" />
      <input type="button" value="撤销" />
      <input type="button" value="打印" />
      <input type="button" value="生成合同" />
      <input type="button" value="生成Word" />
      <input type="button" value="生成出库" />
      <input type="button" value="完成订单" />
      <input type="button" value="关闭" onclick="closeDialog()"/>
    </form>
     <div id="List">  
     </div>
     </div>
      
     <!-- 客户选择 -->    
     <div id="customer">
     <div id="cusListTb">
       <form action="" method="post" >
          <b>检索条件：</b>
          	客户代码<input type="text"> 
          	客户名称<input type="text">
          	<input type="button" value="搜索" onclick=""/>
			<input type="reset" value="重置" />
       </form>
       </div>
	   	<div id="cusList"></div>
     </div>
     <!-- 配件选择 -->
     <div id="parts">
     <div id="parListTb">
       <form action="" method="post" >
          <b>检索条件：</b>
          	件号<input type="text"> 
          	名称<input type="text">
          	仓库<select class="easyui-combobox">
          	   <option>--选择仓库--</option>
          	   <option>主仓库</option>
          	   <option></option>
          	<input type="button" value="搜索" onclick=""/>
			<input type="reset" value="重置" />
       </form>
       </div>
	   	<div id="parList"></div>
     </div>
	</body>
</html>
