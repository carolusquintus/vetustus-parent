package com.carm.vetustus.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
		CustomerTest.class, 
		EmployeeTest.class, 
		OfficeTest.class, 
		PaymentTest.class, 
		ProductLineTest.class, 
		ProductTest.class,
		PurchaseDetailTest.class, 
		PurchaseTest.class 
})
public class AllTests {

}
