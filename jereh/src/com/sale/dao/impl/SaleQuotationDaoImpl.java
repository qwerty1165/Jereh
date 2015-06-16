package com.sale.dao.impl;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;

import net.sf.json.util.NewBeanInstanceStrategy;

import com.sale.dao.SaleQuotationDao;
import com.base.entity.BaseCustomerSupplier;
import com.base.entity.BaseParts;
import com.sale.entity.SaleQuotation;
import com.sale.entity.SaleQuotation_Detail;
import com.stock.entity.Stock;
import com.common.dao.BaseDao;
import com.common.entity.PageBean;

public class SaleQuotationDaoImpl extends BaseDao implements SaleQuotationDao{

	@Override
	public PageBean findList(SaleQuotation sq,Date startDate,Date endDate, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		String sql="select sq.*,bcs.csname from salequotation sq left join basecustomersupplier bcs on sq.customercode=bcs.code where 1=1";
		if(sq.getCode()!=null&&!sq.getCode().equals("")){
			sql+=" and sq.code like "+"'%"+sq.getCode()+"%'";
		}
		if(startDate!=null){
			sql+=" and to_char(sqdate,'yyyy-mm-dd')>="+"'"+new SimpleDateFormat("yyyy-MM-dd").format(startDate)+"'";
		}
		if(endDate!=null){
			sql+=" and to_char(sqdate,'yyyy-mm-dd')<="+"'"+new SimpleDateFormat("yyyy-MM-dd").format(endDate)+"'";
		}
		if(sq.getCsName()!=null&&!sq.getCsName().equals("")){
			sql+="and bcs.csname like "+"'%"+sq.getCsName()+"%'";
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
		
		String sql1 = "delete from salequotation_detail where scode = '"+code+"'";
		String sql2 = "delete from salequotation where code='"+code+"'";
		int ret = 0;
		Connection conn=super.getConnection();
		Statement state=null;
		try {
			state=conn.createStatement();
			state.addBatch(sql1);
			state.addBatch(sql2);
			state.executeBatch();
			ret=1;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{			
				try {
					if(state!=null) state.close();
					if(conn!=null) conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		
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
                baseParts.setPartsModel(rs.getString("partsmodel"));
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

	@Override
	public List<SaleQuotation_Detail> showPartsList(SaleQuotation_Detail saleQuotation_Detail) {
		// TODO Auto-generated method stub
		String sql = "select parts.*,price,deliveryMode,remarks from salequotation_detail join " +
				"(select partscode,partsno,partsname,partsbrand,partsmodel,nums from " +
				"baseparts join stock on baseparts.partscode = stock.pcode )parts on salequotation_detail.pcode = parts.partscode";
		ResultSet rs = super.executeQuery(sql);
		List<SaleQuotation_Detail> sqdList = new ArrayList<SaleQuotation_Detail>();
		BaseParts baseParts = null;
		Stock stock = null;
		try {
			while(rs.next()){
				saleQuotation_Detail = new SaleQuotation_Detail();
				baseParts = new BaseParts();
				baseParts.setPartsNo(rs.getString("partsno"));
				baseParts.setPartsName(rs.getString("partsname"));
				baseParts.setPartsBrand(rs.getString("partsbrand"));
				baseParts.setPartsModel(rs.getString("partsmodel"));
				saleQuotation_Detail.setBaseParts(baseParts);
				stock = new Stock();
				stock.setNums(rs.getInt("nums"));
				saleQuotation_Detail.setStock(stock);
				saleQuotation_Detail.setPrice(rs.getDouble("price"));
				saleQuotation_Detail.setDeliveryMode(rs.getString("DeliveryMode"));
				saleQuotation_Detail.setRemarks(rs.getString("remarks"));
				sqdList.add(saleQuotation_Detail);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return sqdList;
	}

	@Override
	public int addSaleQuotationDetail(SaleQuotation_Detail saleQuotation_Detail) {
		// TODO Auto-generated method stub
		String sql = "insert into salequotation_detail(code, scode, pcode, nums, price, " +
				"deliverymode, remarks) values(?,?,?,?,?,?,?)";
		int ret = super.executeUpdate(sql, new Object[]{saleQuotation_Detail.getCode(),
				saleQuotation_Detail.getScode(),saleQuotation_Detail.getPcode(),
				saleQuotation_Detail.getNums(),saleQuotation_Detail.getPrice(),
				saleQuotation_Detail.getDeliveryMode(),saleQuotation_Detail.getRemarks()});
				return ret;
	}

    
	

}
