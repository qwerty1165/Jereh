package com.sale.entity;

import java.util.Date;
import java.util.List;

public class SaleQuotation {
	private String code;//���۵����
	private Date sqDate;//��������
	private String customercode;//�ͻ����
	private String csName;
	public String getCsName() {
		return csName;
	}
	public void setCsName(String csName) {
		this.csName = csName;
	}
	private String contacter;//��ϵ��
	private String telphone;//�绰
	private String fax;//����
	private int nums;//������
	private double numsPrice;//�ܼ۸�
	private String isShow;//�Ƿ���ʾ
	private String state;//״̬
	private String remarks;//��ע
	private Date addDate;//��������
	private String addUser;//������
	private String addUserName;//����������
	private String addIp;//����Ip
	private String compcode;//������˾
	private List<SaleQuotation_Detail> saleQuotation_Dateil;
	public int getNums() {
		return nums;
	}
	public void setNums(int nums) {
		this.nums = nums;
	}
	public double getNumsPrice() {
		return numsPrice;
	}
	public void setNumsPrice(double numsPrice) {
		this.numsPrice = numsPrice;
	}
	public List getSaleQuotation_Dateil() {
		return saleQuotation_Dateil;
	}
	public void setSaleQuotation_Dateil(List saleQuotation_Dateil) {
		this.saleQuotation_Dateil = saleQuotation_Dateil;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getSqDate() {
		return sqDate;
	}
	public void setSqDate(Date sqDate) {
		this.sqDate = sqDate;
	}
	public String getCustomercode() {
		return customercode;
	}
	public void setCustomercode(String customercode) {
		this.customercode = customercode;
	}
	public String getContacter() {
		return contacter;
	}
	public void setContacter(String contacter) {
		this.contacter = contacter;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	
	public String getIsShow() {
		return isShow;
	}
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	public String getCompcode() {
		return compcode;
	}
	public void setCompcode(String compcode) {
		this.compcode = compcode;
	}
	
	
	
	

}
