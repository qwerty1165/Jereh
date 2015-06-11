package com.common.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class DocumentHandler {
	private Configuration configuration = null;  
	  
    public DocumentHandler() {  
        configuration = new Configuration();  
        configuration.setDefaultEncoding("utf-8");  
    }    
    public void createDoc(Map<String,Object> dataMap,String fileName,String url) throws UnsupportedEncodingException {  
        //dataMap Ҫ����ģ���������ļ�  
        //����ģ��װ�÷�����·��,FreeMarker֧�ֶ���ģ��װ�ط�����������servlet��classpath�����ݿ�װ�أ�  
        //�������ǵ�ģ���Ƿ���template������  
        configuration.setClassForTemplateLoading(this.getClass(), "/template"); //�ҵ���ǰ���µ�·�����µ�/templateĿ¼
        Template t=null;  
        try {  
            //test.ftlΪҪװ�ص�ģ��  
            t = configuration.getTemplate(url);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        //����ĵ�·��������  
        File outFile = new File(fileName);  
        Writer out = null;  
        FileOutputStream fos=null;  
        try {  
            fos = new FileOutputStream(outFile);  
            OutputStreamWriter oWriter = new OutputStreamWriter(fos,"UTF-8");  
            //����ط������ı��벻�ɻ�ȱ��ʹ��main������������ʱ��Ӧ�ÿ��ԣ����������web���󵼳�ʱ������word�ĵ��ͻ�򲻿������Ұ�XML�ļ�������Ҫ�Ǳ����ʽ����ȷ���޷�������  
            //out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));  
             out = new BufferedWriter(oWriter);   
        } catch (FileNotFoundException e1) {  
            e1.printStackTrace();  
        }            
        try {  
            t.process(dataMap, out);  
            out.close();  
            fos.close();  
        } catch (TemplateException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }      
    }  
    
 /*   public static void main(String[] args) {
    	DocumentHandler h=new DocumentHandler();
    	Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("categoryCode", "dsfd"); 
		dataMap.put("code", "dsfdf"); 
		dataMap.put("codeName", "dd"); 
		dataMap.put("orderNo", "d"); 
		dataMap.put("isShow", "d"); 
		dataMap.put("remarks", "d"); 
		
		try {
			h.createDoc(dataMap, "g:/outfile.doc");
		} catch (UnsupportedEncodingException e) {			
			e.printStackTrace();
		}
	}*/
}
