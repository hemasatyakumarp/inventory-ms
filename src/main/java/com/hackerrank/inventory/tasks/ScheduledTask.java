package com.hackerrank.inventory.tasks;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hackerrank.inventory.model.Inventory;
import com.hackerrank.inventory.service.InventoryService;

@Component
public class ScheduledTask {
	
	private static final Logger log = LoggerFactory.getLogger(ScheduledTask.class);

	@Autowired
	InventoryService inventoryService;

	@Scheduled(fixedDelay = 5000)
	public void checkInventory() {
		
		log.info("== Scheduele Task Started == Checking Inventory....\n");

		List<Inventory> inventoryList = inventoryService.findInadequateInventory();

		if (!inventoryList.isEmpty()) {
									
			updateInventory(inventoryList);						
		    inventoryList =null;
		
		  log.info("== Sheduled Task Completed == Inventory is Adequate now....\n");
		    
		} else {		
		  log.info("== Sheduled Task Completed == Inventory is Adequate....\n");
		}
	}

	public void updateInventory(List<Inventory> inventoryList) {

		for (Inventory inventory : inventoryList) {
			
			log.info("== Inventory is not Adequate for product "+inventory.getProductName());
			int inventoryToBeAdded = inventory.getMinQtyReq() - inventory.getInventoryOnHand();
			inventory.setInventoryOnHand(inventory.getInventoryOnHand()+inventoryToBeAdded);
			inventoryService.updateInventory(inventory.getId(), inventory);
			log.info("== Added more quantity of "+inventory.getProductName() +" to the Inventory");

		}

	}

}
