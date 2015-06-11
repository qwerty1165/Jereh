package com.base.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.base.entity.BasePartsCategory;
import com.base.service.BasePartsCategoryService;
import com.base.service.impl.BasePartsCategoryServiceImpl;
import com.common.entity.PageBean;
import com.common.util.JSONDateProcessor;

public class GetBasePartsCategoryJsonServlet extends HttpServlet {
	private BasePartsCategoryService basePartScategoryService = new BasePartsCategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/json; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String pageNo = request.getParameter("page");
		String pageSize = request.getParameter("rows");
		String code = request.getParameter("code");
		String pCode = request.getParameter("pCode");
//		String code="002";
//		String pCode="";
		if (pageNo == null || pageNo.equals("")) {
			pageNo = "1";
		}
		if (pageSize == null || pageSize.equals("")) {
			pageSize = "10";
		}
		
		PageBean pageBean = basePartScategoryService.getBasePartsCategoryList(code,pCode,Integer.parseInt(pageNo),
				Integer.parseInt(pageSize));
		JSONObject jsonObj = new JSONObject();

		// JSON���ã������ų�����Ҫת��Ϊjson���ݵ����ԡ�����ʵ���е����ĸ����Ծ���ת��
		JsonConfig config = new JsonConfig();
		// config.setExcludes(new String[]{});
		/** Date���� */
		config.registerJsonValueProcessor(Date.class,// �������Date����
				new JSONDateProcessor());
		// JSONObject����json���ã�������ֵװ��
		Map attrs = new HashMap();
		attrs.put("rows", pageBean.getData());
		attrs.put("total", pageBean.getRecordCount());
		jsonObj.putAll(attrs, config);

		String data = jsonObj.toString();
		response.getWriter().println(data);

	}

}
