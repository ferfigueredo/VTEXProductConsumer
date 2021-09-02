package com.app.entity;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="walmart_product")
public class WalmartProduct extends LibertadProduct{

	/**
	 * 
	 */
	private static final long serialVersionUID = -906628249029067579L;

	public WalmartProduct() {
		super();
		this.images = new LinkedList<WalmartImage>();
	}
	
	/*  Propiedades de Imagen*/
	@OneToMany(mappedBy = "product")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
	private List<WalmartImage> images = new LinkedList<WalmartImage>();

	public List<WalmartImage> getImages() {
		return images;
	}

	public void setImages(List<WalmartImage> images) {
		this.images = images;
	}
	
	public void addImage(WalmartImage img) {
		this.images.add(img);
	}
	
}
