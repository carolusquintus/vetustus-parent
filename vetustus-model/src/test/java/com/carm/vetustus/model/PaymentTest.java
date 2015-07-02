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
public class PaymentTest {
	
	private static final Logger LOG = LoggerFactory.getLogger(PaymentTest.class);
	
	private Session session;
	private Office office;
	private Employee employee;
	private Customer customer;
	private Payment payment;
	private PaymentPK paymentPK;
	
	@Before
	public void before() {
		session = HibernateUtil.getSessionFactory().openSession();
	}
	
	@After
	public void after() {
		session.close();
	}
	
	@Test
	public void testAACustomer() {
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
		
		paymentPK = new PaymentPK();
		paymentPK.setCustomerNumber(117);
		paymentPK.setCheckNumber("BR462567");
		
		payment = new Payment();
		payment.setPaymentPK(paymentPK);
		payment.setPaymentDate(new Timestamp(new Date().getTime()));
		payment.setAmount(57567.00);
		
		session.save(payment);
		session.getTransaction().commit();		
	}
	
	@Test
	public void testBUpdate() {
		session.beginTransaction();
		
		paymentPK = new PaymentPK();
		paymentPK.setCustomerNumber(117);
		paymentPK.setCheckNumber("BR462567");
		
		payment = new Payment();
		payment.setPaymentPK(paymentPK);
		payment.setPaymentDate(new Timestamp(new Date().getTime()));
		payment.setAmount(77567.00);
		
		session.update(payment);
		session.getTransaction().commit();		
	}
	
	@Test
	public void testCRead() {
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Payment.class);
		criteria.add(Restrictions.eq("paymentPK.customerNumber", 117));
		criteria.add(Restrictions.eq("paymentPK.checkNumber", "BR462567"));
		
		payment = (Payment) criteria.uniqueResult();
		
		LOG.info(payment.toString());
		LOG.info(payment.getCustomer().toString());
		LOG.info(payment.getCustomer().getEmployee().toString());
		LOG.info(payment.getCustomer().getEmployee().getOffice().toString());
	}
	
	@Test
	public void testDDelete() {
		session.beginTransaction();
		
		paymentPK = new PaymentPK();
		paymentPK.setCustomerNumber(117);
		paymentPK.setCheckNumber("BR462567");
		
		payment = (Payment) session.get(Payment.class, paymentPK);
		
		session.delete(payment);
		session.getTransaction().commit();
	}
	
	@Test
	public void testDDeleteCustomer() {
		session.beginTransaction();
		
		customer = (Customer) session.get(Customer.class, 117);
		employee = (Employee) session.get(Employee.class, 1077);
		office = (Office) session.get(Office.class, "8");
		
		session.delete(customer);
		session.delete(employee);
		session.delete(office);
		
		session.getTransaction().commit();
	}
}
