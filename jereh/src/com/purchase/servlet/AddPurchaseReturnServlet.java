package com.purchase.servlet;

import java.io.IOException;
//import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.purchase.dao.PurchaseReturnDao;
import com.purchase.dao.PurchaseReturnDetailDao;
import com.purchase.dao.impl.PurchaseReturnDaoImpl;
import com.purchase.dao.impl.PurchaseReturnDetailDaoImpl;
import com.purchase.entity.PurchaseReturn;
import com.purchase.entity.PurchaseReturnDetail;
import com.purchase.service.PurchaseReturnDetailService;
import com.purchase.service.PurchaseReturnService;
import com.purchase.service.impl.PurchaseReturnDetailServiceImpl;
import com.purchase.service.impl.PurchaseReturnServiceImpl;

public class AddPurchaseReturnServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor of the object.
	 */
	public AddPurchaseReturnServlet() {
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
	private PurchaseReturnService pService = new PurchaseReturnServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String purchaseReturnPageNo = request.getParameter("purchaseReturnPageNo");
		request.getSession().setAttribute("purchaseReturnPageNo", purchaseReturnPageNo);
		PurchaseReturn p = new PurchaseReturn();
		String code = "LiZX" + new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		p.setCode(code);
		p.setRdate(new Date());
		p.setSupplierCode(request.getParameter("supplierCode"));
		p.setContacter(request.getParameter("contacter"));
		p.setTelphone(request.getParameter("telphone"));
		p.setFax(request.getParameter("fax"));
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
		p.setCompCode(request.getParameter("compCode"));
		pService.insertPurchaseReturn(p);
		
		
		
		PurchaseReturnDetail pd = new PurchaseReturnDetail();
		pd.setCode(request.getParameter("dcode"));
		String ctcode = code;
		pd.setCtcode(ctcode);
		pd.setRkcode(request.getParameter("rkcode"));
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
		pd.setRemarks(request.getParameter("remarks"));
		pdService.insertPurchaseReturnDetail(pd);
		double sumMoney = pdDao.getNumsPrice(ctcode);
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
