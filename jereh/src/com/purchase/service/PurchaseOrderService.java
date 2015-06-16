package com.purchase.service;

import com.common.entity.PageBean;
import com.purchase.entity.PurchaseOrder;

public interface PurchaseOrderService {
	
	public PageBean getPurchaseOrder(String code, String beforeDate, String afterDate, String supplierCode, int pageNo, int pageSize);
	
	public int deletePurchaseOrder(String code);
	
	public int insertPurchaseOrder(PurchaseOrder p);
	
	public int updatePurchaseOrder(PurchaseOrder p);
	
	public PurchaseOrder getPurchaseOrder(String code);
	
	public int deleteBatchPurchaseOrder(String[] codes);

}
