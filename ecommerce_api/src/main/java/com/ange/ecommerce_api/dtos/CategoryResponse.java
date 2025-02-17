package com.ange.ecommerce_api.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponse {
    CategoryRespDTO category;
    String errorMessage;

    public CategoryResponse() {}
    public CategoryResponse(String error) {
        errorMessage = error;
    }
}
