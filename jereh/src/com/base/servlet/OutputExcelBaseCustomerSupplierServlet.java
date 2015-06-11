package com.base.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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

import com.base.entity.BaseCustomerSupplier;
import com.base.service.BaseCustomerSupplierService;
import com.base.service.impl.BaseCustomerSupplierServiceImpl;
import com.common.entity.PageBean;


public class OutputExcelBaseCustomerSupplierServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public OutputExcelBaseCustomerSupplierServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}
    BaseCustomerSupplierService customerService = new BaseCustomerSupplierServiceImpl();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//���ղ�ѯȡ������
		String pageNo = request.getParameter("page");
		String pageSize = request.getParameter("row");
			if (pageNo==null||pageNo.equals("")){
				pageNo="1";
			}
			if (pageSize==null||pageSize.equals("")){
				pageSize="5";
			}
			BaseCustomerSupplier bcs=new BaseCustomerSupplier();
			String code=request.getParameter("code");
			String csName=request.getParameter("csName");
			String addDate=request.getParameter("addDate");
			if(code!=null&&!code.equals("")){
				bcs.setCode(code);
			}
			if(csName!=null&&!csName.equals("")){
				bcs.setCsName(csName);
			}
			PageBean pb = customerService.findList(bcs, Integer.valueOf(pageNo), Integer.valueOf(pageSize));
			List bcsList = pb.getData();
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String time = sdf.format(date);

		File file = new File("F://�ͻ��빩Ӧ�̱�"+time+".xls");
		WritableWorkbook wk = Workbook.createWorkbook(file);
		WritableSheet sheet = wk.createSheet("�ͻ��빩Ӧ�̱���", 0);
		//������ͷ
		try {
			sheet.mergeCells(0, 0, 6, 0);
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//����һ����Ԫ����ʽ���ƶ��� ���õ�Ԫ�����ʽ
		WritableCellFormat titleFormat = new WritableCellFormat();
		//����WritableFont������� 
		WritableFont titleFont = new WritableFont(
				WritableFont.createFont("����"),12,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
		//���������ʽ
		titleFormat.setFont(titleFont);
		//�����ı�ˮƽ��Ȼ
		try {
			titleFormat.setAlignment(Alignment.CENTRE);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		//�����ı���ֱ���ж���
		try {
			titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//�����Զ�����
		try {
			titleFormat.setWrap(true);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//���Label���� �������α�ʾ�ڵ�һ�� ��һ�� ʹ�ø�ʽ
		Label lab_00  = new Label(0, 0, "�ͻ��빩Ӧ�̱���", titleFormat);
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
		cloumTitleFormat.setFont(new WritableFont(WritableFont.createFont("����"), 10, WritableFont.NO_BOLD,false));
		try {
			cloumTitleFormat.setAlignment(Alignment.CENTRE);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Label lab_01 = new Label(0,1,"����",cloumTitleFormat);
		Label lab_11 = new Label(1,1,"����",cloumTitleFormat);
		Label lab_21 = new Label(2,1,"���",cloumTitleFormat);
		Label lab_31 = new Label(3,1,"��ϵ��",cloumTitleFormat);
		Label lab_41 = new Label(4,1,"�绰", cloumTitleFormat);
		Label lab_51 = new Label(5,1,"��ַ",cloumTitleFormat);
		Label lab_61 = new Label(6,1,"��ʾ״̬",cloumTitleFormat);
		try {
			sheet.addCell(lab_01);
			sheet.addCell(lab_11);
			sheet.addCell(lab_21);
			sheet.addCell(lab_31);
			sheet.addCell(lab_41);
			sheet.addCell(lab_51);
			sheet.addCell(lab_61);		
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		for(int i =0 ;i<bcsList.size();i++){
			BaseCustomerSupplier baseCustomerSupplier = (BaseCustomerSupplier) bcsList.get(i);	
				try {
					sheet.addCell(new Label(0,i+2,baseCustomerSupplier.getCode(),cloumTitleFormat));
					sheet.addCell(new Label(1,i+2,baseCustomerSupplier.getCsName(),cloumTitleFormat));
					if(baseCustomerSupplier.getCategorycode()!=null&&baseCustomerSupplier.getCategorycode().equals("1")){
						sheet.addCell(new Label(2,i+2,"�ͻ�",cloumTitleFormat));
					}else if(baseCustomerSupplier.getCategorycode()!=null&&baseCustomerSupplier.getCategorycode().equals("2")){
						sheet.addCell(new Label(2,i+2,"��Ӧ��",cloumTitleFormat));
					}else if(baseCustomerSupplier.getCategorycode()==null){
						sheet.addCell(new Label(2,i+2,"",cloumTitleFormat));
					}
					
					sheet.addCell(new Label(3,i+2,baseCustomerSupplier.getContacter(),cloumTitleFormat));
					sheet.addCell(new Label(4,i+2,baseCustomerSupplier.getTelephone(),cloumTitleFormat));
					sheet.addCell(new Label(5,i+2,baseCustomerSupplier.getAddress(),cloumTitleFormat));
					
					if(baseCustomerSupplier.getIsShow()!=null&&baseCustomerSupplier.getIsShow().equals("1")){
						sheet.addCell(new Label(6,i+2,"��ʾ",cloumTitleFormat));
					}else if(baseCustomerSupplier.getIsShow()!=null&&baseCustomerSupplier.getIsShow().equals("0")){
						sheet.addCell(new Label(6,i+2,"����",cloumTitleFormat));
					}else if(baseCustomerSupplier.getIsShow()==null){
						sheet.addCell(new Label(6,i+2,"",cloumTitleFormat));
					}
					
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
		
	   response.sendRedirect("/jereh/base/BaseCustomerSupplier.jsp");
	}
	


}












