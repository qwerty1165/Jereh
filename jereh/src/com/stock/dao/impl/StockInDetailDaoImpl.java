package com.stock.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.base.entity.BaseParts;
import com.common.dao.BaseDao;
import com.stock.dao.StockInDetailDao;
import com.stock.entity.StockInDetail;

public class StockInDetailDaoImpl extends BaseDao implements StockInDetailDao {
	@Override
	public List<StockInDetail> findStockInDetailList(String inCode) {
		String sql="select d.*,partsname,partsbrand,partsmodel from stockin_detail d " +
				"left join baseparts bp " +
				"on d.pcode=bp.partscode " +
				"where incode= ?";
		ResultSet rs=super.executeQuery(sql,inCode);
		List<StockInDetail> list=new ArrayList<StockInDetail>();
		StockInDetail sid=null;
		try {
			while(rs.next()){
				sid=new StockInDetail();
				sid.setCode(rs.getString("code"));
				sid.setInCode(rs.getString("inCode"));
				sid.setOrderCode(rs.getString("orderCode"));
				sid.setpCode(rs.getString("pCode"));
				sid.setNums(rs.getInt("nums"));
				sid.setPrice(rs.getDouble("price"));
				sid.setWareHouse(rs.getString("wareHouse"));
				sid.setRemarks(rs.getString("remarks"));
				BaseParts baseParts=new BaseParts();
				baseParts.setPartsName(rs.getString("partsname"));
				baseParts.setPartsBrand(rs.getString("partsbrand"));
				baseParts.setPartsModel(rs.getString("partsmodel"));
				sid.setBaseParts(baseParts);
				list.add(sid);
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return list;
	}

}
