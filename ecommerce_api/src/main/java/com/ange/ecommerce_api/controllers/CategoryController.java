package com.ange.ecommerce_api.controllers;

import com.ange.ecommerce_api.dtos.CategoryDTO;
import com.ange.ecommerce_api.dtos.CategoryResponse;
import com.ange.ecommerce_api.models.CategoryModel;
import com.ange.ecommerce_api.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryModel> getAll()  {
        return categoryService.getAllCategory();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getById(@PathVariable Long id)  {
        return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CategoryResponse> create(@RequestBody CategoryDTO categoryDTO)  {
        CategoryResponse response = categoryService.createCategory(categoryDTO);
        if(response.getCategory() == null){
            new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable Long id, @RequestBody CategoryModel category)  {
        return ResponseEntity.ok(categoryService.updateCategory(id, category));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id)  {
        return new ResponseEntity<>(categoryService.deleteCategory(id), HttpStatus.OK);
    }
}
