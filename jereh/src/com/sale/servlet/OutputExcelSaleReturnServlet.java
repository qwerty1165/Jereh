package com.sale.servlet;

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

import com.common.entity.PageBean;
import com.sale.entity.SaleReturn;
import com.sale.service.SaleReturnService;
import com.sale.service.impl.SaleReturnServiceImpl;

public class OutputExcelSaleReturnServlet extends HttpServlet {
	SaleReturnService saleReturnService = new SaleReturnServiceImpl();

	/**
	 * Constructor of the object.
	 */
	public OutputExcelSaleReturnServlet() {
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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

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
		List<SaleReturn> SaleReturnList = pageBean.getData();
		// EXCL����
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");// �������ڸ�ʽ
		String t = df.format(new Date()); // new Date()Ϊ��ȡ��ǰϵͳʱ��
		File file = new File("f://���˵�����" + t + ".xls");
		// ����excel�ļ�
		WritableWorkbook wk = Workbook.createWorkbook(file);
		WritableSheet sheet = wk.createSheet("���˵�����", 0); // sheet����
		try {
			sheet.mergeCells(0, 0, 7, 0); // ��Ԫ��ϲ�����
			// sheet.autoSizeColumn(1, true);
		} catch (RowsExceededException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (WriteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// ����WritableCellFormat���󣬽��ö���Ӧ���ڵ�Ԫ��Ӷ����õ�Ԫ�����ʽ
		WritableCellFormat style1 = new WritableCellFormat();
		WritableCellFormat style2 = new WritableCellFormat();
		// ����WritableFont������󣬲������α�ʾ���塢�ֺ�12�����塢��б�塢�����»��ߡ�����ɫ
		WritableFont font1 = new WritableFont(WritableFont.createFont("����"),
				10, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
				Colour.BLACK);
		style1.setFont(font1);// ���������ʽ
		WritableFont font2 = new WritableFont(WritableFont.createFont("����"),
				15, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
				Colour.BLACK);
		style2.setFont(font2);// ���������ʽ
		try {
			style2.setAlignment(Alignment.CENTRE);
		} catch (WriteException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		// try {
		// style.setAlignment(Alignment.CENTRE);//�����ı�ˮƽ���ж���
		// style.setWrap(true);//�����Զ�����
		// } catch (WriteException e) {
		// e.printStackTrace();
		// }
		//
		// ���Label���󣬲������α�ʾ�ڵ�һ�У���һ�У����ݣ�ʹ�õĸ�ʽ
		try {
			sheet.addCell(new Label(0, 0, "��������Ϣ��", style2));
			sheet.addCell(new Label(0, 1, "���˱��", style1));
			sheet.addCell(new Label(1, 1, "��������", style1));
			sheet.addCell(new Label(2, 1, "�ͻ�����", style1));
			sheet.addCell(new Label(3, 1, "����", style1));
			sheet.addCell(new Label(4, 1, "���˽��", style1));
			sheet.addCell(new Label(5, 1, "��ϵ��", style1));
			sheet.addCell(new Label(6, 1, "��ϵ��ʽ", style1));
			sheet.addCell(new Label(7, 1, "���״̬", style1));
		} catch (RowsExceededException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (WriteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String date = null;
		for (int i = 0; i < SaleReturnList.size(); i++) {
			SaleReturn saleReturn = SaleReturnList.get(i);
			try {
				sheet.addCell(new Label(0, i + 2, saleReturn.getCode(), style1));
				
//				if(!saleReturn.getXtDate().equals("")){
//					date = format1.format(saleReturn.getXtDate());
//					sheet.addCell(new Label(1, i + 2, date, style1));
//				}
//				
//				sheet.addCell(new Label(2, i + 2, saleReturn.getCustomerCode(),
//						style1));
//				
//				sheet.addCell(new Label(3, i + 2, Integer.toString(saleReturn
//						.getNums()), style1));
//				
//				sheet.addCell(new Label(4, i + 2, Double.toString(saleReturn
//						.getNumSprice()), style1));
//
//				sheet.addCell(new Label(5, i + 2, saleReturn.getContacter(),
//						style1));
//				
//				sheet.addCell(new Label(6, i + 2, saleReturn.getTelPhone(),
//						style1));
//
//				if (saleReturn.getState().equals("1")) {
//					sheet.addCell(new Label(7, i + 2, "�����", style1));
//				} else {
//					sheet.addCell(new Label(7, i + 2, "δ���", style1));
//				}

			} catch (RowsExceededException e) {
				e.printStackTrace();
			} catch (WriteException e) {
				e.printStackTrace();
			}
		}
		wk.write();

		// �������ʱ���رն����ͷ�ռ�õ��ڴ�ռ�
		try {
			wk.close();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		response.sendRedirect("/jereh/sale/SaleReturn.jsp");

	}
}
