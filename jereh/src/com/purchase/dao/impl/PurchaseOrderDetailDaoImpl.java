package com.purchase.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.common.dao.BaseDao;
import com.purchase.dao.PurchaseOrderDetailDao;
import com.purchase.entity.PurchaseOrderDetail;

public class PurchaseOrderDetailDaoImpl extends BaseDao implements PurchaseOrderDetailDao {

	@Override
	public List<PurchaseOrderDetail> getPurchaseOrderDetail(String ocode) {
		// TODO Auto-generated method stub
		String sql = "select * from purchaseOrder_Detail where ocode = ?";
		ResultSet rs = null;
		PurchaseOrderDetail pd = null;
		List<PurchaseOrderDetail> pdList = new ArrayList<PurchaseOrderDetail>();
		try {
			rs = super.executeQuery(sql, ocode);
			while(rs.next()){
				pd = new PurchaseOrderDetail();
				pd.setDcode(rs.getString("dcode"));
				pd.setOcode(ocode);
				pd.setXcode(rs.getString("xcode"));
				pd.setPcode(rs.getString("pcode"));
				pd.setNums(rs.getInt("nums"));
				pd.setPrice(rs.getDouble("price"));
				pd.setRkState(rs.getString("rkstate"));
				pd.setRkNums(rs.getInt("rknums"));
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
	public int deletePurchaseOrderDetail(String dcode) {
		// TODO Auto-generated method stub
		String sql = "delete from purchaseOrder_detail where dcode = ?";
		return super.executeUpdate(sql, dcode);
	}

	@Override
	public double getNumsPrice(String ocode) {
		// TODO Auto-generated method stub
		String sql = "select nums,price from purchaseOrder_detail where ocode = ?";
		ResultSet rs = null;
		double sumMoney = 0.0;
		try {
			rs = super.executeQuery(sql, ocode);
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
	public int insertPurchaseOrderDetail(PurchaseOrderDetail pd) {
		// TODO Auto-generated method stub
		String sql = "insert into purchaseOrder_detail(dcode,ocode,xcode,pcode,nums,price,rkState,rknums,remarks)" +
				" values(?,?,?,?,?,?,?,?,?)";
		return super.executeUpdate(sql, new Object[]{pd.getDcode(),pd.getOcode(),pd.getXcode(),pd.getPcode(),
				pd.getNums(),pd.getPrice(),pd.getRkState(),pd.getRkNums(),pd.getRemarks()});
	}

}
