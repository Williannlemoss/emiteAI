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
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
@Log4j2
public class TransportOrderServiceImpl implements TransportOrderService {

    private final TransportOrderRepository transportOrderRepository;
    private final PurchaseRepository purchaseRepository;
    private final TransportOrderConverter transportOrderConverter;

    @Override
    public List<TransportOrderDTO> getAllTransportOrder(){
        List<TransportOrder> transportOrders = transportOrderRepository.findAll();

        if (transportOrders.isEmpty()){
            throw new GlobalException("Ordem de serviço vazia", HttpStatus.BAD_REQUEST);
        }

        return this.transportOrderConverter.toCollectionDTO(transportOrders);
    }

    private TransportOrder getById(Long id) {
        return transportOrderRepository.findById(id).orElseThrow(() -> new GlobalException("Ordem de serviço não encontrada", HttpStatus.NOT_FOUND));
    }

    @Override
    public TransportOrderDTO getTransportOrder(Long id){
        return transportOrderConverter.toDTO(getById(id));
    }

    @Override
    public TransportOrderDTO saveTransportOrder(TransportOrderSaveDTO transportOrder){
        TransportOrder transportOrderToBeCreated = transportOrderRepository.save(new TransportOrder());

        for ( Long id: transportOrder.getPurchaseIds()) {
            Optional<Purchase> purchase = purchaseRepository.findById(id);

            if(purchase.isEmpty()){
                deleteTransportOrder(transportOrderToBeCreated.getId());
                throw new GlobalException("Lista de produtos não encontrada", HttpStatus.NOT_FOUND);
            }

            purchase.get().setTransportOrder(transportOrderToBeCreated);
            purchaseRepository.save(purchase.get());
        }

        return transportOrderConverter
            .toDTO(transportOrderRepository.save(transportOrderToBeCreated));
    }

    @Override
    public TransportOrder updateTransportOrder(Long id, TransportOrder transportOrder) {
        return this.transportOrderRepository.save(transportOrder);
    }

    @Override
    public ResponseEntity<Void> deleteTransportOrder(Long id) {
        TransportOrder transportOrder = getById(id);
        transportOrderRepository.deleteById(transportOrder.getId());
        return ResponseEntity.noContent().build();
    }

    @Scheduled(fixedDelay = 600000)
    private void scheduledSendOrder(){
        List<Purchase> purchaseList = this.purchaseRepository.findByIsSentIsFalse();
        List<Long> listId = new ArrayList<>();

        if (!purchaseList.isEmpty()){
            for (Purchase purchase: purchaseList) {
                listId.add(purchase.getId());
                purchase.setIsSent(true);
                this.purchaseRepository.save(purchase);
            }
            this.saveTransportOrder(new TransportOrderSaveDTO(listId));
            }
    }

}