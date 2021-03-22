package com.appsdeveloperblog.estore.ProductsService.command.interceptors;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.BiFunction;

import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.appsdeveloperblog.estore.ProductsService.command.CreateProductCommand;

@Component
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CreateProductCommandInterceptor.class);

	@Override
	public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(
			List<? extends CommandMessage<?>> messages) {
		 
		return (index, command) -> {
			
			LOGGER.info("Intercepted command: " + command.getPayloadType());
			
			if(CreateProductCommand.class.equals(command.getPayloadType())) {
				
				CreateProductCommand createProductCommand = (CreateProductCommand)command.getPayload();
				
				if(createProductCommand.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
					throw new IllegalArgumentException("Price cannot be less or equal than zero");
				}
				
				if(createProductCommand.getTitle() == null 
						|| createProductCommand.getTitle().isBlank()) {
					throw new IllegalArgumentException("Title cannot be empty");
				}
			}
			
			return command;
		};
	}

}
