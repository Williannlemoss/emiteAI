package com.emiteai.willian.controllers;

import com.emiteai.willian.dto.request.TransportOrderSaveDTO;
import com.emiteai.willian.dto.response.TransportOrderDTO;
import com.emiteai.willian.models.TransportOrder;
import com.emiteai.willian.services.TransportOrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transportOrder")
@RequiredArgsConstructor
public class TransportOrderController {

    private final TransportOrderService transportOrderService;

    @GetMapping
    public List<TransportOrderDTO> getTransportOrder(){
        return transportOrderService.getAllTransportOrder();
    }

    @GetMapping(value = "/{transportOrderId}")
    public TransportOrderDTO getTransportOrder(@PathVariable Long transportOrderId){
        return this.transportOrderService.getTransportOrder(transportOrderId);
    }

    @PutMapping(value = "/{transportOrderId}")
    public TransportOrder updateTransportOrder(@PathVariable Long transportOrderId, @RequestBody TransportOrder transportOrder){
        return this.transportOrderService.updateTransportOrder(transportOrderId, transportOrder);
    }

    @PostMapping
    public TransportOrderDTO saveTransportOrder(@RequestBody TransportOrderSaveDTO transportOrder){
        return this.transportOrderService.saveTransportOrder(transportOrder);
    }

    @DeleteMapping(value = "/{transportOrderId}")
    public ResponseEntity<Void> deleteTransportOrder(@PathVariable Long transportOrderId){
        return this.transportOrderService.deleteTransportOrder(transportOrderId);
    }
}