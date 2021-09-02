package com.app.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class LibertadImage implements Serializable{

	/**
	 * 
	 */
	protected static final long serialVersionUID = -3599116186416262543L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@Column(length=255)
	protected String imageName;
	
	@Column(length=255)
    protected String productEan;
	
	@Column(length=3000)
	protected String imageText;
	
	@Column(length=3000)
    protected String imageLabel;
	
	@Column(length=3000)
    protected String imageRemoteUrl;
	
	@Column(length=3000)
    protected String imageLocalUrl;

	
	/** Aca se guarda la imagen en bytes **/
	@Lob
    @Basic(fetch = FetchType.LAZY)
    protected byte[] productImage;
	
	
	
	public byte[] getProductImage() {
		return productImage;
	}

	public void setProductImage(byte[] productImage) {
		this.productImage = productImage;
	}

	public String getImageText() {
		return imageText;
	}

	public void setImageText(String imageText) {
		this.imageText = imageText;
	}

	public String getImageLabel() {
		return imageLabel;
	}

	public void setImageLabel(String imageLabel) {
		this.imageLabel = imageLabel;
	}

	public String getImageRemoteUrl() {
		return imageRemoteUrl;
	}

	public void setImageRemoteUrl(String imageRemoteUrl) {
		this.imageRemoteUrl = imageRemoteUrl;
	}

	public String getImageLocalUrl() {
		return imageLocalUrl;
	}

	public void setImageLocalUrl(String imageLocalUrl) {
		this.imageLocalUrl = imageLocalUrl;
	}


	public String getProductEan() {
		return productEan;
	}

	public void setProductEan(String productEan) {
		this.productEan = productEan;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
}
