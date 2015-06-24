package com.carm.vetustus.persist.dao;

import java.util.List;

import com.carm.vetustus.model.Office;

public interface OfficeDao extends GenericDao<Office, String> {
	
	public List<Office> findByCountry(String country);

}
