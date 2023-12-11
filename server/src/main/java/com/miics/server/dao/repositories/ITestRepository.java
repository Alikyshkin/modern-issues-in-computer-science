package com.miics.server.dao.repositories;

import com.miics.server.dao.models.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("TestRepository")
public interface ITestRepository extends JpaRepository<Test, Long> {
}