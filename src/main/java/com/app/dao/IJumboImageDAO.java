package com.app.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.JumboImage;

@Repository
public interface IJumboImageDAO  extends CrudRepository<JumboImage, Long>, PagingAndSortingRepository<JumboImage, Long>{
	
	
	@Query("SELECT count(DISTINCT id)  FROM JumboImage")
	public int getCountOfImages();
	
	@Query("DELETE FROM JumboImage WHERE id > 0")
	public int borrarImagenes();
}
