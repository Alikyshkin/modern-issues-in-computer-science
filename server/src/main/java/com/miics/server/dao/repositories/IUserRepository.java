package com.miics.server.dao.repositories;

import com.miics.server.dao.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
}