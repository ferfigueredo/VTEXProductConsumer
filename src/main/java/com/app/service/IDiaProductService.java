package com.app.service;

import java.util.List;

import com.app.entity.DiaImage;
import com.app.entity.DiaProduct;

public interface IDiaProductService {

	public void saveAll(List<DiaProduct> productos);
	
	public void save(DiaProduct producto);
	
	public DiaProduct  findById(DiaProduct producto);
	
	public DiaProduct findByEAN(String ean);
	
	public void deleteAll();
	
	public void deleteById(long id);
	
	public List<DiaImage> getImagesByEAN(String ean);

	
}
