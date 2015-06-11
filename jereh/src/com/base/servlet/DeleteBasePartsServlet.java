package com.base.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.base.service.BasePartsService;
import com.base.service.impl.BasePartsServiceImpl;

public class DeleteBasePartsServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DeleteBasePartsServlet() {
		super();
	}

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
	BasePartsService basePartsService=new BasePartsServiceImpl();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/json;charset=utf-8");
		String partsCode=request.getParameter("partsCode");
		
		int ret=basePartsService.deleteBaseParts(partsCode);
		
		response.getWriter().println(ret);
		//response.sendRedirect("/jereh/base/baseParts.jsp");
		
		
	}

}
