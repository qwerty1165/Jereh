package com.purchase.servlet;

import java.io.IOException;
//import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.purchase.dao.PurchaseInQueryDao;
import com.purchase.dao.PurchaseInQueryDetailDao;
import com.purchase.dao.impl.PurchaseInQueryDaoImpl;
import com.purchase.dao.impl.PurchaseInQueryDetailDaoImpl;
import com.purchase.entity.PurchaseInQuery;
import com.purchase.entity.PurchaseInQueryDetail;
import com.purchase.service.PurchaseInQueryDetailService;
import com.purchase.service.PurchaseInQueryService;
import com.purchase.service.impl.PurchaseInQueryDetailServiceImpl;
import com.purchase.service.impl.PurchaseInQueryServiceImpl;

public class AddPurchaseInQueryServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor of the object.
	 */
	public AddPurchaseInQueryServlet() {
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
	private PurchaseInQueryService pService = new PurchaseInQueryServiceImpl();
	private PurchaseInQueryDao pDao = new PurchaseInQueryDaoImpl();
	private PurchaseInQueryDetailService pdService = new PurchaseInQueryDetailServiceImpl();
	private PurchaseInQueryDetailDao pdDao = new PurchaseInQueryDetailDaoImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String purchaseInQueryPageNo = request.getParameter("purchaseInQueryPageNo");
		request.getSession().setAttribute("purchaseInQueryPageNo", purchaseInQueryPageNo);
		PurchaseInQuery p = new PurchaseInQuery();
		String code = "LiZX" + new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		p.setCode(code);
		p.setSupplierCode(request.getParameter("supplierCode"));
		p.setContacter(request.getParameter("contacter"));
		p.setTelphone(request.getParameter("telphone"));
		p.setFax(request.getParameter("fax"));
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
		p.setRemarks(request.getParameter("remarks"));
		pService.insertPurchaseInQuery(p);
		
		
		
		PurchaseInQueryDetail pd = new PurchaseInQueryDetail();
		pd.setDcode(request.getParameter("dcode"));
		String xcode = code;
		pd.setXcode(xcode);
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
		pd.setDeliveryMode(request.getParameter("deliveryDate"));
		pd.setRemarks(request.getParameter("remarks"));
		pdService.insertPurchaseInQueryDetail(pd);
		double sumMoney = pdDao.getNumsPrice(xcode);
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
