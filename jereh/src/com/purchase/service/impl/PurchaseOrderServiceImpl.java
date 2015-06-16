package com.purchase.service.impl;

import com.common.entity.PageBean;
import com.purchase.dao.PurchaseOrderDao;
import com.purchase.dao.impl.PurchaseOrderDaoImpl;
import com.purchase.entity.PurchaseOrder;
import com.purchase.service.PurchaseOrderService;

public class PurchaseOrderServiceImpl implements PurchaseOrderService {

	private PurchaseOrderDao pDao = new PurchaseOrderDaoImpl();
	/**
	 * ��ѯ����
	 */
	public PageBean getPurchaseOrder(String code, String beforeDate,
			String afterDate, String supplierCode, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return pDao.getPurchaseOrder(code, beforeDate, afterDate, supplierCode, pageNo, pageSize);
	}
	/**
	 * ɾ��ѯ��
	 */
	public int deletePurchaseOrder(String code) {
		// TODO Auto-generated method stub
		return pDao.deletePurchaseOrder(code);
	}
	/**
	 * ���ѯ��
	 */
	public int insertPurchaseOrder(PurchaseOrder p) {
		// TODO Auto-generated method stub
		return pDao.insertPurchaseOrder(p);
	}
	/**
	 * �޸�ѯ��
	 */
	public int updatePurchaseOrder(PurchaseOrder p) {
		// TODO Auto-generated method stub
		return pDao.updatePurchaseOrder(p);
	}
	/**
	 * 
	 */
	public PurchaseOrder getPurchaseOrder(String code) {
		// TODO Auto-generated method stub
		return pDao.getPurchaseOrder(code);
	}
	/**
	 * ����ɾ��
	 */
	public int deleteBatchPurchaseOrder(String[] codes) {
		// TODO Auto-generated method stub
		return pDao.deleteBatchPurchaseOrder(codes);
	}

}
