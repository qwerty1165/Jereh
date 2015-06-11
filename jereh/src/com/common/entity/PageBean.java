package com.common.entity;

import java.util.List;

public class PageBean {
	private List data;
	private int recordCount;//记录总数	
	private int pageNo;		//当前页码
	private int pageSize;	//分页尺寸
	
	public List getData() { 
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
