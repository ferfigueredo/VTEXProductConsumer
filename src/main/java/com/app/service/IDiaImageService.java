package com.app.service;

import java.util.List;

import com.app.entity.DiaImage;

public interface IDiaImageService {

	public void saveAll(List<DiaImage> images);
	
	public void save(DiaImage image);
	
	public Iterable<DiaImage> imagesPorPagina(int pageNumber, int pageSize);
	  
	public long getCountOfImages();
	
	public void deleteAll();
}
