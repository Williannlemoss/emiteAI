package com.emiteai.willian.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Problem {
    private Integer status;
    private LocalDateTime dateTime;
    private String title;
    private List<Fields> fields;
}
