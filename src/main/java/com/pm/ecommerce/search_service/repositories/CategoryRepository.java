package com.pm.ecommerce.search_service.repositories;

import com.pm.ecommerce.entities.Category;
import com.pm.ecommerce.entities.Product;
import com.pm.ecommerce.enums.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(value="Select p from Product p where p.category.id =:categoryId and p.status =:status")
    List<Product> getProductsByCategoryId(Integer categoryId, ProductStatus status);

}
