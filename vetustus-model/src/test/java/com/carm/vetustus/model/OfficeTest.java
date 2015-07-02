package com.carm.vetustus.model;

import static org.hamcrest.CoreMatchers.*;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.After;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.FixMethodOrder;
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
		office.setOfficeCode("8");
		office.setCity("Mexico City");
		office.setPhone("+52 55 5612 5172");
		office.setAddressLine1("187 Casas Grandes");
		office.setAddressLine2("Apt. 11");
		office.setState(null);
		office.setCountry("Mexico");
		office.setPostalCode("03023");
		office.setTerritory("LATAM");
		
		final String key = (String) session.save(office);
		
		session.getTransaction().commit();
		
		assertThat(key, is(notNullValue()));
		assertThat(key, is(equalTo("8")));
		LOG.info(key);
	}

	@Test
	public void testBUpdate() {
		session.beginTransaction();
		
		office = new Office();
		office.setOfficeCode("8");
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
		criteria.add(Restrictions.eq("officeCode", "8"));
		
		office = (Office) criteria.uniqueResult();
		
		assertThat(office, is(notNullValue()));
		assertThat(office.getOfficeCode(), is(equalTo("8")));
		assertThat(office.getOfficeCode(), is(equalTo("8")));
		assertThat(office.getOfficeCode(), is(equalTo("8")));
		assertThat(office.getOfficeCode(), is(equalTo("8")));
		assertThat(office.getOfficeCode(), is(equalTo("8")));
		assertThat(office.getOfficeCode(), is(equalTo("8")));
		
		LOG.info(office.toString());
		LOG.info(office.getEmployees().toArray().toString());
	}
	
	@Test
	public void testDDelete() {
		session.beginTransaction();
		
		office = (Office) session.get(Office.class, "8");
		
		session.delete(office);
		session.getTransaction().commit();
	}
}
