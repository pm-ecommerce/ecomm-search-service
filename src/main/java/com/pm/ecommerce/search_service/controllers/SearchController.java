package com.pm.ecommerce.search_service.controllers;

import com.pm.ecommerce.entities.ApiResponse;
import com.pm.ecommerce.search_service.models.FilterRequest;
import com.pm.ecommerce.search_service.models.ProductResult;
import com.pm.ecommerce.search_service.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    SearchService searchService;

    @GetMapping("")
    public ResponseEntity<ApiResponse<List<ProductResult>>> getProductsByFilter(FilterRequest request) {
        ApiResponse<List<ProductResult>> response = new ApiResponse<>();
        try {
        List<ProductResult> productList = searchService.getProductsByFilter(request);
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
