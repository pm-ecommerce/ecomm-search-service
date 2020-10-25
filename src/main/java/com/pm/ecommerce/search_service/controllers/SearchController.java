package com.pm.ecommerce.search_service.controllers;

import com.pm.ecommerce.entities.ApiResponse;
import com.pm.ecommerce.search_service.models.ProductResult;
import com.pm.ecommerce.search_service.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    SearchService searchService;

    @GetMapping("")
    public ResponseEntity<ApiResponse<List<ProductResult>>> getProductsByFilter(
            @RequestParam(required = true,defaultValue = "") String name,
            @RequestParam(required = true,defaultValue = "0") Integer categoryId,
            @RequestParam(required = true,defaultValue = "9999999999999") Double highPrice,
            @RequestParam(required = true,defaultValue = "0") Double lowPrice,
            @RequestParam(required = true,defaultValue = "10") Integer limit,
            @RequestParam(required = true,defaultValue = "1") Integer page
    ) {
        ApiResponse<List<ProductResult>> response = new ApiResponse<>();
        try {
        List<ProductResult> productList = searchService.getProductsByFilter(name, categoryId, highPrice, lowPrice, limit, page);
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
