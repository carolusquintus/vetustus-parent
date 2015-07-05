package com.carm.vetustus.model;

import static org.hamcrest.CoreMatchers.*;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.After;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.carm.vetustus.util.HibernateUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OfficeTest {
	
	private static final Logger LOG = LoggerFactory.getLogger(OfficeTest.class);
	
	private Session session;
	private Office office;
	private static Integer officeCode;
	
	@Before
	public void before() {
		session = HibernateUtil.getSessionFactory().openSession();
	}
	
	@After
	public void after() {
		session.close();
	}
	
	@Test
	public void testACreate() {
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
		
		officeCode = (Integer) session.save(office);
		
		session.getTransaction().commit();
		
		assertThat(officeCode, is(notNullValue()));
//		assertThat(key, is(equalTo(8)));
		LOG.info(officeCode.toString());
	}

	@Test
	public void testBUpdate() {
		session.beginTransaction();
		
		LOG.info(officeCode.toString());
		
		office = new Office();
		office.setOfficeCode(officeCode);
		office.setCity("Mexico City");
		office.setPhone("+52 55 5612 3450");
		office.setAddressLine1("156 Casas Grandes");
		office.setAddressLine2("Apt. 12");
		office.setState(null);
		office.setCountry("Mexico");
		office.setPostalCode("03020");
		office.setTerritory("LATAM");
		
		session.update(office);
		session.getTransaction().commit();
	}
	
	@Test
	public void testCRead() {
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Office.class);
		criteria.add(Restrictions.eq("officeCode", officeCode));
		
		office = (Office) criteria.uniqueResult();
		
		assertThat(office, is(notNullValue()));
		assertThat(office.getOfficeCode(), is(equalTo(officeCode)));
		
		LOG.info(office.toString());
		LOG.info(office.getEmployees().toArray().toString());
	}
	
	@Test
	public void testDDelete() {
		session.beginTransaction();
		
		office = (Office) session.get(Office.class, officeCode);
		
		session.delete(office);
		
		session.getTransaction().commit();
		
		session.createSQLQuery("ALTER TABLE office AUTO_INCREMENT = " + (officeCode - 1)).executeUpdate();
	}
}
