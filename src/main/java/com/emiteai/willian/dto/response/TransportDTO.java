package com.emiteai.willian.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransportDTO {

    private Long id;
    private List<TransportOrderDTO> transportOrderDTOS = new ArrayList<>();
    private Boolean isSent;
    private LocalDateTime tripCompleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
