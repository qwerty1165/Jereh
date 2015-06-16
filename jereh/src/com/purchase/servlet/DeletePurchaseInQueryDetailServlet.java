package com.purchase.servlet;

import java.io.IOException;
//import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.purchase.dao.PurchaseInQueryDao;
import com.purchase.dao.PurchaseInQueryDetailDao;
import com.purchase.dao.impl.PurchaseInQueryDaoImpl;
import com.purchase.dao.impl.PurchaseInQueryDetailDaoImpl;
import com.purchase.service.PurchaseInQueryDetailService;
import com.purchase.service.impl.PurchaseInQueryDetailServiceImpl;

public class DeletePurchaseInQueryDetailServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor of the object.
	 */
	public DeletePurchaseInQueryDetailServlet() {
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
	private PurchaseInQueryDetailService pdService = new PurchaseInQueryDetailServiceImpl();
	private PurchaseInQueryDetailDao pdDao = new PurchaseInQueryDetailDaoImpl();
	private PurchaseInQueryDao pDao = new PurchaseInQueryDaoImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String purchaseInQueryPageNo = request.getParameter("purchaseInQueryPageNo");
		request.getSession().setAttribute("purchaseInQueryPageNo", purchaseInQueryPageNo);
		String dcode = request.getParameter("dcode");
		pdService.deletePurchaseInQueryDetail(dcode);
		String xcode = request.getParameter("xcode");
		double sumMoney = pdDao.getNumsPrice(xcode);
		String code = xcode;
		pDao.updateNumsPrice(sumMoney, code);
		response.sendRedirect("/jereh/purchase/purchaseInQuery.jsp");
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
