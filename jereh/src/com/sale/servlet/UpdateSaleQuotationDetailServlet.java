package com.sale.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateSaleQuotationDetailServlet extends HttpServlet {

	
	public UpdateSaleQuotationDetailServlet() {
		super();
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/jsp; charset=utf-8");
		
		String partsNo = request.getParameter("code");
		String partsName = request.getParameter("scode");
		String partsBrand = request.getParameter("pcode");
		String partsModel = request.getParameter("nums");
		String price = request.getParameter("price");
		String deliveryMode = request.getParameter("deliveryMode");
		String remarks = request.getParameter("remarks");
		
		
		
		
	}

}
