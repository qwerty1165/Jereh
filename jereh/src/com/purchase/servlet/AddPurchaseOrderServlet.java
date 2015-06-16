package com.purchase.servlet;

import java.io.IOException;
//import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.purchase.dao.PurchaseOrderDao;
import com.purchase.dao.PurchaseOrderDetailDao;
import com.purchase.dao.impl.PurchaseOrderDaoImpl;
import com.purchase.dao.impl.PurchaseOrderDetailDaoImpl;
import com.purchase.entity.PurchaseOrder;
import com.purchase.entity.PurchaseOrderDetail;
import com.purchase.service.PurchaseOrderDetailService;
import com.purchase.service.PurchaseOrderService;
import com.purchase.service.impl.PurchaseOrderDetailServiceImpl;
import com.purchase.service.impl.PurchaseOrderServiceImpl;

public class AddPurchaseOrderServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor of the object.
	 */
	public AddPurchaseOrderServlet() {
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
	private PurchaseOrderDetailService pdService = new PurchaseOrderDetailServiceImpl();
	private PurchaseOrderDetailDao pdDao = new PurchaseOrderDetailDaoImpl();
	private PurchaseOrderDao pDao = new PurchaseOrderDaoImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String purchaseOrderPageNo = request.getParameter("purchaseOrderPageNo");
		request.getSession().setAttribute("purchaseOrderPageNo", purchaseOrderPageNo);
		PurchaseOrder p = new PurchaseOrder();
		String code = "LiZX" + new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		p.setCode(code);
		p.setOrderDate(new Date());
		p.setSupplierCode(request.getParameter("supplierCode"));
		p.setContacter(request.getParameter("contacter"));
		p.setTelphone(request.getParameter("telphone"));
		p.setFax(request.getParameter("fax"));
		p.setTrans(request.getParameter("trans"));
		p.setDeliveryDate(new Date());
		p.setBusinesser(request.getParameter("businesser"));
		p.setRemarks(request.getParameter("remarks"));
		p.setIsShow(request.getParameter("isShow"));
		String nums = request.getParameter("nums");
		if(nums != null && !nums.equals(""))
			p.setNums(Integer.parseInt(nums));
		else
			p.setNums(0);
		String numsPrice = request.getParameter("numsPrice");
		if(numsPrice != null && !numsPrice.equals(""))
			p.setNumsPrice(Double.parseDouble(numsPrice));
		else
			p.setNumsPrice(0.00);
		p.setState(request.getParameter("state"));
		p.setAddUser(request.getParameter("addUser"));
		p.setAddUserName(request.getParameter("addUserName"));
		p.setAddIP(request.getParameter("addIP"));
		pService.insertPurchaseOrder(p);
		
		
		
		PurchaseOrderDetail pd = new PurchaseOrderDetail();
		pd.setDcode(request.getParameter("dcode"));
		String ocode = code;
		pd.setOcode(ocode);
		pd.setXcode(request.getParameter("xcode"));
		pd.setPcode(request.getParameter("pcode"));
		String pdNums = request.getParameter("pdNums");
		if(pdNums == null || pdNums.equals(""))
			pd.setNums(0);
		else
			pd.setNums(Integer.parseInt(pdNums));
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
