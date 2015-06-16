package com.purchase.entity;

public class PurchaseOrderDetail {
	private String dcode;
	private String ocode;
	private String xcode;
	private String pcode;
	private int nums;
	private double price;
	private String rkState;
	private int rkNums;
	private String remarks;
	public String getDcode() {
		return dcode;
	}
	public void setDcode(String dcode) {
		this.dcode = dcode;
	}
	public String getOcode() {
		return ocode;
	}
	public void setOcode(String ocode) {
		this.ocode = ocode;
	}
	public String getXcode() {
		return xcode;
	}
	public void setXcode(String xcode) {
		this.xcode = xcode;
	}
	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
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
	public String getRkState() {
		return rkState;
	}
	public void setRkState(String rkState) {
		this.rkState = rkState;
	}
	public int getRkNums() {
		return rkNums;
	}
	public void setRkNums(int rkNums) {
		this.rkNums = rkNums;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
