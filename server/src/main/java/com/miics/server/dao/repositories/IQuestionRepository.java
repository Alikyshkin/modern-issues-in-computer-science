package com.miics.server.dao.repositories;

import com.miics.server.dao.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IQuestionRepository extends JpaRepository<Question, Long> {
}