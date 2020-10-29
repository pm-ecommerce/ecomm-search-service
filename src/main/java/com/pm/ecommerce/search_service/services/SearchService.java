package com.pm.ecommerce.search_service.services;

import com.pm.ecommerce.entities.*;
import com.pm.ecommerce.enums.ProductStatus;
import com.pm.ecommerce.enums.VendorStatus;
import com.pm.ecommerce.search_service.models.FilterRequest;
import com.pm.ecommerce.search_service.models.PagedResponse;
import com.pm.ecommerce.search_service.models.ProductResult;
import com.pm.ecommerce.search_service.repositories.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {
    @Autowired
    SearchRepository searchRepository;

    @Autowired
    EntityManager em;

    public List<ProductResult> getProductsByFilterV2(FilterRequest request) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);

        Root<Product> root = query.from(Product.class);
        Predicate prodStatus = builder.equal(root.get(Product_.status), ProductStatus.PUBLISHED);

        Join<Product, Vendor> vendorJoin = root.join(Product_.vendor);
        Predicate vendorStatus = builder.equal(vendorJoin.get(Vendor_.status), VendorStatus.APPROVED);

        Join<Product, Category> categoryJoin = root.join(Product_.category);
        Predicate categoryStatus = builder.equal(categoryJoin.get(Category_.isDeleted), false);

        Predicate all = builder.and(prodStatus, vendorStatus, categoryStatus);
        query.select(root);
        query.where(all);

        List<Product> products = new ArrayList<>();
        try {
            TypedQuery<Product> typedQuery = em.createQuery(query);
            products = typedQuery.getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return products.stream().map(ProductResult::new).collect(Collectors.toList());
    }

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

