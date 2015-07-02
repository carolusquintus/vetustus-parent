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
public class ProductLineTest {
	
	private static final Logger LOG = LoggerFactory.getLogger(ProductLineTest.class);
	
	private Session session;
	private ProductLine productLine;
	
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
		
		productLine = new ProductLine();
		productLine.setProductLine("GTI");
		productLine.setProductLineName("Grand Tourer Injection");
		productLine.setTextDescription("GTI is a fuel-injection car model variant. Traditionally used for Grand Tourer cars, the term is now applied to various hot hatchbacks, even though);they do not have productLine.set(the luxury traditionally associated with Grand Tourers.");
		productLine.setHtmlDescription(null);
		productLine.setImage(null);

		session.save(productLine);
		session.getTransaction().commit();
	}
	
	@Test
	public void testBUpdate() {
		session.beginTransaction();
		
		productLine = new ProductLine();
		productLine.setProductLine("GTI");
		productLine.setProductLineName("Grand Tourer Injection");
		productLine.setTextDescription("GTI is a fuel-injection car model variant. Traditionally used for Grand Tourer cars, the term is now applied to various hot hatchbacks, even though);they do not have productLine.set(the luxury traditionally associated with Grand Tourers.");
		productLine.setHtmlDescription("<html></html>");
		productLine.setImage(null);

		session.update(productLine);
		session.getTransaction().commit();
	}
	
	@Test
	public void testCRead() {
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(ProductLine.class);
		criteria.add(Restrictions.eq("productLine", "GTI"));
		
		productLine = (ProductLine) criteria.uniqueResult();
		
		LOG.info(productLine.toString());
	}
	
	@Test
	public void testDDelete() {
		session.beginTransaction();
		
		productLine = (ProductLine) session.get(ProductLine.class, "GTI");

		session.delete(productLine);
		session.getTransaction().commit();
	}
}
