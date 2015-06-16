package com.base.dao;


import com.base.entity.BaseParts;
import com.common.entity.PageBean;

public interface BasePartsDao {
	
	public PageBean findBaseParts(BaseParts bp, int pageNo,int pageSize);  //显示配件列表
	
	public PageBean findBasePartsByCom(BaseParts bp, int pageNo, int pageSize);//
	
	public int insertBaseParts(BaseParts baseParts); //插入配件信息
	
	public int deleteBaseParts(String partsCode);  //删除配件信息
	
	public int update(BaseParts baseParts);
}
