package com.api.smart_city.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String comment;
    @OneToOne(cascade = CascadeType.MERGE)
    private User commentBy;
    @ManyToOne(optional = true,cascade = {CascadeType.REMOVE})
    @JoinTable(
            name = "post_comments",
            joinColumns= @JoinColumn(name = "comment_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id")
    )
    private Post post;
}
