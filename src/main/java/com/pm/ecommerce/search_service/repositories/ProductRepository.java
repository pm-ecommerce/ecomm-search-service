package com.pm.ecommerce.search_service.repositories;

import com.pm.ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> getProductsByCategory_Id(Integer categoryId);

    List<Product> getProductsByName(String productName);

    @Query("SELECT p FROM Product p WHERE (:name is null or p.name = :name) and (:categoryId is null or p.category.id = :categoryId) and (:highPrice is null or p.price <=:highPrice) and (:lowPrice is null or p.price >=:lowPrice)")
    List<Product> getProductsByFilter(@Param("name") String name, @Param("categoryId") Integer categoryId , @Param("highPrice") Double highPrice, @Param("lowPrice") Double lowPrice);

}
