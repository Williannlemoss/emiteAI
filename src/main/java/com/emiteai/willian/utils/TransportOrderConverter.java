package com.emiteai.willian.utils;

import com.emiteai.willian.dto.request.PurchasePutDTO;
import com.emiteai.willian.dto.request.PurchaseSaveDTO;
import com.emiteai.willian.dto.request.TransportOrderPutDTO;
import com.emiteai.willian.dto.request.TransportOrderSaveDTO;
import com.emiteai.willian.dto.response.PurchaseDTO;
import com.emiteai.willian.dto.response.TransportOrderDTO;
import com.emiteai.willian.models.Purchase;
import com.emiteai.willian.models.TransportOrder;
import com.emiteai.willian.models.TransportOrder_;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransportOrderConverter {

    TransportOrder toModelSave(TransportOrderSaveDTO transportOrderSaveDTO);
    TransportOrder toModelPut(TransportOrderPutDTO transportOrderPutDTO);

    TransportOrderDTO toDTO(TransportOrder TransportOrder);

    List<TransportOrderDTO> toCollectionDTO(List<TransportOrder> transportOrders);

}
