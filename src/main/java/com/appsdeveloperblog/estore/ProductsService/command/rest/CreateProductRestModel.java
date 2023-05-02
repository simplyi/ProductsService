package com.appsdeveloperblog.estore.ProductsService.command.rest;

import java.math.BigDecimal;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateProductRestModel {
	
	@NotBlank(message="Product title is a required field")
	private String title;
	
	@Min(value=1, message="Price cannot be lower than 1")
	private BigDecimal price;
	
	@Min(value=1, message="Quantity cannot be lower than 1")
	@Max(value=5, message="Quantity cannot be larger than 5")
	private Integer quantity;

}
