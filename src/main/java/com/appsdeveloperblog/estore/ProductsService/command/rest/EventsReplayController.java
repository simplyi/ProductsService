package com.appsdeveloperblog.estore.ProductsService.command.rest;

import org.axonframework.config.EventProcessingConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/management")
public class EventsReplayController {
	
	@Autowired
	private EventProcessingConfiguration eventProcessingConfiguration;

	@PostMapping("/eventProcessor/{processorName}/reset")
	public void replayEvents(@PathVariable String processorName) {
		
	}
	
}
