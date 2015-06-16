package com.stock.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.base.entity.BaseParts;
import com.common.dao.BaseDao;
import com.stock.dao.StockOutDetailDao;
import com.stock.entity.StockInDetail;
import com.stock.entity.StockOutDetail;

public class StockOutDetailDaoImpl extends BaseDao implements StockOutDetailDao {

	@Override
	public List<StockOutDetail> findStockOutDetailList(String outCode) {
		String sql="select d.*,partsname,partsbrand,partsmodel " +
				"from stockout_detail d left join baseparts bp " +
				"on d.pcode=bp.partscode " +				
				"where outcode=?";
		ResultSet rs=super.executeQuery(sql,outCode);
		List<StockOutDetail> list=new ArrayList<StockOutDetail>();
		StockOutDetail sod=null;
		try {
			while(rs.next()){
				sod=new StockOutDetail();
				sod.setCode(rs.getString("code"));
				sod.setOutCode(rs.getString("outCode"));
				sod.setXsCode(rs.getString("xsCode"));//销售单号
				sod.setpCode(rs.getString("pCode"));//件号
				sod.setNums(rs.getInt("nums"));
				sod.setWnums(rs.getInt("wnums"));//库存量
				sod.setPrice(rs.getDouble("price"));
				sod.setWareHouse(rs.getString("wareHouse"));
				sod.setRemarks(rs.getString("remarks"));
				sod.setLastPrice(rs.getDouble("lastPrice"));
				BaseParts baseParts=new BaseParts();
				baseParts.setPartsName(rs.getString("partsname"));
				baseParts.setPartsBrand(rs.getString("partsbrand"));
				baseParts.setPartsModel(rs.getString("partsmodel"));
				sod.setBaseParts(baseParts);
				list.add(sod);
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return list;
	}

}
