package com.sale.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sale.entity.SaleQuotation;
import com.sale.service.SaleQuotationService;
import com.sale.service.impl.SaleQuotationServiceImpl;

public class UpdateSaleQuotationServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UpdateSaleQuotationServlet() {
		super();
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	SaleQuotationService saleQuotationService = new SaleQuotationServiceImpl();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/jsp; charset=utf-8");

		String opt=request.getParameter("opt");
		SaleQuotation saleQuotation = new SaleQuotation();
		saleQuotation.setCode(request.getParameter("code"));
		try {
			saleQuotation.setSqDate(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("sqDate")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		saleQuotation.setCustomercode(request.getParameter("customercode"));
		saleQuotation.setCsName(request.getParameter("csName"));
		saleQuotation.setContacter(request.getParameter("contacter"));
		saleQuotation.setTelphone(request.getParameter("telphone"));
		saleQuotation.setFax(request.getParameter("fax"));
		saleQuotation.setRemarks(request.getParameter("remarks"));
		
		if(opt=="1"){
			saleQuotationService.addSaleQuotation(saleQuotation);
			response.sendRedirect("/jereh/sale/salequotation.jsp");
		}
		else {
			saleQuotationService.updateSaleQuotation(saleQuotation);
			response.sendRedirect("/jereh/sale/salequotation.jsp");
		}
		
	}

}
