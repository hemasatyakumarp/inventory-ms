package com.hackerrank.inventory.repository;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hackerrank.inventory.model.Inventory;


@Repository("InventoryRepository")
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    @Transactional
    Long deleteById(Long id);
    
    @Query(value="select * from Inventory i where i.inventory_On_Hand < i.min_Qty_Req",nativeQuery = true)
    public List<Inventory> findInadequateInventory();
}
