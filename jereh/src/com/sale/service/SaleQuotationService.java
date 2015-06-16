package com.sale.service;

import java.util.Date;
import java.util.List;

import com.sale.entity.SaleQuotation;
import com.sale.entity.SaleQuotation_Detail;

import com.common.entity.PageBean;

public interface SaleQuotationService {
	public PageBean findList(SaleQuotation sq,Date startDate,Date endDate,int pageNo, int pageSize);
	public int deleteSaleQuotation(String code);
	public List<SaleQuotation_Detail> showDetail(String scode);
	public int updateSaleQuotation(SaleQuotation saleQuotation);
	public int addSaleQuotation(SaleQuotation saleQuotation);
	public List<SaleQuotation_Detail> showPartsList(SaleQuotation_Detail saleQuotation_Detail);
    public int addSaleQuotationDetail(SaleQuotation_Detail saleQuotation_Detail);
}
