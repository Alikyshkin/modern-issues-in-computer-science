package com.miics.server.dao.repositories;

import com.miics.server.dao.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
}