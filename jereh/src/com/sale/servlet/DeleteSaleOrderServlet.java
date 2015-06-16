package com.sale.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sale.service.SaleOrderService;
import com.sale.service.impl.SaleOrderServiceImpl;

public class DeleteSaleOrderServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DeleteSaleOrderServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	SaleOrderService saleOrderService=new SaleOrderServiceImpl();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/json;charset=utf-8");
		String code=request.getParameter("code");
		int ret=saleOrderService.deleteSaleOrder(code);
		response.getWriter().println(ret);
		
		
	}

}
