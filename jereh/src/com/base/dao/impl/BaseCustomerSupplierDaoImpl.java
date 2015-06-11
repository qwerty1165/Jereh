package com.base.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import com.base.dao.BaseCustomerSupplierDao;
import com.base.entity.BaseCustomerSupplier;
import com.common.dao.BaseDao;
import com.common.entity.PageBean;

public class BaseCustomerSupplierDaoImpl extends BaseDao implements BaseCustomerSupplierDao {
	public PageBean showAll(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		String sql="select * from basecustomersupplier";
		ResultSet rs=super.excuteQueryForPage(sql, pageNo, pageSize);
		PageBean pageBean=new PageBean();
		List<BaseCustomerSupplier> list=new ArrayList<BaseCustomerSupplier>();
		BaseCustomerSupplier baseCustomerSupplier=null;
		try {
			while(rs.next()){
				baseCustomerSupplier=new BaseCustomerSupplier();
				baseCustomerSupplier.setCode(rs.getString("code"));
				baseCustomerSupplier.setCsName(rs.getString("csname"));
				baseCustomerSupplier.setCategorycode(rs.getString("categorycode"));
				baseCustomerSupplier.setTelephone(rs.getString("telephone"));
				baseCustomerSupplier.setAddress(rs.getString("address"));
				baseCustomerSupplier.setIsShow(rs.getString("isshow"));
				list.add(baseCustomerSupplier);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		pageBean.setData(list);
		pageBean.setRecordCount(super.executeTotalCount(sql));

		return pageBean;
	}
	
	public PageBean findList(BaseCustomerSupplier bcs,int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		String sql="select * from basecustomersupplier where 1=1";
		if(bcs.getCode()!=null&&!bcs.getCode().equals("")){
			sql+=" and code like "+"'%"+bcs.getCode()+"%'";
		}
		if(bcs.getCsName()!=null&&!bcs.getCsName().equals("")){
			sql+=" and csName like "+"'%"+bcs.getCsName()+"%'";
		}
		if(bcs.getAddDate()!=null&&!bcs.getAddDate().equals("")){
			sql+=" and to_char(addDate,'yyyy-mm-dd')="+"'"+new SimpleDateFormat("yyyy-MM-dd").format(bcs.getAddDate())+"'";
		}
		
		ResultSet rs=super.excuteQueryForPage(sql, pageNo, pageSize);
		PageBean pageBean=new PageBean();
		List<BaseCustomerSupplier> list=new ArrayList<BaseCustomerSupplier>();
		BaseCustomerSupplier baseCustomerSupplier=null;
		try {
			while(rs.next()){
				baseCustomerSupplier=new BaseCustomerSupplier();
				baseCustomerSupplier.setCode(rs.getString("code"));			
				baseCustomerSupplier.setAddDate(rs.getDate("adddate"));
				baseCustomerSupplier.setCsName(rs.getString("csname"));
				baseCustomerSupplier.setCategorycode(rs.getString("categorycode"));
				baseCustomerSupplier.setContacter(rs.getString("contacter"));
				baseCustomerSupplier.setTelephone(rs.getString("telephone"));
				baseCustomerSupplier.setAddress(rs.getString("address"));
				baseCustomerSupplier.setIsShow(rs.getString("isshow"));
				baseCustomerSupplier.setFax(rs.getString("fax"));
				baseCustomerSupplier.setPostCode(rs.getString("postcode"));
				baseCustomerSupplier.setEmail(rs.getString("email"));
				baseCustomerSupplier.setProvince(rs.getString("province"));
				baseCustomerSupplier.setCity(rs.getString("city"));
				baseCustomerSupplier.setLegaler(rs.getString("legaler"));
				baseCustomerSupplier.setUrl(rs.getString("url"));
				baseCustomerSupplier.setQQ(rs.getString("QQ"));
				baseCustomerSupplier.setMSN(rs.getString("MSN"));
				baseCustomerSupplier.setAliwang(rs.getString("aliwang"));
				baseCustomerSupplier.setAgent(rs.getString("agent"));
				baseCustomerSupplier.setBank(rs.getString("bank"));
				baseCustomerSupplier.setAccount(rs.getString("account"));
				baseCustomerSupplier.setTax(rs.getString("tax"));
				baseCustomerSupplier.setRemarks(rs.getString("remarks"));
				list.add(baseCustomerSupplier);
			}
		} catch (SQLException e) {		
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		pageBean.setData(list);
		pageBean.setRecordCount(super.executeTotalCount(sql));

		return pageBean;
	}
	

	public int addCustomer(BaseCustomerSupplier baseCustomerSupplier) {
		// TODO Auto-generated method stub
		String sql="insert into basecustomersupplier(code,adddate,csname,contacter,telephone,fax," +
				"postcode,email,province,city,address,legaler,url,qq,msn,aliwang,agent,bank,account," +
				"tax, categorycode, isshow,remarks)values(?,sysdate,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int ret=super.executeUpdate(sql, new Object[]{baseCustomerSupplier.getCode(),
				baseCustomerSupplier.getCsName(),baseCustomerSupplier.getContacter(),baseCustomerSupplier.getTelephone(),
				baseCustomerSupplier.getFax(),baseCustomerSupplier.getPostCode(),baseCustomerSupplier.getEmail(),
				baseCustomerSupplier.getProvince(),baseCustomerSupplier.getCity(),baseCustomerSupplier.getAddress(),
				baseCustomerSupplier.getLegaler(),baseCustomerSupplier.getUrl(),baseCustomerSupplier.getQQ(),
				baseCustomerSupplier.getMSN(),baseCustomerSupplier.getAliwang(),baseCustomerSupplier.getAgent(),
				baseCustomerSupplier.getBank(),baseCustomerSupplier.getAccount(),baseCustomerSupplier.getTax(),
				baseCustomerSupplier.getCategorycode(),baseCustomerSupplier.getIsShow(),baseCustomerSupplier.getRemarks()});
	
		return ret;
	}

	public int update(BaseCustomerSupplier baseCustomerSupplier) {
		// TODO Auto-generated method stub
		String sql="update basecustomersupplier set contacter=?,telephone=?,fax=?,postcode=?,email=?,province=?," +
				"city=?,address=?,legaler=?,url=?,qq=?,msn=?,aliwang=?,agent=?,bank=?,account=?,tax=?," +
				"categorycode=?,isshow=?,remarks=?where code=?";
		int ret=super.executeUpdate(sql, new Object[]{baseCustomerSupplier.getContacter(),baseCustomerSupplier.getTelephone(),
				baseCustomerSupplier.getFax(),baseCustomerSupplier.getPostCode(),baseCustomerSupplier.getEmail(),
				baseCustomerSupplier.getProvince(),baseCustomerSupplier.getCity(),baseCustomerSupplier.getAddress(),
				baseCustomerSupplier.getLegaler(),baseCustomerSupplier.getUrl(),baseCustomerSupplier.getQQ(),
				baseCustomerSupplier.getMSN(),baseCustomerSupplier.getAliwang(),baseCustomerSupplier.getAgent(),
				baseCustomerSupplier.getBank(),baseCustomerSupplier.getAccount(),baseCustomerSupplier.getTax(),
				baseCustomerSupplier.getCategorycode(),baseCustomerSupplier.getIsShow(),baseCustomerSupplier.getRemarks(),
				baseCustomerSupplier.getCode()});
		return ret;
	}

	public int deleteCustomer(String code) {		
		String sql="delete from basecustomersupplier where code=?";
		int ret=super.executeUpdate(sql,new Object[]{code});
		return ret;
	}

	public BaseCustomerSupplier findBaseCustomerSupplier(String code) {
		// TODO Auto-generated method stub
		String sql="select * from basecustomersupplier where code=?";
		ResultSet rs= super.executeQuery(sql, code);
		BaseCustomerSupplier baseCustomerSupplier=new BaseCustomerSupplier();
		try {
			while (rs.next()) {
				baseCustomerSupplier=new BaseCustomerSupplier();
				baseCustomerSupplier.setCode(rs.getString("code"));			
				baseCustomerSupplier.setAddDate(rs.getDate("adddate"));
				baseCustomerSupplier.setCsName(rs.getString("csname"));
				baseCustomerSupplier.setCategorycode(rs.getString("categorycode"));
				baseCustomerSupplier.setContacter(rs.getString("contacter"));
				baseCustomerSupplier.setTelephone(rs.getString("telephone"));
				baseCustomerSupplier.setAddress(rs.getString("address"));
				baseCustomerSupplier.setIsShow(rs.getString("isshow"));
				baseCustomerSupplier.setFax(rs.getString("fax"));
				baseCustomerSupplier.setPostCode(rs.getString("postcode"));
				baseCustomerSupplier.setEmail(rs.getString("email"));
				baseCustomerSupplier.setProvince(rs.getString("province"));
				baseCustomerSupplier.setCity(rs.getString("city"));
				baseCustomerSupplier.setLegaler(rs.getString("legaler"));
				baseCustomerSupplier.setUrl(rs.getString("url"));
				baseCustomerSupplier.setQQ(rs.getString("QQ"));
				baseCustomerSupplier.setMSN(rs.getString("MSN"));
				baseCustomerSupplier.setAliwang(rs.getString("aliwang"));
				baseCustomerSupplier.setAgent(rs.getString("agent"));
				baseCustomerSupplier.setBank(rs.getString("bank"));
				baseCustomerSupplier.setAccount(rs.getString("account"));
				baseCustomerSupplier.setTax(rs.getString("tax"));
				baseCustomerSupplier.setRemarks(rs.getString("remarks"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return baseCustomerSupplier;
	}
	

}
