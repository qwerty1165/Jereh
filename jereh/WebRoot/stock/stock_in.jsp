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
function getCurDate(){
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();
	var h = date.getHours();
	var m = date.getMinutes();
	var s=date.getSeconds();
	return "MTCK"+year+month+day+h+m+s;
}
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
	//弹出添加/修改窗口
  	$("#dg").dialog({
  		resizable:true,
  		fit:true,		
		modal:true,
		closed:true			
	});  
	$("#dg").dialog("close");
	//客户列表
	 $("#customer").dialog({		
		width:800,height:400,
		modal:true,
		closed:true			
	});	
	$("#customer").dialog("close");
	//配件列表
	$("#parts").dialog({		
		width:800,height:400,
		modal:true,
		closed:true			
	});	
	$("#parts").dialog("close");
	
	//详细信息
	$("#detailInfo").hide();
	
	//表头列表
	$("#list").datagrid({
		url:'/jereh/StockIn/GetStockInServlet',	
		idField:'code',		
		singleSelect:false,
		toolbar:'#tools',
		columns:[[
			{field:'id',checkbox:true},
			{field:'code',title:'入库单号',fixed:true},
			{field:'isShow',title:'是否显示',hidden:true},//hidden:true
			{field:'isInvoice',title:'是否开票',hidden:true},
			{field:'inType',title:'入库类型',hidden:true},
			{field:'supplierCode',title:'供应编号',fixed:true},
			{field:'contacter',title:'联系人',hidden:true},
			{field:'telphone',title:'电话',hidden:true},
			{field:'fax',title:'传真',hidden:true},					
			{field:'inDate',title:'入库日期',fixed:true},					
			{field:'supplierName',title:'供应商名',fixed:true},
			{field:'nums',title:'数量',fixed:80},
			{field:'numsPrice',title:'总货值',fixed:true},
			{field:'payState',title:'付款情况',fixed:true,
				formatter:function(val,row,idx){							
					return "0.00";
				}
			},
			{field:'getState',title:'收票情况',fixed:true,
				formatter:function(val,row,idx){							
					return "0.00";
				}
			},					
			{field:'scstate',title:'审核状态',fixed:true,
				formatter:function(val,row,idx){							
					return "审核中";						
				}
			},	
			{field:'addUserName',title:'操作员',fixed:true},
			{field:'opt',title:'操作',fixed:true,
				formatter:function(val,row,idx){							
				var opt="<input type='button' value='删除' onclick=\"delRow('"+row.code+"')\"/>";
				opt+="<input type='button' value='修改'  onclick='updateRow("+idx+")'/>";							
					return opt; 
				}
			}
		]],				
		pagination:true,//分页 
		pageList:[3,5,10],//设置分页尺寸下拉列表中的数据
		pageSize:10
	});	
	//显示详细列表
	$("#detailList").datagrid({
		columns:[[	
			{field:'orderCode',title:'订单编号',fixed:true},
			{field:'pCode',title:'件号',fixed:true},									
			{field:'partsName',title:'配件名称',fixed:true,
				formatter:function(val,row,idx){
					return row.baseParts.partsName;
				}
			},
			{field:'partsBrand',title:'配件品牌',fixed:true,
				formatter:function(val,row,idx){
					return row.baseParts.partsBrand;
				}
			},
			{field:'partsModel',title:'配件型号',fixed:true,
				formatter:function(val,row,idx){
					return row.baseParts.partsModel;
				}
			},									
			{field:'nums',title:'数量',fixed:true},
			{field:'price',title:'单价',fixed:true},
			{field:'payState',title:'金额',fixed:true,
				formatter:function(val,row,idx){
					return row.nums*row.price;
				}
			},
			{field:'wareHouse',title:'所属仓库',fixed:true,
				formatter:function(val,row,idx){
					return "主仓库";
				}
			},					
			{field:'remarks',title:'备注',fixed:true}				
		]],			
	});	
	//添加修改的供应商列表
	$("#cusList").datagrid({  		
		toolbar:'#cusListTb',
		idField:'code',
		columns:[[
			{field:'code',title:'供应商代码',fixed:true},
			{field:'csName',title:'供应商名称',fixed:true},
			{field:'contacter',title:'联系人员',fixed:true},
			{field:'telephone',title:'电话',fixed:true},
			{field:'fax',title:'传真',fixed:true},
			{field:'address',title:'地址',fixed:true}
		]]		
	}); 	
	//添加修改的详细列表
	$("#updateDetailList").datagrid({			
		columns:[[	
			{field:'code',title:'明细主键',hidden:true},
			{field:'orderCode',title:'订单编号',fixed:true},
			{field:'pCode',title:'件号',fixed:true},									
			{field:'partsName',title:'配件名称',fixed:true,
				formatter:function(val,row,idx){
					return row.baseParts.partsName;
				}
			},
			{field:'partsBrand',title:'配件品牌',fixed:true,
				formatter:function(val,row,idx){
					return row.baseParts.partsBrand;
				}
			},
			{field:'partsModel',title:'配件型号',fixed:true,
				formatter:function(val,row,idx){
					return row.baseParts.partsModel;
				}
			},									
			{field:'nums',title:'数量',fixed:true},
			{field:'price',title:'单价',fixed:true},
			{field:'payState',title:'金额',fixed:true,
				formatter:function(val,row,idx){
					return row.nums*row.price;
				}
			},
			{field:'wareHouse',title:'所属仓库',fixed:true,
				formatter:function(val,row,idx){
					val="主仓库";
					return val;
				}
			},					
			{field:'remarks',title:'备注',fixed:true},
			{field:'opt',title:'操作',fixed:true,
				formatter:function(val,row,idx){
					var opt="<input type='button' value='删除' onclick=\"delDetailRow('"+row.code+"')\"/>";
						/* opt+="<input type='button' value='修改'  onclick='updateDetail("+idx+")'/>";	 */						
					return opt; 
				}
			},
			{field:'salePrice',title:'上次价格',fixed:true,
				formatter:function(val,row,idx){
					return row.baseParts.salePrice;
				}
			}					
		]],			
	});		
		
});

//详细列表
function detail(incode){
	$("#codeInfo").text(incode);
	$("#detailInfo").show();
	$("#detailList").datagrid({
		url:'/jereh/StockIn/GetStockInDetailServlet',		
		queryParams:{'inCode':incode}
	});		
}
/**表头查找方法*/
//表头查找
function searchFun(){
	var code=$("input[name='code']").val();
	var startDate=$("input[name='startDate']").val();
	var endDate=$("input[name='endDate']").val();	
	var supplierName=$("select[name='supplierName']").val();
	$("#list").datagrid("reload",{code:code,startDate:startDate,endDate:endDate,supplierName:supplierName});	
}

//表头删除
function delRow(code){	
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
//表头批量删除
function delBatchRow(){
	var selRow = $("#list").datagrid("getSelections");  
	if (selRow.length==0) {  
        $.messager.alert("提示", "请选择要删除的行！", "info");  
        return;  
    }else{                           
        $.messager.confirm('提示', '是否删除选中数据?', function (r) {   
            if (r) { 
		        for (i = 0; i < selRow.length;i++) {  
				   var code=selRow[i].code;				          
		          $.ajax({url:'/jereh/StockIn/DeleteStockInServlet',
						data:{'code':code},
		                type:'post',  
		                async: false		             	
		            });               
		        }
			    $("#updateDetailList").datagrid("reload");	       
            }   
       });    
   	}  
}
//详细删除
function delDetailRow(code){
	$.messager.confirm('警告','确定删除该记录吗？',function(r){
		if(r){
			$.ajax({url:'/jereh/StockIn/DeleteStockInDetailServlet',
				data:{'code':code},
				type:'post',
				success:function(data){
					if(data==1){
						alert("删除成功！");
						$("#updateDetailList").datagrid("reload");							
					}
				}
			});	
		}
	});	
}
/**控制添加修改窗口*/
function showDailog(stitle){
	$("#dg").dialog({title:stitle});
	$("#dg").dialog("open");
}
function closeDailog(){
	$("#dg").dialog("close");
}
	/**添加数据*/
function addRow(){
	showDailog("添加入库数据");	
	$("#update").hide();	
	$("input[name='add']").attr("disabled",false);
	$("input[name='save']").attr("disabled",true);	
	$("input[name='opt']").val("1");//opt=1表示添加，opt=2表示修改	
	$("input[name='code']").val(getCurDate()).attr("readonly",false);
	$("#inDate").datebox("setValue",new Date());		
	$("input[name='supplierName']").val("");	
	$("input[name='contacter']").val("");
	$("input[name='telphone']").val("");
	$("input[name='fax']").val("");
	$("input[name='numsPrice']").val("");
		$("input[name='isShow']:first").prop("checked",true);
		$("input[name='isRoad']:first").prop("checked",true);
		$("input[name='isInvoice']:first").prop("checked",true);	
	$("input[name='remarks']").val("");
}
	/**更新数据*/
function updateRow(idx){
	showDailog("修改入库");
	$("#update").show();
	$("input[name='add']").attr("disabled",true);
	$("input[name='save']").attr("disabled",false);	
	$("input[name='opt']").val("2");
	var row=$("#list").datagrid("getRows")[idx];	 
	var code=row.code;
	var inDate=row.inDate;
	var supplierCode=row.supplierCode;
	var supplierName=row.supplierName;
	var contacter=row.contacter;
	var telphone=row.telphone;
	var fax=row.fax;
	var numsPrice=row.numsPrice;
	var isRoad=row.isRoad;
	var isShow=row.isShow;
	var isInvoice=row.isInvioce;
	var remarks=row.remarks;	
	$("input[name='supplierCode']").val(supplierCode);
	$("input[name='code']").val(code).attr("readonly",true);
	$("#inDate").datebox("setValue",inDate);		
	$("input[name='supplierName']").val(supplierName);	
	$("input[name='contacter']").val(contacter);
	$("input[name='telphone']").val(telphone);
	$("input[name='fax']").val(fax);
	$("input[name='numsPrice']").val(numsPrice);
	if(isRoad==0){
		$("input[name='isRoad']:first").prop("checked",true);
	}else{
		$("input[name='isRoad']:last").prop("checked",true);
	};
	if(isShow==1){
		$("input[name='isShow']:first").prop("checked",true);
	}else{
		$("input[name='isShow']:last").prop("checked",true);
	};
	if(isInvoice==1){
		$("input[name='isInvoice']:first").prop("checked",true);
	}else{
		$("input[name='isInvoice']:last").prop("checked",true);
	};	
	$("input[name='remarks']").val(remarks);	
	
	/**显示详细信息*/
	$("#updateDetailList").datagrid({
		url:'/jereh/StockIn/GetStockInDetailServlet',		
		queryParams:{'inCode':code}		
	});		
		
}

/**客户列表*/
//客户信息列表
function showSupplier(){
	$("#customer").dialog({title:"请选择供应商"});
	$("#customer").dialog("open");
	$("#cusList").datagrid({  
		url:'/jereh/BaseCustomerSupplier/GetBaseCustomerSupplierServlet',
		onClickRow:function(idx, row){
			var row=$("#cusList").datagrid("getRows")[idx];
			var code=row.code;
			var csName=row.csName;
			var contacter=row.contacter;
			var fax = row.fax;
			var telphone = row.telephone;
			$("input[name='supplierCode']").val(code);
			$("input[name='supplierName']").val(csName);
			$("input[name='contacter']").val(contacter);
			$("input[name='fax']").val(fax);
			$("input[name='telphone']").val(telphone);	
			$("#customer").dialog("close");	  
		}
	}); 
}
//客户搜索！
function searchSupplierFun(){
  var code=$("input[name='searchcode']").val();
  var csName=$("input[name='searchcsName']").val();
  var addDate=$("input[name='searchaddDate']").val();
  $("#cusList").datagrid("reload",{'code':code,'csName':csName,'addDate':addDate});
}



/**配件信息表*/
//添加配件信息表
function addParts(){
   $("#parts").dialog({
   		title:"选择配件",
   		width:1000,
   		height:600});
   $("#parts").dialog("open");
   $("#partsList").datagrid({    //往哪里添加table		
		idField:'partsCode',  //必须选择一个id
		url:'/jereh/BaseParts/GetBasePartsByComServlet',
		toolbar:"#parListTb", //绑定顶部工具栏的DataGrid面板。
		fitColumns:true,  //真正的自动展开/收缩列的大小
		singleSelect:false,//是否只能单选
		columns:[[	
			{checkbox:true},//添加复选框checkbox:true，表头会多生成一个全选的复选框
			{field:'partsCode',title:'配件编码',hidden:true},
			{field:'partsNo',title:'配件件号',wfixed:true},			
			{field:'partsName',title:'配件名称',fixed:true},
			{field:'partsBrand',title:'配件品牌',fixed:true},
			{field:'partsModel',title:'配件型号',fixed:true},
			{field:'nums',title:'数量',fixed:true},			
			{field:'salePrice',title:'单价',fixed:true},		
			{field:'remarks',title:'备注',fixed:true},
		]],
		fit:true,
		pagination:true,   //翻页
		pageList:[3,5,10],  //可选一页显示多少条
		pageSize:5   //默认一页显示多少条
	});
}
//确定添加
function confirmAdd(){
	var selRow = $("#partsList").datagrid("getSelections");  
        //判断是否选中行  
	if (selRow.length==0) {  
        $.messager.alert("提示", "请选择要添加的配件！", "info");  
        return;  
    }else{                           
        $.messager.confirm('提示', '是否添加选中数据?', function (r) {   
            if (r) {              
		     	$("update").show;
		        for (i = 0; i < selRow.length;i++) {		        	
				  	$("#updateDetailList").datagrid("appendRow",selRow[i]);	              		
		        }	
            }   
       });    
   	}  
}
//查找配件信息
function searchBasePartsFun(){
	var searchNo=$("input[name='searchNo']").val();
	var name=$("input[name='name']").val();	
	$("#partsList").datagrid("reload",{searchNo:searchNo,name:name});
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
<!-- 	表头工具 -->
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
<!-- 	表头信息 --> 
	<div id="list"></div>   
<!-- 	详细列表  -->
    <div id="detailInfo">
	   	 单据标号为：<strong id="codeInfo" style="font"></strong>&nbsp;的明细如下所列！
	    <div id="detailList"></div>	
    </div>
	
<!-- 	客户选择 -->    
     <div id="customer">
	     <div id="cusListTb">
		       <form action="" method="post" >
		          <b>检索条件：</b>
		          	供应商代码:<input type="text" name='searchcode'/> 
		          	供应商名称:<input type="text" name='searchcsName'/>
		          	<input type="button" value="搜索" onclick="searchSupplierFun()"/>
					<input type="reset" value="重置" />
		       </form>
	       </div>
		 <div id="cusList"></div>
     </div>
<!-- 	配件选择 -->
     <div id="parts">
	     <div id="parListTb">
	       <form action="/jereh/BaseParts/GetBasePartsByComServlet" method="post" >
	          <b>检索条件：</b>
	          	 件号：<input type="text" name="searchNo" width="10px;"/>
       			 名称：<input type="text" name="name"/>
	          	 仓库：<select class="easyui-combobox">
	          	   <option>--选择仓库--</option>
	          	   <option>主仓库</option>
	          	   </select>          	   
	          	<input type="button" value="搜索" onclick="searchBasePartsFun()"/>
				<input type="reset" value="重置" />
				<input type="button" value="确认添加" onclick="confirmAdd()"/>
	       </form>
	       </div>
	 	<div id="partsList"></div>
     </div>		 
<!--	 更新添加页面 -->   
     <div id="dg" style="padding:20px">
		<form name="frm" action="/jereh/StockIn/UpdateStockInServlet" method="post" >
			<input type="hidden" name="supplierCode" /> 
			<input type="hidden" name="opt" />
			<table   border="1"  border="1" bordercolor="#CFDAE8" cellpadding="0"
				cellspacing="0">
			<tr>
			    <td class="td1"><span style="color:red">*</span>入库单号：</td>
			    <td class="td2"><input name="code" type="text"/></td>
			    <td class="td1"><span style="color:red">*</span>入库日期：</td>
			    <td class="td2"><input id="inDate" name="inDate" type="text" class="easyui-datebox"/></td>
			</tr>
			<tr>
			 <td class="td1"><span style="color:red">*</span>供应商名：</td>
			 <td class="td2"><input name="supplierName" readonly="readonly" type="text" onclick="showSupplier()"/></td>
			 <td class="td1"><span style="color:red">*</span>联系人员：</td>
			 <td class="td2"><input name="contacter" type="text" readonly="readonly" /></td>			 
			</tr>
			<tr>
				<td class="td1">电话：</td>
				<td class="td2"><input name="telphone" type="text" readonly="readonly" /></td>
			    <td class="td1">传真：</td>
			    <td class="td2"><input name="fax" type="text" readonly="readonly"/></td>
			</tr>
			<tr>
				<td class="td1">入库类型：</td>
				<td class="td2"><!-- <select name="inType"></select> -->
				<input name="isRoad" type="radio" value="0"/>正常入库<input name="isRoad" type="radio" value="1"/>冲抵入库</td>
			    <td class="td1"><span style="color:red">*</span>是否开票：</td>
			    <td class="td2"><input name="isInvoice" type="radio" value="1"/>是<input name="isInvoice" type="radio" value="0"/>否</td>
			</tr>
			<tr>
				<td class="td1">是否显示：</td>
				<td class="td2"><input name="isShow" type="radio" value="1"/>是 <input name="isShow" type="radio" value="0"/>否</td>
				
				<td class="td1">备注：</td>
				<td class="td2"><input name="remarks" type="text"/></td>			    
			</tr>
			</table><br/>
			<input name="add" type="submit" value="新增" onclick="" />
			<input name="order" type="button" value="采购订单" onclick="" />
			<input name="part" type="button" value="添加配件" onclick="addParts()" />
			<input name="save" type="submit" value="保存" onclick="" />
			<input name="" type="button" value="审核" onclick="" />
			<input name="" type="reset" value="撤销" onclick="" />
			<input name="" type="button" value="生成采购付款" onclick="" />
			<input name="" type="button" value="生成采购收票" onclick="" />
			<input name="word" type="button" value="打印" onclick="exportWord()" />
			<input name="" type="button" name="close" value="关闭" onclick="closeDailog();" /><br/>
			<br/>
			<div id="update"><div id="updateDetailList"></div></div>
			<div id="addDetailList"></div>
		</form>	
	</div>	
</body>
</html>
