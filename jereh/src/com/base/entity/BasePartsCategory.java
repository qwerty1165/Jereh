package com.base.entity;

import java.util.Date;

/**
 * ��������Ϣ��
 * 
 * @author master
 *
 */
public class BasePartsCategory {
	private String code;// ����� CODE
	private String pCode;// ������ PCODE
	private String cateGoryName;// ������� CATEGORYNAME
	private String isShow;// �Ƿ���ʾ ISSHOW
	private String remarks;// ��ע REMARKS
	private String compCode;// ������˾ COMPCODE
	private Date addDate;// ������� ADDDATE
	private String addUser;// �����û� ADDUSER
	private String addUserName;// �����û����� ADDUSERNAME
	private String addIp;// ����ip ADDIP

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
