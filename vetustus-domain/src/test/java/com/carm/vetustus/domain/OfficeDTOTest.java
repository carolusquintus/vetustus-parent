package com.carm.vetustus.domain;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import com.carm.vetustus.model.Office;

public class OfficeDTOTest {

	@Test
	public void testCopeProperties() {
		Office office = new Office();
		office.setOfficeCode("8");
		office.setCity("Mexico City");
		office.setPhone("+52 55 5612 3450");
		office.setAddressLine1("156 Casas Grandes");
		office.setAddressLine2("Apt. 12");
		office.setState(null);
		office.setCountry("Mexico");
		office.setPostalCode("03020");
		office.setTerritory("LATAM");
		
		OfficeDTO dto = new OfficeDTO(office);
		
		assertThat(dto, notNullValue());
		
		assertThat(dto.getOfficeCode(), equalTo(office.getOfficeCode()));
		assertThat(dto.getCity(), equalTo(office.getCity()));
		assertThat(dto.getPhone(), equalTo(office.getPhone()));
		assertThat(dto.getAddressLine1(), equalTo(office.getAddressLine1()));
		assertThat(dto.getAddressLine2(), equalTo(office.getAddressLine2()));
		assertThat(dto.getState(), equalTo(office.getState()));
		assertThat(dto.getCountry(), equalTo(office.getCountry()));
		assertThat(dto.getPostalCode(), equalTo(office.getPostalCode()));
		assertThat(dto.getTerritory(), equalTo(office.getTerritory()));
		
		System.out.println(dto);
	}

}
