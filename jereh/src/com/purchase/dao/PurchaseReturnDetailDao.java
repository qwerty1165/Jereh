package com.purchase.dao;

import java.util.List;

import com.purchase.entity.PurchaseReturnDetail;

public interface PurchaseReturnDetailDao {

	public List<PurchaseReturnDetail> getPurchaseReturnDetail(String ocode);
	
	public int deletePurchaseReturnDetail(String dcode);
	
	public double getNumsPrice(String ocode);
	
	public int insertPurchaseReturnDetail(PurchaseReturnDetail pd);
	
}
