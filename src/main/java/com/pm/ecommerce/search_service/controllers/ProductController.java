package com.pm.ecommerce.search_service.controllers;

import com.pm.ecommerce.entities.ApiResponse;
import com.pm.ecommerce.entities.Product;
import com.pm.ecommerce.search_service.repositories.ProductRepository;
import com.pm.ecommerce.search_service.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/category")
    public ResponseEntity<ApiResponse<List<Product>>> getProductByCategoryId(){
        ApiResponse<List<Product>> response = new ApiResponse<>();
        try{
            List<Product> productList = productRepository.findAll();

            response.setMessage("List of Product");
            response.setData(productList);
        } catch (Exception e){
            response.setMessage(e.getMessage());
            response.setStatus(500);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse<Product>> getProduct(){
        ApiResponse<Product> response = new ApiResponse<>();
        try{
            Product product = productRepository.getOne(1);

            response.setMessage("List of Product");
            response.setData(product);
        } catch (Exception e){
            response.setMessage(e.getMessage());
            response.setStatus(500);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.ok(response);
    }
}
