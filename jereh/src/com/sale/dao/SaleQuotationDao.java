package com.sale.dao;

import java.util.List;

import com.sale.entity.SaleQuotation;
import com.sale.entity.SaleQuotation_Detail;
import com.base.entity.BaseParts;
import com.common.entity.PageBean;

public interface SaleQuotationDao {
	public PageBean findList(SaleQuotation sq,int pageNo, int pageSize);
    public int deleteSaleQuotation(String code);
    public List<SaleQuotation_Detail> showDetail(String scode);
    public int updateSaleQuotation(SaleQuotation saleQuotation);
    public int addSaleQuotation(SaleQuotation saleQuotation);
}
