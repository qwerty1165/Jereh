<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>字典内容管理</title>
<script src="/jereh/js/jquery-1.7.2.min.js"></script>
<script src="/jereh/js/jquery.easyui.min.js"></script>
<link type="text/css" href="/jereh/themes/icon.css" rel="stylesheet" />
<link type="text/css" href="/jereh/themes/default/easyui.css" rel="stylesheet"/>


<script type="text/javascript">
$(function(){
		//弹出添加窗口
  	$("#dg").dialog({		
		width:600,height:200,
		modal:true,
		closed:true	
		
	});  
	$("#dg").dialog("close");
	
	$.ajax({url:'/jereh/BaseContent/GetCategoryJsonServlet',
		success:function(data){//data是GetChannelServlet中取得的json数据	
			if(data.total==0){
				alert("没有数据！");	
			}else{			
				for(var i=0;i<data.length;i++){
					//在select下拉列表中增加选项
					$("<option>").appendTo("select[name='categoryCode']")
					.html(data[i].categoryCode).val(data[i].categoryCode);//设置显示内容以及属性值
				}
			}
		},
		dataType:'json'//数据类型【可略】
	});
	
	$("#list").datagrid({
		url:'/jereh/BaseContent/GetBaseContentServlet',	
		//idField:'id',		
		singleSelect:false,
		success:function(data){//data是GetChannelServlet中取得的json数据	
			if(data.total==0){
				alert("没有数据！");	
			}
		},
		toolbar:'#tools',
		columns:[[
					{field:'id',checkbox:true
						,formatter:function(val,row,idx){													
							return row.code+row.codeName;
						}},
					{field:'categoryCode',title:'所属类别',width:80},
					{field:'code',title:'字典编号',width:80},					
					{field:'codeName',title:'字典内容',width:200},
					{field:'compName',title:'公司名称',fixed:80},
					{field:'orderNo',title:'排序编号',width:80},
					{field:'remarks',title:'备注',width:80},
					{field:'addUser',title:'操作员',width:80},
					{field:'isShow',title:'显示状态',width:80
						,formatter:function(val,row,idx){
							if(val=="1"){
								return "显示";
							}else if(val=="0"){
								return "隐藏";
							}
						}
					},		
					{field:'opt',title:'操作',fixed:true
					,formatter:function(val,row,idx){
							var opt="<input type='button' value='删除' onclick=\"delRow('"+row.code+"','"+row.categoryCode+"')\"/>";
							opt+="<input type='button' value='修改'  onclick='updateRow("+idx+")'/>";							
							return opt; 
						}
					}
				]],		
		fit:true,			
		pagination:true,//分页 
		pageList:[3,5,10],//设置分页尺寸下拉列表中的数据
		pageSize:10,
	});	
	
	
});
function showDailog(stitle){
	$("#dg").dialog({title:stitle});
	$("#dg").dialog("open");
}
function closeDailog(){
	$("#dg").dialog("close");
}
function addRow(){
	showDailog("添加字典");	
	$("input[name='opt']").val("1");//opt=1表示添加，opt=2表示修改	
	//清空数据
	$("input[name='code']").val("").attr("readonly",false);
	$("input[name='codeName']").val("");
	$("input[name='orderNo']").val("");
	$("input[name='isShow']").val("");
	$("input[name='remarks']").val("");
	$("select[name='categoryCode']").val("");	
}
function updateRow(idx){
	showDailog("修改字典");
	$("input[name='opt']").val("2");
	var row=$("#list").datagrid("getRows")[idx];
	
	var code=row.code;//字典编号
	var codeName=row.codeName;//字典名称
	var categoryCode=row.categoryCode;//所属类别	
	var orderNo=row.orderNo;//排序编号
	var isShow=row.isShow;//显示状态
	var remarks=row.remarks;//备注
	
	$("input[name='code']").val(code).attr("readonly",true);
	$("input[name='codeName']").val(codeName);
	$("input[name='orderNo']").val(orderNo);
	if(isShow==1){
		$("input[name='isShow']:first").prop("checked",true);
	}else{
		$("input[name='isShow']:last").prop("checked",true);
	};
	
/* 	for(var i=0;i<isShows.length;i++){
		if(isShows[i].checked){
			$("input[name='isShow']").attr("checked",true);
		}
		break;
	}; */
	
	$("input[name='remarks']").val(remarks);
	$("select[name='categoryCode']").val(categoryCode);	
}
function delRow(code,categoryCode){
	alert(categoryCode);
	$.messager.confirm('警告','确定删除该记录吗？',function(r){
		if(r){
			$.ajax({url:'/jereh/BaseContent/DeleteBaseContentServlet',
				data:{'code':code,'categoryCode':categoryCode},
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
		                url: '/jereh/BaseContent/DeleteBaseContentServlet',  
		               	data:{'code':code,'categoryCode':categoryCode},
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
function searchFun(){
	var code=$("input[name='code']").val();
	var name=$("input[name='name']").val();
	var categoryCode=$("select[name='categoryCode']").val();
	//searchFrm.submit();
	$("#list").datagrid("reload",{code:code,name:name,'categoryCode':categoryCode});	
}
function exportExcl(){
	var code=$("input[name='code']").val();
	var name=$("input[name='name']").val();
	var categoryCode=$("select[name='categoryCode']").val();
	window.location.href="/jereh/excl/OutputExcelBaseContentServlet?code="+code+"&name="+name+"&categoryCode="+categoryCode;
}

function exportWord(){
	var code=$("input[name='code']").val();//row.code;//字典编号
	var codeName=$("input[name='codeName']").val();//row.codeName;//字典名称
	var categoryCode=$("select[name='categoryCode']").val();//row.categoryCode;//所属类别	
	var orderNo=$("input[name='orderNo']").val();//row.orderNo;//排序编号
	var isShow=$("input[name='isShow']").val();//row.isShow;//显示状态
	var remarks=$("input[name='remarks']").val();//row.remarks;//备注 
	window.location.href="/jereh/word/OutputWordBaseContentServlet?code="+code
		+"&codeName="+codeName+"&categoryCode="+categoryCode
		+"&orderNo="+orderNo+"&isShow="+isShow+"&remarks="+remarks;
}
</script>
<style>
#searchFrm{
	background-color:#F4F4F4;
}
</style>

</head>

<body>
    <div id="tools" >
    <form id="searchFrm" action="/jereh/BaseContent/GetBaseContentServlet">  <b>检索条件：</b>	        
        字典编号：<input type="text"  name="code"/>
        字典名称:<input type="text"   name="name"/>
        所属类型：<select name="categoryCode" style="width:100px;"><option>--选择类别--</option></select>
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
	<div id="list">    
    </div>
    
    <div id="dg" style="padding:20px">
		<form name="frm" action="/jereh/BaseContent/UpdateBaseContentServlet" method="post" >
		<input type="hidden" name="id" /> 
		<input type="hidden" name="opt" />
			<table   border="1" bordercolor="#EBEBEB"  cellpadding="0" cellspacing="0">
			<tr>
			    <td class="td1">所属类别：</td><td><select name="categoryCode"></select></td>
			    <td class="td1"><span style="color:red">*</span>字典编号：</td><td><input name="code" type="text"/></td>
			</tr>
			<tr>
			 <td class="td1"><span style="color:red">*</span>字典名称：</td><td><input name="codeName" type="text" /></td>
			 <td class="td1">排序编号：</td><td><input name="orderNo" type="text" /><br/>(请按001格式填写，001优先)</td>
			</tr>
			<tr>
				<td class="td1">显示状态：</td><td><input name="isShow" type="radio" value="1" />显示<input name="isShow" value="0" type="radio"/>隐藏</td>
			    <td class="td1">备注：</td><td><input name="remarks" type="text"/></td>
			</tr>
			</table><br/>
			<input type="submit" value="提交" onclick="" />
			<input type="submit" value="保存" onclick="" />
			<input type="button" value="打印" onclick="exportWord()" />
			<input type="button" name="close" value="关闭" onclick="closeDailog();" />
		</form>
	</div>	
</body>
</html>
