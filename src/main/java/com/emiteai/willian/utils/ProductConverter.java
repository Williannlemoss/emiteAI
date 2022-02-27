package com.emiteai.willian.utils;

import com.emiteai.willian.dto.request.ProductPutDTO;
import com.emiteai.willian.dto.request.ProductSaveDTO;
import com.emiteai.willian.dto.response.ProductDTO;
import com.emiteai.willian.dto.response.TransportOrderDTO;
import com.emiteai.willian.models.Product;
import com.emiteai.willian.models.TransportOrder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductConverter {

    Product toModelPut(ProductPutDTO productPutDTO);
    Product toModelSave(ProductSaveDTO productSaveDTO);

    ProductDTO toDto(Product product);
    List<ProductDTO> toCollectionDTO(List<Product> products);
}
