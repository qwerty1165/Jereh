package com.base.dao;

import java.util.List;

import com.base.entity.BasePartsCategory;
import com.common.entity.PageBean;

public interface BasePartsCategoryDao {
	/**
	 * 获取 配件类别信息表 数据
	 * 
	 * @param 参数pageNo
	 * @param 参数pageSize
	 * @return 返回PageBean
	 */
	public PageBean findAll(int pageNo, int pageSize);

	public PageBean findAll(String code, String pCode, int pageNo, int pageSize);

	public int insert(BasePartsCategory basePartScategory);

	public int update(BasePartsCategory basePartScategory, String code);

	public int delete(String code);

	public BasePartsCategory findByCode(String code);

	public int findMaxCode();

	public List findAllCode();
}
