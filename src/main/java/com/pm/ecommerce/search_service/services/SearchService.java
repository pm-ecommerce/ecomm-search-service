package com.pm.ecommerce.search_service.services;

import com.pm.ecommerce.search_service.models.ProductResult;
import com.pm.ecommerce.search_service.repositories.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {

    @Autowired
    SearchRepository searchRepository;

    public List<ProductResult> getProductsByFilter(String name, Integer categoryId, Double highPrice, Double lowPrice, Integer limit, Integer page) {
        Integer offset = (page - 1) * limit;
        if (name.equals("") && categoryId == 0) {
            return searchRepository.getProductsByFilterNoNameAndCategory(highPrice, lowPrice, limit, offset).stream().map(p -> new ProductResult(p)).collect(Collectors.toList());
        }
        if (name.equals("")) {
            System.out.println("no name");
            return searchRepository.getProductsByFilterNoName(categoryId, highPrice, lowPrice, limit, offset).stream().map(p -> new ProductResult(p)).collect(Collectors.toList());
        }
        if (categoryId == 0) {
            return searchRepository.getProductsByFilterNoCategory(name, highPrice, lowPrice, limit, offset).stream().map(p -> new ProductResult(p)).collect(Collectors.toList());
        }
        return searchRepository.getProductsByFilter(name, categoryId, highPrice, lowPrice, limit, offset).stream().map(p -> new ProductResult(p)).collect(Collectors.toList());
    }
}
