package com.sale.servlet;

import java.io.IOException;
import java.util.ArrayList;
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

import com.common.util.JSONDateProcessor;
import com.sale.entity.SaleReturnDetail;
import com.sale.service.SaleReturnDetailService;
import com.sale.service.impl.SaleReturnDetailServiceImpl;

public class GetSaleReturnDetailServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetSaleReturnDetailServlet() {
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
		response.setContentType("text/json; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String code=request.getParameter("code");
		List<SaleReturnDetail> saleReturnDetailList = new ArrayList<SaleReturnDetail>();
		SaleReturnDetailService saleReturnDetailService = new SaleReturnDetailServiceImpl();
		saleReturnDetailList = saleReturnDetailService
				.getSaleReturnDetailListByCode(code);

		JSONObject jsonObj = new JSONObject();
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(Date.class, new JSONDateProcessor(
				"yyyy-MM-dd"));
		Map attrs = new HashMap();
		attrs.put("rows", saleReturnDetailList);
		attrs.put("total", saleReturnDetailList.size());
		jsonObj.putAll(attrs, config);

		String data = jsonObj.toString();
		response.getWriter().println(data);

		// JSONArray jsonArray=JSONArray.fromObject(saleReturnDetailList);
		// String jsonChannelList=jsonArray.toString();
		// //String jsonChannelList=JSONArray.toJSONString(channelList);
		// System.out.println(jsonChannelList);
		// response.getWriter().println(jsonChannelList);
	}
}
