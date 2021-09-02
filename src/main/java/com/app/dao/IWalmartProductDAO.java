package com.app.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.WalmartProduct;

@Repository
public interface IWalmartProductDAO extends CrudRepository<WalmartProduct, Long>{

	
	public WalmartProduct findByEan(String ean);
	
	
	@Query("DELETE FROM WalmartProduct WHERE id > 0")
	public int borrarProductosEImagenes();
	
}
