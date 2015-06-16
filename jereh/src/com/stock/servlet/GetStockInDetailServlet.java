package com.stock.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.stock.entity.StockInDetail;
import com.stock.service.StockInService;
import com.stock.service.impl.StockInServiceImpl;

public class GetStockInDetailServlet extends HttpServlet {
	private StockInService service=new StockInServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("type/json; charset=utf-8");
		String inCode=request.getParameter("inCode");
		List<StockInDetail> list=service.getStockInDetailList(inCode);		
		JSONObject obj=new JSONObject();
		obj.put("rows", list);		
		String data=obj.toString();
		response.getWriter().println(data);				
	}

}
