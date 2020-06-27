package com.jazva.challenge.service;

import com.jazva.challenge.model.ProductInventoryRequest;

import java.util.Map;

/**
 * The interface Product inventory service.
 */
public interface ProductInventoryService {
    /**
     * Gets quantities by location.
     *
     * @param productId the product id
     * @return the quantities by location
     */
    Map<String, Integer> getQuantitiesByLocation(String productId);

    /**
     * Add inventory.
     *
     * @param productInventoryRequest the product inventory request
     */
    void addInventory(ProductInventoryRequest productInventoryRequest);

    /**
     * Reset inventories.
     *
     * @param productId the product id
     */
    void resetInventories(String productId);

    /**
     * Gets total inventory.
     *
     * @param productId the product id
     * @return the total inventory
     */
    int getTotalInventory(String productId);
}
