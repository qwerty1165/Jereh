package com.sale.entity;

/**
 * 销售退货明细
 * 
 * @author master
 *
 */
public class SaleReturnDetail {
	
	private String outCode;// 出库单号
	private String partsNo;// 件号
	private String partsName;// 配件名称
	private String partsBrand;// 配件品牌
	private String partsModle;// 配件型号
	private int number;// 数量
	private long salePrice;// 单价
	private String remarks;//备注

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getOutCode() {
		return outCode;
	}

	public void setOutCode(String outCode) {
		this.outCode = outCode;
	}

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

	public String getPartsModle() {
		return partsModle;
	}

	public void setPartsModle(String partsModle) {
		this.partsModle = partsModle;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public long getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(long salePrice) {
		this.salePrice = salePrice;
	}

	public SaleReturnDetail(String outCode, String partsNo, String partsName,
			String partsBrand, String partsModle, int number, long salePrice,
			String remarks) {
		super();
		this.outCode = outCode;
		this.partsNo = partsNo;
		this.partsName = partsName;
		this.partsBrand = partsBrand;
		this.partsModle = partsModle;
		this.number = number;
		this.salePrice = salePrice;
		this.remarks = remarks;
	}

	public SaleReturnDetail() {
		super();
	}

	

}
