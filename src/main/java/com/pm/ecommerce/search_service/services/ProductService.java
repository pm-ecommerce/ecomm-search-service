package com.pm.ecommerce.search_service.services;

import com.pm.ecommerce.entities.Product;
import com.pm.ecommerce.enums.ProductStatus;
import com.pm.ecommerce.search_service.models.ProductResult;
import com.pm.ecommerce.search_service.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<ProductResult> getProductsByCategoryId(Integer categoryId) {
        return productRepository.getProductsByCategoryId(categoryId, ProductStatus.PUBLISHED).stream().map(p -> new ProductResult(p)).collect(Collectors.toList());
    }

    public Product getProductsBySlug(String slug) {
        return productRepository.getProductsBySlug(slug,ProductStatus.PUBLISHED);
    }

    public Product getProductById(Integer id) {
        return productRepository.findById(id).get();
    }

    public List<ProductResult> getProductsByFilter(String name, Integer categoryId, Double highPrice, Double lowPrice) {
        return productRepository.getProductsByFilter(name, categoryId, highPrice, lowPrice).stream().map(p -> new ProductResult(p)).collect(Collectors.toList());
    }
}
