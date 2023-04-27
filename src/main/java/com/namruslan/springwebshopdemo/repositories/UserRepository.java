package com.namruslan.springwebshopdemo.repositories;

import com.namruslan.springwebshopdemo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    boolean existsByUsername(String username);
    Optional<User> getUserByUsername(String username);
}
