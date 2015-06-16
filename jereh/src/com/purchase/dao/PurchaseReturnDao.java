package com.purchase.dao;

import com.common.entity.PageBean;
import com.purchase.entity.PurchaseReturn;

public interface PurchaseReturnDao {

	public PageBean getPurchaseReturn(String code, String beforeDate, String afterDate, String supplierCode, int pageNo, int pageSize);

	public int insertPurchaseReturn(PurchaseReturn p);
	
	public int deletePurchaseReturn(String code);
	
	public int deleteBatchPurchaseReturn(String[] codes);
	
	public int updatePurchaseReturn(PurchaseReturn p);
	
	public PurchaseReturn getPurchaseReturn(String code);
	
	public int updateNumsPrice(double sumMoney, String code);
	
}
