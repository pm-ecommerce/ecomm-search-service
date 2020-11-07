package com.pm.ecommerce.search_service.models;

import com.pm.ecommerce.entities.Image;
import com.pm.ecommerce.entities.Product;
import com.pm.ecommerce.entities.ProductAttribute;
import lombok.Data;

import java.util.Set;

@Data
public class ProductDetails {

    private Integer id;
    private Integer categoryId;
    private String name;
    private String slug;
    private double price;
    private Set<Image> images;
    private Set<ProductAttribute> attributes;
    private String description;

    public ProductDetails(Product p) {
        id = p.getId();
        categoryId = p.getCategory().getId();
        name = p.getName();
        slug = p.getSlug();
        price = p.getPrice();
        images = p.getImages();
        attributes = p.getAttributes();
        description = p.getDescription();
    }

}
