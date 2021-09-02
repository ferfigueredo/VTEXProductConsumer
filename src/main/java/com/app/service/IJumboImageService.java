package com.app.service;

import java.util.List;

import com.app.entity.JumboImage;

public interface IJumboImageService {

	public void saveAll(List<JumboImage> images);
	
	public void save(JumboImage image);
	
	public Iterable<JumboImage> imagesPorPagina(int pageNumber, int pageSize);
	  
	public long getCountOfImages();
	
	public void deleteAll();
}
