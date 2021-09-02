package com.app.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.DiaImage;

@Repository
public interface IDiaImageDAO  extends CrudRepository<DiaImage, Long>, PagingAndSortingRepository<DiaImage, Long>{
	
	
	@Query("SELECT count(DISTINCT id)  FROM DiaImage")
	public int getCountOfImages();
	
	@Query("DELETE FROM DiaImage WHERE id > 0")
	public int borrarImagenes();
}
