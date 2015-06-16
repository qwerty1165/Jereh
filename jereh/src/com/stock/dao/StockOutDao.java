package com.stock.dao;

import java.util.Date;
import com.common.entity.PageBean;
import com.stock.entity.StockOut;

public interface StockOutDao {
	public PageBean findByCondition(String code,String supplierCode,Date startDate,Date endDate, int pageNo,int pageSize);
 	public int delete(String code);
 	public int insert(StockOut out);
 	public int update(StockOut out);
}
