package com.sale.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.common.entity.PageBean;
import com.common.util.JSONDateProcessor;
import com.sale.service.SaleReturnService;
import com.sale.service.impl.SaleReturnServiceImpl;

public class GetSaleReturnServlet extends HttpServlet {
	SaleReturnService saleReturnService = new SaleReturnServiceImpl();

	/**
	 * Constructor of the object.
	 */
	public GetSaleReturnServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
		;
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/json; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");

		String pageNo = request.getParameter("page");
		String pageSize = request.getParameter("rows");

		if (pageNo == null || pageNo.equals("")) {
			pageNo = "1";
		}
		if (pageSize == null || pageSize.equals("")) {
			pageSize = "10";
		}

		PageBean pageBean = saleReturnService.getSaleReturnList(
				Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		JSONObject jsonObj = new JSONObject();

		// JSON���ã������ų�����Ҫת��Ϊjson���ݵ����ԡ�����ʵ���е����ĸ����Ծ���ת��
		JsonConfig config = new JsonConfig();
		// config.setExcludes(new String[]{});
		/** Date���� */
		config.registerJsonValueProcessor(Date.class,// �������Date����
				new JSONDateProcessor("yyyy-MM-dd"));
		// JSONObject����json���ã�������ֵװ��
		Map attrs = new HashMap();
		attrs.put("rows", pageBean.getData());
		attrs.put("total", pageBean.getRecordCount());
		jsonObj.putAll(attrs, config);

		String data = jsonObj.toString();
		response.getWriter().println(data);
	}
}
