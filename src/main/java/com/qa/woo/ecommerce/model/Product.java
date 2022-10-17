package com.qa.woo.ecommerce.model;

import com.qa.woo.ecommerce.enums.DeliveryMode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
	
	private int id;
	private String name;
	private double price;
	private float rating;
	private String category;
	private boolean inStock;
	private float discountPercentage;
	private DeliveryMode deliveryMode;
	private boolean isReturnAccepted;
	private String sellerName;
	
	
	
	

}
