package com.pm.ecommerce.search_service.repositories;

import com.pm.ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    //List<Product> getAllByCategoryId(Integer categoryId);
//    @Query("from Product p where p.category.id = :id")
//    List<Product> findProductsByCategoryId(@Param("id") Integer id);
}
