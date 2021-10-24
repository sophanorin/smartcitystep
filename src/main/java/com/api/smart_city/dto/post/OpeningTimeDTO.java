package com.api.smart_city.dto.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OpeningTimeDTO {
    private Long id;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime from;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime to;

    public OpeningTimeDTO() {
    }

    public OpeningTimeDTO(Long id, LocalDateTime from, LocalDateTime to) {
        this.id = id;
        this.from = from;
        this.to = to;
    }
}
