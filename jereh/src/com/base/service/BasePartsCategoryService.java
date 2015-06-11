package com.base.service;

import com.base.entity.BasePartsCategory;
import com.common.entity.PageBean;

public interface BasePartsCategoryService {
	public PageBean getBasePartsCategoryList(int pageNo, int pageSize);
	
	public PageBean getBasePartsCategoryList(String code,String pCode,int pageNo, int pageSize);
	
	public int deleteBasePartsCategory(String code);
	
	public int updataBasePartsCategory(BasePartsCategory basePartScategory,String code);
	
	public BasePartsCategory getBasePartsCategoryByCode(String code);
	
	public int addBasePartsCategory(BasePartsCategory basePartsCategory);
}
