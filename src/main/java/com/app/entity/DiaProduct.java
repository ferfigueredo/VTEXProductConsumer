package com.app.entity;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="dia_product")
public class DiaProduct extends LibertadProduct{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5780493776826730925L;

	public DiaProduct() {
		super();
		this.images = new LinkedList<DiaImage>();
	}
	
	/*  Propiedades de Imagen*/
	@OneToMany(mappedBy = "product")
    @Cascade({org.hibernate.annotations.CascadeType.ALL} )
	private List<DiaImage> images = new LinkedList<DiaImage>();

	public List<DiaImage> getImages() {
		return images;
	}

	public void setImages(List<DiaImage> images) {
		this.images = images;
	}
	
	public void addImage(DiaImage img) {
		this.images.add(img);
	}
	
}

