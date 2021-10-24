package com.api.smart_city.dto.post;

import lombok.Data;

@Data
public class CommentDTO {
    private Long id;
    private String comment;
    private UserDTO commentBy;

    public CommentDTO() {
    }

    public CommentDTO(Long id, String comment, UserDTO commentBy) {
        this.id = id;
        this.comment = comment;
        this.commentBy = commentBy;
    }
}
