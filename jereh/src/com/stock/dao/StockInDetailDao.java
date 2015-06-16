package com.stock.dao;

import java.util.List;

import com.stock.entity.StockInDetail;

public interface StockInDetailDao {
	public List<StockInDetail> findStockInDetailList(String inCode);
	public int insertBatchDetail(List<StockInDetail> detailList);
	public int delete(String code);
}
