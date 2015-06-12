package com.sale.service;

import java.util.Date;

import com.common.entity.PageBean;
import com.sale.entity.SaleOrder;

public interface SaleOrderService {
	
	public PageBean findSaleOrder(String code,Date startDate,Date endDate,int pageNo,int pageSize); //显示
	
	public int insertSaleOrder(SaleOrder saleOrder); //插入配件信息
	
	public int deleteSaleOrder(String code);  //删除配件信息
	
	public int update(SaleOrder saleOrder);
}
