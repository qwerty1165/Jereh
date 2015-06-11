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
import com.base.entity.BaseContent;
import com.base.service.BaseContentService;
import com.base.service.impl.BaseContentServiceImpl;
import com.common.entity.PageBean;
public class OutputExcelBaseContentServlet extends HttpServlet {
	private BaseContentService service=new BaseContentServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("����excel");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json; charset=utf-8");
		//���ղ�ѯȡ������
		String pageNo=request.getParameter("page");
		String pageSize=request.getParameter("rows");
		if(pageNo==null||pageNo.equals("")){
			pageNo="1";
		}
		if(pageSize==null||pageSize.equals("")){
			pageSize="10";
		}
		BaseContent bcs=new BaseContent();
		String code=request.getParameter("code");
		String codeName=request.getParameter("name");
		String categoryCode=new String(request.getParameter("categoryCode").getBytes("iso8859-1"),"utf-8");
		if(code!=null&&!code.equals("")){
			bcs.setCode(code);			
		}
		if(codeName!=null&&!codeName.equals("")){		
			bcs.setCodeName(codeName);
		}
		if(categoryCode!=null&&!categoryCode.equals("--ѡ�����--")){
			bcs.setCategoryCode(categoryCode);				
		}	
		PageBean pb=service.getListByCondition(bcs,Integer.valueOf(pageNo), Integer.valueOf(pageSize));
		List<BaseContent> list=pb.getData();	
		
		
		//EXCL����
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//�������ڸ�ʽ
		String t=df.format(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ��
		File file = new File("f://�ֵ�������Ϣ��"+t+".xls");
		
		
		//����excl�ļ�
		WritableWorkbook wk = Workbook.createWorkbook(file); 
		WritableSheet sheet = wk.createSheet("�ֵ���Ϣ��",0);	//sheet����		
		//����WritableCellFormat���󣬽��ö���Ӧ���ڵ�Ԫ��Ӷ����õ�Ԫ�����ʽ
			
		WritableFont font = new WritableFont(//����WritableFont������󣬲������α�ʾ���塢�ֺ�12�����塢��б�塢�����»��ߡ��Զ�ɫ	
				WritableFont.createFont("����"),12,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.AUTOMATIC);
		WritableCellFormat titleFormate = new WritableCellFormat(font);//���ɱ������ʽ
		WritableCellFormat cellFormate = new WritableCellFormat();//������ݵĵ���ʽ	
        try {
        	sheet.mergeCells(0, 0, 7, 0);//�ϲ���һ�е�Ԫ��8�У� ��Ϊ����       	
			titleFormate.setAlignment(jxl.format.Alignment.CENTRE);//��Ԫ���е�����ˮƽ�������
			titleFormate.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//��Ԫ������ݴ�ֱ�������
			sheet.setRowView(0, 500, false);//���õ�һ�еĸ߶�
			
			//������
			Label title = new Label(0,0,"�ֵ����ݱ���",titleFormate);
			sheet.addCell(title);
			cellFormate.setAlignment(Alignment.CENTRE);
			cellFormate.setWrap(true);//�����Զ�����
			//�������
			sheet.addCell(new Label(0,1,"�������",cellFormate));
			sheet.addCell(new Label(1,1,"�ֵ���",cellFormate));
			sheet.addCell(new Label(2,1,"�ֵ�����",cellFormate));
			sheet.addCell(new Label(3,1,"��˾����",cellFormate));
			sheet.addCell(new Label(4,1,"������",cellFormate));
			sheet.addCell(new Label(5,1,"��ע",cellFormate));
			sheet.addCell(new Label(6,1,"����Ա",cellFormate));
			sheet.addCell(new Label(7,1,"��ʾ״̬",cellFormate));			
		} catch (WriteException e1) {			
			e1.printStackTrace();
		}
        
        
		//���Label����
		for(int i = 0; i < list.size(); i++){
			BaseContent bc = list.get(i);
			try {
				sheet.setColumnView(i,15);
				sheet.addCell(new Label(0,i+2,bc.getCategoryCode(),cellFormate));
				sheet.addCell(new Label(1,i+2,bc.getCode(),cellFormate));
				sheet.addCell(new Label(2,i+2,bc.getCodeName(),cellFormate));
				sheet.addCell(new Label(3,i+2,bc.getCompName(),cellFormate));
				sheet.addCell(new Label(4,i+2,bc.getOrderNo(),cellFormate));
				sheet.addCell(new Label(5,i+2,bc.getRemarks(),cellFormate));
				sheet.addCell(new Label(6,i+2,bc.getAddUser(),cellFormate));
				sheet.addCell(new Label(7,i+2,bc.getIsShow(),cellFormate));	
				
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
		response.sendRedirect("/jereh/base/base_content.jsp");
	}

}
