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

    public Integer status;
    public Integer categoryStatus;
    public Integer vendorStatus;

    public FilterRequest() {
        name = "";
        categoryId = 0;
        page = 1;
        limit = 10;
        highPrice = Double.MAX_VALUE;
        lowPrice = 0.00;

        status = 4;
        categoryStatus = 0;
        vendorStatus = 3;
    }
}
