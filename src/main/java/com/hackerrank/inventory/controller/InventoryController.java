package com.hackerrank.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.inventory.model.*;
import com.hackerrank.inventory.service.InventoryServiceImpl;

@RestController
public class InventoryController {

	@Autowired
	private InventoryServiceImpl service;

	@RequestMapping(value = "/inventory", method = RequestMethod.GET)
	public ResponseEntity<List<Inventory>> getAllInventorys() {
		List<Inventory> list = service.getAllInventorys();
		
		return new ResponseEntity(list, HttpStatus.OK);

	}

	@RequestMapping(value = "/inventory/{id}", method = RequestMethod.GET)
	public ResponseEntity<Inventory> getInventory(@PathVariable("id") Long id) {
		Inventory c = service.getInventoryById(id);
		if (c == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(c, HttpStatus.OK);

	}

	@RequestMapping(value = "/inventory", method = RequestMethod.POST)
	public ResponseEntity<Inventory> createInventory(@RequestBody Inventory inventory) {

		return new ResponseEntity(service.createInventory(inventory), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/inventory/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Inventory> updateInventory(@PathVariable("id") Long inventoryId,@RequestBody Inventory inventory) {

		Inventory c = service.updateInventory(inventoryId,inventory);
		if (c == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(c, HttpStatus.OK);

	}

	@RequestMapping(value = "/inventory/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteInventory(@PathVariable("id") Long id) {
		Boolean b = service.deleteInventoryById(id);
		if (b) {
			return new ResponseEntity("Inventory Deleted", HttpStatus.OK);
		}

		return new ResponseEntity("Inventory Not Found", HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/inventory", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteAllInventories() {
		service.deleteAllInventories();
		return new ResponseEntity("Inventories Deleted", HttpStatus.OK);
	}

}
