package com.miics.server.dao.repositories;

import com.miics.server.dao.models.Test;
import com.miics.server.dao.models.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("UserAnswerRepositoryBean")
public interface IUserAnswerRepository extends JpaRepository<UserAnswer, Long> {
    List<UserAnswer> getUserAnswersByResultIsuNumber(Long id);
}
