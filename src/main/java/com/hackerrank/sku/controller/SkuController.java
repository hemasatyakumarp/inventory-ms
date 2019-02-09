package com.hackerrank.sku.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.hackerrank.sku.service.SkuServiceImpl;
import com.hackerrank.sku.model.*;

@RestController
public class SkuController {

	@Autowired
	private SkuServiceImpl service;

	@RequestMapping(value = "/sku", method = RequestMethod.GET)
	public ResponseEntity<List<Sku>> getAllSkus() {
		List<Sku> list = service.getAllSkus();
		if (list == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(list, HttpStatus.OK);

	}

	@RequestMapping(value = "/sku/{id}", method = RequestMethod.GET)
	public ResponseEntity<Sku> getSku(@PathVariable("id") Long id) {
		Sku c = service.getSkuById(id);
		if (c == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(c, HttpStatus.OK);

	}

	@RequestMapping(value = "/sku", method = RequestMethod.POST)
	public ResponseEntity<Sku> createSku(@RequestBody Sku sku) {

		return new ResponseEntity(service.createSku(sku), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/sku/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Sku> updateSku(@PathVariable("id") Long skuId,@RequestBody Sku sku) {

		Sku c = service.updateSku(skuId,sku);
		if (c == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(c, HttpStatus.OK);

	}

	@RequestMapping(value = "/sku/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteSku(@PathVariable("id") Long id) {
		Boolean b = service.deleteSkuById(id);
		if (b) {
			return new ResponseEntity("Sku Deleted", HttpStatus.OK);
		}

		return new ResponseEntity("Sku Not Found", HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/sku", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteAllSku() {
		service.deleteAllSkus();
		return new ResponseEntity("Skus Deleted", HttpStatus.OK);
	}

}
