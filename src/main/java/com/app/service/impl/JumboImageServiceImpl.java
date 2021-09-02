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

import com.app.dao.IJumboImageDAO;
import com.app.entity.JumboImage;
import com.app.service.IJumboImageService;

@Transactional
@Service
public class JumboImageServiceImpl implements IJumboImageService{


	@PersistenceContext
    private EntityManager em;
	
	@Autowired
	private IJumboImageDAO imageProductDAO;
	
	@Override
	public void saveAll(List<JumboImage> images) {
		imageProductDAO.saveAll(images);		
	}

	@Override
	public void save(JumboImage image) {
		// TODO Auto-generated method stub
		imageProductDAO.save(image);
	}

	@Override
	public Iterable<JumboImage> imagesPorPagina(int pageNumber, int pageSize){
	      PageRequest pageRequest = PageRequest.of(pageNumber,pageSize, Sort.by("id"));
	      Iterable<JumboImage> res = imageProductDAO.findAll(pageRequest);
	      return  res;
	}

	public IJumboImageDAO getImageProductDAO() {
		return imageProductDAO;
	}

	public void setImageProductDAO(IJumboImageDAO imageProductDAO) {
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
		Query query = em.createNativeQuery("DELETE FROM jumbo_image WHERE id > 0;");
		query.executeUpdate();
	}

}
