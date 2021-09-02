package com.app.service;

import java.util.List;

import com.app.entity.JumboImage;
import com.app.entity.JumboProduct;
import com.app.entity.LibertadImage;

public interface IJumboProductService {

	public void saveAll(List<JumboProduct> productos);
	
	public void save(JumboProduct producto);
	
	public JumboProduct  findById(JumboProduct producto);
	
	public JumboProduct findByEAN(String ean);
	
	public void deleteAll();
	
	public void deleteById(long id);
	
	public List<JumboImage> getImagesByEAN(String ean);

	long getCountOfProducts();
	
}
