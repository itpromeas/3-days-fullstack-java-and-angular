package com.ange.ecommerce_api.interfaces;

import com.ange.ecommerce_api.dtos.CategoryRespDTO;
import com.ange.ecommerce_api.dtos.ProductRequestDTO;
import com.ange.ecommerce_api.models.CategoryModel;
import com.ange.ecommerce_api.models.ProductModel;

import java.util.List;

public interface MyMapper {
    ProductRequestDTO productToDto(ProductModel product);
    CategoryRespDTO categoryToDto(CategoryModel category);
    List<ProductRequestDTO> productsListToDTO(List<ProductModel> products);
    List<CategoryRespDTO> categoriesListToDTO(List<CategoryModel> categories);
}
