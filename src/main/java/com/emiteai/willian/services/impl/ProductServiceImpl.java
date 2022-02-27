package com.emiteai.willian.services.impl;

import com.emiteai.willian.dto.request.ProductPutDTO;
import com.emiteai.willian.dto.request.ProductSaveDTO;
import com.emiteai.willian.dto.response.ProductDTO;
import com.emiteai.willian.exceptions.GlobalException;
import com.emiteai.willian.models.Product;
import com.emiteai.willian.repositories.ProductRepository;
import com.emiteai.willian.services.ProductService;
import com.emiteai.willian.utils.ProductConverter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    @Override
    public List<ProductDTO> getAllProduct(){
        List<Product> productList = productRepository.findAll();

        if(productList.isEmpty()){
            throw new GlobalException("A lista está vazia", HttpStatus.BAD_REQUEST);
        }
        return productConverter.toCollectionDTO(productList);
    }

    @Override
    public ProductDTO getProduct(Long id){
        return this.productConverter.toDto(this.productRepository.findById(id).orElseThrow(() -> new GlobalException("Produto não encontrado", HttpStatus.NOT_FOUND)));
    }

    @Override
    public Product getById(Long id){
        return productRepository.findById(id).orElseThrow(() -> new GlobalException("Produto não encontrado", HttpStatus.NOT_FOUND));
    }

    @Override
    public ProductDTO saveProduct(ProductSaveDTO product){
        return productConverter.toDto(this.productRepository.save(productConverter.toModelSave(product)));
    }

    @Override
    public Product updateProduct(Long id, ProductPutDTO product) {

        Product productExistent = getById(id);

        if(product.getName() != null && product.getPrice() != null) {
            productExistent.setName(product.getName());
            productExistent.setPrice(product.getPrice());
        }

        return this.productRepository.save(productExistent);
    }

    @Override
    public ResponseEntity<Void> deleteProduct(Long id) {
        Product product = getById(id);
        this.productRepository.deleteById(product.getId());
        return ResponseEntity.noContent().build();
    }


}
