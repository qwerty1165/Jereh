package com.common.util;

import java.text.SimpleDateFormat;

import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class JSONDateProcessor implements JsonValueProcessor {
	private SimpleDateFormat sdf=null;
	public JSONDateProcessor(){
		sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm");
	}
	public JSONDateProcessor(String fmt){
		sdf=new SimpleDateFormat(fmt);
	}

	public Object processObjectValue(String key, Object val, JsonConfig config) {
		
		// TODO Auto-generated method stub
		String ret="";
		if(val!=null){
			
			ret=sdf.format((Date)val);
		}
		return ret;
	}
	
	public Object processArrayValue(Object val, JsonConfig config) {
		// TODO Auto-generated method stub
		String ret="";
		if(val!=null){
			
			ret=sdf.format((Date)val);
		}
		return ret;
	
	}

}
