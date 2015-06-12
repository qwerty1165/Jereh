package com.sale.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.common.dao.BaseDao;
import com.sale.dao.SaleOrder_DetailDao;
import com.sale.entity.SaleOrder_Detail;

public class SaleOrder_DetailDaoImpl extends BaseDao implements SaleOrder_DetailDao {

	@Override
	public SaleOrder_Detail findDetailByCode(String code) {
		String sql="select * from saleorder_detail sd join baseparts bp on " +
				"sd.pcode=bp.partscode  where sd.scode="+code;
		ResultSet rs=super.executeQuery(sql);
		SaleOrder_Detail sd=new SaleOrder_Detail();
		try {
			while(rs.next()){
				sd=new SaleOrder_Detail();
				sd.setsCode(rs.getString("scode"));
				sd.setPartsNo(rs.getString("partsNo"));
				sd.setPartsName(rs.getString("partsName"));
				sd.setPartsBrand(rs.getString("partsBrand"));
				sd.setPartsModel(rs.getString("partsModel"));
				sd.setsCode(rs.getString("scode"));
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


}
