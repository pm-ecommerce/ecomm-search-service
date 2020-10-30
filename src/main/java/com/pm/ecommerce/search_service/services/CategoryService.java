package com.pm.ecommerce.search_service.services;

import com.pm.ecommerce.entities.Category;
import com.pm.ecommerce.entities.Product;
import com.pm.ecommerce.enums.ProductStatus;
import com.pm.ecommerce.enums.VendorStatus;
import com.pm.ecommerce.search_service.models.PagedResponse;
import com.pm.ecommerce.search_service.models.ProductResult;
import com.pm.ecommerce.search_service.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.getAllCategories(ProductStatus.PUBLISHED, VendorStatus.APPROVED);
    }

    public List<Category> getAllChildCategories() {
        return categoryRepository.getAllChildCategories(ProductStatus.PUBLISHED, VendorStatus.APPROVED);
    }

    public Category getCategoryById(Integer categoryId){
        return categoryRepository.findById(categoryId).get();
    }

    public PagedResponse<ProductResult> getProductsByCategoryId(Integer categoryId, Integer limit, Integer page) {
        Pageable paging = PageRequest.of(page - 1, limit);
        Page<Product> pagedResult = categoryRepository.getProductsByCategoryId(categoryId,ProductStatus.PUBLISHED, paging);
        int totalPages = pagedResult.getTotalPages();
        List<ProductResult> products = pagedResult.toList().stream().map(ProductResult::new).collect(Collectors.toList());
        return new PagedResponse<>(totalPages, page, limit, products);
    }

    public List<Category> getRandomCategories() {
        return categoryRepository.getRandomCategories(ProductStatus.PUBLISHED,VendorStatus.APPROVED);
    }

}
