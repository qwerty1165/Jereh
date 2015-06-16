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
import com.purchase.entity.PurchaseOrderDetail;
import com.purchase.service.PurchaseOrderDetailService;
import com.purchase.service.impl.PurchaseOrderDetailServiceImpl;

public class AddPurchaseOrderDetailServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor of the object.
	 */
	public AddPurchaseOrderDetailServlet() {
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
		PurchaseOrderDetail pd = new PurchaseOrderDetail();
		pd.setDcode(request.getParameter("dcode"));
		String ocode = request.getParameter("ocode");
		pd.setOcode(ocode);
		pd.setXcode(request.getParameter("xcode"));
		pd.setPcode(request.getParameter("pcode"));
		String nums = request.getParameter("nums");
		if(nums == null || nums.equals(""))
			pd.setNums(0);
		else
			pd.setNums(Integer.parseInt(nums));
		String price = request.getParameter("price");
		if(price == null || price.equals(""))
			pd.setPrice(0.00);
		else
			pd.setPrice(Double.parseDouble(price));
		pd.setRkState(request.getParameter("rkState"));
		String rkNums = request.getParameter("rkNums");
		if(rkNums == null || rkNums.equals(""))
			pd.setRkNums(0);
		else
			pd.setRkNums(Integer.parseInt(rkNums));
		pd.setRemarks(request.getParameter("remarks"));
		pdService.insertPurchaseOrderDetail(pd);
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
