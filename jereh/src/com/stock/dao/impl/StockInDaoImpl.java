package com.stock.dao.impl;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.common.dao.BaseDao;
import com.common.entity.PageBean;
import com.stock.dao.StockInDao;
import com.stock.entity.StockIn;

public class StockInDaoImpl extends BaseDao implements StockInDao {	
	@Override
	public PageBean findByCondition(String code, String supplierName,
			Date startDate, Date endDate, int pageNo, int pageSize) {
		String sql="select si.*, bcs.csname suppliername from stockin si " +
				"left join basecustomersupplier bcs on " +
				"si.suppliercode=bcs.code " +
				"where 1=1  ";
		if(code!=null){
			sql+=" and si.code like "+"'%"+code+"%'";
		}
		if(supplierName!=null){
			sql+=" and supplierName like "+"'%"+supplierName+"%'";
		}
		if(startDate!=null){
			sql+=" and to_char(inDate,'yyyy-mm-dd')>="+"'"+new SimpleDateFormat("yyyy-MM-dd").format(startDate)+"'";
		}
		if(endDate!=null){
			sql+=" and to_char(inDate,'yyyy-mm-dd')<="+"'"+new SimpleDateFormat("yyyy-MM-dd").format(endDate)+"'";
		}
		ResultSet rs=super.excuteQueryForPage(sql, pageNo, pageSize);
		PageBean pb=new PageBean();
		List<StockIn> list=new ArrayList<StockIn>();		
		StockIn si=null;
		try {
			while(rs.next()){				
				si=new StockIn();
				si.setCode(rs.getString("code"));
				si.setInDate(new Date(rs.getDate("indate").getTime()));
				si.setSupplierCode(rs.getString("suppliercode"));
				si.setSupplierName(rs.getString("suppliername"));
				si.setContacter(rs.getString("contacter"));
				si.setTelphone(rs.getString("telphone"));
				si.setFax(rs.getString("fax"));
				si.setInType(rs.getString("inType"));
				si.setIsRoad(rs.getString("isRoad"));
				si.setIsInvoice(rs.getString("isInvoice"));
				si.setRemarks(rs.getString("remarks"));
				si.setIsShow(rs.getString("isShow"));
				si.setNums(rs.getInt("nums"));
				si.setNumsPrice(rs.getDouble("numsPrice"));				
				si.setState(rs.getString("state"));
				si.setCompCode(rs.getString("compCode"));
				si.setAddDate(new Date(rs.getDate("adddate").getTime()));
				si.setAddUser(rs.getString("addUser"));
				si.setAddUserName(rs.getString("addusername"));
				si.setAddIp(rs.getString("addip"));				
				list.add(si);
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		pb.setData(list);
		pb.setRecordCount(super.executeTotalCount(sql));
		return pb;
	}

	@Override
	public int delete(String code) {			
		int ret=0;
		String sql1="delete from stockin where code='"+code+"'";
		String sql2="delete from stockin_detail where incode='"+code+"'";
		Connection conn=super.getConnection();
		Statement state=null;
		try{
			state=conn.createStatement();
			state.addBatch(sql2);
			state.addBatch(sql1);
			state.executeBatch();
			ret=1;
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try{
				if(state!=null)state.close();
				if(conn!=null)conn.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return ret;
	}

	@Override
	public int insert(StockIn in) {
		String sql="insert into stockin (code,indate,suppliercode," +//1
				"contacter,telphone,fax," +//2
				"intype,isroad,isinvoice," +//3
				"remarks,isshow,nums," +//4
				"numsprice,state,compcode," +//5
				"adddate,adduser,addusername," +//6
				"addip) values (?,?,?," +//1
				"?,?,?," +//2
				"?,?,?," +//3
				"?,?,?," +//4
				"?,?,?," +//5
				"sysdate,?,?," +//6
				"?)";
		return super.executeUpdate(sql, new Object[]{in.getCode(),new java.sql.Date(in.getInDate().getTime()),in.getSupplierCode(),
				in.getContacter(),in.getTelphone(),in.getFax(),
				in.getInType(),in.getIsRoad(),in.getIsInvoice(),
				in.getRemarks(),in.getIsShow(),in.getNums(),
				in.getNumsPrice(),in.getState(),in.getCompCode(),
				in.getAddUser(),in.getAddUserName(),
				in.getAddIp()});
	}

	@Override
	public int update(StockIn in) {
		String sql="update stockin set indate=?,suppliercode=?," +
				"contacter=?,telphone=?,fax=?," +
				"intype=?,isroad=?,isinvoice=?," +
				"remarks=?,isshow=?," +
				"adddate=sysdate " +
				"where code=?";
		return super.executeUpdate(sql, new Object[]{new java.sql.Date(in.getInDate().getTime()),in.getSupplierCode(),
				in.getContacter(),in.getTelphone(),in.getFax(),
				in.getInType(),in.getIsRoad(),in.getIsInvoice(),
				in.getRemarks(),in.getIsShow(),
				in.getCode()});
	}

	

}
