package com.stock.servlet;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.util.DateUtil;
import com.stock.entity.StockIn;
import com.stock.service.StockInService;
import com.stock.service.impl.StockInServiceImpl;

public class UpdateStockInServlet extends HttpServlet {
	private StockInService service=new StockInServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json; charset=utf-8");
		
		String opt=request.getParameter("opt");
		String supplierCode=request.getParameter("supplierCode");
		String code=request.getParameter("code");
		String inDate=request.getParameter("inDate");		
		String contacter=request.getParameter("contacter");
		String telphone=request.getParameter("telphone");
		String fax=request.getParameter("fax");
		String isShow=request.getParameter("isShow");
		String isRoad=request.getParameter("isRoad");
		String isInvoice=request.getParameter("isInvoice");
		String remarks=request.getParameter("remarks");
		StockIn stockIn=new StockIn();
		stockIn.setCode(code);
		stockIn.setContacter(contacter);
		stockIn.setTelphone(telphone);
		stockIn.setFax(fax);		
		stockIn.setIsShow(isShow);
		stockIn.setIsRoad(isRoad);
		stockIn.setIsInvoice(isInvoice);
		stockIn.setRemarks(remarks);	
		stockIn.setSupplierCode(supplierCode);
		stockIn.setAddUserName("Mar");
		stockIn.setAddIp(request.getRemoteAddr());
		try {
			stockIn.setInDate(DateUtil.toDate(inDate));
		} catch (ParseException e) {			
			e.printStackTrace();
		}
		
		if(opt.equals("1")){//Ìí¼Ó
			service.insertStockIn(stockIn);
		}else if(opt.equals("2")){//ÐÞ¸Ä
			service.updateStockIn(stockIn);
		}
		response.sendRedirect("/jereh/stock/stock_in.jsp");
	}

}
