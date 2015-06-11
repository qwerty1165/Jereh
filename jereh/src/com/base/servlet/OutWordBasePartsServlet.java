package com.base.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.common.util.DocumentHandler;

public class OutWordBasePartsServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public OutWordBasePartsServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json; charset=utf-8");
		
		//String codeName=new String(request.getParameter("codeName").getBytes("iso8859-1"),"utf-8"); 
		
		String partsCode=request.getParameter("partsCode");
		String partsCategory=request.getParameter("partsCategory");
		String partsName=new String(request.getParameter("partsName").getBytes("iso8859-1"),"utf-8"); 
		String partsBrand=new String(request.getParameter("partsBrand").getBytes("iso8859-1"),"utf-8"); 
		
		String partsModel=request.getParameter("partsModel");
		String partsModelOld=request.getParameter("partsModelOld");
		String salePrice=request.getParameter("salePrice");
		String partsWeight=request.getParameter("partsWeight");
	//	String partsSize=request.getParameter("partsSize");
		String isShow=request.getParameter("isShow");
		String remarks=request.getParameter("remarks");
		
		DocumentHandler h=new DocumentHandler();
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("partsCode", partsCode); 
		dataMap.put("partsCategory", partsCategory); 
		dataMap.put("partsName", partsName); 
		dataMap.put("partsGeneralPartsNo", "1"); 
		dataMap.put("partsNo", "1"); 
		dataMap.put("partsBrand", partsBrand); 
		dataMap.put("partsModel", partsModel); 
		dataMap.put("partsModelOld", partsModelOld); 
		dataMap.put("salePrice", salePrice);
		dataMap.put("partsweight",partsWeight );
		dataMap.put("partsImg", "1");
		dataMap.put("partsUnit", "1");
		dataMap.put("partsSize", "1");
		dataMap.put("isShow",isShow );
		dataMap.put("remarks",remarks );
		
		SimpleDateFormat df = new SimpleDateFormat("ddHHmmss");//设置日期格式
		String t=df.format(new Date());// new Date()为获取当前系统时间
		h.createDoc(dataMap, "f://配件信息"+t+".doc","parts.ftl");
		
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("fileName","p"+t+".doc");
		String data=jsonObject.toString();
		System.out.println(data);
		response.getWriter().println(data);
	}

}
