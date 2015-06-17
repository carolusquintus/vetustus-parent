package com.carm.vetustus.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "payment")
public class Payment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1281443111438957886L;
	
	@EmbeddedId
	private PaymentPK paymentPK; 
	
	@ManyToOne
	@JoinColumn(name = "customer_number", insertable = false, updatable = false)
	private Customer customer;
	
	@Column(name = "payment_date", nullable = false)
	private Timestamp paymentDate;
	
	@Column(name = "amount", nullable = false)
	private Double amount;

	public PaymentPK getPaymentPK() {
		return paymentPK;
	}

	public void setPaymentPK(PaymentPK paymentPK) {
		this.paymentPK = paymentPK;
	}

	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public Timestamp getPaymentDate() {
		return paymentDate;
	}
	
	public void setPaymentDate(Timestamp paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	public Double getAmount() {
		return amount;
	}
	
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public String toString() {
		return new StringBuilder("Payment: {\n").
				append(paymentPK).
				append("\t paymentDate: ").append(paymentDate).append(",\n").
				append("\t amount: ").append(amount).append("\n").
				append("}").
				toString();
	}
	
	
}
