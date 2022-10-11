package com.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.entities.Users;

public interface UserRepo extends JpaRepository<Users, Long> {
    Users findByUsername(String username);

    Users findByEmail(String email);
}
