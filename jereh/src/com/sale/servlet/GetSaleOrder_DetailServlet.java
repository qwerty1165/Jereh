package com.sale.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.sale.entity.SaleOrder_Detail;
import com.sale.service.SaleOrder_DetailService;
import com.sale.service.impl.SaleOrder_DetailServiceImpl;

public class GetSaleOrder_DetailServlet extends HttpServlet {

	public GetSaleOrder_DetailServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		response.setContentType("text/json; charset=utf-8");
		
		String code = request.getParameter("code");
		//SaleOrder_Detail sd=new SaleOrder_Detail();
		SaleOrder_DetailService sdService=new SaleOrder_DetailServiceImpl();
		List<SaleOrder_Detail> sdList=sdService.findDetailByCode(code);
		
		JSONObject jsonObject=new JSONObject();
		Map attrs=new HashMap();
		attrs.put("rows",sdList);
		
		jsonObject.putAll(attrs);
		String data=jsonObject.toString();
		System.out.println(data);
		response.getWriter().println(data);
		
	}

}
