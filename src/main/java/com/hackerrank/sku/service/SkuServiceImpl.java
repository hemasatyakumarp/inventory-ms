package com.hackerrank.sku.service;

import com.hackerrank.sku.exception.BadResourceRequestException;
import com.hackerrank.sku.exception.NoSuchResourceFoundException;
import com.hackerrank.sku.model.Sku;
import com.hackerrank.sku.repository.SkuRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SkuService")
public class SkuServiceImpl implements SkuService {
    @Autowired
    private SkuRepository SkuRepository;

    @Override
    public void deleteAllSkus() {
        SkuRepository.deleteAllInBatch();
    }

    @Override
    public Boolean deleteSkuById(Long id) {
    	 Sku Sku = SkuRepository.findOne(id);

         if (Sku == null) {
             throw null;
         }
         
        SkuRepository.deleteById(id);
        return true;
    }

    @Override
    public Sku createSku(Sku sku) {
        Sku existingSku = SkuRepository.findOne(sku.getId());

        if (existingSku != null) {
            throw new BadResourceRequestException("Sku with same id exists.");
        }

        SkuRepository.save(sku);
        return existingSku;
    }
    
    @Override
    public Sku updateSku(Long skuId,Sku sku) {
        
        Sku tempSku = SkuRepository.findOne(skuId);

        if (tempSku == null) {
            return null;
        }
       
        SkuRepository.save(sku);
        return SkuRepository.findOne(skuId);
        
    }

    @Override
    public Sku getSkuById(Long id) {
        Sku Sku = SkuRepository.findOne(id);

        if (Sku == null) {
            throw new NoSuchResourceFoundException("No Sku with given id found.");
        }

        return Sku;
    }

    @Override
    public List<Sku> getAllSkus() {
        return SkuRepository.findAll();
    }
}
