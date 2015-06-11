package com.stock.dao;

import java.util.List;

import com.stock.entity.StockInDetail;

public interface StockInDetailDao {
	public List<StockInDetail> findStockInDetailList(String inCode);
}
