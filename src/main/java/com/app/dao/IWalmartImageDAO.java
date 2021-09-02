package com.app.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.WalmartImage;

@Repository
public interface IWalmartImageDAO  extends CrudRepository<WalmartImage, Long>, PagingAndSortingRepository<WalmartImage, Long>{
	
	
	@Query("SELECT count(DISTINCT id)  FROM WalmartImage")
	public int getCountOfImages();
	
	@Query("DELETE FROM WalmartImage WHERE id > 0")
	public int borrarImagenes();
}
