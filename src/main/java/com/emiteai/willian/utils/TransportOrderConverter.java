package com.emiteai.willian.utils;

import com.emiteai.willian.dto.request.TransportOrderPutDTO;
import com.emiteai.willian.dto.request.TransportOrderSaveDTO;
import com.emiteai.willian.dto.response.TransportOrderDTO;
import com.emiteai.willian.models.TransportOrder;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {PurchaseConverter.class})
public interface TransportOrderConverter {

    TransportOrder toModelSave(TransportOrderSaveDTO transportOrderSaveDTO);
    TransportOrder toModelPut(TransportOrderPutDTO transportOrderPutDTO);

    TransportOrderDTO toDTO(TransportOrder TransportOrder);

    List<TransportOrderDTO> toCollectionDTO(List<TransportOrder> transportOrders);

}
