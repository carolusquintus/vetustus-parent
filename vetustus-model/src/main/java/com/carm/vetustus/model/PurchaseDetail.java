package com.carm.vetustus.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "purchase_detail")
public class PurchaseDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6675273030814541472L;
	
	@EmbeddedId
	private PurchaseDetailPK purchaseDetailPK;
	
	@Column(name = "quantity_ordered", nullable = false)
	private Integer quantityOrdered;
	
	@Column(name = "price_each", nullable = false)
	private Double priceEach;
	
	@Column(name = "purchase_line_number", nullable = false)
	private Integer purchaseLineNumber;
	
	@OneToOne
	@JoinColumn(name = "purchase_number", insertable = false, updatable = false)
	private Purchase purchase;
	
	@ManyToOne
	@JoinColumn(name = "product_code", insertable = false, updatable = false)
	private Product product;

	public PurchaseDetailPK getPurchaseDetailPK() {
		return purchaseDetailPK;
	}

	public void setPurchaseDetailPK(PurchaseDetailPK purchaseDetailPK) {
		this.purchaseDetailPK = purchaseDetailPK;
	}

	public Integer getQuantityOrdered() {
		return quantityOrdered;
	}

	public void setQuantityOrdered(Integer quantityOrdered) {
		this.quantityOrdered = quantityOrdered;
	}

	public Double getPriceEach() {
		return priceEach;
	}

	public void setPriceEach(Double priceEach) {
		this.priceEach = priceEach;
	}

	public Integer getPurchaseLineNumber() {
		return purchaseLineNumber;
	}

	public void setPurchaseLineNumber(Integer purchaseLineNumber) {
		this.purchaseLineNumber = purchaseLineNumber;
	}
	
	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	public String toString() {
		return new StringBuilder(getClass().getSimpleName()).append(": {\n").
				append(purchaseDetailPK).
				append("\t quantityOrdered: ").append(quantityOrdered).append(",\n").
				append("\t priceEach: ").append(priceEach).append(",\n").
				append("\t purchaseLineNumber: ").append(purchaseLineNumber).append("\n").
				append("}").
				toString();
	}
	

}
