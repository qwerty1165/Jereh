package com.sale.entity;

import java.util.Date;

public class SaleOrder {
	private String code;//���۵����
	private Date   orderDate;//��������
	private String customerCode;//�ͻ����
	private String contacter;//��ϵ��
	private String telphone;//��ϵ�绰
	private String fax;//����
	private String trans;//���䷽ʽ
	private String	businesser;//ҵ��Ա
	private Date deLiveryDate;//��������
	private String remarks;//��ע
	private String isShow;//�Ƿ���ʾ1-��ʾ,0-����ʾ
	private int nums;//�������
	private double numsPrice;//���۵��ܼ۸�
	private String	state;//�Ƿ����0-δ����1-ȫ������2-���ֳ���
	private Date addDate;//��������
	private String addUser;//������
	private String addUserName;//����������
	private String addIP;//����IP
	private String csName;//�ͻ�����
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
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
	public String getTrans() {
		return trans;
	}
	public void setTrans(String trans) {
		this.trans = trans;
	}
	public String getBusinesser() {
		return businesser;
	}
	public void setBusinesser(String businesser) {
		this.businesser = businesser;
	}
	public Date getDeLiveryDate() {
		return deLiveryDate;
	}
	public void setDeLiveryDate(Date deLiveryDate) {
		this.deLiveryDate = deLiveryDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getIsShow() {
		return isShow;
	}
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
	public String getAddIP() {
		return addIP;
	}
	public void setAddIP(String addIP) {
		this.addIP = addIP;
	}
	public String getCsName() {
		return csName;
	}
	public void setCsName(String csName) {
		this.csName = csName;
	}
	


}
