package com.purchase.service.impl;

import com.common.entity.PageBean;
import com.purchase.dao.PurchaseInQueryDao;
import com.purchase.dao.impl.PurchaseInQueryDaoImpl;
import com.purchase.entity.PurchaseInQuery;
import com.purchase.service.PurchaseInQueryService;

public class PurchaseInQueryServiceImpl implements PurchaseInQueryService {
	
	private PurchaseInQueryDao pDao = new PurchaseInQueryDaoImpl();
	/**
	 * ��ѯ����
	 */
	public PageBean getPurchaseInQuery(String code, String beforeDate,
			String afterDate, String supplierCode, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return pDao.getPurchaseInQuery(code, beforeDate, afterDate, supplierCode, pageNo, pageSize);
	}
	/**
	 * ɾ��ѯ��
	 */
	public int deletePurchaseInQuery(String code) {
		// TODO Auto-generated method stub
		return pDao.deletePurchaseInQuery(code);
	}
	/**
	 * ���ѯ��
	 */
	public int insertPurchaseInQuery(PurchaseInQuery p) {
		// TODO Auto-generated method stub
		return pDao.insertPurchaseInQuery(p);
	}
	/**
	 * �޸�ѯ��
	 */
	public int updatePurchaseInQuery(PurchaseInQuery p) {
		// TODO Auto-generated method stub
		return pDao.updatePurchaseInQuery(p);
	}
	/**
	 * 
	 */
	public PurchaseInQuery getPurchaseInQuery(String code) {
		// TODO Auto-generated method stub
		return pDao.getPurchaseInQuery(code);
	}
	/**
	 * ����ɾ��
	 */
	public int deleteBatchPurchaseInQuery(String[] codes) {
		// TODO Auto-generated method stub
		return pDao.deleteBatchPurchaseInQuery(codes);
	}

}
