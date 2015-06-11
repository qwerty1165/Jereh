package com.sale.servlet;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.sale.entity.SaleQuotation;
import com.sale.service.SaleQuotationService;
import com.sale.service.impl.SaleQuotationServiceImpl;
import com.common.entity.PageBean;
import com.common.util.JSONDateProcessor;

public class GetSaleQuotationServlet extends HttpServlet {
	

	public GetSaleQuotationServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	SaleQuotationService saleQuotationService = new SaleQuotationServiceImpl();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		
		String pageNo=request.getParameter("page");
		String pageSize=request.getParameter("rows");
		if(pageNo==null||pageNo.equals("")){
			pageNo="1";
		}
		if(pageSize==null||pageSize.equals("")){
			pageSize="5";
		}
		
		SaleQuotation sq = new SaleQuotation();
		String code = request.getParameter("code");
		
		JsonConfig config=new JsonConfig();
		config.registerJsonValueProcessor(Date.class,
				new JSONDateProcessor("yyyy-MM-dd"));
		
		if(code!=null&&!code.equals("")){
			sq.setCode(code);	
		}
		
		PageBean pb = saleQuotationService.findList(sq, Integer.parseInt(pageNo),Integer.parseInt(pageSize));
		JSONObject jsonObject=new JSONObject();
		Map attrs=new HashMap();
		attrs.put("rows", pb.getData());
		attrs.put("total", pb.getRecordCount());
		jsonObject.putAll(attrs,config);
		
		String data=jsonObject.toString();
		System.out.println(data);
        response.getWriter().println(data);
		
	}

}
