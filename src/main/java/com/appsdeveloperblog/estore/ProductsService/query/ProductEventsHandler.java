package com.appsdeveloperblog.estore.ProductsService.query;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import com.appsdeveloperblog.estore.ProductsService.core.events.ProductCreatedEvent;

@Component
public class ProductEventsHandler {

	@EventHandler
	public void on(ProductCreatedEvent event) {
		
	}
	
}
