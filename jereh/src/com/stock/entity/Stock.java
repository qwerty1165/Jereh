package com.stock.entity;

public class Stock {
	public String hcode;//仓库编号
	public String pcode;//配件编号
	public int nums;//库存数量
	public int maxnums;//库存上限
    public int minnums;//库存下限
    public String costprice;//库存平均成本价
    public String remarks;//备注
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