package com.stock.dao;
import java.util.Date;
import com.common.entity.PageBean;
import com.stock.entity.StockIn;
public interface StockInDao {
	public PageBean findByCondition(String code,String supplierName,Date startDate,Date endDate, int pageNo,int pageSize);	
	public int delete(String code);
 	public int insert(StockIn in);
 	public int update(StockIn in);
 	
}
