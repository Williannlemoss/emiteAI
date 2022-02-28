package com.emiteai.willian.services;

import com.emiteai.willian.dto.request.TransportPutDTO;
import com.emiteai.willian.dto.request.TransportSaveDTO;
import com.emiteai.willian.dto.response.TransportDTO;
import com.emiteai.willian.models.Transport;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TransportService {

    List<TransportDTO> getAllTransport();

    TransportDTO getTransport(Long id);

    TransportDTO saveTransport(TransportSaveDTO transportSaveDTO);

    TransportDTO updateTransport(Long id, TransportPutDTO transport);

    ResponseEntity<Void> deleteTransport(Long id);

}