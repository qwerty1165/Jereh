package com.stock.service;

import java.util.Date;

import com.common.entity.PageBean;
import com.stock.entity.StockIn;

public interface StockInService {
	public PageBean getStockInList(String code,String supplierName,Date startDate,Date endDate, int pageNo,int pageSize);
 	public int deleteStockIn(String code);
 	public int insertStockIn(StockIn in);
 	public int updateStockIn(StockIn in);
}
