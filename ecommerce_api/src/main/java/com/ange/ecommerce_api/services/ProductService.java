package com.ange.ecommerce_api.services;

import com.ange.ecommerce_api.daos.ProductRepository;
import com.ange.ecommerce_api.dtos.*;
import com.ange.ecommerce_api.interfaces.Product;
import com.ange.ecommerce_api.models.CategoryModel;
import com.ange.ecommerce_api.models.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements Product {

    private final ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MyMapperDTO myMapper;

    public ProductService(ProductRepository productRepo) {
        this.productRepository = productRepo;
    }


    @Override
    public List<ProductRequestDTO> getAllProducts() {
        return myMapper.productsListToDTO(productRepository.findAll());
    }

    @Override
    public ProductResponse getProductById(Long id) {
        ProductResponse response = new ProductResponse();
        Optional<ProductModel> product = productRepository.findById(id);
        if(product.isPresent()){
           response.setProduct(myMapper.productToDto(product.get()));
        }else{
            response.setErrorMessage("There is no product with id "+id);
        }
        return response;
    }

    @Override
    public ProductResponse createProduct(ProductCreateDTO productDTO) {
        CategoryResponse categoryResponse = categoryService.getCategoryById(productDTO.getCategory_id());
        if(categoryResponse.getCategory() == null){
            return new ProductResponse("Category with id "+productDTO.getCategory_id()+" does not exists");
        }

        String productName = productDTO.getName();
        ProductResponse response = new ProductResponse();

        // check that the name is not empty
        if (productName == null || productName.isEmpty()){
            response.setErrorMessage("Name should not be empty!");
            return response;
        }

        ProductModel product = new ProductModel();
        CategoryRespDTO cat = categoryResponse.getCategory();
        product.setName(productName);
        product.setCategory(new CategoryModel().of(cat.getId(), cat.getName(), cat.getDescription()));
        product.setUnitPrice(productDTO.getUnitPrice());
        product.setDescription(productDTO.getDescription());
        product.setActive(true);
        product.setDateCreated(Date.from(Instant.now()));
        product.setSku(productDTO.getSku());
        product.setImageUrl(productDTO.getImageUrl());
        product.setLastUpdated(Date.from(Instant.now()));

        // save it in db
        response.setProduct(myMapper.productToDto(productRepository.save(product)));
        return response;
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductCreateDTO productToUpdate) {
        // check that the category with given id is in DB
        Optional<ProductModel> existingProduct = productRepository.findById(id);

        ProductResponse response = new ProductResponse();

        if(existingProduct.isPresent()){
            ProductModel product = existingProduct.get();
            // update product infos
            product.setName(productToUpdate.getName());
            //product.setCategory(productToUpdate.getCategory());
            product.setUnitPrice(productToUpdate.getUnitPrice());
            product.setDescription(productToUpdate.getDescription());
            product.setActive(productToUpdate.isActive());
            product.setSku(productToUpdate.getSku());
            product.setImageUrl(productToUpdate.getImageUrl());
            product.setLastUpdated(Date.from(Instant.now()));

            // save it in db
            response.setProduct(myMapper.productToDto(productRepository.save(product)));
            return response;
        }

        return new ProductResponse("category does not exists");
    }

    @Override
    public String deleteProduct(Long id) {
        Optional<ProductModel> product = productRepository.findById(id);
        if(product.isPresent()){
            productRepository.deleteById(id);
            return "product with id "+id+" was successfully deleted";
        }
        return "No product with id "+id+" found!";
    }
}
