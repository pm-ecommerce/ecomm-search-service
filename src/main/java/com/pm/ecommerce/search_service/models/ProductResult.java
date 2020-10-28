package com.pm.ecommerce.search_service.models;

import com.pm.ecommerce.entities.Category;
import com.pm.ecommerce.entities.Image;
import com.pm.ecommerce.entities.Product;
import lombok.Data;

import java.util.List;

@Data
public class ProductResult {

    private Category category;
    private String name;
    private String slug;
    private double price;
    private List<Image> images;

    public ProductResult(Product p) {
        category = p.getCategory();
        name = p.getName();
        slug = p.getSlug();
        price = p.getPrice();
        images = p.getImages();
    }

}
