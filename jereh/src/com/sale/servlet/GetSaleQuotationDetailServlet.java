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

import com.common.util.JSONDateProcessor;
import com.sale.entity.SaleQuotation_Detail;
import com.sale.service.SaleQuotationService;
import com.sale.service.impl.SaleQuotationServiceImpl;

public class GetSaleQuotationDetailServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetSaleQuotationDetailServlet() {
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
        response.setContentType("text/json;charset=utf-8");
        String scode = request.getParameter("code");
        
        JsonConfig config=new JsonConfig();
		config.registerJsonValueProcessor(Date.class,
				new JSONDateProcessor("yyyyƒÍMM‘¬dd»’"));
		
	    List<SaleQuotation_Detail> list = saleQuotationService.showDetail(scode);
        JSONObject jsonObject=new JSONObject();
		Map attrs=new HashMap();		
		 attrs.put("rows", list);
		jsonObject.putAll(attrs,config);
		
		String data=jsonObject.toString();
		System.out.println(data);
        response.getWriter().println(data);
	}

}
