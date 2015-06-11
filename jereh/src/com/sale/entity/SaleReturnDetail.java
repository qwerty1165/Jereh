package com.sale.entity;

/**
 * 销售退货明细
 * 
 * @author master
 *
 */
public class SaleReturnDetail {
	private String code;// 明细主键
	private String xtCode;// 销退单号
	private String ckCode;// 出库单号
	private String pCode;// 配件编号
	private int unms;// 配件数量
	private String remarks;// 备注

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getXtCode() {
		return xtCode;
	}

	public void setXtCode(String xtCode) {
		this.xtCode = xtCode;
	}

	public String getCkCode() {
		return ckCode;
	}

	public void setCkCode(String ckCode) {
		this.ckCode = ckCode;
	}

	public String getpCode() {
		return pCode;
	}

	public void setpCode(String pCode) {
		this.pCode = pCode;
	}

	public int getUnms() {
		return unms;
	}

	public void setUnms(int unms) {
		this.unms = unms;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public SaleReturnDetail(String code, String xtCode, String ckCode,
			String pCode, int unms, String remarks) {
		super();
		this.code = code;
		this.xtCode = xtCode;
		this.ckCode = ckCode;
		this.pCode = pCode;
		this.unms = unms;
		this.remarks = remarks;
	}

	public SaleReturnDetail() {
		super();
	}

}
