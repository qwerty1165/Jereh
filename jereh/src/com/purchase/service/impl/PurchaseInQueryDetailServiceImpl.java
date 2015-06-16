package com.purchase.service.impl;

import java.util.List;

import com.purchase.dao.PurchaseInQueryDetailDao;
import com.purchase.dao.impl.PurchaseInQueryDetailDaoImpl;
import com.purchase.entity.PurchaseInQueryDetail;
import com.purchase.service.PurchaseInQueryDetailService;

public class PurchaseInQueryDetailServiceImpl implements
		PurchaseInQueryDetailService {

	private PurchaseInQueryDetailDao pdDao = new PurchaseInQueryDetailDaoImpl();
	@Override
	public List<PurchaseInQueryDetail> getPurchaseInQuery(String xcode) {
		// TODO Auto-generated method stub
		return pdDao.getPurchaseInQueryDetail(xcode);
	}
	@Override
	public int deletePurchaseInQueryDetail(String dcode) {
		// TODO Auto-generated method stub
		return pdDao.deletePurchaseInQueryDetail(dcode);
	}
	@Override
	public int insertPurchaseInQueryDetail(PurchaseInQueryDetail pd) {
		// TODO Auto-generated method stub
		return pdDao.insertPurchaseInQueryDetail(pd);
	}

}
