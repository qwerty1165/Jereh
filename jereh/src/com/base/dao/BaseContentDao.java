package com.base.dao;

import java.util.List;

import com.base.entity.BaseContent;
import com.common.entity.PageBean;

public interface BaseContentDao {
	
	public List<BaseContent> findAll();
	//public PageBean findList(int pageNo,int pageSize);//�����б�	
	public PageBean findByCondition(BaseContent content,int pageNo,int pageSize);//����������
	public int delete(BaseContent content);//ɾ��
	public int insert(BaseContent content);//���
	public int update(BaseContent content);
	public List<BaseContent> findCategory();//�������select distinct categorycode from basecontent
}
