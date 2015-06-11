package com.base.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.base.entity.BaseCustomerSupplier;
import com.base.service.BaseCustomerSupplierService;
import com.base.service.impl.BaseCustomerSupplierServiceImpl;
import com.common.util.DocumentHandler;

public class OutputWordBaseCustomerSupplierServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public OutputWordBaseCustomerSupplierServlet() {
		super();
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
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
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/json; charset=utf-8");
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String time = sdf.format(date);
		
		String code = request.getParameter("code");

		BaseCustomerSupplierService customerDaoImpl = new BaseCustomerSupplierServiceImpl();
		BaseCustomerSupplier bcs = customerDaoImpl.findBaseCustomerSupplier(code);
		String addDate = new SimpleDateFormat("yyyy年MM月dd日").format(bcs.getAddDate());
		String csName = bcs.getCsName();
		String contacter = bcs.getContacter();
		String telephone = bcs.getTelephone();
		String fax = bcs.getFax();
		String postCode = bcs.getPostCode();
		String email = bcs.getEmail();
		String province = bcs.getPostCode();
		String city = bcs.getCity();
		String address = bcs.getAddress();
		String legaler = bcs.getLegaler();
		String url = bcs.getUrl();
		String QQ = bcs.getQQ();
		String MSN = bcs.getMSN();
		String aliwang = bcs.getAliwang();
		String agent = bcs.getAgent();
		String bank = bcs.getBank();
		String account = bcs.getAccount();
		String tax = bcs.getTax();
		String categorycode = bcs.getCategorycode();
		String isShow = bcs.getIsShow();
		String remarks = bcs.getRemarks();
		 Map<String, Object> dataMap = new HashMap<String, Object>(); 		 
		 dataMap.put("code", code);
		 dataMap.put("addDate", addDate);
		 if(csName!=null&&!csName.equals("")){			 
			 dataMap.put("csName", csName);
		 }else{
			 dataMap.put("csName", "");
		 }
		 if(contacter!=null&&!contacter.equals("")){			 
			 dataMap.put("contacter", contacter);
		 }else{
			 dataMap.put("contacter", "");
		 }
		 if(telephone!=null&&!telephone.equals("")){			 
			 dataMap.put("telephone", telephone);
		 }else{
			 dataMap.put("telephone", "");
		 }
		 if(fax!=null&&!fax.equals("")){			 
			 dataMap.put("fax", fax);
		 }else{
			 dataMap.put("fax", "");
		 }
		 if(postCode!=null&&!postCode.equals("")){			 
			 dataMap.put("postCode", postCode);
		 }else{
			 dataMap.put("postCode", "");
		 }
		 if(email!=null&&!email.equals("")){			 
			 dataMap.put("email", email);
		 }else{
			 dataMap.put("email", "");
		 }
		 if(province!=null&&!province.equals("")){			 
			 dataMap.put("province", province);
		 }else{
			 dataMap.put("province", "");
		 }
		 if(city!=null&&!city.equals("")){			 
			 dataMap.put("city", city);
		 }else{
			 dataMap.put("city", "");
		 }
		 if(address!=null&&!address.equals("")){			 
			 dataMap.put("address", address);
		 }else{
			 dataMap.put("address", "");
		 }
		 if(legaler!=null&&!legaler.equals("")){			 
			 dataMap.put("legaler", legaler);
		 }else{
			 dataMap.put("legaler", "");
		 }
		 if(url!=null&&!url.equals("")){			 
			 dataMap.put("url", url);
		 }else{
			 dataMap.put("url", "");
		 }
		 if(QQ!=null&&!QQ.equals("")){			 
			 dataMap.put("QQ", QQ);
		 }else{
			 dataMap.put("QQ", "");
		 }
		 if(MSN!=null&&!MSN.equals("")){			 
			 dataMap.put("MSN", MSN);
		 }else{
			 dataMap.put("MSN", "");
		 }
		 if(aliwang!=null&&!aliwang.equals("")){			 
			 dataMap.put("aliwang", aliwang);
		 }else{
			 dataMap.put("aliwang", "");
		 }
		 if(agent!=null&&!agent.equals("")){			 
			 dataMap.put("agent", agent);
		 }else{
			 dataMap.put("agent", "");
		 }
		 if(bank!=null&&!bank.equals("")){			 
			 dataMap.put("bank", bank);
		 }else{
			 dataMap.put("bank", "");
		 }
		 if(account!=null&&!account.equals("")){			 
			 dataMap.put("account", account);
		 }else{
			 dataMap.put("account", "");
		 }
		 if(tax!=null&&!tax.equals("")){			 
			 dataMap.put("tax", tax);
		 }else{
			 dataMap.put("tax", "");
		 }
		 if(categorycode!=null&&!categorycode.equals("")){	
			 if(isShow.equals("1")){
				 dataMap.put("categorycode", "客户");
			 }else{
				 dataMap.put("categorycode", "供应商");
			 }				
		 }else{
			 dataMap.put("categorycode", "");
		 }
		 if(isShow!=null&&!isShow.equals("")){
			 if(isShow.equals("1")){
				 dataMap.put("isShow", "显示");
			 }else{
				 dataMap.put("isShow", "隐藏");
			 }			
		 }else{
			 dataMap.put("isShow", "");
		 }
		 if(remarks!=null&&!remarks.equals("")){			 
			 dataMap.put("remarks", remarks);
		 }else{
			 dataMap.put("remarks", "");
		 }
		DocumentHandler doc = new DocumentHandler();
		doc.createDoc(dataMap,"F://客户往来信息"+time+".doc","DocumentHandler.ftl");
	}

}
