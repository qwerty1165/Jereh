package com.sale.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.common.dao.BaseDao;
import com.common.entity.PageBean;
import com.sale.dao.SaleOrderDao;
import com.sale.entity.SaleOrder;

public class SaleOrderDaoImpl extends BaseDao implements SaleOrderDao {
	
	/**
	 * 查找列表
	 * */
	@Override
	public PageBean findSaleOrder(String code,Date startDate,Date endDate,int pageNo,int pageSize) {
	String sql="select * from saleorder so join basecustomersupplier bc on so.customercode= " +
			"bc.code  where 1=1";
		if(code!=null&&!code.equals("")){
			sql+=" and code like "+"'%"+code+"%'";}
		
		if(startDate!=null){
			sql+=" and to_char(orderDate,'yyyy-mm-dd')>="+"'"+new SimpleDateFormat("yyyy-MM-dd").format(startDate)+"'";
		}
		if(endDate!=null){
			sql+=" and to_char(orderDate,'yyyy-mm-dd')<="+"'"+new SimpleDateFormat("yyyy-MM-dd").format(endDate)+"'";
		}
		PageBean pageBean=new PageBean();
		ResultSet rs=super.excuteQueryForPage(sql,pageNo,pageSize);
		List<SaleOrder> saleOrderList=new ArrayList<SaleOrder>();
		SaleOrder saleOrder2=null;
		try {
			
			while(rs.next()){	
				saleOrder2=new SaleOrder();
				saleOrder2.setCode(rs.getString("code"));
				saleOrder2.setOrderDate(rs.getDate("orderDate"));
				saleOrder2.setCsName(rs.getString("csname"));
				saleOrder2.setContacter(rs.getString("Contacter"));
				saleOrder2.setTelphone(rs.getString("Telphone"));
				saleOrder2.setFax(rs.getString("fax"));
				saleOrder2.setTrans(rs.getString("trans"));
				saleOrder2.setBusinesser(rs.getString("businesser"));			
				saleOrder2.setRemarks(rs.getString("remarks"));
				saleOrder2.setDeLiveryDate(rs.getDate("deLiveryDate"));
				saleOrder2.setState(rs.getString("state"));
				
				saleOrderList.add(saleOrder2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		pageBean.setData(saleOrderList);
		pageBean.setRecordCount(super.executeTotalCount(sql));
		return pageBean;
	}
	/**
	 * 插入订单
	 * */
	@Override
	public int insertSaleOrder(SaleOrder saleOrder) {
		String sql="insert into saleOrder(code,orderdate,customercode, contacter, " +
				"telphone, fax, trans, businesser, remarks, deliverydate)values(?,?,?,?,?,?,?,?,?,?)";
			
		return super.executeUpdate(sql,new Object[]{saleOrder.getCode(),saleOrder.getOrderDate(),
				saleOrder.getCustomerCode(),saleOrder.getTelphone(),saleOrder.getFax(),saleOrder.getTrans(),
				saleOrder.getBusinesser(),saleOrder.getRemarks(),saleOrder.getDeLiveryDate()});		
	}
	/**
	 * 删除订单
	 * */
	@Override
	public int deleteSaleOrder(String code) {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * 修改订单
	 * */
	@Override
	public int update(SaleOrder saleOrder) {
		// TODO Auto-generated method stub
		return 0;
	}

}
