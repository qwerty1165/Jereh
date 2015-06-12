package com.sale.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.sale.dao.SaleReturnDetailDao;
import com.sale.dao.impl.SaleReturnDetailDaoImpl;
import com.sale.entity.SaleReturnDetail;
import com.sale.service.SaleReturnDetailService;

public class SaleReturnDetailServiceImpl implements SaleReturnDetailService {
	private SaleReturnDetailDao saleReturnDetailDao = new SaleReturnDetailDaoImpl();

	@Override
	public List<SaleReturnDetail> getSaleReturnDetailList() {
		// TODO Auto-generated method stub
		return saleReturnDetailDao.findAll();
	}

	@Override
	public List<SaleReturnDetail> getSaleReturnDetailListByCode(String code) {
		// TODO Auto-generated method stub
		return saleReturnDetailDao.findByCode(code);
	}

}
