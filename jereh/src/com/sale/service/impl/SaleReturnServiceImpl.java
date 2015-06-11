package com.sale.service.impl;

import com.common.entity.PageBean;
import com.sale.dao.SaleReturnDao;
import com.sale.dao.impl.SaleReturnDaoImpl;
import com.sale.entity.SaleReturn;
import com.sale.service.SaleReturnService;

public class SaleReturnServiceImpl implements SaleReturnService {
	SaleReturnDao saleReturnDao = new SaleReturnDaoImpl();

	@Override
	public PageBean getSaleReturnList(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return saleReturnDao.findAll(pageNo, pageSize);
	}

	@Override
	public int deleteSaleReturn(String code) {
		// TODO Auto-generated method stub
		return saleReturnDao.delete(code);
	}

	@Override
	public int updataSaleReturn(SaleReturn saleReturn, String code) {
		// TODO Auto-generated method stub
		return saleReturnDao.update(saleReturn, code);
	}

	@Override
	public SaleReturn getSaleReturnByCode(String code) {
		// TODO Auto-generated method stub
		return saleReturnDao.findByCode(code);
	}

	@Override
	public int addSaleReturn(SaleReturn saleReturn) {
		// TODO Auto-generated method stub
		return saleReturnDao.insert(saleReturn);
	}

}
