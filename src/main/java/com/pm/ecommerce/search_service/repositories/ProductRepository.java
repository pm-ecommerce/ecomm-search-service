package com.pm.ecommerce.search_service.repositories;

import com.pm.ecommerce.entities.Product;
import com.pm.ecommerce.enums.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("Select p from Product p where p.slug =:slug and p.status =:status")
    Product getProductsBySlug(String slug,ProductStatus status);

    @Query(value = "Select * from products p where p.status=?2 ORDER BY p.id DESC limit ?1 ", nativeQuery = true)
    List<Product> getLatestProducts(Integer number,ProductStatus status);

}
