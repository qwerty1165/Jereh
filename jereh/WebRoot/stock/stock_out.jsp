<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>出库单据管理</title>
<script src="/ERP/js/jquery-1.7.2.min.js"></script>
<script src="/ERP/js/jquery.easyui.min.js"></script>
<link type="text/css" href="/ERP/themes/icon.css" rel="stylesheet" />
<link type="text/css" href="/ERP/themes/default/easyui.css" rel="stylesheet"/>

<script type="text/javascript">
$(function(){
		//弹出添加窗口
  	$("#dg").dialog({		
		width:600,height:200,
		modal:true,
		closed:true	
		
	});  
	$("#dg").dialog("close");
	
	$.ajax({url:'/ERP/BaseContent/GetCategoryJsonServlet',
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
		url:' ',	
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
					{field:'code',title:'出库单号',width:80},
					{field:'inDate',title:'出库日期',width:80},					
					{field:'supplierName',title:'客户名称',width:200},
					{field:'nums',title:'数量',fixed:80},
					{field:'numsPrice',title:'总货值',width:80},
					
					{field:'payState',title:'收款情况',width:80},
					{field:'getState',title:'开票情况',width:80},
					
					{field:'isShow',title:'审核状态',width:80},	
					{field:'remarks',title:'操作员',width:80},
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

function detail(){

	$("#detailList").datagrid({
		url:'/ERP/BaseContent/GetBaseContentServlet',	
		idField:'code',		
		singleSelect:false,
		success:function(data){//data是GetChannelServlet中取得的json数据	
			if(data.total==0){
				alert("没有数据！");	
			}
		},		
		columns:[[							
					{field:'code',title:'入库单号',fixed:true,
					 formatter:function(val,row,idx){
														
							return "<a href='#'>"+code+"</a>"; 
						}
					},
					{field:'inDate',title:'入库日期',fixed:true},					
					{field:'supplierName',title:'供应商名',fixed:true},
					
					{field:'supplierCode',title:'供应编号',hidden:true},
					
					{field:'nums',title:'数量',fixed:true},
					{field:'numsPrice',title:'总货值',fixed:true},
					{field:'payState',title:'付款情况',fixed:true},
					{field:'getState',title:'收票情况',fixed:true},
					{field:'isShow',title:'审核状态',fixed:true},	
					{field:'remarks',title:'操作员',fixed:true},
					{field:'opt',title:'操作',fixed:true
					,formatter:function(val,row,idx){
							var opt="<input type='button' value='删除' onclick=\"delRow('"+row.code+"','"+row.categoryCode+"')\"/>";
							opt+="<input type='button' value='修改'  onclick='updateRow("+idx+")'/>";							
							return opt; 
						}
					}
				]],	
		data:{code:'dafd'},	
		fit:true
	});	
	

}

function showDailog(stitle){
	$("#dg").dialog({title:stitle});
	$("#dg").dialog("open");
}
function closeDailog(){
	$("#dg").dialog("close");
}
function addRow(){
	
}
function updateRow(idx){

}
function delRow(code,categoryCode){
	alert(categoryCode);
	$.messager.confirm('警告','确定删除该记录吗？',function(r){
		if(r){
			$.ajax({url:'/ERP/BaseContent/DeleteBaseContentServlet',
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
		                url: '/ERP/BaseContent/DeleteBaseContentServlet',  
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
	
}
function exportExcl(){
	
}

function exportWord(){

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
    <form id="searchFrm" action="/ERP/BaseContent/GetBaseContentServlet">  <b>检索条件：</b>	        
        入库单号：<input type="text"  name="code"/>
        开始日期:<input type="text" class="easyui-datebox"  name="date1"/>
        结束日期：<input type="text" class="easyui-datebox"   name="date2"/>
	供应商名：<input type="text"  name="code"/>
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
    
    
    <div id="detail" style="padding:20px">
    	<div>单据标号为：《的明细如下所列！</div>	
    	<div id="detailList"></div>	
	</div>	
</body>
</html>
