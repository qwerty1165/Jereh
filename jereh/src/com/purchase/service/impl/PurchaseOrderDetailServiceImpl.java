package com.purchase.service.impl;

import java.util.List;

import com.purchase.dao.PurchaseOrderDetailDao;
import com.purchase.dao.impl.PurchaseOrderDetailDaoImpl;
import com.purchase.entity.PurchaseOrderDetail;
import com.purchase.service.PurchaseOrderDetailService;

public class PurchaseOrderDetailServiceImpl implements
		PurchaseOrderDetailService {

	private PurchaseOrderDetailDao pdDao = new PurchaseOrderDetailDaoImpl();
	@Override
	public List<PurchaseOrderDetail> getPurchaseOrder(String ocode) {
		// TODO Auto-generated method stub
		return pdDao.getPurchaseOrderDetail(ocode);
	}
	@Override
	public int deletePurchaseOrderDetail(String dcode) {
		// TODO Auto-generated method stub
		return pdDao.deletePurchaseOrderDetail(dcode);
	}
	@Override
	public int insertPurchaseOrderDetail(PurchaseOrderDetail pd) {
		// TODO Auto-generated method stub
		return pdDao.insertPurchaseOrderDetail(pd);
	}
	
}
