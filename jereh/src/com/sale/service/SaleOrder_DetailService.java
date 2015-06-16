package com.sale.service;

import java.util.List;

import com.sale.entity.SaleOrder_Detail;

public interface SaleOrder_DetailService {
	
	public List<SaleOrder_Detail> findDetailByCode(String code);
}
