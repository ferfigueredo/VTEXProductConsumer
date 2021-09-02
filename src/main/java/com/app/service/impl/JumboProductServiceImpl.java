package com.app.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.IJumboProductDAO;
import com.app.entity.JumboImage;
import com.app.entity.JumboProduct;
import com.app.service.IJumboProductService;

@Service
public class JumboProductServiceImpl implements IJumboProductService{


	@PersistenceContext
    private EntityManager em;
	
	@Autowired
	private IJumboProductDAO productDAO;

	@Override
	public void saveAll(List<JumboProduct> productos) {
		// TODO Auto-generated method stub
		productDAO.saveAll(productos);
	}

	@Override
	public void save(JumboProduct producto) {
		// TODO Auto-generated method stub
		productDAO.save(producto);
	}

	@Override
	@Transactional(readOnly = true)
	public JumboProduct findById(JumboProduct producto) {
		// TODO Auto-generated method stub
		return productDAO.findById(producto.getId()).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public JumboProduct findByEAN(String ean) {
		// TODO Auto-generated method stub
		return (JumboProduct) productDAO.findByEan(ean);
	}
	
	public List<JumboImage> getImagesByEAN(String ean){
		JumboProduct prod = (JumboProduct) productDAO.findByEan(ean);
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
		Query query = em.createNativeQuery("DELETE FROM jumbo_product WHERE id > 0;");
		query.executeUpdate();
	}

	public void deleteById(long id) {
		productDAO.deleteById(id);
	}
	
}
