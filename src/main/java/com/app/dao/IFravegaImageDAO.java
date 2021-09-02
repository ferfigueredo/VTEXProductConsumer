package com.app.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.FravegaImage;

@Repository
public interface IFravegaImageDAO  extends CrudRepository<FravegaImage, Long>, PagingAndSortingRepository<FravegaImage, Long>{
	
	
	@Query("SELECT count(DISTINCT id)  FROM FravegaImage")
	public int getCountOfImages();
	
	@Query("DELETE FROM FravegaImage WHERE id > 0")
	public int borrarImagenes();
}
