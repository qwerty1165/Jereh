package com.purchase.service;

import com.common.entity.PageBean;
import com.purchase.entity.PurchaseReturn;

public interface PurchaseReturnService {

	public PageBean getPurchaseReturn(String code, String beforeDate, String afterDate, String supplierCode, int pageNo, int pageSize);
	
	public int deletePurchaseReturn(String code);
	
	public int insertPurchaseReturn(PurchaseReturn p);
	
	public int updatePurchaseReturn(PurchaseReturn p);
	
	public PurchaseReturn getPurchaseReturn(String code);
	
	public int deleteBatchPurchaseReturn(String[] codes);
	
}
