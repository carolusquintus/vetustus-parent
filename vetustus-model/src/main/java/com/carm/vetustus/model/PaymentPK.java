package com.carm.vetustus.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PaymentPK implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 76957824796354884L;

	@Column(name = "customer_number", nullable = false, updatable = false)
	private Integer customerNumber;
	
	@Column(name = "check_number", nullable = false, updatable = false, length = 50)
	private String checkNumber;

	public Integer getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(Integer customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}
	
	public String toString() {
		return new StringBuilder().
				append("\t customerNumber: ").append(customerNumber).append(",\n").
				append("\t checkNumber: ").append(checkNumber).append(",\n").
				toString();
	}

}
