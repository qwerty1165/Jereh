package com.purchase.dao;

import java.util.List;

import com.purchase.entity.PurchaseOrderDetail;

public interface PurchaseOrderDetailDao {

	public List<PurchaseOrderDetail> getPurchaseOrderDetail(String ocode);
	
	public int deletePurchaseOrderDetail(String dcode);
	
	public double getNumsPrice(String ocode);
	
	public int insertPurchaseOrderDetail(PurchaseOrderDetail pd);
	
}
