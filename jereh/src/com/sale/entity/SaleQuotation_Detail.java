package com.sale.entity;

import com.base.entity.BaseParts;
import com.stock.entity.Stock;

public class SaleQuotation_Detail {
	private String code;//报价单明细编号
	private String scode;//报价单编号
	private String pcode;//配件编号
	private int nums;//数量
	private double price;//单价
	private double money;//总价
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	private String deliveryMode;//交货期
	private String remarks;//备注
	private BaseParts baseParts;
    private Stock stock;
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
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
	public String getScode() {
		return scode;
	}
	public void setScode(String scode) {
		this.scode = scode;
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
	public String getDeliveryMode() {
		return deliveryMode;
	}
	public void setDeliveryMode(String deliveryMode) {
		this.deliveryMode = deliveryMode;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	

}
