package com.ange.ecommerce_api.dtos;

import com.ange.ecommerce_api.models.CategoryModel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDTO {
    private Long id;

    private CategoryModel category;

    private String sku; // stock keeping unit

    private String name;

    private String description;

    private BigDecimal unitPrice;

    private String imageUrl;

    private boolean active;

    private int unitsInStock;
}
