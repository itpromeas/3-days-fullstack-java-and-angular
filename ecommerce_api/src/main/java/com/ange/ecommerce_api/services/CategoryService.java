package com.ange.ecommerce_api.services;

import com.ange.ecommerce_api.daos.CategoryRepository;
import com.ange.ecommerce_api.dtos.CategoryRequestDTO;
import com.ange.ecommerce_api.dtos.CategoryRespDTO;
import com.ange.ecommerce_api.dtos.CategoryResponse;
import com.ange.ecommerce_api.dtos.MyMapperDTO;
import com.ange.ecommerce_api.interfaces.Category;
import com.ange.ecommerce_api.models.CategoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements Category {

    private final CategoryRepository categoryRepository;
    @Autowired
    private MyMapperDTO myMapper;

    public CategoryService(CategoryRepository categoryRepo) {
        this.categoryRepository = categoryRepo;
    }

    @Override
    public List<CategoryRespDTO> getAllCategory() {
        return myMapper.categoriesListToDTO(categoryRepository.findAll());
    }

    @Override
    public CategoryResponse getCategoryById(Long id) {
        CategoryResponse response = new CategoryResponse();
        Optional<CategoryModel> category = categoryRepository.findById(id);
        if(category.isPresent()){
            response.setCategory(myMapper.categoryToDto(category.get()));
        }else{
            response.setErrorMessage("There is no category with id "+id);
        }
        return response;
    }

    @Override
    public CategoryResponse createCategory(CategoryRequestDTO categoryDTO) {
        String categoryName = categoryDTO.getName();
        CategoryResponse response = new CategoryResponse();

        // check that the name is not empty
        if (categoryName == null || categoryName.isEmpty()){
            response.setErrorMessage("Name should not be empty!");
            return response;
        }

        Optional<CategoryModel> existingCategory = categoryRepository.findByName(categoryName);
        if(existingCategory.isPresent()){
            response.setErrorMessage("Category with name "+categoryDTO.getName()+" already exists");
            return response;
        }

        CategoryModel category = new CategoryModel();
        category.setName(categoryName);
        category.setDescription(categoryDTO.getDescription());

        // save it in db
        response.setCategory(myMapper.categoryToDto(categoryRepository.save(category)));
        return response;
    }

    @Override
    public CategoryResponse updateCategory(Long id, CategoryModel categoryModel) {
        // check that the category with given id is in DB
        Optional<CategoryModel> existingCategory = categoryRepository.findById(id);

        CategoryResponse response = new CategoryResponse();

        if(existingCategory.isPresent()){
            // check that the name is not already in DB
            Optional<CategoryModel> sameCategory = categoryRepository.findByName(categoryModel.getName());
            if(sameCategory.get().getId() == id){
                // check that the name is not empty
                if (categoryModel.getName() == null || categoryModel.getName().isEmpty()){
                    response.setErrorMessage("Name should not be empty!");
                    return response;
                }

                // update category infos
                CategoryModel newCategory = existingCategory.get();
                newCategory.setName(categoryModel.getName());
                newCategory.setDescription(categoryModel.getDescription());

                // save it in db
                response.setCategory(myMapper.categoryToDto(categoryRepository.save(newCategory)));
                return response;
            }
            response.setErrorMessage("There is another category with name "+categoryModel.getName());
            return response;
        }

        return new CategoryResponse("category does not exists");
    }

    @Override
    public String deleteCategory(Long id) {
        Optional<CategoryModel> category = categoryRepository.findById(id);
        if(category.isPresent()){
            categoryRepository.deleteById(id);
            return "category with id "+id+" was successfully deleted";
        }
        return "No category with id "+id+" found!";
    }
}
