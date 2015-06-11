package com.stock.service.impl;

import java.util.List;

import com.stock.dao.StockInDetailDao;
import com.stock.dao.impl.StockInDetailDaoImpl;
import com.stock.entity.StockInDetail;
import com.stock.service.StockInDetailService;

public class StockInDetailServiceImpl implements StockInDetailService {
	private StockInDetailDao dao=new StockInDetailDaoImpl();
	@Override
	public List<StockInDetail> getStockInDetailList(String inCode) {	
		return dao.findStockInDetailList(inCode);
	}

}
