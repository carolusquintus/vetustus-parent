package com.carm.vetustus.domain;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.beanutils.BeanUtils;

import com.carm.vetustus.model.Office;

@XmlRootElement(name = "office")
@XmlAccessorType(XmlAccessType.FIELD)
public class OfficeDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5495696854981157752L;
	
	@XmlElement(name = "officeCode")
	private Integer officeCode;
	
	@XmlElement(name = "city")
	private String city;
	
	@XmlElement(name = "phone")
	private String phone;
	
	@XmlElement(name = "addressLine1")
	private String addressLine1;
	
	@XmlElement(name = "addressLine2")
	private String addressLine2;
	
	@XmlElement(name = "state")
	private String state;
	
	@XmlElement(name = "country")
	private String country;
	
	@XmlElement(name = "postalCode")
	private String postalCode;
	
	@XmlElement(name = "territory")
	private String territory;

	public OfficeDTO() {
	}

	public OfficeDTO(Integer officeCode, String city, String phone,
			String addressLine1, String addressLine2, String state,
			String country, String postalCode, String territory) {
		this.officeCode = officeCode;
		this.city = city;
		this.phone = phone;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.state = state;
		this.country = country;
		this.postalCode = postalCode;
		this.territory = territory;
	}

	public OfficeDTO(Office office) {
		try {
			BeanUtils.copyProperties(this, office);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

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
