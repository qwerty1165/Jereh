package com.sale.service;

import com.common.entity.PageBean;
import com.sale.entity.SaleReturn;

public interface SaleReturnService {
	public PageBean getSaleReturnList(int pageNo, int pageSize);

	//public PageBean getSaleReturnList(String code, String pCode,
	//		int pageNo, int pageSize);

	public int deleteSaleReturn(String code);

	public int updataSaleReturn(SaleReturn saleReturn,
			String code);

	public SaleReturn getSaleReturnByCode(String code);

	public int addSaleReturn(SaleReturn saleReturn);
}
