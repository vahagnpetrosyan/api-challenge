package com.jazva.challenge.model;

import lombok.Data;

/**
 * The type Product inventory request.
 */
@Data
public class ProductInventoryRequest {
    private String productId;
    private int locationId;
    private int quantity;
}
