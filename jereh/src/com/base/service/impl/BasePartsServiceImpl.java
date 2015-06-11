package com.base.service.impl;

import com.base.dao.BasePartsDao;
import com.base.dao.impl.BasePartsDaoImpl;
import com.base.entity.BaseParts;
import com.base.service.BasePartsService;
import com.common.entity.PageBean;

public class BasePartsServiceImpl implements BasePartsService {

	BasePartsDao basePartsDao=new BasePartsDaoImpl();
	@Override
	public PageBean findAll(BaseParts bp, int pageNo, int pageSize) {
		
		return basePartsDao.findBaseParts(bp, pageNo, pageSize);
	}
	@Override
	public int insertBaseParts(BaseParts baseParts) {
		// TODO Auto-generated method stub
		return basePartsDao.insertBaseParts(baseParts);
	}
	@Override
	public int deleteBaseParts(String partsCode) {
		// TODO Auto-generated method stub
		return basePartsDao.deleteBaseParts(partsCode);
	}
	@Override
	public int update(BaseParts baseParts) {
		// TODO Auto-generated method stub
		return basePartsDao.update(baseParts);
	}

}
