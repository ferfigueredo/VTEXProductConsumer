package com.app.entity.builder;

import com.app.entity.DiaImage;
import com.app.entity.DiaProduct;
import com.app.entity.FravegaImage;
import com.app.entity.FravegaProduct;
import com.app.entity.JumboImage;
import com.app.entity.JumboProduct;
import com.app.entity.WalmartImage;
import com.app.entity.WalmartProduct;
import com.app.mapper.CommertialOfferMapper;
import com.app.mapper.ImgMapper;
import com.app.mapper.ItemMapper;
import com.app.mapper.ProductMapper;
import com.app.mapper.SellerMapper;
import com.app.proxy.ProductProxy;

public class ProductBuilder {

	
	public static JumboProduct generateJumboProduct(ProductMapper vtexProd ) {
		
		JumboProduct prodj = new JumboProduct();
		
		prodj.setBrand(vtexProd.getBrand());
		prodj.setProductName(getFirst3000Chars(vtexProd.getDescription()));
		prodj.setProductTitle(getFirst3000Chars(vtexProd.getDescription()));
		//prodj.setDescription(vtexProd.getDescription());
		prodj.setBrand(getFirst256Chars(vtexProd.getBrand()));		
		
		prodj.setCategoryId(getFirst256Chars(vtexProd.getCategoriesIds()[0]));
		prodj.setCategory(getFirst256Chars(vtexProd.getCategories()[0]));
		
		if(vtexProd.getItems().length>0) {	
			ItemMapper item =  vtexProd.getItems()[0];
			prodj.setMeasurementUnit(getFirst256Chars(item.getMeasurementUnit()));
			prodj.setEan(getFirst256Chars(item.getEan()));
			
			int counter = 0;
			for (ImgMapper imgItem : item.getImages()) {
				
				JumboImage img = new JumboImage();
				
				img.setProducto(prodj);
				img.setImageLabel(getFirst3000Chars(prodj.getProductName()));
				img.setImageText(getFirst3000Chars(imgItem.getImageText()));
				img.setImageRemoteUrl(getFirst3000Chars(imgItem.getImageUrl()));
				img.setProductEan(getFirst256Chars(item.getEan()));
				img.setImageName(prodj.getEan()+"_"+counter);
				
				ProductProxy.loadPicture(img);
				
				prodj.addImage(img);
				counter++;
			
			
				if(item.getSellers().length>0) {
					SellerMapper seller = item.getSellers()[0];
					CommertialOfferMapper offer = seller.getCommertialOffer();
					if (null != offer) {
						prodj.setPrice(offer.getPrice().toString());
						prodj.setListPrice(offer.getListPrice().toString());
						prodj.setPriceWithoutDiscount(offer.getPriceWithoutDiscount().toString());
					}
					
				}
			}
		}
		
		return prodj;
	}
	
public static DiaProduct generateDiaProduct(ProductMapper vtexProd ) {
		
		DiaProduct prodj = new DiaProduct();
		
		prodj.setBrand(vtexProd.getBrand());
		prodj.setProductName(getFirst3000Chars(vtexProd.getDescription()));
		prodj.setProductTitle(getFirst3000Chars(vtexProd.getDescription()));
		//prodj.setDescription(vtexProd.getDescription());
		prodj.setBrand(getFirst256Chars(vtexProd.getBrand()));		
		
		prodj.setCategoryId(getFirst256Chars(vtexProd.getCategoriesIds()[0]));
		prodj.setCategory(getFirst256Chars(vtexProd.getCategories()[0]));
		
		if(vtexProd.getItems().length>0) {	
			ItemMapper item =  vtexProd.getItems()[0];
			prodj.setMeasurementUnit(getFirst256Chars(item.getMeasurementUnit()));
			prodj.setEan(getFirst256Chars(item.getEan()));
			
			int counter = 0;
			for (ImgMapper imgItem : item.getImages()) {
				
				DiaImage img = new DiaImage();
				
				img.setProduct(prodj);
				img.setImageLabel(getFirst3000Chars(prodj.getProductName()));
				img.setImageText(getFirst3000Chars(imgItem.getImageText()));
				img.setImageRemoteUrl(getFirst3000Chars(imgItem.getImageUrl()));
				img.setProductEan(getFirst256Chars(item.getEan()));
				img.setImageName(prodj.getEan()+"_"+counter);
				
				ProductProxy.loadPicture(img);
				
				prodj.addImage(img);
				counter++;
			
			
				if(item.getSellers().length>0) {
					SellerMapper seller = item.getSellers()[0];
					CommertialOfferMapper offer = seller.getCommertialOffer();
					if (null != offer) {
						prodj.setPrice(offer.getPrice().toString());
						prodj.setListPrice(offer.getListPrice().toString());
						prodj.setPriceWithoutDiscount(offer.getPriceWithoutDiscount().toString());
					}
					
				}
			}
		}
		
		return prodj;
	}
	
	private static String getFirst3000Chars(String value) {
		if(value.length() < 3000) {
			return value;
		}else {
			return value.substring(0, 2999);
		}
	}
	
	private static String getFirst256Chars(String value) {
		if(value.length() < 3000) {
			return value;
		}else {
			return value.substring(0, 2999);
		}
	}

	public static WalmartProduct generateWalmartProduct(ProductMapper vtexProd) {
		WalmartProduct prodj = new WalmartProduct();
		
		prodj.setBrand(vtexProd.getBrand());
		prodj.setProductName(getFirst3000Chars(vtexProd.getDescription()));
		prodj.setProductTitle(getFirst3000Chars(vtexProd.getDescription()));
		//prodj.setDescription(vtexProd.getDescription());
		prodj.setBrand(getFirst256Chars(vtexProd.getBrand()));		
		
		prodj.setCategoryId(getFirst256Chars(vtexProd.getCategoriesIds()[0]));
		prodj.setCategory(getFirst256Chars(vtexProd.getCategories()[0]));
		
		if(vtexProd.getItems().length>0) {	
			ItemMapper item =  vtexProd.getItems()[0];
			prodj.setMeasurementUnit(getFirst256Chars(item.getMeasurementUnit()));
			prodj.setEan(getFirst256Chars(item.getEan()));
			
			int counter = 0;
			for (ImgMapper imgItem : item.getImages()) {
				
				WalmartImage img = new WalmartImage();
				
				img.setProduct(prodj);
				img.setImageLabel(getFirst3000Chars(prodj.getProductName()));
				img.setImageText(getFirst3000Chars(imgItem.getImageText()));
				img.setImageRemoteUrl(getFirst3000Chars(imgItem.getImageUrl()));
				img.setProductEan(getFirst256Chars(item.getEan()));
				img.setImageName(prodj.getEan()+"_"+counter);
				
				ProductProxy.loadPicture(img);
				
				prodj.addImage(img);
				counter++;
			
			
				if(item.getSellers().length>0) {
					SellerMapper seller = item.getSellers()[0];
					CommertialOfferMapper offer = seller.getCommertialOffer();
					if (null != offer) {
						prodj.setPrice(offer.getPrice().toString());
						prodj.setListPrice(offer.getListPrice().toString());
						prodj.setPriceWithoutDiscount(offer.getPriceWithoutDiscount().toString());
					}
					
				}
			}
		}
		
		return prodj;
	}
	public static FravegaProduct generateFravegaProduct(ProductMapper vtexProd) {
		FravegaProduct prodj = new FravegaProduct();
		
		prodj.setBrand(vtexProd.getBrand());
		prodj.setProductName(getFirst3000Chars(vtexProd.getDescription()));
		prodj.setProductTitle(getFirst3000Chars(vtexProd.getDescription()));
		//prodj.setDescription(vtexProd.getDescription());
		prodj.setBrand(getFirst256Chars(vtexProd.getBrand()));		
		
		prodj.setCategoryId(getFirst256Chars(vtexProd.getCategoriesIds()[0]));
		prodj.setCategory(getFirst256Chars(vtexProd.getCategories()[0]));
		
		if(vtexProd.getItems().length>0) {	
			ItemMapper item =  vtexProd.getItems()[0];
			prodj.setMeasurementUnit(getFirst256Chars(item.getMeasurementUnit()));
			prodj.setEan(getFirst256Chars(item.getEan()));
			
			int counter = 0;
			for (ImgMapper imgItem : item.getImages()) {
				
				FravegaImage img = new FravegaImage();
				
				img.setProduct(prodj);
				img.setImageLabel(getFirst3000Chars(prodj.getProductName()));
				img.setImageText(getFirst3000Chars(imgItem.getImageText()));
				img.setImageRemoteUrl(getFirst3000Chars(imgItem.getImageUrl()));
				img.setProductEan(getFirst256Chars(item.getEan()));
				img.setImageName(prodj.getEan()+"_"+counter);
				
				ProductProxy.loadPicture(img);
				
				prodj.addImage(img);
				counter++;
			
			
				if(item.getSellers().length>0) {
					SellerMapper seller = item.getSellers()[0];
					CommertialOfferMapper offer = seller.getCommertialOffer();
					if (null != offer) {
						prodj.setPrice(offer.getPrice().toString());
						prodj.setListPrice(offer.getListPrice().toString());
						prodj.setPriceWithoutDiscount(offer.getPriceWithoutDiscount().toString());
					}
					
				}
			}
		}
		
		return prodj;
	}
}
