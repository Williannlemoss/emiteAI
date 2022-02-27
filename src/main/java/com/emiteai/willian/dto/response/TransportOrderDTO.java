package com.emiteai.willian.dto.response;

import com.emiteai.willian.dto.request.ProductSaveDTO;
import com.emiteai.willian.dto.request.PurchaseSaveDTO;
import com.emiteai.willian.models.Purchase;
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
public class TransportOrderDTO {

    private Long id;

    private List<PurchaseDTO> purchaseList = new ArrayList<>();

    private Boolean isSent;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
