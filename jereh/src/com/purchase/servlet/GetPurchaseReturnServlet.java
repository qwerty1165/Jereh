package com.purchase.servlet;

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

import com.common.entity.PageBean;
import com.common.util.JSONDateProcessor;
import com.purchase.entity.PurchaseReturn;
import com.purchase.service.PurchaseReturnService;
import com.purchase.service.impl.PurchaseReturnServiceImpl;

public class GetPurchaseReturnServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetPurchaseReturnServlet() {
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
	private PurchaseReturnService pService = new PurchaseReturnServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String code = (String)request.getSession().getAttribute("purchaseReturnCode");
		String beforeDate = (String)request.getSession().getAttribute("purchaseReturnBeforeDate");
		String afterDate = (String)request.getSession().getAttribute("purchaseReturnAfterDate");
		String supplierCode = (String)request.getSession().getAttribute("purchaseReturnSupplierCode");
		
		String purchaseReturnPageNo = request.getParameter("purchaseReturnPageNo");
		if(purchaseReturnPageNo == null || purchaseReturnPageNo.equals(""))
			purchaseReturnPageNo = "1";
		request.getSession().setAttribute("purchaseReturnPageNo", purchaseReturnPageNo);
		
		response.setContentType("text/json; charset=utf-8");
		String pageNo = request.getParameter("page");
		String pageSize = request.getParameter("rows");
		if(pageNo == null || pageNo.equals("") || pageNo.equals("null"))
			pageNo = "1";
		if(pageSize == null || pageSize.equals("") || pageSize.equals("null"))
			pageSize = "5";
		PageBean p = pService.getPurchaseReturn(code, beforeDate, afterDate, supplierCode, 
				Integer.parseInt(pageNo), Integer.parseInt(pageSize));//
		List<PurchaseReturn> pList = p.getData(); 
		int recordCount = p.getRecordCount();
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"fax", "remarks", "isShow", "addUserName", "addIP", "compCode"});
		jsonConfig.registerJsonValueProcessor(Date.class, new JSONDateProcessor("yyyyƒÍMM‘¬dd»’"));
		
		JSONObject jsonObject = new JSONObject();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", pList);
		map.put("total", recordCount);
		jsonObject.putAll(map, jsonConfig);
		String jsonData = jsonObject.toString();
		response.getWriter().println(jsonData);
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
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
