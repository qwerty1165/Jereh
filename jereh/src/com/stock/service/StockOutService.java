package com.stock.service;

import java.util.Date;
import java.util.List;

import com.common.entity.PageBean;
import com.stock.entity.StockOut;
import com.stock.entity.StockOutDetail;

public interface StockOutService {
	public PageBean getStockOutList(String code,String customerName,Date startDate,Date endDate, int pageNo,int pageSize);	
	public int deleteStockOut(String code);
 	public int insertStockOut(StockOut out);
 	public int updateStockOut(StockOut out);
 	public List<StockOutDetail> getStockOutDetailList(String outCode);
}
