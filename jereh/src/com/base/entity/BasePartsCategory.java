package com.base.entity;

import java.util.Date;

/**
 * 配件类别信息表
 * 
 * @author master
 *
 */
public class BasePartsCategory {
	private String code;// 类别编号 CODE
	private String pCode;// 父类编号 PCODE
	private String cateGoryName;// 类别名称 CATEGORYNAME
	private String isShow;// 是否显示 ISSHOW
	private String remarks;// 备注 REMARKS
	private String compCode;// 所属公司 COMPCODE
	private Date addDate;// 添加日期 ADDDATE
	private String addUser;// 操作用户 ADDUSER
	private String addUserName;// 操作用户姓名 ADDUSERNAME
	private String addIp;// 操作ip ADDIP

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getpCode() {
		return pCode;
	}

	public void setpCode(String pCode) {
		this.pCode = pCode;
	}

	public String getCateGoryName() {
		return cateGoryName;
	}

	public void setCateGoryName(String cateGoryName) {
		this.cateGoryName = cateGoryName;
	}

	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String string) {
		this.isShow = string;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCompCode() {
		return compCode;
	}

	public void setCompCode(String compCode) {
		this.compCode = compCode;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public String getAddUser() {
		return addUser;
	}

	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}

	public String getAddUserName() {
		return addUserName;
	}

	public void setAddUserName(String addUserName) {
		this.addUserName = addUserName;
	}

	public String getAddIp() {
		return addIp;
	}

	public void setAddIp(String addIp) {
		this.addIp = addIp;
	}

	public BasePartsCategory(String code, String pCode, String cateGoryName,
			String isShow, String remarks, String compCode, Date addDate,
			String addUser, String addUserName, String addIp) {
		super();
		this.code = code;
		this.pCode = pCode;
		this.cateGoryName = cateGoryName;
		this.isShow = isShow;
		this.remarks = remarks;
		this.compCode = compCode;
		this.addDate = addDate;
		this.addUser = addUser;
		this.addUserName = addUserName;
		this.addIp = addIp;
	}

	public BasePartsCategory() {
		super();
	}

}
