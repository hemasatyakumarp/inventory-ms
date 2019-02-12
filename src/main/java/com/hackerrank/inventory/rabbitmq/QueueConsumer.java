package com.hackerrank.inventory.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackerrank.inventory.service.InventoryServiceImpl;
import com.hackerrank.inventory.model.Inventory;
import com.hackerrank.inventory.model.OrderLineItem;

@Component
public class QueueConsumer {
	
	@Autowired
	InventoryServiceImpl inventoryService;
	
	protected Logger logger = LoggerFactory.getLogger(getClass());

	public void receiveMessage(String message) {
		logger.info("Received (String) " + message);
		processMessage(message);
	}

	public void receiveMessage(byte[] message) {
		String strMessage = new String(message);
		logger.info("Received (No String) " + strMessage);
		processMessage(strMessage);
	}

	private void processMessage(String message) {
		try {
			OrderLineItem orderLineItem = new ObjectMapper().readValue(message, OrderLineItem.class);
			Inventory inventory = inventoryService.getInventoryById(orderLineItem.getSkuId());
			inventory.setInventoryOnHand(inventory.getInventoryOnHand()-orderLineItem.getItemQty());
			inventoryService.updateInventory(inventory.getId(), inventory);			
		} catch (JsonParseException e) {
			logger.warn("Bad JSON in message: " + message);
		} catch (JsonMappingException e) {
			logger.warn("cannot map JSON to NotificationRequest: " + message);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}