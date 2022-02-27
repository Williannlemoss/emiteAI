package com.emiteai.willian.services.impl;

import com.emiteai.willian.models.Purchase;
import com.emiteai.willian.models.TransportOrder;
import com.emiteai.willian.repositories.PurchaseRepository;
import com.emiteai.willian.repositories.TransportOrderRepository;
import com.emiteai.willian.services.TransportOrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@EnableScheduling
public class TransportORderServiceImpl implements TransportOrderService {

    Logger logger = LoggerFactory.getLogger(TransportORderServiceImpl.class);

    private final TransportOrderRepository transportOrderRepository;

//    private final PurchaseRepository purchaseRepository;

    @Override
    public List<TransportOrder> getAllTransportOrder(){
        this.teste();
        return this.transportOrderRepository.findAll();
    }

    @Override
    public TransportOrder getTransportOrder(Long id){
        return this.transportOrderRepository.findById(id).orElseThrow();
    }

    @Override
    public TransportOrder saveTransportOrder(TransportOrder transportOrder){
        return this.transportOrderRepository.save(transportOrder);
    }

    @Override
    public TransportOrder updateTransportOrder(Long id, TransportOrder transportOrder) {
        return this.transportOrderRepository.save(transportOrder);
    }

    @Override
    public ResponseEntity<Void> deleteTransportOrder(Long id) {
        TransportOrder purchase = this.getTransportOrder(id);
        this.transportOrderRepository.deleteById(purchase.getId());
        return ResponseEntity.noContent().build();
    }

    @Scheduled(fixedDelay = 1000)
    private void teste(){
//        List<Purchase> purchaseList = this.purchaseRepository.findByIsSent();
//        for (Purchase purchase: purchaseList) {
//            this.logger.info(purchase.toString());
//        }
        this.logger.error("errorrr");

    }

}
