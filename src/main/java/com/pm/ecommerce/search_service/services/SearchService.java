package com.pm.ecommerce.search_service.services;

import com.pm.ecommerce.entities.Product;
import com.pm.ecommerce.search_service.models.FilterRequest;
import com.pm.ecommerce.search_service.models.PagedResponse;
import com.pm.ecommerce.search_service.models.ProductResult;
import com.pm.ecommerce.search_service.repositories.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {

    @Autowired
    SearchRepository searchRepository;

    public PagedResponse<ProductResult> getProductsByFilter(FilterRequest request) {
        Pageable paging = PageRequest.of(request.getPage() - 1, request.getLimit());

        if (request.getName().equals("") && request.getCategoryId() == 0) {
            Page<Product> pagedResult = searchRepository.getProductsByFilterNoNameAndCategory(request.getHighPrice(), request.getLowPrice(), request.getStatus(), request.getCategoryStatus(), request.getVendorStatus(), paging);
            int totalPages = pagedResult.getTotalPages();
            List<ProductResult> products = pagedResult.toList().stream().map(ProductResult::new).collect(Collectors.toList());
            return new PagedResponse<>(totalPages, request.getPage(), request.getLimit(), products);
        }

        if (request.getName().equals("")) {
            Page<Product> pagedResult = searchRepository.getProductsByFilterNoName(request.getCategoryId(), request.getHighPrice(), request.getLowPrice(), request.getStatus(), request.getCategoryStatus(), request.getVendorStatus(), paging);
            int totalPages = pagedResult.getTotalPages();
            List<ProductResult> products = pagedResult.toList().stream().map(ProductResult::new).collect(Collectors.toList());
            return new PagedResponse<>(totalPages, request.getPage(), request.getLimit(), products);
        }

        if (request.getCategoryId() == 0) {
            Page<Product> pagedResult = searchRepository.getProductsByFilterNoCategory(request.getName(), request.getHighPrice(), request.getLowPrice(), request.getStatus(), request.getCategoryStatus(), request.getVendorStatus(), paging);
            int totalPages = pagedResult.getTotalPages();
            List<ProductResult> products = pagedResult.toList().stream().map(ProductResult::new).collect(Collectors.toList());
            return new PagedResponse<>(totalPages, request.getPage(), request.getLimit(), products);
        }

        Page<Product> pagedResult = searchRepository.getProductsByFilter(request.getName(), request.getCategoryId(), request.getHighPrice(), request.getLowPrice(), request.getStatus(), request.getCategoryStatus(), request.getVendorStatus(), paging);
        int totalPages = pagedResult.getTotalPages();
        List<ProductResult> products = pagedResult.toList().stream().map(ProductResult::new).collect(Collectors.toList());
        return new PagedResponse<>(totalPages, request.getPage(), request.getLimit(), products);
    }

}

