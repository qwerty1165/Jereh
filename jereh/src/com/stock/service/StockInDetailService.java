package com.stock.service;

import java.util.List;

import com.stock.entity.StockInDetail;

public interface StockInDetailService {
	public List<StockInDetail> getStockInDetailList(String inCode);
}
