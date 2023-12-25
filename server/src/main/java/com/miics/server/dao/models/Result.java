package com.miics.server.dao.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "results")
public class Result {
    @Id
    @GeneratedValue
    Long id;
    Long isuNumber;
    Long testId;
    String testTitle;
    int timeTaken;
    int correctAnswers;
    int wrongAnswers;
    int totalQuestions;
    @OneToMany
    List<UserAnswer> userAnswer;
}
