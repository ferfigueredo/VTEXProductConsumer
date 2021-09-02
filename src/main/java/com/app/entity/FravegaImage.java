package com.app.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="fravega_image")
public class FravegaImage extends LibertadImage{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7698855090685741311L;


	@ManyToOne
	protected FravegaProduct product;

	public FravegaProduct getProduct() {
		return product;
	}

	public void setProduct(FravegaProduct producto) {
		this.product = producto;
	}
}
