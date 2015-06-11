package com.base.servlet;

import javax.servlet.http.HttpServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.base.entity.BasePartsCategory;
import com.base.service.BasePartsCategoryService;
import com.base.service.impl.BasePartsCategoryServiceImpl;
import com.common.util.DocumentHandler;

public class OutputWordBasePartsCategoryServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public OutputWordBasePartsCategoryServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	private BasePartsCategoryService basePartsCategoryService = new BasePartsCategoryServiceImpl();

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json; charset=utf-8");

		String code = request.getParameter("code");
		BasePartsCategory basePartsCategory = basePartsCategoryService
				.getBasePartsCategoryByCode(code);

		String pCode = basePartsCategory.getpCode();
		String cateGoryName = basePartsCategory.getCateGoryName();
		String isShow = basePartsCategory.getIsShow();
		String remarks = basePartsCategory.getRemarks();



		DocumentHandler documentHandler = new DocumentHandler();

		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put("code", code);

		if (pCode.equals("001")) {
			dataMap.put("pCode", "一级类别");
		} else if (pCode.equals("002")) {
			dataMap.put("pCode", "二级类别");
		} else if (pCode.equals("003")) {
			dataMap.put("pCode", "三级类别");
		} else {
			dataMap.put("pCode", "其他类别");
		}

		if (cateGoryName != null && !cateGoryName.equals("")) {
			dataMap.put("cateGoryName", cateGoryName);
		} else {
			dataMap.put("cateGoryName", "");
		}

		if (isShow.equals("1")) {
			dataMap.put("isShow", "显示");
		} else {
			dataMap.put("isShow", "隐藏");
		}

		if (remarks != null && !remarks.equals("")) {
			dataMap.put("remarks", remarks);
		} else {
			dataMap.put("remarks", "");
		}


//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
//				"yyyyMMddHHmmss");// 设置日期格式
//		String nameString = "BPCS" + simpleDateFormat.format(new Date());// new
																			// Date()为获取当前系统时间
		String docName="BPCS"+code+cateGoryName;
		documentHandler.createDoc(dataMap, "F://配件类别信息"
				+ docName + ".doc","BasePartsCategory.ftl");
		// response.sendRedirect("/jereh/base/baseParts.jsp");

	}

}
