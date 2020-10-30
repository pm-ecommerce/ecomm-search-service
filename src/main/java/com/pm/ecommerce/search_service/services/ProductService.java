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

    public ProductResult getProductsBySlug(String slug) {
        return new ProductResult(productRepository.getProductsBySlug(slug, ProductStatus.PUBLISHED));
    }

    public Product getProductById(Integer id) {
        return productRepository.findById(id).get();
    }

    public List<ProductResult> getLatestProducts(Integer number) {
        return productRepository.getLatestProducts(number, ProductStatus.PUBLISHED).stream().map(ProductResult::new).collect(Collectors.toList());
    }
}
