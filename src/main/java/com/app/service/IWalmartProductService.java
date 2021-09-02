package com.app.service;

import java.util.List;

import com.app.entity.WalmartImage;
import com.app.entity.WalmartProduct;

public interface IWalmartProductService {

	public void saveAll(List<WalmartProduct> productos);
	
	public void save(WalmartProduct producto);
	
	public WalmartProduct  findById(WalmartProduct producto);
	
	public WalmartProduct findByEAN(String ean);
	
	public void deleteAll();
	
	public void deleteById(long id);
	
	public List<WalmartImage> getImagesByEAN(String ean);

	long getCountOfProducts();
	
}
