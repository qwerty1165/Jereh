<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<meta http-equiv="Content-Type" content="textml; charset=utf-8" />
<title>无标题文档</title>
<script src="/jereh/js/jquery-1.7.2.min.js"></script>
<script src="/jereh/js/jquery.easyui.min.js"></script>
<link type="text/css" href="/jereh/themes/default/easyui.css" rel="stylesheet" />
<link type="text/css" href="/jereh/themes/icon.css" rel="stylesheet" />
<style>
.label{ width:100px; text-align:right;}
.in{ width:300px;}
td{ border:solid #95ADCC; border-width:1px;height: 26px;}
</style>
<script>
$(function(){
		//$("#addParts").dialog("close");
	//格式化日期
	$("input.easyui-datebox").datebox({
		formatter:function(date){
			var y = date.getFullYear();
			var m = date.getMonth() + 1;
			var d = date.getDate();
			var h=date.getHours();
			var min=date.getMinutes();
			var second=date.getSeconds();
			return y+m+d+hour+minute+second;
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
	$("#partsList").datagrid({    //往哪里添加table
		title:'配件信息管理',
		idField:'partsCode',  //必须选择一个id
		url:'/jereh/baseParts/SearchBasePartsServlet',
		toolbar:"#partsTool", //绑定顶部工具栏的DataGrid面板。
		fitColumns:true,  //真正的自动展开/收缩列的大小
		singleSelect:false,//是否只能单选
		columns:[[	
			{checkbox:true},//添加复选框checkbox:true，表头会多生成一个全选的复选框
			{field:'partsCode',title:'配件编码',fixed:true},
			{field:'partsNo',title:'配件件号',wfixed:true},
			{field:'partsCategory',title:'配件类别',fixed:true},
			{field:'partsName',title:'配件名称',fixed:true},
			{field:'partsBrand',title:'配件品牌',fixed:true},
			{field:'partsModel',title:'配件型号',fixed:true},
			{field:'partsModelOld',title:'配件旧型号',fixed:true},			
			{field:'salePrice',title:'配件销售价格',fixed:true},
			{field:'isShow',title:'显示状态',fixed:true,
				formatter:function(val,row,idx){
				if(val=="1") return "显示";
				else return "隐藏";				
				}
			},
			{field:'addUser',title:'操作员',fixed:true},
			{field:'remarks',title:'备注',fixed:true},
			{field:'opt',title:'操作',fixed:true,formatter:function(val,row,idx){
				var content = "<input type='button' value='删除' onclick='delParts(\"" + row.partsCode + "\")' />";
				content += "<input type='button' value='修改' onclick='modifyParts(\"" + idx + "\")' />";
				content += "<input type='button' value='打印并下载' onclick='printParts(" + idx + ")' />";
				return content;	
			}}
		]],
		fit:true,
		pagination:true,   //翻页
		pageList:[3,5,10],  //可选一页显示多少条
		pageSize:5   //默认一页显示多少条
	});
	$("#partsList").datagrid('getPager').pagination({
    	displayMsg:'当前显示从第 {from} 条到第 {to} 条，共 {total} 条记录'
	});
});

//获取当前时间--生成配件编号
function getCurDate(){
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();
	var hour=date.getHours();
	var minute=date.getMinutes();
	var second=date.getSeconds();
	return "MTPJ"+year+month+day+hour+minute+second;  
	}

//批量删除
function delBatchParts(){
	var rows = $("#partsList").datagrid("getSelections");//得到被select的一个数组
	if(rows.length == 0)
		$.messager.alert("信息提示","请选择至少一条记录");
	else{
		$.messager.confirm("删除确认","确实要删除记录吗？",function(r){
			if(r){                                 //如果点击了确定则执行			    
				for(var i = 0; i < rows.length; i++){
					var partsCode=rows[i].partsCode;
				   $.ajax({
				     url:'/jereh/baseParts/DeleteBasePartsServlet',
				     data:{'partsCode':partsCode},
				     success:function(data){
				     	$("#partsList").datagrid("reload");	
				     }		    
		       	 	});			
			}
			}
		
		});
	}
}

//单个删除
function delParts(partsCode){
	$.messager.confirm("删除提醒","确实执行删除操作吗？",function(r){
		if(r){
			$.ajax({
			     url:'/jereh/baseParts/DeleteBasePartsServlet?partsCode='+partsCode,
			     success:function(data){			     	
			       $("#partsList").datagrid("reload");
			     }
			 });
		
			//window.location.href="/jereh/baseParts/DeleteBasePartsServlet?partsCode="+partsCode;
		}
	});
}
//批量删除
function delBatchRow(){
        //返回选中多行  
	var selRow = $("#list").datagrid("getSelections");  
        //判断是否选中行  
	if (selRow.length==0) {  
        $.messager.alert("提示", "请选择要删除的行！", "info");  
        return;  
    }else{                           
        $.messager.confirm('提示', '是否删除选中数据?', function (r) {   
            if (r) {  
                //批量获取选中行的评估模板ID  
                var count=0;
		        for (i = 0; i < selRow.length;i++) {  
				   var code=selRow[i].code;
				   var categoryCode=selRow[i].categoryCode;           
		           $.ajax({  
		                type:'post',  
		                async: false,  
		                url:'/jereh/baseParts/DeleteBasePartsServlet?partsCode='+partsCode,		               	
		             	success:function(data){
							if(data==1){								
								count++;							
							}
						}
		            });               
		        }
			    $("#list").datagrid("reload");	 
		        if(count==selRow.length){
		        	alert("批量删除成功！");
		        }
            }   
       });    
   	}  
}
//添加的
function showDialog(stitle){
	$("#addParts").dialog({
		title:stitle,
		width:800,
		heigth:300,
		modal:true,
		closed:true	
		});
		$("#addParts").dialog("open");	
	}
//添加客户
function addParts(){
	showDialog('添加配件信息');
	$("input[name='opt']").val("1");
	$("input[name='code']").val(getCurDate());
}
//修改客户
function modifyParts(idx){
	showDialog('修改配件信息');
	$("input[name='opt']").val("2");
	var row=$("#partsList").datagrid("getRows")[idx];
	
	var code=row.partsCode;  //编号
	var type=row.partsCategory;//类型
	var name=row.partsName;//名称
	var brand=row.partsBrand;//公司
	var model=row.partsModel;//型号
	var modelOld=row.partsModelOld;//旧型号
	var size=row.partsSize;//尺寸
	var weight=row.partsWeight;//重量
    var price=row.salePrice;
	var isShow=row.isShow;//  显示状态
	var remarks=row.remarks;//备注
	var no=row.partsNo;
	
	$("input[name='code']").val(code).attr("readonly",true);
	$("input[name='type']").val(type);
	$("input[name='name']").val(name);
	$("input[name='brand']").val(brand);
	$("input[name='model']").val(model);
	$("input[name='modelOld']").val(modelOld);
	$("input[name='size']").val(size);
	$("input[name='weight']").val(weight);
	$("input[name='price']").val(price);
	$("input[name='isShow']").val(isShow);
	$("input[name='remarks']").val(remarks);
	$("input[name='no']").val(no);
}
function searchFun(){
	var searchNo=$("input[name='searchNo']").val();
	var name=$("input[name='name']").val();
	var typeOpt=$("select[name='typeOpt']").val();
	$("#partsList").datagrid("reload",{searchNo:searchNo,name:name,'typeOpt':typeOpt});
}
//打印详细信息--导出word
function printParts(idx){
	//window.location.href="/jereh/baseParts/OutWordServlet";
	
	var row=$("#partsList").datagrid("getRows")[idx];
	var partsCode=row.partsCode;  //编号
	var partsCategory=row.partsCategory;//类型
	var partsName=row.partsName;//名称
	var partsBrand=row.partsBrand;//公司
	var partsModel=row.partsModel; //型号
	var partsModelOld=row.partsModelOld; //旧型号
	//var partsSize=row.partsSize; //尺寸
	var partsWeight=row.partsWeight; //重量
	//var img=row.partsImg;
    var salePrice=row.salePrice;
	var isShow=row.isShow; //  显示状态
	var remarks=row.remarks;//备注
	
	
	$.ajax({
	url:'/jereh/baseParts/OutWordBasePartsServlet',
	data:{'partsCode':partsCode,'partsCategory':partsCategory,'partsName':partsName,'partsBrand':partsBrand,
	'partsModel':partsModel,'partsModelOld':partsModelOld,'partsweight':partsWeight,
	'salePrice':salePrice,'partsWeight':partsWeight,'isShow':isShow,'remarks':remarks},
	 success:function(data){	
	 		  var fileName=data.fileName;   	
			  window.location.href="/jereh/baseParts/DownloadBasePartsServlet?fileName="+fileName;    
			     }
	});
		
	
}

//导出excel
function outExcel(){
	window.location.href="/jereh/baseParts/OutExcleBasePartsServlet";
}
//价格调整
function adjustPrice(){

}

//点击"新增"
function subAdd(){
		add.submit();
		$("#addParts").dialog("close");	
}
//点击"保存"
function subSave(){
		add.submit();
		$("#addParts").dialog("close");	
}
</script>
</head>

<body>
<div id="partsTool">
	<form action="/jereh/baseParts/SearchBasePartsServlet" method="post">
        <b>检索条件：</b>
        件号：<input type="text" name="searchNo" width="10px;"/>
        名称：<input type="text" name="name"/>
       类型：<select name="typeOpt">
 	  <option>--请选择--</option>        		
	        	<option value="11">轴承</option>	        	
	        	<option value="12">皮带</option>
     </select>   
        <input type="button" value="搜索" onclick="searchFun()" style="position:relative; top:2px;"/>
        <input type="reset" value="重置" style="position:relative; top:2px;" />
    </form> 
	<button class="easyui-linkbutton" iconCls="icon-search" onclick="partsSearch()">查询</button>
	<button class="easyui-linkbutton" iconCls="icon-add" onclick="addParts()">增加</button>
    <button class="easyui-linkbutton" iconCls="icon-cancel" onclick="delBatchParts()">批量删除</button>
    <button class="easyui-linkbutton" iconCls="icon-ok" onclick="outExcel()">导出excel</button>
    <button class="easyui-linkbutton" iconCls="icon-sum" onclick="adjustPrice()">价格调整</button>
</div>


<div id="partsList">
	<!--table-->
</div>


<div id="addParts" style="padding:10px;">

	<table border="1" cellspacing="0" style="border-collapse:collapse;">	
	<form id="add" action="/jereh/baseParts/addBasePartsServlet" method="post">
	<input type="hidden" name="id" /> 
 	<input type="hidden" name="opt"/>
    	<tr>
        	<td class="label">配件编号：</td><td class="in"><input type="text" name="code" /></td>
        	<td class="label">配件名称：</td><td class="in"><input type="text" name="name" /></td>
        </tr>
        <tr>
        	<td class="label">配件类型：</td><td> <select name="type">
                    <option value="1">轴承</option>
                    <option value="2">轮机</option>
                </select></td>
    			
    		<td class="label">配件品牌：</td><td> <select name="brand">
                    <option value="杰瑞">杰瑞</option>
                    <option value="徐工">徐工</option>
                </select></td>
        </tr>
         <tr>
        	<td class="label">配件件号：</td><td><input type="text" name="no" /></td>
            <td class="label">通用件号：</td><td><input type="text" name="GeneralPartsNo" /></td>
        </tr>
        
        <tr>
        	<td class="label">型号：</td><td><input type="text" name="model" /></td>
            <td class="label">旧型号：</td><td><input type="text" name="modelOld" /></td>
        </tr>
        <tr>
        	<td class="label">配件尺寸：</td><td><input type="text" name="size" /></td>
            <td class="label">配件重量：</td><td><input type="text" name="weight" /></td>
        </tr>
      	<tr>
        	<td class="label">配件图片：</td><td><input type="button" name="img" value="选择文件" /></td>
           
          	<td class="label">配件单位：</td><td> <select name="unit">
                    <option value="1">套</option>
                    <option value="2">车</option>
                </select></td>       
        <tr>
        	<td class="label">销售价格：</td><td><input type="text" name="price" /></td>

            <td class="label">状态：</td>
            <td>
                <input type="radio" name="isShow" value="1" />显示
                <input type="radio" name="isShow" value="0" />隐藏
            </td>
        </tr>
        <tr>
        	<td class="label">备注：</td>
            <td colspan="3"><input type="text" name="remarks" style="width:600px;" /></td>
        </tr>
         <tr>
       <td colspan="5"><input type="button" value="新增" onclick="subAdd()"/>
       <input type="button" value="保存" onclick="subSave()"/>
       <input type="button" value="同步" />
       <input type="button" value="打印" onclick=""/>
      <input type="reset" name="关闭"/></td>
    </tr>
        </form>
    </table>
</div>
</body>
</html>
