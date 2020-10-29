package com.pm.ecommerce.search_service.controllers;

import com.pm.ecommerce.entities.ApiResponse;
import com.pm.ecommerce.entities.Category;
import com.pm.ecommerce.search_service.models.PagedResponse;
import com.pm.ecommerce.search_service.models.ProductResult;
import com.pm.ecommerce.search_service.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<ApiResponse<List<Category>>> getAllCategory() {
        ApiResponse<List<Category>> response = new ApiResponse<>();
        try {
            List<Category> categoryList = categoryService.getAllCategories();
            response.setMessage("List of Category");
            response.setData(categoryList);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setStatus(500);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/sub")
    public ResponseEntity<ApiResponse<List<Category>>> getAllChildCategories() {
        ApiResponse<List<Category>> response = new ApiResponse<>();
        try {
            List<Category> categoryList = categoryService.getAllChildCategories();
            response.setMessage("List of Child Category");
            response.setData(categoryList);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setStatus(500);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/products/{categoryId}")
    public ResponseEntity<ApiResponse<PagedResponse<ProductResult>>> getProductByCategoryId(@PathVariable Integer categoryId,
                                                                                            @RequestParam(required = true,defaultValue = "999999999") String limit,
                                                                                            @RequestParam(required = true,defaultValue = "1") String page) {
        ApiResponse<PagedResponse<ProductResult>> response = new ApiResponse<>();
        try {
            PagedResponse<ProductResult> productList = categoryService.getProductsByCategoryId(categoryId,Integer.parseInt(limit),Integer.parseInt(page));
            response.setMessage("List of Product by Category");
            response.setData(productList);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setStatus(500);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/random")
    public ResponseEntity<ApiResponse<List<Category>>> getRandomCategories() {
        ApiResponse<List<Category>> response = new ApiResponse<>();
        try {
            List<Category> categoryList = categoryService.getRandomCategories();
            response.setMessage("List of Category");
            response.setData(categoryList);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setStatus(500);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.ok(response);
    }

}
