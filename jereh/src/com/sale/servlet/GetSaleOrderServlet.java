package com.sale.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.common.entity.PageBean;
import com.common.util.JSONDateProcessor;
import com.sale.entity.SaleOrder;
import com.sale.service.SaleOrderService;
import com.sale.service.impl.SaleOrderServiceImpl;

public class GetSaleOrderServlet extends HttpServlet {

	public GetSaleOrderServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	private SaleOrderService saleOrderService=new SaleOrderServiceImpl();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/json; charset=utf-8");
		String pageNo=request.getParameter("page");
		String pageSize=request.getParameter("rows");
		
		if(pageNo==null||pageNo.equals("")){
			pageNo="1";
		}
		if(pageSize==null||pageSize.equals("")){
			pageSize="10";
		} 
		String startDate=request.getParameter("startDate");
		Date date1=null;
		if(startDate!=null&&!startDate.equals("")){
			try {
				date1=new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
			} catch (ParseException e) {			
				e.printStackTrace();
			}
		}
		String endDate=request.getParameter("endDate");
		Date date2=null;
		if(endDate!=null&&!endDate.equals("")){
			try {
				date2=new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
			} catch (ParseException e) {			
				e.printStackTrace();
			}
		}
		String code=request.getParameter("searchCode");
		
		PageBean pageBean=saleOrderService.findSaleOrder(code,date1,date2, 
				Integer.parseInt(pageNo),Integer.parseInt(pageSize));
		
		
		
		JsonConfig  config=new JsonConfig();
		config.setExcludes(new String[]{"trans"});
		
		config.registerJsonValueProcessor(Date.class,// 如果遇到Date类型
				new JSONDateProcessor("yyyy-MM-dd"));
		
		JSONObject jsonObject=new JSONObject();
		Map attrs=new HashMap();
		attrs.put("rows",pageBean.getData());
		attrs.put("total",pageBean.getRecordCount());
		
		jsonObject.putAll(attrs,config);
		String data=jsonObject.toString();
		System.out.println(data);
		response.getWriter().println(data);
		
	}

}
