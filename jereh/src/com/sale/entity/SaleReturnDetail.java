package com.sale.entity;

/**
 * �����˻���ϸ
 * 
 * @author master
 *
 */
public class SaleReturnDetail {
	
	private String outCode;// ���ⵥ��
	private String partsNo;// ����
	private String partsName;// �������
	private String partsBrand;// ���Ʒ��
	private String partsModle;// ����ͺ�
	private int number;// ����
	private long salePrice;// ����

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
			String partsBrand, String partsModle, int number, long salePrice) {
		super();
		this.outCode = outCode;
		this.partsNo = partsNo;
		this.partsName = partsName;
		this.partsBrand = partsBrand;
		this.partsModle = partsModle;
		this.number = number;
		this.salePrice = salePrice;
	}

	public SaleReturnDetail() {
		super();
	}

}
