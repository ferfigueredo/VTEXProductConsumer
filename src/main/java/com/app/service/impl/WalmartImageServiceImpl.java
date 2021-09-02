package com.app.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.IWalmartImageDAO;
import com.app.entity.WalmartImage;
import com.app.service.IWalmartImageService;

@Transactional
@Service
public class WalmartImageServiceImpl implements IWalmartImageService{


	@PersistenceContext
    private EntityManager em;
	
	@Autowired
	private IWalmartImageDAO imageProductDAO;
	
	@Override
	public void saveAll(List<WalmartImage> images) {
		imageProductDAO.saveAll(images);		
	}

	@Override
	public void save(WalmartImage image) {
		// TODO Auto-generated method stub
		imageProductDAO.save(image);
	}

	@Override
	public Iterable<WalmartImage> imagesPorPagina(int pageNumber, int pageSize){
	      PageRequest pageRequest = PageRequest.of(pageNumber,pageSize, Sort.by("id"));
	      Iterable<WalmartImage> res = imageProductDAO.findAll(pageRequest);
	      return  res;
	}

	public IWalmartImageDAO getImageProductDAO() {
		return imageProductDAO;
	}

	public void setImageProductDAO(IWalmartImageDAO imageProductDAO) {
		this.imageProductDAO = imageProductDAO;
	}

	@Override
	public long getCountOfImages() {
		try {
			return this.imageProductDAO.count();
		}catch (Exception e) {
			return 0;
		}
	}

	@Override
	@Transactional
	public void deleteAll() {
		Query query = em.createNativeQuery("DELETE FROM walmart_image WHERE id > 0;");
		query.executeUpdate();
	}

}
