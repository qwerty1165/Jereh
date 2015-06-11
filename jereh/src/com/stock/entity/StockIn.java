package com.stock.entity;

import java.util.Date;
import java.util.List;

public class StockIn {
	private String code;//入库单据 主键
	private Date inDate;//入库日期
	private String supplierCode;//供应商编号
	
	private String supplierName;//供应商名称
	
	
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	private String contacter;//联系人
	private String telphone;//联系电话
	private String fax;//传真
	private String inType;//入库类型，对应字典表中入库类型
	private String isRoad;//入库用途0-正常入库1-冲抵入库
	private String isInvoice;//是否开票1-是0否
	private String remarks;//备注
	private String isShow;//是否显示1显示，0不显示
	private int nums;//入库配件数量
	private double numsPrice;//入库配件总价值
	private String state;//入库单据状态
	private String compCode;//所属公司
	private Date addDate;//操作日期
	private String addUser;//操作账号
	private String addUserName;//操作人姓名
	private String addIp;//操作IP
	
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
