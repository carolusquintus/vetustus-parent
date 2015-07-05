package com.carm.vetustus.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
		OfficeTest.class,
		EmployeeTest.class,
		CustomerTest.class,
		PaymentTest.class,
		ProductLineTest.class,
		ProductTest.class,
		PurchaseTest.class,
		PurchaseDetailTest.class		 
})
public class AllTests {

}
