package com.ange.ecommerce_api.dtos;


public class ProductResponse {

    private ProductRequestDTO product;

    private String errorMessage;

    public ProductResponse() {}
    public ProductResponse(String error) {
        errorMessage = error;
    }

    public ProductRequestDTO getProduct() {
        return product;
    }

    public void setProduct(ProductRequestDTO product) {
        this.product = product;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
