package com.carm.vetustus.persist.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.carm.vetustus.model.Office;
import com.carm.vetustus.persist.dao.AbstractDao;
import com.carm.vetustus.persist.dao.OfficeDao;

@Repository("officeDao")
@Transactional
public class OfficeDaoImpl extends AbstractDao implements OfficeDao {

	@Override
	public String save(Office type) {
		final String key = (String) getSession().save(type);
		return key;
	}

	@Override
	public Office find(String key) {
		Office office = (Office) getSession().get(Office.class, key);
		return office;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Office> findAll() {
		Criteria criteria = getSession().createCriteria(Office.class);
		final List<Office> offices = (List<Office>) criteria.list();
		return offices;
	}

	@Override
	public void update(Office type) {
		getSession().update(type);
	}

	@Override
	public void delete(Office type) {
		getSession().delete(type);		
	}

	@Override
	public void deleteById(String key) {
		final Office office = (Office) getSession().get(Office.class, key);
		getSession().delete(office);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Office> findByCountry(String country) {
		Query query = getSession().getNamedQuery("office.findByCountry");
		query.setString("country", country);
		
		final List<Office> offices = (List<Office>) query.list();
		
		return offices;
	}

}
