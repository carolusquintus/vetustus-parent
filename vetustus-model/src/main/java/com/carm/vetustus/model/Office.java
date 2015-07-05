package com.carm.vetustus.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "office")
@NamedQueries({
	@NamedQuery(name = "office.findByCountry", query = "from Office where country = :country")
})
public class Office implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5162674072680002618L;
	
	@Id
	@Column(name = "office_code", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer officeCode;
	
	@Column(name = "city", nullable = false, length = 50)
	private String city;
	
	@Column(name = "phone", nullable = false, length = 50)
	private String phone;
	
	@Column(name = "address_line_1", nullable = false, length = 50)
	private String addressLine1;
	
	@Column(name = "address_line_2", nullable = true, length = 50)
	private String addressLine2;
	
	@Column(name = "state", nullable = true, length = 50)
	private String state;
	
	@Column(name = "country", nullable = false, length = 50)
	private String country;
	
	@Column(name = "postal_code", nullable = false, length = 15)
	private String postalCode;
	
	@Column(name = "territory", nullable = false, length = 10)
	private String territory;
	
	@OneToMany(mappedBy = "office")
	private Set<Employee> employees = new HashSet<Employee>(0);
	
	public Integer getOfficeCode() {
		return officeCode;
	}
	
	public void setOfficeCode(Integer officeCode) {
		this.officeCode = officeCode;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
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
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getPostalCode() {
		return postalCode;
	}
	
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	public String getTerritory() {
		return territory;
	}
	
	public void setTerritory(String territory) {
		this.territory = territory;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
	
	public String toString() {
		return new StringBuilder(getClass().getSimpleName()).append(": {\n").
				append("\t officeCode: ").append(officeCode).append(",\n").
				append("\t city: ").append(city).append(",\n").
				append("\t phone: ").append(phone).append(",\n").
				append("\t addressLine1: ").append(addressLine1).append(",\n").
				append("\t addressLine2: ").append(addressLine2).append(",\n").
				append("\t state: ").append(state).append(",\n").
				append("\t country: ").append(country).append(",\n").
				append("\t postalCode: ").append(postalCode).append(",\n").
				append("\t territory: ").append(territory).append("\n").
				append("}").
				toString();
	}

}
