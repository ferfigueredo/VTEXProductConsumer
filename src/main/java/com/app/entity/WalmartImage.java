package com.app.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="walmart_image")
public class WalmartImage extends LibertadImage{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1645793822902107675L;

	@ManyToOne
	protected WalmartProduct product;

	public WalmartProduct getProduct() {
		return product;
	}

	public void setProduct(WalmartProduct producto) {
		this.product = producto;
	}
}
