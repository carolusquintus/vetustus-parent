package com.carm.vetustus.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PurchaseDetailPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6857045216840306872L;
	
	@Column(name = "purchase_number", nullable = false, updatable = false)
	private Integer purchaseNumber;
	
	@Column(name = "product_code", nullable = false, updatable = false)
	private String productCode;

	public Integer getPurchaseNumber() {
		return purchaseNumber;
	}

	public void setPurchaseNumber(Integer purchaseNumber) {
		this.purchaseNumber = purchaseNumber;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	public String toString() {
		return new StringBuilder().
				append("\t purchaseNumber: ").append(purchaseNumber).append(",\n").
				append("\t productCode: ").append(productCode).append(",\n").
				toString();
	}

}
