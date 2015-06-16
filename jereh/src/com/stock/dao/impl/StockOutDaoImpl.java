package com.stock.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.common.dao.BaseDao;
import com.common.entity.PageBean;
import com.stock.dao.StockOutDao;
import com.stock.entity.StockOut;

public class StockOutDaoImpl extends BaseDao implements StockOutDao {

	@Override
	public PageBean findByCondition(String code, String customerName,
			Date startDate, Date endDate, int pageNo, int pageSize) {
		String sql="select so.*, bcs.csname customername from stockout so " +
				"left join basecustomersupplier bcs on " +
				"so.CUSTOMERCODE=bcs.code " +
				"where 1=1  ";
		if(code!=null){
			sql+=" and so.code like "+"'%"+code+"%'";
		}
		if(customerName!=null){
			sql+=" and customername like "+"'%"+customerName+"%'";
		}
		if(startDate!=null){
			sql+=" and to_char(inDate,'yyyy-mm-dd')>="+"'"+new SimpleDateFormat("yyyy-MM-dd").format(startDate)+"'";
		}
		if(endDate!=null){
			sql+=" and to_char(inDate,'yyyy-mm-dd')<="+"'"+new SimpleDateFormat("yyyy-MM-dd").format(endDate)+"'";
		}
		ResultSet rs=super.excuteQueryForPage(sql, pageNo, pageSize);
		PageBean pb=new PageBean();
		List<StockOut> list=new ArrayList<StockOut>();
		StockOut so=null;
		try {
			while(rs.next()){
				so=new StockOut();
				so.setCode(rs.getString("code"));
				so.setOutDate(rs.getDate("outdate"));
				so.setCustomerCode(rs.getString("customercode"));
				so.setCustomerName(rs.getString("customername"));
				so.setContacter(rs.getString("contacter"));
				so.setTelphone(rs.getString("telphone"));
				so.setFax(rs.getString("fax"));	
				so.setOutType(rs.getString("outType"));
				so.setIsInvoice(rs.getString("isInvoice"));
				so.setAddress(rs.getString("address"));
				so.setRemarks(rs.getString("remarks"));
				so.setIsShow(rs.getString("isShow"));
				so.setNums(rs.getInt("nums"));
				so.setNumsPrice(rs.getDouble("numsPrice"));
				so.setState(rs.getString("state"));
				so.setAddDate(rs.getDate("adddate"));
				so.setAddUser(rs.getString("addUser"));
				so.setAddUserName(rs.getString("addusername"));
				so.setAddIp(rs.getString("addip"));				
				list.add(so);
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
		String sql="delete from stockout where code=?";
		return super.executeUpdate(sql, code);
	}

	@Override
	public int insert(StockOut out) {
		String sql="insert into stockout(" +
				"code,outdate,customercode," +//1
				"contacter,telphone,fax," +//2
				"outtype,isinvoice,address," +//3
				"remarks,isshow,nums," +//4
				"numsprice,state,adddate," +//5
				"adduser,addusername,addip" +//6			
				")values(" +
				"?,?,?," +//1
				"?,?,?," +//2
				"?,?,?," +//3
				"?,?,?," +//4
				"?,?,sysdate," +//5
				"?,?,?)"; //6
				return super.executeUpdate(sql, new Object[]{
				out.getCode(),new java.sql.Date(out.getOutDate().getTime()),out.getCustomerCode(),//1
				out.getContacter(),out.getTelphone(),out.getFax(),//2
				out.getOutType(),out.getIsInvoice(),out.getAddress(),//3
				out.getRemarks(),out.getIsShow(),out.getNums(),//4
				out.getNumsPrice(),out.getState(),//5
				out.getAddUser(),out.getAddUserName(),
				out.getAddIp()});//6
	}

	@Override
	public int update(StockOut out) {
		String sql="update stockout set outdate=?,customercode=?," +//1 2
				"contacter=?,telphone=?,fax=?," +//2 3
				"outtype=?,isinvoice=?,address=?," +//3 3
				"remarks=?,isshow=?,adddate=sysdate " +//4 2
				"where code=?"; //5 1
		return super.executeUpdate(sql, new Object[]{
				new java.sql.Date(out.getOutDate().getTime()),out.getCustomerCode(),//1
				out.getContacter(),out.getTelphone(),out.getFax(),//2
				out.getOutType(),out.getIsInvoice(),out.getAddress(),//3
				out.getRemarks(),out.getIsShow(),//4
				out.getCode()});//5
	}

}
