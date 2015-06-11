package com.base.dao;

import java.util.List;



import com.base.entity.BaseCustomerSupplier;
import com.common.entity.PageBean;

public interface BaseCustomerSupplierDao {
	public PageBean showAll(int pageNo,int pageSize);
	public PageBean findList(BaseCustomerSupplier bcs,int pageNo, int pageSize);
    public int addCustomer(BaseCustomerSupplier baseCustomerSupplier);
    public int update(BaseCustomerSupplier baseCustomerSupplier);
    public int deleteCustomer(String code);
    public BaseCustomerSupplier findBaseCustomerSupplier(String code);
 
}
