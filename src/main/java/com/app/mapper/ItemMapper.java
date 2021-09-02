package com.app.mapper;

public class ItemMapper {

	private ImgMapper[] images;
	
	private String unitMultiplier;
	
	private String nameComplete;
	
	private String measurementUnit;
	
	private String itemId;
	
	private String isKit;
	
	private String ean;
	
	private String modalType;
	
	private String name;

    private SellerMapper[] sellers;
    
	public SellerMapper[] getSellers() {
		return sellers;
	}

	public void setSellers(SellerMapper[] sellers) {
		this.sellers = sellers;
	}

	public ImgMapper[] getImages() {
		return images;
	}
	
	
	
	public void setImages(ImgMapper[] images) {
		this.images = images;
	}
	
	
	
	public String getUnitMultiplier() {
		return unitMultiplier;
	}
	
	
	
	public void setUnitMultiplier(String unitMultiplier) {
		this.unitMultiplier = unitMultiplier;
	}
	
	
	
	public String getNameComplete() {
		return nameComplete;
	}
	
	
	
	public void setNameComplete(String nameComplete) {
		this.nameComplete = nameComplete;
	}
	
	
	
	public String getMeasurementUnit() {
		return measurementUnit;
	}
	
	
	
	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
	}
	
	
	
	public String getItemId() {
		return itemId;
	}
	
	
	
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	
	
	
	public String getIsKit() {
		return isKit;
	}
	
	
	
	public void setIsKit(String isKit) {
		this.isKit = isKit;
	}
	
	
	
	public String getEan() {
		return ean;
	}
	
	
	
	public void setEan(String ean) {
		this.ean = ean;
	}
	
	
	
	public String getModalType() {
		return modalType;
	}
	
	
	
	public void setModalType(String modalType) {
		this.modalType = modalType;
	}
	
	
	
	public String getName() {
		return name;
	}
	
	
	
	public void setName(String name) {
		this.name = name;
	}
	

}
		