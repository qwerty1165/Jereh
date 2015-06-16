package com.purchase.dao;

import java.util.List;

import com.purchase.entity.PurchaseInQueryDetail;

public interface PurchaseInQueryDetailDao {
	
	public List<PurchaseInQueryDetail> getPurchaseInQueryDetail(String xcode);
	
	public int deletePurchaseInQueryDetail(String dcode);
	
	public double getNumsPrice(String xcode);
	
	public int insertPurchaseInQueryDetail(PurchaseInQueryDetail pd);
	
}
