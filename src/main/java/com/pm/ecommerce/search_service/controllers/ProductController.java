package com.pm.ecommerce.search_service.controllers;

import com.pm.ecommerce.entities.ApiResponse;
import com.pm.ecommerce.entities.Product;
import com.pm.ecommerce.search_service.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<ApiResponse<List<Product>>> getProductByCategoryId(@PathVariable Integer categoryId) {
        ApiResponse<List<Product>> response = new ApiResponse<>();
        try {
            List<Product> productList = productService.getProductsByCategory_Id(categoryId);
            response.setMessage("List of Product by Category");
            response.setData(productList);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setStatus(500);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("{productId}")
    public ResponseEntity<ApiResponse<Product>> getProductById(@PathVariable Integer productId) {
        ApiResponse<Product> response = new ApiResponse<>();
        try {
            Product product = productService.getProductById(productId);
            response.setMessage("Product Details");
            response.setData(product);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setStatus(500);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search/{productName}")
    public ResponseEntity<ApiResponse<List<Product>>> getProductsByName(@PathVariable String productName) {
        ApiResponse<List<Product>> response = new ApiResponse<>();
        try {
            List<Product> productList = productService.getProductsByName(productName);
            response.setMessage("List of Product by name");
            response.setData(productList);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setStatus(500);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Product>>> getProductsByFilter(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) Double highPrice,
            @RequestParam(required = false) Double lowPrice
    ) {
        ApiResponse<List<Product>> response = new ApiResponse<>();
        try {
            List<Product> productList = productService.getProductsByFilter(name, categoryId, highPrice, lowPrice);
            response.setMessage("List of Product by filter");
            response.setData(productList);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setStatus(500);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.ok(response);
    }

}
