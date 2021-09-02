package com.app.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.FravegaProduct;
import com.app.entity.JumboProduct;

@Repository
public interface IFravegaProductDAO extends CrudRepository<FravegaProduct, Long>{

	
	public FravegaProduct findByEan(String ean);
	
	
	@Query("DELETE FROM FravegaProduct WHERE id > 0")
	public int borrarProductosEImagenes();
	
}
