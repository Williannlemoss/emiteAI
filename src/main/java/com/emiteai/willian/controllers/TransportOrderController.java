package com.emiteai.willian.controllers;

import com.emiteai.willian.models.TransportOrder;
import com.emiteai.willian.services.TransportOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transportOrder")
@RequiredArgsConstructor
public class TransportOrderController {

    private final TransportOrderService transportOrderService;

    @GetMapping
    public List<TransportOrder> getTransportOrder(){
        return transportOrderService.getAllTransportOrder();
    }

    @GetMapping(value = "/{transportOrderId}")
    public TransportOrder getTransportOrder(@PathVariable Long transportOrderId){
        return this.transportOrderService.getTransportOrder(transportOrderId);
    }

    @PutMapping(value = "/{transportOrderId}")
    public TransportOrder updateTransportOrder(@PathVariable Long transportOrderId, @RequestBody TransportOrder transportOrder){
        return this.transportOrderService.updateTransportOrder(transportOrderId, transportOrder);
    }

    @PostMapping
    public TransportOrder saveTransportOrder(@RequestBody TransportOrder transportOrder){
        return this.transportOrderService.saveTransportOrder(transportOrder);
    }

    @DeleteMapping(value = "/{transportOrderId}")
    public ResponseEntity<Void> deleteTransportOrder(@PathVariable Long transportOrderId){
        return this.transportOrderService.deleteTransportOrder(transportOrderId);
    }
}
