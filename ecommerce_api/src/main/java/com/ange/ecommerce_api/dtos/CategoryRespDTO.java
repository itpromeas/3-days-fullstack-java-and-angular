package com.ange.ecommerce_api.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryRespDTO {
    private Long id;

    private String name;

    private String description;

    List<ProductRequestDTO> products;

    CategoryRespDTO(Long id, String name, String desc, List<ProductRequestDTO> prod){
        this.id = id;
        this.name = name;
        this.description = desc;
        this.products = prod;
    }

    public CategoryRespDTO of(Long id, String name, String desc, List<ProductRequestDTO> prd){
        return new CategoryRespDTO(id, name, desc, prd);
    }

    CategoryRespDTO(){}
}
