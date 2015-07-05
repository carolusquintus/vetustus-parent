package com.carm.vetustus.model;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.carm.vetustus.util.HibernateUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerTest {

	private static final Logger LOG = LoggerFactory.getLogger(CustomerTest.class);
	
	private Session session;
	private Office office;
	private Employee employee;
	private Customer customer;
	
	private static Integer officeCode;
	private static Integer employeeNumber;
	private static Integer customerNumber;
	
	@Before
	public void before() {
		session = HibernateUtil.getSessionFactory().openSession();
	}
	
	@After
	public void after() {
		session.close();
	}
	
	@Test
	public void testAACreateEmployee() {
		session.beginTransaction();
		
		office = new Office();
		office.setCity("Mexico City");
		office.setPhone("+52 55 5612 5172");
		office.setAddressLine1("187 Casas Grandes");
		office.setAddressLine2("Apt. 11");
		office.setState(null);
		office.setCountry("Mexico");
		office.setPostalCode("03023");
		office.setTerritory("LATAM");
				
		employee = new Employee();
		employee.setLastName("Rosas");
		employee.setFirstName("Carlos");
		employee.setExtension("x4625");
		employee.setEmail("crosas@vetustus.com");
		employee.setOffice(office);
		employee.setReportsTo(1002);
		employee.setJobTitle("VP Sales");
		
		officeCode = (Integer) session.save(office);
		employeeNumber = (Integer) session.save(employee);
		session.getTransaction().commit();
	}
	
	@Test
	public void testACreate() {
		session.beginTransaction();
		
		employee = new Employee();
		employee.setEmployeeNumber(employeeNumber);		
		
		customer = new Customer();
		customer.setCustomerName("Le Bachete Prem");
		customer.setContactLastName("Soriano");
		customer.setContactFirstName("Joan");
		customer.setPhone("50.64.8333");
		customer.setAddressLine1("77, Av des Frinqe Otrages");
		customer.setAddressLine2(null);
		customer.setCity("Lyon");
		customer.setState(null);
		customer.setPostalCode("44000");
		customer.setCountry("France");
		customer.setEmployee(employee);
		customer.setCreditLimit(118200.00);
		
		customerNumber = (Integer) session.save(customer);
		session.getTransaction().commit();
	}
	
	@Test
	public void testBUpdate() {
		session.beginTransaction();
		
		employee = new Employee();
		employee.setEmployeeNumber(employeeNumber);		
		
		customer = new Customer();
		customer.setCustomerNumber(customerNumber);
		customer.setCustomerName("Le Batata Praem");
		customer.setContactLastName("Vargas");
		customer.setContactFirstName("Luis");
		customer.setPhone("50.64.8333");
		customer.setAddressLine1("77, Av des Amargue");
		customer.setAddressLine2(null);
		customer.setCity("Avignon");
		customer.setState(null);
		customer.setPostalCode("45000");
		customer.setCountry("France");
		customer.setEmployee(employee);
		customer.setCreditLimit(118277.45);
		
		session.update(customer);
		session.getTransaction().commit();
	}
	
	@Test
	public void testCRead() {
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Customer.class);
		criteria.add(Restrictions.eq("customerNumber", customerNumber));
		
		customer = (Customer) criteria.uniqueResult();
		
		LOG.info(customer.toString());
		LOG.info(customer.getEmployee().toString());
		LOG.info(customer.getEmployee().getOffice().toString());
	}
	
	@Test
	public void testDDelete() {
		session.beginTransaction();
		
		customer = (Customer) session.get(Customer.class, customerNumber);
		
		session.delete(customer);
		
		session.getTransaction().commit();
		
		session.createSQLQuery("ALTER TABLE customer AUTO_INCREMENT = " + (customerNumber - 1)).executeUpdate();
	}
	
	@Test
	public void testDDeleteEmployee() {
		session.beginTransaction();
		
		employee = (Employee) session.get(Employee.class, employeeNumber);		
		office = (Office) session.get(Office.class, officeCode);
		
		session.delete(employee);
		session.delete(office);
		
		session.getTransaction().commit();
		
		session.createSQLQuery("ALTER TABLE employee AUTO_INCREMENT = " + (employeeNumber - 1)).executeUpdate();
		session.createSQLQuery("ALTER TABLE office AUTO_INCREMENT = " + (officeCode - 1)).executeUpdate();
	}
}
