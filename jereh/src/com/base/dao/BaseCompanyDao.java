package com.base.dao;

import com.base.entity.BaseCompany;

public interface BaseCompanyDao {

	public BaseCompany selectBaseCompany();
	
	public int updateBaseCompany(BaseCompany baseCompany);
	
}
