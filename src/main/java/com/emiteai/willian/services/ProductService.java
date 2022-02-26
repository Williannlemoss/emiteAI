package com.emiteai.willian.services;

import com.emiteai.willian.models.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {

    List<Product> getAllProduct();

    Product getProduct(Long id);

    Product saveProduct(Product product);

    Product updateProduct(Long id, Product product);

    ResponseEntity<Void> deleteProduct(Long id);

}
