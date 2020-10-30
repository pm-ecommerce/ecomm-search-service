package com.pm.ecommerce.search_service.models;

import com.pm.ecommerce.entities.Image;
import com.pm.ecommerce.entities.Product;
import lombok.Data;

import java.util.Set;

@Data
public class ProductResult {

    private Integer id;
    private Integer category_id;
    private String name;
    private String slug;
    private double price;
    private Set<Image> images;

    public ProductResult(Product p) {
        id = p.getId();
        category_id = p.getCategory().getId();
        name = p.getName();
        slug = p.getSlug();
        price = p.getPrice();
        images = p.getImages();
    }

}
