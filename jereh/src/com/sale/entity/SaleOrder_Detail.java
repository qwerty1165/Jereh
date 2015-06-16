package com.sale.entity;

public class SaleOrder_Detail {
	
	private String code;//明细编号
	private String sCode;//销售单编号
	private String sqCode;//报价单编号
	private String pCode;//配件编号
	private int nums;//数量
	private double price;//销售单价
	private String state;//是否出库0-未出库1-全部出库2-部分出库
	private int ckNums; //出库数量
	private String remarks;//备注
	
	private String partsNo;//配件件号
	private String partsName;//配件名称
	private String partsBrand;//配件品牌
	private String partsModel;//配件型号
	private double numsPrice;//总价
	
	
	
	
	
	
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
