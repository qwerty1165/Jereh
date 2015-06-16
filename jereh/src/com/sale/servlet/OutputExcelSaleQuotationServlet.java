package com.sale.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.common.entity.PageBean;
import com.sale.entity.SaleQuotation;
import com.sale.service.SaleQuotationService;
import com.sale.service.impl.SaleOrderServiceImpl;
import com.sale.service.impl.SaleQuotationServiceImpl;

public class OutputExcelSaleQuotationServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public OutputExcelSaleQuotationServlet() {
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
	SaleQuotationService saleQuotationService = new SaleQuotationServiceImpl();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//按照查询取得数据
		String pageNo = request.getParameter("page");
		String pageSize = request.getParameter("row");
			if (pageNo==null||pageNo.equals("")){
				pageNo="1";
			}
			if (pageSize==null||pageSize.equals("")){
				pageSize="5";
			}
			SaleQuotation saleQuotation = new SaleQuotation();
			String code = request.getParameter("searchcode");
			String startdate = request.getParameter("startDate");
			String enddate = request.getParameter("endDate");
			String csName = request.getParameter("searchcsName");
			if(code!=null&&!code.equals("")){
				saleQuotation.setCode(code);	
			}
			Date startDate=null;
			if(startdate!=null&&!startdate.equals("")){
				try {
					startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startdate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Date endDate=null;
			if(enddate!=null&&!enddate.equals("")){
				try {
					endDate = new SimpleDateFormat("yyyy-MM-dd").parse(enddate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(csName!=null&&!csName.equals("")){
				saleQuotation.setCsName(csName);
			}
			PageBean pb = saleQuotationService.findList(saleQuotation,startDate,endDate,Integer.parseInt(pageNo),Integer.parseInt(pageSize));
			List sqlist = pb.getData();
			
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
			String time = sdf.format(date);

			File file = new File("F://报价单"+time+".xls");
			WritableWorkbook wk = Workbook.createWorkbook(file);
			WritableSheet sheet = wk.createSheet("销售订单报表", 0);
			
			//创建表头
			try {
				sheet.mergeCells(0, 0, 8, 0);
			} catch (RowsExceededException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//生成一个单元格样式控制对象 设置单元格的样式
			WritableCellFormat titleFormat = new WritableCellFormat();
			//创建WritableFont字体对象 
			WritableFont titleFont = new WritableFont(
					WritableFont.createFont("黑体"),12,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
			//设置字体格式
			titleFormat.setFont(titleFont);
			//设置文本水平居然
			try {
				titleFormat.setAlignment(Alignment.CENTRE);
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			//设置文本垂直居中对齐
			try {
				titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//设置自动换行
			try {
				titleFormat.setWrap(true);
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//添加Label对象 参数依次表示在第一列 第一行 使用格式
			Label lab_00  = new Label(0, 0, "销售订单报表", titleFormat);
			try {
				sheet.addCell(lab_00);
			} catch (RowsExceededException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			WritableCellFormat cloumTitleFormat = new WritableCellFormat();
			cloumTitleFormat.setFont(new WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.NO_BOLD,false));
			try {
				cloumTitleFormat.setAlignment(Alignment.CENTRE);
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Label lab_01 = new Label(0,1,"报价单号",cloumTitleFormat);
			Label lab_11 = new Label(1,1,"报价日期",cloumTitleFormat);
			Label lab_21 = new Label(2,1,"客户名称",cloumTitleFormat);
			Label lab_31 = new Label(3,1,"数量",cloumTitleFormat);
			Label lab_41 = new Label(4,1,"总货值", cloumTitleFormat);
			Label lab_51 = new Label(5,1,"联系人",cloumTitleFormat);
			Label lab_61 = new Label(6,1,"联系方式",cloumTitleFormat);
			Label lab_71 = new Label(7,1,"审核状态",cloumTitleFormat);
			Label lab_81 = new Label(8,1,"操作员",cloumTitleFormat);
			try {
				sheet.addCell(lab_01);
				sheet.addCell(lab_11);
				sheet.addCell(lab_21);
				sheet.addCell(lab_31);
				sheet.addCell(lab_41);
				sheet.addCell(lab_51);
				sheet.addCell(lab_61);	
				sheet.addCell(lab_71);
				sheet.addCell(lab_81);
			} catch (RowsExceededException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(int i=2;i<sqlist.size()+2;i++){
				SaleQuotation sq = (SaleQuotation) sqlist.get(i-2);//new SaleQuotation();
				try {
					sheet.addCell(new Label(0,i,sq.getCode(),cloumTitleFormat));
					System.out.println(sq.getSqDate());
					sheet.addCell(new Label(1,i,new SimpleDateFormat("yyyy/MM/dd").format(sq.getSqDate()),cloumTitleFormat));//new SimpleDateFormat("yyyy/MM/dd").format(sq.getSqDate()),cloumTitleFormat)
					sheet.addCell(new Label(2,i,sq.getCsName(),cloumTitleFormat));
					sheet.addCell(new Label(3,i,sq.getNums()+"",cloumTitleFormat));
					sheet.addCell(new Label(4,i,sq.getNumsPrice()+"",cloumTitleFormat));
					sheet.addCell(new Label(5,i,sq.getContacter(),cloumTitleFormat));
					sheet.addCell(new Label(6,i,sq.getTelphone(),cloumTitleFormat));
					sheet.addCell(new Label(7,i,sq.getState(),cloumTitleFormat));
					sheet.addCell(new Label(8,i,sq.getAddUser(),cloumTitleFormat));
					
				} catch (RowsExceededException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		wk.write();
		try {
			wk.close();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	   response.sendRedirect("/jereh/sale/salequotation.jsp");		
			}
				
			
	}


