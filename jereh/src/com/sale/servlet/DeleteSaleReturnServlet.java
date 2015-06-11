package com.sale.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sale.service.SaleReturnService;
import com.sale.service.impl.SaleReturnServiceImpl;

public class DeleteSaleReturnServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DeleteSaleReturnServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	private SaleReturnService saleReturnService = new SaleReturnServiceImpl();

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/json; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String code = request.getParameter("code");
		int ret=saleReturnService.deleteSaleReturn(code);
		response.getWriter().println(ret);
	}
}
