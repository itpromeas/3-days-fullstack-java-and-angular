package com.ange.ecommerce_api.dtos;

import com.ange.ecommerce_api.models.ProductModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {

    ProductModel product;

    String errorMessage;

    public ProductResponse() {}
    public ProductResponse(String error) {
        errorMessage = error;
    }
}
