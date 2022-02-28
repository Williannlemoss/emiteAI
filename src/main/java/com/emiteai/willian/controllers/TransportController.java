package com.emiteai.willian.controllers;

import com.emiteai.willian.dto.request.TransportPutDTO;
import com.emiteai.willian.dto.request.TransportSaveDTO;
import com.emiteai.willian.dto.response.TransportDTO;
import com.emiteai.willian.models.Transport;
import com.emiteai.willian.services.TransportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transport")
@RequiredArgsConstructor
public class TransportController {

    private final TransportService transportService;

    @GetMapping
    public List<TransportDTO> getTransport(){
        return transportService.getAllTransport();
    }

    @GetMapping(value = "/{transportId}")
    public TransportDTO getTransport(@PathVariable Long transportId){
        return this.transportService.getTransport(transportId);
    }

    @PutMapping(value = "/{transportId}")
    public TransportDTO updateTransport(@PathVariable Long transportId, @RequestBody TransportPutDTO transport){
        return this.transportService.updateTransport(transportId, transport);
    }

    @PostMapping
    public TransportDTO saveTransport(@RequestBody TransportSaveDTO transport){
        return this.transportService.saveTransport(transport);
    }

    @DeleteMapping(value = "/{transportId}")
    public ResponseEntity<Void> deleteTransport(@PathVariable Long transportId){
        return this.transportService.deleteTransport(transportId);
    }
}