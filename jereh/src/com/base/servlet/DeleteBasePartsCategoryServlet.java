package com.base.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.base.service.BasePartsCategoryService;
import com.base.service.impl.BasePartsCategoryServiceImpl;

public class DeleteBasePartsCategoryServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	private BasePartsCategoryService basePartScategoryService = new BasePartsCategoryServiceImpl();

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/json; charset=utf-8");
		String code = request.getParameter("code");
		basePartScategoryService.deleteBasePartsCategory(code);
		response.sendRedirect("../base/BasePartsCategory.jsp");
	}
}
