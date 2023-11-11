package com.miics.server.dao.repositories;

import com.miics.server.dao.models.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFeedbackRepository extends JpaRepository<Feedback, Long> {
}