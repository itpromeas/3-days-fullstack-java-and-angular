package com.ange.ecommerce_api.dtos;

import com.ange.ecommerce_api.interfaces.MyMapper;
import com.ange.ecommerce_api.models.CategoryModel;
import com.ange.ecommerce_api.models.ProductModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyMapperDTO implements MyMapper {

    @Override
    public ProductRequestDTO productToDto(ProductModel product) {
        ProductRequestDTO productDto = new ProductRequestDTO();
        productDto.category = new CategoryRequestDTO().of(product.getCategory().getId(),product.getCategory().getName(), product.getCategory().getDescription());

        productDto.id = product.getId();

        productDto.setDescription(product.getDescription());
        productDto.setActive(product.isActive());
        productDto.setName(product.getName());
        productDto.setUnitsInStock(product.getUnitsInStock());
        productDto.setSku(product.getSku());
        productDto.setImageUrl(product.getImageUrl());
        return productDto;
    }

    @Override
    public CategoryRespDTO categoryToDto(CategoryModel category) {
        List<ProductRequestDTO> products = new ArrayList<>();
        category.getProducts().forEach(x -> {
            products.add(productToDto(x));
        });

        return new CategoryRespDTO().of(category.getId(), category.getName(), category.getDescription(), products);
    }

    @Override
    public List<ProductRequestDTO> productsListToDTO(List<ProductModel> products) {
        List<ProductRequestDTO> productsDTO = new ArrayList<>();
        products.forEach(x -> {
            productsDTO.add(productToDto(x));
        });
        return productsDTO;
    }

    @Override
    public List<CategoryRespDTO> categoriesListToDTO(List<CategoryModel> categories) {
        List<CategoryRespDTO> categoryRespDTOS = new ArrayList<>();

        categories.forEach(cat ->{
            List<ProductRequestDTO> products = new ArrayList<>();
            cat.getProducts().forEach(x -> {
                products.add(productToDto(x));
            });

            categoryRespDTOS.add(new CategoryRespDTO().of(cat.getId(), cat.getName(), cat.getDescription(), products));
        });

        return categoryRespDTOS;
    }

}
