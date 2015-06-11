<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>报价单</title>
<script src="/jereh/js/jquery-1.7.2.min.js"></script>
<script src="/jereh/js/jquery.easyui.min.js"></script>
<link type="text/css" href="/jereh/themes/default/easyui.css" rel="stylesheet" />
<link type="text/css" href="/jereh/themes/icon.css" rel="stylesheet" />
<script type="text/javascript">
$(function(){
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
});


</script>
  </head>
  
  <body>
  <div id="addSaleQuotation">
    <form action="" >
      <table border="1" style="border-collapse:collapse;">
        <tr><td>报价编号：</td><td><input type="text"></td>
            <td>报价日期：</td><td><input type="text" class="easyui-datebox"></td></tr>
        <tr><td>客户名称：</td><td><input type="text"></td>
            <td>联系人员：</td><td><input type="text"></td></tr>
        <tr><td>电话：</td><td><input type="text"></td>
            <td>传真：</td><td><input type="text"></td></tr>    
        <tr><td>备注：</td><td rowspan="3"><input type="text"></td></tr>
      </table>
      <br/>
      <input type="button" value="新增" />
      <input type="button" value="添加配件" />
      <input type="button" value="保存" />
      <input type="button" value="审核" />
      <input type="button" value="撤销" />
      <input type="button" value="打印" />
      <input type="button" value="生成Word" />
      <input type="button" value="生成订单" />
      <input type="button" value="关闭" />
    </form>
    </div>
    <div id="List">
    
    </div>
  </body>
</html>
