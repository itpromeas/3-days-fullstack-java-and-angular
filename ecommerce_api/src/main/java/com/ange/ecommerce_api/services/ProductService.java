package com.ange.ecommerce_api.services;

import com.ange.ecommerce_api.daos.ProductRepository;
import com.ange.ecommerce_api.dtos.CategoryDTO;
import com.ange.ecommerce_api.dtos.CategoryResponse;
import com.ange.ecommerce_api.dtos.ProductDTO;
import com.ange.ecommerce_api.dtos.ProductResponse;
import com.ange.ecommerce_api.interfaces.Product;
import com.ange.ecommerce_api.models.CategoryModel;
import com.ange.ecommerce_api.models.ProductModel;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements Product {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepo) {
        this.productRepository = productRepo;
    }


    @Override
    public List<ProductModel> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public ProductResponse getProductById(Long id) {
        ProductResponse response = new ProductResponse();
        Optional<ProductModel> product = productRepository.findById(id);
        if(product.isPresent()){
            response.setProduct(product.get());
        }else{
            response.setErrorMessage("There is no product with id "+id);
        }
        return response;
    }

    @Override
    public ProductResponse createProduct(ProductDTO productDTO) {
        String productName = productDTO.getName();
        ProductResponse response = new ProductResponse();

        // check that the name is not empty
        if (productName == null || productName.isEmpty()){
            response.setErrorMessage("Name should not be empty!");
            return response;
        }

        ProductModel product = new ProductModel();
        product.setName(productName);
        product.setCategory(product.getCategory());
        product.setUnitPrice(productDTO.getUnitPrice());
        product.setDescription(productDTO.getDescription());
        product.setActive(true);
        product.setDateCreated(Date.from(Instant.now()));
        product.setSku(productDTO.getSku());
        product.setImageUrl(productDTO.getImageUrl());
        product.setLastUpdated(Date.from(Instant.now()));

        // save it in db
        response.setProduct(productRepository.save(product));
        return response;
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductModel productModel) {
        // check that the category with given id is in DB
        Optional<ProductModel> existingProduct = productRepository.findById(id);

        ProductResponse response = new ProductResponse();

        if(existingProduct.isPresent()){
            ProductModel product = existingProduct.get();
            // update product infos
            product.setName(productModel.getName());
            product.setCategory(productModel.getCategory());
            product.setUnitPrice(productModel.getUnitPrice());
            product.setDescription(productModel.getDescription());
            product.setActive(productModel.isActive());
            product.setDateCreated(productModel.getDateCreated());
            product.setSku(productModel.getSku());
            product.setImageUrl(productModel.getImageUrl());
            product.setLastUpdated(Date.from(Instant.now()));

            // save it in db
            response.setProduct(productRepository.save(product));
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
