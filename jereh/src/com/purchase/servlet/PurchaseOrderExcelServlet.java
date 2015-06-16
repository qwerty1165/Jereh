package com.purchase.servlet;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
//import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.common.entity.PageBean;
import com.purchase.entity.PurchaseOrder;
import com.purchase.service.PurchaseOrderService;
import com.purchase.service.PurchaseOrderService;
import com.purchase.service.impl.PurchaseOrderServiceImpl;
import com.purchase.service.impl.PurchaseOrderServiceImpl;

public class PurchaseOrderExcelServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public PurchaseOrderExcelServlet() {
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
	private PurchaseOrderService pService = new PurchaseOrderServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String code = (String)request.getSession().getAttribute("purchaseOrderCode");
		String beforeDate = (String)request.getSession().getAttribute("purchaseOrderBeforeDate");
		String afterDate = (String)request.getSession().getAttribute("purchaseOrderAfterDate");
		String supplierCode = (String)request.getSession().getAttribute("purchaseOrderSupplierCode");
		
		String purchaseOrderPageNo = request.getParameter("purchaseOrderPageNo");
		request.getSession().setAttribute("purchaseOrderPageNo", purchaseOrderPageNo);
		
		response.setContentType("text/json; charset=utf-8");
		String pageNo = request.getParameter("page");
		String pageSize = request.getParameter("rows");
		if(pageNo == null || pageNo.equals("") || pageNo.equals("null"))
			pageNo = "1";
		if(pageSize == null || pageSize.equals("") || pageSize.equals("null"))
			pageSize = "5";
		PageBean pb = pService.getPurchaseOrder(code, beforeDate, afterDate, supplierCode, 
				Integer.parseInt(pageNo), Integer.parseInt(pageSize));//
		List<PurchaseOrder> pList = pb.getData(); 
		
		//EXCEL����
		String t = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());//�������ڸ�ʽ
		File file = new File("f://�ɹ���������" + t + ".xls");
		
		//����excl�ļ�
		WritableWorkbook wk = Workbook.createWorkbook(file); 
		WritableSheet sheet = wk.createSheet("�ֵ���Ϣ��",0);	//sheet����		
		//����WritableCellFormat���󣬽��ö���Ӧ���ڵ�Ԫ��Ӷ����õ�Ԫ�����ʽ
		
		WritableFont font = new WritableFont(//����WritableFont������󣬲������α�ʾ���塢�ֺ�12�����塢��б�塢�����»��ߡ��Զ�ɫ	
				WritableFont.createFont("����"),12,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.AUTOMATIC);
		WritableCellFormat titleFormate = new WritableCellFormat(font);//���ɱ������ʽ
		WritableCellFormat cellFormate = new WritableCellFormat();//������ݵĵ���ʽ	
        try {
        	sheet.mergeCells(0, 0, 8, 0);//�ϲ���һ�е�Ԫ��9�У���Ϊ����
			titleFormate.setAlignment(jxl.format.Alignment.CENTRE);//��Ԫ���е�����ˮƽ�������
			titleFormate.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//��Ԫ������ݴ�ֱ�������
			sheet.setRowView(0, 500, false);//���õ�һ�еĸ߶�
			
			//������
			Label title = new Label(0,0,"ѯ�۵��ݱ���",titleFormate);
			sheet.addCell(title);
			cellFormate.setAlignment(Alignment.CENTRE);
			cellFormate.setWrap(true);//�����Զ�����
			//�������
			sheet.addCell(new Label(0,1,"�������",cellFormate));
			sheet.addCell(new Label(1,1,"��������",cellFormate));
			sheet.addCell(new Label(2,1,"��Ӧ����",cellFormate));
			sheet.addCell(new Label(3,1,"����",cellFormate));
			sheet.addCell(new Label(4,1,"���",cellFormate));
			sheet.addCell(new Label(5,1,"��ϵ��",cellFormate));
			sheet.addCell(new Label(6,1,"��ϵ��ʽ",cellFormate));
			sheet.addCell(new Label(7,1,"���״̬",cellFormate));
			sheet.addCell(new Label(8,1,"����Ա",cellFormate));
		} catch (WriteException e1) {			
			e1.printStackTrace();
		}
        
		//���Label����
        sheet.setColumnView(0, 20);
        sheet.setColumnView(1, 15);
        sheet.setColumnView(2, 15);
        sheet.setColumnView(3, 10);
        sheet.setColumnView(4, 10);
        sheet.setColumnView(5, 10);
        sheet.setColumnView(6, 10);
        sheet.setColumnView(7, 10);
        sheet.setColumnView(8, 10);
		for(int i = 0; i < pList.size(); i++){
			PurchaseOrder p = pList.get(i);
			try {
				sheet.addCell(new Label(0,i+2,p.getCode(),cellFormate));
				sheet.addCell(new Label(1,i+2,new SimpleDateFormat("yyyy��MM��dd��").format(p.getAddDate()),cellFormate));
				sheet.addCell(new Label(2,i+2,p.getSupplierCode(),cellFormate));
				sheet.addCell(new jxl.write.Number(3,i+2,p.getNums(),new WritableCellFormat(new NumberFormat("0"))));
				sheet.addCell(new jxl.write.Number(4,i+2,p.getNumsPrice(),new WritableCellFormat(new NumberFormat("0.00"))));
				sheet.addCell(new Label(5,i+2,p.getContacter(),cellFormate));
				sheet.addCell(new Label(6,i+2,p.getTelphone(),cellFormate));
				sheet.addCell(new Label(7,i+2,p.getState(),cellFormate));	
				sheet.addCell(new Label(8,i+2,p.getAddUser(),cellFormate));	
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
		response.sendRedirect("/jereh/purchase/purchaseOrder.jsp");
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

		this.doGet(request, response);
	}

}
