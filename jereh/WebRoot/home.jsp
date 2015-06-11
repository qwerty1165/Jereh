<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>主页</title>
<script src="/jereh/js/jquery-1.7.2.min.js"></script>
<script src="/jereh/js/jquery.easyui.min.js"></script>
<link type="text/css" href="/jereh/themes/icon.css" rel="stylesheet" />
<link type="text/css" href="/jereh/themes/default/easyui.css" rel="stylesheet"/>    
<style>
	body{ font:12px/20px 微软雅黑; padding:20px 10px 30px 50px;}
	td{ padding:5px;}
	.quick{ width:90px; height:90px; border:#CCC solid 1px; text-align:center;}
	a{ text-decoration:none; color:#333}
	a:hover{color:#F00; width:90px;}
</style>

  </head>
  
  <body>
	<div id="dd" class="easyui-draggable" data-options="handle:'#title'" style="width:160px;height:300px; border:#CCC 1px solid">    
		<div id="title" style="background:#ccc;color:#FFF; padding:2px">我的工作单</div>   
	</div>
<!-- 	<div id="dd" class="easyui-draggable" data-options="handle:'#p'">   
		<div id="p" class="easyui-panel" title="快速通道	"     
		        style="width:540px;height:240px;background:#fafafa;">   
				<table >
		        	<tr>
		            	<td><div class="quick"><a href="#"><img src="../img/add.jpg"/></a></div></td>
		                <td><div class="quick"><a href="#"><img src="../img/fankui.jpg"/></a></div></td>
		                <td><div class="quick"><a href="#"><img src="../img/fenxi.jpg"/></a></div></td>
		                <td><div class="quick"><a href="#"><img src="../img/guidang.jpg"/></a></div></td>
		                <td><div class="quick"><a href="#"><img src="../img/quanxian.jpg"/></a></div></td>
		            </tr>
		            <tr>
		            	<td><div class="quick"><a href="#"><img src="../img/quanxian.jpg"/></a></div></td>
		                <td><div class="quick"><a href="#"><img src="../img/quanxian.jpg"/></a></div></td>
		                <td><div class="quick"><a href="#"><img src="../img/quanxian.jpg"/></a></div></td>
		                <td><div class="quick"><a href="#"><img src="../img/quanxian.jpg"/></a></div></td>
		                <td><div class="quick"><a href="#"><img src="../img/quanxian.jpg"/></a></div></td>
		            </tr>
		        </table>
		</div>
	</div> -->
  </body>
</html>
