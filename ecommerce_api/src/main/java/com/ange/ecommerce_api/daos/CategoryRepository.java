package com.ange.ecommerce_api.daos;

import com.ange.ecommerce_api.models.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {
}
