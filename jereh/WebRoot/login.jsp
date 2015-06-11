<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8">
	<title>我的登陆页面</title>
<script src="/jereh/js/jquery-1.7.2.min.js"></script>
<script src="/jereh/js/jquery.easyui.min.js"></script>
<link type="text/css" href="/jereh/themes/icon.css" rel="stylesheet" />
<link type="text/css" href="/jereh/themes/default/easyui.css" rel="stylesheet"/>
</head>
<body>
<div id="win" class="easyui-window" title="进销存管理系统" data-options="iconCls:'icon-save',collapsible:false,minimizable:false,maximizable:false" style="width:400px;">
	<form id="tform"  action="../../user/LoginServlet" style="padding:50px 20px 10px 50px;" method="post">
    	<img src="/jereh/img/user.jpg" style="float:right"/>
		<p>用户名: <input type="text" name="name"></p>
		<p>密&nbsp;&nbsp;码: <input type="password" name="pwd"></p>	
	</form>
    <div style="clear:right"></div>
    <div style=" margin-top:10px;padding:5px;text-align: right; float:right; width:100%; height:25px; background-color:#E8E8E8">
        	<a href="#" class="easyui-linkbutton" onclick="document.getElementById('tform').submit()">登录</a>
	</div>
</div>

</body>
</html>
