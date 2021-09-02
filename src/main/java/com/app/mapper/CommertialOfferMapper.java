package com.app.mapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL) 
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommertialOfferMapper {

		@JsonProperty("ListPrice")
	    private String listPrice;
		@JsonProperty("Price")
	    private String price;
		@JsonProperty("PriceWithoutDiscount")
	    private String priceWithoutDiscount;

		public String getListPrice() {
			return listPrice;
		}

		public void setListPrice(String listPrice) {
			this.listPrice = listPrice;
		}

		public String getPrice() {
			return price;
		}

		public void setPrice(String price) {
			this.price = price;
		}

		public String getPriceWithoutDiscount() {
			return priceWithoutDiscount;
		}

		public void setPriceWithoutDiscount(String priceWithoutDiscount) {
			this.priceWithoutDiscount = priceWithoutDiscount;
		}


}
