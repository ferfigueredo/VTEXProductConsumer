package com.app.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;

@MappedSuperclass
public class LibertadProduct implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8033582988226824942L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	/* Propiedades de Producto*/ 

	@Column(length=255)
    protected String category;
	
	@Column(length=255)
    protected String categoryId;

	@Column(length=3000)
    protected String description;

	@Column(length=3000)
    protected String productName;
	
	@Column(length=3000)
    protected String productTitle;

	@Column(length=255)
    protected String brand;

	@Column(length=255)
	protected String measurementUnit;
	
	@Column(length=255)
	protected String ean;
	
	@Column(length=255)
	protected String price;
	
	@Column(length=255)
	protected String listPrice;
	
	@Column(length=255)
	protected String priceWithoutDiscount;
	
	
	public String getPriceWithoutDiscount() {
		return priceWithoutDiscount;
	}

	public void setPriceWithoutDiscount(String priceWithoutDiscount) {
		this.priceWithoutDiscount = priceWithoutDiscount;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if(description.length()>2000) {
			this.description = description.substring(0, 1999);
		}else {
			this.description = description;
		}
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getMeasurementUnit() {
		return measurementUnit;
	}

	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
	}

	public String getEan() {
		return ean;
	}

	public void setEan(String ean) {
		this.ean = ean;
	}


	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getListPrice() {
		return listPrice;
	}

	public void setListPrice(String listPrice) {
		this.listPrice = listPrice;
	}
	

}
