package com.pm.ecommerce.search_service.repositories;

import com.pm.ecommerce.entities.Product;
import com.pm.ecommerce.enums.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("Select p from Product p inner join Vendor v on v.id = p.vendor.id inner join Category  c on c.id = p.category.id where v.status = 3 and c.isDeleted=false and p.slug =:slug and p.status =:status")
    Product getProductsBySlug(String slug, ProductStatus status);

    @Query(value = "Select * from products p inner join vendors v on v.id = p.vendor_id inner join categories  c on c.id = p.category_id where v.status = 3 and c.is_deleted=false and p.status=4 ORDER BY p.id DESC limit ?1 ", nativeQuery = true)
    List<Product> getLatestProducts(Integer number, ProductStatus status);

}
