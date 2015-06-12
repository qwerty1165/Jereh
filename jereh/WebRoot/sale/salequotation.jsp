<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>销售报价管理</title>
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

</style>
<script>

$(function(){
    $("#showData").hide();
    
      
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
    $("#dg").dialog({		
		fit:true,
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
$("#barList").datagrid({//往哪里添加table	
	toolbar:'#barListTb',
	idField:'code',
//	fit : true,
	striped : true,
	singleSelect : false,
	checkOnSelect : false,
	url:'/jereh/SaleQuotation/GetSaleQuotationServlet',
    columns:[[{field:'id',checkbox:true,},
              {field:'code',title:'报价单号',fixed:true,
                  formatter:function(val,row,idx){
                       return  "<a onclick=\"show('"+row.code+"')\" href='#' target='_self'>"+val+"</a>";
                  }},
              {field:'sqDate',title:'报价日期',fixed:true},
              {field:'csName',title:'客户名称',fixed:true},
              {field:'nums',title:'数量',fixed:true},
              {field:'numsPrice',title:'总货值',fixed:true},
              {field:'contacter',title:'联系人',fixed:true},
              {field:'telphone',title:'联系方式',fixed:true},
              {field:'state',title:'审核状态',fixed:true,
                   formatter:function(val,row,idx){
                     if(val=="1"){
                        return "显示";
                        }if(val=="0"){
                        return "隐藏";
                       }
                     }
                   },
              {field:'addUser',title:'操作员',fixed:true},
              {field:'operate',title:'操作',fixed:true,
					 formatter : function(val, row, idx) {//"<input type='button' value='删除' onclick=\"delRow('"+row.code+"')\" />"
								var content="<input type='button' value='删除' onclick=\"delRow('"+ row.code
													+ "')\" /> <input type='button' value='修改' onclick=\"updateRow("
													+ idx + ")\" />";
											return content;
										}},
			  {field:'customercode',title:'客户编号',hidden:true},
			  {field:'fax',title:'传真',hidden:true},
			  {field:'remarks',title:'备注',hidden:true}
    
    ]],
    pagination : true,
	pageList : [ 3, 5, 10 ],
	pageSize : 5,
    
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
          $("#barList").datagrid("reload");
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
       columns:[[{field:'code',title:'报价单明细编号',fixed:true},
                 {field:'scode',title:'报价单编号',fixed:true},
                 {field:'pcode',title:'配件编号',fixed:true},
                 {field:'nums',title:'数量',fixed:true},
                 {field:'price',title:'单价',fixed:true},
                 {field:'deliveryMode',title:'交货期',fixed:true},
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
	showDailog("添加报价单");	
	$("#SList").hide();	
	$("input[name='opt']").val("1");//opt=1表示添加，opt=2表示修改		
	$("input[name='code']").val(getCurDate());
	$("#sqDate").datebox("setValue",new Date);
	$("input[name='csName']").val("");
	$("input[name='contacter']").val("");
	$("input[name='telphone']").val("");
	$("input[name='fax']").val("");
	$("input[name='remarks']").val("");
}
//修改订单
function updateRow(idx){
	showDailog("修改报价单");
	$("#SList").show();	
	$("input[name='opt']").val("2");//opt=1表示添加，opt=2表示修改	
	var row=$("#barList").datagrid("getRows")[idx];	
	var code = row.code;
	var sqDate = row.sqDate;
	var csName = row.csName;
	var customercode = row.customercode;
	var contacter = row.contacter;
	var telphone = row.telphone;
	var fax = row.fax;
	var remarks = row.remarks;
	$("input[name='code']").val(code).attr("readonly",true);
	$("#sqDate").datebox("setValue",sqDate);
	$("input[name='csName']").val(csName);
	$("input[name='customercode']").val(customercode);
	$("input[name='contacter']").val(contacter);
	$("input[name='telphone']").val(telphone);
	$("input[name='fax']").val(fax);
	$("input[name='remarks']").val(remarks);

    $("#List").datagrid({
		url:'/jereh/SaleQuotation/GetSaleQuotationDetailServlet',
		queryParams:{'code':code},
		columns:[[{field:'partsNo',title:'件号',fixed:true},
		          {field:'pcode',title:'件号',hidden:true},
		          {field:'partsName',title:'配件名称',fixed:true},
		          {field:'partsBrand',title:'配件品牌',fixed:true},
		          {field:'partsModel',title:'配件型号',fixed:true},
		          {field:'nums',title:'数量',fixed:true},
		          {field:'price',title:'单价',fixed:true},
		          {field:'numsPrice',title:'金额',fixed:true,
		            formatter:function(val,row,idx){
		              return row.nums*row.price;
		          }},
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
          var fax = row.fax;
          var telphone = row.telephone;
           $("input[name='customercode']").val(code);
		  $("input[name='csName']").val(csName);
		  $("input[name='contacter']").val(contacter).attr("readonly",true);
		  $("input[name='fax']").val(fax).attr("readonly",true);
          $("input[name='telphone']").val(telphone).attr("readonly",true);		  
       },
       url:'/jereh/BaseCustomerSupplier/GetBaseCustomerSupplierServlet',
       toolbar:'#cusListTb',
	   idField:'code',
       columns:[[{field:'code',title:'客户代码',fixed:true},
       			 {field:'csName',title:'客户名称',fixed:true},
       			 {field:'contacter',title:'联系人员',fixed:true},
       			 {field:'telephone',title:'电话',fixed:true},
       			 {field:'fax',title:'传真',fixed:true},
       			 {field:'adress',title:'地址',fixed:true},
   	  	]],   		
        onDblClickRow:function(idx, row){
          var row=$("#cusList").datagrid("getRows")[idx];
          var code=row.code;
          var csName=row.csName;
          var contacter=row.contacter;
          var fax = row.fax;
          var telphone = row.telphone;
          $("input[name='customercode']").val(code);
		  $("input[name='csName']").val(csName);
		  $("input[name='contacter']").val(contacter);
		  $("input[name='fax']").val(fax);
          $("input[name='telphone']").val(telphone);		  
       },

   }); 
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
	<div id="barList"></div>
		<div id="barListTb">
			<form action="" name="search" method="post" class="search">
				<b>检索条件:</b>
				报价单号:<input type="text" name="searchcode" />
				开始日期:<input type="text" class="easyui-datebox" name="startTime" />
				结束日期:<input type="text" class="easyui-datebox" name="endTime" />
				客户名称:<input type="text" name="" />
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
	<div id="dg">
    <form action="/jereh/SaleQuotation/UpdateSaleQuotationServlet" >
     <input type="hidden" name="opt" />
     <input type="hidden" name="customercode" />
      <table border="1" style="border-collapse:collapse;">   
        <tr><td>报价编号：</td><td><input name="code" type="text"></td>
            <td>报价日期：</td><td><input id="sqDate" name="sqDate" type="text"  class="easyui-datebox"></td></tr>
        <tr><td>客户名称：</td><td><input type="text" name="csName" onclick="showName()"></td>
            <td>联系人员：</td><td><input type="text" name="contacter"></td></tr>
        <tr><td>电话：</td><td><input type="text" name="telphone" ></td>
            <td>传真：</td><td><input type="text" name="fax" ></td></tr>    
        <tr><td>备注：</td><td rowspan="3"><input type="text" name="remarks" ></td></tr>
      </table>
      <br/>
      <input type="submit" value="新增"  />
      <input type="button" value="添加配件" onclick="addParts()" />
      <input type="button" value="保存" />
      <input type="button" value="审核" />
      <input type="button" value="撤销" />
      <input type="button" value="打印" />
      <input type="button" value="生成Word" />
      <input type="button" value="生成订单" />
      <input type="button" value="关闭" onclick="closeDialog()"/>
    </form>
    <div id=SList><div id="List"></div>   
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
