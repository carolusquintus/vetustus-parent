package com.carm.vetustus.model;

import java.sql.Timestamp;
import java.util.Date;

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
public class PurchaseTest {
	
	private static final Logger LOG = LoggerFactory.getLogger(PurchaseTest.class);
	private Office office;
	private Employee employee;
	private Customer customer;
	private Purchase purchase;
	
	private Session session;
	
	@Before
	public void before() {
		session = HibernateUtil.getSessionFactory().openSession();
	}
	
	@After
	public void after() {
		session.close();
	}
	
	@Test
	public void testAACreateCustomer() {
		session.beginTransaction();
		
		office = new Office();
		office.setOfficeCode("8");
		office.setCity("Mexico City");
		office.setPhone("+52 55 5612 5172");
		office.setAddressLine1("187 Casas Grandes");
		office.setAddressLine2("Apt. 11");
		office.setState(null);
		office.setCountry("Mexico");
		office.setPostalCode("03023");
		office.setTerritory("LATAM");
		
		employee = new Employee();
		employee.setEmployeeNumber(1077);
		employee.setLastName("Rosas");
		employee.setFirstName("Carlos");
		employee.setExtension("x4625");
		employee.setEmail("crosas@vetustus.com");
		employee.setOffice(office);
		employee.setReportsTo(1002);
		employee.setJobTitle("VP Sales");
		
		customer = new Customer();
		customer.setCustomerNumber(117);
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
		
		session.save(office);
		session.save(employee);		
		session.save(customer);
		session.getTransaction().commit();
	}
	
	@Test
	public void testACreate() {
		session.beginTransaction();
		
		customer = new Customer();
		customer.setCustomerNumber(117);
		
		purchase = new Purchase();
		purchase.setPurchaseNumber(10426);
		purchase.setPurchaseDate(new Timestamp(new Date().getTime()));
		purchase.setRequiredDate(new Timestamp(new Date().getTime() + 345600000));
		purchase.setShippedDate(null);
		purchase.setStatus("In Process");
		purchase.setComments("Customer has worked with some of our vendors and requieres preference in its shipment.");
		purchase.setCustomer(customer);

		
		session.save(purchase);
		session.getTransaction().commit();
	}
	
	@Test
	public void testBUpdate() {
		session.beginTransaction();
		
		customer = new Customer();
		customer.setCustomerNumber(117);
		
		purchase = new Purchase();
		purchase.setPurchaseNumber(10426);
		purchase.setShippedDate(new Timestamp(new Date().getTime() + 172800000));
		purchase.setStatus("Shipped");
		purchase.setCustomer(customer);
		
		session.update(purchase);
		session.getTransaction().commit();
	}
	
	@Test
	public void testCRead() {
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Purchase.class);
		criteria.add(Restrictions.eq("purchaseNumber", 10426));
		
		purchase = (Purchase) criteria.uniqueResult();
		
		LOG.info(purchase.toString());
		LOG.info(purchase.getCustomer().toString());
		LOG.info(purchase.getCustomer().getEmployee().toString());
		LOG.info(purchase.getCustomer().getEmployee().getOffice().toString());
	}
	
	@Test
	public void testDDelete() {
		session.beginTransaction();
		
		purchase = (Purchase) session.get(Purchase.class, 10426);
		
		session.delete(purchase);
		session.getTransaction().commit();
	}
	
	@Test
	public void testDDeleteCustomer() {
		session.beginTransaction();
		
		customer = (Customer) session.get(Customer.class, 117);
		session.delete(customer);
		session.getTransaction().commit();
		
		session.beginTransaction();
		
		employee = (Employee) session.get(Employee.class, 1077);
		session.delete(employee);
		session.getTransaction().commit();
		
		session.beginTransaction();
		
		office = (Office) session.get(Office.class, "8");
		session.delete(office);
		session.getTransaction().commit();
	}
}
