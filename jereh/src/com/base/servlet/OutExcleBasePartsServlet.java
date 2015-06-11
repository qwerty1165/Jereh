package com.base.servlet;

import java.io.File;
import java.io.IOException;
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
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.base.entity.BaseParts;
import com.base.service.BasePartsService;
import com.base.service.impl.BasePartsServiceImpl;
import com.common.entity.PageBean;

public class OutExcleBasePartsServlet extends HttpServlet {

	public OutExcleBasePartsServlet() {
		super();
	}
	private BasePartsService basePartsService=new BasePartsServiceImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//���ղ�ѯȡ������
		String pageNo=request.getParameter("page");
		String pageSize=request.getParameter("rows");
		
		if(pageNo==null||pageNo.equals("")){
			pageNo="1";
		}
		if(pageSize==null||pageSize.equals("")){
			pageSize="10";
		} 
		BaseParts bp=new BaseParts();
		String partsNo=request.getParameter("searchNo");
		String partsName=request.getParameter("name");
		String partsCategory=request.getParameter("typeOpt");
		
		if(partsNo!=null&&!partsNo.equals("")){
			bp.setPartsNo(partsNo);			
		}
		if(partsName!=null&&!partsName.equals("")){		
			bp.setPartsName(partsName);
		}
		if(partsCategory!=null&&!partsCategory.equals("--��ѡ��--")){
			bp.setPartsCategory(partsCategory);				
		}
		PageBean pageBean=basePartsService.findAll(bp,Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		List<BaseParts> bpList=pageBean.getData();
		

		//EXCL����
		SimpleDateFormat df = new SimpleDateFormat("ddHHmmss");   //�������ڸ�ʽ
		String t=df.format(new Date());                           //new Date()Ϊ��ȡ��ǰϵͳʱ��
		File file = new File("F://�����Ϣ��"+t+".xls");
		//����excl�ļ�
		WritableWorkbook wk = Workbook.createWorkbook(file); 
		WritableSheet sheet = wk.createSheet("�����Ϣ��",0);	//sheet����
		try {
			sheet.mergeCells(0,0,11,0);    //��Ԫ��ϲ�����
	//		sheet.autoSizeColumn(1, true);
		} catch (RowsExceededException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (WriteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//����WritableCellFormat���󣬽��ö���Ӧ���ڵ�Ԫ��Ӷ����õ�Ԫ�����ʽ
		WritableCellFormat style1 = new WritableCellFormat();
		WritableCellFormat style2 = new WritableCellFormat();
		//����WritableFont������󣬲������α�ʾ���塢�ֺ�12�����塢��б�塢�����»��ߡ�����ɫ	
		WritableFont font1 = new WritableFont(
				WritableFont.createFont("����"),10,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
		style1.setFont(font1);//���������ʽ
		WritableFont font2 = new WritableFont(
				WritableFont.createFont("����"),15,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
		style2.setFont(font2);//���������ʽ
		try {
			style2.setAlignment(Alignment.CENTRE);
		} catch (WriteException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
//		try {
//			style.setAlignment(Alignment.CENTRE);//�����ı�ˮƽ���ж���
//			style.setWrap(true);//�����Զ�����
//		} catch (WriteException e) {			
//			e.printStackTrace();
//		}
//		
		//���Label���󣬲������α�ʾ�ڵ�һ�У���һ�У����ݣ�ʹ�õĸ�ʽ
		try {
			sheet.addCell(new Label(0,0,"�����Ϣ��",style2));
			sheet.addCell(new Label(0,1,"�������",style1));
			sheet.addCell(new Label(1,1,"�������",style1));
			sheet.addCell(new Label(2,1,"�������",style1));
			sheet.addCell(new Label(3,1,"����ͺ�",style1));
			sheet.addCell(new Label(4,1,"������ͺ�",style1));
			sheet.addCell(new Label(5,1,"���Ʒ��",style1));
			sheet.addCell(new Label(6,1,"�������",style1));
			sheet.addCell(new Label(7,1,"�Ƿ����",style1));
			sheet.addCell(new Label(8,1,"�Ƿ���",style1));
			sheet.addCell(new Label(9,1,"��ʾ״̬",style1));
			sheet.addCell(new Label(10,1,"��ע",style1));
			
		} catch (RowsExceededException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (WriteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		for(int i = 0; i < bpList.size(); i++){
			BaseParts bp1 = bpList.get(i);
			try {
				
				sheet.addCell(new Label(0,i+2,bp1.getPartsCode(),style1));
				sheet.addCell(new Label(1,i+2,bp1.getPartsNo(),style1));
				sheet.addCell(new Label(2,i+2,bp1.getPartsName(),style1));
				sheet.addCell(new Label(3,i+2,bp1.getPartsModelOld(),style1));
				sheet.addCell(new Label(4,i+2,bp1.getPartsModel(),style1));
				sheet.addCell(new Label(5,i+2,bp1.getPartsBrand(),style1));
				sheet.addCell(new Label(6,i+2,bp1.getPartsCategory(),style1));
				sheet.addCell(new Label(9,i+2,bp1.getIsShow(),style1));
				sheet.addCell(new Label(10,i+2,bp1.getRemarks(),style1));

				
				
			} catch (RowsExceededException e) {				
				e.printStackTrace();
			} catch (WriteException e) {				
				e.printStackTrace();
			}
		}
		wk.write();

		//�������ʱ���رն����ͷ�ռ�õ��ڴ�ռ�   
		try {
			wk.close();
		} catch (WriteException e) {			
			e.printStackTrace();
		}
		response.sendRedirect("/jereh/base/baseParts.jsp");
		
		
	}	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
		
	}

}
