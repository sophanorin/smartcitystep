package com.api.smart_city.dto.post;

import lombok.Data;

@Data
public class AttachmentDTO {
    private Long id;
    private String title;
    private String url;

    public AttachmentDTO() {
    }

    public AttachmentDTO(Long id, String title, String url) {
        this.id = id;
        this.title = title;
        this.url = url;
    }
}
