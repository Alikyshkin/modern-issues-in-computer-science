package com.miics.server.dao.repositories;

import com.miics.server.dao.models.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IResultRepository extends JpaRepository<Result, Long> {
    List<Result> findAllById (Long testId);
}