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
import com.purchase.dao.PurchaseReturnDao;
import com.purchase.entity.PurchaseReturn;

public class PurchaseReturnDaoImpl extends BaseDao implements PurchaseReturnDao {

	/**
	 * 查询方法
	 */
	public PageBean getPurchaseReturn(String code, String beforeDate, String afterDate,
			String supplierCode, int pageNo, int pageSize){
		String sql = "select * from purchaseReturn where 1=1 ";
		ResultSet rs = null;
		List<PurchaseReturn> pList = new ArrayList<PurchaseReturn>();
		PurchaseReturn p = null;
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
				sql += " and to_char(rdate,'yyyy-mm-dd') > '" + beforeDate + "'";
			}
			if(afterDate != null && !afterDate.equals("")){
				try {
					afterDate = new SimpleDateFormat("yyyy-MM-dd").format(new 
							SimpleDateFormat("yyyy-MM-dd").parse(afterDate));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				sql += " and to_char(rdate,'yyyy-mm-dd') < '" + afterDate + "'";
			}
			if(supplierCode != null && !supplierCode.equals(""))
				sql += " and supplierCode like '%" + supplierCode + "%'";
			rs = super.excuteQueryForPage(sql, pageNo, pageSize);
			while(rs.next()){
				p = new PurchaseReturn();
				p.setCode(rs.getString("code"));
				p.setRdate(new Date(rs.getDate("Rdate").getTime()));
				p.setSupplierCode(rs.getString("supplierCode"));
				p.setContacter(rs.getString("contacter"));
				p.setTelphone(rs.getString("telPhone"));
				p.setFax(rs.getString("fax"));
				p.setRemarks(rs.getString("remarks"));
				p.setIsShow(rs.getString("isShow"));
				p.setNums(rs.getInt("nums"));
				p.setNumsPrice(rs.getDouble("numsPrice"));
				p.setState(rs.getString("state"));
				p.setAddUser(rs.getString("addUser"));
				p.setAddUserName(rs.getString("addUserName"));
				p.setAddIP(rs.getString("addIP"));
				p.setCompCode(rs.getString("compCode"));
				pList.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		PageBean pageBean = new PageBean();
		int recordCount = super.executeTotalCount("select count(*) from purchaseReturn");
		pageBean.setData(pList);
		pageBean.setRecordCount(recordCount);
		return pageBean;
	}
	/**
	 * 添加询价
	 */
	public int insertPurchaseReturn(PurchaseReturn p) {
		// TODO Auto-generated method stub
		String sql = "insert into purchaseReturn(code,rdate,suppliercode,contacter,telphone,fax" +
				",remarks,isShow,nums,numsprice,state,addUser,addUserName,addIP,compCode) values(" +
				"?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		return super.executeUpdate(sql, new Object[]{p.getCode(),new java.sql.Date(p.getRdate().getTime()),
				p.getSupplierCode(),p.getContacter(),p.getTelphone(),p.getFax(),p.getRemarks(),
				p.getIsShow(),p.getNums(),p.getNumsPrice(),p.getState(),p.getAddUser(),
				p.getAddUserName(),p.getAddIP(),p.getCompCode()});
	}
	/**
	 * 删除询价
	 */
	public int deletePurchaseReturn(String code) {
		// TODO Auto-generated method stub
		String sql1 = "delete from purchaseReturn_Detail where ctcode = '" + code + "'";
		String sql2 = "delete from purchaseReturn where code = '" + code + "'";
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
	public int deleteBatchPurchaseReturn(String[] codes) {
		// TODO Auto-generated method stub
		String sql1 = "";
		String sql2 = "";
		int ret = 0;
		Connection conn = super.getConnection();
		Statement state = null;
		try {
			state = conn.createStatement();
			for(int i = 0; i < codes.length; i++){
				sql1 = "delete from purchaseReturn_Detail where ctcode = '" + codes[i] + "'";
				state.addBatch(sql1);
			}
			for(int i = 0; i < codes.length; i++){
				sql2 = "delete from purchaseReturn where code = '" + codes[i] + "'";
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
	public int updatePurchaseReturn(PurchaseReturn p) {
		// TODO Auto-generated method stub
		String sql = "update purchaseReturn set Rdate=?,supplierCode=?,contacter=?,telphone=?,fax=?," +
				"remarks=?,isShow=?,nums=?,numsPrice=?,state=?,addUser=?,addUserName=?,addIP=?,compCode=? where code = ?";
		return super.executeUpdate(sql, new Object[]{new java.sql.Date(p.getRdate().getTime()),p.getSupplierCode(),
				p.getContacter(),p.getTelphone(),p.getFax(),p.getRemarks(),p.getIsShow(),p.getNums(),p.getNumsPrice(),p.getState(),
				p.getAddUser(),p.getAddUserName(),p.getAddIP(),p.getCompCode(),p.getCode()});
	}
	/**
	 * 拿一个详显数据
	 */
	public PurchaseReturn getPurchaseReturn(String code) {
		// TODO Auto-generated method stub
		String sql = "select * from purchaseReturn where code = ?";
		ResultSet rs = null;
		PurchaseReturn p = null;
		try {
			rs = super.executeQuery(sql, code);
			if(rs.next()){
				p = new PurchaseReturn();
				p.setCode(rs.getString("code"));
				p.setRdate(new Date(rs.getDate("Rdate").getTime()));
				p.setSupplierCode(rs.getString("supplierCode"));
				p.setContacter(rs.getString("contacter"));
				p.setTelphone(rs.getString("telPhone"));
				p.setFax(rs.getString("fax"));
				p.setRemarks(rs.getString("remarks"));
				p.setIsShow(rs.getString("isShow"));
				p.setNums(rs.getInt("nums"));
				p.setNumsPrice(rs.getDouble("numsPrice"));
				p.setState(rs.getString("state"));
				p.setAddUser(rs.getString("addUser"));
				p.setAddUserName(rs.getString("addUserName"));
				p.setAddIP(rs.getString("addIP"));
				p.setCompCode(rs.getString("compCode"));
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
		String sql = "update purchaseReturn set numsPrice = ? where code = ?";
		return super.executeUpdate(sql, new Object[]{sumMoney, code});
	}

}
