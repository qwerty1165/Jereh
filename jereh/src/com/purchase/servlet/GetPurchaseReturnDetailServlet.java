package com.purchase.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.purchase.entity.PurchaseReturnDetail;
import com.purchase.service.PurchaseReturnDetailService;
import com.purchase.service.impl.PurchaseReturnDetailServiceImpl;

public class GetPurchaseReturnDetailServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetPurchaseReturnDetailServlet() {
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
	private PurchaseReturnDetailService pdService = new PurchaseReturnDetailServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/json; charset=utf-8");
		String ctcode = request.getParameter("ctcode");
		List<PurchaseReturnDetail> pdList = pdService.getPurchaseReturn(ctcode);
		JSONArray jsonArray = JSONArray.fromObject(pdList);
		String jsonData = jsonArray.toString();
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
