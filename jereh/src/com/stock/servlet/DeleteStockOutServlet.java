package com.stock.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stock.service.StockOutService;
import com.stock.service.impl.StockOutServiceImpl;

public class DeleteStockOutServlet extends HttpServlet {
	private StockOutService service=new StockOutServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json; charset=utf-8");
		String code=request.getParameter("code");
		int ret=service.deleteStockOut(code);		
		response.getWriter().println(ret);
	}

}
