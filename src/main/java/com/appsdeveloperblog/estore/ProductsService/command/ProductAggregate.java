package com.appsdeveloperblog.estore.ProductsService.command;

import java.math.BigDecimal;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class ProductAggregate {
	
	public ProductAggregate() {
		
	}
	
	@CommandHandler
	public ProductAggregate(CreateProductCommand createProductCommand) {
		// Validate Create Product Command
		
		if(createProductCommand.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("Price cannot be less or equal than zero");
		}
		
		if(createProductCommand.getTitle() == null 
				|| createProductCommand.getTitle().isBlank()) {
			throw new IllegalArgumentException("Title cannot be empty");
		}
	}

}
