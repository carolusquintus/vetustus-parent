package com.carm.vetustus.persist.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, K extends Serializable> {
	
	K save(T type);
	
	T find(K key);
	
	List<T> findAll();
	
	void update(T type);
	
	void delete(T type);
	
	void deleteById(K key);

}
