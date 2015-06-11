package com.stock.entity;

import java.util.Date;
import java.util.List;

public class StockIn {
	private String code;//��ⵥ�� ����
	private Date inDate;//�������
	private String supplierCode;//��Ӧ�̱��
	
	private String supplierName;//��Ӧ������
	
	
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	private String contacter;//��ϵ��
	private String telphone;//��ϵ�绰
	private String fax;//����
	private String inType;//������ͣ���Ӧ�ֵ�����������
	private String isRoad;//�����;0-�������1-������
	private String isInvoice;//�Ƿ�Ʊ1-��0��
	private String remarks;//��ע
	private String isShow;//�Ƿ���ʾ1��ʾ��0����ʾ
	private int nums;//����������
	private double numsPrice;//�������ܼ�ֵ
	private String state;//��ⵥ��״̬
	private String compCode;//������˾
	private Date addDate;//��������
	private String addUser;//�����˺�
	private String addUserName;//����������
	private String addIp;//����IP
	
	private List<StockInDetail> detailList;
	
	public List<StockInDetail> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<StockInDetail> detailList) {
		this.detailList = detailList;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getInDate() {
		return inDate;
	}
	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
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
	public String getInType() {
		return inType;
	}
	public void setInType(String inType) {
		this.inType = inType;
	}
	public String getIsRoad() {
		return isRoad;
	}
	public void setIsRoad(String isRoad) {
		this.isRoad = isRoad;
	}
	public String getIsInvoice() {
		return isInvoice;
	}
	public void setIsInvoice(String isInvoice) {
		this.isInvoice = isInvoice;
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
	
	
}
