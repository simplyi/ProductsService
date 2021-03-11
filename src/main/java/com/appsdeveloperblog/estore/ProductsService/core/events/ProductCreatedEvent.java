package com.appsdeveloperblog.estore.ProductsService.core.events;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductCreatedEvent {

	private String productId;
	private String title;
	private BigDecimal price;
	private Integer quantity;
	
}
