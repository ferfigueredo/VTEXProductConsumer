package com.app.mapper;


import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL) 
@JsonIgnoreProperties(ignoreUnknown = true)
public class SellerMapper {

	 	private String sellerId;

	    private String sellerName;

	    private String addToCartLink;

	    private String sellerDefault;
	    

	    private CommertialOfferMapper commertialOffer;

	    public CommertialOfferMapper getCommertialOffer() {
			return commertialOffer;
		}

		public void setCommertialOffer(CommertialOfferMapper commertialOffer) {
			this.commertialOffer = commertialOffer;
		}

		public String getSellerId ()
	    {
	        return sellerId;
	    }

	    public void setSellerId (String sellerId)
	    {
	        this.sellerId = sellerId;
	    }

	    public String getSellerName ()
	    {
	        return sellerName;
	    }

	    public void setSellerName (String sellerName)
	    {
	        this.sellerName = sellerName;
	    }

	    public String getAddToCartLink ()
	    {
	        return addToCartLink;
	    }

	    public void setAddToCartLink (String addToCartLink)
	    {
	        this.addToCartLink = addToCartLink;
	    }

	    public String getSellerDefault ()
	    {
	        return sellerDefault;
	    }

	    public void setSellerDefault (String sellerDefault)
	    {
	        this.sellerDefault = sellerDefault;
	    }


}
