package com.hackerrank.sku.service;

import com.hackerrank.sku.model.Sku;
import java.util.List;

public interface SkuService {
    void deleteAllSkus();
    Boolean deleteSkuById(Long id);
    Sku createSku(Sku sku);
    Sku getSkuById(Long id);
    List<Sku> getAllSkus();
	Sku updateSku(Long id,Sku sku);
}
