package com.stock.entity;
public class StockOutDetail {
	private String code;//����
	private String outCode;//���ⵥ�ݱ��
	private String xsCode;//���۵���
	private String pCode;//������
	private int nums;//��������
	private double price;//���ⵥ��
	private String wareHouse;//����ֿ�
	private int wnums;//��ǰ���
	private double lastPrice;//�ϴμ۸�
	private String Remarks;//��ע
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getOutCode() {
		return outCode;
	}
	public void setOutCode(String outCode) {
		this.outCode = outCode;
	}
	public String getXsCode() {
		return xsCode;
	}
	public void setXsCode(String xsCode) {
		this.xsCode = xsCode;
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
	public String getWareHouse() {
		return wareHouse;
	}
	public void setWareHouse(String wareHouse) {
		this.wareHouse = wareHouse;
	}
	public int getWnums() {
		return wnums;
	}
	public void setWnums(int wnums) {
		this.wnums = wnums;
	}
	public double getLastPrice() {
		return lastPrice;
	}
	public void setLastPrice(double lastPrice) {
		this.lastPrice = lastPrice;
	}
	public String getRemarks() {
		return Remarks;
	}
	public void setRemarks(String remarks) {
		Remarks = remarks;
	}
	
}
