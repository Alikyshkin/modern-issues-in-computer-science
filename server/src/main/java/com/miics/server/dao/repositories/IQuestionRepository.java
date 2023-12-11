package com.miics.server.dao.repositories;

import com.miics.server.dao.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("QuestionRepository")
public interface IQuestionRepository extends JpaRepository<Question, Long> {
}