package com.api.smart_city.dto.post;


import lombok.Data;

@Data
public class FeedbackDTO {
    private Long id;
    private String feedback;
    private UserDTO feedbackBy;

    public FeedbackDTO() {
    }

    public FeedbackDTO(Long id, String feedback, UserDTO feedbackBy) {
        this.id = id;
        this.feedback = feedback;
        this.feedbackBy = feedbackBy;
    }
}
