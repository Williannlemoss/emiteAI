package com.emiteai.willian.services;

import com.emiteai.willian.models.TransportOrder;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TransportOrderService {

    List<TransportOrder> getAllTransportOrder();

    TransportOrder getTransportOrder(Long id);

    TransportOrder saveTransportOrder(TransportOrder purchase);

    TransportOrder updateTransportOrder(Long id, TransportOrder purchase);

    ResponseEntity<Void> deleteTransportOrder(Long id);

}
