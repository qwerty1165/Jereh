package com.base.service;

import java.util.List;

import com.base.entity.BaseContent;
import com.common.entity.PageBean;

public interface BaseContentService {
	//public PageBean getList(int pageNo,int pageSize);
	public PageBean getListByCondition(BaseContent bc,int pageNo,int pageSize);
	public int deleteBaseContent(BaseContent bc);
	public int insertBaseContent(BaseContent bc);
	public int updateBaseContent(BaseContent bc);
	public List<BaseContent> getCategory();
	public List<BaseContent> getAll();
}
