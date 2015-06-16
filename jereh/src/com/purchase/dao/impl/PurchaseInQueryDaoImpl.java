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

import com.purchase.dao.PurchaseInQueryDao;
import com.purchase.entity.PurchaseInQuery;

public class PurchaseInQueryDaoImpl extends BaseDao implements PurchaseInQueryDao{
	/**
	 * 查询方法
	 */
	public PageBean getPurchaseInQuery(String code, String beforeDate, String afterDate,
			String supplierCode, int pageNo, int pageSize){
		String sql = "select * from purchaseInQuery where 1=1 ";
		ResultSet rs = null;
		List<PurchaseInQuery> pList = new ArrayList<PurchaseInQuery>();
		PurchaseInQuery p = null;
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
				p = new PurchaseInQuery();
				p.setCode(rs.getString("code"));
				p.setAddDate(new Date(rs.getDate("addDate").getTime()));
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
		int recordCount = super.executeTotalCount("select count(*) from purchaseInQuery");
		pageBean.setData(pList);
		pageBean.setRecordCount(recordCount);
		return pageBean;
	}
	/**
	 * 添加询价
	 */
	public int insertPurchaseInQuery(PurchaseInQuery p) {
		// TODO Auto-generated method stub
		String sql = "insert into purchaseInQuery(code,addDate,suppliercode,contacter,telphone,fax" +
				",remarks,isshow,nums,numsprice,state,addUser,addUserName,addIP,compCode) values(" +
				"?,sysdate,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		return super.executeUpdate(sql, new Object[]{p.getCode(),p.getSupplierCode(),p.getContacter(),
				p.getTelphone(),p.getFax(),p.getRemarks(),p.getIsShow(),p.getNums(),p.getNumsPrice(),
				p.getState(),p.getAddUser(),p.getAddUserName(),p.getAddIP(),p.getCompCode()});
	}
	/**
	 * 删除询价
	 */
	public int deletePurchaseInQuery(String code) {
		// TODO Auto-generated method stub
		String sql1 = "delete from purchaseInQuery_Detail where xcode = '" + code + "'";
		String sql2 = "delete from purchaseInQuery where code = '" + code + "'";
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
	public int deleteBatchPurchaseInQuery(String[] codes) {
		// TODO Auto-generated method stub
		String sql1 = "";
		String sql2 = "";
		int ret = 0;
		Connection conn = super.getConnection();
		Statement state = null;
		try {
			state = conn.createStatement();
			for(int i = 0; i < codes.length; i++){
				sql1 = "delete from purchaseInQuery_Detail where xcode = '" + codes[i] + "'";
				state.addBatch(sql1);
			}
			for(int i = 0; i < codes.length; i++){
				sql2 = "delete from purchaseInQuery where code = '" + codes[i] + "'";
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
	public int updatePurchaseInQuery(PurchaseInQuery p) {
		// TODO Auto-generated method stub
		String sql = "update purchaseInQuery set supplierCode=?,contacter=?,telphone=?,fax=?," +
				"remarks=?,isShow=?,nums=?,numsPrice=?,state=?,addUser=?,addUserName=?,addIP=?,compCode=? where code = ?";
		return super.executeUpdate(sql, new Object[]{p.getSupplierCode(),p.getContacter(),
				p.getTelphone(),p.getFax(),p.getRemarks(),p.getIsShow(),p.getNums(),p.getNumsPrice(),p.getState(),
				p.getAddUser(),p.getAddUserName(),p.getAddIP(),p.getCompCode(),p.getCode()});
	}
	/**
	 * 拿一个详显数据
	 */
	public PurchaseInQuery getPurchaseInQuery(String code) {
		// TODO Auto-generated method stub
		String sql = "select * from purchaseInQuery where code = ?";
		ResultSet rs = null;
		PurchaseInQuery p = null;
		try {
			rs = super.executeQuery(sql, code);
			if(rs.next()){
				p = new PurchaseInQuery();
				p.setCode(rs.getString("code"));
				p.setAddDate(new Date(rs.getDate("addDate").getTime()));
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
		String sql = "update purchaseInQuery set numsPrice = ? where code = ?";
		return super.executeUpdate(sql, new Object[]{sumMoney, code});
	}

}
