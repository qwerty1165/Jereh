package com.sale.entity;

/**
 * �����˻���ϸ
 * 
 * @author master
 *
 */
public class SaleReturnDetail {
	private String code;// ��ϸ����
	private String xtCode;// ���˵���
	private String ckCode;// ���ⵥ��
	private String pCode;// ������
	private int unms;// �������
	private String remarks;// ��ע

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
