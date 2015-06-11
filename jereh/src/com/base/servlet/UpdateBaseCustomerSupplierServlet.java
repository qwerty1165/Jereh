package com.base.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.base.entity.BaseCustomerSupplier;
import com.base.service.BaseCustomerSupplierService;
import com.base.service.impl.BaseCustomerSupplierServiceImpl;


public class UpdateBaseCustomerSupplierServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UpdateBaseCustomerSupplierServlet() {
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
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
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
	BaseCustomerSupplierService customerService=new BaseCustomerSupplierServiceImpl();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/jsp; charset=utf-8");

		String opt=request.getParameter("opt");
		BaseCustomerSupplier baseCustomerSupplier=new BaseCustomerSupplier();
		baseCustomerSupplier.setCode(request.getParameter("code"));
        baseCustomerSupplier.setCsName(request.getParameter("csName"));
        baseCustomerSupplier.setContacter(request.getParameter("contacter"));
        baseCustomerSupplier.setTelephone(request.getParameter("telephone"));
        baseCustomerSupplier.setFax(request.getParameter("fax"));
//        baseCustomerSupplier.setAddDate(new Date());
        baseCustomerSupplier.setPostCode(request.getParameter("postCode"));
        baseCustomerSupplier.setEmail(request.getParameter("email"));
        baseCustomerSupplier.setProvince(request.getParameter("province"));
        baseCustomerSupplier.setCity(request.getParameter("city"));
        baseCustomerSupplier.setAddress(request.getParameter("address"));
        baseCustomerSupplier.setLegaler(request.getParameter("legaler"));
        baseCustomerSupplier.setUrl(request.getParameter("url"));
        baseCustomerSupplier.setQQ(request.getParameter("QQ"));
        baseCustomerSupplier.setMSN(request.getParameter("MSN"));
        baseCustomerSupplier.setAliwang(request.getParameter("aliwang"));
        baseCustomerSupplier.setAgent(request.getParameter("agent"));
        baseCustomerSupplier.setBank(request.getParameter("bank"));
        baseCustomerSupplier.setAccount(request.getParameter("account"));
        baseCustomerSupplier.setTax(request.getParameter("tax"));
        baseCustomerSupplier.setCategorycode(request.getParameter("categorycode"));
        baseCustomerSupplier.setIsShow(request.getParameter("isShow"));
        baseCustomerSupplier.setRemarks(request.getParameter("remarks"));
        
		if(opt!=null&&opt.equals("1")){	
			customerService.addCustomer(baseCustomerSupplier);
			response.sendRedirect("/jereh/base/BaseCustomerSupplier.jsp");
		}
		else{
		//	String code = baseCustomerSupplier.getCode();
		//	customerService.findBaseCustomerSupplier(code);
			customerService.update(baseCustomerSupplier);
			response.sendRedirect("/jereh/base/BaseCustomerSupplier.jsp");
		}
	}

}
