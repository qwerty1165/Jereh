package com.base.service.impl;

import com.base.dao.BaseCompanyDao;
import com.base.dao.impl.BaseCompanyDaoImpl;
import com.base.entity.BaseCompany;
import com.base.service.BaseCompanyService;

public class BaseCompanyServiceImpl implements BaseCompanyService {

	private BaseCompanyDao baseCompanyDao = new BaseCompanyDaoImpl();
	public BaseCompany searchBaseCompany() {
		// TODO Auto-generated method stub
		return baseCompanyDao.selectBaseCompany();
	}
	public int modifyBaseCompany(BaseCompany baseCompany) {
		// TODO Auto-generated method stub
		return baseCompanyDao.updateBaseCompany(baseCompany);
	}

}
