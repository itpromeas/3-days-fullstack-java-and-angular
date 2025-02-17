package com.ange.ecommerce_api.controllers;

import com.ange.ecommerce_api.dtos.ProductCreateDTO;
import com.ange.ecommerce_api.dtos.ProductRequestDTO;
import com.ange.ecommerce_api.dtos.ProductResponse;
import com.ange.ecommerce_api.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public List<ProductRequestDTO> getAll()  {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getCategoryById(@PathVariable Long id)  {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ProductResponse> create(@RequestBody ProductCreateDTO product)  {
        ProductResponse response = productService.createProduct(product);
        if(response.getProduct() == null){
            new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable Long id, @RequestBody ProductCreateDTO product)  {
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id)  {
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
    }
}
