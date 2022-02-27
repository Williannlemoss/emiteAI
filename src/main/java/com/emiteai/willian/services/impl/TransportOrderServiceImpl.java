package com.emiteai.willian.services.impl;

import com.emiteai.willian.dto.request.TransportOrderSaveDTO;
import com.emiteai.willian.dto.response.TransportOrderDTO;
import com.emiteai.willian.exceptions.GlobalException;
import com.emiteai.willian.models.Purchase;
import com.emiteai.willian.models.TransportOrder;
import com.emiteai.willian.repositories.PurchaseRepository;
import com.emiteai.willian.repositories.TransportOrderRepository;
import com.emiteai.willian.services.TransportOrderService;
import com.emiteai.willian.utils.TransportOrderConverter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@EnableScheduling
public class TransportOrderServiceImpl implements TransportOrderService {

    Logger logger = LoggerFactory.getLogger(TransportOrderServiceImpl.class);

    private final TransportOrderRepository transportOrderRepository;

    private final PurchaseRepository purchaseRepository;

    private final TransportOrderConverter transportOrderConverter;

    @Override
    public List<TransportOrderDTO> getAllTransportOrder(){
        List<TransportOrder> transportOrders = this.transportOrderRepository.findAll();

        if (transportOrders.isEmpty()){
            throw new GlobalException("Lista vazia", HttpStatus.NO_CONTENT);
        }

        return this.transportOrderConverter.toCollectionDTO(transportOrders);
    }

    @Override
    public TransportOrderDTO getTransportOrder(Long id){
        return this.transportOrderConverter.toDTO(this.transportOrderRepository.findById(id).orElseThrow(() -> new GlobalException("Lista de pedidos não encontrada", HttpStatus.NOT_FOUND)));
    }

    @Override
    public TransportOrderDTO saveTransportOrder(TransportOrderSaveDTO transportOrder){
        TransportOrder transportOrderToBeCreated = this.transportOrderRepository.save(new TransportOrder());

        for ( Long id: transportOrder.getProductsIds()) {
            logger.error("" + id);
            Purchase purchase = this.purchaseRepository.findById(id).orElseThrow();
            purchase.setTransportOrder(transportOrderToBeCreated);
            logger.error("id" + purchase.getId());
            this.purchaseRepository.save(purchase);
        }

        return this.transportOrderConverter.toDTO(this.transportOrderRepository.save(transportOrderToBeCreated));
    }

    @Override
    public TransportOrder updateTransportOrder(Long id, TransportOrder transportOrder) {
        return this.transportOrderRepository.save(transportOrder);
    }

    @Override
    public ResponseEntity<Void> deleteTransportOrder(Long id) {
        TransportOrder transportOrder = this.transportOrderRepository.findById(id).orElseThrow();
        this.transportOrderRepository.deleteById(transportOrder.getId());
        return ResponseEntity.noContent().build();
    }

    @Scheduled(fixedDelay = 10000)
    private void teste(){
        List<Purchase> purchaseList = this.purchaseRepository.findByIsSentIsFalse();
        List<Long> listId = new ArrayList<>();

        if (!purchaseList.isEmpty()){
            for (Purchase purchase: purchaseList) {
                logger.error("scheduling" + purchase.getId());
                listId.add(purchase.getId());
                purchase.setIsSent(true);
                this.purchaseRepository.save(purchase);
            }
            this.saveTransportOrder(new TransportOrderSaveDTO(listId));
            }
    }

}