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

    public List<Product> getProductsByCategory_Id(Integer categoryId){
        return productRepository.getProductsByCategory_Id(categoryId);
    }

    public Product getProductById(int id) {
        return productRepository.findById(id).get();
    }
}
