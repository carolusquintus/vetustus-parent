package com.carm.vetustus.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "purchase")
public class Purchase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3144769236484875716L;
	
	@Id
	@Column(name = "purchase_number", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer purchaseNumber;
	
	@Column(name = "purchase_date", nullable = false, updatable = false)
	private Timestamp purchaseDate;
	
	@Column(name = "required_date", nullable = false, updatable = false)
	private Timestamp requiredDate;
	
	@Column(name = "shipped_date", nullable = true)
	private Timestamp shippedDate;
	
	@Column(name = "status", nullable = false, length = 15)
	private String status;
	
	@Column(name = "comments", nullable = true, updatable = false)
	private String comments;
	
	@ManyToOne
	@JoinColumn(name = "customer_number", nullable = false)
	private Customer customer;
	
	@OneToOne(mappedBy = "purchase")
	private PurchaseDetail purchaseDetail;
	
	public Integer getPurchaseNumber() {
		return purchaseNumber;
	}
	
	public void setPurchaseNumber(Integer purchaseNumber) {
		this.purchaseNumber = purchaseNumber;
	}
	
	public Timestamp getPurchaseDate() {
		return purchaseDate;
	}
	
	public void setPurchaseDate(Timestamp purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	
	public Timestamp getRequiredDate() {
		return requiredDate;
	}
	
	public void setRequiredDate(Timestamp requiredDate) {
		this.requiredDate = requiredDate;
	}
	
	public Timestamp getShippedDate() {
		return shippedDate;
	}
	
	public void setShippedDate(Timestamp shippedDate) {
		this.shippedDate = shippedDate;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getComments() {
		return comments;
	}
	
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public PurchaseDetail getPurchaseDetail() {
		return purchaseDetail;
	}

	public void setPurchaseDetail(PurchaseDetail purchaseDetail) {
		this.purchaseDetail = purchaseDetail;
	}
	
	public String toString() {
		return new StringBuilder(getClass().getSimpleName()).append(": {\n").
				append("\t purchaseNumber: ").append(purchaseNumber).append(",\n").
				append("\t purchaseDate: ").append(purchaseDate).append(",\n").
				append("\t requiredDate: ").append(requiredDate).append(",\n").
				append("\t shippedDate: ").append(shippedDate).append(",\n").
				append("\t status: ").append(status).append(",\n").
				append("\t comments: ").append(comments).append(",\n").
				append("\t customerNumber: ").append(customer.getCustomerNumber()).append("\n").
				append("}").
				toString();
	}

}
