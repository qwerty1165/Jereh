package com.sale.dao;

import com.sale.entity.SaleOrder_Detail;

public interface SaleOrder_DetailDao {
	
	public SaleOrder_Detail findDetailByCode(String cCode);
	

}
