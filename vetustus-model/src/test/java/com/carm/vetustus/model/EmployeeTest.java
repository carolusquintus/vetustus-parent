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
public class EmployeeTest {
	
	private static final Logger LOG = LoggerFactory.getLogger(EmployeeTest.class);
	
	private Session session;
	private Office office;
	private Employee employee;
	
	@Before
	public void before() {
		session = HibernateUtil.getSessionFactory().openSession();
	}

	@After
	public void after() {
		session.close();
	}
	
	@Test
	public void testAACreateOffice() {
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
		
		session.save(office);
		session.getTransaction().commit();		
	}
	
	@Test
	public void testACreate() {
		session.beginTransaction();
		
		office = new Office();
		office.setOfficeCode("8");
		
		employee = new Employee();
		employee.setEmployeeNumber(1077);
		employee.setLastName("Rosas");
		employee.setFirstName("Carlos");
		employee.setExtension("x4625");
		employee.setEmail("crosas@vetustus.com");
		employee.setOffice(office);
		employee.setReportsTo(1002);
		employee.setJobTitle("VP Sales");
		
		session.save(employee);
		session.getTransaction().commit();
	}
	
	@Test
	public void testBUpdate() {
		session.beginTransaction();
		
		office = new Office();
		office.setOfficeCode("8");
		
		employee = new Employee();
		employee.setEmployeeNumber(1077);
		employee.setLastName("Rosae");
		employee.setFirstName("Carolus");
		employee.setExtension("x4625");
		employee.setEmail("crosae@vetustus.com");
		employee.setOffice(office);
		employee.setReportsTo(1002);
		employee.setJobTitle("VP Sales CEO");
		
		session.update(employee);
		session.getTransaction().commit();
	}
	
	@Test
	public void testCRead() {
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.eq("employeeNumber", 1077));
		
		employee = (Employee) criteria.uniqueResult();
		
		LOG.info(employee.toString());
		LOG.info(employee.getOffice().toString());
	}
	
	@Test
	public void testDDelete() {
		session.beginTransaction();
		
		employee = (Employee) session.get(Employee.class, 1077);
		
		session.delete(employee);
		session.getTransaction().commit();		
	}
	
	@Test
	public void testDDeleteOffice() {
		session.beginTransaction();
		
		office = (Office) session.get(Office.class, "8");
		
		session.delete(office);
		session.getTransaction().commit();		
	}
}
