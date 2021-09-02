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

import com.app.dao.IDiaImageDAO;
import com.app.entity.DiaImage;
import com.app.service.IDiaImageService;

@Transactional
@Service
public class DiaImageServiceImpl implements IDiaImageService{

	@PersistenceContext
    private EntityManager em;
	
	@Autowired
	private IDiaImageDAO imageProductDAO;
	
	@Override
	public void saveAll(List<DiaImage> images) {
		imageProductDAO.saveAll(images);		
	}

	@Override
	public void save(DiaImage image) {
		// TODO Auto-generated method stub
		imageProductDAO.save(image);
	}

	@Override
	public Iterable<DiaImage> imagesPorPagina(int pageNumber, int pageSize){
	      PageRequest pageRequest = PageRequest.of(pageNumber,pageSize, Sort.by("id"));
	      Iterable<DiaImage> res = imageProductDAO.findAll(pageRequest);
	      return  res;
	}

	public IDiaImageDAO getImageProductDAO() {
		return imageProductDAO;
	}

	public void setImageProductDAO(IDiaImageDAO imageProductDAO) {
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
		Query query = em.createNativeQuery("DELETE FROM dia_image WHERE id > 0;");
		query.executeUpdate();
	}

}
