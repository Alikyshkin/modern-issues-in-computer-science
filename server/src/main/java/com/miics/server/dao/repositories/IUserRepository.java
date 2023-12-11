package com.miics.server.dao.repositories;

import com.miics.server.dao.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("UserRepository")
public interface IUserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
}