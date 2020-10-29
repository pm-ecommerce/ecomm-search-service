package com.pm.ecommerce.search_service.repositories;

import com.pm.ecommerce.entities.Category;
import com.pm.ecommerce.entities.Product;
import com.pm.ecommerce.enums.ProductStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query(value="Select c from Category c where c.isDeleted=false and c.parent IS NULL ORDER BY c.name")
    List<Category> getAllCategories();

    @Query(value="Select p from Product p where p.category.id =:categoryId and p.status=:productStatus ORDER BY p.name" )
    Page<Product> getProductsByCategoryId(Integer categoryId, ProductStatus productStatus, Pageable page);

    @Query(value="Select * from categories c where c.is_deleted=0 order by  rand() limit 4",  nativeQuery = true)
    List<Category> getRandomCategories();

    @Query(value="Select c from Category c where c.isDeleted=false and c.parent IS NOT NULL ORDER BY c.name")
    List<Category> getAllChildCategories();

}
