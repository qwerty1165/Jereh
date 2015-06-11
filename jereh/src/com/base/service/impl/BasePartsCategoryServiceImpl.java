package com.base.service.impl;

import java.util.List;
import com.base.dao.BasePartsCategoryDao;
import com.base.dao.impl.BasePartsCategoryDaoImpl;
import com.base.entity.BasePartsCategory;
import com.base.service.BasePartsCategoryService;
import com.common.entity.PageBean;

public class BasePartsCategoryServiceImpl implements BasePartsCategoryService {
	BasePartsCategoryDao basePartsCategoryDao = new BasePartsCategoryDaoImpl();

	@Override
	public PageBean getBasePartsCategoryList(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return basePartsCategoryDao.findAll(pageNo, pageSize);
	}

	@Override
	public PageBean getBasePartsCategoryList(String code, String pCode,
			int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return basePartsCategoryDao.findAll(code, pCode, pageNo, pageSize);
	}

	@Override
	public int deleteBasePartsCategory(String code) {
		// TODO Auto-generated method stub

		return basePartsCategoryDao.delete(code);
	}

	@Override
	public int updataBasePartsCategory(BasePartsCategory basePartScategory,
			String code) {
		// TODO Auto-generated method stub
		return basePartsCategoryDao.update(basePartScategory, code);
	}

	@Override
	public BasePartsCategory getBasePartsCategoryByCode(String code) {
		// TODO Auto-generated method stub
		return basePartsCategoryDao.findByCode(code);
	}

	@Override
	public int addBasePartsCategory(BasePartsCategory basePartsCategory) {
		// TODO Auto-generated method stub
		BasePartsCategoryDao basePartsCategoryDao = new BasePartsCategoryDaoImpl();
		List<String> codeList = basePartsCategoryDao.findAllCode();
		int nextCode = 1;
		if (!codeList.isEmpty()) {
			int i = 0;
			while (Integer.parseInt(codeList.get(i)) == (i + 1)) {
				i++;
				nextCode = i + 1;
				if (i >= codeList.size()) {
					break;
				}
			}
			// for (int i = 0; i < codeList.size(); i++) {
			// int code = Integer.parseInt(codeList.get(i));
			// nextCode = i + 1;
			// if (code != nextCode) {
			//
			// break;
			// }
			// }
		} else {
			nextCode = 1;
		}
		// int maxCode = basePartsCategoryDao.findMaxCode()+1;
		String code = null;
		if (nextCode < 10) {
			code = "00" + nextCode;
		} else if (nextCode < 100) {
			code = "0" + nextCode;
		} else {
			code = "" + nextCode;
		}

		basePartsCategory.setCode(code);
		return basePartsCategoryDao.insert(basePartsCategory);
	}
}
