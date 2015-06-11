package com.base.servlet;

import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadBasePartsServlet extends HttpServlet {
	
	 private static final long serialVersionUID = 1L; 
	 
	/**
	 * Constructor of the object.
	 */
	public DownloadBasePartsServlet() {
		super();
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		 //��������ļ���  
        String fileName = request.getParameter("fileName");  
        System.out.println(fileName);  
          
        //�����ļ�MIME����  
        response.setContentType(getServletContext().getMimeType(fileName)); 
        
        //����Content-Disposition  
        //�������ͻ��������������ļ�ʱ������������֧�ֵ��ļ����ͣ�һ���Ĭ��ʹ��������򿪣�����txt��jpg�ȣ���ֱ�������������ʾ��
        //�����Ҫ��ʾ�û����棬��Ҫ����Content-Disposition����һ�´����ؼ�����һ��Ҫ����attachment��
        
        response.setHeader("Content-Disposition", "attachment;filename="+fileName);  
       
        //��ȡĿ���ļ���ͨ��response��Ŀ���ļ�д���ͻ���  
        //��ȡĿ���ļ��ľ���·��  	
        
        String fullFileName = getServletContext().getRealPath("/download/" + fileName);        //System.out.println(fullFileName);  
        
        //��ȡ�ļ�  
        
        InputStream in = new FileInputStream(fullFileName);  
        OutputStream out = response.getOutputStream();  
          
        //д�ļ�  
        byte[] buff = new byte[2048];
        int b= 0;
        while(-1 != (b= in.read(buff , 0 , buff.length)))
        {
            out.write(buff , 0 , b);
        }
          
        in.close();  
        out.close();  
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
