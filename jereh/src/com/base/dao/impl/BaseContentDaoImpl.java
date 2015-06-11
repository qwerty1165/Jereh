package com.base.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import com.base.dao.BaseContentDao;
import com.base.entity.BaseContent;
import com.common.dao.BaseDao;
import com.common.entity.PageBean;

public class BaseContentDaoImpl extends BaseDao implements BaseContentDao {	
	@Override
	public PageBean findByCondition(BaseContent content,int pageNo,int pageSize) {
		PageBean pb=new PageBean();
		List<BaseContent> list=new ArrayList<BaseContent>();
		BaseContent bc=null;
		String sql="select * from vw_content where 1=1 ";
		
		if(content.getCode()!=null&&!content.getCode().equals("")){
			sql+=" and code like "+"'%"+content.getCode()+"%'";//like "+"'%"+bp.getPartsNo()+"%'";
		}
		if(content.getCodeName()!=null&&!content.getCodeName().equals("")){
			sql+=" and codename like "+"'%"+content.getCodeName()+"%'";					
		}
		if(content.getCategoryCode()!=null&&!content.getCategoryCode().equals("")){
			sql+=" and categorycode like "+"'%"+content.getCategoryCode()+"%'";
		}
		
		ResultSet rs=super.excuteQueryForPage(sql,pageNo,pageSize);
		try {
		  	while(rs.next()){
		  		bc=new BaseContent();
		  		bc.setCode(rs.getString("code"));
		  		bc.setCodeName(rs.getString("codename"));
		  		bc.setCategoryCode(rs.getString("categorycode"));
		  		bc.setOrderNo(rs.getString("orderno"));
		  		bc.setIsShow(rs.getString("isshow"));
		  		bc.setRemarks(rs.getString("remarks"));
		  		bc.setAddDate(new Date(rs.getDate("adddate").getTime()));
		  		bc.setAddUserName(rs.getString("addusername"));
		  		bc.setAddUser(rs.getString("adduser"));
		  		bc.setAddIp(rs.getString("addip"));
		  		bc.setCompCode(rs.getString("compcode"));
		  		bc.setCompName(rs.getString("compname"));
		  		list.add(bc);
		  	}
		} catch (SQLException e) {			
			e.printStackTrace();
		}finally{
			super.closeAll();
		}					
		pb.setData(list);		
		sql="select count(*) c from ("+sql+")";
		int total=super.executeTotalCount(sql);
		pb.setRecordCount(total);			
		return pb;
	}

	@Override
	public int delete(BaseContent content) {	
		String sql="delete from basecontent where code=? and categorycode=? ";		
		return super.executeUpdate(sql,new Object[]{content.getCode(),content.getCategoryCode()});
	}

	@Override
	public int insert(BaseContent bc) {		
		String sql="insert into basecontent (code, codename, categorycode, orderno, isshow, remarks, adddate, adduser, addusername, addip, compcode) " +
				"values(?,?,?,?,?,?,sysdate,?,?,?,?) ";
		return super.executeUpdate(sql,new Object[]{bc.getCode(),bc.getCodeName(),bc.getCategoryCode(),
				bc.getOrderNo(),bc.getIsShow(),bc.getRemarks(),
				bc.getAddUser(),bc.getAddUserName(),bc.getAddIp(),bc.getCompCode()});
	}
	@Override
	public int update(BaseContent content) {//字典名称不可选
		String sql="update basecontent set orderno=?,isshow=?,remarks=? " +
				"where code=? and categorycode=? ";
		return super.executeUpdate(sql, new Object[]{content.getOrderNo(),content.getIsShow(),content.getRemarks()
				,content.getCode(),content.getCategoryCode()});
	}
	@Override
	public List<BaseContent> findCategory() {
		String sql="select distinct categorycode from basecontent";
		List<BaseContent> list=new ArrayList<BaseContent>();
		BaseContent bc=null;
		ResultSet rs=super.executeQuery(sql);
		try {
			while (rs.next()) {
				bc=new BaseContent();
				bc.setCategoryCode(rs.getString("categorycode"));
				list.add(bc);
			}
		} catch (SQLException e) {		
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<BaseContent> findAll() {		
		List<BaseContent> list=new ArrayList<BaseContent>();
		BaseContent bc=null;
		String sql="select * from vw_content";
		ResultSet rs=super.executeQuery(sql);
		try {
		  	while(rs.next()){
		  		bc=new BaseContent();
		  		bc.setCode(rs.getString("code"));
		  		bc.setCodeName(rs.getString("codename"));
		  		bc.setCategoryCode(rs.getString("categorycode"));
		  		bc.setOrderNo(rs.getString("orderno"));
		  		bc.setIsShow(rs.getString("isshow"));
		  		bc.setRemarks(rs.getString("remarks"));
		  		bc.setAddDate(new Date(rs.getDate("adddate").getTime()));
		  		bc.setAddUserName(rs.getString("addusername"));
		  		bc.setAddUser(rs.getString("adduser"));
		  		bc.setAddIp(rs.getString("addip"));
		  		bc.setCompCode(rs.getString("compcode"));
		  		bc.setCompName(rs.getString("compname"));
		  		list.add(bc);
		  	}
		} catch (SQLException e) {			
			e.printStackTrace();
		}finally{
			super.closeAll();
		}	
		return list;
	}
	
/*
 @Override
	public PageBean findList(int pageNo,int pageSize) {
		PageBean pb=new PageBean();
		List<BaseContent> list=new ArrayList<BaseContent>();
		BaseContent bc=null;
		String sql="select * from vw_content";//"select * from basecontent";//
		ResultSet rs=super.executeQueryForPage(sql,pageNo,pageSize);
		try {
		  	while(rs.next()){
		  		bc=new BaseContent();
		  		bc.setCode(rs.getString("code"));
		  		bc.setCodeName(rs.getString("codename"));
		  		bc.setCategoryCode(rs.getString("categorycode"));
		  		bc.setOrderNo(rs.getString("orderno"));
		  		bc.setIsShow(rs.getString("isshow"));
		  		bc.setRemarks(rs.getString("remarks"));
		  		bc.setAddDate(new Date(rs.getDate("adddate").getTime()));
		  		bc.setAddUserName(rs.getString("addusername"));
		  		bc.setAddUser(rs.getString("adduser"));
		  		bc.setAddIp(rs.getString("addip"));
		  		bc.setCompCode(rs.getString("compcode"));
		  		bc.setCompName(rs.getString("compname"));
		  		list.add(bc);
		  	}
		} catch (SQLException e) {			
			e.printStackTrace();
		}finally{
			super.close();
		}					
		pb.setData(list);		
		sql="select count(*) c from basecontent";
		int total=super.executeTotalCount(sql);
		pb.setRecordCount(total);			
		return pb;
	}
 */	
	
}
