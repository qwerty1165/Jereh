<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>进销存系统后台管理</title>
<script src="/jereh/js/jquery-1.7.2.min.js"></script>
<script src="/jereh/js/jquery.easyui.min.js"></script>
<link type="text/css" href="/jereh/themes/icon.css" rel="stylesheet" />
<link type="text/css" href="/jereh/themes/default/easyui.css" rel="stylesheet"/>
<script>
function addTab(t,u){	
	if($("#tt").tabs("exists",t)){
		$("#tt").tabs("select",t);
	}else{
		var tavContent="<iframe src=\""+u+"\" width=\"100%\" height=\"100%\" frameborder=\"0\" scrolling=\"no\"></iframe>";
		$("#tt").tabs("add",{
		title:t,
		closable:true,
		fit:true,
		content:tavContent
		});	
	}	
}
function showDate(){
	var now=new Date();
	var hour=now.getHours();
	var minu=now.getMinutes();
	var sec=now.getSeconds();
	if(sec<10){sec="0"+sec;};		
	this.day = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六")[now.getDay()];
	var time=now.getFullYear()+'-'+(now.getMonth()+1)+'-'+now.getDate()+' '+day+' '+hour+':'+minu+':'+sec;	
	$("#timeDiv").html(time);
	 window.setTimeout( showDate, 1000);  
}
$(function(){
	showDate();
});
</script>
<style>
body{
	font:16px/14px 微软雅黑;
	font-weight:bold;
}
a{text-decoration:none;}
ul,li{margin:0px; padding:0px;}
li{ 
	padding:2px 20px;
	list-style:none;
	height:25px;
	line-height:25px;
	border-bottom:#EBEBEB solid 1px;	
}
li a{
	color:#333;	
}
li a:hover{
	color:#F90;
} 
#timeDiv{	
	padding:2px 20px;
	position:fixed;
	right:2px;		
	font:20px/50px 微软雅黑;
	color:#666;	
}
</style>
</head>

<body>
<div  class="easyui-layout" fit="true">
	<div  data-options="region:'north'" style="border-top:#36C solid 5px; background-color:#E0ECFF; padding:7px 5px 2px 30px; color:#0E64BE; overflow:hidden"> 
	  	<span style="font-size:25px">进销存系统后台管理</span>
        <div id="tools" style="float:right; font-size:12px" >                    	
            <span>您好，&nbsp;管理员</span>   
    	   	<span><a href="#"><img src="/jereh/img/add.gif"/>更改个人信息</a></span>   
            <span><a href="exit.jsp" ><img src="/jereh/img/edit.gif"/>安全退出</a></span>    
            <span><input type="text"/><a id="btn" href="#" class="easyui-linkbutton" >搜索</a> </span>  
         </div>   
    </div>
    <div region="west" title="导航" spilt="true" style="width:150px;">
    	 <div class="easyui-accordion" data-options="fit:true">
            <div data-options="iconCls:'icon-search',title:'采购管理'">
               	<ul>
                	<li><a href="#" onclick="addTab('询价单据管理','#')">询价单据管理</a></li>                    
                 	<li><a href="#" onclick="addTab('采购订单管理','#')">采购订单管理</a></li>
               	 	<li><a href="#" onclick="addTab('采购退货管理','#')">采购退货管理</a></li>
                    <li><a href="#" onclick="addTab('采购合同管理','#')">采购合同管理</a></li>
                </ul>
            </div>
            <div title="销售管理" data-options="iconCls:'icon-add'">
                <ul>
                	<li><a href="#" onclick="addTab('销售报价管理','#')">销售报价管理</a></li>
               	 	<li><a href="#" onclick="addTab('销售订单管理','#')">销售订单管理</a></li>
                    <li><a href="#" onclick="addTab('销售退货管理','#')">销售退货管理</a></li>
               	 	<li><a href="#" onclick="addTab('销售合同管理','#')">销售合同管理</a></li>
                </ul>
            </div>
            <div title="库存管理" data-options="iconCls:'icon-reload'">
                <ul>
                	<li><a href="#" onclick="addTab('入库单据管理','#')">入库单据管理</a></li>
               	 	<li><a href="#" onclick="addTab('出库单据管理','#')">出库单据管理</a></li>
               	 	<li><a href="#" onclick="addTab('库存盘点调价','#')">库存盘点调价</a></li>
               		<li><a href="#" onclick="addTab('库存调拨管理','#')">库存调拨管理</a></li>
                    <li><a href="#" onclick="addTab('库存信息管理','#')">库存信息管理</a></li>
                </ul>
            </div>
            <div title="财务管理" data-options="iconCls:'icon-large-chart'">
                <ul>
                	<li><a href="#" onclick="addTab('采购付款管理','#')">采购付款管理</a></li>
               	 	<li><a href="#" onclick="addTab('采购收票管理','#')">采购收票管理</a></li>
               	 	<li><a href="#" onclick="addTab('销售收款管理','#')">销售收款管理</a></li>
               		<li><a href="#" onclick="addTab('销售开票管理','#')">销售开票管理</a></li>
                    <li><a href="#" onclick="addTab('预付账款管理','#')">预付账款管理</a></li>
                    <li><a href="#" onclick="addTab('预收账款管理','#')">预收账款管理</a></li>
               	 	<li><a href="#" onclick="addTab('费用转账管理','#')">费用转账管理</a></li>

                </ul>
            </div>
            <div title="统计管理" data-options="iconCls:'icon-large-clipart'" >
                <ul>
                	<li><a href="#" onclick="addTab('销售走势管理','#')">销售走势管理</a></li>
               	 	<li><a href="#" onclick="addTab('销售排行管理','#')">销售排行管理</a></li>
               	 	<li><a href="#" onclick="addTab('采购走势管理','#')">采购走势管理</a></li>
               		<li><a href="#" onclick="addTab('采购排行管理','#')">采购排行管理</a></li>
                    <li><a href="#" onclick="addTab('应收应付管理','#')">应收应付管理</a></li>
                    <li><a href="#" onclick="addTab('毛利润量管理','#')">毛利润量管理</a></li>
               	 	<li><a href="#" onclick="addTab('费用统计管理','#')">费用统计管理</a></li>
                    <li><a href="#" onclick="addTab('历史询价管理','#')">历史询价管理</a></li>
                    <li><a href="#" onclick="addTab('历史报价管理','#')">历史报价管理</a></li>
                </ul>
            </div>
           <div title="基础管理" data-options="iconCls:'icon-large-shapes',selected:true">
                <ul>
					<li><a href="#" onclick="addTab('往来单位管理','/jereh/base/BaseCustomerSupplier.jsp')">往来单位管理</a></li>
               	 	<li><a href="#" onclick="addTab('字典内容管理','/jereh/base/base_content.jsp')">字典内容管理</a></li>
               	 	<li><a href="#" onclick="addTab('配件类别管理','/jereh/base/BasePartsCategory.jsp')">配件类别管理</a></li>
               	 	<li><a href="#" onclick="addTab('配件信息管理','/jereh/base/baseParts.jsp')">配件信息管理</a></li>
               		<li><a href="#" onclick="addTab('公司信息管理','/jereh/base/BaseCompanySearchServlet')">公司信息管理</a></li>
                </ul>
            </div>
            <div title="系统管理" data-options="iconCls:'icon-large-clipart'" >
                <ul>
                	<li><a href="#" onclick="addTab('管理人员管理','#')">管理人员管理</a></li>
                    <li><a href="#" onclick="addTab('角色权限管理','#')">角色权限管理</a></li>
                    <li><a href="#" onclick="addTab('期初数据管理','#')">期初数据管理</a></li>
                    <li><a href="#" onclick="addTab('期初库存管理','#')">期初库存管理</a></li>
                </ul>
            </div>
        </div> 
    </div>
    
    <div  region="center"  spilt="true" fit="true">
    	<div id="tt" class="easyui-tabs" fit="true">
           <div title="主页"  closable="false" fit="true" ><!-- href="home.html"-->
            	<iframe src="home.jsp" width="100%" height="100%" frameborder="0" scrolling="no"></iframe>
            </div>
        </div>
    </div>
    <div title="当前时间" region="south" spilt="true" style="height:80px;">
    	<div id="timeDiv"></div>
    </div>

</div>
</body>
</html>

