package com.ange.ecommerce_api.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCreateDTO extends ProductRequestBaseDTO {

    private Long category_id;
}
