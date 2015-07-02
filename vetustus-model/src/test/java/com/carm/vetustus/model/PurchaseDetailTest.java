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
public class PurchaseDetailTest {
	
	private static final Logger LOG = LoggerFactory.getLogger(PurchaseDetailTest.class);
	
	private Session session;
	private Office office;
	private Employee employee;
	private Customer customer;
	private Purchase purchase;
	private ProductLine productLine;
	private Product product;
	private PurchaseDetail purchaseDetail;
	private PurchaseDetailPK purchaseDetailPK;
	
	@Before
	public void before() {
		session = HibernateUtil.getSessionFactory().openSession();
	}
	
	@After
	public void after() {
		session.close();
	}
	
	@Test
	public void testAACreateAllEntities() {
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
		
		purchase = new Purchase();
		purchase.setPurchaseNumber(10426);
		purchase.setPurchaseDate(new Timestamp(new Date().getTime()));
		purchase.setRequiredDate(new Timestamp(new Date().getTime() + 345600000));
		purchase.setShippedDate(null);
		purchase.setStatus("In Process");
		purchase.setComments("Customer has worked with some of our vendors and requieres preference in its shipment.");
		purchase.setCustomer(customer);
		
		productLine = new ProductLine();
		productLine.setProductLine("GTI");
		productLine.setProductLineName("Grand Tourer Injection");
		productLine.setTextDescription("GTI is a fuel-injection car model variant. Traditionally used for Grand Tourer cars, the term is now applied to various hot hatchbacks, even though);they do not have productLine.set(the luxury traditionally associated with Grand Tourers.");
		productLine.setHtmlDescription(null);
		productLine.setImage(null);
		
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
		
		session.save(office);
		session.save(employee);
		session.save(customer);		
		session.save(purchase);
		session.save(productLine);
		session.save(product);
		session.getTransaction().commit();
	}
	
	@Test
	public void testACreate() {
		session.beginTransaction();
		
		purchaseDetailPK = new PurchaseDetailPK();
		purchaseDetailPK.setPurchaseNumber(10426);
		purchaseDetailPK.setProductCode("S12_4477");
		
		purchaseDetail = new PurchaseDetail();
		purchaseDetail.setPurchaseDetailPK(purchaseDetailPK);
		purchaseDetail.setQuantityOrdered(80);
		purchaseDetail.setPriceEach(342.45);
		purchaseDetail.setPurchaseLineNumber(10);
		
		session.save(purchaseDetail);
		session.getTransaction().commit();
	}
	
	@Test
	public void testBUpdate() {
		session.beginTransaction();
		
		purchaseDetailPK = new PurchaseDetailPK();
		purchaseDetailPK.setPurchaseNumber(10426);
		purchaseDetailPK.setProductCode("S12_4477");
		
		purchaseDetail = new PurchaseDetail();
		purchaseDetail.setPurchaseDetailPK(purchaseDetailPK);
		purchaseDetail.setQuantityOrdered(90);
		purchaseDetail.setPriceEach(342312.45);
		purchaseDetail.setPurchaseLineNumber(40);
		
		session.update(purchaseDetail);
		session.getTransaction().commit();	
	}
	
	@Test
	public void testCRead() {
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(PurchaseDetail.class);
		criteria.add(Restrictions.eq("purchaseDetailPK.purchaseNumber", 10426));
		criteria.add(Restrictions.eq("purchaseDetailPK.productCode", "S12_4477"));
		
		purchaseDetail = (PurchaseDetail) criteria.uniqueResult();
		
		LOG.info(purchaseDetail.toString());
		LOG.info(purchaseDetail.getProduct().toString());
		LOG.info(purchaseDetail.getProduct().getProductLine().toString());
		LOG.info(purchaseDetail.getPurchase().toString());
		LOG.info(purchaseDetail.getPurchase().getCustomer().toString());
		for(Payment p : purchaseDetail.getPurchase().getCustomer().getPayments()) LOG.info(p.toString());
		LOG.info(purchaseDetail.getPurchase().getCustomer().getEmployee().toString());
		LOG.info(purchaseDetail.getPurchase().getCustomer().getEmployee().getOffice().toString());		
	}
	
	@Test
	public void testDDelete() {
		session.beginTransaction();
		
		purchaseDetailPK = new PurchaseDetailPK();
		purchaseDetailPK.setPurchaseNumber(10426);
		purchaseDetailPK.setProductCode("S12_4477");
		
		purchaseDetail = (PurchaseDetail) session.get(PurchaseDetail.class, purchaseDetailPK);
		
		session.delete(purchaseDetail);
		session.getTransaction().commit();
	}
	
	@Test
	public void testDDeleteAllEntities() {
		session.beginTransaction();
		
		product = (Product) session.get(Product.class, "S12_4477");
		productLine = (ProductLine) session.get(ProductLine.class, "GTI");
		purchase = (Purchase) session.get(Purchase.class, 10426);
		customer = (Customer) session.get(Customer.class, 117);
		employee = (Employee) session.get(Employee.class, 1077);
		office = (Office) session.get(Office.class, "8");
		
		session.delete(product);
		session.delete(productLine);
		session.delete(purchase);
		session.delete(customer);
		session.delete(employee);
		session.delete(office);
		
		session.getTransaction().commit();
	}
}
