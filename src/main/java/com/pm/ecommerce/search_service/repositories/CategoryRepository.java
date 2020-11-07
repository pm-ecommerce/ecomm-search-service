package com.pm.ecommerce.search_service.repositories;

import com.pm.ecommerce.entities.Category;
import com.pm.ecommerce.entities.Product;
import com.pm.ecommerce.enums.ProductStatus;
import com.pm.ecommerce.enums.VendorStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query(value="Select distinct c from Category c Join Product p on p.category.id=c.id Join Vendor v on p.vendor.id=v.id where p.status=:productStatus and v.status=:vendorStatus and c.isDeleted=false and c.parent IS NULL ORDER BY c.name")
    List<Category> getAllCategories(ProductStatus productStatus, VendorStatus vendorStatus);

    @Query(value="Select p from Product p where p.category.id =:categoryId and p.status=:productStatus ORDER BY p.id DESC" )
    Page<Product> getProductsByCategoryId(Integer categoryId, ProductStatus productStatus, Pageable page);

    @Query(value="select distinct c.* from categories c Join products p on p.category_id=c.id Join vendors v on p.vendor_id=v.id where p.status=4 and v.status=3 and c.is_deleted=0 order by rand() limit 4;",  nativeQuery = true)
    List<Category> getRandomCategories(ProductStatus productStatus,VendorStatus vendorStatus);

    @Query(value="Select distinct c from Category c Join Product p on p.category.id=c.id Join Vendor v on p.vendor.id=v.id where p.status=:productStatus and v.status=:vendorStatus and c.isDeleted=false and c.parent IS NOT NULL ORDER BY c.name")
    List<Category> getAllChildCategories(ProductStatus productStatus, VendorStatus vendorStatus);

}
