package com.hackerrank.sku.repository;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hackerrank.sku.model.Sku;


@Repository("skyRepository")
public interface SkuRepository extends JpaRepository<Sku, Long> {
    @Transactional
    Long deleteById(Long id);
}
