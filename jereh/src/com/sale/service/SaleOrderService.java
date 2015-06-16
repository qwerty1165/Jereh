package com.sale.service;

import java.util.Date;

import com.common.entity.PageBean;
import com.sale.entity.SaleOrder;

public interface SaleOrderService {
	
	public PageBean findSaleOrder(String code,Date startDate,Date endDate,int pageNo,int pageSize); //��ʾ
	
	public int insertSaleOrder(SaleOrder saleOrder); //���������Ϣ
	
	public int deleteSaleOrder(String code);  //ɾ�������Ϣ
	
	public int update(SaleOrder saleOrder);
}
