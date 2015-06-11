package com.base.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.base.dao.BaseCompanyDao;
import com.base.entity.BaseCompany;
import com.common.dao.BaseDao;

public class BaseCompanyDaoImpl extends BaseDao implements BaseCompanyDao {

	public BaseCompany selectBaseCompany() {
		// TODO Auto-generated method stub
		String sql = "select * from BaseCompany";
		ResultSet rs = null;
		BaseCompany baseCompany = null;
		try {
			rs = super.executeQuery(sql);
			if(rs.next()){
				baseCompany = new BaseCompany();
				baseCompany.setCode(rs.getString("code"));
				baseCompany.setCompName(rs.getString("compName"));
				baseCompany.setCompAddress(rs.getString("compAddress"));
				baseCompany.setCompPostCode(rs.getString("compPostCode"));
				baseCompany.setCompPhone(rs.getString("compPhone"));
				baseCompany.setCompFax(rs.getString("compFax"));
				baseCompany.setCompUrl(rs.getString("compUrl"));
				baseCompany.setCompEmail(rs.getString("compEmail"));
				baseCompany.setCompLegaler(rs.getString("CompLegaler"));
				baseCompany.setCompAgent(rs.getString("compAgent"));
				baseCompany.setAddUser(rs.getString("addUser"));
				baseCompany.setCompBank(rs.getString("compBank"));
				baseCompany.setCompAccount(rs.getString("compAccount"));
				baseCompany.setRemarks(rs.getString("remarks"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return baseCompany;
	}

	public int updateBaseCompany(BaseCompany baseCompany) {
		// TODO Auto-generated method stub
		String sql = "update baseCompany set compName=?,compAddress=?,compPostCode=?," +
				"compPhone=?,compFax=?,compUrl=?,compEmail=?,compLegaler=?,compAgent=?," +
				"addUser=?,compBank=?,compTax=?,remarks=? where code=?";
		return super.executeUpdate(sql, new Object[]{baseCompany.getCompName(),baseCompany.getCompAddress(),
				baseCompany.getCompPostCode(),baseCompany.getCompPhone(),baseCompany.getCompFax(),
				baseCompany.getCompUrl(),baseCompany.getCompEmail(),baseCompany.getCompLegaler(),
				baseCompany.getCompAgent(),baseCompany.getAddUser(),baseCompany.getCompBank(),
				baseCompany.getCompTax(),baseCompany.getRemarks(),baseCompany.getCode()});
	}

}
