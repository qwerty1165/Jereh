package com.sale.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sale.entity.SaleOrder;
import com.sale.service.SaleOrderService;
import com.sale.service.impl.SaleOrderServiceImpl;

public class UpdateSaleOrderServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UpdateSaleOrderServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	private SaleOrderService saleOrderService=new SaleOrderServiceImpl();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/jsp; charset=utf-8");
		String opt=request.getParameter("opt");
		
		SaleOrder saleOrder=new SaleOrder();
		
		saleOrder.setCode(request.getParameter("code"));
		try {
			saleOrder.setDeLiveryDate(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("deLiveryDate")));
			saleOrder.setOrderDate(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("orderDate")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		saleOrder.setCustomerCode(request.getParameter("customerName"));
		saleOrder.setCsName(request.getParameter("csName"));
		saleOrder.setContacter(request.getParameter("contacter"));
		saleOrder.setTelphone(request.getParameter("telphone"));
		saleOrder.setFax(request.getParameter("fax"));
		saleOrder.setTrans(request.getParameter("trans"));
		saleOrder.setBusinesser(request.getParameter("businesser"));
		saleOrder.setRemarks(request.getParameter("remarks"));
		
		if(opt.equals("1")){
			saleOrderService.insertSaleOrder(saleOrder);
			response.sendRedirect("/jereh/sale/saleOrder.jsp");
		}else{
			
			
		}
		
		
	}

}
