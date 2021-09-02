package com.app.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.IWalmartProductDAO;
import com.app.entity.WalmartImage;
import com.app.entity.WalmartProduct;
import com.app.service.IWalmartProductService;

@Service
public class WalmartProductServiceImpl implements IWalmartProductService{


	@PersistenceContext
    private EntityManager em;
	
	@Autowired
	private IWalmartProductDAO productDAO;

	@Override
	public void saveAll(List<WalmartProduct> productos) {
		// TODO Auto-generated method stub
		productDAO.saveAll(productos);
	}

	@Override
	public void save(WalmartProduct producto) {
		// TODO Auto-generated method stub
		productDAO.save(producto);
	}

	@Override
	@Transactional(readOnly = true)
	public WalmartProduct findById(WalmartProduct producto) {
		// TODO Auto-generated method stub
		return productDAO.findById(producto.getId()).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public WalmartProduct findByEAN(String ean) {
		// TODO Auto-generated method stub
		return (WalmartProduct) productDAO.findByEan(ean);
	}
	
	public List<WalmartImage> getImagesByEAN(String ean){
		WalmartProduct prod = (WalmartProduct) productDAO.findByEan(ean);
		if(prod != null && !prod.getImages().isEmpty()) {
			return  prod.getImages();			
		}
		return null;		
	}
	
	@Override
	public long getCountOfProducts() {
		try {
			return this.productDAO.count();
		}catch (Exception e) {
			return 0;
		}
	}

	
	@Override
	@Transactional
	public void deleteAll() {
		Query query = em.createNativeQuery("DELETE FROM walmart_producto WHERE id > 0;");
		query.executeUpdate();
	}
	public void deleteById(long id) {
		productDAO.deleteById(id);
	}
	
}
