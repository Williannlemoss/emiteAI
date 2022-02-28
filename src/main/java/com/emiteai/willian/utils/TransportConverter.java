package com.emiteai.willian.utils;

import com.emiteai.willian.dto.request.TransportPutDTO;
import com.emiteai.willian.dto.request.TransportSaveDTO;
import com.emiteai.willian.dto.response.TransportDTO;
import com.emiteai.willian.dto.response.TransportOrderDTO;
import com.emiteai.willian.models.Transport;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TransportOrderConverter.class, PurchaseConverter.class})
public interface TransportConverter {

    Transport toModelSave(TransportSaveDTO transportSaveDTO);

    Transport toModelPut(TransportPutDTO transportPutDTO);

    TransportDTO toDTO(Transport Transport);

    List<TransportDTO> toCollectionDTO(List<Transport> transports);

}
