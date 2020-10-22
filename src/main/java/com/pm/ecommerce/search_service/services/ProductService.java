package com.pm.ecommerce.search_service.services;

import com.pm.ecommerce.entities.Category;
import com.pm.ecommerce.entities.Product;
import com.pm.ecommerce.search_service.repositories.CategoryRepository;
import com.pm.ecommerce.search_service.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

//    public List<Product> getAllProductByCateGoryId(){
//        return productRepository.getAllByCategoryId(categoryId);
//    }
}
