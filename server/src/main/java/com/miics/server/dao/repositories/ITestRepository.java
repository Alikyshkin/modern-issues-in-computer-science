package com.miics.server.dao.repositories;

import com.miics.server.dao.models.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITestRepository extends JpaRepository<Test, Long> {
}