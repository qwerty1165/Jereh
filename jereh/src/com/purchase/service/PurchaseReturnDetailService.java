package com.purchase.service;

import java.util.List;

import com.purchase.entity.PurchaseReturnDetail;

public interface PurchaseReturnDetailService {

	public List<PurchaseReturnDetail> getPurchaseReturn(String ocode);
	
	public int deletePurchaseReturnDetail(String dcode);
	
	public int insertPurchaseReturnDetail(PurchaseReturnDetail pd);
	
}
