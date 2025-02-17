package com.ange.ecommerce_api.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequestDTO {
    private Long id;
    private String name;

    private String description;

    CategoryRequestDTO(Long id, String name, String desc){
        this.id = id;
        this.name = name;
        this.description = desc;
    }

    public CategoryRequestDTO of(Long id, String name, String desc){
        return new CategoryRequestDTO(id, name, desc);
    }

    CategoryRequestDTO(){}
}
