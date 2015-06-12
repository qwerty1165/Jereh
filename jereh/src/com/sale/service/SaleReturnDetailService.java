package com.sale.service;

import java.util.List;

import com.common.entity.PageBean;
import com.sale.entity.SaleReturn;
import com.sale.entity.SaleReturnDetail;

public interface SaleReturnDetailService {
	public List<SaleReturnDetail> getSaleReturnDetailList();

	public List<SaleReturnDetail> getSaleReturnDetailListByCode(String code);
}
