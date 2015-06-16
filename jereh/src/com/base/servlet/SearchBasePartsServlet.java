package com.base.servlet;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.base.entity.BaseParts;
import com.base.service.BasePartsService;
import com.base.service.impl.BasePartsServiceImpl;
import com.common.entity.PageBean;

public class SearchBasePartsServlet extends HttpServlet {

	private BasePartsService basePartsService=new BasePartsServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/json; charset=utf-8");
		String pageNo=request.getParameter("page");
		String pageSize=request.getParameter("rows");
		
		if(pageNo==null||pageNo.equals("")){
			pageNo="1";
		}
		if(pageSize==null||pageSize.equals("")){
			pageSize="10";
		} 
		BaseParts bp=new BaseParts();
		String partsNo=request.getParameter("searchNo");
		String partsName=request.getParameter("name");
		
		if(partsNo!=null&&!partsNo.equals("")){
			bp.setPartsNo(partsNo);			
		}
		if(partsName!=null&&!partsName.equals("")){		
			bp.setPartsName(partsName);
		}	
		PageBean pageBean=basePartsService.findByCom(bp,Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		JsonConfig  config=new JsonConfig();
		config.setExcludes(new String[]{"addDate","addIp","addUserName","compCode","costPrice","spell","partsUnit","partsSize","partsImg"});
		
		JSONObject jsonObject=new JSONObject();
		Map attrs=new HashMap();
		attrs.put("rows",pageBean.getData());
		attrs.put("total",pageBean.getRecordCount());
		
		jsonObject.putAll(attrs,config);
		String data=jsonObject.toString();
		System.out.println(data);
		response.getWriter().println(data);
		
		
	}

	/**
	 * Constructor of the object.
	 */
	public SearchBasePartsServlet() {
		super();
	}

}
