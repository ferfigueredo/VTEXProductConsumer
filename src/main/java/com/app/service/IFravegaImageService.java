package com.app.service;

import java.util.List;

import com.app.entity.FravegaImage;

public interface IFravegaImageService {

	public void saveAll(List<FravegaImage> images);
	
	public void save(FravegaImage image);
	
	public Iterable<FravegaImage> imagesPorPagina(int pageNumber, int pageSize);
	  
	public long getCountOfImages();
	
	public void deleteAll();
}
