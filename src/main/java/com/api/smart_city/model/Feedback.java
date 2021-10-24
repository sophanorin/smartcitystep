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
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String feedback;
    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.REMOVE},optional = true)
    private User feedbackBy;
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REMOVE},optional = true)
    private Post post;

}
