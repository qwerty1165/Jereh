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
import com.purchase.entity.PurchaseInQuery;
import com.purchase.entity.PurchaseOrder;
import com.purchase.service.PurchaseInQueryService;
import com.purchase.service.PurchaseOrderService;
import com.purchase.service.impl.PurchaseInQueryServiceImpl;
import com.purchase.service.impl.PurchaseOrderServiceImpl;

public class GetPurchaseOrderServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetPurchaseOrderServlet() {
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
	private PurchaseOrderService pService = new PurchaseOrderServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String code = (String)request.getSession().getAttribute("purchaseOrderCode");
		String beforeDate = (String)request.getSession().getAttribute("purchaseOrderBeforeDate");
		String afterDate = (String)request.getSession().getAttribute("purchaseOrderAfterDate");
		String supplierCode = (String)request.getSession().getAttribute("purchaseOrderSupplierCode");
		
		String purchaseOrderPageNo = request.getParameter("purchaseOrderPageNo");
		if(purchaseOrderPageNo == null || purchaseOrderPageNo.equals(""))
			purchaseOrderPageNo = "1";
		request.getSession().setAttribute("purchaseOrderPageNo", purchaseOrderPageNo);
		
		response.setContentType("text/json; charset=utf-8");
		String pageNo = request.getParameter("page");
		String pageSize = request.getParameter("rows");
		if(pageNo == null || pageNo.equals("") || pageNo.equals("null"))
			pageNo = "1";
		if(pageSize == null || pageSize.equals("") || pageSize.equals("null"))
			pageSize = "5";
		PageBean p = pService.getPurchaseOrder(code, beforeDate, afterDate, supplierCode, 
				Integer.parseInt(pageNo), Integer.parseInt(pageSize));//
		List<PurchaseOrder> pList = p.getData(); 
		int recordCount = p.getRecordCount();
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"fax", "remarks", "isShow", "addUserName", "addIP", "trans","deliveryDate","businesser"});//设置把哪些实体属性排除
		jsonConfig.registerJsonValueProcessor(Date.class, new JSONDateProcessor("yyyy年MM月dd日"));
		
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
