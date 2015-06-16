package com.purchase.service;

import java.util.List;

import com.purchase.entity.PurchaseInQueryDetail;

public interface PurchaseInQueryDetailService {
	
	public List<PurchaseInQueryDetail> getPurchaseInQuery(String xcode);
	
	public int deletePurchaseInQueryDetail(String dcode);
	
	public int insertPurchaseInQueryDetail(PurchaseInQueryDetail pd);
	
}
