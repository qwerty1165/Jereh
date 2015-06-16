package com.purchase.servlet;

import java.io.IOException;
//import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.purchase.dao.PurchaseReturnDao;
import com.purchase.dao.PurchaseReturnDetailDao;
import com.purchase.dao.impl.PurchaseReturnDaoImpl;
import com.purchase.dao.impl.PurchaseReturnDetailDaoImpl;
import com.purchase.entity.PurchaseReturnDetail;
import com.purchase.service.PurchaseReturnDetailService;
import com.purchase.service.impl.PurchaseReturnDetailServiceImpl;

public class AddPurchaseReturnDetailServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor of the object.
	 */
	public AddPurchaseReturnDetailServlet() {
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
	private PurchaseReturnDetailDao pdDao = new PurchaseReturnDetailDaoImpl();
	private PurchaseReturnDao pDao = new PurchaseReturnDaoImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String purchaseReturnPageNo = request.getParameter("purchaseReturnPageNo");
		request.getSession().setAttribute("purchaseReturnPageNo", purchaseReturnPageNo);
		PurchaseReturnDetail pd = new PurchaseReturnDetail();
		pd.setCode(request.getParameter("dcode"));
		String ctcode = request.getParameter("ctcode");
		pd.setCtcode(ctcode);
		pd.setRkcode(request.getParameter("rkcode"));
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
		pd.setRemarks(request.getParameter("remarks"));
		pdService.insertPurchaseReturnDetail(pd);
		double sumMoney = pdDao.getNumsPrice(ctcode);
		String code = ctcode;
		pDao.updateNumsPrice(sumMoney, code);
		response.sendRedirect("/jereh/purchase/purchaseReturn.jsp");
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
