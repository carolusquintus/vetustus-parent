package com.carm.vetustus.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
@NamedQueries({
	@NamedQuery(name = "employee.findByCompleteName", query = "from Employee where firstName = :firstName and lastName = :lastName"),
	@NamedQuery(name = "employee.findByReportsTo", query = "from Employee where reportsTo = :reportsTo"),
	@NamedQuery(name = "employee.findByJobTitle", query = "from Employee where jobTitle = :jobTitle")
})
public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2189691745553352999L;
	
	@Id
	@Column(name = "employee_number", nullable = false)
	private Integer employeeNumber;
	
	@Column(name = "last_name", nullable = false, length = 50)
	private String lastName;
	
	@Column(name = "first_name", nullable = false, length = 50)
	private String firstName;
	
	@Column(name = "extension", nullable = false, length = 10)
	private String extension;
	
	@Column(name = "email", nullable = false, length = 100)
	private String email;
	
	@ManyToOne
	@JoinColumn(name = "office_code", nullable = false)
	private Office office;
	
	@Column(name = "reports_to", nullable = true)
	private Integer reportsTo;
	
	@Column(name = "job_title", nullable = false, length = 50)
	private String jobTitle;
	
	@OneToMany(mappedBy = "employee")
	private Set<Customer> customers = new HashSet<Customer>(0);
	
	public Integer getEmployeeNumber() {
		return employeeNumber;
	}
	
	public void setEmployeeNumber(Integer employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getExtension() {
		return extension;
	}
	
	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Office getOffice() {
		return office;
	}
	
	public void setOffice(Office office) {
		this.office = office;
	}
	
	public Integer getReportsTo() {
		return reportsTo;
	}
	
	public void setReportsTo(Integer reportsTo) {
		this.reportsTo = reportsTo;
	}
	
	public String getJobTitle() {
		return jobTitle;
	}
	
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	public String toString() {
		return new StringBuilder(getClass().getSimpleName()).append(": {\n").
				append("\t employeeNumber: ").append(employeeNumber).append(",\n").
				append("\t lastName: ").append(lastName).append(",\n").
				append("\t firstName: ").append(firstName).append(",\n").
				append("\t extension: ").append(extension).append(",\n").
				append("\t email: ").append(email).append(",\n").
				append("\t officeCode: ").append(office.getOfficeCode()).append(",\n").
				append("\t reportsTo: ").append(reportsTo).append(",\n").
				append("\t jobTitle: ").append(jobTitle).append("\n").
				append("}").
				toString();
		
	}

}
