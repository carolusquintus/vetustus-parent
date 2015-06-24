package com.carm.vetustus.persist.dao;

import java.util.List;

import com.carm.vetustus.model.Employee;

public interface EmployeeDao extends GenericDao<Employee, Integer> {
	
	public Employee findByCompleteName(String firstName, String lastName);
	
	public List<Employee> findByReportsTo(Integer reportsTo);
	
	public List<Employee> findByJobTitle(String jobTitle);

}
