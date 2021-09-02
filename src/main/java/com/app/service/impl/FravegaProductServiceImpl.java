package com.app.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.IFravegaProductDAO;
import com.app.entity.FravegaImage;
import com.app.entity.FravegaProduct;
import com.app.service.IFravegaProductService;

@Service
public class FravegaProductServiceImpl implements IFravegaProductService{


	@PersistenceContext
    private EntityManager em;
	
	@Autowired
	private IFravegaProductDAO productDAO;

	@Override
	public void saveAll(List<FravegaProduct> productos) {
		// TODO Auto-generated method stub
		productDAO.saveAll(productos);
	}

	@Override
	public void save(FravegaProduct producto) {
		// TODO Auto-generated method stub
		productDAO.save(producto);
	}

	@Override
	@Transactional(readOnly = true)
	public FravegaProduct findById(FravegaProduct producto) {
		// TODO Auto-generated method stub
		return productDAO.findById(producto.getId()).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public FravegaProduct findByEAN(String ean) {
		// TODO Auto-generated method stub
		return (FravegaProduct) productDAO.findByEan(ean);
	}
	
	public List<FravegaImage> getImagesByEAN(String ean){
		FravegaProduct prod = (FravegaProduct) productDAO.findByEan(ean);
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
		Query query = em.createNativeQuery("DELETE FROM fravega_product WHERE id > 0;");
		query.executeUpdate();
	}

	public void deleteById(long id) {
		productDAO.deleteById(id);
	}
	
}
