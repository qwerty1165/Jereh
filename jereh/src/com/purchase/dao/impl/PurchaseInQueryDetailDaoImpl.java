package com.purchase.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.common.dao.BaseDao;
import com.purchase.dao.PurchaseInQueryDetailDao;
import com.purchase.entity.PurchaseInQueryDetail;

public class PurchaseInQueryDetailDaoImpl extends BaseDao implements PurchaseInQueryDetailDao {

	@Override
	public List<PurchaseInQueryDetail> getPurchaseInQueryDetail(String xcode) {
		// TODO Auto-generated method stub
		String sql = "select * from purchaseInQuery_Detail where xcode = ?";
		ResultSet rs = null;
		PurchaseInQueryDetail pd = null;
		List<PurchaseInQueryDetail> pdList = new ArrayList<PurchaseInQueryDetail>();
		try {
			rs = super.executeQuery(sql, xcode);
			while(rs.next()){
				pd = new PurchaseInQueryDetail();
				pd.setDcode(rs.getString("dcode"));
				pd.setXcode(xcode);
				pd.setPcode(rs.getString("pcode"));
				pd.setNums(rs.getInt("nums"));
				pd.setPrice(rs.getDouble("price"));
				pd.setDeliveryMode(rs.getString("deliveryMode"));
				pd.setRemarks(rs.getString("remarks"));
				pdList.add(pd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return pdList;
	}

	@Override
	public int deletePurchaseInQueryDetail(String dcode) {
		// TODO Auto-generated method stub
		String sql = "delete from purchaseInQuery_detail where dcode = ?";
		return super.executeUpdate(sql, dcode);
	}

	@Override
	public double getNumsPrice(String xcode) {
		// TODO Auto-generated method stub
		String sql = "select nums,price from purchaseInQuery_detail where xcode = ?";
		ResultSet rs = null;
		double sumMoney = 0.0;
		try {
			rs = super.executeQuery(sql, xcode);
			while(rs.next()){
				sumMoney += rs.getInt("nums")*rs.getDouble("price");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return sumMoney;
	}

	@Override
	public int insertPurchaseInQueryDetail(PurchaseInQueryDetail pd) {
		// TODO Auto-generated method stub
		String sql = "insert into purchaseInQuery_detail(dcode,xcode,pcode,nums,price,deliveryMode,remarks)" +
				" values(?,?,?,?,?,?,?)";
		return super.executeUpdate(sql, new Object[]{pd.getDcode(),pd.getXcode(),pd.getPcode(),pd.getNums(),
				pd.getPrice(),pd.getDeliveryMode(),pd.getRemarks()});
	}

}
