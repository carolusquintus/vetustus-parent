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
public class ProductTest {
	
	private static final Logger LOG = LoggerFactory.getLogger(ProductTest.class);
	
	private Session session;
	private ProductLine productLine;
	private Product product;
	
	@Before
	public void before() {
		session = HibernateUtil.getSessionFactory().openSession();
	}

	@Test
	public void testAACreateProductLine() {
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
	public void testACreate() {
		session.beginTransaction();
		
		productLine = new ProductLine();
		productLine.setProductLine("GTI");
		
		product = new Product();
		product.setProductCode("S12_4477");
		product.setProductName("1961 Maserati 3500 GTI");
		product.setProductLine(productLine);
		product.setProductScale("1:15");
		product.setProductVendor("Exoto Designs");
		product.setProductDescription("1:15 scale die-cast about 20\' long Hood opens, Rubber wheels");
		product.setQuantityInStock(7777);
		product.setBuyPrice(355.7);
		product.setMsrp(11128.5);

		session.save(product);
		session.getTransaction().commit();
	}
	
	@Test
	public void testBUpdate() {
		session.beginTransaction();
		
		productLine = new ProductLine();
		productLine.setProductLine("GTI");
		
		product = new Product();
		product.setProductCode("S12_4477");
		product.setProductName("1961 Maserati 3500 GTI");
		product.setProductLine(productLine);
		product.setProductScale("1:15");
		product.setProductVendor("Exoto Designs");
		product.setProductDescription("1:15 scale die-cast about 20\' long Hood opens, Rubber wheels");
		product.setQuantityInStock(7567);
		product.setBuyPrice(55.7);
		product.setMsrp(118.5);

		session.update(product);
		session.getTransaction().commit();
	}
	
	@Test
	public void testCRead() {
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Product.class);
		criteria.add(Restrictions.eq("productCode", "S12_4477"));
		
		product = (Product) criteria.uniqueResult();
		
		LOG.info(product.toString());
	}
	
	@Test
	public void testDDelete() {
		session.beginTransaction();
		
		product = (Product) session.get(Product.class, "S12_4477");

		session.delete(product);
		session.getTransaction().commit();
	}
	
	@Test
	public void testDDeleteProductLine() {
		session.beginTransaction();
		
		productLine = (ProductLine) session.get(ProductLine.class, "GTI");

		session.delete(productLine);
		session.getTransaction().commit();
	}
	
	@After
	public void after() {
		session.close();
	}

}
