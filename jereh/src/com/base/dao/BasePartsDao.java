package com.base.dao;


import com.base.entity.BaseParts;
import com.common.entity.PageBean;

public interface BasePartsDao {
	
	public PageBean findBaseParts(BaseParts bp, int pageNo,int pageSize);  //��ʾ����б�
	
	public PageBean findBasePartsByCom(BaseParts bp, int pageNo, int pageSize);//
	
	public int insertBaseParts(BaseParts baseParts); //���������Ϣ
	
	public int deleteBaseParts(String partsCode);  //ɾ�������Ϣ
	
	public int update(BaseParts baseParts);
}
