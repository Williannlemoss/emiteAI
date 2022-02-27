package com.emiteai.willian.services;

import com.emiteai.willian.dto.request.ProductPutDTO;
import com.emiteai.willian.dto.request.ProductSaveDTO;
import com.emiteai.willian.dto.response.ProductDTO;
import com.emiteai.willian.models.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getAllProduct();

    Product getById(Long id);

    ProductDTO getProduct(Long id);

    ProductDTO saveProduct(ProductSaveDTO product);

    Product updateProduct(Long id, ProductPutDTO product);

    ResponseEntity<Void> deleteProduct(Long id);



}
