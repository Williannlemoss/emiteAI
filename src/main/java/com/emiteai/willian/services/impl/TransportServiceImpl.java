package com.emiteai.willian.services.impl;

import com.emiteai.willian.dto.request.TransportPutDTO;
import com.emiteai.willian.dto.request.TransportSaveDTO;
import com.emiteai.willian.dto.response.TransportDTO;
import com.emiteai.willian.exceptions.GlobalException;
import com.emiteai.willian.models.Transport;
import com.emiteai.willian.models.TransportOrder;
import com.emiteai.willian.repositories.TransportOrderRepository;
import com.emiteai.willian.repositories.TransportRepository;
import com.emiteai.willian.services.TransportService;
import com.emiteai.willian.utils.TransportConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@EnableScheduling
@Log4j2
public class TransportServiceImpl implements TransportService {

    Logger logger = LoggerFactory.getLogger(TransportServiceImpl.class);

    private final TransportRepository transportRepository;
    private final TransportOrderRepository transportOrderRepository;
    private final TransportConverter transportConverter;

    @Override
    public List<TransportDTO> getAllTransport(){
        List<Transport> transports = transportRepository.findAll();

        if (transports.isEmpty()){
            throw new GlobalException("Ordem de serviço vazia", HttpStatus.BAD_REQUEST);
        }

        return this.transportConverter.toCollectionDTO(transports);
    }

    private Transport getById(Long id) {
        return transportRepository.findById(id).orElseThrow(() -> new GlobalException("Ordem de serviço não encontrada", HttpStatus.NOT_FOUND));
    }

    @Override
    public TransportDTO getTransport(Long id){
        return transportConverter.toDTO(getById(id));
    }

    @Override
    public TransportDTO saveTransport(TransportSaveDTO transport){
        Transport transportToBeCreated = transportRepository.save(new Transport());
        return transportConverter.toDTO(transportToBeCreated);
    }

    @Override
    public TransportDTO updateTransport(Long id, TransportPutDTO transport) {
        logger.error("aqui 222");
        logger.error("id");
        Transport transportToBeUpdated = transportRepository.findById(id).orElseThrow();
        List<TransportOrder> transportOrders = new ArrayList<>();

        for ( Long ids: transport.getTransportOrderIds()) {
            Optional<TransportOrder> transportOrder = transportOrderRepository.findById(ids);

            if(transportOrder.isEmpty()){
                deleteTransport(transportToBeUpdated.getId());
                throw new GlobalException("Lista de produtos não encontrada", HttpStatus.NOT_FOUND);
            }

            transportOrder.get().setTransport(transportToBeUpdated);
            transportOrderRepository.save(transportOrder.get());
            transportOrders.add(transportOrder.get());
        }

        if (transport.getId() != null && transport.getTransportOrderIds() != null && transport.getIsSent() != null && transport.getTripCompleted() != null){
            transportToBeUpdated.setId(transport.getId());
            transportToBeUpdated.setIsSent(transport.getIsSent());
            transportToBeUpdated.setTripCompleted(transport.getTripCompleted());
            transportToBeUpdated.setTransportOrdersList(transportOrders);
        }

        return transportConverter
                .toDTO(transportRepository.save(transportToBeUpdated));
    }

    @Override
    public ResponseEntity<Void> deleteTransport(Long id) {
        Transport transport = getById(id);
        transportRepository.deleteById(transport.getId());
        return ResponseEntity.noContent().build();
    }

    @Scheduled(fixedDelay = 600000)
    private void scheduledSend(){
        List<TransportOrder> transportOrders = this.transportOrderRepository.findByIsSentIsFalse();
        List<Transport> transport = this.transportRepository.findByIsSentIsFalse();

        List<Long> listId = new ArrayList<>();

        if (!transportOrders.isEmpty()){
            for (TransportOrder transportOrder: transportOrders) {
                listId.add(transportOrder.getId());
                transportOrder.setIsSent(true);
                this.transportOrderRepository.save(transportOrder);
            }

            logger.info(transport.get(0).getId() + " dois");
            TransportPutDTO transportPutDTO = new TransportPutDTO(transport.get(0).getId(), listId, true, LocalDateTime.now());
            this.updateTransport(transport.get(0).getId(), transportPutDTO);
            }
    }

}