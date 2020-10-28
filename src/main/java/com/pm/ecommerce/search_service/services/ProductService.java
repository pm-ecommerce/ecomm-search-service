package com.pm.ecommerce.search_service.services;

import com.pm.ecommerce.entities.Product;
import com.pm.ecommerce.enums.ProductStatus;
import com.pm.ecommerce.search_service.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Product getProductsBySlug(String slug) {
        return productRepository.getProductsBySlug(slug, ProductStatus.PUBLISHED);
    }

    public Product getProductById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> getLatestProducts(Integer number) {
        return productRepository.getLatestProducts(number, ProductStatus.PUBLISHED);
    }
}
