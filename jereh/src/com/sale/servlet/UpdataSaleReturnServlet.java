package com.sale.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sale.entity.SaleReturn;
import com.sale.service.SaleReturnService;
import com.sale.service.impl.SaleReturnServiceImpl;

public class UpdataSaleReturnServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UpdataSaleReturnServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/json; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		SaleReturn saleReturn = null;
		String opt = request.getParameter("opt");

		String code = request.getParameter("code");
		// String xtDate =request.getParameter("xtDate");
		String customerCode = request.getParameter("customerCode");
		String contacter = request.getParameter("contacter");
		String telPhone = request.getParameter("telPhone");
		String fax = request.getParameter("fax");
		String remarks = request.getParameter("remarks");

		saleReturn = new SaleReturn();
		saleReturn.setCustomerCode(customerCode);
		saleReturn.setContacter(contacter);
		saleReturn.setTelPhone(telPhone);
		saleReturn.setRemarks(remarks);

		SaleReturnService saleReturnService = new SaleReturnServiceImpl();
		if (opt.equals("update")) {
			saleReturnService.updataSaleReturn(saleReturn, code);
		} else if (opt.equals("add")) {
			saleReturnService.addSaleReturn(saleReturn);
		}
		response.sendRedirect("../sale/SaleReturn.jsp");
	}
}
