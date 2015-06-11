package com.base.servlet;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.util.DocumentHandler;

public class OutputWordBaseContentServlet extends HttpServlet {
	//private Configuration configuration = null;  
	private DocumentHandler handler=new DocumentHandler();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json; charset=utf-8");
		System.out.println("导出word……");
		String code=request.getParameter("code");//字典编号
		String codeName=new String(request.getParameter("codeName").getBytes("iso8859-1"),"utf-8");//字典名称
		String categoryCode=new String(request.getParameter("categoryCode").getBytes("iso8859-1"),"utf-8");
		String orderNo=request.getParameter("orderNo");
		String isShow=request.getParameter("isShow");
		if(isShow.equals("1")){
			isShow="显示";
		}else{
			isShow="隐藏";
		}
		String remarks=request.getParameter("remarks");
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("categoryCode",categoryCode); //
		dataMap.put("code",code); //
		dataMap.put("codeName",codeName); 
		dataMap.put("orderNo",orderNo); 
		dataMap.put("isShow",isShow); 
		dataMap.put("remarks",remarks); 
		
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		String t=df.format(new Date());// new Date()为获取当前系统时间
		handler.createDoc(dataMap, "F://字典内容"+t+".doc","base_content_tem.ftl");		
		response.sendRedirect("/jereh/base/base_content.jsp");
	}

}
