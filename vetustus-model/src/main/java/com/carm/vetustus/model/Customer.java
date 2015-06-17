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
@Table(name = "customer")
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5412755898810482498L;
	
	@Id
	@Column(name = "customer_number", nullable = false)
	private Integer customerNumber;
	
	@Column(name = "customer_name", nullable = false, length = 50)
	private String customerName;
	
	@Column(name = "contact_last_name", nullable = false, length = 50)
	private String contactLastName;
	
	@Column(name = "contact_first_name", nullable = false, length = 50)
	private String contactFirstName;
	
	@Column(name = "phone", nullable = false, length = 50)
	private String phone;
	
	@Column(name = "address_line_1", nullable = false, length = 50)
	private String addressLine1;
	
	@Column(name = "address_line_2", nullable = true, length = 50)
	private String addressLine2;
	
	@Column(name = "city", nullable = false, length = 50)
	private String city;
	
	@Column(name = "state", nullable = true, length = 50)
	private String state;
	
	@Column(name = "postal_code", nullable = true, length = 15)
	private String postalCode;
	
	@Column(name = "country", nullable = false, length = 50)
	private String country;
	
	@ManyToOne
	@JoinColumn(name = "sales_rep_employee_number", nullable = true)
	private Employee employee;
	
	@Column(name = "credit_limit", nullable = true)
	private Double creditLimit;
	
	@OneToMany(mappedBy = "customer")
	private Set<Payment> payments = new HashSet<Payment>(0);
	
	@OneToMany(mappedBy = "customer")
	private Set<Purchase> purchases = new HashSet<Purchase>(0);
	
	public Integer getCustomerNumber() {
		return customerNumber;
	}
	
	public void setCustomerNumber(Integer customerNumber) {
		this.customerNumber = customerNumber;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public String getContactLastName() {
		return contactLastName;
	}
	
	public void setContactLastName(String contactLastName) {
		this.contactLastName = contactLastName;
	}
	
	public String getContactFirstName() {
		return contactFirstName;
	}
	
	public void setContactFirstName(String contactFirstName) {
		this.contactFirstName = contactFirstName;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getAddressLine1() {
		return addressLine1;
	}
	
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	
	public String getAddressLine2() {
		return addressLine2;
	}
	
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getPostalCode() {
		return postalCode;
	}
	
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public Employee getEmployee() {
		return employee;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public Double getCreditLimit() {
		return creditLimit;
	}
	
	public void setCreditLimit(Double creditLimit) {
		this.creditLimit = creditLimit;
	}

	public Set<Payment> getPayments() {
		return payments;
	}

	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}

	public Set<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(Set<Purchase> purchases) {
		this.purchases = purchases;
	}
	
	public String toString() {
		return new StringBuilder("Customer: {\n").
				append("\t customerNumber: ").append(customerNumber).append(",\n").
				append("\t customerName: ").append(customerName).append(",\n").
				append("\t contactLastName: ").append(contactLastName).append(",\n").
				append("\t contactFirstName: ").append(contactFirstName).append(",\n").
				append("\t phone: ").append(phone).append(",\n").
				append("\t addressLine1: ").append(addressLine1).append(",\n").
				append("\t addressLine2: ").append(addressLine2).append(",\n").
				append("\t city: ").append(city).append(",\n").
				append("\t state: ").append(state).append(",\n").
				append("\t postalCode: ").append(postalCode).append(",\n").
				append("\t country: ").append(country).append(",\n").
				append("\t salesRepEmployeeNumber: ").append(getEmployee().getEmployeeNumber()).append(",\n").
				append("\t creditLimit: ").append(creditLimit).append("\n").
				append("}").
				toString();
	}

}
