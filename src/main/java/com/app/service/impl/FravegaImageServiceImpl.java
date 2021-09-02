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

import com.app.dao.IFravegaImageDAO;
import com.app.entity.FravegaImage;
import com.app.service.IFravegaImageService;

@Transactional
@Service
public class FravegaImageServiceImpl implements IFravegaImageService{


	@PersistenceContext
    private EntityManager em;
	
	@Autowired
	private IFravegaImageDAO imageProductDAO;
	
	@Override
	public void saveAll(List<FravegaImage> images) {
		imageProductDAO.saveAll(images);		
	}

	@Override
	public void save(FravegaImage image) {
		// TODO Auto-generated method stub
		imageProductDAO.save(image);
	}

	@Override
	public Iterable<FravegaImage> imagesPorPagina(int pageNumber, int pageSize){
	      PageRequest pageRequest = PageRequest.of(pageNumber,pageSize, Sort.by("id"));
	      Iterable<FravegaImage> res = imageProductDAO.findAll(pageRequest);
	      return  res;
	}

	public IFravegaImageDAO getImageProductDAO() {
		return imageProductDAO;
	}

	public void setImageProductDAO(IFravegaImageDAO imageProductDAO) {
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
		Query query = em.createNativeQuery("DELETE FROM fravega_image WHERE id > 0;");
		query.executeUpdate();
	}

}
