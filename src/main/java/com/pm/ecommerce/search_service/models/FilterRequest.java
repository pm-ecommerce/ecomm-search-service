package com.pm.ecommerce.search_service.models;

import lombok.Data;

@Data
public class FilterRequest {
    public String name;
    public Integer categoryId;
    public Double highPrice;
    public Double lowPrice;
    public Integer limit;
    public Integer page;

    public FilterRequest() {
        name = "";
        categoryId = 0;
        page = 1;
        limit = 20;
        highPrice = Double.MAX_VALUE;
        lowPrice = 0.00;
    }
}
