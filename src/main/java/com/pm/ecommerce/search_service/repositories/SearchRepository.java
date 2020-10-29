package com.pm.ecommerce.search_service.repositories;

import com.pm.ecommerce.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRepository extends JpaRepository<Product, Integer> {

    //    @Query("SELECT p FROM Product p WHERE (:name is null or p.name like :name) and (:categoryId is null or p.category.id = :categoryId) and (:highPrice is null or p.price <=:highPrice) and (:lowPrice is null or p.price >=:lowPrice) ORDER BY p.id ")
//    List<Product> getProductsByFilter(@Param("name") String name, @Param("categoryId") Integer categoryId , @Param("highPrice") Double highPrice, @Param("lowPrice") Double lowPrice, Integer limit, Integer offset);

    @Query(value = "SELECT * FROM products p join categories c on c.id=p.category_id join vendors v on v.id=p.vendor_id WHERE p.name like ?1 and p.category_id =?2 and p.price BETWEEN ?4 and ?3 and p.status =?5 and c.is_deleted=?6 and v.status=?7 ORDER BY p.id ", nativeQuery = true)
    Page<Product> getProductsByFilter(@Param("name") String name, @Param("categoryId") Integer categoryId, @Param("highPrice") Double highPrice, @Param("lowPrice") Double lowPrice,  @Param("status") Integer status, @Param("categoryStatus") Integer categoryStatus, @Param("vendorStatus") Integer vendorStatus, Pageable page);

    @Query(value = "SELECT * FROM products p join categories c on c.id=p.category_id join vendors v on v.id=p.vendor_id WHERE p.category_id =?1 and p.price BETWEEN ?3 and ?2 and p.status =?4 and c.is_deleted=?5 and v.status=?6 ORDER BY p.id", nativeQuery = true)
    Page<Product> getProductsByFilterNoName(@Param("categoryId") Integer categoryId, @Param("highPrice") Double highPrice, @Param("lowPrice") Double lowPrice, @Param("status") Integer status, @Param("categoryStatus") Integer categoryStatus, @Param("vendorStatus") Integer vendorStatus, Pageable page);

    @Query(value = "SELECT * FROM products p join categories c on c.id=p.category_id join vendors v on v.id=p.vendor_id WHERE p.name like ?1 and p.price BETWEEN ?3 and ?2 and p.status =?4 and c.is_deleted=?5 and v.status=?6 ORDER BY p.id ", nativeQuery = true)
    Page<Product> getProductsByFilterNoCategory(@Param("name") String name, @Param("highPrice") Double highPrice, @Param("lowPrice") Double lowPrice,  @Param("status") Integer status, @Param("categoryStatus") Integer categoryStatus, @Param("vendorStatus") Integer vendorStatus, Pageable page);

    @Query(value = "SELECT * FROM products p join categories c on c.id=p.category_id join vendors v on v.id=p.vendor_id WHERE p.price BETWEEN ?2 and ?1 and p.status =?3 and c.is_deleted=?4 and v.status=?5 ORDER BY p.id", nativeQuery = true)
    Page<Product> getProductsByFilterNoNameAndCategory(@Param("highPrice") Double highPrice, @Param("lowPrice") Double lowPrice, @Param("status") Integer status, @Param("categoryStatus") Integer categoryStatus, @Param("vendorStatus") Integer vendorStatus, Pageable page);

}
