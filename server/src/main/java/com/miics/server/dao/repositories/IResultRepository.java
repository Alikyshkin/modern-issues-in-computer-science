package com.miics.server.dao.repositories;

import com.miics.server.dao.models.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IResultRepository extends JpaRepository<Result, Long> {
}