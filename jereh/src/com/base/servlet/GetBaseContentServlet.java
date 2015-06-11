package com.base.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;

import com.base.entity.BaseContent;
import com.base.service.BaseContentService;
import com.base.service.impl.BaseContentServiceImpl;
import com.common.entity.PageBean;

public class GetBaseContentServlet extends HttpServlet {
	private BaseContentService service=new BaseContentServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json; charset=utf-8");
		String pageNo=request.getParameter("page");
		String pageSize=request.getParameter("rows");
		if(pageNo==null||pageNo.equals("")){
			pageNo="1";
		}
		if(pageSize==null||pageSize.equals("")){
			pageSize="10";
		}
		
		BaseContent bc=new BaseContent();
		String code=request.getParameter("code");
		String codeName=request.getParameter("name");
		String categoryCode=request.getParameter("categoryCode");
		if(code!=null&&!code.equals("")){
			bc.setCode(code);			
		}
		if(codeName!=null&&!codeName.equals("")){	
			bc.setCodeName(codeName);
		}
		if(categoryCode!=null&&!categoryCode.equals("--Ñ¡ÔñÀà±ð--")){
			bc.setCategoryCode(categoryCode);				
		}
		
		PageBean pb=service.getListByCondition(bc,Integer.valueOf(pageNo), Integer.valueOf(pageSize));
		JSONObject obj=new JSONObject();
		obj.put("rows", pb.getData());
		obj.put("total",pb.getRecordCount());
		String data=obj.toString();
		response.getWriter().println(data);
		//System.out.println(pb.getRecordCount());
	}

}
