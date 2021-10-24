package com.api.smart_city.model;

import com.api.smart_city.model.enums.Status;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private String email;
    private String phoneNumber;
    private Double rating = 0.0;
    private Integer viewNumber = 0;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Boolean claimed = false;
    private Boolean isDeleted = false;

    @OneToOne(mappedBy = "post",cascade = CascadeType.ALL)
    private Location location;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private Collection<OpeningTime> openingTimes = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.REMOVE})
    private City city;

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE},optional = true)
    private User owner;

    @ManyToMany(mappedBy = "posts",cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @Column(nullable = true)
    private Collection<Feature> features = new ArrayList<>();

    @ManyToMany(mappedBy = "posts",cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @Column(nullable = true)
    private Collection<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private Collection<Attachment> attachments = new ArrayList<>();

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private Collection<Feedback> feedbacks = new ArrayList<>();

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private Collection<Comment> comments = new ArrayList<>();
}
