package com.stock.servlet;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.util.DateUtil;
import com.stock.entity.StockIn;
import com.stock.entity.StockOut;
import com.stock.service.StockOutService;
import com.stock.service.impl.StockOutServiceImpl;

public class UpdateStockOutServlet extends HttpServlet {
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
		
		String opt=request.getParameter("opt");
		String customerCode=request.getParameter("customerCode");
		String code=request.getParameter("code");
		String outDate=request.getParameter("outDate");		
		String contacter=request.getParameter("contacter");
		String telphone=request.getParameter("telphone");
		String fax=request.getParameter("fax");	
		String outType=request.getParameter("outType");				
		String isInvoice=request.getParameter("isInvoice");
		String remarks=request.getParameter("remarks");
		StockOut stockOut=new StockOut();
		stockOut.setCode(code);
		stockOut.setContacter(contacter);
		stockOut.setTelphone(telphone);
		stockOut.setFax(fax);		
		stockOut.setOutType(outType);
		stockOut.setIsInvoice(isInvoice);
		stockOut.setRemarks(remarks);	
		stockOut.setCustomerCode(customerCode);
		stockOut.setAddUserName("Mar");
		stockOut.setAddIp(request.getRemoteAddr());//获取客户端的ip
		try {
			stockOut.setOutDate(DateUtil.toDate(outDate));
		} catch (ParseException e) {			
			e.printStackTrace();
		}
		int ret=-1;
		if(opt.equals("1")){//添加
			ret=service.insertStockOut(stockOut);
		}else if(opt.equals("2")){//修改
			ret=service.updateStockOut(stockOut);
		}
		System.out.println("操作返回值："+ret);
		response.sendRedirect("/jereh/stock/stock_out.jsp");
	}

}
