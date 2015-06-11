package com.base.service.impl;

import com.base.dao.BaseCustomerSupplierDao;

import com.base.dao.impl.BaseCustomerSupplierDaoImpl;
import com.base.entity.BaseCustomerSupplier;
import com.base.service.BaseCustomerSupplierService;
import com.common.entity.PageBean;

public class BaseCustomerSupplierServiceImpl implements BaseCustomerSupplierService {
    BaseCustomerSupplierDao customerDao=new BaseCustomerSupplierDaoImpl();
	public PageBean showAll(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return customerDao.showAll(pageNo, pageSize);
	}
	
	public int addCustomer(BaseCustomerSupplier baseCustomerSupplier) {
		// TODO Auto-generated method stub
		return customerDao.addCustomer(baseCustomerSupplier);
	}
	public int update(BaseCustomerSupplier baseCustomerSupplier) {
		// TODO Auto-generated method stub
		return customerDao.update(baseCustomerSupplier);
	}
	public int deleteCustomer(String code) {
		// TODO Auto-generated method stub
		return customerDao.deleteCustomer(code);
	}

	public PageBean findList(BaseCustomerSupplier bcs, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return customerDao.findList(bcs, pageNo, pageSize);
	}

	public BaseCustomerSupplier findBaseCustomerSupplier(String Code) {
		// TODO Auto-generated method stub
		return customerDao.findBaseCustomerSupplier(Code);
	}

}
