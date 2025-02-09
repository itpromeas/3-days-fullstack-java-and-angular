package com.ange.ecommerce_api.dtos;

import com.ange.ecommerce_api.models.CategoryModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponse {
    CategoryModel category;
    String errorMessage;

    public CategoryResponse() {}
    public CategoryResponse(String error) {
        errorMessage = error;
    }
}
