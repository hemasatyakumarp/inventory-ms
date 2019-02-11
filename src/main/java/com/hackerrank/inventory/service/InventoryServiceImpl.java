package com.hackerrank.inventory.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackerrank.inventory.exception.BadResourceRequestException;
import com.hackerrank.inventory.exception.NoSuchResourceFoundException;
import com.hackerrank.inventory.model.Inventory;
import com.hackerrank.inventory.repository.InventoryRepository;

@Service("InventoryService")
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    
    public void deleteAllInventories() {
    	inventoryRepository.deleteAllInBatch();
    }

   
    public Boolean deleteInventoryById(Long id) {
    	 Inventory Inventory = inventoryRepository.findOne(id);

         if (Inventory == null) {
             throw null;
         }
         
         inventoryRepository.deleteById(id);
        return true;
    }

    @Override
    public Inventory createInventory(Inventory sku) {
        Inventory existingInventory = inventoryRepository.findOne(sku.getId());

        if (existingInventory != null) {
            throw new BadResourceRequestException("Inventory with same id exists.");
        }

        inventoryRepository.save(sku);
        return existingInventory;
    }
    
    @Override
    public Inventory updateInventory(Long skuId,Inventory sku) {
        
        Inventory tempInventory = inventoryRepository.findOne(skuId);

        if (tempInventory == null) {
            return null;
        }
       
        inventoryRepository.save(sku);
        return inventoryRepository.findOne(skuId);
        
    }

    @Override
    public Inventory getInventoryById(Long id) {
        Inventory Inventory = inventoryRepository.findOne(id);

        if (Inventory == null) {
            throw new NoSuchResourceFoundException("No Inventory with given id found.");
        }

        return Inventory;
    }

    @Override
    public List<Inventory> getAllInventorys() {
    	
        return inventoryRepository.findAll();
    }
}
