package com.app.service;

import java.util.List;

import com.app.entity.WalmartImage;

public interface IWalmartImageService {

	public void saveAll(List<WalmartImage> images);
	
	public void save(WalmartImage image);
	
	public Iterable<WalmartImage> imagesPorPagina(int pageNumber, int pageSize);
	  
	public long getCountOfImages();
	
	public void deleteAll();
}
