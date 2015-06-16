package com.sale.service.impl;

import java.util.List;

import com.sale.dao.SaleOrder_DetailDao;
import com.sale.dao.impl.SaleOrder_DetailDaoImpl;
import com.sale.entity.SaleOrder_Detail;
import com.sale.service.SaleOrder_DetailService;

public class SaleOrder_DetailServiceImpl implements SaleOrder_DetailService{
	
	SaleOrder_DetailDao saleOrder_DetailDao=new SaleOrder_DetailDaoImpl();
	@Override
	public List<SaleOrder_Detail> findDetailByCode(String code) {		
		return saleOrder_DetailDao.findDetailByCode(code);
	}

}
