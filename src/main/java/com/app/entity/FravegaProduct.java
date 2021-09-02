package com.app.entity;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="fravega_product")
public class FravegaProduct extends LibertadProduct{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8062142731591153374L;

	public FravegaProduct() {
		super();
		this.images = new LinkedList<FravegaImage>();
	}
	
	/*  Propiedades de Imagen*/
	@OneToMany(mappedBy = "product")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
	private List<FravegaImage> images = new LinkedList<FravegaImage>();

	public List<FravegaImage> getImages() {
		return images;
	}

	public void setImages(List<FravegaImage> images) {
		this.images = images;
	}
	
	public void addImage(FravegaImage img) {
		this.images.add(img);
	}
	
}

