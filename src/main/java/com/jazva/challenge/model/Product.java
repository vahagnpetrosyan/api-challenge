package com.jazva.challenge.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

/**
 * The type Product.
 */
@Data
@Entity
public class Product {
    @Id
    private String id;
    private String name;
}
