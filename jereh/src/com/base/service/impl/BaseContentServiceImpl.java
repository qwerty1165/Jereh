package com.base.service.impl;
import java.util.List;
import com.base.dao.BaseContentDao;
import com.base.dao.impl.BaseContentDaoImpl;
import com.base.entity.BaseContent;
import com.base.service.BaseContentService;
import com.common.entity.PageBean;

public class BaseContentServiceImpl implements BaseContentService {
	private BaseContentDao dao=new BaseContentDaoImpl();
	@Override
	public PageBean getListByCondition(BaseContent bc,int pageNo,int pageSize) {		
		return dao.findByCondition(bc,pageNo,pageSize);
	}

	@Override
	public int deleteBaseContent(BaseContent bc) {		
		return dao.delete(bc);
	}

	@Override
	public int insertBaseContent(BaseContent bc) {		
		return dao.insert(bc);
	}

	@Override
	public List<BaseContent> getCategory() {		
		return dao.findCategory();
	}
	@Override
	public int updateBaseContent(BaseContent bc) {		
		return dao.update(bc);
	}
	@Override
	public List<BaseContent> getAll() {		
		return dao.findAll();
	}
/*	public static void main(String[] args) {
		BaseContentService service=new BaseContentServiceImpl();
		PageBean pb=null;
		
				
		pb=service.getList(1, 10); 
		List<BaseContent> clist=service.getCategory();
		for (BaseContent b : clist) {
			System.out.println(""+b.getCategoryCode());
		}
		System.out.println("test");
		
		
		BaseContent content=new BaseContent();
		content.setCategoryCode("¹þ¹þ");
		content.setCode("001");
		service.insertBaseContent(content);
		//content.setCategoryCode("1");
		pb=service.getListByCondition(content,1,100);
		List<BaseContent> list=pb.getData();
		//System.out.println(list);
		System.out.println(list.size()+"  "+pb.getRecordCount());
		for (BaseContent bc : list) {
			System.out.println("²âÊÔ£º---"+"×ÖµäÃû³Æ£º"+bc.getCodeName()+"-----×Öµä±àºÅ£º"+bc.getCode()+"------Ìí¼ÓÕß£º"+bc.getAddUser());
		}
	}*/




}
