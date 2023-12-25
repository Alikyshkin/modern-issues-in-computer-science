package com.miics.server.dao.models;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    protected String title;
    protected String content;
    protected String type;
    @ManyToOne
    protected Test test;
    @ElementCollection
    protected List<String> answers;
    @ElementCollection
    protected List<Boolean> isCorrect;

}
