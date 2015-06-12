package com.sale.dao;

import java.util.List;

import com.sale.entity.SaleReturnDetail;

public interface SaleReturnDetailDao {
	public List<SaleReturnDetail> findAll();
	
	public List<SaleReturnDetail> findByCode(String code);
}
