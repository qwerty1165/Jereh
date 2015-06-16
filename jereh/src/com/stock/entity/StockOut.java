package com.stock.entity;

import java.util.Date;
import java.util.List;

public class StockOut {
	private String code;//���ⵥ���
	private Date  outDate;//��������
	private String customerCode;//�ͻ����
	private String customerName;//�ͻ�����
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	private String contacter;//�ͻ���ϵ��
	private String telphone;//��ϵ�绰
	private String fax;//����
	private String outType;//��������
	private String isInvoice;//�Ƿ�Ʊ
	private String address;//��ַ
	private String remarks;//��ע
	private String isShow;//�Ƿ���ʾ
	private int nums;//��������
	private double numsPrice;//�����ܼ�ֵ
	private String state;//���ⵥ�����״̬
	private Date addDate;//��������
	private String addUser;//������
	private String addUserName;//����������
	private String addIp;//����IP
	private List<StockOutDetail> detailList;
	
	public List<StockOutDetail> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<StockOutDetail> detailList) {
		this.detailList = detailList;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getOutDate() {
		return outDate;
	}
	public void setOutDate(Date outDate) {
		this.outDate = outDate;
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
	public String getOutType() {
		return outType;
	}
	public void setOutType(String outType) {
		this.outType = outType;
	}
	public String getIsInvoice() {
		return isInvoice;
	}
	public void setIsInvoice(String isInvoice) {
		this.isInvoice = isInvoice;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getAddIp() {
		return addIp;
	}
	public void setAddIp(String addIp) {
		this.addIp = addIp;
	}
	
	
	
}
