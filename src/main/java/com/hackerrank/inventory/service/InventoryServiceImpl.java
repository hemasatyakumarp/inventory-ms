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
    private InventoryRepository InventoryRepository;

    @Override
    public void deleteAllInventorys() {
        InventoryRepository.deleteAllInBatch();
    }

    @Override
    public Boolean deleteInventoryById(Long id) {
    	 Inventory Inventory = InventoryRepository.findOne(id);

         if (Inventory == null) {
             throw null;
         }
         
        InventoryRepository.deleteById(id);
        return true;
    }

    @Override
    public Inventory createInventory(Inventory sku) {
        Inventory existingInventory = InventoryRepository.findOne(sku.getId());

        if (existingInventory != null) {
            throw new BadResourceRequestException("Inventory with same id exists.");
        }

        InventoryRepository.save(sku);
        return existingInventory;
    }
    
    @Override
    public Inventory updateInventory(Long skuId,Inventory sku) {
        
        Inventory tempInventory = InventoryRepository.findOne(skuId);

        if (tempInventory == null) {
            return null;
        }
       
        InventoryRepository.save(sku);
        return InventoryRepository.findOne(skuId);
        
    }

    @Override
    public Inventory getInventoryById(Long id) {
        Inventory Inventory = InventoryRepository.findOne(id);

        if (Inventory == null) {
            throw new NoSuchResourceFoundException("No Inventory with given id found.");
        }

        return Inventory;
    }

    @Override
    public List<Inventory> getAllInventorys() {
        return InventoryRepository.findAll();
    }
}
