package com.miics.server.dao.repositories;

import com.miics.server.dao.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("QuestionRepositoryBean")
public interface IQuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findAllByTestId(Long testId);
}