package com.pm.ecommerce.search_service.services;

import com.pm.ecommerce.entities.Product;
import com.pm.ecommerce.search_service.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> getProductsByCategory_Id(Integer categoryId) {
        return productRepository.getProductsByCategory_Id(categoryId);
    }

    public List<Product> getProductsByName(String productName) {
        return productRepository.getProductsByName(productName);
    }

    public Product getProductById(Integer id) {
        return productRepository.findById(id).get();
    }

    public List<Product> getProductsByFilter(String name, Integer categoryId, Double highPrice, Double lowPrice) {
        return productRepository.getProductsByFilter(name, categoryId, highPrice, lowPrice);
    }
}
