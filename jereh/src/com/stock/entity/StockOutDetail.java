package com.stock.entity;

import com.base.entity.BaseParts;

public class StockOutDetail {
	private String code;//主键
	private String outCode;//出库单据编号
	private String xsCode;//销售单号
	private String pCode;//配件编号
	private int nums;//出库数量
	private double price;//出库单价
	private String wareHouse;//出库仓库
	private int wnums;//当前库存
	private double lastPrice;//上次价格
	private String Remarks;//备注
	private BaseParts baseParts;
	
	public BaseParts getBaseParts() {
		return baseParts;
	}
	public void setBaseParts(BaseParts baseParts) {
		this.baseParts = baseParts;
	}
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
