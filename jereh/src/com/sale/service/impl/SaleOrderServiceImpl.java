package com.sale.service.impl;

import java.util.Date;

import com.sale.dao.impl.SaleOrderDaoImpl;
import com.common.entity.PageBean;
import com.sale.dao.SaleOrderDao;
import com.sale.entity.SaleOrder;
import com.sale.service.SaleOrderService;

public class SaleOrderServiceImpl implements SaleOrderService {

	SaleOrderDao saleOrderDao=new SaleOrderDaoImpl();
	@Override
	public PageBean findSaleOrder(String code,Date startDate,Date endDate,int pageNo,int pageSize) {
		// TODO Auto-generated method stub
		return saleOrderDao.findSaleOrder(code,startDate,endDate, pageNo, pageSize);
	}

	@Override
	public int insertSaleOrder(SaleOrder saleOrder) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteSaleOrder(String code) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(SaleOrder saleOrder) {
		// TODO Auto-generated method stub
		return 0;
	}

}
