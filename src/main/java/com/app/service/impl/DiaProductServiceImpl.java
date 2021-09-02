package com.app.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.IDiaProductDAO;
import com.app.entity.DiaImage;
import com.app.entity.DiaProduct;
import com.app.service.IDiaProductService;

@Service
public class DiaProductServiceImpl implements IDiaProductService{

	@PersistenceContext
    private EntityManager em;
	
	@Autowired
	private IDiaProductDAO productDAO;

	@Override
	public void saveAll(List<DiaProduct> productos) {
		// TODO Auto-generated method stub
		productDAO.saveAll(productos);
	}

	@Override
	public void save(DiaProduct producto) {
		// TODO Auto-generated method stub
		productDAO.save(producto);
	}

	@Override
	@Transactional(readOnly = true)
	public DiaProduct findById(DiaProduct producto) {
		// TODO Auto-generated method stub
		return productDAO.findById(producto.getId()).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public DiaProduct findByEAN(String ean) {
		// TODO Auto-generated method stub
		return (DiaProduct) productDAO.findByEan(ean);
	}
	
	public List<DiaImage> getImagesByEAN(String ean){
		DiaProduct prod = (DiaProduct) productDAO.findByEan(ean);
		if(prod != null && !prod.getImages().isEmpty()) {
			return  prod.getImages();			
		}
		return null;		
	}
	
	@Transactional
	public void deleteAll() {
		Query query = em.createNativeQuery("DELETE FROM dia_product WHERE id > 0;");
		query.executeUpdate();
	}

	public void deleteById(long id) {
		productDAO.deleteById(id);
	}
	
}
