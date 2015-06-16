package com.purchase.service.impl;

import com.common.entity.PageBean;
import com.purchase.dao.PurchaseReturnDao;
import com.purchase.dao.impl.PurchaseReturnDaoImpl;
import com.purchase.entity.PurchaseReturn;
import com.purchase.service.PurchaseReturnService;

public class PurchaseReturnServiceImpl implements PurchaseReturnService {

	private PurchaseReturnDao pDao = new PurchaseReturnDaoImpl();
	/**
	 * 查询方法
	 */
	public PageBean getPurchaseReturn(String code, String beforeDate,
			String afterDate, String supplierCode, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return pDao.getPurchaseReturn(code, beforeDate, afterDate, supplierCode, pageNo, pageSize);
	}
	/**
	 * 删除询价
	 */
	public int deletePurchaseReturn(String code) {
		// TODO Auto-generated method stub
		return pDao.deletePurchaseReturn(code);
	}
	/**
	 * 添加询价
	 */
	public int insertPurchaseReturn(PurchaseReturn p) {
		// TODO Auto-generated method stub
		return pDao.insertPurchaseReturn(p);
	}
	/**
	 * 修改询价
	 */
	public int updatePurchaseReturn(PurchaseReturn p) {
		// TODO Auto-generated method stub
		return pDao.updatePurchaseReturn(p);
	}
	/**
	 * 
	 */
	public PurchaseReturn getPurchaseReturn(String code) {
		// TODO Auto-generated method stub
		return pDao.getPurchaseReturn(code);
	}
	/**
	 * 批量删除
	 */
	public int deleteBatchPurchaseReturn(String[] codes) {
		// TODO Auto-generated method stub
		return pDao.deleteBatchPurchaseReturn(codes);
	}

}
