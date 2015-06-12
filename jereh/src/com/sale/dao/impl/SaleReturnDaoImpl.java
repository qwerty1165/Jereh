package com.sale.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.common.dao.BaseDao;
import com.common.entity.PageBean;
import com.sale.dao.SaleReturnDao;
import com.sale.entity.SaleReturn;

public class SaleReturnDaoImpl extends BaseDao implements SaleReturnDao {

	@Override
	public int findMaxCode() {
		// TODO Auto-generated method stub
		String sql = "select max(CODE) from SALERETURN";
		int ret = super.executeQueryForNewId(sql);
		return ret;
	}

	@Override
	public PageBean findAll(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		String sql = "select * from SALERETURN order by CODE";
		ResultSet rs = super.excuteQueryForPage(sql, pageNo, pageSize);
		PageBean pageBean = new PageBean();
		List<SaleReturn> saleReturnList = new ArrayList<SaleReturn>();
		SaleReturn saleReturn = null;
		try {
			while (rs.next()) {
				saleReturn = new SaleReturn();
				saleReturn.setCode(rs.getString("CODE"));
				saleReturn.setXtDate(rs.getDate("XTDATE"));
				saleReturn.setCustomerCode(rs.getString("CUSTOMERCODE"));
				saleReturn.setContacter(rs.getString("CONTACTER"));
				saleReturn.setTelPhone(rs.getString("TELPHONE"));
				saleReturn.setFax(rs.getString("FAX"));
				saleReturn.setRemarks(rs.getString("REMARKS"));
				saleReturn.setIsShow(rs.getString("ISSHOW"));
				saleReturn.setNums(rs.getInt("NUMS"));
				saleReturn.setNumSprice(rs.getDouble("NUMSPRICE"));
				saleReturn.setState(rs.getString("STATE"));
				saleReturn.setCompCode(rs.getString("COMPCODE"));
				saleReturn.setAddDate(rs.getDate("ADDDATE"));
				saleReturn.setAddUser(rs.getString("ADDUSER"));
				saleReturn.setAddUserName(rs.getString("ADDUSERNAME"));
				saleReturn.setAddIp(rs.getString("ADDIP"));
				saleReturnList.add(saleReturn);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pageBean.setData(saleReturnList);
		//sql = "select count(*) from SALERETURN";
		int total = super.executeTotalCount(sql);
		pageBean.setRecordCount(total);
		return pageBean;
	}

	@Override
	public int insert(SaleReturn saleReturn) {
		// TODO Auto-generated method stub
		String sql = "insert into SALERETURN(CODE,XTDATE,CUSTOMERCODE,CONTACTER,TELPHONE,FAX,REMARKS,ISSHOW,NUMS,NUMSPRICE,STATE,COMPCODE,ADDDATE,ADDUSER,ADDUSERNAME,ADDIP) values(?,?,?,?,?,?,?,?,?,?,?,?,sysdate,?,?,?)";
		int ret = super.executeUpdate(sql,
				new Object[] { saleReturn.getCode(), saleReturn.getXtDate(),
						saleReturn.getCustomerCode(),
						saleReturn.getContacter(), saleReturn.getTelPhone(),
						saleReturn.getFax(), saleReturn.getRemarks(),
						saleReturn.getIsShow(), saleReturn.getNums(),
						saleReturn.getNumSprice(), saleReturn.getState(),
						saleReturn.getCompCode(), saleReturn.getAddUser(),
						saleReturn.getAddUserName(), saleReturn.getAddIp() });
		if (ret == 1) {
			ret = this.findMaxCode();
		}
		return ret;
	}

	@Override
	public int update(SaleReturn saleReturn, String code) {
		// TODO Auto-generated method stub
		//String sql = "update SALERETURN set XTDATE=?,CUSTOMERCODE=?,CONTACTER=?,TELPHONE=?,FAX=?,REMARKS=?,ISSHOW=?,NUMS=?,NUMSPRICE=?,STATE=?,COMPCODE=?,ADDDATE=sysdate,ADDUSER=?,ADDUSERNAME=?,ADDIP=? where CODE=?";
		String sql = "update SALERETURN set CONTACTER=?,TELPHONE=?,FAX=?,REMARKS=? where CODE=?";
		return super.executeUpdate(
				sql,
				new Object[] { 
						//saleReturn.getXtDate(),
						//saleReturn.getCustomerCode(),
						saleReturn.getContacter(), 
						saleReturn.getTelPhone(),
						saleReturn.getFax(), 
						saleReturn.getRemarks(),
//						saleReturn.getIsShow(), 
//						saleReturn.getNums(),
//						saleReturn.getNumSprice(), saleReturn.getState(),
//						saleReturn.getCompCode(), saleReturn.getAddUser(),
//						saleReturn.getAddUserName(), saleReturn.getAddIp(),
						code });
	}

	@Override
	public int delete(String code) {
		// TODO Auto-generated method stub
		String sql = "delete SALERETURN where code=?";
		return super.executeUpdate(sql, new Object[] { code });
	}

	@Override
	public SaleReturn findByCode(String code) {
		// TODO Auto-generated method stub
		SaleReturn saleReturn = null;
		String sql = "select * from SALERETURN where code=?";
		ResultSet rs = super.executeQuery(sql, code);
		try {
			while (rs.next()) {
				saleReturn = new SaleReturn();
				saleReturn.setCode(rs.getString("CODE"));
				saleReturn.setXtDate(rs.getDate("XTDATE"));
				saleReturn.setCustomerCode(rs.getString("CUSTOMERCODE"));
				saleReturn.setContacter(rs.getString("CONTACTER"));
				saleReturn.setTelPhone(rs.getString("TELPHONE"));
				saleReturn.setFax(rs.getString("FAX"));
				saleReturn.setRemarks(rs.getString("REMARKS"));
				saleReturn.setIsShow(rs.getString("ISSHOW"));
				saleReturn.setNums(rs.getInt("NUMS"));
				saleReturn.setNumSprice(rs.getDouble("NUMSPRICE"));
				saleReturn.setState(rs.getString("STATE"));
				saleReturn.setCompCode(rs.getString("COMPCODE"));
				saleReturn.setAddDate(rs.getDate("ADDDATE"));
				saleReturn.setAddUser(rs.getString("ADDUSER"));
				saleReturn.setAddUserName(rs.getString("ADDUSERNAME"));
				saleReturn.setAddIp(rs.getString("ADDIP"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return saleReturn;
	}

}
