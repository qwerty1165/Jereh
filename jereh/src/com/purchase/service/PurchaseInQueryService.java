package com.purchase.service;

import com.common.entity.PageBean;
import com.purchase.entity.PurchaseInQuery;

public interface PurchaseInQueryService {
	
	public PageBean getPurchaseInQuery(String code, String beforeDate, String afterDate, String supplierCode, int pageNo, int pageSize);
	
	public int deletePurchaseInQuery(String code);
	
	public int insertPurchaseInQuery(PurchaseInQuery p);
	
	public int updatePurchaseInQuery(PurchaseInQuery p);
	
	public PurchaseInQuery getPurchaseInQuery(String code);
	
	public int deleteBatchPurchaseInQuery(String[] codes);
	
}
