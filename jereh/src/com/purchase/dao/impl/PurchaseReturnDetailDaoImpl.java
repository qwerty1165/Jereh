package com.purchase.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.common.dao.BaseDao;
import com.purchase.dao.PurchaseReturnDetailDao;
import com.purchase.entity.PurchaseReturnDetail;

public class PurchaseReturnDetailDaoImpl extends BaseDao implements PurchaseReturnDetailDao {

	@Override
	public List<PurchaseReturnDetail> getPurchaseReturnDetail(String ctcode) {
		// TODO Auto-generated method stub
		String sql = "select * from purchaseReturn_Detail where ctcode = ?";
		ResultSet rs = null;
		PurchaseReturnDetail pd = null;
		List<PurchaseReturnDetail> pdList = new ArrayList<PurchaseReturnDetail>();
		try {
			rs = super.executeQuery(sql, ctcode);
			while(rs.next()){
				pd = new PurchaseReturnDetail();
				pd.setCode(rs.getString("code"));
				pd.setCtcode(ctcode);
				pd.setRkcode(rs.getString("rkcode"));
				pd.setPcode(rs.getString("pcode"));
				pd.setNums(rs.getInt("nums"));
				pd.setPrice(rs.getDouble("price"));
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
	public int deletePurchaseReturnDetail(String code) {
		// TODO Auto-generated method stub
		String sql = "delete from purchaseReturn_detail where code = ?";
		return super.executeUpdate(sql, code);
	}

	@Override
	public double getNumsPrice(String ctcode) {
		// TODO Auto-generated method stub
		String sql = "select nums,price from purchaseReturn_detail where ctcode = ?";
		ResultSet rs = null;
		double sumMoney = 0.0;
		try {
			rs = super.executeQuery(sql, ctcode);
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
	public int insertPurchaseReturnDetail(PurchaseReturnDetail pd) {
		// TODO Auto-generated method stub
		String sql = "insert into purchaseReturn_detail(code,ctcode,rkcode,pcode,nums,price,remarks)" +
				" values(?,?,?,?,?,?,?)";
		return super.executeUpdate(sql, new Object[]{pd.getCode(),pd.getCtcode(),pd.getRkcode(),pd.getPcode(),
				pd.getNums(),pd.getPrice(),pd.getRemarks()});
	}

}
