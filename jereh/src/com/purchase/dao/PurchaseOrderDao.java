package com.purchase.dao;

import com.common.entity.PageBean;
import com.purchase.entity.PurchaseOrder;

public interface PurchaseOrderDao {
	
	public PageBean getPurchaseOrder(String code, String beforeDate, String afterDate, String supplierCode, int pageNo, int pageSize);

	public int insertPurchaseOrder(PurchaseOrder p);
	
	public int deletePurchaseOrder(String code);
	
	public int deleteBatchPurchaseOrder(String[] codes);
	
	public int updatePurchaseOrder(PurchaseOrder p);
	
	public PurchaseOrder getPurchaseOrder(String code);
	
	public int updateNumsPrice(double sumMoney, String code);

}
