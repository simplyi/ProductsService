package com.appsdeveloperblog.estore.ProductsService.command;

import java.math.BigDecimal;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateProductCommand {
	
	@TargetAggregateIdentifier
	private final String productId;
	private final String title;
	private final BigDecimal price;
	private final Integer quantity;
	
}
