package com.pm.ecommerce.search_service.repositories;

import com.pm.ecommerce.entities.Product;
import com.pm.ecommerce.enums.ProductStatus;
import com.pm.ecommerce.search_service.models.ProductResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value="Select p from Product p where p.category.id =:categoryId and p.status =:status")
    List<Product> getProductsByCategoryId(Integer categoryId, ProductStatus status);

    @Query("Select p from Product p where p.slug =:slug and p.status =:status")
    Product getProductsBySlug(String slug,ProductStatus status);

    @Query("SELECT p FROM Product p WHERE (:name is null or p.name = :name) and (:categoryId is null or p.category.id = :categoryId) and (:highPrice is null or p.price <=:highPrice) and (:lowPrice is null or p.price >=:lowPrice)")
    List<Product> getProductsByFilter(@Param("name") String name, @Param("categoryId") Integer categoryId , @Param("highPrice") Double highPrice, @Param("lowPrice") Double lowPrice);

}
