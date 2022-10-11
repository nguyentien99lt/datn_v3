package com.services;
import com.entities.Token;

public interface TokenService {
    Token createToken(Token token);

    Token findByToken(String token);
}
