package com.purchase.dao;

import com.common.entity.PageBean;
import com.purchase.entity.PurchaseInQuery;

public interface PurchaseInQueryDao {
	
	public PageBean getPurchaseInQuery(String code, String beforeDate, String afterDate, String supplierCode, int pageNo, int pageSize);

	public int insertPurchaseInQuery(PurchaseInQuery p);
	
	public int deletePurchaseInQuery(String code);
	
	public int deleteBatchPurchaseInQuery(String[] codes);
	
	public int updatePurchaseInQuery(PurchaseInQuery p);
	
	public PurchaseInQuery getPurchaseInQuery(String code);
	
	public int updateNumsPrice(double sumMoney, String code);
	
}
