package com.pm.ecommerce.search_service.services;

import com.pm.ecommerce.entities.Product;
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
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(int id) {
        return productRepository.findById(id).get();
    }
}
