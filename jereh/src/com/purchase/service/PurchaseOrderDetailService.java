package com.purchase.service;

import java.util.List;

import com.purchase.entity.PurchaseOrderDetail;

public interface PurchaseOrderDetailService {

	public List<PurchaseOrderDetail> getPurchaseOrder(String ocode);
	
	public int deletePurchaseOrderDetail(String dcode);
	
	public int insertPurchaseOrderDetail(PurchaseOrderDetail pd);
	
}
