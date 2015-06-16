package com.purchase.servlet;

import java.io.IOException;
//import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.purchase.dao.PurchaseOrderDao;
import com.purchase.dao.PurchaseOrderDetailDao;
import com.purchase.dao.impl.PurchaseOrderDaoImpl;
import com.purchase.dao.impl.PurchaseOrderDetailDaoImpl;
import com.purchase.service.PurchaseOrderDetailService;
import com.purchase.service.impl.PurchaseOrderDetailServiceImpl;

public class DeletePurchaseOrderDetailServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor of the object.
	 */
	public DeletePurchaseOrderDetailServlet() {
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
	private PurchaseOrderDetailService pdService = new PurchaseOrderDetailServiceImpl();
	private PurchaseOrderDetailDao pdDao = new PurchaseOrderDetailDaoImpl();
	private PurchaseOrderDao pDao = new PurchaseOrderDaoImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String purchaseOrderPageNo = request.getParameter("purchaseOrderPageNo");
		request.getSession().setAttribute("purchaseOrderPageNo", purchaseOrderPageNo);
		String dcode = request.getParameter("dcode");
		pdService.deletePurchaseOrderDetail(dcode);
		String ocode = request.getParameter("ocode");
		double sumMoney = pdDao.getNumsPrice(ocode);
		String code = ocode;
		pDao.updateNumsPrice(sumMoney, code);
		response.sendRedirect("/jereh/purchase/purchaseOrder.jsp");
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
