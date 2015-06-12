package com.sale.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.common.dao.BaseDao;
import com.sale.dao.SaleReturnDetailDao;
import com.sale.entity.SaleReturnDetail;

public class SaleReturnDetailDaoImpl extends BaseDao implements
		SaleReturnDetailDao {

	@Override
	public List<SaleReturnDetail> findAll() {
		// TODO Auto-generated method stub
		String sql="select CKCODE,PARTSNO,PARTSNAME,PARTSBRAND,PARTSMODEL,NUMS,REMARKS from salereturn_detail r left join baseparts b on r.pcode=b.partscode";
		ResultSet rs=super.executeQuery(sql);
		List<SaleReturnDetail> saleReturnDetailList=new ArrayList<SaleReturnDetail>();
		SaleReturnDetail saleReturnDetail=null;
		try {
			while(rs.next()){
				saleReturnDetail=new SaleReturnDetail();
				saleReturnDetail.setOutCode(rs.getString("CKCODE"));
				saleReturnDetail.setPartsNo(rs.getString("PARTSNO"));
				saleReturnDetail.setPartsName(rs.getString("PARTSNAME"));
				saleReturnDetail.setPartsBrand(rs.getString("PARTSBRAND"));
				saleReturnDetail.setPartsModle(rs.getString("PARTSMODEL"));
				saleReturnDetail.setNumber(rs.getInt("NUMS"));
				saleReturnDetail.setRemarks(rs.getString("REMARKS"));
				saleReturnDetail.setSalePrice(rs.getLong("salePrice"));
				saleReturnDetailList.add(saleReturnDetail);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return saleReturnDetailList;
	}

	@Override
	public List<SaleReturnDetail> findByCode(String code) {
		// TODO Auto-generated method stub
		String sql="select CKCODE,PARTSNO,PARTSNAME,PARTSBRAND,PARTSMODEL,NUMS,SALEPRICE,r.REMARKS from salereturn_detail r left join baseparts b on r.pcode=b.partscode where xtcode=?";
		ResultSet rs=super.executeQuery(sql, code);
		List<SaleReturnDetail> saleReturnDetailList=new ArrayList<SaleReturnDetail>();
		SaleReturnDetail saleReturnDetail=null;
		try {
			while(rs.next()){
				saleReturnDetail=new SaleReturnDetail();
				saleReturnDetail.setOutCode(rs.getString("CKCODE"));
				saleReturnDetail.setPartsNo(rs.getString("PARTSNO"));
				saleReturnDetail.setPartsName(rs.getString("PARTSNAME"));
				saleReturnDetail.setPartsBrand(rs.getString("PARTSBRAND"));
				saleReturnDetail.setPartsModle(rs.getString("PARTSMODEL"));
				saleReturnDetail.setNumber(rs.getInt("NUMS"));
				saleReturnDetail.setSalePrice(rs.getLong("SALEPRICE"));
				saleReturnDetail.setRemarks(rs.getString("REMARKS"));
				saleReturnDetailList.add(saleReturnDetail);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return saleReturnDetailList;
	}
	
	
}
