package com.hackerrank.inventory.service;

import java.util.List;

import com.hackerrank.inventory.model.Inventory;

public interface InventoryService {
    void deleteAllInventorys();
    Boolean deleteInventoryById(Long id);
    Inventory createInventory(Inventory sku);
    Inventory getInventoryById(Long id);
    List<Inventory> getAllInventorys();
	Inventory updateInventory(Long id,Inventory sku);
}
