package com.app.service;

import java.util.List;

import com.app.entity.FravegaImage;
import com.app.entity.FravegaProduct;

public interface IFravegaProductService {

	public void saveAll(List<FravegaProduct> productos);
	
	public void save(FravegaProduct producto);
	
	public FravegaProduct  findById(FravegaProduct producto);
	
	public FravegaProduct findByEAN(String ean);
	
	public void deleteAll();
	
	public void deleteById(long id);
	
	public List<FravegaImage> getImagesByEAN(String ean);

	long getCountOfProducts();
	
}
