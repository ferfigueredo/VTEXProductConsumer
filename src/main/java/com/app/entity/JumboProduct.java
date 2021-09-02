package com.app.entity;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="jumbo_product")
public class JumboProduct  extends LibertadProduct{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1606602574036894767L;
	
	public JumboProduct() {
		super();
		this.images = new LinkedList<JumboImage>();
	}
	
	/*  Propiedades de Imagen*/
	@OneToMany(mappedBy = "product")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
	private List<JumboImage> images = new LinkedList<JumboImage>();

	public List<JumboImage> getImages() {
		return images;
	}

	public void setImages(List<JumboImage> images) {
		this.images = images;
	}
	
	public void addImage(JumboImage img) {
		this.images.add(img);
	}
	
}
