package com.base.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.base.entity.BaseContent;
import com.base.service.BaseContentService;
import com.base.service.impl.BaseContentServiceImpl;

public class GetCategoryJsonServlet extends HttpServlet {
	
	private BaseContentService service=new BaseContentServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/json; charset=utf-8");
		List<BaseContent> list=service.getCategory();
		
		JSONArray jsonArray=JSONArray.fromObject(list);		
		String jsonList=jsonArray.toString();
		//System.out.println("数据库中的数据转换成json"+"\r"+jsonList);
		//将sjon数据返回到前台页面
		response.getWriter().println(jsonList);
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
}
