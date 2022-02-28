package com.emiteai.willian.dto.request;

import com.emiteai.willian.models.TransportOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransportPutDTO {

    private Long id;

    private List<Long> transportOrderIds = new ArrayList<>();

    private Boolean isSent;

    private LocalDateTime tripCompleted;
}
