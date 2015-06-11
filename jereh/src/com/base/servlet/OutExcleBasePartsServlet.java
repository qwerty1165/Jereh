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
		//按照查询取得数据
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
		if(partsCategory!=null&&!partsCategory.equals("--请选择--")){
			bp.setPartsCategory(partsCategory);				
		}
		PageBean pageBean=basePartsService.findAll(bp,Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		List<BaseParts> bpList=pageBean.getData();
		

		//EXCL部分
		SimpleDateFormat df = new SimpleDateFormat("ddHHmmss");   //设置日期格式
		String t=df.format(new Date());                           //new Date()为获取当前系统时间
		File file = new File("F://配件信息表"+t+".xls");
		//建立excl文件
		WritableWorkbook wk = Workbook.createWorkbook(file); 
		WritableSheet sheet = wk.createSheet("配件信息表",0);	//sheet名称
		try {
			sheet.mergeCells(0,0,11,0);    //单元格合并方法
	//		sheet.autoSizeColumn(1, true);
		} catch (RowsExceededException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (WriteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//创建WritableCellFormat对象，将该对象应用于单元格从而设置单元格的样式
		WritableCellFormat style1 = new WritableCellFormat();
		WritableCellFormat style2 = new WritableCellFormat();
		//创建WritableFont字体对象，参数依次表示黑体、字号12、粗体、非斜体、不带下划线、亮蓝色	
		WritableFont font1 = new WritableFont(
				WritableFont.createFont("宋体"),10,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
		style1.setFont(font1);//设置字体格式
		WritableFont font2 = new WritableFont(
				WritableFont.createFont("宋体"),15,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
		style2.setFont(font2);//设置字体格式
		try {
			style2.setAlignment(Alignment.CENTRE);
		} catch (WriteException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
//		try {
//			style.setAlignment(Alignment.CENTRE);//设置文本水平居中对齐
//			style.setWrap(true);//设置自动换行
//		} catch (WriteException e) {			
//			e.printStackTrace();
//		}
//		
		//添加Label对象，参数依次表示在第一列，第一行，内容，使用的格式
		try {
			sheet.addCell(new Label(0,0,"配件信息表",style2));
			sheet.addCell(new Label(0,1,"配件件号",style1));
			sheet.addCell(new Label(1,1,"配件件号",style1));
			sheet.addCell(new Label(2,1,"配件名称",style1));
			sheet.addCell(new Label(3,1,"配件型号",style1));
			sheet.addCell(new Label(4,1,"配件新型号",style1));
			sheet.addCell(new Label(5,1,"配件品牌",style1));
			sheet.addCell(new Label(6,1,"配件类型",style1));
			sheet.addCell(new Label(7,1,"是否进口",style1));
			sheet.addCell(new Label(8,1,"是否纯正",style1));
			sheet.addCell(new Label(9,1,"显示状态",style1));
			sheet.addCell(new Label(10,1,"备注",style1));
			
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

		//操作完成时，关闭对象，释放占用的内存空间   
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
