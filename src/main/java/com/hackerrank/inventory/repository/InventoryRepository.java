package com.hackerrank.inventory.repository;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hackerrank.inventory.model.Inventory;


@Repository("skyRepository")
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    @Transactional
    Long deleteById(Long id);
}
