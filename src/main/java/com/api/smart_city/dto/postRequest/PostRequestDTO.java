package com.api.smart_city.dto.postRequest;

import com.api.smart_city.model.enums.Status;
import com.api.smart_city.dto.post.*;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.ArrayList;
import java.util.Collection;

@Data
public class PostRequestDTO {
    private Long id;
    private String title;
    private String description;
    private String email;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Boolean claimed = false;
    private LocationRequestDTO location;
    private Long ownerId;
    private Collection<OpeningTimeDTO> openingTimes = new ArrayList<>();
    private Collection<Long> categories = new ArrayList<>();
    private Collection<Long> features = new ArrayList<>();
    private Collection<AttachmentDTO> attachments = new ArrayList<>();

    @Override
    public String toString() {
        return "PostRequestDTO{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", status=" + status +
                ", claimed=" + claimed +
                '}';
    }
}
