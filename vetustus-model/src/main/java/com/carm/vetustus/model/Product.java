package com.carm.vetustus.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3993507859517101422L;
	
	@Id
	@Column(name = "product_code", nullable = false, length = 15)
	private String productCode;
	
	@Column(name = "product_name", nullable = false, length = 70)
	private String productName;
	
	@Column(name = "product_scale", nullable = false, length = 10)
	private String productScale;
	
	@Column(name = "product_vendor", nullable = false, length = 50)
	private String productVendor;
	
	@Column(name = "product_description", nullable = false)
	private String productDescription;
	
	@Column(name = "quantity_in_stock", nullable = false)
	private Integer quantityInStock;
	
	@Column(name = "buy_price", nullable = false)
	private Double buyPrice;
	
	@Column(name = "msrp", nullable = false)
	private Double msrp;
	
	@ManyToOne
	@JoinColumn(name = "product_line", nullable = false)
	private ProductLine productLine;
	
	@OneToMany(mappedBy = "product")
	private Set<PurchaseDetail> purchaseDetails = new HashSet<PurchaseDetail>(0);
	
	public String getProductCode() {
		return productCode;
	}
	
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public ProductLine getProductLine() {
		return productLine;
	}
	
	public void setProductLine(ProductLine productLine) {
		this.productLine = productLine;
	}
	
	public String getProductScale() {
		return productScale;
	}
	
	public void setProductScale(String productScale) {
		this.productScale = productScale;
	}
	
	public String getProductVendor() {
		return productVendor;
	}
	
	public void setProductVendor(String productVendor) {
		this.productVendor = productVendor;
	}
	
	public String getProductDescription() {
		return productDescription;
	}
	
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	
	public Integer getQuantityInStock() {
		return quantityInStock;
	}
	
	public void setQuantityInStock(Integer quantityInStock) {
		this.quantityInStock = quantityInStock;
	}
	
	public Double getBuyPrice() {
		return buyPrice;
	}
	
	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}
	
	public Double getMsrp() {
		return msrp;
	}
	
	public void setMsrp(Double msrp) {
		this.msrp = msrp;
	}

	public Set<PurchaseDetail> getPurchaseDetails() {
		return purchaseDetails;
	}

	public void setPurchaseDetails(Set<PurchaseDetail> purchaseDetails) {
		this.purchaseDetails = purchaseDetails;
	}

	public String toString() {
		return new StringBuilder("Product: {\n").
				append("\t productCode: ").append(productCode).append(",\n").
				append("\t productName: ").append(productName).append(",\n").
				append("\t productLine: ").append(productLine.getProductLine()).append(",\n").
				append("\t productScale: ").append(productScale).append(",\n").
				append("\t productVendor: ").append(productVendor).append(",\n").
				append("\t productDescription: ").append(productDescription).append(",\n").
				append("\t quantityInStock: ").append(quantityInStock).append(",\n").
				append("\t buyPrice: ").append(buyPrice).append(",\n").
				append("\t msrp: ").append(msrp).append("\n").
				append("}").
				toString();
	}

}
