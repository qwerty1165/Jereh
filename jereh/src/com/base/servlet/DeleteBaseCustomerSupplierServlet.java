package com.base.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.base.service.BaseCustomerSupplierService;
import com.base.service.impl.BaseCustomerSupplierServiceImpl;


public class DeleteBaseCustomerSupplierServlet extends HttpServlet {

	public DeleteBaseCustomerSupplierServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}


	BaseCustomerSupplierService customerService=new BaseCustomerSupplierServiceImpl();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json; charset=utf-8");
		String code=request.getParameter("code");
		int ret=customerService.deleteCustomer(code);
		response.getWriter().println(ret);
	
	}

}
