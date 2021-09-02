package com.app.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="jumbo_image")
public class JumboImage  extends LibertadImage{

	private static final long serialVersionUID = -7162619308988456546L;

	@ManyToOne
	protected JumboProduct product;

	public JumboProduct getProduct() {
		return product;
	}

	public void setProducto(JumboProduct producto) {
		this.product = producto;
	}
	
}
