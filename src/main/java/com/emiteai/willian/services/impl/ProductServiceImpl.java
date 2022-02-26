package com.emiteai.willian.services.impl;

import com.emiteai.willian.models.Product;
import com.emiteai.willian.repositories.ProductRepository;
import com.emiteai.willian.services.ProductService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getAllProduct(){
        return this.productRepository.findAll();
    }

    @Override
    public Product getProduct(Long id){
        return this.productRepository.findById(id).orElseThrow();
    }

    @Override
    public Product saveProduct(Product product){
        return this.productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public ResponseEntity<Void> deleteProduct(Long id) {
        Product product = this.getProduct(id);
        this.productRepository.deleteById(product.getId());
        return ResponseEntity.noContent().build();
    }


}
