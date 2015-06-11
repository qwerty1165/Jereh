package com.base.servlet;

import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.base.entity.BaseCustomerSupplier;
import com.base.service.BaseCustomerSupplierService;
import com.base.service.impl.BaseCustomerSupplierServiceImpl;
import com.common.entity.PageBean;
import com.common.util.JSONDateProcessor;

public class GetBaseCustomerSupplierServlet extends HttpServlet {

	public GetBaseCustomerSupplierServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	private BaseCustomerSupplierService customerService=new BaseCustomerSupplierServiceImpl();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json; charset=utf-8");
		
		String pageNo=request.getParameter("page");
		String pageSize=request.getParameter("rows");
		if(pageNo==null||pageNo.equals("")){
			pageNo="1";
		}
		if(pageSize==null||pageSize.equals("")){
			pageSize="5";
		}
		
		BaseCustomerSupplier bcs=new BaseCustomerSupplier();
		String code=request.getParameter("code");
		String csName=request.getParameter("csName");
		String addDate=request.getParameter("addDate");
		
		JsonConfig config=new JsonConfig();
		config.setExcludes(new String[]{});
		config.registerJsonValueProcessor(Date.class,
				new JSONDateProcessor("yyyyƒÍMM‘¬dd»’"));
		
		if(code!=null&&!code.equals("")){
			bcs.setCode(code);
		}
		if(csName!=null&&!csName.equals("")){
			bcs.setCsName(csName);
		}
		
		if(addDate!=null&&!addDate.equals("")){
			try {
				bcs.setAddDate(new SimpleDateFormat("yyyy-MM-dd").parse(addDate));
			} catch (ParseException e) {			
				e.printStackTrace();
			}
		}
		
		PageBean pb=customerService.findList(bcs,Integer.valueOf(pageNo), Integer.valueOf(pageSize));
		
		
		//System.out.println(pb.getData().size());
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
