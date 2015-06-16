package com.purchase.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.common.dao.BaseDao;
import com.common.entity.PageBean;
import com.purchase.dao.PurchaseOrderDao;
import com.purchase.entity.PurchaseOrder;

public class PurchaseOrderDaoImpl extends BaseDao implements PurchaseOrderDao {

	/**
	 * 查询方法
	 */
	public PageBean getPurchaseOrder(String code, String beforeDate, String afterDate,
			String supplierCode, int pageNo, int pageSize){
		String sql = "select * from purchaseOrder where 1=1 ";
		ResultSet rs = null;
		List<PurchaseOrder> pList = new ArrayList<PurchaseOrder>();
		PurchaseOrder p = null;
		try{
			if(code != null && !code.equals(""))
				sql += " and code like '%" + code + "%'";
			if(beforeDate != null && !beforeDate.equals("")){
				try {
					beforeDate = new SimpleDateFormat("yyyy-MM-dd").format(new 
							SimpleDateFormat("yyyy-MM-dd").parse(beforeDate));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				sql += " and to_char(addDate,'yyyy-mm-dd') > '" + beforeDate + "'";
			}
			if(afterDate != null && !afterDate.equals("")){
				try {
					afterDate = new SimpleDateFormat("yyyy-MM-dd").format(new 
							SimpleDateFormat("yyyy-MM-dd").parse(afterDate));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				sql += " and to_char(addDate,'yyyy-mm-dd') < '" + afterDate + "'";
			}
			if(supplierCode != null && !supplierCode.equals(""))
				sql += " and supplierCode like '%" + supplierCode + "%'";
			rs = super.excuteQueryForPage(sql, pageNo, pageSize);
			while(rs.next()){
				p = new PurchaseOrder();
				p.setCode(rs.getString("code"));
				p.setOrderDate(new Date(rs.getDate("orderDate").getTime()));
				p.setSupplierCode(rs.getString("supplierCode"));
				p.setContacter(rs.getString("contacter"));
				p.setTelphone(rs.getString("telPhone"));
				p.setFax(rs.getString("fax"));
				p.setTrans(rs.getString("trans"));
				p.setDeliveryDate(new Date(rs.getDate("deliveryDate").getTime()));
				p.setBusinesser(rs.getString("businesser"));
				p.setRemarks(rs.getString("remarks"));
				p.setIsShow(rs.getString("isShow"));
				p.setNums(rs.getInt("nums"));
				p.setNumsPrice(rs.getDouble("numsPrice"));
				p.setState(rs.getString("state"));
				p.setAddDate(new Date(rs.getDate("addDate").getTime()));
				p.setAddUser(rs.getString("addUser"));
				p.setAddUserName(rs.getString("addUserName"));
				p.setAddIP(rs.getString("addIP"));
				pList.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		PageBean pageBean = new PageBean();
		int recordCount = super.executeTotalCount("select count(*) from purchaseOrder");
		pageBean.setData(pList);
		pageBean.setRecordCount(recordCount);
		return pageBean;
	}
	/**
	 * 添加询价
	 */
	public int insertPurchaseOrder(PurchaseOrder p) {
		// TODO Auto-generated method stub
		String sql = "insert into purchaseOrder(code,orderDate,suppliercode,contacter,telphone,fax" +
				",trans,deliveryDate,businesser,remarks,isShow,nums,numsprice,state,addDate,addUser," +
				"addUserName,addIP) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate,?,?,?)";
		return super.executeUpdate(sql, new Object[]{p.getCode(),new java.sql.Date(p.getOrderDate().getTime()),
				p.getSupplierCode(),p.getContacter(),p.getTelphone(),p.getFax(),p.getTrans(),
				new java.sql.Date(p.getDeliveryDate().getTime()),p.getBusinesser(),p.getRemarks(),
				p.getIsShow(),p.getNums(),p.getNumsPrice(),p.getState(),p.getAddUser(),
				p.getAddUserName(),p.getAddIP()});
	}
	/**
	 * 删除询价
	 */
	public int deletePurchaseOrder(String code) {
		// TODO Auto-generated method stub
		String sql1 = "delete from purchaseOrder_Detail where ocode = '" + code + "'";
		String sql2 = "delete from purchaseOrder where code = '" + code + "'";
		int ret = 0;
		Connection conn = super.getConnection();
		Statement state = null;
		try {
			state = conn.createStatement();
			state.addBatch(sql1);
			state.addBatch(sql2);
			state.executeBatch();
			ret = 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(state != null)
					state.close();
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ret;
	}
	/**
	 * 批量删除询价
	 */
	public int deleteBatchPurchaseOrder(String[] codes) {
		// TODO Auto-generated method stub
		String sql1 = "";
		String sql2 = "";
		int ret = 0;
		Connection conn = super.getConnection();
		Statement state = null;
		try {
			state = conn.createStatement();
			for(int i = 0; i < codes.length; i++){
				sql1 = "delete from purchaseOrder_Detail where ocode = '" + codes[i] + "'";
				state.addBatch(sql1);
			}
			for(int i = 0; i < codes.length; i++){
				sql2 = "delete from purchaseOrder where code = '" + codes[i] + "'";
				state.addBatch(sql2);
			}
			state.executeBatch();
			ret = 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(state != null)
					state.close();
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ret;
	}
	/**
	 * 修改询价
	 */
	public int updatePurchaseOrder(PurchaseOrder p) {
		// TODO Auto-generated method stub
		String sql = "update purchaseOrder set orderDate=?,supplierCode=?,contacter=?,telphone=?,fax=?," +
				"trans=?,deliveryDate=?,businesser=?,remarks=?,isShow=?,nums=?,numsPrice=?,state=?," +
				"addUser=?,addUserName=?,addIP=? where code = ?";
		return super.executeUpdate(sql, new Object[]{new java.sql.Date(p.getOrderDate().getTime()),p.getSupplierCode(),
				p.getContacter(),p.getTelphone(),p.getFax(),p.getTrans(),new java.sql.Date(p.getDeliveryDate().getTime()),
				p.getBusinesser(),p.getRemarks(),p.getIsShow(),p.getNums(),p.getNumsPrice(),p.getState(),
				p.getAddUser(),p.getAddUserName(),p.getAddIP(),p.getCode()});
	}
	/**
	 * 拿一个详显数据
	 */
	public PurchaseOrder getPurchaseOrder(String code) {
		// TODO Auto-generated method stub
		String sql = "select * from purchaseOrder where code = ?";
		ResultSet rs = null;
		PurchaseOrder p = null;
		try {
			rs = super.executeQuery(sql, code);
			if(rs.next()){
				p = new PurchaseOrder();
				p.setCode(rs.getString("code"));
				p.setOrderDate(new Date(rs.getDate("orderDate").getTime()));
				p.setSupplierCode(rs.getString("supplierCode"));
				p.setContacter(rs.getString("contacter"));
				p.setTelphone(rs.getString("telphone"));
				p.setFax(rs.getString("fax"));
				p.setTrans(rs.getString("trans"));
				p.setDeliveryDate(new Date(rs.getDate("deliveryDate").getTime()));
				p.setBusinesser(rs.getString("businesser"));
				p.setRemarks(rs.getString("remarks"));
				p.setIsShow(rs.getString("isShow"));
				p.setNums(rs.getInt("nums"));
				p.setNumsPrice(rs.getDouble("numsPrice"));
				p.setState(rs.getString("state"));
				p.setAddDate(new Date(rs.getDate("addDate").getTime()));
				p.setAddUser(rs.getString("addUser"));
				p.setAddUserName(rs.getString("addUserName"));
				p.setAddIP(rs.getString("addIP"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return p;
	}
	/**
	 * 修改numsPrice
	 */
	@Override
	public int updateNumsPrice(double sumMoney, String code) {
		// TODO Auto-generated method stub
		String sql = "update purchaseOrder set numsPrice = ? where code = ?";
		return super.executeUpdate(sql, new Object[]{sumMoney, code});
	}

}
