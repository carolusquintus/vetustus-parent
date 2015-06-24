package com.carm.vetustus.persist.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;

import com.carm.vetustus.model.Employee;
import com.carm.vetustus.persist.dao.AbstractDao;
import com.carm.vetustus.persist.dao.EmployeeDao;

public class EmployeeDaoImpl extends AbstractDao implements EmployeeDao {

	@Override
	public Integer save(Employee type) {
		Integer key = (Integer) getSession().save(type);
		return key;
	}

	@Override
	public Employee find(Integer key) {
		Employee employee = (Employee) getSession().get(Employee.class, key);
		return employee;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Employee> findAll() {
		Criteria criteria = getSession().createCriteria(Employee.class);
		final List<Employee> employees = (List<Employee>) criteria.list();
		return employees;
	}

	@Override
	public void update(Employee type) {
		getSession().update(type);
	}

	@Override
	public void delete(Employee type) {
		getSession().delete(type);
	}

	@Override
	public void deleteById(Integer key) {
		final Employee employee = (Employee) getSession().get(Employee.class, key);
		getSession().delete(employee);
	}

	@Override
	public Employee findByCompleteName(String firstName, String lastName) {
		Query query = getSession().getNamedQuery("office.findByCompleteName");
		query.setString("firstName", firstName);
		query.setString("lastName", lastName);
		
		final Employee employee = (Employee) query.uniqueResult(); 
		return employee;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Employee> findByReportsTo(Integer reportsTo) {
		Query query = getSession().getNamedQuery("office.findByReportsTo");
		query.setInteger("reportsTo", reportsTo);
		
		final List<Employee> employees = (List<Employee>) query.list(); 
		return employees;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Employee> findByJobTitle(String jobTitle) {
		Query query = getSession().getNamedQuery("office.findByJobTitle");
		query.setString("jobTitle", jobTitle);
		
		final List<Employee> employees = (List<Employee>) query.list(); 
		return employees;
	}

}
