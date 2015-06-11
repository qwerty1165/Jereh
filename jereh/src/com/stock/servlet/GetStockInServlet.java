package com.stock.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import com.common.entity.PageBean;
import com.common.util.JSONDateProcessor;
import com.stock.entity.StockIn;
import com.stock.service.StockInService;
import com.stock.service.impl.StockInServiceImpl;

public class GetStockInServlet extends HttpServlet {
	private StockInService service=new StockInServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("type/json; charset=utf-8");		
		String pageNo=request.getParameter("page");
		String pageSize=request.getParameter("rows");
		if(pageNo==null||pageNo.equals("")){
			pageNo="1";
		}
		if(pageSize==null||pageSize.equals("")){
			pageSize="5";
		}
		String startDate=request.getParameter("startDate");
		Date date1=null;
		if(startDate!=null&&!startDate.equals("")){
			try {
				date1=new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
			} catch (ParseException e) {			
				e.printStackTrace();
			}
		}
		String endDate=request.getParameter("endDate");
		Date date2=null;
		if(endDate!=null&&!endDate.equals("")){
			try {
				date2=new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
			} catch (ParseException e) {			
				e.printStackTrace();
			}
		}
		String code=request.getParameter("code");
		String supplierName=request.getParameter("supplierName");
		PageBean pb=service.getStockInList(code, supplierName, date1, date2, Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		List<StockIn> list=pb.getData();		
		JsonConfig config=new JsonConfig();
		config.registerJsonValueProcessor(Date.class,
				new JSONDateProcessor("yyyy-MM-dd"));
		JSONObject jsonObject=new JSONObject();
		Map attrs=new HashMap();
		attrs.put("rows", pb.getData());
		attrs.put("total", pb.getRecordCount());
		jsonObject.putAll(attrs,config);	
		String data=jsonObject.toString();
		System.out.println(data);
        response.getWriter().println(data);		
		
	}

}
