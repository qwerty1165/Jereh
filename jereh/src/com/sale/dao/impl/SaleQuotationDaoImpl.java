package com.sale.dao.impl;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sale.dao.SaleQuotationDao;
import com.base.entity.BaseCustomerSupplier;
import com.base.entity.BaseParts;
import com.sale.entity.SaleQuotation;
import com.sale.entity.SaleQuotation_Detail;
import com.common.dao.BaseDao;
import com.common.entity.PageBean;

public class SaleQuotationDaoImpl extends BaseDao implements SaleQuotationDao{

	@Override
	public PageBean findList(SaleQuotation sq, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		String sql="select sq.*,bcs.csname from salequotation sq left join basecustomersupplier bcs on sq.customercode=bcs.code where 1=1";
		if(sq.getCode()!=null&&!sq.getCode().equals("")){
			sql+=" and sq.code like "+"'%"+sq.getCode()+"%'";
		}
		ResultSet rs=super.excuteQueryForPage(sql, pageNo, pageSize);
		PageBean pageBean=new PageBean();
		List<SaleQuotation> sqList = new ArrayList<SaleQuotation>();
		SaleQuotation saleQuotation = null ;
		try {
			while(rs.next()){
				saleQuotation = new SaleQuotation();
				saleQuotation.setCode(rs.getString("code"));
				saleQuotation.setSqDate(rs.getDate("sqdate"));
				saleQuotation.setCsName(rs.getString("csname"));
				saleQuotation.setCustomercode(rs.getString("customercode"));
				saleQuotation.setNums(rs.getInt("nums"));
				saleQuotation.setNumsPrice(rs.getDouble("numsprice"));
				saleQuotation.setContacter(rs.getString("contacter"));
				saleQuotation.setTelphone(rs.getString("telphone"));
				saleQuotation.setFax(rs.getString("fax"));
				saleQuotation.setState(rs.getString("state"));
				saleQuotation.setAddUser(rs.getString("adduser"));
				saleQuotation.setRemarks(rs.getString("remarks"));
				sqList.add(saleQuotation);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			super.closeAll();
		}
		pageBean.setData(sqList);
		pageBean.setRecordCount(super.executeTotalCount(sql));

		return pageBean;
		
	}

	@Override
	public int deleteSaleQuotation(String code) {
		// TODO Auto-generated method stub
		String sql = "delete from salequotation where code=?";
		int ret=super.executeUpdate(sql,new Object[]{code});
		return ret;
	}

	@Override
	public List<SaleQuotation_Detail> showDetail(String scode) {
		// TODO Auto-generated method stub
		String sql = "select partsno,partsname,partsbrand,partsmodel,salequotation_detail.*from baseparts right join salequotation_detail " +
				"on baseparts.partscode = salequotation_detail.pcode where " +
				"salequotation_detail.scode =?";
		ResultSet rs = super.executeQuery(sql,new Object[]{scode});
		List<SaleQuotation_Detail> list = new ArrayList<SaleQuotation_Detail>();
		SaleQuotation_Detail sq = null;
		try {
			while(rs.next()){
				sq = new SaleQuotation_Detail();
				BaseParts baseParts = new BaseParts();
				baseParts.setPartsNo(rs.getString("partsno"));
				baseParts.setPartsName(rs.getString("partsname"));
				baseParts.setPartsBrand(rs.getString("partsbrand"));
				sq.setBaseParts(baseParts);
				sq.setCode(rs.getString("code"));
				sq.setScode(rs.getString("scode"));
				sq.setPcode(rs.getString("pcode"));
				sq.setNums(rs.getInt("nums"));
				sq.setPrice(rs.getDouble("price"));
				sq.setDeliveryMode(rs.getString("deliveryMode"));
				sq.setRemarks(rs.getString("remarks"));
				list.add(sq);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return list;
	}

	@Override
	public int updateSaleQuotation(SaleQuotation saleQuotation) {
		// TODO Auto-generated method stub	
		String sql="update salequotation set sqdate=?,customercode=?,contacter=?,telphone=?,fax=?,remarks=?where code=?";
		int ret = super.executeUpdate(sql,new Object[]{new java.sql.Date(saleQuotation.getSqDate().getTime()),saleQuotation.getCustomercode(),
				saleQuotation.getContacter(),saleQuotation.getTelphone(),
				saleQuotation.getFax(),saleQuotation.getRemarks(),saleQuotation.getCode()});	
		return ret;
	}

	@Override
	public int addSaleQuotation(SaleQuotation saleQuotation) {
		// TODO Auto-generated method stub
		String sql = "insert into salequotation(code,sqdate,customercode,contacter,telphone," +
				"fax,remarks)values(?,?,?,?,?,?,?)";
		int ret = super.executeUpdate(sql, new Object[]{saleQuotation.getCode(),new java.sql.Date(saleQuotation.getSqDate().getTime()),
				saleQuotation.getCustomercode(),saleQuotation.getContacter(),saleQuotation.getTelphone(),
				saleQuotation.getFax(),saleQuotation.getRemarks()});
		return ret;
	}


	

}
