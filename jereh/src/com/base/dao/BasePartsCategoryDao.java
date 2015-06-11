package com.base.dao;

import java.util.List;

import com.base.entity.BasePartsCategory;
import com.common.entity.PageBean;

public interface BasePartsCategoryDao {
	/**
	 * ��ȡ ��������Ϣ�� ����
	 * 
	 * @param ����pageNo
	 * @param ����pageSize
	 * @return ����PageBean
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
