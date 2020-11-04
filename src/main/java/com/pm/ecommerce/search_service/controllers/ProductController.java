package com.pm.ecommerce.search_service.controllers;

import com.pm.ecommerce.entities.ApiResponse;
import com.pm.ecommerce.search_service.models.ProductDetails;
import com.pm.ecommerce.search_service.models.ProductResult;
import com.pm.ecommerce.search_service.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/{slug}")
    public ResponseEntity<ApiResponse<ProductDetails>> getProductsByName(@PathVariable String slug) {
        ApiResponse<ProductDetails> response = new ApiResponse<>();
        try {
            ProductDetails product = productService.getProductsBySlug(slug);
            response.setMessage("Product by slug");
            response.setData(product);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setStatus(500);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/latest/{number}")
    public ResponseEntity<ApiResponse<List<ProductResult>>> getProductsByName(@PathVariable Integer number) {
        ApiResponse<List<ProductResult>> response = new ApiResponse<>();
        try {
            List<ProductResult> product = productService.getLatestProducts(number);
            response.setMessage("List of latest Products");
            response.setData(product);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setStatus(500);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.ok(response);
    }

}
