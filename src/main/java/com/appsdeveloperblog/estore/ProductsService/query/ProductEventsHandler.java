package com.appsdeveloperblog.estore.ProductsService.query;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.ResetHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import com.appsdeveloperblog.estore.ProductsService.core.data.ProductEntity;
import com.appsdeveloperblog.estore.ProductsService.core.data.ProductsRepository;
import com.appsdeveloperblog.estore.ProductsService.core.events.ProductCreatedEvent;
import com.appsdeveloperblog.estore.core.events.ProductReservationCancelledEvent;
import com.appsdeveloperblog.estore.core.events.ProductReservedEvent;

@Component
@ProcessingGroup("product-group")
public class ProductEventsHandler {

	private final ProductsRepository productsRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductEventsHandler.class);

	public ProductEventsHandler(ProductsRepository productsRepository) {
		this.productsRepository = productsRepository;
	}
	
	@ExceptionHandler(resultType=Exception.class)
	public void handle(Exception exception) throws Exception {
		throw exception;
	}
	
	@ExceptionHandler(resultType=IllegalArgumentException.class)
	public void handle(IllegalArgumentException exception) {
		// Log error message
	}
	

	@EventHandler
	public void on(ProductCreatedEvent event) {

		ProductEntity productEntity = new ProductEntity();
		BeanUtils.copyProperties(event, productEntity);

		try {
			productsRepository.save(productEntity);
		} catch (IllegalArgumentException ex) {
			ex.printStackTrace();
		}

	}
	
	@EventHandler
	public void on(ProductReservedEvent productReservedEvent) {
		ProductEntity productEntity = productsRepository.findByProductId(productReservedEvent.getProductId());
		
		LOGGER.debug("ProductReservedEvent: Current product quantity " + productEntity.getQuantity());
		
		productEntity.setQuantity(productEntity.getQuantity() - productReservedEvent.getQuantity());
		
		
		productsRepository.save(productEntity);
		
		LOGGER.debug("ProductReservedEvent: New product quantity " + productEntity.getQuantity());
 	
		LOGGER.info("ProductReservedEvent is called for productId:" + productReservedEvent.getProductId() +
				" and orderId: " + productReservedEvent.getOrderId());
	}
	
	@EventHandler
	public void on(ProductReservationCancelledEvent productReservationCancelledEvent) {
		ProductEntity currentlyStoredProduct =  productsRepository.findByProductId(productReservationCancelledEvent.getProductId());
	
		LOGGER.debug("ProductReservationCancelledEvent: Current product quantity " 
		+ currentlyStoredProduct.getQuantity() );
		
		int newQuantity = currentlyStoredProduct.getQuantity() + productReservationCancelledEvent.getQuantity();
		currentlyStoredProduct.setQuantity(newQuantity);
		
		productsRepository.save(currentlyStoredProduct);
		
		LOGGER.debug("ProductReservationCancelledEvent: New product quantity " 
		+ currentlyStoredProduct.getQuantity() );
	
	}
	
	@ResetHandler
	public void reset() {
		productsRepository.deleteAll();
	}

}
