package com.base.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.base.dao.BasePartsDao;
import com.base.entity.BaseParts;
import com.common.dao.BaseDao;
import com.common.entity.PageBean;

public class BasePartsDaoImpl extends BaseDao implements BasePartsDao {

	/** 
	 * 查询配件信息
	 * */

	@Override
	public PageBean findBaseParts(BaseParts bp, int pageNo, int pageSize) {
		String sql="select * from baseparts where 1=1 ";
		PageBean pageBean=new PageBean();

		if(bp.getPartsNo()!=null&&!bp.getPartsNo().equals("")){
			sql+=" and partsno like "+"'%"+bp.getPartsNo()+"%'";}
		 if(bp.getPartsName()!=null&&!bp.getPartsName().equals("")){
			sql+=" and partsname like"+"'%"+bp.getPartsName()+"%'";	}
		if(bp.getPartsCategory()!=null&&!bp.getPartsCategory().equals("")){
			sql+=" and partsCategory like"+"'%"+bp.getPartsCategory()+"%'";
		}
		ResultSet rs=super.excuteQueryForPage(sql,pageNo,pageSize);
		List<BaseParts> basePartsList=new ArrayList<BaseParts>();
		BaseParts baseParts=null;
		try {
			while(rs.next()){
				baseParts=new BaseParts();
				baseParts.setPartsCode(rs.getString("partscode"));
				baseParts.setPartsNo(rs.getString("PartsNo"));
				baseParts.setPartsCategory(rs.getString("partsCategory"));
				baseParts.setPartsName(rs.getString("partsName"));
				baseParts.setPartsBrand(rs.getString("partsBrand"));
				baseParts.setPartsModel(rs.getString("partsModel"));
				baseParts.setPartsModelOld(rs.getString("partsModelOld"));
				baseParts.setSalePrice(rs.getInt("salePrice"));
				baseParts.setAddUser(rs.getString("addUser"));
				baseParts.setIsShow(rs.getString("isShow"));
				baseParts.setRemarks(rs.getString("remarks"));
				baseParts.setPartsGeneralPartsNo(rs.getString("partsGeneralPartsNo"));
				baseParts.setPartsSize(rs.getString("partsSize"));
				baseParts.setPartsWeight(rs.getString("partsWeight"));
				baseParts.setPartsUnit(rs.getString("partsUnit"));
				basePartsList.add(baseParts);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		pageBean.setData(basePartsList);
		sql="select count(partscode) from baseParts";
		pageBean.setRecordCount(super.executeTotalCount(sql));
		return pageBean;
	
		}
		
//	@Override
//	public PageBean findBaseParts(int pageNo, int pageSize) {
//		
//		String sql="select * from baseparts";
//		ResultSet rs=super.executeQueryForPage(sql,pageNo,pageSize);
//		PageBean pageBean=new PageBean();
//		List<BaseParts> basePartsList=new ArrayList<BaseParts>();
//		BaseParts baseParts=null;
//		try {
//			while(rs.next()){
//				baseParts=new BaseParts();
//				baseParts.setPartsCode(rs.getString("partscode"));
//				baseParts.setPartsNo(rs.getString("PartsNo"));
//				baseParts.setPartsCategory(rs.getString("partsCategory"));
//				baseParts.setPartsName(rs.getString("partsName"));
//				baseParts.setPartsBrand(rs.getString("partsBrand"));
//				baseParts.setPartsModel(rs.getString("partsModel"));
//				baseParts.setPartsModelOld(rs.getString("partsModelOld"));
//				baseParts.setSalePrice(rs.getInt("salePrice"));
//				baseParts.setAddUser(rs.getString("addUser"));
//				baseParts.setIsShow(rs.getString("isShow"));
//				baseParts.setRemarks(rs.getString("remarks"));
//				basePartsList.add(baseParts);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally{
//			super.closeAll();
//		}
//		pageBean.setData(basePartsList);
//		sql="select count(partscode) from baseParts";
//		pageBean.setRecordCount(super.executeTotalCount(sql));
//		return pageBean;
//	}

	/** 
	 * 插入配件信息
	 * */
	@Override
	public int insertBaseParts(BaseParts baseParts) {
		String sql="insert into baseparts(partsBrand,partsModel,partsModelOld,salePrice,addUser," +
				"remarks,partsName,partsCategory,PartsNo,partscode,isShow)values(?,?,?,?,?,?,?,?,?,?,?)";
		int ret= super.executeUpdate(sql,new Object[]{baseParts.getPartsBrand(),baseParts.getPartsModel(),
				baseParts.getPartsModelOld(),baseParts.getSalePrice(),baseParts.getAddUser(),baseParts.getRemarks(),
				baseParts.getPartsName(),baseParts.getPartsCategory(),baseParts.getPartsNo(),baseParts.getPartsCode(),
				baseParts.getIsShow()});
		return ret;
	}

	/** 
	 * 删除配件信息
	 * */
	@Override
	public int deleteBaseParts(String partsCode) {
		String sql="delete from baseparts where partscode=?";
		int ret=super.executeUpdate(sql, new Object[]{partsCode});
		return ret;
	}

	/** 
	 * 修改配件信息
	 * */
	@Override
	public int update(BaseParts baseParts) {
		String sql="update baseparts set partsBrand=?,partsModel=?,partsModelOld=?,salePrice=?," +
				"addUser=?,partsSize=?,partsWeight=?,remarks=?,partsName=?,partsCategory=?,PartsNo=?," +
				"partscode=?,isShow=? where partscode=?";
		return super.executeUpdate(sql,new Object[]{baseParts.getPartsBrand(),baseParts.getPartsModel(),
				baseParts.getPartsModelOld(),baseParts.getSalePrice(),baseParts.getAddUser(),baseParts.getPartsSize(),
				baseParts.getPartsWeight(),baseParts.getRemarks(),
				baseParts.getPartsName(),baseParts.getPartsCategory(),baseParts.getPartsNo(),baseParts.getPartsCode(),
				baseParts.getIsShow(),baseParts.getPartsCode()});
	}


}
