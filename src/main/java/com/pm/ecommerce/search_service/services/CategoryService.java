package com.pm.ecommerce.search_service.services;

import com.pm.ecommerce.entities.Category;
import com.pm.ecommerce.enums.ProductStatus;
import com.pm.ecommerce.search_service.models.ProductResult;
import com.pm.ecommerce.search_service.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.getAllCategories();
    }

    public List<ProductResult> getProductsByCategoryId(Integer categoryId) {
        return categoryRepository.getProductsByCategoryId(categoryId, ProductStatus.PUBLISHED).stream().map(p -> new ProductResult(p)).collect(Collectors.toList());
    }

    public List<Category> getRandomCategories() {
        return categoryRepository.getRandomCategories();
    }

}
