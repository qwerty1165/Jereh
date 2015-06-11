package com.stock.service.impl;

import java.util.Date;

import com.common.entity.PageBean;
import com.stock.dao.StockInDao;
import com.stock.dao.impl.StockInDaoImpl;
import com.stock.entity.StockIn;
import com.stock.service.StockInService;

public class StockInServiceImpl implements StockInService {
	private StockInDao dao=new StockInDaoImpl();
	@Override
	public PageBean getStockInList(String code, String supplierName,
			Date startDate, Date endDate, int pageNo, int pageSize) {		
		return dao.findByCondition(code, supplierName, startDate, endDate, pageNo, pageSize);
	}

	@Override
	public int deleteStockIn(String code) {		
		return dao.delete(code);
	}

	@Override
	public int insertStockIn(StockIn in) {		
		return dao.insert(in);
	}

	@Override
	public int updateStockIn(StockIn in) {		
		return dao.update(in);
	}

}
