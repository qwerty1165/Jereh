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
		// EXCL部分
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");// 设置日期格式
		String t = df.format(new Date()); // new Date()为获取当前系统时间
		File file = new File("f://销退单报表" + t + ".xls");
		// 建立excel文件
		WritableWorkbook wk = Workbook.createWorkbook(file);
		WritableSheet sheet = wk.createSheet("销退单报表", 0); // sheet名称
		try {
			sheet.mergeCells(0, 0, 7, 0); // 单元格合并方法
			// sheet.autoSizeColumn(1, true);
		} catch (RowsExceededException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (WriteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// 创建WritableCellFormat对象，将该对象应用于单元格从而设置单元格的样式
		WritableCellFormat style1 = new WritableCellFormat();
		WritableCellFormat style2 = new WritableCellFormat();
		// 创建WritableFont字体对象，参数依次表示黑体、字号12、粗体、非斜体、不带下划线、亮蓝色
		WritableFont font1 = new WritableFont(WritableFont.createFont("宋体"),
				10, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
				Colour.BLACK);
		style1.setFont(font1);// 设置字体格式
		WritableFont font2 = new WritableFont(WritableFont.createFont("宋体"),
				15, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
				Colour.BLACK);
		style2.setFont(font2);// 设置字体格式
		try {
			style2.setAlignment(Alignment.CENTRE);
		} catch (WriteException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		// try {
		// style.setAlignment(Alignment.CENTRE);//设置文本水平居中对齐
		// style.setWrap(true);//设置自动换行
		// } catch (WriteException e) {
		// e.printStackTrace();
		// }
		//
		// 添加Label对象，参数依次表示在第一列，第一行，内容，使用的格式
		try {
			sheet.addCell(new Label(0, 0, "配件类别信息表", style2));
			sheet.addCell(new Label(0, 1, "销退编号", style1));
			sheet.addCell(new Label(1, 1, "销退日期", style1));
			sheet.addCell(new Label(2, 1, "客户名称", style1));
			sheet.addCell(new Label(3, 1, "数量", style1));
			sheet.addCell(new Label(4, 1, "销退金额", style1));
			sheet.addCell(new Label(5, 1, "联系人", style1));
			sheet.addCell(new Label(6, 1, "联系方式", style1));
			sheet.addCell(new Label(7, 1, "审核状态", style1));
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
				date = format1.format(saleReturn.getXtDate());
				sheet.addCell(new Label(1, i + 2, date, style1));
//				sheet.addCell(new Label(2, i + 2, saleReturn.getCustomerCode(),
//						style1));
//
//				sheet.addCell(new Label(3, i + 2, Integer.toString(saleReturn
//						.getNums()), style1));
//				sheet.addCell(new Label(4, i + 2, Double.toString(saleReturn
//						.getNumSprice()), style1));
//				sheet.addCell(new Label(1, i + 2,"123", style1));
//				sheet.addCell(new Label(2, i + 2, saleReturn.getTelPhone(), style1));
//				sheet.addCell(new Label(5, i + 2, saleReturn.getContacter(),
//						style1));
//				sheet.addCell(new Label(6, i + 2, saleReturn.getTelPhone(),
//						style1));

//				if (saleReturn.getState().equals("1")) {
//					sheet.addCell(new Label(7, i + 2, "已审核", style1));
//				} else {
//					sheet.addCell(new Label(7, i + 2, "未审核", style1));
//				}

			} catch (RowsExceededException e) {
				e.printStackTrace();
			} catch (WriteException e) {
				e.printStackTrace();
			}
		}
		wk.write();

		// 操作完成时，关闭对象，释放占用的内存空间
		try {
			wk.close();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		response.sendRedirect("/jereh/sale/SaleReturn.jsp");

	}
}
