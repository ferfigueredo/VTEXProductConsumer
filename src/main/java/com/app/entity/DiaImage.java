package com.app.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="dia_image")
public class DiaImage extends LibertadImage{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5573302785182017678L;

	@ManyToOne
	protected DiaProduct product;

	public DiaProduct getProduct() {
		return product;
	}

	public void setProduct(DiaProduct producto) {
		this.product = producto;
	}
}
