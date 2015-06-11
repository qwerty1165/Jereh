package com.base.dao;

import java.util.List;

import com.base.entity.BaseContent;
import com.common.entity.PageBean;

public interface BaseContentDao {
	
	public List<BaseContent> findAll();
	//public PageBean findList(int pageNo,int pageSize);//所有列表	
	public PageBean findByCondition(BaseContent content,int pageNo,int pageSize);//按条件查找
	public int delete(BaseContent content);//删除
	public int insert(BaseContent content);//添加
	public int update(BaseContent content);
	public List<BaseContent> findCategory();//所属类别select distinct categorycode from basecontent
}
