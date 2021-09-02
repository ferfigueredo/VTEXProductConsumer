package com.app.mapper;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL) 
public class ProductMapper
{
    private String[] ProductData;

    private String productId;

    private String[] categoriesIds;
    
    private String[] categories;

    public String[] getCategories() {
		return categories;
	}

	public void setCategories(String[] categories) {
		this.categories = categories;
	}

	private String description;

    private String productName;

    private String[] Configuraciones;

    private String brand;

    private ItemMapper[] items;

    private String categoryId;
    

    

	public String[] getProductData() {
		return ProductData;
	}

	public void setProductData(String[] productData) {
		ProductData = productData;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String[] getCategoriesIds() {
		return categoriesIds;
	}

	public void setCategoriesIds(String[] categoriesIds) {
		this.categoriesIds = categoriesIds;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String[] getConfiguraciones() {
		return Configuraciones;
	}

	public void setConfiguraciones(String[] configuraciones) {
		Configuraciones = configuraciones;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public ItemMapper[] getItems() {
		return items;
	}

	public void setItems(ItemMapper[] items) {
		this.items = items;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

    
}