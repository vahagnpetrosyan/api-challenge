package com.jazva.challenge.service.impl;

import com.jazva.challenge.model.ProductInventoryRequest;
import com.jazva.challenge.repository.ProductInventoryRepository;
import com.jazva.challenge.service.ProductInventoryService;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Product inventory service.
 */
@Service
public class ProductInventoryServiceImpl implements ProductInventoryService {

    @Autowired
    private ProductInventoryRepository productInventoryRepository;

    @Override
    public int getTotalInventory(String productId) {
        return ((BigInteger) productInventoryRepository.findTotal(productId)[0]).intValue();
    }

    @Override
    @Transactional
    public void resetInventories(String productId) {
        productInventoryRepository.resetInventory(productId);
    }

    @Override
    @Transactional
    public void addInventory(ProductInventoryRequest productInventoryRequest) {
        Object[] value = productInventoryRepository.findQuantity(productInventoryRequest.getProductId(),
                productInventoryRequest.getLocationId());

        int toBeAdded = 0;
        if (value.length != 0) {
            Integer currentQuantity = (Integer) value[0];
            if (currentQuantity + productInventoryRequest.getQuantity() > 0) {
                toBeAdded = currentQuantity + productInventoryRequest.getQuantity();
            }

            productInventoryRepository.updateInventory(toBeAdded, productInventoryRequest.getProductId(),
                    productInventoryRequest.getLocationId());
        } else {
            if (productInventoryRequest.getQuantity() > toBeAdded) {
                toBeAdded = productInventoryRequest.getQuantity();
            }
            productInventoryRepository.addInventory(productInventoryRequest.getProductId(),
                    productInventoryRequest.getLocationId(),
                    toBeAdded);
        }

    }

    @Override
    public Map<String, Integer> getQuantitiesByLocation(String productId) {
        Map<String, Integer> response = new HashMap<>();

        List<Object[]> locationCounts = productInventoryRepository.findQuantitiesByLocation(productId);
        for (Object[] locationCount : locationCounts) {
            response.put((String) locationCount[0], ((BigInteger) locationCount[1]).intValue());
        }

        return response;
    }
}
