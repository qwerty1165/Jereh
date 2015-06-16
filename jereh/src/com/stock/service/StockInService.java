package com.stock.service;

import java.util.Date;
import java.util.List;

import com.common.entity.PageBean;
import com.stock.entity.StockIn;
import com.stock.entity.StockInDetail;

public interface StockInService {
	public PageBean getStockInList(String code,String supplierName,Date startDate,Date endDate, int pageNo,int pageSize);	
	public int deleteStockIn(String code);
 	public int insertStockIn(StockIn in);
 	public int updateStockIn(StockIn in);
 	public List<StockInDetail> getStockInDetailList(String inCode);
 	public int deleteStockInDetail(String code);
}
