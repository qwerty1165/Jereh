package com.stock.dao;

import java.util.List;
import com.stock.entity.StockOutDetail;

public interface StockOutDetailDao {
	public List<StockOutDetail> findStockOutDetailList(String outCode);
}
