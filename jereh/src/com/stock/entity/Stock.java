package com.stock.entity;

public class Stock {
	public String hcode;//�ֿ���
	public String pcode;//������
	public int nums;//�������
	public int maxnums;//�������
    public int minnums;//�������
    public String costprice;//���ƽ���ɱ���
    public String remarks;//��ע
	public String getHcode() {
		return hcode;
	}
	public void setHcode(String hcode) {
		this.hcode = hcode;
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
	public int getMaxnums() {
		return maxnums;
	}
	public void setMaxnums(int maxnums) {
		this.maxnums = maxnums;
	}
	public int getMinnums() {
		return minnums;
	}
	public void setMinnums(int minnums) {
		this.minnums = minnums;
	}
	public String getCostprice() {
		return costprice;
	}
	public void setCostprice(String costprice) {
		this.costprice = costprice;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}