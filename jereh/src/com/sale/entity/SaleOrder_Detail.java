package com.sale.entity;

public class SaleOrder_Detail {
	
	private String code;//��ϸ���
	private String sCode;//���۵����
	private String sqCode;//���۵����
	private String pCode;//������
	private int nums;//����
	private double price;//���۵���
	private String state;//�Ƿ����0-δ����1-ȫ������2-���ֳ���
	private int ckNums; //��������
	private String remarks;//��ע
	
	private String partsNo;//�������
	private String partsName;//�������
	private String partsBrand;//���Ʒ��
	private String partsModel;//����ͺ�
	private double numsPrice;//�ܼ�
	
	
	
	
	
	
	public String getPartsNo() {
		return partsNo;
	}
	public void setPartsNo(String partsNo) {
		this.partsNo = partsNo;
	}
	public String getPartsName() {
		return partsName;
	}
	public void setPartsName(String partsName) {
		this.partsName = partsName;
	}
	public String getPartsBrand() {
		return partsBrand;
	}
	public void setPartsBrand(String partsBrand) {
		this.partsBrand = partsBrand;
	}
	public String getPartsModel() {
		return partsModel;
	}
	public void setPartsModel(String partsModel) {
		this.partsModel = partsModel;
	}
	public double getNumsPrice() {
		return numsPrice;
	}
	public void setNumsPrice(double numsPrice) {
		this.numsPrice = numsPrice;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getsCode() {
		return sCode;
	}
	public void setsCode(String sCode) {
		this.sCode = sCode;
	}
	public String getSqCode() {
		return sqCode;
	}
	public void setSqCode(String sqCode) {
		this.sqCode = sqCode;
	}
	public String getpCode() {
		return pCode;
	}
	public void setpCode(String pCode) {
		this.pCode = pCode;
	}
	public int getNums() {
		return nums;
	}
	public void setNums(int nums) {
		this.nums = nums;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getCkNums() {
		return ckNums;
	}
	public void setCkNums(int ckNums) {
		this.ckNums = ckNums;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
