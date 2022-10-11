package com.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.entities.Token;

public interface TokenRepo extends JpaRepository<Token, Long> {
    Token findByToken(String token);
}
