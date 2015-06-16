package com.sale.entity;

import com.base.entity.BaseParts;
import com.stock.entity.Stock;

public class SaleQuotation_Detail {
	private String code;//���۵���ϸ���
	private String scode;//���۵����
	private String pcode;//������
	private int nums;//����
	private double price;//����
	private double money;//�ܼ�
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	private String deliveryMode;//������
	private String remarks;//��ע
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
