package com.stock.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
		String sql="select d.*,partsname,partsbrand,partsmodel,SALEPRICE from stockin_detail d  " +
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
				baseParts.setSalePrice(rs.getDouble("salePrice"));
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

	@Override
	public int insertBatchDetail(List<StockInDetail> detailList) {
		int ret=0;
		String sql="insert into stockin_detail (" +
				"code,incode,ordercode,pcode,nums,price,warehouse,remarks) values(" +
				"?,?,?,?,?,?,?,?)";
		Connection conn=super.getConnection();
		PreparedStatement pstm=null;
		try {
			pstm=conn.prepareStatement(sql);
			for (StockInDetail detail : detailList) {
				pstm.setString(1,detail.getCode());//�����ϸ����
				pstm.setString(2,detail.getInCode());//��ⵥ�ݱ��
				pstm.setString(3,detail.getOrderCode());//�������
				pstm.setString(4,detail.getpCode());//������
				pstm.setInt(5,detail.getNums());//�������
				pstm.setDouble(6, detail.getPrice());//�������
				pstm.setString(7,detail.getWareHouse());//�����ֿ�
				pstm.setString(8, detail.getRemarks());//��ע
				pstm.addBatch();
			}
			pstm.executeBatch();
			ret=1;
		} catch (SQLException e) {			
			e.printStackTrace();
		}finally{
			try {				
				if(pstm!=null)pstm.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		}
		return ret;
	}

	@Override
	public int delete(String code) {
		String sql="delete from stockin_detail where code=?";
		return super.executeUpdate(sql,code);
	}
	
}
