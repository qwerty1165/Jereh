package com.sale.dao;

import java.util.List;

import com.common.entity.PageBean;
import com.sale.entity.SaleReturn;

public interface SaleReturnDao {
	public PageBean findAll(int pageNo, int pageSize);

	//public PageBean findAll(String code, String pCode, int pageNo, int pageSize);

	public int insert(SaleReturn saleReturn);

	public int update(SaleReturn saleReturn, String code);

	public int delete(String code);

	public SaleReturn findByCode(String code);

	public int findMaxCode();

	//public List findAllCode();
}
