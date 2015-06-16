package com.stock.service.impl;

import java.util.Date;
import java.util.List;

import com.common.entity.PageBean;
import com.stock.dao.StockInDao;
import com.stock.dao.StockInDetailDao;
import com.stock.dao.impl.StockInDaoImpl;
import com.stock.dao.impl.StockInDetailDaoImpl;
import com.stock.entity.StockIn;
import com.stock.entity.StockInDetail;
import com.stock.service.StockInService;

public class StockInServiceImpl implements StockInService {
	private StockInDao dao=new StockInDaoImpl();
	private StockInDetailDao detailDao=new StockInDetailDaoImpl();
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
		return dao.insert(in);//+detailDao.insertBatchDetail(in.getDetailList());
	}

	@Override
	public int updateStockIn(StockIn in) {		
		return dao.update(in);
	}

	@Override
	public List<StockInDetail> getStockInDetailList(String inCode) {		
		return detailDao.findStockInDetailList(inCode);
	}

	@Override
	public int deleteStockInDetail(String code) {		
		return detailDao.delete(code);
	}

}
