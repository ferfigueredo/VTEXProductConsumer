package com.app.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.JumboProduct;

@Repository
public interface IJumboProductDAO extends CrudRepository<JumboProduct, Long>{

	
	public JumboProduct findByEan(String ean);
	
	
	@Query("DELETE FROM JumboProduct WHERE id > 0")
	public int borrarProductosEImagenes();
	
}
