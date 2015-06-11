package com.base.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.base.entity.BasePartsCategory;
import com.base.service.BasePartsCategoryService;
import com.base.service.impl.BasePartsCategoryServiceImpl;

public class UpdataBasePartsCategoryServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UpdataBasePartsCategoryServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BasePartsCategoryService basePartcSategoryService = new BasePartsCategoryServiceImpl();

		response.setContentType("text/json; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");

		BasePartsCategory basePartcSategory = null;
		String opt = request.getParameter("opt");
		if (opt.equals("updata")) {
			String code = request.getParameter("code");
			String pCode = request.getParameter("pCode");
			String cateGoryName = request.getParameter("cateGoryName");
			String isShow = request.getParameter("isShow");
			String remarks = request.getParameter("remarks");

			// BasePartScategory
			// basePartScategory=basePartScategoryService.getBasePartScategoryByCode(code);
			basePartcSategory = new BasePartsCategory();

			basePartcSategory.setpCode(pCode);
			basePartcSategory.setCateGoryName(cateGoryName);
			basePartcSategory.setIsShow(isShow);
			basePartcSategory.setRemarks(remarks);

			basePartcSategoryService.updataBasePartsCategory(basePartcSategory,
					code);
		} else {
			String pCode = request.getParameter("pCode");
			String cateGoryName = request.getParameter("cateGoryName");
			String isShow = request.getParameter("isShow");
			String remarks = request.getParameter("remarks");

			basePartcSategory = new BasePartsCategory();

			basePartcSategory.setpCode(pCode);
			basePartcSategory.setCateGoryName(cateGoryName);
			basePartcSategory.setIsShow(isShow);
			basePartcSategory.setRemarks(remarks);
			// basePartcSategory.setCompCode(null);
			// basePartcSategory.setAddUser(null);
			// basePartcSategory.setAddUserName(null);
			// basePartcSategory.setAddIp(null);

			basePartcSategoryService.addBasePartsCategory(basePartcSategory);
		}
		response.sendRedirect("../base/BasePartsCategory.jsp");
	}
}
