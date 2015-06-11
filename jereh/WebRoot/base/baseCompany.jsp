<%@page pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<style>
body{ padding:0px; margin:0px;}
.label{ width:90px; text-align:right;}
.in{ width:250px;}
td{ border:solid #95ADCC; border-width:1px;}
</style>
<script src="/jereh/js/jquery-1.7.2.min.js"></script>
<script src="/jereh/js/jquery.easyui.min.js"></script>
<link type="text/css" href="/jereh/themes/icon.css" rel="stylesheet" />
<link type="text/css" href="/jereh/themes/default/easyui.css" rel="stylesheet"/>
<script>
$(function(){
	$("input[name='code']").val('${baseCompany.code}');
	$("input[name='compName']").val('${baseCompany.compName}');
	$("input[name='compAddress']").val('${baseCompany.compAddress}');
	$("input[name='compPostCode']").val('${baseCompany.compPostCode}');
	$("input[name='compPhone']").val('${baseCompany.compPhone}');
	$("input[name='compFax']").val('${baseCompany.compFax}');
	$("input[name='compUrl']").val('${baseCompany.compUrl}');
	$("input[name='compEmail']").val('${baseCompany.compEmail}');
	$("input[name='compLegaler']").val('${baseCompany.compLegaler}');
	$("input[name='compAgent']").val('${baseCompany.compAgent}');
	$("input[name='addUser']").val('${baseCompany.addUser}');
	$("input[name='compBank']").val('${baseCompany.compBank}');
	$("input[name='compTax']").val('${baseCompany.compTax}');
	$("input[name='remarks']").val('${baseCompany.remarks}');
});
</script>
</head>

<body>
<div style="padding:10px" class="easyui-window" title=" " 
        maximized="true" maximizable="false" minimizable="false" closable="false">
    <form action="/jereh/base/ModifyBaseCompanyServlet" method="post" target="_self">
        <table border="1" cellspacing="0" style="border-collapse:collapse;">
            <tr>
                <td class="label">公司代码：</td><td class="in"><input type="text" name="code" readonly="readonly" /></td>
                <td class="label">公司名称：</td><td class="in"><input type="text" name="compName" /></td>
            </tr>
            <tr>
                <td class="label">公司地址：</td><td><input type="text" name="compAddress" /></td>
                <td class="label">公司邮编：</td><td><input type="text" name="compPostCode" /></td>
            </tr>
            <tr>
                <td class="label">公司电话：</td><td><input type="text" name="compPhone" /></td>
                <td class="label">公司传真：</td><td><input type="text" name="compFax" /></td>
            </tr>
            <tr>
                <td class="label">公司网址：</td><td><input type="text" name="compUrl" /></td>
                <td class="label">电子邮箱：</td><td><input type="text" name="compEmail" /></td>
            </tr>
            <tr>
                <td class="label">法人：</td><td><input type="text" name="compLegaler" /></td>
                <td class="label">委托代理：</td><td><input type="text" name="compAgent" /></td>
   			</tr>
            <tr>
            	<td class="label">账号：</td><td><input type="text" name="addUser" /></td>
                <td class="label">银行：</td><td><input type="text" name="compBank" /></td>
            </tr>
            <tr>
                <td class="label">税号：</td><td><input type="text" name="compTax" /></td>
                <td class="label">备注：</td><td><input type="text" name="remarks" /></td>
            </tr>
    	</table>
        <div style="padding-top:10px; position:relative; left:589px;">
        	<input type="submit" value="保 存" />&nbsp;<input type="button" value="关 闭" />
        </div>
    </form>
</div>
</body>
</html>
