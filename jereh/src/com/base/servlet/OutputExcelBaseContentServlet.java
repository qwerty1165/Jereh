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
		System.out.println("导出excel");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json; charset=utf-8");
		//按照查询取得数据
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
		if(categoryCode!=null&&!categoryCode.equals("--选择类别--")){
			bcs.setCategoryCode(categoryCode);				
		}	
		PageBean pb=service.getListByCondition(bcs,Integer.valueOf(pageNo), Integer.valueOf(pageSize));
		List<BaseContent> list=pb.getData();	
		
		
		//EXCL部分
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		String t=df.format(new Date());// new Date()为获取当前系统时间
		File file = new File("f://字典内容信息表"+t+".xls");
		
		
		//建立excl文件
		WritableWorkbook wk = Workbook.createWorkbook(file); 
		WritableSheet sheet = wk.createSheet("字典信息表",0);	//sheet名称		
		//创建WritableCellFormat对象，将该对象应用于单元格从而设置单元格的样式
			
		WritableFont font = new WritableFont(//创建WritableFont字体对象，参数依次表示黑体、字号12、粗体、非斜体、不带下划线、自动色	
				WritableFont.createFont("宋体"),12,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.AUTOMATIC);
		WritableCellFormat titleFormate = new WritableCellFormat(font);//生成标题的样式
		WritableCellFormat cellFormate = new WritableCellFormat();//表格内容的的样式	
        try {
        	sheet.mergeCells(0, 0, 7, 0);//合并第一行单元格（8列） 作为标题       	
			titleFormate.setAlignment(jxl.format.Alignment.CENTRE);//单元格中的内容水平方向居中
			titleFormate.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//单元格的内容垂直方向居中
			sheet.setRowView(0, 500, false);//设置第一行的高度
			
			//表格标题
			Label title = new Label(0,0,"字典内容报表",titleFormate);
			sheet.addCell(title);
			cellFormate.setAlignment(Alignment.CENTRE);
			cellFormate.setWrap(true);//设置自动换行
			//表格列名
			sheet.addCell(new Label(0,1,"所属类别",cellFormate));
			sheet.addCell(new Label(1,1,"字典编号",cellFormate));
			sheet.addCell(new Label(2,1,"字典内容",cellFormate));
			sheet.addCell(new Label(3,1,"公司名称",cellFormate));
			sheet.addCell(new Label(4,1,"排序编号",cellFormate));
			sheet.addCell(new Label(5,1,"备注",cellFormate));
			sheet.addCell(new Label(6,1,"操作员",cellFormate));
			sheet.addCell(new Label(7,1,"显示状态",cellFormate));			
		} catch (WriteException e1) {			
			e1.printStackTrace();
		}
        
        
		//添加Label对象
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
		//操作完成时，关闭对象，释放占用的内存空间   
		try {
			wk.close();
		} catch (WriteException e) {			
			e.printStackTrace();
		}
		response.sendRedirect("/jereh/base/base_content.jsp");
	}

}
