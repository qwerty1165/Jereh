
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>往来客户（单位）</title>
<script src="/jereh/js/jquery-1.7.2.min.js"></script>
<script src="/jereh/js/jquery.easyui.min.js"></script>
<link type="text/css" href="/jereh/themes/default/easyui.css" rel="stylesheet" />
<link type="text/css" href="/jereh/themes/icon.css" rel="stylesheet" />
<style>
.label{ width:100px; text-align:right;}
.in{ width:300px;}
td{ border:solid #95ADCC; border-width:1px;}
</style>

<script>

$(function(){
  
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
	//ajax
	$("#customerList").datagrid({//往哪里添加table
		title:'往来客户（单位）',
		idField:'code',//必须选择一个id
		url:'/jereh/BaseCustomerSupplier/GetBaseCustomerSupplierServlet',
		singleSelect:false,//是否只能单选
		fitColumns:true,
		toolbar:'#customerTool',//把newsTool标识成工具栏
		columns:[[	
			{checkbox:true},//添加复选框checkbox:true，表头会多生成一个全选的复选框
			{field:'code',title:'代码',fixed:true},
			{field:'csName',title:'名称',fixed:true},
			{field:'categorycode',title:'类别',fixed:true,
			     formatter:function(val,row,idx){
			       if(val=="1"){
			         return "客户";
			         }
			        else{
			          return "供应商";
			         }
			       
			     }
			},
			{field:'contacter',title:'联系人',fixed:true},
			{field:'telephone',title:'电话',fixed:true},
			{field:'address',title:'地址',fixed:true},
			{field:'isShow',title:'显示状态',fixed:true,
			       formatter:function(val,row,idx){
			          if(val=="1"){
			             return "显示";
			             }
			             else{
			             return "隐藏";
			             }
			          
			       }
			   },
			{field:'opt',title:'操作',fixed:true,formatter:function(val,row,idx){
				var content = "<input type='button' value='删除' onclick='delCustomer(\"" + row.code + "\");' />";
				content += "<input type='button' value='修改' onclick='modifyCustomer(\"" + idx + "\");' />";
				return content;	
			}},
			{field:'adddate',title:'操作日期',width:70,hidden:true},
			{field:'fix',title:'传真',width:70,hidden:true},
			{field:'postcode',title:'邮编',width:70,hidden:true},
			{field:'email',title:'邮箱',width:70,hidden:true},
			{field:'province',title:'所在省',width:70,hidden:true},
			{field:'city',title:'所在市',width:70,hidden:true},
			{field:'legaler',title:'法人代表',width:70,hidden:true},
			{field:'url',title:'网址',width:70,hidden:true},
			{field:'QQ',title:'QQ',width:70,hidden:true},
			{field:'MSN',title:'MSN',width:70,hidden:true},
			{field:'aliwang',title:'阿里旺旺',width:70,hidden:true},
			{field:'agent',title:'委托代理',width:70,hidden:true},
			{field:'bank',title:'银行',width:70,hidden:true},
			{field:'account',title:'开户账号',width:70,hidden:true},
			{field:'tax',title:'税号',width:70,hidden:true},
			{field:'remarks',title:'备注',width:70,hidden:true}
			
		]],
		
		fit:true,
		pagination:true,//翻页
		pageList:[3,5,10],//可选一页显示多少条
		pageSize:5//默认一页显示多少条
	});
	$('#customerList').datagrid('getPager').pagination({
    	displayMsg:'当前显示从第 {from} 条到第 {to} 条，共 {total} 条记录'
	}); 
	
//添加用户弹窗
	
	
});

 function getCurDate(){
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();
	var h = date.getHours();
	var m = date.getMinutes();
	return "MTPJ"+year+month+day+h+m;
   }
function getAddDate(){
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth()+1;
    var day = date.getDate();
    return year+"-"+month+"-"+day;
}

//单个删除客户

function delCustomer(code){
	$.messager.confirm("删除提醒","确认删除吗？",function(r){
		if(r){
		//window.location.href='/jereh/customer/deleteCustomerServlet?code='+code;
		
			 $.ajax({
			     url:'/jereh/BaseCustomerSupplier/DeleteBaseCustomerSupplierServlet?code='+code,
			     success:function(data){			     	
			       $("#customerList").datagrid("reload");
			     }
			 });
		}
			
	});
	
}

//批量删除客户

function delBatchCustomer(){
	var rows = $("#customerList").datagrid("getSelections");//得到被select的一个数组
	if(rows.length == 0){
		$.messager.alert("信息提示","请选择一条记录");
	}else{
		$.messager.confirm("删除确认","确实要删除记录吗？",function(r){
			if(r){//如果点击了确定则执行			    
				for(var i = 0; i < rows.length; i++){
					var code=rows[i].code;
				   $.ajax({
				     url:'/jereh/BaseCustomerSupplier/DeleteBaseCustomerSupplierServlet',
				     data:{'code':code},
				     success:function(data){
				     	$("#customerList").datagrid("reload");	
				     }		    
		       	 	});			
			   }
			}
		});
	}

}


function showDialog(stitle){
	$("#addCustomer").dialog({
		title:stitle,
		width:800,
		height:400,
		modal:true,
		closed:true
		});
		$("#addCustomer").dialog("open");
			}
//添加客户

function addCustomer(){
	showDialog('添加账号');
	$("input[name='opt']").val("1");//opt=1表示添加；opt=2表示修改
	$("input[name='code']").val(getCurDate());
	$("input[name='addDate']").val(getAddDate());
}
//修改客户

function modifyCustomer(idx){
	showDialog('修改账号');
	$("input[name='opt']").val("2");
	var row=$("#customerList").datagrid("getRows")[idx];
	var code=row.code;
	var addDate=row.addDate;
	var csName=row.csName;
	var contacter=row.contacter;
	var telephone=row.telephone;
	var fax=row.fax;
	var postCode=row.postCode;
	var email=row.email;
	var province=row.province;
	var city=row.city;
	var address=row.address;
	var legaler=row.legaler;
	var url=row.url;
	var QQ=row.QQ;
	var MSN=row.MSN;
	var aliwang=row.aliwang;
	var agent=row.agent;
	var bank=row.bank;
	var account=row.account;
	var tax=row.tax;
	var categorycode=row.categorycode;
	var isShow=row.isShow;
	var remarks=row.remarks;	
	$("input[name='code']").val(code).attr("readonly",true);
	$("input[name='addDate']").val(addDate);
	$("input[name='csName']").val(csName);
	$("input[name='contacter']").val(contacter);
	$("input[name='telephone']").val(telephone);
	$("input[name='fax']").val(fax);
	$("input[name='postCode']").val(postCode);
	$("input[name='email']").val(email);
	$("input[name='province']").val(province);
	$("input[name='city']").val(city);
	$("input[name='address']").val(address);
	$("input[name='legaler']").val(legaler);
	$("input[name='url']").val(url);
	$("input[name='QQ']").val(QQ);
	$("input[name='MSN']").val(MSN);
	$("input[name='aliwang']").val(aliwang);
	$("input[name='agent']").val(agent);
	$("input[name='bank']").val(bank);
	$("input[name='account']").val(account);
	$("input[name='tax']").val(tax);
	if(isShow==1){
		$("input[name='isShow']:first").prop("checked",true);
	}else{
		$("input[name='isShow']:last").prop("checked",true);
	};
	if(categorycode==1){
		$("input[name='categorycode']:first").prop("checked",true);
	}else{
		$("input[name='categorycode']:last").prop("checked",true);
	};
	$("input[name='remarks']").val(remarks);
	
}
function search(){
  var code=$("input[name='searchcode']").val();
  var csName=$("input[name='searchcsName']").val();
  var addDate=$("input[name='searchaddDate']").val();
  $("#customerList").datagrid("reload",{'code':code,'csName':csName,'addDate':addDate});
}
function outExcel(){
  window.location.href='/jereh/BaseCustomerSupplier/OutputExcelBaseCustomerSupplierServlet';
}
function freemarker(){
    var code = $("input[name='code']").val();
	
  $.ajax({
    url:'/jereh/BaseCustomerSupplier/OutputWordBaseCustomerSupplierServlet',   
    data:{'code':code},
  })
  $("#addCustomer").dialog("close");
}
</script>
</head>

<body>

<div id="customerTool">
	<form action="" method="post">
        <b>检索条件：</b>
        代码：<input type="text" name="searchcode"  width="10px;"/>
        名称：<input type="text" name="searchcsName" />
        操作日期：<input type="text" class="easyui-datebox"  name="searchaddDate" />
        <input type="button" value="搜索" onclick="search()"/>
        <input type="reset" value="重置" />
    </form> 
	<button class="easyui-linkbutton" iconCls="icon-search" onclick="customerSearch()">查询</button>
	<button class="easyui-linkbutton" iconCls="icon-add" onclick="addCustomer()">增加</button>
    <button class="easyui-linkbutton" iconCls="icon-remove" onclick="delBatchCustomer()">批量删除</button>
    <button class="easyui-linkbutton" iconCls="icon-ok" onclick="outExcel()">导出excel</button>
</div>
<div id="customerList">
	<!--table-->
</div>

<div id="addCustomer" style="padding:10px;">
	<table border="1" cellspacing="0" style="border-collapse:collapse;">	
    	<form id="myFrm" action="/jereh/BaseCustomerSupplier/UpdateBaseCustomerSupplierServlet" method="post" >
    	<input type="hidden" name="id" /> 
 	    <input type="hidden" name="opt"/>
    	<tr>
        	<td class="label">代码：</td><td class="in"><input type="text" name="code" /></td>
        	<td class="label">操作日期：</td><td class="in"><input type="text" name="addDate" /></td>
            
        </tr>
        <tr>
        	<td class="label">名称：</td><td><input type="text" name="csName" /></td>
    		<td class="label">联系人员：</td><td><input type="text" name="contacter" /></td>
        </tr>
        <tr>
        	<td class="label">电话：</td><td><input type="text" name="telephone" /></td>
            <td class="label">传真：</td><td><input type="text" name="fax" /></td>
        </tr>
        <tr>
        	<td class="label">邮编：</td><td><input type="text" name="postCode" /></td>
            <td class="label">邮箱：</td><td><input type="text" name="email" /></td>
        </tr>
      	<tr>
        	<td class="label">省市：</td>
            <td>
                <select name="province">
                    <option value="北京">北京</option>
                    <option value="山东">山东</option>
                </select>&nbsp;省（直辖市）
                <select name="city">
                    <option value="北京">北京</option>
                    <option value="烟台">烟台</option>
                </select>&nbsp;市
            </td>
          	<td class="label">地址：</td><td><input type="text" name="address" /></td>
        </tr>
        <tr>
        	<td class="label">法人代表：</td><td><input type="text" name="legaler" /></td>
            <td class="label">网址：</td><td><input type="text" name="url" /></td>
        </tr>
        <tr>
        	<td class="label">QQ：</td><td><input type="text" name="QQ" /></td>
            <td class="label">MSN：</td><td><input type="text" name="MSN" /></td>
        </tr>
        <tr>
        	<td class="label">阿里旺旺：</td><td><input type="text" name="aliwang" /></td>
            <td class="label">委托代理：</td><td><input type="text" name="agent" /></td>
        </tr>
        <tr>
        	<td class="label">银行：</td><td><input type="text" name="bank" /></td>
            <td class="label">开户账号：</td><td><input type="text" name="account" /></td>
        </tr>
        <tr>
        	<td class="label">税号：</td><td><input type="text" name="tax" /></td>
            <td class="label">类别：</td>
            <td>
            	<input type="radio" name="categorycode" value="1" checked="checked" />客户
                <input type="radio" name="categorycode" value="0" />供应商     
                      &nbsp; 状态：         
                <input type="radio" name="isShow" value="1" checked="checked"/>显示
                <input type="radio" name="isShow" value="0" />隐藏
            </td>
        </tr>
        <tr>
        	<td class="label">备注：</td>
            <td colspan="3"><input type="text" name="remarks" style="width:600px;" /></td>
        </tr>
        <tr><td colspan="4"><input type="submit" value="新增" />
            <input type="submit" value="保存" />
            <input type="button" onclick="freemarker()" value="打印"/>
            <input type="button" value="关闭"/></td>
        </tr>
        </form>
    </table>
</div>
</body>
</html>
