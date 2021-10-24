package com.api.smart_city.dto.post;

import com.api.smart_city.model.*;
import com.api.smart_city.model.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Data
public class PostDTO {
    private Long id;
    private String title;
    private String description;
    private String email;
    private String phoneNumber;
    private Double rating;
    private Integer viewNumber;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Boolean claimed = false;
    private LocationDTO location;
    private UserDTO owner;
    private Collection<OpeningTimeDTO> openingTimes = new ArrayList<>();
    private Collection<CategoryDTO> categories = new ArrayList<>();
    private Collection<AttachmentDTO> attachments = new ArrayList<>();
    private Collection<FeedbackDTO> feedbacks = new ArrayList<>();
    private Collection<CommentDTO> comments = new ArrayList<>();
    private Collection<FeatureDTO> features = new ArrayList<>();
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate createdAt;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate updatedAt;

    public PostDTO(PostDTO post) {
    }

    public PostDTO(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.description = post.getDescription();
        this.email = post.getEmail();
        this.phoneNumber = post.getPhoneNumber();
        this.rating = post.getRating();
        this.viewNumber = post.getViewNumber();
        this.status = post.getStatus();
        this.claimed = post.getClaimed();
        this.owner = new UserDTO();
        this.owner.setId(post.getOwner().getId());
        this.owner.setFirstName(post.getOwner().getFirstName());
        this.owner.setLastName(post.getOwner().getLastName());
        this.owner.setUsername(post.getOwner().getUsername());
        this.owner.setUsername(post.getOwner().getUsername());
        this.createdAt = post.getCreatedAt();
        this.updatedAt = post.getUpdatedAt();
        this.location = new LocationDTO(
                post.getLocation().getId(),
                post.getLocation().getMapb_location(),
                new CityDTO(
                        post.getCity().getId(),
                        post.getCity().getName())
        );

        post.getOpeningTimes().forEach(time -> {
            this.openingTimes.add(new OpeningTimeDTO(time.getId(),time.getFromTime(),time.getToTime()));
        });


        post.getFeatures().forEach(feature -> {
            this.features.add(new FeatureDTO(feature.getId(),feature.getFeature()));
        });

        post.getCategories().forEach(category -> {
            this.categories.add(new CategoryDTO(category.getId(),category.getCategory()));
        });

        post.getAttachments().forEach(attachment -> {
            this.attachments.add(
                    new AttachmentDTO(
                            attachment.getId(),
                            attachment.getTitle(),
                            attachment.getUrl())
            );
        });

        post.getComments().forEach(comment -> {
            UserDTO user = new UserDTO(
                    comment.getCommentBy().getId(),
                    comment.getCommentBy().getFirstName(),
                    comment.getCommentBy().getLastName(),
                    comment.getCommentBy().getUsername()
            );

            this.comments.add(new CommentDTO(comment.getId(),comment.getComment(),user));
        });

        post.getFeedbacks().forEach(feedback -> {
            UserDTO user = new UserDTO(
                    feedback.getFeedbackBy().getId(),
                    feedback.getFeedbackBy().getFirstName(),
                    feedback.getFeedbackBy().getLastName(),
                    feedback.getFeedbackBy().getUsername()
            );
            this.feedbacks.add(new FeedbackDTO(feedback.getId(),feedback.getFeedback(),user));
        });
    }
}
