package com.emiteai.willian.controllers;

import com.emiteai.willian.models.Product;
import com.emiteai.willian.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<Product> getProduct(){
        return productService.getAllProduct();
    }

    @GetMapping(value = "/{productId}")
    public Product getProduct(@PathVariable Long productId){
        return this.productService.getProduct(productId);
    }

    @PutMapping(value = "/{productId}")
    public Product updateProduct(@PathVariable Long productId, @RequestBody Product product){
        return this.productService.updateProduct(productId, product);
    }

    @PostMapping
    public Product saveProduct(@RequestBody Product product){
        return this.productService.saveProduct(product);
    }

    @DeleteMapping(value = "/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId){
        return this.productService.deleteProduct(productId);
    }
}
