package com.ange.ecommerce_api.interfaces;

import com.ange.ecommerce_api.dtos.CategoryDTO;
import com.ange.ecommerce_api.dtos.ProductDTO;
import com.ange.ecommerce_api.dtos.ProductResponse;
import com.ange.ecommerce_api.models.CategoryModel;
import com.ange.ecommerce_api.models.ProductModel;

import java.util.List;

public interface Product {
    /***
     * get all products
     * @return a list of products
     */
    List<ProductModel> getAllProducts();

    /***
     * get a product by id
     * @param id: an integer representing a product id
     * @return a product
     */
    ProductResponse getProductById(Long id);

    /***
     * create a product
     * @param productDTO: product params
     */
    ProductResponse createProduct(ProductDTO productDTO);

    /***
     * update a given product
     * @param id: an integer
     * @param productModel: product model
     * @return a product
     */
    ProductResponse updateProduct(Long id, ProductModel productModel);

    /***
     * delete a product
     * @param id: product id
     * @return a message
     */
    String deleteProduct(Long id);

}
