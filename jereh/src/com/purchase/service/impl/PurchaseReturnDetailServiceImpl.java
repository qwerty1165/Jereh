package com.purchase.service.impl;

import java.util.List;

import com.purchase.dao.PurchaseReturnDetailDao;
import com.purchase.dao.impl.PurchaseReturnDetailDaoImpl;
import com.purchase.entity.PurchaseReturnDetail;
import com.purchase.service.PurchaseReturnDetailService;

public class PurchaseReturnDetailServiceImpl implements
		PurchaseReturnDetailService {

	private PurchaseReturnDetailDao pdDao = new PurchaseReturnDetailDaoImpl();
	@Override
	public List<PurchaseReturnDetail> getPurchaseReturn(String ocode) {
		// TODO Auto-generated method stub
		return pdDao.getPurchaseReturnDetail(ocode);
	}
	@Override
	public int deletePurchaseReturnDetail(String dcode) {
		// TODO Auto-generated method stub
		return pdDao.deletePurchaseReturnDetail(dcode);
	}
	@Override
	public int insertPurchaseReturnDetail(PurchaseReturnDetail pd) {
		// TODO Auto-generated method stub
		return pdDao.insertPurchaseReturnDetail(pd);
	}

}
