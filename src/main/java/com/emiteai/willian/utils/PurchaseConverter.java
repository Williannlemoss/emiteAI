package com.emiteai.willian.utils;

import com.emiteai.willian.dto.request.PurchasePutDTO;
import com.emiteai.willian.dto.request.PurchaseSaveDTO;
import com.emiteai.willian.dto.response.PurchaseDTO;
import com.emiteai.willian.models.Purchase;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PurchaseConverter {

    Purchase toModelSave(PurchaseSaveDTO purchaseSaveDTO);
    Purchase toModelPut(PurchasePutDTO purchasePutDTO);

    PurchaseDTO toDTO(Purchase purchase);

    List<PurchaseDTO> toCollectionDTO(List<Purchase> purchaseList);

}
