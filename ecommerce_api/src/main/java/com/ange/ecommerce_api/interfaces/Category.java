package com.ange.ecommerce_api.interfaces;

import com.ange.ecommerce_api.dtos.CategoryDTO;
import com.ange.ecommerce_api.dtos.CategoryResponse;
import com.ange.ecommerce_api.models.CategoryModel;

import java.util.List;

public interface Category {

    /***
     * get all categories
     * @return a list of categories
     */
    List<CategoryModel> getAllCategory();

    /***
     * get a category by id
     * @param id: an integer representing a category id
     * @return a category
     */
    CategoryResponse getCategoryById(Long id);

    /***
     * create a category
     * @param categoryDTO: category params
     */
    CategoryResponse createCategory(CategoryDTO categoryDTO);

    /***
     * update a given category
     * @param id: an integer
     * @param categoryModel: category model
     * @return a category
     */
    CategoryResponse updateCategory(Long id, CategoryModel categoryModel);

    /***
     * delete a category
     * @param id: category id
     * @return a message
     */
    String deleteCategory(Long id);

}
