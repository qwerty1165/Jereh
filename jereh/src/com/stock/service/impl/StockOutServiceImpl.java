package com.stock.service.impl;

import java.util.Date;
import java.util.List;

import com.common.entity.PageBean;
import com.stock.dao.StockOutDao;
import com.stock.dao.StockOutDetailDao;
import com.stock.dao.impl.StockOutDaoImpl;
import com.stock.dao.impl.StockOutDetailDaoImpl;
import com.stock.entity.StockOut;
import com.stock.entity.StockOutDetail;
import com.stock.service.StockOutService;

public class StockOutServiceImpl implements StockOutService {
	private StockOutDao dao=new StockOutDaoImpl();
	private StockOutDetailDao detailDao=new StockOutDetailDaoImpl();
	@Override
	public PageBean getStockOutList(String code, String customerName,
			Date startDate, Date endDate, int pageNo, int pageSize) {		
		return dao.findByCondition(code, customerName, startDate, endDate, pageNo, pageSize);
	}

	@Override
	public int deleteStockOut(String code) {		
		return dao.delete(code);
	}

	@Override
	public int insertStockOut(StockOut out) {		
		return dao.insert(out);
	}

	@Override
	public int updateStockOut(StockOut out) {		
		return dao.update(out);
	}

	@Override
	public List<StockOutDetail> getStockOutDetailList(String outCode) {		
		return detailDao.findStockOutDetailList(outCode);
	}

}
