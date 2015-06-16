package com.sale.dao;

import java.util.List;

import com.sale.entity.SaleOrder_Detail;

public interface SaleOrder_DetailDao {
	
	public List<SaleOrder_Detail> findDetailByCode(String cCode);
	
	public  int insertSaleOrder_Detail(SaleOrder_Detail saleOrder_Detail);
}
