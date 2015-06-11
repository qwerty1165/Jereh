package com.base.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.base.entity.BaseContent;
import com.base.service.BaseContentService;
import com.base.service.impl.BaseContentServiceImpl;

public class DeleteBaseContentServlet extends HttpServlet {
	private BaseContentService service=new BaseContentServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//1°¢
		String code=request.getParameter("code");
		String categoryCode=request.getParameter("categoryCode");
		BaseContent bc=new BaseContent();
		bc.setCode(code);
		bc.setCategoryCode(categoryCode);
		
		//2°¢
		int ret=service.deleteBaseContent(bc);
		//3°¢÷ÿ∂®œÚ		
		response.getWriter().println(ret);;
	}

}
