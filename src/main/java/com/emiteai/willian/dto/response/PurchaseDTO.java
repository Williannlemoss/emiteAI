package com.emiteai.willian.dto.response;

import com.emiteai.willian.dto.request.ProductSaveDTO;
import com.emiteai.willian.models.Product;
import com.emiteai.willian.models.TransportOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDTO {

    private Long id;

    private List<ProductDTO> productList = new ArrayList<>();
    private Double totalOrderAmount;
    private Boolean isSent;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
