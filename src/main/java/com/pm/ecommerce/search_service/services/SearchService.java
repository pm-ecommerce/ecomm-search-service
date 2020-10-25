package com.pm.ecommerce.search_service.services;

import com.pm.ecommerce.search_service.models.FilterRequest;
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

    public List<ProductResult> getProductsByFilter(FilterRequest request) {
        Integer offset = (request.getPage() - 1) * request.getLimit();

        if (request.getQuery().equals("") && request.getCategoryId() == 0) {
            return searchRepository.getProductsByFilterNoNameAndCategory(request.getHighPrice(), request.getLowPrice(), request.getLimit(), offset).stream().map(ProductResult::new).collect(Collectors.toList());
        }

        if (request.getQuery().equals("")) {
            return searchRepository.getProductsByFilterNoName(request.getCategoryId(), request.getHighPrice(), request.getLowPrice(), request.getLimit(), offset).stream().map(ProductResult::new).collect(Collectors.toList());
        }

        if (request.getCategoryId() == 0) {
            return searchRepository.getProductsByFilterNoCategory(request.getQuery(), request.getHighPrice(), request.getLowPrice(), request.getLimit(), offset).stream().map(ProductResult::new).collect(Collectors.toList());
        }

        return searchRepository.getProductsByFilter(request.getQuery(), request.getCategoryId(), request.getHighPrice(), request.getLowPrice(), request.getLimit(), offset).stream().map(ProductResult::new).collect(Collectors.toList());
    }
}