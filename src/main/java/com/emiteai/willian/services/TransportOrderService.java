package com.emiteai.willian.services;

import com.emiteai.willian.dto.request.TransportOrderSaveDTO;
import com.emiteai.willian.dto.response.TransportOrderDTO;
import com.emiteai.willian.models.TransportOrder;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TransportOrderService {

    List<TransportOrderDTO> getAllTransportOrder();

    TransportOrderDTO getTransportOrder(Long id);

    TransportOrderDTO saveTransportOrder(TransportOrderSaveDTO purchase);

    TransportOrder updateTransportOrder(Long id, TransportOrder purchase);

    ResponseEntity<Void> deleteTransportOrder(Long id);

}