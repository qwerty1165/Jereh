package com.base.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.base.entity.BaseContent;
import com.base.service.BaseContentService;
import com.base.service.impl.BaseContentServiceImpl;

public class UpdateBaseContentServlet extends HttpServlet {
	private BaseContentService service=new BaseContentServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String code=request.getParameter("code");
		String codeName=request.getParameter("codeName");		
		String categoryCode=request.getParameter("categoryCode");		
		String orderNo=request.getParameter("orderNo");		
		String isShow=request.getParameter("isShow");
		String remarks=request.getParameter("remarks");
		BaseContent bc=new BaseContent();
		bc.setCode(code);
		bc.setCodeName(codeName);
		bc.setCategoryCode(categoryCode);
		bc.setOrderNo(orderNo);
		bc.setIsShow(isShow);
		bc.setRemarks(remarks);
		String opt=request.getParameter("opt");
	
		if(opt.equals("1")&&opt!=null){
			service.insertBaseContent(bc);				
		}else if(opt.equals("2")&&opt!=null){			
			service.updateBaseContent(bc);				
		}
		response.sendRedirect("/jereh/base/base_content.jsp");	
		
	}

}
