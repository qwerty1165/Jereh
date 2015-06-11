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
			dataMap.put("pCode", "һ�����");
		} else if (pCode.equals("002")) {
			dataMap.put("pCode", "�������");
		} else if (pCode.equals("003")) {
			dataMap.put("pCode", "�������");
		} else {
			dataMap.put("pCode", "�������");
		}

		if (cateGoryName != null && !cateGoryName.equals("")) {
			dataMap.put("cateGoryName", cateGoryName);
		} else {
			dataMap.put("cateGoryName", "");
		}

		if (isShow.equals("1")) {
			dataMap.put("isShow", "��ʾ");
		} else {
			dataMap.put("isShow", "����");
		}

		if (remarks != null && !remarks.equals("")) {
			dataMap.put("remarks", remarks);
		} else {
			dataMap.put("remarks", "");
		}


//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
//				"yyyyMMddHHmmss");// �������ڸ�ʽ
//		String nameString = "BPCS" + simpleDateFormat.format(new Date());// new
																			// Date()Ϊ��ȡ��ǰϵͳʱ��
		String docName="BPCS"+code+cateGoryName;
		documentHandler.createDoc(dataMap, "F://��������Ϣ"
				+ docName + ".doc","BasePartsCategory.ftl");
		// response.sendRedirect("/jereh/base/baseParts.jsp");

	}

}
