<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>入库单据管理</title>
<script src="/jereh/js/jquery-1.7.2.min.js"></script>
<script src="/jereh/js/jquery.easyui.min.js"></script>
<link type="text/css" href="/jereh/themes/icon.css" rel="stylesheet" />
<link type="text/css" href="/jereh/themes/default/easyui.css" rel="stylesheet"/>

<script type="text/javascript">
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
		//弹出添加窗口
  	$("#dg").dialog({
  		resizable:true,
  		fit:true,		
		//width:1000,height:500,
		modal:true,
		closed:true			
	});  
	$("#dg").dialog("close");
	
	$("#detailInfo").hide();
	$("#list").datagrid({
		url:'/jereh/StockIn/GetStockInServlet',	
		idField:'code',		
		singleSelect:false,
		success:function(data){//data是GetChannelServlet中取得的json数据	
			if(data.total==0){
				alert("没有数据！");	
			}
		},
		toolbar:'#tools',
		columns:[[
					{field:'id',checkbox:true},
					{field:'code',title:'入库单号',fixed:true
						,formatter:function(val,row,idx){							
							return "<a onclick=\"detail('"+row.code+"')\" href='#' >"+val+"</a>";
						}
					},
					{field:'inDate',title:'入库日期',fixed:true},					
					{field:'supplierName',title:'供应商名',fixed:true},
					//{field:'supplierCode',title:'供应商名',fixed:true},
					{field:'nums',title:'数量',fixed:80},
					{field:'numsPrice',title:'总货值',fixed:true},
					
					{field:'payState',title:'付款情况',fixed:true
						,formatter:function(val,row,idx){							
							return "0.00";
						}
					},
					{field:'getState',title:'收票情况',fixed:true
						,formatter:function(val,row,idx){							
							return "0.00";
						}
					},					
					{field:'isShow',title:'审核状态',fixed:true
						,formatter:function(val,row,idx){							
								return "审核中";						
						}
					},	
					{field:'remarks',title:'操作员',fixed:true},
					{field:'opt',title:'操作',fixed:true
					,formatter:function(val,row,idx){
							var opt="<input type='button' value='删除' onclick=\"delRow('"+row.code+"','"+row.categoryCode+"')\"/>";
							opt+="<input type='button' value='修改'  onclick='updateRow("+idx+")'/>";							
							return opt; 
						}
					}
				]],				
		pagination:true,//分页 
		pageList:[3,5,10],//设置分页尺寸下拉列表中的数据
		pageSize:10,
	});	
});
function detail(incode){
	$("#codeInfo").text(incode);
	$("#detailInfo").show();
	$("#detailList").datagrid({
		url:'/jereh/StockIn/GetStockInDetailServlet',		
		queryParams:{'inCode':incode},			
		//singleSelect:false,
		success:function(data){//data是GetChannelServlet中取得的json数据	
			if(data.total==0){
				alert("没有数据！");	
			}
		},		
		columns:[[	{field:'orderCode',title:'订单编号',fixed:true},
					{field:'pCode',title:'件号',fixed:true},									
					{field:'baseParts',title:'配件名称',fixed:true,
						formatter:function(val,row,idx){
							return val.partsName;
						}
					},
					{field:'baseParts',title:'配件品牌',fixed:true,
						formatter:function(val,row,idx){
							return val.partsBrand;
						}
					},
					{field:'baseParts',title:'配件型号',fixed:true,
						formatter:function(val,row,idx){
							return val.partsModel;
						}
					},									
					{field:'nums',title:'数量',fixed:true},
					{field:'price',title:'单价',fixed:true},
					{field:'payState',title:'金额',fixed:true,
						formatter:function(val,row,idx){
							return row.nums*row.price;
						}
					},
					{field:'wareHouse',title:'所属仓库',fixed:true},					
					{field:'remarks',title:'备注',fixed:true},					
				]],		
		//fit:true
	});		
}

function getCurDate(){
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();
	var h = date.getHours();
	var m = date.getMinutes();
	var s=date.getSeconds();
	return "MTRK"+year+month+day+h+m+s;
}
function showDailog(stitle){
	$("#dg").dialog({title:stitle});
	$("#dg").dialog("open");
}
function closeDailog(){
	$("#dg").dialog("close");
}
function addRow(){
	showDailog("添加入库数据");	
	$("input[name='opt']").val("1");//opt=1表示添加，opt=2表示修改	
	//清空数据
	$("input[name='code']").val(getCurDate()).attr("readonly",false);
	$("input[name='codeName']").val("");
	$("input[name='orderNo']").val("");
	$("input[name='isShow']").val("");
	$("input[name='remarks']").val("");
	$("select[name='categoryCode']").val("");	
}
function updateRow(idx){
	showDailog("修改入库");
	$("input[name='opt']").val("2");
	var row=$("#list").datagrid("getRows")[idx];
	 
	 
	var code=row.code;//字典编号
	var codeName=row.codeName;//字典名称
	var categoryCode=row.categoryCode;//所属类别	
	var orderNo=row.orderNo;//排序编号
	var isShows=row.isShow;//显示状态
	var remarks=row.remarks;//备注	
	$("input[name='code']").val(code).attr("readonly",true);
	$("input[name='codeName']").val(codeName);
	$("input[name='orderNo']").val(orderNo);
	for(var i=0;i<isShows.length;i++){
		if(isShows[i].checked){
			$("input[name='isShow']").attr("checked",true);
		}
		break;
	};	
	$("input[name='remarks']").val(remarks);
	$("select[name='categoryCode']").val(categoryCode);	
}


function delRow(code,categoryCode){
	alert(categoryCode);
	$.messager.confirm('警告','确定删除该记录吗？',function(r){
		if(r){
			$.ajax({url:'/jereh/StockIn/DeleteStockInServlet',
				data:{'code':code},
				type:'post',
				success:function(data){
					if(data==1){
						alert("删除成功！");
						$("#list").datagrid("reload");							
					}
				}
			});	
		}
	});	
};


function showSupplier(){


}

function searchFun(){
	var code=$("input[name='code']").val();
	var startDate=$("input[name='startDate']").val();
	var endDate=$("input[name='endDate']").val();	
	var supplierName=$("select[name='supplierName']").val();
	//searchFrm.submit();
	$("#list").datagrid("reload",{code:code,startDate:startDate,endDate:endDate,supplierName:supplierName});	
}

</script>
<style>
#searchFrm{
	background-color:#F4F4F4;
}
  td{padding:2px;}
  .td1{width:200px;}
  .td2{width:500px;}
</style>

</head>

<body>
    <div id="tools" >
    <form id="searchFrm" action="/jereh/StockIn/GetStockInServlet">  <b>检索条件：</b>	        
        入库单号：<input type="text"  name="code"/>
        开始日期:<input type="text" class="easyui-datebox"  name="startDate"/>
        结束日期：<input type="text" class="easyui-datebox"   name="endDate"/>
	供应商名：<input type="text"  name="supplierName"/>
        <input type="button" onclick="searchFun()" value="搜索" />
        <input type="reset" value="重置"/>
    </form>
    	<div id="tb">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun()">查询</a>|
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addRow()">增加</a>|
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="delBatchRow()">批量删除</a>|
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" onclick="exportExcl()">导出EXCEL</a>
		</div>       
    </div>
    
    
	<div id="list"></div>
    
    <div id="detailInfo">
	   	 单据标号为：<strong id="codeInfo" style="font"></strong>&nbsp;的明细如下所列！
	    <div id="detailList"></div>	
    </div>


	<div id="dg" style="padding:20px">
		<form name="frm" action="" method="post" >
		<input type="hidden" name="id" /> 
		<input type="hidden" name="opt" />
			<table   border="1"  bordercolor="#EBEBEB"   cellpadding="0" cellspacing="0">
			<tr>
			    <td class="td1"><span style="color:red">*</span>入库单号：</td><td class="td2"><input name="code" type="text"/></td>
			    <td class="td1"><span style="color:red">*</span>入库日期：</td><td class="td2"><input name="addDate" type="text" class="easyui-datebox"/></td>
			</tr>
			<tr>
			 <td class="td1"><span style="color:red">*</span>供应商名：</td><td class="td2"><input name="supplierName" readonly type="text" onclick="showSupplier()"/></td>
			 <td class="td1"><span style="color:red">*</span>联系人员：</td><td class="td2"><input name="contacter" type="text" /></td>			 
			</tr>
			<tr>
				<td class="td1">电话：</td><td class="td2"><input name="telphone" type="text" /></td>
			    <td class="td1">传真：</td><td class="td2"><input name="fax" type="text"/></td>
			</tr>
			<tr>
				<td class="td1">入库类型：</td><td class="td2"><select name="inType"></select><input name="isRoad" type="radio" value="0"/>正常入库<input name="isRoad" type="radio" value="1"/>冲抵入库</td>
			    <td class="td1"><span style="color:red">*</span>是否开票</td><td class="td2"><input name="isInvoice" type="radio" value="1"/>是<input name="fax" type="radio" value="0"/>否</td>
			</tr>
			<tr>
				<td class="td1">备注：</td><td colspan="3" class="td2"><input name="remarks" type="text"/></td>
			    
			</tr>
			</table><br/>
			<input type="button" value="新增" onclick="" />
			<input type="button" value="采购订单" onclick="" />
			<input type="button" value="添加配件" onclick="" />
			<input type="button" value="保存" onclick="" />
			<input type="button" value="审核" onclick="" />
			<input type="button" value="撤销" onclick="" />
			<input type="button" value="生成采购付款" onclick="" />
			<input type="button" value="生成采购收票" onclick="" />
			<input type="button" value="打印" onclick="exportWord()" />
			<input type="button" name="close" value="关闭" onclick="closeDailog();" />
		</form>
	</div>	
	
	
	
	<!-- 客户选择 -->    
     <div id="customer">
	     <div id="cusListTb">
		       <form action="" method="post" >
		          <b>检索条件：</b>
		          	客户代码:<input type="text"/> 
		          	客户名称:<input type="text"/>
		          	<input type="button" value="搜索" onclick=""/>
					<input type="reset" value="重置" />
		       </form>
	       </div>
		 <div id="cusList"></div>
     </div>
</body>
</html>
