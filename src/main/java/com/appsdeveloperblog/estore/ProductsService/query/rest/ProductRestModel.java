package com.appsdeveloperblog.estore.ProductsService.query.rest;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductRestModel {
	private String productId;
	private String title;
	private BigDecimal price;
	private Integer quantity;
}
