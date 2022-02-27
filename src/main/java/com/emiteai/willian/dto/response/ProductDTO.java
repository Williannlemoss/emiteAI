package com.emiteai.willian.dto.response;

import com.emiteai.willian.dto.request.ProductSaveDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long id;

    private String name;

    private Double price;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
