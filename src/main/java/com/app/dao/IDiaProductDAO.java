package com.app.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.DiaProduct;

@Repository
public interface IDiaProductDAO extends CrudRepository<DiaProduct, Long>{

	
	public DiaProduct findByEan(String ean);
	
	
	@Query("DELETE FROM DiaProduct WHERE id > 0")
	public int borrarProductosEImagenes();
	
}
