package com.jazva.challenge.repository;

import com.jazva.challenge.model.Product;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * The interface Product inventory repository.
 */
@Repository
public interface ProductInventoryRepository extends CrudRepository<Product, Long> {
    /**
     * Find quantities by location list.
     *
     * @param productId the product id
     * @return the list
     */
    @Query(nativeQuery = true, value =
            "SELECT " +
                    "l.name as location, SUM(pl.cnt) as quantity " +
                    "FROM productlocations pl " +
                    "JOIN locations l ON pl.location_id = l.id and pl.product_id = :productId " +
                    "GROUP BY location_id")
    List<Object[]> findQuantitiesByLocation(@Param("productId") String productId);

    /**
     * Find quantity object [ ].
     *
     * @param productId  the product id
     * @param locationId the location id
     * @return the object [ ]
     */
    @Query(nativeQuery = true, value =
            "SELECT " +
                    "cnt as quantity " +
                    "FROM productlocations " +
                    "WHERE product_id = :productId and location_id = :locationId")
    Object[] findQuantity(@Param("productId") String productId,
                          @Param("locationId") Integer locationId);

    /**
     * Update inventory.
     *
     * @param quantity   the quantity
     * @param productId  the product id
     * @param locationId the location id
     */
    @Modifying
    @Query(nativeQuery = true, value =
            "UPDATE " +
                    "productlocations pl " +
                    "SET pl.cnt = :quantity " +
                    "WHERE product_id = :productId and location_id = :locationId")
    void updateInventory(@Param("quantity") Integer quantity,
                         @Param("productId") String productId,
                         @Param("locationId") Integer locationId);

    /**
     * Add inventory.
     *
     * @param productId  the product id
     * @param locationId the location id
     * @param quantity   the quantity
     */
    @Modifying
    @Query(nativeQuery = true, value =
            "INSERT INTO productlocations " +
                    "(product_id, location_id, cnt) " +
                    "VALUES " +
                    "(:productId, :locationId, :quantity)")
    void addInventory(@Param("productId") String productId,
                      @Param("locationId") Integer locationId,
                      @Param("quantity") Integer quantity);

    /**
     * Reset inventory.
     *
     * @param productId the product id
     */
    @Modifying
    @Query(nativeQuery = true, value =
            "UPDATE " +
                    "productlocations pl " +
                    "SET pl.cnt = 0 " +
                    "WHERE product_id = :productId")
    void resetInventory(@Param("productId") String productId);

    /**
     * Find total object [ ].
     *
     * @param productId the product id
     * @return the object [ ]
     */
    @Query(nativeQuery = true, value =
            "SELECT SUM(pl.cnt) as total " +
                    "FROM productlocations pl " +
                    "WHERE pl.product_id = :productId " +
                    "GROUP BY pl.product_id")
    Object[] findTotal(@Param("productId") String productId);
}
