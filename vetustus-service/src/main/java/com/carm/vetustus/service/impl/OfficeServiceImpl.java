package com.carm.vetustus.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carm.vetustus.domain.OfficeDTO;
import com.carm.vetustus.model.Office;
import com.carm.vetustus.persist.dao.OfficeDao;
import com.carm.vetustus.service.OfficeService;

@Service("officeService")
@Transactional
public class OfficeServiceImpl implements OfficeService {
	
	@Autowired
	private OfficeDao officeDao;

	@Override
	public OfficeDTO getOfficeById(String id) {
		
		final Office office = officeDao.find(id);		
		final OfficeDTO dto = new OfficeDTO(office);
		
		return dto;
	}

}
