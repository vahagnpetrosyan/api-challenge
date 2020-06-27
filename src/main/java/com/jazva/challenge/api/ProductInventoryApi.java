package com.jazva.challenge.api;

import com.jazva.challenge.model.ProductInventoryRequest;
import com.jazva.challenge.service.ProductInventoryService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * The type Product inventory api.
 */
@RestController
@RequestMapping("/api/products")
public class ProductInventoryApi {

    @Autowired
    private ProductInventoryService productInventoryService;

    /**
     * Gets quantities by location.
     *
     * @param productId the product id
     * @return the quantities by location
     */
    @RequestMapping(value = "/{productId}/quantities", method = RequestMethod.GET)
    public Map<String, Integer> getQuantitiesByLocation(@PathVariable("productId") String productId) {
        return productInventoryService.getQuantitiesByLocation(productId);
    }

    /**
     * Add inventory.
     *
     * @param productInventoryRequest the product inventory request
     */
    @RequestMapping(value = "/inventory", method = RequestMethod.PUT)
    public void addInventory(@RequestBody ProductInventoryRequest productInventoryRequest) {
        productInventoryService.addInventory(productInventoryRequest);
    }

    /**
     * Reset inventories.
     *
     * @param productId the product id
     */
    @RequestMapping(value = "/{productId}/inventory/reset", method = RequestMethod.PUT)
    public void resetInventories(@PathVariable("productId") String productId) {
        productInventoryService.resetInventories(productId);
    }

    /**
     * Gets totalinventory.
     *
     * @param productId the product id
     * @return the totalinventory
     */
    @RequestMapping(value = "/{productId}/inventory/total", method = RequestMethod.GET)
    public int getTotalinventory(@PathVariable("productId") String productId) {
        return productInventoryService.getTotalInventory(productId);
    }
}
