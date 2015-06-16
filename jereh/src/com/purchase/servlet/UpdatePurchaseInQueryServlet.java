package com.purchase.servlet;

import java.io.IOException;
//import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.purchase.entity.PurchaseInQuery;
import com.purchase.service.PurchaseInQueryService;
import com.purchase.service.impl.PurchaseInQueryServiceImpl;

public class UpdatePurchaseInQueryServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor of the object.
	 */
	public UpdatePurchaseInQueryServlet() {
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
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String purchaseInQueryPageNo = request.getParameter("purchaseInQueryPageNo");
		request.getSession().setAttribute("purchaseInQueryPageNo", purchaseInQueryPageNo);
		PurchaseInQuery p = new PurchaseInQuery();
		p.setCode(request.getParameter("code"));
		try {
			p.setAddDate(new SimpleDateFormat("yyyyƒÍMM‘¬dd»’").parse(request.getParameter("addDate")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p.setSupplierCode(request.getParameter("supplierCode"));
		p.setContacter(request.getParameter("contacter"));
		p.setTelphone(request.getParameter("telphone"));
		p.setFax(request.getParameter("fax"));
		p.setIsShow(request.getParameter("isShow"));
		p.setNums(Integer.parseInt(request.getParameter("nums")));
		p.setNumsPrice(Double.parseDouble(request.getParameter("numsPrice")));
		p.setState(request.getParameter("state"));
		p.setAddUser(request.getParameter("addUser"));
		p.setAddUserName(request.getParameter("addUserName"));
		p.setAddIP(request.getParameter("addIP"));
		p.setCompCode(request.getParameter("compCode"));
		p.setRemarks(request.getParameter("remarks"));
		pService.updatePurchaseInQuery(p);
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
