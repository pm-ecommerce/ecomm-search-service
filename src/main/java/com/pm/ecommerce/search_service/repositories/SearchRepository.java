package com.pm.ecommerce.search_service.repositories;

import com.pm.ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRepository extends JpaRepository<Product, Integer> {

    //    @Query("SELECT p FROM Product p WHERE (:name is null or p.name like :name) and (:categoryId is null or p.category.id = :categoryId) and (:highPrice is null or p.price <=:highPrice) and (:lowPrice is null or p.price >=:lowPrice) ORDER BY p.id ")
//    List<Product> getProductsByFilter(@Param("name") String name, @Param("categoryId") Integer categoryId , @Param("highPrice") Double highPrice, @Param("lowPrice") Double lowPrice, Integer limit, Integer offset);
<<<<<<< HEAD
 // status of product (published)
    //status of category (deleted = false)
    //status of vendor (approved)
    @Query(value="SELECT * FROM products p WHERE p.name like ?1 and p.category_id =?2 and p.price BETWEEN ?4 and ?3 ORDER BY p.id limit ?5 offset ?6", nativeQuery = true)
    List<Product> getProductsByFilter(@Param("name") String name, @Param("categoryId") Integer categoryId , @Param("highPrice") Double highPrice, @Param("lowPrice") Double lowPrice, @Param("limit") Integer limit, @Param("offset") Integer offset);
=======

    @Query(value = "SELECT * FROM products p join categories c on c.id=p.category_id join vendors v on v.id=p.vendor_id WHERE p.name like ?1 and p.category_id =?2 and p.price BETWEEN ?4 and ?3 and p.status =?7 and c.is_deleted=?8 and v.status=?9 ORDER BY p.id limit ?5 offset ?6", nativeQuery = true)
    List<Product> getProductsByFilter(@Param("name") String name, @Param("categoryId") Integer categoryId, @Param("highPrice") Double highPrice, @Param("lowPrice") Double lowPrice, @Param("limit") Integer limit, @Param("offset") Integer offset, @Param("status") Integer status, @Param("categoryStatus") Integer categoryStatus, @Param("vendorStatus") Integer vendorStatus);
>>>>>>> 0a43fa90227a74b91a50dbcf10275c6c68e8a44e

    @Query(value = "SELECT * FROM products p join categories c on c.id=p.category_id join vendors v on v.id=p.vendor_id WHERE p.category_id =?1 and p.price BETWEEN ?3 and ?2 and p.status =?6 and c.is_deleted=?7 and v.status=?8 ORDER BY p.id limit ?4 offset ?5", nativeQuery = true)
    List<Product> getProductsByFilterNoName(@Param("categoryId") Integer categoryId, @Param("highPrice") Double highPrice, @Param("lowPrice") Double lowPrice, @Param("limit") Integer limit, @Param("offset") Integer offset, @Param("status") Integer status, @Param("categoryStatus") Integer categoryStatus, @Param("vendorStatus") Integer vendorStatus);

    @Query(value = "SELECT * FROM products p join categories c on c.id=p.category_id join vendors v on v.id=p.vendor_id WHERE p.name like ?1 and p.price BETWEEN ?3 and ?2 and p.status =?6 and c.is_deleted=?7 and v.status=?8 ORDER BY p.id limit ?4 offset ?5", nativeQuery = true)
    List<Product> getProductsByFilterNoCategory(@Param("name") String name, @Param("highPrice") Double highPrice, @Param("lowPrice") Double lowPrice, @Param("limit") Integer limit, @Param("offset") Integer offset, @Param("status") Integer status, @Param("categoryStatus") Integer categoryStatus, @Param("vendorStatus") Integer vendorStatus);

    @Query(value = "SELECT * FROM products p join categories c on c.id=p.category_id join vendors v on v.id=p.vendor_id WHERE p.price BETWEEN ?2 and ?1 and p.status =?5 and c.is_deleted=?6 and v.status=?7 ORDER BY p.id limit ?3 offset ?4", nativeQuery = true)
    List<Product> getProductsByFilterNoNameAndCategory(@Param("highPrice") Double highPrice, @Param("lowPrice") Double lowPrice, @Param("limit") Integer limit, @Param("offset") Integer offset, @Param("status") Integer status, @Param("categoryStatus") Integer categoryStatus, @Param("vendorStatus") Integer vendorStatus);

}
